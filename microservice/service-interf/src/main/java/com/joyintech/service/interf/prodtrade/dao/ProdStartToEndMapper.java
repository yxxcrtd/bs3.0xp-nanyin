package com.joyintech.service.interf.prodtrade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.InterfBranch;
import com.joyintech.tams.common.base.entity.InterfProdInfo;
import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;

/**
 * 成立申赎到期产品查询
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see ProdStartToEndMapper
 * @since 1.0
 */
@Repository
public interface ProdStartToEndMapper {

    /**
     * 成立产品查询
     * 
     * @param prodType 金融产品类型（投管分类）
     * @param sysDate 平台日期
     * @param typeList 处理模式
     * @param prodList 产品列表
     * @return 金融产品信息
     * @see
     * @since 1.0
     */
    List<InterfFinProdDto> selectProdFoundInfo(@Param("prodType") String prodType,
                                               @Param("sysDate") String sysDate,
                                               @Param("typeList") List<String> typeList,
                                               @Param("prodList") List<String> prodList);

    /**
     * 存续期产品查询
     * 
     * @param prodType 金融产品类型（投管分类）
     * @param sysDate 平台日期
     * @param typeList 处理模式
     * @param prodList 产品列表
     * @return 金融产品信息
     * @see
     * @since 1.0
     */
    List<InterfFinProdDto> selectProdAliveInfo(@Param("prodType") String prodType,
                                               @Param("sysDate") String sysDate,
                                               @Param("typeList") List<String> typeList,
                                               @Param("prodList") List<String> prodList);

    /**
     * 到期产品查询
     * 
     * @param prodType 金融产品类型（投管分类）
     * @param sysDate 平台日期
     * @param prodList 产品列表
     * @return 金融产品信息
     * @see
     * @since 1.0
     */
    List<InterfFinProdDto> selectProdEndInfo(@Param("prodType") String prodType,
                                             @Param("sysDate") String sysDate,
                                             @Param("prodList") List<String> prodList);

    /**
     * 删除产品申赎信息
     * 
     * @param info 产品申赎信息
     * @return 删除行数
     * @see
     * @since 1.0
     */
    int deleteProdInfo(InterfProdInfo info);

    /**
     * 删除产品分行申赎信息
     * 
     * @param branch 产品分行申赎信息
     * @return 删除行数
     * @see
     * @since 1.0
     */
    int deleteProdBranchInfo(InterfBranch branch);

    /**
     * 删除产品分层申赎信息
     * 
     * @param detail 产品分层申赎信息
     * @return 删除行数
     * @see
     * @since 1.0
     */
    int deleteProdLadderDetail(InterfProdLadderDetail detail);

    /**
     * 查询分层信息
     * 
     * @param lvl 查询条件
     * @return 分层信息
     * @see
     * @since 1.0
     */
    InterfProdLadderDetail selectLvlInfo(InterfProdLadderDetail lvl);
}
