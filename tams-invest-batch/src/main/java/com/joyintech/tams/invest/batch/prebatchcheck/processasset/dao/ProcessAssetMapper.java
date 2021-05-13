package com.joyintech.tams.invest.batch.prebatchcheck.processasset.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

/**
 * 流程中资产定义信息查询Mapper
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Repository
public interface ProcessAssetMapper {
	
	/**
	 * 获取流程中资产定义信息
	 * @param map
	 * @return
	 */
	List<SysPrebatchResultDetail> selectProcessAssetList(Map<String,Object> map);
}
