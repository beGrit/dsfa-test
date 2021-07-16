package com.dsfa.platform.service.core.web.login.controller;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.login.model.dto.LoginInfo;
import com.dsfa.platform.service.core.web.login.service.ILoginService;
import com.dsfa.platform.starter.meta.core.base.DsfaBaseController;
import com.dsfa.platform.starter.session.AuthIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


/**
 * @author wenjin
 */
@RestController
@RequestMapping("login")
public class LoginController extends DsfaBaseController {

    @Autowired
    private ILoginService userService;


    /**
     * <pre>
     *     管理员登陆
     * </pre>
     *
     * @return
     */
    @AuthIgnore
    @PostMapping("login")
    public Result login(LoginInfo loginInfo) {
        return userService.userLogin(loginInfo);
    }


    /**
     * 退出登录
     * @return
     */
    @PostMapping("loginOut")
    public Result loginOut(HttpServletRequest request) {
        userService.logOut();
        return success();
    }


}
