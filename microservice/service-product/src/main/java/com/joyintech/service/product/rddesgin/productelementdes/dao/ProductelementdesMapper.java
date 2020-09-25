/*
 * 文件名：FinProductDurDealMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.rddesgin.productelementdes.dao;

import com.joyintech.service.product.rddesgin.productelementdes.dto.Productelementdes001Dto;
import com.joyintech.service.product.rddesgin.productelementdes.dto.Productelementdes002Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 产品存续期交易查询微服务Mapper
 *
 * @author jyl
 * @version 1.0 2019年9月25日
 * @see ProductelementdesMapper
 * @since 1.0
 */
@Repository
public interface ProductelementdesMapper {
   /**
     * 分页查询画面交易数据
     * @param pageParam 参数
     * @return 交易数据
     * @see
     * @since 1.0
     */
    List<Productelementdes002Dto> selectMainData(Map<String, Object> pageParam);
    
    /**
     * 分页查询画明细数据
     * @param pageParam 参数
     * @return 交易数据
     * @see
     * @since 1.0
     */
    List<Productelementdes001Dto> selectDetailData(Map<String, Object> pageParam);
    /**
     * 分页查询画明细数据
     * @param productelementdesDto 参数
     * @return 插入产品要素设计
     * @see
     * @since 1.0
     */
    void insertProductelementdes(Productelementdes002Dto productelementdes002Dto);
    Productelementdes001Dto findSinglePage(String requirementId);
    Productelementdes002Dto findprdDesignId(String prdDesignId);
    void createProductelementdes002(Productelementdes002Dto productelementdes002Dto);
    //删流程表
    void deleteProductelementdes002Dto(String prdDesignId);
    //删要素信息表
    void deletePro002Dto(String prdDesignId);
    //更新
    void updateProductelementdes(Productelementdes002Dto productelementdes002Dto);

}
