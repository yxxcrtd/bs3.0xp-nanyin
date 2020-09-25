/*
 * 文件名：FinPayPlanAdjustmentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.prod.BussFinProductFeeInfoService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustment002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 信息调整管理_付费规则调整 〈功能详细描述〉
 * 
 * @author yaozhenguo
 * @version 1.0 2019年11月26日
 * @see FinPayPlanAdjustment002Service
 * @since 1.0
 */
@Service
public class FinPayPlanAdjustment002Service extends CommonExportService<FinPayPlanAdjustment002Dto>
		implements BaseProcessService<FinPayPlanAdjustment002Dto> {

	@Autowired
	private BussFinProductFeeInfoService bussFinProductFeeInfoService;

	@Override
	public void doComplete(FinPayPlanAdjustment002Dto entity, OperationType type) {
		if (type == OperationType.DELETE) {
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/deleteFinProductFeeInfo", entity,
					Integer.class);
		} else if (type == OperationType.NEW) {
			if (entity.getSelectedItem() != null) {
				List<? extends DataDictItem> feeTypeItemList = SysDataUtil.getDataDictItem(DictCode.费用品种类型.getValue());
				List<? extends DataDictItem> basicItemList = SysDataUtil.getDataDictItem(DictCode.计费基数_费用品种.getValue());
				List<? extends DataDictItem> basisItemList = SysDataUtil.getDataDictItem(DictCode.计息基础.getValue());
				for (FinPayPlanAdjustment002Dto finPayPlanAdjustment002Dto : entity.getSelectedItem()) {
					BussFinProductFeeInfoDto bussFinProductFeeInfoDto = new BussFinProductFeeInfoDto();
					BeanUtil.copyPropertiesIgnoreNull(bussFinProductFeeInfoDto, finPayPlanAdjustment002Dto);
					feeTypeItemList.forEach(item -> {
						if (StringUtil.equals(item.getItemValue(), bussFinProductFeeInfoDto.getFeeType())) {
							bussFinProductFeeInfoDto.setFeeType(item.getItemCode());
						}
					});
					basicItemList.forEach(item -> {
						if (StringUtil.equals(item.getItemValue(), bussFinProductFeeInfoDto.getBasicBill())) {
							bussFinProductFeeInfoDto.setBasicBill(item.getItemCode());
						}
					});
					basisItemList.forEach(item -> {
						if (StringUtil.equals(item.getItemValue(), bussFinProductFeeInfoDto.getBasis())) {
							bussFinProductFeeInfoDto.setBasis(item.getItemCode());
						}
					});
					if (!StringUtil.equals(DictCodeList.收费类型_不规则费用.getValue(), bussFinProductFeeInfoDto.getChargeType())
							&& !StringUtil.equals(bussFinProductFeeInfoDto.getPayFeeRule(),
									DictCodeList.频率_不规则.getValue())) {
						// 付费规则调整页面生成付费计划
						if (StringUtil.isNotEmpty(bussFinProductFeeInfoDto.getCashId())) {
							finPayPlanAdjustment002Dto.setFinCashPlanList(
									bussFinProductFeeInfoService.finCashPlanAdjustment(bussFinProductFeeInfoDto,
											finPayPlanAdjustment002Dto.getPlanStartDay(),
											finPayPlanAdjustment002Dto.getPlanEndDay()));
							/*
							 * List<FinCashPlan> finCashPlans = bussFinProductFeeInfoService
							 * .queryFeePlanListByFinprodId(bussFinProductFeeInfoDto.getFinprodId(),
							 * bussFinProductFeeInfoDto.getFeeId()); if
							 * (!StringUtil.isEmptyList(finCashPlans)) { if
							 * (DateUtil.compareDate(bussFinProductFeeInfoDto.getProvisionFirstDay(),
							 * finCashPlans.get(0).getVdate()) < 0) {
							 * bussFinProductFeeInfoDto.setFirstCalEndDate(null); list =
							 * bussFinProductFeeInfoService.generatePayFeePlan(bussFinProductFeeInfoDto); }
							 * for (FinCashPlan finCashPlan : finCashPlans) { if
							 * (DateUtil.compareDate(bussFinProductFeeInfoDto.getProvisionFirstDay(),
							 * finCashPlan.getVdate()) > 0) { list.add(finCashPlan); // 新调整计划从下一区间开始 if
							 * (DateUtil.compareDate(bussFinProductFeeInfoDto.getProvisionFirstDay(),
							 * finCashPlan.getMdate()) <= 0) { if
							 * (DateUtil.compareDate(bussFinProductFeeInfoDto.geteAccrDate(),
							 * finCashPlan.getMdate()) > 0) {
							 * bussFinProductFeeInfoDto.setVdate(finCashPlan.getMdate());
							 * bussFinProductFeeInfoDto.setFirstCalEndDate(null);
							 * list.addAll(bussFinProductFeeInfoService
							 * .generatePayFeePlan(bussFinProductFeeInfoDto)); } break; } } } }
							 * finPayPlanAdjustment002Dto.setFinCashPlanList(list);
							 */} else {
								 bussFinProductFeeInfoDto.setVdate(bussFinProductFeeInfoDto.getProvisionFirstDay());
								 finPayPlanAdjustment002Dto.setFinCashPlanList(
									bussFinProductFeeInfoService.generatePayFeePlan(bussFinProductFeeInfoDto));
						}
						// 付费规则调整页面生成付费计划
					}
				}
				restTemplate.postForObject(
						ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/insertFinProductFeeInfo",
						entity, Integer.class);

			}
		}
	}

	/**
	 * 
	 * 新建页面产品代码下拉框 <功能详细描述>
	 * 
	 * @param param
	 *            Map<String,Object>
	 * @return List<SelectOptionDto>
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> getSelectFinprodId(Map<String, Object> param) {
		if (!StringUtil.isNotEmpty(param.get("initValue"))) {
			User user = SecurityUtil.getLoginUser();
			param.put("userId", user.getUserName());
		}
		return restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/getSelectFinprodId", param,
				SelectOptionDto.class);
	}

	/**
	 * 产品款型下拉框
	 * 
	 * @param param
	 * @return
	 */
	public Page<SelectOptionDto> getSelectProdStyle(Map<String, Object> param) {
		if (!StringUtil.isNotEmpty(param.get("finprodMarketId"))) {
			return new Page<>();
		}
		return restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/getSelectProdStyle", param,
				SelectOptionDto.class);
	}

	/**
	 * 费用列表查询
	 * 
	 * @param finprodId
	 * @param finprodType2
	 * @return
	 */
	public List<FinPayPlanAdjustment002Dto> queryFeeInfoByFinprodId(Map<String, Object> param) {
		List<FinPayPlanAdjustment002Dto> feeInfoList = restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinPayPlanAdjustment/queryFeeInfoByFinprodId", param,
				FinPayPlanAdjustment002Dto.class);
		return feeInfoList;
	}

	public FinProduct selectFinProductByFinprodId(String finprodId) {
		return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/FinPayPlanAdjustment/selectFinProductByFinprodId?finprodId=" + finprodId, FinProduct.class);
	}

	/**
	 * 根据金融产品代码查询分层产品子产品
	 * 
	 * @param finprodId
	 * @return
	 */
	public List<FinProduct> selectLayProductByFinproductId(String finprodId) {
		return restTemplate.getForList(
				ServiceApplication.PRODUCT_SERVICE_NAME
						+ "/FinPayPlanAdjustment/selectLayProductByFinproductId?finprodId=" + finprodId,
				FinProduct.class);
	}
}
