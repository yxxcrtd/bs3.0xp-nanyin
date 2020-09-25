package com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.dto.ValBondQuotationInfoDto;

/**
 * 
 * @author wangzehui
 *
 */
@Repository
public interface ValBondQuotationInfoMapper {

	/**
	 * 根据条件查找数据
	 * 
	 * @param pageParam
	 *            查询条件
	 * @return 返回数据库里符合条件的数据
	 */
	List<ValBondQuotationInfoDto> select(Map<String, Object> pageParam);

	/**
	 * 资产代码的联想下拉框
	 * 
	 * @param param
	 *            页面输入的资产代码
	 * @return 结果集
	 */
	List<SuggestDto> getSecIdOption(Map<String, Object> param);

	/**
	 * 资产简称的联想下拉框
	 * 
	 * @return 接轨及
	 */
	List<SelectOptionDto> getFinprodAbbrOption(Map<String, Object> map);


	/**
	 * 通过页面输入的债券代码查询债券简称
	 * 
	 * @param param
	 *            页面输入的资产代码
	 * @return 结果集
	 */
	List<SuggestDto> getFinprodAbbrBySecId(String param);

	/**
	 * 获得债券代码的下拉选择框
	 * 
	 * @param param
	 *            参数
	 * @return 结果集
	 */
	List<SelectOptionDto> getMarketCodeOption(Map<String, Object> param);

	/**
	 * 通过secId获取数据信息
	 * 
	 * @param secId
	 *            secId
	 * @return dto
	 */
	ValBondQuotationInfoDto getBySecId(String secId);

	/**
	 * 通过secId和valueDate获取数据信息
	 * 
	 * @param map
	 *            map
	 * @return dto
	 */
	ValBondQuotationInfoDto selectByKey(Map<String, Object> map);

	/**
	 * 债券简称的联想文本框
	 * 
	 * @param param
	 *            param
	 * @return 结果集
	 */
	List<SuggestDto> getFinProdAbbr(Map<String, Object> param);

	/**
	 * 新建画面的资产代码查询 <功能详细描述>
	 * 
	 * @param param
	 *            param
	 * @return list
	 * @see
	 * @since 1.0
	 */
	List<SuggestDto> getSecIdToNew(Map<String, Object> param);
	
	
	List<SelectOptionDto> getAssetCodeList();

}
