/*
 * 文件名：BaseRepTradeInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.trade;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepAssetAstBill;
import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.tams.component.base.report.dto.BaseRepTradeInfoDto;

/**
 * 交易报送信息组件
 *
 * @author 刘二通
 * @version 1.0 2018年11月4日
 * @see BaseRepTradeInfoService
 * @since 1.0
 */
public interface BaseRepTradeInfoService {

    /**
     * 新建
     * <功能详细描述>
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(List<RepTrade> list);

    /**
     * 删除
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean delete(BaseRepTradeInfoDto dto);

    /**
     * 更新
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean update(BaseRepTradeInfoDto dto);

    /**
     * 查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetAstBill> select(BaseRepTradeInfoDto dto);
}
