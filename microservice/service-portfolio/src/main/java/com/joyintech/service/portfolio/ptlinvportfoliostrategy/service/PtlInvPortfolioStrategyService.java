/*
 * 文件名：PtlInvPortfolioStrategyService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinvportfoliostrategy.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.portfolio.ptlinvportfoliostrategy.dao.PtlInvPortfolioStrategyMapper;
import com.joyintech.service.portfolio.ptlinvportfoliostrategy.dto.PtlIncomeIndexDto;
import com.joyintech.service.portfolio.ptlinvportfoliostrategy.dto.PtlInvPortfolioStrategyDto;
import com.joyintech.tams.common.base.dao.PtlIncomeIndexFloatMapper;
import com.joyintech.tams.common.base.dao.PtlIncomeIndexMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioEvalMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioStgyMapper;
import com.joyintech.tams.common.base.entity.PtlIncomeIndex;
import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloat;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlPortfolioEval;
import com.joyintech.tams.common.base.entity.PtlPortfolioEvalKey;
import com.joyintech.tams.common.base.entity.PtlPortfolioStgy;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;

@Service
public class PtlInvPortfolioStrategyService extends BaseService<PtlInvPortfolioStrategyDto> {

	@Autowired
	private PtlInvPortfolioStrategyMapper ptlInvPortfolioStrategyMapper;

	/**
	 * 组合策略
	 */
	@Autowired
	private PtlPortfolioStgyMapper ptlPortfolioStgyMapper;

	/**
	 * 组合策略指标
	 */
	@Autowired
	private PtlPortfolioEvalMapper ptlPortfolioEvalMapper;

	/**
	 * 收益指标目标
	 */
	@Autowired
	private PtlIncomeIndexMapper ptlIncomeIndexMapper;

	/**
	 * 浮动收益目标
	 */
	@Autowired
	private PtlIncomeIndexFloatMapper ptlIncomeIndexFloatMapper;

	/**
	 * 组合代码下拉列表
	 * 
	 * @param pageParam
	 * @param page
	 * @param length
	 * @return
	 */
	public Page<SelectOptionDto> getPortfolioId(Map<String, Object> pageParam, int page, int length) {
		pageParam.put("investYesOrNo", DictCodeList.是否_是.getValue());
		return super.queryForPageForSelectReturn(page, length,
				() -> ptlInvPortfolioStrategyMapper.getPortfolioId(pageParam));
	}

	/**
	 * 策略代码下拉框
	 * 
	 * @param pageParam
	 * @param page
	 * @param length
	 * @return
	 */
	public Page<SelectOptionDto> getStrategyId(Map<String, Object> pageParam, int page, int length) {
		return super.queryForPageForSelectReturn(page, length,
				() -> ptlInvPortfolioStrategyMapper.getStrategyId(pageParam));
	}

	/**
	 * 画面查询方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public Page<PtlInvPortfolioStrategyDto> selectForPage(int page, int length, Map<String, Object> pageParam) {
		Page<PtlInvPortfolioStrategyDto> dtoPage = super.queryForPage(page, length,
				() -> ptlInvPortfolioStrategyMapper.selectForPage(pageParam));
		return dtoPage;

	}

	/**
	 * 导出
	 * 
	 * @param pageParam
	 * @return
	 */
	public List<PtlInvPortfolioStrategyDto> selectForList(Map<String, Object> pageParam) {
		List<PtlInvPortfolioStrategyDto> list = ptlInvPortfolioStrategyMapper.selectForPage(pageParam);
		return list;
	}

	/**
	 * 通过组合代码，获取组合名称
	 * 
	 * @param pageParam
	 * @return
	 */
	public PtlPortfolio getPortfolioName(Map<String, Object> pageParam) {
		return ptlInvPortfolioStrategyMapper.getPortfolioName(pageParam);
	}

	/**
	 * 通过策略代码，获取策略名称和策略描述
	 * 
	 * @param pageParam
	 * @return
	 */
	public PtlInvPortfolioStrategyDto getStrategy(Map<String, Object> pageParam) {
	    PtlInvPortfolioStrategyDto dto =  ptlInvPortfolioStrategyMapper.getStrategy(pageParam);
	    pageParam.put("indexId", dto.getStrategyId());
	    dto.setPtlIncomeIndexList(ptlInvPortfolioStrategyMapper.getPtlIncomeIndexFloatList(pageParam));
	    return dto;
	}

	/**
	 * 新建-唯一性校验
	 * 
	 * @param ptlPortfolioStrategy
	 * @return
	 */
	public PtlPortfolioStgy newCheck(Map<String, Object> map) {
		return ptlInvPortfolioStrategyMapper.newCheck(map);
	}

	/**
	 * 双击查看
	 * 
	 * @param params
	 * @return
	 */
    public PtlInvPortfolioStrategyDto getPtlInvPortfolioStrategy(Map<String, Object> params) {
        PtlInvPortfolioStrategyDto dto = new PtlInvPortfolioStrategyDto();
        List<PtlInvPortfolioStrategyDto> list = ptlInvPortfolioStrategyMapper.getPtlInvPortfolioStrategy(
            params);
        List<PtlIncomeIndexDto> ptlIncomeIndexList = new ArrayList<>();
        for (PtlInvPortfolioStrategyDto temp : list) {
            PtlIncomeIndexDto index = new PtlIncomeIndexDto();
            if (StringUtil.isNotEmpty(temp.getIncomeType())) {
                if (StringUtil.equals(temp.getIncomeType(), "01")) {
                    index.setEvalIndex(temp.getEvalIndex());
                    index.setIncomeType(temp.getIncomeType());
                    index.setIncomeTarget(temp.getIncomeTarget().toString());
                    index.setMaxIncomeTarget(temp.getMaxIncomeTarget());
                    index.setMinIncomeTarget(temp.getMinIncomeTarget());
                    ptlIncomeIndexList.add(index);
                }
                else {
                    index.setEvalIndex(temp.getEvalIndex());
                    index.setIncomeType(temp.getIncomeType());
                    List<PtlIncomeIndexFloat> floatList = ptlInvPortfolioStrategyMapper.selectFloatByIndexId(
                        temp.getIndexId());
                    StringBuilder incomeTarget = new StringBuilder();
                    StringBuilder incomeTargetCode = new StringBuilder();
                    for (PtlIncomeIndexFloat floatDto : floatList) {
                        incomeTarget.append(SysDataUtil.getNameFromDataDict(DictCode.利率行情,
                            floatDto.getBaseRuleId())).append("*").append(
                                floatDto.getCoefficient().toString()).append("+");

                        incomeTargetCode.append(floatDto.getBaseRuleId()).append("*").append(
                            floatDto.getCoefficient().toString()).append("+");
                    }
                    incomeTarget.append(ArithmeticUtil.multiply(floatList.get(0).getSpreadRate(),new BigDecimal(100))).append("%");
                    incomeTargetCode.append(ArithmeticUtil.multiply(floatList.get(0).getSpreadRate(),new BigDecimal(100)));
                    index.setIncomeTarget(incomeTarget.toString());
                    index.setIncomeTargetCode(incomeTargetCode.toString());
                    ptlIncomeIndexList.add(index);
                }
            }
            else {
                index.setEvalIndex(temp.getEvalIndex());
                ptlIncomeIndexList.add(index);
            }
        }
        BeanUtil.copyPropertiesIgnoreNull(dto, list.get(0));
        dto.setPtlIncomeIndexList(ptlIncomeIndexList);
        return dto;
    }

	/**
	 * 删除
	 * 
	 * @param dto
	 * @return
	 */
	public int delete(PtlInvPortfolioStrategyDto dto) {
		// 组合策略代码
		String ptlStgyId = dto.getPtlStgyId();
		// 指标代码
		String indexId = dto.getIndexId();

		// 删除组合策略指标
		PtlPortfolioEvalKey ptlPortfolioEvalKey = new PtlPortfolioEvalKey();
		ptlPortfolioEvalKey.setPtlStgyId(ptlStgyId);
		ptlPortfolioEvalKey.setEvalIndex(DictCodeList.评价指标_收益指标.getValue());
		ptlPortfolioEvalKey.setIndexId(indexId);
		ptlPortfolioEvalMapper.deleteByPrimaryKey(ptlPortfolioEvalKey);

		// 删除收益指标目标
		ptlIncomeIndexMapper.deleteByPrimaryKey(indexId);

		if (StringUtil.equals(dto.getIncomeType(), DictCodeList.评价指标收益目标类型_浮动.getValue())) {
			// 删除浮动收益目标
			Map<String, Object> params = new HashMap<>();
			params.put("indexId", indexId);
			ptlInvPortfolioStrategyMapper.deletePtlIncomeIndexFloat(params);
		}

		// 删除 组合策略
		return ptlPortfolioStgyMapper.deleteByPrimaryKey(dto.getPtlStgyId());
	}

	/**
	 * 新建
	 * 
	 * @param dto
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public int insert(PtlInvPortfolioStrategyDto dto) throws Exception {
        // 组合策略代码
        String ptlStgyId = dto.getPtlStgyId();
	    //【投资策略】入库
        PtlPortfolioStgy ptlPortfolioStgy = new PtlPortfolioStgy();
        // 组合策略代码
        ptlPortfolioStgy.setPtlStgyId(ptlStgyId);
        // 组合代码
        ptlPortfolioStgy.setPortfolioId(dto.getPortfolioId());
        // 策略代码
        ptlPortfolioStgy.setStrategyId(dto.getStrategyId());
        // 生效日
        ptlPortfolioStgy.setEffDate(DateUtil.parse(dto.getEffDate()));

        ptlPortfolioStgy.setCreateUser(dto.getCreateUser());
        ptlPortfolioStgy.setCreateTime(dto.getCreateTime());
        ptlPortfolioStgy.setUpdateUser(dto.getUpdateUser());
        ptlPortfolioStgy.setUpdateTime(dto.getUpdateTime());
	    int count = ptlPortfolioStgyMapper.insert(ptlPortfolioStgy);
	    
	    for(PtlIncomeIndexDto detail:dto.getPtlIncomeIndexList()) {
	        // 指标代码
	        String indexId = InterfHelper.getUUid();
	        //【组合策略指标】入库
	        PtlPortfolioEval ptlPortfolioEval = new PtlPortfolioEval();
	        // 组合策略代码
	        ptlPortfolioEval.setPtlStgyId(ptlStgyId);
	        // 评价指标
	        ptlPortfolioEval.setEvalIndex(detail.getEvalIndex());
	        // 指标代码
	        ptlPortfolioEval.setIndexId(indexId);
	        // 组合策略指标入库
	        ptlPortfolioEvalMapper.insert(ptlPortfolioEval);
	        
	        //【收益指标目标】入库
	        if (StringUtil.equals(detail.getIncomeType(), DictCodeList.评价指标收益目标类型_固定.getValue())) {
	            // 收益指标目标表
	            PtlIncomeIndex ptlIncomeIndex = new PtlIncomeIndex();
	            ptlIncomeIndex.setIndexId(indexId);
	            ptlIncomeIndex.setIncomeType(detail.getIncomeType());
	            ptlIncomeIndex.setIncomeTarget(ArithmeticUtil.divide(new BigDecimal(detail.getIncomeTarget()), 100, 14));
	            ptlIncomeIndex.setMinIncomeTarget(ArithmeticUtil.divide(detail.getMinIncomeTarget(), 100, 14));
	            ptlIncomeIndex.setMaxIncomeTarget(ArithmeticUtil.divide(detail.getMaxIncomeTarget(), 100, 14));
	            ptlIncomeIndexMapper.insert(ptlIncomeIndex);
	        } else {
	            // 收益指标目标表
	            PtlIncomeIndex ptlIncomeIndex = new PtlIncomeIndex();
	            ptlIncomeIndex.setIndexId(indexId);
	            ptlIncomeIndex.setIncomeType(detail.getIncomeType());
	            ptlIncomeIndexMapper.insert(ptlIncomeIndex);
	            
                // 浮动收益目标
	            //【浮动目标值】公式格式：基准1*系数1+基准2*系数2+……+利差
	            List<String> floatTarget = StringUtil.splitToList("+", detail.getIncomeTargetCode());
                if (!StringUtil.isEmptyList(floatTarget)) {
                    for (int i = 0; i < floatTarget.size() - 1; i++ ) {
                        String incomeTarget = floatTarget.get(i);
                        PtlIncomeIndexFloat indexDto = new PtlIncomeIndexFloat();
                        indexDto.setIndexId(indexId);
                        // 层级
                        indexDto.setLev((long)(i + 1));
                        // 基准
                        String baseRuleId = incomeTarget.substring(0, incomeTarget.indexOf("*"));
                        indexDto.setBaseRuleId(baseRuleId);
                        // 系数
                        String coefficient = incomeTarget.substring(incomeTarget.indexOf("*") + 1,
                            incomeTarget.length());
                        indexDto.setCoefficient(new BigDecimal(coefficient));
                        // 利差
                        indexDto.setSpreadRate(ArithmeticUtil.divide(
                            new BigDecimal(floatTarget.get(floatTarget.size() - 1)), 100, 14));
                        ptlIncomeIndexFloatMapper.insert(indexDto);
                    }
                }
	        }
	    }
		return count;
	}

	/**
	 * 根据指标代码，获取浮动收益目标列表
	 * 
	 * @param pageParam
	 * @return
	 */
	public List<PtlIncomeIndexDto> getPtlIncomeIndexFloatList(Map<String, Object> pageParam) {
		return ptlInvPortfolioStrategyMapper.getPtlIncomeIndexFloatList(pageParam);
	}

	/**
	 * 查询组合代码不是没有子组合的产品组合
	 * 
	 * @param params
	 * @return
	 */
	public int getPtlPortfolio(Map<String, Object> params) {
		return ptlInvPortfolioStrategyMapper.getPtlPortfolio(params);
	}

}
