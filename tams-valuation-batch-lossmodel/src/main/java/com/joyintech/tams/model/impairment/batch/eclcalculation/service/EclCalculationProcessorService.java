package com.joyintech.tams.model.impairment.batch.eclcalculation.service;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokFinProductValuationParaMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.BokFinProductValuationPara;
import com.joyintech.tams.common.base.entity.BokFinProductValuationParaKey;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.TrdAssetLoss;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.*;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class EclCalculationProcessorService {

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

    /**
     * 减值模型数据加载-processor
     *
     * @param params
     * @return
     */
    public EclCalculationResultDto excute(EclCalculationReaderDto entity, Map<String, Object> params, StepExecution stepExecution) {
        // 初始化相关变量
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
        // 定义 标的信息
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(finprodId);
        // 定义 变量.减值配置使用情况表List
        List<ModConfigUseage> modConfigUseageList = new ArrayList<>();
        // 定义 变量.减值结果过程表List
        List<ModEclResultProcess> modEclResultProcessList = new ArrayList<>();
        // 定义 变量.资产减值结果表List
        List<ModEclResult> modEclResultList = new ArrayList<>();
        // 默认 变量.代偿期类型 = 到期
        String peroidType = DictCodeListExt3.代偿期类型_到期.getValue();
        // 定义 变量.含权摊销日 = 标的信息.含权摊销日;
        String exeDate = finProduct.getExeDate();
        // 定义 变量.减值结果过程List  = map.get(key)
        List<ModEclResultProcess> listProcess = entity.getModEclResultProcessList();
        if(CollectionUtils.isEmpty(listProcess)){
            return result;
        }
        // 若标的信息.是否含权 = 是，且金融产品类型 = 债券
        if(DictCodeList.是否_是.getValue().equals(finProduct.getIsRight())
            && DictCodeListExt1.金融产品类型_债券.getValue().equals(finProduct.getFinprodType())
        ){
            // 查询【含权债摊销参数设置表(BOK_FIN_PRODUCT_VALUATION_PARA)】
            /* 判断债券含权摊销日，优先级为账套加资产维度>资产维度>账套维度(暂时不存在 ) */
            BokFinProductValuationParaKey bokFinProductValuationParaKey=new BokFinProductValuationParaKey();
            bokFinProductValuationParaKey.setBoksetId(booksetId);
            bokFinProductValuationParaKey.setFinprodId(finprodId);
            bokFinProductValuationParaKey.setBranch(InterfCommonConstant.分支序号_默认);
            BokFinProductValuationPara bokFinProductValuationPara=bokFinProductValuationParaKeyMapper.selectByPrimaryKey(bokFinProductValuationParaKey);
            // 查询结果不为空，重新赋值含权债摊销日
            if(bokFinProductValuationPara !=null){
                exeDate = bokFinProductValuationPara.getExeDateType();
            }
        }
        // IF 含权摊销日 != 到期日
        if(!DictCodeList.含权债摊销日_到期日.getValue().equals(exeDate)){
            // 变量.代偿期类型 = [数据字典.代偿期类型 PERIOD_TYPE: '01 行权']
            peroidType = DictCodeListExt3.代偿期类型_行权.getValue();
        }
        // 定义 变量.三阶段减值管理数据 = null
        TrdAssetLoss trdAssetLossP3 = null;
        // 循环 变量.减值结果过程List
        for(ModEclResultProcess entityDto : listProcess){
            // 定义 变量.减值配置使用情况表
            ModConfigUseage modConfigUseage = new ModConfigUseage();
            // 定义 变量.减值结果过程表
            ModEclResultProcess modEclResultProcess = new ModEclResultProcess();
            // 定义 变量.资产减值结果表
            ModEclResult modEclResult = new ModEclResult();
            // 定义 变量.数据来源 = 模型
            String dataSource  = DictCodeListExt3.减值数据来源_模型.getValue();
            //跑批日
            Date platDate = (Date)params.get("PLATE_DATE");
            SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
            List<CommonErrorParams> errors = (List<CommonErrorParams>) stepExecution.getJobExecution().getExecutionContext().get("ERRORS");
            if (errors == null) {
                errors = new ArrayList<CommonErrorParams>();
            }
            try {
                //模型计算LGD
                caclLGD(entityDto,params, batchExecuteInfo, errors, stepExecution, modConfigUseage);

                // 计算ECL
                // 依据资产减值管理界面资产数据来源字段区分，手工、模型、wind、中债
                // 查询资产减值日期对应的资产减值管理数据
                Map<String,Object> searchPara = new HashMap<>();
                searchPara.put("assetId", finprodId);
                searchPara.put("platDate", platDate);
                searchPara.put("periodType", peroidType);
                searchPara.put("inputType", DictCodeListExt3.减值数据来源_中债接入.getValue());
                // 查询按照录入方式升序排序,优先取手工数据，其次取导入数据，故只需取第一条即可
                List<TrdAssetLoss> listTrdAssetLoss  = eclCalculationMapper.queryEclManage(searchPara);
                // 返回结果不为空
                if(!CollectionUtils.isEmpty(listTrdAssetLoss)){
                    // 定义 变量.减值管理Dto= list.get(0)
                    TrdAssetLoss trdAssetLoss = listTrdAssetLoss.get(0);
                    // 变量.数据来源 = 变量.减值管理Dto.减值录入方式
                    dataSource = trdAssetLoss.getLossInputType();
                    // 手工录入：当存在交易处于三阶段时，则所有交易按三阶段比例计算ECL
                    if(DictCodeListExt3.阶段_买入后出现减值_P3.getValue().equals(trdAssetLoss.getStage())){
                        // 变量.三阶段减值管理数据= 变量.减值管理Dto
                        trdAssetLossP3 = trdAssetLoss;
                        break;
                    }
                    else{
                        // 前瞻前PD置空
                        entityDto.setPreProspectivePd(null);
                        // 前瞻后PD = 变量.减值管理Dto.PD
                        entityDto.setProspectivePd(trdAssetLoss.getPdVaule());
                        // 明细数据.LGD = 变量.减值管理Dto.LGD
                        entityDto.setLgd(trdAssetLossP3.getLgdVaule());
                        // 交易信息DTO.前瞻后ECL预期信用损失 = 变量.资产减值流水List.get(0).ECL预期信用损失比例 * 交易信息.EAD 四舍五入保留2位小数
                        entityDto.setProspectiveEcl(ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(),
                            RoundingMode.HALF_UP, trdAssetLoss.getEclVaule(),entityDto.getEad()));
                    }
                }
                // 无手工录入、wind、中债导入，采用模型LGD计算
                else{
                    // 计算ECL值
                    //交易信息.前瞻前ECL预期信用损失 = 交易信息.前瞻前PD违约概率% * 交易信息.EAD * 交易信息.LGD 四舍五入保留2位小数
                    entityDto.setPreProspectiveEcl(ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(),
                        RoundingMode.HALF_UP, entityDto.getPreProspectivePd(), entityDto.getEad(), entityDto.getLgd()));
                    //交易信息.前瞻后ECL预期信用损失 = 交易信息.前瞻后PD违约概率% * 交易信息.EAD * 交易信息.LGD  四舍五入保留2位小数
                    entityDto.setProspectiveEcl(ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(),
                        RoundingMode.HALF_UP, entityDto.getProspectivePd(), entityDto.getEad(), entityDto.getLgd()));
                }

                //3.3 设置每一笔交易的资产类型
                //            para.put("type9", entityDto.getType9());
                //            para.put("type10", entityDto.getType10());
                //            para.put("type11", entityDto.getType11());
                //            String input2 = eclCalculationMapper.selectInput2(para);
                //封装结果集
                //            quaryResultParam(entityDto, modEclResultProcess, modEclResult, input2, platDate);
                quaryResultParam(entityDto, modEclResultProcess, modEclResult, platDate, dataSource);

            } catch (Exception e) {
                CommonErrorParams commonErrorParams = new CommonErrorParams();
                commonErrorParams.setFinprodId(entityDto.getAssetId());
                commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
                commonErrorParams.setErrorMsg("【ECL计算批处理】：计算错误");
                commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                errors.add(commonErrorParams);
                stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
                e.getMessage();
            }
            // 变量.减值配置使用情况表List 更新
            modConfigUseageList.add(modConfigUseage);
            // 变量.减值结果过程表List 更新
            modEclResultProcessList.add(modEclResultProcess);
            // 变量.资产减值结果表List 更新
            modEclResultList.add(modEclResult);
        }

        // 若变量.三阶段ECL损失比例 != null,重新计算所有ECL
        if(trdAssetLossP3 != null){
            // 循环 变量.减值结果过程表List，重新赋值ECL
            for(ModEclResultProcess item : modEclResultProcessList){
                // 前瞻前PD置空
                item.setPreProspectivePd(null);
                // 前瞻后PD =  变量.三阶段减值管理数据.PD
                item.setProspectivePd(trdAssetLossP3.getPdVaule());
                // 明细数据.LGD =  变量.三阶段减值管理数据.LGD
                item.setLgd(trdAssetLossP3.getLgdVaule());
                // ECL = EAD *   变量.三阶段减值管理数据.三阶段ECL损失比例
                item.setProspectiveEcl(ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(),
                    RoundingMode.HALF_UP, item.getEad(), trdAssetLossP3.getEclVaule()));
            }
            // 循环 变量.资产减值结果List，重新赋值ECL
            for(ModEclResult item : modEclResultList){
                // 前瞻前PD置空
                item.setPreProspectivePd(null);
                // 前瞻后PD =  变量.三阶段减值管理数据.PD
                item.setProspectivePd(trdAssetLossP3.getPdVaule());
                // 明细数据.LGD =  变量.三阶段减值管理数据.LGD
                item.setLgd(trdAssetLossP3.getLgdVaule());
                // 数据来源 = 变量.三阶段减值管理数据.减值录入方式
                item.setDataSource(trdAssetLossP3.getLossInputType());
                // ECL = EAD *  变量.三阶段ECL损失比例
                item.setProspectiveEcl(ArithmeticUtil.multiply(NumberEnum.MONEY.getCode(),
                    RoundingMode.HALF_UP, item.getEad(),  trdAssetLossP3.getEclVaule()));
            }
        }
        result.setModConfigUseageList(modConfigUseageList);
        result.setModEclResultProcessList(modEclResultProcessList);
        result.setModEclResultList(modEclResultList);
        return result;
    }

    /**
     * 封装结果集
     * @param entity
     * @param modEclResultProcess
     * @param modEclResult
     * @param input2
     * @param platDate
     */
    private void quaryResultParam(ModEclResultProcess entity, ModEclResultProcess modEclResultProcess,
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
     *  模型计算LGD值
     * @param entityDto
     * @param params
     * @param batchExecuteInfo
     * @param errors
     * @param stepExecution
     * @param modConfigUseage
     */
    public void caclLGD(ModEclResultProcess entityDto, Map<String, Object> params,
                        SysBatchExecInfo batchExecuteInfo, List<CommonErrorParams> errors,
                        StepExecution stepExecution, ModConfigUseage modConfigUseage) {

        //1、查询每一笔交易数据对应的LGD值
        Map<String, Object> para = new HashMap<>();
        para.put("MODEL_DEC_CONFIG_TYPE_06", ModelDictCodeList.模型_减值配置类型_特殊资产LGD配置.getValue());
        para.put("MODEL_DEC_CONFIG_TYPE_05", ModelDictCodeList.模型_减值配置类型_资产大类LGD配置.getValue());
        //            para.put("type8", entityDto.getType8());
        //            para.put("type12", entityDto.getType12());
        //项目类资产判断不出是否含有抵质押物
        if (DictCodeListExt1.证券品种_其他债权类.getValue().equals(entityDto.getModelAssetType())
            && StringUtil.isEmpty(entityDto.getIsContainPledge())) {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entityDto.getAssetId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【ECL计算批处理】：无法判断是否含有合格抵质押物");
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
        }
        para.put("assetDetailType",entityDto.getModelAssetDetailType());
        para.put("isContainPledge", entityDto.getIsContainPledge());
        para.put("MODEL_MAPPING_TYPE_01", ModelDictCodeList.模型_映射类型_资产分类映射.getValue());
        para.put("finprodId", entityDto.getAssetId());
        List<ModLgdConfig> configList = eclCalculationMapper.selectModLgdConfig(para);

        // 模型计算LGD值
        if (configList != null && configList.size() > 0) {
            //交易信息.LGD = LGD参数Dto.LGD
            entityDto.setLgd(configList.get(0).getLgd());
            //定义 配置流水号 = LGD参数Dto.流水号
            String seqno = configList.get(0).getSeqno();
            //定义 配置类型 = LGD参数Dto.配置类型
            String configType = configList.get(0).getAssetType();

            //4.1 判断使用的参数是否已存在减值配置使用情况表
            para.put("configSeqno", seqno);
            para.put("configType", configType);
            int resultCount= impairmentDataLoadMapper.selectModConfigUseageCount(para);
            if (resultCount == 0) {
                modConfigUseage.setSeqno(IDGenerator.generate());
                modConfigUseage.setUseProcess(ModelDictCodeList.模型_减值过程_ECL计算.getValue());
                modConfigUseage.setConfigSeqno(seqno);
                modConfigUseage.setConfigType(configType);
            }

        } else {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entityDto.getAssetId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【ECL计算批处理】：资产未配置LGD参数");
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
        }

    }
}
