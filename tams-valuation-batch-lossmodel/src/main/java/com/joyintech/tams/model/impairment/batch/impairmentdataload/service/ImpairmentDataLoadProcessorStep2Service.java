package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.common.base.entity.ModConfigUseage;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.*;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.valuation.model.common.rateinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.valuation.model.common.rateinfo.service.ImpairmentCalculationCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImpairmentDataLoadProcessorStep2Service {
    private static final Logger logger = LoggerFactory.getLogger(ImpairmentDataLoadProcessorService.class);
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
    public ImpairmentDataLoadStep1ResultDto excute(ImpairmentDataLoadStep2ReaderDto entity, Map<String, Object> params) {
        ImpairmentDataLoadStep1ResultDto result = new ImpairmentDataLoadStep1ResultDto();
        result.setModEclResultProcess(entity.getModEclResultProcess());
        // 查询评级配置使用情况
        ModConfigUseage modConfigUseage = entity.getModConfigUseage();
        //2.1 判断使用的参数是否已存在减值配置使用情况表
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("configSeqno", modConfigUseage.getConfigSeqno());
        para.put("configType", modConfigUseage.getConfigType());
        int resultCount= impairmentDataLoadMapper.selectModConfigUseageCount(para);
        if (resultCount == 0) {
            modConfigUseage.setSeqno(IDGenerator.generate());
            modConfigUseage.setUseProcess(ModelDictCodeList.模型_减值过程_数据加载.getValue());
            result.setModConfigUseage(modConfigUseage);
        }

        return result;
    }

}
