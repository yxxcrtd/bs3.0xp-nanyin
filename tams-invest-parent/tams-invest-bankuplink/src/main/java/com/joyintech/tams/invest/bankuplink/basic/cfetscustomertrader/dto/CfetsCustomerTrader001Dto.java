/*
 * 文件名：CfetsCustomerTrader001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.basic.cfetscustomertrader.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.MstCfetsCustomerTrader;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerTrader001Dto
 * @since 1.0
 */
public class CfetsCustomerTrader001Dto extends MstCfetsCustomerTrader {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1285946910753697150L;

    /**
     * 交易员
     */
    private String partyName;
    
    /**
     * 6位机构码
     */
    private String partyCustomerId;

    /**
     * 获取 partyName
     * 
     * @return partyName.
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     * 设置 partyName
     * 
     * @param partyName partyName
     */
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    
    /**
     * 获取 partyCustomerId
     * @return partyCustomerId.
     */
    public String getPartyCustomerId() {
        return partyCustomerId;
    }

    
    /**
     * 设置 partyCustomerId
     * @param partyCustomerId partyCustomerId
     */
    public void setPartyCustomerId(String partyCustomerId) {
        this.partyCustomerId = partyCustomerId;
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
