package com.joyintech.tams.invest.common.component.business.basicdata;

import java.util.List;

import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.invest.common.component.business.basicdata.dto.BasicTradeFeeInputDto;

/**
 * 业务共通组件.交易费用组件
 * @author 裴家俊
 */
public interface BaseTradeFeeCommService {

    /**
     * 银行间交易费用详细
     */
    List<TrdTradeFeeDetail> innerBankFeeDeal(BasicTradeFeeInputDto inputDto);

    /**
     * 交易所佣金费用详细
     */
    List<TrdTradeFeeDetail> bourseFeeDeal(BasicTradeFeeInputDto inputDto);
    
}
