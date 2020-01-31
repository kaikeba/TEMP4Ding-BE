package com.kkb.temperature.controller;


import com.kkb.temperature.constant.KkbResponse;
import com.kkb.temperature.entity.Family;
import com.kkb.temperature.service.IFamilyService;
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
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private IFamilyService iFamilyService;

    @PostMapping
    public KkbResponse create(@RequestBody Family family) {
        if (iFamilyService.create(family)) {
            return KkbResponse.success();
        }
        return KkbResponse.failure();
    }

    @PutMapping("/{id}")
    public KkbResponse update(@PathVariable Integer id, @RequestBody Family family) {
        if (iFamilyService.update(id, family)) {
            return KkbResponse.success();
        }
        return KkbResponse.failure();
    }

    @DeleteMapping("/{id}")
    public KkbResponse delete(@PathVariable Integer id) {
        if (iFamilyService.delete(id)) {
            return KkbResponse.success();
        }
        return KkbResponse.failure();
    }

    @GetMapping("/{userId}")
    public KkbResponse list(@PathVariable String userId) {
        return KkbResponse.success(iFamilyService.list(userId));
    }
}

