/*
 * 文件名：BankAccountTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.psbc.bankaccounttrade.dao;

import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeReaderDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月10日
 * @see BankAccountTradeMapper
 * @since 1.0
 */
@Repository
public interface BankTransactionAccountMapper {

	List<BankAccountTradeReaderDto> selectBankAccountTrade(Map<String, Object> param);

    void deleteBokVouchDetail(@Param("vouchNum") List<String> vouchNum);

    void deleteBokVouchMain(@Param("vouchNum") List<String> vouchNum);
    
    List<String> selectVouchNum(@Param("booksetId") String booksetId, @Param("bookDate") Date bookDate);
     
    BigDecimal selectSubjectBalance(Map<String, Object> map);
    
    BigDecimal queryFeeAmtByFeeType(Map<String, Object> map);
}
