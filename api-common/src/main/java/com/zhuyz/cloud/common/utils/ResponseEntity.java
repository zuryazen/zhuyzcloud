package com.zhuyz.cloud.common.utils;

import com.zhuyz.cloud.common.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    // 状态码
    private int code;
    private String msg;
    private T data;

    public ResponseEntity(int code) {
        this.code = code;
    }

    public ResponseEntity(T data, int code) {
        this.data = data;
        this.code = code;
    }
    public ResponseEntity(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
    public static <T> ResponseEntity<T> buildSuccess() {
        return new ResponseEntity<>(Constant.StatusCode.OK);
    }

    public static <T> ResponseEntity<T> buildSuccess(T data) {
        return new ResponseEntity<>(data, Constant.StatusCode.OK);
    }

    public static <T> ResponseEntity<T> buildSuccess(T data, String msg) {
        return new ResponseEntity<>(data, Constant.StatusCode.OK, msg);
    }

    public static <T> ResponseEntity<T> buildCustom(int code) {
        return new ResponseEntity<>(code);
    }

    public static <T> ResponseEntity<T> buildCustom(T data, int code) {
        return new ResponseEntity<>(data, code);
    }

    public static <T> ResponseEntity<T> buildCustom(T data, int code, String msg) {
        return new ResponseEntity<>(data, code, msg);
    }
}
