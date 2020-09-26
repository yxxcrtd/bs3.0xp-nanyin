/*
 * 文件名：MoneyProdValuationDataMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.dto.MoneyProdValuationDataDto;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendong
 * @version 1.0 2018年11月19日
 * @see MoneyProdValuationDataMapper
 * @since 1.0
 */
@Repository
public interface MoneyProdValuationDataMapper {
	List<MoneyProdValuationDataDto> queryMoneyValueData(SendReaderEntity item);
}
