package com.limei.common.response;


import cn.hutool.core.util.ObjectUtil;
import com.limei.common.constants.HttpStatus;

import java.util.HashMap;
import java.util.Objects;

/**
* 统一响应类
* */
public class LiMeiResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

//    状态码
    public static final String CODE_TAG = "code";

//    返回内容
    public static final String MSG_TAG = "msg";

//    数据对象
    public static final String DATA_TAG = "data";

//   /**
//   初始化一个新创建的AjaxResult对象, 使其表示一个空消息
//   是构造方法*/
    public LiMeiResult(){
    }

    /**
    * 初始化一个新创建的AjaxResult对象
    * @param code 状态码
    * @param msg 返回内容
    * */
    public LiMeiResult(int code, String msg){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 一个新创建的AjaxResult对象
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     * */
    public LiMeiResult(int code, String msg, Object data){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if(ObjectUtil.isNull(data)){
            super.put(DATA_TAG,data);
        }
    }


    /** 返回成功消息
        @return 成功消息
    * */
    public static LiMeiResult success() {
        return LiMeiResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * */
    public static LiMeiResult success(Object data){
        return LiMeiResult.success("操作成功",data);
    }

    /**
     * 返回成消息
    * */
    public static LiMeiResult success(String msg) {
        return LiMeiResult.success(msg, null);
    }

    /**
     * 返回成功消息和数据
    * */
    public static LiMeiResult success(String msg, Object data) {
        return new LiMeiResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     * @param msg 内容
     * @param data 数据
     * @return 警告消息
    * */
    public static LiMeiResult warn(String msg, Object data){
        return new LiMeiResult(HttpStatus.WARN,msg, data);
    }

    /**
     * 返回警告消息
     * @param msg 内容
     * @return 警告消息
    * */
    public static LiMeiResult warn(String msg){
        return LiMeiResult.warn(msg, null);
    }


    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @param data
    * */
    public static LiMeiResult error(String msg, Object data){
        return new LiMeiResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     * @return 错误消息
    * */
    public static LiMeiResult error(String msg){
        return LiMeiResult.error(msg, null);
    }

    public static LiMeiResult error(Object data){
        return LiMeiResult.error(null,data);
    }

    public static LiMeiResult error(){
        return LiMeiResult.error("操作失败");
    }

    public static LiMeiResult error(int code, String msg){
        return new LiMeiResult(code, msg, null);
    }

    /**
     * 是否为成功消息
    * */
    public boolean isSuccess(){
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * 是否为警告消息
    * **/
    public boolean isWarn(){
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * 是否为错误消息
    * */
    public boolean isError(){
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }

    /**
     * 方便链式调用
     * @param key
     * @param value
     * @return 数据对象
    * */
    @Override
    public LiMeiResult put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
