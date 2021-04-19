/*
 * 文件名：MstCoinFundQuotationInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.marketdata.mstcoinfundquotationinfo.dto.ComCoinFundQuotationInfo001Dto;

/**
 * 货币型基金行情信息Mapper接口
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月3日
 * @see CurrencyFundInfo001Mapper
 * @since 1.0
 */
@Repository
public interface ComCoinFundQuotationInfoMapper {

    /**
     * 分页查询
     * 
     * @param pageParam 查询条件
     * @return List<MstCoinFundQuotationInfo001Dto> 实体类集合
     * @see
     * @since 1.0
     */
    List<ComCoinFundQuotationInfo001Dto> queryForPage(Map<String, Object> pageParam);
    /**
     * 查询信息
     *
     * @param pageParam 查询条件
     * @return List<MstCoinFundQuotationInfo001Dto> 实体类集合
     * @see
     * @since 1.0
     */
    List<ComCoinFundQuotationInfo001Dto> selectPageInfo(Map<String, Object> pageParam);

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
     * @return MstCoinFundQuotationInfo001Dto 返回的基金信息实体类
     * @see
     * @since 1.0
     */
    ComCoinFundQuotationInfo001Dto selectManagement(Map<String, Object> map);
    
    //hsgexinghua add by zhangxing1 20200228 start
    /**
     * 查询新增数据主键是否重复
     * @param list 产品list
     * @param orgCode 机构代码
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList(Map<String,Object> param);

    /**
     * 查询新增数据主键是否重复
     * @param list 产品list
     * @param orgCode 机构代码
     * @return list
     * @see
     * @since 1.0
     */

    ComCoinFundQuotationInfo001Dto checkFinprod(Map<String,Object> param) ;
    
    /**
     * 查询新增数据主键是否重复
     * @param list 产品list
     * @param orgCode 机构代码
     * @return list
     * @see
     * @since 1.0
     */

    ComCoinFundQuotationInfo001Dto checkType(Map<String,Object> param) ;
    
    /**
     * 查询新增数据主键是否重复
     * @param list 产品list
     * @param orgCode 机构代码
     * @return list
     * @see
     * @since 1.0
     */

    String  checkFundManager(Map<String,Object> param) ;
    //hsgexinghua add by zhangxing1 20200228  end
}
