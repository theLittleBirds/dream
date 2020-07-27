package com.zc.dream.util.result;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(1200),//成功
    FAIL(1400),//失败
    UNAUTHORIZED(1401),//未认证（签名错误）
    FORBIDDEN(1403),//权限不足，禁止访问
    NOT_FOUND(1404),//接口不存在
    INTERNAL_SERVER_ERROR(1500);//服务器内部错误

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
