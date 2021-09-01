package com.joyintech.tams.model.impairment.batch.pdcalculate.service;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentCommonService;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dao.PdCalculateMapper;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description:减值模型-PD计算批处理Service
 * @auther: yuzj
 * @date: 2021/7/14
 * @param:
 * @return:
 */
@Service
public class BMWPdCalculateProcessService {
    @Autowired
    private PdCalculateMapper pdCalculateMapper;

    @Autowired
    private ImpairmentCommonService impairmentCommonService;

    private final String BATCH_PARAMS_KEY = "params";

    private final String BATCH_LOG_RESULTS = "logResults";


    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public AssetImpairmentPdCalculateResultDto execute(AssetGroupPdCalculateReaderDto readerDto,Map<String, Object> mapParams,StepExecution stepExecution){

        Date plateDate = (Date)mapParams.get("PLATE_DATE");//平台日
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)mapParams.get("BATCH_INFO");
        List<ModValBatchLogResult> bBatchLogResultList = new ArrayList<>();
        String taskId =(String)mapParams.get("TASK_ID");
        String logMsg = "";

        AssetImpairmentPdCalculateResultDto resultDto = new AssetImpairmentPdCalculateResultDto();
        // 若返回结果.交易明细 = 空，则直接返回
        if(StringUtil.isEmptyList(readerDto.getAssetImpairmentPdCalculateList())){
            return resultDto;
        }
        //1.1=====初始化变量Dto=====start=====
        VariablePdCalculateDto variablePdCalculateDto = new VariablePdCalculateDto();
        //交易数据列表  === 资产交易信息DTO.get交易信息列表();
        variablePdCalculateDto.setAssetImpairmentPdCalculateList(readerDto.getAssetImpairmentPdCalculateList());
        try {
            // 定义 变量.资产信息
            ModEclResultProcess assetInfo = variablePdCalculateDto.getAssetImpairmentPdCalculateList().get(0);
            //评级类型（=最终使用评级类型）
            variablePdCalculateDto.setoType(assetInfo.getoTypeFinal());
            //评级机构
            variablePdCalculateDto.setGradeOrgId(assetInfo.getGradeOrgId());
            //评级结果
            variablePdCalculateDto.setGradeResult(assetInfo.getResultCurrent());
            //主体是否持有金融牌照
            variablePdCalculateDto.setIsFinLicence(assetInfo.getIsFinLicence());
            //到期日
            variablePdCalculateDto.setMdate(assetInfo.getMdate());
            /** 剩余年限**/
            int remainingTerm = impairmentCommonService.getRemainingTerm(plateDate, assetInfo.getMdate());
            // 获取当前跑批日期所处年份
            int tdyYear = DateUtil.getYear(DateUtil.format(plateDate));

            //评级结果--最新评级结果
            if(variablePdCalculateDto.getGradeResult() ==null){
                //生成错误日志  --【批处理名称】：【机构名称/资产代码】无评级信息
                //=====记录警告日志=====start=====
                String gradeOrgName = SysDataUtil.getNameFromDataDict(ModelDictCode.评级机构, variablePdCalculateDto.getGradeOrgId());
                logMsg ="【PD计算批处理】：【"+gradeOrgName+"/"+readerDto.getAssetId()+"】无评级信息";
                impairmentCommonService.sendLogContextInfo(stepExecution, batchExecuteInfo,
                    readerDto.getAssetId(), logMsg,ModelDictCodeList.批处理执行结果_警告.getValue(), BATCH_LOG_RESULTS);
                //=====记录计算警告日志=====end=====

                // 国债、政策性金融债、地方政府债兜底评级
                if(DictCodeListExt1.证券品种_债券.getValue().equals(assetInfo.getModelAssetType())){
                    if(DictCodeList.债券类型_国债.getValue().equals(assetInfo.getModelAssetDetailType())
                    || DictCodeList.债券类型_政策银行债.getValue().equals(assetInfo.getModelAssetDetailType())
                    || DictCodeList.债券类型_地方政府债.getValue().equals(assetInfo.getModelAssetDetailType())){
                        // 系统参数-国债、政策性金融债、地方政府债兜底评级结果
                        variablePdCalculateDto.setGradeResult(CacheUtil.getSysParam(GlobalConstant.BOND_BOTTOM_RATE_1));
                    }
                    else{
                        // 系统参数-除国债、政策性金融债、地方政府债外其他债券兜底评级结果
                        variablePdCalculateDto.setGradeResult(CacheUtil.getSysParam(GlobalConstant.BOND_BOTTOM_RATE_2));
                    }
                }
                else if(DictCodeListExt1.证券品种_其他债权类.getValue().equals(assetInfo.getModelAssetType())){
                    // 减值方案系统参数 = 系统参数-其他债权资产兜底融资人评级
                    variablePdCalculateDto.setGradeResult(CacheUtil.getSysParam(GlobalConstant.DELT_RIGHT_BOTTOM_RATE));
                }
                else if(DictCodeListExt1.证券品种_存款.getValue().equals(assetInfo.getModelAssetType())){
                    // 减值方案系统参数 = 系统参数-存款资产兜底评级结果
                    variablePdCalculateDto.setGradeResult(CacheUtil.getSysParam(GlobalConstant.DEPOSITE_BOTTOM_RATE));
                }
                else if(DictCodeListExt1.证券品种_回购.getValue().equals(assetInfo.getModelAssetType())){
                    // 减值方案系统参数 = 系统参数-回购资产兜底评级结果
                    variablePdCalculateDto.setGradeResult(CacheUtil.getSysParam(GlobalConstant.REPO_FINANCIER_RATE));
                }
            }
            //=====1.1初始化变量Dto=====end=====

            //2、查询计算基础[前瞻前年度边际PD]、[前瞻因子值]
            //2.1查询资产对应的PD值 列表
            List<ModGradePdConfigDto> modGradePdConfigList = new ArrayList<>();
            // 定义 评级PD查询参数
            Map<String, Object> params = new HashMap<>();
            params.put("gradeResult", variablePdCalculateDto.getGradeResult());
            if(DictCodeListExt1.证券品种_存款.getValue().equals(assetInfo.getModelAssetType())){
                params.put("pdConfigType", DictCodeListExt3.评级PD类型_存款.getValue());
                params.put("configType", ModelDictCodeList.模型_减值配置类型_存款评级PD配置.getValue());
            }
            else if(DictCodeListExt1.证券品种_回购.getValue().equals(assetInfo.getModelAssetType())){
                params.put("pdConfigType", DictCodeListExt3.评级PD类型_回购.getValue());
                params.put("configType", ModelDictCodeList.模型_减值配置类型_回购评级PD配置.getValue());
            }
            else{
                params.put("pdConfigType", DictCodeListExt3.评级PD类型_债券_其他债权.getValue());
                params.put("configType", ModelDictCodeList.模型_减值配置类型_评级PD配置.getValue());
            }

            params.put("yearNum", new BigDecimal(tdyYear));
            ModGradePdConfigDto modGradePdConfigDto = pdCalculateMapper.selectModGradePdConfigList(params);
            //获取年份的评级PD配置列表
            if(modGradePdConfigDto ==null ) {
                    //生成警告日志
                    //=====记录计算错误日志=====start=====
                    logMsg ="【PD计算批处理】：【"+tdyYear+"】年对应评级结果【"+variablePdCalculateDto.getGradeResult()+"】的PD未维护";
                    impairmentCommonService.sendLogContextInfo(stepExecution, batchExecuteInfo,
                        readerDto.getAssetId(), logMsg,ModelDictCodeList.批处理执行结果_异常结束.getValue(), BATCH_LOG_RESULTS);
                    //=====记录计算错误日志=====end=====
                    return resultDto;
            }

            // 资产对应的PD值 列表.add(查询PD结果)
            modGradePdConfigList.add(modGradePdConfigDto);
            // 查询综合系数
            ModPdFactorConfigDto configDto = pdCalculateMapper.getCombinedFactorConfig(
                String.valueOf(tdyYear));
            // 前瞻性调整后PD= 初始评级维护PD
            BigDecimal prospectivePd = modGradePdConfigDto.getForwardYearPd();
            // 前瞻性调整后PD=Min（初始评级维护PD*综合系数，100%）
            if(configDto != null){
                prospectivePd = ArithmeticUtil.multiply(prospectivePd, configDto.getCombinedFactor());
                prospectivePd = BigDecimal.ONE.compareTo(prospectivePd) > 0?prospectivePd:BigDecimal.ONE;
            }

            //遍历 交易数据
            for(ModEclResultProcess aiPdDto :variablePdCalculateDto.getAssetImpairmentPdCalculateList()){
                // 评级结果重新赋值，防止明细数据中的评级结果为空值
                aiPdDto.setResultCurrent(variablePdCalculateDto.getGradeResult());
                // 若资产处于三阶段，则直接返回1
                if(ModelDictCodeList.阶段_买入后出现减值_P3.getValue().equals(aiPdDto.getFinStage())){
                    aiPdDto.setProspectivePd(BigDecimal.ONE);
                    continue;
                }
                // 若是存款和回购类资产，取评级结果对应的调整后PD值
                if(DictCodeListExt1.证券品种_存款.getValue().equals(assetInfo.getModelAssetType())
                || DictCodeListExt1.证券品种_回购.getValue().equals(assetInfo.getModelAssetType())){
                    aiPdDto.setProspectivePd(prospectivePd);
                }
                // 债券、其他债权类资产
                else{
                    // 当资产出于1阶段，取12个月（1年期）PD值
                    if(ModelDictCodeList.阶段_初始买入时_P1.getValue().equals(aiPdDto.getFinStage())){
                        //资产是否有交易处于二三阶段
                        aiPdDto.setProspectivePd(prospectivePd);
                    }
                    // 否则取资产对应剩余期限的对应矩阵PD值
                    else{
                        prospectivePd = ArithmeticUtil.doSub(BigDecimal.ONE,
                            BigDecimal.valueOf(Math.pow((ArithmeticUtil.doSub(BigDecimal.ONE,prospectivePd)).doubleValue(),
                                Double.valueOf(remainingTerm))));
                        aiPdDto.setProspectivePd(prospectivePd);
                    }
                }
            }

            //2.3定义 [PD计算过程DTO]列表 = 获取2.2查询结果列表
            variablePdCalculateDto.setModGradePdConfigList(modGradePdConfigList);
            //2.4生成配置使用情况入库结果
            //配置使用情况DTO列表
            variablePdCalculateDto.setModGradePdConfigUsedList(modGradePdConfigList);

            //3、加载减值配置使用情况表
            List<ModConfigUseageResultDto> modConfigUseageResultList= new ArrayList<>();
            for(ModGradePdConfigDto pdConfigDto:variablePdCalculateDto.getModGradePdConfigUsedList()){
                // 3.1查询配置使用情况表
                int countConfigUseAge =pdCalculateMapper.selectCountConfigUseAge(pdConfigDto.getSeqNo(),pdConfigDto.getConfigType());
                //若3.1查询结果大于0，则说明该配置已存在表中，不用加载减值配置使用情况表入库数，不入库。
                //若3.1查询结果==0，则加载减值配置使用情况表入库数据，加载逻辑参考输出结果-减值配置使用情况表，
                if(countConfigUseAge>0){
                    //不入库
                }else{
                    ModConfigUseageResultDto  configUseageResult = new ModConfigUseageResultDto();
                    configUseageResult.setSeqNo(IDGenerator.generate());
                    configUseageResult.setConfigSeqNo(pdConfigDto.getSeqNo());
                    configUseageResult.setConfigType(pdConfigDto.getConfigType());
                    configUseageResult.setUseProcess(ModelDictCodeList.模型_减值过程_PD计算.getValue());//04 PD计算
                    modConfigUseageResultList.add(configUseageResult);
                }
            }
            //待入库 减值配置使用情况表
            resultDto.setModConfigUseageResultList(modConfigUseageResultList);


            //待入库 减值结果过程表
            List<ModEclResultProcess> eclResultProcessResultList = new ArrayList<>();
            for(ModEclResultProcess assetImpairmentPdCalculateDto: variablePdCalculateDto.getAssetImpairmentPdCalculateList()){
                ModEclResultProcess modEclResultProcessResult = new ModEclResultProcess();
                BeanUtils.copyProperties(assetImpairmentPdCalculateDto, modEclResultProcessResult);
                modEclResultProcessResult.setSeqno(IDGenerator.generate());//流水号
                modEclResultProcessResult.setDecDate(plateDate);//减值日期
                modEclResultProcessResult.setLgd(null);//LGD违约损失率%
                modEclResultProcessResult.setPreProspectiveEcl(null);//前瞻前ECL预期信用损失
                modEclResultProcessResult.setProspectiveEcl(null);//前瞻后ECL预期信用损失
                modEclResultProcessResult.setProcess(ModelDictCodeList.模型_减值过程_PD计算.getValue());//过程  04

                eclResultProcessResultList.add(modEclResultProcessResult);

            }
            resultDto.setModEclResultProcessResultList(eclResultProcessResultList);
        } catch (Exception e) {
            //=====记录计算错误日志=====start=====
            logMsg ="【PD计算批处理】：计算异常";
            impairmentCommonService.sendLogContextInfo(stepExecution, batchExecuteInfo,
                readerDto.getAssetId(), logMsg,ModelDictCodeList.批处理执行结果_异常结束.getValue(), BATCH_LOG_RESULTS);
            //=====记录计算错误日志=====end=====
            return resultDto;
        }

        return resultDto;
    }


}
