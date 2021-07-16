package com.dsfa.platform.service.core.web.test.lsf.rygl.ext;

import com.dsfa.platform.service.core.kit.checker.DataChecker;
import com.dsfa.platform.service.core.web.test.lsf.rygl.service.IPersonManager;
import com.dsfa.platform.starter.meta.core.ext.MetaAspect;
import com.dsfa.platform.starter.meta.core.ext.MetaExt;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@MetaAspect("test.lsf.rygl.addOneEdit")
public class PersonEditExt extends MetaExt {
    @Autowired
    IPersonManager iPersonManager;

    @Autowired
    @Qualifier(value = "PersonChecker")
    DataChecker personChecker;

    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) {
        super.beforePersistData(metaPoint, persistData);
        personChecker.customCheck(persistData);
    }
}
