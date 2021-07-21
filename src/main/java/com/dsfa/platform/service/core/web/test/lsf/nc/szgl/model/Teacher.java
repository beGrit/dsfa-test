package com.dsfa.platform.service.core.web.test.lsf.nc.szgl.model;

import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.model.base.BaseTeacher;
import com.dsfa.platform.starter.db.jfinal.tablebind.TableBind;

import java.io.Serializable;

/**
 * @ClassName Teacher 教师
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
@TableBind(
        tableName = "test_lsf_nc_szgl",
        pkName = "test_lsf_nc_szgl_id"
)
public class Teacher extends BaseTeacher<Teacher> implements Serializable {
    public static final Teacher DAO = (Teacher) (new Teacher().dao());

    public Teacher() {
    }
}
