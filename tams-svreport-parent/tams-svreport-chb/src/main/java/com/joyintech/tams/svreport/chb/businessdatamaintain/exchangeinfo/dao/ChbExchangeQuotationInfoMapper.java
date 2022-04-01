/*
 * 文件名：MstYieldCurveInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.exchangeinfo.dao;

import com.joyintech.tams.svreport.chb.businessdatamaintain.calendarinfo.dto.ChbCalendarInfo001Dto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.exchangeinfo.dto.ChbExchangeQuotationInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 汇率行情mapper 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ChbExchangeQuotationInfoMapper
 * @since 1.0
 */
@Repository
public interface ChbExchangeQuotationInfoMapper {

    /**
     * 查询汇率行情信息
     * 
     * @param param 查询参数
     * @return 返回查询结果
     * @see
     * @since 1.0
     */
    List<ChbExchangeQuotationInfoDto> select(Map<String, Object> param);
    
    /**
     * 查询新增数据主键是否重复
     * @param param 产品list
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );

    /**
     * 查询新增数据主键是否重复
     * @param dto
     * @return list
     */

    Integer getMstExchargeQuoteByKey(ChbExchangeQuotationInfoDto dto);

    /**
     *
     * 导入（新建）
     * <功能详细描述>
     * @param listN 导入数据
     * @return 导入结果
     */
    int insertExchargeQuoteInfo(@Param("listN")List<ChbExchangeQuotationInfoDto> listN);

    /**
     *
     * 导入（修改）
     * <功能详细描述>
     * @param listA 导入数据
     * @return 导入结果
     * @see
     * @since 1.0
     */
    int updateExchargeQuoteInfo(@Param("listA")List<ChbExchangeQuotationInfoDto> listA);

    /**
     * 更新数据类型
     * @param param
     */
    int updateStatus(Map<String,Object> param );

}
