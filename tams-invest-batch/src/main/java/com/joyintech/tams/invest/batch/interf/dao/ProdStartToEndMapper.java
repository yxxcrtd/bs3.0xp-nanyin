package com.joyintech.tams.invest.batch.interf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
     * 产品查询
     * 
     * @param doType 处理类型：成立、存续、到期、万分收益
     * @param prodType 金融产品类型（投管分类）
     * @param sysDate 平台日期
     * @param dealType 处理模式
     * @param saleDept 销售部门
     * @return 金融产品信息
     * @see
     * @since 1.0
     */
    List<String> selectProdInfo(@Param("doType") String doType,
                                @Param("prodType") String finProdId,
                                @Param("sysDate") String sysDate,
                                @Param("typeList") List<String> typeList,
                                @Param("saleDept") String saleDept);
}
