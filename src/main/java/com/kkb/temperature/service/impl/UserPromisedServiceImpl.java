package com.kkb.temperature.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.temperature.constant.KkbResponse;
import com.kkb.temperature.entity.UserPromised;
import com.kkb.temperature.mapper.UserPromisedMapper;
import com.kkb.temperature.service.IUserPromisedService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kkb
 * @since 2020-01-27
 */
@Service
public class UserPromisedServiceImpl extends ServiceImpl<UserPromisedMapper, UserPromised> implements IUserPromisedService {

	@Override
	public KkbResponse createUserPromised(UserPromised userPromised) {
		UserPromised before = getOne(new QueryWrapper<UserPromised>()
			.lambda()
			.eq(UserPromised::getUserId, userPromised.getUserId())
			.eq(UserPromised::getIsPromised, true), false);
		if (before == null) {
			userPromised.setIsPromised(true);
			userPromised.setCreateTime(LocalDateTime.now());
			save(userPromised);
		}
		return KkbResponse.success();
	}

	@Override
	public Boolean isPromised(String userId) {
		UserPromised promised = getOne(new QueryWrapper<UserPromised>()
			.lambda()
			.eq(UserPromised::getIsPromised, true)
			.eq(UserPromised::getUserId, userId));
		return promised != null;
	}
}
