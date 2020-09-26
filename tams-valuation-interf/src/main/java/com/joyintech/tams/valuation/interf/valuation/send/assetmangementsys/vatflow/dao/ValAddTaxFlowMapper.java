/*
 * 文件名：ValAddTaxFlowMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年4月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowDto;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2019年4月23日
 * @see ValAddTaxFlowMapper
 * @since 1.0
 */
@Repository
public interface ValAddTaxFlowMapper {

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param item
     * @return
     * @see
     * @since 1.0
     */
    List<ValAddTaxFlowDto> querryData(SendReaderEntity item);

}
