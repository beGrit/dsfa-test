package com.dsfa.platform.service.core.web.test.lsf.jsgl.controller;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.test.lsf.jsgl.service.IActorManager;
import com.dsfa.platform.starter.meta.core.base.DsfaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pc/test/lsf/jsgl/actorList")
public class ActorListIndexController extends DsfaBaseController {
    @Autowired
    IActorManager service;

    @PostMapping(value = "disable")
    public Result disableActor(@RequestParam("actorId") String id) {
        service.disableActor(id);
        return success().setMessage(id + ":禁用成功");
    }

    @PostMapping(value = "enable")
    public Result enableActor(@RequestParam("actorId") String id) {
        service.enableActor(id);
        return success().setMessage(id + ":启用成功");
    }
}
