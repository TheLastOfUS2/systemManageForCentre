package com.systemManage.common.exception;

/**
 * 类     名:SystemException
 * 作     用:自定义异常类
 * 作     者:张金秋
 * 日     期:2017年6月27日 下午22:10:58
 */
public class SystemException extends Exception {

    private static final long serialVersionUID = 1L;

    public SystemException(String exceMessage) {
        super(exceMessage);
    }
}
