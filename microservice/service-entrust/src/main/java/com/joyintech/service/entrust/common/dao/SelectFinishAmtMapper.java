/*
 * 文件名：SelectFinishAmt.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.common.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月9日
 * @see SelectFinishAmtMapper
 * @since 1.0
 */
@Component
public interface SelectFinishAmtMapper {

    /**
     * 
     * 查询委托完成额度
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectfinishAmt(Map<String, Object> map);
}
