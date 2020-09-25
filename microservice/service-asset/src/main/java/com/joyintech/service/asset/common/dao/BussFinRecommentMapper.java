/*
 * 文件名：BussFinRecommentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.asset.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.FinProductRecomment;

/**
 * 资产推荐方及费率信息
 * 
 * @author renping
 * @version 1.0 2019年10月10日
 * @see BussFinRecommentMapper
 * @since 1.0
 */
@Repository
public interface BussFinRecommentMapper {

    /**
     * 查询标的类金融产品推荐表
     *
     * @param finprodId 参数
     * @return 标的类金融产品推荐表
     * @see
     * @since 1.0
     */
    List<FinProductRecomment> getFinProductRecommentByProductId(@Param("finprodId") String finprodId);

    /**
     * 标的类金融产品推荐表删除
     *
     * @param finprodId finprodId
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByProductId(@Param("finprodId") String finprodId);

    /**
     * 构建推荐方下拉框
     * 
     * @param param 参数
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    List<SuggestDto> qryRecommenderSelect(Map<String, Object> param);

}
