package com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.ext;

import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.service.ICoursewareService;
import com.dsfa.platform.starter.meta.core.ext.MetaAspect;
import com.dsfa.platform.starter.meta.core.ext.MetaExt;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName KjEditExt
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
@MetaAspect("test.lsf.nc.kjk.kjEdit")
public class KjEditExt extends MetaExt {
    @Autowired
    ICoursewareService coursewareService;

    /**
     * 保存按钮拦截
     * @param metaPoint
     * @param persistData
     */
    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) {
        coursewareService.beforePersistData(metaPoint, persistData);
    }
}
