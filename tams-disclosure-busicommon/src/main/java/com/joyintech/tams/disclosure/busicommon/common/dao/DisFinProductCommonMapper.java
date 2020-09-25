/*
 * 文件名：FinProductCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.common.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.common.dto.FinProduct;


/**
 * 标的类共通方法 - 数据库数据获取
 * 
 * @author wangzhiang
 * @version 1.0 2018年5月31日
 * @see FinProductCommonMapper
 * @since 1.0
 */
@Repository
public interface DisFinProductCommonMapper {
	 /**
     * 核算主体查询 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    FinProduct queryAcctSubject(@Param("booksetId") String booksetId,
                                         @Param("acctSubjectType") String acctSubjectType);
    
    
}
