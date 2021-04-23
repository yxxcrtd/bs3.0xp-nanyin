/*
 * 文件名：CfetsCustomerInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetscustomerinfo.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.MstCfetsCustomerInfo;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerInfo001Dto
 * @since 1.0
 */
public class CfetsCustomerInfo001Dto extends MstCfetsCustomerInfo {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -990056168226792553L;

    /**
     * 主机构
     */
    private String mpartyAbbr;

    /**
     * 获取 mpartyAbbr
     * 
     * @return mpartyAbbr.
     */
    public String getMpartyAbbr() {
        return mpartyAbbr;
    }

    /**
     * 设置 mpartyAbbr
     * 
     * @param mpartyAbbr mpartyAbbr
     */
    public void setMpartyAbbr(String mpartyAbbr) {
        this.mpartyAbbr = mpartyAbbr;
    }

    /**
     * 返回格式化后的日期 <功能详细描述>
     * 
     * @return 结果
     * @see
     * @since 1.0
     */
    public String getCreateTimeStr() {
        if (this.getCreateTime() != null) {
            return DateUtil.format(getCreateTime(), DateUtil.DEFAULT_DATETIME_FORMAT);
        }
        else {
            return null;
        }
    }

    /**
     * 返回格式化后的日期 <功能详细描述>
     * 
     * @return 结果
     * @see
     * @since 1.0
     */
    public String getUpdateTimeStr() {
        if (this.getUpdateTime() != null) {
            return DateUtil.format(getUpdateTime(), DateUtil.DEFAULT_DATETIME_FORMAT);
        }
        else {
            return null;
        }
    }
}
