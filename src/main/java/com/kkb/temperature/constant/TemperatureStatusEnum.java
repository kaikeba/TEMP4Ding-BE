package com.kkb.temperature.constant;

import java.util.HashMap;

/**
 * @author weehours
 */

public enum TemperatureStatusEnum implements KkbStatus {
    ACCESS_TOKEN_ERROR(40101, "获取钉钉Acesss_token异常"),
    USERID_ERROR(40102, "获取钉钉UserID异常"),
    USER_INFO_ERROR(40103, "获取钉钉用户信息异常"),
    JSAPI_TICKET_ERROR(40104, "获取钉钉JSAPI_TICKET异常"),
    EXIST_MEMBERS(40105, "家属已存在");

    private int code;
    private String msg;

    TemperatureStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    private static HashMap<Integer, TemperatureStatusEnum> map = new HashMap<>();

    static {
        for (TemperatureStatusEnum d : TemperatureStatusEnum.values()) {
            map.put(d.code, d);
        }
    }

    public static TemperatureStatusEnum parse(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        return null;
    }
}
