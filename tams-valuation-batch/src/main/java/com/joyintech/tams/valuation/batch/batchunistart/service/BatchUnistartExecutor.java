package com.joyintech.tams.valuation.batch.batchunistart.service;

import com.joyintech.batch.service.BatchCoreService;
import com.joyintech.batch.service.ServerDoubleAliveService;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.batchunistart.dao.BatchUnistartMapper;
import com.joyintech.tams.common.base.dao.SttDataReadyMapper;
import com.joyintech.tams.common.base.dao.SysBatchUnistartMapper;
import com.joyintech.tams.common.base.entity.SttDataReady;
import com.joyintech.tams.common.base.entity.SysBatchUnistart;
import com.joyintech.tams.common.base.entity.SysBatchUnistartDetail;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class BatchUnistartExecutor implements Runnable {

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
    private BatchUnistartMapper batchUnistartMapper;

    @Autowired
    private SysBatchUnistartMapper sysBatchUnistartMapper;

    @Autowired
    private SttDataReadyMapper sttDataReadyMapper;

    @Autowired
    private BatchCoreService batchCoreService;

    @Autowired
    private ServerDoubleAliveService serverDoubleAliveService;

    @Value("${batch.properties.sysCode:ALL}")
    private String sysCode;

    @Value("${batch.properties.enableServerDoubleAlive:false}")
    private boolean enableServerDoubleAlive = false;

    @Value("${batch.properties.serverName:SERVER1}")
    private String serverName = "SERVER1";

    @Autowired
    private BatchUnistartCommonService batchUnistartCommonService;

    @Override
    public void run() {
        try {
            // 启动10S后开始轮询
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            logger.debug(e.getMessage());
            Thread.currentThread().interrupt();
        }
        while (true) {
            // 双活判断
            if (enableServerDoubleAlive) {
                if (!serverDoubleAliveService.selectMinPriAllDown(serverName, sysCode)) {
                    // 没有全部宕机时，本机不处理
                    logger.debug("比当前服务器："+serverName + ", 优先级高的服务器没有全部宕机，本机不处理。");
                    continue;
                }
            }

            try {
                List<SysBatchUnistart> list = batchUnistartMapper.findUnistartUnrOrRun();
                logger.debug("轮询到数据条数：" + list.size());
                if (!StringUtil.isEmptyList(list)) {
                    list.forEach(x -> {
                        Map<String, Object> params = new HashMap<>();
                        params.put("seqNo", x.getSeqNo());
                        List<SysBatchUnistartDetail> sysBatchUnistartDetailList = batchUnistartMapper.findUnistartDetailBySeqNo(
                            params);
                        if (BatchDictCodeList.批处理执行状态_未执行.getValue().equals(x.getStatus())) {
                            batchUnistartCommonService.updateBatchUnistart(x, BatchDictCodeList.批处理执行状态_执行中.getValue());
                            sysBatchUnistartDetailList.forEach(y -> resolveSysBatchUnistartDetail(y));
                        }
                        else {
//                            List<SysBatchUnistartDetail> detailsRunning = sysBatchUnistartDetailList.stream().filter(
//                                (y) -> BatchDictCodeList.批处理执行状态_未执行.getValue().equals(y.getStatus())
//                                       || BatchDictCodeList.批处理执行状态_执行中.getValue().equals(y.getStatus())).collect(
//                                Collectors.toList());
                            Map<String, List<SysBatchUnistartDetail>> detailMap = sysBatchUnistartDetailList.stream().collect(
                                JoyinCollectors.groupingBy(z -> z.getStatus()));

                            if (!detailMap.containsKey(BatchDictCodeList.批处理执行状态_未执行.getValue())
                                && !detailMap.containsKey(BatchDictCodeList.批处理执行状态_执行中.getValue())) {
                                batchUnistartCommonService.updateBatchUnistart(x,
                                    BatchDictCodeList.批处理执行状态_正常结束.getValue());
                                String datareadey = CacheUtil.getSysParam("BATCH_UNISTART_DATAREADEY");
                                if (StringUtil.isNotEmpty(datareadey)) {
                                    List<String> datareadyList = Arrays.asList(datareadey.split(","));
                                    if (datareadyList.contains("STT")) {
                                        updateSttDataReadey(x.getBusiStartDate());
                                    }
                                }
                            } else if(detailMap.containsKey(BatchDictCodeList.批处理执行状态_异常结束.getValue())) {
                                batchUnistartCommonService.updateBatchUnistart(x,
                                    BatchDictCodeList.批处理执行状态_异常结束.getValue());
                            }
                        }
                    });
                }
            }
            catch (Exception e) {
                logger.error("轮询失败", e);
            }
            finally {
                try {
                    Thread.sleep(sleepTime);
                }
                catch (InterruptedException e) {
                    logger.debug(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    private void updateSttDataReadey(Date busiDate) {
        SttDataReady sttDataReady = new SttDataReady();
        sttDataReady.setSeqNo(CommonUtil.getSequenceLong(DataIndexSequenceType.压力测试数据准备序号));
        sttDataReady.setSys("ZG");
        sttDataReady.setHostNm("1");
        sttDataReady.setBatchNum("ALL");
        sttDataReady.setDataDate(busiDate);
        sttDataReady.setReadyTime(DateUtil.now());
        sttDataReadyMapper.insert(sttDataReady);
    }

    /**
     * 处理做账信息
     *
     * @param detail
     */
    private void resolveSysBatchUnistartDetail(SysBatchUnistartDetail detail) {
        if ("VAL".equals(detail.getSystemCode())) {
            // 估值
            ValBatchResolveThread thread =  SpringContextUtil.getBean(ValBatchResolveThread.class);
            thread.setSysBatchUnistartDetail(detail);
            Thread t = new Thread(thread);
            t.setDaemon(true);
            t.start();
        }
        else if ("INV".equals(detail.getSystemCode())) {
            // 投管
            InvBatchResolveThread thread =  SpringContextUtil.getBean(InvBatchResolveThread.class);
            thread.setSysBatchUnistartDetail(detail);
            Thread t = new Thread(thread);
            t.setDaemon(true);
            t.start();
        }
        else {
            // 其他
            OtherBatchResolveThread thread =  SpringContextUtil.getBean(OtherBatchResolveThread.class);
            thread.setSysBatchUnistartDetail(detail);
            Thread t = new Thread(thread);
            t.setDaemon(true);
            t.start();
        }
    }

}
