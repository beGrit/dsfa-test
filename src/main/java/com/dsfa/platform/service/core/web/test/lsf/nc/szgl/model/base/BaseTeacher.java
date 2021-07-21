package com.dsfa.platform.service.core.web.test.lsf.nc.szgl.model.base;

import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.model.Courseware;
import com.dsfa.platform.starter.meta.base.DsfaBaseModel;

import java.util.List;

/**
 * @ClassName BaseTeacher
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
public class BaseTeacher<M extends BaseTeacher<M>> extends DsfaBaseModel<M> {
    public String getName() {
        return this.getStr("name");
    }

    public M setName(String name) {
        return this.set("name", name);
    }

    public List<Courseware> getCoursewares(String id) {
        return Courseware.DAO.find("select * from test_lsf_nc_kjgl where ");
    }
}
