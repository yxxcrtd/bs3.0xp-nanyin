package com.joyintech.tams.exchange.common.utils;

import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.EntrustProp;
import com.joyintech.tams.exchange.common.constant.InvestmentAssetType;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.OrderInterface;

import java.math.BigDecimal;
import java.util.Objects;

public class TradeUtils {

    public static BigDecimal getBigDecimal(Double d, int round) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static double formatDouble(Double d, int round) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(round, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double formatDouble(Double d, int round, int roundingMode) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(round, roundingMode).doubleValue();
    }

    public static BigDecimal add(BigDecimal d, BigDecimal decimal, int round) {
        return d.add(decimal).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal add(BigDecimal d, long volume, int round) {
        BigDecimal decimal1 = new BigDecimal(volume);
        return d.add(decimal1).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal add(BigDecimal d, BigDecimal decimal, BigDecimal bigDecimal, int round) {
        return d.add(decimal).add(bigDecimal).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal add(Double d, BigDecimal decimal, int round) {
        BigDecimal bd = new BigDecimal(d);
        return decimal.add(bd).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal d, long num, int round) {
        BigDecimal bd = new BigDecimal(num);
        return d.multiply(bd).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal d, BigDecimal num, int round) {
        return d.multiply(num).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divide(BigDecimal d, long num, int round) {
        BigDecimal bd = new BigDecimal(num);
        return d.divide(bd, round, BigDecimal.ROUND_HALF_UP);
    }

    public static Double divide(BigDecimal d, BigDecimal num, int round) {
        return d.divide(num, round, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static BigDecimal divideAndRemainder(BigDecimal d, BigDecimal num) {
        return d.divideAndRemainder(num)[1];
    }

    public static BigDecimal subtract(BigDecimal d, long num, int round) {
        BigDecimal bd = new BigDecimal(num);
        return d.subtract(bd).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal subtract(BigDecimal d, BigDecimal num, int round) {
        return d.subtract(num).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal subtract(BigDecimal d, BigDecimal num, BigDecimal decimal, int round) {
        return d.subtract(num).subtract(decimal).setScale(round, BigDecimal.ROUND_HALF_UP);
    }

    public static boolean equals(BigDecimal d, BigDecimal decimal) {
        return d.compareTo(decimal) == 0;
    }

    public static boolean bigThan(BigDecimal d, BigDecimal decimal) {
        if (Objects.nonNull(d) && Objects.nonNull(decimal)) {
            return d.compareTo(decimal) == 1;
        } else
            return false;
    }

    public static boolean lessThan(BigDecimal d, BigDecimal decimal) {
        if (Objects.nonNull(d) && Objects.nonNull(decimal)) {
            return d.compareTo(decimal) == -1;
        } else
            return false;
    }

    // 卖出冻结数量
    public static boolean isFrozenVolume(String entrustProp, String entrustBs, String investmentAssetType) {
        // 意向申报，不需要冻结
        if (EntrustProp.INTENTION.value.equals(entrustProp))
            return false;
            // 协议回购首期报价申报，正回购 冻结数量
        else if (EntrustProp.SH_REPURCHASE_TRADE.value.equals(entrustProp))
            return true;
        else if (EntrustProp.SH_REPURCHASE_TRADE_CONFIRM.value.equals(entrustProp))
            return false;
        else if (EntrustProp.SZ_REPURCHASE_BEGIN.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs))
            return true;
        else if (EntrustProp.SZ_REPURCHASE_BEGIN.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs))
            return false;
            // 质押式竞价回购，正回购 冻结数量
        else if (InvestmentAssetType.PLEDGE_REPURCHASE.value.equals(investmentAssetType) && EntrustBs.BUY.value.equals(entrustBs))
            return true;
        else if (InvestmentAssetType.PLEDGE_REPURCHASE.value.equals(investmentAssetType) && EntrustBs.SELL.value.equals(entrustBs))
            return false;
        else
            return EntrustBs.SELL.value.equals(entrustBs);
    }

    // 买入冻结金额
    public static boolean isFrozenBalance(String entrustProp, String entrustBs, String investmentAssetType) {
        // 意向申报，不需要冻结
        if (EntrustProp.INTENTION.value.equals(entrustProp))
            return false;
            // 协议回购首期成交确认，逆回购 冻结金额
        else if (EntrustProp.SH_REPURCHASE_TRADE_CONFIRM.value.equals(entrustProp))
            return true;
        else if (EntrustProp.SH_REPURCHASE_TRADE.value.equals(entrustProp))
            return false;
        else if (EntrustProp.SZ_REPURCHASE_BEGIN.value.equals(entrustProp) && EntrustBs.SELL.value.equals(entrustBs))
            return true;
        else if (EntrustProp.SZ_REPURCHASE_BEGIN.value.equals(entrustProp) && EntrustBs.BUY.value.equals(entrustBs))
            return false;
            // 质押式竞价回购，逆回购 冻结金额
        else if (InvestmentAssetType.PLEDGE_REPURCHASE.value.equals(investmentAssetType) && EntrustBs.SELL.value.equals(entrustBs))
            return true;
        else if (InvestmentAssetType.PLEDGE_REPURCHASE.value.equals(investmentAssetType) && EntrustBs.BUY.value.equals(entrustBs))
            return false;
        else
            return EntrustBs.BUY.value.equals(entrustBs);
    }

    public static BigDecimal calculateBalance(BigDecimal entrustPrice, long entrustVolume, String assetType) {
        return calculateBalance(null, entrustPrice, entrustVolume, assetType, null, null);
    }

    public static BigDecimal calculateBalance(String entrustBs, BigDecimal entrustPrice, long entrustVolume, String assetType, OrderInterface orderInterface, FuturesInstrument futuresInstrument) {
        //协议回购金额计算
        if (Objects.nonNull(orderInterface) && IntegratedEntrustUtils.isProtocolBack(orderInterface.getEntrustProp())) {
            return ((IntegratedEntrust) orderInterface).getEntrustBalance();
        }
        //债券按净价冻结 --- 需求新修正为净价 --- > 修正为全价
        if (InvestmentAssetType.BOND.value.equals(assetType)) {
            return multiply(orderInterface.getFullPrice(), entrustVolume, 2);
        }
        // 质押式竞价回购，金额计算
        if (InvestmentAssetType.PLEDGE_REPURCHASE.value.equals(assetType)) {
            return new BigDecimal(entrustVolume * 100).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        if (InvestmentAssetType.FUTURES.value.equals(assetType)) {
            BigDecimal res = multiply(entrustPrice, entrustVolume, 2);
            return multiply(res, BigDecimal.valueOf(Objects.nonNull(futuresInstrument) ? futuresInstrument.getVolumeMultiple() : 1), 2);
        }
        // TODO ETF计算
        return multiply(entrustPrice, entrustVolume, 2);
    }

    //投管风控获取全价总额以及净价总额
    public static BigDecimal calculateBalanceForRisk(String entrustBs, BigDecimal entrustPrice, long entrustVolume, String assetType) {
        // 质押式竞价回购，金额计算
        if (InvestmentAssetType.PLEDGE_REPURCHASE.value.equals(assetType)) {
            return new BigDecimal(entrustVolume * 100).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return multiply(entrustPrice, entrustVolume, 2);
    }
}
