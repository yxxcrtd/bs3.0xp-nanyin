package com.joyintech.tams.batch.svreport.chbsalesstaffmanage.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.saleriden.service.SvrSalerIdenDBInterf;
import com.joyintech.tams.svreport.chb.salerinst.service.SvrSalerInstDBInterf;
import com.joyintech.tams.svreport.chb.salerrewpena.service.SvrSalerRewPenaDBInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service(value = "SalesStaffManageImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SalesStaffManageImpMainService extends AutoSendCBBaseService{
    
    @Autowired
    private SvrSalerInstDBInterf svrSalerInstDBInterf;
    @Autowired
    private SvrSalerIdenDBInterf svrSalerIdenDBInterf;
    @Autowired
    private SvrSalerRewPenaDBInterf svrSalerRewPenaDBInterf;
    
    /**
     * 总部或分支机构信息
     * @param batchDate 跑批日
     * void
     */
    public void generateSalerInst(Date batchDate){
        svrSalerInstDBInterf.generateData(batchDate);
    }

    /**
     * 销售人员身份信息
     * @param batchDate 跑批日
     * void
     */
    public void generateSalerIden(Date batchDate){
        svrSalerIdenDBInterf.generateData(batchDate);
    }

    /**
     * 销售人员奖惩投诉记录
     * @param batchDate 跑批日
     * void
     */
    public void generateSalerRewPena(Date batchDate){
        svrSalerRewPenaDBInterf.generateData(batchDate);
    }
}
