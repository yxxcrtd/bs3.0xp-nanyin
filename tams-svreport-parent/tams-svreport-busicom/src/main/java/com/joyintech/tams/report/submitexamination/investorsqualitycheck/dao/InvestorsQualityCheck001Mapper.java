package com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSubSum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;


/**
 * 投资者质量核查Dao
 * @author hpp
 *
 */
@Repository
public interface InvestorsQualityCheck001Mapper {
	/**
	 * 查询左侧-核查项合格率信息
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchInvestorsPassRate(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --证件类型
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchTableZJLX(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --证件匹配
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchTableZJPP(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --及时性
	 * @param param
	 * @return
	 */
	List<InvestorsQualityCheck001Dto> searchTableJSX(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --产品状态合格率
	 * @param param 参数
	 * @return 产品状态合格率
	 */
	List<InvestorsQualityCheck001Dto> searchCPZTErr(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --产品状态合格率
	 * @param param 参数
	 * @return 产品状态合格率
	 */
	List<InvestorsQualityCheck001Dto> searchCPZTCalc(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --投资者数量匹配合格率
	 * @param param 参数
	 * @return 产品状态合格率
	 */
	List<InvestorsQualityCheck001Dto> searchSLPPAll(Map<String, Object> param);

	/**
	 * 查询右侧-不合格数据的详细信息
	 * --投资者数量匹配合格率
	 * @param param 参数
	 * @return 产品状态合格率
	 */
	List<InvestorsQualityCheck001Dto> searchSLPPErr(Map<String, Object> param);

	/**
	 * 获取上次汇总时间
	 * @return
	 */
	String getLastSummaryTime();


	/**
	 * 根据持有日期获取投资者持有产品汇总信息
	 * @param holdDate 持有日期
	 * @return
	 */
	List<ChbInvestholdInfoSubSum> queryChbInvestholdInfoSubSumByHoldDate(@Param("holdDate") Date holdDate);

	/**
	 * 删除数据
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	int deleteChbInvestholdInfoSubSum(@Param("startDate")Date startDate, @Param("endDate")Date endDate);

	/**
	 * 批量插入投资者持有产品汇总表
	 * @param dataList
	 * @return
	 */
	int insertChbInvestholdInfoSubSumBatch(List<ChbInvestholdInfoSubSum> dataList);

}
