package com.joyintech.service.basicdata.marketdata.fundmarketinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * 数据库操作接口
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ComFundMarketInfo001mapper
 * @since 1.0
 */
@Repository(value="serviceComFundMarketInfo001mapper")
public interface ComFundMarketInfo001mapper {


	/**
	 * 查询结果，加载表格 
	 * @param params
	 * @return ComFundMarketInfo001dto
	 */
    List<ComFundMarketInfo001dto> tableLoad(Map<String, Object> params);

	/**
	 * 删除数据
	 */
	int deleteByPrimaryKey(ComFundMarketInfo001dto dto);

	/**
	 * 新建数据
	 */
	int saveNewDto(ComFundMarketInfo001dto dto);

	/**
	 * 更新数据
	 */
	int updateByPrimaryKey(ComFundMarketInfo001dto dto);

	/**
	 * 查询符合条件的dto数据
	 */
	List<ComFundMarketInfo001dto> queryDtoByKey(Map<String,Object> map);

	/**
	 * 查询基金代码
	 */
	List<SelectOptionDto> getFinProductList(Map<String,Object> map);

    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> map);

	/**
	 * 根据产品代码查找市场代码
	 * @param finprodId
	 * @return
	 */
	String queryFinprodMarketIdByFinprodId(@Param("finprodId") String finprodId);

    List<ComFundMarketInfo001dto> getAssetInfo(Map<String, Object> param);
}
