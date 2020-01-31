package com.kkb.temperature.controller;


import com.kkb.temperature.constant.KkbPage;
import com.kkb.temperature.constant.KkbResponse;
import com.kkb.temperature.domain.TemperatureVO;
import com.kkb.temperature.service.ITemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kkb
 * @since 2020-01-26
 */
@RestController
public class TemperatureController {

    @Autowired
    private ITemperatureService iTemperatureService;

    @PostMapping("/employee/temperature")
    public KkbResponse employeeCreateTemperature(@RequestBody TemperatureVO temperatureVO) {
        return iTemperatureService.createTemperature(temperatureVO);
    }

    @PostMapping("/admin/temperature")
    public KkbResponse adminCreateTemperature(@RequestBody TemperatureVO temperatureVO) {
        return iTemperatureService.createTemperature(temperatureVO);
    }

    @PostMapping("/temperatures/{userId}")
    public KkbResponse listTemperatures(@PathVariable String userId,
                                        @RequestParam(value = "familyId", required = false) Integer familyId,
                                        @RequestBody KkbPage page) {
        return iTemperatureService.listPageTemperatures(userId, familyId, page);
    }


}

