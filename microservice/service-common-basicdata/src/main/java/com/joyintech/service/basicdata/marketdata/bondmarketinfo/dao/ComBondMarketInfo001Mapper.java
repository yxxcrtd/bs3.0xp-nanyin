
package com.joyintech.service.basicdata.marketdata.bondmarketinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Mapper
 * @since 1.0
 */
@Repository(value="serviceComBondMarketInfo001Mapper")
public interface ComBondMarketInfo001Mapper {

	/**
	 * 根据条件查找数据
     * 债券每日行情查询
     * @param ValBondMarketInfo001SearchDto
     * @return ComBondMarketInfo001Dto
     * @see    
     * @since 1.0
     */
    List<ComBondMarketInfo001Dto> queryPageList(Map<String, Object> params);

    /**
     * 新建  add by zwq 2021/01/28
     */
    int insert(ComBondMarketInfo001Dto dto);

    /**
     * 删除   add by zwq 2021/01/28
     */
    int deleteByPrimaryKey(ComBondMarketInfo001Dto dto);

    /**
     * 更新   add by zwq 2021/01/28
     */
    int updateByPrimaryKey(ComBondMarketInfo001Dto dto);

    /**
     * 查询资产代码 add by zwq 2021/01/28
     */
    List<SelectOptionDto> getFinProductList(Map<String,Object> map);

    /**
     * 查询符合条件的dto数据
     */
    List<ComBondMarketInfo001Dto> queryDtoByKey(Map<String,Object> map);
    
    /**
     * 保存，已存在则更新
     * 
     * @param insertList 需要保存的数据
     * @see
     * @since 1.0
     */
    int mergeInfoList(@Param("newDataList")List<ComBondMarketInfo001Dto> insertList);

    /**
     * 根据产品代码查找市场代码
     * @param finprodId
     * @return
     */
    String queryFinprodMarketIdByFinprodId(@Param("finprodId") String finprodId);
}
