package com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.model;

import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.model.base.BaseCourseware;
import com.dsfa.platform.starter.db.jfinal.tablebind.TableBind;

import java.io.Serializable;

/**
 * @ClassName Courseware 课件
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
@TableBind(
        tableName = "test_lsf_nc_kjgl",
        pkName = "test_lsf_nc_kjgl_id"
)
public class Courseware extends BaseCourseware<Courseware> implements Serializable {
    public static final Courseware DAO = (Courseware) (new Courseware().dao());
}
