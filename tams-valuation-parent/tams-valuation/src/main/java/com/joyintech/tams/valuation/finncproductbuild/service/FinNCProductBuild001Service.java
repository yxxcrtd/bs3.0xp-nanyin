/*
 * 文件名：FinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年2月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.finncproductbuild.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;
import com.joyintech.tams.valuation.finncproductbuild.dto.ValProductInfoAcctDto;
import com.joyintech.tams.valuation.finncproductbuild.dto.ValProductInfoFeeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 产品信息管理Service
 * @author tanxuan
 * @version 1.0 2020年2月12日
 * @see FinprodTradeQuery001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinNCProductBuild001Service extends CommonExportService<FinNCProductBuild001Dto> implements BaseProcessService<FinNCProductBuild001Dto>{
	
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<FinNCProductBuild001Dto> findPage(Map<String, Object> params) throws Exception {
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/findPage",
                params, FinNCProductBuild001Dto.class);
    }
    
    public FinNCProductBuild001Dto findByFinprodId(Map<String, Object> params) {
  	return restTemplate.postForObject(
              ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/findByFinprodId",
              params, FinNCProductBuild001Dto.class);
  }

    /**
     * 下拉框分页获取产品
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String,Object> map) {
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/getFinprodId",
                map, SelectOptionDto.class);
    }

    /**
     * 根据产品findprodid获取产品详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public FinNCProductBuild001Dto queryProductByfinprodId(Map<String, Object> map) {
    	return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild002/queryProductByfinprodId",
                map, FinNCProductBuild001Dto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoFeeDto> queryFeeList(Map<String, Object> pageParam) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/queryFeeList",
                pageParam, ValProductInfoFeeDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<FinNCProductBuild001Dto> findAll(Map<String, Object> params){
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/findAll",
                params, FinNCProductBuild001Dto.class);
    }

    /**
     * 查询资金账户信息
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoAcctDto> getFinprodAcct(Map<String, Object> map) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/getFinprodAcct",
                map, ValProductInfoAcctDto.class);
    }
    
    /**
     * 查询资金账户名称列表
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctName(Map<String, Object> map) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/getAcctName",
                map, SelectOptionDto.class);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoAcctDto> getSecAccount(Map<String, Object> map) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/getSecAccount",
                map, ValProductInfoAcctDto.class);
    }
    
    /**
     * 查看费用调节 - 分页
     * @param pageParam
     * @return
     * @throws Exception 
     */
	public List<ValProductInfoAcctDto> findBankdesc(Map<String, Object> map) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/findBankdesc",
                map, ValProductInfoAcctDto.class);
    }
    
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList(Map<String, Object> pageParam) {
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/queryfloatFeeList",
                pageParam, ValProductInfoFeeDto.class);
	}

    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList1(Map<String, Object> pageParam) {
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/queryfloatFeeList1",
                pageParam, ValProductInfoFeeDto.class);
	}
	
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList2(Map<String, Object> pageParam) {
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/queryfloatFeeList2",
                pageParam, ValProductInfoFeeDto.class);
	}
	
	/**
     * 查询账套配置表
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
    public List<BokBooksetExt> queryBooksetExt(Map<String, Object> map){
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild002/queryBooksetExt",
                map, BokBooksetExt.class);
    }
    
    /**
     * 通过主键 查询账套配置表
     * <一句话功能简述>
     * @param finprodId
     * @param paramCode
     * @return
     */
	public List<BokBooksetExt>  queryBooksetExtByKey(Map<String, Object> map) {
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild002/queryBooksetExtByKey",
                map, BokBooksetExt.class);
	}
	
    /**
     * 删除check
     * <一句话功能简述>
     * @param finprodId
     * @param paramCode
     * @return
     */
    public List<FinNCProductBuild001Dto> deleteCheck(String finprodId) throws Exception {
  	return restTemplate.postForList(
              ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/deleteCheck",
              finprodId, FinNCProductBuild001Dto.class);
  }
	
	@Override
	public void doComplete(FinNCProductBuild001Dto entity, OperationType type) {
		String action = StringUtil.EMPTY;
	   	 if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	        }
	        else if(OperationType.MODIFY.equals(type)){
	            action = "update";
	        }
	   	  restTemplate.postForObject(
	   	            ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/" + action, entity,
	   	            Integer.class);
	}
	
}
