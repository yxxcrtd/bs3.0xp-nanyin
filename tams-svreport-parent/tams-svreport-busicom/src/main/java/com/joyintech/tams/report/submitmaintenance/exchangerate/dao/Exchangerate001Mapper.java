/*
 * 文件名：EquityBaseInfo001Mapper.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月15日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */
 
package com.joyintech.tams.report.submitmaintenance.exchangerate.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitmaintenance.exchangerate.dto.Exchangerate001Dto;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dto.Exchangerate002Dto;


/**
 * 汇率行情维护查询Mapper文件
 * 
 * @author 何阳阳
 * @version 1.0 2017年9月15日
 * @see Exchangerate001Mapper
 * @since 1.0
 */
@Repository
public interface Exchangerate001Mapper {

    /**
     * 汇率行情维护分页列表查询
     * <p>根据指定条件查询列表数据</p>
     * @param param 查询参数
     * @return 查询结果列表
     */
    List<Exchangerate001Dto> queryForPage(Map<String, Object> param);
    
 
    /**
     * 插入导入数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param exchangerate001Dto 参数
     * @return int
     * @see
     * @since 1.0
     */
    int insertSelective(Exchangerate001Dto exchangerate001Dto);
    
    /**
     * 删除选中数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param exchangerate001Dto 更新参数
     * @return int
     * @see
     * @since 1.0
     */
    int updateByPrimaryKeySelective(Exchangerate001Dto exchangerate001Dto);

    /**
     *
     * 根据流程ID查询用户信息
     * @param param 参数数据
     * @return 用户信息
     * @see
     * @since 1.0
     */
    Exchangerate002Dto getInfoDataByPrimaryKey(Map<String, Object> param);


    /**
     * 判断数据库中是否已经存在了将要新建的对应数据
     * <功能详细描述>
     * @param dto 要验证的数据
     * @return 查询到的数量
     * @see
     * @since 1.0
     */
    int isExist(Exchangerate002Dto dto);


    /**
     * 根据条件删除数据
     * <功能详细描述>
     * @param exchangerate001Dto 条件
     * @see
     * @since 1.0
     */
    void deleteData(Exchangerate001Dto exchangerate001Dto);
    
    /**
     * 查找导入文件中的数据在数据库中已经存在的数据条数
     *
     * @param keylist 数据列表
     * @return 数据条数
     * @see
     * @since 1.0
     */
    int selectDataCount(@Param("keylist") List<Exchangerate001Dto> keylist);


    /**
     * 查询最接近查询日期的最新的汇率
     * @param map 查询map
     * @return 汇率
     * @see
     * @since 1.0
     */
    BigDecimal selectNewRate(Map<String, Object> map);
}
