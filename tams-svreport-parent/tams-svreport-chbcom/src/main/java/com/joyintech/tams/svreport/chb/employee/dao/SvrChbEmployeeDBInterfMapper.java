package com.joyintech.tams.svreport.chb.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbEmployeeInfo;
import com.joyintech.tams.common.base.entity.ChbEmployeeInfoSub;


@Repository
public interface SvrChbEmployeeDBInterfMapper {
	
	/**
	 * 查询所有从业人员信息数据
	 * @return
	 */
	List<ChbEmployeeInfo> selectBatchData(@Param("batchDate")String batchDate);
	
	/**
	 * 查询所有已登记的从业人员信息数据
	 * @return
	 */
	List<ChbEmployeeInfoSub> selectRegEmployeeData();
	
	int deleteBatchData(@Param("deleleList")List<ChbEmployeeInfoSub> deleleList);
    /**
     * 增量插入数据
     * @param subList
     */
    int insertChbEmployeeSubData(@Param("subList")List<ChbEmployeeInfoSub> subList);
}
