/*
 * 文件名：IDictCodeList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.common.base.intf;

import java.io.Serializable;

/**
 * @author 何阳阳
 * @version 1.0 2018年2月5日
 * @see IDictCodeList
 * @since 1.0
 */
public interface IDictCodeList extends Serializable {

    /**
     * 获取 DictCode
     *
     * @return value.
     */
    IDictCode getDictCode();

    /**
     * 获取 value
     *
     * @return value.
     */
    String getValue();

    /**
     * 设置 value
     *
     * @param value value
     */
    void setValue(String value);

    /**
     * 获取 codeName
     *
     * @return codeName.
     */
    String getCodeName();

    /**
     * 设置 codeName
     *
     * @param codeName codeName
     */
    void setCodeName(String codeName);
}
