/*
 * 文件名：BatchDispatchProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年4月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchdispatch.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchProcessorDto;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchReaderDto;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchTaskInfo;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.DealAccountingTodo;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年4月27日
 * @see BatchDispatchProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchDispatchProcessor extends BaseProcessor<BatchDispatchReaderDto, NeedToWriteEntity> {

    private static final String TASKTYPE_ALL = "QL";

    private static final String TASKTYPE_CH = "CL";

    /**
     * 是否倒起息_是
     */
    private static final String BACKVALUE_YES = DictCodeList.是否_是.getValue();

    /**
     * 是否倒起息_否
     */
    private static final String BACKVALUE_NO = DictCodeList.是否_否.getValue();

    /**
     * 全量账务明细
     */
    private static final String ALLDETAIL_YES = DictCodeList.是否_是.getValue();

    /**
     * 差量账务明细
     */
    private static final String ALLDETAIL_NO = DictCodeList.是否_否.getValue();

    /**
     * {@inheritDoc}
     */
    @Override
    public NeedToWriteEntity process(BatchDispatchReaderDto readerDto)
        throws Exception {
        String backRange = TASKTYPE_CH;
        Map<String, Object> params = this.getParams();
        List<BatchTaskInfo> tasks = (List<BatchTaskInfo>)params.get(BatchTaskInfo.class.getName());
        List<DealAccountingTodo> dealAccountingTodoList = readerDto.getDealAccountingTodoList();
        // 定义 批处理待处理任务 实体类
        List<BokBatchTaskTodo> list = new ArrayList<>();
        // 全量账套任务代码MAP：全量账套跑批的批处理
        Map<String, Set<String>> taskIdMem = new HashMap<>();
        // 已处理任务MAP：若当前交易类型下的“差量”，记录已经插入的任务代码
        Map<String, Set<String>> taskIdAlready = new HashMap<>();
        // 已处理账套日期SET
        Set<String> notBackValueAlready = new HashSet<>();
        Set<String> booksetDates = new HashSet<>();
        List<BatchTaskInfo> booksetDateCommonTask = (List<BatchTaskInfo>)this.getParams().get(
            BookConstant.COMMON + BatchTaskInfo.class.getName());
        for (DealAccountingTodo deal : dealAccountingTodoList) {
            if (!booksetDates.contains(DateUtil.format(deal.getBookSetDate()))) {
                // 定义 批处理待处理任务 实体类
                booksetDateCommonTask.forEach(task -> {
                    if (isSkipTask(deal, task)) {
                        return;
                    }
                    if (DictCodeListExt1.跑批规则_按账套日最初执行.getValue().equals(task.getTaskExeRule())) {
                        BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                            ALLDETAIL_YES, BACKVALUE_NO, readerDto.getDetailSeqNo(), 1);
                        // 塞入list中
                        list.add(valBatchTaskTodo);
                    }
                    else if (DictCodeListExt1.跑批规则_最初执行.getValue().equals(task.getTaskExeRule())) {
                        BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                            ALLDETAIL_YES, BACKVALUE_NO, readerDto.getDetailSeqNo(), 1);
                        // 塞入list中
                        list.add(valBatchTaskTodo);
                    }
                    else if (DictCodeListExt1.跑批规则_按账套日最后执行.getValue().equals(task.getTaskExeRule())) {
                        BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                            ALLDETAIL_YES, BACKVALUE_NO, readerDto.getDetailSeqNo(), 3);
                        // 塞入list中
                        list.add(valBatchTaskTodo);
                    }
                });
                booksetDates.add(DateUtil.format(deal.getBookSetDate()));
            }
            String key = DateUtil.format(deal.getBookSetDate())
                + DateUtil.format(deal.getBookDate())
                + DateUtil.format(deal.getHappenDate());
            // 若[入账日 = 账套日期 = 发生日]
            if (DateUtil.isSameDate(deal.getBookDate(), deal.getBookSetDate())
                && DateUtil.isSameDate(deal.getBookDate(), deal.getHappenDate())) {
                // IF 已处理账套日期SET 包含 入账信息.账套日期 -> CONTINUE
                if (notBackValueAlready.contains(key)) {
                    continue;
                }
                tasks.forEach(task -> {
                    if (isSkipTask(deal, task)) {
                        return;
                    }
                    // 定义 批处理待处理任务 实体类
                    BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                        ALLDETAIL_YES, BACKVALUE_NO, readerDto.getDetailSeqNo(), 3);
                    // 塞入list中
                    list.add(valBatchTaskTodo);
                });
                // 批处理待处理任务LIST 新增 上面组装的批处理待处理任务
                notBackValueAlready.add(key);
            } // 倒起息范围为“全量”
            else if (TASKTYPE_ALL.equals(backRange)) {
                // IF 已处理账套日期SET 包含 入账信息.账套日期 -> CONTINUE
                if (notBackValueAlready.contains(key)) {
                    continue;
                }
                tasks.forEach(task -> {
                    if (isSkipTask(deal, task)) {
                        return;
                    }
                    // 定义 批处理待处理任务 实体类
                    BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                        ALLDETAIL_YES, BACKVALUE_YES, readerDto.getDetailSeqNo(), 3);
                    // 塞入list中
                    list.add(valBatchTaskTodo);
                });
                // 批处理待处理任务LIST 新增 上面组装的批处理待处理任务
                notBackValueAlready.add(key);
            } // 倒起息范围为“差量”
            else if (TASKTYPE_CH.equals(backRange)) {
                // 循环批处理优先信息
                tasks.forEach(task -> {
                    if (isSkipTask(deal, task)) {
                        return;
                    }
                    // 如果任务代码在SET_全账套任务代码 中已存在，则CONTINUE
                    if (taskIdMem.containsKey(key)
                        && taskIdMem.get(key).contains(task.getTaskId())) {
                        return;
                    }
                    if (DictCodeList.是否_是.getValue().equals(task.getAllDetail())) {
                        if (taskIdMem.containsKey(key)) {
                            taskIdMem.get(key).add(task.getTaskId());
                        }
                        else {
                            Set<String> taskids = new HashSet<>();
                            taskids.add(task.getTaskId());
                            taskIdMem.put(key, taskids);
                        }
                    }
                    if (task.getpTaskId() == null
                        || (StringUtils.isNotEmpty(task.getBusiType())
                            && task.getBusiType().equals(deal.getBusiType()))) {
                        BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                            ALLDETAIL_NO, BACKVALUE_YES, readerDto.getDetailSeqNo(), 3);
                        // 塞入list中
                        list.add(valBatchTaskTodo);
                        // 记录当前交易类型下的插入的任务id
                        if (taskIdAlready.containsKey(key)) {
                            taskIdAlready.get(key).add(task.getTaskId());
                        }
                        else {
                            Set<String> taskids = new HashSet<>();
                            taskids.add(task.getTaskId());
                            taskIdAlready.put(key, taskids);
                        }
                    } // 前序任务代码 != NULL AND 前序任务代码存在于结果集中
                    else if (task.getpTaskId() != null
                             && taskIdAlready.get(key).contains(task.getpTaskId())) {
                        BokBatchTaskTodo valBatchTaskTodo = this.getValBatchTaskTodo(deal, task,
                            ALLDETAIL_NO, BACKVALUE_YES, readerDto.getDetailSeqNo(), 3);
                        // 塞入list中
                        list.add(valBatchTaskTodo);
                        // 记录当前任务id
                        if (taskIdAlready.containsKey(key)) {
                            taskIdAlready.get(key).add(task.getTaskId());
                        }
                        else {
                            Set<String> taskids = new HashSet<>();
                            taskids.add(task.getTaskId());
                            taskIdAlready.put(key, taskids);
                        }
                    }
                });
            }
        }
        // 任务组执行ID
        BatchDispatchProcessorDto dto = new BatchDispatchProcessorDto();
        dto.setBokBatchTaskTodoList(list);
        dto.setProdId(readerDto.getDetailSeqNo() + GlobalConstant.SPLIT + readerDto.getBooksetId());
        return dto;
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
    private boolean isSkipTask(DealAccountingTodo deal, BatchTaskInfo task) {
		if (!deal.getBooksetType().equals(task.getBooksetType())) {
		    // 跳过
			return true;
		}
		if (DictCodeListExt1.跑批规则_按入账日最后执行.getValue().equals(task.getTaskExeRule())) {
			if (!DateUtil.isSameDate(deal.getBookDate(), deal.getHappenDate())) {
				// 跳过
				return true;
			}
		}
		if (DictCodeListExt1.跑批规则_按账套日最后执行.getValue().equals(task.getTaskExeRule())) {
			if (!(DateUtil.isSameDate(deal.getBookSetDate(), deal.getBookDate())
					&& DateUtil.isSameDate(deal.getBookSetDate(), deal.getHappenDate()))) {
				// 跳过
				return true;
			}
		}
		if (DictCodeListExt1.跑批规则_最初执行.getValue().equals(task.getTaskExeRule())) {
			if (!(DateUtil.isSameDate(deal.getBookSetDate(), deal.getStartDate())
					|| DateUtil.isSameDate(deal.getBookSetDate(), this.getBatchExecuteDetail().getStartDate())
							&& deal.getBookSetDate().after(deal.getStartDate()))) {
				// 跳过
				return true;
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
    private BokBatchTaskTodo getValBatchTaskTodo(DealAccountingTodo deal, BatchTaskInfo task,
                                                 String alldetailflag, String isBackValue,
                                                 String seqNo, int dim) {
        // 定义 批处理待处理任务 实体类
        BokBatchTaskTodo tmp = new BokBatchTaskTodo();
        tmp.setSeqNo(seqNo);// 批处理任务组执行ID = 直接取批处理优先信息中的第一个
        tmp.setTaskId(task.getTaskId());// 任务代码
        tmp.setBooksetId(deal.getBooksetId());// 账套代码
        tmp.setBooksetDate(deal.getBookSetDate());// 账套日期
        if (dim >= 2) {
            tmp.setBookDate(deal.getBookDate());// 入账日 = 取参数入账日
        }
        if (dim >= 3) {
            tmp.setHappenDate(deal.getHappenDate());// 发生日
        }
        // 如果是差量批处理，且该批需要账务明细代码则赋值
        if (ALLDETAIL_NO.equals(alldetailflag)
            && (task.getAllDetail() == null
                || !DictCodeList.是否_是.getValue().equals(task.getAllDetail()))) {
            tmp.setBokDetailId(deal.getBokDetailId());// 账务明细代码
        }
        tmp.setIsBackValue(isBackValue);// 是否倒起息
        tmp.setIsSubBookset(deal.getIsSubBookset());// 是否子账套
        return tmp;
    }
}
