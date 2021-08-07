package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.impairment.batch.common.calculation.ImpairmentCalculationCommonService;
import com.joyintech.tams.model.impairment.batch.common.constants.GlobalConstant;
import com.joyintech.tams.model.impairment.batch.common.constants.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.*;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
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
        Map<Date,Map<String, String>>  ratingMap = new HashMap<>();
        if (CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class) != null) {
            ratingMap = CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class);
        }

        //1.2 装载交易数据的最新评级结果
        //定义 交易日结果列表 = 交易日评级结果Map.get(交易数据.交易日)
        Map<String, String> listRating = ratingMap.get(entity.getTradeDate());
        if (listRating != null) {
            //定义 买入时点债项评级结果 = 交易日结果Map.get(交易数据.市场代码 + "_" + "02" + "_" + 交易数据.评级机构);
            String result01 = null;
            String ratingInfoDto1 = listRating.get(entity.getMarketId() + GlobalConstant.UNDER_LINE
                    + ModelDictCodeList.模型_评级类型_债项评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
            result01 = ratingInfoDto1;
            //定义 买入时点债券主体评级结果 = 交易日结果Map.get(交易数据.市场代码 + "_" + "01" + "_" + 交易数据.评级机构);
            String result02 = null;
            String ratingInfoDto2 = listRating.get(entity.getMarketId() + GlobalConstant.UNDER_LINE
                    + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
             result02 = ratingInfoDto2;
            //定义 买入时点发行人评级结果 = 交易日结果Map.get(交易数据.发行人 + "_" + "01" + "_" + 交易数据.评级机构);
            String resultIssuer = null;
            if (StringUtil.isNotEmpty(entity.getIssuer())) {
                String ratingInfoDto3 = listRating.get(entity.getIssuer() + GlobalConstant.UNDER_LINE
                        + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
                resultIssuer = ratingInfoDto3;
            }
            //定义 买入时点融资人评级结果 = 交易日结果Map.get(交易数据.融资人 + "_" + "01" + "_" + 交易数据.评级机构);
            String resultFinancier = null;
            if (StringUtil.isNotEmpty(entity.getFinancier())) {
                String ratingInfoDto3 = listRating.get(entity.getFinancier() + GlobalConstant.UNDER_LINE
                        + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
                resultFinancier = ratingInfoDto3;
            }
            //定义 买入时点担保人评级结果 = 交易日结果Map.get(交易数据.担保人 + "_" + "01" + "_" + 交易数据.评级机构);
            String resultSponsor = null;
            if (StringUtil.isNotEmpty(entity.getSponsor())) {
                String ratingInfoDto3 = listRating.get(entity.getSponsor() + GlobalConstant.UNDER_LINE
                        + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
                resultSponsor = ratingInfoDto3;
            }
            //定义 买入时点实际授信主体评级结果 = 交易日结果Map.get(交易数据.实际授信主体 + "_" + "01" + "_" + 交易数据.评级机构);
            String resultActualCredit = null;
            if (StringUtil.isNotEmpty(entity.getActualCredit())) {
                String ratingInfoDto3 = listRating.get(entity.getActualCredit() + GlobalConstant.UNDER_LINE
                        + ModelDictCodeList.模型_评级类型_主体评级.getValue() + GlobalConstant.UNDER_LINE + entity.getGradeOrgId());
                resultActualCredit = ratingInfoDto3;
            }

            // 如果评级类型是债项评级,不管是行内评级还是外部评级,取值逻辑一致
            if (ModelDictCodeList.模型_评级类型_债项评级.getValue().equals(entity.getoType())) {
                //交易数据.买入时点评级 = 买入时点债项评级结果
                modEclResultProcess.setResultDeal(result01);
            } else {
                // 如果评级类型不是债项评级,则需要针对评级机构进行区分
                if (ModelDictCodeList.评级机构_资管内部.getValue().equals(entity.getGradeOrgId())) {
                    // 如果是者ABS，需要根据实际授信主体去匹配
                    if (ModelDictCodeList.模型_证券品种_债券.getValue().equals(entity.getModelAssetType())
                            && (ModelDictCodeList.模型_证券细分类_交易商协会ABN.getValue().equals(entity.getModelAssetDetailType())
                            || ModelDictCodeList.模型_证券细分类_银保监会主管ABS.getValue().equals(entity.getModelAssetDetailType())
                            || ModelDictCodeList.模型_证券细分类_证监会主管ABS.getValue().equals(entity.getModelAssetDetailType()))) {
                        if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 买入时点实际授信主体评级结果
                            modEclResultProcess.setResultDeal(resultActualCredit);
                        } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 买入时点担保人主体评级结果
                            modEclResultProcess.setResultDeal(resultSponsor);
                        } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = max(买入时点实际授信主体评级结果,买入时点担保人主体评级结果)
                            modEclResultProcess.setResultDeal(impairmentCalculationCommonService.getMaxRating(resultActualCredit, null, resultSponsor));
                        } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = min(买入时点实际授信主体评级结果,买入时点担保人主体评级结果)
                            modEclResultProcess.setResultDeal(impairmentCalculationCommonService.getMinRating(resultActualCredit, null, resultSponsor));
                        }
                    } else if (ModelDictCodeList.模型_证券品种_项目类资产.getValue().equals(entity.getModelAssetType())) {
                        if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 买入时点实际授信主体评级结果
                            modEclResultProcess.setResultDeal(resultActualCredit);
                        } else if (ModelDictCodeList.模型_评级类型_融资人.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 买入时点融资人主体评级结果
                            modEclResultProcess.setResultDeal(resultFinancier);
                        } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 买入时点担保人主体评级结果
                            modEclResultProcess.setResultDeal(resultSponsor);
                        } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = max(买入时点实际授信主体评级结果,买入时点融资人评级结果,买入时点担保人评级结果)
                            modEclResultProcess.setResultDeal(impairmentCalculationCommonService.getMaxRating(resultActualCredit, resultFinancier, resultSponsor));
                        } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = min(买入时点实际授信主体评级结果,买入时点融资人评级结果,买入时点担保人评级结果)
                            modEclResultProcess.setResultDeal(impairmentCalculationCommonService.getMinRating(resultActualCredit, resultFinancier, resultSponsor));
                        }
                    } else {
                        if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 买入时点发行人评级结果
                            modEclResultProcess.setResultDeal(resultIssuer);
                        } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 买入时点担保人主体评级结果
                            modEclResultProcess.setResultDeal(resultSponsor);
                        } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = max(买入时点发行人评级结果,买入时点担保人主体评级结果)
                            modEclResultProcess.setResultDeal(impairmentCalculationCommonService.getMaxRating(resultIssuer, null, resultSponsor));
                        } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = min(买入时点发行人评级结果,买入时点担保人主体评级结果)
                            modEclResultProcess.setResultDeal(impairmentCalculationCommonService.getMinRating(resultIssuer, null, resultSponsor));
                        }
                    }
                } else {
                    if (ModelDictCodeList.模型_证券品种_债券.getValue().equals(entity.getModelAssetType())) {
                        if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 买入时点债券主体评级结果
                            modEclResultProcess.setResultDeal(result02);
                        }
                    } else if (ModelDictCodeList.模型_证券品种_项目类资产.getValue().equals(entity.getModelAssetType())) {
                        // 非标实际授信主体外部评级查询
                        //调用 原始库查询共通.xlsx 【非标外部评级查询-减值模型】
                        ModImpairRateParamsDto param = new ModImpairRateParamsDto();
                        //评级主体					交易数据i.实际授信主体
                        param.setOcode(entity.getActualCredit());
                        //评级机构					交易数据i.评级机构
                        param.setGradeorgId(entity.getGradeOrgId());
                        //评级日期					跑批日
                        param.setSearchDate(platDate);
                        ModRateDataDto modRateDataDto = null;
                        if (StringUtil.isNotEmpty(entity.getActualCredit()) && StringUtil.isNotEmpty(entity.getGradeOrgId())) {
                            modRateDataDto = getOutRateData(param);
                        }
                        String resultActualCreditWb = null;
                        if (modRateDataDto != null) {
                            resultActualCreditWb = modRateDataDto.getGraderesult();
                        }
                        // 非标融资人外部评级查询
                        //调用 原始库查询共通.xlsx 【非标外部评级查询-减值模型】
                        //评级主体					交易数据i.融资人
                        param.setOcode(entity.getFinancier());
                        ModRateDataDto modRateDataDto1 = null;
                        if (StringUtil.isNotEmpty(entity.getFinancier()) && StringUtil.isNotEmpty(entity.getGradeOrgId())) {
                            modRateDataDto1 = getOutRateData(param);
                        }
                        String resultFinancierWb = null;
                        if (modRateDataDto1 != null) {
                            resultFinancierWb = modRateDataDto1.getGraderesult();
                        }
                        // 非标担保人外部评级查询
                        //调用 原始库查询共通.xlsx 【非标外部评级查询-减值模型】
                        //评级主体					交易数据i.担保人
                        param.setOcode(entity.getSponsor());
                        ModRateDataDto modRateDataDto2 = null;
                        if (StringUtil.isNotEmpty(entity.getSponsor()) && StringUtil.isNotEmpty(entity.getGradeOrgId())) {
                            modRateDataDto2 = getOutRateData(param);
                        }
                        String resultSponsorWb = null;
                        if (modRateDataDto2 != null) {
                            resultSponsorWb = modRateDataDto2.getGraderesult();
                        }
                        if (ModelDictCodeList.模型_评级类型_主体评级.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 实际授信主体外部评级结果
                            modEclResultProcess.setResultDeal(resultActualCreditWb);
                        } else if (ModelDictCodeList.模型_评级类型_融资人.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 融资人外部评级结果
                            modEclResultProcess.setResultDeal(resultFinancierWb);
                        } else if (ModelDictCodeList.模型_评级类型_担保人.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = 担保人外部评级结果
                            modEclResultProcess.setResultDeal(resultSponsorWb);
                        } else if (ModelDictCodeList.模型_评级类型_较高值.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = max(实际授信主体外部评级结果,融资人外部评级结果,担保人外部评级结果)
                            modEclResultProcess.setResultDeal(impairmentCalculationCommonService.getMaxRating(resultActualCreditWb, resultFinancierWb, resultSponsorWb));
                        } else if (ModelDictCodeList.模型_评级类型_较低值.getValue().equals(entity.getoType())) {
                            //交易数据.买入时点评级 = min(实际授信主体外部评级结果,融资人外部评级结果,担保人外部评级结果)
                            modEclResultProcess.setResultDeal(impairmentCalculationCommonService.getMinRating(resultActualCreditWb, resultFinancierWb, resultSponsorWb));
                        }
                    }
                }
            }

        }

        return result;
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
