package com.dsfa.platform.service.core.web.test.lsf.zhgl.controller;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.IAccountManager;
import com.dsfa.platform.starter.meta.core.base.DsfaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pc/test/lsf/zhgl")
public class AccountListIndexController extends DsfaBaseController {
    @Autowired
    IAccountManager service;

    @RequestMapping(value = "/enabled", method = RequestMethod.POST)
    public Result enabled(String id) {
        return service.enableAccount(id);
    }

    @RequestMapping(value = "/disEnabled", method = RequestMethod.POST)
    public Result disEnabled(String id) {
        return service.disableAccount(id);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Result restPassword(String id) {
        return service.resetPassword(id);
    }

    @RequestMapping(value = "/randomName", method = RequestMethod.GET)
    public Result getRandomName() {
        return service.getRandomAccountName();
    }
}
