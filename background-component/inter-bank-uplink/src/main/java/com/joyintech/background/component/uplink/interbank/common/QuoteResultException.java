/*
 * 文件名：QuoteResultException.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.common;

import com.joyintech.background.component.uplink.interbank.business.dto.QuoteResultDto;

/**
 * 带报价处理结果异常的异常对象
 * @author Jiaoxujin
 * @version 1.0 2020年5月13日
 * @see QuoteResultException
 * @since 1.0
 */
public class QuoteResultException extends Exception {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -349146208326199146L;
    
    QuoteResultDto quoteResult;
    
    public QuoteResultException() {}

    public QuoteResultException(String message) {
        super(message);
    }
    
    public QuoteResultException(QuoteResultDto quoteResult) {
        super(quoteResult.getErrMsg());
        this.quoteResult = quoteResult;
    }

    public QuoteResultException(Throwable cause) {
        super(cause);
    }

    public QuoteResultException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public QuoteResultException(QuoteResultDto quoteResult, Throwable cause) {
        super(quoteResult.getErrMsg(), cause);
        this.quoteResult = quoteResult;
    }

    /**
     * 获取 quoteResult
     * @return quoteResult.
     */
    public QuoteResultDto getQuoteResult() {
        return quoteResult;
    }
}
