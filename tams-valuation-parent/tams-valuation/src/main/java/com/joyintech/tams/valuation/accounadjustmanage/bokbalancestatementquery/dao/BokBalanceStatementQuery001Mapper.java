/*
 * 文件名：BokBalanceStatementQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.dto.BokBalanceStatementQuery001Dto;

/**
 * 估值余额表查询Mapper
 * 
 * @author 杨智
 * @version 1.0 2018年5月17日
 * @see BokBalanceStatementQuery001Mapper
 * @since 1.0
 */
@Repository
public interface BokBalanceStatementQuery001Mapper {

    List<BokBalanceStatementQuery001Dto> selectAll(Map<String, Object> pageParam);
//    List<BokBalanceStatementQuery001Dto> selectSum(Map<String, Object> pageParam);
}
