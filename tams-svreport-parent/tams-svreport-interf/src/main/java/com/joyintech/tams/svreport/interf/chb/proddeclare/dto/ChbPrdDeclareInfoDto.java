package com.joyintech.tams.svreport.interf.chb.proddeclare.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_PRD_DECLARE_INFO表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-10-09
 */
public class ChbPrdDeclareInfoDto extends BaseEntity{
    /**
     * null
     */
    private String declareUuid;

    /**
     * 行内标识码
     */
    private String prodInsId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 产品品牌
     */
    private String prodBrand;

    /**
     * 产品期次
     */
    private BigDecimal prodStage;

    /**
     * 产品审批人身份证号
     */
    private String approverId;

    /**
     * 产品审批人姓名
     */
    private String approverName;

    /**
     * 产品设计人身份证号
     */
    private String designerId;

    /**
     * 产品设计人姓名
     */
    private String designerName;

    /**
     * 投资经理身份证号
     */
    private String managerId;

    /**
     * 投资经理姓名
     */
    private String managerName;

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
     * 产品收益类型
     */
    private String profitType;

    /**
     * 产品期限
     */
    private String termtype;

    /**
     * 是否金融同业专属
     */
    private String excFinSector;

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
     * 募集方式 01公募 02私募
     */
    private String raiseType;

    /**
     * 产品资产配置方式
     */
    private String cmmode;

    /**
     * 产品管理模式
     */
    private String pmmode;

    /**
     * 实际管理人员名称
     */
    private String mname;

    /**
     * 产品定价方式
     */
    private String pricedMode;

    /**
     * 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    private String ivstorientation;

    /**
     * 业绩比较基准%
     */
    private BigDecimal yield;

    /**
     * 是否设置最短持有期限
     */
    private String isShortTerm;

    /**
     * 最短持有期限(天)
     */
    private BigDecimal shortTerm;

    /**
     * 最短持有期后是否自由赎回
     */
    private String isFreeRedem;

    /**
     * 是否现金管理类
     */
    private String cashManaCate;

    /**
     * 产品销售区域
     */
    private String saleArea;

    /**
     * 起点销售金额
     */
    private BigDecimal saleAmtStr;

    /**
     * 募集币种
     */
    private String raiseCcy;

    /**
     * 兑付本金币种
     */
    private String cashPrinCcy;

    /**
     * 兑付收益币种
     */
    private String cashProfCcy;

    /**
     * 销售手续费
     */
    private BigDecimal saleFeeRate;

    /**
     * 托管费率
     */
    private BigDecimal truFeeRate;

    /**
     * 募集起始日期（从）
     */
    private Date raisePstrdate;

    /**
     * 募集起始日期（到）
     */
    private Date raisePenddate;

    /**
     * 计划募集金额
     */
    private BigDecimal raiseAmtPlan;

    /**
     * 境内托管机构名称
     */
    private String inDepName;

    /**
     * 境内托管机构代码
     */
    private String inDepCode;

    /**
     * 境外托管机构国别
     */
    private String outDepCtry;

    /**
     * 境外托管机构名称
     */
    private String outDepName;

    /**
     * 投资者风险偏好
     */
    private String riskType;

    /**
     * 产品风险等级
     */
    private String riskLevel;

    /**
     * 发型机构提前终止权标识
     */
    private String termRightFlag;

    /**
     * 客户赎回权标识
     */
    private String cusRedeemFlag;

    /**
     * 投资管理费率%
     */
    private BigDecimal investFeeRate;

    /**
     * 合作模式
     */
    private String contMode;

    /**
     * 合作机构名称
     */
    private String cooperationName;

    /**
     * 投资本金到账日
     */
    private String investPrinDate;

    /**
     * 投资收益到账日
     */
    private String investProfDate;

    /**
     * 产品增信标识
     */
    private String creditFlag;

    /**
     * 产品增信机构类型
     */
    private String creditType;

    /**
     * 产品增信形式
     */
    private String creditMode;

    /**
     * 投资资产种类及比例
     */
    private String invTypeRat;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * null
     */
    private String deptCode;

