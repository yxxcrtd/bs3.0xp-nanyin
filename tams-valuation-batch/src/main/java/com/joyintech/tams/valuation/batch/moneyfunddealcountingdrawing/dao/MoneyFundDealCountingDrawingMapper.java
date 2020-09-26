/*
 * 文件名：MoneyFundDealCountingDrawingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.moneyfunddealcountingdrawing.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComDTO;



/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author weipeng
 * @version 1.0 2018年12月25日
 * @see MoneyFundDealCountingDrawingMapper
 * @since 1.0
 */
public interface MoneyFundDealCountingDrawingMapper {
    /**
     * 估值日非计提数据查询 
     * @param params 参数
     * @return
     * @see
     * @since 1.0
     */
    List<BokBookdetailCurrent> queryValDateNoDrawData(Map<String, Object> params);
    
    /**
     * 查询历史表中最新余额
     * 
     * @param param
     * @return
     */
    List<DrawBaseInfoSelectComDTO> selectDrawBaseInfoFromHis(Map<String, Object> param);
}
