package com.dsfa.platform.service.core.exception;

import cn.hutool.core.util.ObjectUtil;
import com.dsfa.platform.sdk.common.kit.StrKit;

/**
 * @author wenjin
 */
public enum Code {

    /**
     *
     */
    NOT_LOGIN(40000,"会话超时或未登录"),
    LOST_EFFICACY(30001,"无效账户"),
    BLOCK_UP(30002,"账户已停用"),
    USER_INFO_EXCEPTION(30003,"用户信息异常"),
    PWD_ACCOUNT_ERROR(30004,"账号或密码错误")
    ;

    /**
     *  状态码
     */
    private int state;
    /**
     * 消息内容
     */
    private String msg;

    Code(int state, String msg){
        this.state = state;
        this.msg = msg;
    }

    public int state(){
        return this.state;
    }

    public String msg(){
        return this.msg;
    }

    public void hasThrow(boolean flag) {
        if (flag) {
            throw PlatformCoreException.create(this);
        }
    }

    public void notBlank(Object obj) {
        hasThrow(ObjectUtil.isNull(obj) || StrKit.isBlank(obj.toString()));
    }

    @Override
    public String toString(){
        return this.msg;
    }
}
