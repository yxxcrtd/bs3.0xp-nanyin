package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepExpectedLog;
import com.joyintech.tams.common.base.entity.RepInstallFile;
import com.joyintech.tams.common.base.entity.RepJob;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;

/**
 * 读取作业信息并生成待处理信息mapper
 * @author wangmin
 * @version 1.0 2018年11月28日
 * @see InfoDisclosureReadAndGenMapper
 * @since 1.0
 */
@Repository("disclosureInfoDisclosureReadAndGenMapper")
public interface InfoDisclosureReadAndGenMapper {
	/**
	 * 获取所有满足条件的作业列表
	 * @param executeDateStr 传入时间
	 * @return 满足条件的列表信息
	 */
	List<RepJob> getJobList(String executeDateStr);
	/**
	 * 通过作业编号查询作业规则
	 * @param jobId 作业编号
	 * @return 作业规则列表
	 */
	List<RepJobRange> getRepJobRangeList(String jobId);
	/**
	 * 通过ruleId查询规则编号
	 * @param ruleId 规则编号
	 * @return
	 */
	RepRuleEntity getRepRuleInfo(String ruleId);
	/**
	 * 通过拼接的条件查询茶品基本信息
	 * @param sqlVal 拼接处的条件
	 * @return 返回列表
	 */
	List<ProdInfoListEntity> getProdList(@Param("sqlVal")String sqlVal, @Param("jobId")String jobId);
	/**
	 * 批量插入批处理组装表
	 * @param revealList 信息list
	 * @return 
	 */
	int insertRepInstallFileBatch(List<RepInstallFile> list);
	/**
	 * 批量插入预期表
	 * @param list
	 * @return
	 */
	int insertRepExceptedLogBatch(List<RepExpectedLog> list);
	/**
	 * 将日志主表更新为D
	 */
	int updateInstallLogD(@Param("jobId") String jobId,@Param("execDate") Date execDate);
	/**
	 * 将日志详情表更新为D
	 */
	int updateInstallLogDetailD(@Param("jobSeq") String jobSeq, @Param("execStatus") String execStatus);
	/**
	 * 查询执行失败的产品列表
	 * @param execStatus 执行失败状态
	 * @param jobSeq  工作seq
	 * @return
	 */
	List<ProdInfoListEntity> getErrorProdList(@Param("execStatus")String execStatus, @Param("jobSeq")String jobSeq);
	
	/**
	 * 查询当天是否有巨额赎回
	 * @param executeDateStr
	 * @return
	 */
	int queryJESHCPCount(@Param("executeDateStr")Date executeDateStr);
	
	/**
	 * 查询当天是否有募集金额上限调整
	 * @param executeDateStr
	 * @return
	 */
	int queryMJJETZCount(@Param("executeDateStr")Date executeDateStr);
}
