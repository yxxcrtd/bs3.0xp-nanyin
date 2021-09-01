package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.common.calculation.ImpairmentCalculationCommonService;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.*;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.valuation.common.dto.modelDto.ModImpairRateParamsDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModRateDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImpairmentDataLoadProcessorStep2Service {
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
    public ImpairmentDataLoadStep1ResultDto excute(ImpairmentDataLoadStep2ReaderDto entity, Map<String, Object> params) {
        ImpairmentDataLoadStep1ResultDto result = new ImpairmentDataLoadStep1ResultDto();
        ModConfigUseage modConfigUseage = new ModConfigUseage();
        ModEclResultProcess modEclResultProcess = new ModEclResultProcess();
        result.setModConfigUseage(modConfigUseage);
        result.setModEclResultProcess(modEclResultProcess);
        modEclResultProcess.setSeqno(entity.getSeqno());
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");

        //1.1 从系统缓存中获取交易日评级结果Map
        Map<Date,Map<String, ViewRatingInfoDto>>  ratingMap = new HashMap<>();
        if (CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class) != null) {
            ratingMap = CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class);
        }
        Map<Date,Map<String, ViewRatingInfoDto>>  ratingMapLatest = new HashMap<>();
        if (CacheUtil.getAppCache(GlobalConstant.RATING_INFO_LATEST, Map.class) != null) {
            ratingMapLatest = CacheUtil.getAppCache(GlobalConstant.RATING_INFO_LATEST, Map.class);
        }

        //1.2 装载交易数据的最新评级结果
        //定义 交易日结果列表 = 交易日评级结果Map.get(交易数据.交易日)
        Map<String, ViewRatingInfoDto> listRating = ratingMap.get(entity.getTradeDate());
        Map<String, ViewRatingInfoDto> listRatingLatest = ratingMapLatest.get(entity.getTradeDate());
        if (listRating != null) {

            //定义 买入时点债项评级结果 = 交易日结果Map.get(交易数据.市场代码 + "_" + "02" + "_" + 交易数据.评级机构);
            String result01 = null;
            ViewRatingInfoDto ratingInfoDto1 = getRatingInfo(listRating, listRatingLatest, entity,
                entity.getMarketId(), ModelDictCodeList.模型_评级类型_债项评级.getValue());
            result01 = ratingInfoDto1.getMstratingresult01();
            //定义 买入时点债券主体评级结果 = 交易日结果Map.get(交易数据.市场代码 + "_" + "01" + "_" + 交易数据.评级机构);
            String result02 = null;
            ViewRatingInfoDto ratingInfoDto2 = getRatingInfo(listRating, listRatingLatest, entity,
                entity.getMarketId(), ModelDictCodeList.模型_评级类型_主体评级.getValue());
             result02 = ratingInfoDto2.getMstratingresult01();
            //定义 买入时点发行人评级结果 = 交易日结果Map.get(交易数据.发行人 + "_" + "01" + "_" + 交易数据.评级机构);
            String resultIssuer = null;
            if (StringUtil.isNotEmpty(entity.getIssuer())) {
                ViewRatingInfoDto ratingInfoDto3 = getRatingInfo(listRating, listRatingLatest, entity,
                    entity.getIssuer(), ModelDictCodeList.模型_评级类型_主体评级.getValue());
                resultIssuer = ratingInfoDto3.getMstratingresult01();
            }
            //定义 买入时点融资人评级结果 = 交易日结果Map.get(交易数据.融资人 + "_" + "01" + "_" + 交易数据.评级机构);
            String resultFinancier = null;
            if (StringUtil.isNotEmpty(entity.getFinancier())) {
                ViewRatingInfoDto ratingInfoDto3 = getRatingInfo(listRating, listRatingLatest, entity,
                    entity.getFinancier(), ModelDictCodeList.模型_评级类型_主体评级.getValue());
                resultFinancier = ratingInfoDto3.getMstratingresult01();
            }
            //定义 买入时点担保人评级结果 = 交易日结果Map.get(交易数据.担保人 + "_" + "01" + "_" + 交易数据.评级机构);
            String resultSponsor = null;
            if (StringUtil.isNotEmpty(entity.getSponsor())) {
                ViewRatingInfoDto ratingInfoDto3 = getRatingInfo(listRating, listRatingLatest, entity,
                    entity.getSponsor(), ModelDictCodeList.模型_评级类型_主体评级.getValue());
                resultSponsor = ratingInfoDto3.getMstratingresult01();
            }
            //资产类型 = 债券
            if (DictCodeListExt1.证券品种_债券.getValue().equals(entity.getModelAssetType())) {
                if (ModelDictCodeList.模型_评级类型_债项评级.getValue().equals(entity.getoType())) {
                    entity.setoTypeFinal(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                    //交易数据.最新评级结果 = 交易数据.债项评级
                    modEclResultProcess.setResultCurrent(result01);
                }
                else if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                    //交易数据.最新评级结果 = 交易数据.主体评级
                    modEclResultProcess.setResultCurrent(result02);
                }
                else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                    if (impairmentCalculationCommonService.getPoorRating(result01,result02) >= 0) {
                        //交易数据.最新评级结果 = 交易数据.债项评级
                        modEclResultProcess.setResultCurrent(result01);
                    } else {
                        //交易数据.最新评级结果 = 交易数据.主体评级
                        modEclResultProcess.setResultCurrent(result02);
                    }
                }
                else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                    if (impairmentCalculationCommonService.getPoorRating(entity.getResult01(), entity.getResult02()) <= 0) {
                        //交易数据.最新评级结果 = 交易数据.发行人评级结果
                        modEclResultProcess.setResultCurrent(entity.getResult01());
                    } else {
                        // 交易数据.买入时点评级结果 = 融资人外部评级结果
                        modEclResultProcess.setResultDeal(resultFinancier);
                    }
                }
            }
            else if (DictCodeListExt1.证券品种_其他债权类.getValue().equals(entity.getModelAssetType())) {
                if (ModelDictCodeList.模型_评级类型_发行人.getValue().equals(entity.getoType())) {
                    // 交易数据.买入时点评级结果 = 发行人外部评级结果
                    modEclResultProcess.setResultDeal(resultIssuer);
                }
                else if (ModelDictCodeList.模型_评级类型_融资人.getValue().equals(entity.getoType())) {
                    // 交易数据.买入时点评级结果 = 融资人外部评级结果
                    modEclResultProcess.setResultDeal(resultFinancier);
                }
                else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                    // 交易数据.买入时点评级结果 = 担保人外部评级结果
                    modEclResultProcess.setResultDeal(resultSponsor);
                }
                else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                    //定义 最新评级结果 = 发行人评级结果
                    String resultCurrent = entity.getResultIssuer();
                    // 比较最终评级结果与融资人
                    if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultFinancier()) <= 0) {
                        resultCurrent = entity.getResultFinancier();
                    }
                    // 比较最终评级结果与担保人
                    if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultSponsor()) <= 0) {
                        resultCurrent = entity.getResultSponsor();
                    }
                    // 交易数据.买入时点评级结果 = 最终结果
                    modEclResultProcess.setResultDeal(resultCurrent);
                } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                    //定义 最新评级结果 = 发行人评级结果
                    String resultCurrent = entity.getResultIssuer();
                    // 比较最终评级结果与融资人
                    if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultFinancier()) >= 0) {
                        resultCurrent = entity.getResultFinancier();
                    }
                    // 比较最终评级结果与担保人
                    if (impairmentCalculationCommonService.getPoorRating(resultCurrent, entity.getResultSponsor()) >= 0) {
                        resultCurrent = entity.getResultSponsor();
                    }
                    // 交易数据.买入时点评级结果 = 最终结果
                    modEclResultProcess.setResultDeal(resultCurrent);
                }
            }
            else if (DictCodeListExt1.证券品种_存款.getValue().equals(entity.getModelAssetType())
                     || DictCodeListExt1.证券品种_回购.getValue().equals(entity.getModelAssetType())) {
                // 交易数据.买入时点评级结果 = 发行人评级结果
                modEclResultProcess.setResultDeal(resultIssuer);
            }

        }

        return result;
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
                                           Map<String, ViewRatingInfoDto> mapRatingLatest, ImpairmentDataLoadStep2ReaderDto entity,
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
