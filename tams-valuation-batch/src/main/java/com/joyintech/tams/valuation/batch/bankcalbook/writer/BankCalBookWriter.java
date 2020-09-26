/*
 * 文件名：BankCalBookWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.bankcalbook.writer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.soap.Detail;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.bankcalbook.dao.BankCalBookMapper;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BankCalBookGenerateResult;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BokVouchDetailDto;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BokVouchMainDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.common.base.dao.BokBankBalanceMapper;
import com.joyintech.tams.common.base.dao.BokVouchMainMapper;
import com.joyintech.tams.common.base.entity.BokBankBalance;

/**
 * 银行账计提账务处理
 * 
 * @author zhangwei
 * @version 1.0 Apr 10, 2019
 * @see BankCalBookWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankCalBookWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private BankCalBookMapper bankCalBookMapper;

    @Autowired
    private AccountingCommonService accountingCommonService;

    @Autowired
    private BokBankBalanceMapper bokBankBalanceMapper;
    
    @Autowired
    private BokVouchMainMapper bokVouchMainMapper;

    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<BankCalBookGenerateResult> resultList = (List<BankCalBookGenerateResult>)items;

        // 循环记账凭证list，对每个记账凭证进行操作
        for (BankCalBookGenerateResult result : resultList) {
            // 待出账list入库
            // 凭证主表
            // bankCalBookMapper.insertBokVouchMain(result.getListBokVouchMainDto());

            for (BokVouchMainDto bokVouchMainDto : result.getListBokVouchMainDto()) {

                for (BokVouchDetailDto bokVouchDetailDto : bokVouchMainDto.getListBokVouchDetailDto()) {

                    // 科目余额方向查询
//                    String balFlag = accountingCommonService.getBalFlag(bokVouchDetailDto.getSubjectNo(),
//                            bokVouchMainDto.getBooksetId());

                    // 调用共通方法查询：银行账计提余额
                    List<BokBankBalance> listBankBokBankBalance = accountingCommonService.findBokBankBalance(
                        bokVouchDetailDto.getFinprodId(), bokVouchDetailDto.getBranch(), bokVouchMainDto.getBooksetId(),
                        bokVouchDetailDto.getTableId(), bokVouchMainDto.getBookDate(),
                        bokVouchDetailDto.getSubjectNo());

                    if (CollectionUtils.isNotEmpty(listBankBokBankBalance)) {
                        BokBankBalance bankBokBankBalance = listBankBokBankBalance.get(0);
                        // 调用共同处理 【余额方向处理_发生额加余额】
                        Map<String, Object> map = accountingCommonService.balanceDirectDealAccrualAddBalance(bokVouchDetailDto.getCdFlag(),
                                bankBokBankBalance.getBalFlagBookDate(), bankBokBankBalance.getbAmtBookDate(), null,
                                bokVouchDetailDto.getCdFlag(), bokVouchDetailDto.getHappenAmt(), null,null,null);

                        // 银行账计提余额.余额 = 返回.余额
                        bankBokBankBalance.setbAmtBookDate((BigDecimal)map.get("balance"));
                        // 银行账计提余额.余额方向 = 返回.余额方向
                        bankBokBankBalance.setBalFlagBookDate((String)map.get("balDirect"));

                        // 银行账计提余额表插入、更新，按业务key值插入、更新
                        bokBankBalanceMapper.updateByPrimaryKey(bankBokBankBalance);
                    }
                    else {
                        BokBankBalance bokBankBalance = new BokBankBalance();
                        bokBankBalance.setSqeNo(IDGenerator.generate());
                        bokBankBalance.setBooksetId(bokVouchMainDto.getBooksetId());
                        bokBankBalance.setVouchNum(bokVouchMainDto.getBookDate());
                        bokBankBalance.setFinprodId(bokVouchDetailDto.getFinprodId());
                        bokBankBalance.setBranch(bokVouchDetailDto.getBranch());
                        bokBankBalance.setTableId(bokVouchDetailDto.getTableId());
                        bokBankBalance.setSubjectNo(bokVouchDetailDto.getSubjectNo());
                        bokBankBalance.setbAmtBookDate(bokVouchDetailDto.getHappenAmt());
                        bokBankBalance.setBalFlagBookDate(bokVouchDetailDto.getCdFlag());
                        bokBankBalance.setCcy(bokVouchDetailDto.getCcy());
                        bokBankBalance.setBusiId(bokVouchDetailDto.getBusiId());

                        // 银行账计提余额表插入、更新，按业务key值插入、更新
                        bokBankBalanceMapper.insertSelective(bokBankBalance);
                    }
                }
                
                // 凭证科目发生额都0时，不出凭证
                List<BokVouchDetailDto> listBokVouchDetailDto = bokVouchMainDto.getListBokVouchDetailDto().stream().filter(item -> {
                    return BigDecimal.ZERO.compareTo(item.getHappenAmt()) != 0;
                }).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(listBokVouchDetailDto)) {
                    continue;
                }
                
                // 凭证主表
                bokVouchMainMapper.insertSelective(bokVouchMainDto);
                // 凭证子表
                bankCalBookMapper.insertBokVouchDetail(bokVouchMainDto.getListBokVouchDetailDto());
            }
        }

    }

}
