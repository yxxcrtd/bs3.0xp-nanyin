/*
 * 文件名：ProdTradeInfo002Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：chenfangfang
 * 修改时间：2017年12月12日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */

package com.joyintech.tams.report.submitmaintenance.prodfactorinfo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.report.common.base.TableRow;
import com.joyintech.tams.report.submitmaintenance.prodfactorinfo.validate.ProdFactorInfo001Validator;
import com.joyintech.tams.report.common.util.BigDecimalSerializer;
/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author liuertong
 * @version 1.0 2017年12月15日
 * @see ProdFactorInfo001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ProdFactorInfo001Validator.class)
public class ProdFactorInfo001Dto extends TableRow {

    /**
     * 经办人
     */
    private String agent;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 数据状态
     */
    private String statusName;

    /**
     * 投资者类型
     */
    private String investorType;

    /**
     * 投资者类型
     */
    private String investorTypeName;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 发行要素维护完整
     */
    private String isIntact;

    /**
     * 发行要素维护完整
     */
    private String isIntactName;

    /**
     * 发行要素维护完整
     */
    private String isIntactNameName;

    /**
     * 报备状态
     */
    private String declareStatus;

    /**
     * 报备状态
     */
    private String declareStatusName;

    /**
     * 发行状态
     */
    private String issuingStatus;

    /**
     * 发行状态
     */
    private String issuingStatusName;

    /**
     * 产品风险等级
     */
    private String riskLevel;

    /**
     * 产品收益类型
     */
    private String profitType;

    /**
     * 产品收益类型
     */
    private String profitTypeName;

    /**
     * 产品期限
     */
    private String term;

    /**
     * 募集币种
     */
    private String raiseCcy;

    /**
     * 募集币种
     */
    private String raiseCcyName;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 募集起始日期
     */
    private Date raiseVdate;

    /**
     * 募集起始日期
     */
    private String raiseVdateStr;

    /**
     * 募集结束日期
     */
    private Date raiseEnddate;

    /**
     * 募集结束日期
     */
    private String raiseEnddateStr;

    /**
     * 层级类别
     */
    private String gradeType;

    /**
     * 行内标识码
     */
    private String prodInsId;

    /**
     * 募集起始日期（从）
     */
    private Date raisePstrdate;

    /**
     * 募集起始日期（到）
     */
    private Date raisePenddate;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 产品品牌
     */
    private String prodBrand;

    /**
     * 产品期次
     */
    private Integer prodStage;

    /**
     * 产品设计人姓名
     */
    private String designerName;

    /**
     * 产品设计人身份证号
     */
    private String designerId;

    /**
     * 产品审批人姓名
     */
    private String approverName;

    /**
     * 产品审批人身份证号
     */
    private String approverId;

    /**
     * 投资经理姓名
     */
    private String managerName;

    /**
     * 投资经理身份证号
     */
    private String managerId;

    /**
     * 会计核算方式
     */
    private String bookMode;

    /**
     * 业务联络人姓名
     */
    private String liaisonName;

    /**
     * 业务联络人座机
     */
    private String liaisonTel;

    /**
     * 业务联络人手机
     */
    private String liaisonMobile;

    /**
     * 业务联络人邮箱
     */
    private String liaisonEmail;

    /**
     * 资金投向地区
     */
    private String investArae;

    /**
     * 产品投资国家或地区
     */
    private String investCountry;

    /**
     * 理财业务服务模式
     */
    private String finanSerModel;

    /**
     * 产品运作模式
     */
    private String operateMode;

    /**
     * 产品资产配置方式
     */
    private String cmmode;

    /**
     * 产品管理模式
     */
    private String pmmode;

    /**
     * 实际管理人名称
     */
    private String mname;

    /**
     * 投资资产类型
     */
    private String invAssetType;

    /**
     * 合作模式
     */
    private String contMode;

    /**
     * 合作机构
     */
    private String cooperationName;

    /**
     * 结构性产品挂钩标的
     */
    private String conPrdTraget;

    /**
     * 是否有预期收益率
     */
    private String isYield;

    /**
     * 是否有收益率测算依据
     */
    private String isYieldMeasBasis;

    /**
     * 投资资产种类及比例
     */
    private String invTypeRat;

    /**
     * 投资者风险偏好
     */
    private String riskType;

