package com.joyintech.tams.valuation.batch.unitfycaccountant.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.valuation.batch.batchdispatch.dao.BatchDispatchMapper;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchGrpPri;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.DimensionValue;
import com.joyintech.tams.valuation.batch.common.base.dao.ValBatchCronJobMapper;
import com.joyintech.tams.valuation.batch.unitfycaccountant.dao.UnitfycaccountantInfoMapper;
import com.joyintech.tams.valuation.batch.unitfycaccountant.entity.ValAccountInfoDto;
import com.joyintech.framework.base.dao.SysBatchGrpMapper;
import com.joyintech.framework.base.dao.SysBatchgrpExecInfoMapper;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchGrp;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysCodeUtil;
import com.joyintech.system.batch.batchprocess.dto.BatchTaskDto;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoDetailMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;

@Component
public class ValBatchAccountResolveThread implements Runnable  {
    //代表登账批处理
    private static String InterfFlag = "task";

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
    private SysUnaccountantInfoDetailMapper sysUcaccountantInfoDetailMapper;

    @Autowired
    private BokBooksetDateMapper valBokBooksetDateMapper;

    @Autowired
    private SysBatchgrpExecInfoMapper batchExecuteDetailMapper;

    @Autowired
    private SysBatchGrpMapper batchGroupMapper;

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
        String detailSeqNo = IDGenerator.generate();

        String execId = sysUcaccountantInfoDetail.getExectaskId();
        sendBatch(list, execId);
        params.put("execId", execId);
        params.put("modelCode", sysUcaccountantInfoDetail.getModelCode());
        params.put("seqno", sysUcaccountantInfoDetail.getSeqNo());
        params.put("orderNo", orderNo);
        sysUcaccountantInfoDetail.setStartTime(DateUtil.now());
        sysUcaccountantInfoDetail.setExectaskId(execId);
        sysUcaccountantInfoDetail.setStatus("1");
        sysUcaccountantInfoDetailMapper.updateByPrimaryKey(sysUcaccountantInfoDetail);
        
        // 查询当前任务的日志
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<BatchTaskDto> listBatchTaskDto= new ArrayList<>();
        try {
            listBatchTaskDto =  unitfycaccountantInfoMapper.getBatchTaskid(params);
        }
        catch (Exception e) {
            logger.error("", e);
        }
        finally {
            DynamicDataSourceHolder.clearDataSource();
        }
        
        
        
