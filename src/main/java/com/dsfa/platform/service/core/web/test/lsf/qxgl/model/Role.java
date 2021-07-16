package com.dsfa.platform.service.core.web.test.lsf.qxgl.model;

import com.dsfa.platform.starter.meta.base.DsfaBaseModel;

import java.io.Serializable;

public class Role extends DsfaBaseModel<Role> implements Serializable {
    public static final Role DAO = (Role) (new Role()).dao();

    public Role() {
    }
}
