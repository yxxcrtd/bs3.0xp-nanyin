package com.joyintech.tams.svreport.chb.agentprdinfo.dao;

import com.joyintech.tams.common.base.entity.ChbAgentPrdInfo;
import com.joyintech.tams.common.base.entity.ChbAgentPrdSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SvrAgentPrdInfoDBInterfMapper {
	
	/**
	 * 查询所有信息数据
	 * @return
	 */
	List<ChbAgentPrdInfo> selectBatchData(@Param("batchDate")String batchDate);
	
	/**
	 * 查询所有已登记的信息数据
	 * @return
	 */
	List<ChbAgentPrdSub> selectRegAgentPrdData();
	
	int deleteBatchData(@Param("deleleList")List<ChbAgentPrdSub> deleleList);

	/**
	 * 插入数据
	 * @param sub
	 */
	int insertBatchData(ChbAgentPrdSub sub);
}
