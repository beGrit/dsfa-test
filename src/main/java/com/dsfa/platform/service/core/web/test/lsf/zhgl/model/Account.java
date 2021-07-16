package com.dsfa.platform.service.core.web.test.lsf.zhgl.model;

import com.dsfa.platform.starter.meta.base.DsfaBaseModel;

import java.io.Serializable;

public class Account extends DsfaBaseModel<Account> implements Serializable {
    public static final Account DAO = (Account) (new Account()).dao();

    public Account() {
    }
}
