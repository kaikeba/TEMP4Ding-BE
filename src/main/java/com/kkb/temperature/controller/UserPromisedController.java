package com.kkb.temperature.controller;


import com.kkb.temperature.constant.KkbResponse;
import com.kkb.temperature.entity.UserPromised;
import com.kkb.temperature.service.IUserPromisedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kkb
 * @since 2020-01-26
 */
@RestController
public class UserPromisedController {

    @Autowired
    private IUserPromisedService iUserPromisedService;

    @PostMapping("/promise")
    public KkbResponse userMakePromise(@RequestBody UserPromised userPromised) {
        return iUserPromisedService.createUserPromised(userPromised);
    }

    @GetMapping("/isPromised/{userId}")
    public KkbResponse isPromised(@PathVariable String userId) {
        Map map = new HashMap();
        map.put("isPromised", iUserPromisedService.isPromised(userId));
        return KkbResponse.success(map);
    }

}

