/*
 * 文件名：CeAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.ceasset.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;

/**
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAssetMapper
 * @since 1.0
 */
@Repository
public interface CeAssetMapper {

    List<CreditLinesAstDto> selectForPage(Map<String, Object> param);
    CreditLinesAstDto queryCeAssetBySeqno(Map<String, Object> param);
    /**
     * 资产代码联想下拉框
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getFinprodMarketIds(Map<String, Object> map);
    
    List<CreditLinesAstDto> searchSameAssetGroup(@Param(value = "customerId") String customerId);
    //多级法人改造 add by jingqiongqiong 20200615
    /**
     * 获取资产id联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAssetIdPub(Map<String, Object> param);
    //多级法人改造 add by jingqiongqiong 20200615
}
