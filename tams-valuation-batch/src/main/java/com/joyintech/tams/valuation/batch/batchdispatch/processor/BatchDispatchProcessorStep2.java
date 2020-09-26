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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDimensionConfig;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchProcessorDto;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchReaderDto;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchGrpPri;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.DimensionDto;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.DimensionValue;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年4月27日
 * @see BatchDispatchProcessorStep2
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchDispatchProcessorStep2 extends BaseProcessor<BatchDispatchReaderDto, NeedToWriteEntity> {
	

    /**
     * {@inheritDoc}
     */
    @Override
    public NeedToWriteEntity process(BatchDispatchReaderDto readerDto)
        throws Exception {
        // 任务组执行ID
        BatchDispatchProcessorDto dto = new BatchDispatchProcessorDto();
        dto.setDimensionDtoList(genDimensionDto(readerDto));
        dto.setProdId(readerDto.getDetailSeqNo());
        return dto;
    }
    
    private List<DimensionDto> genDimensionDto(BatchDispatchReaderDto readerDto){
        List<String> dimList = new ArrayList<>();
        DimensionDto dto = null;
        List<DimensionDto> dtoList = new ArrayList<>();
        Map<String, Set<String>> todoMap = new HashMap<>();
        Set<String> taskList = null;
        List<BatchDimensionConfig> dimConfigList = (List<BatchDimensionConfig>) this.getParams().get(BatchDimensionConfig.class.getName());
        for (BokBatchTaskTodo todo : readerDto.getBokBatchTaskTodoList()) {
            StringBuffer dim = new StringBuffer();
            dimList = createDimList(todo,dimConfigList);
            dimList.forEach(x -> {
                if (dim.length() > 0) {
                    dim.append(GlobalConstant.COMMA);
                }
                dim.append(StringUtil.getOrElse(x, BookConstant.NONE));
            });
            if (todoMap.containsKey(dim.toString())) {
                todoMap.get(dim.toString()).add(todo.getTaskId());
            }
            else {
                taskList = new HashSet<>();
                taskList.add(todo.getTaskId());
                todoMap.put(dim.toString(), taskList);
            }
        }
        
        // 任务组执行ID
        String detailSeqNo = readerDto.getDetailSeqNo();
        for (Map.Entry<String, Set<String>> entry : todoMap.entrySet()) {
            dimList = Arrays.asList(entry.getKey().split(GlobalConstant.COMMA));
            // 任务组实例ID
            String groupInsSeqNo = IDGenerator.generate();
            List<DimensionValue> dimValueList = genDimensionValue(dimList, groupInsSeqNo,
                dimConfigList);
            BatchGrpPri batchGrpPri = new BatchGrpPri(detailSeqNo, groupInsSeqNo,
                DateUtil.parse(dimList.get(0)), BigDecimal.ONE);
            List<SysBatchJob> batchJobList = genBatchJob(groupInsSeqNo, entry.getValue());
            dto = new DimensionDto();
            dto.setDimValueList(dimValueList);
            dto.setBatchGrpPri(batchGrpPri);
            dto.setBatchJobList(batchJobList);
            dtoList.add(dto);
        }
//        if(!dtoList.isEmpty()) {
//            dtoList.add(genBooksetCommonJob(detailSeqNo));
//        }
        return dtoList;
    }
    

//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @return
//     * @see
//     * @since 1.0
//     */
//    private DimensionDto genBooksetCommonJob(String detailSeqNo) {
//        DimensionDto dimDto = new DimensionDto();
//        Date startDate = this.getBatchExecuteDetail().getStartDate();
//        Date endDate = this.getBatchExecuteDetail().getEndDate();
//        while (!startDate.after(endDate)) {
//            String groupInsSeqNo = IDGenerator.generate();
//            List<DimensionValue> dimValueList = new ArrayList<>();
//            DimensionValue item = new DimensionValue(groupInsSeqNo, "1",
//                DateUtil.format(startDate));
//            dimValueList.add(item);
//
//            BatchGrpPri batchGrpPri = new BatchGrpPri(detailSeqNo, groupInsSeqNo, startDate,
//                BigDecimal.ONE);
//            
//            Set<String> taskList = new HashSet();
//            taskList.add("100");
//            taskList.add("120");
//            List<SysBatchJob> batchJobList = genBatchJob(groupInsSeqNo, taskList);
//            
//            dimDto.setDimValueList(dimValueList);
//            dimDto.setBatchGrpPri(batchGrpPri);
//            dimDto.setBatchJobList(batchJobList);
//            startDate = DateUtil.addDay(startDate, 1);
//        }
//        return dimDto;
//    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param todo
     * @return
     * @see
     * @since 1.0
     */
    private List<String> createDimList(BokBatchTaskTodo todo,List<BatchDimensionConfig> dimConfigList) {
        List<String> dimList = new ArrayList<>();
//        Class c1 = todo.getClass();
//		for (BatchDimensionConfig config : dimConfigList) {
//			try {
//				Method m1 = c1.getDeclaredMethod("get" + underlineToCamel(config.getDimensionName()));
//				dimList.add((String) m1.invoke(todo));
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//			} 
//		}
        // 暂时不要这个维度
		// dimList.add(todo.getBooksetId());
        dimList.add(DateUtil.format(todo.getBooksetDate()));
        dimList.add(DateUtil.format(todo.getBookDate()));
        dimList.add(DateUtil.format(todo.getHappenDate()));
        dimList.add(todo.getIsSubBookset());
        return dimList;
    }

//    private String underlineToCamel(String param) {
//        if (param == null || "".equals(param.trim())) {
//            return param;
//        }
//        param = param.toLowerCase();
//        int len = param.length();
//        StringBuilder sb = new StringBuilder(len);
//        for (int i = 0; i < len; i++ ) {
//            char c = param.charAt(i);
//            if (c == '_') {
//                if (++i < len) {
//                    sb.append(Character.toUpperCase(param.charAt(i)));
//                }
//            }
//            else if(i == 0) {
//            	sb.append(Character.toUpperCase(param.charAt(i)));
//            } else {
//            	sb.append(c);
//            }
//        }
//        return sb.toString();
//    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param groupInsSeqNo
     * @param value
     * @return
     * @see
     * @since 1.0
     */
    private List<SysBatchJob> genBatchJob(String groupInsSeqNo, Set<String> taskList) {
        List<SysBatchJob> result = new ArrayList<>();
        taskList.forEach(x -> {
            SysBatchJob sysBatchJob = new SysBatchJob();
            sysBatchJob.setBatchSeqNo(groupInsSeqNo);
            sysBatchJob.setTaskId(x);
            result.add(sysBatchJob);
        });
        return result;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param groupInsSeqNo
     * @param value
     * @return
     * @see
     * @since 1.0
     */
//    private BatchGrpPri genBatchGrpPri(String detailSeqNo, String groupInsSeqNo,
//                                       String executeDate) {
//        BatchGrpPri batchGrpPri = new BatchGrpPri(detailSeqNo, groupInsSeqNo,
//            DateUtil.parse(executeDate), BigDecimal.ONE);
//        batchGrpPri.setDetailSeqNo(detailSeqNo);
//        batchGrpPri.setGroupSeqNo(groupInsSeqNo);
//        batchGrpPri.setExecuteDate(DateUtil.parse(executeDate));
//        batchGrpPri.setPri(BigDecimal.ONE);
//        
//        return batchGrpPri;
//    }

    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @see
     * @since 1.0
     */
    private List<DimensionValue> genDimensionValue(List<String> dimList, String groupInsSeqNo,
                                                   List<BatchDimensionConfig> dimConfigList) {
        List<DimensionValue> result = new ArrayList<>();
        for (int i = 0; i < dimList.size(); i++ ) {
            if (!BookConstant.NONE.equals(dimList.get(i))) {
                DimensionValue dimValue = new DimensionValue(groupInsSeqNo,
                    dimConfigList.get(i).getSeqNo(), dimList.get(i));
                result.add(dimValue);
            }
        }
        return result;
    }
}
