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

import com.joyintech.tams.valuation.batch.psbc.bankcalbook.dto.BokBankPositionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 银行账余额处理
 * 
 * @author wangyang1 
 * @version 1.0 2019年8月28日
 * @see BokBankPositionMapper
 * @since 1.0
 */
@Repository
public interface BokBankPositionMapper {

    /**
     * 银行账科目余额
     * @param map
     * @return
     */
    List<BokBankPositionDto> selectBokBankPosition(Map<String, Object> map);

    void updateByPrimaryKey(BokBankPositionDto dto);
    
    void insertBokBankPosition(List<BokBankPositionDto> list);
    
    void insertSelective(BokBankPositionDto dto);
     
}
