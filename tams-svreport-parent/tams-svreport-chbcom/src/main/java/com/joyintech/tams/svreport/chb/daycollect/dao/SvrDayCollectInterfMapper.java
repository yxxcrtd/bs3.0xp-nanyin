package com.joyintech.tams.svreport.chb.daycollect.dao;

import com.joyintech.tams.svreport.chb.daycollect.dto.ChbDayCollect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SvrDayCollectInterfMapper {
	
	/**
	 * 查询所有从业人员信息数据
	 * @return
	 */
	List<ChbDayCollect> selectBatchData(@Param("batchDate")String batchDate);
	
	int deleteBatchData(@Param("batchDate")String batchDate);
    /**
     * 插入数据
     * @param sub
     */
    int insertBatchData(ChbDayCollect sub);

	/**
	 * check当月有无当日交易数据
	 * @param batchDate
	 * @return
	 */
	int checkTransaction(@Param("batchDate")String batchDate);

	/**
	 * check身份信息
	 * @param batchDate
	 * @return
	 */
	int checkIdentity(@Param("batchDate")String batchDate);
}
