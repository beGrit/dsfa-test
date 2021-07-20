package com.dsfa.platform.service.core.web.test.lsf.zhgl.model;

import com.dsfa.platform.service.core.web.test.lsf.zhgl.model.base.BaseAccount;
import com.dsfa.platform.starter.db.jfinal.tablebind.TableBind;

import java.io.Serializable;

@TableBind(
        tableName = "test_lsf_zhgl",
        pkName = "test_lsf_zhgl_id"
)
public class Account extends BaseAccount<Account> implements Serializable {
    public static final Account DAO = (Account) (new Account()).dao();

    public Account() {
    }
}
