package com.joyintech.tams.valuation.batch.batchunistart.service;

import com.joyintech.framework.base.entity.SysBatchGrp;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.dao.SysBatchUnistartDetailMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.SysBatchUnistartDetail;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchGrpPri;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.DimensionValue;
import com.joyintech.tams.valuation.batch.batchunistart.dao.BatchUnistartMapper;
import com.joyintech.tams.valuation.batch.common.base.dao.ValBatchCronJobMapper;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Scope("prototype")
public class ValBatchResolveThread implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${batch.properties.sleeptime:20000}")
    private Integer sleepTime;

    @Value("${batch.properties.checktime:20000}")
    private Integer checktime;

    private static final int LOG_COUNT = 2;

    private SysBatchUnistartDetail sysBatchUnistartDetail;

    public void setSysBatchUnistartDetail(SysBatchUnistartDetail sysBatchUnistartDetail) {
        this.sysBatchUnistartDetail = sysBatchUnistartDetail;
    }

    @Autowired
    private BooksetCommonService booksetCommonService;

    @Autowired
    private BatchUnistartCommonService batchUnistartCommonService;

    @Autowired
    private BatchUnistartMapper batchUnistartMapper;

    @Autowired
    private SysBatchUnistartDetailMapper sysBatchUnistartDetailMapper;

    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;

    @Autowired
    private ValBatchCronJobMapper valBatchCronJobMapper;

    @Override
    public void run() {
        Map<String, Object> searchParam = null;
        List<SysBatchUnistartDetail> list = null;
        // 监控前序批处理状态
        while (true) {
            searchParam = new HashMap<>();
            searchParam.put("seqNo", sysBatchUnistartDetail.getSeqNo());
//            searchParam.put("detailSeqNo", sysBatchUnistartDetail.getDetailSeqNo());
            searchParam.put("orderNo", sysBatchUnistartDetail.getOrderNo());
            searchParam.put("status", BatchDictCodeList.批处理执行状态_异常结束.getValue());
            list = batchUnistartMapper.findUnistartDetailBySeqNo(searchParam);
            // 如果存在失败信息，则后序批处理不执行
            if (!StringUtil.isEmptyList(list)) {
                // 失败的任务不执行
                logger.error("前序任务失败，线程中断");
                return;
            }
            // 如前序批处理全部执行完，即开始启动
            list = batchUnistartMapper.findUnistartOrderNoLessList(searchParam);
            if (StringUtil.isEmptyList(list)) {
                break;
            }
            try {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e) {
                batchUnistartCommonService.updateBatchUnistartDetail(sysBatchUnistartDetail,
                    BatchDictCodeList.批处理执行状态_异常结束.getValue());
                logger.error("异常错误，线程中断：" + e);
                Thread.currentThread().interrupt();
                return;
            }
        }

        // 启动批处理
        startBatch(sysBatchUnistartDetail);

        // 更新批处理状态->启动
        batchUnistartCommonService.updateBatchUnistartDetail(sysBatchUnistartDetail, BatchDictCodeList.批处理执行状态_执行中.getValue());

        // 监控批处理状态
        while (true) {
            // 判断批处理结束或异常
            boolean isEnd = batchUnistartCommonService.checkTaskLog(sysBatchUnistartDetail, LOG_COUNT);
            if (isEnd) {
                break;
            }

            try {
                Thread.sleep(checktime);
            }
            catch (InterruptedException e) {
                batchUnistartCommonService.updateBatchUnistartDetail(sysBatchUnistartDetail,
                    BatchDictCodeList.批处理执行状态_异常结束.getValue());
                logger.error("异常错误，线程中断：" + e);
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    /**
     * 启动批处理
     *
     * @param detail
     */
    public void startBatch(SysBatchUnistartDetail detail) {

        List<String> prodList = null;
        // 初始化账套日期
        if (StringUtil.isNotEmpty(detail.getPrdGroupSeqNo())) {
            prodList = findAllProdbyGroup(detail.getPrdGroupSeqNo());
        } else {
            prodList = booksetCommonService.findAllEffProductId();
        }

        if (prodList == null) {
            batchUnistartCommonService.updateBatchUnistartDetail(sysBatchUnistartDetail,
                BatchDictCodeList.批处理执行状态_异常结束.getValue());
            logger.error("无可用账套, 线程中断");
            Thread.currentThread().interrupt();
        }
        // 跑批开始日
        Date start = sysBatchUnistartDetail.getBusiDate();
        // 跑批结束日
        Date end = sysBatchUnistartDetail.getBusiDate();
        // 更新账套日期
        updateBooksetDate(start, end, prodList);

        // 初始化批处理参数
        // 组装批处理任务组
        String batchGroupSeqNoInst = IDGenerator.generate();
        SysBatchGrp batchGroup = new SysBatchGrp();
        batchGroup.setSystemCode(sysBatchUnistartDetail.getSystemCode());
        batchGroup.setSeqNo(batchGroupSeqNoInst);
        batchGroup.setBatchName(batchGroupSeqNoInst);
        batchGroup.setDetail(batchGroup.getBatchName() + ";启动批处理时创建。");
        // 组装批处理任务组实例
        List<SysBatchJob> jobInstList = new ArrayList<>();
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<SysBatchJob> jobList = batchUnistartMapper.findAllJobByGroup(detail.getBatchgrpSeqNo());
        DynamicDataSourceHolder.clearDataSource();
        for (SysBatchJob job : jobList) {
            SysBatchJob jobInst = new SysBatchJob();
            jobInst.setBatchSeqNo(batchGroupSeqNoInst);
            jobInst.setTaskId(job.getTaskId());
            jobInstList.add(jobInst);
        }
//        List<SysBatchJob> jobList = new ArrayList<>();
//        SysBatchJob job = new SysBatchJob();
//        job.setBatchSeqNo(batchGroupSeqNoInst);
//        job.setTaskId("100");
//        jobList.add(job);

        // 组装批处理任务组优先级
        List<BatchGrpPri> batchGrpPriList = new ArrayList<>();
        BatchGrpPri batchGrpPri = new BatchGrpPri(detail.getDetailSeqNo(), batchGroupSeqNoInst, start, BigDecimal.ZERO);
        batchGrpPriList.add(batchGrpPri);

        // 组装批处理维度值
        List<DimensionValue> dimValueList = new ArrayList<>();
        DimensionValue dimensionValue = new DimensionValue(batchGroupSeqNoInst, "1", DateUtil.format(start));
        dimValueList.add(dimensionValue);

        // 插入批处理参数
        batchUnistartCommonService.insertBatchConfig(batchGroup, jobInstList, batchGrpPriList, dimValueList);
        // 组装批处理执行信息
        SysBatchgrpExecInfo execInfo = new SysBatchgrpExecInfo();
        execInfo.setSystemCode(sysBatchUnistartDetail.getSystemCode());
        execInfo.setSeqNo(detail.getDetailSeqNo());
        execInfo.setBatchSeqNo(batchGroupSeqNoInst);
        execInfo.setPrdGroupSeqNo(detail.getPrdGroupSeqNo());
        execInfo.setStartDate(start);
        execInfo.setEndDate(end);
        execInfo.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
        execInfo.setDetailName(detail.getRemark());
        execInfo.setOtherParam3(execInfo.getSeqNo());
        execInfo.setCreateUser(detail.getCreateUser());
        execInfo.setUpdateUser(detail.getCreateUser());

        // 插入批处理启动信息
        batchUnistartCommonService.insertBatchgrpExecInfo(execInfo);

    }

    /**
     * 查询产品组所有产品
     *
     * @param prdGrpId 产品组ID
     * @return
     */
    public List<String> findAllProdbyGroup(String prdGrpId) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<String> res = valBatchCronJobMapper.findAllProdbyGroup(prdGrpId);
        DynamicDataSourceHolder.clearDataSource();
        return res;
    }

    /**
     * 更新账套日期
     * @param start
     * @param end
     * @param prodList
     */
    private void updateBooksetDate(Date start, Date end, List<String> prodList) {
        // 获取所有账套
        List<BokBookset> booksetList = batchUnistartMapper.selectValBokBookset(start, end, prodList);

        if (booksetList != null && booksetList.size() > 0) {
            for (BokBookset var : booksetList) {
                Date date1 = null;
                if (start.compareTo(var.getStartDate()) >= 0) {
                    date1 = start;
                }
                else {
                    date1 = var.getStartDate();
                }
                // 更新账套日期表
                BokBooksetDate record = new BokBooksetDate();
                record.setBooksetId(var.getBooksetId());
                record.setBooksetDate(date1);
                // 切账套会计日期
                bokBooksetDateMapper.updateByPrimaryKeySelective(record);
            }
        }

    }
}
