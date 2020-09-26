package com.joyintech.tams.valuation.batch.unitfycaccountant.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.joyintech.tams.valuation.batch.common.netvalueconfirm.ISendNetValueConfirmProcess;
import com.joyintech.tams.valuation.batch.unitfycaccountant.dao.UnitfycaccountantInfoMapper;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoDetailMapper;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;

@Component
public class ValBatchNetValueConfirmThread  implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${batch.properties.sleeptime:20000}")
    private Integer sleepTime;

    // 任务组序列号
    private String seqno;

    // 执行顺序号
    private Long orderNo;

    private SysUnaccountantInfoDetail sysUcaccountantInfoDetail;

    public void setSysUcaccountantInfoDetail(SysUnaccountantInfoDetail sysUcaccountantInfoDetail) {
        this.sysUcaccountantInfoDetail = sysUcaccountantInfoDetail;
    }

    @Autowired
    private UnitfycaccountantInfoMapper unitfycaccountantInfoMapper;

    @Autowired
    private ValBatchUnitfycAccountAntService valBatchUnitfycAccountAntService;

    @Autowired
    private SysUnaccountantInfoDetailMapper sysUcaccountantInfoDetailMapper;

    // 产品组ID
    private String prdGrpSeqNo;

    public void setPrdGrpSeqNo(String prdGrpSeqNo) {
        this.prdGrpSeqNo = prdGrpSeqNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    @Override
    public void run() {
        Map<String, Object> params = new HashMap<>();
        while (true) {
            List<SysUnaccountantInfoDetail> list = unitfycaccountantInfoMapper.findInfoFailList(seqno, orderNo);
            if (!StringUtil.isEmptyList(list)) {
                // 失败的任务不执行
                return;
            }

            list = unitfycaccountantInfoMapper.findOrderNoLessList(seqno, orderNo);
            if (StringUtil.isEmptyList(list)) {
                break;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            	Thread.currentThread().interrupt();
            }
        }
        Map<String, Object> map = new HashMap<>();
//        String execId = IDGenerator.generate();
        String execId = sysUcaccountantInfoDetail.getExectaskId();
        params.put("execId", execId);
        params.put("modelCode", sysUcaccountantInfoDetail.getModelCode());
        params.put("seqno", sysUcaccountantInfoDetail.getSeqNo());
        params.put("orderNo", orderNo);
        sysUcaccountantInfoDetail.setStartTime(DateUtil.now());
        sysUcaccountantInfoDetail.setExectaskId(execId);
        sysUcaccountantInfoDetail.setStatus("1");
        sysUcaccountantInfoDetailMapper.updateByPrimaryKey(sysUcaccountantInfoDetail);

        // 净值下发
        boolean sendRes = sendNetValueConfirm(execId, x->{
            // TODO 净值下发逻辑由各行自主实现

        });
        if (sendRes) {
            sysUcaccountantInfoDetail.setStatus("2");
            Date endTime = DateUtil.now();
            sysUcaccountantInfoDetail.setEndTime(DateUtil.now());
            sysUcaccountantInfoDetail.setLastTime(endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
            sysUcaccountantInfoDetailMapper.updateByPrimaryKey(sysUcaccountantInfoDetail);
        } else {
            Date endTime = DateUtil.now();
            params.put("endTime", endTime);
            params.put("lastTime", endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
            params.put("status", "3");
            unitfycaccountantInfoMapper.updateInfoFail(params);
            unitfycaccountantInfoMapper.updateInfoDetailFail(params);
        }
    }

    // 净值下发处理
    private boolean sendNetValueConfirm(String execId, ISendNetValueConfirmProcess confirm) {
        if (StringUtil.isBlank(execId)) {
            return false;
        }
        try {
            confirm.sendNetValueConfirm(execId);
        }
        catch (Exception e) {
            logger.error("", e);
            return false;
        }
        return true;

    }
}
