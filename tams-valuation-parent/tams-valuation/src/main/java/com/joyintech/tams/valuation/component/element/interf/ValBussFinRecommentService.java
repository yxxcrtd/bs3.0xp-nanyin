/*
 * 文件名：ValBussFinRecommentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.interf;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.FinProductRecomment;

/**
 *  资产推荐方及费率信息
 * 〈功能详细描述〉
 * @author renping
 * @version 1.0 2019年10月10日
 * @see ValBussFinRecommentService
 * @since 1.0
 */
public interface ValBussFinRecommentService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinRecommentDto";

    /**
     * 构建推荐方下拉框
     * 
     * @param param 参数
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    Page<SelectOptionDto> qryRecommenderSelect(Map<String, Object> param);


    /**
     * 初始化资产推荐方及费率信息
     * 
     * @param finprodId
     * @return 资产推荐方及费率信息
     * @see
     * @since 1.0
     */
    List<FinProductRecomment> getFinProductRecommentByProductId(String finprodId);
}
