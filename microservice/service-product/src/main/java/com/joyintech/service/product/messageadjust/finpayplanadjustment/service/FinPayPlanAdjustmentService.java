/*
 * 文件名：FinPayPlanAdjustmentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finpayplanadjustment.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.component.base.core.BaseFinProductCashPayPlanService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.dao.FinPayPlanAdjustmentMapper;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustmentDto;

/**
 * 信息调整管理_付费计划调整
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月18日
 * @see FinPayPlanAdjustmentService
 * @since 1.0
 */
@Service("backFinPayPlanAdjustmentService")
public class FinPayPlanAdjustmentService extends BaseService<FinPayPlanAdjustmentDto>{
    
    /**
     * 现金流计划
     */
//    @Autowired
//    private FinCashPlanMapper finCashPlanMapper;
    
    /**
     * 金融产品现金流支付计划组件
     */
    @Autowired(required=false)
    private BaseFinProductCashPayPlanService baseFinProductCashPayPlanService;
    
    /**
     * 
     */
    @Autowired
    private FinPayPlanAdjustmentMapper finPayPlanAdjustmentMapper;
    
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId(Map<String, Object> param){
        //数据字典.金融产品类型（投管用）.理财产品
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        //数据字典缓存.现金流业务类型.金融产品
//        param.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        //数据字典.费用类型.费用
        param.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        //数据字典.支付频率.付息频率.不规则
     // 一体化对应etliu start
//        param.put("payCycle", DictCodeList.支付频率_付息频率_不规则.getValue());
        param.put("payCycle", DictCodeList.频率_不规则.getValue());
     // 一体化对应etliu end
        param.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        //add by wm 周期型产品添加参数 2019-10-16 start
        param.put("dealMode_10", DictCodeList.处理模式_周期型.getValue());
        param.put("dealMode_01", DictCodeList.处理模式_普通.getValue());
        param.put("yeOrNO_N", DictCodeList.是否_否.getValue());
        //add by wm 周期型产品添加参数 2019-10-16 end
        param.put("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        return finPayPlanAdjustmentMapper.getFinprodId(param);
    }
    
    /**
     * 
     * 产品款型下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getProdType(Map<String, Object> param){
        return finPayPlanAdjustmentMapper.getProdType(param);
    }
    
    /**
     * 
     * 根据产品代码获取费用名称
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFeeName(Map<String, Object> param){
        //数据字典.现金流业务类型.金融产品
        param.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        //数据字典.现金流一级类型.费用   
        param.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        //数据字典.现金流二级类型.浮动管理费
        param.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        return finPayPlanAdjustmentMapper.getFeeName(param);
    }
    
    /**
     * 
     * 根据产品代码和费用名称，联动带出产品对应费用的付费计划
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam
     * @return Page<FinPayPlanAdjustmentDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<FinPayPlanAdjustmentDto> selectForPage(Map<String, Object> pageParam)
        throws Exception {
            /*pageParam.put("openDateStrStart", DateUtil.parse((String)pageParam.get("openDateStrStart")));
            pageParam.put("openDateStrEnd", DateUtil.parse((String)pageParam.get("openDateStrEnd")));*/
            //数据字典.交易方向.付费  
            pageParam.put("ps", DictCodeList.交易方向_产品付费.getValue());
            //return super.queryForPage(page, length, () -> finPayPlanAdjustmentMapper.selectForPage(pageParam));
            return finPayPlanAdjustmentMapper.selectForPage(pageParam);
    }
    
    /**
     * 
     * 获取到期日
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinPayPlanAdjustmentDto
     * @see
     * @since 1.0
     */
    public FinPayPlanAdjustmentDto getMdate(Map<String, Object> param){
        return finPayPlanAdjustmentMapper.getMdate(param);
    }
    
