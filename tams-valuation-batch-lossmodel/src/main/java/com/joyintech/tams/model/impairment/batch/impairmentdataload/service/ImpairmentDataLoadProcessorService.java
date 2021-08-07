package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.impairment.batch.common.calculation.ImpairmentCalculationCommonService;
import com.joyintech.tams.model.impairment.batch.common.constants.GlobalConstant;
import com.joyintech.tams.model.impairment.batch.common.constants.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import com.joyintech.tams.valuation.common.dto.modelDto.ModImpairRateParamsDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModRateDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ImpairmentDataLoadProcessorService {
    private static final Logger logger = LoggerFactory.getLogger(ImpairmentDataLoadProcessorService.class);
    /**
     * 应收款流水处理Mapper
     */
    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;

    @Autowired
    private ImpairmentCalculationCommonService impairmentCalculationCommonService;


    /**
     * 减值模型数据加载-processor
     *
     * @param params
     * @return
     */
    public ImpairmentDataLoadStep1ResultDto excute(ImpairmentDataLoadStep1ReaderDto entity, Map<String, Object> params, StepExecution stepExecution) {
        ImpairmentDataLoadStep1ResultDto result = new ImpairmentDataLoadStep1ResultDto();
        ModConfigUseage modConfigUseage = new ModConfigUseage();
        ModEclResultProcess modEclResultProcess = new ModEclResultProcess();
        result.setModConfigUseage(modConfigUseage);
        result.setModEclResultProcess(modEclResultProcess);
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        List<CommonErrorParams> errors = (List<CommonErrorParams>) stepExecution.getJobExecution().getExecutionContext().get("ERRORS");
        if (errors == null) {
            errors = new ArrayList<CommonErrorParams>();
        }
        //结果集封装
        quaryMainResult(entity, modEclResultProcess, platDate);
        //1、对初始数据进行模型第一次装载（针对每一笔交易装载评级参数、当前最新评级结果、买入时点评级结果）
        //1.1 过滤交易列表
        if (!ModelDictCodeList.交易方向_买入.getValue().equals(entity.getPs())) {
            return result;
        }

        //1.3 查询资产评级配置
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("MODEL_DEC_CONFIG_TYPE_02", ModelDictCodeList.模型_减值配置类型_特殊资产评级配置.getValue());
        para.put("MODEL_DEC_CONFIG_TYPE_01", ModelDictCodeList.模型_减值配置类型_资产大类评级配置.getValue());
        para.put("MODEL_MAPPING_TYPE_01", ModelDictCodeList.模型_映射类型_资产分类映射.getValue());
        para.put("finprodId", entity.getFinprodId());
        para.put("assetOne", entity.getAssetOne());
        para.put("assetTwo", entity.getAssetTwo());
        para.put("assetThree", entity.getAssetThree());
        para.put("assetFour", entity.getAssetFour());
        para.put("assetFive", entity.getAssetFive());
        List<ModAssetGradeConfig> listModAssetGradeConfig = impairmentDataLoadMapper.selectModAssetGradeConfig(para);
        if (listModAssetGradeConfig != null && listModAssetGradeConfig.size() > 0) {
            //定义 配置流水号 = LGD参数Dto.流水号
            modConfigUseage.setConfigSeqno(listModAssetGradeConfig.get(0).getSeqno());
            //定义 配置类型 = LGD参数Dto.配置类型
            modConfigUseage.setConfigType(listModAssetGradeConfig.get(0).getAssetType());
            //交易信息.评级类型 = 评级参数Dto.评级类型
            entity.setoType(listModAssetGradeConfig.get(0).getoType());
            //交易信息.评级机构 = 评级参数Dto.评级机构
            entity.setGradeOrgId(listModAssetGradeConfig.get(0).getGradeOrgId());
        } else {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entity.getFinprodId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【数据加载批处理】：未能匹配到相应的评级配置信息");
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            return new ImpairmentDataLoadStep1ResultDto();
        }

        //1.4 从系统缓存中获取最新的评级结果
        Map<String, String> mapRating = new HashMap<String, String>();
        if (CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class) != null) {
            mapRating = CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class);
        }

        //1.5 装载交易数据的最新评级结果
        quaryNewRatingInfo(mapRating, entity, platDate);

        if (StringUtil.isEmpty(entity.getResultCurrent())) {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entity.getFinprodId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【数据加载批处理】：未能匹配到相应的评级结果");
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            return new ImpairmentDataLoadStep1ResultDto();
        }
        //1.6 装载交易数据的买入时点评级结果
        getResultDeal(entity, platDate);

        //2、加载减值配置使用情况表
        quaryModConfigUseage(modConfigUseage);

        //封装减值结果过程表（评级信息）
        quaryMainResultRating(entity, modEclResultProcess);

        return result;
    }

    /**
     * 封装减值结果过程表（评级信息）
     * @param entity
     * @param modEclResultProcess
     */
    private void quaryMainResultRating(ImpairmentDataLoadStep1ReaderDto entity, ModEclResultProcess modEclResultProcess) {
        //评级类型
        modEclResultProcess.setoType(entity.getoType());
        //评级机构
        modEclResultProcess.setGradeOrgId(entity.getGradeOrgId());
        //债项评级结果（最新）
        modEclResultProcess.setResult01(entity.getResult01());
        //债券主体评级结果（最新）
        modEclResultProcess.setResult02(entity.getResult02());
        //发行人评级结果（最新）
        modEclResultProcess.setResultIssuer(entity.getResultIssuer());
        //融资人评级结果（最新）
        modEclResultProcess.setResultFinancier(entity.getResultFinancier());
        //担保人评级结果（最新）
        modEclResultProcess.setResultSponsor(entity.getResultSponsor());
        //实际授信主体评级结果（最新）
        modEclResultProcess.setResultActualCredit(entity.getResultActualCredit());
        //买入时点评级结果
        modEclResultProcess.setResultDeal(entity.getResultDeal());
        //最新评级结果
        modEclResultProcess.setResultCurrent(entity.getResultCurrent());
        //最终使用评级类型
        modEclResultProcess.setoTypeFinal(entity.getoTypeFinal());
        //最终使用主体机构
        modEclResultProcess.setOrgIdFinal(entity.getOrgIdFinal());
        //主体是否持有金融牌照
        modEclResultProcess.setIsFinLicence(entity.getIsFinLicence());
    }

    /**
     * 加载减值配置使用情况表
     * @param modConfigUseage
     */
    private void quaryModConfigUseage(ModConfigUseage modConfigUseage) {
        //2.1 判断使用的参数是否已存在减值配置使用情况表
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("configSeqno", modConfigUseage.getConfigSeqno());
        para.put("configType", modConfigUseage.getConfigType());
        int resultCount= impairmentDataLoadMapper.selectModConfigUseageCount(para);
        if (resultCount == 0) {
            modConfigUseage.setSeqno(IDGenerator.generate());
            modConfigUseage.setUseProcess(ModelDictCodeList.模型_减值过程_数据加载.getValue());
        }
    }

    /**
     * 装载交易数据的买入时点评级结果
     * @param entity
     * @param platDate
     */
    private void getResultDeal(ImpairmentDataLoadStep1ReaderDto entity, Date platDate) {
        if (platDate.compareTo(entity.getSettleDate()) == 0) {
            //交易数据.买入时点评级结果 = 交易数据.最新评级结果
            entity.setResultDeal(entity.getResultCurrent());
        } else {
            //查询昨日评级结果
            Map<String, Object> para = new HashMap<String, Object>();
            para.put("tradeId", entity.getTradeId());
            para.put("gradeOrgId", entity.getGradeOrgId());
            para.put("oType", entity.getoType());
            para.put("searchDate", DateUtil.addDay(platDate, -1));
            para.put("process", ModelDictCodeList.模型_减值过程_数据加载.getValue());
            String resultDeal = impairmentDataLoadMapper.selectResultDeal(para);
            entity.setResultDeal(resultDeal);
        }
    }

    /**
     * 装载交易数据的最新评级结果
     * @param mapRating
     * @param entity
     */
    private void quaryNewRatingInfo(Map<String, String> mapRating, ImpairmentDataLoadStep1ReaderDto entity, Date platDate) {
        //交易数据.债项评级结果 = 新评级结果Map.get(交易数据.市场代码 + "_" + "02" + "_" + 交易数据.评级机构);
        String ratingInfoDto1 = mapRating.get(entity.getFinprodMarketId() + GlobalConstant.UNDER_LINE
                + ModelDictCodeList.模型_评级类型_债项评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
        entity.setResult01(ratingInfoDto1);
        //交易数据.债券主体评级结果 = 新评级结果Map.get(交易数据.市场代码 + "_" + "01" + "_" + 交易数据.评级机构);
        String ratingInfoDto2 = mapRating.get(entity.getFinprodMarketId() + GlobalConstant.UNDER_LINE
                + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
        entity.setResult02(ratingInfoDto2);
        //交易数据.发行人评级结果 = 最新评级结果Map.get(交易数据.发行人 + "_" + "01" + "_" + 交易数据.评级机构);
        if (StringUtil.isNotEmpty(entity.getIssuer())) {
            String ratingInfoDto3 = mapRating.get(entity.getIssuer() + GlobalConstant.UNDER_LINE
                    + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
            entity.setResultIssuer(ratingInfoDto3);
        }
        //交易数据.融资人评级结果 = 最新评级结果Map.get(交易数据.融资人 + "_" + "01" + "_" + 交易数据.评级机构);
        if (StringUtil.isNotEmpty(entity.getFinancier())) {
            String ratingInfoDto3 = mapRating.get(entity.getFinancier() + GlobalConstant.UNDER_LINE
                    + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
            entity.setResultFinancier(ratingInfoDto3);
        }
        //交易数据.担保人评级结果 = 最新评级结果Map.get(交易数据.担保人 + "_" + "01" + "_" + 交易数据.评级机构);
        if (StringUtil.isNotEmpty(entity.getSponsor())) {
            String ratingInfoDto3 = mapRating.get(entity.getSponsor() + GlobalConstant.UNDER_LINE
                    + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
            entity.setResultSponsor(ratingInfoDto3);
        }
        //交易数据.实际授信主体评级结果 = 最新评级结果Map.get(交易数据.实际授信主体 + "_" + "01" + "_" + 交易数据.评级机构);
        if (StringUtil.isNotEmpty(entity.getActualCreditProvid())) {
            String ratingInfoDto3 = mapRating.get(entity.getActualCreditProvid() + GlobalConstant.UNDER_LINE
                    + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
            entity.setResultActualCredit(ratingInfoDto3);
        }

        //如果评级类型不是债项评级,则需要针对评级机构进行区分
        if (ModelDictCodeList.评级机构_资管内部.getValue().equals(entity.getGradeOrgId())) {
            //交易数据.最终使用评级类型 = "01 主体评级"
            entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_主体评级.getValue());
            if (ModelDictCodeList.模型_证券品种_债券.getValue().equals(entity.getSecTypeModel())
                    && (ModelDictCodeList.模型_证券细分类_交易商协会ABN.getValue().equals(entity.getSecDetailTypeModel())
                    || ModelDictCodeList.模型_证券细分类_银保监会主管ABS.getValue().equals(entity.getSecDetailTypeModel())
                    || ModelDictCodeList.模型_证券细分类_证监会主管ABS.getValue().equals(entity.getSecDetailTypeModel()))) {
                if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 交易数据.实际授信主体评级结果
                    entity.setResultCurrent(entity.getResultActualCredit());
                    //交易数据.最终使用主体机构 = 交易数据.实际授信主体
                    entity.setOrgIdFinal(entity.getActualCreditProvid());
                    //交易数据.主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                    entity.setIsFinLicence(entity.getActualCreditFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 交易数据.担保人评级结果
                    entity.setResultCurrent(entity.getResultSponsor());
                    //交易数据.最终使用主体机构 = 交易数据.担保人
                    entity.setOrgIdFinal(entity.getSponsor());
                    //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                    entity.setIsFinLicence(entity.getSponsorFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                    if (StringUtil.isNotEmpty(entity.getResultActualCredit()) && StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        if (impairmentCalculationCommonService.getPoorRating(entity.getResultActualCredit(), entity.getResultSponsor()) >= 0) {
                            //交易数据.最新评级结果 = 交易数据.实际授信主体评级结果
                            entity.setResultCurrent(entity.getResultActualCredit());
                            //交易数据.最终使用主体机构 = 交易数据.实际授信主体
                            entity.setOrgIdFinal(entity.getActualCreditProvid());
                            //交易数据.主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                            entity.setIsFinLicence(entity.getActualCreditFinLicence());
                        } else {
                            //交易数据.最新评级结果 = 交易数据.担保人评级结果
                            entity.setResultCurrent(entity.getResultSponsor());
                            //交易数据.最终使用主体机构 = 交易数据.担保人
                            entity.setOrgIdFinal(entity.getSponsor());
                            //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                            entity.setIsFinLicence(entity.getSponsorFinLicence());
                        }
                    } else if (StringUtil.isNotEmpty(entity.getResultActualCredit())) {
                        //交易数据.最新评级结果 = 交易数据.实际授信主体评级结果
                        entity.setResultCurrent(entity.getResultActualCredit());
                        //交易数据.最终使用主体机构 = 交易数据.实际授信主体
                        entity.setOrgIdFinal(entity.getActualCreditProvid());
                        //交易数据.主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                        entity.setIsFinLicence(entity.getActualCreditFinLicence());
                    } else if (StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        //交易数据.最新评级结果 = 交易数据.担保人评级结果
                        entity.setResultCurrent(entity.getResultSponsor());
                        //交易数据.最终使用主体机构 = 交易数据.担保人
                        entity.setOrgIdFinal(entity.getSponsor());
                        //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                        entity.setIsFinLicence(entity.getSponsorFinLicence());
                    }
                } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                    if (StringUtil.isNotEmpty(entity.getResultActualCredit()) && StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        if (impairmentCalculationCommonService.getPoorRating(entity.getResultActualCredit(), entity.getResultSponsor()) <= 0) {
                            //交易数据.最新评级结果 = 交易数据.实际授信主体评级结果
                            entity.setResultCurrent(entity.getResultActualCredit());
                            //交易数据.最终使用主体机构 = 交易数据.实际授信主体
                            entity.setOrgIdFinal(entity.getActualCreditProvid());
                            //交易数据.主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                            entity.setIsFinLicence(entity.getActualCreditFinLicence());
                        } else {
                            //交易数据.最新评级结果 = 交易数据.担保人评级结果
                            entity.setResultCurrent(entity.getResultSponsor());
                            //交易数据.最终使用主体机构 = 交易数据.担保人
                            entity.setOrgIdFinal(entity.getSponsor());
                            //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                            entity.setIsFinLicence(entity.getSponsorFinLicence());
                        }
                    } else if (StringUtil.isNotEmpty(entity.getResultActualCredit())) {
                        //交易数据.最新评级结果 = 交易数据.实际授信主体评级结果
                        entity.setResultCurrent(entity.getResultActualCredit());
                        //交易数据.最终使用主体机构 = 交易数据.实际授信主体
                        entity.setOrgIdFinal(entity.getActualCreditProvid());
                        //交易数据.主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                        entity.setIsFinLicence(entity.getActualCreditFinLicence());
                    } else if (StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        //交易数据.最新评级结果 = 交易数据.担保人评级结果
                        entity.setResultCurrent(entity.getResultSponsor());
                        //交易数据.最终使用主体机构 = 交易数据.担保人
                        entity.setOrgIdFinal(entity.getSponsor());
                        //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                        entity.setIsFinLicence(entity.getSponsorFinLicence());
                    }
                }
            } else if (ModelDictCodeList.模型_证券品种_项目类资产.getValue().equals(entity.getSecTypeModel())) {
                if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 交易数据.实际授信主体评级结果
                    entity.setResultCurrent(entity.getResultActualCredit());
                    //交易数据.最终使用主体机构 = 交易数据.实际授信主体
                    entity.setOrgIdFinal(entity.getActualCreditProvid());
                    //交易数据.主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                    entity.setIsFinLicence(entity.getActualCreditFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 交易数据.担保人评级结果
                    entity.setResultCurrent(entity.getResultSponsor());
                    //交易数据.最终使用主体机构 = 交易数据.担保人
                    entity.setOrgIdFinal(entity.getSponsor());
                    //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                    entity.setIsFinLicence(entity.getSponsorFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_融资人.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 交易数据.融资人评级结果
                    entity.setResultCurrent(entity.getResultFinancier());
                    //交易数据.最终使用主体机构 = 交易数据.融资人
                    entity.setOrgIdFinal(entity.getFinancier());
                    //交易数据.主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                    entity.setIsFinLicence(entity.getFinancierFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                    //定义 最新评级结果 = 交易数据.实际授信主体评级结果
                    String resultCurrent = entity.getResultActualCredit();
                    //定义 最终使用主体 = 交易数据.实际授信主体
                    String orgIdFinal = entity.getActualCreditProvid();
                    //定义 主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                    String isFinLicence = entity.getActualCreditFinLicence();
                    if (StringUtil.isNotEmpty(entity.getResultFinancier())) {
                        if (StringUtil.isEmpty(resultCurrent)) {
                            //交易数据.最新评级结果 = 交易数据.融资人评级结果
                            resultCurrent = entity.getResultFinancier();
                            //交易数据.最终使用主体机构 = 交易数据.融资人
                            orgIdFinal = entity.getFinancier();
                            //交易数据.主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                            isFinLicence = entity.getFinancierFinLicence();
                        } else if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultFinancier()) <= 0) {
                            //最新评级结果 = 交易数据.融资人评级结果
                            resultCurrent = entity.getResultFinancier();
                            if (impairmentCalculationCommonService.getPoorRating(entity.getResultActualCredit(), entity.getResultFinancier()) == 0) {
                                if (ModelDictCodeList.是_否_是.getValue().equals(entity.getFinancierFinLicence())) {
                                    //最终使用主体 = 交易数据.融资人
                                    orgIdFinal = entity.getFinancier();
                                    //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                    isFinLicence = entity.getFinancierFinLicence();
                                }
                            } else {
                                //最终使用主体 = 交易数据.融资人
                                orgIdFinal = entity.getFinancier();
                                //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                isFinLicence = entity.getFinancierFinLicence();
                            }
                        }
                    }
                    if (StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        if (StringUtil.isEmpty(resultCurrent)) {
                            //交易数据.最新评级结果 = 交易数据.担保人评级结果
                            resultCurrent = entity.getResultSponsor();
                            //交易数据.最终使用主体机构 = 交易数据.担保人
                            orgIdFinal = entity.getSponsor();
                            //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                            isFinLicence = entity.getSponsorFinLicence();
                        } else if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultSponsor()) <= 0) {
                            //最新评级结果 = 交易数据.担保人评级结果
                            resultCurrent = entity.getResultSponsor();
                            if (impairmentCalculationCommonService.getPoorRating(entity.getResultActualCredit(), entity.getResultSponsor()) == 0) {
                                if (ModelDictCodeList.是_否_是.getValue().equals(entity.getSponsorFinLicence())) {
                                    //最终使用主体 = 交易数据.融资人
                                    orgIdFinal = entity.getSponsor();
                                    //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                    isFinLicence = entity.getSponsorFinLicence();
                                }
                            } else {
                                //最终使用主体 = 交易数据.融资人
                                orgIdFinal = entity.getSponsor();
                                //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                isFinLicence = entity.getSponsorFinLicence();
                            }
                        }
                    }
                    entity.setResultCurrent(resultCurrent);
                    entity.setOrgIdFinal(orgIdFinal);
                    entity.setIsFinLicence(isFinLicence);
                } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                    //定义 最新评级结果 = 交易数据.实际授信主体评级结果
                    String resultCurrent = entity.getResultActualCredit();
                    //定义 最终使用主体 = 交易数据.实际授信主体
                    String orgIdFinal = entity.getActualCreditProvid();
                    //定义 主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                    String isFinLicence = entity.getActualCreditFinLicence();
                    if (StringUtil.isNotEmpty(entity.getResultFinancier())) {
                        if (StringUtil.isEmpty(resultCurrent)) {
                            //交易数据.最新评级结果 = 交易数据.融资人评级结果
                            resultCurrent = entity.getResultFinancier();
                            //交易数据.最终使用主体机构 = 交易数据.融资人
                            orgIdFinal = entity.getFinancier();
                            //交易数据.主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                            isFinLicence = entity.getFinancierFinLicence();
                        } else if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultFinancier()) >= 0) {
                            //最新评级结果 = 交易数据.融资人评级结果
                            resultCurrent = entity.getResultFinancier();
                            if (impairmentCalculationCommonService.getPoorRating(entity.getResultActualCredit(), entity.getResultFinancier()) == 0) {
                                if (ModelDictCodeList.是_否_是.getValue().equals(entity.getFinancierFinLicence())) {
                                    //最终使用主体 = 交易数据.融资人
                                    orgIdFinal = entity.getFinancier();
                                    //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                    isFinLicence = entity.getFinancierFinLicence();
                                }
                            } else {
                                //最终使用主体 = 交易数据.融资人
                                orgIdFinal = entity.getFinancier();
                                //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                isFinLicence = entity.getFinancierFinLicence();
                            }
                        }
                    }
                    if (StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        if (StringUtil.isEmpty(resultCurrent)) {
                            //交易数据.最新评级结果 = 交易数据.担保人评级结果
                            resultCurrent = entity.getResultSponsor();
                            //交易数据.最终使用主体机构 = 交易数据.担保人
                            orgIdFinal = entity.getSponsor();
                            //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                            isFinLicence = entity.getSponsorFinLicence();
                        } else if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultSponsor()) >= 0) {
                            //最新评级结果 = 交易数据.担保人评级结果
                            resultCurrent = entity.getResultSponsor();
                            if (impairmentCalculationCommonService.getPoorRating(entity.getResultActualCredit(), entity.getResultSponsor()) == 0) {
                                if (ModelDictCodeList.是_否_是.getValue().equals(entity.getSponsorFinLicence())) {
                                    //最终使用主体 = 交易数据.融资人
                                    orgIdFinal = entity.getSponsor();
                                    //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                    isFinLicence = entity.getSponsorFinLicence();
                                }
                            } else {
                                //最终使用主体 = 交易数据.融资人
                                orgIdFinal = entity.getSponsor();
                                //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                isFinLicence = entity.getSponsorFinLicence();
                            }
                        }
                    }
                    entity.setResultCurrent(resultCurrent);
                    entity.setOrgIdFinal(orgIdFinal);
                    entity.setIsFinLicence(isFinLicence);
                }
            } else {
                if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 交易数据.发行人评级结果
                    entity.setResultCurrent(entity.getResultIssuer());
                    //交易数据.最终使用主体机构 = 交易数据.发行人
                    entity.setOrgIdFinal(entity.getIssuer());
                    //交易数据.主体是否持有金融牌照 = 交易数据.发行人是否持有金融牌照
                    entity.setIsFinLicence(entity.getIssueFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 交易数据.担保人评级结果
                    entity.setResultCurrent(entity.getResultSponsor());
                    //交易数据.最终使用主体机构 = 交易数据.担保人
                    entity.setOrgIdFinal(entity.getSponsor());
                    //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                    entity.setIsFinLicence(entity.getSponsorFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                    if (StringUtil.isNotEmpty(entity.getResultIssuer()) && StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        if (impairmentCalculationCommonService.getPoorRating(entity.getResultIssuer(), entity.getResultSponsor()) >= 0) {
                            //交易数据.最新评级结果 = 交易数据.发行人评级结果
                            entity.setResultCurrent(entity.getResultIssuer());
                            //交易数据.最终使用主体机构 = 交易数据.发行人
                            entity.setOrgIdFinal(entity.getIssuer());
                            //交易数据.主体是否持有金融牌照 = 交易数据.发行人是否持有金融牌照
                            entity.setIsFinLicence(entity.getIssueFinLicence());
                        } else {
                            //交易数据.最新评级结果 = 交易数据.担保人评级结果
                            entity.setResultCurrent(entity.getResultSponsor());
                            //交易数据.最终使用主体机构 = 交易数据.担保人
                            entity.setOrgIdFinal(entity.getSponsor());
                            //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                            entity.setIsFinLicence(entity.getSponsorFinLicence());
                        }
                    } else if (StringUtil.isNotEmpty(entity.getIssuer())) {
                        //交易数据.最新评级结果 = 交易数据.发行人评级结果
                        entity.setResultCurrent(entity.getResultIssuer());
                        //交易数据.最终使用主体机构 = 交易数据.发行人
                        entity.setOrgIdFinal(entity.getIssuer());
                        //交易数据.主体是否持有金融牌照 = 交易数据.发行人是否持有金融牌照
                        entity.setIsFinLicence(entity.getIssueFinLicence());
                    } else if (StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        //交易数据.最新评级结果 = 交易数据.担保人评级结果
                        entity.setResultCurrent(entity.getResultSponsor());
                        //交易数据.最终使用主体机构 = 交易数据.担保人
                        entity.setOrgIdFinal(entity.getSponsor());
                        //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                        entity.setIsFinLicence(entity.getSponsorFinLicence());
                    }
                } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                    if (StringUtil.isNotEmpty(entity.getResultIssuer()) && StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        if (impairmentCalculationCommonService.getPoorRating(entity.getResultIssuer(), entity.getResultSponsor()) <= 0) {
                            //交易数据.最新评级结果 = 交易数据.发行人评级结果
                            entity.setResultCurrent(entity.getResultIssuer());
                            //交易数据.最终使用主体机构 = 交易数据.发行人
                            entity.setOrgIdFinal(entity.getIssuer());
                            //交易数据.主体是否持有金融牌照 = 交易数据.发行人是否持有金融牌照
                            entity.setIsFinLicence(entity.getIssueFinLicence());
                        } else {
                            //交易数据.最新评级结果 = 交易数据.担保人评级结果
                            entity.setResultCurrent(entity.getResultSponsor());
                            //交易数据.最终使用主体机构 = 交易数据.担保人
                            entity.setOrgIdFinal(entity.getSponsor());
                            //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                            entity.setIsFinLicence(entity.getSponsorFinLicence());
                        }
                    } else if (StringUtil.isNotEmpty(entity.getResultIssuer())) {
                        //交易数据.最新评级结果 = 交易数据.发行人评级结果
                        entity.setResultCurrent(entity.getResultIssuer());
                        //交易数据.最终使用主体机构 = 交易数据.发行人
                        entity.setOrgIdFinal(entity.getIssuer());
                        //交易数据.主体是否持有金融牌照 = 交易数据.发行人是否持有金融牌照
                        entity.setIsFinLicence(entity.getIssueFinLicence());
                    } else if (StringUtil.isNotEmpty(entity.getResultSponsor())) {
                        //交易数据.最新评级结果 = 交易数据.担保人评级结果
                        entity.setResultCurrent(entity.getResultSponsor());
                        //交易数据.最终使用主体机构 = 交易数据.担保人
                        entity.setOrgIdFinal(entity.getSponsor());
                        //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                        entity.setIsFinLicence(entity.getSponsorFinLicence());
                    }
                }
            }
        } else {
            if (ModelDictCodeList.模型_证券品种_债券.getValue().equals(entity.getSecTypeModel())) {
                //交易数据.最终使用主体机构 = null
                entity.setOrgIdFinal(null);
                //交易数据.主体是否持有金融牌照 = 'N 否'
                entity.setIsFinLicence(ModelDictCodeList.是_否_否.getValue());
                if (ModelDictCodeList.模型_证券细分类_交易商协会ABN.getValue().equals(entity.getSecDetailTypeModel())
                        || ModelDictCodeList.模型_证券细分类_银保监会主管ABS.getValue().equals(entity.getSecDetailTypeModel())
                        || ModelDictCodeList.模型_证券细分类_证监会主管ABS.getValue().equals(entity.getSecDetailTypeModel())) {
                    if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                        //交易数据.最终使用评级类型 = "02 主体评级"
                        entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                        //交易数据.最新评级结果 = 交易数据.债券主体评级结果
                        entity.setResultCurrent(entity.getResult02());
                    } else if (ModelDictCodeList.模型_评级类型_债项评级.getValue().equals(entity.getoType())) {
                        //交易数据.最终使用评级类型 = "01 债项评级"
                        entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                        //交易数据.最新评级结果 = 交易数据.债项评级结果
                        entity.setResultCurrent(entity.getResult01());
                    } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                        if (StringUtil.isNotEmpty(entity.getResult02()) && StringUtil.isNotEmpty(entity.getResult01())) {
                            if (impairmentCalculationCommonService.getPoorRating(entity.getResult02(), entity.getResult01()) >= 0) {
                                //交易数据.最终使用评级类型 = "02 主体评级"
                                entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                                //交易数据.最新评级结果 = 交易数据.债券主体评级结果
                                entity.setResultCurrent(entity.getResult02());
                            } else {
                                //交易数据.最终使用评级类型 = "01 债项评级"
                                entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                                //交易数据.最新评级结果 = 交易数据.债项评级结果
                                entity.setResultCurrent(entity.getResult01());
                            }
                        } else if (StringUtil.isNotEmpty(entity.getResult02())) {
                            //交易数据.最终使用评级类型 = "02 主体评级"
                            entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                            //交易数据.最新评级结果 = 交易数据.债券主体评级结果
                            entity.setResultCurrent(entity.getResult02());
                        } else if (StringUtil.isNotEmpty(entity.getResult01())) {
                            //交易数据.最终使用评级类型 = "01 债项评级"
                            entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                            //交易数据.最新评级结果 = 交易数据.债项评级结果
                            entity.setResultCurrent(entity.getResult01());
                        }
                    } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                        if (StringUtil.isNotEmpty(entity.getResult02()) && StringUtil.isNotEmpty(entity.getResult01())) {
                            if (impairmentCalculationCommonService.getPoorRating(entity.getResult02(), entity.getResult01()) <= 0) {
                                //交易数据.最终使用评级类型 = "02 主体评级"
                                entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                                //交易数据.最新评级结果 = 交易数据.债券主体评级结果
                                entity.setResultCurrent(entity.getResult02());
                            } else {
                                //交易数据.最终使用评级类型 = "01 债项评级"
                                entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                                //交易数据.最新评级结果 = 交易数据.债项评级结果
                                entity.setResultCurrent(entity.getResult01());
                            }
                        } else if (StringUtil.isNotEmpty(entity.getResult02())) {
                            //交易数据.最终使用评级类型 = "02 主体评级"
                            entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                            //交易数据.最新评级结果 = 交易数据.债券主体评级结果
                            entity.setResultCurrent(entity.getResult02());
                        } else if (StringUtil.isNotEmpty(entity.getResult01())) {
                            //交易数据.最终使用评级类型 = "01 债项评级"
                            entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                            //交易数据.最新评级结果 = 交易数据.债项评级结果
                            entity.setResultCurrent(entity.getResult01());
                        }
                    }
                } else {
                    //交易数据i.最新评级结果 = 交易数据i.债券主体评级结果
                    entity.setResultCurrent(entity.getResult02());
                    //交易数据.最终使用评级类型 = "01 主体评级"
                    entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                }
            } else if (ModelDictCodeList.模型_证券品种_项目类资产.getValue().equals(entity.getSecTypeModel())) {
                // 非标实际授信主体外部评级查询
                //调用 原始库查询共通.xlsx 【非标外部评级查询-减值模型】
                ModImpairRateParamsDto param = new ModImpairRateParamsDto();
                //评级主体					交易数据i.实际授信主体
                param.setOcode(entity.getActualCreditProvid());
                //评级机构					交易数据i.评级机构
                param.setGradeorgId(entity.getGradeOrgId());
                //评级日期					跑批日
                param.setSearchDate(platDate);
                ModRateDataDto modRateDataDto = null;
                if (StringUtil.isNotEmpty(entity.getActualCreditProvid()) && StringUtil.isNotEmpty(entity.getGradeOrgId())) {
                    modRateDataDto = getOutRateData(param);
                }
                String resultActualCredit = null;
                if (modRateDataDto != null) {
                    resultActualCredit = modRateDataDto.getGraderesult();
                }

                // 非标融资人外部评级查询
                //调用 原始库查询共通.xlsx 【非标外部评级查询-减值模型】
                //评级主体					交易数据i.融资人
                param.setOcode(entity.getFinancier());
                ModRateDataDto modRateDataDto1 = null;
                if (StringUtil.isNotEmpty(entity.getFinancier()) && StringUtil.isNotEmpty(entity.getGradeOrgId())) {
                    modRateDataDto1 = getOutRateData(param);
                }
                String resultFinancier = null;
                if (modRateDataDto1 != null) {
                    resultFinancier = modRateDataDto1.getGraderesult();
                }

                // 非标担保人外部评级查询
                //调用 原始库查询共通.xlsx 【非标外部评级查询-减值模型】
                //评级主体					交易数据i.担保人
                param.setOcode(entity.getSponsor());
                ModRateDataDto modRateDataDto2 = null;
                if (StringUtil.isNotEmpty(entity.getSponsor()) && StringUtil.isNotEmpty(entity.getGradeOrgId())) {
                    modRateDataDto2 = getOutRateData(param);
                }
                String resultSponsor = null;
                if (modRateDataDto2 != null) {
                    resultSponsor = modRateDataDto2.getGraderesult();
                }

                if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 实际授信主体外部评级结果
                    entity.setResultCurrent(resultActualCredit);
                    //交易数据.最终使用主体机构 = 交易数据.实际授信主体
                    entity.setOrgIdFinal(entity.getActualCreditProvid());
                    //交易数据.主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                    entity.setIsFinLicence(entity.getActualCreditFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_融资人.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 融资人外部评级结果
                    entity.setResultCurrent(resultFinancier);
                    //交易数据.最终使用主体机构 = 交易数据.融资人
                    entity.setOrgIdFinal(entity.getFinancier());
                    //交易数据.主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                    entity.setIsFinLicence(entity.getFinancierFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 担保人外部评级结果
                    entity.setResultCurrent(resultSponsor);
                    //交易数据.最终使用主体机构 = 交易数据.担保人
                    entity.setOrgIdFinal(entity.getSponsor());
                    //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                    entity.setIsFinLicence(entity.getSponsorFinLicence());
                } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                    //定义 最新评级结果 = 实际授信主体外部评级结果
                    String resultCurrent = resultActualCredit;
                    //定义 最终使用主体 = 交易数据.实际授信主体
                    String orgIdFinal = entity.getActualCreditProvid();
                    //定义 主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                    String isFinLicence = entity.getActualCreditFinLicence();
                    if (StringUtil.isNotEmpty(resultFinancier)) {
                        if (StringUtil.isEmpty(resultCurrent)) {
                            //交易数据.最新评级结果 = 融资人外部评级结果
                            resultCurrent = resultFinancier;
                            //交易数据.最终使用主体机构 = 交易数据.融资人
                            orgIdFinal = entity.getFinancier();
                            //交易数据.主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                            isFinLicence = entity.getFinancierFinLicence();
                        } else if (impairmentCalculationCommonService.getPoorRating(resultCurrent, resultFinancier) <= 0) {
                            //最新评级结果 = 交易数据.融资人评级结果
                            resultCurrent = resultFinancier;
                            if (impairmentCalculationCommonService.getPoorRating(resultActualCredit, resultFinancier) == 0) {
                                if (ModelDictCodeList.是_否_是.getValue().equals(entity.getFinancierFinLicence())) {
                                    //最终使用主体 = 交易数据.融资人
                                    orgIdFinal = entity.getFinancier();
                                    //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                    isFinLicence = entity.getFinancierFinLicence();
                                }
                            } else {
                                //最终使用主体 = 交易数据.融资人
                                orgIdFinal = entity.getFinancier();
                                //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                isFinLicence = entity.getFinancierFinLicence();
                            }
                        }
                    }
                    if (StringUtil.isNotEmpty(resultSponsor)) {
                        if (StringUtil.isEmpty(resultCurrent)) {
                            //交易数据.最新评级结果 = 担保人外部评级结果
                            resultCurrent = resultSponsor;
                            //交易数据.最终使用主体机构 = 交易数据.担保人
                            orgIdFinal = entity.getSponsor();
                            //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                            isFinLicence = entity.getFinancierFinLicence();
                        } else if (impairmentCalculationCommonService.getPoorRating(resultCurrent, resultSponsor) <= 0) {
                            //最新评级结果 = 交易数据.担保人评级结果
                            resultCurrent = resultSponsor;
                            if (impairmentCalculationCommonService.getPoorRating(resultActualCredit, resultSponsor) == 0
                                    || impairmentCalculationCommonService.getPoorRating(resultFinancier, resultSponsor) == 0 ) {
                                if (ModelDictCodeList.是_否_是.getValue().equals(entity.getFinancierFinLicence())) {
                                    //最终使用主体 = 交易数据.担保人
                                    orgIdFinal = entity.getSponsor();
                                    //主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                                    isFinLicence = entity.getFinancierFinLicence();
                                }
                            } else {
                                //最终使用主体 = 交易数据.担保人
                                orgIdFinal = entity.getSponsor();
                                //主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                                isFinLicence = entity.getFinancierFinLicence();
                            }
                        }
                    }
                    entity.setResultCurrent(resultCurrent);
                    entity.setOrgIdFinal(orgIdFinal);
                    entity.setIsFinLicence(isFinLicence);
                } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                    //定义 最新评级结果 = 实际授信主体外部评级结果
                    String resultCurrent = resultActualCredit;
                    //定义 最终使用主体 = 交易数据.实际授信主体
                    String orgIdFinal = entity.getActualCreditProvid();
                    //定义 主体是否持有金融牌照 = 交易数据.实际授信主体是否持有金融牌照
                    String isFinLicence = entity.getActualCreditFinLicence();
                    if (StringUtil.isNotEmpty(resultFinancier)) {
                        if (StringUtil.isEmpty(resultCurrent)) {
                            //交易数据.最新评级结果 = 融资人外部评级结果
                            resultCurrent = resultFinancier;
                            //交易数据.最终使用主体机构 = 交易数据.融资人
                            orgIdFinal = entity.getFinancier();
                            //交易数据.主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                            isFinLicence = entity.getFinancierFinLicence();
                        } else if (impairmentCalculationCommonService.getPoorRating(resultCurrent, resultFinancier) >= 0) {
                            //最新评级结果 = 交易数据.融资人评级结果
                            resultCurrent = resultFinancier;
                            if (impairmentCalculationCommonService.getPoorRating(resultActualCredit, resultFinancier) == 0) {
                                if (ModelDictCodeList.是_否_是.getValue().equals(entity.getFinancierFinLicence())) {
                                    //最终使用主体 = 交易数据.融资人
                                    orgIdFinal = entity.getFinancier();
                                    //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                    isFinLicence = entity.getFinancierFinLicence();
                                }
                            } else {
                                //最终使用主体 = 交易数据.融资人
                                orgIdFinal = entity.getFinancier();
                                //主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                                isFinLicence = entity.getFinancierFinLicence();
                            }
                        }
                    }
                    if (StringUtil.isNotEmpty(resultSponsor)) {
                        if (StringUtil.isEmpty(resultCurrent)) {
                            //交易数据.最新评级结果 = 担保人外部评级结果
                            resultCurrent = resultSponsor;
                            //交易数据.最终使用主体机构 = 交易数据.担保人
                            orgIdFinal = entity.getSponsor();
                            //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                            isFinLicence = entity.getFinancierFinLicence();
                        } else if (impairmentCalculationCommonService.getPoorRating(resultCurrent, resultSponsor) >= 0) {
                            //最新评级结果 = 交易数据.担保人评级结果
                            resultCurrent = resultSponsor;
                            if (impairmentCalculationCommonService.getPoorRating(resultActualCredit, resultSponsor) == 0
                                    || impairmentCalculationCommonService.getPoorRating(resultFinancier, resultSponsor) == 0 ) {
                                if (ModelDictCodeList.是_否_是.getValue().equals(entity.getFinancierFinLicence())) {
                                    //最终使用主体 = 交易数据.担保人
                                    orgIdFinal = entity.getSponsor();
                                    //主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                                    isFinLicence = entity.getFinancierFinLicence();
                                }
                            } else {
                                //最终使用主体 = 交易数据.担保人
                                orgIdFinal = entity.getSponsor();
                                //主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                                isFinLicence = entity.getFinancierFinLicence();
                            }
                        }
                    }
                    entity.setResultCurrent(resultCurrent);
                    entity.setOrgIdFinal(orgIdFinal);
                    entity.setIsFinLicence(isFinLicence);
                }
            }
        }
    }

    /**
     * 结果集封装
     * @param entity
     * @param modEclResultProcess
     * @param platDate
     */
    private void quaryMainResult(ImpairmentDataLoadStep1ReaderDto entity, ModEclResultProcess modEclResultProcess, Date platDate) {
        //序列号
        modEclResultProcess.setSeqno(IDGenerator.generate());
        //交易编号
        modEclResultProcess.setTradeId(entity.getTradeId());
        //资产代码
        modEclResultProcess.setAssetId(entity.getFinprodId());
        //资产名称
        modEclResultProcess.setAssetName(entity.getFinprodAbbr());
        //市场代码
        modEclResultProcess.setMarketId(entity.getFinprodMarketId());
        //投资组合代码
        modEclResultProcess.setPortfolioId(entity.getPortfolioId());
        //产品代码
        modEclResultProcess.setProdId(entity.getProdId());
        //产品名称
        modEclResultProcess.setProdName(entity.getProdName());
        //发行人
        modEclResultProcess.setIssuer(entity.getIssuer());
        //融资人
        modEclResultProcess.setFinancier(entity.getFinancier());
        //担保人
        modEclResultProcess.setSponsor(entity.getSponsor());
        //实际授信主体
        modEclResultProcess.setActualCredit(entity.getActualCreditProvid());
        //是否含有抵质押物
        modEclResultProcess.setIsContainPledge(entity.getIsLimits());
        //到期日
        modEclResultProcess.setMdate(entity.getMdate());
        //TYPE_8(平安资产类型)
        modEclResultProcess.setType8(entity.getAssetOne());
        //TYPE_9(行内资产二级分类)
        modEclResultProcess.setType9(entity.getAssetTwo());
        //TYPE_10(行内资产三级分类)
        modEclResultProcess.setType10(entity.getAssetThree());
        //TYPE_11(行内资产四级分类)
        modEclResultProcess.setType11(entity.getAssetFour());
        //TYPE_12(行内资产五级分类)
        modEclResultProcess.setType12(entity.getAssetFive());
        //模型资产类型
        modEclResultProcess.setModelAssetType(entity.getSecTypeModel());
        //模型资产细分类
        modEclResultProcess.setModelAssetDetailType(entity.getSecDetailTypeModel());
        //风险五级分类
        modEclResultProcess.setRiskLevelFive(entity.getFirstResult());
        //交易日
        modEclResultProcess.setTradeDate(entity.getSettleDate());
        //交易方向
        modEclResultProcess.setPs(entity.getPs());
        //交易份额
        modEclResultProcess.setShareAmt(entity.getShareAmt());
        //交易金额
        modEclResultProcess.setTradeAmt(entity.getTradeAmt());
        //到期收益率
        modEclResultProcess.setYield(entity.getYield());
        //票面利率
        modEclResultProcess.setPaperRate(null);
        //计划截止日
        modEclResultProcess.setPlanEndDate(entity.getPlanEndDate());
        //是否结清
        modEclResultProcess.setIsClean(entity.getIsClean());
        //减值日期
        modEclResultProcess.setDecDate(platDate);
        //移动平均后份额
        modEclResultProcess.setEmaShareAmt(entity.getShareAmt());
        //过程
        modEclResultProcess.setProcess(ModelDictCodeList.模型_减值过程_数据加载.getValue());
        //发行规模
        modEclResultProcess.setIssueAmt(entity.getIssueAmt());
        //a.息票品种
        modEclResultProcess.setCouponSpecies(entity.getCouponSpecies());
        //a.发行价
        modEclResultProcess.setIssuePrice(entity.getIssuePrice());
        //a.起息日
        modEclResultProcess.setVdate(entity.getVdate());
    }


    /**
     * 非标外部评级查询-减值模型
     * @param param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ModRateDataDto getOutRateData(ModImpairRateParamsDto param){
        try{
            if(param.getGradeorgId() == null || param.getOcode() == null || param.getSearchDate()==null ){
                logger.info("参数不能为空！");
                throw new Exception("参数不能为空");
            }
            Map<String,Object> paraMap = new HashMap<String,Object>();
            paraMap.put("issuer",param.getOcode());
            //查询评级主体发行的所有债券
            List<String> list = impairmentDataLoadMapper.getUnissueSecIdList(paraMap);
            paraMap.put("ocodeList",list);
            paraMap.put("gradeorgId",param.getGradeorgId());
            paraMap.put("searchDate",param.getSearchDate());
            return impairmentDataLoadMapper.selectOutRateData(paraMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
