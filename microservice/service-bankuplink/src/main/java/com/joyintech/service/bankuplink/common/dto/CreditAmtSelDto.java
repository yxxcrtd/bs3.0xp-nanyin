/*
 * 文件名：CreditAmtSelDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.common.dto;


import java.util.List;

/**
 * 授信额度信息
 * 
 * @author fanshuanghu
 * @version 1.0 2019年5月23日
 * @see CreditAmtSelDto
 * @since 1.0
 */
public class CreditAmtSelDto {

    /**
     * IBLS客户编号
     */
    private String iblsClientNo;

    /**
     * 查询类别标志
     */
    private String queryTypeFlag;

    /**
     * 查询批复类型
     */
    private String queryApproveType;

    /**
     * 所属机构编号
     */
    private String orgNo;

    /**
     * 来源系统ID
     */
    private String sourceSystemId;

    /**
     * 映射账号
     */
    private String mappingAcctNo;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 业务编号
     */
    private String tranNo;

    /**
     * 标志
     */
    private String flag;

    /**
     * 品种代码
     */
    private String varietyCode;

    /**
     * 交易时间
     */
    private String tranTime;

    /**
     * 交易日期
     */
    private String tranDate;

    /**
     * 交易机构(分行)号
     */
    private String tranBranchId;

    /**
     * 日期时间
     */
    private String dateTime;

    /**
     * 系统来源
     */
    private String moduleSource;

    /**
     * 处理状态代码
     */
    private boolean result;

    /**
     * 接口类型
     */
    private String interfType;

    /**
     * 处理状态
     */
    private String errMsg;

    /**
     * 操作结果返回标志
     */
    private String opResultFlag;

    /**
     * 返回信息
     */
    private String retMsg;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 债券编号
     */
    private String secId;

    /**
     * 请求列表
     */
    List<CreditAmtSelDto> creditList;


    /**
     * 获取 iblsClientNo
     * 
     * @return iblsClientNo.
     */
    public String getIblsClientNo() {
        return iblsClientNo;
    }

    /**
     * 设置 iblsClientNo
     * 
     * @param iblsClientNo iblsClientNo
     */
    public void setIblsClientNo(String iblsClientNo) {
        this.iblsClientNo = iblsClientNo;
    }

    /**
     * 获取 queryTypeFlag
     * 
     * @return queryTypeFlag.
     */
    public String getQueryTypeFlag() {
        return queryTypeFlag;
    }

    /**
     * 设置 queryTypeFlag
     * 
     * @param queryTypeFlag queryTypeFlag
     */
    public void setQueryTypeFlag(String queryTypeFlag) {
        this.queryTypeFlag = queryTypeFlag;
    }

    /**
     * 获取 queryApproveType
     * 
     * @return queryApproveType.
     */
    public String getQueryApproveType() {
        return queryApproveType;
    }

    /**
     * 设置 queryApproveType
     * 
     * @param queryApproveType queryApproveType
     */
    public void setQueryApproveType(String queryApproveType) {
        this.queryApproveType = queryApproveType;
    }

    /**
     * 获取 orgNo
     * 
     * @return orgNo.
     */
    public String getOrgNo() {
        return orgNo;
    }

    /**
     * 设置 orgNo
     * 
     * @param orgNo orgNo
     */
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    /**
     * 获取 sourceSystemId
     * 
     * @return sourceSystemId.
     */
    public String getSourceSystemId() {
        return sourceSystemId;
    }

    /**
     * 设置 sourceSystemId
     * 
     * @param sourceSystemId sourceSystemId
     */
    public void setSourceSystemId(String sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }

    /**
     * 获取 mappingAcctNo
     * 
     * @return mappingAcctNo.
     */
    public String getMappingAcctNo() {
        return mappingAcctNo;
    }

    /**
     * 设置 mappingAcctNo
     * 
     * @param mappingAcctNo mappingAcctNo
     */
    public void setMappingAcctNo(String mappingAcctNo) {
        this.mappingAcctNo = mappingAcctNo;
    }

