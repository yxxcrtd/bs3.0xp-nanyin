/*
 * 文件名：MstBankTranFeeManageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.TrdFeeConfig;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.valid.MstBankTranFeeManageValidator;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年3月22日
 * @see MstBankTranFeeManageDto
 * @since 1.0
 */
@PageId("MstBankTranFeeManage002")
@ValidLogic(targetClass = MstBankTranFeeManageValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class MstBankTranFeeManageDto extends TrdFeeConfig implements ProcessEntity {

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
    private String feeRate;
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
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;

    /**
     * 交易市场
     */
    private String tradeMarketMode;

    public String getTradeMarketMode() {
        return tradeMarketMode;
    }
    /**
     * 设置 交易市场，一级市场、二级市场
     * @param tradeMarketMode 交易市场，一级市场、二级市场
     */
    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode == null ? null : tradeMarketMode.trim();
    }

    /**
     * 交易市场Str
     */
    private String tradeMarketModeStr;

    public String getTradeMarketModeStr() {
        return tradeMarketModeStr;
    }

    public void setTradeMarketModeStr(String tradeMarketModeStr) {
        this.tradeMarketModeStr = tradeMarketModeStr;
    }

    /**
     * 流程中做pk1存json串
     */
    private String allData;
    @PrimaryKey(value = Field.PK_1)
    public String getAllData() {
        return allData;
    }

    public void setAllData(String allData) {
        this.allData = allData;
    }
    @PrimaryKey(value = Field.PK_2)
    public String getBatchSeq() {
        return super.getBatchSeq();
    }
    @NotEmpty(fieldName = "MstBankTranFeeManage001.feeType", groups = {ValidateGroupEdit.class})
    public String getFeeType(){
        return super.getFeeType();
    }
    public String getRegistOrg() {
        return super.getRegistOrg();
    }
    @NotEmpty(fieldName = "MstBankTranFeeManage001.tradeType", groups = {ValidateGroupEdit.class})
    public String getTradeType() {
        return super.getTradeType();
    }
    @NotEmpty(fieldName = "MstBankTranFeeManage001.ps", groups = {ValidateGroupEdit.class})
    /*public String getPs() {
        return super.getPs();
    }*/
    @Override
    public String getPs() {
        // TODO Auto-generated method stub
        return super.getPs();
    }

    @NotEmpty(fieldName = "MstBankTranFeeManage001.chargeType", groups = {ValidateGroupEdit.class})
    public String getChargeType() {
        return super.getChargeType();
    }
    @NotEmpty(fieldName = "MstBankTranFeeManage001.effDate", groups = {ValidateGroupEdit.class})
    public Date getEffDate() {
        return super.getEffDate();
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

    public String getFeeRate() {
        return feeRate;
    }



    public void setFeeRate(String feeRate) {
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
    public String getFeeTypeStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.费用类型, super.getFeeType());
    }
    public String getRegistOrgStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.登记托管机构, super.getRegistOrg());
    }
    public String getTradeTypeStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.平安资产类型, super.getTradeType());
    }
    public String getChargeTypeStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.计费类型, super.getChargeType());
    }
    public String getPsStr() {
        String psStr = null;
        if(StringUtil.isNotEmpty(super.getPs())){
            //将查询出的交易方向取出，根据","切割成数组
            String[] psArr = super.getPs().split(",");
            //建议个新的交易方向数组
            String[] newPs = new String[psArr.length];
            for(int i=0;i<psArr.length;i++){
                newPs[i] = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, psArr[i])!= null?
                        SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, psArr[i]):
                        SysDataUtil.getNameFromDataDict(BussinessDictCode.回购拆借方向, psArr[i]);
            }
            psStr = String.join(",", newPs);
        }
        return psStr;
    }
    public String getChargeBasisStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.计费基数, super.getChargeBasis());
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    public String getEffDateStr(){
        return DateUtil.format(super.getEffDate());
    }
}
