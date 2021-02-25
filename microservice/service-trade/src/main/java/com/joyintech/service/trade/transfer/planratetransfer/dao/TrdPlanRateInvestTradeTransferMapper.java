package com.joyintech.service.trade.transfer.planratetransfer.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.planratetransfer.dto.PlanTransferDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @package com.joyintech.service.report.hxbank.resolute.planratetransfer.dao
 * @author: Xu Zhihao
 * @date: 2019/12/18 10:14
 * @version: V1.0
 * @description:
 */
@Repository
public interface TrdPlanRateInvestTradeTransferMapper {

    /**
     * 查询组合代码
     * @param param
     * @return
     */
    List<SelectOptionDto> selectPortoId(Map<String, Object> param);

    /**
     * 查询利息信息
     * @param param
     * @return
     */
    PlanTransferDto queryAssetInt(Map<String, Object> param);

    String getProdRegistCode(Map<String, Object> map);
}
