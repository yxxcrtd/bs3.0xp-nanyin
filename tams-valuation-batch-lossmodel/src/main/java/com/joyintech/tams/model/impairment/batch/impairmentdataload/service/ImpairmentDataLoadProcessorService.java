package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.common.calculation.ImpairmentCalculationCommonService;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ResultDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ModAssetGradeConfig;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
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
    private static final Logger logger = LoggerFactory.getLogger(
        ImpairmentDataLoadProcessorService.class);
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
        //1.3 查询资产评级配置
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("MODEL_DEC_CONFIG_TYPE_02", ModelDictCodeList.模型_减值配置类型_特殊资产评级配置.getValue());
        para.put("MODEL_DEC_CONFIG_TYPE_01", ModelDictCodeList.模型_减值配置类型_资产大类评级配置.getValue());
        para.put("finprodId", entity.getFinprodId());
        para.put("assetType", entity.getSecTypeModel());
        para.put("assetDetailType", entity.getSecDetailTypeModel());
        // 取对应资产评级配置：特殊资产+资产大类
        List<ModAssetGradeConfig> listModAssetGradeConfig = impairmentDataLoadMapper.selectModAssetGradeConfig(para);
        if (listModAssetGradeConfig != null && listModAssetGradeConfig.size() > 0) {
            ModAssetGradeConfig configDto = listModAssetGradeConfig.get(0);
            //定义 配置流水号 = LGD参数Dto.流水号
            modConfigUseage.setConfigSeqno(configDto.getSeqno());
            //定义 配置类型 = LGD参数Dto.配置类型
            modConfigUseage.setConfigType(configDto.getAssetType());
            //交易信息.评级类型 = 评级参数Dto.评级类型
            entity.setoType(configDto.getoType());
            //交易信息.评级机构 = 评级参数Dto.评级机构
            entity.setGradeOrgId(configDto.getGradeOrgId());
            // 交易信息.评级机构类型 = 评级参数Dto.评级机构类型
            entity.setGradeOrgType(configDto.getPdConfigType());
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
        Map<String, ViewRatingInfoDto> mapRating = new HashMap<String, ViewRatingInfoDto>();
        Map<String, ViewRatingInfoDto> mapRatingLatest = new HashMap<String, ViewRatingInfoDto>();
        // 指定评级机构评级结果分组：评级主体(债项)_评级类型_评级机构（主体）：
        if (CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class) != null) {
            mapRating = CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class);
        }
        // 最新评级机构评级结果分组：评级主体(债项)_评级类型：
        if (CacheUtil.getAppCache(GlobalConstant.RATING_INFO_LATEST, Map.class) != null) {
            mapRatingLatest = CacheUtil.getAppCache(GlobalConstant.RATING_INFO_LATEST, Map.class);
        }

        //1.5 装载交易数据的最新评级结果
        quaryNewRatingInfo(mapRating, mapRatingLatest, entity, platDate);

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
        // 首次买入日期为当天
        if (platDate.compareTo(entity.getSettleDate()) == 0) {
            //交易数据.买入时点评级结果 = 交易数据.最新评级结果
            entity.setResultDeal(entity.getResultCurrent());
        } else {
            //查询首次买入时的评级结果
            Map<String, Object> para = new HashMap<String, Object>();
            para.put("tradeId", entity.getTradeId());
            para.put("gradeOrgId", entity.getGradeOrgId());
            para.put("oType", entity.getoType());
            // 查询日期 = 交割日（首次买入日期）
            String lossType = CacheUtil.getSysParam("LOSS_TYPE");
            if (DictCodeListExt3.减值方案类型_普华.getValue().equals(lossType)) {
                para.put("searchDate", entity.getSettleDate());
            }
            else{
                para.put("searchDate", DateUtil.addDay(platDate, -1));
            }

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
    private void quaryNewRatingInfo(Map<String, ViewRatingInfoDto> mapRating,
                                    Map<String, ViewRatingInfoDto> mapRatingLatest,
                                    ImpairmentDataLoadStep1ReaderDto entity, Date platDate) {
        //交易数据.债项评级结果 = 新评级结果Map.get(交易数据.市场代码 + "_" + "02" + "_" + 交易数据.评级机构);
        ViewRatingInfoDto ratingInfoDto1 = getRatingInfo(mapRating, mapRatingLatest, entity,
            entity.getFinprodMarketId(), ModelDictCodeList.模型_评级类型_债项评级.getValue());
        entity.setResult01(ratingInfoDto1.getMstratingresult01());
        //交易数据.债券主体评级结果 = 新评级结果Map.get(交易数据.市场代码 + "_" + "01" + "_" + 交易数据.评级机构);
        ViewRatingInfoDto ratingInfoDto2 = getRatingInfo(mapRating, mapRatingLatest, entity,
            entity.getFinprodMarketId(), ModelDictCodeList.模型_评级类型_主体评级.getValue());
        entity.setResult02(ratingInfoDto2.getMstratingresult01());
        //交易数据.发行人评级结果 = 最新评级结果Map.get(交易数据.发行人 + "_" + "01" + "_" + 交易数据.评级机构);
        if (StringUtil.isNotEmpty(entity.getIssuer())) {
            ViewRatingInfoDto ratingInfoDto3 = getRatingInfo(mapRating, mapRatingLatest, entity,
                entity.getIssuer(), ModelDictCodeList.模型_评级类型_主体评级.getValue());
            entity.setResultIssuer(ratingInfoDto3.getMstratingresult01());
        }
        //交易数据.融资人评级结果 = 最新评级结果Map.get(交易数据.融资人 + "_" + "01" + "_" + 交易数据.评级机构);
        if (StringUtil.isNotEmpty(entity.getFinancier())) {
            ViewRatingInfoDto ratingInfoDto3 = getRatingInfo(mapRating, mapRatingLatest, entity,
                entity.getFinancier(), ModelDictCodeList.模型_评级类型_主体评级.getValue());
            entity.setResultFinancier(ratingInfoDto3.getMstratingresult01());
        }
        //交易数据.担保人评级结果 = 最新评级结果Map.get(交易数据.担保人 + "_" + "01" + "_" + 交易数据.评级机构);
        if (StringUtil.isNotEmpty(entity.getSponsor())) {
            ViewRatingInfoDto ratingInfoDto3 = getRatingInfo(mapRating, mapRatingLatest, entity,
                entity.getSponsor(), ModelDictCodeList.模型_评级类型_主体评级.getValue());
            entity.setResultSponsor(ratingInfoDto3.getMstratingresult01());
        }

        // 默认评级类型 = 主体评级
        entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_主体评级.getValue());
        //资产类型 = 债券
        if (DictCodeListExt1.证券品种_债券.getValue().equals(entity.getSecTypeModel())) {
            //交易数据.最终使用主体机构 = null
            entity.setOrgIdFinal(null);
            //交易数据.主体是否持有金融牌照 = 否
            entity.setIsFinLicence(DictCodeList.是否_否.getValue());
            if (ModelDictCodeList.模型_评级类型_债项评级.getValue().equals(entity.getoType())) {
                entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                //交易数据.最新评级结果 = 交易数据.发行人评级结果
                entity.setResultCurrent(entity.getResult01());
            }
            else if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                //交易数据.最新评级结果 = 交易数据.发行人评级结果
                entity.setResultCurrent(entity.getResult02());
            }
            else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                if (impairmentCalculationCommonService.getPoorRating(entity.getResult01(), entity.getResult02()) >= 0) {
                    //交易数据.最新评级结果 = 交易数据.发行人评级结果
                    entity.setResultCurrent(entity.getResult01());
                } else {
                    //交易数据.最新评级结果 = 交易数据.担保人评级结果
                    entity.setResultCurrent(entity.getResultSponsor());
                }
            } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                if (impairmentCalculationCommonService.getPoorRating(entity.getResult01(), entity.getResult02()) <= 0) {
                    //交易数据.最新评级结果 = 交易数据.发行人评级结果
                    entity.setResultCurrent(entity.getResult01());
                } else {
                    //交易数据.最新评级结果 = 交易数据.担保人评级结果
                    entity.setResultCurrent(entity.getResultSponsor());
                }
            }
        }
        else if (DictCodeListExt1.证券品种_其他债权类.getValue().equals(entity.getSecTypeModel())) {
            if (ModelDictCodeList.模型_评级类型_发行人.getValue().equals(entity.getoType())) {
                //交易数据.最新评级结果 = 发行人外部评级结果
                entity.setResultCurrent(entity.getResultIssuer());
                //交易数据.最终使用主体机构 = 交易数据.发行人
                entity.setOrgIdFinal(entity.getIssuer());
                //交易数据.主体是否持有金融牌照 = 交易数据.发行人是否持有金融牌照
                entity.setIsFinLicence(entity.getIssueFinLicence());
            }
            else if (ModelDictCodeList.模型_评级类型_融资人.getValue().equals(entity.getoType())) {
                //交易数据.最新评级结果 = 融资人外部评级结果
                entity.setResultCurrent(entity.getResultFinancier());
                //交易数据.最终使用主体机构 = 交易数据.融资人
                entity.setOrgIdFinal(entity.getFinancier());
                //交易数据.主体是否持有金融牌照 = 交易数据.融资人是否持有金融牌照
                entity.setIsFinLicence(entity.getFinancierFinLicence());
            } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                //交易数据.最新评级结果 = 担保人外部评级结果
                entity.setResultCurrent(entity.getResultSponsor());
                //交易数据.最终使用主体机构 = 交易数据.担保人
                entity.setOrgIdFinal(entity.getSponsor());
                //交易数据.主体是否持有金融牌照 = 交易数据.担保人是否持有金融牌照
                entity.setIsFinLicence(entity.getSponsorFinLicence());
            } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                //定义 最新评级结果 = 发行人评级结果
                String resultCurrent = entity.getResultIssuer();
                //定义 最终使用主体 = 交易数据.发行人
                String orgIdFinal = entity.getIssuer();
                //定义 主体是否持有金融牌照 = 交易数据.发行人是否持有金融牌照
                String isFinLicence = entity.getIssueFinLicence();
                // 比较最终评级结果与融资人
                if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultFinancier()) <= 0) {
                    resultCurrent = entity.getResultFinancier();
                    orgIdFinal = entity.getFinancier();
                    isFinLicence = entity.getFinancierFinLicence();
                }
                // 比较最终评级结果与担保人
                if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultSponsor()) <= 0) {
                    resultCurrent = entity.getResultSponsor();
                    orgIdFinal = entity.getSponsor();
                    isFinLicence = entity.getSponsorFinLicence();
                }
                entity.setResultCurrent(resultCurrent);
                entity.setOrgIdFinal(orgIdFinal);
                entity.setIsFinLicence(isFinLicence);
            } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                //定义 最新评级结果 = 发行人评级结果
                String resultCurrent = entity.getResultIssuer();
                //定义 最终使用主体 = 交易数据.发行人
                String orgIdFinal = entity.getIssuer();
                //定义 主体是否持有金融牌照 = 交易数据.发行人是否持有金融牌照
                String isFinLicence = entity.getIssueFinLicence();
                // 比较最终评级结果与融资人
                if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultFinancier()) >= 0) {
                    resultCurrent = entity.getResultFinancier();
                    orgIdFinal = entity.getFinancier();
                    isFinLicence = entity.getFinancierFinLicence();
                }
                // 比较最终评级结果与担保人
                if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultSponsor()) >= 0) {
                    resultCurrent = entity.getResultSponsor();
                    orgIdFinal = entity.getSponsor();
                    isFinLicence = entity.getSponsorFinLicence();
                }
                entity.setResultCurrent(resultCurrent);
                entity.setOrgIdFinal(orgIdFinal);
                entity.setIsFinLicence(isFinLicence);
            }
        }
        else if (DictCodeListExt1.证券品种_存款.getValue().equals(entity.getSecTypeModel())
                 || DictCodeListExt1.证券品种_回购.getValue().equals(entity.getSecTypeModel())) {
            // 交易数据.最终评级结果 = 发行人评级结果
            entity.setResultCurrent(entity.getResultIssuer());
            // 交易数据.最终评级机构 = 发行人
            entity.setOrgIdFinal(entity.getIssuer());
            //交易数据.主体是否持有金融牌照 = 发行人.是否持有金融牌照
            entity.setIsFinLicence(entity.getIssueFinLicence());
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
        //模型资产类型
        modEclResultProcess.setModelAssetType(entity.getSecTypeModel());
        //模型资产细分类
        modEclResultProcess.setModelAssetDetailType(entity.getSecDetailTypeModel());
        //风险五级分类
        modEclResultProcess.setRiskLevelFive(entity.getFirstResult());
        //票面利率
        modEclResultProcess.setPaperRate(null);
        //减值日期
        modEclResultProcess.setDecDate(platDate);
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
     * 查询评级信息
     * @param mapRating 指定评级机构评级信息
     * @param mapRatingLatest 最新评级机构评级信息
     * @param gradeOrgType 评级机构类型：01最新；02指定
     * @param ocode  评级主体
     * @param otype 评级类型
     * @param  gradeorgid 评级机构代码
     * @return
     */
    public ViewRatingInfoDto getRatingInfo(Map<String, ViewRatingInfoDto> mapRating,
                                           Map<String, ViewRatingInfoDto> mapRatingLatest, ImpairmentDataLoadStep1ReaderDto entity,
                                           String ocode, String otype){
        String key = null;
        if(DictCodeListExt3.评级机构类型_最新.getValue().equals(entity.getGradeOrgType())){
            return mapRatingLatest.get(ocode + GlobalConstant.UNDER_LINE + otype);
        }
        else{
            return mapRating.get(ocode + GlobalConstant.UNDER_LINE + otype + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
        }
    }
}
