package com.dsfa.platform.service.core.web.test.lsf.zhgl.service;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.exception.PlatformCoreException;

/**
 * 账户信息管理业务
 */
public interface IAccountManager {

    /**
     * 启用账号
     * @param id
     * @return
     * @throws PlatformCoreException
     */
    Result enableAccount(String id) throws PlatformCoreException;

    /**
     * 禁用账号
     * @param id
     * @return
     * @throws PlatformCoreException
     */
    Result disableAccount(String id) throws PlatformCoreException;

    /**
     * 重置密码
     * @param id
     * @return
     * @throws PlatformCoreException
     */
    Result resetPassword(String id) throws PlatformCoreException;

    /**
     * 随机获取一个合法的账户名
     */
    Result getRandomAccountName();

    /**
     * 判断用户是否注册过
     * @param personId
     * @return
     */
    boolean isRegistered(String personId);

    /**
     * 判断账号是否可用
     * @param str
     * @return
     */
    boolean isAccountNameDuplicate(String str);
}
