/*
 * 文件名：FinOpenPlanMaintenanceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.maintenance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenRule;
import com.joyintech.tams.component.base.core.BaseFinProductOpenPlanService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductOpenPlanDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.messageadjust.maintenance.dao.FinOpenPlanMaintenanceMapper;
import com.joyintech.service.product.messageadjust.maintenance.dto.FinOpenPlanMaintenanceDto;
import com.joyintech.service.product.messageadjust.maintenance.dto.OpenPlanParamDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wanzgehui
 * @version 1.0 2018年10月22日
 * @see FinOpenPlanMaintenanceService
 * @since 1.0
 */
@Service("backFinOpenPlanMaintenanceService")
public class FinOpenPlanMaintenanceService  extends BaseService<FinOpenPlanMaintenanceDto> {
    @Autowired
    private FinOpenPlanMaintenanceMapper finOpenPlanMaintenanceMapper;
    /**
     * 开放计划组件
     */
    @Autowired
    private BaseFinProductOpenPlanService baseFinProductOpenPlanService;
    /**
     * 产品代码的联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodIds(Map<String, Object> param) {
        param.put("ISSUE_MODE_01", DictCodeList.开放类型_开放式.getValue());
        param.put("OPENMODE_02", DictCodeList.开放模式_高频开放.getValue());
        param.put("openType03", DictCodeList.申赎开放类型_申赎开放.getValue());
        param.put("openType01", DictCodeList.申赎开放类型_申购开放.getValue());
        return finOpenPlanMaintenanceMapper.getFinprodIds(param);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public List<FinOpenPlanMaintenanceDto> selectForPage(Map<String, Object> pageParam) throws Exception {
        pageParam.put("openType03", DictCodeList.申赎开放类型_申赎开放.getValue());
        return finOpenPlanMaintenanceMapper.selectForPage(pageParam);
    }
    /**
     * 根据条件查询开放规则表
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public String getByCashId(Map<String, Object> param) {
        return finOpenPlanMaintenanceMapper.getByCashId(param);
    }
    /**
     * 查询封闭期限
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public Long getByFinprodId(String finprodId) {
        return finOpenPlanMaintenanceMapper.getByFinprodId(finprodId);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int update(FinOpenPlanMaintenanceDto dto) {
        List<FinOpenPlanMaintenanceDto> listDto = dto.getSelectedDto();
        List<FinOpenPlan> finOpenPlanList = new ArrayList<FinOpenPlan>();
        if(listDto != null && listDto.size()>0){
            String openType = new String();
            for (FinOpenPlanMaintenanceDto finOpenPlanMaintenanceDto : listDto) {
                if(StringUtil.isNotEmpty(finOpenPlanMaintenanceDto.getOpenType())){
                    openType = finOpenPlanMaintenanceDto.getOpenType();
                    break;
                }
            }
            for(FinOpenPlanMaintenanceDto entity:listDto){
                entity.setFinprodId(dto.getFinprodId());
                //如果数据为新添加的，那么未调整的日期同新建日期
                if(entity.getOpenDateEndUn() == null){
                    entity.setOpenDateEndUn(entity.getOpenDateEnd());
                }
                if(entity.getOpenDateStrUn() == null){
                    entity.setOpenDateStrUn(entity.getOpenDateStr());
                }
                if(entity.getOpenVdateUn() == null){
                    entity.setOpenVdateUn(entity.getOpenVdate());
                }
                if(entity.getOpenMdateUn() == null){
                    entity.setOpenMdateUn(entity.getOpenMdate());
                }
                if(entity.getBranch() == null){
                    entity.setBranch(dto.getBranch());
                }
                if(entity.getOpenType() == null){
                    entity.setOpenType(openType);
                }
                if(entity.getOpenStatus() == null){
                    entity.setOpenStatus(DictCodeList.开放状态_开放.getValue());
                }
                finOpenPlanList.add(entity);
            }
        }
        BaseFinProductOpenPlanDto baseFinProductOpenPlanDto = new BaseFinProductOpenPlanDto();
        baseFinProductOpenPlanDto.setCashId(dto.getCashId());
        if(!StringUtil.isEmptyList(finOpenPlanList)){
            baseFinProductOpenPlanDto.setOpenType(finOpenPlanList.get(0).getOpenType());
        }
        if(baseFinProductOpenPlanService != null){
            baseFinProductOpenPlanService.deleteByFinOpenPlan(baseFinProductOpenPlanDto);
            baseFinProductOpenPlanService.insert(finOpenPlanList);
            //根据开放计划更新浮动管理费计划
            baseFinProductOpenPlanService.updateFinFloatAccrualSche(baseFinProductOpenPlanDto, finOpenPlanList);
        }
        if(!StringUtil.isEmptyList(finOpenPlanList)){
            //根据母产品代码查询子产品代码和开放计划现金流代码
            List<FinOpenPlan> list = this.queryLayerFinprodIdAndCashId(dto.getFinprodId(), finOpenPlanList.get(0).getOpenType());
            //子产品根据母产品来更新
            if(!StringUtil.isEmptyList(list)){
                list.forEach(finOpenPlan ->{
                    for (FinOpenPlan plan : finOpenPlanList) {
                        plan.setCashId(finOpenPlan.getCashId());
                        plan.setFinprodId(finOpenPlan.getFinprodId());
                    }
                    baseFinProductOpenPlanDto.setCashId(finOpenPlan.getCashId());
                    baseFinProductOpenPlanService.deleteByFinOpenPlan(baseFinProductOpenPlanDto);
                    baseFinProductOpenPlanService.insert(finOpenPlanList);
                    //根据开放计划更新浮动管理费计划
                    baseFinProductOpenPlanService.updateFinFloatAccrualSche(baseFinProductOpenPlanDto, finOpenPlanList);
                });
            }
        }
        return 0;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public String getCalendarIdByFinprodId(String finprodId) {
        return finOpenPlanMaintenanceMapper.getCalendarIdByFinprodId(finprodId);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public String getFinprodMarketIdByFinprodId(String finprodId) {
        return finOpenPlanMaintenanceMapper.getFinprodMarketIdByFinprodId(finprodId);
    }
    /**
     * 根据母产品代码查询子产品代码和开放计划现金流代码
     * <功能详细描述>
     * @param pfinprodId
     * @return
     * @see
     * @since 1.0
     */
    private List<FinOpenPlan> queryLayerFinprodIdAndCashId(String finprodId,String openType){
        HashMap<String, Object> map = new HashMap<>();
        map.put("pfinprodId", finprodId);
        map.put("CASH_TYPE1_01", DictCodeListExt1.现金流一级类型_本金.getValue());
        map.put("CASH_TYPE2_C01", DictCodeListExt1.现金流二级类型_本金.getValue());
        map.put("openType", openType);
        return finOpenPlanMaintenanceMapper.queryLayerFinprodIdAndCashId(map);
    } 
    
