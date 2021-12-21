package com.joyintech.tams.batch.svreport.identityrepair.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.identityrepair.service.SvrIdentityRepairInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service(value = "identityRepairImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class IdentityRepairImpMainService extends AutoSendCBBaseService{
    
    @Autowired
    private SvrIdentityRepairInterf svrIdentityRepairInterf;
    
    /**
     * 批处理
     * @param batchDate 跑批日
     * void
     */
    public void generateSalesTransactionSubInfo(Date batchDate){
        svrIdentityRepairInterf.generateData(batchDate);
    }
}
