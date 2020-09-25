/*
 * 文件名：BaseMstFinExercisePlanServiceImpl.java
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
import com.joyintech.service.valuation.component.base.core.ValBaseFinProductExercisePlanService;
import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductExercisePlanDto;
import com.joyintech.service.valuation.component.base.core.impl.dao.ValBaseFinProductExercisePlanMapper;
import com.joyintech.tams.common.base.dao.FinExercisePlanMapper;
import com.joyintech.tams.common.base.entity.FinExercisePlan;


/**
 * 金融产品现金流行权计划组件
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see ValBaseFinProductExercisePlanServiceImpl
 * @since 1.0
 */
@Service
public class ValBaseFinProductExercisePlanServiceImpl  implements ValBaseFinProductExercisePlanService{

    /**
     * 金融产品现金流行权计划组件mapper 
     */
    @Autowired
    private ValBaseFinProductExercisePlanMapper baseFinProductExercisePlanMapper;
    /**
     *自动生成mapper
     */
    @Autowired
    private FinExercisePlanMapper finExercisePlanMapper;

    /**
     * 删除金融产品现金流行权计划组件信息
     */
    @Override
    public boolean deleteByKey(FinExercisePlan finExercisePlan) {
        return baseFinProductExercisePlanMapper.deleteByKey(finExercisePlan)==1?true:false;
    }

    /**
     * 新增金融产品现金流行权计划组件信息
     */
    @Override
    public boolean insert(List<FinExercisePlan> list) {
        if(list!=null&&list.size()>0){
            for(FinExercisePlan finExercisePlan:list){
                finExercisePlanMapper.insert(finExercisePlan);
            }
            return true;
        }
        return false;
    }

    /**
     *修改金融产品现金流行权计划组件信息
     */
    @Override
    public boolean update(ValBaseFinProductExercisePlanDto dto) {
        List<FinExercisePlan> list=dto.getFinExercisePlanList();
        String cashId =dto.getCashId();
        if(StringUtil.isNotEmpty(cashId)&&StringUtil.isNotEmpty(list)){
            FinExercisePlan entity =new FinExercisePlan();
            entity.setCashId(cashId);
            entity.setExerciseType(dto.getExerciseType());
            entity.setExerciseDate(dto.getExerciseDate());
            entity.setExerciseStatus(dto.getExerciseStatus());
            entity.setFinprodId(dto.getFinprodId());
            entity.setBranch(dto.getBranch());
            if(this.deleteByKey(entity)){
                this.insert(list);
                return true;
            }
        }
        return false;
       
    }

    /**
     * 查询金融产品现金流行权计划组件信息
     */
    @Override
    public List<FinExercisePlan> select(FinExercisePlan finExercisePlan) {
        return baseFinProductExercisePlanMapper.selectDetail(finExercisePlan);
    }
    

}
