package com.joyintech.tams.svreport.chb.monthreport.dao;

import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dto.SalesTransactionMaintenanceRegist001Dto;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReportDetail;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Repository
public interface SellMonthReportMapper {
//    int deleteByPrimaryKey(String reportuuid);
//
//    int insert(ChbMonthReport record);
//
//    int insertSelective(ChbMonthReport record);
//
    SellMonthReport selectByPrimaryKey(String reportuuid);
    //
    //    int updateByPrimaryKeySelective(ChbMonthReport record);
    //
    //    int updateByPrimaryKey(ChbMonthReport record);
    //
    List<SellMonthReport> searchPage(Map<String,Object> map);
    //手工处理
    int updateInterfSubmitAndSeqBatch(String reportuuid);
    //取消手工处理
    int cancelMannultRegist(String reportuuid);
    //查询报表类型
    String searchReporttype(Map<String,Object> map);
    /**
     * 查询
     * @param param
     * @return
     */
    List<SellMonthReportDetail> setDataList(Map<String, Object> param);
//
//
//    int deleteByTermId(@Param("termid")String termid,@Param("reportType")String reportType,@Param("reportFrequ")String reportFrequ);
//
//    int deleteByModel(ChbMonthReport record);
//    /**
//     * 查询已报送的数据（sendstatus为02和03）
//     * @param termid
//     * @return
//     */
//    List<ChbMonthReport> selectSendDataByTermId(String termid);
//
//
//    /**
//     * 查询已报送的数据（sendstatus为02和03）
//     * @param termid
//     * @return
//     */
//    List<ChbMonthReport> selectSendDataByTermId(@Param("termid")String termid,@Param("OrgCode")String OrgCode,@Param("sendStatus")String sendStatus);
//
//
//    /**
//     * 查询已报送的数据（sendstatus为02和03）
//     * @param termid
//     * @return
//     */
//    List<ChbMonthReport> selectDataByTermIdForAsset(@Param("termid")String termid,@Param("OrgCode")String OrgCode);
//
//
//    List<ChbMonthReport> selectUnSendDataByTermId(String termid);
//
//    ChbMonthReport selectByTermId(String termid);
//
//    /**
//     * 功能:查询报送日期当天报送的银行月度报表记录
//     * @since 1.7.0
//     * @version 1.7.0
//     * @author：weiyuqiang 2019年4月19日
//     * @param termid
//     * @return
//     * List<ChbMonthReport>
//     */
//    List<ChbMonthReport> selectMonthReportBySendDate(@Param("sendDate")Date sendDate);
//    int insertReportHis(@Param("registList")List<SellMonthReport> registList);
//    int deleteLastSubmitRegist(@Param("registList")List<SellMonthReport> registList);
//    /**
//     * 取消手工处理时将履历表中的数据移回登记表
//     * @see
//     * @since 1.0
//     */
//    void insertReportDataFromHis(@Param("registList")List<SellMonthReport> registList);
//    /**
//     * 取消手工处理时将履历表中的数据移回登记表
//     * @see
//     * @since 1.0
//     */
//    void deleteReportDataHis(@Param("registList")List<SellMonthReport> registList);
//
//    /**
//     *
//    * 根据主键查询.
//    * @author liuyu
//    * @time 2019年6月26日 上午10:25:46
//    * @param keyList
//    * @return
//     */
//    List<ChbMonthReport> searchByKeys(List<String> keyList);
//
//    List<String> findProductByName(Map<String,Object> map);
}