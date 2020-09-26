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

import com.joyintech.tams.common.base.entity.BokBussDetsubKey;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年6月14日
 * @see ValBokBussDetsubMem
 * @since 1.0
 */
public class ValBokBussDetsubMem extends BokBussDetsubKey {

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
                 + ((getDetailSubjectNo() == null) ? 0 : getDetailSubjectNo().hashCode());
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
        ValBokBussDetsubMem other = (ValBokBussDetsubMem)obj;
        if (getBooksetId() == null) {
            if (other.getBooksetId() != null)
                return false;
        }
        else if (!getBooksetId().equals(other.getBooksetId()))
            return false;
        if (getDetailSubjectNo() == null) {
            if (other.getDetailSubjectNo() != null)
                return false;
        }
        else if (!getDetailSubjectNo().equals(other.getDetailSubjectNo()))
            return false;
        return true;
    }
}
