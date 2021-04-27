package com.joyintech.tams.batch.report.equitybusiinfoexport.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.equitybusiinfoexport.dao.EquityBusiInfoExportMapper;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;


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
            //判断导出中债excel业务确认日期是否大于当天日期  start 20200512
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = format.format(date);
            String equityConfDateStart = param.get("holdDateStart").toString();
            String equityConfDateEnd = param.get("holdDateEnd").toString();

            if(DateUtil.compareDate(equityConfDateStart, nowDate)>0 && DateUtil.compareDate(equityConfDateEnd, nowDate)>0){
                batchLog.setDealStatus(DictCodeListExt3.报送处理状态_正常结束.getValue());
                batchLog.setDealMessage("明细登记导出中债Excel业务确认日期不能大于当天日期!不导出"+equityConfDateStart+"-"+equityConfDateEnd+"数据。");
                chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
                continue;
            }else if(DateUtil.compareDate(equityConfDateStart, nowDate)<=0 && DateUtil.compareDate(equityConfDateEnd, nowDate)>0){
                param.put("holdDateEnd", nowDate);
            }
            //判断导出中债excel业务确认日期是否大于当天日期  end 20200512

            long count = equityBusiInfoExportMapper.countExportNum(param);
            equityBusiInfoExportCommonService.export(count,param,batchLog);
        }
        logInfoBatchEnd();
    }
}
