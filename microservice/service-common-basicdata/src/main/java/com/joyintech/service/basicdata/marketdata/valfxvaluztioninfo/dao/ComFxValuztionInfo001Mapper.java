package com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo001Dto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Repository("serviceComFxValuztionInfo001Mapper")
public interface ComFxValuztionInfo001Mapper {

    List<ComFxValuztionInfo001Dto> selectList(Map<String, Object> params);

    List<SelectOptionDto> getFinprodId2(Map<String, Object> params);
    
    /**
     * 保存，已存在则更新
     * 
     * @param insertList 需要保存的数据
     * @see
     * @since 1.0
     */
    int mergeInfoList(@Param("newDataList")List<ComFxValuztionInfo001Dto> insertList);

}
