package com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.controller;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.service.ICoursewareService;
import com.dsfa.platform.starter.meta.core.base.DsfaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ListIndexController
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/21
 **/
@RestController
@RequestMapping("/test/lsf/nc/kjgl/kjList")
public class KjListController extends DsfaBaseController {
    @Autowired
    ICoursewareService coursewareService;

    @PostMapping("/enable")
    public Result enable(@RequestParam String id) {
        coursewareService.enable(id);
        return success();
    }

    @PostMapping("/disable")
    public Result disable(@RequestParam String id) {
        coursewareService.disable(id);
        return success();
    }
}
