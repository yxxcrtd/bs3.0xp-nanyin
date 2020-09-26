package com.joyintech.tams.valuation.batch.unitfycaccountant.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoDetailMapper;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;
import com.joyintech.tams.common.base.entity.ValInterfActionlog;
import com.joyintech.tams.valuation.batch.unitfycaccountant.dao.UnitfycaccountantInfoMapper;
import com.joyintech.tams.valuation.batch.unitfycaccountant.entity.ValAccountInfoDto;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tools.util.HttpClient;

/**
 * 公共数据导入线程
 */
@Component
public class ValBatchCommonDataImportThread implements Runnable {

    //代表公共数据
    private static String InterfFlag = "InterfData";

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
        map.put("taskGrpType", InterfFlag);
        List<ValAccountInfoDto> list = unitfycaccountantInfoMapper.findAllTask(map);
//        String execId = interfCommonService.getUUid();
        String execId = sysUcaccountantInfoDetail.getExectaskId();
        sendInterf(list, execId);

        params.put("execId", execId);
        params.put("modelCode", sysUcaccountantInfoDetail.getModelCode());
        params.put("seqno", sysUcaccountantInfoDetail.getSeqNo());
        params.put("orderNo", orderNo);
        sysUcaccountantInfoDetail.setStartTime(DateUtil.now());
        sysUcaccountantInfoDetail.setExectaskId(execId);
        sysUcaccountantInfoDetail.setStatus("1");
        sysUcaccountantInfoDetailMapper.updateByPrimaryKey(sysUcaccountantInfoDetail);
        while (true) {

            List<ValInterfActionlog> listIntefLog = unitfycaccountantInfoMapper.getInterActionLog(params);
            if (listIntefLog.size() > 0) {
                // 成功的数据
                List<ValInterfActionlog> listInterfLogSuccess = listIntefLog.stream()
                        .filter(x->DictCodeInterfList.接口执行状态_执行成功.getValue().equals(x.getStatus()))
                        .collect(Collectors.toList());
                // 失败的数据
                List<ValInterfActionlog> listInterfLogFail = listIntefLog.stream().filter(x->DictCodeInterfList.接口执行状态_执行失败.getValue().equals(x.getStatus())).collect(Collectors.toList());
                // 手动停止的数据
                List<ValInterfActionlog> listInterfLogFManual = listIntefLog.stream().filter(x->DictCodeInterfList.接口执行状态_手动停止.getValue().equals(x.getStatus())).collect(Collectors.toList());
                int countSuccess = listInterfLogSuccess.size();
                int countFail = listInterfLogFail.size() + listInterfLogFManual.size();
                int total = listIntefLog.size();
                if (total == countSuccess) {
                    sysUcaccountantInfoDetail.setStatus("2");
                    Date endTime = DateUtil.now();
                    sysUcaccountantInfoDetail.setEndTime(DateUtil.now());
                    sysUcaccountantInfoDetail.setLastTime(endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
                    sysUcaccountantInfoDetailMapper.updateByPrimaryKey(sysUcaccountantInfoDetail);
                }
                if (countFail > 0) {
                    Date endTime = DateUtil.now();
                    params.put("endTime", endTime);
                    params.put("lastTime", endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
                    params.put("status", "3");
                    unitfycaccountantInfoMapper.updateInfoFail(params);
                    unitfycaccountantInfoMapper.updateInfoDetailFail(params);
                    break;
                }
                if (countSuccess + countFail == total) {
                    break;
                }
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            	Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 发送消息给接口
     * @param list
     */
    private void sendInterf(List<ValAccountInfoDto> list, String execId) {
        String interfName = "";
        Map<String, String> urlParams = new HashMap<>();
        if (!StringUtil.isEmptyList(list)) {
            for(ValAccountInfoDto dto : list) {
                interfName = interfName + "," + dto.getTaskGrpId();
            }
        }
        urlParams.put(InterfCommonConstant.接口_运行接口名称, interfName);
        urlParams.put(InterfCommonConstant.接口_发送接收, DictCodeInterfList.接口发送接收状态_接收.getValue());
        urlParams.put(InterfCommonConstant.接口_接口时间, DateUtil.format(sysUcaccountantInfoDetail.getBusiDate()));
        urlParams.put(InterfCommonConstant.接口_任务执行ID, execId);
        runInterf(valBatchUnitfycAccountAntService.getUrl(), urlParams);
    }

    /**
     * 异步发送请求
     *
     * @param url
     * @param urlParams
     * @see
     * @since 1.0
     */
    private int runInterf(String url, Map<String, String> urlParams) {
        final int[] flag = new int[1];
        HttpClient.postAsync(url, urlParams, null, null, (t)->{
            if (t.isSuccessful()) {
                logger.info("请求成功：" + url);
                flag[0] = 0;
            }
        }, (t)->{
            logger.error(t.getMessage());
            Map<String, Object> params = new HashMap<>();
            Date endTime = DateUtil.now();
            params.put("endTime", endTime);
            params.put("lastTime", endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
            params.put("status", "3");
            params.put("seqno", seqno);
            params.put("orderNo", orderNo);
            unitfycaccountantInfoMapper.updateInfoFail(params);
            unitfycaccountantInfoMapper.updateInfoDetailFail(params);
            flag[0] = 1;
        });

        return flag[0];

    }
}
