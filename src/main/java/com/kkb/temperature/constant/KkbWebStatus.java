package com.kkb.temperature.constant;

import java.util.HashMap;

/**
 * @Description 全局返回码（禁止将业务code码定义在此类）
 * @Author : czq
 * @Date: 2019-12-26 15:51
 * @return
 **/
public enum KkbWebStatus implements KkbStatus {
    /**
     * 成功
     */
    SUCCESS(0, "success"),
    /**
     * 失败（Exception使用）（慎用、慎用、慎用）
     */
    FAILURE(1, "failure");


    private int code;
    private String msg;

    KkbWebStatus(int code, String msg) {
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

    private static HashMap<Integer, KkbWebStatus> map = new HashMap<>();

    static {
        for (KkbWebStatus d : KkbWebStatus.values()) {
            map.put(d.code, d);
        }
    }

    public static KkbWebStatus parse(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        return null;
    }

    public static boolean isSuccess(KkbResponse response) {
        return SUCCESS.getCode() == response.getCode();
    }

}
