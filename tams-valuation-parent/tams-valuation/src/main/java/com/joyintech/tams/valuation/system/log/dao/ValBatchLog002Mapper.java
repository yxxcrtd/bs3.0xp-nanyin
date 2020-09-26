/*
 * 文件名：ValBatchLog002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ww1
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.system.log.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.system.log.dto.BokBatchMarketwarningInfoDto;


/**
 * 批处理日志Mapper
 * 
 * @author ww1
 * @version 1.0 2018年2月23日
 * @see ValBatchLog002Mapper
 * @since 1.0
 */
@Repository
public interface ValBatchLog002Mapper {
	int selectWarn(@Param("seqNo")String seqNo,@Param("taskId")String taskId, @Param("isWarn")String isWarn);

	List<BokBatchMarketwarningInfoDto> findWarnLogDetail(Map<String, Object> pageParam);
}
