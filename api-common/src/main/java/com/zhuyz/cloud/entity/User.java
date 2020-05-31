package com.zhuyz.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zhuyz
 * @since 2020-05-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("T_BASE_USER")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名称")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "用户地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "用户性别")
    @TableField("SEX")
    private Integer sex;

    @Override
    protected Serializable pkVal() {
        return super.getId();
    }

}
