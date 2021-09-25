package com.joyintech.tams.valuation.batch.prebatchcheck.processvoucher.dao;

import com.joyintech.tams.valuation.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
