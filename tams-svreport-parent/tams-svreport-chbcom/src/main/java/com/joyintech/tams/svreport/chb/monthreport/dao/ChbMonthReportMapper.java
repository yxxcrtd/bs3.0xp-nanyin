package com.joyintech.tams.svreport.chb.monthreport.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;

public interface ChbMonthReportMapper {
    int deleteByPrimaryKey(String reportuuid);

    int insert(ChbMonthReport record);

    int insertSelective(ChbMonthReport record);

    ChbMonthReport selectByPrimaryKey(String reportuuid);

    int updateByPrimaryKeySelective(ChbMonthReport record);

    int updateByPrimaryKey(ChbMonthReport record);
    
    List<ChbMonthReport> searchPage(Map<String,Object> map);
    
    void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<ChbMonthReport> registList);
    
    Integer cancelMannultRegist(List<ChbMonthReport> list);
   
    
    /**
	 * 功能:批量删除当月银行月度报表资产端数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月16日
     * @param termid 期数
     * @param reportType 报表类型 PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @param reportFrequ 报表频率 M-月
     * @return 
     * int
     */
    int deleteByTermId(@Param("termid")String termid,@Param("reportType")String reportType,@Param("reportFrequ")String reportFrequ);
    
    int deleteByModel(ChbMonthReport record);
    /**
     * 查询已报送的数据（sendstatus为02和03）
     * @param termid
     * @return
     */
    List<ChbMonthReport> selectSendDataByTermId(String termid);
    
    
    /**
     * 查询已报送的数据（sendstatus为02和03）
     * @param termid
     * @return
     */
    List<ChbMonthReport> selectSendDataByTermId(@Param("termid")String termid,@Param("OrgCode")String OrgCode,@Param("sendStatus")String sendStatus);
    
    
    /**
     * 查询已报送的数据（sendstatus为02和03）
     * @param termid
     * @return
     */
    List<ChbMonthReport> selectDataByTermIdForAsset(@Param("termid")String termid,@Param("OrgCode")String OrgCode);
    
    
    List<ChbMonthReport> selectUnSendDataByTermId(String termid);
    
    ChbMonthReport selectByTermId(String termid);
    
    /**
     * 功能:查询报送日期当天报送的银行月度报表记录
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param termid
     * @return 
     * List<ChbMonthReport>
     */
    List<ChbMonthReport> selectMonthReportBySendDate(@Param("sendDate")Date sendDate);
    int insertReportHis(@Param("registList")List<ChbMonthReport> registList);
    int deleteLastSubmitRegist(@Param("registList")List<ChbMonthReport> registList);
    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @param list
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<ChbMonthReport> registList);
    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @param list
     * @see
     * @since 1.0
     */
    void deleteReportDataHis(@Param("registList")List<ChbMonthReport> registList);
    
    /**
     * 
    * 根据主键查询.
    * @author liuyu
    * @time 2019年6月26日 上午10:25:46
    * @param keyList
    * @return
     */
    List<ChbMonthReport> searchByKeys(List<String> keyList);
}