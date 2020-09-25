package com.joyintech.service.interf.i449181.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.i449181.entity.Interf449181InEntity;
import com.joyintech.service.interf.i449181.entity.Interf449181InLvlEntity;

/**
 * 接口449181查询Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see Interf449181Mapper
 * @since 1.0
 */
@Repository
public interface Interf449181Mapper {

    /**
     * 取得是否分层，市场代码，开始销售日，截止销售日字段
     * 
     * @param prodId 金融产品代码
     * @return 输入Entity
     */
    Interf449181InEntity selectProdInfo(@Param("prodId") String prodId);

    /**
     * 取得分层信息
     * 
     * @param prodId 金融产品代码
     * @param prodType 金融产品类型（投管分类）
     * @return 分层信息
     * @see
     * @since 1.0
     */
    List<Interf449181InLvlEntity> selectProdLvlInfo(@Param("prodId") String prodId,
                                                   @Param("prodType") String prodType);

}
