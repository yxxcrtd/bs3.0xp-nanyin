package com.joyintech.tams.model.valuation.batch.incomemethodmodel.service;
import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.constant.*;
import com.joyintech.calculation.shell.AccurIntForBookCalculator;
import com.joyintech.calculation.shell.param.AccurIntCalInParameter;
import com.joyintech.calculation.shell.param.AccurIntCalOutParameter;
import com.joyintech.calculation.shell.param.SecInfoModel;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.calculation.constant.GlobalConstant;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dao.IncomeMethodMapper;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.dto.modelDto.*;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import com.joyintech.tams.valuation.component.batch.service.ModValuationCommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:收益法估值批处理Service
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class ProductAssetsIncomeMethodService {
    private final String BATCH_LOG_RESULTS = "logResults";

    @Autowired
    private IncomeMethodMapper incomeMethodMapper;
    //引入共通service
    @Autowired
    private ModValuationCommonService modValuationCommonService;
    @Autowired
    private ModParamsService modParamsService;

    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public List<ProductAssetsIncomeMethodReaderDto> selectProductAssets(Map<String, Object> params){
        Date plateDate = (Date)params.get("PLATE_DATE");//平台日
        List<ProductAssetsIncomeMethodReaderDto> productAssetsIncomeMethodReaderDtoList = new ArrayList<ProductAssetsIncomeMethodReaderDto>();
        List<ProductAssetsIncomeMethodReaderDto> preReaderDtoList = new ArrayList<ProductAssetsIncomeMethodReaderDto>();
        //1.查询资产映射（返回资产分类五级分类集合）
        List<String> fiveTypeList = new ArrayList<String>();
        fiveTypeList = selectFiveTypeList();

        //2.调用【资产基本信息查询-估值模型】方法查询有持仓的资产
        preReaderDtoList = selectIncomeMethodDtoList(fiveTypeList,params);

        //过滤（帅选出符合查询条件的资产信息集合）
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }

        for(ProductAssetsIncomeMethodReaderDto preInComeDto: preReaderDtoList){
            if(finprodIdList.contains(preInComeDto.getFinprodId())){
                productAssetsIncomeMethodReaderDtoList.add(preInComeDto);
            }
        }

        //3.设定资产其他相关信息-数据组装
//        for(ProductAssetsIncomeMethodReaderDto inComeDto: productAssetsIncomeMethodReaderDtoList){
        for(int i=0;i<productAssetsIncomeMethodReaderDtoList.size();i++){

            // 该注释部分，可能是导致 优先股被过滤掉的原因
//            if((GlobalConstant.YES.equals(productAssetsIncomeMethodReaderDtoList.get(i).getIsRight()) && GlobalConstant.YES.equals(productAssetsIncomeMethodReaderDtoList.get(i).getIsSus())
//            && ModelDictCodeList.资产五级分类_优先股.equals(productAssetsIncomeMethodReaderDtoList.get(i).getAssetType5())) ||
//                    !ModelDictCodeList.资产五级分类_优先股.equals(productAssetsIncomeMethodReaderDtoList.get(i).getAssetType5())
//            )

            String isSus = productAssetsIncomeMethodReaderDtoList.get(i).getIsSus();            // 是否永续
            String assetType5 = productAssetsIncomeMethodReaderDtoList.get(i).getAssetType5();  // 资产五级分类_优先股
            if((GlobalConstant.YES.equals(productAssetsIncomeMethodReaderDtoList.get(i).getIsRight()) && GlobalConstant.YES.equals(isSus) && !ModelDictCodeList.资产五级分类_优先股.getValue().equals(assetType5))
                || (GlobalConstant.NO.equals(isSus) && !ModelDictCodeList.资产五级分类_优先股.getValue().equals(assetType5))
            ){
               //3.0过滤含权永续债
                productAssetsIncomeMethodReaderDtoList.remove(i);
                i--;
            }else{
                //3.1组装 预期股息增长率，调用【估值模型股息增长率配置】
                String stockIntrate = incomeMethodMapper.selectStockIntrateByFinprodId(productAssetsIncomeMethodReaderDtoList.get(i).getFinprodId());
                if(StringUtils.isNotEmpty(stockIntrate)){
                    productAssetsIncomeMethodReaderDtoList.get(i).setStockIntarte(new BigDecimal(stockIntrate));
                }
                //3.2组装-资产现金流计划集合
                productAssetsIncomeMethodReaderDtoList.get(i).setFinCashPlanDtoList(modValuationCommonService.getCashFlowPlanInfoByFinprodId(productAssetsIncomeMethodReaderDtoList.get(i).getFinprodId()));

                //3.3组装-现金流计算规则集合
                productAssetsIncomeMethodReaderDtoList.get(i).setFinCashCalcRuleDtoList(modValuationCommonService.getCashFlowCalRulesByFinprodId(productAssetsIncomeMethodReaderDtoList.get(i).getFinprodId()));

                //3.4组装-现金流支付规则集合
                List<ModCashFlowPayRulesDto> modCashFlowPayRulesList = modValuationCommonService.getCashFlowPayRulesByFinprodId(productAssetsIncomeMethodReaderDtoList.get(i).getFinprodId());
                productAssetsIncomeMethodReaderDtoList.get(i).setModCashFlowPayRulesList(modCashFlowPayRulesList);

            }
        }
        return productAssetsIncomeMethodReaderDtoList;
    }

    /**
     * @Description:返回处理后的结果DTO
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public ProductAssetsIncomeMethodResultDto convertResultFromReader(ProductAssetsIncomeMethodReaderDto item, Map<String, Object> mapParam,StepExecution stepExecution){
        ProductAssetsIncomeMethodResultDto resultDto = new ProductAssetsIncomeMethodResultDto();//从reader 返回结果result
        Date plateDate = (Date)mapParam.get("PLATE_DATE");//平台日
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)mapParam.get("BATCH_INFO");
        String taskId =(String)mapParam.get("TASK_ID");
        String finprodId=item.getFinprodId();
        List<ModValBatchLogResult> bBatchLogResultList = new ArrayList<>();
        //待计算变量初始化值
        AssetsVariableDto varDto = new AssetsVariableDto(new BigDecimal("0"),new BigDecimal("0"),
                new BigDecimal("0"),new BigDecimal("100"),new BigDecimal("0"),new BigDecimal("0"),
                new BigDecimal("0"), GlobalConstant.NO,new BigDecimal("0"),new BigDecimal("0"),
                new BigDecimal("0"),new BigDecimal("0"),new BigDecimal("0"),new BigDecimal("0"));
        //计算变量-股息预期股息增长率
        varDto.setStockIntrate(item.getStockIntarte());
        //计算变量-票面利率
        if(item!=null && item.getFinCashCalcRuleDtoList()!=null && item.getFinCashCalcRuleDtoList().size()>0){
            varDto.setPaperRate(item.getFinCashCalcRuleDtoList().get(0).getYield());
        }
        try {
            //0.计算买入全价
            if(item.getLatestTradeDate() !=null && DateUtil.format(item.getLatestTradeDate(),DateUtil.DEFAULT_OTHER_FORMAT).equals(DateUtil.format(plateDate,DateUtil.DEFAULT_OTHER_FORMAT))){
                varDto.setIsTodayTrade(GlobalConstant.YES);
            }
            if(varDto.getIsTodayTrade() !=null && varDto.getIsTodayTrade().equals(GlobalConstant.YES)){
                varDto.setbFullprice(item.getLatestAverPrice());
                varDto.setBenchDate(item.getLatestTradeDate());
            }else{
                AssetsMakePriceResultDto makePriceDto =
                        incomeMethodMapper.selectAssetsMakePriceByFinprodId(item.getFinprodId(),
                                DateUtil.format(plateDate,DateUtil.DEFAULT_OTHER_FORMAT ),
                                ModelDictCodeList.模型_估值模型名称_收益率法.getValue());
                if(makePriceDto !=null){
                    varDto.setbFullprice(makePriceDto.getbFullprice());
                    varDto.setBenchDate(makePriceDto.getBenchDate());
                }else{
                    varDto.setbFullprice(item.getLatestAverPrice());
                    varDto.setBenchDate(item.getLatestTradeDate());
                }
            }

            //1.计算年股息
            if(item.getFinCashPlanDtoList()!=null && item.getFinCashPlanDtoList().size()>0){
                //计算已还本面值
                for (ModCashFlowDto modCashFlowDto : item.getFinCashPlanDtoList()) {
                    if (modCashFlowDto.getCashType2().equals(ModelDictCodeList.现金流二级类型_本金.getValue())
                            && modCashFlowDto.getMdate().getTime() <= plateDate.getTime()) {
                        varDto.setPaidPrice(ArithmeticUtil.doAdd(varDto.getPaidPrice(),modCashFlowDto.getPrinAmt()));
                    }
                }
                varDto.setFaceValue(ArithmeticUtil.subtract(varDto.getFaceValue(),varDto.getPaidPrice()));//当前面值
                varDto.setYearDividend(ArithmeticUtil.multiply(varDto.getFaceValue(),varDto.getPaperRate()));
            }

            //2计算折现率
            //2.1取中债国债收益率曲线的最大年限点的利率
            String qxdmStr= ModelDictCodeList.曲线代码_中债国债收益率曲线.getValue();////数据字典.曲线代码.1231
            ModYeildCurveParamsDto modYeildCurveParamsVDto = new ModYeildCurveParamsDto();
            List<String> yeildIdVList = new ArrayList<>();
            yeildIdVList.add(qxdmStr);//数据字典.曲线代码.1231
            modYeildCurveParamsVDto.setYeildIdList(yeildIdVList);
            modYeildCurveParamsVDto.setSearchDate(plateDate);
            List<ModYieldCurveInfoDto> modYieldCurveInfoVList =  new ArrayList<>();


            Map<String,List<ModYieldCurveInfoDto>> mapVList = modValuationCommonService.getCurveInfo(modYeildCurveParamsVDto);
            if(!mapVList.isEmpty() && mapVList.get(qxdmStr) !=null){//数据字典.曲线代码.1231
                modYieldCurveInfoVList =(List<ModYieldCurveInfoDto>)mapVList.get(qxdmStr);
            }else{
                //=====记录警告日志=====start=====
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(finprodId);
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_警告.getValue());//警告
                batchParam.setMsg("【收益法模型批处理】：当天无收益率曲线行情");
                bBatchLogResultList.add(batchParam);
                sendlogResults(bBatchLogResultList,stepExecution);
                //=====记录警告日志=====end=====
            }
            varDto.setModYieldCurveInfoVList(modYieldCurveInfoVList);//估值日曲线收益率列表
            if(modYieldCurveInfoVList !=null && modYieldCurveInfoVList.size()>0){
                varDto.setValuationGovernmentDebtYield(modYieldCurveInfoVList.get(modYieldCurveInfoVList.size()-1).getYield());
            }
            //2.2基准日
            ModYeildCurveParamsDto modYeildCurveParamsBDto = new ModYeildCurveParamsDto();

            List<String> yeildIdBList = new ArrayList<>();
            yeildIdBList.add(qxdmStr);//数据字典.曲线代码.1231
            modYeildCurveParamsBDto.setYeildIdList(yeildIdBList);
            modYeildCurveParamsBDto.setSearchDate(varDto.getBenchDate());
            List<ModYieldCurveInfoDto> modYieldCurveInfoBList =  new ArrayList<ModYieldCurveInfoDto>();
            Map<String,List<ModYieldCurveInfoDto>> mapBList = modValuationCommonService.getCurveInfo(modYeildCurveParamsBDto);
            if(!mapBList.isEmpty() && mapBList.get(qxdmStr) !=null){//数据字典.曲线代码.1231
                modYieldCurveInfoBList =(List<ModYieldCurveInfoDto>)mapBList.get(qxdmStr);
            }else{
                //=====记录警告日志=====start=====
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(finprodId);
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_警告.getValue());//警告
                batchParam.setMsg("【收益法模型批处理】：当天无收益率曲线行情!");
                bBatchLogResultList.add(batchParam);
                sendlogResults(bBatchLogResultList,stepExecution);
                //=====记录警告日志=====end=====
            }
            varDto.setModYieldCurveInfoBList(modYieldCurveInfoBList);//估值日曲线收益率列表
            if(modYieldCurveInfoBList !=null && modYieldCurveInfoBList.size()>0){
                varDto.setBenchDateGovernmentDebtYield(modYieldCurveInfoBList.get(modYieldCurveInfoBList.size()-1).getYield());
            }
            //2.3折算率
            //算法==> 变量.折现率 = 变量.估值日国债收益率  + （（变量.当前面值*（1+ 变量.股息预期股息增长率）/ 变量.买入全价 + 变量.股息预期股息增长率）- 变量.基准日国债收益率 ）
            //拆分算法，方便检查阅读
            //deci1=（1+ 变量.股息预期股息增长率）
            BigDecimal deciRate1= ArithmeticUtil.doAdd(new BigDecimal("1"),varDto.getStockIntrate());
            //deci2=（变量.年股息*deci1/ 变量.买入全价 + 变量.股息预期股息增长率）
            BigDecimal deciRate2 = ArithmeticUtil.doAdd(ArithmeticUtil.divide(ArithmeticUtil.multiply(varDto.getYearDividend(),deciRate1),varDto.getbFullprice(),12),varDto.getStockIntrate());
            //deci3=变量.估值日国债收益率  +（deci2- 变量.基准日国债收益率 ）
            BigDecimal deciRate3= ArithmeticUtil.doAdd(varDto.getValuationGovernmentDebtYield(),ArithmeticUtil.subtract(deciRate2,varDto.getBenchDateGovernmentDebtYield()));
            varDto.setYearDiscountRate(deciRate3);

//            //估值全价计算
//            //算法==>变量.估值全价 = 变量.当前面值*（1 + 变量.股息预期股息增长率）/ (变量.折现率 - 变量.股息预期股息增长率)
//            //拆分算法，方便检查阅读
//            //deciPrice1=变量.年股息*（1 + 变量.股息预期股息增长率）
//            BigDecimal deciPrice1=ArithmeticUtil.multiply(varDto.getYearDividend(),ArithmeticUtil.doAdd(new BigDecimal("1"),varDto.getStockIntrate()));
//            //deciPrice2=deciPrice1/ (变量.折现率 - 变量.股息预期股息增长率)
//            BigDecimal deciPrice2= ArithmeticUtil.divide(deciPrice1,ArithmeticUtil.subtract(varDto.getYearDiscountRate(),varDto.getStockIntrate()),4);  ArithmeticUtil.subtract(varDto.getYearDiscountRate(),varDto.getStockIntrate());
//            varDto.setFullprice(deciPrice2);
//
//            //计算估值净价bAnalNetCnbd
//            //调用【算法包.计算百元面值的债券的利息-记账用】
//            BigDecimal interest = getCalculate(item, plateDate).getInterest();
////        //test-yu
////        BigDecimal interest =new BigDecimal(0);
//            varDto.setInterest(interest);
//            varDto.setbAnalNetCnbd(ArithmeticUtil.subtract(varDto.getFullprice(),varDto.getInterest()));

            /**
             * 3.估值全价、净价计算
             * 变量.估值净价 = 变量.年股息*（1 + 变量.股息预期股息增长率）/ (变量.折现率 - 变量.股息预期股息增长率)
             */
            // deciPrice1 = 变量.年股息*（1 + 变量.股息预期股息增长率）
            BigDecimal deciPrice1 = ArithmeticUtil.multiply(
                    varDto.getYearDividend(),
                    ArithmeticUtil.doAdd(new BigDecimal("1"), varDto.getStockIntrate())
            );
            // deciPrice1 / (变量.折现率 - 变量.股息预期股息增长率)
            varDto.setbAnalNetCnbd(
                    ArithmeticUtil.divide(deciPrice1, ArithmeticUtil.subtract(varDto.getYearDiscountRate(), varDto.getStockIntrate()), 14)
            );

            // 变量.单位应计利息 = 返回值.单位应计利息
            BigDecimal interest = getCalculate(item, plateDate).getInterest();
            varDto.setInterest(interest);

            // 变量.估值全价 = 变量.估值净价 + 变量.单位应计利息
            varDto.setFullprice(ArithmeticUtil.doAdd(varDto.getbAnalNetCnbd(), interest));

            //封装结果
            //1封装债券估值行情DTO
            ValBondValuationInfoResultDto vResultDto = new ValBondValuationInfoResultDto();
            vResultDto.setSeqNo(IDGenerator.generate());
            vResultDto.setFinprodId(item.getFinprodId());
            vResultDto.setValueDate(plateDate);
            vResultDto.setValueSource(ModelDictCodeList.模型_估值模型名称_收益率法.getValue());//数据字典.估值来源.模型
            vResultDto.setbAnalDirtyCnbd(varDto.getFullprice());
            vResultDto.setbAnalNetCnbd(varDto.getbAnalNetCnbd());

            //2封装资产定价DTO
            AssetsMakePriceResultDto mResultDto = new AssetsMakePriceResultDto();
            mResultDto.setSeqNo(IDGenerator.generate());
            mResultDto.setSecType(ModelDictCodeList.模型_证券品种_债券.getValue());
            mResultDto.setSecDetailType(item.getSecDetailType());
            mResultDto.setFinprodId(item.getFinprodId());
            mResultDto.setFinprodName(item.getFinprodAbbr());
            mResultDto.setBenchDate(varDto.getBenchDate());
            mResultDto.setbFullprice(varDto.getbFullprice());
            mResultDto.setValueModelMethod(ModelDictCodeList.模型_估值模型名称_收益率法.getValue());

            resultDto.setValBondValuationInfoResultDto(vResultDto);
            resultDto.setAssetsMakePriceResultDto(mResultDto);

        } catch (Exception e) {
            //=====记录错误日志=====start=====
            ModValBatchLogResult batchParam = new ModValBatchLogResult();
            batchParam.setFinprodId(finprodId);
            batchParam.setTaskId(taskId);
            batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
            batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            batchParam.setMsg("【收益法模型批处理】：计算错误!");
            bBatchLogResultList.add(batchParam);
            sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录错误日志=====end=====
            return null;
        }

        return resultDto;
    }

    /**
     * @Description:根据数据字典.模型映射类型.资产分类+模型证券品种.债券+数据字典.模型证券品种细分类(部分值)
     * 获取资产分类的五级分类集合 List<String>
     * @auther: yuzj
     * @date: 2021/7/7
     * @param: 
     * @return: 
     */
    public List<String> selectFiveTypeList(){
        //待传参数 DictCode.资产类型;
        List<String> assetsFiveTypeList = new ArrayList<String>();
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_他行发行的同业存单.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_国债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_地方政府债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_央票.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_政策性金融债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_政策性金融次级债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_铁道债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_金融债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_金融机构次级债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_企业债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_公司债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_交易所私募债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_短融.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_超短融.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_中票.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_银行间私募债.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_交易商协会ABN.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_银保监会主管ABS.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_证监会主管ABS.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_外国债券.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_理财直接融资工具.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_其他理财直接融资工具.getValue());
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_上市银行优先股.getValue()); // input2 上市银行优先股  =可在 基础数据资产信息映射关系表查
        assetsFiveTypeList.add(ModelDictCodeList.模型_证券细分类_非上市银行优先股.getValue()); // input2 非上市银行优先股 =可在 基础数据资产信息映射关系表查
        assetsFiveTypeList = incomeMethodMapper.selectFiveTypeList(ModelDictCodeList.模型_映射类型_资产分类映射.getValue(),
                ModelDictCodeList.模型_证券品种_债券.getValue(),assetsFiveTypeList);
        return assetsFiveTypeList;
    }

    /**
     * @Description:调用【资产基本信息查询-估值模型】方法查询有持仓的资产
     * @auther: yuzj
     * @date: 2021/7/7
     * @param:
     * @return:
     */
    public List<ProductAssetsIncomeMethodReaderDto>  selectIncomeMethodDtoList(List<String> fiveTypeList, Map<String, Object> mapParam){
        List<ProductAssetsIncomeMethodReaderDto> productAssetsIncomeMethodReaderDtoList = new ArrayList<ProductAssetsIncomeMethodReaderDto>();
        //获取共同中的资产信息
        ModAssetInfoParamsDto paraMap = new ModAssetInfoParamsDto();
        Date plateDate = (Date)mapParam.get("PLATE_DATE");//平台日

//        //test-yu
//        fiveTypeList.add("085");

        paraMap.setAssetFiveList(fiveTypeList);
        paraMap.setSearchDate(plateDate);//查询日=平台日
        List<ModAssetInfoDto> modAssetInfoDtoList = modValuationCommonService.selectAssetInfo(paraMap);
        for(ModAssetInfoDto modAssetInfoDto:modAssetInfoDtoList){
            ProductAssetsIncomeMethodReaderDto inDto = new ProductAssetsIncomeMethodReaderDto();
            inDto.setFinprodId(modAssetInfoDto.getFinprodId());//产品代码
            inDto.setFinprodAbbr(modAssetInfoDto.getFinprodAbbr());//产品简称
            inDto.setFinprodMarketId(modAssetInfoDto.getFinprodMarketId());//市场代码
            inDto.setVdate(modAssetInfoDto.getVdate());//起息日
            inDto.setMdate(modAssetInfoDto.getMdate());//到期日
            inDto.setIssuer(modAssetInfoDto.getIssuer());//发行人
            inDto.setFinancie(modAssetInfoDto.getFinancier());//融资人
            inDto.setSponsor(modAssetInfoDto.getSponsor());//担保人
            inDto.setIsSus(modAssetInfoDto.getIsSus());//是否永续
            inDto.setIsRight(modAssetInfoDto.getIsRight());//是否含权
            inDto.setConShareId(modAssetInfoDto.getConFinprodId());//转股代码
            inDto.setConvdate(modAssetInfoDto.getConVdate());//转股起始日
            inDto.setPbondvalue(modAssetInfoDto.getConPrice());//转股价格
            inDto.setActualCreditProvid(modAssetInfoDto.getActualCreaditProvid());//实际授信主体
            inDto.setAssetType1(modAssetInfoDto.getAssetOne());//资产一级分类
            inDto.setAssetType2(modAssetInfoDto.getAssetTwo());//资产二级分类
            inDto.setAssetType3(modAssetInfoDto.getAssetThree());//资产三级分类
            inDto.setAssetType4(modAssetInfoDto.getAssetFour());//资产四级分类
            inDto.setAssetType5(modAssetInfoDto.getAssetFive());//资产五级分类
            inDto.setLatestTradeDate(modAssetInfoDto.getLastTradeInDate());//最近买入交易发生日
            inDto.setLatestAverPrice(modAssetInfoDto.getLastTradeAvgPrice());//最近买入交易日交易均价
            inDto.setIsFinLicenceIssuer(modAssetInfoDto.getIssueFinLicence());//发行人是否持有金融牌照
            inDto.setIsFinLicenceFinancie(modAssetInfoDto.getFinancierFinLicence());//融资人是否持有金融牌照
            inDto.setIsFinLicenceSponsor(modAssetInfoDto.getSponsorFinLicence());//担保人是否持有金融牌照
            inDto.setIsFinLicenceActualCredit(modAssetInfoDto.getActualCreditFinLicence());//实际授信主体是否持有金融牌照
            inDto.setIssuePrice(modAssetInfoDto.getIssuePrice());//发行价
            inDto.setCouponSpecies(modAssetInfoDto.getCouponSpecies());//息票品种
            productAssetsIncomeMethodReaderDtoList.add(inDto);
        }
        return productAssetsIncomeMethodReaderDtoList;
    }

    public AccurIntCalOutParameter getCalculate(ProductAssetsIncomeMethodReaderDto entity, Date platDate) {
        // 组装参数
        AccurIntCalInParameter parameter = getCalInParam(entity, platDate);
        // 计算百元面值的债券的利息
        AccurIntForBookCalculator calculator2 = CalculatorFactory.createAccurIntForBookCalculator();
        AccurIntCalOutParameter output2 = calculator2.calculate(parameter);
        return output2;
    }


    /**
     * 算法包.计算百元面值的债券的利息-记账用传参封装
     * @param entity
     * @param platDate
     * @return
     */
    private AccurIntCalInParameter getCalInParam(ProductAssetsIncomeMethodReaderDto entity, Date platDate) {
        AccurIntCalInParameter parameter = new AccurIntCalInParameter();
        //计算日
//        Date date = DateUtil.parse("2021-09-25");
        //platDate
        parameter.setCalDate(BasicUtil.dateToLocal(platDate));

        // 计算日
        // 资产信息
        SecInfoModel secInfoModel = new SecInfoModel();
        // 债券起息日
        secInfoModel.setStartDate(BasicUtil.dateToLocal(entity.getVdate()));
        // 债券到期日
        secInfoModel.setEndDate(BasicUtil.dateToLocal(entity.getMdate()));
        // 债券首次付息日
        secInfoModel.setFirstPayDate(BasicUtil.dateToLocal(entity.getModCashFlowPayRulesList().get(0).getFirstPayDate()));
        // 发行价
        secInfoModel.setIssuePrice(entity.getIssuePrice());
        // 债券类型（息票品种）
        Variety bondsType = null;
        if (ModelDictCodeList.息票品种_贴现.getValue().equals(entity.getCouponSpecies())) {
            bondsType = Variety.贴现;
        }
        else if (ModelDictCodeList.息票品种_零息.getValue().equals(entity.getCouponSpecies())) {
            bondsType = Variety.零息;
        }
        else if (ModelDictCodeList.息票品种_附息.getValue().equals(entity.getCouponSpecies())) {
            bondsType = Variety.附息;
        }
        else if (ModelDictCodeList.息票品种_利随本清.getValue().equals(entity.getCouponSpecies())) {
            bondsType = Variety.利随本清;
        }
        secInfoModel.setBondsType(bondsType);
        // 计息基础
        Basis basis = null;
        String basisString = entity.getFinCashCalcRuleDtoList().get(0).getBasis();
        if (ModelDictCodeList.计息基础_A_360.getValue().equals(basisString)) {
            basis = Basis.A_360;
        }
        else if (ModelDictCodeList.计息基础_A_365.getValue().equals(basisString)) {
            basis = Basis.A_365;
        }
        else if (ModelDictCodeList.计息基础_A_A_平均分配.getValue().equals(basisString)
                || ModelDictCodeList.计息基础_A_A_按实际天数分配.getValue().equals(basisString)) {
            basis = Basis.A_A;
        }
        else if (ModelDictCodeList.计息基础_A_365F.getValue().equals(basisString)) {
            basis = Basis.A_365F;
        }
        else if (ModelDictCodeList.计息基础_30_360.getValue().equals(basisString)) {
            basis = Basis._30_360;
        }
        else if (ModelDictCodeList.计息基础_30U_360.getValue().equals(basisString)) {
            basis = Basis._30U_360;
        }

        secInfoModel.setBasis(basis);
        // 利率类型
        IntRateType intRateType = IntRateType.固定利率;
        secInfoModel.setIntRateType(intRateType);
        // 利息分配方式
        AllocationType allocationType = null;
        if (ModelDictCodeList.计息基础_A_A_平均分配.getValue().equals(basisString)) {
            allocationType = AllocationType.平均分配;
        }
        else if (ModelDictCodeList.计息基础_A_A_按实际天数分配.getValue().equals(basisString)) {
            allocationType = AllocationType.按实际天数分配;
        }
        else {
            allocationType = AllocationType.按实际天数分配;
        }
        secInfoModel.setAllocationType(allocationType);
        // 付息计划推算方式
        SecIntSchCalMethod secIntSchCalMethod = null;
        String secIntSchCalMethodString = entity.getModCashFlowPayRulesList().get(0).getCalcRule();
        if (ModelDictCodeList.推算方式_起息日向后.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.起息日向后;
        }
        else if (ModelDictCodeList.推算方式_到期日向前.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.到期日向前;
        }
        else if (ModelDictCodeList.推算方式_首次支付日.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.首次付息日;
        }
        secInfoModel.setCalMethod(secIntSchCalMethod);
        // 付息频率
        IntFrequency yearIntRate = null;
        String yearIntRateString = entity.getModCashFlowPayRulesList().get(0).getPayCycle();
        if (ModelDictCodeList.频率_月.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.月付;
        }
        else if (ModelDictCodeList.频率_季.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.季付;
        }
        else if (ModelDictCodeList.频率_年.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.年付;
        }
        else if (ModelDictCodeList.频率_半年.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.半年付;
        }
        else {
            yearIntRate = IntFrequency.年付;
        }
        secInfoModel.setYearIntRate(yearIntRate);
        parameter.setSecInfoModel(secInfoModel);

        // 付息计划列表
        List<SecIntSchedule> invScheduleList = new ArrayList<>();
        List<ModCashFlowDto> intSchList = new ArrayList<ModCashFlowDto>();
        for (ModCashFlowDto item : entity.getFinCashPlanDtoList()) {
            if (StringUtil.equals(item.getCashType2(), ModelDictCodeList.现金流二级类型_利息.getValue())
                    && item.getVdateUnadjust() != null) {
                SecIntSchedule schedule = new SecIntSchedule();
                schedule.setTheoryStartDate(toLocalDate(item.getVdateUnadjust()));
                schedule.setTheoryCutoffDate(toLocalDate(item.getMdateUnadjust()));
                schedule.setActualStartDate(toLocalDate(item.getVdate()));
                schedule.setActualCutoffDate(toLocalDate(item.getMdate()));
                schedule.setAmount(item.getIntAmt());
                schedule.setCorpus(new BigDecimal(100));
                invScheduleList.add(schedule);
                intSchList.add(item);
            }
        }
        parameter.setSecIntScheduleList(invScheduleList);

        // 债券利率重置信息
        List<PaperIrReset> paperIrResetList = entity.getFinCashCalcRuleDtoList().stream().map(item -> {
            PaperIrReset ret = new PaperIrReset();
            // BeanUtil.copyPropertiesIgnoreNull(ret, item);
            ret.setBaseRateEffDate(BasicUtil.dateToLocal(item.getEffDate()));
            ret.setParRate(item.getYield());
            ret.setParRateEffDate(BasicUtil.dateToLocal(item.getEffDate()));
            return ret;
        }).collect(Collectors.toList());
        if (paperIrResetList != null && paperIrResetList.size() > 0) {
            paperIrResetList.get(0).setBaseRateEffDate(BasicUtil.dateToLocal(entity.getVdate()));
        }
        if (paperIrResetList != null && paperIrResetList.size() > 1) {
            // 多重利率则计算按浮动利率算
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }
        parameter.setPaperIrResetList(paperIrResetList);

        // 当前面值
        BigDecimal currentValue = getCurrentValue(entity, platDate);
        parameter.setPaperAmt(currentValue);
        return parameter;
    }

    /**
     * 转换数据
     *
     * @param date 日期
     * @return LocalDate
     * @see
     * @since 1.0
     */
    private LocalDate toLocalDate(Date date) {
        if (date != null) {
            return LocalDate.parse(DateUtil.format(date));
        }
        return null;
    }

    /**
     * 获取当前面值
     * @param entity
     * @param platDate
     * @return
     */
    private BigDecimal getCurrentValue(ProductAssetsIncomeMethodReaderDto entity, Date platDate) {
        BigDecimal currentValue = BigDecimal.valueOf(100);
        //已还本面值
        BigDecimal printValue = BigDecimal.ZERO;
        for (ModCashFlowDto dto : entity.getFinCashPlanDtoList()){
            //If 明细.现金流类型 = 数据字典.现金流类型.中间还本 or 到期还本 and 明细.调整后的计算结束日 <= 跑批日
            if (StringUtil.equals(dto.getCashType2(), ModelDictCodeList.现金流二级类型_本金.getValue())
                    && dto.getMdate().compareTo(platDate) <= 0) {
                //变量.已还本面值 = 变量.已还本面值 + 明细.计划支付本金
                printValue = ArithmeticUtil.doAdd(printValue, dto.getPrinAmt());
            }
        }
        //变量.当前面值 = 变量.当前面值 - 变量.已还本面值
        currentValue = ArithmeticUtil.subtract(currentValue, printValue);
        return currentValue;
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




}
