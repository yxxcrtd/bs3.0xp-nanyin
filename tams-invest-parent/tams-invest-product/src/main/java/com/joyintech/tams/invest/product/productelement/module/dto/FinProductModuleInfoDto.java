/*
 * 文件名：FinProductModuleInfoDto.java
 * 描述：理财资管管理系统V2.0
 * 修改人：yaozhenguo
 * 修改时间：2020年1月20日
 * 修改内容：新建
 */

package com.joyintech.tams.invest.product.productelement.module.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.EncryptUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductLayeredInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticInfoDto;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品新建管理信息dto
 * 
 * @author yaozhenguo
 * @version 1.0 2020年1月20日
 * @see FinProductModuleInfoDto
 * @since 1.0
 */
public class FinProductModuleInfoDto extends FinProduct implements ProcessEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -8543840737635969481L;

	/**
	 * 操作类型
	 */
	private String operateType;
	/**
	 * 费用新建缓存key值
	 */
	private String feeNewAddId;

	/**
	 * 确认标识
	 */
	private String confirmFlag;

	/**
	 * 元素Id
	 */
	private String elementId;

	/**
	 * 分支序号
	 */
	private Long branch;

	/**
	 * 画面Id
	 */
	private String pageId;

	/**
	 * 模板代码
	 */
	private String templateId;

	/**
	 * 模板名称
	 */
	private String templateName;
	/**
	 * 模板内容
	 */
	private String templateContent;

	/**
	 * 组合代码
	 */
	private String ptlId;

	/**
	 * 处理模式
	 */
	private String dealMode;

	/**
	 * 利率类型
	 */
	private String calcType;

	/**
	 * 是否周期型
	 */
	private String isCycle;

	/**
	 * 是否分层
	 */
	private String isLay;

	/**
	 * 分层类型
	 */
	private String layType;

	/**
	 * 产品收益标识
	 */
	private String profitFlag;

	/**
	 * 预期收益率
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal yield;

	/**
	 * 募集开始日
	 */
	private Date firstSaleVdate;

	/**
	 * 募集结束日
	 */
	private Date firstSaleMdate;

	/**
	 * 封闭期限
	 */
	private Integer closeDays;

	/**
	 * 计息基础
	 */
	private String basis;

	/**
	 * 销售部门
	 */
	private String saleDepartment;

	/**
	 * 申赎确认天数
	 */
	private Integer purSpeed;

	/**
	 * 赎回确认天数
	 */
	private Integer redSpeed;

	/**
	 * 投资性质
	 */
	private String investNature;

	/**
	 * 销售模式
	 */
	private String saleMode;

	/**
	 * 有效状态
	 */
	private String effectStatus;

	private String period;
	private Integer investDays;

	/**
	 * 发行状态
	 */
	private String issueStatus;

	/********* add by zhangweiqi 主产品需求添加 begin **************/
	/**
	 * 产品系列
	 */
	private String prodSeries;
	
	/**
	 * 投资性质
	 */
	private String investType;

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getInvestDays() {
		return investDays;
	}

	public void setInvestDays(Integer investDays) {
		this.investDays = investDays;
	}

	/**
	 * 产品分类
	 */
	private String prodClass;
	/**
	 * 产品特点
	 */

	private String prodFeature;

	/**
	 * 托管机构
	 * 
	 */
	private String trusteeId;

	/**
	 * 专属产品
	 * 
	 */
	private String exclusiveProd;

	/**
	 * 理财经理
	 */
	private String prodManager;

	/**
	 * 销售类型
	 */
	private String saleType;

	/**
	 * 投资周期
	 */
	private String investmentCycle;

	/**
	 * 是否续投 continue_Flag
	 */
	private String continueFlag;

	/**
	 * 同业机构
	 */
	private String sameOrg;

	/**
	 * 是否支持代销
	 */
	private String consignmentFlag;

	/**
	 * 是否允许提前终止 BEFOREEND_FLAG
	 */
	private String beforeEndFlag;

	/**
	 * 是否允许客户赎回
	 */
	private String redFlag;
	/**
	 * 是否可违约赎回 DEFAULTRED_FLAG
	 */
	private String defaultRedFlag;
	/**
	 * 是否可提前成立 beforeestablish_FLAG
	 */
	private String beforeEstablishFlag;
	/**
	 * 是否收益权转让产品 add dgf 20191230
	 */
	// private String repFiduciaryDuty;

	/**
	 * 实际到期日
	 */
	private Date actMdate;

	/**
	 * 分红方式
	 */

	private String payType;

	/**
	 * 是否永续
	 */
	private String isSus;

	/**
	 * 产品登记编码
	 */
	private String prodRegistCode;

	/**
	 * 目标客户一级分类
	 */
	private String targetCustomer1;
	/**
	 * 目标客户二级分类
	 */
	private String targetCustomer2;

	/**
	 * 数据来源
	 */
	private String dataSource;

	private String termType;

	// add by yejiejie start 分层信息
	private List<BussFinProductLayeredInfoDto> bussFinProductLayeredInfoDto;

	/**
	 * 是否现金管理类
	 */
	private String isCashManage;

	public String getIsCashManage() {
		return isCashManage;
	}

	public void setIsCashManage(String isCashManage) {
		this.isCashManage = isCashManage;
	}

	// add by niuqianyu 2019-07-18 start
	/**
	 * 附件临时Id
	 */
	private String fjId;
	/**
	 * 文件上传输入参数
	 */
	private MstFileInputParameter mstFileInputParameter;
	/**
	 * 附件信息DTO
	 */
	private List<FileCommonDto> fileCommonList;
	/**
	 * 行内标识码
	 */
	private String repFinprodInsId;

	/**
	 * 产品设计系列编号 yzg 20191107
	 */
	private String prdDesignId;
	/**
	 * 产品设计系列名称 yzg 20191107
	 */
	private String prdDesignName;

	public String getFjId() {
		return fjId;
	}

	public void setFjId(String fjId) {
		this.fjId = fjId;
	}

	public MstFileInputParameter getMstFileInputParameter() {
		return mstFileInputParameter;
	}

	public void setMstFileInputParameter(MstFileInputParameter mstFileInputParameter) {
		this.mstFileInputParameter = mstFileInputParameter;
	}

	public List<FileCommonDto> getFileCommonList() {
		return fileCommonList;
	}

	public void setFileCommonList(List<FileCommonDto> fileCommonList) {
		this.fileCommonList = fileCommonList;
	}

	// add by niuqianyu 2019-07-18 end
	/**
	 * 获取 pageId
	 * 
	 * @return pageId.
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * 设置 pageId
	 * 
	 * @param pageId
	 *            pageId
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getPtlId() {
		return ptlId;
	}

	public void setPtlId(String ptlId) {
		this.ptlId = ptlId;
	}

	public String getDealMode() {
		return dealMode;
	}

	public String getDealModeStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.处理模式, this.getDealMode());
	}
	
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperationType() {
		return super.getOperationType();
	}
	
	public String getOperationTypeStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.开放类型, this.getOperationType());
	}

	public String getCalcType() {
		return calcType;
	}

	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}

	public String getIsCycle() {
		return isCycle;
	}

	public void setIsCycle(String isCycle) {
		this.isCycle = isCycle;
	}

	@NotEmpty(fieldName = "FinProductBuild002.isLay")
	public String getIsLay() {
		return isLay;
	}

	public void setIsLay(String isLay) {
		this.isLay = isLay;
	}

	public String getLayType() {
		return layType;
	}

	public void setLayType(String layType) {
		this.layType = layType;
	}

	public String getProfitFlag() {
		return profitFlag;
	}
	
	public String getProfitFlagStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.产品收益标识, this.getProfitFlag());
	}

	public void setProfitFlag(String profitFlag) {
		this.profitFlag = profitFlag;
	}

	@Rate(fieldName = "FinProductBuild002.yield", maxDecBit = 4)
	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public Date getFirstSaleVdate() {
		return firstSaleVdate;
	}

	public void setFirstSaleVdate(Date firstSaleVdate) {
		this.firstSaleVdate = firstSaleVdate;
	}

	public Date getFirstSaleMdate() {
		return firstSaleMdate;
	}

	public void setFirstSaleMdate(Date firstSaleMdate) {
		this.firstSaleMdate = firstSaleMdate;
	}

	public Integer getCloseDays() {
		return closeDays;
	}

	public void setCloseDays(Integer closeDays) {
		this.closeDays = closeDays;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	// @NotEmpty(fieldName = "FinProductBuild002.saleDepartment")
	public String getSaleDepartment() {
		return saleDepartment;
	}

	public void setSaleDepartment(String saleDepartment) {
		this.saleDepartment = saleDepartment;
	}

	public Integer getPurSpeed() {
		return purSpeed;
	}

	public void setPurSpeed(Integer purSpeed) {
		this.purSpeed = purSpeed;
	}

	public String getInvestNature() {
		return investNature;
	}

	public void setInvestNature(String investNature) {
		this.investNature = investNature;
	}

	// @NotEmpty(fieldName = "FinProductBuild002.saleMode")
	public String getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(String saleMode) {
		this.saleMode = saleMode;
	}

	@Override
	public String getFinprodId() {
		return super.getFinprodId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Length(fieldName = "FinProductBuild002.finprodMarketId", max = 20)
	public String getFinprodMarketId() {
		return super.getFinprodMarketId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Length(fieldName = "FinProductBuild002.finprodName", max = 200)
	public String getFinprodName() {
		return super.getFinprodName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Length(fieldName = "FinProductBuild002.finprodAbbr", max = 200)
	public String getFinprodAbbr() {
		return super.getFinprodAbbr();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProfitType() {
		return super.getProfitType();
	}
	
	public String getProfitTypeStr() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.收益类型, this.getProfitType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// @NotEmpty(fieldName = "FinProductBuild002.entrustType")
	public String getEntrustType() {
		return super.getEntrustType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Length(fieldName = "FinProductBuild002.entruster", max = 2000)
	public String getEntruster() {
		return super.getEntruster();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getVdate() {
		return super.getVdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getMdate() {
		return super.getMdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// @NotEmpty(fieldName = "FinProductBuild002.termDays")
	public Long getTermDays() {
		return super.getTermDays();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCalendarId() {
		return super.getCalendarId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCcy() {
		return super.getCcy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIssueType() {
		return super.getIssueType();
	}

	/**
	 * 获取 elementId
	 * 
	 * @return elementId.
	 */
	public String getElementId() {
		return elementId;
	}

	/**
	 * 设置 elementId
	 * 
	 * @param elementId
	 *            elementId
	 */
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

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
	 * @param operateType
	 *            operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 获取 branch
	 * 
	 * @return branch.
	 */
	public Long getBranch() {
		return branch;
	}

	/**
	 * 设置 branch
	 * 
	 * @param branch
	 *            branch
	 */
	public void setBranch(Long branch) {
		this.branch = branch;
	}

	private transient List<FinProductModuleInfoDto> selectedItem;

	/**
	 * 获取 selectedItem
	 * 
	 * @return selectedItem.
	 */
	public List<FinProductModuleInfoDto> getSelectedItem() {
		return selectedItem;
	}

	/**
	 * 设置 selectedItem
	 * 
	 * @param selectedItem
	 *            selectedItem
	 */
	public void setSelectedItem(List<FinProductModuleInfoDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * 获取 confirmFlag
	 * 
	 * @return confirmFlag.
	 */
	public String getConfirmFlag() {
		return confirmFlag;
	}

	/**
	 * 设置 confirmFlag
	 * 
	 * @param confirmFlag
	 *            confirmFlag
	 */
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	/**
	 * 获取 effectStatus
	 * 
	 * @return effectStatus.
	 */
	public String getEffectStatus() {
		return effectStatus;
	}

	/**
	 * 设置 effectStatus
	 * 
	 * @param effectStatus
	 *            effectStatus
	 */
	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}

	/**
	 * 获取 issueStatus
	 * 
	 * @return issueStatus.
	 */
	public String getIssueStatus() {
		return issueStatus;
	}

	/**
	 * 设置 issueStatus
	 * 
	 * @param issueStatus
	 *            issueStatus
	 */
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	/**
	 * 获取 feeNewAddId
	 * 
	 * @return feeNewAddId.
	 */
	public String getFeeNewAddId() {
		return feeNewAddId;
	}

	/**
	 * 设置 feeNewAddId
	 * 
	 * @param feeNewAddId
	 *            feeNewAddId
	 */
	public void setFeeNewAddId(String feeNewAddId) {
		this.feeNewAddId = feeNewAddId;
	}

	public String getProdSeries() {
		return prodSeries;
	}

	public void setProdSeries(String prodSeries) {
		this.prodSeries = prodSeries;
	}

	public String getProdClass() {
		return prodClass;
	}

	public void setProdClass(String prodClass) {
		this.prodClass = prodClass;
	}

	public String getProdFeature() {
		return prodFeature;
	}

	public void setProdFeature(String prodFeature) {
		this.prodFeature = prodFeature;
	}

	@NotEmpty(fieldName = "FinProductBuild002.truOrg")
	public String getTrusteeId() {
		return trusteeId;
	}

	public void setTrusteeId(String trusteeId) {
		this.trusteeId = trusteeId;
	}

	public String getExclusiveProd() {
		return exclusiveProd;
	}

	public void setExclusiveProd(String exclusiveProd) {
		this.exclusiveProd = exclusiveProd;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	@NotEmpty(fieldName = "FinProductBuild002.consignmentFlag")
	public String getConsignmentFlag() {
		return consignmentFlag;
	}

	public void setConsignmentFlag(String consignmentFlag) {
		this.consignmentFlag = consignmentFlag;
	}

	@NotEmpty(fieldName = "FinProductBuild002.beforeEndFlag")
	public String getBeforeEndFlag() {
		return beforeEndFlag;
	}

	public void setBeforeEndFlag(String beforeEndFlag) {
		this.beforeEndFlag = beforeEndFlag;
	}

	public String getRedFlag() {
		return redFlag;
	}

	public void setRedFlag(String redFlag) {
		this.redFlag = redFlag;
	}

	public String getDefaultRedFlag() {
		return defaultRedFlag;
	}

	public void setDefaultRedFlag(String defaultRedFlag) {
		this.defaultRedFlag = defaultRedFlag;
	}

	public String getBeforeEstablishFlag() {
		return beforeEstablishFlag;
	}
	// @NotEmpty(fieldName = "FinProductBuild002.repFiduciaryDuty")
	// public String getRepFiduciaryDuty() {
	// return repFiduciaryDuty;
	// }
	// public void setRepFiduciaryDuty(String repFiduciaryDuty) {
	// this.repFiduciaryDuty = repFiduciaryDuty;
	// }

	public void setBeforeEstablishFlag(String beforeEstablishFlag) {
		this.beforeEstablishFlag = beforeEstablishFlag;
	}

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}

	public String getContinueFlag() {
		return continueFlag;
	}

	public void setContinueFlag(String continueFlag) {
		this.continueFlag = continueFlag;
	}

	public String getProdManager() {
		return prodManager;
	}

	public void setProdManager(String prodManager) {
		this.prodManager = prodManager;
	}

	public String getSameOrg() {
		return sameOrg;
	}

	public void setSameOrg(String sameOrg) {
		this.sameOrg = sameOrg;
	}

	public Date getActMdate() {
		return actMdate;
	}

	public void setActMdate(Date actMdate) {
		this.actMdate = actMdate;
	}

	// add by zhangweiqi 2019-3-14 净值型改成非必填
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIsSus() {
		return isSus;
	}

	public void setIsSus(String isSus) {
		this.isSus = isSus;
	}

	public String getProdRegistCode() {
		return prodRegistCode;
	}

	public void setProdRegistCode(String prodRegistCode) {
		this.prodRegistCode = prodRegistCode;
	}

	public Integer getRedSpeed() {
		return redSpeed;
	}

	public void setRedSpeed(Integer redSpeed) {
		this.redSpeed = redSpeed;
	}

	// @NotEmpty(fieldName = "FinProductBuild002.targetCustomer1")
	public String getTargetCustomer1() {
		return targetCustomer1;
	}

	public void setTargetCustomer1(String targetCustomer1) {
		this.targetCustomer1 = targetCustomer1;
	}

	// add by gcl 2019/3/3
	// @NotEmpty(fieldName = "FinProductBuild002.targetCustomer2")
	public String getTargetCustomer2() {
		return targetCustomer2;
	}

	public void setTargetCustomer2(String targetCustomer2) {
		this.targetCustomer2 = targetCustomer2;
	}

	// add by yejiejie 2019-3-8
	// 产品期次
	// @NotEmpty(fieldName = "FinProductBuild002.repFinprodStage")
	private Integer repFinprodStage;

	public Integer getRepFinprodStage() {
		return repFinprodStage;
	}

	public void setRepFinprodStage(Integer repFinprodStage) {
		this.repFinprodStage = repFinprodStage;
	}
	// 产品期次

	// 产品风险等级
	private String riskLevel;

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	// add by tuqirong 2019-03-27 end

	/**
	 * 获取 bussFinProductLayeredInfoDto
	 * 
	 * @return bussFinProductLayeredInfoDto.
	 */
	public List<BussFinProductLayeredInfoDto> getBussFinProductLayeredInfoDto() {
		return bussFinProductLayeredInfoDto;
	}

	/**
	 * 设置 bussFinProductLayeredInfoDto
	 * 
	 * @param bussFinProductLayeredInfoDto
	 *            bussFinProductLayeredInfoDto
	 */
	public void setBussFinProductLayeredInfoDto(List<BussFinProductLayeredInfoDto> bussFinProductLayeredInfoDto) {
		this.bussFinProductLayeredInfoDto = bussFinProductLayeredInfoDto;
	}

	/** add by 发送接口状态 lizhi 2019-6-19 start **/
	// 发送状态
	private String sendStatus;
	// 发送状态备注
	private String sendRemark;

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSendRemark() {
		return sendRemark;
	}

	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}

	/** add by 发送接口状态 lizhi 2019-6-19 end **/

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getRepFinprodInsId() {
		return repFinprodInsId;
	}

	public void setRepFinprodInsId(String repFinprodInsId) {
		this.repFinprodInsId = repFinprodInsId;
	}

	private List<FinProductAnapleroticInfoDto> finProductAnapleroticTabInfoDtoList;
	/**
	 * 是否回补
	 */
	private String isAnaplerotic;

	public List<FinProductAnapleroticInfoDto> getFinProductAnapleroticTabInfoDtoList() {
		return finProductAnapleroticTabInfoDtoList;
	}

	public void setFinProductAnapleroticTabInfoDtoList(
			List<FinProductAnapleroticInfoDto> finProductAnapleroticTabInfoDtoList) {
		this.finProductAnapleroticTabInfoDtoList = finProductAnapleroticTabInfoDtoList;
	}

	public String getIsAnaplerotic() {
		return isAnaplerotic;
	}

	public void setIsAnaplerotic(String isAnaplerotic) {
		this.isAnaplerotic = isAnaplerotic;
	}

	public String getPrdDesignId() {
		return prdDesignId;
	}

	public void setPrdDesignId(String prdDesignId) {
		this.prdDesignId = prdDesignId;
	}

	public String getPrdDesignName() {
		return prdDesignName;
	}

	public void setPrdDesignName(String prdDesignName) {
		this.prdDesignName = prdDesignName;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}

	
}
