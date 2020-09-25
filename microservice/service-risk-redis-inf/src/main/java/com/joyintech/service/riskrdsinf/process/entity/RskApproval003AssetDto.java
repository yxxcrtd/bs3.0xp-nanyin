/*
 * 文件名：RskApproval003AssetDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.process.entity;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;

/**
 * 模糊委托审批接口-资产信息
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月10日
 * @see RskApproval003AssetDto
 * @since 1.0
 */
public class RskApproval003AssetDto extends RskDimEntrustInfo {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -6836505466098037351L;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 获取 资产名称
     * 
     * @return 资产名称.
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * 
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * 将交易场所对应的数据字典代码转换为数据字典值
     * 
     * @return 数据字典值
     */
    public String getMarketAbbr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易流通场所, this.getMarket());
    }

}
