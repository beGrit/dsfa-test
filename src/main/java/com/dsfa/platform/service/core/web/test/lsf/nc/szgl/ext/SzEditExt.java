package com.dsfa.platform.service.core.web.test.lsf.nc.szgl.ext;

import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.service.ITeacherService;
import com.dsfa.platform.starter.meta.core.ext.MetaAspect;
import com.dsfa.platform.starter.meta.core.ext.MetaExt;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName SzEditExt
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
@MetaAspect("test.lsf.nc.szgl.szEdit")
public class SzEditExt extends MetaExt {
    @Autowired
    ITeacherService teacherService;

    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) {

    }
}
