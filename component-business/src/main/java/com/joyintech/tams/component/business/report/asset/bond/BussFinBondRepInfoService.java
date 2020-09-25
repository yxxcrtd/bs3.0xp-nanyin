/*
 * 文件名：BussFinBondRepInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.bond;

import java.util.Map;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.bond.dto.BussFinBondRepInfoDto;

/**
 *  债券中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see BussFinBondRepInfoService
 * @since 1.0
 */
public interface BussFinBondRepInfoService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinBondRepInfoDto";
    
    /**
     * 获取画面初始化需要的数据字典
     */
    IDictCode[] getInitDictCode();
    
    BussFinBondRepInfoDto getBussFinBondRepInfoDto(BussFinBondRepInfoDto dto);
    
    /**
     * 初始化中债报送
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    BussFinBondRepInfoDto getBussFinBondRepInfoByProductId(String finprodId);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    BussFinBondRepInfoDto getInfoByIssuer(Map<String, Object> pageParam);
}
