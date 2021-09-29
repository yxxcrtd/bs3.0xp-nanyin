package com.joyintech.tams.model.impairment.batch.eclcalculation.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokFinProductValuationParaMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.*;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.AccountingCommonBatchService;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.valuation.common.service.CalCommonService;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BMWEclCalculationProcessorService {

    @Autowired
    private EclCalculationMapper eclCalculationMapper;

    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;

    @Autowired
    private ModLogsService modLogsService;

    @Autowired
    private FinProductMapper finProductMapper;

    @Autowired
    private BokFinProductValuationParaMapper bokFinProductValuationParaKeyMapper;

    @Autowired
    private FinProductCommonBatchService finProductCommonBatchService;

    @Autowired
    private CalCommonService calCommonService;

    @Autowired
    private AccountingCommonBatchService accountingCommonBatchService;

    /**
     * 减值模型数据加载-processor
     *
     * @param params
     * @return
     */
    public EclCalculationResultDto excute(EclCalculationReaderDto entity,
                                          Map<String, Object> params,
                                          StepExecution stepExecution) {
        // 1.初始化信息
        // 1.1 定义相关查询参数
        // 定义 变量.返回结果
        EclCalculationResultDto result = new EclCalculationResultDto();
        // 初始化返回结果集
        result.setModConfigUseageList(new ArrayList<>());
        result.setModEclResultProcessList(new ArrayList<>());
        result.setModEclResultList(new ArrayList<>());
        // 定义 变量.账套代码 = 返回结果.产品代码
        String booksetId = entity.getProdId();
        // 定义 变量.金融产品代码 = 返回结果.资产代码
        String finprodId = entity.getAssetId();
        //跑批日
        Date platDate = (Date) params.get("PLATE_DATE");
        // 批处理任务执行信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo) params.get("BATCH_INFO");
        // 定义 变量.减值配置使用情况表List
        List<ModConfigUseage> modConfigUseageList = new ArrayList<>();
        // 定义 变量.减值结果过程表List
        List<ModEclResultProcess> modEclResultProcessList = new ArrayList<>();
        // 定义 变量.资产减值结果表List
        List<ModEclResult> modEclResultList = new ArrayList<>();

        // 1.2 查询标的类金融产品信息
        // 定义查询list
        List<TargetSearchDto> targetSearchDtoList = new ArrayList<TargetSearchDto>();
        // 定义查询类型 实体类
        TargetSearchDto 标的信息 = new TargetSearchDto();
        标的信息.setQueryType(ValDictCodeList.标的_信息.getValue());
        // 查询实体类塞入查询实体类List中
        targetSearchDtoList.add(标的信息);
        Map<TargetSearchDto, Object> resultMap = finProductCommonBatchService.getTargetInfo(
                finprodId, targetSearchDtoList);
        TargetFinProduct targetFinProduct = (TargetFinProduct) resultMap.get(标的信息);
        if (targetFinProduct == null) {
            return result;
        }
        // 查询账务明细代码
        String bokDetailId = eclCalculationMapper.getBokDetailId(booksetId, finprodId, entity.getInvAim());

        // 定义 变量.减值结果过程List  = map.get(key)
        List<ModEclResultProcess> listProcess = entity.getModEclResultProcessList();
        if (CollectionUtils.isEmpty(listProcess)) {
            return result;
        }
        // 按资产分组，毕马威方案只有一条数据, 故取第一条作为资产信息使用
        ModEclResultProcess entityDto = listProcess.get(0);
        List<CommonErrorParams> errors = (List<CommonErrorParams>) stepExecution.getJobExecution().getExecutionContext().get(
                "ERRORS");
        if (errors == null) {
            errors = new ArrayList<CommonErrorParams>();
        }
        //模型计算LGD
        caclLGD(entity, entityDto, params, batchExecuteInfo, errors, stepExecution, modConfigUseageList);

        // 计算ECL
//        // 查询成本科目性质
//        List<String> subjectNatureList = accountingCommonBatchService
//                .getValSubjectNatureListWithAcctStandardId(DictCodeListNatureGroup.分组代码_成本类对应科目查询.getValue(), null);

        // 定义 变量.减值结果过程表
        ModEclResultProcess modEclResultProcess = new ModEclResultProcess();
        // 定义 变量.资产减值结果表
        ModEclResult modEclResult = new ModEclResult();
        // 定义 变量.数据来源 = 模型
        String dataSource = DictCodeListExt3.减值数据来源_模型.getValue();

        String ECL比例结果保留小数位 = StringUtil.isEmpty(CacheUtil.getSysParam(
                "SYS_PARA_ECL_RATIO_DIGIT")) ? "6" : CacheUtil.getSysParam(
                "SYS_PARA_ECL_RATIO_DIGIT");
        String ECL比例结果保留小数位尾数处理方式 = StringUtil.isEmpty(CacheUtil.getSysParam(
                "SYS_PARA_ECL_RATIO_DIGIT_TYPE")) ? DictCodeList.估值舍入规则_四舍五入.getValue() : CacheUtil.getSysParam(
                "SYS_PARA_ECL_RATIO_DIGIT_TYPE");
        try {
            // 计算ECL
            // 依据资产减值管理界面资产数据来源字段区分，手工、模型、wind、中债
            // 查询资产减值日期对应的资产减值管理数据
            Map<String, Object> searchPara = new HashMap<>();
            searchPara.put("assetId", finprodId);
            searchPara.put("platDate", platDate);
            // 查询按照录入方式升序排序,优先取手工数据，其次取导入数据，故只需取第一条即可
            List<TrdAssetLoss> listTrdAssetLoss = eclCalculationMapper.queryEclManage(
                    searchPara);
            // 定义 变量.ecl比例Dto = null
            EclRatioDto eclRatioDto = null;
            // 定义 变量.ecl比例
            BigDecimal eclRatio = BigDecimal.ZERO;
            if(!CollectionUtils.isEmpty(listTrdAssetLoss)){
                // 将查询结果按数据来源分组：手工、wind、中债
                Map<String, List<TrdAssetLoss>> lossMap = listTrdAssetLoss.stream().collect(Collectors.groupingBy(TrdAssetLoss::getLossInputType));
                // 返回结果中手工数据来源不为空
                if (!CollectionUtils.isEmpty(lossMap.get(DictCodeListExt3.减值数据来源_手工.getValue()))) {
                    // 数据来源
                    dataSource = DictCodeListExt3.减值数据来源_手工.getValue();
                    listTrdAssetLoss = lossMap.get( DictCodeListExt3.减值数据来源_手工.getValue());
                }
                else if (!CollectionUtils.isEmpty(lossMap.get(DictCodeListExt3.减值数据来源_wind接入.getValue()))) {
                    // 数据来源
                    dataSource = DictCodeListExt3.减值数据来源_wind接入.getValue();
                    listTrdAssetLoss = lossMap.get( DictCodeListExt3.减值数据来源_wind接入.getValue());
                }
                else{
                    // 数据来源
                    dataSource = DictCodeListExt3.减值数据来源_中债接入.getValue();
                    listTrdAssetLoss = lossMap.get( DictCodeListExt3.减值数据来源_中债接入.getValue());
                }

                // 调用共通根据最终减值流水计算ECL损失比例（当有持仓数据位于三阶段时，则以三阶段减值行情日数据预期信用损失比例为准）
                eclRatioDto = this.calcEclByTrdLoss(listTrdAssetLoss, targetFinProduct,
                        booksetId, entity.getInvAim(), platDate);
            }
            // 无手工录入、wind、中债导入，采用模型LGD计算
            if(eclRatioDto == null) {
                //交易信息.ECL预期信用损失比例 = 交易信息.前瞻后PD违约概率% * 交易信息.LGD  四舍五入保留4位小数
                eclRatio =ArithmeticUtil.multiply(entityDto.getProspectivePd(),entityDto.getLgd());
            }
            else{
                // 变量.ecl比例Dto
                eclRatio = eclRatioDto.getEclRatio();
                // PD = 变量.ecl比例Dto.PD
                entityDto.setProspectivePd(eclRatioDto.getPd());
                // LGD = 变量.ecl比例Dto.LGD
                entityDto.setLgd(eclRatioDto.getLgd());
                // 减值阶段 = 变量.ecl比例Dto.减值阶段
                entityDto.setFinStage(eclRatioDto.getFinStage());
            }
            // 系统参数控制ECL比例保留小数位数以及尾数处理方式
            eclRatio = calCommonService.round(eclRatio, ECL比例结果保留小数位尾数处理方式, ECL比例结果保留小数位);
            entityDto.setEclRatio(eclRatio);
            //3.3 封装结果集
            quaryResultParam(entityDto, modEclResultProcess, modEclResult, platDate, dataSource);

        } catch (Exception e) {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entityDto.getAssetId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg(
                    "【ECL计算批处理】：资产【" + entityDto.getAssetId() + "】对应交易信息【" + entityDto.getTradeId()
                            + "】计算异常");
            commonErrorParams.setErrorType(ModelDictCodeList.模型计算结果_失败.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            e.getMessage();
        }
        // 变量.减值结果过程表List 更新
        modEclResultProcessList.add(modEclResultProcess);
        // 变量.资产减值结果表List 更新
        modEclResultList.add(modEclResult);

        result.setModConfigUseageList(modConfigUseageList);
        result.setModEclResultProcessList(modEclResultProcessList);
        result.setModEclResultList(modEclResultList);
        return result;
    }

    /**
     * 封装结果集
     *
     * @param entity
     * @param modEclResultProcess
     * @param modEclResult
     * @param input2
     * @param platDate
     * @param finStage 减值阶段
     */
    private void quaryResultParam(ModEclResultProcess entity,
                                  ModEclResultProcess modEclResultProcess,
                                  ModEclResult modEclResult, Date platDate, String dataSource) {
        //减值结果过程表
        BeanUtil.copyPropertiesIgnoreNull(modEclResultProcess, entity);
        //序列号								自动生成
        modEclResultProcess.setSeqno(IDGenerator.generate());
        //减值日期								跑批日
        modEclResultProcess.setDecDate(platDate);
        //过程								“05 ECL计算”，此批处理主要加载数据。
        modEclResultProcess.setProcess(ModelDictCodeList.模型_减值过程_ECL计算.getValue());

        //资产减值结果表
        BeanUtil.copyPropertiesIgnoreNull(modEclResult, entity);
        //序列号								自动生成
        modEclResult.setSeqno(IDGenerator.generate());
        //资产类型
        modEclResult.setAssetType(entity.getModelAssetType());
        //数据来源
        modEclResult.setDataSource(dataSource);
        // 买入时点评级
        modEclResult.setPurchaseTimeGrade(entity.getResultDeal());
        // 当前时点评级
        modEclResult.setCurrentTimeGrade(entity.getResultCurrent());
        // 是否允许模型覆盖 = 是
        modEclResult.setAllowModelCover(DictCodeList.是否_是.getValue());
    }

    /**
     * 模型计算LGD值
     *
     * @param entityDto
     * @param params
     * @param batchExecuteInfo
     * @param errors
     * @param stepExecution
     * @param modConfigUseage
     */
    public void caclLGD(EclCalculationReaderDto entity, ModEclResultProcess entityDto, Map<String, Object> params,
                        SysBatchExecInfo batchExecuteInfo, List<CommonErrorParams> errors,
                        StepExecution stepExecution, List<ModConfigUseage> modConfigUseageList) {
        // 债券类、存款类、回购类资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> otherLgdConfigMap = entity.getOtherLgdConfigMap();
        // 其他债权类资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> deltRightLgdConfigMap = entity.getDeltRightLgdConfigMap();
        // 特殊资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> specialLgdConfigMap = entity.getSpecialLgdConfigMap();

        // LGD配置信息判空
        if(CollectionUtils.isEmpty(specialLgdConfigMap)){
            if (DictCodeListExt1.证券品种_其他债权类.getValue().equals(entityDto.getModelAssetType()) && CollectionUtils.isEmpty(specialLgdConfigMap)) {
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),entityDto.getAssetId(), "资产无对应LGD配置信息");
                return;
            }
            else if(CollectionUtils.isEmpty(otherLgdConfigMap)){
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),entityDto.getAssetId(), "资产无对应LGD配置信息");
                return;
            }
        }

        // LGD小数位以及尾数处理方式取值
        String LGD违约损失小数位 = StringUtil.isEmpty(CacheUtil.getSysParam(
                "SYS_PARA_LGD_DECIMAL_DIGIT")) ? "6" : CacheUtil.getSysParam(
                "SYS_PARA_LGD_DECIMAL_DIGIT");
        String LGD违约损失小数尾数处理方式 = StringUtil.isEmpty(CacheUtil.getSysParam(
                "SYS_PARA_LGD_DIGIT_TYPE")) ? DictCodeList.估值舍入规则_四舍五入.getValue() : CacheUtil.getSysParam(
                "SYS_PARA_LGD_DIGIT_TYPE");

        //定义 变量.LGD配置信息
        ModLgdConfigDto configDto = new ModLgdConfigDto();
        //定义 变量.LGD配置信息使用情况List
        List<ModLgdConfigDto> configDtoList = new ArrayList<ModLgdConfigDto>();
        // 定义 变量.LGD最终值 = 0
        BigDecimal lgdRate = BigDecimal.ZERO;
        //1、处理回购类资产
        if (DictCodeListExt1.证券品种_回购.getValue().equals(entityDto.getModelAssetType())) {
            // 回购类资产查询对应质押券信息
            List<FinDealPledgeDTO> finDealPledgeDTOList = eclCalculationMapper.queryFinDealPledgeByFinprodId(
                    entityDto.getAssetId());
            if (CollectionUtils.isEmpty(finDealPledgeDTOList)) {
                CommonErrorParams commonErrorParams = new CommonErrorParams();
                commonErrorParams.setFinprodId(entityDto.getAssetId());
                commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
                commonErrorParams.setErrorMsg("【ECL计算批处理】：回购资产【" + entityDto.getAssetId() + "】无质押券信息");
                commonErrorParams.setErrorType(ModelDictCodeList.模型计算结果_失败.getValue());
                errors.add(commonErrorParams);
                stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
                return;
            }
            // 1.1 当存在多笔质押券时，则采用加权计算最终LGD值
            if (finDealPledgeDTOList.size() > 1) {
                // 定义 折算后汇总金额
                BigDecimal totalPledgeAmt = BigDecimal.ZERO;
                // 循环遍历取质押券对应债券的LGD配置
                for (FinDealPledgeDTO item : finDealPledgeDTOList) {
                    // 折算后汇总金额 += 折算后质押金额
                    totalPledgeAmt = ArithmeticUtil.doAdd(totalPledgeAmt, item.getPledgeAmt());
                }
                // 循环遍历质押券信息
                for (FinDealPledgeDTO item : finDealPledgeDTOList) {
                    configDto = null;
                    // 优先取质押券对应特殊资产LGD配置
                    if (specialLgdConfigMap != null && specialLgdConfigMap.get(item.getPledgeFinprodId()) != null) {
                        configDto = specialLgdConfigMap.get(item.getPledgeFinprodId());
                    } else {
                        // 债券类、存款类、回购类资产LGD违约损失率配置 中 取质押券：资产类型+资产细分类的LGD值
                        configDto = otherLgdConfigMap.get(item.getSecType() + GlobalConstant.UNDER_LINE
                                + item.getSecDetailType());
                    }
                    if (configDto != null) {
                        //  LGD = Σ每笔质押券折算后金额/汇总质押券折算后金额*当前质押券类型维护的LGD值（%）
                        lgdRate = ArithmeticUtil.doAdd(lgdRate, ArithmeticUtil.multiply(ArithmeticUtil.divide(item.getPledgeAmt(), totalPledgeAmt),
                                configDto.getLgd()));
                        configDtoList.add(configDto);
                    }
                }
            }
            // 1.2 当没有多笔质押券时，直接取界面对应质押券的LGD维护值
            else {
                // 优先取特殊资产配置
                String assetId = finDealPledgeDTOList.get(0).getPledgeFinprodId();
                // 取界面对应质押券的LGD维护值
                if (specialLgdConfigMap != null && specialLgdConfigMap.get(assetId) != null) {
                    configDto = specialLgdConfigMap.get(assetId);
                } else {
                    // 债押券资产类型 + 质押券资产细分类
                    configDto = otherLgdConfigMap.get(finDealPledgeDTOList.get(0).getSecType() + GlobalConstant.UNDER_LINE + finDealPledgeDTOList.get(0).getSecDetailType());
                }
                if (configDto != null) {
                    // 变量.LGD最终值 = 取LGD界面维护值
                    lgdRate = configDto.getLgd();
                    configDtoList.add(configDto);
                }
            }
        }
        // 优先特殊资产LGD配置取值：key--资产代码
        else if (specialLgdConfigMap != null && specialLgdConfigMap.get(entityDto.getAssetId()) != null) {
            configDto = specialLgdConfigMap.get(entityDto.getAssetId());
            lgdRate = configDto.getLgd();
            configDtoList.add(configDto);
        }
        // 其他债权类资产LGD配置取值：key--资产类型+是否含有抵质押物
        else if (DictCodeListExt1.证券品种_其他债权类.getValue().equals(entityDto.getModelAssetType())) {
            configDto = deltRightLgdConfigMap.get(
                    entityDto.getModelAssetType() + GlobalConstant.UNDER_LINE
                            + entityDto.getIsContainPledge());
            lgdRate = configDto.getLgd();
            configDtoList.add(configDto);
        }
        // 债券、存款类资产LGD配置取值：key--资产类型+资产细分类
        else {
            configDto = otherLgdConfigMap.get(
                    entityDto.getModelAssetType() + GlobalConstant.UNDER_LINE
                            + entityDto.getModelAssetDetailType());
            lgdRate = configDto.getLgd();
            configDtoList.add(configDto);
        }

        // 三阶段取(LGD+100%)÷2，LGD取界面维护值
        if (ModelDictCodeList.阶段_买入后出现减值_P3.getValue().equals(entityDto.getFinStage())) {
            lgdRate = ArithmeticUtil.divide(ArithmeticUtil.doAdd(BigDecimal.ONE, lgdRate), 2);

        }
        // 模型计算LGD值
        if (!CollectionUtils.isEmpty(configDtoList)) {
            // 进行位数保留和尾数处理（取决于系统参数）
            lgdRate = calCommonService.round(lgdRate, LGD违约损失小数尾数处理方式, LGD违约损失小数位);
            //交易信息.LGD = LGD参数Dto.LGD
            entityDto.setLgd(lgdRate);
            // 循环遍历配置使用情况List，当回购存在多笔质押券时，会有多条数据，否则都是一条数据
            for (ModLgdConfigDto item : configDtoList) {
                //定义 配置流水号 = LGD参数Dto.流水号
                String seqno = item.getSeqno();
                //定义 配置类型 = LGD参数Dto.配置类型
                String configType = item.getConfigType();

                //4.1 判断使用的参数是否已存在减值配置使用情况表
                Map<String, Object> para = new HashMap<>();
                para.put("configSeqno", seqno);
                para.put("configType", configType);
                int resultCount = impairmentDataLoadMapper.selectModConfigUseageCount(para);
                if (resultCount == 0) {
                    ModConfigUseage modConfigUseage = new ModConfigUseage();
                    modConfigUseage.setSeqno(IDGenerator.generate());
                    modConfigUseage.setUseProcess(ModelDictCodeList.模型_减值过程_ECL计算.getValue());
                    modConfigUseage.setConfigSeqno(seqno);
                    modConfigUseage.setConfigType(configType);
                    modConfigUseageList.add(modConfigUseage);
                }
            }
        } else {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entityDto.getAssetId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【ECL计算批处理】：资产【" + entityDto.getAssetId() + "】未配置LGD参数");
            commonErrorParams.setErrorType(ModelDictCodeList.模型计算结果_失败.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
        }
    }

    /**
     * 通过减值流水计算ECL损失比例
     *
     * @param trdAssetLoss 资产减值流水列表
     * @param targetFinProduct 标的信息
     * @param happenDate 发生日
     * @param bookDate 入账日
     * @param booksetId 账套代码
     * @param bokDetailId 账务明细代码
     * @param subjectNatureList 科目性质列表
     * @return
     */
    public EclRatioDto calcEclByTrdLoss(List<TrdAssetLoss> trdAssetLoss, TargetFinProduct targetFinProduct,
                                        String booksetId, String invAim, Date happenDate) {
        EclRatioDto eclRatioDto = new EclRatioDto();
        BigDecimal ecl = BigDecimal.ZERO;
        eclRatioDto.setEclRatio(ecl);
        Map<BigDecimal,List<TrdAssetLoss>> map= trdAssetLoss.stream().collect(Collectors.groupingBy(TrdAssetLoss::getPeroid));
        List<TrdAssetLoss> eclTrdAssetLoss  = null;
        if(map.size()>1){
            String exeDate = DictCodeList.含权债摊销日_到期日.getValue();
            if(targetFinProduct!=null){
                exeDate = finProductCommonBatchService.getExeDate(targetFinProduct, booksetId).getExeDate();
                if(StringUtil.isEmpty(exeDate) ){
                    exeDate  = DictCodeList.含权债摊销日_到期日.getValue();
                }
            }
            BigDecimal peroid = accountingCommonBatchService.peroidDecide(map.keySet(),exeDate);
            eclTrdAssetLoss = map.get(peroid);
        }else{
            eclTrdAssetLoss = trdAssetLoss;
        }
        if(StringUtil.isEmptyList(eclTrdAssetLoss)){
            return null;
        }
        if(eclTrdAssetLoss.size()>1){
            // 降序排列
            Collections.sort(eclTrdAssetLoss, (o1, o2) -> o2.getEndDate().compareTo(o1.getEndDate()));
            // 获取资产买入交易日期列表
            List<Date> dates = accountingCommonBatchService.getBuyDate(happenDate, invAim, booksetId, targetFinProduct.getFinprodId());
            // 定义 变量.计数器 = 0;
            int num =0;
            if(StringUtil.isEmptyList(dates)){
                return null;
            }
            // 定义 变量.最终减值结果 = 列表第一条数据（最新）
            TrdAssetLoss resultLoss = null;
            for(TrdAssetLoss loss :eclTrdAssetLoss){
                // 若为三阶段，则直接返回
                if(DictCodeListExt3.阶段_买入后出现减值_P3.getValue().equals(loss.getStage())){
                    resultLoss = loss;
                    break;
                }else{
                    for(Date date :dates){
                        if(date == null){
                            continue;
                        }
                        // 交易日期 >=开始日期 且 交易日期<= 结束日期
                        if(loss.getStrDate().compareTo(date)<=0 && loss.getEndDate().compareTo(date)>=0 ){
                            // 计数+1
                            num++;
                            // 当最终结果为空，则表示第一次匹配到减值流水
                            if(resultLoss == null){
                                // 变量.最终结果 = 减值流水
                                resultLoss = loss;
                            }
                            // 否则后续依次判断，若减值阶段不同，则直接减值阶段为同处一二阶段
                            else if(!StringUtil.equals(loss.getStage(), resultLoss.getStage())){
                                resultLoss.setStage(DictCodeListExt3.阶段_买入后同处一二阶段_P12.getValue());
                            }
                            // ecl累计值+= 减值流水.ecl比例
                            ecl = ArithmeticUtil.doAdd(ecl,loss.getEclVaule());
                            break;
                        }
                    }
                }

            }
            if(resultLoss == null){
                return null;
            }
            // 减值阶段 = 最终结果.减值阶段
            eclRatioDto.setFinStage(resultLoss.getStage());
            // 只有同处一二阶段，才会计算算术平均值，直接返回ecl
            if(DictCodeListExt3.阶段_买入后同处一二阶段_P12.getValue().equals(resultLoss.getStage())){
                // 计算累加值，算算术平均值，PD、LGD为空
                eclRatioDto.setEclRatio( ArithmeticUtil.divide(ecl, num));
            }
            // 否则，直接取最终的最新数据
            else{
                eclRatioDto.setPd(resultLoss.getPdVaule());
                eclRatioDto.setLgd(resultLoss.getLgdVaule());
                eclRatioDto.setEclRatio(resultLoss.getEclVaule());
            }
            return eclRatioDto;
        }else{
            eclRatioDto.setEclRatio(eclTrdAssetLoss.get(0).getEclVaule());
            eclRatioDto.setLgd(eclTrdAssetLoss.get(0).getLgdVaule());
            eclRatioDto.setPd(eclTrdAssetLoss.get(0).getPdVaule());
            // 若取到一条为三阶段，则返回减值阶段 = 三阶段
            if(DictCodeListExt3.阶段_买入后出现减值_P3.getValue().equals(eclTrdAssetLoss.get(0).getStage())){
                eclRatioDto.setFinStage(DictCodeListExt3.阶段_买入后出现减值_P3.getValue());
            }
            return eclRatioDto;
        }
    }


}
