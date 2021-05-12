/*
 * 文件名：MstYieldCurveInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.mstshareprice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;

/**
 * 股票行情管理mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ComSharePrice001Mapper
 * @since 1.0
 */

@Repository
public interface ComSharePrice001Mapper {
    
	ComSharePriceDto getLatelyShareById(Map<String,Object> key);
    
    List<ComSharePriceDto> select(Map<String,Object> key);

    List<SelectOptionDto> getFinprodIdForAdd(Map<String, String> map);
    
    /**
     * 保存，已存在则更新
     * 
     * @param insertList 需要保存的数据
     * @see
     * @since 1.0
     */
    int mergeInfoList(@Param("newDataList")List<ComSharePriceDto> insertList);
}