package com.joyintech.tams.batch.svreport.chb.transmonthsub.service;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.transmonthreport.service.TransMonthReportRegistBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 功能:中债理财产品转型情况月度统计表批处理入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TransMonthReportMainService extends AutoSendCBBaseService{
    
    @Autowired
    private TransMonthReportRegistBatchService transMonthReportRegistBatchService;
	
    public void genReport(Date executeDate,List<String> prodids,boolean ifTrigger) throws BatchException {
        transMonthReportRegistBatchService.genReport(executeDate, prodids, ifTrigger);
	}


}
