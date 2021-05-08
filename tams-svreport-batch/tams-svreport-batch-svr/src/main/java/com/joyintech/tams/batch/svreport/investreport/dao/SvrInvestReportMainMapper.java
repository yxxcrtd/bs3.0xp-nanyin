package com.joyintech.tams.batch.svreport.investreport.dao;

import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.base.entity.MstHolidayKey;
import org.springframework.stereotype.Repository;

/**
 * @author yanya
 * @create 2021-05-05 19:29
 */
@Repository(value="svrInvestReportMainMapper")
public interface SvrInvestReportMainMapper {
    /**
     * 根据指定条件查询上一个工作日
     * @param keykey
     * @return
     */
    MstHoliday getPreMstHolidayByParam(MstHolidayKey key);

}
