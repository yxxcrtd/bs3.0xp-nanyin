/*
 * 文件名：BussCoreCalculateService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.common.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 平安银行算法组件
 *
 * @author 刘二通
 * @version 1.0 2018年11月13日
 * @see BussCoreCalculateService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class BussCoreCalculateService extends BaseService {

    /**
     * 计算债券净价总额
     * 净价总额 = 净价 * 券面总额/100
     *
     * @param 净价
     * @param 券面总额
     * @return 债券净价总额 小数点保留4位
     * @see
     * @since 1.0
     */
    public BigDecimal calc债券净价总额(BigDecimal 净价, BigDecimal 券面总额) {
        BigDecimal 债券净价总额 = ArithmeticUtil.multiply(净价, 券面总额);
        债券净价总额 = ArithmeticUtil.divide(债券净价总额, BigDecimal.valueOf(100), 4);

        return 债券净价总额;
    }

    /**
     * 计算债券全价总额
     * 全价总额=(应计利息+净价) * 券面总额/100
     *
     * @param 应计利息
     * @param 净价
     * @param 券面总额
     * @return 债券全价总额 小数点保留4位
     * @see
     * @since 1.0
     */
    public BigDecimal calc债券全价总额(BigDecimal 应计利息, BigDecimal 净价, BigDecimal 券面总额) {
        BigDecimal 债券全价总额 = ArithmeticUtil.doAdd(应计利息, 净价);
        债券全价总额 = ArithmeticUtil.multiply(债券全价总额, 券面总额);
        债券全价总额 = ArithmeticUtil.divide(债券全价总额, BigDecimal.valueOf(100), 4);

        return 债券全价总额;
    }

    /**
     * 投资交易计算回购利息
     * 回购利息=本金金额*回购利率*回购期限*计息基础
     *
     * @param 本金金额
     * @param 回购利率
     * @param 起息日
     * @param 到期日
     * @param 计息基础
     * @return 回购利息
     * @see
     * @since 1.0
     */
    public BigDecimal calc投资交易计算回购利息(BigDecimal 本金金额, BigDecimal 回购利率, Date 起息日, Date 到期日,
                                     String 计息基础) {
        BigDecimal 回购利息 = null;

        int num_计息基础 = 365;
        if (StringUtil.equals(DictCodeList.计息基础_A_360.getValue(), 计息基础)) {
            num_计息基础 = 360;
        }

        long 回购期限 = DateUtil.getBetweenDayNumber(起息日, 到期日);

        // 回购利息=本金金额*回购利率*回购期限*计息基础
        // 小数点保留2位
        回购利息 = ArithmeticUtil.multiply(本金金额, 回购利率, BigDecimal.valueOf(回购期限));
        回购利息 = ArithmeticUtil.divide(回购利息, BigDecimal.valueOf(num_计息基础), 2);

        return 回购利息;
    }
}
