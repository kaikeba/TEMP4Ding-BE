package com.kkb.temperature.controller;


import com.kkb.temperature.component.DingtalkComponent;
import com.kkb.temperature.constant.KkbResponse;
import com.kkb.temperature.constant.TemperatureStatusEnum;
import com.kkb.temperature.domain.DingTalkUser;
import com.kkb.temperature.service.IWhitelistService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/dingtalk")
public class DingTalkController {

    @Autowired
    private DingtalkComponent dingtalkComponent;

    @Autowired
    private IWhitelistService iWhitelistService;

    @GetMapping("/jsapi_ticket")
    public KkbResponse getJsApiTicket() {
        String ticket = dingtalkComponent.getJsApiTicket();
        if (StringUtils.isNotEmpty(ticket)) {
            Map map = new HashMap<>();
            map.put("ticket", ticket);
            return KkbResponse.success(map);
        }
        return KkbResponse.failure(TemperatureStatusEnum.JSAPI_TICKET_ERROR);
    }

    @GetMapping("/{code}")
    public KkbResponse getUserInfo(@PathVariable("code") String code) {
        DingTalkUser dingTalkUser = dingtalkComponent.getUserInfoByCode(code);
        if (dingTalkUser != null) {
            dingTalkUser.setAdmin(iWhitelistService.isWhitelist(dingTalkUser.getMobile()));
            // TODO 添加协议判断
            return KkbResponse.success(dingTalkUser);
        }
        return KkbResponse.failure(TemperatureStatusEnum.USER_INFO_ERROR);
    }

    @GetMapping("/isoperator")
    public KkbResponse isWhitelist(@RequestParam(name = "mobile") String mobile) {
        Map map = new HashMap<>();
        map.put("isAdmin", iWhitelistService.isWhitelist(mobile));
        return KkbResponse.success(map);
    }
}

