/*
 * 文件名：ValSubjectBalanceAbnormalQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.financemanage.valsubjectbalanceabnormalquery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.financemanage.valsubjectbalanceabnormalquery.dto.ValSubjectBalanceAbnormalQuery001Dto;


/**
 * 到期产品科目余额异常查询-mapper
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月28日
 * @see ValSubjectBalanceAbnormalQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValSubjectBalanceAbnormalQuery001Mapper")
public interface ValSubjectBalanceAbnormalQuery001Mapper {

    List<ValSubjectBalanceAbnormalQuery001Dto> queryPageList(Map<String, Object> params);

}
