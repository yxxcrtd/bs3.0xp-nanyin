/*
 * 文件名：NetAccountingCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dao.NetProdAccountingCommonMapper;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto.NetBokStatBokPositionDto;

/**
 * 净值型产品估值数据相关Service
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see NetAccountingCommonService
 * @since 1.0
 */
@Service
public class NetProdAccountingCommonService {
	
	/**
     * 数据库数据获取
     */
    @Autowired
    private NetProdAccountingCommonMapper netAccountingCommonMapper;
	
	/**
     * 公用方法：账务类共通方法-帐套资产负债查询
     * 
     * @param sdateStart 查询开始日
     * @param sdateEnd 查询结束日
     * @param bookDate 入账日
     * @param booksetId 帐套代码
     * @param queryDates 查询日列表
     * @param finprodId 金融产品代码
     * @return 帐套资产负债信息
     * @see
     * @since 1.0
     */
	public List<BokStatBokPosition> getValStatBokPosition(NetBokStatBokPositionDto position) {

        return netAccountingCommonMapper.getValStatBokPosition(position);
    }
}
