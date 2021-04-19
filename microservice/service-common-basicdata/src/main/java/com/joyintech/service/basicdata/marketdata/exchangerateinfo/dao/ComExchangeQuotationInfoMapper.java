/*
 * 文件名：MstYieldCurveInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.exchangerateinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.basicdata.marketdata.exchangerateinfo.dto.ComExchangeQuotationInfoDto;

/**
 * 汇率行情mapper 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ComExchangeQuotationInfoMapper
 * @since 1.0
 */
@Repository
public interface ComExchangeQuotationInfoMapper {

    /**
     * 查询汇率行情信息
     * 
     * @param param 查询参数
     * @return 返回查询结果
     * @see
     * @since 1.0
     */
    List<ComExchangeQuotationInfoDto> select(Map<String, Object> param);
    
    /**
     * 查询新增数据主键是否重复
     * @param param 产品list
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );

}
