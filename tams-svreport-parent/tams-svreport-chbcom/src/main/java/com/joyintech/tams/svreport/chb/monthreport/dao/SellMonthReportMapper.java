package com.joyintech.tams.svreport.chb.monthreport.dao;

import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReportDetail;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Repository
public interface SellMonthReportMapper {
    //根据主键查询
    List<SellMonthReport> selectByPrimaryKey(String reportuuid);
    //分页查询
    List<SellMonthReport> searchPage(Map<String,Object> map);
    //手工处理
    int updateInterfSubmitAndSeqBatch(@Param("termid")String termid);
    //取消手工处理
    int cancelMannultRegist(@Param("termid")String termid);
    /**
     * 查询
     * @param param
     * @return
     */
    List<SellMonthReportDetail> setDataList(Map<String, Object> param);

    /**
     * 根据机构代码查询机构名称
     * @param orgCode
     * @return
     */
    String selectOrgName(@Param("orgCode")String orgCode);
}