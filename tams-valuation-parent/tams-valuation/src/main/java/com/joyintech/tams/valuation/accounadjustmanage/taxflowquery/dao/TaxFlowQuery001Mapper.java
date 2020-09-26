/*
 * 文件名：TaxFlowQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.dto.TaxFlowQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
/**
 * 计税流水号查询mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年9月14日
 * @see TaxFlowQuery001Mapper
 * @since 1.0
 */
@Repository
public interface TaxFlowQuery001Mapper {
	/**
	 * 查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 */
     List <TaxFlowQuery001Dto> queryList(TaxFlowQuery001Dto dto);
     
     /**
      * 
      * 所属账套下拉框初始化
      * @return List<ValAssetbook001Dto>  
      * @see
      * @since 1.0
      */
     List<ValAssetbook001Dto> getBooksetId();
}
