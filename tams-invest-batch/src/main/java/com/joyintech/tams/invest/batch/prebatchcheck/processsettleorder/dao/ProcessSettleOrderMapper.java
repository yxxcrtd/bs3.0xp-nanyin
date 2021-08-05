package com.joyintech.tams.invest.batch.prebatchcheck.processsettleorder.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

/**
 * 批前检查-流程中清算指令Mapper
 * @author yantianyuan
 * 创建日期：2021-05-13
 *
 */
@Repository
public interface ProcessSettleOrderMapper {
	/**
	 * 获取流程中的清算指令信息
	 * @param map
	 * @return
	 */
	List<SysPrebatchResultDetail> selectProcessSettleOrderList(Map<String,Object> map);
}
