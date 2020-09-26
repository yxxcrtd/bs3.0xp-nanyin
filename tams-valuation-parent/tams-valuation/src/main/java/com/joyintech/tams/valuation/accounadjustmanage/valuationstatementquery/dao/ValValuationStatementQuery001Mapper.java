/*
 * 文件名：ValVoucherQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.valuationstatementquery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;

/**
 * 凭证查询Mapper
 * @author qiangchengxi
 * @version 1.0 2018年5月5日
 * @see ValVoucherQuery001Mapper
 * @since 1.0
 */
@Repository
public interface ValValuationStatementQuery001Mapper {
	/**
     * 
     * 所属账套下拉框初始化
     * @return List<ValAssetbook001Dto>  
     * @see
     * @since 1.0
     */
    List<ValValuationStatementQuery001Dto> getFileType();
    
    /**
     * 业务类别下拉框初始化
     * @return List<ValTablelist001Dto>  
     * @see
     * @since 1.0
     */
    List<ValValuationStatementQuery001Dto> getBusiClass();
    
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValVoucherQuery001Dto>
     * @see
     * @since 1.0
     */
    List<ValValuationStatementQuery001Dto> queryPageList(Map<String, Object> params);
    
    List<ValValuationStatementQuery001Dto> queryFilepath(Map<String, Object> params);
}
