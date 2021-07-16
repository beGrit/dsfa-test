package com.dsfa.platform.service.core.web.test.lsf.rygl.ext;

import com.dsfa.platform.starter.meta.core.ext.MetaAspect;
import com.dsfa.platform.starter.meta.core.ext.MetaExt;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.meta.MetaData;
import com.dsfa.platform.starter.meta.core.model.query.InitModel;
import com.dsfa.platform.starter.meta.core.model.query.QueryModel;
import com.dsfa.platform.starter.meta.core.model.query.list.ListQueryModel;

@MetaAspect("test.lsf.rygl.commonPersonList")
public class CommonPersonListExt extends MetaExt {

    @Override
    public void beforeQueryData(MetaPoint metaPoint, QueryModel queryModel) {
        super.beforeQueryData(metaPoint, queryModel);
    }

    @Override
    public void beforeListData(MetaPoint metaPoint, ListQueryModel listQueryModel) {
        super.beforeListData(metaPoint, listQueryModel);
    }

    @Override
    public void beforeBuild(MetaPoint metaPoint, MetaData meta) {
        super.beforeBuild(metaPoint, meta);
    }

    @Override
    public void beforeInitData(MetaPoint metaPoint, InitModel initModel) {
        super.beforeInitData(metaPoint, initModel);
    }

    @Override
    public void afterInitData(MetaPoint metaPoint, InitModel initModel) {
        super.afterInitData(metaPoint, initModel);
    }


}
