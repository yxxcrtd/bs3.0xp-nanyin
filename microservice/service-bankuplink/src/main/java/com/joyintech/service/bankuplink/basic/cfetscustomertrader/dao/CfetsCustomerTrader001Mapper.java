/*
 * 文件名：CfetsCustomerTrader001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetscustomertrader.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.bankuplink.basic.cfetscustomertrader.dto.CfetsCustomerTrader001Dto;

/**
 * 银行间交易员信息 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerTrader001Mapper
 * @since 1.0
 */
@Repository
public interface CfetsCustomerTrader001Mapper {

    /**
     * 模糊查询 <功能详细描述>
     * 
     * @param pageParam pageParam
     * @return List<MstCaptialAccountDto>
     * @see
     * @since 1.0
     */
    List<CfetsCustomerTrader001Dto> select(Map<String, Object> pageParam);
}
