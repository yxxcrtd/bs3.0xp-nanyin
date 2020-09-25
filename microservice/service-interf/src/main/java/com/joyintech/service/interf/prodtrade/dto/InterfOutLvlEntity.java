/*
 * 文件名：InterfOutLvlEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodtrade.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口输出分层信息
 * 
 * @author yjm
 * @version 1.0 2018年11月7日
 * @see InterfOutLvlEntity
 * @since 1.0
 */
public class InterfOutLvlEntity extends InterfProdLadderDetail {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2963665423402284920L;

    @EsbName(value = "RefAddRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setRefaddrate(BigDecimal refaddrate) {
        super.setRefaddrate(refaddrate);
    }

    @EsbName(value = "PreInAmountByRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setAccountamountbyrate(BigDecimal accountamountbyrate) {
        super.setAccountamountbyrate(accountamountbyrate);
    }

    /**
     * 449191接口.分层金额
     * 
     * @param accountamountbyrate 分层金额
     * @see
     * @since 1.0
     */
    @EsbName(value = "AccountAmountByRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setAccountAmountByRateV(BigDecimal accountamountbyrate) {
        super.setAccountamountbyrate(accountamountbyrate);
    }

    /**
     * 449189接口.分层总金额
     * 
     * @param accountamountbyrate 分层总金额
     * @see
     * @since 1.0
     */
    @EsbName(value = "TotalAmountByRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setTotalAmountByRate(BigDecimal accountamountbyrate) {
        super.setAccountamountbyrate(accountamountbyrate);
    }

    /**
     * 449183接口.参考总本金
     * 
     * @param accountamountbyrate 参考总本金
     * @see
     * @since 1.0
     */
    @EsbName(value = "CurrentAmountByRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setCurrentAmountByRate(BigDecimal accountamountbyrate) {
        super.setAccountamountbyrate(accountamountbyrate);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "StartAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setStartamount(BigDecimal startamount) {
        super.setStartamount(startamount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "EndAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setEndamount(BigDecimal endamount) {
        super.setEndamount(endamount);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CustomerLevel")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setStartcustomerlevel(String startcustomerlevel) {
        super.setStartcustomerlevel(startcustomerlevel);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "EndCustomerLevel")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setEndcustomerlevel(String endcustomerlevel) {
        super.setEndcustomerlevel(endcustomerlevel);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "StartDayNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @Override
    public void setStartdaynum(Short startdaynum) {
        super.setStartdaynum(startdaynum);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "EndDayNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @Override
    public void setEnddaynum(Short enddaynum) {
        super.setEnddaynum(enddaynum);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalSourceAmountByRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setSourceamountbyrate(BigDecimal sourceamountbyrate) {
        super.setSourceamountbyrate(sourceamountbyrate);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "TotalProfitAmountByRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setTotalprofitamount(BigDecimal totalprofitamount) {
        super.setTotalprofitamount(totalprofitamount);
    }

    /**
     * 设置 参考收益率
     * 
     * @param refIncomeRate 参考收益率
     */
    @EsbName(value = "REF_INCOME_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setRefIncomeRate(BigDecimal refIncomeRate) {
        super.setRefaddrate(refIncomeRate);
    }

    /**
     * 设置 参考总本金
     * 
     * @param refTotalCorpus 参考总本金
     */
    @EsbName(value = "REF_TOTAL_CORPUS")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setRefTotalCorpus(BigDecimal refTotalCorpus) {
        super.setAccountamountbyrate(refTotalCorpus);
    }
}
