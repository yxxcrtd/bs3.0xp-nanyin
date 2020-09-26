package com.joyintech.tams.svreport.interf.chb.ptlvalution.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbPtlValution;

public interface ChbPtlValutionInterfMapper {
	void selectByCdate(Date batchTime);
	
	int deleteByCdate(Date batchTime);
	
	int insertBatchList(@Param("listChbPtlValution")List<ChbPtlValution> listChbPtlValution);
	
	int insert(ChbPtlValution chbPtlValution);
	
	List<Date> isUpdated(Date batchTime);
	
}