/*
 * 文件名：MstTradeOpponentDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.msttradeopponent.dto;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.MstCounterParty;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
/**
 * 交易对手管理实体类
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see MstTradeOpponentDto
 * @since 1.0
 */
public class MstTradeOpponentDto  extends MstCounterParty implements ITableRow{
    /**
     * 序列化值
     */
    private static final long serialVersionUID = 5631714671113877371L;
    /**
     * 人行信托代码
     */
    private String bankTrustCode;
    /**
     * 清算账户实体类
     */
    private List<StlCapacctDto> selectStlCapacct;
    /**
     * 现金流实体类
     */
    private List<FinCashDto> selectFinCash;
    /**
     * 市场代码
     */
    private String customerId;
    /**
     * 托管人
     */
    private String trusteeId;
    /**
     * 行内类型一
     */
    private String type1;
    /**
     * 行内类型二
     */
    private String type2;
    /**
     * 标的类金融产品代码
     */
    private String finprodId;
    /**
     * 金融产品类型
     */
    private String finprodType;
    /**
     * 资金实际投向
     */
    private String repCapiInvest;
    /**
     * 资金运用方式
     */
    private String repCapUseWay;
    /**
     * 资金运用行业
     */
    private String repCapUseIndu;
    /**
     * 资管计划属性
     */
    private String repPlanProp;
    /**
     * 是否有预期收益率
     */
    private String repYieldFlag;
    /**
     * 预期最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxRate;
    /**
     * 预期最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinRate;
    /**
     * 购买结构
     */
    private String repBuySruct;
    /**
     * 管理方式
     */
    private String repManageType;
    /**
     * 是否掌控计划底层资产详情
     */
    private String repIsCtrlBttom;
    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAmt;
    /**
     * 资管计划发起人机构编码
     */
    private String repPartyCode;
    /**
     * 资管计划登记编码
     */
    private String repRegistCode;
    /**
     * 资管计划成立日
     */
    private Date repSetDate;
    /**
     * 资管计划到期日
     */
    private Date repEndDate;
    /**
     * 交易相关合计费率（%）
     */
    private BigDecimal repTradeFeeRate;
    /**
     * 中介服务机构合计费率（%）
     */
    private BigDecimal repAgencyFeeRate;
    /**
     * 其他合计费率（%）
     */
    private BigDecimal repOtherFeeRate;
    /**
     * 备注
     */
    private String repRemarks;
    /**
     * 选中的数据
     */
    private transient List<MstTradeOpponentDto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * pageModel操作类型
     */
    private String pageModel;
    /**
     * elementId请求的节点名
     */
    private String elementId;
    /**
     * 所属总行
     */
    private String headOffice;
    
    private String feeType;
    /**
     * 分支序号
     */
    private Long branch;
    
    /**
     * 获取 repPartyCode
     * @return repPartyCode.
     */
    public String getRepPartyCode() {
        return repPartyCode;
    }
    
    /**
     * 设置 repPartyCode
     * @param repPartyCode repPartyCode
     */
    public void setRepPartyCode(String repPartyCode) {
        this.repPartyCode = repPartyCode;
    }
    
    /**
     * 获取 repRegistCode
     * @return repRegistCode.
     */
    public String getRepRegistCode() {
        return repRegistCode;
    }
    
    /**
     * 设置 repRegistCode
     * @param repRegistCode repRegistCode
     */
    public void setRepRegistCode(String repRegistCode) {
        this.repRegistCode = repRegistCode;
    }
    
    /**
     * 获取 repSetDate
     * @return repSetDate.
     */
    public Date getRepSetDate() {
        return repSetDate;
    }
    
    /**
     * 设置 repSetDate
     * @param repSetDate repSetDate
     */
    public void setRepSetDate(Date repSetDate) {
        this.repSetDate = repSetDate;
    }
    
    /**
     * 获取 repEndDate
     * @return repEndDate.
     */
    public Date getRepEndDate() {
        return repEndDate;
    }
    
    /**
     * 设置 repEndDate
     * @param repEndDate repEndDate
     */
    public void setRepEndDate(Date repEndDate) {
        this.repEndDate = repEndDate;
    }
    
    /**
     * 获取 repTradeFeeRate
     * @return repTradeFeeRate.
     */
    public BigDecimal getRepTradeFeeRate() {
        return repTradeFeeRate;
    }
    
    /**
     * 设置 repTradeFeeRate
     * @param repTradeFeeRate repTradeFeeRate
     */
    public void setRepTradeFeeRate(BigDecimal repTradeFeeRate) {
        this.repTradeFeeRate = repTradeFeeRate;
    }
    
