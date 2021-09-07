package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ModAssetGradeConfig;
import com.joyintech.tams.common.base.entity.ModAssetGradeSpecialConfig;
import com.joyintech.tams.common.base.entity.ModConfigUseage;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentCommonService;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep2ReaderDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.modelDto.ModAssetInfoDto;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import com.joyintech.tams.valuation.model.common.rateconfig.service.RateConfigCommonService;
import com.joyintech.tams.valuation.model.common.rateinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.valuation.model.common.rateinfo.service.RateInfoCommonService;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImpairmentDataLoadReaderStep2Service {

    @Autowired
    private ImpairmentCommonService impairmentCommonService;
    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;
    @Autowired
    private ModParamsService modParamsService;
    @Autowired
    private RateConfigCommonService rateConfigCommonService;
    @Autowired
    private RateInfoCommonService rateInfoCommonService;

    /**
     * 二叉树模型-reader
     * @param params
     * @return
     */
    public List<ImpairmentDataLoadStep2ReaderDto> createReaderList(Map<String, Object> params, StepExecution stepExecution) {
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        //1.读取需要进行处理的资产
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("searchDate", platDate);
        para.put("process", ModelDictCodeList.模型_减值过程_数据加载.getValue());
        para.put("ps", DictCodeList.交易方向_买入.getValue());
        para.put("batchExedetailSeqNo", batchExecuteInfo.getBatchExedetailSeqNo());
        // 定义 变量.返回结果
        List<ImpairmentDataLoadStep2ReaderDto> resultList = new ArrayList<ImpairmentDataLoadStep2ReaderDto>();
        //1、获取需要当天处理的初始数据列表
        List<ModEclResultProcess> list = impairmentDataLoadMapper.selectModEclResultProcessForDeal(para);
        if (list == null || list.size() == 0) {
            return resultList;
        }
        //2、查询外部机构评级结果，映射评级机构对应的内部评级
        // 2.1 查询所有的评级配置数据
        // 定义 变量.特殊资产评级配置
        Map<String, ModAssetGradeSpecialConfig> sepcialConfigMap = rateConfigCommonService.listAssetSpecialGradeConfigs();
        // 定义 变量.资产大类评级配置
        Map<String, ModAssetGradeConfig> configMap = rateConfigCommonService.listAssetGradeConfigs();
        //2.1.加载查询参数
        for (ModEclResultProcess item : list) {
            // 定义 变量.返回reader结果
            ImpairmentDataLoadStep2ReaderDto readerDto = new ImpairmentDataLoadStep2ReaderDto();
            ModConfigUseage modConfigUseage = new ModConfigUseage();
            ModAssetInfoDto modAssetInfoDto = new ModAssetInfoDto();
            BeanUtil.copyPropertiesIgnoreNull(modAssetInfoDto, item);
            modAssetInfoDto.setSecType(item.getModelAssetType());
            modAssetInfoDto.setFinprodId(item.getAssetId());
            modAssetInfoDto.setSecTypeDetail(item.getModelAssetDetailType());
            // 定义 变量.评级信息
            ViewRatingInfoDto ratingInfoDto = null;
            // 特殊资产评级配置不为空时
            if(sepcialConfigMap.get(item.getAssetId()) != null){
                // 特殊资产评级配置
                ModAssetGradeSpecialConfig specialConfig = sepcialConfigMap.get(item.getAssetId());
                //定义 变量.评级配置使用情况.配置流水号 = LGD参数Dto.流水号
                modConfigUseage.setConfigSeqno(specialConfig.getSeqno());
                //定义 变量.评级配置使用情况.配置类型 = LGD参数Dto.配置类型
                modConfigUseage.setConfigType(specialConfig.getoType());
                // 交易信息.评级配置使用情况 = 变量.评级配置使用情况
                readerDto.setModConfigUseage(modConfigUseage);

                // 查询对应评级信息
                ratingInfoDto  = rateInfoCommonService.getRatingInfoBySpecialConfig(modAssetInfoDto,specialConfig,item.getTradeDate());
            }else if(configMap.get(item.getModelAssetType()+item.getModelAssetDetailType()) != null
                    || configMap.get(item.getModelAssetType()) != null){
                ModAssetGradeConfig config = new ModAssetGradeConfig();
                // 资产类型+资产细分类
                if(configMap.get(item.getModelAssetType()+item.getModelAssetDetailType()) != null){
                    config = configMap.get(item.getModelAssetType()+item.getModelAssetDetailType());
                }
                // 否则，直接根据资产类型取值
                else{
                    config = configMap.get(item.getModelAssetType());
                }
                //定义 配置流水号 = LGD参数Dto.流水号
                modConfigUseage.setConfigSeqno(config.getSeqno());
                //定义 配置类型 = LGD参数Dto.配置类型
                modConfigUseage.setConfigType(config.getoType());
                // 交易信息.评级配置使用情况 = 变量.评级配置使用情况
                readerDto.setModConfigUseage(modConfigUseage);

                // 查询对应评级信息,查询时间为对应买入交易日期
                ratingInfoDto = rateInfoCommonService.getRatingInfoByTypeConfig(modAssetInfoDto,config,item.getTradeDate());
            }
            else{
                // 警告：资产无对应资产评级配置
                String msg = "【资产减值过程加载批处理】：资产【"+item.getAssetId()+"】未能匹配到相应的评级配置信息";
                impairmentCommonService.sendLogContextInfo(stepExecution, batchExecuteInfo,
                        item.getAssetId(), msg, ModelDictCodeList.批处理执行结果_异常结束.getValue(), "ERRORS");
                continue;
            }
            if(ratingInfoDto != null){
                //交易数据.买入时点评级结果 = 评级信息.评级结果
                item.setResultDeal(ratingInfoDto.getRating());
                // 添加到返回结果List中
                readerDto.setModEclResultProcess(item);
            }
            else{
                // 警告：资产无对应评级信息
                String msg = "【资产减值过程加载批处理】：资产【"+item.getAssetId()+"】未能匹配到相应的评级结果";
                impairmentCommonService.sendLogContextInfo(stepExecution, batchExecuteInfo,
                        item.getAssetId(), msg, ModelDictCodeList.批处理执行结果_异常结束.getValue(), "ERRORS");
                continue;
            }

        }
        return resultList;
    }

}
