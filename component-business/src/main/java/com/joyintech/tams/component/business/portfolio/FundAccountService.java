/*
 * 文件名：FundAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.portfolio;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.portfolio.dto.FundAccountInfoDto;

/**
 * 组合管理接口
 * @author fanshuanghu
 * @version 1.0 2018年9月13日
 * @see FundAccountService
 * @since 1.0
 */
public interface FundAccountService {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "FundAccountInfoDto";
    /**
     * 获取账号信息
     * @param acctNo 账号
     * @return 金融产品实体类
     * @see
     * @since 1.0
     */
    
    StlCapacctInfo getCctInfo(String acctNo);
    /**
     * 获取账号信息
     * @return 下拉框列表
     * @see
     * @since 1.0
     */
    Page<SelectOptionDto> getAcctNo(Map<String, Object> param);
    /**
     * 删除组合所有账户
     * @param portfolioId 组合代码
     */
    void delete(String portfolioId);
    
    /**
     * 添加账户信息
     * @param fundAccountInfoDto 账户信息
     */
    void insert(FundAccountInfoDto fundAccountInfoDto);

    /**
     * 修改账户信息
     * @param fundAccountInfoDto 账户信息
     */
    void update(FundAccountInfoDto fundAccountInfoDto);

    /**
     * 批量插入账户信息
     * @param fundAccountInfoDto
     * @param portfolioId
     */
    void insertMulti(List<FundAccountInfoDto> fundAccountInfoDto, String portfolioId);
}
