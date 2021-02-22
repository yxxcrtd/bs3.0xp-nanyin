package com.joyintech.tams.invest.common.asset.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.common.base.entity.MstFundMarketInfo;
import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.invest.common.asset.dao.PriceinfoMapper;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockQuotationInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 行情服务
 * @author wl
 * @version 1.0 2020年4月15日
 * @see PriceinfoController
 * @since 1.0
 */
@Service
public class PriceinfoService extends BaseService {
    /**
     * 行情服务Mapper
     */
    @Autowired
    private PriceinfoMapper priceinfoMapper;

    /**
     * 行情服务查询
     *
     */
    public List<MockQuotationInfoDto> selectPriceinfo(Map<String, Object> map) throws Exception {
        return priceinfoMapper.selectPriceinfo(map);
    }
    /**
     * 汇率行情查询
     *
     */
    public List<MockMstExchargeQuoteDto> selectQuotationofrate(Map<String, Object> map) throws Exception {
        return priceinfoMapper.selectQuotationofrate(map);
    }

    /**
     *
     * @param map
     * @return
     */
    public boolean checkIsNeedAutoPrice(Map<String, Object> map) {
        int count = priceinfoMapper.checkIsNeedAutoPrice(map);
        if(count>0){
            return false;
        }
        return true;
    }

    /**
     * 债券市场行情
     * @param map
     * @return
     */
    public List<MstBondMarketInfo> selectMstBondMarketInfoList(Map<String, Object> map) {
        return priceinfoMapper.selectMstBondMarketInfoList(map);
    }

    /**
     * 债券估值行情
     * @param map
     * @return
     */
    public List<MstBondValuationInfo> selectMstBondValuationInfoList(Map<String, Object> map) {
        return priceinfoMapper.selectMstBondValuationInfoList(map);
    }

    /**
     * 基金估值行情
     * @param map
     * @return
     */
    public List<MstFundMarketInfo> selectMstFundMarketInfoList(Map<String, Object> map) {
        return priceinfoMapper.selectMstFundMarketInfoList(map);
    }

    /**
     * 股票估值行情
     * @param map
     * @return
     */
    public List<MstSharePrice> selectMstSharePriceList(Map<String, Object> map) {
        return priceinfoMapper.selectMstSharePriceList(map);
    }

    /**
     * 基金估值数据
     * @param map
     * @return
     */
    public List<FinProductValuation> selectFinProductValuationList(Map<String, Object> map){
        return priceinfoMapper.selectFinProductValuationList(map);
    }
}
