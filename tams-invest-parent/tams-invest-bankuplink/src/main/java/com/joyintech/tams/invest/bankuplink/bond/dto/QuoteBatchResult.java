/*
 * 文件名：QuoteBatchResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.bankuplink.bond.dto;

import java.util.List;

/**
 * 现劵报价批量处理结果
 * @author Jiaoxujin
 * @version 1.0 2020年5月22日
 * @see QuoteBatchResult
 * @since 1.0
 */
public class QuoteBatchResult {
    /**
     * 成功条数
     */
    private long successAccout;
    
    /**
     * 失败条数
     */
    private long failAccout;

    /**
     * 错误信息
     */
    private List<String> errMsgList;

    /**
     * 已处理成功报价信息
     */
    private List<String> quoteResultJsonStrList;

    /**
     * 获取 successAccout
     * @return successAccout.
     */
    public long getSuccessAccout() {
        return successAccout;
    }

    /**
     * 设置 successAccout
     * @param successAccout successAccout
     */
    public void setSuccessAccout(long successAccout) {
        this.successAccout = successAccout;
    }

    /**
     * 获取 failAccout
     * @return failAccout.
     */
    public long getFailAccout() {
        return failAccout;
    }

    /**
     * 设置 failAccout
     * @param failAccout failAccout
     */
    public void setFailAccout(long failAccout) {
        this.failAccout = failAccout;
    }

    /**
     * 获取 errMsgList
     * @return errMsgList.
     */
    public List<String> getErrMsgList() {
        return errMsgList;
    }

    /**
     * 设置 errMsgList
     * @param errMsgList errMsgList
     */
    public void setErrMsgList(List<String> errMsgList) {
        this.errMsgList = errMsgList;
    }

    
    /**
     * 获取 quoteResultJsonStrList
     * @return quoteResultJsonStrList.
     */
    public List<String> getQuoteResultJsonStrList() {
        return quoteResultJsonStrList;
    }

    
    /**
     * 设置 quoteResultJsonStrList
     * @param quoteResultJsonStrList quoteResultJsonStrList
     */
    public void setQuoteResultJsonStrList(List<String> quoteResultJsonStrList) {
        this.quoteResultJsonStrList = quoteResultJsonStrList;
    }
}
