/*
 * 文件名：ResetBaseRuleInfoReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dao.MProdDivReinDealGenMapper;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealDelDto;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealGenReaderResult;

/**
 * reader相关业务处理
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealDelReaderService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class MProdDivReinDealDelReaderService extends BaseService{

    @Autowired
    private MProdDivReinDealGenMapper mProdDivReinDealGenMapper;
    
    /*@Autowired(required = false)
    private PinganBatchPlatformDateImpl platformDateService;*/
    /**
     * 获取待处理数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<MProdDivReinDealGenReaderResult> execute(Map<String, Object> param) {
        // 获取待处理任务信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get(BatchConstant.BATCH_INFO);
        if(null==batchExecuteInfo){
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        paramMap.put("cashTypeC02", DictCodeListExt1.现金流二级类型_利息.getValue());
        paramMap.put("sysDate", DateUtil.format(batchExecuteInfo.getExecuteDate()));
        paramMap.put("ystDate", DateUtil.format(DateUtil.addDay(batchExecuteInfo.getExecuteDate(), -1)));
        paramMap.put("BONUS_RULE_07", DictCodeList.分红规则_日付.getValue());
        paramMap.put("BUSI_TYPE_21", DictCodeList.业务类型_货币型TA交易.getValue());
        paramMap.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        paramMap.put("PS_C10", DictCodeList.交易方向_产品红利转投.getValue());
        paramMap.put("CREATE_TYPE_03", DictCodeListExt1.生成方式_批处理生成.getValue());
        paramMap.put("BUSI_TABLE_02", DictCodeList.业务表_金融产品表.getValue()); 
        paramMap.put("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue()); 
        paramMap.put("BONUS_MODE_02", DictCodeList.分红方式_红利再投.getValue()); 
        paramMap.put("LOSS_HANDLE_MODE_01", DictCodeListExt1.亏损处理方式_缩减当前份额.getValue());
        paramMap.put("productIdList",param.get("productIdList"));
        List<MProdDivReinDealGenReaderResult> resultList = new ArrayList<MProdDivReinDealGenReaderResult>();
        List<MProdDivReinDealDelDto> delDtos = mProdDivReinDealGenMapper.selectTradeInfoForDel(paramMap);
        MProdDivReinDealGenReaderResult result = new  MProdDivReinDealGenReaderResult();
        result.setDelDtos(delDtos);
        resultList.add(result);
        return resultList;
    }
    
}
