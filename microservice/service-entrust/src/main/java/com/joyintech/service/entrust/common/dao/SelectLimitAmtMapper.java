/*
 * 文件名：SelectLimitAmtMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.common.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月23日
 * @see SelectLimitAmtMapper
 * @since 1.0
 */
@Repository
public interface SelectLimitAmtMapper {

    /**
     * 
     * 查询授权额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectLimitAmt(Map<String, Object> map);
}
