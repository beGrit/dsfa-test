package com.dsfa.platform.service.core.web.test.lsf.rygl.model.base;

import com.dsfa.platform.starter.meta.base.DsfaBaseModel;

public class BasePerson<M extends BasePerson<M>> extends DsfaBaseModel<M> {
    public String getName() {
        return this.getStr("name");
    }

    public BasePerson<M> setName(String name) {
        M m = this.set("name", name);
        return m;
    }

    public String getDepartmentText() {
        return this.getStr("department_text");
    }

    public M setId(String id) {
        return this.set("test_lsf_rygl_id", id);
    }
}
