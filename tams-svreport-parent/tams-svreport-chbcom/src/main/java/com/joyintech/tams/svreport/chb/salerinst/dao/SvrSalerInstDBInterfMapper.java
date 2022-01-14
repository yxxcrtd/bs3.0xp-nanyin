package com.joyintech.tams.svreport.chb.salerinst.dao;

import com.joyintech.tams.common.base.entity.ChbSalerInstInfo;
import com.joyintech.tams.common.base.entity.ChbSalerInstSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SvrSalerInstDBInterfMapper {
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<ChbSalerInstInfo> selectBatchData(@Param("batchDate")String batchDate);
	
	/**
	 * 查询所有已登记的数据
	 * @return
	 */
	List<ChbSalerInstSub> selectRegSalerInstData();
	
	int deleteBatchData(@Param("deleleList")List<ChbSalerInstSub> deleleList);

	/**
	 * 插入数据
	 * @param sub
	 */
	int insertBatchData(ChbSalerInstSub sub);
}
