/*
 * 文件名：BaseFinProductOpenPlanServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinFloatAccrualScheMapper;
import com.joyintech.tams.common.base.dao.FinOpenPlanMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.base.core.BaseFinProductOpenPlanService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductOpenPlanDto;
import com.joyintech.tams.component.base.core.impl.dao.BaseFinProductOpenPlanMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.util.StringUtil;

/**
 * 开放计划组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see BaseFinProductOpenPlanServiceImpl
 * @since 1.0
 */
@Service
public class BaseFinProductOpenPlanServiceImpl implements BaseFinProductOpenPlanService{
    /**
     * 
     */
    @Autowired
    private BaseFinProductOpenPlanMapper baseFinProductOpenPlanMapper;
    
    @Autowired
    private FinOpenPlanMapper finOpenPlanMapper;
    
    @Autowired
    private FinProductMapper finProductMapper;
    
    @Autowired
    private FinFloatAccrualScheMapper finFloatAccrualScheMapper;
    
    /**
     * 新建开放计划
     */
    @Override
    public boolean insert(List<FinOpenPlan> finOpenPlanList) {
        int i = 0;
        if(finOpenPlanList!=null && finOpenPlanList.size()>0){
            for(FinOpenPlan baseFinProductOpenPlan:finOpenPlanList){
                finOpenPlanMapper.insert(baseFinProductOpenPlan);
                i++;
            }
        }
        return i>0 && i==finOpenPlanList.size()?true:false;
    }

    /**
     * 删除开放计划
     */
    @Override
    public boolean deleteByFinOpenPlan(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto) {
        return baseFinProductOpenPlanMapper.deleteByFinOpenPlan(baseFinProductOpenPlanDto)>0?true:false;
    }

    /**
     * 查询开放计划
     */
    @Override
    public List<FinOpenPlan> selectByFinOpenPlan(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto) {
        return baseFinProductOpenPlanMapper.selectByFinOpenPlan(baseFinProductOpenPlanDto);
    }

    /**
     * 修改开放计划
     */
    @Override
    public boolean updataFinOpenPlan(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto) {
        int i = 0;
        baseFinProductOpenPlanMapper.deleteByFinOpenPlan(baseFinProductOpenPlanDto);
        if(baseFinProductOpenPlanDto.getFinOpenPlanList().size()>0){
            for(FinOpenPlan baseFinProductOpenPlan:baseFinProductOpenPlanDto.getFinOpenPlanList()){
                
                baseFinProductOpenPlanMapper.insert(baseFinProductOpenPlan);
                i++;
            }
        }
        return i>0 && i==baseFinProductOpenPlanDto.getFinOpenPlanList().size()?true:false;
    }

