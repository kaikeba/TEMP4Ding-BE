package com.kkb.temperature.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.temperature.entity.Family;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kkb
 * @since 2020-01-26
 */
public interface IFamilyService extends IService<Family> {

	Boolean create(Family family);

	Boolean update(Integer id, Family family);

	Boolean delete(Integer id);

	List<Family> list(String userId);
}
