package com.joyintech.tams.invest.trade.api;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;
import com.joyintech.tams.invest.common.trade.dao.BaseAssetCashPositionCommMapper;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.trade.exchangetransmatch.position.constant.PositionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

import static com.joyintech.tams.invest.common.position.constant.PositionCommonConstant.*;
import static com.joyintech.tams.invest.common.position.constant.PositionCommonConstant.INTERF_TYPE_STOCK001;

@Service
public class PositionRequestTradeRestService {
    private static final Logger logger = LoggerFactory.getLogger(PositionRequestTradeRestService.class);

    @Autowired
    private CommonPositionEngineService commonPositionEngineService;

    @Autowired
    private BaseAssetCashPositionCommMapper baseAssetCashPositionCommMapper;

    /**
     * 发送交易Rest头寸请求
     * @param finProductTradeDto
     */
    public void sendTradeRestPositionRequest(FinProductTradeDto finProductTradeDto) {
        logger.debug("【发送交易Rest头寸请求】entity: {}", JSON.toJSONString(finProductTradeDto));
        if(CollectionUtils.isEmpty(finProductTradeDto.getBaseTradeProductDealDtos())){
            return;
        }
        if(DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())){
            this.revoke(finProductTradeDto);
        }else if(DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())){
            Map<String, Object> resultMap = new HashMap<>();
            for (BaseTradeProductDealDto entity:finProductTradeDto.getBaseTradeProductDealDtos()) {
                TrdFinProductDealTotal dealTotal = entity.getTrdFinProductDealTotal();
                if(null != dealTotal && DictCodeList.共通业务类型_债券类资产存续期.getValue().equals(dealTotal.getBusiType())){

                }else if(null != dealTotal && DictCodeList.共通业务类型_质押式回购存续期.getValue().equals(dealTotal.getBusiType())){

                }
            }
            PositionHttpRequest.send(resultMap);
        }

    }

    /**
     * 撤销新头寸接口
     * @param finProductTradeDto
     */
    private void revoke(FinProductTradeDto finProductTradeDto){
        for (BaseTradeProductDealDto entity:finProductTradeDto.getBaseTradeProductDealDtos()) {
            TrdProductDeal deal = entity.getTrdProductDeal();
            // 资产二级类型
            String assetType2 = StringUtil.isNotEmpty(finProductTradeDto.getAssetType2()) ?
                    finProductTradeDto.getAssetType2() : baseAssetCashPositionCommMapper.selectFundType(deal.getFinprodId());
            String busiType = commonPositionEngineService.getPositionBusiType(deal.getFinprodType2(),
                    deal.getPs(), assetType2);
            PositionHttpRequest.dorevoke(deal.getTradeId(), PositionConstant.POSITION_TYPE_TRD, busiType, deal.getPs());
        }
    }
}
