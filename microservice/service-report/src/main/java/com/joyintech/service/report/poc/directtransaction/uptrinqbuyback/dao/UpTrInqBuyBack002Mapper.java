/*
 * 文件名：UpTrInqBuyBack002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.directtransaction.uptrinqbuyback.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;

/**
 * 询价交易-质押式回购
 * @author wanghb
 * @version 1.0 2019年2月15日
 * @see UpTrInqBuyBack002Mapper
 * @since 1.0
 */
@Repository
public interface UpTrInqBuyBack002Mapper {
    /**
     * 
     * 查询交易对手
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCounterId(Map<String, Object> map);
    /**
     * 检索组合
     * @param param 参数
     * @return 债券交易列表
     * @see
     * @since 1.0
     */

    List<SelectOptionDto> getPortfolioId(Map<String, Object> param);
    /**
     * 托管账户信息
     * @param param 参数
     * @return 债券交易列表
     * @see
     * @since 1.0
     */

    List<SelectOptionDto> getAcctNo(Map<String, Object> param);
    /**
     * 资金账户信息
     * @param param 参数
     * @return 债券交易列表
     * @see
     * @since 1.0
     */

    List<SelectOptionDto> getTrdAcctId(Map<String, Object> param);
    
}
