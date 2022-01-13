package com.joyintech.tams.svreport.chb.saleriden.dao;

import com.joyintech.tams.common.base.entity.ChbSalerIdenInfo;
import com.joyintech.tams.common.base.entity.ChbSalerIdenSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SvrSalerIdenDBInterfMapper {
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<ChbSalerIdenInfo> selectBatchData(@Param("batchDate")String batchDate);
	
	/**
	 * 查询所有已登记的数据
	 * @return
	 */
	List<ChbSalerIdenSub> selectRegSalerIdenData();
	
	int deleteBatchData(@Param("deleleList")List<ChbSalerIdenSub> deleleList);

	/**
	 * 插入数据
	 * @param sub
	 */
	int insertBatchData(ChbSalerIdenSub sub);
}
