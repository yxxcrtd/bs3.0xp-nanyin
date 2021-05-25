package com.joyintech.tams.svreport.chbreport.g06bmonthreport.dao;


import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportG06bDetail;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * G06b风险资本计算表查询Mapper
 *
 * @author WanShiWei
 * @version 1.0 2021年4月19日
 * @see G06bMonthReportQuery001Mapper
 * @since 1.0
 */
@Repository
public interface G06bMonthReportQuery001Mapper {

    /**
     * 根据条件查询画面数据
     */
    List<ChbreportPrdInfoDto> selectAllData(Map<String, Object> map);

    /**
     * 查询表数据
     */
    List<ChbMonthReportG06bDetail> selectG06bMonthReportData(Map<String, Object> map);
}
