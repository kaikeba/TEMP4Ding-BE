package com.kkb.temperature.exception;


import com.kkb.temperature.constant.KkbStatus;
import com.kkb.temperature.constant.KkbWebStatus;

/**
 * 业务异常，主要用于全局异常handler处理
 * 注意，如果是服务间调用，则不能抛出此异常，会触发熔断
 * 想避免触发熔断，需要在FeignClient注解中配置Configuration
 */

/**
 * 已将RuntimeException改成HystrixBadRequestException
 * HystrixBadRequestException不会触发熔断
 * 适用于业务的异常，如参数缺失，数据重复等业务判断异常
 */
public class KkbBusinessException extends RuntimeException {

    private int code;

    private String message;

    public KkbBusinessException() {
        this("This is blank message");
    }

    public KkbBusinessException(String msg) {
        this(KkbWebStatus.FAILURE.getCode(), msg);
    }

    public KkbBusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public KkbBusinessException(KkbStatus kkbStatus) {
        super(kkbStatus.getMsg());
        this.code = kkbStatus.getCode();
        this.message = kkbStatus.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

}
