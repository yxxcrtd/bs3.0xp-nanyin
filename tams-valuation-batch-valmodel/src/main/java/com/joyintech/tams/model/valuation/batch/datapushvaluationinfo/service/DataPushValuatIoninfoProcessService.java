package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dao.DataPushValuatIoninfoMapper;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class DataPushValuatIoninfoProcessService extends BaseService {
    private final String BATCH_LOG_RESULTS = "logResults";
    @Autowired
    private DataPushValuatIoninfoMapper dataPushValuatIoninfoMapper;

    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public ValuationBatchSynResultDto execute(ValuationBatchSynDto item, Map<String, Object> mapParam) {
        //数据推送(调用投管微服务【估值模型结果同步】)
        ValuationBatchSynResultDto valuationBatchSynResultDto = new ValuationBatchSynResultDto();
        BeanUtils.copyProperties(item,valuationBatchSynResultDto);

        return valuationBatchSynResultDto;
    }

    /**
     * @Description:数据推送
     * @auther: yuzj
     * @date: 2021/7/12
     * @param:
     * @return:
     */
    public Integer  sendValuationInfoResult(List<ValuationBatchSynResultDto> items){
        ValuationBatchSynParam param = new ValuationBatchSynParam();
        List<ValuationBatchSynDto> valuationBatchSynList = new ArrayList<>();
        for(ValuationBatchSynResultDto synResultDto :items){
            ValuationBatchSynDto synDto = new ValuationBatchSynDto();
            BeanUtils.copyProperties(synResultDto,synDto);
            valuationBatchSynList.add(synDto);
        }
        param.setSynList(valuationBatchSynList);
        /*Integer result = restTemplate.postForObject(
                ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/ModelCallOnInvest/valModelResultSyn", param,
                Integer.class);*/
        Integer result = valModelResultSyn(param);
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

    /**
     * 估值结果批处理结果同步业务方法
     * @param param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int valModelResultSyn(ValuationBatchSynParam param) {
        //债券估值行情
        List<MstBondQuotationInfoDto> valuationList = new ArrayList<MstBondQuotationInfoDto>();
        //项目类资产估值行情
        List<FinProductProjectValuation> finList = new ArrayList<FinProductProjectValuation>();
        // 场外金融产品行情信息
        List<FinProductValuation> finProductValuations = new ArrayList<FinProductValuation>() ;
        //批处理同步估值行情list
        List<ValuationBatchSynDto> synList = param.getSynList();
        // 场外金融产品
        try {
            for(ValuationBatchSynDto dto : synList) {
                //查询标的信息
                FinProduct finProduct = dataPushValuatIoninfoMapper.selectByPrimaryKey(dto.getFinprodId());
                if(null != finProduct) {
                    if(DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(finProduct.getFinprodType())) {
                        MstBondQuotationInfoDto bondDto = new MstBondQuotationInfoDto();
                        bondDto.setSecId(finProduct.getFinprodMarketId()); //市场代码
                        bondDto.setValueDate(dto.getValueDate());//估值日
                        bondDto.setValueSource(DictCodeListExt1.估值来源_模型法_MOD.getValue());//估值来源
                        bondDto.setPrice(dto.getPrice()); //估值全价
                        bondDto.setNetprice(dto.getNetprice());//估值净价
                        bondDto.setInputType("wind"); //录入方式 待定 测试 interf
                        bondDto.setCreateUser("batchSyn"); //
                        bondDto.setUpdateUser("batchSyn");
                        bondDto.setCreateTime(DateUtil.now());
                        bondDto.setUpdateTime(DateUtil.now());
                        valuationList.add(bondDto);
                    }else if(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue().equals(finProduct.getFinprodType())){ //场外金融产品估值
                        FinProductProjectValuation finDto = new FinProductProjectValuation();
                        finDto.setFinprodId(finProduct.getFinprodMarketId()); //市场代码
                        finDto.setFinprodType(finProduct.getFinprodType()); // 标的信息.金融产品类型（估值核算）
                        finDto.setFinprodType2(finProduct.getFinprodType2());// 标的信息.金融产品类型（投管分类）
                        finDto.setValueSource(DictCodeListExt1.估值来源_模型法_MOD.getValue());
                        finDto.setValDate(dto.getValueDate());
                        finDto.setPublishDate(dto.getValueDate());
                        finDto.setValuation(dto.getNetprice());
                        finDto.setCreateUser("batchSyn"); //
                        finDto.setUpdateUser("batchSyn");
                        finDto.setCreateTime(DateUtil.now());
                        finDto.setUpdateTime(DateUtil.now());
                        finList.add(finDto);
                    }else{ // 场外金融产品行情
                        FinProductValuation finDto = new FinProductValuation();
                        finDto.setFinprodId(finProduct.getFinprodMarketId()); //市场代码
                        finDto.setFinprodType(finProduct.getFinprodType()); // 标的信息.金融产品类型（估值核算）
                        finDto.setFinprodType2(finProduct.getFinprodType2());// 标的信息.金融产品类型（投管分类）
                        finDto.setValueSource(DictCodeListExt1.估值来源_模型法_MOD.getValue());
                        finDto.setValDate(dto.getValueDate());
                        finDto.setPublishDate(dto.getValueDate());
                        finDto.setValuation(dto.getNetprice());
                        finDto.setCreateUser("batchSyn"); //
                        finDto.setUpdateUser("batchSyn");
                        finDto.setCreateTime(DateUtil.now());
                        finDto.setUpdateTime(DateUtil.now());
                        finProductValuations.add(finDto);
                    }
                }
            }
        }catch(Exception e) {
        }finally {

        }
        //估值行情信息先删后插
        if(valuationList.size()>0) {
            //删除
            this.deleteBondQuotationInfo(valuationList);
            // 插入
            this.insertBondQuotationInfo(valuationList);
        }
        // 项目类资产行情信息先删后插
        if(finList.size()>0) {
            //删除
            this.deleteFinProjectValuation(finList);
            //保存
            this.insertFinProjectValuation(finList);
        }
        //场外金融产品估值先删后插
        if(finProductValuations.size()>0) {
            // 删除
            this.deletFinProductVal(finProductValuations);
            // 插入
            this.insertFinProductVal(finProductValuations);
        }
        return 0;
    }


    /**
     * 删除估值行情信息
     * @param list
     */
//	@DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void  deleteBondQuotationInfo(List<MstBondQuotationInfoDto> list) {
        list.forEach(item ->{
            dataPushValuatIoninfoMapper.deleteBondByConditions(item);
        });
    }

    /**
     * 插入估值行情信息
     * @param list
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void insertBondQuotationInfo(List<MstBondQuotationInfoDto> list) {
        list.forEach(item ->{
            dataPushValuatIoninfoMapper.insertSelectiveBondQuotationInfo(item);
        });
    }

    //删除项目类资产行情信息
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void deleteFinProjectValuation(List<FinProductProjectValuation> finList) {
        finList.forEach(item->{
            dataPushValuatIoninfoMapper.deleteFinProductProjectByConditions(item);
        });
    }

    // 插入项目类资产行情信息
    public void insertFinProjectValuation(List<FinProductProjectValuation> finList) {
        finList.forEach(item->{
            dataPushValuatIoninfoMapper.insertSelectiveFinProject(item);
        });
    }

    /**
     * 删除场外金融产品估值
     * @param finList
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void deletFinProductVal(List<FinProductValuation> finList) {
        finList.forEach(item ->{
            dataPushValuatIoninfoMapper.deleteFinProductByConditions(item);
        });
    }

    /**
     * 插入场外金融产品行情
     * @param finList
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void insertFinProductVal(List<FinProductValuation> finList) {
        finList.forEach(item ->{
            dataPushValuatIoninfoMapper.insertSelectiveFinProduct(item);
        });
    }




}
