package com.dsfa.platform.service.core.web.test.lsf.shgl.controller;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.test.lsf.shgl.service.IAuditManager;
import com.dsfa.platform.starter.meta.core.base.DsfaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 审核列表页面的控制器
 */
@RestController
@RequestMapping("/pc/test/lsf/shgl")
public class AuditListIndexController extends DsfaBaseController { // 审核管理

    @Autowired
    IAuditManager service;

    @RequestMapping(value = "commit", method = RequestMethod.POST)
    public Result commit(@RequestParam("id") String id) {
        service.commit(id);
        return success().setMessage("提交审核操作成功");
    }

    @RequestMapping(value = "admit", method = RequestMethod.POST)
    public Result admit(@RequestParam("id") String id) { // id(人员id)
        service.admit(id);
        return success().setMessage("同意操作成功");
    }

    @RequestMapping(value = "unAdmit", method = RequestMethod.POST)
    public Result unAdmit(@RequestParam("id") String id) {
        service.unAdmit(id);
        return success().setMessage("不同意操作成功");
    }
}
