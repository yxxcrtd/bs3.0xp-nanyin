package com.joyintech.tams.batch.svreport.salestransaction.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.salestransaction.service.SvrSalesTransactionDBInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service(value = "salesTransactionImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SalesTransactionImpMainService extends AutoSendCBBaseService{
    
    @Autowired
    private SvrSalesTransactionDBInterf svrSalesTransactionDBInterf;
    
    /**
     * 功能:将维护表中的数据导入到登记表中
     * @param batchDate 跑批日
     * void
     */
    public void generateSalesTransactionSubInfo(Date batchDate){
        svrSalesTransactionDBInterf.generateData(batchDate);
    }
}
