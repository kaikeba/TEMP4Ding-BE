package com.kkb.temperature.constant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author kkb
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KkbResponse implements Serializable {


    private static final long serialVersionUID = -7318467237446066728L;
    private int code;
    private String msg;
    /**
     * @JsonInclude(Include.NON_NULL) 这个注解放在类头上就可以解决。 实体类与json互转的时候 属性值为null的不参与序列化
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;


    public static KkbResponse success() {
        return build(KkbWebStatus.SUCCESS.getCode(), KkbWebStatus.SUCCESS.getMsg(), null);
    }

    public static KkbResponse success(Object data) {
        return build(KkbWebStatus.SUCCESS.getCode(), KkbWebStatus.SUCCESS.getMsg(), data);
    }

    public static KkbResponse failure() {
        return build(KkbWebStatus.FAILURE.getCode(), KkbWebStatus.FAILURE.getMsg(), null);
    }

    public static KkbResponse error(Exception e) {
        return build(KkbWebStatus.FAILURE.getCode(), e.getMessage(), null);
    }

    public static KkbResponse failure(KkbStatus kkbStatus) {
        return build(kkbStatus.getCode(), kkbStatus.getMsg(), null);
    }

    public static KkbResponse response(KkbStatus kkbStatus, Object data) {
        return build(kkbStatus.getCode(), kkbStatus.getMsg(), data);
    }

    public static KkbResponse response(int code, String msg) {
        return build(code, msg, null);
    }

    private static KkbResponse build(int code, String msg, Object data) {
        KkbResponse res = new KkbResponse();
        res.code = code;
        res.msg = msg;
        res.data = data;
        return res;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == KkbWebStatus.SUCCESS.getCode();
    }


}
