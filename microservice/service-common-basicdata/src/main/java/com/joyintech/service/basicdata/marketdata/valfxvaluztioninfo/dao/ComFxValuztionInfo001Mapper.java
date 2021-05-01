package com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo001Dto;
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
     * 查询新增数据主键是否重复
     * @param param 产品list
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );

    /**
     * 查询新增数据产品代码是否存在
     * @param param
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFinprod( Map<String,Object> param) ;
    
    /**
     * 验证交易编号与外汇类型是否匹配
     * @param param
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFinprodAndFinprodType2( Map<String,Object> param) ;
    
    /**
     * 验证交易编号与外汇类型是否匹配
     * @param param
     * @return list
     * @see
     * @since 1.0
     */

    String  checkCcy( Map<String,Object> param) ;

    /**
     * 查询
     * @param params
     * @return
     */
    List<SelectOptionDto> getFinprodIdFromJx(Map<String, Object> params);

}
