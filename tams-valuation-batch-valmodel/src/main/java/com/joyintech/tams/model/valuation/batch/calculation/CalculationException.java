package com.joyintech.tams.model.valuation.batch.calculation;

/**
 * 异常封装类
 * @author zznn
 * @date 2021/7/1
 */
public class CalculationException extends RuntimeException{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 3555238643867679421L;

    /**
     *
     */
    public CalculationException() {
        super();
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public CalculationException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message
     * @param cause
     */
    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public CalculationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public CalculationException(Throwable cause) {
        super(cause);
    }
}

