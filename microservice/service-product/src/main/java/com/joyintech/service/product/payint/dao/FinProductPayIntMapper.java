/*
 * 文件名：FinProductPayIntMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.payint.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntIntDetailDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntProdBaseInfoDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntShowDto;

/**
 * 产品存续期交易查询微服务Mapper
 *
 * @author etliu
 * @version 1.0 2018年12月29日
 * @see FinProductPayIntMapper
 * @since 1.0
 */
public interface FinProductPayIntMapper {

    /**
     * 产品代码下拉框
     * @param paramMap 参数
     * @return 下拉框列表
     * @throws Exception
     */
    List<SelectOptionDto> queryProdIdInfo(Map<String, Object> paramMap);

    /**
     * 组合代码下拉框
     * @param paramMap 参数
     * @return 下拉框列表
     * @throws Exception
     */
    List<SelectOptionDto> selectPortfolioId(Map<String, Object> paramMap);

    /**
     * 查询画面分页数据
     *
     * @param paramMap 参数
     * @return 画面分页数据
     * @see
     * @since 1.0
     */
    List<FinProductPayIntDto> selectPageData(Map<String, Object> paramMap);

    /**
     * 1.6确认数据取得
     * 产品基本信息数据查询
     * @param paramMap 参数
     * @return 产品基本信息数据
     * @throws Exception
     */
    FinProductPayIntProdBaseInfoDto selectConfirmProdBaseInfo(Map<String, Object> paramMap);

    /**
     * 入参.是否分层 为是时；
     * 查询利息明细信息
     *
     * @param paramMap 参数
     * @return 利息明细信息
     * @see
     * @since 1.0
     */
    List<FinProductPayIntIntDetailDto> selectLayIntDetailData(Map<String, Object> paramMap);

    /**
     * 入参.是否分层 为否时；
     * 查询利息明细信息
     *
     * @param paramMap 参数
     * @return 利息明细信息
     * @see
     * @since 1.0
     */
    List<FinProductPayIntIntDetailDto> selectNotLayIntDetailData(Map<String, Object> paramMap);

    FinProductPayIntProdBaseInfoDto queryProdInfoByprodId(Map<String, Object> map) ;

	List<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> param);
		

	FinProductPayIntShowDto selectProdPayInfoByFinprodId(Map<String, Object> map) ;
//    /**
//     * 对应业务区间,以交易方向为维度做分组汇总
//     *
//     * @param paramMap 参数
//     * @return 汇总数据
//     * @see
//     * @since 1.0
//     */
//    List<RepDuration> selectPsRepDurationData(Map<String, Object> paramMap);
	List<FinProductPayIntIntDetailDto> selectLayIntDetailDataList(Map<String, Object> map) ;
}
