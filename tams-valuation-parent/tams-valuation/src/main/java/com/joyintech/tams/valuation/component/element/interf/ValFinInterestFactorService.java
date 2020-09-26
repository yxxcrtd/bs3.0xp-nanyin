/*
 * 文件名：ValFinInterestFactorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.interf;


import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.valuation.component.element.dto.ValFinInterestFactorDto;

/**
 * 计息要素业务组件接口
 * @author zhangnian
 * @version 1.0 2018年9月6日
 * @see ValFinInterestFactorService
 * @since 1.0
 */
public interface ValFinInterestFactorService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "finInterestFactorDto";

    /**
     * 获取画面初始化需要的数据字典
     */
    IDictCode[] getInitDictCode();
    
    /**
     * 共同画面返回值
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    ValFinInterestFactorDto getFinInterestFactorDto(ValFinInterestFactorDto dto);
    
    /**
     * 业务组件画面初始化
     * <一句话功能简述>
     * <功能详细描述>
     * @param finProductId
     * @return
     * @see
     * @since 1.0
     */
    ValFinInterestFactorDto getFinInterestFactorByProductId(String finProductId,String vdate);
    
}
