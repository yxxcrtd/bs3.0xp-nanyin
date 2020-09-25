/*
 * 文件名：IRiskRds.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import java.io.Serializable;

/**
 * 风险基口
 * @author 裴家俊
 * @version 1.0 2020年1月10日
 * @see IRiskRds
 * @since 1.0
 */
public interface IRiskRds extends Serializable {

    /**
     * 事件类型
     * @return
     */
    String getEventType();

    /**
     * 资产类型
     * @return
     */
    String getAssetType();

    /**
     * 事件代码
     * @return
     */
    String getInfCode();

    /**
     * 金融产品类型_投管
     * @return
     */
    String getFinType();

    /**
     * 事件状态
     * @return
     */
    String getEventStatus();

    void setAssetType(String assetType);

    void setFinType(String finType);

    void setInfCode(String infCode);

    void setEventType(String eventType);

    void setEventStatus(String eventStatus);
}
