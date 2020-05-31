package com.zhuyz.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhuyz
 * @date 2020/5/30 0030 15:13
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity extends Model {

    @ApiModelProperty(value = "主键id")
    @TableId("ID")
    private String id;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_USER")
    private String createUser;
}
