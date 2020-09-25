package com.joyintech.tams.invest.common.risktrial.reflect.impl;

import com.csii.dc.client.util.StringUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrial.dto.SystemParam;
import com.joyintech.tams.invest.common.risktrial.reflect.PluginClass;
import com.joyintech.tams.invest.common.risktrial.reflect.PluginClassDao;

import java.math.BigDecimal;

/**
 * @author 裴家俊
 * @date 2017年12月15日
 * @remark 久期前
 */

public class DeviationRateBond implements PluginClass {

    private PluginClassDao pluginClassDao;

    private SystemParam sysRefParam;

    private InvestTradeTrialInput investTradeTrialInput;


    @Override
    public void setSysParameter(SystemParam sysPass, PluginClassDao assetDao, InvestTradeTrialInput investTrialInput) {
        sysRefParam = sysPass;
        pluginClassDao = assetDao;
        investTradeTrialInput = investTrialInput;
    }

    @Override
    public Object getParamterValue() throws Exception {
        if (StringUtil.isEmpty(investTradeTrialInput.getTrdProductDeal()) || StringUtil.isEmpty(investTradeTrialInput.getTrdProductDeal().getUnitCprice())) {
            return null;
        }
        BigDecimal 上一估值日净价 = pluginClassDao.getRecentNetPrice(sysRefParam.getAssetCode());//取前一日净价
        if (上一估值日净价 == null || BigDecimal.ZERO.compareTo(上一估值日净价) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal 所输净价 = investTradeTrialInput.getTrdProductDeal().getUnitCprice();

        // 计算公式：（所输净价-上一估值净价）/上一估值净价*100%
        BigDecimal result = ArithmeticUtil.subtract(所输净价, 上一估值日净价);
        //result = ArithmeticUtil.multiply(result, new BigDecimal(100));
        result = ArithmeticUtil.divide(result, 上一估值日净价, 6);
        return result;
    }

}
