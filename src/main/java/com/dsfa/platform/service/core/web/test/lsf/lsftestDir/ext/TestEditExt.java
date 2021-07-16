package com.dsfa.platform.service.core.web.test.lsf.lsftestDir.ext;

import com.dsfa.platform.starter.meta.core.ext.MetaAspect;
import com.dsfa.platform.starter.meta.core.ext.MetaExt;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;

@MetaAspect("test.lsf.lsftestDir.testEdit")
public class TestEditExt extends MetaExt {
    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) {
        super.beforePersistData(metaPoint, persistData);
    }
}
