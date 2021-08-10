package com.joyintech.tams.model.impairment.batch.pdcalculate.service;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.ModelDictCode;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dao.PdCalculateMapper;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:减值模型-PD计算批处理Service
 * @auther: yuzj
 * @date: 2021/7/14
 * @param:
 * @return:
 */
@Service
public class PdCalculateProcessService {
    @Autowired
    private PdCalculateMapper pdCalculateMapper;

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

        AssetImpairmentPdCalculateResultDto resultDto = new AssetImpairmentPdCalculateResultDto();
        //1.1=====初始化变量Dto=====start=====
        VariablePdCalculateDto variablePdCalculateDto = new VariablePdCalculateDto();
        //交易数据列表  === 资产交易信息DTO.get交易信息列表();
        variablePdCalculateDto.setAssetImpairmentPdCalculateList(readerDto.getAssetImpairmentPdCalculateList());
        //季度列表
        variablePdCalculateDto.setQuarterList(Arrays.asList(ModelDictCodeList.季度_第一季度.getValue(),ModelDictCodeList.季度_第二季度.getValue(),ModelDictCodeList.季度_第三季度.getValue(),ModelDictCodeList.季度_第四季度.getValue()));

        try {
            if(!StringUtil.isEmptyList(variablePdCalculateDto.getAssetImpairmentPdCalculateList())){
                //评级类型（=最终使用评级类型）
                variablePdCalculateDto.setoType(variablePdCalculateDto.getAssetImpairmentPdCalculateList().get(0).getoTypeFinal());
                //评级机构
                variablePdCalculateDto.setGradeOrgId(variablePdCalculateDto.getAssetImpairmentPdCalculateList().get(0).getGradeOrgId());
                //评级结果
                variablePdCalculateDto.setGradeResult(variablePdCalculateDto.getAssetImpairmentPdCalculateList().get(0).getResultCurrent());
                //主体是否持有金融牌照
                variablePdCalculateDto.setIsFinLicence(variablePdCalculateDto.getAssetImpairmentPdCalculateList().get(0).getIsFinLicence());
                //到期日
                variablePdCalculateDto.setMdate(variablePdCalculateDto.getAssetImpairmentPdCalculateList().get(0).getMdate());
                //资产到期日所在年月
                if(variablePdCalculateDto.getAssetImpairmentPdCalculateList().get(0).getMdate() != null){
                    variablePdCalculateDto.setMdateYearMonStr(DateUtil.format(variablePdCalculateDto.getAssetImpairmentPdCalculateList().get(0).getMdate(),"yyyyMM"));
                }
                //当前年月
                variablePdCalculateDto.setNowYearMonStr(DateUtil.format(plateDate,"yyyyMM"));
                //12个月后所在年月  从（当前月份+1月）开始往后推第12个月的年份月份
                variablePdCalculateDto.setEndYearMonStr(DateUtil.format(DateUtil.addMonthDate(new Date(),12+1),"yyyyMM"));
                //资产是否有交易处于二三阶段 false
                variablePdCalculateDto.setTtStage(false);
                //遍历 交易数据
                for(ModEclResultProcess aiPdDto :variablePdCalculateDto.getAssetImpairmentPdCalculateList()){
                    if(StringUtil.isNotEmpty(aiPdDto.getFinStage()) && "P1".equals(aiPdDto.getFinStage())){
                        //资产是否有交易处于二三阶段
                        variablePdCalculateDto.setTtStage(true);
                        break;
                    }
                }
                //资产剩余期限是否不足一年 false
                variablePdCalculateDto.setMaturityYear(false);
                if(StringUtil.isNotEmpty(variablePdCalculateDto.getMdateYearMonStr()) && DateUtil.compareDate(variablePdCalculateDto.getEndYearMonStr(),variablePdCalculateDto.getMdateYearMonStr())>0){
                    variablePdCalculateDto.setMaturityYear(true);
                }

            }

            //评级结果--最新评级结果
            if(variablePdCalculateDto.getGradeResult() ==null){
                //生成错误日志  --【批处理名称】：【机构名称/资产代码】无评级信息
                //=====记录计算错误日志=====start=====
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(readerDto.getAssetId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());

                String gradeOrgName = SysDataUtil.getNameFromDataDict(ModelDictCode.评级机构, variablePdCalculateDto.getGradeOrgId());
                batchParam.setMsg("【PD计算批处理】：【"+gradeOrgName+"/"+readerDto.getAssetId()+"】无评级信息");
                bBatchLogResultList.add(batchParam);
                sendlogResults(bBatchLogResultList,stepExecution);
                //=====记录计算错误日志=====end=====
                return null;
            }

            //=====1.1初始化变量Dto=====end=====

            //1.2 查询前瞻因子权重配置
            ModPdWeightConfigDto modPdWeightConfigDto  = pdCalculateMapper.selectModPdWeightConfigInfo();
            if(modPdWeightConfigDto==null){
                //生成错误日志
                //=====记录计算错误日志=====start=====
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(readerDto.getAssetId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());//EXP 失败
                batchParam.setMsg("【PD计算批处理】：Z值权重未维护");
                bBatchLogResultList.add(batchParam);
                sendlogResults(bBatchLogResultList,stepExecution);
                //=====记录计算错误日志=====end=====
                return null;
            }

            //2、查询计算基础[前瞻前年度边际PD]、[前瞻因子值]
            //2.1 判断需要查询PD的年份
            //定义 年份列表 = 估值日(跑批日)所在年份(含) -> 资产到期日所在年份(含) 中的所有年份
            variablePdCalculateDto.setYearList(getYearMonList(plateDate,variablePdCalculateDto.getMdate(),VariablePdCalculateDto.YEAR_TYPE));
            if(variablePdCalculateDto.getTtStage()==false && variablePdCalculateDto.getMaturityYear() ==false){
                Date endYearMonDate = DateUtil.parse(variablePdCalculateDto.getEndYearMonStr());
                variablePdCalculateDto.setYearList(getYearMonList(plateDate,endYearMonDate,VariablePdCalculateDto.YEAR_TYPE));
            }

            //2.2查询每一年的PD值 列表
            List<ModGradePdConfigDto> modGradePdConfigList = new ArrayList<>();
            if(variablePdCalculateDto.getYearList() !=null){
                for(String yearStr:variablePdCalculateDto.getYearList()){
                    //获取年份的评级PD配置列表
                    if(pdCalculateMapper.selectModGradePdConfigList(variablePdCalculateDto.getGradeResult(),new BigDecimal(yearStr)) !=null){
                        modGradePdConfigList.add(pdCalculateMapper.selectModGradePdConfigList(variablePdCalculateDto.getGradeResult(),new BigDecimal(yearStr)));
                    }else{
                        //生成警告日志
                        //=====记录计算错误日志=====start=====
                        ModValBatchLogResult batchParam = new ModValBatchLogResult();
                        batchParam.setFinprodId(readerDto.getAssetId());
                        batchParam.setTaskId(taskId);
                        batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                        batchParam.setType(ModelDictCodeList.批处理执行结果_警告.getValue());
                        batchParam.setMsg("【PD计算批处理】：【"+yearStr+"】年PD未维护");
                        bBatchLogResultList.add(batchParam);
                        sendlogResults(bBatchLogResultList,stepExecution);
                        //=====记录计算错误日志=====end=====
                    }
                }
            }
            //2.3定义 [PD计算过程DTO]列表 = 获取2.2查询结果列表
            variablePdCalculateDto.setModGradePdConfigList(modGradePdConfigList);

            //2.4生成配置使用情况入库结果
            //配置使用情况DTO列表
            variablePdCalculateDto.setModGradePdConfigUsedList(variablePdCalculateDto.getModGradePdConfigList());


            //3、计算加载PDstress
            List<ModGradePdConfigDto> pdConfigList = new ArrayList<ModGradePdConfigDto>();
            for(ModGradePdConfigDto pdConfigDto:variablePdCalculateDto.getModGradePdConfigList()){
                //3.1 计算每一年的风险暴露相关系数R
                BigDecimal R=new BigDecimal("0");
                //deciR3 = 1-(1/(e^(50*PD)))
                BigDecimal deciR1 = ArithmeticUtil.multiply(new BigDecimal(50),pdConfigDto.getForwardYearPd());
                Double deciR2 =Math.pow(Math.E, deciR1.doubleValue());
                BigDecimal deciR3 = ArithmeticUtil.subtract(BigDecimal.ONE,ArithmeticUtil.divide(BigDecimal.ONE,new BigDecimal(Double.toString(deciR2)),12));
                //deciR5=1-(1/e^50)
                Double deciR4=Math.pow(Math.E, new BigDecimal(50).doubleValue());
                BigDecimal deciR5=ArithmeticUtil.subtract(BigDecimal.ONE,ArithmeticUtil.divide(BigDecimal.ONE,new BigDecimal(Double.toString(deciR4)),12));
                //deciR6=deciR3/deciR5
                BigDecimal deciR6=ArithmeticUtil.divide(deciR3,deciR5,12);
                BigDecimal deciR7 =ArithmeticUtil.multiply(new BigDecimal("0.12"),deciR6);
                BigDecimal deciR8 =ArithmeticUtil.multiply(new BigDecimal("0.24"),ArithmeticUtil.subtract(BigDecimal.ONE,deciR6));
                if(variablePdCalculateDto.getIsFinLicence() ==null){
                    //=====记录计算错误日志=====start=====
                    ModValBatchLogResult batchParam = new ModValBatchLogResult();
                    batchParam.setFinprodId(readerDto.getAssetId());
                    batchParam.setTaskId(taskId);
                    batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                    batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                    batchParam.setMsg("【PD计算批处理】：无法判断【机构名称】是否金融机构");
                    bBatchLogResultList.add(batchParam);
                    sendlogResults(bBatchLogResultList,stepExecution);
                    //=====记录计算错误日志=====end=====
                    return null;
                }

                if("Y".equals(variablePdCalculateDto.getIsFinLicence())){
                    //使用金融机构风险暴露的相关系数为
                    R =ArithmeticUtil.multiply(new BigDecimal("1.25"),ArithmeticUtil.doAdd(deciR7,deciR8));
                }else{
                    //主权、一般公司风险暴露的相关系数为
                    R =ArithmeticUtil.doAdd(deciR7,deciR8);
                }
                pdConfigDto.setR(R);

                //3.2 遍历当年的每个季度，计算每个季度的ZtStress、PDStress
                //遍历季度列表

                for(String quarterStr :variablePdCalculateDto.getQuarterList()){
                    ModPdFactorConfigDto modPdFactorConfigDto =
                            pdCalculateMapper.selectModPdFactorConfig(pdConfigDto.getYearStr(),quarterStr);
                    String quarterStrName =getQuarterName(quarterStr);
                    if(modPdFactorConfigDto !=null){
                        //3.2.2 计算调整后Z值
                        if(ModelDictCodeList.季度_第一季度.getValue().equals(quarterStr)){//一季度
                            //乐观因子-一季度
                            pdConfigDto.setOptimisticFactorOne(getZtStress(modPdFactorConfigDto.getOptimisticFactor(),pdConfigDto));
                            pdConfigDto.setOptimisticFactorPdOne(getPdStress(pdConfigDto.getOptimisticFactorOne(),pdConfigDto));
                            //基础因子-一季度
                            pdConfigDto.setBasicFactorOne(getZtStress(modPdFactorConfigDto.getBasicFactor(),pdConfigDto));
                            pdConfigDto.setBasicFactorPdOne(getPdStress(pdConfigDto.getBasicFactorOne(),pdConfigDto));
                            //悲观因子-一季度
                            pdConfigDto.setPessimismFactorOne(getZtStress(modPdFactorConfigDto.getPessimismFactor(),pdConfigDto));
                            pdConfigDto.setPessimismFactorPdOne(getPdStress(pdConfigDto.getPessimismFactorOne(),pdConfigDto));
                        }else if(ModelDictCodeList.季度_第二季度.getValue().equals(quarterStr)){ //二季度
                            //乐观因子-二季度
                            pdConfigDto.setOptimisticFactorTwo(getZtStress(modPdFactorConfigDto.getOptimisticFactor(),pdConfigDto));
                            pdConfigDto.setOptimisticFactorPdTwo(getPdStress(pdConfigDto.getOptimisticFactorTwo(),pdConfigDto));
                            //基础因子-二季度
                            pdConfigDto.setBasicFactorTwo(getZtStress(modPdFactorConfigDto.getBasicFactor(),pdConfigDto));
                            pdConfigDto.setBasicFactorPdTwo(getPdStress(pdConfigDto.getBasicFactorTwo(),pdConfigDto));
                            //悲观因子-二季度
                            pdConfigDto.setPessimismFactorTwo(getZtStress(modPdFactorConfigDto.getPessimismFactor(),pdConfigDto));
                            pdConfigDto.setPessimismFactorPdTwo(getPdStress(pdConfigDto.getPessimismFactorTwo(),pdConfigDto));
                        }else if(ModelDictCodeList.季度_第三季度.getValue().equals(quarterStr)){ //三季度
                            //乐观因子-三季度
                            pdConfigDto.setOptimisticFactorThree(getZtStress(modPdFactorConfigDto.getOptimisticFactor(),pdConfigDto));
                            pdConfigDto.setOptimisticFactorPdThree(getPdStress(pdConfigDto.getOptimisticFactorThree(),pdConfigDto));
                            //基础因子-三季度
                            pdConfigDto.setBasicFactorThree(getZtStress(modPdFactorConfigDto.getBasicFactor(),pdConfigDto));
                            pdConfigDto.setBasicFactorPdThree(getPdStress(pdConfigDto.getBasicFactorThree(),pdConfigDto));
                            //悲观因子-三季度
                            pdConfigDto.setPessimismFactorThree(getZtStress(modPdFactorConfigDto.getPessimismFactor(),pdConfigDto));
                            pdConfigDto.setPessimismFactorPdThree(getPdStress(pdConfigDto.getPessimismFactorThree(),pdConfigDto));
                        }else if(ModelDictCodeList.季度_第四季度.getValue().equals(quarterStr)){ //四季度
                            //乐观因子-四季度
                            pdConfigDto.setOptimisticFactorFour(getZtStress(modPdFactorConfigDto.getOptimisticFactor(),pdConfigDto));
                            pdConfigDto.setOptimisticFactorPdFour(getPdStress(pdConfigDto.getOptimisticFactorFour(),pdConfigDto));
                            //基础因子-四季度
                            pdConfigDto.setBasicFactorFour(getZtStress(modPdFactorConfigDto.getBasicFactor(),pdConfigDto));
                            pdConfigDto.setBasicFactorPdFour(getPdStress(pdConfigDto.getBasicFactorFour(),pdConfigDto));
                            //悲观因子-四季度
                            pdConfigDto.setPessimismFactorFour(getZtStress(modPdFactorConfigDto.getPessimismFactor(),pdConfigDto));
                            pdConfigDto.setPessimismFactorPdFour(getPdStress(pdConfigDto.getPessimismFactorFour(),pdConfigDto));
                        }

                        //3.2.5 加载配置使用情况(在已有数据List基础上加，后会一起存入数据库)
                        ModGradePdConfigDto factorConfigDto = new ModGradePdConfigDto();
                        factorConfigDto.setSeqNo(modPdFactorConfigDto.getSeqNo());
                        factorConfigDto.setConfigType(modPdFactorConfigDto.getConfigType());
                        pdConfigList.add(factorConfigDto);

                    }else{
                        //生成警告日志
                        //=====记录计算错误日志=====start=====
                        ModValBatchLogResult batchParam = new ModValBatchLogResult();
                        batchParam.setFinprodId(readerDto.getAssetId());
                        batchParam.setTaskId(taskId);
                        batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                        batchParam.setType(ModelDictCodeList.批处理执行结果_警告.getValue());
                        batchParam.setMsg("【PD计算批处理】：【"+pdConfigDto.getYearStr()+"】年【"+quarterStrName+"】季度Z值未维护");
                        bBatchLogResultList.add(batchParam);
                        sendlogResults(bBatchLogResultList,stepExecution);
                        //=====记录计算错误日志=====end=====
                    }
                }
            }
            if(pdConfigList.size()>0){
                variablePdCalculateDto.getModGradePdConfigUsedList().addAll(pdConfigList);
            }

            //4、计算加载调整后月度累积PD
            //4.1 判断需要计算PD的年月列表
            //定义 年月列表 = 估值日所在年份 + 1月(含) -> 资产到期日所在年月(含) 中的所有年份 + 月份
            variablePdCalculateDto.setYearMonList(getYearMonList(plateDate,variablePdCalculateDto.getMdate(),VariablePdCalculateDto.YEARMON_TYPE));
            if(variablePdCalculateDto.getTtStage()==false && variablePdCalculateDto.getMaturityYear() ==false){
                Date endYearMonDate = DateUtil.parse(variablePdCalculateDto.getEndYearMonStr());
                variablePdCalculateDto.setYearMonList(getYearMonList(plateDate,endYearMonDate,VariablePdCalculateDto.YEARMON_TYPE));
            }

            //4.2 计算每个累积PD

            for(int i = 0; i < variablePdCalculateDto.getYearMonList().size() ; i++){
                String yearMonStr = variablePdCalculateDto.getYearMonList().get(i);//年月 202107
                String yearStr = variablePdCalculateDto.getYearMonList().get(i).substring(0,4);//年份 2021
                String monStr = variablePdCalculateDto.getYearMonList().get(i).substring(4);//月份 07
                for(ModGradePdConfigDto pdConfigDto: variablePdCalculateDto.getModGradePdConfigList()){
                    if(yearStr.equals(pdConfigDto.getYearStr())){
                        //先计算前瞻前的
                        if(i==0){
                            variablePdCalculateDto.setPreCdfPd(getPdm(pdConfigDto.getForwardYearPd()));
                        }else{
                            variablePdCalculateDto.setPreCdfPd(ArithmeticUtil.doAdd(variablePdCalculateDto.getPreCdfPd(),ArithmeticUtil.multiply(ArithmeticUtil.subtract(BigDecimal.ONE,variablePdCalculateDto.getPreCdfPd()),getPdm(pdConfigDto.getForwardYearPd()))));
                        }

                        //这里开始计算前瞻后PD
                        int monInt = Integer.valueOf(monStr).intValue();
                        if(monInt>=1 && monInt<=3){
                            if(i==0){
                                variablePdCalculateDto.setOptimisticCdfPd(getPdm(pdConfigDto.getOptimisticFactorPdOne()));
                                variablePdCalculateDto.setBasicCdfPd(getPdm(pdConfigDto.getBasicFactorPdOne()));
                                variablePdCalculateDto.setPessimismCdfPd(getPdm(pdConfigDto.getPessimismFactorPdOne()));
                            }else{
                                variablePdCalculateDto.setOptimisticCdfPd(getCdfPd(variablePdCalculateDto.getOptimisticCdfPd(),pdConfigDto.getOptimisticFactorPdOne()));
                                variablePdCalculateDto.setBasicCdfPd(getCdfPd(variablePdCalculateDto.getBasicCdfPd(),pdConfigDto.getBasicFactorPdOne()));
                                variablePdCalculateDto.setPessimismCdfPd(getCdfPd(variablePdCalculateDto.getPessimismCdfPd(),pdConfigDto.getPessimismFactorPdOne()));
                            }
                        }else if(monInt>=4 && monInt<=6){
                            if(i==0){
                                variablePdCalculateDto.setOptimisticCdfPd(getPdm(pdConfigDto.getOptimisticFactorPdTwo()));
                                variablePdCalculateDto.setBasicCdfPd(getPdm(pdConfigDto.getBasicFactorPdTwo()));
                                variablePdCalculateDto.setPessimismCdfPd(getPdm(pdConfigDto.getPessimismFactorPdTwo()));
                            }else{
                                variablePdCalculateDto.setOptimisticCdfPd(getCdfPd(variablePdCalculateDto.getOptimisticCdfPd(),pdConfigDto.getOptimisticFactorPdTwo()));
                                variablePdCalculateDto.setBasicCdfPd(getCdfPd(variablePdCalculateDto.getBasicCdfPd(),pdConfigDto.getBasicFactorPdTwo()));
                                variablePdCalculateDto.setPessimismCdfPd(getCdfPd(variablePdCalculateDto.getPessimismCdfPd(),pdConfigDto.getPessimismFactorPdTwo()));
                            }

                        }else if(monInt>=7 && monInt<=9){
                            if(i==0){
                                variablePdCalculateDto.setOptimisticCdfPd(getPdm(pdConfigDto.getOptimisticFactorPdThree()));
                                variablePdCalculateDto.setBasicCdfPd(getPdm(pdConfigDto.getBasicFactorPdThree()));
                                variablePdCalculateDto.setPessimismCdfPd(getPdm(pdConfigDto.getPessimismFactorPdThree()));
                            }else{
                                variablePdCalculateDto.setOptimisticCdfPd(getCdfPd(variablePdCalculateDto.getOptimisticCdfPd(),pdConfigDto.getOptimisticFactorPdThree()));
                                variablePdCalculateDto.setBasicCdfPd(getCdfPd(variablePdCalculateDto.getBasicCdfPd(),pdConfigDto.getBasicFactorPdThree()));
                                variablePdCalculateDto.setPessimismCdfPd(getCdfPd(variablePdCalculateDto.getPessimismCdfPd(),pdConfigDto.getPessimismFactorPdThree()));
                            }

                        }else if(monInt>=10 && monInt<=12){
                            if(i==0){
                                variablePdCalculateDto.setOptimisticCdfPd(getPdm(pdConfigDto.getOptimisticFactorPdFour()));
                                variablePdCalculateDto.setBasicCdfPd(getPdm(pdConfigDto.getBasicFactorPdFour()));
                                variablePdCalculateDto.setPessimismCdfPd(getPdm(pdConfigDto.getPessimismFactorPdFour()));
                            }else{
                                variablePdCalculateDto.setOptimisticCdfPd(getCdfPd(variablePdCalculateDto.getOptimisticCdfPd(),pdConfigDto.getOptimisticFactorPdFour()));
                                variablePdCalculateDto.setBasicCdfPd(getCdfPd(variablePdCalculateDto.getBasicCdfPd(),pdConfigDto.getBasicFactorPdFour()));
                                variablePdCalculateDto.setPessimismCdfPd(getCdfPd(variablePdCalculateDto.getPessimismCdfPd(),pdConfigDto.getPessimismFactorPdFour()));
                            }
                        }

                    }

                }

                if(i == 11){
                    //第12个月累积加权PD
                    BigDecimal deciTwelve1  =ArithmeticUtil.multiply(variablePdCalculateDto.getOptimisticCdfPd(),modPdWeightConfigDto.getOptimisticWeight());
                    BigDecimal deciTwelve2  =ArithmeticUtil.multiply(variablePdCalculateDto.getBasicCdfPd(),modPdWeightConfigDto.getBasicWeight());
                    BigDecimal deciTwelve3  =ArithmeticUtil.multiply(variablePdCalculateDto.getPessimismCdfPd(),modPdWeightConfigDto.getPessimismWeight());
                    variablePdCalculateDto.setTwelveMonWeightedPd(ArithmeticUtil.doAdd(deciTwelve1,deciTwelve2,deciTwelve3));
                    //前瞻前第12个月累积加权PD
                    variablePdCalculateDto.setPreTwelveMonWeightedPd(ArithmeticUtil.doAdd(deciTwelve1,deciTwelve2,deciTwelve3));
                    if(VariablePdCalculateDto.TRUE_STR.equals(variablePdCalculateDto.getIsFinLicence())){
                        variablePdCalculateDto.setMdateWeightedPd(ArithmeticUtil.multiply(variablePdCalculateDto.getMdateWeightedPd(), BigDecimal.valueOf(0.7)));
                        variablePdCalculateDto.setPreMdateWeightedPd(ArithmeticUtil.multiply(variablePdCalculateDto.getPreMdateWeightedPd(),BigDecimal.valueOf(0.7)));
                    }

                }
            }

            //5、加载每一笔交易数据的加权PD
            for(ModEclResultProcess aipcDto:variablePdCalculateDto.getAssetImpairmentPdCalculateList()){
                aipcDto.setPreProspectivePd(variablePdCalculateDto.getPreMdateWeightedPd());
                aipcDto.setProspectivePd(variablePdCalculateDto.getMdateWeightedPd());
            }

            //6、加载减值配置使用情况表
            List<ModConfigUseageResultDto> modConfigUseageResultList= new ArrayList<>();
            for(ModGradePdConfigDto pdConfigDto:variablePdCalculateDto.getModGradePdConfigUsedList()){
                int countConfigUseAge =pdCalculateMapper.selectCountConfigUseAge(pdConfigDto.getSeqNo(),pdConfigDto.getConfigType());
                //若6.1查询结果大于0，则说明该配置已存在表中，不用加载减值配置使用情况表入库数，不入库。
                //若6.1查询结果==0，则加载减值配置使用情况表入库数据，加载逻辑参考输出结果-减值配置使用情况表，
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
            ModValBatchLogResult batchParam = new ModValBatchLogResult();
            batchParam.setFinprodId(readerDto.getAssetId());
            batchParam.setTaskId(taskId);
            batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
            batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            batchParam.setMsg("【PD计算批处理】：计算错误");
            bBatchLogResultList.add(batchParam);
            sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录计算错误日志=====end=====
            return null;
        }

        return resultDto;
    }

