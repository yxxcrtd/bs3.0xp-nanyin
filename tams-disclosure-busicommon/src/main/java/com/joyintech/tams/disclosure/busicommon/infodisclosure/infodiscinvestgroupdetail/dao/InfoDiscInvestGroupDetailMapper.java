package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscinvestgroupdetail.dao;

import java.util.HashMap;
import java.util.List;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscinvestgroupdetail.entity.InfoDiscInvestGroupDetailEntity;

public interface InfoDiscInvestGroupDetailMapper {

	/**
	 * 查询各产品总投资估值
	 * 
	 * @param para
	 * @return
	 */
	List<InfoDiscInvestGroupDetailEntity> selectProdInvestList(HashMap<String, String> para);

	/**
	 * 查询各类资产的投资估值-资产一级分类
	 * 
	 * @param para
	 * @return
	 */
	List<InfoDiscInvestGroupDetailEntity> selectAssetInvestListByAssetclassOne(HashMap<String, String> para);

	/**
	 * 存入投资组合详细表
	 * 
	 * @param infoDiscInvestGroupDetail
	 * @return
	 */
	int insertInfoDiscInvestGroupDetail(List<InfoDiscInvestGroupDetailEntity> infoDiscInvestGroupDetail);

	/**
	 * 删除当天已产生投资组合详细表
	 * 
	 * @param para
	 * @return
	 */
	int deleteInfoDiscInvestGroupDetail(HashMap<String, String> para);

	/**
	 * 查询最新的投资比例
	 * 
	 * @param para
	 * @return
	 */
	InfoDiscInvestGroupDetailEntity selectProdInvestAdjRateList(HashMap<String, String> para);

}
