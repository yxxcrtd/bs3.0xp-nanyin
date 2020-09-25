/*
 * 文件名：FinIntPayDateAdjustmentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finintpaydateadjustment.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.base.core.BaseFinProductCashPayPlanService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashPayPlanDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.messageadjust.finintpaydateadjustment.dao.FinIntPayDateAdjustmentMapper;
import com.joyintech.service.product.messageadjust.finintpaydateadjustment.dto.FinIntPayDateAdjustmentDto;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.dao.FinPayPlanAdjustmentMapper;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustmentDto;

/**
 * 信息调整管理_付息日调整 
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月23日
 * @see FinIntPayDateAdjustmentService
 * @since 1.0
 */
@Service("backFinIntPayDateAdjustmentService")
public class FinIntPayDateAdjustmentService extends BaseService<FinIntPayDateAdjustmentDto>{
    
    /**
     * 
     */
    @Autowired
    private FinCashPlanMapper finCashPlanMapper;
    
    /**
     * 
     */
    @Autowired
    private FinPayPlanAdjustmentMapper finPayPlanAdjustmentMapper;
    
    /**
     * 金融产品现金流支付组件
     */
    @Autowired
    private BaseFinProductCashPayPlanService baseFinProductCashPayPlanService;
    
    /**
     * 付息日调整mapper
     */
    @Autowired
    private FinIntPayDateAdjustmentMapper finIntPayDateAdjustmentMapper;
    
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCashId(Map<String, Object> param){
        //数据字典缓存.金融产品类型.枚举值.理财产品
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        //数据字典缓存.收益类型类型.枚举值.预期收益型
        param.put("profitType", DictCodeList.收益类型_收益型.getValue());
        param.put("profitType2", DictCodeList.收益类型_货币型.getValue());
        param.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        return finIntPayDateAdjustmentMapper.getCashId(param);
    }
    
    /**
     * 
     * 查询
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam Map<String, Object>
     * @return Page<FinIntPayDateAdjustmentDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<FinIntPayDateAdjustmentDto> selectForPage(Map<String, Object> pageParam)
        throws Exception {
            pageParam.put("no", DictCodeList.是否_否.getValue());
            pageParam.put("yes", DictCodeList.是否_是.getValue());
            pageParam.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
            pageParam.put("ps", DictCodeList.交易方向_中间付息.getValue());
            pageParam.put("ps2", DictCodeList.交易方向_产品现金分红.getValue());
            //add by wm 增加货币型产品到期增加交易类型 start 2019-07-11
            pageParam.put("ps4", DictCodeList.交易方向_赎回收益确认.getValue());
          //add by wm 增加货币型产品到期增加交易类型 end 2019-07-11
            pageParam.put("ps3", DictCodeList.交易方向_产品红利转投.getValue());
            //return super.queryForPage(page, length, () -> finIntPayDateAdjustmentMapper.selectForPage(pageParam));
            return finIntPayDateAdjustmentMapper.selectForPage(pageParam);
    }
    
    /**
     * 
     * 获取付息日计划
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<FinIntPayDateAdjustmentDto>
     * @see
     * @since 1.0
     */
    public List<FinIntPayDateAdjustmentDto> selectForList(Map<String, Object> pageParam){
        pageParam.put("no", DictCodeList.是否_否.getValue());
        pageParam.put("yes", DictCodeList.是否_是.getValue());
        pageParam.put("ps", DictCodeList.交易方向_中间付息.getValue());
        return finIntPayDateAdjustmentMapper.selectForPage(pageParam);
    }
    
