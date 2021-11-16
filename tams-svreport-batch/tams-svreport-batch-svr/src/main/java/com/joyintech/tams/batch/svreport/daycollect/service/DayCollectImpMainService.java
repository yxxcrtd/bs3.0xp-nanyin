package com.joyintech.tams.batch.svreport.daycollect.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.daycollect.service.SvrDayCollectInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service(value = "dayCollectImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class DayCollectImpMainService extends AutoSendCBBaseService{
    
    @Autowired
    private SvrDayCollectInterf svrDayCollectInterf;
    
    /**
     * 功能:每日批
     * @param batchDate 跑批日
     * void
     */
    public void generateSalesTransactionSubInfo(Date batchDate){
        svrDayCollectInterf.generateData(batchDate);
    }

    /**
     * check当月有无当日交易数据
     * @param batchDate 跑批日
     * void
     */
    public int checkTransaction(Date batchDate){
        return svrDayCollectInterf.checkTransaction(batchDate);
    }

    /**
     * check身份信息
     * @param batchDate 跑批日
     * void
     */
    public int checkIdentity(Date batchDate){
        return svrDayCollectInterf.checkIdentity(batchDate);
    }
}
