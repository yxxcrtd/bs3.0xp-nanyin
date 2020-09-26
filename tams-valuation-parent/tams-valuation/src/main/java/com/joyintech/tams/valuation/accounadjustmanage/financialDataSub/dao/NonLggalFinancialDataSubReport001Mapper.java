/*
 * 文件名：NonLggalFinancialDataSubReport001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanwgei1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.financialDataSub.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.accounadjustmanage.financialDataSub.dto.NonLggalFinancialDataSubReport001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
/**
 * 非法人财务数据报送mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wanwgei1
 * @version 1.0 2018年11月20日
 * @see NonLggalFinancialDataSubReport001Mapper
 * @since 1.0
 */
@Repository
public interface NonLggalFinancialDataSubReport001Mapper {
	/**
	 * 查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 */
     List <NonLggalFinancialDataSubReport001Dto> queryList(NonLggalFinancialDataSubReport001Dto dto);
     
     /**
      * 
      * 所属账套下拉框初始化
      * @return List<ValAssetbook001Dto>  
      * @see
      * @since 1.0
      */
     List<ValAssetbook001Dto> getBooksetId();
}
