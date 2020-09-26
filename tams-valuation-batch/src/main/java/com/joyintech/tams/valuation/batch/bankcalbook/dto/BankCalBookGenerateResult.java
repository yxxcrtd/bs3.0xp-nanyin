/*
 * 文件名：BankCalBookGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 11, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.bankcalbook.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;

/**
 * 银行账计提账务处理 待出账DTO
 * @author zhangwei
 * @version 1.0 Apr 11, 2019
 * @see BankCalBookGenerateResult
 * @since 1.0
 */
public class BankCalBookGenerateResult extends BookGenerateResult {

    List<BokVouchMainDto> listBokVouchMainDto = Lists.newArrayList();

    
    public List<BokVouchMainDto> getListBokVouchMainDto() {
        return listBokVouchMainDto;
    }

    
    public void setListBokVouchMainDto(List<BokVouchMainDto> listBokVouchMainDto) {
        this.listBokVouchMainDto = listBokVouchMainDto;
    }
    
    
    
}
