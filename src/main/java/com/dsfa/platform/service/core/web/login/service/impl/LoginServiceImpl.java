package com.dsfa.platform.service.core.web.login.service.impl;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.exception.Code;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.session.UserSession;
import com.dsfa.platform.service.core.web.login.model.dto.LoginInfo;
import com.dsfa.platform.service.core.web.login.service.ILoginService;
import com.dsfa.platform.starter.meta.api.oua2.pojo.po.DsfaOuaUser;
import com.dsfa.platform.starter.meta.part.oua2.service.IUserService;
import com.dsfa.platform.starter.db.jfinal.kit.Kv;
import com.dsfa.platform.starter.session.ISessionHolder;
import com.dsfa.platform.starter.web.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 文进
 */
@Service
public class LoginServiceImpl extends BaseService implements ILoginService {

    private static final String SQL_KEY = "web.login.sql.";


    @Autowired
    private UserSession userSession;

    @Autowired
    IUserService userService;

    @Autowired
    ISessionHolder sessionHolder;

    /**
     * 用户登录
     * @return
     */
    @Override
    public Result userLogin(LoginInfo loginInfo) {
        DsfaOuaUser user =userService.userLogin(loginInfo.getUserName(),loginInfo.getPassword());
        if(user==null){
            throw PlatformCoreException.create(Code.PWD_ACCOUNT_ERROR);
        }
        String creditCode = user.get("credit_code");
        userSession.put(UserSession.OT_SOCIAL_CREDIT_CODE, creditCode);
        String userId = user.getId();
        String userName = user.getName();
        return success(Kv.create()
                        .set("token", sessionHolder.getToken())
                        .set("userId", userId)
                        .set("userName", userName));

    }


    /**
     * 清空cookie
     */
    @Override
    public void logOut() {
        userService.logOut();
    }



}
