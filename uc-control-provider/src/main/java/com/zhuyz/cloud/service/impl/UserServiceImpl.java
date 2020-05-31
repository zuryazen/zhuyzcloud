package com.zhuyz.cloud.service.impl;

import com.zhuyz.cloud.entity.User;
import com.zhuyz.cloud.mapper.UserMapper;
import com.zhuyz.cloud.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhuyz
 * @since 2020-05-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

}
