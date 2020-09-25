/*
 * 文件名：FinProductModuleInfo002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.module.dto;

import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductBonusPlanDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductGoalInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductLayeredInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductOpenPlanDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductRevalDocumentDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductSaleParamDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductValuationDto;
import com.joyintech.tams.component.business.report.prod.dto.BussRepFinProductInfoDto;
import com.joyintech.tams.invest.product.productelement.anaplerotic.dto.FinProductAnapleroticInfoDto;
import com.joyintech.tams.invest.product.productelement.module.validator.FinProductModuleInfo002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品模板信息
 *
 * @author 何阳阳
 * @version 1.0 2018年9月17日
 * @see FinProductModuleInfo002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = FinProductModuleInfo002Validator.class)
@PageId("FinProductModule002")
public class FinProductModuleInfo002Dto extends FinProduct implements ProcessEntity {

	/**
	 */
	private static final long serialVersionUID = 65660117111895647L;

	/**
	 * 画面处理模式
	 */
	private String pageModel;

	/**
	 * 费用新建缓存key值
	 */
	private String feeNewAddId;

	/**
	 * 操作类型 - 工作流参数
	 */
	private String operateType;

	/**
	 * 按钮ID - 工作流参数
	 */
	private String elementId;
	/**
	 * 画面Id - 工作流参数
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
	 * 基本信息
	 */
	private FinProductModuleInfoDto productModuleInfo;

	/**
	 * 开放计划
	 */
	private BussFinProductOpenPlanDto bussFinProductOpenPlanDto;

	/**
	 * 赎回计划
	 */
	private BussFinProductOpenPlanDto bussFinProductOpenPlanRedDto;

	/**
	 * 付息计划
	 */
	private BussFinProductPayPlanDto bussFinProductPayPlanDto;

	/**
	 * 分红计划
	 */
	private BussFinProductBonusPlanDto bussFinProductBonusPlanDto;

	/**
	 * 估值信息
	 */
	private BussFinProductValuationDto bussFinProductValuationDto;

	/**
	 * 产品费用
	 */
	private BussFinProductFeeInfoDto bussFinProductFeeInfoDto;

	/**
	 * 浮动管理费-业绩基准
	 */
	private BussBenchMarkInfoDto bussBenchMarkFloatInfoDto;

	/**
	 * 业绩基准
	 */
	private BussBenchMarkInfoDto bussBenchMarkInfoDto;

	/**
	 * 浮动管理费
	 */
	private BussFinProductFloatFeeDto bussFinProductFloatFeeDto;

	/**
	 * 销售参数
	 */
	private BussFinProductSaleParamDto bussFinProductSaleParamDto;

	/**
	 * 报送信息
	 */
	private BussRepFinProductInfoDto bussRepFinProductInfoDto;

	/**
	 * 分层款型业绩基准
	 */
	private BussBenchMarkLayeredInfoDto bussBenchMarkLayeredInfoDto;

	/**
	 * 分层信息
	 */
	private List<BussFinProductLayeredInfoDto> bussFinProductLayeredInfoDto;
	
	/**
	 * 回补信息
	 */
	private List<FinProductAnapleroticInfoDto> finProductAnapleroticTabInfoDtoList;

	/**
	 * 选择数据
	 */
	private List<FinProductModuleInfo002Dto> selectedItem;

	@Override
	public String getFinprodId() {
		return StringUtil.isNotEmpty(super.getFinprodId()) ? super.getFinprodId()
				: StringUtil.isNotEmpty(productModuleInfo) ? productModuleInfo.getFinprodId() : null;
	}

	@Override
	public String getFinprodName() {
		return super.getFinprodName();
	}

	@Override
	public String getFinprodType2() {
		return super.getFinprodType2();
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
	 * @param pageModel
	 *            pageModel
	 */
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
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
	 * 获取 productModuleInfo
	 * 
	 * @return productModuleInfo.
	 */
	public FinProductModuleInfoDto getProductModuleInfo() {
		return productModuleInfo;
	}

	/**
	 * 设置 productModuleInfo
	 * 
	 * @param productModuleInfo
	 *            productModuleInfo
	 */
	public void setProductModuleInfo(FinProductModuleInfoDto productModuleInfo) {
		this.productModuleInfo = productModuleInfo;
	}

	/**
	 * 获取 bussFinProductOpenPlanDto
	 * 
	 * @return bussFinProductOpenPlanDto.
	 */
	public BussFinProductOpenPlanDto getBussFinProductOpenPlanDto() {
		return bussFinProductOpenPlanDto;
	}

	/**
	 * 设置 bussFinProductOpenPlanDto
	 * 
	 * @param bussFinProductOpenPlanDto
	 *            bussFinProductOpenPlanDto
	 */
	public void setBussFinProductOpenPlanDto(BussFinProductOpenPlanDto bussFinProductOpenPlanDto) {
		this.bussFinProductOpenPlanDto = bussFinProductOpenPlanDto;
	}

	/**
	 * 获取 bussFinProductPayPlanDto
	 * 
	 * @return bussFinProductPayPlanDto.
	 */
	public BussFinProductPayPlanDto getBussFinProductPayPlanDto() {
		return bussFinProductPayPlanDto;
	}

	/**
	 * 设置 bussFinProductPayPlanDto
	 * 
	 * @param bussFinProductPayPlanDto
	 *            bussFinProductPayPlanDto
	 */
	public void setBussFinProductPayPlanDto(BussFinProductPayPlanDto bussFinProductPayPlanDto) {
		this.bussFinProductPayPlanDto = bussFinProductPayPlanDto;
	}

	/**
	 * 获取 bussFinProductBonusPlanDto
	 * 
	 * @return bussFinProductBonusPlanDto.
	 */
	public BussFinProductBonusPlanDto getBussFinProductBonusPlanDto() {
		return bussFinProductBonusPlanDto;
	}

	/**
	 * 设置 bussFinProductBonusPlanDto
	 * 
	 * @param bussFinProductBonusPlanDto
	 *            bussFinProductBonusPlanDto
	 */
	public void setBussFinProductBonusPlanDto(BussFinProductBonusPlanDto bussFinProductBonusPlanDto) {
		this.bussFinProductBonusPlanDto = bussFinProductBonusPlanDto;
	}

	/**
	 * 获取 bussBenchMarkInfoDto
	 * 
	 * @return bussBenchMarkInfoDto.
	 */
	public BussBenchMarkInfoDto getBussBenchMarkInfoDto() {
		return bussBenchMarkInfoDto;
	}

	/**
	 * 设置 bussBenchMarkInfoDto
	 * 
	 * @param bussBenchMarkInfoDto
	 *            bussBenchMarkInfoDto
	 */
	public void setBussBenchMarkInfoDto(BussBenchMarkInfoDto bussBenchMarkInfoDto) {
		this.bussBenchMarkInfoDto = bussBenchMarkInfoDto;
	}

	/**
	 * 获取 bussRepFinProductInfoDto
	 * 
	 * @return bussRepFinProductInfoDto.
	 */
	public BussRepFinProductInfoDto getBussRepFinProductInfoDto() {
		return bussRepFinProductInfoDto;
	}

	/**
	 * 设置 bussRepFinProductInfoDto
	 * 
	 * @param bussRepFinProductInfoDto
	 *            bussRepFinProductInfoDto
	 */
	public void setBussRepFinProductInfoDto(BussRepFinProductInfoDto bussRepFinProductInfoDto) {
		this.bussRepFinProductInfoDto = bussRepFinProductInfoDto;
	}

	public List<FinProductModuleInfo002Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<FinProductModuleInfo002Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
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

	@PrimaryKey(Field.PK_1)
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

	public BussFinProductOpenPlanDto getBussFinProductOpenPlanRedDto() {
		return bussFinProductOpenPlanRedDto;
	}

	public void setBussFinProductOpenPlanRedDto(BussFinProductOpenPlanDto bussFinProductOpenPlanRedDto) {
		this.bussFinProductOpenPlanRedDto = bussFinProductOpenPlanRedDto;
	}

	public BussFinProductValuationDto getBussFinProductValuationDto() {
		return bussFinProductValuationDto;
	}

	public void setBussFinProductValuationDto(BussFinProductValuationDto bussFinProductValuationDto) {
		this.bussFinProductValuationDto = bussFinProductValuationDto;
	}

	public BussFinProductFeeInfoDto getBussFinProductFeeInfoDto() {
		return bussFinProductFeeInfoDto;
	}

	public void setBussFinProductFeeInfoDto(BussFinProductFeeInfoDto bussFinProductFeeInfoDto) {
		this.bussFinProductFeeInfoDto = bussFinProductFeeInfoDto;
	}

	public BussBenchMarkInfoDto getBussBenchMarkFloatInfoDto() {
		return bussBenchMarkFloatInfoDto;
	}

	public void setBussBenchMarkFloatInfoDto(BussBenchMarkInfoDto bussBenchMarkFloatInfoDto) {
		this.bussBenchMarkFloatInfoDto = bussBenchMarkFloatInfoDto;
	}

	public BussFinProductFloatFeeDto getBussFinProductFloatFeeDto() {
		return bussFinProductFloatFeeDto;
	}

	public void setBussFinProductFloatFeeDto(BussFinProductFloatFeeDto bussFinProductFloatFeeDto) {
		this.bussFinProductFloatFeeDto = bussFinProductFloatFeeDto;
	}

	public BussFinProductSaleParamDto getBussFinProductSaleParamDto() {
		return bussFinProductSaleParamDto;
	}

	public void setBussFinProductSaleParamDto(BussFinProductSaleParamDto bussFinProductSaleParamDto) {
		this.bussFinProductSaleParamDto = bussFinProductSaleParamDto;
	}

	public BussBenchMarkLayeredInfoDto getBussBenchMarkLayeredInfoDto() {
		return bussBenchMarkLayeredInfoDto;
	}

	public void setBussBenchMarkLayeredInfoDto(BussBenchMarkLayeredInfoDto bussBenchMarkLayeredInfoDto) {
		this.bussBenchMarkLayeredInfoDto = bussBenchMarkLayeredInfoDto;
	}

	public List<BussFinProductLayeredInfoDto> getBussFinProductLayeredInfoDto() {
		return bussFinProductLayeredInfoDto;
	}

	public void setBussFinProductLayeredInfoDto(List<BussFinProductLayeredInfoDto> bussFinProductLayeredInfoDto) {
		this.bussFinProductLayeredInfoDto = bussFinProductLayeredInfoDto;
	}

	public List<FinProductAnapleroticInfoDto> getFinProductAnapleroticTabInfoDtoList() {
		return finProductAnapleroticTabInfoDtoList;
	}

	public void setFinProductAnapleroticTabInfoDtoList(
			List<FinProductAnapleroticInfoDto> finProductAnapleroticTabInfoDtoList) {
		this.finProductAnapleroticTabInfoDtoList = finProductAnapleroticTabInfoDtoList;
	}
	
	
}
