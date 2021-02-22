package com.joyintech.tams.invest.common.asset.dao;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.common.base.entity.MstFundMarketInfo;
import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockQuotationInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 行情服务
 * @author wl
 * @version 1.0 2020年4月15日
 * @see PriceinfoController
 * @since 1.0
 */
@Repository
public interface PriceinfoMapper {
	
    List<MockQuotationInfoDto> selectPriceinfo(Map<String, Object> map);

    List<MockMstExchargeQuoteDto> selectQuotationofrate(Map<String, Object> map);

    int checkIsNeedAutoPrice(Map<String, Object> map);

    List<MstBondMarketInfo> selectMstBondMarketInfoList(Map<String, Object> map);

    List<MstBondValuationInfo> selectMstBondValuationInfoList(Map<String, Object> map);

    List<MstFundMarketInfo> selectMstFundMarketInfoList(Map<String, Object> map);

    List<MstSharePrice> selectMstSharePriceList(Map<String, Object> map);

    List<FinProductValuation> selectFinProductValuationList(Map<String, Object> map);
}
