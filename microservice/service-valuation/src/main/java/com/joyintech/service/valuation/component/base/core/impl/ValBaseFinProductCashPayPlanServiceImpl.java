/*
 * 文件名：BaseMstFinCashPlanServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.component.base.core.ValBaseFinProductCashPayPlanService;
import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductCashPayPlanDto;
import com.joyintech.service.valuation.component.base.core.impl.dao.ValBaseFinProductCashPayPlanMapper;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;


/**
 * 金融产品现金流支付计划组件
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see ValBaseFinProductCashPayPlanServiceImpl
 * @since 1.0
 */
@Service
public class ValBaseFinProductCashPayPlanServiceImpl  implements ValBaseFinProductCashPayPlanService{

    /**
     * 金融产品现金流支付计划组件mapper
     */
    @Autowired
    private ValBaseFinProductCashPayPlanMapper baseFinProductCashPayPlanMapper;
    /**
     * 自动生成mapper
     */
    @Autowired
    private FinCashPlanMapper finCashPlanMapper;

    /**
     * 删除金融产品现金流支付计划信息
     */
    @Override
    public boolean deleteByPrimaryKey(FinCashPlan finCashPlan) {
        return baseFinProductCashPayPlanMapper.deleteByPrimaryKey(finCashPlan)==1?true:false;
    }

    /**
     * 新增金融产品现金流支付计划信息
     */
    @Override
    public boolean insertList(List<FinCashPlan> list) {
        if(list!=null&&list.size()>0){
            for(FinCashPlan dto:list){
                //新增数据前根据现金流代码和结束日删除已有数据
               // finCashPlanMapper.deleteByPrimaryKey(dto);
                finCashPlanMapper.insert(dto);
            }
            return true;
        }
        return false;
    }

    /**
     * 修改金融产品现金流支付计划信息
     */
    @Override
    public boolean update(ValBaseFinProductCashPayPlanDto dto) {
        List<FinCashPlan> list =dto.getFinCashPlanList();
        String cashId =dto.getCashId();
        if(StringUtil.isNotEmpty(cashId)&&StringUtil.isNotEmpty(list)){
            FinCashPlan entity =new FinCashPlan();
            entity.setCashId(cashId);
            entity.setMdate(dto.getMdate());
            entity.setBranch(dto.getBranch());
            entity.setFinprodId(dto.getFinprodId());
               if(this.deleteByPrimaryKey(entity)){
               this.insertList(list);
               return true;
               }
        }
        return false;
        
    }

    /**
     * 查询金融产品现金流支付计划信息
     */
    @Override
    public List<FinCashPlan> select(FinCashPlan finCashPlan) {
        return baseFinProductCashPayPlanMapper.selectDetail(finCashPlan);
    }

    //新增数据前根据现金流代码删除已有数据
    @Override
    public Boolean deleteByCashId(String cashId) {
        return baseFinProductCashPayPlanMapper.deleteByCashId(cashId);
    }

}
