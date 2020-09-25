/*
 * 文件名：FinFundTargetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.fund.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.fund.dto.BussFinIntPayDto;
import com.joyintech.service.asset.fund.dto.BussRepFundInfoDto;
import com.joyintech.service.asset.fund.dto.FinFundTargetDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月10日
 * @see FinFundTargetMapper
 * @since 1.0
 */
@Repository
public interface FinFundTargetMapper {

    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinProdMarketIdList(Map<String, Object> param);

    /**
     * 查询基金管理人
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getIssuer(Map<String, Object> param);

    /**
     * 页面查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinFundTargetDto> selectForPage(Map<String, Object> param);

    /**
     * 根据金融产品代码查询dto
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    FinFundTargetDto selectByFinprodId(Map<String, Object> param);

    /**
     * 根据市场代码查询dto
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FinFundTargetDto findEntity(Map<String, Object> param);

    /**
     * 删除标的类金融产品扩展
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByFinprodId(String finprodId);

    /**
     * 根据画面.管理人，关联客户信息查询出客户名称
     * <功能详细描述>
     * @param issuer
     * @return
     * @see
     * @since 1.0
     */
    String getCustomerNameByManager(String manager);

    /**
     * 给分红计划组件赋值
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    BussFinIntPayDto getIntPayByFinprodId(Map<String,Object> param);

    /**
     * 给基金中债报送页面赋值
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    BussRepFundInfoDto getFundInfoByFinprodId(Map<String, Object> param);

    /**
     * 根据发行人查询发行机构类型、发行机构所属行业
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    BussRepFundInfoDto getInfoByIssuer(Map<String, Object> pageParam);

}
