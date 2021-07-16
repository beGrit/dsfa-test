package com.dsfa.platform.service.core.web.test.lsf.shgl.service.impl;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.rygl.model.Person;
import com.dsfa.platform.service.core.web.test.lsf.rygl.service.IPersonManager;
import com.dsfa.platform.service.core.web.test.lsf.shgl.metadata.AuditStatus;
import com.dsfa.platform.service.core.web.test.lsf.shgl.service.IAuditManager;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.IAccountManager;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.RegisterService;
import com.dsfa.platform.starter.db.jfinal.kit.Kv;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.Db;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.Record;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.SqlPara;
import com.dsfa.platform.starter.web.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditManagerImpl extends BaseService implements IAuditManager {
    private static final String SQL_KEY = "test.lsf.shgl.person.sql.";

    @Autowired
    IAccountManager accountManagementService;

    @Autowired
    RegisterService registerService;

    @Autowired
    IPersonManager iPersonManager;

    @Override
    public void commit(String id) throws PlatformCoreException {
        // 判断是否填写了账户信息
        Person person = iPersonManager.findOneById(id);
        if (person == null) {
            throw PlatformCoreException.create(500, "系统错误");
        }
        if (person.getStr("accountname") == null || person.getStr("nickname") == null) {// 未填写账户信息字段,抛出异常
            throw PlatformCoreException.create(500, "未填写账户信息字段, 不能提交审核");
        }

        AuditStatus currentStatus = this.getCurrentStatus(id);
        if (currentStatus == AuditStatus.NOT_COMMIT) { // 待提交状态
            int update = this.updateStatus(AuditStatus.NOT_COMMIT, AuditStatus.COMMIT, id);
            if (update != 1) { // 线程冲突
                throw PlatformCoreException.create(500, "数据已被其他人修改了,请刷新!");
            }

            // 业务正常执行完成并退出
            return;
        } else { // 状态有误,不是未提交状态,无法修改
            throw PlatformCoreException.create(500, "当前审核状态不是未提交");
        }
    }

    @Override
    public void admit(String id) throws PlatformCoreException {
        AuditStatus currentStatus = this.getCurrentStatus(id);
        if (currentStatus == AuditStatus.COMMIT) { // 保证当前状态无误
            // 调用账户模块的注册功能,对信息校验并入库
            registerService.register(id);

            int update = this.updateStatus(AuditStatus.COMMIT, AuditStatus.ADMIT, id);
            if (update != 1) { // 线程冲突
                throw PlatformCoreException.create(500, "数据已被其他人修改了,请刷新!");
            }

            // 业务正常执行完成并退出
            return;
        } else {
            throw PlatformCoreException.create(500, "当前审核状态不是已提交");
        }
    }

    @Override
    public void unAdmit(String id) throws PlatformCoreException {
        AuditStatus currentStatus = this.getCurrentStatus(id);
        if (currentStatus == AuditStatus.COMMIT) { // 保证当前状态无误
            int update = this.updateStatus(AuditStatus.COMMIT, AuditStatus.NOT_ADMIT, id);
            if (update != 1) {
                throw PlatformCoreException.create(500, "数据已被其他人修改了,请刷新!");
            }

            // 业务正常执行完成并退出
            return;
        } else {
            throw PlatformCoreException.create(500, "当前审核状态不是已提交");
        }
    }

    /**
     * 根据用户主键,获取当前用户的审核状态
     * (持久化:数据调取)
     * @param id 用户主键
     * @return null(获取失败) AuditStatus(当前状态)
     */
    private AuditStatus getCurrentStatus(String id) {
        SqlPara sqlPara = Db.getSqlPara(SQL_KEY + "findStausValueById", Kv.by("id", id));
        Record record = Db.findFirst(sqlPara);
        if (record == null) {
            return null;
        }
        Integer currentStatus = Integer.parseInt(record.get("status_value"));
        if (currentStatus == 0) {
            return AuditStatus.NOT_COMMIT;
        } else if (currentStatus == 1) {
            return AuditStatus.COMMIT;
        } else if (currentStatus == 2) {
            return AuditStatus.ADMIT;
        } else if (currentStatus == 3) {
            return AuditStatus.NOT_COMMIT;
        } else {
            return AuditStatus.UNKNOWN;
        }
    }

    /**
     * 状态转移
     * (持久化:数据修改)
     * @param oldAuditStatus 预期的旧状态(期望值)
     * @param newAuditStatus 希望转移过去的状态
     * @param id 记录的主键
     * @return 0(更新失败) 1(更新成功)
     */
    private int updateStatus(AuditStatus oldAuditStatus, AuditStatus newAuditStatus, String id) {
        Kv condition = Kv.create();
        condition.set("statusValue", newAuditStatus.getValue());
        condition.set("statusText", newAuditStatus.getText());
        condition.set("oldStatusValue", oldAuditStatus.getValue());
        condition.set("oldStatusText", oldAuditStatus.getText());
        condition.set("id", id);
        SqlPara sqlPara = Db.getSqlPara(SQL_KEY + "updateStatus", condition);
        int rtn = Db.update(sqlPara);
        return rtn;
    }
}
