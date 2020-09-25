/*
 * 文件名：ValVoucherQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.voucherquery.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.*;
import org.springframework.stereotype.Repository;

/**
 * 凭证查询Mapper
 * @author weipeng
 * @version 1.0 2018年5月5日
 * @see ValVoucherQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValVoucherQuery001Mapper")
public interface ValVoucherQuery001Mapper {
	/**
     * 
     * 所属账套下拉框初始化
     * @return List<ValAssetbook001Dto>  
     * @see
     * @since 1.0
     */
    List<ValAssetbook001Dto> getBooksetId();
    
    /**
     * 业务类别下拉框初始化
     * @return List<ValTablelist001Dto>  
     * @see
     * @since 1.0
     */
    List<ValTablelist001Dto> getBusiClass();
    
    /**
     * 
     * 页面初始化查询列表
     * @param params 参数    
     * @return List<ValVoucherQuery001Dto>
     * @see
     * @since 1.0
     */
    List<ValVoucherQuery001Dto> queryPageList(ValVoucherQuery001SearchDto dto);

    List<ValVoucherQuery002Dto> selectPrintInfo(Map<String, Object> param);
}
