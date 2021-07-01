/*
 * 文件名：ValRiftMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.creditPierce.dao;

import com.joyintech.tams.common.base.entity.CreditMstInfo;
import com.joyintech.tams.invest.batch.creditPierce.dto.PtlSecPierceValDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 *  授信估值穿透数据接入
 * 〈功能详细描述〉
 * @author wnagyang1
 * @version 1.0 2020年12月14日
 * @see CreditPierceMapper
 * @since 1.0
 */
@Repository
public interface CreditPierceMapper {

    List<PtlSecPierceValDto>  selectPtlSecPierceValutionList(Map<String, Object> paramMap);

    List<CreditMstInfo>  selectCreditMstInfoList();

    void deleteCreditPierce(Map<String, Object> paramMap);

//    String getInstitutionCode(Map<String,Object> paramMap);
    
}
