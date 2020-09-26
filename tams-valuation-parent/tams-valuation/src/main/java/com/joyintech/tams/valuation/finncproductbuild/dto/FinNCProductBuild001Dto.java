/*
 * 文件名：FinNCProductBuild001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年2月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.finncproductbuild.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.finncproductbuild.validator.FinNCProductBuild001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品资产查询Dto
 * 
 * @author tanxuan
 * @version 1.0 2020年2月12日
 * @see FinNCProductBuild001Dto
 * @since 1.0
 */
@PageId("FinNCProductBuild002")
@ValidLogic(targetClass = FinNCProductBuild001Validator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class FinNCProductBuild001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 金融产品代码
     */
    @PrimaryKey(value = Field.PK_1)
    private String finprodId;

    /**
     * 金融产品类型，债券、理财产品、基金
     */
    @PrimaryKey(value = Field.PK_2)
    private String finprodType;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 收益标识
     */
    private String profitFlag;

    /**
     * 募集金额
     */
    private BigDecimal planraiseAmt;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 产品期限
     */
    private String term;

    /**
     * 收益类型，净值、预期收益、货币
     */
    private String profitType;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 发行方式
     */
    private String issueType;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 到期日
     */
    private String mdate;

    /**
     * 实际到期日
     */
    private String actmdate;

    /**
     * 是否永续
     */
    private String isSus;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 挂钩母产品 名称
     */
    private String pFinprodName;
    
    /**
     * 份额小数位
     */
    private String shareDecDigits;
    
    /**
     * 份额尾数处理
     */
    private String shareDecType;

    /**
     * 万份收益小数位
     */
    private String pyeildDecDigits;

    /**
     * 收益率小数位
     */
    private String yeildDecDigits;

    /**
     * 估值小数位位数
     */
    private String valDecDigits;
    
    /**
     * 是否对账
     */
    private String isReconciliation;
    
    /**
     * 创建机构
     */
    private String createOrg;
    
    /**
     * 创建机构名称
     */
    private String createOrgName;
    
    /**
     * 清盘日
     */
    private String liquidationDate;
    
    /**
     * 发行方式
     */
    private String operationType;
    
    /**
     * 单位净值小数位
     */
    private String unitvalueDec;
    
    /**
     * 单位净值尾数处理方式 
     */
    private String unitvalueMode;
    
    /**
     * 万份收益小数位
     */
    private String milionProfitDec;
    
    /**
     * 万份收益尾数处理方式
     */
    private String milionProfitDecMode;
    
    /**
     * 年化收益率小数位
     */
    private String yearYieldDec;
    
    /**
     * 年化收益率尾数处理方式 
     */
    private String yearYieldDecMode;
    
    /**
     * 生效利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;
  
    /**
     * 登记托管机构
     */
    private String registOrg;
    
    /**
     * 是否计提活期利息
     */
    private String isCountingCurInt;
    
    /**
     * 托管人
     */
    private String trusteeId;
    
    /**
     * 估值机构
     */
    private String valOrg;
    
    /**
     * 估值机构名称
     */
    private String valOrgName;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 交易日历名称
     */
    private String calendarName;
    
    /**
     * 产品登记编码
     */
    private String prodRegistCode;
    
    /**
     * 是否周期型
     */
    private String isCycle;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 现金流二级类型
     */
    private String cashType2;

    /**
     * 生效日期
     */
    private String effDate;

    /**
     * 基数类型
     */
    private String baseType;

    /**
     * 基数日期类型，上一工作日、上一自然日、当日、上一开放日等
     */
    private String baseDateType;

    /**
     * 首次支付日
     */
    private String firstPayDate;

    /**
     * 生效规则
     */
    private String actRule;
    
    /**
     * 首次计提日
     */
    private String firCalculaDate;
    
    /**
     * 计提结束日
     */
    private String endCalculaDate;
    
    /**
     * 资产净值日
     */
    private String actVdateType;
    
    /**
     * 计提频率
     */
    private String accrCycle;
    
    /**
     * 是否计提浮动管理费
     */
    private String isAccrFloManageFee;
    
    /**
     * 处理操作
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;
    
    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
		return operateType;
	}

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
    /**
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
		return pageModel;
	}

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

    /**
     * 获取 isReconciliation
     * 
     * @return isReconciliation.
     */
    public String getIsReconciliation() {
		return isReconciliation;
	}

    /**
     * 设置 isReconciliation
     * 
     * @param isReconciliation isReconciliation
     */
	public void setIsReconciliation(String isReconciliation) {
		this.isReconciliation = isReconciliation;
	}
    
    /**
     * 获取 term
     * 
     * @return term.
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 term
     * 
     * @param term term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 获取 planraiseAmt
     * 
     * @return planraiseAmt.
     */
    public BigDecimal getPlanraiseAmt() {
        if (planraiseAmt != null) {
            return planraiseAmt;
        }
        else {
            return BigDecimal.ZERO;
        }

    }

    /**
     * 设置 planraiseAmt
     * 
     * @param planraiseAmt planraiseAmt
     */
    public void setPlanraiseAmt(BigDecimal planraiseAmt) {
        this.planraiseAmt = planraiseAmt;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 是否周期型
     * 
     * @return 是否周期型.
     */
    public String getIsCycle() {
        return isCycle;
    }

    /**
     * 设置 是否周期型
     * 
     * @param isCycle 是否周期型
     */
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle;
    }

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 finprodAbbr
     * 
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 profitType
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 tradeMarket
     * 
     * @return tradeMarket.
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     * 
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 issueType
     * 
     * @return issueType.
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 issueType
     * 
     * @param issueType issueType
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    /**
     * 获取 isSus
     * 
     * @return isSus.
     */
    public String getIsSus() {
        return isSus;
    }

    /**
     * 设置 isSus
     * 
     * @param isSus isSus
     */
    public void setIsSus(String isSus) {
        this.isSus = isSus;
    }

    /**
     * 获取 pFinprodId
     * 
     * @return pFinprodId.
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    /**
     * 设置 pFinprodId
     * 
     * @param pFinprodId pFinprodId
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 actmdate
     * 
     * @return actmdate.
     */
    public String getActmdate() {
        return actmdate;
    }

    /**
     * 设置 actmdate
     * 
     * @param actmdate actmdate
     */
    public void setActmdate(String actmdate) {
        this.actmdate = actmdate;
    }

    /**
     * 获取 shareDecDigits
     * 
     * @return shareDecDigits.
     */
    public String getShareDecDigits() {
        return shareDecDigits;
    }

    /**
     * 设置 shareDecDigits
     * 
     * @param shareDecDigits shareDecDigits
     */
    public void setShareDecDigits(String shareDecDigits) {
        this.shareDecDigits = shareDecDigits;
    }
    
    /**
     * 获取 shareDecType
     * 
     * @return shareDecType.
     */
    public String getShareDecType() {
        return shareDecType;
    }

    /**
     * 设置 shareDecType
     * 
     * @param shareDecType shareDecType
     */
    public void setShareDecType(String shareDecType) {
        this.shareDecType = shareDecType;
    }

    /**
     * 获取 pyeildDecDigits
     * 
     * @return pyeildDecDigits.
     */
    public String getPyeildDecDigits() {
        return pyeildDecDigits;
    }

    /**
     * 设置 pyeildDecDigits
     * 
     * @param pyeildDecDigits pyeildDecDigits
     */
    public void setPyeildDecDigits(String pyeildDecDigits) {
        this.pyeildDecDigits = pyeildDecDigits;
    }

    /**
     * 获取 yeildDecDigits
     * 
     * @return yeildDecDigits.
     */
    public String getYeildDecDigits() {
        return yeildDecDigits;
    }

    /**
     * 设置 yeildDecDigits
     * 
     * @param yeildDecDigits yeildDecDigits
     */
    public void setYeildDecDigits(String yeildDecDigits) {
        this.yeildDecDigits = yeildDecDigits;
    }

    /**
     * 获取 valDecDigits
     * 
     * @return valDecDigits.
     */
    public String getValDecDigits() {
        return valDecDigits;
    }

    /**
     * 设置 valDecDigits
     * 
     * @param valDecDigits valDecDigits
     */
    public void setValDecDigits(String valDecDigits) {
        this.valDecDigits = valDecDigits;
    }

    /**
     * 获取 calendarId
     * 
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * 
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 calendarName
     * 
     * @return calendarName.
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 calendarName
     * 
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    /**
     * 取得 生效利率
     * @return 生效利率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 生效利率
     * @param yield 生效利率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }
    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }
    
    /**
     * 取得 托管人
     * @return 托管人
     */
    public String getTrusteeId() {
        return trusteeId;
    }

    /**
     * 设置 托管人
     * @param trusteeId 托管人
     */
    public void setTrusteeId(String trusteeId) {
        this.trusteeId = trusteeId == null ? null : trusteeId.trim();
    }
    
    /**
     * 取得 创建机构
     * @return 创建机构
     */
    public String getCreateOrg() {
        return createOrg;
    }

    /**
     * 设置 创建机构
     * @param createOrg 创建机构
     */
    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg == null ? null : createOrg.trim();
    }
    
    /**
     * 取得 创建机构名称
     * @return 创建机构名称
     */
    public String getCreateOrgName() {
        return createOrgName;
    }

    /**
     * 设置 创建机构名称
     * @param createOrgName 创建机构名称
     */
    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName == null ? null : createOrgName.trim();
    }
    
    /**
     * 取得 估值机构
     * @return 估值机构
     */
    public String getValOrg() {
        return valOrg;
    }

    /**
     * 设置 估值机构
     * @param valOrg 估值机构
     */
    public void setValOrg(String valOrg) {
        this.valOrg = valOrg == null ? null : valOrg.trim();
    }
    
    /**
     * 取得 估值机构名称
     * @return 估值机构名称
     */
    public String getValOrgName() {
        return valOrgName;
    }

    /**
     * 设置 估值机构名称
     * @param valOrgName 估值机构名称
     */
    public void setValOrgName(String valOrgName) {
        this.valOrgName = valOrgName == null ? null : valOrgName.trim();
    }
    
    public String getpFinprodName() {
        return pFinprodName;
    }

    
    public void setpFinprodName(String pFinprodName) {
        this.pFinprodName = pFinprodName;
    }

    /**
     * 取得 收益标识
     * @return 收益标识
     */
	public String getProfitFlag() {
		return profitFlag;
	}

    /**
     * 设置 收益标识
     * @param profitFlag 收益标识
     */
	public void setProfitFlag(String profitFlag) {
		this.profitFlag = profitFlag;
	}
	
    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistCode 产品登记编码
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
    }
    
    /**
     * 取得 清盘日
     * @return 清盘日
     */
    public String getLiquidationDate() {
        return liquidationDate;
    }

    /**
     * 设置 清盘日
     * @param liquidationDate 清盘日
     */
    public void setLiquidationDate(String liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置 发行方式
     * @param operationType 发行方式
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    
    /**
     * 取得 单位净值小数位
     * @return 单位净值小数位
     */
    public String getUnitvalueDec() {
        return unitvalueDec;
    }

    /**
     * 设置 单位净值小数位
     * @param unitvalueDec 单位净值小数位
     */
    public void setUnitvalueDec(String unitvalueDec) {
        this.unitvalueDec = unitvalueDec;
    }
    
    /**
     * 取得 单位净值尾数处理方式
     * @return 单位净值尾数处理方式
     */
    public String getUnitvalueMode() {
        return unitvalueMode;
    }

    /**
     * 设置 单位净值尾数处理方式
     * @param unitvalueMode 单位净值尾数处理方式
     */
    public void setUnitvalueMode(String unitvalueMode) {
        this.unitvalueMode = unitvalueMode;
    }
    
    /**
     * 取得 万份收益小数位
     * @return 万份收益小数位
     */
    public String getMilionProfitDec() {
        return milionProfitDec;
    }

    /**
     * 设置 万份收益小数位
     * @param milionProfitDec 万份收益小数位
     */
    public void setMilionProfitDec(String milionProfitDec) {
        this.milionProfitDec = milionProfitDec;
    }
    
    /**
     * 取得 万份收益尾数处理方式
     * @return 万份收益尾数处理方式
     */
    public String getMilionProfitDecMode() {
        return milionProfitDecMode;
    }

    /**
     * 设置 万份收益尾数处理方式
     * @param milionProfitDecMode 万份收益尾数处理方式
     */
    public void setMilionProfitDecMode(String milionProfitDecMode) {
        this.milionProfitDecMode = milionProfitDecMode;
    }
    
    /**
     * 取得 年化收益率小数位
     * @return 年化收益率小数位
     */
    public String getYearYieldDec() {
        return yearYieldDec;
    }

    /**
     * 设置 年化收益率小数位
     * @param yearYieldDec 年化收益率小数位
     */
    public void setYearYieldDec(String yearYieldDec) {
        this.yearYieldDec = yearYieldDec;
    }
    
    /**
     * 取得 年化收益率尾数处理方式
     * @return 年化收益率尾数处理方式
     */
    public String getYearYieldDecMode() {
        return yearYieldDecMode;
    }

    /**
     * 设置 年化收益率尾数处理方式
     * @param yearYieldDecMode 年化收益率尾数处理方式
     */
    public void setYearYieldDecMode(String yearYieldDecMode) {
        this.yearYieldDecMode = yearYieldDecMode;
    }
    
    /**
     * 取得 是否计提活期利息
     * @return 是否计提活期利息
     */
    public String getIsCountingCurInt() {
        return isCountingCurInt;
    }

    /**
     * 设置 是否计提活期利息
     * @param isCountingCurInt 是否计提活期利息
     */
    public void setIsCountingCurInt(String isCountingCurInt) {
        this.isCountingCurInt = isCountingCurInt;
    }
    
    /**
     * 选中的数据
     */
    private transient List<FinNCProductBuild001Dto> listFinNCProductBuildDto;

    public List<FinNCProductBuild001Dto> getListFinNCProductBuildDto() {
        return listFinNCProductBuildDto;
    }

    public void setListFinNCProductBuildDto(List<FinNCProductBuild001Dto> listFinNCProductBuildDto) {
        this.listFinNCProductBuildDto = listFinNCProductBuildDto;
    }
    
    /**
     * 费用
     */
    private ValProductInfoFeeDto valProductInfoFeeDto;

    public ValProductInfoFeeDto getValProductInfoFeeDto() {
        return valProductInfoFeeDto;
    }

    public void setValProductInfoFeeDto(ValProductInfoFeeDto valProductInfoFeeDto) {
        this.valProductInfoFeeDto = valProductInfoFeeDto;
    }
    
    /**
     * 资金账户信息
     */
    private transient List<ValProductInfoAcctDto> listValProductInfoAcctDto_ZJ;

    public List<ValProductInfoAcctDto> getListValProductInfoAcctDto_ZJ() {
        return listValProductInfoAcctDto_ZJ;
    }

    public void setListValProductInfoAcctDto_ZJ(List<ValProductInfoAcctDto> listValProductInfoAcctDto_ZJ) {
        this.listValProductInfoAcctDto_ZJ = listValProductInfoAcctDto_ZJ;
    }
    
    /**
     * 证券账户信息
     */
    private transient List<ValProductInfoAcctDto> listValProductInfoAcctDto_ZQ;

    public List<ValProductInfoAcctDto> getListValProductInfoAcctDto_ZQ() {
        return listValProductInfoAcctDto_ZQ;
    }

    public void setListValProductInfoAcctDto_ZQ(List<ValProductInfoAcctDto> listValProductInfoAcctDto_ZQ) {
        this.listValProductInfoAcctDto_ZQ = listValProductInfoAcctDto_ZQ;
    }
    
    /**
     * 固定费用信息
     */
    private transient List<ValProductInfoFeeDto> listValProductInfoFeeDto_GD;

    public List<ValProductInfoFeeDto> getListValProductInfoFeeDto_GD() {
        return listValProductInfoFeeDto_GD;
    }

    public void setListValProductInfoFeeDto_GD(List<ValProductInfoFeeDto> listValProductInfoFeeDto_GD) {
        this.listValProductInfoFeeDto_GD = listValProductInfoFeeDto_GD;
    }
    
    /**
     * 浮动费用信息1
     */
    private transient List<ValProductInfoFeeDto> listValProductInfoFeeDto_FD1;

    public List<ValProductInfoFeeDto> getListValProductInfoFeeDto_FD1() {
        return listValProductInfoFeeDto_FD1;
    }

    public void setListValProductInfoFeeDto_FD1(List<ValProductInfoFeeDto> listValProductInfoFeeDto_FD1) {
        this.listValProductInfoFeeDto_FD1 = listValProductInfoFeeDto_FD1;
    }
    
    /**
     * 浮动费用信息2
     */
    private transient List<ValProductInfoFeeDto> listValProductInfoFeeDto_FD2;

    public List<ValProductInfoFeeDto> getListValProductInfoFeeDto_FD2() {
        return listValProductInfoFeeDto_FD2;
    }

    public void setListValProductInfoFeeDto_FD2(List<ValProductInfoFeeDto> listValProductInfoFeeDto_FD2) {
        this.listValProductInfoFeeDto_FD2 = listValProductInfoFeeDto_FD2;
    }

    /**
     * 获取 现金流二级类型
     * 
     * @return 现金流二级类型.
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置 现金流二级类型
     * 
     * @param cashType2 现金流二级类型
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    /**
     * 获取 effDate
     * 
     * @return effDate.
     */
    public String getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     * 
     * @param effDate effDate
     */
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 基数类型
     * 
     * @return 基数类型.
     */
    public String getBaseType() {
        return baseType;
    }
    
    /**
     * 设置 基数类型
     * 
     * @param baseType 基数类型
     */
    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }
    
    /**
     * 获取 基数日期类型
     * 
     * @return 基数日期类型
     */
    public String getBaseDateType() {
        return baseDateType;
    }

    /**
     * 设置 基数日期类型
     * 
     * @param baseDateType 基数日期类型
     */
    public void setBaseDateType(String baseDateType) {
        this.baseDateType = baseDateType;
    }

    /**
     * 获取 cashId
     * 
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     * 
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 firstPayDate
     * 
     * @return firstPayDate.
     */
    public String getFirstPayDate() {
        return firstPayDate;
    }

    /**
     * 设置 firstPayDate
     * 
     * @param firstPayDate firstPayDate
     */
    public void setFirstPayDate(String firstPayDate) {
        this.firstPayDate = firstPayDate;
    }

    /**
     * 获取 actRule
     * 
     * @return actRule.
     */
    public String getActRule() {
        return actRule;
    }

    /**
     * 设置 actRule
     * 
     * @param actRule actRule
     */
    public void setActRule(String actRule) {
        this.actRule = actRule;
    }

	/**
	 * 获取 firCalculaDate
	 * @return firCalculaDate.
	 */
	public String getFirCalculaDate() {
		return firCalculaDate;
	}

	/**
	 * 设置 firCalculaDate
	 * @param firCalculaDate firCalculaDate
	 */
	public void setFirCalculaDate(String firCalculaDate) {
		this.firCalculaDate = firCalculaDate;
	}

	/**
	 * 获取 endCalculaDate
	 * @return endCalculaDate.
	 */
	public String getEndCalculaDate() {
		return endCalculaDate;
	}

	/**
	 * 设置 endCalculaDate
	 * @param endCalculaDate endCalculaDate
	 */
	public void setEndCalculaDate(String endCalculaDate) {
		this.endCalculaDate = endCalculaDate;
	}
	
	/**
	 * 获取 actVdateType
	 * @return actVdateType.
	 */
	public String getActVdateType() {
		return actVdateType;
	}

	/**
	 * 设置 actVdateType
	 * @param actVdateType actVdateType
	 */
	public void setActVdateType(String actVdateType) {
		this.actVdateType = actVdateType;
	}
	
	/**
	 * 获取 accrCycle
	 * @return accrCycle.
	 */
	public String getAccrCycle() {
		return accrCycle;
	}

	/**
	 * 设置 accrCycle
	 * @param accrCycle accrCycle
	 */
	public void setAccrCycle(String accrCycle) {
		this.accrCycle = accrCycle;
	}

	/**
	 * 获取 isAccrFloManageFee
	 * @return isAccrFloManageFee.
	 */
	public String getIsAccrFloManageFee() {
		return isAccrFloManageFee;
	}

	/**
	 * 设置 isAccrFloManageFee
	 * @param isAccrFloManageFee isAccrFloManageFee
	 */
	public void setIsAccrFloManageFee(String isAccrFloManageFee) {
		this.isAccrFloManageFee = isAccrFloManageFee;
	}

    public String getProfitTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType))?
    			SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.profitType):this.profitType;
    }

    /**
     * 获取 金融产品类型 数据字典
     * 
     * @return 金融产品类型 数据字典
     */
    public String getFinprodTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType))?
    			SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.finprodType):this.finprodType;
    }

    public String getOperationTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.开放类型, operationType))?
    			SysDataUtil.getNameFromDataDict(DictCode.开放类型, operationType):operationType;
    }
    
    public String getIssueTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发行方式, issueType))?
    			SysDataUtil.getNameFromDataDict(DictCode.发行方式, issueType):issueType;
    }

    public String getCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
    			SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
    }

    /**
     * 获取 收益标识数据字典
     * @param profitFlag 收益标识数据字典
     */
    public String getProfitFlagFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.收益标识, this.profitFlag))?
    			SysDataUtil.getNameFromDataDict(DictCode.收益标识, this.profitFlag):this.profitFlag;
    }
    
    /**
     * 获取 现金流二级类型数据字典
     * 
     * @return 现金流二级类型数据字典
     */
    public String getCashType2Fmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, this.cashType2))?
    			SysDataUtil.getNameFromDataDict(DictCode.现金流二级类型, this.cashType2):this.cashType2;
    }

    /**
     * 获取 baseType
     * 
     * @return baseType.
     */
    public String getBaseTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.基数类型, this.baseType))?
    			SysDataUtil.getNameFromDataDict(DictCode.基数类型, this.baseType):this.baseType;
    }

    /**
     * 获取 基数日期类型
     * 
     * @return 基数日期类型
     */
    public String getBaseDateTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.基数日期类型, this.baseDateType))?
    			SysDataUtil.getNameFromDataDict(DictCode.基数日期类型, this.baseDateType):this.baseDateType;
    }

}
