package com.joyintech.tams.valuation.batch.unitfycaccountant.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.joyintech.tams.valuation.batch.unitfycaccountant.dao.UnitfycaccountantInfoMapper;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoMapper;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfo;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

@Component
public class UnitfycaccountantExecutor implements Runnable {

    // 执行公共数据导入逻辑
    @Autowired
    private ValBatchCommonDataImportThread valBatchCommonDataImportThread;

    // 执行交易数据导入逻辑
    @Autowired
    private ValBatchDealDataImportThread valBatchDealDataImportThread;
    /**
     * 批处理轮询时间
     */
    @Value("${batch.properties.sleeptime:20000}")
    private Integer sleepTime;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UnitfycaccountantInfoMapper unitfycaccountantInfoMapper;

    @Autowired
    private SysUnaccountantInfoMapper sysUcaccountantInfoMapper;

    // 执行登账批处理
    @Autowired
    private ValBatchAccountResolveThread valBatchAccountResolveThread;

    // 执行净值下发确认
    @Autowired
    private ValBatchNetValueConfirmThread valBatchNetValueConfirmThread;
    //余额表对账
    @Autowired
    private ValBatchBalanceCheckThread valBatchBalanceCheckThread;
    //估值表对账
    @Autowired
    private ValBatchValuationCheckThread valBatchValuationCheckThread;
    //数据回传
    @Autowired
    private ValBatchDataBackhaulThread valBatchDataBackhaulThread;
    @Override
    public void run() {
        while (true) {
            try {
                List<SysUnaccountantInfo> list = unitfycaccountantInfoMapper.findUnExecUnitfycaccountantInfo();
                logger.debug("轮询到数据条数：" + list.size());
                if (!StringUtil.isEmptyList(list)) {
                    list.forEach( x -> {
                        Map<String, Object> params = new HashMap<>();
                        params.put("seqno", x.getSeqNo());
                        List<SysUnaccountantInfoDetail> sysUcaccountantInfoDetails = unitfycaccountantInfoMapper.findSysUcaccountantInfoDetailBySeqno(params);
                        if ("0".equals(x.getStatus())) {
                            unitfycaccountantInfoMapper.updateUnitfycaccountantInfoRun(params);
                            sysUcaccountantInfoDetails.forEach(y -> resolveSysUnitfycaccountantInfo(x.getPrdGroupSeqNo(), y.getModelCode(), y));
                        } else {
                            List<SysUnaccountantInfoDetail> detailsRunning = sysUcaccountantInfoDetails.stream()
                                    .filter((y)->"0".equals(y.getStatus()) || "1".equals(y.getStatus()))
                                    .collect(Collectors.toList());
                            if (StringUtil.isEmptyList(detailsRunning)) {
                                x.setStatus("2");
                                Date endTime = DateUtil.now();
//                                x.setLastTime(endTime.getTime() - x.getStartTime().getTime());
                                x.setEndTime(endTime);
                                sysUcaccountantInfoMapper.updateByPrimaryKey(x);
                            }
                        }
                    });
                }
            } catch (Exception e) {
                logger.error("轮询失败", e);
            } finally {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                	Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * 处理做账信息
     * @param modelCode
     * @param info
     */
    private void resolveSysUnitfycaccountantInfo(String prdGrpSeqNo, String modelCode, SysUnaccountantInfoDetail info) {
        if (DictCodeListExt1.一键做账模块分类_公共数据导入.getValue().equals(modelCode)) {
            valBatchCommonDataImportThread.setOrderNo(info.getOrderNo());
            valBatchCommonDataImportThread.setSeqno(info.getSeqNo());
            valBatchCommonDataImportThread.setSysUcaccountantInfoDetail(info);
            valBatchCommonDataImportThread.setPrdGrpSeqNo(prdGrpSeqNo);
            Thread t = new Thread(valBatchCommonDataImportThread);
            t.setDaemon(true);
            t.start();
        }
        else if (DictCodeListExt1.一键做账模块分类_交易数据导入.getValue().equals(modelCode)) {
            // 执行交易数据导入逻辑
            valBatchDealDataImportThread.setOrderNo(info.getOrderNo());
            valBatchDealDataImportThread.setSeqno(info.getSeqNo());
            valBatchDealDataImportThread.setSysUcaccountantInfoDetail(info);
            valBatchDealDataImportThread.setPrdGrpSeqNo(prdGrpSeqNo);
            Thread t = new Thread(valBatchDealDataImportThread);
            t.setDaemon(true);
            t.start();
        }
        else if (DictCodeListExt1.一键做账模块分类_登账批处理.getValue().equals(modelCode)) {
            // 执行登账批处理逻辑
            valBatchAccountResolveThread.setOrderNo(info.getOrderNo());
            valBatchAccountResolveThread.setSeqno(info.getSeqNo());
            valBatchAccountResolveThread.setSysUcaccountantInfoDetail(info);
            valBatchAccountResolveThread.setPrdGrpSeqNo(prdGrpSeqNo);
            Thread t = new Thread(valBatchAccountResolveThread);
            t.setDaemon(true);
            t.start();
        }
        else if (DictCodeListExt1.一键做账模块分类_估值表核对_管理人.getValue().equals(modelCode)) {
            // TODO
        }
        else if (DictCodeListExt1.一键做账模块分类_估值表核对_托管人.getValue().equals(modelCode)) {
            valBatchValuationCheckThread.setOrderNo(info.getOrderNo());
            valBatchValuationCheckThread.setPrdGrpSeqNo(prdGrpSeqNo);
            valBatchValuationCheckThread.setSeqno(info.getSeqNo());
            valBatchValuationCheckThread.setSysUcaccountantInfoDetail(info);
            Thread t = new Thread(valBatchValuationCheckThread);
            t.setDaemon(true);
            t.start();
        }
        else if (DictCodeListExt1.一键做账模块分类_余额表核对.getValue().equals(modelCode)) {
            valBatchBalanceCheckThread.setOrderNo(info.getOrderNo());
            valBatchBalanceCheckThread.setPrdGrpSeqNo(prdGrpSeqNo);
            valBatchBalanceCheckThread.setSeqno(info.getSeqNo());
            valBatchBalanceCheckThread.setSysUcaccountantInfoDetail(info);
            Thread t = new Thread(valBatchBalanceCheckThread);
            t.setDaemon(true);
            t.start();
        }
        else if (DictCodeListExt1.一键做账模块分类_净值确认下发.getValue().equals(modelCode)) {
            // 执行登账批处理逻辑
            valBatchNetValueConfirmThread.setOrderNo(info.getOrderNo());
            valBatchNetValueConfirmThread.setSeqno(info.getSeqNo());
            valBatchNetValueConfirmThread.setSysUcaccountantInfoDetail(info);
            valBatchNetValueConfirmThread.setPrdGrpSeqNo(prdGrpSeqNo);
            Thread t = new Thread(valBatchNetValueConfirmThread);
            t.setDaemon(true);
            t.start();
        }else if (DictCodeListExt1.一键做账模块分类_数据回传.getValue().equals(modelCode)) {
            // 执行登账批处理逻辑
            valBatchDataBackhaulThread.setOrderNo(info.getOrderNo());
            valBatchDataBackhaulThread.setSeqno(info.getSeqNo());
            valBatchDataBackhaulThread.setSysUcaccountantInfoDetail(info);
            valBatchDataBackhaulThread.setPrdGrpSeqNo(prdGrpSeqNo);
            Thread t = new Thread(valBatchDataBackhaulThread);
            t.setDaemon(true);
            t.start();
        }
    }


}