    /**
     * null
     */
    private String workflowId;

    /**
     * null
     */
    private String datasource;

    /**
     * 是否合规新产品
     */
    private String isFhxgcp;

    /**
     * null
     * @return DECLARE_UUID null
     */
    public String getDeclareUuid() {
        return declareUuid;
    }

    /**
     * null
     * @param declareUuid null
     */
    public void setDeclareUuid(String declareUuid) {
        this.declareUuid = declareUuid == null ? null : declareUuid.trim();
    }

    /**
     * 行内标识码
     * @return PROD_INS_ID 行内标识码
     */
    public String getProdInsId() {
        return prodInsId;
    }

    /**
     * 行内标识码
     * @param prodInsId 行内标识码
     */
    public void setProdInsId(String prodInsId) {
        this.prodInsId = prodInsId == null ? null : prodInsId.trim();
    }

    /**
     * 产品名称
     * @return PROD_NAME 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 产品品牌
     * @return PROD_BRAND 产品品牌
     */
    public String getProdBrand() {
        return prodBrand;
    }

    /**
     * 产品品牌
     * @param prodBrand 产品品牌
     */
    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand == null ? null : prodBrand.trim();
    }

    /**
     * 产品期次
     * @return PROD_STAGE 产品期次
     */
    public BigDecimal getProdStage() {
        return prodStage;
    }

    /**
     * 产品期次
     * @param prodStage 产品期次
     */
    public void setProdStage(BigDecimal prodStage) {
        this.prodStage = prodStage;
    }

    /**
     * 产品审批人身份证号
     * @return APPROVER_ID 产品审批人身份证号
     */
    public String getApproverId() {
        return approverId;
    }

    /**
     * 产品审批人身份证号
     * @param approverId 产品审批人身份证号
     */
    public void setApproverId(String approverId) {
        this.approverId = approverId == null ? null : approverId.trim();
    }

    /**
     * 产品审批人姓名
     * @return APPROVER_NAME 产品审批人姓名
     */
    public String getApproverName() {
        return approverName;
    }

    /**
     * 产品审批人姓名
     * @param approverName 产品审批人姓名
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    /**
     * 产品设计人身份证号
     * @return DESIGNER_ID 产品设计人身份证号
     */
    public String getDesignerId() {
        return designerId;
    }

    /**
     * 产品设计人身份证号
     * @param designerId 产品设计人身份证号
     */
    public void setDesignerId(String designerId) {
        this.designerId = designerId == null ? null : designerId.trim();
    }

    /**
     * 产品设计人姓名
     * @return DESIGNER_NAME 产品设计人姓名
     */
    public String getDesignerName() {
        return designerName;
    }

    /**
     * 产品设计人姓名
     * @param designerName 产品设计人姓名
     */
    public void setDesignerName(String designerName) {
        this.designerName = designerName == null ? null : designerName.trim();
    }

    /**
     * 投资经理身份证号
     * @return MANAGER_ID 投资经理身份证号
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 投资经理身份证号
     * @param managerId 投资经理身份证号
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    /**
     * 投资经理姓名
     * @return MANAGER_NAME 投资经理姓名
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 投资经理姓名
     * @param managerName 投资经理姓名
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    /**
     * 业务联络人姓名
     * @return LIAISON_NAME 业务联络人姓名
     */
    public String getLiaisonName() {
        return liaisonName;
    }

    /**
     * 业务联络人姓名
     * @param liaisonName 业务联络人姓名
     */
    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName == null ? null : liaisonName.trim();
    }

    /**
     * 业务联络人座机
     * @return LIAISON_TEL 业务联络人座机
     */
    public String getLiaisonTel() {
        return liaisonTel;
    }

    /**
     * 业务联络人座机
     * @param liaisonTel 业务联络人座机
     */
    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel == null ? null : liaisonTel.trim();
    }

    /**
     * 业务联络人手机
     * @return LIAISON_MOBILE 业务联络人手机
     */
    public String getLiaisonMobile() {
        return liaisonMobile;
    }

    /**
     * 业务联络人手机
     * @param liaisonMobile 业务联络人手机
     */
    public void setLiaisonMobile(String liaisonMobile) {
        this.liaisonMobile = liaisonMobile == null ? null : liaisonMobile.trim();
    }

    /**
     * 业务联络人邮箱
     * @return LIAISON_EMAIL 业务联络人邮箱
     */
    public String getLiaisonEmail() {
        return liaisonEmail;
    }

    /**
     * 业务联络人邮箱
     * @param liaisonEmail 业务联络人邮箱
     */
    public void setLiaisonEmail(String liaisonEmail) {
        this.liaisonEmail = liaisonEmail == null ? null : liaisonEmail.trim();
    }

    /**
     * 产品收益类型
     * @return PROFIT_TYPE 产品收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 产品收益类型
     * @param profitType 产品收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 产品期限
     * @return TERMTYPE 产品期限
     */
    public String getTermtype() {
        return termtype;
    }

    /**
     * 产品期限
     * @param termtype 产品期限
     */
    public void setTermtype(String termtype) {
        this.termtype = termtype == null ? null : termtype.trim();
    }

    /**
     * 是否金融同业专属
     * @return EXC_FIN_SECTOR 是否金融同业专属
     */
    public String getExcFinSector() {
        return excFinSector;
    }

    /**
     * 是否金融同业专属
     * @param excFinSector 是否金融同业专属
     */
    public void setExcFinSector(String excFinSector) {
        this.excFinSector = excFinSector == null ? null : excFinSector.trim();
    }

    /**
     * 资金投向地区
     * @return INVEST_ARAE 资金投向地区
     */
    public String getInvestArae() {
        return investArae;
    }

    /**
     * 资金投向地区
     * @param investArae 资金投向地区
     */
    public void setInvestArae(String investArae) {
        this.investArae = investArae == null ? null : investArae.trim();
    }

    /**
     * 产品投资国家或地区
     * @return INVEST_COUNTRY 产品投资国家或地区
     */
    public String getInvestCountry() {
        return investCountry;
    }

    /**
     * 产品投资国家或地区
     * @param investCountry 产品投资国家或地区
     */
    public void setInvestCountry(String investCountry) {
        this.investCountry = investCountry == null ? null : investCountry.trim();
    }

    /**
     * 理财业务服务模式
     * @return FINAN_SER_MODEL 理财业务服务模式
     */
    public String getFinanSerModel() {
        return finanSerModel;
    }

    /**
     * 理财业务服务模式
     * @param finanSerModel 理财业务服务模式
     */
    public void setFinanSerModel(String finanSerModel) {
        this.finanSerModel = finanSerModel == null ? null : finanSerModel.trim();
    }

    /**
     * 产品运作模式
     * @return OPERATE_MODE 产品运作模式
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 产品运作模式
     * @param operateMode 产品运作模式
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 募集方式 01公募 02私募
     * @return RAISE_TYPE 募集方式 01公募 02私募
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 募集方式 01公募 02私募
     * @param raiseType 募集方式 01公募 02私募
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 产品资产配置方式
     * @return CMMODE 产品资产配置方式
     */
    public String getCmmode() {
        return cmmode;
    }

    /**
     * 产品资产配置方式
     * @param cmmode 产品资产配置方式
     */
    public void setCmmode(String cmmode) {
        this.cmmode = cmmode == null ? null : cmmode.trim();
    }

    /**
     * 产品管理模式
     * @return PMMODE 产品管理模式
     */
    public String getPmmode() {
        return pmmode;
    }

    /**
     * 产品管理模式
     * @param pmmode 产品管理模式
     */
    public void setPmmode(String pmmode) {
        this.pmmode = pmmode == null ? null : pmmode.trim();
    }

    /**
     * 实际管理人员名称
     * @return MNAME 实际管理人员名称
     */
    public String getMname() {
        return mname;
    }

    /**
     * 实际管理人员名称
     * @param mname 实际管理人员名称
     */
    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    /**
     * 产品定价方式
     * @return PRICED_MODE 产品定价方式
     */
    public String getPricedMode() {
        return pricedMode;
    }

    /**
     * 产品定价方式
     * @param pricedMode 产品定价方式
     */
    public void setPricedMode(String pricedMode) {
        this.pricedMode = pricedMode == null ? null : pricedMode.trim();
    }

    /**
     * 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @return IVSTORIENTATION 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public String getIvstorientation() {
        return ivstorientation;
    }

    /**
     * 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @param ivstorientation 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
    }

    /**
     * 业绩比较基准%
     * @return YIELD 业绩比较基准%
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 业绩比较基准%
     * @param yield 业绩比较基准%
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 是否设置最短持有期限
     * @return IS_SHORT_TERM 是否设置最短持有期限
     */
    public String getIsShortTerm() {
        return isShortTerm;
    }

    /**
     * 是否设置最短持有期限
     * @param isShortTerm 是否设置最短持有期限
     */
    public void setIsShortTerm(String isShortTerm) {
        this.isShortTerm = isShortTerm == null ? null : isShortTerm.trim();
    }

    /**
     * 最短持有期限(天)
     * @return SHORT_TERM 最短持有期限(天)
     */
    public BigDecimal getShortTerm() {
        return shortTerm;
    }

    /**
     * 最短持有期限(天)
     * @param shortTerm 最短持有期限(天)
     */
    public void setShortTerm(BigDecimal shortTerm) {
        this.shortTerm = shortTerm;
    }

    /**
     * 最短持有期后是否自由赎回
     * @return IS_FREE_REDEM 最短持有期后是否自由赎回
     */
    public String getIsFreeRedem() {
        return isFreeRedem;
    }

    /**
     * 最短持有期后是否自由赎回
     * @param isFreeRedem 最短持有期后是否自由赎回
     */
    public void setIsFreeRedem(String isFreeRedem) {
        this.isFreeRedem = isFreeRedem == null ? null : isFreeRedem.trim();
    }

    /**
     * 是否现金管理类
     * @return CASH_MANA_CATE 是否现金管理类
     */
    public String getCashManaCate() {
        return cashManaCate;
    }

    /**
     * 是否现金管理类
     * @param cashManaCate 是否现金管理类
     */
    public void setCashManaCate(String cashManaCate) {
        this.cashManaCate = cashManaCate == null ? null : cashManaCate.trim();
    }

    /**
     * 产品销售区域
     * @return SALE_AREA 产品销售区域
     */
    public String getSaleArea() {
        return saleArea;
    }

    /**
     * 产品销售区域
     * @param saleArea 产品销售区域
     */
    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea == null ? null : saleArea.trim();
    }

    /**
     * 起点销售金额
     * @return SALE_AMT_STR 起点销售金额
     */
    public BigDecimal getSaleAmtStr() {
        return saleAmtStr;
    }

    /**
     * 起点销售金额
     * @param saleAmtStr 起点销售金额
     */
    public void setSaleAmtStr(BigDecimal saleAmtStr) {
        this.saleAmtStr = saleAmtStr;
    }

    /**
     * 募集币种
     * @return RAISE_CCY 募集币种
     */
    public String getRaiseCcy() {
        return raiseCcy;
    }

    /**
     * 募集币种
     * @param raiseCcy 募集币种
     */
    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy == null ? null : raiseCcy.trim();
    }

    /**
     * 兑付本金币种
     * @return CASH_PRIN_CCY 兑付本金币种
     */
    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    /**
     * 兑付本金币种
     * @param cashPrinCcy 兑付本金币种
     */
    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy == null ? null : cashPrinCcy.trim();
    }

    /**
     * 兑付收益币种
     * @return CASH_PROF_CCY 兑付收益币种
     */
    public String getCashProfCcy() {
        return cashProfCcy;
    }

    /**
     * 兑付收益币种
     * @param cashProfCcy 兑付收益币种
     */
    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy == null ? null : cashProfCcy.trim();
    }

    /**
     * 销售手续费
     * @return SALE_FEE_RATE 销售手续费
     */
    public BigDecimal getSaleFeeRate() {
        return saleFeeRate;
    }

    /**
     * 销售手续费
     * @param saleFeeRate 销售手续费
     */
    public void setSaleFeeRate(BigDecimal saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    /**
     * 托管费率
     * @return TRU_FEE_RATE 托管费率
     */
    public BigDecimal getTruFeeRate() {
        return truFeeRate;
    }

    /**
     * 托管费率
     * @param truFeeRate 托管费率
     */
    public void setTruFeeRate(BigDecimal truFeeRate) {
        this.truFeeRate = truFeeRate;
    }

    /**
     * 募集起始日期（从）
     * @return RAISE_PSTRDATE 募集起始日期（从）
     */
    public Date getRaisePstrdate() {
        return raisePstrdate;
    }

    /**
     * 募集起始日期（从）
     * @param raisePstrdate 募集起始日期（从）
     */
    public void setRaisePstrdate(Date raisePstrdate) {
        this.raisePstrdate = raisePstrdate;
    }

    /**
     * 募集起始日期（到）
     * @return RAISE_PENDDATE 募集起始日期（到）
     */
    public Date getRaisePenddate() {
        return raisePenddate;
    }

    /**
     * 募集起始日期（到）
     * @param raisePenddate 募集起始日期（到）
     */
    public void setRaisePenddate(Date raisePenddate) {
        this.raisePenddate = raisePenddate;
    }

    /**
     * 计划募集金额
     * @return RAISE_AMT_PLAN 计划募集金额
     */
    public BigDecimal getRaiseAmtPlan() {
        return raiseAmtPlan;
    }

    /**
     * 计划募集金额
     * @param raiseAmtPlan 计划募集金额
     */
    public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
        this.raiseAmtPlan = raiseAmtPlan;
    }

    /**
     * 境内托管机构名称
     * @return IN_DEP_NAME 境内托管机构名称
     */
    public String getInDepName() {
        return inDepName;
    }

    /**
     * 境内托管机构名称
     * @param inDepName 境内托管机构名称
     */
    public void setInDepName(String inDepName) {
        this.inDepName = inDepName == null ? null : inDepName.trim();
    }

    /**
     * 境内托管机构代码
     * @return IN_DEP_CODE 境内托管机构代码
     */
    public String getInDepCode() {
        return inDepCode;
    }

    /**
     * 境内托管机构代码
     * @param inDepCode 境内托管机构代码
     */
    public void setInDepCode(String inDepCode) {
        this.inDepCode = inDepCode == null ? null : inDepCode.trim();
    }

    /**
     * 境外托管机构国别
     * @return OUT_DEP_CTRY 境外托管机构国别
     */
    public String getOutDepCtry() {
        return outDepCtry;
    }

    /**
     * 境外托管机构国别
     * @param outDepCtry 境外托管机构国别
     */
    public void setOutDepCtry(String outDepCtry) {
        this.outDepCtry = outDepCtry == null ? null : outDepCtry.trim();
    }

    /**
     * 境外托管机构名称
     * @return OUT_DEP_NAME 境外托管机构名称
     */
    public String getOutDepName() {
        return outDepName;
    }

    /**
     * 境外托管机构名称
     * @param outDepName 境外托管机构名称
     */
    public void setOutDepName(String outDepName) {
        this.outDepName = outDepName == null ? null : outDepName.trim();
    }

    /**
     * 投资者风险偏好
     * @return RISK_TYPE 投资者风险偏好
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * 投资者风险偏好
     * @param riskType 投资者风险偏好
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType == null ? null : riskType.trim();
    }

    /**
     * 产品风险等级
     * @return RISK_LEVEL 产品风险等级
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 产品风险等级
     * @param riskLevel 产品风险等级
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 发型机构提前终止权标识
     * @return TERM_RIGHT_FLAG 发型机构提前终止权标识
     */
    public String getTermRightFlag() {
        return termRightFlag;
    }

    /**
     * 发型机构提前终止权标识
     * @param termRightFlag 发型机构提前终止权标识
     */
    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag == null ? null : termRightFlag.trim();
    }

    /**
     * 客户赎回权标识
     * @return CUS_REDEEM_FLAG 客户赎回权标识
     */
    public String getCusRedeemFlag() {
        return cusRedeemFlag;
    }

    /**
     * 客户赎回权标识
     * @param cusRedeemFlag 客户赎回权标识
     */
    public void setCusRedeemFlag(String cusRedeemFlag) {
        this.cusRedeemFlag = cusRedeemFlag == null ? null : cusRedeemFlag.trim();
    }

    /**
     * 投资管理费率%
     * @return INVEST_FEE_RATE 投资管理费率%
     */
    public BigDecimal getInvestFeeRate() {
        return investFeeRate;
    }

    /**
     * 投资管理费率%
     * @param investFeeRate 投资管理费率%
     */
    public void setInvestFeeRate(BigDecimal investFeeRate) {
        this.investFeeRate = investFeeRate;
    }

    /**
     * 合作模式
     * @return CONT_MODE 合作模式
     */
    public String getContMode() {
        return contMode;
    }

    /**
     * 合作模式
     * @param contMode 合作模式
     */
    public void setContMode(String contMode) {
        this.contMode = contMode == null ? null : contMode.trim();
    }

    /**
     * 合作机构名称
     * @return COOPERATION_NAME 合作机构名称
     */
    public String getCooperationName() {
        return cooperationName;
    }

    /**
     * 合作机构名称
     * @param cooperationName 合作机构名称
     */
    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName == null ? null : cooperationName.trim();
    }

    /**
     * 投资本金到账日
     * @return INVEST_PRIN_DATE 投资本金到账日
     */
    public String getInvestPrinDate() {
        return investPrinDate;
    }

    /**
     * 投资本金到账日
     * @param investPrinDate 投资本金到账日
     */
    public void setInvestPrinDate(String investPrinDate) {
        this.investPrinDate = investPrinDate == null ? null : investPrinDate.trim();
    }

    /**
     * 投资收益到账日
     * @return INVEST_PROF_DATE 投资收益到账日
     */
    public String getInvestProfDate() {
        return investProfDate;
    }

    /**
     * 投资收益到账日
     * @param investProfDate 投资收益到账日
     */
    public void setInvestProfDate(String investProfDate) {
        this.investProfDate = investProfDate == null ? null : investProfDate.trim();
    }

    /**
     * 产品增信标识
     * @return CREDIT_FLAG 产品增信标识
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    /**
     * 产品增信标识
     * @param creditFlag 产品增信标识
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag == null ? null : creditFlag.trim();
    }

    /**
     * 产品增信机构类型
     * @return CREDIT_TYPE 产品增信机构类型
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 产品增信机构类型
     * @param creditType 产品增信机构类型
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 产品增信形式
     * @return CREDIT_MODE 产品增信形式
     */
    public String getCreditMode() {
        return creditMode;
    }

    /**
     * 产品增信形式
     * @param creditMode 产品增信形式
     */
    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode == null ? null : creditMode.trim();
    }

    /**
     * 投资资产种类及比例
     * @return INV_TYPE_RAT 投资资产种类及比例
     */
    public String getInvTypeRat() {
        return invTypeRat;
    }

    /**
     * 投资资产种类及比例
     * @param invTypeRat 投资资产种类及比例
     */
    public void setInvTypeRat(String invTypeRat) {
        this.invTypeRat = invTypeRat == null ? null : invTypeRat.trim();
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 数据状态
     * @return STATUS 数据状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态
     * @param status 数据状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * null
     * @return DEPT_CODE null
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * null
     * @param deptCode null
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * null
     * @return WORKFLOW_ID null
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * null
     * @param workflowId null
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * null
     * @return DATASOURCE null
     */
    public String getDatasource() {
        return datasource;
    }

    /**
     * null
     * @param datasource null
     */
    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public String getIsFhxgcp() {
        return isFhxgcp;
    }

    public void setIsFhxgcp(String isFhxgcp) {
        this.isFhxgcp = isFhxgcp;
    }
}