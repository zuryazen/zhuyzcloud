package com.zhuyz.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单表
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
@TableName("T_BASE_PAYMENT")
public class Payment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("SERIAL")
    private String serial;

    @Override
    protected Serializable pkVal() {
        return super.getId();
    }

}
