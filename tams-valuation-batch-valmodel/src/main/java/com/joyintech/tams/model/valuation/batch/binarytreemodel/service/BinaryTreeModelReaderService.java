package com.joyintech.tams.model.valuation.batch.binarytreemodel.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dao.BinaryTreeModelMapper;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogs;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.dto.modelDto.*;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import com.joyintech.tams.valuation.component.batch.service.ModValuationCommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BinaryTreeModelReaderService {
    /**
     * Mapper
     */
    @Autowired
    private BinaryTreeModelMapper binaryTreeModelMapper;


    @Autowired
    private ModLogsService modLogsService;

    @Autowired
    private ModParamsService modParamsService;
    @Autowired
    private ModValuationCommonService modValuationCommonService;

    private final String BATCH_PARAMS_KEY = "params";

    /**
     * 二叉树模型-reader
     * @param params
     * @return
     */
    public List<BinaryTreeModelReaderEntity> createReaderList(Map<String, Object> params, StepExecution stepExecution) {
        List<BinaryTreeModelReaderEntity> list = new ArrayList<BinaryTreeModelReaderEntity>();
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            stepExecution.getJobExecution().getExecutionContext().put(BATCH_PARAMS_KEY, params);
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }
        //1.2查询映射
        Map<String, Object> paraType = new HashMap<String, Object>();
        paraType.put("MODEL_MAPPING_TYPE_01", ModelDictCodeList.模型_映射类型_资产分类映射.getValue());
        paraType.put("MODEL_SEC_TYPE_F01", ModelDictCodeList.模型_证券品种_债券.getValue());
        paraType.put("MODEL_SEC_DETAIL_TYPE_011", ModelDictCodeList.模型_证券细分类_可交换债券.getValue());
        paraType.put("MODEL_SEC_DETAIL_TYPE_013", ModelDictCodeList.模型_证券细分类_可转债.getValue());
        List<String> output5List = binaryTreeModelMapper.findOutPut5(paraType);

        //2.调用方法【资产基本信息查询-估值模型】取配置了模型法的并且有持仓的可转可交换债
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        //资产基本信息查询-估值模型
        ModAssetInfoParamsDto modAssetInfoParamsDto = new ModAssetInfoParamsDto();

        modAssetInfoParamsDto.setFinprodIdList(finprodIdList);
        //资产五级分类集合
        modAssetInfoParamsDto.setAssetFiveList(output5List);
        modAssetInfoParamsDto.setSearchDate(platDate);
        List<ModAssetInfoDto> listAsset = modValuationCommonService.selectAssetInfo(modAssetInfoParamsDto);
        //封装日志信息
        if (CollectionUtils.isNotEmpty(listAsset)) {
            List<ModBatchLogs> listLog = new ArrayList<>(params.size());
            for (ModAssetInfoDto param : listAsset) {
                ModBatchLogs log = new ModBatchLogs();
                log.setSeqno(IDGenerator.generate());
                log.setFinprodId(param.getFinprodId());
                log.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                log.setTaskId(batchExecuteInfo.getTaskId());
                listLog.add(log);
            }
            modLogsService.saveBatch(listLog);
        }
        //1.1查询配置了资产代码
        List<ModBintreeConfig> listFin = binaryTreeModelMapper.findModBintreeConfig(finprodIdList);
        if (listFin == null || listFin.size() == 0) {
            if (listAsset != null && listAsset.size() > 0) {
                for (ModAssetInfoDto para : listAsset) {
                    modLogsService.error(para.getFinprodId(), batchExecuteInfo.getBatchExedetailSeqNo(), batchExecuteInfo.getTaskId(),
                            "【二叉树模型批处理】：未配置二叉树模型参数");
                }
            }
            return list;
        }

        //3. 设定资产其他相关信息
        for (ModAssetInfoDto modAssetInfoDto : listAsset) {
            BinaryTreeModelReaderEntity model = new BinaryTreeModelReaderEntity();
            BeanUtil.copyPropertiesIgnoreNull(model, modAssetInfoDto);

            //资产配置信息
            for(ModBintreeConfig config : listFin) {
                if (config.getFinprodId().equals(model.getFinprodId())) {
                    model.setModBintreeConfig(config);
                    break;
                }
            }

            //3.2 现金流信息查询
            List<ModCashFlowDto> modCashFlowDtoList = modValuationCommonService.getCashFlowPlanInfoByFinprodId(modAssetInfoDto.getFinprodId());
            model.setModCashFlowList(modCashFlowDtoList);

            //3.3 现金流计算规则查询
            List<ModCashFlowCalRuleDto> modCashFlowCalRuleDtoList = modValuationCommonService.getCashFlowCalRulesByFinprodId(modAssetInfoDto.getFinprodId());
            model.setModCashFlowCalRuleList(modCashFlowCalRuleDtoList);

            //3.4 现金流支付规则查询
            List<ModCashFlowPayRulesDto> modCashFlowPayRulesList = modValuationCommonService.getCashFlowPayRulesByFinprodId(modAssetInfoDto.getFinprodId());
            model.setModCashFlowPayRulesList(modCashFlowPayRulesList);

            //3.5 股票行情查询
            ModStockMarketParamsDto modStockMarketParamsDto = new ModStockMarketParamsDto();
            List<String> listStockId = new ArrayList<>();
            listStockId.add(modAssetInfoDto.getConFinprodId());
            modStockMarketParamsDto.setStockIdList(listStockId);
            //查询开始日	= 跑批日-（明细.到期日-跑批日）
            long lastQx = DateUtil.getBetweenDayNumber(platDate, modAssetInfoDto.getMdate());
            Date startDate = DateUtil.addDay(platDate, -(int)lastQx);
            modStockMarketParamsDto.setStartDate(startDate);
            //查询结束日 = 跑批日
            modStockMarketParamsDto.setSearchDate(platDate);
            List<ModStockMarketInfoDto> listStock = modValuationCommonService.getStockMarketIntervalInfo(modStockMarketParamsDto);
            model.setModStockMarketInfoList(listStock);

            //3.6 曲线行情查询
            ModYeildCurveParamsDto modYeildCurveParamsDto = new ModYeildCurveParamsDto();
            List<String> listYeildId = new ArrayList<>();
            listYeildId.add(model.getModBintreeConfig().getCurveCode().toString());
            modYeildCurveParamsDto.setYeildIdList(listYeildId);
            modYeildCurveParamsDto.setSearchDate(platDate);
            Map<String, List<ModYieldCurveInfoDto>> mapYield = modValuationCommonService.getCurveInfo(modYeildCurveParamsDto);
            if (mapYield != null) {
                model.setModYieldCurveInfoList(mapYield.get(model.getModBintreeConfig().getCurveCode().toString()));
            }

            list.add(model);
        }

        return list;
    }





}
