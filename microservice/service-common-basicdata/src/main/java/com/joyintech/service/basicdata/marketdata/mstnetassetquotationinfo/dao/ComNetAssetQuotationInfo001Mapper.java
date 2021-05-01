/*
 * 文件名：MstNetAssetQuotationInfoMapper001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstnetassetquotationinfo.dto.ComNetAssetQuotationInfo001Dto;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;

/**
 * 计划类（净值型）资产估值行情信息mapper
 * 
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see ComNetAssetQuotationInfo001Mapper
 * @since 1.0
 */
@Repository 
public interface ComNetAssetQuotationInfo001Mapper {
    /**
     * 根据条件查找数据
     * 
     * @param param 查询条件
     * @return 场外金融产品估值信息
     */
    List<ComNetAssetQuotationInfo001Dto> select(Map<String, Object> param);
    
    /**
     * 资产名称的联想文本框事件
     * @param map 
     * 
     * @return 资产名称和资产代码列表
     */
    List<SuggestDto> getAssetName(Map<String, Object> map);
    
    /**
     * 查询界面资产代码的联想文本框事件
     * @param map 
     * 
     * @return 资产名称和资产代码列表
     */
    List<SuggestDto> getAssetCode(Map<String, Object> map);
    
    /**
     * 新建界面资产代码的联想文本框事件
     * @param map 
     * 
     * @return 资产名称和资产代码列表
     */
    List<SelectOptionDto> getAssetCodeList(Map<String, Object> map);
    
    /**
     * 根据条件查找数据
     * 通过资产代码查询相关数据，完成查看和编辑界面的初始化
     * @param param 传入的参数
     * @return dto
     */
    ComNetAssetQuotationInfo001Dto selectByFinProdId(Map<String, Object> param);
    
    /**
     * 插入操作
     *
     * @param entity 插入的内容
     * @return 结果
     * @see
     * @since 1.0
     */
    int insert(ComNetAssetQuotationInfo001Dto entity);
    
    /**
     * 查询金融产品类型
     * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodId 资产代码
     * @return 产品类型
     * @see
     * @since 1.0
     */
    String selectFinProdType(String finprodId);
    
    /**
     * 修改操作
     * 
     * @param entity 需要更新的内容
     * @return 结果
     * @see
     * @since 1.0
     */
    int update(ComNetAssetQuotationInfo001Dto entity);
    
    /**
     * 查找数据
     * check是否插入已存在的数据
     * @param finprodId 资产代码
     * @param valDate  估值日期
     * @return dto
     * @see
     * @since 1.0
     */
    ComNetAssetQuotationInfo001Dto selectByIdAndDate(Map<String,Object> map);
    
    /**
     * 查找资产名称和管理人
     * 通过输入的资产代码，查出资产名称和管理人完成画面联动处理
     * @param map 查询的参数
     * @return dto
     * @see
     * @since 1.0
     */
    ComNetAssetQuotationInfo001Dto selectNameAndFundManager(Map<String, Object> map);

    /**
     * 删除操作
     * 
     * @param entity 删除的参数
     * @return 结果
     * @see
     * @since 1.0
     */
    int delete(ComNetAssetQuotationInfo001Dto entity);

    /**
     * 查询金融产品类型
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodId 金融产品代码
     * @return 金融产品类型（投管分类）
     * @see
     * @since 1.0
     */
    String selectFinProdType2(String finprodId);
    
  //hsgexinghua add by zhangxing1 20200228 start
    /**
     * 查询新增数据主键是否重复
     * @param list 产品list
     * @return list
     * @see
     * @since 1.0
     */

    String getRegistExcelList( Map<String,Object> param );

    /**
     * 查询新增数据产品代码是否存在
     * @param finprodId 产品代码
     * @param finprodType2 金融产品类型
     * @return list
     * @see
     * @since 1.0
     */

    ComNetAssetQuotationInfo001Dto checkFinprod(Map<String,Object> param) ;
    
    /**
     * 查询新增数据产品代码与产品名称是否匹配
     * @param finprodId 产品代码
     * @param finprodType2 金融产品类型
     * @return list
     * @see
     * @since 1.0
     */

    ComNetAssetQuotationInfo001Dto checkFinprodIdAndFinprodName(Map<String,Object> param) ;
    //hsgexinghua add by zhangxing1 20200228  end
}
