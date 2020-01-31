package com.kkb.temperature.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author iisheng
 * @date 2020/01/26 18:17:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureVO {

	private Integer id;
	private LocalDateTime createTime;
	private Integer enterType;

	/**
	 * 钉钉用户id
	 */
	@Valid
	@NotEmpty(message = "用户id不能为空")
	private String userId;
	/**
	 * 管理员用户id
	 */
	private String adminUserId;

	/**
	 * 体温 温度
	 */
	private BigDecimal degrees;

	/**
	 * 家属表 id
	 */
	private Integer familyId;
}
