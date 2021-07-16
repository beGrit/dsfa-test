package com.dsfa.platform.service.core.web.test.lsf.zhgl.ext;

import com.dsfa.platform.service.core.kit.checker.DataChecker;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.PasswordDEService;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.RegisterService;
import com.dsfa.platform.starter.meta.core.ext.MetaAspect;
import com.dsfa.platform.starter.meta.core.ext.MetaExt;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import com.dsfa.platform.starter.meta.core.model.query.QueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@MetaAspect("test.lsf.zhgl.accountEdit")
public class AccountEditExt extends MetaExt {
    @Autowired
    RegisterService registerService;

    @Autowired
    @Qualifier(value = "accountChecker")
    DataChecker accountChecker;

    @Autowired
    PasswordDEService passwordDEService;

    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) {
        super.beforePersistData(metaPoint, persistData);

        // 表单字段格式校验
        accountChecker.customCheck(persistData);
        // 判断是否能够注册账号
        registerService.canRegister(persistData);
        // 密码加密
        passwordDEService.encode(persistData);

    }

    @Override
    public void afterQueryData(MetaPoint metaPoint, QueryModel queryModel) {
        super.afterQueryData(metaPoint, queryModel);
    }

    @Override
    public void afterReturnQueryData(MetaPoint metaPoint, QueryModel queryModel) {
        super.afterReturnQueryData(metaPoint, queryModel);
    }
}
