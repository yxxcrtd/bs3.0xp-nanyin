/*
 * 文件名：PtlInvPortfolioStrategy001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloat;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlPortfolioStgy;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto.PtlIncomeIndexDto;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto.PtlInvPortfolioStrategy001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
@Transactional(rollbackFor = Exception.class)
public class PtlInvPortfolioStrategy001Service extends CommonExportService<PtlInvPortfolioStrategy001Dto>
		implements BaseProcessService<PtlInvPortfolioStrategy001Dto> {

	/**
	 * 组合代码下拉框
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<SelectOptionDto> getPortfolioId(Map<String, Object> pageParam) {
		return restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/getPortfolioId", pageParam,
				SelectOptionDto.class);
	}

	/**
	 * 策略代码下拉框
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<SelectOptionDto> getStrategyId(Map<String, Object> param) {
		return restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/getStrategyId", param,
				SelectOptionDto.class);
	}

	/**
	 * 分页查询
	 * 
	 * @param parameters
	 * @return
	 */
	public Page<PtlInvPortfolioStrategy001Dto> selectForPage(Map<String, Object> pageParam) {
		// 组合代码
		String portfolioId = (String) pageParam.get("portfolioId");
		if (StringUtil.isNotEmpty(portfolioId)) {
			List<String> portfolioIdList = StringUtil.splitToList(",", portfolioId);
			pageParam.put("portfolioIdList", portfolioIdList);
		}

		// 策略代码
		String strategyId = (String) pageParam.get("strategyId");
		if (StringUtil.isNotEmpty(strategyId)) {
			List<String> strategyIdList = StringUtil.splitToList(",", strategyId);
			pageParam.put("strategyIdList", strategyIdList);
		}

		// 策略类型
		String strategyType = (String) pageParam.get("strategyType");
		if (StringUtil.isNotEmpty(strategyType)) {
			List<String> strategyTypeList = StringUtil.splitToList(",", strategyType);
			pageParam.put("strategyTypeList", strategyTypeList);
		}
		return restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/selectForPage", pageParam,
				PtlInvPortfolioStrategy001Dto.class);
	}

	/**
	 * 导出
	 * 
	 * @param parameters
	 * @return
	 */
	public List<PtlInvPortfolioStrategy001Dto> selectForList(Map<String, Object> pageParam) {
		// 组合代码
		String portfolioId = (String) pageParam.get("portfolioId");
		if (StringUtil.isNotEmpty(portfolioId)) {
			List<String> portfolioIdList = StringUtil.splitToList(",", portfolioId);
			pageParam.put("portfolioIdList", portfolioIdList);
		}

		// 策略代码
		String strategyId = (String) pageParam.get("strategyId");
		if (StringUtil.isNotEmpty(strategyId)) {
			List<String> strategyIdList = StringUtil.splitToList(",", strategyId);
			pageParam.put("strategyIdList", strategyIdList);
		}

		// 策略类型
		String strategyType = (String) pageParam.get("strategyType");
		if (StringUtil.isNotEmpty(strategyType)) {
			List<String> strategyTypeList = StringUtil.splitToList(",", strategyType);
			pageParam.put("strategyTypeList", strategyTypeList);
		}

		return restTemplate.postForList(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/selectForList", pageParam,
				PtlInvPortfolioStrategy001Dto.class);
	}

	/**
	 * 通过组合代码，获取组合名称
	 * 
	 * @param pageParam
	 * @return
	 */
	public PtlPortfolio getPortfolioName(Map<String, Object> pageParam) {
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/getPortfolioName", pageParam,
				PtlPortfolio.class);
	}

	/**
	 * 通过策略代码，获取策略名称和策略描述
	 * 
	 * @param pageParam
	 * @return
	 */
	public PtlInvPortfolioStrategy001Dto getStrategy(Map<String, Object> pageParam) {
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/getStrategy", pageParam,
				PtlInvPortfolioStrategy001Dto.class);
	}

	/**
	 * 新建-唯一性校验
	 * 
	 * @param dto
	 */
	public PtlPortfolioStgy newCheck(PtlInvPortfolioStrategy001Dto dto) {
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/newCheck", dto,
				PtlPortfolioStgy.class);
	}

	/**
	 * 双击查看
	 * 
	 * @param params
	 * @return
	 */
	public PtlInvPortfolioStrategy001Dto getPtlInvPortfolioStrategy(Map<String, Object> params) {
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/getPtlInvPortfolioStrategy",
				params, PtlInvPortfolioStrategy001Dto.class);
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @return
	 */
	public int delete(PtlInvPortfolioStrategy001Dto dto) {
		return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/delete",
				dto, Integer.class);
	}

	/**
	 * 新建
	 * 
	 * @param dto
	 * @return
	 */
	public int insert(PtlInvPortfolioStrategy001Dto dto) {
		return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/insert",
				dto, Integer.class);
	}

	/**
	 * 获取收益目标列表
	 * 
	 * @param pageParam
	 * @return
	 */
	public List<PtlIncomeIndexDto> getPtlIncomeIndexFloatList(Map<String, Object> pageParam) {
		return restTemplate.postForList(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/getPtlIncomeIndexFloatList",
				pageParam, PtlIncomeIndexDto.class);
	}

	/**
	 * 查询组合代码不是没有子组合的产品组合
	 * 
	 * @param params
	 * @return
	 */
	public int getPtlPortfolio(Map<String, Object> params) {
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlinvportfoliostrategy/getPtlPortfolio", params,
				Integer.class);
	}

	@Override
	public void doComplete(PtlInvPortfolioStrategy001Dto entity, OperationType type) {
		this.insert(entity);

	}

    /**
     * 处理行编辑数据
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public PtlIncomeIndexDto dealEditData(PtlIncomeIndexDto entity) {
        // 浮动
        if (!StringUtil.isEmpty(entity.getIncomeType())
            && StringUtil.equals(entity.getIncomeType(), "02")) {
            List<PtlIncomeIndexFloat> floatList = new ArrayList<>();
            // 【浮动目标值】公式格式：基准1*系数1+基准2*系数2+……+利差
            List<String> floatTarget = StringUtil.splitToList("+", entity.getIncomeTargetCode());
            if (!StringUtil.isEmptyList(floatTarget)) {
                for (int i = 0; i < floatTarget.size() - 1; i++ ) {
                    String detail = floatTarget.get(i);
                    PtlIncomeIndexFloat indexDto = new PtlIncomeIndexFloat();
                    // 基准
                    String baseRuleId = detail.substring(0, detail.indexOf("*"));
                    indexDto.setBaseRuleId(baseRuleId);
                    // 系数
                    String coefficient = detail.substring(detail.indexOf("*") + 1,
                        detail.length());
                    indexDto.setCoefficient(new BigDecimal(coefficient));
                    // 利差
                    indexDto.setSpreadRate(ArithmeticUtil.divide(
                        new BigDecimal(floatTarget.get(floatTarget.size() - 1)), 100, 14));
                    floatList.add(indexDto);
                }
            }
            entity.setFloatList(floatList);
            entity.setSpreadRate(new BigDecimal(floatTarget.get(floatTarget.size() - 1)));
        }

        return entity;
    }

}
