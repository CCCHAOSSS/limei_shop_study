package com.limei.common.constants;

//定义静态常量常量,用于返回http状态
public interface HttpStatus {
    /*
    * 操作成功*/
    public static final int SUCCESS = 200;

    /*
    * 对象创建成功*/
    public static final int CREATE = 201;

    /*
    * 请求已被接收*/
    public static final int ACCEPTED = 202;

    /*
    * 操作已经执行成功,但没有返回数据*/
    public static final int NO_CONTENT = 204;

    /*
    * 资源已经被移除*/
    public static final int MOVED_PERM = 301;

    /*
    * 重定向*/
    public static final int SEE_OTHER = 303;

    /*
    * 资源没有被修改*/
    public static final int NOT_MODIFIED = 303;

    /*
    * 参数列表错误(缺少,格式不匹配)*/
    public static final int BAD_REQUEST =400;

    /*
    * 未授权*/
    public static final int UNAUTH0RIZED = 401;

    /*
    * 访问受限,授权过期*/
    public static final int FORBIDDEN = 403;

    /*
    * 资源,服务未找到*/
    public static final int NOT_FOUND = 404;

    /*
    * 不允许的http方法*/
    public static final int BAD_METHOD = 405;

    /*
    * 资源冲突或者资源被锁*/
    public static final int CONFLICT = 409;

    /*
    * 不支持的数据,媒体类型*/
    public static final int UNSUPPORTED_TYPE = 415;

    /*
    * 系统内部错误*/
    public static final int ERROR = 500;

    /*
    * 接口未实现*/
    public static final int NOT_IMPLEMENTED = 501;

    /*
    * 系统警告消息*/
    public static final int WARN = 601;




}
