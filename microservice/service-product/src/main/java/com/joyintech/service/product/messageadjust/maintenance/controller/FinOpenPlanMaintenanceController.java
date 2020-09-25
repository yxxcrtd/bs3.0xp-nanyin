/*
 * 文件名：FinOpenPlanMaintenanceController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.maintenance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.messageadjust.maintenance.dto.BussFinProductOpenPlanDto2;
import com.joyintech.service.product.messageadjust.maintenance.dto.FinOpenPlanMaintenance002Dto;
import com.joyintech.service.product.messageadjust.maintenance.dto.FinOpenPlanMaintenanceDto;
import com.joyintech.service.product.messageadjust.maintenance.dto.OpenPlanParamDto;
import com.joyintech.service.product.messageadjust.maintenance.service.FinOpenPlanMaintenanceService;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenRule;
import com.joyintech.tams.component.base.core.BaseFinProductOpenPlanService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductOpenPlanDto;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 开放计划维护
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月22日
 * @see FinOpenPlanMaintenanceController
 * @since 1.0
 */
@RestController("backFinOpenPlanMaintenanceController_dtx")
@RequestMapping("/FinOpenPlanMaintenance")
public class FinOpenPlanMaintenanceController {
	
    @Autowired
    private FinOpenPlanMaintenanceService finOpenPlanMaintenanceService;
    
    @Autowired
    private BaseFinProductOpenPlanService baseFinProductOpenPlanService;
    
