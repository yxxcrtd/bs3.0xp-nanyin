/*
 * 文件名：BalTableGenerate001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.balancetablegeneration.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.balancetablegeneration.dto.BalTableGenerate001Dto;
import com.joyintech.service.valuation.accounadjustmanage.balancetablegeneration.dto.BalTableGenerate001HandleDto;
import com.joyintech.service.valuation.accounadjustmanage.balancetablegeneration.dto.BalTableGenerate001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValTablelist001Dto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;

/**
 * 余额表生成Mapper 
 * 
 * @author 王波
 * @version 1.0 2018年8月21日
 * @see BalTableGenerate001Mapper
 * @since 1.0
 */
@Repository(value="serviceBalTableGenerate001Mapper")
public interface BalTableGenerate001Mapper {

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
    List<BalTableGenerate001Dto> queryPageList(BalTableGenerate001SearchDto dto);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<ValProductInfoQuery001Dto> getFinprodId(Map<String,Object> map);
    
    /**
     * 
     * 手动点击
     * @param params 参数    
     * @return List<ValVoucherQuery001Dto>
     * @see
     * @since 1.0
     */
    List<BalTableGenerate001HandleDto> queryValbalTableStatus(BalTableGenerate001SearchDto dto);
    
       
    /**
     * 
     * 查询估值表和余额表是否有数据
     * @param params 参数    
     * @return Map<String,Object> map
     * @see
     * @since 1.0
     */
    int queryValbalTableStatusCount (Map<String,Object> map);
    
    /**
     * 
     * 删除估值余额表数据
     * @param params 参数    
     * @return List<ValTableGenerate001SearchDto>
     * @see
     * @since 1.0
     */
    void deleteValbalTableStatus (BalTableGenerate001SearchDto dto);
    
    /**
     * 
     * 删除估值日志表数据
     * @param params 参数    
     * @return List<ValTableGenerate001SearchDto>
     * @see
     * @since 1.0
     */
    void deleteValbalTableLog (BalTableGenerate001SearchDto dto);
    
    /**
     * 
     * 根据产品代码查询账套代码
     * @param params 参数    
     * @return List<ValTableGenerate001SearchDto>
     * @see
     * @since 1.0
     */
    ValAssetbook001Dto queryBooksetId(@Param("prodId") String prodId);
    
    /**
     * 
     * 查询excel文件信息
     * @param params 参数    
     * @return List<ValTableGenerate001SearchDto>
     * @see
     * @since 1.0
     */
    ValValuationStatementQuery001Dto queryExcelInfo(ValValuationStatementQuery001Dto dto);
}
