/*
 * 文件名：MstNetAssetQuotationInfoMapper001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.mstrateassetquotationinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.mstrateassetquotationinfo.dto.MstRateAssetQuotationInfo001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 计划类（利率型）资产估值行情信息mapper
 * 
 * @author lby
 * @version 1.0 2020年10月20日
 * @see MstRateAssetQuotationInfo001Mapper
 * @since 1.0
 */
@Repository 
public interface MstRateAssetQuotationInfo001Mapper {
    /**
     * 根据条件查找数据
     *
     * @param param 查询条件
     * @return 场外金融产品估值信息
     */
    List<MstRateAssetQuotationInfo001Dto> selectForPage(Map<String, Object> param);

    List<SuggestDto> getAssetCode(Map<String, Object> map);

    List<SuggestDto> getAssetName(Map<String, Object> map);

    List<SelectOptionDto> getAssetCodeList(Map<String, Object> map);

    MstRateAssetQuotationInfo001Dto getFinProdNameAndFinprodId(Map<String, Object> map);

    List<MstRateAssetQuotationInfo001Dto> selectByIdAndDate(Map<String, Object> map);

    MstRateAssetQuotationInfo001Dto selectMstRateAsset(Map<String, Object> param);

    int update(MstRateAssetQuotationInfo001Dto entity);
}