    /**
     * 实现docomplement方法
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public void doComplete(FinIntPayDateAdjustmentDto entity) throws IllegalAccessException, InvocationTargetException {
        String type = entity.getOperateType();
        //删除
        if (StringUtil.equals(type, "DELETE")) {
           
        }//新建
        else if (StringUtil.equals(type, "NEW")) {
            List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
            FinCashPlan finCashPlanTemp = new FinCashPlan();
            finCashPlanTemp.setCashId(entity.getCashId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("cashId", entity.getCashId());
            List<FinCashPlan> finCashPlanCopy = finIntPayDateAdjustmentMapper.getFinCashPlan(param);//finCashPlanMapper.selectByPrimaryKey(finCashPlan);
            if(baseFinProductCashPayPlanService != null){
                baseFinProductCashPayPlanService.deleteByPrimaryKey(finCashPlanTemp);
            }
            long i = 1;
            for(FinIntPayDateAdjustmentDto dto:entity.getSelectedItem1()){
                FinCashPlan finCashPlan = new FinCashPlan();
                BeanUtil.copyProperties(finCashPlan, finCashPlanCopy.get(0));
                /*if(dto.getPayStatus().equals("已支付")){
                    finCashPlanCopy.setVdate(dto.getVdate());
                    finCashPlanCopy.setMdate(dto.getMdate());
                    finCashPlanCopy.setPayDate(dto.getPayDate());
                    finCashPlanList.add(finCashPlanCopy);
                }else{
                    finCashPlanCopy.setCashId(entity.getCashId());
                    finCashPlanCopy.setVdate(dto.getVdate());
                    finCashPlanCopy.setMdate(dto.getMdate());
                    finCashPlanCopy.setPayDate(dto.getPayDate());
                    finCashPlanList.add(finCashPlanCopy);
                }*/
                finCashPlan.setMdate(dto.getMdate());
                finCashPlan.setVdate(dto.getVdate());
                finCashPlan.setMdateUnadjust(dto.getMdate());
                finCashPlan.setVdateUnadjust(dto.getVdate());
                finCashPlan.setCashNum(i++);
                finCashPlan.setPayDate(dto.getPayDate());
                finCashPlanList.add(finCashPlan);
                
            }
            if(baseFinProductCashPayPlanService != null){
                baseFinProductCashPayPlanService.insertList(finCashPlanList); 
            }
        }//修改
        else{
            List<FinCashPlan> finCashPlanList = new ArrayList<FinCashPlan>();
            FinCashPlan finCashPlanTemp = new FinCashPlan();
            finCashPlanTemp.setCashId(entity.getCashId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("cashId", entity.getCashId());
            List<FinCashPlan> finCashPlanCopy = finIntPayDateAdjustmentMapper.getFinCashPlan(param);
            for(FinIntPayDateAdjustmentDto dto:entity.getSelectedItem1()){
                FinCashPlan finCashPlan = new FinCashPlan();
                BeanUtil.copyProperties(finCashPlan, finCashPlanCopy.get(0));
                finCashPlan.setMdate(dto.getMdate());
                finCashPlan.setVdate(dto.getVdate());
                finCashPlan.setPayDate(dto.getPayDate());
                finCashPlanList.add(finCashPlan);
            }
            BaseFinProductCashPayPlanDto baseFinProductCashPayPlanDto = new BaseFinProductCashPayPlanDto();
            baseFinProductCashPayPlanDto.setCashId(entity.getCashId());
            baseFinProductCashPayPlanDto.setFinCashPlanList(finCashPlanList);
            if(baseFinProductCashPayPlanService != null){
                baseFinProductCashPayPlanService.update(baseFinProductCashPayPlanDto);
            }
        }
    }
    
    /**
     * 
     * 获得起息日与到期日
     * <功能详细描述>
     * @param param Map<String, Object> 
     * @return FinIntPayDateAdjustmentDto
     * @see
     * @since 1.0
     */
    public FinProduct getVdateMdate(Map<String, Object> param){
        return finIntPayDateAdjustmentMapper.getVdateMdate(param);
    }

    /**
     * 
     * 获得产品是否永续
     * <功能详细描述>
     * @param param Map<String, Object> 
     * @return String
     * @see
     * @since 1.0
     */
	public String queryProductIsSus(Map<String, Object> param) {
		return finIntPayDateAdjustmentMapper.queryProductIsSus(param);
	}
}
