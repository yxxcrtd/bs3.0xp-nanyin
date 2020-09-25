/*
 * 文件名：BaseRepBankBackAssetInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.asset;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepLiabilities;
import com.joyintech.tams.component.base.report.dto.BaseRepBankBackAssetInfoDto;

/**
 * 负债(同业拆入卖出回购)报送信息
 * 对拆借及回购新建时的报送信息进行处理，提供新建、修改、删除和查询接口。
 *
 * @author 刘二通
 * @version 1.0 2018年11月5日
 * @see BaseRepBankBackAssetInfoService
 * @since 1.0
 */
public interface BaseRepBankBackAssetInfoService {

    /**
     * 新建负债(同业拆入/卖出回购)报送信息
     * <功能详细描述>
     * @param repLiabilitiesList 负债(同业拆入/卖出回购)报送信息
     * @return 处理结果
     * @see
     * @since 1.0
     */
    boolean insert(List<RepLiabilities> repLiabilitiesList);

    /**
     * 查询负债(同业拆入/卖出回购)报送信息
     *
     * @param dto 参数
     * @return 负债(同业拆入/卖出回购)报送信息
     * @see
     * @since 1.0
     */
    List<RepLiabilities> selectRepBankBackAssetInfo(BaseRepBankBackAssetInfoDto dto);

    /**
     * 删除负债(同业拆入/卖出回购)报送信息
     * <功能详细描述>
     * @param dto 参数
     * @return 处理结果
     * @see
     * @since 1.0
     */
    boolean deleteByPrimaryKey(BaseRepBankBackAssetInfoDto dto);

    /**
     * 修改负债(同业拆入/卖出回购)报送信息
     * <功能详细描述>
     * @param dto 参数
     * @return 处理结果
     * @see
     * @since 1.0
     */
    boolean updateRepBankBackAssetInfo(BaseRepBankBackAssetInfoDto dto);
}