    /**
     * 实现docomplement方法
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public void doComplete(FinPayPlanAdjustmentDto entity) throws IllegalAccessException, InvocationTargetException {
        String type = entity.getOperateType();
        //删除
        if (StringUtil.equals(type, "DELETE")) {
           
        }//新建
        else if (StringUtil.equals(type, "NEW")) {
            List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
            FinCashPlan finCashPlan = new FinCashPlan();
            finCashPlan.setCashId(entity.getCashId());
            List<FinPayPlanAdjustmentDto> list = entity.getSelectedItem1();
            if(list.size() != 0){
                finCashPlan.setMdate(list.get(0).getMdate());
            }
            List<FinCashPlan> cashPlanList = finPayPlanAdjustmentMapper.selectByCashId(entity.getCashId());
            //调用金融产品现金流支付计划组件
            FinCashPlan finCash = new FinCashPlan();
            finCash.setCashId(entity.getCashId());
            if(baseFinProductCashPayPlanService != null){
                baseFinProductCashPayPlanService.deleteByPrimaryKey(finCash);
            }
            long i =1;
            for(FinPayPlanAdjustmentDto dto:list){
                FinCashPlan temp = new FinCashPlan();
                //temp = cashPlanList.get(0);
                BeanUtil.copyProperties(temp, cashPlanList.get(0));
                temp.setCashId(entity.getCashId());
                temp.setCashType(DictCodeList.现金流类型_费用支付.getValue());
                temp.setVdate(dto.getVdate());
                temp.setMdate(dto.getMdate());
                temp.setPayDate(dto.getPayDate());
                temp.setVdateUnadjust(dto.getVdate());
                temp.setMdateUnadjust(dto.getMdate());
                temp.setCashNum(i++);
                finCashPlanList.add(temp);
            }
            if(baseFinProductCashPayPlanService != null){
                baseFinProductCashPayPlanService.insertList(finCashPlanList); 
            }
        }//修改
        else{
            /*List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
            FinCashPlan finCashPlan = new FinCashPlan();
            finCashPlan.setCashId(entity.getCashId());
            List<FinPayPlanAdjustmentDto> list = entity.getSelectedItem();
            if(list.size() != 0 && list.get(0).getPayStatus().equals("已支付")){
                finCashPlan.setMdate(list.get(0).getMdate());
            }
            FinCashPlan finCashPlanCopy = finCashPlanMapper.selectByPrimaryKey(finCashPlan);
            for(FinPayPlanAdjustmentDto dto:list){
                if(dto.getPayStatus().equals("已支付")){
                    finCashPlanCopy.setVdate(dto.getVdate());
                    finCashPlanCopy.setMdate(dto.getMdate());
                    finCashPlanCopy.setPayDate(dto.getPayDate());
                    finCashPlanList.add(finCashPlanCopy);
                }
                else{
                    finCashPlanCopy.setCashId(entity.getCashId());
                    finCashPlanCopy.setCashType(DictCodeList.交易方向_产品付费.getValue());
                    finCashPlanCopy.setVdate(dto.getVdate());
                    finCashPlanCopy.setMdate(dto.getMdate());
                    finCashPlanCopy.setPayDate(dto.getPayDate());
                    finCashPlanList.add(finCashPlanCopy);
                }
            }
            BaseFinProductCashPayPlanDto baseFinProductCashPayPlanDto = new BaseFinProductCashPayPlanDto();
            baseFinProductCashPayPlanDto.setCashId(entity.getCashId());
            baseFinProductCashPayPlanDto.setFinCashPlanList(finCashPlanList);
            if(baseFinProductCashPayPlanService != null){
                baseFinProductCashPayPlanService.update(baseFinProductCashPayPlanDto);
            }*/
        }
    }
    
    /**
     * 
     * 获取所有的付费计划
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<FinPayPlanAdjustmentDto>
     * @see
     * @since 1.0
     */
    public List<FinPayPlanAdjustmentDto> selectForList(Map<String, Object> param){
        //数据字典.交易方向.付费  
        param.put("ps", DictCodeList.交易方向_产品付费.getValue());
        return finPayPlanAdjustmentMapper.selectForPage(param);
    }
    
    /**
     * 
     * 付费规则调整产品代码下拉框 yzg 20191126
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getSelectFinprodId(int page, int length,Map<String, Object> param){
        //数据字典.金融产品类型.理财产品, 数据字典.金融产品类型.理财产品分层
        param.put("args0", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("profitType_02", DictCodeList.收益类型_收益型.getValue());
        param.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        param.put("dealMode_10", DictCodeList.处理模式_周期型.getValue());
        param.put("ISSUE_STATUS_01", DictCodeListExt1.发行状态_发行成功.getValue());
        return super.queryForPageForSelectReturn(page, length, () -> finPayPlanAdjustmentMapper.getSelectFinprodId(param));
        		
    }
    
}
