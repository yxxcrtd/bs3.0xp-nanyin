package com.joyintech.tams.batch.report.equitybusiinfoexport.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.equitybusiinfoexport.dao.EquityBusiInfoExportMapper;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class EquityBusiInfoExportMainService extends AutoSendCBBaseService {

    @Autowired
    private EquityBusiInfoExportCommonService equityBusiInfoExportCommonService;

    @Autowired
    private EquityBusiInfoExportMapper equityBusiInfoExportMapper;

    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;
    public void getExportData(){
        setBatchBeanName(BatchBeanName.投资者明细信息登记导出);
        logInfoBatchStart();
        List<ChbSubinfoBatchLog> listBatchLog = equityBusiInfoExportMapper.getBatchLog();
        for (ChbSubinfoBatchLog batchLog : listBatchLog) {
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_处理中.getValue());
            chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
            Map param = (Map)JSON.parse(batchLog.getGenCondition());
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = format.format(date);
            String equityConfDate = param.get("holdDateEnd").toString();
            if(DateUtil.compareDate(equityConfDate, nowDate)>0){
            	continue;
            }
            long count = equityBusiInfoExportMapper.countExportNum(param);
            equityBusiInfoExportCommonService.export(count,param,batchLog);
        }
        logInfoBatchEnd();
    }
}
