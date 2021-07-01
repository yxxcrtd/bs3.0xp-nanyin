/*
 * 文件名：ValVoucherQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.tams.valuation.psbc.bankvoucherquery.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dao.ValVoucherQuery001Mapper;
import com.joyintech.tams.valuation.psbc.bankvoucherquery.dto.BankValVoucherQuery001Dto;
import com.joyintech.tams.valuation.psbc.bankvoucherquery.dto.BankValVoucherQuery001SearchDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 凭证查询Mapper
 * @author weipeng
 * @version 1.0 2018年5月5日
 * @see ValVoucherQuery001Mapper
 * @since 1.0
 */
@Repository
public interface BankValVoucherQuery001Mapper {
    
    /**
     * 
     * 所属账套下拉框初始化
     * @return List<ValAssetbook001Dto>  
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBooksetId();
    
	/**
     * 
     * 所属账套下拉框初始化
     * @return List<ValAssetbook001Dto>  
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getTableId();
     
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValVoucherQuery001Dto>
     * @see
     * @since 1.0
     */
    List<BankValVoucherQuery001Dto> queryPageList(BankValVoucherQuery001SearchDto dto);
}
