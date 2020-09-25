package com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo001Dto;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Repository("serviceValFxValuztionInfo001Mapper")
public interface ValFxValuztionInfo001Mapper {

    List<ValFxValuztionInfo001Dto> selectList(Map<String, Object> params);

    List<SelectOptionDto> getFinprodId2(Map<String, Object> params);
    
    /**
     * 查询新增数据主键是否重复
     * @param list 产品list
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );

    /**
     * 查询新增数据产品代码是否存在
     * @param finprodId 产品代码
     * @param finprodType2 金融产品类型
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFinprod( Map<String,Object> param) ;
    
    /**
     * 验证交易编号与外汇类型是否匹配
     * @param finprodId 产品代码
     * @param finprodType2 金融产品类型
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFinprodAndFinprodType2( Map<String,Object> param) ;
    
    /**
     * 验证交易编号与外汇类型是否匹配
     * @param finprodId 产品代码
     * @param finprodType2 金融产品类型
     * @return list
     * @see
     * @since 1.0
     */

    String  checkCcy( Map<String,Object> param) ;

}
