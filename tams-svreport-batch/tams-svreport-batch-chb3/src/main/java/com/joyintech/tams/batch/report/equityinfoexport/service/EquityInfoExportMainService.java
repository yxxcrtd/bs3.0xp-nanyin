package com.joyintech.tams.batch.report.equityinfoexport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.equityinfoexport.dao.EquityInfoExportMapper;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;


@Service
public class EquityInfoExportMainService extends AutoSendCBBaseService {

    @Autowired
    private EquityInfoExportCommonService equityInfoExportCommonService;

    @Autowired
    private EquityInfoExportMapper equityInfoExportMapper;
    
    @Autowired
    private EquityInfoRegistExportDealUtil equityInfoRegistExportDealUtil;

    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;
    public void getExportData(){
        setBatchBeanName(BatchBeanName.投资者身份信息登记导出);
        logInfoBatchStart();
        //获取报送信息登记生成记录表中报送处理状态为04_未处理，前台导出的记录
        List<ChbSubinfoBatchLog> listBatchLog = equityInfoExportMapper.getBatchLog();
        for (ChbSubinfoBatchLog batchLog : listBatchLog) {
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_处理中.getValue());
            chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
            //获取生成条件
            Map param = (Map)JSON.parse(batchLog.getGenCondition());
            equityInfoRegistExportDealUtil.dealSearchParam(param);
            long count = equityInfoExportMapper.countExportNum(param);
            equityInfoExportCommonService.export(count,param,batchLog);
        }
        logInfoBatchEnd();
    }
}
