package com.joyintech.tams.model.impairment.batch.datapusheclresult.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dao.DataPushEclResultMapper;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResult;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResultProcessDto;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResultReaderDto;
import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.ImpairmentBatchSynParam;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:数据推送批处理Service
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class DataPushEclResultProcessService extends BaseService {
    private final String BATCH_LOG_RESULTS = "logResults";
    @Autowired
    private DataPushEclResultMapper dataPushEclResultMapper;
    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public AssetLossResultProcessDto execute(AssetLossResultReaderDto item, Map<String, Object> mapParam) {
        //数据推送(调用投管微服务【减值模型结果同步】)
        AssetLossResultProcessDto valuationBatchSynResultDto = new AssetLossResultProcessDto();
        if(item !=null ){
            valuationBatchSynResultDto.setAssetLossResult(item.getAssetLossResult());
        }
        return valuationBatchSynResultDto;
    }

    /**
     * @Description:数据推送
     * @auther: yuzj
     * @date: 2021/7/20
     * @param:
     * @return:
     */
    public Integer  sendEclResultResult(List<AssetLossResultProcessDto> items){
        ImpairmentBatchSynParam param = new ImpairmentBatchSynParam();
        List<AssetLossResult> assetLossResultList = new ArrayList<>();
        for(AssetLossResultProcessDto assetLossResultProcess :items){
            AssetLossResult assetLossResult = new AssetLossResult();
            assetLossResult=assetLossResultProcess.getAssetLossResult();
            assetLossResultList.add(assetLossResult);
        }
        param.setSynList(assetLossResultList);
        Integer result = impairmentModReultSyn(param);
        return result;
    }


    /**
     * @Description:保存上下文日志信息
     * @auther: yuzj
     * @date: 2021/7/27
     * @param:
     * @return:
     */
    public  void  sendlogResults(List<ModValBatchLogResult> bBatchLogResultList, StepExecution stepExecution){
        if(CollectionUtils.isNotEmpty(bBatchLogResultList)){
            if( stepExecution.getJobExecution().getExecutionContext().get("BATCH_LOG_RESULTS") !=null){
                List<ModValBatchLogResult> oldLogResultList = (List<ModValBatchLogResult>)stepExecution.getJobExecution().getExecutionContext().get("BATCH_LOG_RESULTS");
                oldLogResultList.addAll(bBatchLogResultList);
                stepExecution.getJobExecution().getExecutionContext().put(BATCH_LOG_RESULTS,oldLogResultList);
            }else{
                stepExecution.getJobExecution().getExecutionContext().put(BATCH_LOG_RESULTS,bBatchLogResultList);
            }
        }
    }

    public int impairmentModReultSyn(ImpairmentBatchSynParam param){
        //判断结果对象是否大于0,删除资产减值结果
        if(param.getSynList().size()>0){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("delDate",param.getSynDate());
            map.put("ALLOW_MODEL_COVER_Y", DictCodeListExt1.是否_是.getValue());
            dataPushEclResultMapper.deleteModEclResultByConditions(map);
            List<AssetLossResult> batchList = param.getSynList();
            for(AssetLossResult dto : batchList){
                dataPushEclResultMapper.insertSelective(dto);
            }
        }
        return 0;
    }



}
