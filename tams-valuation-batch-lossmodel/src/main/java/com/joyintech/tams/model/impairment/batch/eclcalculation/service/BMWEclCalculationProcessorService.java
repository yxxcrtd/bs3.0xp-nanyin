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
import com.joyintech.tams.common.base.entity.TrdAssetLoss;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationResultDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.FinDealPledgeDTO;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModConfigUseage;
import com.joyintech.tams.common.base.entity.ModEclResult;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModLgdConfigDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.AccountingCommonBatchService;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.valuation.common.service.CalCommonService;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        // 按资产分组，故取第一条作为资产信息使用
        ModEclResultProcess assetInfo = listProcess.get(0);
        // 定义 变量.三阶段减值管理数据 = null
        TrdAssetLoss trdAssetLossP3 = null;
        List<CommonErrorParams> errors = (List<CommonErrorParams>) stepExecution.getJobExecution().getExecutionContext().get(
                "ERRORS");
        if (errors == null) {
            errors = new ArrayList<CommonErrorParams>();
        }
        //模型计算LGD
        caclLGD(assetInfo, params, batchExecuteInfo, errors, stepExecution, modConfigUseageList);

        // 计算ECL
        // 查询成本科目性质
        List<String> subjectNatureList = accountingCommonBatchService
                .getValSubjectNatureListWithAcctStandardId(DictCodeListNatureGroup.分组代码_成本类对应科目查询.getValue(), null);
        // 循环 变量.减值结果过程List，毕马威方案只有一条数据
        for (ModEclResultProcess entityDto : listProcess) {
            // 定义 变量.减值结果过程表
            ModEclResultProcess modEclResultProcess = new ModEclResultProcess();
            // 定义 变量.资产减值结果表
            ModEclResult modEclResult = new ModEclResult();
            // 定义 变量.数据来源 = 模型
            String dataSource = DictCodeListExt3.减值数据来源_模型.getValue();
            try {
                // 计算ECL
                // 依据资产减值管理界面资产数据来源字段区分，手工、模型、wind、中债
                // 查询资产减值日期对应的资产减值管理数据
                Map<String, Object> searchPara = new HashMap<>();
                searchPara.put("assetId", finprodId);
                searchPara.put("platDate", platDate);
                searchPara.put("INPUT_TYPE_03", DictCodeListExt3.减值数据来源_中债接入.getValue());
                // 查询按照录入方式升序排序,优先取手工数据，其次取导入数据，故只需取第一条即可
                List<TrdAssetLoss> listTrdAssetLoss = eclCalculationMapper.queryEclManage(
                        searchPara);
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

                    // 调用共通根据最终减值流水计算ECL损失比例
                    BigDecimal eclRatio = accountingCommonBatchService.calcEclByTrdLoss(listTrdAssetLoss, targetFinProduct,
                            booksetId, bokDetailId, platDate,
                            platDate, subjectNatureList);
                    // 存入减值结果过程表ECL损失比例
                    entityDto.setEclRatio(ArithmeticUtil.round(eclRatio, NumberEnum.MONEY));
                }
                // 无手工录入、wind、中债导入，采用模型LGD计算
                else {
                    // 计算ECL值
                    //交易信息.前瞻前ECL预期信用损失 = 交易信息.前瞻前PD违约概率% * 交易信息.EAD * 交易信息.LGD 四舍五入保留2位小数
                    entityDto.setPreProspectiveEcl(
                            ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), RoundingMode.HALF_UP,
                                    entityDto.getPreProspectivePd(), entityDto.getEad(),
                                    entityDto.getLgd()));
                    //交易信息.前瞻后ECL预期信用损失 = 交易信息.前瞻后PD违约概率% * 交易信息.EAD * 交易信息.LGD  四舍五入保留2位小数
                    entityDto.setProspectiveEcl(
                            ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), RoundingMode.HALF_UP,
                                    entityDto.getProspectivePd(), entityDto.getEad(), entityDto.getLgd()));
                }
                //3.3 封装结果集
                quaryResultParam(entityDto, modEclResultProcess, modEclResult, platDate,
                        dataSource);

            } catch (Exception e) {
                CommonErrorParams commonErrorParams = new CommonErrorParams();
                commonErrorParams.setFinprodId(entityDto.getAssetId());
                commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
                commonErrorParams.setErrorMsg(
                        "【ECL计算批处理】：资产【" + assetInfo.getAssetId() + "】对应交易信息【" + entityDto.getTradeId()
                                + "】计算异常");
                commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                errors.add(commonErrorParams);
                stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
                e.getMessage();
            }
            // 变量.减值结果过程表List 更新
            modEclResultProcessList.add(modEclResultProcess);
            // 变量.资产减值结果表List 更新
            modEclResultList.add(modEclResult);
        }

        // 若变量.三阶段ECL损失比例 != null,重新计算所有ECL
        if (trdAssetLossP3 != null) {
            // 循环 变量.减值结果过程表List，重新赋值ECL
            for (ModEclResultProcess item : modEclResultProcessList) {
                // 前瞻前PD置空
                item.setPreProspectivePd(null);
                // 前瞻后PD =  变量.三阶段减值管理数据.PD
                item.setProspectivePd(trdAssetLossP3.getPdVaule());
                // 明细数据.LGD =  变量.三阶段减值管理数据.LGD
                item.setLgd(trdAssetLossP3.getLgdVaule());
                // ECL = EAD *   变量.三阶段减值管理数据.三阶段ECL损失比例
                item.setProspectiveEcl(
                        ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), RoundingMode.HALF_UP,
                                item.getEad(), trdAssetLossP3.getEclVaule()));
            }
            // 循环 变量.资产减值结果List，重新赋值ECL
            for (ModEclResult item : modEclResultList) {
                // 前瞻前PD置空
                item.setPreProspectivePd(null);
                // 前瞻后PD =  变量.三阶段减值管理数据.PD
                item.setProspectivePd(trdAssetLossP3.getPdVaule());
                // 明细数据.LGD =  变量.三阶段减值管理数据.LGD
                item.setLgd(trdAssetLossP3.getLgdVaule());
                // 数据来源 = 变量.三阶段减值管理数据.减值录入方式
                item.setDataSource(trdAssetLossP3.getLossInputType());
                // ECL = EAD *  变量.三阶段ECL损失比例
                item.setProspectiveEcl(
                        ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(), RoundingMode.HALF_UP,
                                item.getEad(), trdAssetLossP3.getEclVaule()));
            }
        }
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
        //减值阶段 交易信息DTO.减值阶段
        modEclResult.setStage(entity.getFinStage());
        //数据来源
        modEclResult.setDataSource(dataSource);
        //业务编号
        modEclResult.setBussNo(entity.getTradeId());
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
    public void caclLGD(ModEclResultProcess entityDto, Map<String, Object> params,
                        SysBatchExecInfo batchExecuteInfo, List<CommonErrorParams> errors,
                        StepExecution stepExecution, List<ModConfigUseage> modConfigUseageList) {
        // 债券类、存款类、回购类资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> otherLgdConfigMap = CacheUtil.getAppCache(
                GlobalConstant.OTHER_LGD_CONFIG, Map.class);
        // 其他债权类资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> deltRightLgdConfigMap = CacheUtil.getAppCache(
                GlobalConstant.DELT_RIGHT_LGD_CONFIG, Map.class);
        // 特殊资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> specialLgdConfigMap = CacheUtil.getAppCache(
                GlobalConstant.SPECIAL_LGD_CONFIG, Map.class);
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
                commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                errors.add(commonErrorParams);
                stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
                return;
            }
            // 1.1 当存在多笔质押券时，则采用加权计算最终LGD值
            if (finDealPledgeDTOList.size() > 1) {
                // 定义 变量.总质押金额
                BigDecimal totalPledgeAmt = BigDecimal.ZERO;
                // 循环遍历取质押券对应债券的LGD配置
                for (FinDealPledgeDTO item : finDealPledgeDTOList) {
                    totalPledgeAmt = ArithmeticUtil.doAdd(totalPledgeAmt, item.getPledgeAmt());
                }
                // 循环遍历质押券信息
                for (FinDealPledgeDTO item : finDealPledgeDTOList) {
                    configDto = null;
                    // 优先取质押券对应特殊资产LGD配置
                    if (specialLgdConfigMap.get(item.getPledgeFinprodId()) != null) {
                        configDto = specialLgdConfigMap.get(item.getPledgeFinprodId());
                    } else {
                        // 债券类、存款类、回购类资产LGD违约损失率配置 中 取质押券：资产类型+资产细分类的LGD值
                        configDto = otherLgdConfigMap.get(item.getSecType() + GlobalConstant.UNDER_LINE
                                + item.getSecDetailType());
                    }
                    if (configDto != null) {
                        lgdRate = ArithmeticUtil.doAdd(lgdRate, ArithmeticUtil.multiply(ArithmeticUtil.divide(item.getPledgeAmt(), totalPledgeAmt),
                                configDto.getLgd()));
                        configDtoList.add(configDto);
                    }
                }
                // 进行位数保留和尾数处理（取决于系统参数）
                lgdRate = calCommonService.round(lgdRate, LGD违约损失小数尾数处理方式, LGD违约损失小数位);
            }
            // 1.2 当没有多笔质押券时，直接取界面维护值LGD
            else {
                // 优先取特殊资产配置
                if (specialLgdConfigMap.get(entityDto.getAssetId()) != null) {
                    configDto = specialLgdConfigMap.get(entityDto.getAssetId());
                } else {
                    // 债券类、存款类、回购类资产LGD违约损失率配置中取值：资产类型 + 质押券对应债券类型
                    configDto = otherLgdConfigMap.get(entityDto.getModelAssetType() + GlobalConstant.UNDER_LINE + finDealPledgeDTOList.get(0).getSecDetailType());
                }
                if (configDto != null) {
                    // 变量.LGD最终值 = 取LGD界面维护值
                    lgdRate = configDto.getLgd();
                    configDtoList.add(configDto);
                }
            }
        }
        // 优先特殊资产LGD配置取值：key--资产代码
        else if (specialLgdConfigMap.get(entityDto.getAssetId()) != null) {
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
            // 进行位数保留和尾数处理（取决于系统参数）
            lgdRate = calCommonService.round(lgdRate, LGD违约损失小数尾数处理方式, LGD违约损失小数位);
        }
        // 模型计算LGD值
        if (!CollectionUtils.isEmpty(configDtoList)) {
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
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
        }
    }
}
