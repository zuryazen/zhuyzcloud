package com.zhuyz.cloud.mapper;

import com.zhuyz.cloud.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhuyz
 * @since 2020-05-30
 */
public interface UserMapper extends BaseMapper<User> {

}
