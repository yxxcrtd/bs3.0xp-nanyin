package com.joyintech.tams.component.base.report.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
/**
 * 产品端中债报备信息组件Mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年9月4日
 * @see BaseRepFinProductInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseRepFinProductInfoMapper {
    /**
     * 删除产品申报报送信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param repFinprodIdList
     * @return
     * @see
     * @since 1.0
     */
    int deleteRepProdDeclare(@Param("repFinprodIdList")List<String> repFinprodIdList);
    /**
     * 删除产品发行报送信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param repFinprodIdList
     * @return
     * @see
     * @since 1.0
     */
    int deleteRepProdIssue(@Param("repFinprodIdList")List<String> repFinprodIdList);
    
    /**
     * 修改标的类金融产品表
     * 
     * @param finProduct
     * @return
     */
    int updateFinProduct(FinProduct finProduct);
    /**
     * 根据行内标识码查询中债报送信息
     * 
     * @param finprodId 行内标识码
     * @return 中债报送信息
     */
    RepProdDeclare selectRepFinprodId(@Param("repFinprodInsId") String repFinprodInsId);
}