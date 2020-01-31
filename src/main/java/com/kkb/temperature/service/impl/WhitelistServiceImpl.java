package com.kkb.temperature.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.temperature.entity.Whitelist;
import com.kkb.temperature.mapper.WhitelistMapper;
import com.kkb.temperature.service.IWhitelistService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kkb
 * @since 2020-01-26
 */
@Service
public class WhitelistServiceImpl extends ServiceImpl<WhitelistMapper, Whitelist> implements IWhitelistService {

	@Override
	public boolean isWhitelist(String mobile) {
		if(count(new QueryWrapper<Whitelist>().lambda().eq(Whitelist::getMobile , mobile)) > 0) {
			return true;
		}
		return false;
	}
}
