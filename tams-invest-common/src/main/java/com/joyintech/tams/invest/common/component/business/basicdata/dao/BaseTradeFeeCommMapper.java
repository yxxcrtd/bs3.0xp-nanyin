package com.joyintech.tams.invest.common.component.business.basicdata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.component.business.basicdata.dto.BasicTradeFeeInputDto;
import com.joyintech.tams.invest.common.component.business.basicdata.dto.MstCommissionSetDto;

/**
 * 业务共通组件.交易费用
 * @author 裴家俊
 * @version 1.0 2018年9月21日
 * @see BaseTradeFeeCommMapper
 * @since 1.0
 */
@Repository
public interface BaseTradeFeeCommMapper {

	List<MstCommissionSetDto> selectFeeConfig(BasicTradeFeeInputDto basicTradeFeeInputDto);

}
