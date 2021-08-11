/*
 * 文件名：AssetIntdateCarryoverWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.valuation.batch.cashflowdiscount.writer;


import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.ModCurveResult;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dao.CashFlowDiscountMapper;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.CashFlowDiscountResult;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.ModCurveResultDetail;
import com.joyintech.tams.valuation.common.dto.modelDto.ModRiskPremium;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 现金流贴现批处理(写入)
 * @author zznn
 * @date 20210706
 */
@Service
@Scope("prototype")
public class CashFlowDiscountWriter extends BaseWriter<NeedToWriteEntity>{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CashFlowDiscountMapper cashFlowDiscountMapper;
    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
        List<CashFlowDiscountResult> resultList = (List<CashFlowDiscountResult>)items;
        List<ModRiskPremium> modRiskPremiums = new ArrayList<>();
        List<ModCurveResult> modCurveResults = new ArrayList<>();
        List<ModCurveResultDetail> modCurveResultDetails = new ArrayList<>();
        try{
            if(!StringUtil.isEmptyList(resultList)){
                resultList.forEach(item ->{
                    Map<String,Object> map = new HashMap<>();
                    ModCurveResult modCurveResult = item.getModCurveResult();
                    ModRiskPremium modRiskPremium = item.getModRiskPremium();
                    if(null!=modCurveResult){
                        map.put("finprodId", modCurveResult.getFinprodId());
                        map.put("cdate", modCurveResult.getCdate());
                        modCurveResults.add(item.getModCurveResult());
                        cashFlowDiscountMapper.deleteCurveResult(map);
                    }
                    if(null!=modRiskPremium){
                        map.put("basedate", modRiskPremium.getBasedate());
                        modRiskPremiums.add(modRiskPremium);
                        cashFlowDiscountMapper.deleteRiskPremium(map);
                    }
                    if(!StringUtil.isEmptyList(item.getModCurveResultDetails())){
                        modCurveResultDetails.addAll(item.getModCurveResultDetails());
                        cashFlowDiscountMapper.deleteCurveResultDetail(map);
                    }
                });
                if(!StringUtil.isEmptyList(modRiskPremiums)){
                    cashFlowDiscountMapper.insertBatchRiskPremium(modRiskPremiums);
                }
                if(!StringUtil.isEmptyList(modCurveResults)){
                    cashFlowDiscountMapper.insertBatchCurveResult(modCurveResults);
                }
                if(!StringUtil.isEmptyList(modCurveResultDetails)){
                    cashFlowDiscountMapper.insertBatchCurveResultDetail(modCurveResultDetails);
                }
            }
        }catch (Exception e){
            logger.error("现金流贴现批处理writer相关业务处理异常:"+e.getMessage());
        }
    }

}
