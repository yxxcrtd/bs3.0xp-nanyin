/*
 * 文件名：MstNetAssetQuotationInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年2月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductrenewalregist.dto;

import com.joyintech.tams.common.base.entity.RepDuration;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品存续期登记
 * @author fanshuanghu
 * @version 1.0 2019年2月22日
 * @see RepFinProductRenewalRegistDto
 * @since 1.0
 */
public class RepFinProductRenewalRegistDto extends RepDuration {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4247790795414974635L;
    /**
     * 币种和申购兑付信息
     * @return 币种和申购兑付信息
     * @see
     * @since 1.0
     */
    public String getSubscriptionHonour(){
        return super.getRepCcy()+","+super.getRepApplyCny()+","+super.getRepCashCny()+","+super.getRepCashProfitCny();
    }
    /**
     * 净值币种
     * @return 净值币种
     * @see
     * @since 1.0
     */
    public String getRepNetvalCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种,super.getRepNetvalCcy());
    }
}
