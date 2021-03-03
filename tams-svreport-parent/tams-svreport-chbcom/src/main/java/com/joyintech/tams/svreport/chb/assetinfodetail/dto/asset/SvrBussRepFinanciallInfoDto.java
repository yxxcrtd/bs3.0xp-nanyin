/*
 * 文件名：BussFinBondRepInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.asset.SvrBussFinBondRepInfoValidator;

import java.math.BigDecimal;

/**
 *  资产基本信息_金融衍生品类信息 DTO
 * 〈功能详细描述〉
 * @author
 * @version 1.0
 * @see SvrBussRepFinanciallInfoDto
 * @since 1.0
 */
public class SvrBussRepFinanciallInfoDto extends RepAssetBond{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;


    /**
     * 名义本金
     */
    private BigDecimal nomCapi;

    /**
     * 标的类别 01 债券/ 02 股票/03 基金类产品/99 其他
     */
    private String targetType3;

    /**
     * 持有目的
     */
    private String holdPurpose;

    /**
     * 备注
     */
    private  String  remark;


    public BigDecimal getNomCapi() {
        return nomCapi;
    }

    public void setNomCapi(BigDecimal nomCapi) {
        this.nomCapi = nomCapi;
    }

    public String getTargetType3() {
        return targetType3;
    }

    public void setTargetType3(String targetType3) {
        this.targetType3 = targetType3;
    }

    public String getHoldPurpose() {
        return holdPurpose;
    }

    public void setHoldPurpose(String holdPurpose) {
        this.holdPurpose = holdPurpose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
