package com.joyintech.tams.valuation.report.common.infodisclosure.reportDeal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepReveal;

@Repository("valuationReportDealMapper")
public interface ReportDealMapper {
	/**
    *
    * 删除相同的报表
    * @return count
    * @see
    * @since 1.0
    */
   int updateDataStatus(RepReveal repReveal);
   
   /**
   *
   * 查询相同的报表是否已确认
   * @return boolean
   * @see
   * @since 1.0
   */
   int selectConfirmStatus(RepReveal repReveal);
   
   /**
    * 查询数据
    * @param paraMap
    * @return count
    * @see
    * @since 1.0
    */
   List<RepReveal> selectData(Map<String, Object> paraMap);
}
