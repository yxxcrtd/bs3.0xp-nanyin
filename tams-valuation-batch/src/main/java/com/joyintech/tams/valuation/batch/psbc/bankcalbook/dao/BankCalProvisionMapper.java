/*
 * 文件名：BankCalProvisionMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.psbc.bankcalbook.dao;

import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BankCalBookComResult;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BokVouchDetailDto;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BokVouchMainDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 银行账计提账务处理
 * 
 * @author wangyang1
 * @version 1.0 Apr 10, 2019
 * @see BankCalProvisionMapper
 * @since 1.0
 */
@Repository
public interface BankCalProvisionMapper {

    BokBookset selectBankBookset(Map<String, Object> map);

    List<BankCalBookComResult> selectFinProduct(Map<String, Object> map);
    
    void insertBokVouchMain(List<BokVouchMainDto> list);
    
    void insertBokVouchDetail(List<BokVouchDetailDto> list);
}
