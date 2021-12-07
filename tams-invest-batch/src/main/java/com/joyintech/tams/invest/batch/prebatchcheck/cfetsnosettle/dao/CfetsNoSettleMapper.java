package com.joyintech.tams.invest.batch.prebatchcheck.cfetsnosettle.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

/**
 * 交收管理 mapper
 * @author sjj
 * 创建日期：2021-12-07
 */
@Repository
public interface CfetsNoSettleMapper {
	/**
	 * 查询当天未交收记录
	 * @param map
	 * @return
	 */
	List<SysPrebatchResultDetail> selectAllSettleToDoList(Map<String,Object> map);
}
