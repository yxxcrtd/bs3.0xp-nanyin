package com.joyintech.tams.model.valuation.batch.common.searchinfo.dao;

import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.valuation.batch.common.searchinfo.dto.ViewRatingInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ValViewCommonMapper {
	//获取资产交易列表
	List<ImpairmentDataLoadStep1ReaderDto> selectAssetTradeList(Map<String, Object> params);
	//获取评级信息
	List<ViewRatingInfoDto> selectViewRatingInfo(Map<String, Object> params);
	//查询小于等于查询日最新的评级结果
	List<ViewRatingInfoDto> selectViewRatingInfoByList(@Param("list") List<ViewRatingInfoDto> list, @Param("searchDate") Date searchDate);
}
