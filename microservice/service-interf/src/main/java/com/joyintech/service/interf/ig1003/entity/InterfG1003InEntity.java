/*
 * 文件名：InterfG1003InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1003.entity;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * G1003输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see InterfG1003InEntity
 * @since 1.0
 */
public class InterfG1003InEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1039554081539693395L;

    /**
     * 转账数组
     */
    private List<InterfG1003InTransferEntity> transferlist;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取 转账数组
     * 
     * @return 转账数组
     */
    @EsbName(value = "TRANSFER_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public List<InterfG1003InTransferEntity> getTransferlist() {
        return transferlist;
    }

    /**
     * 设置 转账数组
     * 
     * @param transferlist 转账数组
     */
    public void setTransferlist(List<InterfG1003InTransferEntity> transferlist) {
        this.transferlist = transferlist;
    }

    /**
     * 获取 备注
     * 
     * @return 备注
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
