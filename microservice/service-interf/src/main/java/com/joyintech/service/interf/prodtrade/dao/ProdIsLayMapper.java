package com.joyintech.service.interf.prodtrade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 查询产品分层信息
 * 
 * @author yjm
 * @version 2018年11月6日下午8:30:12
 */
@Repository
public interface ProdIsLayMapper {

    /**
     * 取得是否分层，市场代码，开始销售日，截止销售日字段
     * 
     * @param prodId 金融产品代码
     * @return 输入Entity
     */
    InterfAliveProdEntity<?> selectProdInfo(@Param("prodId") String prodId);

    /**
     * 取得分层信息
     * 
     * @param prodId 金融产品代码
     * @param prodType 金融产品类型（投管分类）
     * @return 分层信息
     * @see
     * @since 1.0
     */
    List<InterfProdLadderDetail> selectProdLvlInfo(@Param("prodId") String prodId,
                                                   @Param("prodType") String prodType);
}
