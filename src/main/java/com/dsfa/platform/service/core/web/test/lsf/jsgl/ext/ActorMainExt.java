package com.dsfa.platform.service.core.web.test.lsf.jsgl.ext;

import com.dsfa.platform.starter.meta.core.ext.MetaAspect;
import com.dsfa.platform.starter.meta.core.ext.MetaExt;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import com.dsfa.platform.service.core.kit.checker.DataChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@MetaAspect("test.lsf.jsgl.actorMain")
public class ActorMainExt extends MetaExt {

    @Autowired
    @Qualifier(value = "ActorChecker")
    DataChecker dataChecker;

    @Autowired
    ActorMainExtService service;

    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) {
        super.beforePersistData(metaPoint, persistData);

        // 表单字段校验
        dataChecker.customCheck(persistData);

        // 校验列表是否无误
        service.checkRoleList(persistData);
        service.checkAccountList(persistData);

        service.initialActorOrder(persistData);
    }
}
