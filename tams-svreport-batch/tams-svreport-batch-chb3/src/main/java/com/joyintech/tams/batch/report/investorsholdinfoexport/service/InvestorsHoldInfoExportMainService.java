package com.joyintech.tams.batch.report.investorsholdinfoexport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.equitybusiinfoexport.dao.EquityBusiInfoExportMapper;
import com.joyintech.tams.batch.report.investorsholdinfoexport.dao.InvestorsHoldInfoExportMapper;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;


@Service
public class InvestorsHoldInfoExportMainService extends AutoSendCBBaseService {

    @Autowired
    private InvestorsHoldInfoExportCommonService InvestorsHoldInfoExportCommonService;

    @Autowired
    private InvestorsHoldInfoExportMapper investorsHoldInfoExportMapper;
    
    @Autowired
    private InvestorsHoldInfoExportDealUtil investorsHoldInfoExportDealUtil;
    
    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;
    public void getExportData(){
        setBatchBeanName(BatchBeanName.投资者持有信息登记导出);
        logInfoBatchStart();
        List<ChbSubinfoBatchLog> listBatchLog = investorsHoldInfoExportMapper.getBatchLog();
        for (ChbSubinfoBatchLog batchLog : listBatchLog) {
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_处理中.getValue());
            chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
            Map param = (Map)JSON.parse(batchLog.getGenCondition());
            investorsHoldInfoExportDealUtil.dealSearchParam(param);
            long count = investorsHoldInfoExportMapper.countExportNum(param);
            InvestorsHoldInfoExportCommonService.export(count,param,batchLog);
        }
        logInfoBatchEnd();
    }
}
