package com.joyintech.tams.invest.entrust.etrinstruction.dto; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luocheng
 * 修改时间：2020/1/6
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrinstruction.validator.EtrInstructionValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("EtrInstruction002")
@ValidLogic(targetClass = EtrInstructionValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrInstructionDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 页面提交模式
     */
    private String pageModel;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<EtrInstructionDto> selectedItem;

    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 委托操作类型
     */
    private String entrustOperationType;

    /**
     * 资产一级分类
     */
    private String assetType1;

    /**
     * 资产二级分类
     */
    private String  assetType10;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 融资人/管理人
     */
    private String repFinancier;

    /**
     * 收益率
     */
    private BigDecimal matureYield;

    /**
     * 委托金额
     */
    private BigDecimal entrustAmt;
    /**
     * 合同签署结果
     */
    private String contractResult;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     *起息日
     */
    private Date vDate;

    /**
     * 到期日
     */
    private Date mDate;

    private String vdateStr;

    private String mdateStr;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 上传附件路径
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 投资人员Dto
     */
    private BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto;

    public String getVdateStr() {
        return vdateStr;
    }

    public void setVdateStr(String vdateStr) {
        this.vdateStr = vdateStr;
    }

    public String getMdateStr() {
        return mdateStr;
    }

    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }

    public BussEtrInvestPersonInfoDto getBussEtrInvestPersonInfoDto() {
        return bussEtrInvestPersonInfoDto;
    }

    public void setBussEtrInvestPersonInfoDto(
        BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto) {
        this.bussEtrInvestPersonInfoDto = bussEtrInvestPersonInfoDto;
    }

    public String getContractResult() {
        return contractResult;
    }

    public void setContractResult(String contractResult) {
        this.contractResult = contractResult;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    public String getEntrustId(){
        return super.getEntrustId();
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getRepFinancier() {
        return repFinancier;
    }

    public void setRepFinancier(String repFinancier) {
        this.repFinancier = repFinancier;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getAssetType10() {
        return assetType10;
    }

    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }

    public BigDecimal getMatureYield() {
        return matureYield;
    }

    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }

    @Override public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    @Override public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<EtrInstructionDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<EtrInstructionDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getCloseWay() {
        return CloseWay;
    }

    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }

    public String getEntrustOperationType() {
        return entrustOperationType;
    }

    public void setEntrustOperationType(String entrustOperationType) {
        this.entrustOperationType = entrustOperationType;
    }

    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 委托状态数据字典转换 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String getEntrustStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }

    /**
     * 币种数据字典转换 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    /**
     * 一级资产类型字典转换 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String getAssetType1Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, this.getAssetType1());
    }

    /**
     * 二级资产类型字典转换 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public String getAssetType10Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.计划类资产二级分类, this.getAssetType10());
    }
}
