/*
 * 文件名：BatchChildDispatchReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchchilddispatch.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dao.BatchChildDispatchMapper;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.BatchChildDispatchDtoO;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.TodoDeal;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.TodoTask;
import com.joyintech.tams.valuation.batch.batchchilddispatch.service.BatchChildDispatchReaderBaseService;
import com.joyintech.tams.valuation.batch.batchchilddispatch.service.BatchChildDispatchReaderMastService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 子调度批处理 - reader
 * 
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see BatchChildDispatchReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchChildDispatchReader extends BaseReader<BatchChildDispatchDtoO> {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private BatchChildDispatchMapper batchChildDispatchMapper;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private BatchChildDispatchReaderBaseService batchChildDispatchReaderBaseService;
    

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private BatchChildDispatchReaderMastService batchChildDispatchReaderMastService;
    
    private final String SEQ_NO = "SEQ_NO";

    private final String BATCH_EXEDETAIL_SEQ_NO = "BATCH_EXEDETAIL_SEQ_NO";

    private final String BOOKDATE = "BOOKDATE";

    private final String HAPPENDATE = "HAPPENDATE";

    private final String BOOKSETIDS = "BOOKSETIDS";

    private final String BUSIID = "BUSIID";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BatchChildDispatchDtoO> createList() {
        // 批处理任务执行信息
        // 资产代码
        // 账套代码
        // 入账日
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();

        SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
        // 入账日
        String bookDate = batchExecuteDetail.getOtherParam1();
        // 发生日
        String happenDate = DateUtil.format(batchExecuteInfo.getExecuteDate(), "yyyy-MM-dd");
        // 金融产品代码【yangjian-note,此处确认金融产品代码如何传递过来】
        String busiId = null;
        // 删除跑批范围临时表
        batchChildDispatchMapper.deleteAllOfTodo();

        // 取得所有倒起息交易数据
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(BOOKDATE, bookDate);
        param.put(HAPPENDATE, happenDate);
        param.put(BUSIID, busiId);
        param.put(GlobalConstant.BOOKSETS, this.getParams().get(GlobalConstant.BOOKSETS));
        List<TodoDeal> deals = batchChildDispatchMapper.queryTodoDeal(param);

        // 取得批处理优先信息
        Map<String, String> param2 = new HashMap<String, String>();
        param2.put(SEQ_NO, batchExecuteInfo.getSeqNo());
        param2.put(BATCH_EXEDETAIL_SEQ_NO, batchExecuteInfo.getBatchExedetailSeqNo());
        //取得批处理优先信息(base库)
        List<TodoTask> tasks = batchChildDispatchReaderBaseService.queryTodoTasks(param2);
        //取得批处理优先信息(mast库)
        List<TodoTask> tasksEx = batchChildDispatchReaderMastService.queryTodoTasksEx();
        //合并数据
        for (TodoTask task : tasks) {
            for (TodoTask taskEx : tasksEx) {
                if (task.getTaskId().equals(taskEx.getTaskId())) {
                    task.setpTaskId(taskEx.getpTaskId());
                    task.setTradeType(taskEx.getTradeType());
                    task.setAllDetail(taskEx.getAllDetail());
                    task.setTaskExeRule(taskEx.getTaskExeRule());
                }
            }
        }

        // 循环数据，生成需要的[批处理待处理任务表]结果集
        // List<ValBatchTaskTodo> result = new ArrayList<ValBatchTaskTodo>();

        // // SET_账套代码：全量批处理跑批的账套代码
        // Set<String> bookSetIdMem = new HashSet<>();
        // // SET_已处理任务：若当前交易类型下的“差量”，记录已经插入的任务代码
        // Set<String> taskIdAlready = null;
        // // SET_任务代码：全量账套跑批的批处理
        // Set<String> taskIdMem = null;
        // 定义 批处理待处理任务 实体类
        // ValBatchTaskTodo valBatchTaskTodo = null;
        // 循环取得所有倒起息交易数据,生成需要的[批处理待处理任务表]结果集
        // for (TodoDeal deal : deals) {
        //
        // }
        // sysPageElements.stream().collect(Collectors.groupingBy(SysPageElement::getElementId));
        Map<String, List<TodoDeal>> resultMap = deals.stream().collect(
            JoyinCollectors.groupingBy(x -> x.getBooksetId()));

        List<BatchChildDispatchDtoO> results = new ArrayList<BatchChildDispatchDtoO>();
        resultMap.forEach((k, v) -> {
            BatchChildDispatchDtoO item = new BatchChildDispatchDtoO();
            item.setBooksetId(k);
            item.setDeals(v);
            item.setTasks(tasks);
            results.add(item);
        });
        return results;
    }

}
