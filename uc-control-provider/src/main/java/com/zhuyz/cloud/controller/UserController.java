package com.zhuyz.cloud.controller;


import com.zhuyz.cloud.common.utils.ResponseEntity;
import com.zhuyz.cloud.common.utils.UUIDUtils;
import com.zhuyz.cloud.entity.User;
import com.zhuyz.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.zhuyz.cloud.common.constant.Constant.StatusCode.SAVE_ERROR;
import static com.zhuyz.cloud.common.constant.Constant.StatusCode.USER_NOT_FOUND;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhuyz
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/findUser/{id}")
    public ResponseEntity findUserById(@PathVariable("id") String id) {
        User user = userService.getById(id);
        return user != null ? ResponseEntity.buildSuccess(user) : ResponseEntity.buildCustom("user not found", USER_NOT_FOUND);
    }

    @PostMapping("/addUser")
    public ResponseEntity findUserById(@RequestBody User user) {
        user.setId(UUIDUtils.create32Id());
        boolean save = userService.save(user);
        return save ? ResponseEntity.buildSuccess(null, "add user success") : ResponseEntity.buildCustom(SAVE_ERROR);
    }


}
