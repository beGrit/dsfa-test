package com.dsfa.platform.service.core.web.test.lsf.rygl.service;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.rygl.model.Person;

/**
 * 人员(Person)管理业务
 */
public interface IPersonManager {
    /**
     * 根据主键获取Person
     * @param id 主键(personId)
     * @return null(表示参数信息有误,没有找到相关用户) Person(主键为id的用户)
     */
    Person findOneById(String id);

//    void persistCheck() throws PlatformCoreException;
}
