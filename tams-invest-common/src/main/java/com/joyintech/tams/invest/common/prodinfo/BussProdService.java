/*
 * 文件名：BussProdService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.prodinfo;

import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.invest.common.prodinfo.dto.IPtlProd;

import java.util.List;
import java.util.Map;

/**
 * 组合信息
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年10月11日
 * @see BussProdService
 * @since 1.0
 */
public interface BussProdService<V>{

    /**
     * 新建组合
     * @param prodInfoList
     * @return
     */
     int insertBussProd(V... prodInfoList);

    /**
     * 更新组合
     * @param prodInfoList
     * @return
     */
    int updateBussProd(V... prodInfoList);

    /**
     * 删除组合
     * @param prodInfoList
     * @return
     */
    int deleteBussProd(V... prodInfoList);

	List<PtlPortfolio> selectAllEnaled(V... prodInfoList);

}
