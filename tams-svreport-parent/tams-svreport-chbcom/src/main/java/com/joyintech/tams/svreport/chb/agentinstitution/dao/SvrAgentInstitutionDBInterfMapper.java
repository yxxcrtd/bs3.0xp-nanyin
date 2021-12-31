package com.joyintech.tams.svreport.chb.agentinstitution.dao;

import com.joyintech.tams.common.base.entity.ChbAgentInstitutionInfo;
import com.joyintech.tams.common.base.entity.ChbAgentInstitutionSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SvrAgentInstitutionDBInterfMapper {
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<ChbAgentInstitutionInfo> selectBatchData(@Param("batchDate")String batchDate);
	
	/**
	 * 查询所有已登记的数据
	 * @return
	 */
	List<ChbAgentInstitutionSub> selectRegAgentInstitutionData();
	
	int deleteBatchData(@Param("deleleList")List<ChbAgentInstitutionSub> deleleList);

	/**
	 * 插入数据
	 * @param sub
	 */
	int insertBatchData(ChbAgentInstitutionSub sub);
}
