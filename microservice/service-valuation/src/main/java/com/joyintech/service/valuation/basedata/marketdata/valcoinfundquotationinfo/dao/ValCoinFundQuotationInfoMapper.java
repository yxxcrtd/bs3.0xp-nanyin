/*
 * 文件名：ValCoinFundQuotationInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valcoinfundquotationinfo.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valcoinfundquotationinfo.dto.ValCoinFundQuotationInfo001Dto;

/**
 * 货币型基金行情信息Mapper接口
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月3日
 * @see CurrencyFundInfo001Mapper
 * @since 1.0
 */
@Repository
public interface ValCoinFundQuotationInfoMapper {

    /**
     * 分页查询
     * 
     * @param pageParam 查询条件
     * @return List<ValCoinFundQuotationInfo001Dto> 实体类集合
     * @see
     * @since 1.0
     */
    List<ValCoinFundQuotationInfo001Dto> queryForPage(Map<String, Object> pageParam);
    /**
     * 查询信息
     *
     * @param pageParam 查询条件
     * @return List<ValCoinFundQuotationInfo001Dto> 实体类集合
     * @see
     * @since 1.0
     */
    List<ValCoinFundQuotationInfo001Dto> selectPageInfo(Map<String, Object> pageParam);

    /**
     * 查询基金代码（画面一联想文本框）
     * 
     * @param map 模糊查询中前台输入的值
     * @return List<SuggestDto> 联想文本框实体类集合
     * @see
     * @since 1.0
     */

    List<SuggestDto> queryCode(Map<String, Object> map);

    /**
     * 查询基金管理人（画面一联想文本框）
     * 
     * @param map 模糊查询中前台输入的值
     * @return List<SuggestDto> 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    List<SuggestDto> queryManagement(Map<String, Object> map);

    /**
     * 查询基金代码（画面二联想文本框）
     * 
     * @param map 模糊查询中前台输入的值
     * @return List<SelectOptionDto> 联想下拉框实体类集合
     * @see
     * @since 1.0
     */
    List<SuggestDto> queryFinprod(Map<String, Object> map);

    /**
     * 查找基金信息是否存在
     * 
     * @param finprodId 基金代码
     * @return String 基金代码
     * @see
     * @since 1.0
     */
    String selectByfinprodId(Map<String,Object> map);

    /**
     * 通过主键进行查找
     * 
     * @param map 参数map
     * @return stirng 代码
     * @see
     * @since 1.0
     */
    String selectByIdAndValDate(Map<String, Object> map);

    /**
     * 查找基金管理人
     * 
     * @param map 限制条件
     * @return ValCoinFundQuotationInfo001Dto 返回的基金信息实体类
     * @see
     * @since 1.0
     */
    ValCoinFundQuotationInfo001Dto selectManagement(Map<String, Object> map);
}
