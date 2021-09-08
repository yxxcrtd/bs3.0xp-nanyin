package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.ModAssetGradeConfig;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.common.base.entity.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ResultDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.valuation.model.common.rateinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.valuation.model.common.rateinfo.service.ImpairmentCalculationCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // 定义 变量.减值结果过程
        ModEclResultProcess modEclResultProcess = new ModEclResultProcess();
        // 定义 变量.评级配置使用情况 = 返回结果.评级配置使用情况
        ModConfigUseage modConfigUseage = entity.getModConfigUseage();
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        List<CommonErrorParams> errors = (List<CommonErrorParams>) stepExecution.getJobExecution().getExecutionContext().get("ERRORS");
        if (errors == null) {
            errors = new ArrayList<CommonErrorParams>();
        }
        //1. 结果集封装
        quaryMainResult(entity, modEclResultProcess, platDate);

        //2.  查询买入时点评级结果
        // 首次买入日期为当天
        if (platDate.compareTo(entity.getSettleDate()) == 0) {
            //交易数据.买入时点评级结果 = 交易数据.最新评级结果
            modEclResultProcess.setResultDeal(entity.getResultCurrent());
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
            //买入时点评级结果
            modEclResultProcess.setResultDeal(resultDeal);
        }
        // 返回结果.减值结果过程 = 变量.减值结果过程
        result.setModEclResultProcess(modEclResultProcess);

        //3、加载减值配置使用情况表
        //2.1 判断使用的参数是否已存在减值配置使用情况表
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("configSeqno", modConfigUseage.getConfigSeqno());
        para.put("configType", modConfigUseage.getConfigType());
        int resultCount= impairmentDataLoadMapper.selectModConfigUseageCount(para);
        if (resultCount == 0) {
            modConfigUseage.setSeqno(IDGenerator.generate());
            modConfigUseage.setUseProcess(ModelDictCodeList.模型_减值过程_数据加载.getValue());
            // 返回结果.评级配置使用情况 = 变量.评级配置使用情况
            result.setModConfigUseage(modConfigUseage);
        }

        return result;
    }

    /**
     * 加载减值配置使用情况表
     * @param modConfigUseage
     */
    private void quaryModConfigUseage(ModConfigUseage modConfigUseage) {

    }

    /**
     * 结果集封装
     * @param entity
     * @param modEclResultProcess
     * @param platDate
     */
    private void quaryMainResult(ImpairmentDataLoadStep1ReaderDto entity, ModEclResultProcess modEclResultProcess, Date platDate) {
        BeanUtil.copyPropertiesIgnoreNull(modEclResultProcess, entity);
        //序列号
        modEclResultProcess.setSeqno(IDGenerator.generate());
        // 交易日期 = readerEntity.交割日
        modEclResultProcess.setTradeDate(entity.getSettleDate());
//        //交易编号
//        modEclResultProcess.setTradeId(entity.getTradeId());
        //资产代码
        modEclResultProcess.setAssetId(entity.getFinprodId());
        //资产名称
        modEclResultProcess.setAssetName(entity.getFinprodAbbr());
        //市场代码
        modEclResultProcess.setMarketId(entity.getFinprodMarketId());
//        //投资组合代码
//        modEclResultProcess.setPortfolioId(entity.getPortfolioId());
//        //产品代码
//        modEclResultProcess.setProdId(entity.getProdId());
//        //产品名称
//        modEclResultProcess.setProdName(entity.getProdName());
//        //发行人
//        modEclResultProcess.setIssuer(entity.getIssuer());
//        //融资人
//        modEclResultProcess.setFinancier(entity.getFinancier());
//        //担保人
//        modEclResultProcess.setSponsor(entity.getSponsor());
//        //实际授信主体
//        modEclResultProcess.setActualCredit(entity.getActualCreditProvid());
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
//        //发行规模
//        modEclResultProcess.setIssueAmt(entity.getIssueAmt());
//        //a.息票品种
//        modEclResultProcess.setCouponSpecies(entity.getCouponSpecies());
//        //a.发行价
//        modEclResultProcess.setIssuePrice(entity.getIssuePrice());
//        //a.起息日
//        modEclResultProcess.setVdate(entity.getVdate());
//        // a.投资目的
//        modEclResultProcess.setInvAim(entity.getInvAim());
//        //评级类型
//        modEclResultProcess.setoType(entity.getoType());
//        //评级机构
//        modEclResultProcess.setGradeOrgId(entity.getGradeOrgId());
//        //最新评级结果
//        modEclResultProcess.setResultCurrent(entity.getResultCurrent());
//        //最终使用评级类型
//        modEclResultProcess.setoTypeFinal(entity.getoTypeFinal());
//        //最终使用主体机构
//        modEclResultProcess.setOrgIdFinal(entity.getOrgIdFinal());
//        //主体是否持有金融牌照
//        modEclResultProcess.setIsFinLicence(entity.getIsFinLicence());
    }

    /**
     * 查询评级信息
     * @param mapRating 指定评级机构评级信息
     * @param mapRatingLatest 最新评级机构评级信息
     * @param entity
     * @param ocode  评级主体
     * @param otype 评级类型
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
