package com.joyintech.tams.batch.svreport.monthcollect.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.monthcollect.service.SvrMonthCollectInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service(value = "monthCollectImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class MonthCollectImpMainService extends AutoSendCBBaseService{
    
    @Autowired
    private SvrMonthCollectInterf svrMonthCollectInterf;
    
    /**
     * 功能:每日批
     * @param batchDateStart 跑批日当月第一天
     * @param batchDateEnd 跑批日当月最后一天
     * void
     */
    public void generateSalesTransactionSubInfo(Date batchDate,Date batchDateStart ,Date batchDateEnd){
        svrMonthCollectInterf.generateData(batchDate,batchDateStart,batchDateEnd);
    }

    /**
     *检查整个月汇总是否有值
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    public int checkAllMonth(Date batchDateStart,Date batchDateEnd){
        return svrMonthCollectInterf.checkAllMonth(batchDateStart,batchDateEnd);
    }

    /**
     *检查月末日汇总
     * @param batchDateEnd
     * @return
     */
    public int checkLastDay(Date batchDateEnd){
        return svrMonthCollectInterf.checkLastDay(batchDateEnd);
    }

    /**
     *检查月末日持有
     * @param batchDateEnd
     * @return
     */
    public int checkHold(Date batchDateEnd){
        return svrMonthCollectInterf.checkHold(batchDateEnd);
    }

    /**
     * 检查数据是否已报送
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    public int checkSubmission(Date batchDateStart,Date batchDateEnd){
        return svrMonthCollectInterf.checkSubmission(batchDateStart,batchDateEnd);
    }
}