    /**
     * 产品代码联想框 
     * @param param
     * @return
     */
    @PostMapping("/getFinprodIds")
    public List<SelectOptionDto> getFinprodIds(@RequestBody Map<String,Object> param) {
        return finOpenPlanMaintenanceService.getFinprodIds(param);
    }
    /**
     * 
     * 画面查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage")
    public List<FinOpenPlanMaintenanceDto> selectForPage(@RequestBody Map<String,Object> param) throws Exception  {
        return finOpenPlanMaintenanceService.selectForPage(param);
    }
    /**
     * 根据条件查询开放规则表
     * <功能详细描述>
     * @param param param
     * @return FinOpenRule FinOpenRule
     * @see
     * @since 1.0
     */
    @PostMapping("/getByCashId")
    public String getByCashId(@RequestBody Map<String, Object> param) {
        return finOpenPlanMaintenanceService.getByCashId(param);
    }
    /**
     * 根据金融产品代码查询市场代码
     * <功能详细描述>
     * @param param param
     * @return FinOpenRule FinOpenRule
     * @see
     * @since 1.0
     */
    @GetMapping("/getFinprodMarketIdByFinprodId")
    public String getFinprodMarketIdByFinprodId(String finprodId) {
        return finOpenPlanMaintenanceService.getFinprodMarketIdByFinprodId(finprodId);
    }
    /**
     * 查询封闭期限
     * <功能详细描述>
     * @param finprodId finprodId
     * @return Long Long
     * @see
     * @since 1.0
     */
    @GetMapping("/getByFinprodId")
    @ResponseBody
    public Long getByFinprodId(String finprodId) {
        return finOpenPlanMaintenanceService.getByFinprodId(finprodId);
    }
    /**
     * 获取交易日历代码
     * <功能详细描述>
     * @param finprodId finprodId
     * @return Long Long
     * @see
     * @since 1.0
     */
    @GetMapping("/getCalendarIdByFinprodId")
    public String getCalendarIdByFinprodId(String finprodId) {
        return finOpenPlanMaintenanceService.getCalendarIdByFinprodId(finprodId);
    }
    /**
     * 更新
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody FinOpenPlanMaintenanceDto dto) throws Exception {
        return finOpenPlanMaintenanceService.update(dto);
    }
    
	/**
	 * 开放计划调整
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("doUpdate")
	public int doUpdate(@RequestBody FinOpenPlanMaintenance002Dto dto) throws Exception {

		BussFinProductOpenPlanDto2 bfpopd = dto.getBussFinProductOpenPlanDto();
		BussFinProductOpenPlanDto2 bfpopdRed = dto.getBussFinProductOpenPlanRedDto();
		
		// 插入新开放规则
		FinOpenRule finOpenRule = new FinOpenRule();
		finOpenRule.setCashId(dto.getCashId());
		finOpenRule.setOpenType(bfpopd.getOpenType());
		finOpenRule.setOpenMode(bfpopd.getOpenMode());
		finOpenRule.setOpenDays(bfpopd.getOpenDays());
		finOpenRule.setOpenCycle(bfpopd.getOpenCycle());
		finOpenRule.setOpenCycleUnit(bfpopd.getOpenCycleUnit());
		finOpenRule.setOpenDaysType(bfpopd.getOpenDaysType());
		
		if(StringUtil.equals(bfpopd.getOpenDateRadio(), "0")) { // 几号 02
			finOpenRule.setDayType("02");
			finOpenRule.setDayValue(bfpopd.getDayOfMonth() == null ? "" : bfpopd.getDayOfMonth());
			
		} else if (StringUtil.equals(bfpopd.getOpenDateRadio(), "1")) { // 第几工作日 01
			finOpenRule.setDayType("01");
			finOpenRule.setDayValue(bfpopd.getWorkdayOfMonth() == null ? "" : bfpopd.getWorkdayOfMonth());
			
		} else if (StringUtil.equals(bfpopd.getOpenDateRadio(), "2")) { // 第几周 06  weekOfMonth;workdayOfWeek
			finOpenRule.setDayType("06");
			
			String weekOfMonth = bfpopd.getWeekOfMonth() == null ? "" : bfpopd.getWeekOfMonth();
			String workdayOfWeek = bfpopd.getWorkdayOfWeek() == null ? "" : bfpopd.getWorkdayOfWeek();
			
			if(StringUtil.isNotEmpty(weekOfMonth) && StringUtil.isNotEmpty(workdayOfWeek)) {
				finOpenRule.setDayValue(weekOfMonth + ";" + workdayOfWeek);
			}else {
				finOpenRule.setDayValue(weekOfMonth + workdayOfWeek);
			}
		} // 周几... 03
		
		finOpenRule.setFirstOpenDate(dto.getNextOpenDay()); // 首次开放日
		finOpenRule.setTradedayRule(bfpopd.getTradedayRule());
		finOpenRule.setHdayOpenFlag(bfpopd.getHdayOpenFlag());
		finOpenRule.setCpriceRule(bfpopd.getCpriceRule());
		finOpenRule.setOpenDaysType(bfpopd.getOpenDaysType());
		finOpenRule.setCalcMethod(DictCodeList.推算方式_首次支付日.getValue()); // 推算方法设置为首次支付日
		finOpenRule.setCalcRule(bfpopd.getCalcRule());

		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("cashId", dto.getCashId());
		pMap.put("openType", bfpopd.getOpenType());
		finOpenPlanMaintenanceService.deleteFinOpenRule(pMap); // 删除旧 开放规则

		// 插入新的计划
		List<FinOpenPlan> finOpenPlanList = dto.getBussFinProductOpenPlanDto().getFinOpenPlanList();
		for (int i = 0; i < finOpenPlanList.size(); i++) { // 重新组装
			finOpenPlanList.get(i).setFinprodId(dto.getFinprodId());
			finOpenPlanList.get(i).setCashId(dto.getCashId());
			finOpenPlanList.get(i).setOpenType(dto.getOpenType());
			finOpenPlanList.get(i).setOpenDateStrUn(finOpenPlanList.get(i).getOpenDateStr());
			finOpenPlanList.get(i).setOpenDateEndUn(finOpenPlanList.get(i).getOpenDateEnd());
			finOpenPlanList.get(i).setOpenVdateUn(finOpenPlanList.get(i).getOpenVdate());
			finOpenPlanList.get(i).setOpenMdateUn(finOpenPlanList.get(i).getOpenMdate());
			finOpenPlanList.get(i).setOpenNdate(finOpenPlanList.get(i).getOpenNdate());
			finOpenPlanList.get(i).setOpenStatus(DictCodeList.开放状态_开放.getValue());
			finOpenPlanList.get(i).setBranch(Long.parseLong(dto.getBranch()));
		}
		
		// 删除旧计划开始
		BaseFinProductOpenPlanDto baseFinProductOpenPlanDto = new BaseFinProductOpenPlanDto();
		baseFinProductOpenPlanDto.setFinprodId(dto.getFinprodId());
		baseFinProductOpenPlanDto.setOpenType(dto.getOpenType());
		List<FinOpenPlan> oldResultListInit = baseFinProductOpenPlanService.selectByFinOpenPlan(baseFinProductOpenPlanDto);
    	
		for(FinOpenPlan finOpenPlan : oldResultListInit) {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("cashId", finOpenPlan.getCashId());
			paraMap.put("openType", finOpenPlan.getOpenType());
			paraMap.put("openDateEnd", finOpenPlan.getOpenDateEnd());
			
			finOpenPlanMaintenanceService.deleteNewOpenPlan(paraMap);
		}
		// 删除旧计划结束
		
		//// red赎回计划开始
		if(StringUtil.equals(bfpopd.getSameFLag(), DictCodeList.是否_否.getValue())) { // 申赎不同频
			
			FinOpenRule finOpenRuleRed = new FinOpenRule();
			finOpenRuleRed.setCashId(dto.getCashId());
			finOpenRuleRed.setOpenType(DictCodeList.申赎开放类型_赎回开放.getValue());
			finOpenRuleRed.setOpenMode(bfpopdRed.getOpenMode());
			finOpenRuleRed.setOpenDays(bfpopdRed.getOpenDays());
			finOpenRuleRed.setOpenCycle(bfpopdRed.getOpenCycle());
			finOpenRuleRed.setOpenCycleUnit(bfpopdRed.getOpenCycleUnit());
			finOpenRuleRed.setOpenDaysType(bfpopdRed.getOpenDaysType());
			
			if(StringUtil.equals(bfpopdRed.getOpenDateRadio(), "0")) { // 几号 02
				finOpenRuleRed.setDayType("02");
				finOpenRuleRed.setDayValue(bfpopdRed.getDayOfMonth() == null ? "" : bfpopdRed.getDayOfMonth());
				
			} else if (StringUtil.equals(bfpopdRed.getOpenDateRadio(), "1")) { // 第几工作日 01
				finOpenRuleRed.setDayType("01");
				finOpenRuleRed.setDayValue(bfpopdRed.getWorkdayOfMonth() == null ? "" : bfpopdRed.getWorkdayOfMonth());
				
			} else if (StringUtil.equals(bfpopdRed.getOpenDateRadio(), "2")) { // 第几周 06  weekOfMonth;workdayOfWeek
				finOpenRuleRed.setDayType("06");
				
				String weekOfMonth = bfpopdRed.getWeekOfMonth() == null ? "" : bfpopdRed.getWeekOfMonth();
				String workdayOfWeek = bfpopdRed.getWorkdayOfWeek() == null ? "" : bfpopdRed.getWorkdayOfWeek();
				
				if(StringUtil.isNotEmpty(weekOfMonth) && StringUtil.isNotEmpty(workdayOfWeek)) {
					finOpenRuleRed.setDayValue(weekOfMonth + ";" + workdayOfWeek);
				}else {
					finOpenRuleRed.setDayValue(weekOfMonth + workdayOfWeek);
				}
			} // 周几... 03
			
			finOpenRuleRed.setFirstOpenDate(dto.getNextOpenDay()); // 首次开放日
			finOpenRuleRed.setTradedayRule(bfpopdRed.getTradedayRule());
			finOpenRuleRed.setHdayOpenFlag(bfpopdRed.getHdayOpenFlag());
			finOpenRuleRed.setCpriceRule(bfpopdRed.getCpriceRule());
			finOpenRuleRed.setOpenDaysType(bfpopdRed.getOpenDaysType());
			finOpenRuleRed.setCalcMethod(DictCodeList.推算方式_首次支付日.getValue()); // 推算方法设置为首次支付日
			finOpenRuleRed.setCalcRule(bfpopdRed.getCalcRule());
	
			Map<String, Object> pMapRed = new HashMap<String, Object>();
			pMapRed.put("cashId", dto.getCashId());
			pMapRed.put("openType", DictCodeList.申赎开放类型_赎回开放.getValue());
			finOpenPlanMaintenanceService.deleteFinOpenRule(pMapRed); // 删除旧 开放规则
	
			// 插入新的计划
			List<FinOpenPlan> finOpenPlanRedList = dto.getBussFinProductOpenPlanRedDto().getFinOpenPlanList();
			for (int i = 0; i < finOpenPlanRedList.size(); i++) { // 重新组装
				finOpenPlanRedList.get(i).setFinprodId(dto.getFinprodId());
				finOpenPlanRedList.get(i).setCashId(dto.getCashId());
				finOpenPlanRedList.get(i).setOpenType(DictCodeList.申赎开放类型_赎回开放.getValue());
				finOpenPlanRedList.get(i).setOpenDateStrUn(finOpenPlanRedList.get(i).getOpenDateStr());
				finOpenPlanRedList.get(i).setOpenDateEndUn(finOpenPlanRedList.get(i).getOpenDateEnd());
				finOpenPlanRedList.get(i).setOpenVdateUn(finOpenPlanRedList.get(i).getOpenVdate());
				finOpenPlanRedList.get(i).setOpenMdateUn(finOpenPlanRedList.get(i).getOpenMdate());
				finOpenPlanRedList.get(i).setOpenNdate(finOpenPlanRedList.get(i).getOpenNdate());
				finOpenPlanRedList.get(i).setOpenStatus(DictCodeList.开放状态_开放.getValue());
				finOpenPlanRedList.get(i).setBranch(Long.parseLong(dto.getBranch()));
			}
			
			// 删除旧计划开始
			BaseFinProductOpenPlanDto baseFinProductOpenPlanDtoRed = new BaseFinProductOpenPlanDto();
			baseFinProductOpenPlanDtoRed.setFinprodId(dto.getFinprodId());
			baseFinProductOpenPlanDtoRed.setOpenType(DictCodeList.申赎开放类型_赎回开放.getValue());
			List<FinOpenPlan> oldResultListInitRed = baseFinProductOpenPlanService.selectByFinOpenPlan(baseFinProductOpenPlanDtoRed);
	    	
			for(FinOpenPlan finOpenPlanRed : oldResultListInitRed) {
				Map<String, Object> paraMapRed = new HashMap<String, Object>();
				paraMapRed.put("cashId", finOpenPlanRed.getCashId());
				paraMapRed.put("openType", DictCodeList.申赎开放类型_赎回开放.getValue());
				paraMapRed.put("openDateEnd", finOpenPlanRed.getOpenDateEnd());
				
				finOpenPlanMaintenanceService.deleteNewOpenPlan(paraMapRed);
			}
		
			return finOpenPlanMaintenanceService.insertFinOpenRule(finOpenRule)
					+ finOpenPlanMaintenanceService.insertNewOpenPlan(finOpenPlanList.addAll(finOpenPlanRedList) ? finOpenPlanList : finOpenPlanList)
					+ finOpenPlanMaintenanceService.insertFinOpenRule(finOpenRuleRed);
		}
		//// red赎回计划结束 
		
		return finOpenPlanMaintenanceService.insertFinOpenRule(finOpenRule)
				+ finOpenPlanMaintenanceService.insertNewOpenPlan(finOpenPlanList);
	}
	
    /**
     * 产品代码联想框
     */
    @PostMapping("productCodeList")
    public List<SelectOptionDto> productCodeList(@RequestBody Map<String, Object> param) {
        return finOpenPlanMaintenanceService.productCodeList(param);
    }
    
    /**
     * 开放计划信息参数查询
     */
    @PostMapping("selectOpenPlanParamDto")
    public OpenPlanParamDto selectOpenPlanParamDto(@RequestBody Map<String,Object> paraMap){
        return finOpenPlanMaintenanceService.selectOpenPlanParamDto(paraMap);
    }
}
