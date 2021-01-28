/*
 * 文件名：MstBankTranFeeManageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstbanktranfeemanage.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.TrdFeeConfig;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年3月22日
 * @see MstBankTranFeeManageDto
 * @since 1.0
 */
public class MstBankTranFeeManageDto extends TrdFeeConfig{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 有效状态
     */
    private String validStatus;

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * 有效状态转换
     * @return
     */
    public String getValidStatusVal(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getValidStatus());
    }

    /**
     * 费用上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeMaxi;

    /**
     * 费用下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeMini;
    /**
     * 是否多券种
     */
    private String isMlti;
    /**
     * 计费值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeValue;
    /**
     * 计费比率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeRate;
    /**
     * 费用值/费率值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeRate;
    /**
     * 交易费用配置明细 流水号
     */
    private String detailSeqNo;
    /**
     * 新生成的批次号
     */
    private String newBatchSeq;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 交易市场
     */
    private String tradeMarketMode;

    /**
     * 选择数据
     */
    private List<MstBankTranFeeManageDto> selectedItem;
    /**
     * 要素id
     */
    private String elementId;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;
    private Map<String,Object> psMap;
    /**
     * 流程中做pk1存json串
     */
    private String allData;

    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode == null ? null : tradeMarketMode.trim();
    }

    public String getAllData() {
        return allData;
    }
    
    public void setAllData(String allData) {
        this.allData = allData;
    }
    
    public BigDecimal getFeeMaxi() {
        return feeMaxi;
    }

    
    public void setFeeMaxi(BigDecimal feeMaxi) {
        this.feeMaxi = feeMaxi;
    }

    
    public BigDecimal getFeeMini() {
        return feeMini;
    }

    
    public void setFeeMini(BigDecimal feeMini) {
        this.feeMini = feeMini;
    }

    
    public String getIsMlti() {
        return isMlti;
    }

    
    public void setIsMlti(String isMlti) {
        this.isMlti = isMlti;
    }

    
    public BigDecimal getChargeValue() {
        return chargeValue;
    }

    
    public void setChargeValue(BigDecimal chargeValue) {
        this.chargeValue = chargeValue;
    }

    
    public BigDecimal getChargeRate() {
        return chargeRate;
    }

    
    public void setChargeRate(BigDecimal chargeRate) {
        this.chargeRate = chargeRate;
    }

    
    public BigDecimal getFeeRate() {
        return feeRate;
    }


    
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }


    
    public String getDetailSeqNo() {
        return detailSeqNo;
    }


    
    public void setDetailSeqNo(String detailSeqNo) {
        this.detailSeqNo = detailSeqNo;
    }
    

    
    public String getNewBatchSeq() {
        return newBatchSeq;
    }


    
    public void setNewBatchSeq(String newBatchSeq) {
        this.newBatchSeq = newBatchSeq;
    }


    public String getOperateType() {
        return operateType;
    }

    
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    public List<MstBankTranFeeManageDto> getSelectedItem() {
        return selectedItem;
    }

    
    public void setSelectedItem(List<MstBankTranFeeManageDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    public String getElementId() {
        return elementId;
    }

    
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    
    public Map<String, Object> getPsMap() {
        return psMap;
    }


    
    public void setPsMap(Map<String, Object> psMap) {
        this.psMap = psMap;
    }
    
}
