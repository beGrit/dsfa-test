package com.dsfa.platform.service.core.web.test.lsf.nc.bqgl.service;

import com.dsfa.platform.service.core.exception.PlatformCoreException;

/**
 * @ClassName ITagService
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/21
 **/
public interface ITagService {
    /**
     * 启用标签
     * @param id
     * @throws PlatformCoreException
     */
    void enable(String id) throws PlatformCoreException;

    /**
     * 禁用标签
     * @param id
     * @throws PlatformCoreException
     */
    void disable(String id) throws PlatformCoreException;
}
