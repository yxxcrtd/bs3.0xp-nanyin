package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CfetsStatusDatadictConvert extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 报价类别
     */
    private String quoteTypeIbu;

    /**
     * 报文类型
     */
    private String msgType;

    /**
     * 操作类型
     */
    private String quoteTransType;

    /**
     * 报价状态
     */
    private String quoteStatus;

    /**
     * 成交状态
     */
    private String execType;

    /**
     * 业务系统本方角色
     */
    private String mySide;

    /**
     * 操作类型
     */
    private String exeType;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 发送状态
     */
    private String sendStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 报价类别
     * @return 报价类别
     */
    public String getQuoteTypeIbu() {
        return quoteTypeIbu;
    }

    /**
     * 设置 报价类别
     * @param quoteTypeIbu 报价类别
     */
    public void setQuoteTypeIbu(String quoteTypeIbu) {
        this.quoteTypeIbu = quoteTypeIbu == null ? null : quoteTypeIbu.trim();
    }

    /**
     * 取得 报文类型
     * @return 报文类型
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 设置 报文类型
     * @param msgType 报文类型
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    /**
     * 取得 操作类型
     * @return 操作类型
     */
    public String getQuoteTransType() {
        return quoteTransType;
    }

    /**
     * 设置 操作类型
     * @param quoteTransType 操作类型
     */
    public void setQuoteTransType(String quoteTransType) {
        this.quoteTransType = quoteTransType == null ? null : quoteTransType.trim();
    }

    /**
     * 取得 报价状态
     * @return 报价状态
     */
    public String getQuoteStatus() {
        return quoteStatus;
    }

    /**
     * 设置 报价状态
     * @param quoteStatus 报价状态
     */
    public void setQuoteStatus(String quoteStatus) {
        this.quoteStatus = quoteStatus == null ? null : quoteStatus.trim();
    }

    /**
     * 取得 成交状态
     * @return 成交状态
     */
    public String getExecType() {
        return execType;
    }

    /**
     * 设置 成交状态
     * @param execType 成交状态
     */
    public void setExecType(String execType) {
        this.execType = execType == null ? null : execType.trim();
    }

    /**
     * 取得 业务系统本方角色
     * @return 业务系统本方角色
     */
    public String getMySide() {
        return mySide;
    }

    /**
     * 设置 业务系统本方角色
     * @param mySide 业务系统本方角色
     */
    public void setMySide(String mySide) {
        this.mySide = mySide == null ? null : mySide.trim();
    }

    /**
     * 取得 操作类型
     * @return 操作类型
     */
    public String getExeType() {
        return exeType;
    }

    /**
     * 设置 操作类型
     * @param exeType 操作类型
     */
    public void setExeType(String exeType) {
        this.exeType = exeType == null ? null : exeType.trim();
    }

    /**
     * 取得 交易状态
     * @return 交易状态
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 交易状态
     * @param tradeStatus 交易状态
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    /**
     * 取得 发送状态
     * @return 发送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 发送状态
     * @param sendStatus 发送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}