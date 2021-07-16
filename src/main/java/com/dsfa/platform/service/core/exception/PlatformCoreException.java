package com.dsfa.platform.service.core.exception;

import com.dsfa.platform.starter.exception.DsfException;

/**
 * @ClassName OTException
 * @Author 文进
 * @Date 2020/7/31 下午2:50
 * @Version 1.0.0
 * @Description TODO
 **/
public class PlatformCoreException extends DsfException {

    public PlatformCoreException(String message) {
        super(message);
    }

    public static PlatformCoreException create(Code code){
        return (PlatformCoreException) new PlatformCoreException(code.msg()).setState(code.state());
    }
}