    /**
     * 预计客户最高年收益率%
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal highYield;

    /**
     * 预计客户最低年收益率%
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal lowYield;

    /**
     * 产品销售地区
     */
    private String saleArea;

    /**
     * 起点销售金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal saleAmtStr;

    /**
     * 兑付本金币种
     */
    private String cashPrinCcy;

    /**
     * 兑付收益币种
     */
    private String cashProfCcy;

    /**
     * 计划募集金额（元）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal raiseAmtPlan;

    /**
     * 销售手续费率%
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal saleFeeRate;

    /**
     * 投资本金到账日
     */
    private String investPrinDate;

    /**
     * 投资收益到账日
     */
    private String investProfDate;

    /**
     * 托管费率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal truFeeRate;

    /**
     * 境内托管机构代码
     */
    private String inDepCode;

    /**
     * 境内托管机构名称
     */
    private String inDepName;

    /**
     * 境外托管机构国别
     */
    private String outDepCtry;

    /**
     * 境外托管机构名称
     */
    private String outDepName;

    /**
     * 发行机构提前终止权标识
     */
    private String termRightFlag;

    /**
     * 客户赎回权标识
     */
    private String cusRedeemFlag;

    /**
     * 产品增信标识
     */
    private String creditFlag;

    /**
     * 产品增信机构类型
     */
    private String creditType;

    /**
     * 产品定价方式
     */
    private String pricedMode;

    /**
     * 产品起始日期
     */
    private Date prdVdate;

    /**
     * 产品终止日期
     */
    private Date prdMdate;

    /**
     * 平均预计客户年收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal expAvgYield;

    /**
     * 是否结构化分层产品
     */
    private String isConstracte;

    /**
     * 收益率分档情况说明
     */
    private String yieldComment;

    /**
     * 开放模式
     */
    private String openMode;

    /**
     * 规律开放周期
     */
    private String openCircle;

    /**
     * 其他规律开放周期（天）
     */
    private Short openCircleDays;

    /**
     * 无规律开放说明
     */
    private String ruleComment;

    /**
     * 首次开放周期起始日
     */
    private Date firstOpenDay;

    /**
     * 节假日是否开放
     */
    private String holidayOnFlag;

    /**
     * 平均开放次数（年化）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal avgOpenCount;

    /**
     * 开放期业务
     */
    private String openBuss;

    /**
     * 开放期业务说明
     */
    private String openBussRemark;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 收益模式
     */
    private String profitMode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 产品增信形式
     */
    private String creditMode;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 预计客户最低年收益率%
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal expMinYield;

    /**
     * 预计客户最高年收益率%
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal expMaxYield;

    /**
     * 预计客户最高年收益率%
     */
    private String highYieldStr;

    /**
     * 预计客户最低年收益率%
     */
    private String lowYieldStr;

    /**
     * 平均预计客户年收益率
     */
    private String expAvgYieldStr;

    /**
     * 托管费率
     */
    private String truFeeRateStr;

    /**
     * 销售手续费率%
     */
    private String saleFeeRateStr;

    /**
     * 删除list
     */
    private List<ProdFactorInfo001Dto> dtoList;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvestorType() {
        return investorType;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getIsIntact() {
        return isIntact;
    }

    public void setIsIntact(String isIntact) {
        this.isIntact = isIntact;
    }

    public String getDeclareStatus() {
        return declareStatus;
    }

    public void setDeclareStatus(String declareStatus) {
        this.declareStatus = declareStatus;
    }

    public String getIssuingStatus() {
        return issuingStatus;
    }

    public void setIssuingStatus(String issuingStatus) {
        this.issuingStatus = issuingStatus;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getRaiseCcy() {
        return raiseCcy;
    }

    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy;
    }

    public Date getRaiseVdate() {
        return raiseVdate;
    }

    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate;
    }

    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getInvestorTypeName() {
        return investorTypeName;
    }

    public void setInvestorTypeName(String investorTypeName) {
        this.investorTypeName = investorTypeName;
    }

    public String getIsIntactName() {
        return isIntactName;
    }

    public void setIsIntactName(String isIntactName) {
        this.isIntactName = isIntactName;
    }

    public String getIsIntactNameName() {
        return isIntactNameName;
    }

    public void setIsIntactNameName(String isIntactNameName) {
        this.isIntactNameName = isIntactNameName;
    }

    public String getDeclareStatusName() {
        return declareStatusName;
    }