    /**
     * 获取 result
     * 
     * @return result.
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置 result
     * 
     * @param result result
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * 获取 errMsg
     * 
     * @return errMsg.
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 errMsg
     * 
     * @param errMsg errMsg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }


    /**
     * 获取 seqNo
     * 
     * @return seqNo.
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 seqNo
     * 
     * @param seqNo seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 获取 tranNo
     * 
     * @return tranNo.
     */
    public String getTranNo() {
        return tranNo;
    }

    /**
     * 设置 tranNo
     * 
     * @param tranNo tranNo
     */
    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    /**
     * 获取 flag
     * 
     * @return flag.
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置 flag
     * 
     * @param flag flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 获取 varietyCode
     * 
     * @return varietyCode.
     */
    public String getVarietyCode() {
        return varietyCode;
    }

    /**
     * 设置 varietyCode
     * 
     * @param varietyCode varietyCode
     */
    public void setVarietyCode(String varietyCode) {
        this.varietyCode = varietyCode;
    }

    /**
     * 获取 tranTime
     * 
     * @return tranTime.
     */
    public String getTranTime() {
        return tranTime;
    }

    /**
     * 设置 tranTime
     * 
     * @param tranTime tranTime
     */
    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    /**
     * 获取 tranDate
     * 
     * @return tranDate.
     */
    public String getTranDate() {
        return tranDate;
    }

    /**
     * 设置 tranDate
     * 
     * @param tranDate tranDate
     */
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    /**
     * 获取 tranBranchId
     * 
     * @return tranBranchId.
     */
    public String getTranBranchId() {
        return tranBranchId;
    }

    /**
     * 设置 tranBranchId
     * 
     * @param tranBranchId tranBranchId
     */
    public void setTranBranchId(String tranBranchId) {
        this.tranBranchId = tranBranchId;
    }

    /**
     * 获取 dateTime
     * 
     * @return dateTime.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * 设置 dateTime
     * 
     * @param dateTime dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 获取 moduleSource
     * 
     * @return moduleSource.
     */
    public String getModuleSource() {
        return moduleSource;
    }

    /**
     * 设置 moduleSource
     * 
     * @param moduleSource moduleSource
     */
    public void setModuleSource(String moduleSource) {
        this.moduleSource = moduleSource;
    }

    /**
     * 获取 interfType
     * 
     * @return interfType.
     */
    public String getInterfType() {
        return interfType;
    }

    /**
     * 设置 interfType
     * 
     * @param interfType interfType
     */
    public void setInterfType(String interfType) {
        this.interfType = interfType;
    }

    /**
     * 获取 opResultFlag
     * 
     * @return opResultFlag.
     */
    public String getOpResultFlag() {
        return opResultFlag;
    }

    /**
     * 设置 opResultFlag
     * 
     * @param opResultFlag opResultFlag
     */
    public void setOpResultFlag(String opResultFlag) {
        this.opResultFlag = opResultFlag;
    }

    /**
     * 获取 retMsg
     * 
     * @return retMsg.
     */
    public String getRetMsg() {
        return retMsg;
    }

    /**
     * 设置 retMsg
     * 
     * @param retMsg retMsg
     */
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    /**
     * 获取 creditList
     * 
     * @return creditList.
     */
    public List<CreditAmtSelDto> getCreditList() {
        return creditList;
    }

    /**
     * 设置 creditList
     * 
     * @param creditList creditList
     */
    public void setCreditList(List<CreditAmtSelDto> creditList) {
        this.creditList = creditList;
    }

    /**
     * 获取 tradeId
     * 
     * @return tradeId.
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 tradeId
     * 
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 secId
     * 
     * @return secId.
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 secId
     * 
     * @param secId secId
     */
    public void setSecId(String secId) {
        this.secId = secId;
    }

}
