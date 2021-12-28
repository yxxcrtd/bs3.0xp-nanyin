package com.joyintech.tams.batch.svreport.chbagentmanage.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.agentinstitution.service.SvrAgentInstitutionDBInterf;
import com.joyintech.tams.svreport.chb.agentprdinfo.service.SvrAgentPrdInfoDBInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service(value = "AgentManageImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class AgentManageImpMainService extends AutoSendCBBaseService{
    
    @Autowired
    private SvrAgentInstitutionDBInterf svrAgentInstitutionDBInterf;
    @Autowired
    private SvrAgentPrdInfoDBInterf svrAgentPrdInfoDBInterf;
    
    /**
     * 代理销售机构信息
     * @param batchDate 跑批日
     * void
     */
    public void generateInstitution(Date batchDate){
        svrAgentInstitutionDBInterf.generateData(batchDate);
    }

    /**
     * 委托销售产品编码信息
     * @param batchDate 跑批日
     * void
     */
    public void generatePrdInfo(Date batchDate){
        svrAgentPrdInfoDBInterf.generateData(batchDate);
    }
}
