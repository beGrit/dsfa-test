package com.dsfa.platform.service.core.web.login.service;


import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.login.model.dto.LoginInfo;

/**
 * 登录service
 * @author wenjin
 */
public interface ILoginService {

    /**
     * 用户登录
     * @param loginInfo
     * @return
     */
    Result userLogin(LoginInfo loginInfo);


    /**
     * 清空cookies
     */
    void logOut();

}
