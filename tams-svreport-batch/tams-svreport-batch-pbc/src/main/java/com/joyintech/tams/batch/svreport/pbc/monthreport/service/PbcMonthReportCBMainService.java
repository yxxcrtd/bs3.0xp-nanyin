package com.joyintech.tams.batch.svreport.pbc.monthreport.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.pbc.report.service.PbcMonthReportRegistBatchService;


@Service
@Transactional(rollbackFor = Exception.class)
public class PbcMonthReportCBMainService  extends AutoSendCBBaseService{

    
    @Autowired
    private PbcMonthReportRegistBatchService pbcMonthReportRegistBatchService;

    /**
     * 功能:生成人行报表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param batchDate 跑批日期
     * @param prodIdList 产品id
     * @param isEdit 是否是修改的数据
     * @throws Exception 
     */
    public void generateReport(Date batchDate,List<String> prodIdList,boolean isEdit) throws Exception{
        pbcMonthReportRegistBatchService.generateReport(batchDate, prodIdList, isEdit);
    }
}
