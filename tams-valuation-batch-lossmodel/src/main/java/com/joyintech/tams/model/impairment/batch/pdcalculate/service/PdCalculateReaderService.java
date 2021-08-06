package com.joyintech.tams.model.impairment.batch.pdcalculate.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModEclResultProcess;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModValBatchLogResult;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModValBatchParams;
import com.joyintech.tams.model.impairment.batch.batchtask.service.ModParamsService;
import com.joyintech.tams.model.impairment.batch.common.constants.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dao.PdCalculateMapper;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.AssetGroupPdCalculateReaderDto;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:减值-PD计算批处理Service
 * @auther: yuzj
 * @date: 2021/7/14
 * @param:
 * @return:
 */
@Service
public class PdCalculateReaderService {
    @Autowired
    private PdCalculateMapper pdCalculateMapper;
    @Autowired
    private PdCalculateProcessService pdCalculateProcessService;
    @Autowired
    private ModParamsService modParamsService;


    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/14
     * @param:
     * @return:
     */
    public List<AssetGroupPdCalculateReaderDto> execute(Map<String, Object> mapParams, StepExecution stepExecution){
        List<AssetGroupPdCalculateReaderDto> assetGroupPdCalculateResultList = new ArrayList<>();
        List<ModValBatchLogResult> bBatchLogResultList = new ArrayList<>();
        Date plateDate = (Date)mapParams.get("PLATE_DATE");//平台日
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)mapParams.get("BATCH_INFO");
        String taskId =(String)mapParams.get("TASK_ID");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }
        //1.根基跑批日查询出需要PD计算的交易信息列表(资产代码不可能为空)
        List<ModEclResultProcess> AssetImpairmentPdCalculateList = pdCalculateMapper.selectWaitPdCalculateList(plateDate,finprodIdList);
        try {


//            //注释原因：1）了解到正常流程不会出现无数据；2）此种异常无法获取资产代码，保存异常信息资产代码必填，不可缺失；
//            if (CollectionUtils.isEmpty(AssetImpairmentPdCalculateList)) {
//                //=====记录计算错误日志=====start=====
//                for(ModEclResultProcess processDto:AssetImpairmentPdCalculateList){
//                    ModValBatchLogResult batchParam = new ModValBatchLogResult();
//                    batchParam.setFinprodId(processDto.getProdId());
//                    batchParam.setTaskId(taskId);
//                    batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
//                    batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());//EXP 失败
//                    batchParam.setMsg(" "【PD计算批处理】：PD未维护");
//                    bBatchLogResultList.add(batchParam);
//                }
//                pdCalculateProcessService.sendlogResults(bBatchLogResultList);
//                //=====记录计算错误日志=====end=====
//                return null;

            //2.构建输出列表(组装为资产交易信息列表)
            //2.1根据资产代码分组为Map<String, List<ModEclResultProcess>>
            Map<String, List<ModEclResultProcess>> bmListMap = new HashMap<String, List<ModEclResultProcess>>();
            bmListMap = AssetImpairmentPdCalculateList.stream().filter(map -> map.getAssetId() != null)
                    .collect(Collectors.groupingBy(ModEclResultProcess::getAssetId));
            //2.2将Map<String, List<ModEclResultProcess>>转为List<AssetGroupPdCalculateReaderDto>
            for (Map.Entry<String, List<ModEclResultProcess>> entry : bmListMap.entrySet()) {
                AssetGroupPdCalculateReaderDto groupDto = new AssetGroupPdCalculateReaderDto();
                groupDto.setAssetId(entry.getKey());
                groupDto.setAssetImpairmentPdCalculateList(entry.getValue());
                assetGroupPdCalculateResultList.add(groupDto);
            }
        }catch(Exception e){
            //=====记录计算错误日志=====start=====
            for(ModEclResultProcess modEclResultProcess:AssetImpairmentPdCalculateList){
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(modEclResultProcess.getAssetId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                batchParam.setMsg("【PD计算批处理】：计算错误");
                bBatchLogResultList.add(batchParam);
            }
            pdCalculateProcessService.sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录计算错误日志=====end=====
            return null;
        }


        return assetGroupPdCalculateResultList;
    }




}
