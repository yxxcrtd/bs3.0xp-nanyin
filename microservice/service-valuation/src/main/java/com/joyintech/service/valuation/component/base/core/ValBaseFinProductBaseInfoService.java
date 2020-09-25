/*
 * 文件名：StandardFinancialProducts.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core;

import java.util.List;

import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductBaseInfoDto;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.FinStdsecConverseRadio;
import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.common.base.entity.MstFundMarketInfo;


/**
 * 标的类金融产品基本信息组件
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年8月29日
 * @see ValBaseFinProductBaseInfoService
 * @since 1.0
 */
public interface ValBaseFinProductBaseInfoService {
    /**
     * 
     * 新建标的类金融产品基本信息
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(List<ValBaseFinProductBaseInfoDto> list);
    /**
     *
     * 删除标的类金融产品信息
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean delete(List<String> finprodIdList);
    /**
     * 
     * 修改标的类金融产品信息
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean update(List<String> finprodIdList,List<ValBaseFinProductBaseInfoDto> list);
    /**
     * 
     * 查询已生效的标的类金融产品信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<ValBaseFinProductBaseInfoDto> select(List<String> finprodIdList);
    /**
     * 债券估值行情保存
     * @param list
     * @return
     */
    boolean insertMstBondValuationInfo(List<MstBondValuationInfo> list);
    /**
     * 债券市场行情保存
     * @param list
     * @return
     */
    boolean insertmstBondMarketInfo(List<MstBondMarketInfo> list);
    /**
     * 回购标准折算率保存(新增)
     * @param list
     * @return
     */
    boolean insertFinStdsecConverseRadio(List<FinStdsecConverseRadio> list);
    /**
     * 基金-场外金融产品保存(新增)
     * @param list
     * @return
     */
    boolean insertFinProductValuation(List<FinProductValuation> list);
    /**
     * 基金-基金市场行情保存(新增)
     * @param list
     * @return
     */
    boolean insertMstFundMarketInfo(List<MstFundMarketInfo> list);
}
