package com.kkb.temperature.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
public class Family implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private String userId;

	private String name;

	/**
	 * 1 父母  2 夫妻 3 儿女 4 恋人 5 室友 6 朋友  7 其他
	 */
	private Integer members;

	private LocalDateTime createTime;

	private String mobile;

	/**
	 * 0 无效 1 有效
	 */
	@JsonIgnore
	private Integer status;

}