    /**
     * 删除开放规则
     */
    public int deleteFinOpenRule(Map<String,Object> paraMap) {
    	return finOpenPlanMaintenanceMapper.deleteFinOpenRule(paraMap);
    }
    
    /**
     * 删除开放计划
     */
    public int deleteNewOpenPlan(Map<String,Object> paraMap) {
    	return finOpenPlanMaintenanceMapper.deleteNewOpenPlan(paraMap);
    }
    
    /**
     * 新增开放规则
     */
    public int insertFinOpenRule(FinOpenRule finOpenRule) {
    	return finOpenPlanMaintenanceMapper.insertFinOpenRule(finOpenRule);
    }
    
    /**
     * 新开放计划生成
     */
    public int insertNewOpenPlan(List<FinOpenPlan> finOpenPlanList) {
    	
    	int flag = 0;
    	try {
    		flag = finOpenPlanMaintenanceMapper.insertNewOpenPlan(finOpenPlanList);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return flag;
    }
    
    /**
     * 产品代码联想框
     */
    public List<SelectOptionDto> productCodeList(Map<String,Object> pageParam){
        return finOpenPlanMaintenanceMapper.productCodeList(pageParam);
    }
    
    /**
     * 开放计划信息参数查询
     */
    public OpenPlanParamDto selectOpenPlanParamDto(Map<String,Object> paraMap){
    	paraMap.put("cash_type_1", "01");
    	paraMap.put("cash_type_2", "C01");
        return finOpenPlanMaintenanceMapper.selectOpenPlanParamDto(paraMap);
    }
}
