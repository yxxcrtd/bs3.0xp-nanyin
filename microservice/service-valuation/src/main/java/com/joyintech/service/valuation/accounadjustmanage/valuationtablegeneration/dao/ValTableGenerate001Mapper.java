/*
 * 文件名：ValTableGenerate001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.ValTableGenerate001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.ValTableGenerate001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValTablelist001Dto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;
import com.joyintech.tams.common.base.entity.BokValbalTableLog;
import com.joyintech.tams.common.base.entity.BokValbalTableStatus;

/**
 * 凭证查询Mapper
 * @author weipeng
 * @version 1.0 2018年5月5日
 * @see ValTableGenerate001Mapper
 * @since 1.0
 */
@Repository(value="serviceValTableGenerate001Mapper")
public interface ValTableGenerate001Mapper {
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
    List<ValTableGenerate001Dto> queryPageList(ValTableGenerate001SearchDto dto);
    
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
    List<BokValbalTableStatus> queryValbalTableStatus(ValTableGenerate001SearchDto dto);
    
    /**
     * 
     * 估值约状态表数据入库
     * @param params 参数    
     * @return BokValbalTableStatus
     * @see
     * @since 1.0
     */
    int insertBokValbalTableStatus(BokValbalTableStatus bokValbalTableStatus);
    
    /**
     * 
     * 估值余额日志表数据入库
     * @param params 参数    
     * @return BokValbalTableLog
     * @see
     * @since 1.0
     */
    int insertBokValbalTableLog(BokValbalTableLog bokValbalTableLog);
    
    /**
     * 
     * 查询估值表和余额表是否有数据
     * @param params 参数    
     * @return Map<String,Object> map
     * @see
     * @since 1.0
     */
    int queryValbalTableStatusCount (ValTableGenerate001SearchDto dto);
    
    /**
     * 
     * 删除估值余额表数据
     * @param params 参数    
     * @return List<ValTableGenerate001SearchDto>
     * @see
     * @since 1.0
     */
    void deleteValbalTableStatus (ValTableGenerate001SearchDto dto);
    
    /**
     * 
     * 删除估值日志表数据
     * @param params 参数    
     * @return List<ValTableGenerate001SearchDto>
     * @see
     * @since 1.0
     */
    void deleteValbalTableLog (ValTableGenerate001SearchDto dto);
    
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
    
    /**
     * 
     * 查询估值表和余额表是否有数据
     * @param params 参数    
     * @return Map<String,Object> map
     * @see
     * @since 1.0
     */
    int queryBooksetIdCount (ValTableGenerate001SearchDto dto);
    
    /**
     * 查询产品对应下拉框
     * @param booksetType 账套类型
     * @param param 参数
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBooksetList(@Param("booksetType") String booksetType);
    
//    /**
//     * 查询账务汇总表
//     * @param params 参数
//     * @return List<BokStatBokPositionDto>
//     * @see
//     * @since 1.0
//     */
//    List<BokStatBokPositionDto> queryBokStatBokPosition(Map<String,Object> params);
}
