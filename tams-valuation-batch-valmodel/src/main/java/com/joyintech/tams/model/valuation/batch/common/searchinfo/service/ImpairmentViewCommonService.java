package com.joyintech.tams.model.valuation.batch.common.searchinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.valuation.batch.common.searchinfo.dao.ImpairmentViewCommonMapper;
import com.joyintech.tams.model.valuation.batch.common.searchinfo.dto.ViewRatingInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 减值模块视图查询
 */
@Service
public class ImpairmentViewCommonService extends BaseService{
	
	private static final Logger logger = LoggerFactory.getLogger(ImpairmentViewCommonService.class);
	
	@Autowired
	private ImpairmentViewCommonMapper mmpairmentViewCommonMapper;

	/**
	 * 获取资产交易列表
	 * @param params
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ImpairmentDataLoadStep1ReaderDto> selectAssetTradeList(Map<String, Object> params){
		return mmpairmentViewCommonMapper.selectAssetTradeList(params);
	}

	/**
	 * 获取评级信息
	 * @param params
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ViewRatingInfoDto> selectViewRatingInfo(Map<String, Object> params){
		return mmpairmentViewCommonMapper.selectViewRatingInfo(params);
	}

	/**
	 * 查询小于等于查询日最新的评级结果
	 * @param list
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ViewRatingInfoDto> selectViewRatingInfoByList(List<ViewRatingInfoDto> list, Date searchDate){
		return mmpairmentViewCommonMapper.selectViewRatingInfoByList(list, searchDate);
	}

}
