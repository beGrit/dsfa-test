package com.dsfa.platform.service.core.web.test.lsf.jsgl.service;

import com.dsfa.platform.service.core.exception.PlatformCoreException;

/**
 * 角色管理
 */
public interface IActorManager {
    /**
     * 禁用角色
     * @param id roleId(主键)
     * @throws PlatformCoreException
     */
    void disableActor(String id) throws PlatformCoreException;

    /**
     * 启用角色
     * @param id roleId(主键)
     * @throws PlatformCoreException
     */
    void enableActor(String id) throws PlatformCoreException;
}
