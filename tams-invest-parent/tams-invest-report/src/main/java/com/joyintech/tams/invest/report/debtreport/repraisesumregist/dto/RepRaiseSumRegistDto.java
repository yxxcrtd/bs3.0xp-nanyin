/*
 * 文件名：RepRaiseSumRegistDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年2月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repraisesumregist.dto;

import com.joyintech.tams.common.base.entity.RepRaiseSum;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 募集期总量登记实体类
 * @author fanshuanghu
 * @version 1.0 2019年2月22日
 * @see RepRaiseSumRegistDto
 * @since 1.0
 */
public class RepRaiseSumRegistDto extends RepRaiseSum {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4944985513180623198L;
    /**
     * 是否有其他代销机构
     * @return
     * @see
     * @since 1.0
     */
    public String getRepIsOtherSaleStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, super.getRepIsOtherSale());
    }
    /**
     * 代销区域及代销总金额
     * @return 代销区域及代销总金额
     * @see
     * @since 1.0
     */
    public String getRepSaleAreaStr(){
        if (StringUtil.isNotEmpty(super.getRepSaleArea())) {
            return super.getRepSaleArea()+","+SysDataUtil.getNameFromDataDict(DictCode.行政区划_省, super.getRepSaleArea())+","+super.getRepAreaRaiseSum();
        }
        return "";
    }

}
