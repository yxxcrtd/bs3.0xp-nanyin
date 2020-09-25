package com.joyintech.tams.invest.product.productduration.addreduce.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.product.addreduce.ProductFeeInfo;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-02-17 17:30
 **/
public class ProductFeeInfoDto extends ProductFeeInfo {

    /**
     * 获取 feeType
     *
     * @return feeType
     */
    public String getFeeTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用, getFeeType());
    }

}
