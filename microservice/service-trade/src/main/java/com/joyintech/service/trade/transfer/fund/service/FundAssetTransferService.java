package com.joyintech.service.trade.transfer.fund.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.fund.dao.FundAssetTransferMapper;
import com.joyintech.service.trade.transfer.fund.dto.FundAssetTransfer001Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 基金类交易转仓Service
 * @author liupengfei
 * @version 1.0 2019年11月13日
 * @see FundAssetTransferService
 * @since 1.0
 */
@Service("backFundAssetTransferService")
public class FundAssetTransferService{
    @Autowired
    private FundAssetTransferMapper fundAssetTransferMapper;

    public List<SelectOptionDto> selectPortfolioId(Map<String, Object> param) {
        param.put("capacctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        return fundAssetTransferMapper.selectPortfolioId(param);
    }

    public BigDecimal findInitCarryOverQty(Map<String, Object> param) {
        return fundAssetTransferMapper.findInitCarryOverQty(param);
    }

    public List<FundAssetTransfer001Dto> findUnitCprice(Map<String, Object> param) {
        return fundAssetTransferMapper.findUnitCprice(param);
    }

    public String selProdCode(Map<String, Object> param) {
        return fundAssetTransferMapper.selProdCode(param);
    }

    public BigDecimal selCloPrice(Map<String, Object> param) {
        return fundAssetTransferMapper.selCloPrice(param);
    }

    public int updateRep(Map<String, Object> param) {
        return fundAssetTransferMapper.updateRep(param);
    }
}
