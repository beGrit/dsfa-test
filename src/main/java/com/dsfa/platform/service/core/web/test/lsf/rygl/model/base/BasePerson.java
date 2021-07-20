package com.dsfa.platform.service.core.web.test.lsf.rygl.model.base;

import com.dsfa.platform.starter.meta.base.DsfaBaseModel;

public class BasePerson<M extends BasePerson<M>> extends DsfaBaseModel<M> {
    public String getName() {
        return this.getStr("name");
    }

    public M setName(String name) {
        return this.set("name", name);
    }

    public M setDepartmentText(String departmentText) {
        return this.setDepartmentText("department_text");
    }

    public String getDepartmentText() {
        return this.getStr("department_text");
    }

    public M setTestLsfRyglId(String id) {
        return this.set("test_lsf_rygl_id", id);
    }

    public M getTestLsfRyglId() {
        return this.get("test_lsf_rygl_id");
    }
}
