package com.app.patest.common.exception;

public enum CommonExceptionEnum implements CommonException {
    PASSWPRD_ERR(1, "密码错误"),
    USER_NOT_EXIST(2, "用户不存在"),
    UNKNOWS_PRAMAR_ERR(3, "未知的参数错误"),
    NO_PERMISSION(4, "尚未登陆，无法访问"),
    ILLEGAL_ACCESS(5, "非法访问"),
    ILLEGAL_USER(6, "非法用户"),
    VERIFY_OUTTIME(7, "验证过期，检查密码是否修改"),
    USERNAME_EMPTY(8, "用户名为空"),
    PASSWORD_EMPTY(9, "密码为空"),
    REGISTER_FAIL(10, "创建用户失败"),
    USERNAME_EXIST(11, "用户名已存在"),
    TIME_OUT(12,"验证过时"),
    DECODE_FAIL(13,"解析令牌失败，非法令牌"),
    NO_TEACHER_PERMISSION(14,"无教师权限，无法访问"),

    //20开头的是关于题目上传的异常
    UPLOAD_PERMISSION_ERR(20, "你没有权限上传问题"),
    UPLOAD_FAIL(21, "上传失败"),

    //30服务器相关
    REDIS_SERVER_ERR(31,"redis服务异常");

    private int exceptionCode;
    private String exceptionMsg;


    /* Constructor */
    CommonExceptionEnum(int exceptionCode, String exceptionMsg) {
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }

    /* Override */
    @Override
    public int getExceptionCode() {
        return this.exceptionCode;
    }

    @Override
    public String getExceptionMsg() {
        return this.exceptionMsg;
    }

    @Override
    public CommonException setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
        return this;
    }
}
