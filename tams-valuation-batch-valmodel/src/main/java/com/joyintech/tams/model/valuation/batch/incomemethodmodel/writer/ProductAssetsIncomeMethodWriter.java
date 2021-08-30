/*
 * 文件名：ResetBaseRuleInfoWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.model.valuation.batch.incomemethodmodel.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dao.IncomeMethodMapper;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.AssetsMakePriceResultDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.ProductAssetsIncomeMethodResultDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.ValBondValuationInfoResultDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.service.ProductAssetsIncomeMethodProcessService;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.utils.DataHandleUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:收益法估值批处理Writer
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
@Scope("prototype")
public class ProductAssetsIncomeMethodWriter extends BaseWriter<ProductAssetsIncomeMethodResultDto> implements StepExecutionListener {

    private final String BATCH_PARAMS_KEY = "params";
    @Autowired
	private IncomeMethodMapper incomeMethodMapper;

    @Autowired
    private ProductAssetsIncomeMethodProcessService productAssetsIncomeMethodProcessService;

    private StepExecution stepExecution;

    private final String BATCH_LOG_RESULTS = "logResults";


    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution=stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends ProductAssetsIncomeMethodResultDto> items)
            throws Exception {
        @SuppressWarnings("unchecked")
        Map<String,Object> mapParam = this.getParams();
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)mapParam.get("BATCH_INFO");
        String taskId =(String)mapParam.get("TASK_ID");
        List<ProductAssetsIncomeMethodResultDto> resultList = (List<ProductAssetsIncomeMethodResultDto>)items;
        List<ValBondValuationInfoResultDto>   valuationResultList = new ArrayList<ValBondValuationInfoResultDto>();
        List<AssetsMakePriceResultDto>   makePriceResultList = new ArrayList<AssetsMakePriceResultDto>();
        List<ModValBatchLogResult> bBatchLogResultList = new ArrayList<>();
        try {
            if (!StringUtil.isEmptyList(resultList)) {
                for (ProductAssetsIncomeMethodResultDto incomeMethodResultDto : resultList) {
                    if(incomeMethodResultDto.getValBondValuationInfoResultDto() !=null){
                        //改为批量处理
                        valuationResultList.add(incomeMethodResultDto.getValBondValuationInfoResultDto());
                    }
                    if(incomeMethodResultDto.getAssetsMakePriceResultDto() !=null){
                        //test-yu
//                    incomeMethodResultDto.getAssetsMakePriceResultDto().setSecType("F18");
//                    incomeMethodResultDto.getAssetsMakePriceResultDto().setSecDetailType("09");
                        //改为批量处理
                        makePriceResultList.add(incomeMethodResultDto.getAssetsMakePriceResultDto());
                    }
                }
            }
            //债券估值行情批处理
            if (valuationResultList != null && valuationResultList.size() > 0) {
                deleteValuationResultPremium(valuationResultList);
                insertValuationResultPremium(valuationResultList,batchExecuteInfo);
            }
            //资产定价批操作
            if (makePriceResultList != null && makePriceResultList.size() > 0) {
                deleteMakePriceResultPremium(makePriceResultList);
                insertMakePriceResultPremium(makePriceResultList,batchExecuteInfo);
            }
            //=====记录成功日志=====start=====
            for(ValBondValuationInfoResultDto resultDto:valuationResultList){
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(resultDto.getFinprodId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_正常结束.getValue());
                batchParam.setMsg("【收益法模型批处理】:执行批处理【" + resultDto.getFinprodId()+ "】成功结束！");
                bBatchLogResultList.add(batchParam);
            }
            productAssetsIncomeMethodProcessService.sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录成功日志=====end=====


        } catch (Exception e) {
            //=====记录计算错误日志=====start=====
            for(ValBondValuationInfoResultDto resultDto:valuationResultList){
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(resultDto.getFinprodId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());//EXP 失败
                batchParam.setMsg("【收益法模型批处理】：计算错误");
                bBatchLogResultList.add(batchParam);
            }
            productAssetsIncomeMethodProcessService.sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录计算错误日志=====end=====
        }



    }




    /**
     * 插入债券估值行情结果集
     * @param valuationResultList
     */
    private void insertValuationResultPremium(List<ValBondValuationInfoResultDto> valuationResultList,SysBatchExecInfo batchExecuteInfo) {
        if (valuationResultList == null || valuationResultList.isEmpty()) {
            return;
        }
        List<ModValBatchParams> modValBatchParamsList = new ArrayList<>();
        // 定义一个固定大小插入存放集合
        List<List<ValBondValuationInfoResultDto>> list = DataHandleUtil.splitList(valuationResultList);
        for (List<ValBondValuationInfoResultDto> listInsert : list) {

            // TODO 测试 ， 记得删除
            for (ValBondValuationInfoResultDto dto : listInsert){
                dto.setUpdateUser("test007");
                dto.setCreateDept("test007");
            }

            if (CollectionUtils.isNotEmpty(listInsert)) {
                incomeMethodMapper.insertValuationResultPremium(listInsert);
            }
        }

    }

    /**
     * 删除债券估值行情结果集
     * @param listModRiskPremium
     */
    private void deleteValuationResultPremium(List<ValBondValuationInfoResultDto> listModRiskPremium) {
        if (listModRiskPremium == null || listModRiskPremium.isEmpty()) {
            return;
        }
        // 定义一个固定大小插入存放集合
        List<List<ValBondValuationInfoResultDto>> list = DataHandleUtil.splitList(listModRiskPremium);
        for (List<ValBondValuationInfoResultDto> listInsert : list) {
            if (CollectionUtils.isNotEmpty(listInsert)) {
                incomeMethodMapper.deleteValuationResultPremium(listInsert);
            }
        }
    }


    /**
     * 插入资产定价结果集
     * @param makePriceResultList
     */
    private void insertMakePriceResultPremium(List<AssetsMakePriceResultDto> makePriceResultList,SysBatchExecInfo batchExecuteInfo) {

        if (makePriceResultList == null || makePriceResultList.isEmpty()) {
            return;
        }
        //日志list
        List<ModValBatchParams> modValBatchParamsList = new ArrayList<>();
        // 定义一个固定大小插入存放集合
        List<List<AssetsMakePriceResultDto>> list = DataHandleUtil.splitList(makePriceResultList);
        for (List<AssetsMakePriceResultDto> listInsert : list) {

            // TODO 测试 ， 记得删除
            for (AssetsMakePriceResultDto dto : listInsert){
                dto.setUpdateUser("test007");
                dto.setCreateDept("test007");
            }

            if (CollectionUtils.isNotEmpty(listInsert)) {
                incomeMethodMapper.insertMakePriceResultPremium(listInsert);
            }
        }

    }

    /**
     * 删除资产定价结果集
     * @param makePriceResultList
     */
    private void deleteMakePriceResultPremium(List<AssetsMakePriceResultDto> makePriceResultList) {
        if (makePriceResultList == null || makePriceResultList.isEmpty()) {
            return;
        }
        // 定义一个固定大小插入存放集合
        List<List<AssetsMakePriceResultDto>> list = DataHandleUtil.splitList(makePriceResultList);
        for (List<AssetsMakePriceResultDto> listInsert : list) {
            if (CollectionUtils.isNotEmpty(listInsert)) {
                incomeMethodMapper.deleteMakePriceResultPremium(listInsert);
            }
        }
    }





}