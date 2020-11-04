package com.dsfa.platform.service.core.web.login.model.dto;


import com.dsfa.platform.starter.db.jfinal.kit.Kv;
import com.dsfa.platform.starter.web.base.Base;

/**
 * @ClassName LoginInfo
 * @Author 文进
 * @Date 2019/11/24 下午3:21
 * @Version 1.0.0
 * @Description TODO
 **/
public class LoginInfo extends Base {

    private static final long serialVersionUID = -6429914135511311752L;
    /**
     *  万能验证码
     */
    public static final String CODE = "good";
    /**
     * 账号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 是否为固定code
     * @return
     */
    public boolean isFinalCode(){
        return this.getCode().equalsIgnoreCase(CODE);
    }

    public Kv params(){
        return Kv.by("loginName",userName).set("password",password);
    }
}
