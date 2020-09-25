/*
 * 文件名：InterfLc0006Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0006.dao;

import org.springframework.stereotype.Repository;

/**
 * Lc0006信贷指令推送给理财系统接口mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年12月20日
 * @see InterfLc0006Mapper
 * @since 1.0
 */
@Repository
public interface InterfLc0006Mapper {

    /**
     * 通过出账编号查询记录数
     * 
     * @param cashOutNo 出账编号
     * @return 记录个数
     * @see
     * @since 1.0
     */
    int selectByCashOutNo(String cashOutNo);
}
