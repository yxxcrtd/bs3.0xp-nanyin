/*
 * 文件名：FeeRateService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ifeerate.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.ifeerate.dao.FeeRateMapper;
import com.joyintech.service.interf.ifeerate.entity.FeeRateEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 查询费率
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see FeeRateService
 * @since 1.0
 */
@Service
public class FeeRateService {

    /**
     * 查询费率
     */
    @Autowired
    private FeeRateMapper mapper;

    /**
     * 返回费率，类型BigDecimal
     * 
     * @param feeRateEntity 查询条件
     *            <p>
     *            {@link FeeRateEntity}</br>
     *            金融产品代码（finprodId）必须</br>
     *            现金流一级类型（cashType1）必须</br>
     *            现金流二级类型（cashType2）必须</br>
     *            截止日（cdate）必须
     *            </p>
     * @return 费率
     */
    public BigDecimal selectFeeRate(FeeRateEntity feeRateEntity) {
        return mapper.selectFeeRate(feeRateEntity);
    }

    /**
     * 查询费率
     * 
     * @param finprodId 金融产品类型
     * @param cashType2 费用类型（现金流二级类型）
     * @param cdate 截止日
     * @return 费率
     */
    public BigDecimal selectFeeRate(String finprodId, DictCodeListExt1 cashType2,
                                    String cdate) {
        FeeRateEntity feeRateEntity = new FeeRateEntity();
        feeRateEntity.setFinprodId(finprodId);
        feeRateEntity.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
        feeRateEntity.setCashType2(cashType2.getValue());
        feeRateEntity.setCdate(cdate);
        return selectFeeRate(feeRateEntity);
    }

    /**
     * 返回费率，类型String
     * 
     * @param feeRateEntity 查询条件
     * @return 费率
     */
    public String selectFeeRateStr(FeeRateEntity feeRateEntity) {
        BigDecimal rate = mapper.selectFeeRate(feeRateEntity);
        return StringUtil.numberToString(rate);
    }
}
