/*
 * 文件名：BussFinRecommentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.component.base.core.impl.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 资产推荐方及费率信息
 * 
 * @author renping
 * @version 1.0 2019年10月10日
 * @see ValBaseFinRecommentMapper
 * @since 1.0
 */
@Repository
public interface ValBaseFinRecommentMapper {

    /**
     * 标的类金融产品推荐表删除
     *
     * @param finprodId finprodId
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByProductId(@Param("finprodId") String finprodId);
}
