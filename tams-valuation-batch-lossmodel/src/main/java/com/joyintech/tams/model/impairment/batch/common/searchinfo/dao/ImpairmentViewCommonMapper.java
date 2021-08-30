package com.joyintech.tams.model.impairment.batch.common.searchinfo.dao;

import com.joyintech.tams.model.impairment.batch.common.searchinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ModStandardGradeMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ImpairmentViewCommonMapper {
	//获取资产交易列表
	List<ImpairmentDataLoadStep1ReaderDto> selectAssetTradeList(Map<String, Object> params);
	//获取评级信息
	List<ViewRatingInfoDto> selectViewRatingInfo(Map<String, Object> params);
	//查询小于等于查询日最新的评级结果
	List<ViewRatingInfoDto> selectViewRatingInfoByList(@Param("list")List<ViewRatingInfoDto> list,@Param("searchDate") Date searchDate);



	/**
	 *  查询昨日仍然持仓资产
	 *  ps：包含今日到期资产，后续结果不会入库
	 * @param params
	 * @return
	 */
	List<ImpairmentDataLoadStep1ReaderDto> selectAssetInfo(Map<String, Object> params);

	/**
	 * 查询当日资产信息
	 * 无历史持仓，当日买入
	 * @param params
	 * @return
	 */
	List<ImpairmentDataLoadStep1ReaderDto> selectTdyAssetInfo(Map<String, Object> params);

	/**
	 * 查询资产首次买入日
	 * @param params
	 * @return
	 */
	Date queryFirstBuyDate(Map<String, Object> params);

	//获取评级信息映射
	List<ModStandardGradeMapping> selectViewRatingInfoMapping();
}
