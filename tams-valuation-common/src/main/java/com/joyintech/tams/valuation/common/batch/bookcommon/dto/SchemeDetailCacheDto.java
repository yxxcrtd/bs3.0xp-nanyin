/*
 * 文件名：SchemeDetailCacheDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统v1.0
 * 修改人：LiJun
 * 修改时间：2020年07月27日
 * 修改内容：新建
 * 系统名称：估值核算系统v1.0
 */
package com.joyintech.tams.valuation.common.batch.bookcommon.dto;

import java.io.Serializable;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SchemeDetailDto;


/**
 * 估值方案明细缓存数据Dto
 *
 * @author LiJun
 * @version 1.0 2020年07月27日
 * @see SchemeDetailCacheDto
 * @since 1.0
 */
public class SchemeDetailCacheDto extends SchemeDetailDto implements Serializable {

    /**
    * <意义，目的和功能，以及可能被用到的地方>
    */
    private static final long serialVersionUID = 1L;

    /**
     * 是否默认方案
     */
    private String isdefault;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 方案类型
     */
    private String schemeType;

    /**
     * 是否特殊
     */
    private String isSpecial;

    /**
     * 获取 是否默认方案
     * @return
     */
    public String getIsdefault() {
        return isdefault;
    }

    /**
     * 设置 是否默认方案
     * @param isdefault
     */
    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial;
    }
}
