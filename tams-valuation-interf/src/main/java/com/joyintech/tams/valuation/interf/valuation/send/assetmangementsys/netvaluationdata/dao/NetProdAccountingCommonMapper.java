/*
 * 文件名：NetAccountingCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto.NetBokStatBokPositionDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see NetAccountingCommonMapper
 * @since 1.0
 */
@Repository
public interface NetProdAccountingCommonMapper {
	/**
     * 帐套资产负债查询
     *
     * @param sdateStart 查询开始日
     * @param sdateEnd 查询结束日 
     * @param bookDate 入账日
     * @param booksetId 帐套代码
     * @return 帐套资产负债信息
     * @see
     * @since 1.0
     */
    List<BokStatBokPosition> getValStatBokPosition(NetBokStatBokPositionDto position);
}
