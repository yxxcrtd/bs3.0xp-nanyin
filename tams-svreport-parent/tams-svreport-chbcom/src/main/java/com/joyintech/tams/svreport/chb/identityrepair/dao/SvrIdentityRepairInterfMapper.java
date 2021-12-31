package com.joyintech.tams.svreport.chb.identityrepair.dao;

import com.joyintech.tams.svreport.chb.identityrepair.dto.ChbIdentityRepair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SvrIdentityRepairInterfMapper {

	/**
	 * 从明细数据进行身份信息的修复
	 * @return
	 */
	List<ChbIdentityRepair> selectBatchData(@Param("lastRepairTime")String lastRepairTime);

	/**
	 * 身份变更的情况下，查询最近一条的变更前数据--非识别标识变更
	 * @return
	 */
	List<ChbIdentityRepair> selectBatchDataForChg(@Param("batchDate")String batchDate);

	/**
	 * 身份变更的情况下，查询最近一条的变更前数据--识别标识变更
	 * @return
	 */
	List<ChbIdentityRepair> selectBatchDataForChgByIde(@Param("batchDate")String batchDate);

	/**
	 * 判断跑批日数据的存在性
	 * @param lastRepairTime
	 * @return
	 */
	int idenCheck(@Param("lastRepairTime")String lastRepairTime);

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

	/**
	 * 更新系统最后一次修复身份的参数
	 * @param
	 * @return
	 */
	int updataLastRepairTime();

	/**
	 * 获取最后一次修复时间
	 * @return
	 */
	String getLastRepairTime();
}
