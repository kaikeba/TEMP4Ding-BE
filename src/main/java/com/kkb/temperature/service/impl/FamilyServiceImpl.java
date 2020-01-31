package com.kkb.temperature.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.temperature.constant.CommonConstants;
import com.kkb.temperature.constant.TemperatureStatusEnum;
import com.kkb.temperature.entity.Family;
import com.kkb.temperature.exception.KkbBusinessException;
import com.kkb.temperature.mapper.FamilyMapper;
import com.kkb.temperature.service.IFamilyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kkb
 * @since 2020-01-26
 */
@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements IFamilyService {

    @Override
    public Boolean create(Family family) {

        Family existing = getOne(new QueryWrapper<Family>().lambda()
                .eq(Family::getUserId, family.getUserId())
                .eq(Family::getName, family.getName())
                .eq(Family::getMembers, family.getMembers())
                .eq(Family::getStatus, CommonConstants.ENABLE));

        if (existing != null) {
            throw new KkbBusinessException(TemperatureStatusEnum.EXIST_MEMBERS);
        }

        family.setCreateTime(LocalDateTime.now());
        family.setStatus(CommonConstants.ENABLE);

        return save(family);
    }

    @Override
    public Boolean update(Integer id, Family family) {
        family.setId(id);
        return updateById(family);
    }


    @Override
    public Boolean delete(Integer id) {
        Family family = new Family();
        family.setId(id);
        family.setStatus(CommonConstants.DISABLE);
        return updateById(family);
    }

    @Override
    public List<Family> list(String userId) {
        return list(new QueryWrapper<Family>().lambda()
                .eq(Family::getUserId, userId)
                .eq(Family::getStatus, CommonConstants.ENABLE)
                .orderByDesc(Family::getCreateTime));
    }
}
