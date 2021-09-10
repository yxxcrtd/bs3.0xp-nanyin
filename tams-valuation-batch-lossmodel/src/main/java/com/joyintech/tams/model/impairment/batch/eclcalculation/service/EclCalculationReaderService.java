package com.joyintech.tams.model.impairment.batch.eclcalculation.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModLgdConfigDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service public class EclCalculationReaderService {

    @Autowired private EclCalculationMapper eclCalculationMapper;

    @Autowired private ModParamsService modParamsService;

    /**
     * 二叉树模型-reader
     *
     * @param params
     * @return
     */
    public List<EclCalculationReaderDto> createReaderList(Map<String, Object> params) {
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        //1.读取需要进行处理的资产
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("searchDate", platDate);
        para.put("process", ModelDictCodeList.模型_减值过程_PD计算.getValue());
        para.put("batchExedetailSeqNo", batchExecuteInfo.getBatchExedetailSeqNo());
        List<EclCalculationReaderDto> rstList = new ArrayList<>();
        //1.获取理需要处理的每一笔交易数据（计划做在数据加载批处理中）
        // 减值方案系统参数
        String lossType = CacheUtil.getSysParam(DictCode.减值方案类型.getValue());
        para.put("lossType", lossType);
        List<ModEclResultProcess> list = eclCalculationMapper.selectModEclResultProcessForDeal(
            para);
        if(CollectionUtils.isEmpty(list)){
            return rstList;
        }

        // 债券类、存款类、回购类资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> otherLgdConfigMap = new HashMap<>();
        // 其他债权类资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> deltRightLgdConfigMap = new HashMap<>();
        // 特殊资产LGD违约损失率配置
        Map<String, ModLgdConfigDto> specialLgdConfigMap = new HashMap<>();
        // 查询所有LGD配置
        Map<String, Object> searchPara = new HashMap<>();
        searchPara.put("MODEL_DEC_CONFIG_TYPE_06", ModelDictCodeList.模型_减值配置类型_特殊资产LGD配置.getValue());
        searchPara.put("MODEL_DEC_CONFIG_TYPE_05", ModelDictCodeList.模型_减值配置类型_资产大类LGD配置.getValue());
        List<ModLgdConfigDto> lgdConfigList = eclCalculationMapper.selectModLgdConfigAll(searchPara);
        if(!CollectionUtils.isEmpty(lgdConfigList)){
            for(ModLgdConfigDto item : lgdConfigList){
                // 若金融产品代码 != null
                if(item.getFinprodId() != null){
                    // key = 金融产品代码
                    specialLgdConfigMap.put(item.getFinprodId(), item);
                }
                else if(DictCodeListExt1.证券品种_其他债权类.getValue().equals(item.getAssetType())){
                    // key = 资产类型 + 是否含有抵质押物
                    deltRightLgdConfigMap.put( item.getAssetType() + GlobalConstant.UNDER_LINE  + item.getIsContainPledge(), item);
                }
                else {
                    // key = 资产类型 + 资产细分类
                    otherLgdConfigMap.put(item.getAssetType() + GlobalConstant.UNDER_LINE  + item.getAssetDetailType(), item);
                }
            }
        }

        // 定义 变量.减值结果过程Map集合
        Map<String, List<ModEclResultProcess>> modEclResultProcessMap = new HashMap<>();
        for (ModEclResultProcess dto : list) {
            // 定义 变量.key =  账套代码 + 资产代码 + 投资目的
            String key = dto.getProdId() + "," + dto.getAssetId()+","+dto.getInvAim();
            // map集合无key对应value值
            if (modEclResultProcessMap.get(key) == null) {
                // 放入map集合中
                modEclResultProcessMap.put(key, Arrays.asList(dto));
            }
            else {
                // 获取对应value值
                List<ModEclResultProcess> listProcess = modEclResultProcessMap.get(key);
                // 增加dto
                listProcess.add(dto);
                // 更新Map
                modEclResultProcessMap.put(key, listProcess);
            }
        }
        for (String key : modEclResultProcessMap.keySet()) {
            EclCalculationReaderDto rstDto = new EclCalculationReaderDto();
            String[] keyArr = key.split(",");
            rstDto.setProdId(keyArr[0]);
            rstDto.setAssetId(keyArr[1]);
            // 当投资目的为空时，keyArr长度为2
            if(keyArr.length>2){
                rstDto.setInvAim(keyArr[2]);
            }
            rstDto.setModEclResultProcessList(modEclResultProcessMap.get(key));
            rstDto.setOtherLgdConfigMap(otherLgdConfigMap);
            rstDto.setDeltRightLgdConfigMap(deltRightLgdConfigMap);
            rstDto.setSpecialLgdConfigMap(specialLgdConfigMap);
            rstList.add(rstDto);
        }

        return rstList;
    }

}
