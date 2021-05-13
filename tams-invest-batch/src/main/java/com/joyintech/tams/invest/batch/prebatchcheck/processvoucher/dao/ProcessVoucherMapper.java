package com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

/**
 * 流程中的手工凭证
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Repository
public interface ProcessVoucherMapper {
	
	/**
	 * 获取流程中的手工凭证信息
	 * @param map
	 * @return
	 */
	List<SysPrebatchResultDetail> selectProcessVoucherList(Map<String,Object> map);
}