    /**
     * 获取 repAgencyFeeRate
     * @return repAgencyFeeRate.
     */
    public BigDecimal getRepAgencyFeeRate() {
        return repAgencyFeeRate;
    }
    
    /**
     * 设置 repAgencyFeeRate
     * @param repAgencyFeeRate repAgencyFeeRate
     */
    public void setRepAgencyFeeRate(BigDecimal repAgencyFeeRate) {
        this.repAgencyFeeRate = repAgencyFeeRate;
    }
    
    /**
     * 获取 repOtherFeeRate
     * @return repOtherFeeRate.
     */
    public BigDecimal getRepOtherFeeRate() {
        return repOtherFeeRate;
    }
    
    /**
     * 设置 repOtherFeeRate
     * @param repOtherFeeRate repOtherFeeRate
     */
    public void setRepOtherFeeRate(BigDecimal repOtherFeeRate) {
        this.repOtherFeeRate = repOtherFeeRate;
    }

    /**
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }
    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }
       
    
    /**
     * 获取 feeType
     * @return feeType.
     */
    public String getFeeType() {
        return feeType;
    }
    
    /**
     * 设置 feeType
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    /**
     * 获取 bankTrustCode
     * @return bankTrustCode.
     */
    public String getBankTrustCode() {
        return bankTrustCode;
    }
    
    /**
     * 设置 bankTrustCode
     * @param bankTrustCode bankTrustCode
     */
    public void setBankTrustCode(String bankTrustCode) {
        this.bankTrustCode = bankTrustCode;
    }

    /**
     * 获取 headOffice
     * @return headOffice.
     */
    public String getHeadOffice() {
        return headOffice;
    }
    
