
package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.util.StringUtil;

import java.io.Serializable;

/**
 * 个性化基类条件
 *  @author 裴家俊
 *  @version 1.0 2019年10月2日
 */
public interface IColBaseCond extends Serializable {

    default String getFinprodId(){
        return StringUtil.EMPTY;
    };

    String getPrtBagId();

    String getIndId();

    default String getFoulCond(){
        return StringUtil.EMPTY;
    };

    default String getApproveCond(){
        return StringUtil.EMPTY;
    };

    default String getWarnCond(){
        return StringUtil.EMPTY;
    };

}