    public void setDeclareStatusName(String declareStatusName) {
        this.declareStatusName = declareStatusName;
    }

    public String getIssuingStatusName() {
        return issuingStatusName;
    }

    public void setIssuingStatusName(String issuingStatusName) {
        this.issuingStatusName = issuingStatusName;
    }

    public String getProfitTypeName() {
        return profitTypeName;
    }

    public void setProfitTypeName(String profitTypeName) {
        this.profitTypeName = profitTypeName;
    }

    public String getRaiseCcyName() {
        return raiseCcyName;
    }

    public void setRaiseCcyName(String raiseCcyName) {
        this.raiseCcyName = raiseCcyName;
    }

    public String getRaiseVdateStr() {
        return raiseVdateStr;
    }

    public void setRaiseVdateStr(String raiseVdateStr) {
        this.raiseVdateStr = raiseVdateStr;
    }

    public String getRaiseEnddateStr() {
        return raiseEnddateStr;
    }

    public void setRaiseEnddateStr(String raiseEnddateStr) {
        this.raiseEnddateStr = raiseEnddateStr;
    }

    public List<ProdFactorInfo001Dto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<ProdFactorInfo001Dto> dtoList) {
        this.dtoList = dtoList;
    }

    /**
     * 获取 gradeType
     * 
     * @return gradeType.
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * 设置 gradeType
     * 
     * @param gradeType gradeType
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    /**
     * 获取 prodInsId
     * 
     * @return prodInsId.
     */
    public String getProdInsId() {
        return prodInsId;
    }

    /**
     * 设置 prodInsId
     * 
     * @param prodInsId prodInsId
     */
    public void setProdInsId(String prodInsId) {
        this.prodInsId = prodInsId;
    }

    /**
     * 获取 raisePstrdate
     * 
     * @return raisePstrdate.
     */
    public Date getRaisePstrdate() {
        return raisePstrdate;
    }

    /**
     * 设置 raisePstrdate
     * 
     * @param raisePstrdate raisePstrdate
     */
    public void setRaisePstrdate(Date raisePstrdate) {
        this.raisePstrdate = raisePstrdate;
    }

    /**
     * 获取 raisePenddate
     * 
     * @return raisePenddate.
     */
    public Date getRaisePenddate() {
        return raisePenddate;
    }

    /**
     * 设置 raisePenddate
     * 
     * @param raisePenddate raisePenddate
     */
    public void setRaisePenddate(Date raisePenddate) {
        this.raisePenddate = raisePenddate;
    }

    /**
     * 获取 templateName
     * 
     * @return templateName.
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 设置 templateName
     * 
     * @param templateName templateName
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * 获取 prodBrand
     * 
     * @return prodBrand.
     */
    public String getProdBrand() {
        return prodBrand;
    }

    /**
     * 设置 prodBrand
     * 
     * @param prodBrand prodBrand
     */
    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand;
    }

    /**
     * 获取 prodStage
     * 
     * @return prodStage.
     */
    public Integer getProdStage() {
        return prodStage;
    }

    /**
     * 设置 prodStage
     * 
     * @param prodStage prodStage
     */
    public void setProdStage(Integer prodStage) {
        this.prodStage = prodStage;
    }

    /**
     * 获取 designerName
     * 
     * @return designerName.
     */
    public String getDesignerName() {
        return designerName;
    }

    /**
     * 设置 designerName
     * 
     * @param designerName designerName
     */
    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    /**
     * 获取 designerId
     * 
     * @return designerId.
     */
    public String getDesignerId() {
        return designerId;
    }

    /**
     * 设置 designerId
     * 
     * @param designerId designerId
     */
    public void setDesignerId(String designerId) {
        this.designerId = designerId;
    }

    /**
     * 获取 approverName
     * 
     * @return approverName.
     */
    public String getApproverName() {
        return approverName;
    }

    /**
     * 设置 approverName
     * 
     * @param approverName approverName
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    /**
     * 获取 approverId
     * 
     * @return approverId.
     */
    public String getApproverId() {
        return approverId;
    }

    /**
     * 设置 approverId
     * 
     * @param approverId approverId
     */
    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    /**
     * 获取 managerName
     * 
     * @return managerName.
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 设置 managerName
     * 
     * @param managerName managerName
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * 获取 managerId
     * 
     * @return managerId.
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置 managerId
     * 
     * @param managerId managerId
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    /**
     * 获取 bookMode
     * 
     * @return bookMode.
     */
    public String getBookMode() {
        return bookMode;
    }

    /**
     * 设置 bookMode
     * 
     * @param bookMode bookMode
     */
    public void setBookMode(String bookMode) {
        this.bookMode = bookMode;
    }

    /**
     * 获取 liaisonName
     * 
     * @return liaisonName.
     */
    public String getLiaisonName() {
        return liaisonName;
    }

    /**
     * 设置 liaisonName
     * 
     * @param liaisonName liaisonName
     */
    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName;
    }

    /**
     * 获取 liaisonTel
     * 
     * @return liaisonTel.
     */
    public String getLiaisonTel() {
        return liaisonTel;
    }

    /**
     * 设置 liaisonTel
     * 
     * @param liaisonTel liaisonTel
     */
    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel;
    }

    /**
     * 获取 liaisonMobile
     * 
     * @return liaisonMobile.
     */
    public String getLiaisonMobile() {
        return liaisonMobile;
    }

    /**
     * 设置 liaisonMobile
     * 
     * @param liaisonMobile liaisonMobile
     */
    public void setLiaisonMobile(String liaisonMobile) {
        this.liaisonMobile = liaisonMobile;
    }

    /**
     * 获取 liaisonEmail
     * 
     * @return liaisonEmail.
     */
    public String getLiaisonEmail() {
        return liaisonEmail;
    }

    /**
     * 设置 liaisonEmail
     * 
     * @param liaisonEmail liaisonEmail
     */
    public void setLiaisonEmail(String liaisonEmail) {
        this.liaisonEmail = liaisonEmail;
    }

    /**
     * 获取 investArae
     * 
     * @return investArae.
     */
    public String getInvestArae() {
        return investArae;
    }

    /**
     * 设置 investArae
     * 
     * @param investArae investArae
     */
    public void setInvestArae(String investArae) {
        this.investArae = investArae;
    }

    /**
     * 获取 investCountry
     * 
     * @return investCountry.
     */
    public String getInvestCountry() {
        return investCountry;
    }

    /**
     * 设置 investCountry
     * 
     * @param investCountry investCountry
     */
    public void setInvestCountry(String investCountry) {
        this.investCountry = investCountry;
    }

    /**
     * 获取 finanSerModel
     * 
     * @return finanSerModel.
     */
    public String getFinanSerModel() {
        return finanSerModel;
    }

    /**
     * 设置 finanSerModel
     * 
     * @param finanSerModel finanSerModel
     */
    public void setFinanSerModel(String finanSerModel) {
        this.finanSerModel = finanSerModel;
    }

    /**
     * 获取 operateMode
     * 
     * @return operateMode.
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 设置 operateMode
     * 
     * @param operateMode operateMode
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    /**
     * 获取 cmmode
     * 
     * @return cmmode.
     */
    public String getCmmode() {
        return cmmode;
    }

    /**
     * 设置 cmmode
     * 
     * @param cmmode cmmode
     */
    public void setCmmode(String cmmode) {
        this.cmmode = cmmode;
    }

    /**
     * 获取 pmmode
     * 
     * @return pmmode.
     */
    public String getPmmode() {
        return pmmode;
    }

    /**
     * 设置 pmmode
     * 
     * @param pmmode pmmode
     */
    public void setPmmode(String pmmode) {
        this.pmmode = pmmode;
    }

    /**
     * 获取 mname
     * 
     * @return mname.
     */
    public String getMname() {
        return mname;
    }

    /**
     * 设置 mname
     * 
     * @param mname mname
     */
    public void setMname(String mname) {
        this.mname = mname;
    }

    /**
     * 获取 invAssetType
     * 
     * @return invAssetType.
     */
    public String getInvAssetType() {
        return invAssetType;
    }

    /**
     * 设置 invAssetType
     * 
     * @param invAssetType invAssetType
     */
    public void setInvAssetType(String invAssetType) {
        this.invAssetType = invAssetType;
    }

    /**
     * 获取 contMode
     * 
     * @return contMode.
     */
    public String getContMode() {
        return contMode;
    }

    /**
     * 设置 contMode
     * 
     * @param contMode contMode
     */
    public void setContMode(String contMode) {
        this.contMode = contMode;
    }

    /**
     * 获取 cooperationName
     * 
     * @return cooperationName.
     */
    public String getCooperationName() {
        return cooperationName;
    }

    /**
     * 设置 cooperationName
     * 
     * @param cooperationName cooperationName
     */
    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName;
    }

    /**
     * 获取 conPrdTraget
     * 
     * @return conPrdTraget.
     */
    public String getConPrdTraget() {
        return conPrdTraget;
    }

    /**
     * 设置 conPrdTraget
     * 
     * @param conPrdTraget conPrdTraget
     */
    public void setConPrdTraget(String conPrdTraget) {
        this.conPrdTraget = conPrdTraget;
    }

    /**
     * 获取 isYield
     * 
     * @return isYield.
     */
    public String getIsYield() {
        return isYield;
    }

    /**
     * 设置 isYield
     * 
     * @param isYield isYield
     */
    public void setIsYield(String isYield) {
        this.isYield = isYield;
    }

    /**
     * 获取 isYieldMeasBasis
     * 
     * @return isYieldMeasBasis.
     */
    public String getIsYieldMeasBasis() {
        return isYieldMeasBasis;
    }

    /**
     * 设置 isYieldMeasBasis
     * 
     * @param isYieldMeasBasis isYieldMeasBasis
     */
    public void setIsYieldMeasBasis(String isYieldMeasBasis) {
        this.isYieldMeasBasis = isYieldMeasBasis;
    }

    /**
     * 获取 invTypeRat
     * 
     * @return invTypeRat.
     */
    public String getInvTypeRat() {
        return invTypeRat;
    }

    /**
     * 设置 invTypeRat
     * 
     * @param invTypeRat invTypeRat
     */
    public void setInvTypeRat(String invTypeRat) {
        this.invTypeRat = invTypeRat;
    }

    /**
     * 获取 riskType
     * 
     * @return riskType.
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * 设置 riskType
     * 
     * @param riskType riskType
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    /**
     * 获取 highYield
     * 
     * @return highYield.
     */
    public BigDecimal getHighYield() {
        return highYield;
    }

    /**
     * 设置 highYield
     * 
     * @param highYield highYield
     */
    public void setHighYield(BigDecimal highYield) {
        this.highYield = highYield;
    }

    /**
     * 获取 lowYield
     * 
     * @return lowYield.
     */
    public BigDecimal getLowYield() {
        return lowYield ;
    }

    /**
     * 设置 lowYield
     * 
     * @param lowYield lowYield
     */
    public void setLowYield(BigDecimal lowYield) {
        this.lowYield = lowYield;
    }

    /**
     * 获取 saleArea
     * 
     * @return saleArea.
     */
    public String getSaleArea() {
        return saleArea;
    }

    /**
     * 设置 saleArea
     * 
     * @param saleArea saleArea
     */
    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea;
    }

    /**
     * 获取 saleAmtStr
     * 
     * @return saleAmtStr.
     */
    public BigDecimal getSaleAmtStr() {
        return saleAmtStr;
    }

    /**
     * 设置 saleAmtStr
     * 
     * @param saleAmtStr saleAmtStr
     */
    public void setSaleAmtStr(BigDecimal saleAmtStr) {
        this.saleAmtStr = saleAmtStr;
    }

    /**
     * 获取 cashPrinCcy
     * 
     * @return cashPrinCcy.
     */
    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    /**
     * 设置 cashPrinCcy
     * 
     * @param cashPrinCcy cashPrinCcy
     */
    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy;
    }

    /**
     * 获取 cashProfCcy
     * 
     * @return cashProfCcy.
     */
    public String getCashProfCcy() {
        return cashProfCcy;
    }

    /**
     * 设置 cashProfCcy
     * 
     * @param cashProfCcy cashProfCcy
     */
    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy;
    }

    /**
     * 获取 raiseAmtPlan
     * 
     * @return raiseAmtPlan.
     */
    public BigDecimal getRaiseAmtPlan() {
        return raiseAmtPlan;
    }

    /**
     * 设置 raiseAmtPlan
     * 
     * @param raiseAmtPlan raiseAmtPlan
     */
    public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
        this.raiseAmtPlan = raiseAmtPlan;
    }

    /**
     * 获取 saleFeeRate
     * 
     * @return saleFeeRate.
     */
    public BigDecimal getSaleFeeRate() {
        return saleFeeRate;
    }

    /**
     * 设置 saleFeeRate
     * 
     * @param saleFeeRate saleFeeRate
     */
    public void setSaleFeeRate(BigDecimal saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    /**
     * 获取 investPrinDate
     * 
     * @return investPrinDate.
     */
    public String getInvestPrinDate() {
        return investPrinDate;
    }

    /**
     * 设置 investPrinDate
     * 
     * @param investPrinDate investPrinDate
     */
    public void setInvestPrinDate(String investPrinDate) {
        this.investPrinDate = investPrinDate;
    }

    /**
     * 获取 investProfDate
     * 
     * @return investProfDate.
     */
    public String getInvestProfDate() {
        return investProfDate;
    }

    /**
     * 设置 investProfDate
     * 
     * @param investProfDate investProfDate
     */
    public void setInvestProfDate(String investProfDate) {
        this.investProfDate = investProfDate;
    }

    /**
     * 获取 truFeeRate
     * 
     * @return truFeeRate.
     */
    public BigDecimal getTruFeeRate() {
        return truFeeRate;
    }

    /**
     * 设置 truFeeRate
     * 
     * @param truFeeRate truFeeRate
     */
    public void setTruFeeRate(BigDecimal truFeeRate) {
        this.truFeeRate = truFeeRate;
    }

    /**
     * 获取 inDepCode
     * 
     * @return inDepCode.
     */
    public String getInDepCode() {
        return inDepCode;
    }

    /**
     * 设置 inDepCode
     * 
     * @param inDepCode inDepCode
     */
    public void setInDepCode(String inDepCode) {
        this.inDepCode = inDepCode;
    }

    /**
     * 获取 inDepName
     * 
     * @return inDepName.
     */
    public String getInDepName() {
        return inDepName;
    }

    /**
     * 设置 inDepName
     * 
     * @param inDepName inDepName
     */
    public void setInDepName(String inDepName) {
        this.inDepName = inDepName;
    }

    /**
     * 获取 outDepCtry
     * 
     * @return outDepCtry.
     */
    public String getOutDepCtry() {
        return outDepCtry;
    }

    /**
     * 设置 outDepCtry
     * 
     * @param outDepCtry outDepCtry
     */
    public void setOutDepCtry(String outDepCtry) {
        this.outDepCtry = outDepCtry;
    }

    /**
     * 获取 outDepName
     * 
     * @return outDepName.
     */
    public String getOutDepName() {
        return outDepName;
    }

    /**
     * 设置 outDepName
     * 
     * @param outDepName outDepName
     */
    public void setOutDepName(String outDepName) {
        this.outDepName = outDepName;
    }

    /**
     * 获取 termRightFlag
     * 
     * @return termRightFlag.
     */
    public String getTermRightFlag() {
        return termRightFlag;
    }

    /**
     * 设置 termRightFlag
     * 
     * @param termRightFlag termRightFlag
     */
    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag;
    }

    /**
     * 获取 cusRedeemFlag
     * 
     * @return cusRedeemFlag.
     */
    public String getCusRedeemFlag() {
        return cusRedeemFlag;
    }

    /**
     * 设置 cusRedeemFlag
     * 
     * @param cusRedeemFlag cusRedeemFlag
     */
    public void setCusRedeemFlag(String cusRedeemFlag) {
        this.cusRedeemFlag = cusRedeemFlag;
    }

    /**
     * 获取 creditFlag
     * 
     * @return creditFlag.
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    /**
     * 设置 creditFlag
     * 
     * @param creditFlag creditFlag
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    /**
     * 获取 creditType
     * 
     * @return creditType.
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 creditType
     * 
     * @param creditType creditType
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    /**
     * 获取 pricedMode
     * 
     * @return pricedMode.
     */
    public String getPricedMode() {
        return pricedMode;
    }

    /**
     * 设置 pricedMode
     * 
     * @param pricedMode pricedMode
     */
    public void setPricedMode(String pricedMode) {
        this.pricedMode = pricedMode;
    }

    /**
     * 获取 prdVdate
     * 
     * @return prdVdate.
     */
    public Date getPrdVdate() {
        return prdVdate;
    }

    /**
     * 设置 prdVdate
     * 
     * @param prdVdate prdVdate
     */
    public void setPrdVdate(Date prdVdate) {
        this.prdVdate = prdVdate;
    }

    /**
     * 获取 prdMdate
     * 
     * @return prdMdate.
     */
    public Date getPrdMdate() {
        return prdMdate;
    }

    /**
     * 设置 prdMdate
     * 
     * @param prdMdate prdMdate
     */
    public void setPrdMdate(Date prdMdate) {
        this.prdMdate = prdMdate;
    }

    /**
     * 获取 expAvgYield
     * 
     * @return expAvgYield.
     */
    public BigDecimal getExpAvgYield() {
        return expAvgYield;
    }

    /**
     * 设置 expAvgYield
     * 
     * @param expAvgYield expAvgYield
     */
    public void setExpAvgYield(BigDecimal expAvgYield) {
        this.expAvgYield = expAvgYield;
    }

    /**
     * 获取 isConstracte
     * 
     * @return isConstracte.
     */
    public String getIsConstracte() {
        return isConstracte;
    }

    /**
     * 设置 isConstracte
     * 
     * @param isConstracte isConstracte
     */
    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte;
    }

    /**
     * 获取 yieldComment
     * 
     * @return yieldComment.
     */
    public String getYieldComment() {
        return yieldComment;
    }

    /**
     * 设置 yieldComment
     * 
     * @param yieldComment yieldComment
     */
    public void setYieldComment(String yieldComment) {
        this.yieldComment = yieldComment;
    }

    /**
     * 获取 openMode
     * 
     * @return openMode.
     */
    public String getOpenMode() {
        return openMode;
    }

    /**
     * 设置 openMode
     * 
     * @param openMode openMode
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    /**
     * 获取 openCircle
     * 
     * @return openCircle.
     */
    public String getOpenCircle() {
        return openCircle;
    }

    /**
     * 设置 openCircle
     * 
     * @param openCircle openCircle
     */
    public void setOpenCircle(String openCircle) {
        this.openCircle = openCircle;
    }

    /**
     * 获取 openCircleDays
     * 
     * @return openCircleDays.
     */
    public Short getOpenCircleDays() {
        return openCircleDays;
    }

    /**
     * 设置 openCircleDays
     * 
     * @param openCircleDays openCircleDays
     */
    public void setOpenCircleDays(Short openCircleDays) {
        this.openCircleDays = openCircleDays;
    }

    /**
     * 获取 ruleComment
     * 
     * @return ruleComment.
     */
    public String getRuleComment() {
        return ruleComment;
    }

    /**
     * 设置 ruleComment
     * 
     * @param ruleComment ruleComment
     */
    public void setRuleComment(String ruleComment) {
        this.ruleComment = ruleComment;
    }

    /**
     * 获取 firstOpenDay
     * 
     * @return firstOpenDay.
     */
    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 设置 firstOpenDay
     * 
     * @param firstOpenDay firstOpenDay
     */
    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 获取 holidayOnFlag
     * 
     * @return holidayOnFlag.
     */
    public String getHolidayOnFlag() {
        return holidayOnFlag;
    }

    /**
     * 设置 holidayOnFlag
     * 
     * @param holidayOnFlag holidayOnFlag
     */
    public void setHolidayOnFlag(String holidayOnFlag) {
        this.holidayOnFlag = holidayOnFlag;
    }

    /**
     * 获取 avgOpenCount
     * 
     * @return avgOpenCount.
     */
    public BigDecimal getAvgOpenCount() {
        return avgOpenCount;
    }

    /**
     * 设置 avgOpenCount
     * 
     * @param avgOpenCount avgOpenCount
     */
    public void setAvgOpenCount(BigDecimal avgOpenCount) {
        this.avgOpenCount = avgOpenCount;
    }

    /**
     * 获取 openBuss
     * 
     * @return openBuss.
     */
    public String getOpenBuss() {
        return openBuss;
    }

    /**
     * 设置 openBuss
     * 
     * @param openBuss openBuss
     */
    public void setOpenBuss(String openBuss) {
        this.openBuss = openBuss;
    }

    /**
     * 获取 openBussRemark
     * 
     * @return openBussRemark.
     */
    public String getOpenBussRemark() {
        return openBussRemark;
    }

    /**
     * 设置 openBussRemark
     * 
     * @param openBussRemark openBussRemark
     */
    public void setOpenBussRemark(String openBussRemark) {
        this.openBussRemark = openBussRemark;
    }

    /**
     * 获取 deptCode
     * 
     * @return deptCode.
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 deptCode
     * 
     * @param deptCode deptCode
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * 获取 workflowId
     * 
     * @return workflowId.
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 workflowId
     * 
     * @param workflowId workflowId
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 dataSource
     * 
     * @return dataSource.
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 dataSource
     * 
     * @param dataSource dataSource
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取 profitMode
     * 
     * @return profitMode.
     */
    public String getProfitMode() {
        return profitMode;
    }

    /**
     * 设置 profitMode
     * 
     * @param profitMode profitMode
     */
    public void setProfitMode(String profitMode) {
        this.profitMode = profitMode;
    }

    /**
     * 获取 remark
     * 
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * 
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 creditMode
     * 
     * @return creditMode.
     */
    public String getCreditMode() {
        return creditMode;
    }

    /**
     * 设置 creditMode
     * 
     * @param creditMode creditMode
     */
    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode;
    }

    /**
     * 获取 orgCode
     * 
     * @return orgCode.
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 orgCode
     * 
     * @param orgCode orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取 issuePartyId
     * 
     * @return issuePartyId.
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 issuePartyId
     * 
     * @param issuePartyId issuePartyId
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    /**
     * 获取 tableName
     * 
     * @return tableName.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置 tableName
     * 
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 获取 sendStatus
     * 
     * @return sendStatus.
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 sendStatus
     * 
     * @param sendStatus sendStatus
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取 agent
     * 
     * @return agent.
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 agent
     * 
     * @param agent agent
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * 获取 expMinYield
     * 
     * @return expMinYield.
     */
    public BigDecimal getExpMinYield() {
        return expMinYield;
    }

    /**
     * 设置 expMinYield
     * 
     * @param expMinYield expMinYield
     */
    public void setExpMinYield(BigDecimal expMinYield) {
        this.expMinYield = expMinYield;
    }

    /**
     * 获取 expMaxYield
     * 
     * @return expMaxYield.
     */
    public BigDecimal getExpMaxYield() {
        return expMaxYield;
    }

    /**
     * 设置 expMaxYield
     * 
     * @param expMaxYield expMaxYield
     */
    public void setExpMaxYield(BigDecimal expMaxYield) {
        this.expMaxYield = expMaxYield;
    }

    /**
     * 获取 highYieldStr
     * 
     * @return highYieldStr.
     */
    public String getHighYieldStr() {
        return highYieldStr;
    }

    /**
     * 设置 highYieldStr
     * 
     * @param highYieldStr highYieldStr
     */
    public void setHighYieldStr(String highYieldStr) {
        this.highYieldStr = highYieldStr;
    }

    /**
     * 获取 lowYieldStr
     * 
     * @return lowYieldStr.
     */
    public String getLowYieldStr() {
        return lowYieldStr;
    }

    /**
     * 设置 lowYieldStr
     * 
     * @param lowYieldStr lowYieldStr
     */
    public void setLowYieldStr(String lowYieldStr) {
        this.lowYieldStr = lowYieldStr;
    }

    /**
     * 获取 expAvgYieldStr
     * 
     * @return expAvgYieldStr.
     */
    public String getExpAvgYieldStr() {
        return expAvgYieldStr;
    }

    /**
     * 设置 expAvgYieldStr
     * 
     * @param expAvgYieldStr expAvgYieldStr
     */
    public void setExpAvgYieldStr(String expAvgYieldStr) {
        this.expAvgYieldStr = expAvgYieldStr;
    }

    /**
     * 获取 truFeeRateStr
     * 
     * @return truFeeRateStr.
     */
    public String getTruFeeRateStr() {
        return truFeeRateStr;
    }

    /**
     * 设置 truFeeRateStr
     * 
     * @param truFeeRateStr truFeeRateStr
     */
    public void setTruFeeRateStr(String truFeeRateStr) {
        this.truFeeRateStr = truFeeRateStr;
    }

    /**
     * 获取 saleFeeRateStr
     * 
     * @return saleFeeRateStr.
     */
    public String getSaleFeeRateStr() {
        return saleFeeRateStr;
    }

    /**
     * 设置 saleFeeRateStr
     * 
     * @param saleFeeRateStr saleFeeRateStr
     */
    public void setSaleFeeRateStr(String saleFeeRateStr) {
        this.saleFeeRateStr = saleFeeRateStr;
    }

}
