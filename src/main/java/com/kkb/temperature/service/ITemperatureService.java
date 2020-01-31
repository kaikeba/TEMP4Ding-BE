package com.kkb.temperature.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.temperature.constant.KkbPage;
import com.kkb.temperature.constant.KkbResponse;
import com.kkb.temperature.domain.TemperatureVO;
import com.kkb.temperature.entity.Temperature;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author kkb
 * @since 2020-01-26
 */
public interface ITemperatureService extends IService<Temperature> {
	KkbResponse createTemperature(TemperatureVO temperatureVO);

	KkbResponse listTemperatures(String userId);

	KkbResponse listPageTemperatures(String userId, Integer familyId, KkbPage page);
}
