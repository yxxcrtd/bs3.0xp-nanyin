/*
 * 文件名：SecuritiesAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.portfolio;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;

/**
 * 证券账户Service
 * @author fanshuanghu
 * @version 1.0 2018年9月20日
 * @see SecuritiesAccountService
 * @since 1.0
 */
public interface SecuritiesAccountService {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "FundAccountInfoDto";
    /**
     * 获取证券账号信息
     * @param acctNo 账号
     * @return 金融产品实体类
     * @see
     * @since 1.0
     */
    
    SecuritiesAccountInfoDto getSecAcctInfo(String secAcctNo);
    /**
     * 获取证券账号
     * @return 下拉框列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSecAcctId(Map<String, Object> param);
    /**
     * 删除组合所有证券账户
     * @param portfolioId portfolioId
     */
    void delete(String portfolioId);
    
    /**
     * 添加证券账户信息
     * @param securitiesAccountInfoDto 证券账户信息
     */
    void insert(SecuritiesAccountInfoDto securitiesAccountInfoDto);

    /**
     * 批量插入证券账户信息
     * @param securitiesAccountInfoDto
     * @param portfolioId
     */
    void insertMulti(List<SecuritiesAccountInfoDto> securitiesAccountInfoDto, String portfolioId);
}
