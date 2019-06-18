package com.app.patest.common.exception;

public interface CommonException {

    int getExceptionCode();

    String getExceptionMsg();

    CommonException setExceptionMsg(String exceptionMsg);

}
