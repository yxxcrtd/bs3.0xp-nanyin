/*
 * 文件名：FinAssetConfigPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productplanning.asset.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.AssetConfigDetail;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.AssetConfigPlan;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productplanning.asset.controller.FinAssetConfigPlanController;
import com.joyintech.service.product.productplanning.asset.dao.FinAssetConfigPlanMapper;
import com.joyintech.service.product.productplanning.asset.dto.FinAssetClassDto;
import com.joyintech.service.product.productplanning.asset.dto.FinAssetConfigPlanDto;
import com.joyintech.service.product.productplanning.asset.dto.FinTacticConfigOptDto;
import com.joyintech.service.product.productplanning.common.dao.PlanningCommonMapper;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinAssetConfigPlanService
 * @since 1.0
 */
@Service("finAssetConfigPlanService")
public class FinAssetConfigPlanService extends BaseService<FinAssetConfigPlanDto> {

    @Autowired(required = false)
    private PlanningCommonMapper planningCommonMapper;
    
    @Autowired(required = false)
    private FinAssetConfigPlanMapper finAssetConfigPlanMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param page page
     * @param length length
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinAssetConfigPlanDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
    	Page<FinAssetConfigPlanDto> rpage = super.queryForPage(page, length,() -> finAssetConfigPlanMapper.queryAssetConfigPlanList(pageParam));
    	List<FinAssetConfigPlanDto> llist = rpage.getRoot();       
        for (FinAssetConfigPlanDto dto : llist) {
        	Map<String, Object> param = new HashMap<String, Object>(); 
        	param.put("assetConfigCode", dto.getAssetConfigCode());
        	param.put("assetType", dto.getAssetType());
        	List<AssetConfigMonDetail> monList = finAssetConfigPlanMapper.queryAssetConfigMons(param);
        	for (AssetConfigMonDetail mondetail : monList) {
        		Method method;
				try {
					method = dto.getClass().getDeclaredMethod("setCurEndAmt" + mondetail.getAssetMonth(),BigDecimal.class);
	        		method.invoke(dto,mondetail.getCurEndAmt());
					method = dto.getClass().getDeclaredMethod("setCurInvAmt" + mondetail.getAssetMonth(),BigDecimal.class);
	        		method.invoke(dto,mondetail.getCurInvAmt());
				} catch (Exception e) {
					e.printStackTrace();
				} 
        	}
        }
        return rpage;
    }
    
    /**
     * 得到资产配置计划列表
     *
     * @param Map<String, Object> pageParam
     * @return List<FinAssetConfigPlanDto>结果
     * @see
     * @since 1.0
     */
    public List<FinAssetConfigPlanDto> queryAssetConfigPlanList(Map<String, Object> pageParam)
            throws Exception {
        	List<FinAssetConfigPlanDto> llist = finAssetConfigPlanMapper.queryAssetConfigPlanList(pageParam);      
        	for (FinAssetConfigPlanDto dto : llist) {
            	Map<String, Object> param = new HashMap<String, Object>(); 
            	param.put("assetConfigCode", dto.getAssetConfigCode());
            	param.put("assetType", dto.getAssetType());
            	List<AssetConfigMonDetail> monList = finAssetConfigPlanMapper.queryAssetConfigMons(param);
            	for (AssetConfigMonDetail mondetail : monList) {
            		Method method;
    				try {
    					method = dto.getClass().getDeclaredMethod("setCurEndAmt" + mondetail.getAssetMonth(),BigDecimal.class);
    	        		method.invoke(dto,mondetail.getCurEndAmt());
    					method = dto.getClass().getDeclaredMethod("setCurInvAmt" + mondetail.getAssetMonth(),BigDecimal.class);
    	        		method.invoke(dto,mondetail.getCurInvAmt());
    				} catch (Exception e) {
    					e.printStackTrace();
    				} 
            	}
            }
            return llist;
        }
    
    /**
     * 得到持仓
     *
     * @param Map<String, Object> param
     * @return List<AssetConfigMonDetail>结果
     * @see
     * @since 1.0
     */
    public List<AssetConfigMonDetail> getCurEndAmt(Map<String, Object> map) {
        return planningCommonMapper.getCurEndAmt(map);
    }

    /**
     * 调用【资产分类查询】
     *
     * @return List<FinAssetClassDto>结果
     * @see
     * @since 1.0
     */
    public List<FinAssetClassDto> queryAssetClassList() {
        return finAssetConfigPlanMapper.queryAssetClassList();
    }

    /**
     * 得到资产配置计划列表
     *
     * @param Map<String, Object> param
     * @return List<AssetConfigPlan>结果
     * @see
     * @since 1.0
     */
    public List<AssetConfigPlan> queryAssetConfigPlan(Map<String, Object> map) {
        return finAssetConfigPlanMapper.queryAssetConfigPlan(map);
    }

    /**
     * 得到资产分类明细
     *
     * @param Map<String, Object> param
     * @return List<AssetConfigDetail>结果
     * @see
     * @since 1.0
     */
    public List<AssetConfigDetail> queryAssetConfigDetails(Map<String, Object> map) {
        return finAssetConfigPlanMapper.queryAssetConfigDetails(map);
    }

    /**
     * 得到战术配置列表
     *
     * @param Map<String, Object> param
     * @return List<FinTacticConfigOptDto>结果
     * @see
     * @since 1.0
     */
    public List<FinTacticConfigOptDto> queryTactics(Map<String, Object> map) {
    	List<FinTacticConfigOptDto> list = new ArrayList<FinTacticConfigOptDto>();
    	list = finAssetConfigPlanMapper.queryTactics(map);
    	/*FinTacticConfigOptDto dto = new FinTacticConfigOptDto();
    	dto.setAssetType("02");
    	dto.setCorRatio(new BigDecimal(1.2));
    	list.add(dto);
    	dto = new FinTacticConfigOptDto();
    	dto.setAssetType("05");
    	dto.setCorRatio(new BigDecimal(1.5));
    	list.add(dto);*/
       return list;
   }
    
}
