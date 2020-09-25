package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlOrdersMapping extends BaseEntity {
    /**
     * 主键
     */
    private String seqno;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 业务类型名称
     */
    private String busiTypeName;

    /**
     * 交易方向
     */
    private String tradePs;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 指令类型
     */
    private String orderType;

    /**
     * 指令类型名称
     */
    private String orderTypeName;

    /**
     * 日期类型
     */
    private String dateType;

    /**
     * 收支方向
     */
    private String ps;

    /**
     * 本方账户类型
     */
    private String myAcctType;

    /**
     * 对手方账户类型
     */
    private String counterAcctType;

    /**
     * 否随交易支付
     */
    private String isBook;

    /**
     * 系统参数Code
     */
    private String paraCode;

    /**
     * 系统参数Value
     */
    private String paramValue;

    /**
     * 通道虚拟资产
     */
    private String channelAsset;

    /**
     * 参数条件；存储格式[code1,value1]|[code2,value2]
     */
    private String params;

    /**
     * 结算金额字段名
     */
    private String settlamtFieldcode;

    /**
     * 映射类型
     */
    private String mappingType;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 主键
     * @param seqno 主键
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 业务类型名称
     * @return 业务类型名称
     */
    public String getBusiTypeName() {
        return busiTypeName;
    }

    /**
     * 设置 业务类型名称
     * @param busiTypeName 业务类型名称
     */
    public void setBusiTypeName(String busiTypeName) {
        this.busiTypeName = busiTypeName == null ? null : busiTypeName.trim();
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getTradePs() {
        return tradePs;
    }

    /**
     * 设置 交易方向
     * @param tradePs 交易方向
     */
    public void setTradePs(String tradePs) {
        this.tradePs = tradePs == null ? null : tradePs.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }

    /**
     * 取得 指令类型
     * @return 指令类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置 指令类型
     * @param orderType 指令类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * 取得 指令类型名称
     * @return 指令类型名称
     */
    public String getOrderTypeName() {
        return orderTypeName;
    }

    /**
     * 设置 指令类型名称
     * @param orderTypeName 指令类型名称
     */
    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName == null ? null : orderTypeName.trim();
    }

    /**
     * 取得 日期类型
     * @return 日期类型
     */
    public String getDateType() {
        return dateType;
    }

    /**
     * 设置 日期类型
     * @param dateType 日期类型
     */
    public void setDateType(String dateType) {
        this.dateType = dateType == null ? null : dateType.trim();
    }

    /**
     * 取得 收支方向
     * @return 收支方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 收支方向
     * @param ps 收支方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 本方账户类型
     * @return 本方账户类型
     */
    public String getMyAcctType() {
        return myAcctType;
    }

    /**
     * 设置 本方账户类型
     * @param myAcctType 本方账户类型
     */
    public void setMyAcctType(String myAcctType) {
        this.myAcctType = myAcctType == null ? null : myAcctType.trim();
    }

    /**
     * 取得 对手方账户类型
     * @return 对手方账户类型
     */
    public String getCounterAcctType() {
        return counterAcctType;
    }

    /**
     * 设置 对手方账户类型
     * @param counterAcctType 对手方账户类型
     */
    public void setCounterAcctType(String counterAcctType) {
        this.counterAcctType = counterAcctType == null ? null : counterAcctType.trim();
    }

    /**
     * 取得 否随交易支付
     * @return 否随交易支付
     */
    public String getIsBook() {
        return isBook;
    }

    /**
     * 设置 否随交易支付
     * @param isBook 否随交易支付
     */
    public void setIsBook(String isBook) {
        this.isBook = isBook == null ? null : isBook.trim();
    }

    /**
     * 取得 系统参数Code
     * @return 系统参数Code
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 系统参数Code
     * @param paraCode 系统参数Code
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

    /**
     * 取得 系统参数Value
     * @return 系统参数Value
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 系统参数Value
     * @param paramValue 系统参数Value
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 取得 通道虚拟资产
     * @return 通道虚拟资产
     */
    public String getChannelAsset() {
        return channelAsset;
    }

    /**
     * 设置 通道虚拟资产
     * @param channelAsset 通道虚拟资产
     */
    public void setChannelAsset(String channelAsset) {
        this.channelAsset = channelAsset == null ? null : channelAsset.trim();
    }

    /**
     * 取得 参数条件；存储格式[code1,value1]|[code2,value2]
     * @return 参数条件；存储格式[code1,value1]|[code2,value2]
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置 参数条件；存储格式[code1,value1]|[code2,value2]
     * @param params 参数条件；存储格式[code1,value1]|[code2,value2]
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * 取得 结算金额字段名
     * @return 结算金额字段名
     */
    public String getSettlamtFieldcode() {
        return settlamtFieldcode;
    }

    /**
     * 设置 结算金额字段名
     * @param settlamtFieldcode 结算金额字段名
     */
    public void setSettlamtFieldcode(String settlamtFieldcode) {
        this.settlamtFieldcode = settlamtFieldcode == null ? null : settlamtFieldcode.trim();
    }

    /**
     * 取得 映射类型
     * @return 映射类型
     */
    public String getMappingType() {
        return mappingType;
    }

    /**
     * 设置 映射类型
     * @param mappingType 映射类型
     */
    public void setMappingType(String mappingType) {
        this.mappingType = mappingType == null ? null : mappingType.trim();
    }
}