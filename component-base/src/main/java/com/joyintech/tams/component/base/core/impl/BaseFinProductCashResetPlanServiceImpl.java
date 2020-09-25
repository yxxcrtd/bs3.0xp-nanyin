/*
 * 文件名：BaseFinProductCashResetPlanServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinCashResetPlanMapper;
import com.joyintech.tams.common.base.entity.FinCashResetPlan;
import com.joyintech.tams.component.base.core.BaseFinProductCashResetPlanService;

/**
 * 利率重置计划组件
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月18日
 * @see BaseFinProductCashResetPlanServiceImpl
 * @since 1.0
 */
@Service
public class BaseFinProductCashResetPlanServiceImpl implements BaseFinProductCashResetPlanService{

	@Autowired
	private FinCashResetPlanMapper finCashResetPlanMapper;
	
	
	/**
	 * 批量插入利率重置计划
	 * {@inheritDoc}
	 */
    @Override
    public boolean insertList(List<FinCashResetPlan> list) {
        if(list!=null&&list.size()>0){
            for(FinCashResetPlan dto:list){
            	finCashResetPlanMapper.insert(dto);
            }
            return true;
        }
        return false;
    }
}
