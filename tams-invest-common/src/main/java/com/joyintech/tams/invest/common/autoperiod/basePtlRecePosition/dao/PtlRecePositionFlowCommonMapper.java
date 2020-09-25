/*
 * 文件名：AutoPeriodCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dao;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.PtlRecePositionFlow;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dto.PtlRecePositionFlowDto;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 应收款项持仓流水共通Mapper
 *
 * @author zhouxiuping
 * @version 1.0 2019年12月25日
 * @see PtlRecePositionFlowCommonMapper
 * @since 1.0
 */
@Repository
public interface PtlRecePositionFlowCommonMapper {

    //查询区间还本未付利息的可用持仓
    List<PtlRecePositionFlow> selectUnpayPtlRecePosition(Map<String, Object> paramMap);

    //查询区间还本未付利息的可用持仓金额
    BigDecimal unpayTotalIntAmt(Map<String, Object> paramMap);

    //根据交易日查询付息区间
    FinCashPlan findPayInterestStdInfo(Map<String, Object> paramMap);

    //删除可用数据
	int deletePtlSecPositionFlow(PtlRecePositionFlowDto positionFlowDto);

    //获取支付日
   Date getPayDate(PtlRecePositionFlowDto ptlRecePositionFlowDto);

    //获取存续期支付日
    Date getDurationPayDate(PtlRecePositionFlowDto ptlRecePositionFlowDto);


   //通过id获取所有应收款持仓流水
   List<PtlRecePositionFlow> selectAllPosition(String tradeId);
   
   //通过id删除所有符合条件的应收款持仓流水
   int deletePositionByTradeId(String tradeId);

   //根据流水信息更新持仓
   int updateRecePositionAmt(PtlRecePositionFlow ptlSecPositionFlow);

    /**
     * 更新应收款持仓流水的支付日
     *
     * @param param
     * @return
     */
    int updateReceFlowPayDate(Map<String, Object> param);
    /**
     * 根据汇总交易编号查出应收款交易编号
     *
     * @param param
     * @return
     */
    List<String> getTrdProductDealTradeIdByTotelTradeId(Map<String, Object> param);

    /**
     * 查询存续期区间理论未付还本不带息金额
     *
     * @param paramMap
     * @return
     */
    BigDecimal getRepaymentUnpayInterest(Map<String, Object> paramMap);

}
