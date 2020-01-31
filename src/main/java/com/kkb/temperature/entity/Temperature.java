package com.kkb.temperature.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author kkb
 * @since 2020-01-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Temperature implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private String userId;

	private BigDecimal degrees;

	private String createBy;

	private Integer familyId;

	/**
	 * 1 员工录入  2 管理员录入
	 */
	private Integer enterType;

	private String departmentId;

	private String name;

	private String title;

	private String mobile;

	private LocalDateTime createTime;

	@TableField(exist = false)
	private String showTime;

	public static class EnterType {
		/**
		 * 员工录入
		 */
		public static final Integer EMPLOYEE_ENTER = 1;
		/**
		 * 管理员录入
		 */
		public static final Integer ADMIN_ENTER = 2;
	}

}