    /**
     * @Description:根据开始日期和结束日期，获取日期间年份/年月集合，包含开始日期和结束日期当年年份
     * @auther: yuzj
     * @date: 2021/7/16
     * @param:type 1-年份{2019,2020,2021} ，  2-年月{201901,201902...,202107}
     * @return:
     */
    public List<String> getYearMonList(Date startDate,Date endDate,String type){
        List<String> yearList = new ArrayList<>();
        if(endDate ==null){
            return yearList;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        if("1".equals(type)){
            sdf = new SimpleDateFormat("yyyy");
        }else if("2".equals(type)){
            sdf = new SimpleDateFormat("yyyyMM");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        for (long d = cal.getTimeInMillis(); d <= endDate.getTime(); d = getYearMonAddOne(cal,type)) {
            yearList.add(sdf.format(d));
        }
        return yearList;

    }
    /**
     * @Description:给日期加1年
     * @auther: yuzj
     * @date: 2021/7/17
     * @param:
     * @return:
     */
    public  long getYearMonAddOne(Calendar c,String type) {
        if("1".equals(type)){
            c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
        }else if("2".equals(type)){
            c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        }
        return c.getTimeInMillis();
    }

    /**
     * @Description:3.2.3 ZtStress算法公式
     * ZtStress算法公式
     * @auther: yuzj
     * @date: 2021/7/17
     * @param:factor 前瞻因子
     * @return:
     */
    public BigDecimal  getZtStress(BigDecimal factor,ModGradePdConfigDto pdConfigDto){
        BigDecimal ztStress = BigDecimal.ZERO;
        NormalDistribution normalDistribution = new NormalDistribution();
        //deciZt1 =根号（1-R）
        double deciZt1 = Math.sqrt(ArithmeticUtil.subtract(BigDecimal.ONE,pdConfigDto.getR()).doubleValue());
        //deciZt2= N^-1(PD)
//        BigDecimal deciZt2 =ArithmeticUtil.divide(BigDecimal.ONE,normsdist(pdConfigDto.getForwardYearPd().doubleValue()),12);
        BigDecimal deciZt2 = new BigDecimal(Double.toString(normalDistribution.inverseCumulativeProbability(pdConfigDto.getForwardYearPd().doubleValue())));
        //deciZt3=1-deciZt1*deciZt2
        BigDecimal deciZt3 = ArithmeticUtil.subtract(BigDecimal.ONE,ArithmeticUtil.multiply(new BigDecimal(Double.toString(deciZt1)),deciZt2));
        ztStress= ArithmeticUtil.doAdd(factor,ArithmeticUtil.divide(deciZt3, BigDecimal.valueOf(Math.sqrt(pdConfigDto.getR().doubleValue())),12));

        return ztStress;
    }

    /**
     * @Description:  3.2.4 PDStress算法公式
     * PdStress算法公式
     * @auther: yuzj
     * @date: 2021/7/17
     * @param:factor 前瞻因子
     * @return:
     */
    public BigDecimal  getPdStress(BigDecimal ztStress,ModGradePdConfigDto pdConfigDto){
        BigDecimal pdStress = BigDecimal.ZERO;
        NormalDistribution normalDistribution = new NormalDistribution();
        //拆分算法，方便检查与理解
        //deciPd1 = 𝜑^-1(PD)
//        BigDecimal deciPd1 =ArithmeticUtil.divide(BigDecimal.ONE,normsdist(pdConfigDto.getForwardYearPd().doubleValue()),12);
        BigDecimal deciPd1 =new BigDecimal(Double.toString(normalDistribution.inverseCumulativeProbability(pdConfigDto.getForwardYearPd().doubleValue())));
        //deciPd2 =根号R* ztStress
        BigDecimal deciPd2 =ArithmeticUtil.multiply(BigDecimal.valueOf(Math.sqrt(pdConfigDto.getR().doubleValue())),ztStress);
        //deciPd3=根号(1-R)
        BigDecimal deciPd3 =new BigDecimal(Double.toString(Math.sqrt(ArithmeticUtil.subtract(BigDecimal.ONE,pdConfigDto.getR()).doubleValue())));
        //deciPd4 =(deciPd1-deciPd2)/deciPd3
        BigDecimal deciPd4 =ArithmeticUtil.divide(ArithmeticUtil.subtract(deciPd1,deciPd2),deciPd3,12);
//        pdStress=normsdist(deciPd4.doubleValue());
        pdStress=new BigDecimal(Double.toString(normalDistribution.cumulativeProbability(deciPd4.doubleValue())));
        return pdStress;
    }


    
    /**
     * @Description:PD计算
     * @auther: yuzj
     * @date: 2021/7/17
     * @param: 
     * @return: 
     */
    public BigDecimal getPdm(BigDecimal pdFactor){
        BigDecimal pdm = BigDecimal.ZERO;
        pdm = ArithmeticUtil.subtract(BigDecimal.ONE, BigDecimal.valueOf(Math.pow(ArithmeticUtil.subtract(BigDecimal.ONE,pdFactor).doubleValue(), 1.0/12)));
        return pdm;
    }

    /**
     * @Description:根据条件获取累计PD值
     * @auther: yuzj
     * @date: 2021/7/17
     * @param: 
     * @return: 
     */
    public BigDecimal getCdfPd(BigDecimal cdfPd,BigDecimal factorPd){
        return ArithmeticUtil.doAdd(cdfPd,ArithmeticUtil.multiply(ArithmeticUtil.subtract(BigDecimal.ONE,cdfPd),getPdm(factorPd)));
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
     * @auther: 根据季度编码获取季度名称
     * @date: 2021/7/27 22:54
     * @param:
     * @return: 季度名称
     */
    
    public String getQuarterName(String quarterStr){
        String quarterName="";
        if(ModelDictCodeList.季度_第一季度.getValue().equals(quarterStr)){//一季度
            quarterName=ModelDictCodeList.季度_第一季度.getCodeName();
        }else if(ModelDictCodeList.季度_第二季度.getValue().equals(quarterStr)){ //二季度
            quarterName=ModelDictCodeList.季度_第二季度.getCodeName();
        }else if(ModelDictCodeList.季度_第三季度.getValue().equals(quarterStr)){ //三季度
            quarterName=ModelDictCodeList.季度_第三季度.getCodeName();
        }else if(ModelDictCodeList.季度_第四季度.getValue().equals(quarterStr)){ //四季度
            quarterName=ModelDictCodeList.季度_第四季度.getCodeName();
        }
        return quarterName;
    }



}
