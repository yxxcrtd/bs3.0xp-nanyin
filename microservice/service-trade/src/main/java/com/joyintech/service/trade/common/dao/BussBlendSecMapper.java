/*
 * 文件名：BussBlendSecMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年06月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.common.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 融入劵管理共通组件-微服务调用Mapper
 *
 * @author 裴家俊
 * @version 1.0 2019年06月27日
 * @since 1.0
 */
@Repository
public interface BussBlendSecMapper {

    /**
     * 债券代码：下拉框
     *
     * @param pageParam 画面参数
     * @return 债券代码：下拉框
     */
    List<SelectOptionDto> getBondCode(Map<String, Object> pageParam);

    /**
     * 根据画面条件检索可融入券信息(正回购时）
     *
     * @param params 画面参数
     * @return List<PledgeBondInfoDto>
     */
    List<PledgeBondInfoDto> selectAvaBlendBondDetailListSellRepo(Map<String, Object> params);

    /**
     * 根据画面条件检索可融入券信息(逆回购时）
     *
     * @param params 画面参数
     * @return List<BlendBondInfoDto>
     */
    List<PledgeBondInfoDto> selectAvaBlendBondDetailListAntiRepo(Map<String, Object> params);

    /**
     * 通过entrustId/bizCode检索已融入券信息(正回购或融入时）
     *
     * @param params 画面参数
     * @return List<BussRepoSecDto>
     */
    List<PledgeBondInfoDto> selectBussRepoSecSellRepo(Map<String, Object> params);

    /**
     * 通过entrustId/bizCode检索已融入券信息(逆回购或融出时）
     *
     * @param params 画面参数
     * @return List<BussRepoSecDto>
     */
    List<PledgeBondInfoDto> selectBussRepoSecAntiRepo(Map<String, Object> params);

}
