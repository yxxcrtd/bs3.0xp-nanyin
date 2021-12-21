package com.joyintech.tams.svreport.chb.identityrepair.dao;

import com.joyintech.tams.common.base.entity.ChbSalesTransactionInfo;
import com.joyintech.tams.common.base.entity.ChbSalesTransactionInfoSub;
import com.joyintech.tams.svreport.chb.daycollect.dto.ChbDayCollect;
import com.joyintech.tams.svreport.chb.identityrepair.dto.ChbIdentityRepair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SvrIdentityRepairInterfMapper {
	
	/**
	 * 查询所有从业人员信息数据
	 * @return
	 */
	List<ChbIdentityRepair> selectBatchData(@Param("batchDate")String batchDate);

	/**
	 * 判断跑批日数据的存在性
	 * @param batchDate
	 * @return
	 */
	int idenCheck(@Param("batchDate")String batchDate);

	/**
	 * 更新数据SUB
	 * @param
	 */
	int updataSub(@Param("agencyCode")String agencyCode,@Param("registBankCode")String registBankCode,@Param("fundAccPlace")String fundAccPlace,@Param("orgIndentifyMark")String orgIndentifyMark);

	/**
	 * 更新数据CHECK
	 * @param
	 */
	int updataCheck(@Param("agencyCode")String agencyCode,@Param("registBankCode")String registBankCode,@Param("fundAccPlace")String fundAccPlace,@Param("orgIndentifyMark")String orgIndentifyMark);
}
