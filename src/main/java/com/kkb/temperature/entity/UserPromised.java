package com.kkb.temperature.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author kkb
 * @since 2020-01-27
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPromised implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 钉钉用户id
     */
	@Valid
	@NotEmpty(message = "用户id不能为空")
    private String userId;

    /**
     * 是否签了保证书
     */
    private Boolean isPromised;

    private LocalDateTime createTime;

}
