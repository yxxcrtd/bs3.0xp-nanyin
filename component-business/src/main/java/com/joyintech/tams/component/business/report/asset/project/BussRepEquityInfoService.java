/*
 * 文件名：BussRepEquityInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepEquityInfoDto;

/**
 *  权益类--中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月31日
 * @see BussRepEquityInfoService
 * @since 1.0
 */
public interface BussRepEquityInfoService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussRepEquityInfoDto";
    
    /**
     * 获取画面初始化需要的数据字典
     */
    IDictCode[] getInitDictCode();
    
    /**
     * 初始化中债报送
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    BussRepEquityInfoDto getBussRepEquityInfoByProductId(String finprodId);
}
