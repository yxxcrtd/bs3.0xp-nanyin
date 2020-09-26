package com.joyintech.tams.batch.svreport.chb.updateoperatemode.dao;

import org.springframework.stereotype.Repository;

/**
 * 产品运作模式更新mapper
 * @author zhengxiaolian
 * @version 1.0 2019年12月23日
 * @see UpdateOperateModeMapper
 * @since 1.0
 */
@Repository
public interface UpdateOperateModeMapper {

	/**
	 * 更新产品运作模式
	 */
	int updateOperateMode();
}
