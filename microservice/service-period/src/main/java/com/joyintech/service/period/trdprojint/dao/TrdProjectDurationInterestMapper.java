/*
 * 文件名：FinProductModule002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdprojint.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.period.trdprojbuyoff.TrdProjectBuyOffInterestDto;
import com.joyintech.tams.invest.common.dto.period.trdprojint.TrdProjectDurationInterestDto;

/**
 * 债券存续期管理
 *
 * @author 强成西
 * @version 1.0 2018年8月31日
 * @see TrdProjectDurationInterestMapper
 * @since 1.0
 */
@Repository
public interface TrdProjectDurationInterestMapper {

    List<TrdProjectDurationInterestDto> findPage(Map<String, Object> param);

    TrdProjectBuyOffInterestDto findDtoByAssetId(String assetId);
}
