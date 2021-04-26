package com.joyintech.tams.valuation.valapi.exception;

/**
 * @ClassName ValInterfApiException
 * @Description: 自定义估值api调用异常
 * @Author yanya
 * @Date 2021/2/3
 * @Version V1.0
 **/
public class ValInterfApiException extends Exception{


    private static final long serialVersionUID = -8617715467332049745L;

    /*无参构造函数*/
    public ValInterfApiException(){
        super();
    }

    //用详细信息指定一个异常
    public ValInterfApiException(String message){
        super(message);
    }

    //用指定的详细信息和原因构造一个新的异常
    public ValInterfApiException(String message, Throwable cause){
        super(message,cause);
    }
    //用指定原因构造一个新的异常
    public ValInterfApiException(Throwable cause) {
        super(cause);
    }

}
