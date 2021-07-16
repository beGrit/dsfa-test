package com.dsfa.platform.service.core.web.test.lsf.jsgl.service.impl;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.jsgl.metadata.ActorEnableStatus;
import com.dsfa.platform.service.core.web.test.lsf.jsgl.service.IActorManager;
import com.dsfa.platform.starter.db.jfinal.kit.Kv;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.Db;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.SqlPara;
import com.dsfa.platform.starter.web.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActorManagerImpl extends BaseService implements IActorManager {
    private static final String ACTOR_SQL_KEY = "test.lsf.jsgl.actor.sql.";

    @Override
    @Transactional
    public void disableActor(String id) throws PlatformCoreException {
        updateEnabledStatus(ActorEnableStatus.DISABLED, ActorEnableStatus.ENABLED, id);
    }

    @Override
    @Transactional
    public void enableActor(String id) throws PlatformCoreException {
        updateEnabledStatus(ActorEnableStatus.ENABLED, ActorEnableStatus.DISABLED, id);
    }

    /**
     * 转移启用状态
     * @param newStatus 转移后的状态
     * @param oldStatus 期望旧状态
     * @param id
     * @throws PlatformCoreException
     */
    private void updateEnabledStatus(ActorEnableStatus newStatus, ActorEnableStatus oldStatus, String id) throws PlatformCoreException {
        Kv condition = Kv.create();
        condition.set("newStatusText", newStatus.getText());
        condition.set("newStatusValue", newStatus.getValue());
        condition.set("id", id);
        condition.set("oldStatusText", oldStatus.getText());
        condition.set("oldStatusValue", oldStatus.getValue());
        SqlPara sqlPara = Db.getSqlPara(ACTOR_SQL_KEY + "updateEnabledStatus", condition);
        int rtn = Db.update(sqlPara);
        if (rtn != 1) {
            throw PlatformCoreException.create(500, "更新启用状态失败");
        }
    }
}
