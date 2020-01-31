package com.kkb.temperature.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.temperature.component.DingtalkComponent;
import com.kkb.temperature.constant.KkbPage;
import com.kkb.temperature.constant.KkbResponse;
import com.kkb.temperature.domain.DingTalkUser;
import com.kkb.temperature.domain.TemperatureVO;
import com.kkb.temperature.entity.Temperature;
import com.kkb.temperature.mapper.TemperatureMapper;
import com.kkb.temperature.service.ITemperatureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
public class TemperatureServiceImpl extends ServiceImpl<TemperatureMapper, Temperature> implements ITemperatureService {

	@Autowired
	private DingtalkComponent dingtalkComponent;

	@Override
	public KkbResponse createTemperature(TemperatureVO temperatureVO) {

		DingTalkUser dingTalkUser = dingtalkComponent.getUserInfo(temperatureVO.getUserId());

		Temperature temperature = Temperature.builder()
			.createTime(LocalDateTime.now())
			.degrees(temperatureVO.getDegrees())
			.userId(temperatureVO.getUserId())
			.familyId(temperatureVO.getFamilyId())
			.name(dingTalkUser.getName())
			.departmentId(dingTalkUser.getDepartment())
			.title(dingTalkUser.getTitle())
			.mobile(dingTalkUser.getMobile())
			.build();
		boolean isAdmin = StringUtils.isNotEmpty(temperatureVO.getAdminUserId());
		if (isAdmin) {
			temperature.setCreateBy(temperatureVO.getAdminUserId());
			temperature.setEnterType(Temperature.EnterType.ADMIN_ENTER);
		} else {
			temperature.setCreateBy(temperatureVO.getUserId());
			temperature.setEnterType(Temperature.EnterType.EMPLOYEE_ENTER);
		}
		save(temperature);
		return KkbResponse.success();
	}

	@Override
	public KkbResponse listTemperatures(String userId) {
		List<Temperature> temperatures = list(new QueryWrapper<Temperature>()
			.lambda()
			.eq(Temperature::getUserId, userId)
			.orderByDesc(Temperature::getCreateTime));
		return KkbResponse.success(temperatures);
	}

	@Override
	public KkbResponse listPageTemperatures(String userId, Integer familyId, KkbPage page) {
		IPage iPage;
		// 查询自己
		if (familyId == null) {
			iPage = baseMapper.selectPage(page, new QueryWrapper<Temperature>()
				.lambda()
				.eq(Temperature::getUserId, userId)
				.isNull(Temperature::getFamilyId)
				.orderByDesc(Temperature::getCreateTime));
		} else {
			// 查询亲属
			iPage = baseMapper.selectPage(page, new QueryWrapper<Temperature>()
				.lambda()
				.eq(Temperature::getUserId, userId)
				.eq(Temperature::getFamilyId, familyId)
				.orderByDesc(Temperature::getCreateTime));
		}
		List<Temperature> data = iPage.getRecords();
		data.forEach(temperature -> {
			int daysNum = Period.between(LocalDate.now(), temperature.getCreateTime().toLocalDate()).getDays();
			if (daysNum == 0) {
				temperature.setShowTime("今天");
			} else if (daysNum == -1) {
				temperature.setShowTime("昨天");
			} else {
				temperature.setShowTime(temperature.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
		});
		iPage.setRecords(data);
		return KkbResponse.success(iPage);
	}
}
