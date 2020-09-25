package com.joyintech.tams.invest.portfolio.actualtimecapital.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.actualtimecapital.dto.ActualTimeCapital002Dto;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeCapital002Service
 * @author: yanya
 * @create: 2019-10-15
 **/
@Service
public class ActualTimeCapital002Service extends CommonExportService<ActualTimeCapital002Dto> {

//    @Override
//    public void doComplete(ActualTimeCapital002Dto entity, OperationType type) {
//        entity.setOperateType(type.toString());
//        restTemplate.postForObject(
//            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ActualTimeCapital/insertAdjustmentBalance", entity,
//            Integer.class);
//    }

    /**
     * 获取页面初始化现金余额信息
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ActualTimeCapital002Dto getInitInfo(ActualTimeCapital002Dto dto){
        return restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ActualTimeCapital/getCashArtDealInfo", dto,
            ActualTimeCapital002Dto.class);
    }
   
    /**
     * 现金余额调整提交
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public String adjustmentBalance(ActualTimeCapital002Dto dto) {
        restTemplate.postForObject(
          ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ActualTimeCapital/insertAdjustmentBalance", dto, Integer.class);
        return getMessage("ActualTimeAsset002.adjustmentBalance");
    }
}
