package com.joyintech.tams.common.svreport.seq.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 描述：获取数据库sequence值
 * @作者：niexiaohui
 * @创建时间：2017年1月4日
 * @修改记录：移入joyin-base包
 */
@Repository
public interface SequenceMapper {
	/**
	 * 获取序号
	 * @param sequenceName
	 * @return
	 */
	int getSequenceVal(@Param("sequenceName")String sequenceName);
}
