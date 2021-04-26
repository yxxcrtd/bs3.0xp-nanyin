package com.joyintech.tams.settle.batch.datacreate.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.settle.batch.datacreate.dto.StlDataCreateDto;

import java.util.List;
import java.util.Map;

/**
 * @author 陈卓
 * @date 2020/11/24
 * @see StlDataCreateMapper
 * @since 1.0
 */
@Repository
public interface StlDataCreateMapper {
	
    /**
     * 金融产品交易临时表.交易方向是买入、卖出、正回购、逆回购的相关数据检索
     * @param param 查询参数
     * @return 金融产品交易临时表.交易方向是买入、卖出、正回购、逆回购的检索结果DTO
     */
    List<StlDataCreateDto> selectStlDownFileData(Map<String, Object> param);
    
    /**
     * 查询结算下行数据自动生成对象组合
     * @param param Map<String, Object>
     * @return 结算下行数据自动生成对象组合
     */
    String selectStlDataCreateTargetPortfolio(Map<String, Object> param);
}
