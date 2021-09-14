/*
 * 文件名：AssetCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.valbatchimport.common.dao;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.invest.batch.valbatchimport.dto.RskValtblPrtBotassetMapDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *  资产端共通
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月25日
 * @see AssetCommonMapper
 * @since 1.0
 */
@Repository
public interface AssetCommonMapper {

    List<FinProduct> selectAllAsset();

    /**
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<MstCalendarInfo> getMstCalendarInfo();

    /**
     * by组合代码查找底层资产映射
     * <一句话功能简述>
     * <功能详细描述>
     * @param ptlId
     * @return
     * @see
     * @since 1.0
     */
    List<RskValtblPrtBotassetMapDto>  selectBotAssetMap(@Param("ptlId") String ptlId);
}
