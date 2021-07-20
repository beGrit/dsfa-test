package com.dsfa.platform.service.core.web.test.lsf.zhgl.model.base;

import com.dsfa.platform.starter.meta.base.DsfaBaseModel;

public class BaseAccount<M extends BaseAccount<M>> extends DsfaBaseModel<M> {
    public String getName() {
        return this.getStr("name");
    }

    public M setName(String name) {
        return this.set("name", name);
    }

    public String getDepartmentText() {
        return this.getStr("department_text");
    }

    public M setId(String id) {
        return this.set("test_lsf_rygl_id", id);
    }

    public String getAvatar() {
        return this.getStr("avatar");
    }

    public M setAvatar(String avatar) {
        return this.set("avatar", avatar);
    }
}
