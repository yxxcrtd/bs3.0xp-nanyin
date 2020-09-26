/*
 * 文件名：BatchChildDispatchProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchchilddispatch.proseccor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.BatchChildDispatchDtoO;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.BatchChildDispatchDtoW;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.TodoDeal;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.TodoTask;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;

/**
 * 子调度批处理 - proseccor
 * 
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see BatchChildDispatchProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchChildDispatchProcessor extends BaseProcessor<BatchChildDispatchDtoO, NeedToWriteEntity> {

    private final String TASKTYPE_ALL = "QL";

    private final String TASKTYPE_CH = "CL";

    /**
     * 是否倒起息_是
     */
    private final String BACKVALUE_YES = DictCodeList.是否_是.getValue();

    /**
     * 是否倒起息_否
     */
    private final String BACKVALUE_NO = DictCodeList.是否_否.getValue();

    /**
     * 全量账务明细
     */
    private final String ALLDETAIL_YES = DictCodeList.是否_是.getValue();

    /**
     * 差量账务明细
     */
    private final String ALLDETAIL_NO = DictCodeList.是否_否.getValue();

    @Override
    public NeedToWriteEntity process(BatchChildDispatchDtoO dtoO)
        throws Exception {
        List<TodoDeal> deals = dtoO.getDeals();
        List<TodoTask> tasks = dtoO.getTasks();
        // 输出
        BatchChildDispatchDtoW result = new BatchChildDispatchDtoW();
        // 定义 批处理待处理任务 实体类
        List<BokBatchTaskTodo> list = new ArrayList<>();
        // SET_任务代码：全量账套跑批的批处理
        Set<String> taskIdMem = new HashSet<>();
        // SET_已处理任务：若当前交易类型下的“差量”，记录已经插入的任务代码
        Set<String> taskIdAlready = new HashSet<>();
        for (TodoDeal deal : deals) {
            // 若[入账日 = 账套日期 = 发生日]
            if (deal.getBookDate().compareTo(deal.getBookSetDate()) == 0
                && deal.getBookDate().compareTo(deal.getHappenDate()) == 0) {
                tasks.forEach(task -> {
                    if (isSkipTask(deal, task)) {
                        return;
                    }
                    // 定义 批处理待处理任务 实体类
                    BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                        ALLDETAIL_YES, BACKVALUE_NO);
                    // 塞入list中
                    list.add(valBatchTaskTodo);
                });
                break;
            } // 倒起息范围为“全量”
            else if (TASKTYPE_ALL.equals(deal.getBackRange())) {
                tasks.forEach(task -> {
                    if (isSkipTask(deal, task)) {
                        return;
                    }
                    // 定义 批处理待处理任务 实体类
                    BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                        ALLDETAIL_YES, BACKVALUE_YES);
                    // 塞入list中
                    list.add(valBatchTaskTodo);
                });
                break;
            } // 倒起息范围为“差量”
            else if (TASKTYPE_CH.equals(deal.getBackRange())) {
                // 循环批处理优先信息
                tasks.forEach(task -> {
                    if (isSkipTask(deal, task)) {
                        return;
                    }
                    // 如果任务代码在SET_全账套任务代码 中已存在，则CONTINUE
                    if (taskIdMem.contains(task.getTaskId())) {
                        return;
                    }
                    if (DictCodeList.是否_是.getValue().equals(task.getAllDetail())) {
                        taskIdMem.add(task.getTaskId());
                    }
                    if (task.getpTaskId() == null
                        || (StringUtils.isNotEmpty(task.getTradeType())
                            && task.getTradeType().equals(deal.getBusiType()))) {
                        BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                            ALLDETAIL_NO, BACKVALUE_YES);
                        // 塞入list中
                        list.add(valBatchTaskTodo);
                        // 记录当前交易类型下的插入的任务id
                        taskIdAlready.add(task.getTaskId());
                    } // 前序任务代码 != NULL AND 前序任务代码存在于结果集中
                    else if (task.getpTaskId() != null
                             && taskIdAlready.contains(task.getpTaskId())) {
                        BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                            ALLDETAIL_NO, BACKVALUE_YES);
                        // 塞入list中
                        list.add(valBatchTaskTodo);
                        // 记录当前任务id
                        taskIdAlready.add(task.getTaskId());
                    }
                });
            }
        }

        result.setProdId(dtoO.getBooksetId());
        result.setDetails(list);
        return result;
    }

    /**
     * 是否跳过批处理
     * 
     * @param deal
     * @param task
     * @return
     * @see
     * @since 1.0
     */
    private boolean isSkipTask(TodoDeal deal, TodoTask task) {
        // if (DictCodeListExt1.跑批规则_按账套日最初执行.getValue().equals(task.getTaskExeRule())) {
        // if (GlobalConstant.FIRST.equals(this.getBatchExecuteDetail().getOtherParam2())
        // && this.getBatchExecuteInfo().getExecuteDate().equals(
        // this.getBatchExecuteDetail().getStartDate())) {
        // // 不跳过
        // return false;
        // }
        // // 跳过
        // return true;
        // }
        // else
        if (DictCodeListExt1.跑批规则_按入账日最后执行.getValue().equals(task.getTaskExeRule())) {
            if (deal.getBookDate().equals(deal.getHappenDate())) {
                // 不跳过
                return false;
            }
            // 跳过
            return true;
        }
        else if (DictCodeListExt1.跑批规则_按账套日最后执行.getValue().equals(task.getTaskExeRule())) {
            if (this.getBatchExecuteDetail().getOtherParam1().equals(
                DateUtil.format(deal.getBookSetDate()))
                && DateUtil.format(this.getBatchExecuteInfo().getExecuteDate()).equals(
                    this.getBatchExecuteDetail().getOtherParam1())) {
                // 不跳过
                return false;
            }
        }
        // 不跳过
        return false;
    }

    /**
     * 获取 批处理待处理任务 实体类 【针对非倒起息和全量】
     * 
     * @param deal 倒起息交易数据
     * @param bookDate 入账日
     * @param seqNo 传递的seqno
     * @param isBackValue 是否倒起息
     * @return ValBatchTaskTodo
     * @see
     * @since 1.0
     */
    private BokBatchTaskTodo getValBatchTaskTodo(TodoDeal deal, TodoTask task,
                                                 String alldetailflag, String isBackValue) {
        // 定义 批处理待处理任务 实体类
        BokBatchTaskTodo tmp = new BokBatchTaskTodo();
        tmp.setSeqNo(task.getSeqno());// 批处理任务组执行ID = 直接取批处理优先信息中的第一个
        tmp.setTaskId(task.getTaskId());// 任务代码
        tmp.setBooksetId(deal.getBooksetId());// 账套代码
        tmp.setBooksetDate(deal.getBookSetDate());// 账套日期
        tmp.setHappenDate(deal.getHappenDate());// 发生日
        tmp.setBookDate(deal.getBookDate());// 入账日 = 取参数入账日
        // 如果是差量批处理，且该批需要账务明细代码则赋值
        if (ALLDETAIL_NO.equals(alldetailflag)
            && (task.getAllDetail() == null
                || !DictCodeList.是否_是.getValue().equals(task.getAllDetail()))) {
            tmp.setBokDetailId(deal.getBokDetailId());// 账务明细代码
        }
        tmp.setIsBackValue(isBackValue);// 是否倒起息
        return tmp;
    }

}
