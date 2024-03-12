package com.limei.common.exception;

/**
 * @author limei
 * @data 2024/03/12
 * 自定义的业务异常,当系统出现异常时,返回该异常给前端
* */
public class ServiceException extends RuntimeException {    //继承运行时候的异常
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     * */
    private Integer code;

    /**
     * 错误提示
     * */
    private String message;

    /**
     * 错误明细,可以用于内部调试错误
    * */
    private String detailMessage;

    /**
     * 空构造方法,避免反序列化问题
    * */
    public ServiceException(){}

    public ServiceException(String message){
        this.message = message;
    }

    public ServiceException(Integer code, String message){
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public Integer getCode() {
        return code;
    }

    public ServiceException setMessage(String message){
        this.message = message;
        return this;
    }
}
