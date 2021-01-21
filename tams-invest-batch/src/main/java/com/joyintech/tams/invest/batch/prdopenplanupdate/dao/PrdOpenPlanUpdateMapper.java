package com.joyintech.tams.invest.batch.prdopenplanupdate.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashPlanKey;
import com.joyintech.tams.common.base.entity.FinFloatAccrualScheKey;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlanKey;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.AccrDateNeedOtherDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinAnapleroticPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinCashPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinFloatPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinOpenPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.NewFinPayPlanDto;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;

/**
 *  批处理-市场日历接入后更新产品开放计划 DAO
 */
@Repository
public interface PrdOpenPlanUpdateMapper {
	/**
	 * 查询市场日历表的最新的更新日期
	 * @return
	 */
	Date getNewUpdateTime();
	/**
	 * 获取系统参数-市场日历更新联动更新的开发计划日期（年）
	 * @return
	 */
	String getLastUpdateYear();
	/**
	 * 更新系统参数-市场日历更新联动更新的开发计划日期（年）
	 * @param year
	 * @return
	 */
	int updateSysParam(String year);
	
	/**
	 * 查询开放计划开始日大于平台日的需要更新的开放计划
	 * @param paramMap
	 * @return
	 */
	List<PrdOpenPlanUpdateDto> queryOldOpenPlanList(Map<String,Object> paramMap);
	
	/**
	 * 查询分红/付息计划调整后的计算开始日 大于平台日的需要更新的分红/付息计划
	 * @param paramMap
	 * @return
	 */
	List<PrdOpenPlanUpdateDto> queryOldCashPlanList(Map<String,Object> paramMap);
	
	/**
	 * 查询付费计划付费开始日大于平台日的需要更新的付费计划
	 * @param paramMap
	 * @return
	 */
	List<PrdOpenPlanUpdateDto> queryOldPayPlanList(Map<String,Object> paramMap);
	
	/**
	 * 查询业绩报酬计提计划区间开始日大于平台日的需要更新的业绩报酬计提计划
	 * @param paramMap
	 * @return
	 */
	List<PrdOpenPlanUpdateDto> queryOldFloatPlanList(Map<String,Object> paramMap);
	
	/**
	 * 查询回补计划区间开始日大于平台日的需要更新的回补计划
	 * @param paramMap
	 * @return
	 */
	List<PrdOpenPlanUpdateDto> queryOldAnapleroticPlanList(Map<String,Object> paramMap);
	
	
	 int updateOpenPlanByPrimaryKey(NewFinOpenPlanDto record);
	 
	 int updateCashPlanByPrimaryKey(NewFinCashPlanDto record);
	 
	 int updatePayPlanByPrimaryKey(NewFinPayPlanDto record);
	 
	 int updateFloatPlanByPrimaryKey(NewFinFloatPlanDto record);
	 
	 int updateAnapleroticPlanByPrimaryKey(NewFinAnapleroticPlanDto record);
	 
	 
	
	/**
	 * 如果是净值型产品则取净值日，如果是货币产品，则取区间结束日
	 */
	List<Date> findNdateOrEndDateByProdid(String finprodId);
	/**
	 * 根据产品代码查询其他计算浮动管理费计提计划的必须字段(业绩报酬计提规则表)
	 */
	AccrDateNeedOtherDto findOtherNeedDateByProdid(String finprodId);
	/**
	 * 根据cashid删除原浮动管理费计提计划 
	 */
	int deleteFloatAccrualScheByPrimaryKey(FinFloatAccrualScheKey finFloatAccrualScheKey);
	/**
	 * 查询浮动管理费的现金流代码
	 * @param map
	 * @return
	 */
	String selectfinCashInfo(Map<String,Object> map);
	/**
	 * 删除更新前的付息计划
	 * @param dto
	 * @return
	 */
	int deleteOldPayPlans(FinCashPlanKey key);
	
	Date queryMaxDealMdate(@Param("finprodId") String finprodId);
}
