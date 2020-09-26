/*
 * 文件名：EquityBaseInfo001Mapper.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月15日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */
 
package com.joyintech.tams.report.maintenance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.maintenance.dto.EquiteBaseInfo001Dto;

/**
 * 权益基期信息查询Mapper文件
 * 
 * @author 何阳阳
 * @version 1.0 2017年9月15日
 * @see EquityBaseInfo001Mapper
 * @since 1.0
 */
@Repository
public interface EquityBaseInfo001Mapper {

    /**
     * 权益基期分页列表查询
     * <p>根据指定条件查询列表数据</p>
     * @param param 查询参数
     * @return 查询结果列表
     */
    List<EquiteBaseInfo001Dto> queryForPage(Map<String, Object> param);

    /**
     * 查询产品登记编码是否有对应的产品
     * @param map 查询条件（产品登记编码，部门代码）
     * @return 结果条数
     * @see
     * @since 1.0
     */
    Integer queryProdInfo(Map<String, Object> map);

    /**
     * 查询指定识别表示在投资者信息中是否存在
     * @param indentifyMark 识别标识
     * @return 结果数
     * @see
     * @since 1.0
     */
    Integer queryIndentifyMark(String indentifyMark);
    /**
     * Excel中的主键获取表中已存在数据
     * @param list  excel
     * @return  list
     * @see
     * @since 1.0
     */
    List<EquiteBaseInfo001Dto> getEquityBaseList(@Param("list") List<EquiteBaseInfo001Dto> list);
}
