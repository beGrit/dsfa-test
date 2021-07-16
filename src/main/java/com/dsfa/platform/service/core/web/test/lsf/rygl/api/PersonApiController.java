package com.dsfa.platform.service.core.web.test.lsf.rygl.api;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.test.lsf.rygl.model.Person;
import com.dsfa.platform.service.core.web.test.lsf.rygl.service.IPersonManager;
import com.dsfa.platform.starter.meta.core.base.DsfaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/com/test/web/lsf/rygl/api")
public class PersonApiController extends DsfaBaseController {

    @Autowired
    IPersonManager service;

    @GetMapping(value = "person")
    public Result findPersonById(@RequestParam("id") String id) {
        Person person  = service.findOneById(id);
        return success(person);
    }
}
