/*
 * 文件名：PtlMockTestTaaDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.portfolio.ptlmock.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTestTaa;
import com.joyintech.tams.constant.DictCode;
//import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValCommonUtil;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

/**
 * 模拟测算资产配置
 * 
 * @author etliu
 * @version 1.0 2020年4月26日
 * @see PtlMockTestTaaDto
 * @since 1.0
 */
public class PtlMockTestTaaDto extends MockTestTaa {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4106713235979114001L;

    /**
     * 资产维度
     */
    private String assetTypeName;

    /**
     * 获取 assetTypeName
     * 
     * @return assetTypeName.
     */
    public String getAssetTypeName() {
        if (StringUtil.equals("00", getAssetType())) {
            assetTypeName = "现金";
        }
        else {
            assetTypeName = SysDatadictUtil.getNameFromDataDict(DictCode.平安资产类型, getAssetType());
        }
        return assetTypeName;
    }

    /**
     * 设置 assetTypeName
     * 
     * @param assetTypeName assetTypeName
     */
    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

}
