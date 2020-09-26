package com.joyintech.tams.svreport.chb.monthreport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreport.dto.PrdSaletargetDealDetail;

public interface ChbMonthReportDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(ChbMonthReportDetail record);

    int insertSelective(ChbMonthReportDetail record);

    ChbMonthReportDetail selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(ChbMonthReportDetail record);

    int updateByPrimaryKey(ChbMonthReportDetail record);
    
    List<ChbMonthReportDetail> selectByModel(Map<String,Object> map);

    /**
     * 功能:批量插入明细表
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月1日
     * @param assetMonthReportDetailList
     * @return 
     * int
     */
	int insertBatch(@Param("insertList")List<ChbMonthReportDetail> assetMonthReportDetailList);
	
	/**
	 * 功能:批量删除当月银行月度报表资产端数据
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月12日
	 * @param termid 期数
     * @param reportType 报表类型 PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
	 * @return 
	 * int
	 */
	int deleteByTermId(@Param("termid")String termid,@Param("reportType")String reportType,@Param("reportuuid")String reportuuid);
    
    /**
     * 功能:批量删除当月银行月度报表资产端数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月12日
     * @param termid
     * @param reporttype 产品端 资产端
     * @param reportuuid 例外的外键 已报送的不删
     * @return 
     * int
     */
    int delete(@Param("termid")String termid,@Param("reporttype")String reporttype,@Param("reportuuid")String reportuuid);
    
    PrdSaletargetDealDetail getInvestorHoldings(PrdSaletargetDealDetail qmodel);
    
   List<String> selectAllPropertyCode();
}