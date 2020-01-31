package com.kkb.temperature.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.temperature.entity.Whitelist;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kkb
 * @since 2020-01-26
 */
public interface IWhitelistService extends IService<Whitelist> {

	boolean isWhitelist(String mobile);
}
