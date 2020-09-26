/*
 * 文件名：损益结转（新）处理数据读取器.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：魏鹏
 * 修改时间：2019年1月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitandlossaccounting.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.profitandlossaccounting.dao.ProfitAndLossAccountingMapper;
import com.joyintech.tams.valuation.batch.profitandlossaccounting.dto.ProfitAndLossAccountingNeedOperateEntity;

/**
 * 权益科目分层处理Reader
 * @author weipeng
 * @version 1.0 2019年1月14日
 * @see ProfitAndLossAccountingLayeringReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProfitAndLossAccountingLayeringReader extends BaseReader<ProfitAndLossAccountingNeedOperateEntity> {

    @Autowired
    private ProfitAndLossAccountingMapper profitAndLossAccountingMapper;

    @Autowired
    private CalculationCommonService calculationCommonService;
    
    /**
     * 损益结转频率:UP_CARRYOVER_CYCLE
     */
    private static final String  CARRYOVER_CYCLE = "CARRYOVER_CYCLE";
    
    @Override
    public List<ProfitAndLossAccountingNeedOperateEntity> createList() {

        this.getParams().put("carryoverCycle", CARRYOVER_CYCLE);
        Map<String,Object> param = new HashMap<>();
        param.putAll(this.getParams());
        List<String> bokSpecModeList = new ArrayList<>();
//        bokSpecModeList.add(DictCodeList.特殊核算模型_普通分层核算.getValue());
        bokSpecModeList.add(DictCodeList.特殊核算模型_收益分层核算.getValue());
        param.put("bokSpecModeList", bokSpecModeList);
        List<ProfitAndLossAccountingNeedOperateEntity> list = profitAndLossAccountingMapper.selectTodoBooksetIdList(param);
        // 共通参数去除特殊核算模型_收益分层核算,否则后面传值会接收到
        // this.getParams().put("bokSpecMode", "");
        
        List<ProfitAndLossAccountingNeedOperateEntity> result = new ArrayList<>();

        list.forEach(x -> {
            x.setTaskPri(this.getBatchTask().getTaskPri());
            /**ADD BY WUJIE ON 20190730 START**/
            List<String> pslist = new ArrayList<String>();
            pslist.add(DictCodeList.交易方向_产品除权登记.getValue());
            /**ADD BY WUJIE ON 20190730 END**/
            if (DictCodeListExt1.损益结转频率_日.getValue().equals(x.getCarryoverCycle())
                || DateUtil.isSameDay(x.getHappenDate(), DateUtil.parse(x.getBksEndDate()))
                || DateUtil.isSameDay(x.getHappenDate(), DateUtil.parse(x.getPrdMdate()))) {
                result.add(x);
            }
            else if (calculationCommonService.checkDateNeedCarryover(x.getHappenDate(),
                x.getCarryoverCycle())) {
                result.add(x);
            }
            /**ADD BY WUJIE ON 20190730 START**/
            else if (calculationCommonService.isTradeDealAccount(x.getBooksetId(),x.getHappenDate(),pslist)) {
                    result.add(x);
            }
            /**ADD BY WUJIE ON 20190730 END**/
        });

        return result;
    }

}
