/*
 * 文件名：ValBokSubjectDetailMem.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年6月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.cache.service.entity;

import com.joyintech.tams.common.base.entity.BokSubjectDetailKey;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年6月14日
 * @see ValBokSubjectDetailMem
 * @since 1.0
 */
public class ValBokSubjectDetailMem extends BokSubjectDetailKey {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBooksetId() == null) ? 0 : getBooksetId().hashCode());
        result = prime * result
                 + ((getBaseSubjectNature() == null) ? 0 : getBaseSubjectNature().hashCode());
        result = prime * result + ((getBokDetailId() == null) ? 0 : getBokDetailId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ValBokSubjectDetailMem other = (ValBokSubjectDetailMem)obj;
        if (getBooksetId() == null) {
            if (other.getBooksetId() != null)
                return false;
        }
        else if (!getBooksetId().equals(other.getBooksetId()))
            return false;
        if (getBaseSubjectNature() == null) {
            if (other.getBaseSubjectNature() != null)
                return false;
        }
        else if (!getBaseSubjectNature().equals(other.getBaseSubjectNature()))
            return false;
        if (getBokDetailId() == null) {
            if (other.getBokDetailId() != null)
                return false;
        }
        else if (!getBokDetailId().equals(other.getBokDetailId()))
            return false;
        return true;
    }
}
