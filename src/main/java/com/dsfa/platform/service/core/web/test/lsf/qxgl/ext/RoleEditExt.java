package com.dsfa.platform.service.core.web.test.lsf.qxgl.ext;

import com.dsfa.platform.service.core.kit.checker.DataChecker;
import com.dsfa.platform.starter.meta.core.ext.MetaAspect;
import com.dsfa.platform.starter.meta.core.ext.MetaExt;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@MetaAspect("test.lsf.qxgl.roleEdit")
public class RoleEditExt extends MetaExt {
    @Autowired
    @Qualifier(value = "roleChecker")
    DataChecker roleChecker;

    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) {
        super.beforePersistData(metaPoint, persistData);
        roleChecker.customCheck(persistData);
    }
}
