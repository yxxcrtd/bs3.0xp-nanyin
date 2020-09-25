package com.joyintech.tams.invest.settle.fundback.dto;

import java.text.DecimalFormat;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.util.CommonBusinessUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.settle.fundback.FundBackPrintDataBaseDto;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-11-27 16:25
 **/
public class FundBackPrintDataDto extends FundBackPrintDataBaseDto {

    /**
     * 本金大写
     * 
     * @return 本金大写
     */
    public String getPrinAmtString() {
        return CommonBusinessUtil.getUpperMoneyResult(getPrinAmt());
    }

    public String getTradeDateString() {
        return DateUtil.format(getTradeDate(), DateUtil.DEFAULT_DATE_FORMAT_CN);
    }

    public String getSettleDateString() {
        return DateUtil.format(getSettleDate(), DateUtil.DEFAULT_DATE_FORMAT_CN);
    }

    public String getvDateString() {
        return DateUtil.format(getvDate(), DateUtil.DEFAULT_DATE_FORMAT_CN);
    }

    public String getmDateString() {
        return DateUtil.format(getmDate(), DateUtil.DEFAULT_DATE_FORMAT_CN);
    }

    public String getPayCycleString() {
     // 一体化对应etliu
//        return SysDataUtil.getNameFromDataDict(DictCode.支付频率_付息频率, getPayCycle());
        return SysDataUtil.getNameFromDataDict(DictCode.频率, getPayCycle());
    }

    public String getCcyString() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, getCcy());
    }

    public String getType6String() {
        return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, getType6());
    }

    public String getType6PlanString() {
        return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, getType6());
    }

    public String getBasisString() {
        return SysDataUtil.getNameFromDataDict(DictCode.计息基础, getBasis());
    }

    public String getSaleDepartmentString() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售部门, getSaleDepartment());
    }

    public String getDealModeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.处理模式, getDealMode());
    }

    public String getNoLayYield() {
        if (StringUtil.isNotEmpty(super.getNoLayYield()) && super.getNoLayYield().trim().equals("%")) {
            return "";
        }
        return super.getNoLayYield();
    }

    public String getEntrustTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.排期发行方式, getEntrustType());
    }

    public String getYieldString() {
        DecimalFormat df2 = new DecimalFormat("##0.00000");
        return df2.format(super.getYield());
    }

    public String getLaySubString() {
        if (super.getLaySub() != null) {
            return super.getLaySub().replaceAll(" ", "");
        }
        return "";
    }

    public String getPrinAmtLowerString() {
        DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00#");
        return df2.format(super.getPrinAmt());
    }

    public String getLastAmtSubLowerString() {
        DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00#");
        return df2.format(super.getLastAmtSub());
    }

    public String getCurrentSubLowerString() {
        DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00#");
        return df2.format(super.getCurrentSub());
    }

    public String getSettleAmtString() {
        return CommonBusinessUtil.getUpperMoneyResult(getSettleAmt());
    }

    public String getSettleAmtLowerString() {
        DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00#");
        return df2.format(super.getSettleAmt());
    }

    public String getmTradeAmtString() {
        return CommonBusinessUtil.getUpperMoneyResult(getmTradeAmt());
    }

    public String getmTradeAmtLowerString() {
        DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,##0.00#");
        return df2.format(super.getmTradeAmt());
    }

    public String getComment() {
        String comment = CacheUtil.getSysParam(BussinessConstant.存款类资产存放同业保本系列的单据业务说明_KEY);
        if (StringUtil.isNotEmpty(comment)) {
            comment = comment.replace("{ccy}", SysDataUtil.getNameFromDataDict(DictCode.币种, getCcy()));
            comment = comment.replace("{bank}", getAssetSupplyBank());
        }
        return comment;
    }
}
