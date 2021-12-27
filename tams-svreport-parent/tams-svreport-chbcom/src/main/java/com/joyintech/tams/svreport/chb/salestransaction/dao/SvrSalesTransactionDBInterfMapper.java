package com.joyintech.tams.svreport.chb.salestransaction.dao;

import java.util.List;

import com.joyintech.tams.common.base.entity.ChbSalesTransactionInfo;
import com.joyintech.tams.common.base.entity.ChbSalesTransactionInfoSub;
import com.joyintech.tams.svreport.chb.daycollect.dto.ChbDayCollect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SvrSalesTransactionDBInterfMapper {
	
	/**
	 * 查询所有从业人员信息数据
	 * @return
	 */
	List<ChbSalesTransactionInfo> selectBatchData(@Param("batchDate")String batchDate);
	
	/**
	 * 查询所有已登记的从业人员信息数据
	 * @return
	 */
	List<ChbSalesTransactionInfoSub> selectRegSalesTransactionData();
	
	int deleteBatchData(@Param("deleleList")List<ChbSalesTransactionInfoSub> deleleList);

	/**
	 * 插入数据
	 * @param sub
	 */
	int insertBatchData(ChbSalesTransactionInfoSub sub);
}