    /**
     * 修改浮动管理费计划
     */
    @Override
    public boolean updateFinFloatAccrualSche(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto, List<FinOpenPlan> finOpenPlanList) {
        String finprodId = finOpenPlanList.get(0).getFinprodId();
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
        if (DictCodeList.收益类型_收益型.getValue().equals(finProduct.getProfitType())) {
        	return false;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("FINPROD_ID", finprodId);
        map.put("CASH_TYPE_2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        //add by zhangJin 20191018 start 修复BUG:若为子产品，现金流代码返回多个
        //判断当前产品为母/子产品
        map.put("FINPROD_TYPE", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        if(StringUtil.isNotEmpty(finProduct.getpFinprodId())) {
            map.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_理财子产品.getValue());
        }
        //add by zhangJin 20191018 end 修复BUG:若为子产品，现金流代码返回多个
        //查询浮动管理费的现金流代码
        String cashId = baseFinProductOpenPlanMapper.selectfinCashInfo(map);
        //add by wm 2019-08-18 当净值型产品没有挂浮动管理费时，无需更新浮动管理费计划 start
        if(StringUtil.isEmpty(cashId)){
        	return false;
        }
        //add by wm 2019-08-18 当净值型产品没有挂浮动管理费时，无需更新浮动管理费计划 end
        //根据现金流代码删除现有的浮动管理费计划
        map.put("cashId", cashId);
        baseFinProductOpenPlanMapper.deleteFinFloatAccrualSche(map);
        List<FinFloatAccrualSche> llistFinFloatAccrualSche = new ArrayList<FinFloatAccrualSche>();
        //定义计提结束日
        Date mDate = null;
        List<FinFloatCountingRule> listRule = baseFinProductOpenPlanMapper.selectFinFloatCountingRule(map);
        if (listRule != null && listRule.size() > 0) {
        	mDate = listRule.get(listRule.size() - 1).geteAccrDate();
        }
        
        if (DictCodeList.收益类型_净值型.getValue().equals(finProduct.getProfitType())) {
        	//add by yujiaping 2020-12-11 TISHKBS-1671【开放计划调整】开放计划调整后生成的浮动管理费计提计划错误start
        	finOpenPlanList.sort((x, y) -> x.getOpenNdate().compareTo(y.getOpenNdate()));
        	//add by yujiaping 2020-12-11 TISHKBS-1671【开放计划调整】开放计划调整后生成的浮动管理费计提计划错误end
        	for (int i = 0; i < finOpenPlanList.size(); i++) {
        		FinFloatAccrualSche sche = new FinFloatAccrualSche();
        		FinOpenPlan open = finOpenPlanList.get(i);
        		sche.setCashId(cashId);
        		sche.setAccrDate(open.getOpenNdate());
        		if (i == 0) {
        			sche.setAccrStrDate(DateUtils.addDays(finProduct.getVdate(), -1));
        		} else {
        			sche.setAccrStrDate(finOpenPlanList.get(i - 1).getOpenNdate());
        		}
        		sche.setAccrEndDate(open.getOpenNdate());
        		sche.setValDate(open.getOpenNdate());
        		llistFinFloatAccrualSche.add(sche);
        	}
        } else if (DictCodeList.收益类型_货币型.getValue().equals(finProduct.getProfitType())) {
        	//add by yujiaping 2020-12-11 TISHKBS-1671【开放计划调整】开放计划调整后生成的浮动管理费计提计划错误start
        	finOpenPlanList.sort((x, y) -> x.getOpenDateEnd().compareTo(y.getOpenDateEnd()));
        	//add by yujiaping 2020-12-11 TISHKBS-1671【开放计划调整】开放计划调整后生成的浮动管理费计提计划错误end
        	for (int i = 0; i < finOpenPlanList.size(); i++) {
        		FinFloatAccrualSche sche = new FinFloatAccrualSche();
        		FinOpenPlan open = finOpenPlanList.get(i);
        		sche.setCashId(cashId);
        		sche.setAccrDate(open.getOpenDateEnd());
        		if (i == 0) {
        			sche.setAccrStrDate(DateUtils.addDays(finProduct.getVdate(), -1));
        		} else {
        			sche.setAccrStrDate(finOpenPlanList.get(i - 1).getOpenDateEnd());
        		}
        		sche.setAccrEndDate(open.getOpenDateEnd());
        		sche.setValDate(open.getOpenDateEnd());
        		llistFinFloatAccrualSche.add(sche);
        	}
        }

        llistFinFloatAccrualSche.sort((o1, o2) -> o1.getAccrDate().compareTo(o2.getAccrDate()));

        if(llistFinFloatAccrualSche.size() > 0){
            if (mDate != null) {
            	if (mDate.compareTo(llistFinFloatAccrualSche.get(llistFinFloatAccrualSche.size() - 1).getAccrEndDate()) > 0) {
            		FinFloatAccrualSche sche = new FinFloatAccrualSche();
            		FinFloatAccrualSche scheOld = llistFinFloatAccrualSche.get(llistFinFloatAccrualSche.size() - 1);
            		sche.setCashId(cashId);
            		sche.setAccrDate(mDate);
            		sche.setAccrStrDate(scheOld.getAccrEndDate());
            		sche.setAccrEndDate(mDate);
            		sche.setValDate(mDate);
            		llistFinFloatAccrualSche.add(sche);
            	} else {
            		List<FinFloatAccrualSche> newList = new ArrayList<FinFloatAccrualSche>();
            		for(FinFloatAccrualSche sche : llistFinFloatAccrualSche){
            			if (mDate.compareTo(sche.getAccrEndDate()) > 0) {
            				newList.add(sche);
            			} else {
                    		sche.setAccrDate(mDate);
                    		sche.setAccrEndDate(mDate);
                    		sche.setValDate(mDate);
                    		newList.add(sche);
                    		break;
            			}
            		}
                    for(FinFloatAccrualSche sche : newList){
                    	finFloatAccrualScheMapper.insert(sche);
                    }
                    return true;
            	}
            }
            for(FinFloatAccrualSche sche : llistFinFloatAccrualSche){
            	finFloatAccrualScheMapper.insert(sche);
            }
        }
        return true;
    }
}