    /**
     * 设置 headOffice
     * @param headOffice headOffice
     */
    public void setHeadOffice(String headOffice) {
        this.headOffice = headOffice;
    }
    /**
     * 定义主键
     */
    @Override
    public String getCounterId(){
        return super.getCounterId();
    }
    /**
     * 获取管理人中文字符
     * @return 管理人
     * @see
     * @since 1.0
     */
    @Override
    public String getManagerId(){
        return super.getManagerId();
    }
    /**
     * 获取 customerId
     * @return customerId.
     */
    public String getCustomerId() {
        return customerId;
    }
    /**
     * 设置 customerId
     * @param customerId customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    /**
     * 获取 repFinprodType
     * @return repFinprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }
    /**
     * 设置 repFinprodType
     * @param repFinprodType repFinprodType
     */
    public void setFinprodType(String repFinprodType) {
        this.finprodType = repFinprodType;
    }
    /**
     * 获取 repFinprodId
     * @return repFinprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }
    /**
     * 设置 repFinprodId
     * @param repFinprodId repFinprodId
     */
    public void setFinprodId(String repFinprodId) {
        this.finprodId = repFinprodId;
    }
    /**
     * 获取 selectStlCapacct
     * @return selectStlCapacct.
     */
    public List<StlCapacctDto> getSelectStlCapacct() {
        return selectStlCapacct;
    }
    /**
     * 设置 selectStlCapacct
     * @param selectStlCapacct selectStlCapacct
     */
    public void setSelectStlCapacct(List<StlCapacctDto> selectStlCapacct) {
        this.selectStlCapacct = selectStlCapacct;
    }
    /**
     * 获取 selectFinCash
     * @return selectFinCash.
     */
    public List<FinCashDto> getSelectFinCash() {
        return selectFinCash;
    }
    /**
     * 设置 selectFinCash
     * @param selectFinCash selectFinCash
     */
    public void setSelectFinCash(List<FinCashDto> selectFinCash) {
        this.selectFinCash = selectFinCash;
    }
    /**
     * 获取 repCapiInvest
     * @return repCapiInvest.
     */
    public String getRepCapiInvest() {
        return repCapiInvest;
    }
    /**
     * 设置 repCapiInvest
     * @param repCapiInvest repCapiInvest
     */
    public void setRepCapiInvest(String repCapiInvest) {
        this.repCapiInvest = repCapiInvest;
    }
    /**
     * 获取 repCapUseWay
     * @return repCapUseWay.
     */
    public String getRepCapUseWay() {
        return repCapUseWay;
    }
    /**
     * 设置 repCapUseWay
     * @param repCapUseWay repCapUseWay
     */
    public void setRepCapUseWay(String repCapUseWay) {
        this.repCapUseWay = repCapUseWay;
    }
    /**
     * 获取 repCapUseIndu
     * @return repCapUseIndu.
     */
    public String getRepCapUseIndu() {
        return repCapUseIndu;
    }
    /**
     * 设置 repCapUseIndu
     * @param repCapUseIndu repCapUseIndu
     */
    public void setRepCapUseIndu(String repCapUseIndu) {
        this.repCapUseIndu = repCapUseIndu;
    }
    /**
     * 获取 repPlanProp
     * @return repPlanProp.
     */
    public String getRepPlanProp() {
        return repPlanProp;
    }
    /**
     * 设置 repPlanProp
     * @param repPlanProp repPlanProp
     */
    public void setRepPlanProp(String repPlanProp) {
        this.repPlanProp = repPlanProp;
    }
    /**
     * 获取 repYieldFlag
     * @return repYieldFlag.
     */
    public String getRepYieldFlag() {
        return repYieldFlag;
    }
    /**
     * 设置 repYieldFlag
     * @param repYieldFlag repYieldFlag
     */
    public void setRepYieldFlag(String repYieldFlag) {
        this.repYieldFlag = repYieldFlag;
    }
    /**
     * 获取 repMaxRate
     * @return repMaxRate.
     */
    public BigDecimal getRepMaxRate() {
        return repMaxRate;
    }
    /**
     * 设置 repMaxRate
     * @param repMaxRate repMaxRate
     */
    public void setRepMaxRate(BigDecimal repMaxRate) {
        this.repMaxRate = repMaxRate;
    }
    /**
     * 获取 repMinRate
     * @return repMinRate.
     */
    public BigDecimal getRepMinRate() {
        return repMinRate;
    }
    /**
     * 设置 repMinRate
     * @param repMinRate repMinRate
     */
    public void setRepMinRate(BigDecimal repMinRate) {
        this.repMinRate = repMinRate;
    }
    /**
     * 获取 repBuySruct
     * @return repBuySruct.
     */
    public String getRepBuySruct() {
        return repBuySruct;
    }
    /**
     * 设置 repBuySruct
     * @param repBuySruct repBuySruct
     */
    public void setRepBuySruct(String repBuySruct) {
        this.repBuySruct = repBuySruct;
    }
    /**
     * 获取 repManageType
     * @return repManageType.
     */
    public String getRepManageType() {
        return repManageType;
    }
    /**
     * 设置 repManageType
     * @param repManageType repManageType
     */
    public void setRepManageType(String repManageType) {
        this.repManageType = repManageType;
    }
    /**
     * 获取 repIsCtrlBttom
     * @return repIsCtrlBttom.
     */
    public String getRepIsCtrlBttom() {
        return repIsCtrlBttom;
    }
    /**
     * 设置 repIsCtrlBttom
     * @param repIsCtrlBttom repIsCtrlBttom
     */
    public void setRepIsCtrlBttom(String repIsCtrlBttom) {
        this.repIsCtrlBttom = repIsCtrlBttom;
    }
    
    /**
     * 获取 repAmt
     * @return repAmt.
     */
    public BigDecimal getRepAmt() {
        return repAmt;
    }
    
    /**
     * 设置 repAmt
     * @param repAmt repAmt
     */
    public void setRepAmt(BigDecimal repAmt) {
        this.repAmt = repAmt;
    }
    /**
     * 获取 remarks
     * @return remarks.
     */
    public String getRepRemarks() {
        return repRemarks;
    }
    /**
     * 备注信息
     * @param repRemarks 备注
     * @see
     * @since 1.0
     */
    public void setRepRemarks(String repRemarks) {
        this.repRemarks = repRemarks;
    }
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<MstTradeOpponentDto> getSelectedItem() {
        return selectedItem;
    }
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstTradeOpponentDto> selectedItem) {
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
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
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
     * 获取 type1
     * @return type1.
     */
    public String getType1() {
        return type1;
    }
    /**
     * 设置 type1
     * @param type1 type1
     */
    public void setType1(String type1) {
        this.type1 = type1;
    }
    /**
     * 获取 type2
     * @return type2.
     */
    public String getType2() {
        return type2;
    }
    /**
     * 设置 type2
     * @param type2 type2
     */
    public void setType2(String type2) {
        this.type2 = type2;
    }
    /**
     * 获取 trusteeId
     * @return trusteeId.
     */
    public String getTrusteeId() {
        return trusteeId;
    }
    /**
     * 设置 trusteeId
     * @param trusteeId trusteeId
     */
    public void setTrusteeId(String trusteeId) {
        this.trusteeId = trusteeId;
    }
}