        while (true) {

            // 查询当前任务的日志
            DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
            List<SysBatchExecInfo> listTaskLog= new ArrayList<>();
            try {
                listTaskLog =  unitfycaccountantInfoMapper.getBatchTaskLogInfo(params);
            }
            catch (Exception e) {
                logger.error("", e);
            }
            finally {
                DynamicDataSourceHolder.clearDataSource();
            }

            // 所有的任务
            int taskCount = listBatchTaskDto.size();
            List<SysBatchExecInfo> listTaskLogFail = listTaskLog.stream()
                    .filter(x->BatchDictCodeList.批处理执行状态_异常结束.getValue().equals(x.getTaskStatus()))
                    .collect(Collectors.toList());
            if (listTaskLogFail.size() > 0) {
                Date endTime = DateUtil.now();
                params.put("endTime", endTime);
//                params.put("lastTime", endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
                params.put("status", "3");
                unitfycaccountantInfoMapper.updateInfoFail(params);
                unitfycaccountantInfoMapper.updateInfoDetailFail(params);
                break;
            }
            // 所有的成功任务
            List<SysBatchExecInfo> listTaskLogSuccess = listTaskLog.stream()
                    .filter(x->BatchDictCodeList.批处理执行状态_正常结束.getValue().equals(x.getTaskStatus()))
                    .collect(Collectors.toList());
            if (listTaskLogSuccess.size() == taskCount) {
                // 全部执行成功
                sysUcaccountantInfoDetail.setStatus("2");
                Date endTime = DateUtil.now();
                sysUcaccountantInfoDetail.setEndTime(DateUtil.now());
//                sysUcaccountantInfoDetail.setLastTime(endTime.getTime() - sysUcaccountantInfoDetail.getStartTime().getTime());
                sysUcaccountantInfoDetailMapper.updateByPrimaryKey(sysUcaccountantInfoDetail);
                break;
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
    private void sendBatch(List<ValAccountInfoDto> list, String detailSeqNo) {

        // 批组的SeqNo
        String batchGroupSeqNoInst = IDGenerator.generate();

        // 单个登账批处理
        String batchGroupSeqNo = IDGenerator.generate();

        SysBatchGrp batchGroup = new SysBatchGrp();
        batchGroup.setSystemCode("VAL");
        String nowString = DateUtil.getCurrentDateTime();
        batchGroup.setSeqNo(batchGroupSeqNo);
        batchGroup.setBatchName("system" + "创建于" + nowString);
        batchGroup.setDetail(batchGroup.getBatchName() + ";启动批处理时创建。");

        List<SysBatchJob> jobList = new ArrayList<>();
        SysBatchJob job = new SysBatchJob();
        job.setBatchSeqNo(batchGroupSeqNoInst);
        job.setTaskId("100");
        jobList.add(job);
        insertGroup(batchGroup, jobList);

        List<String> prodList = findAllProdbyGroup(prdGrpSeqNo);
        Map<String, String> dateMap = new HashMap<>();
        String startDate = DateUtil.format(sysUcaccountantInfoDetail.getBusiDate());
        String endDate = DateUtil.format(sysUcaccountantInfoDetail.getBusiDate());

        // 跑批开始日
        Date start = sysUcaccountantInfoDetail.getBusiDate();
        // 跑批结束日
        Date end = sysUcaccountantInfoDetail.getBusiDate();

        dateMap.put("startDate", startDate);
        dateMap.put("endDate", endDate);


        SysBatchgrpExecInfo executeDetail = getSysBatchgrpExecInfo(start, end, prodList,
                dateMap);
        List<BatchGrpPri> batchGrpPriList = new ArrayList<>();
        BatchGrpPri batchGrpPri = new BatchGrpPri(detailSeqNo, batchGroupSeqNoInst, start, BigDecimal.ZERO);
        batchGrpPriList.add(batchGrpPri);

        List<DimensionValue> dimValueList = new ArrayList<>();
        DimensionValue dimensionValue = new DimensionValue(batchGroupSeqNoInst, "1", startDate);
        dimValueList.add(dimensionValue);
        insertDim(batchGrpPriList, dimValueList);

        executeDetail.setSystemCode("VAL");
        executeDetail.setSeqNo(detailSeqNo);
        executeDetail.setBatchSeqNo("100");
        executeDetail.setPrdGroupSeqNo(prdGrpSeqNo);
        executeDetail.setStartDate(DateUtil.parse(startDate));
        executeDetail.setEndDate(DateUtil.parse(endDate));
        executeDetail.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());

        executeDetail.setDetailName("system" + "创建于" + DateUtil.getCurrentDateTime() + ": 自动做账！");
        executeDetail.setOtherParam3(executeDetail.getSeqNo());
        insertBatchgrpExecInfo(executeDetail);
    }

    private void insertGroup(SysBatchGrp batchGroup, List<SysBatchJob> jobList) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        try {
            BatchDispatchMapper batchDispatchMapper =  SpringContextUtil.getBean(BatchDispatchMapper.class);
            batchDispatchMapper.insertBatchJob(jobList);

            batchGroupMapper.insert(batchGroup);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void insertBatchgrpExecInfo(SysBatchgrpExecInfo detail) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        detail.setSystemCode(SysCodeUtil.getSysCode());
        batchExecuteDetailMapper.insert(detail);
        DynamicDataSourceHolder.clearDataSource();
    }

    private void insertDim(List<BatchGrpPri> batchGrpPriList, List<DimensionValue> dimValueList) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        try {
            BatchDispatchMapper batchDispatchMapper =  SpringContextUtil.getBean(BatchDispatchMapper.class);
            batchDispatchMapper.insertBatchGrpPri(batchGrpPriList);
            batchDispatchMapper.insertDimValue(dimValueList);
        } catch (Exception e) {
            logger.error("", e);
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


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    public SysBatchgrpExecInfo getSysBatchgrpExecInfo(Date start, Date end, List<String> prodList,
                                                      Map<String, String> dateMap) {
        String startDate = dateMap.get("startDate");
        String endDate = dateMap.get("endDate");
        SysBatchgrpExecInfo executeDetail = new SysBatchgrpExecInfo();
        // 获取所有账套
        List<BokBookset> booksetList = unitfycaccountantInfoMapper.selectValBokBookset(start, end,
                prodList);

        // 画面选择日期
        executeDetail.setOtherParam2("Y");
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
                valBokBooksetDateMapper.updateByPrimaryKeySelective(record);
            }
        }

        dateMap.put("startDate", startDate);
        dateMap.put("endDate", endDate);
        return executeDetail;
    }
}
