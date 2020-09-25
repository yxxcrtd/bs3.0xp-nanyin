/*
 * 文件名：ValBalanceStatementQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery001Dto;

/**
 * 估值余额表查询Mapper
 * 
 * @author 杨智
 * @version 1.0 2018年5月17日
 * @see ValBalanceStatementQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValBalanceStatementQuery001Mapper")
public interface ValBalanceStatementQuery001Mapper {

    List<ValBalanceStatementQuery001Dto> selectAll(Map<String, Object> pageParam);
    List<ValBalanceStatementQuery001Dto> selectSum(Map<String, Object> pageParam);
}
