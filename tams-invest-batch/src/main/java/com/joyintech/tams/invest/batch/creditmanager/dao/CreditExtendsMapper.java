/*
 * 文件名：CreditExtendsMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.creditmanager.dao;

import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesDeal;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *  授信额度继承
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年1月25日
 * @see CreditExtendsMapper
 * @since 1.0
 */
@Repository
public interface CreditExtendsMapper {

    List<CreditLines>  selectCreditIdForTask(Map<String, Object> paramMap);

    List<CreditLines>  selectCreditIdForTaskAdd(Map<String, Object> paramMap);
    
    List<CreditLines>  selectPreCreditIdForTask(Map<String, Object> paramMap);

    List<CreditLinesDeal>  selectDealByPrimaryKey(String seqno);

    int updateLinesByPrimaryKeySelective(CreditLines creditLines);

    List<CreditLinesDeal> selectCreditLinesDeal(Map<String, Object> param);

    BigDecimal selectCreditLinesHap(Map<String, Object> param);
    
}
