/*
 * 文件名：EtrProject001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrproject.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrproject.validator.EtrProjectValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 项目类委托dto
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年10月11日
 * @see EtrProjectDto
 * @since 1.0
 */
@PageId("EtrProject002")
@ValidLogic(targetClass = EtrProjectValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrProjectDto extends EtrEntrust implements ProcessEntity {
    /**
     *  所属部门
     */
    private String room;
    /**
     *  序号
     */
    private String indexId;
    /**
     *  通道
     */
    private String chlId;
    /**
     *  通道名称
     */
    private String chlIdName;
    /**
     *  交易方向名称
     */
    private String psName;
    /**
     *  交易对手名称
     */
    private  String counterIdName;
    /**
     *  通道可用现金
     */
    private BigDecimal channelAvailableCash;
    /**
     * 指令可用现金
     */
    private BigDecimal proAvailableCash;
    /**
     * 组合T+1可用现金
     */
    private BigDecimal proAvailableCash1;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 本金金额
     */
    private BigDecimal prinAmt;
    /**
     * 清算速度
     */
    private String settleSpeed;
    /**
     * 净投资收益率
     */
    private BigDecimal investRate;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1807574758768083939L;

    /**
     * 资产起息日
     */
    private Date finvdate;
    /**
     * 资产到期日
     */
    private Date finmdate;
    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 人员信息业务组件DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 选中的数据
     */
    private transient List<EtrProjectDto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 资产一级分类
     */
    private String assetType1;
// 项目类资产五级分类改造 add by renping 2019-08-01 start
    /**
     * 资产二级分类
     */
    private String type9;

    /**
     * 资产三级分类
     */
    private String type10;

    /**
     * 资产四级分类
     */
    private String type11;

    /**
     * 获取 type9
     *
     * @return type9.
     */
    public String getType9() {
        return type9;
    }

    /**
     * 设置 type9
     *
     * @param type9
     *            type9
     */
    public void setType9(String type9) {
        this.type9 = type9;
    }

    /**
     * 获取 type10
     *
     * @return type10.
     */
    public String getType10() {
        return type10;
    }

    /**
     * 设置 type10
     *
     * @param type10
     *            type10
     */
    public void setType10(String type10) {
        this.type10 = type10;
    }

    /**
     * 获取 type11
     *
     * @return type11.
     */
    public String getType11() {
        return type11;
    }

    /**
     * 设置 type11
     *
     * @param type11
     *            type11
     */
    public void setType11(String type11) {
        this.type11 = type11;
    }

    /**
     * 获取 type9Name
     *
     * @return type9Name.
     */
    public String getType9Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getType9());
    }

    /**
     * 获取 type10Name
     *
     * @return type10Name.
     */
    public String getType10Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, this.getType10());
    }

    /**
     * 获取 type11Name
     *
     * @return type11Name.
     */
    public String getType11Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, this.getType11());
    }
    // 项目类资产五级分类改造 add by renping 2019-08-01 end
    /**
     * 金融产品全称
     */
    private String finprodName;
    /**
     * 完成额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finishAmt;

    /**
     * 交易完成度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal transactioncom;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;



    /**
     * 获取 finvdate
     * @return finvdate.
     */
    public Date getFinvdate() {
        return finvdate;
    }



    /**
     * 交易对手类型
     */
    private String counterType;

    /**
     * 授信来源
     */
    private String creditSource;

    /**
     * 获取 counterType
     *
     * @return counterType
     */
    public String getCounterType() {
        return counterType;
    }

    /**
     * 设置 counterType
     *
     * @param counterType counterType
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }



    /**
     * 设置 finvdate
     * @param finvdate finvdate
     */
    public void setFinvdate(Date finvdate) {
        this.finvdate = finvdate;
    }







    /**
     * 获取 finmdate
     * @return finmdate.
     */
    public Date getFinmdate() {
        return finmdate;
    }







    /**
     * 设置 finmdate
     * @param finmdate finmdate
     */
    public void setFinmdate(Date finmdate) {
        this.finmdate = finmdate;
    }






    /**
     * 获取 riskTrialRetDto
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }






    /**
     * 设置 riskTrialRetDto
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }





    /**
     * 获取 assetAvailablePosition
     * @return assetAvailablePosition.
     */
    public BigDecimal getAssetAvailablePosition() {
        return assetAvailablePosition;
    }





    /**
     * 设置 assetAvailablePosition
     * @param assetAvailablePosition assetAvailablePosition
     */
    public void setAssetAvailablePosition(BigDecimal assetAvailablePosition) {
        this.assetAvailablePosition = assetAvailablePosition;
    }




    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }




    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }



    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @NotEmpty(fieldName = "EtrProject002.finprodId", groups = {ValidateGroupEdit.class})
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
     * 获取 etrInvestPersonInfo
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }


    /**
     * 设置 etrInvestPersonInfo
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }


    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrProjectDto> getSelectedItem() {
        return selectedItem;
    }


    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrProjectDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    /**
     * 获取 closeWay
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }


    /**
     * 设置 closeWay
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }


    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }


    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }


    /**
     * 获取 assetType1
     * @return assetType1.
     */
    public String getAssetType1() {
        return assetType1;
    }


    /**
     * 设置 assetType1
     * @param assetType1 assetType1
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }


    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }


    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }


    /**
     * 获取 finishAmt
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }


    /**
     * 设置 finishAmt
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }


    /**
     * 获取 transactioncom
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }


    /**
     * 设置 transactioncom
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }

    /**
     *
     * 委托状态数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusEntrustStatus(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    /**
     *
     * 币种数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusCcy(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    /**
     *
     * 交易方向数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusPs(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }
    /**
     *
     * 资产类型数据字典转换
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusAssetType1(){
        return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, this.getAssetType1());
    }

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId() {
        return super.getEntrustId();
    }

    @Override
    @NotEmpty(fieldName = "EtrProject002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }

    @Override
    @NotEmpty(fieldName = "EtrProject002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }

    @Override
    @NotEmpty(fieldName = "EtrProject002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }

//    @Override
//    @NotEmpty(fieldName = "EtrProject002.finprodId", groups = {ValidateGroupEdit.class})
//    public String getFinprodId(){
//        return super.getFinprodId();
//    }
    @Override
    @NotEmpty(fieldName = "EtrProject002.entrustAmt", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "EtrProject002.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt(){
        return super.getEntrustAmt();
    }
    @Override
    @NotEmpty(fieldName = "EtrProject002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }

    @Override
    @NotEmpty(fieldName = "EtrProject002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    @Override
    public String getChlId() {
        return chlId;
    }

    @Override
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getCounterIdName() {
        return counterIdName;
    }

    public void setCounterIdName(String counterIdName) {
        this.counterIdName = counterIdName;
    }

    public BigDecimal getChannelAvailableCash() {
        return channelAvailableCash;
    }

    public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
        this.channelAvailableCash = channelAvailableCash;
    }

    @Override
    public BigDecimal getProAvailableCash() {
        return proAvailableCash;
    }

    @Override
    public void setProAvailableCash(BigDecimal proAvailableCash) {
        this.proAvailableCash = proAvailableCash;
    }

    public BigDecimal getProAvailableCash1() {
        return proAvailableCash1;
    }

    public void setProAvailableCash1(BigDecimal proAvailableCash1) {
        this.proAvailableCash1 = proAvailableCash1;
    }

    @Override
    public String getCounterId() {
        return counterId;
    }

    @Override
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public BigDecimal getInvestRate() {
        return investRate;
    }

    public void setInvestRate(BigDecimal investRate) {
        this.investRate = investRate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getChlIdName() {
        return chlIdName;
    }

    public void setChlIdName(String chlIdName) {
        this.chlIdName = chlIdName;
    }

    public String getCreditSource() {
        return creditSource;
    }

    public void setCreditSource(String creditSource) {
        this.creditSource = creditSource;
    }
}
