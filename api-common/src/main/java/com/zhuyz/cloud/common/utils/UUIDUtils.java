package com.zhuyz.cloud.common.utils;

import java.util.UUID;

/**
 * @author zhuyz
 * @date 2020/5/30 0030 18:17
 * @description
 */
public class UUIDUtils {

    public static String create32Id() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }



}
