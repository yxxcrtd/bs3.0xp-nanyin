package com.joyintech.tams.svreport.chb.salerrewpena.dao;

import com.joyintech.tams.common.base.entity.ChbSalerRewPenaInfo;
import com.joyintech.tams.common.base.entity.ChbSalerRewPenaSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SvrSalerRewPenaDBInterfMapper {
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<ChbSalerRewPenaInfo> selectBatchData(@Param("batchDate")String batchDate);
	
	/**
	 * 查询所有已登记的数据
	 * @return
	 */
	List<ChbSalerRewPenaSub> selectRegSalerRewPenaData();
	
	int deleteBatchData(@Param("deleleList")List<ChbSalerRewPenaSub> deleleList);

	/**
	 * 插入数据
	 * @param sub
	 */
	int insertBatchData(ChbSalerRewPenaSub sub);
}
