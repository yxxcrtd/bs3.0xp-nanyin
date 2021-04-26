package com.joyintech.tams.invest.period.trdprojnetduration.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdfundduration.TrdFundDurationCommonDto;
import com.joyintech.tams.invest.period.trdprojnetduration.validator.TrdProjectNetDurationValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("TrdProjectNetDuration002")
@ValidLogic(targetClass = TrdProjectNetDurationValidator.class, groups = {
        ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdProjectNetDurationDto extends TrdFundDurationCommonDto implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3974078160802737259L;
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 发行机构代码
     */
    private String repIssuePartyId;
    /**
     * 发行人
     */
    private String issuer;
    /**
     * 业务类型00 新建模式 01 货币再投，02 非货除权 03 非货分红 04 非货再投（用于画面显示及业务操作类型甄别）
     */
    private String type;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 控件Id
     */
    private String elementId;
    /**
     * 模式（辅助标记功能）
     */
    private String mode;
    /**
     * 有效状态
     */
    private String statusValid;
    /**
     * 资产代码
     */
    @NotEmpty(fieldName = "TrdPlanNetDuration001.finprodMarketId",groups= {ValidateGroupEdit.class})
    private String finprodMarketId;
    /**
     * 资产名称
     */
    /*@NotEmpty(fieldName = "TrdPlanNetDuration001.finprodMarketName",groups= {ValidateGroupEdit.class})*/
    private String finprodMarketName;
    /**
     * 分红类型
     */
    @NotEmpty(fieldName = "TrdPlanNetDuration001.bonusMode",groups= {ValidateGroupEdit.class})
    private String bonusMode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 权益登记日
     */
    private Date registDate;
    /**
     * 除权日
     */
    private Date exRightsDate;
    /**
     * 分红到账日
     */
    private Date arriveDate;
    /**
     * 红利再投日
     */
    private Date reInvesDate;

    /**
     * 通道代码
     */
    private String chlId;
    /**
     * 权益登记日份额
     */
    @Amount(fieldName="TrdPlanNetDuration001.registShare",groups= {ValidateGroupEdit.class})
    private BigDecimal registShare;
    /**
     * 单位分红净值
     *
     */

//    @NotEmpty(fieldName = "TrdPlanNetDuration001.unitCprice",groups= {ValidateGroupEdit.class})
    @Amount(maxDecBit=4,fieldName="TrdProjectNetDuration001.unitCprice",groups= {ValidateGroupEdit.class})
    private BigDecimal unitCprice;
    /**
     * 分红金额
     */
    @Amount(fieldName="TrdPlanNetDuration001.tradeAmt",groups= {ValidateGroupEdit.class})
    /*@NotEmpty(fieldName = "TrdPlanNetDuration001.tradeAmt",groups= {ValidateGroupEdit.class})*/
    private BigDecimal tradeAmt;
    /**
     * 分红份额
     */
    @Amount(fieldName="TrdPlanNetDuration001.qty",groups= {ValidateGroupEdit.class})
    private BigDecimal qty;


    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    /**
     * 基金类型
     */
    private String assetType;

    public String getUpdateFlag(){
        if(!StringUtil.equals(statusValid, DictCodeListExt1.画面数据状态_有效.getValue())||StringUtil.isNotEmpty(getHasFlow())){
            return "02";
        }
        else if(StringUtil.equals(type,"04")||StringUtil.equals(type,"01")||StringUtil.equals(type,"03")){
            return "02";
        }
        else{
            return "01";
        }
    }
    //   交易方向
    public String getPsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getPs());
    }
    //    画面数据状态
    public String getStatusValidStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, this.statusValid);
    }
    //   分红类型
    public String getBonusModeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.分红方式, this.bonusMode);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * PK1
     * @return
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId_PK() {
        return StringUtil.isNotEmpty(super.getTotleTradeId())? super.getTotleTradeId(): IDGenerator.generate();
    }
    /**
     * 获取 PK2
     * @return elementId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusinessCode() {
        return DictCodeListExt3.共通业务类型_权益类资产存续期.getValue();
    }
    /**
     * 获取 PK3
     * @return getbTradeId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getbTradeId() {
        return super.getbTradeId();
    }
    /**
     * 获取 PK4
     * @return getpTradeId.
     */
    @PrimaryKey(PrimaryKey.Field.PK_4)
    public String getpTradeId() {
        return super.getpTradeId();
    }
    /**
     * 获取 statusValid
     * @return statusValid.
     */
    public String getStatusValid() {
        return statusValid;
    }

    /**
     * 设置 statusValid
     * @param statusValid statusValid
     */
    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }


    /**
     * 获取 finprodMarketName
     * @return finprodMarketName.
     */
    public String getFinprodMarketName() {
        return finprodMarketName;
    }

    /**
     * 设置 finprodMarketName
     * @param finprodMarketName finprodMarketName
     */
    public void setFinprodMarketName(String finprodMarketName) {
        this.finprodMarketName = finprodMarketName;
    }


    /**
     * 获取 bonusMode
     * @return bonusMode.
     */
    public String getBonusMode() {
        return bonusMode;
    }

    /**
     * 设置 bonusMode
     * @param bonusMode bonusMode
     */
    public void setBonusMode(String bonusMode) {
        this.bonusMode = bonusMode;
    }

    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 registDate
     * @return registDate.
     */
    public Date getRegistDate() {
        return registDate;
    }

    /**
     * 设置 registDate
     * @param registDate registDate
     */
    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    /**
     * 获取 exRightsDate
     * @return exRightsDate.
     */
    public Date getExRightsDate() {
        return exRightsDate;
    }

    /**
     * 设置 exRightsDate
     * @param exRightsDate exRightsDate
     */
    public void setExRightsDate(Date exRightsDate) {
        this.exRightsDate = exRightsDate;
    }

    /**
     * 获取 arriveDate
     * @return arriveDate.
     */
    public Date getArriveDate() {
        return arriveDate;
    }

    /**
     * 设置 arriveDate
     * @param arriveDate arriveDate
     */
    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    /**
     * 获取 reInvesDate
     * @return reInvesDate.
     */
    public Date getReInvesDate() {
        return reInvesDate;
    }

    /**
     * 设置 reInvesDate
     * @param reInvesDate reInvesDate
     */
    public void setReInvesDate(Date reInvesDate) {
        this.reInvesDate = reInvesDate;
    }

    /**
     * 获取 registShare
     * @return registShare.
     */
    public BigDecimal getRegistShare() {
        return registShare;
    }

    /**
     * 设置 registShare
     * @param registShare registShare
     */
    public void setRegistShare(BigDecimal registShare) {
        this.registShare = registShare;
    }

    /**
     * 获取 unitCprice
     * @return unitCprice.
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 unitCprice
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 获取 tradeAmt
     * @return tradeAmt.
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 tradeAmt
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 qty
     * @return qty.
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 qty
     * @param qty qty
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * 获取 chlId
     *
     * @return chlId
     */
    @Override
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 chlId
     *
     * @param chlId chlId
     */
    @Override
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }
    /**
     * 获取 uploadFileList
     * @return uploadFileList.
     */
    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }
    /**
     * 设置 uploadFileList
     * @param uploadFileList uploadFileList
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetTypeName(){
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getAssetType());
    }
}
