/*
 * 文件名：CfetsCustomerInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetscustomerinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.bankuplink.basic.cfetscustomerinfo.dto.CfetsCustomerInfo001Dto;

/**
 * 银行间机构信息 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerInfo001Mapper
 * @since 1.0
 */
@Repository
public interface CfetsCustomerInfo001Mapper {

    /**
     * 模糊查询 <功能详细描述>
     * 
     * @param pageParam pageParam
     * @return List<MstCaptialAccountDto>
     * @see
     * @since 1.0
     */
    List<CfetsCustomerInfo001Dto> select(Map<String, Object> pageParam);
}
