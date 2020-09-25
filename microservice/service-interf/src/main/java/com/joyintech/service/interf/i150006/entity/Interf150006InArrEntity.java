/*
 * 文件名：Interf150006InArrEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150006.entity;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 产品观察日数组
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月16日
 * @see Interf150006InArrEntity
 * @since 1.0
 */
public class Interf150006InArrEntity {

    /**
     * 产品观察日
     */
    private String prodObserveDt;

    /**
     * 获取 产品观察日
     * 
     * @return 产品观察日.
     */
    @EsbName(value = "PROD_OBSERVE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getProdObserveDt() {
        return prodObserveDt;
    }

    /**
     * 设置 产品观察日
     * 
     * @param prodObserveDt 产品观察日
     */
    public void setProdObserveDt(String prodObserveDt) {
        this.prodObserveDt = prodObserveDt;
    }
}
