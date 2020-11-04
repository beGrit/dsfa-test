package com.dsfa.platform.service.core.session;

import com.dsfa.platform.starter.meta.part.oua2.model.po.DsfaOuaUser;
import com.dsfa.platform.starter.meta.session.UserInfoHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户信息
 * @author wenjin
 */
@Component
public class UserSession {

    public static final String OT_UNIT = "otUnit";

    public static final String OT_UNIT_ID = "ot_unit_id";

    public static final String OT_UNIT_NAME = "ot_unit_name";

    public static final String OT_SOCIAL_CREDIT_CODE = "ot_social_credit_code";

    @Autowired
    UserInfoHolder userInfoHolder;

    /**
     * 获取当前登陆人ID
     * @return 用户ID
     */
    public String getUserId(){
        return userInfoHolder.getUserId();
    }
    /**
     * 获取当前登陆人姓名
     * @return 用户姓名
     */
    public String getUserName(){
        return userInfoHolder.getUserName();
    }

    public DsfaOuaUser getUser(){
        return userInfoHolder.getUser();
    }

    /**
     * 是否存在角色
     * @param roleId
     * @return
     */
    public Boolean hasRole(String roleId){
        return userInfoHolder.hasRole(roleId);
    }

    public UserSession put(String key,Object value){
        userInfoHolder.put(key,value);
        return this;
    }



}
