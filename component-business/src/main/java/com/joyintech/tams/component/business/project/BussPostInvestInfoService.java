/*
 * 文件名：BussPostInvestInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.FinPostInvestInfo;
import com.joyintech.framework.base.intf.IDictCode;

import java.util.Map;

/**
 * 投后信息--业务组件
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月26日
 * @see BussPostInvestInfoService
 * @since 1.0
 */
public interface BussPostInvestInfoService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussPostInvestInfoDto";
    
    /**
     * 获取画面初始化需要的数据字典
     */
    IDictCode[] getInitDictCode();
    
    FinPostInvestInfo getBussPostInvestInfoByProductId(String finprodId);

    Page<SelectOptionDto> selectCounterId(Map<String, Object> param);
}
