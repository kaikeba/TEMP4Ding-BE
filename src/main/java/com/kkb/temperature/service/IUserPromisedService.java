package com.kkb.temperature.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.temperature.constant.KkbResponse;
import com.kkb.temperature.entity.UserPromised;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kkb
 * @since 2020-01-27
 */
public interface IUserPromisedService extends IService<UserPromised> {

	KkbResponse createUserPromised(UserPromised userPromised);

	Boolean isPromised(String userId);
}
