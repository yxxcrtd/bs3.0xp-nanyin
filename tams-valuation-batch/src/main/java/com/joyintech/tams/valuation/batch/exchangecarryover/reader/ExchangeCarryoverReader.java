package com.joyintech.tams.valuation.batch.exchangecarryover.reader;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.batch.common.calculation.service.CalculationCommonService;
import com.joyintech.tams.valuation.batch.exchangecarryover.dao.ExchangeCarryoverMapper;
import com.joyintech.tams.valuation.batch.exchangecarryover.dto.ExchangeCarryoverEntity;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;

/**
 * 汇兑损益结转
 *
 * @author zhangwei
 * @createTime 2019-07-08 11:05
 */
@Service
@Scope("prototype")
public class ExchangeCarryoverReader extends BaseReader<ExchangeCarryoverEntity> {

    @Autowired
    private ExchangeCarryoverMapper exchangeCarryoverMapper;

    /**
     * 计算类共通
     */
    @Autowired
    private CalculationCommonService calculationCommonService;

    @Override
    public List<ExchangeCarryoverEntity> createList() {
        // 获取 task 传递的参数
        Map<String, Object> params = this.getParams();
        if (MapUtils.isEmpty(params)) {
            return null;
        }

        List<ExchangeCarryoverEntity> listResult = Lists.newArrayList();

        params.put("carryoverCycle", ValDictCodeList.账套配置表参数项_汇兑损益结转频率.getValue());
        List<ExchangeCarryoverEntity> list = exchangeCarryoverMapper.selectBooksetId(params);

        //  2.查询结果处理
        //  LOOP 查询结果 O
        for (ExchangeCarryoverEntity item : list) {
            //  IF(O.损益结转频率='D' OR O.发生日 != O.账套结束日 OR O.发生日 = O.到期日)
            if (DictCodeListExt1.损益结转频率_日.getValue().equals(item.getCarryoverCycle())
                || !DateUtil.isSameDay(item.getHappenDate(), item.getEndDate())
                || DateUtil.isSameDay(item.getHappenDate(), item.getMdate())) {
                //  返回结果 += O
                listResult.add(item);
            } else {
                //  调用共通设计->【详细设计-计算类共通方法设计.xlsx】->判断日期是否结转SHEET
                boolean flag = calculationCommonService.checkDateNeedCarryover(item.getHappenDate(), item.getCarryoverCycle());
                //  IF 返回值 = TRUE
                if (flag){
                    //  返回结果 += O
                    listResult.add(item);
                }
            }

        }

        return listResult;
    }
}
