/*
 * 文件名：AccountCurrentRate002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.accountcurrentrate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate002Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年11月13日
 * @see AccountCurrentRate002Mapper
 * @since 1.0
 */
@Repository(value="serviceAccountCurrentRate002Mapper")
public interface AccountCurrentRate002Mapper {

    /**
     * 帐户下拉框初始化
     * 
     * @param map Map
     * @return List<AccountCurrentRate002Dto>
     * @see
     * @since 1.0
     */
    List<AccountCurrentRate002Dto> getAccNo(Map<String, Object> map);

    // /**
    // * 联动帐户类型
    // *
    // * @param dto AccountCurrentRate002Dto
    // * @return
    // * @see
    // * @since 1.0
    // */
    // AccountCurrentRate002Dto searchType(AccountCurrentRate002Dto dto);

    /**
     * 修改双击查看页面数据初始化
     * 
     * @param map Map
     * @return AccountCurrentRate002Dto
     * @see
     * @since 1.0
     */
    AccountCurrentRate002Dto getfind002Dto(Map<String, Object> map);

    /**
     * 基准利率初始化
     * 
     * @return List<AccountCurrentRate002Dto>
     * @see
     * @since 1.0
     */
    List<AccountCurrentRate002Dto> getBasicRate();

    /**
     * check 帐号账户名 生效日是否存在相同数据
     * 
     * @param map Map<String, Object>
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean checkAccNo(Map<String, Object> map);

    /**
     * 帐户名联想
     * 
     * @param map Map
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAcctNo1(Map<String, Object> map);

    /**
     * 现金流信息表 现金流代码 是否存在
     * 
     * @param cashId 现金流代码
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean checkCashId(@Param("cashId") String cashId);

    /**
     * check账户号是否联想框选择的
     * 
     * @param accNo accNo
     * @param accType accType
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean checkAccNo1(@Param("accNo") String accNo, @Param("accType") String accType);
}
