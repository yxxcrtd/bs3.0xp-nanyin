/*
 * 文件名：ValCronJob.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2019年2月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.base.service;

import com.joyintech.batch.service.BatchCoreService;
import com.joyintech.batch.service.ServerDoubleAliveService;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.valuation.batch.batchdispatch.dao.BatchDispatchMapper;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchGrpPri;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.DimensionValue;
import com.joyintech.tams.valuation.batch.common.base.dao.ValBatchCronJobMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 自定义批处理定时任务实现
 *
 * @author 王波
 * @version 1.0 2019年2月13日
 * @see ValCronJob
 * @since 1.0
 */
public class ValCronJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private BatchCoreService batchCoreService = SpringContextUtil.getBean(BatchCoreService.class);

    private String enableServerDoubleAlive = SpringContextUtil.getTextValue("batch.properties.enableServerDoubleAlive");

    private String serverName = SpringContextUtil.getTextValue("batch.properties.serverName");

    private String sysCode = SpringContextUtil.getTextValue("batch.properties.sysCode");

    private ServerDoubleAliveService serverDoubleAliveService = SpringContextUtil.getBean(ServerDoubleAliveService.class);

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        if (enableServerDoubleAlive != null && Boolean.valueOf(enableServerDoubleAlive)) {
            if (!serverDoubleAliveService.selectMinPriAllDown(serverName, sysCode)) {
                // 没有全部宕机时，本机不处理
                logger.debug("比当前服务器："+serverName + ", 优先级高的服务器没有全部宕机，本机不处理。");
                return;
            }
        }

        List<SysBatchgrpExecInfo> listSysBatchgrpExecInfo = batchCoreService
                .findAllUnrunDetail(BatchDictCodeList.批处理执行状态_未执行.getValue(), "VAL");
        if (listSysBatchgrpExecInfo.size() > 0) {
            // 有未执行的任务待处理
            LoggerFactory.getLogger(this.getClass()).error("有未执行的任务待处理!");
            return;
        }

        listSysBatchgrpExecInfo = batchCoreService
                .findAllUnrunDetail(BatchDictCodeList.批处理执行状态_执行中.getValue(), "VAL");
        if (listSysBatchgrpExecInfo.size() > 0) {
            // 有未执行的任务待处理
            LoggerFactory.getLogger(this.getClass()).error("有执行中的任务!");
            return;
        }

        // 任务组ID
        String taskGrpId = (String) context.getMergedJobDataMap().get("taskGrpId");
        // 产品组ID
        String prdGrpId = (String) context.getMergedJobDataMap().get("prdGrpId");
        // 批详细名称
        String detailName = (String) context.getMergedJobDataMap().get("detailName");
        // 查询任务组所有任务
        List<String> tasks = selectGroupBySeqNo(taskGrpId);

        // 产品列表
        List<String> prodList = findAllProdbyGroup(prdGrpId);

        SysBatchgrpExecInfo executeDetail = new SysBatchgrpExecInfo();

        // 明细Seqno
        String detailSeqNo = IDGenerator.generate();
        // 任务实例
        List<SysBatchJob> insertJobInstList = new ArrayList<>();
        // 任务组优先级
        List<BatchGrpPri> insertGrpPriList = new ArrayList<>();
        // 维度值
        List<DimensionValue> insertDimValueList = new ArrayList<>();
        // 日期维度列表
        List<Date> dates = new ArrayList<>();
        //当前日期
        ValBatchCronJobMapper valBatchCronJobMapper = SpringContextUtil.getBean(ValBatchCronJobMapper.class);
        Date nowDate = valBatchCronJobMapper.getSysDate();
        // 跑当日批
        dates.add(nowDate);
        dates.forEach(x->{
            // 任务实例
            List<SysBatchJob> jobInstList = new ArrayList<>();
            // 任务的Seqno
            String batchGroupSeqNoInst = IDGenerator.generate();
            tasks.forEach(taskId -> {
                SysBatchJob job = new SysBatchJob();
                job.setBatchSeqNo(batchGroupSeqNoInst);
                job.setTaskId(taskId);
                jobInstList.add(job);
            });
            insertJobInstList.addAll(jobInstList);
            // 任务组优先级
            BatchGrpPri batchGrpPri = new BatchGrpPri(detailSeqNo, batchGroupSeqNoInst, x, BigDecimal.ZERO);
            insertGrpPriList.add(batchGrpPri);
            // 维度值
            DimensionValue dimensionValue = new DimensionValue(batchGroupSeqNoInst, "1", DateUtil.format(x));
            insertDimValueList.add(dimensionValue);
        });
        // 维度批处理必备参数
        insertDimensionTask(insertJobInstList, insertGrpPriList, insertDimValueList);

        executeDetail.setSystemCode("VAL");
        executeDetail.setSeqNo(detailSeqNo);
        executeDetail.setBatchSeqNo(taskGrpId);
        executeDetail.setPrdGroupSeqNo(prdGrpId);
        executeDetail.setStartDate(nowDate);
        executeDetail.setEndDate(nowDate);
        executeDetail.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
        executeDetail.setDetailName("任务组名称："+detailName+", 启动时间:" + DateUtil.format(nowDate, "yyyy-MM-dd HH:mm:ss.SSS"));
        // 是否日切:是
        executeDetail.setOtherParam1(DictCodeList.是否_是.getValue());
        // 估值分组
        executeDetail.setOtherParam3(executeDetail.getSeqNo());
        batchCoreService.insertBatchgrpExecInfo(executeDetail);
    }

    /**
     * 维度批处理必备参数 入库
     * @param jobInstList
     * @param batchGrpPriList
     * @param dimValueList
     */
    private void insertDimensionTask(List<SysBatchJob> jobInstList, List<BatchGrpPri> batchGrpPriList,
                                     List<DimensionValue> dimValueList) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        try {
            BatchDispatchMapper batchDispatchMapper =  SpringContextUtil.getBean(BatchDispatchMapper.class);
            batchDispatchMapper.insertBatchJob(jobInstList);
            batchDispatchMapper.insertBatchGrpPri(batchGrpPriList);
            batchDispatchMapper.insertDimValue(dimValueList);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("", e);
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
    }

    /**
     *  查询任务组的任务
     * @param taskGrpId 任务组ID
     * @return
     */
    private List<String> selectGroupBySeqNo(String taskGrpId) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        ValBatchCronJobMapper valBatchCronJobMapper = SpringContextUtil.getBean(ValBatchCronJobMapper.class);
        List<String> res  = valBatchCronJobMapper.findAllTaskbyGroup(taskGrpId);
        DynamicDataSourceHolder.clearDataSource();
        return res;
    }

    /**
     *  查询产品组所有产品
     * @param prdGrpId 产品组ID
     * @return
     */
    private List<String> findAllProdbyGroup(String prdGrpId) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        ValBatchCronJobMapper valBatchCronJobMapper = SpringContextUtil.getBean(ValBatchCronJobMapper.class);
        List<String> res = valBatchCronJobMapper.findAllProdbyGroup(prdGrpId);
        DynamicDataSourceHolder.clearDataSource();
        return res;
    }
}
