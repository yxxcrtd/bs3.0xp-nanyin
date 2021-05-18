package com.joyintech.tams.invest.batch.prebatchcheck.processassettrade.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
/**
 * 流程中投资交易查询Mapper
 * @author yantianyuan
 * 创建日期： 2021-05-13
 */
@Repository
public interface ProcessAssetTradeMapper {
	/**
	 * 获取流程中投资交易数据
	 * @param map
	 * @return
	 */
	List<SysPrebatchResultDetail> selectProcessAssetTradeList(Map<String,Object> map);
}