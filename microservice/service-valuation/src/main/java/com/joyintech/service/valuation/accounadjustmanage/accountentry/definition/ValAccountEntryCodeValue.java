/*
 * 文件名：AccountEntryCodeValue.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月18日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.accountentry.definition;

import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
*
* 文件名：AccountEntryCodeValue.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：会计分录管理枚举类
* 系统名称：估值核算系统V1.0
*/
public enum ValAccountEntryCodeValue {
    /**
     * 会计事件
     */
    会计事件("AccountEntryCodeValue.bookEvent"),
    /**
     * ACCOUNTEVENTS
     */
    ACCOUNTEVENTS(GlobalConstant.ACCOUNTEVENTS),
    /**
     * ACCOUNTEVENTS
     */
    ROOTNODE("ROOTNODE"),
    /**
     * 根节点
     */
    根节点("AccountEntryCodeValue.rootNode");

    /**
     * code值
     */
    private String code;

    /**
     * 构造方法
     * 
     * @param code code值
     */
    ValAccountEntryCodeValue(String code) {
        this.code = code;
    }

    /**
     * 获取 code
     * 
     * @return code.
     */
    public String getCode() {
        return code;
    }
}
