/*
 * 文件名：CmpBlockTradeBondService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.business.service;


import java.math.BigDecimal;

import com.joyintech.background.component.order.exchange.database.dao.EntrustComponentMapper;
import com.joyintech.background.component.order.exchange.database.entity.EntrustComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.order.exchange.business.dto.AgreRepFristIssueDto;
import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.constant.LocalConstant;
import com.joyintech.background.component.order.exchange.common.service.EtrSubComponentService;
import com.joyintech.background.component.order.exchange.common.util.EtrComponentUtil;
import com.joyintech.background.component.order.exchange.database.dao.EtrPledgeComponentMapper;
import com.joyintech.background.component.order.exchange.database.entity.EtrPledgeComponent;


/**
 * 协议回购首期-Service
 * 
 * @author lihuadong
 * @version 1.0 2020年7月10日
 * @see AgreRepFristIssueService
 * @since 1.0
 */
@Component
class AgreRepFristIssueService extends EtrSubComponentService<AgreRepFristIssueDto> {

	@Autowired
	private EtrPledgeComponentMapper etrPledgeComponentMapper;

	@Autowired
	private EntrustComponentMapper etrMapper;

    @Override
    public BindingResult entrustCheck(AgreRepFristIssueDto dto, BindingResult result) {
    	//组合代码
    	if(StringUtil.isEmpty(dto.getPortfolioId())){
    		result.addError(error(dto.getClass().getSimpleName(), LocalConstant.PORTFOLIOID,
                    "ETR_COMPONENT_ERR_012","组合代码"
            ));
    	}
    	//资金账号
    	if(StringUtil.isEmpty(dto.getDealCurAcct())){
    		result.addError(error(dto.getClass().getSimpleName(), LocalConstant.ACCTNO,
                    "ETR_COMPONENT_ERR_012","资金账号"
            ));
    	}
    	//交易场所
    	if(StringUtil.isEmpty(dto.getTradeMarket())){
    		result.addError(error(dto.getClass().getSimpleName(), LocalConstant.TRADE_MARKET,
                    "ETR_COMPONENT_ERR_012","交易场所"
            ));
    	}
    	//委托方向
    	if(StringUtil.isEmpty(dto.getPs())){
    		result.addError(error(dto.getClass().getSimpleName(), LocalConstant.PS,
                    "ETR_COMPONENT_ERR_012","委托方向"
            ));
    	}
    	//回购期限
    	if(dto.getTermDays() == null){
    		result.addError(error(dto.getClass().getSimpleName(), LocalConstant.TERM,
                    "ETR_COMPONENT_ERR_012","回购期限"
            ));
    	}
    	//回购期限
    	if(dto.getTermDays() != null && (dto.getTermDays()<1 || dto.getTermDays()>365)){
    		result.addError(error(dto.getClass().getSimpleName(), LocalConstant.TERM,
                    "ETR_COMPONENT_ERR_004","回购期限","1-365天"
            ));
    	}
    	
    	//回购金额
    	if(dto.getEntrustAmt() == null){
    		result.addError(error(dto.getClass().getSimpleName(), LocalConstant.TRADEAMT,
                    "ETR_COMPONENT_ERR_012","回购金额"
            ));
    	}
    	//回购利率
    	if(dto.getYield() == null){
    		result.addError(error(dto.getClass().getSimpleName(), LocalConstant.YIELD,
                    "ETR_COMPONENT_ERR_012","回购利率"
            ));
    	}
    	if(dto.getYield() != null && !EtrComponentUtil.isNumberInRange(dto.getYield(), BigDecimal.ZERO,
    			BigDecimal.valueOf(1), EtrComponentUtil.RANGE_MODE_CL_CL)){
    		result.addError(new FieldError(dto.getClass().getSimpleName(),LocalConstant.YIELD,"回购利率不能超过100%"));

    	}
    	//首期结算日
    	if(dto.getVpayDate() == null){
    		result.addError(error(dto.getClass().getSimpleName(), "fristSettleDate",
                    "ETR_COMPONENT_ERR_012","首期结算日"
            ));
    	}
    	//到期结算日
    	if(dto.getMpayDate() == null){
    		result.addError(error(dto.getClass().getSimpleName(), "expireSettleDate",
                    "ETR_COMPONENT_ERR_012","到期结算日"
            ));
    	}
    	//占款天数
    	if(dto.getCapDays() == null){
    		result.addError(error(dto.getClass().getSimpleName(), "occupyDays",
                    "ETR_COMPONENT_ERR_012","占款天数"
            ));
    	}
    	//回购利息
    	if(dto.getmIntAmt() == null){
    		result.addError(error(dto.getClass().getSimpleName(), "intAmt",
                    "ETR_COMPONENT_ERR_012","回购利息"
            ));
    	}
    	//到期结算金额
    	if(dto.getmTradeAmt() == null){
    		result.addError(error(dto.getClass().getSimpleName(), "expireSettleAmt",
                    "ETR_COMPONENT_ERR_012","到期结算金额"
            ));
    	}
    	//回购方向
    	if(dto.getBusiPs() == null){
    		result.addError(error(dto.getClass().getSimpleName(), "repPs",
                    "ETR_COMPONENT_ERR_012","回购方向"
            ));
    	}
    	//正回购时需要校验质押券，质押数量，折算比例必填
    	if(StringUtil.equals(dto.getBusiPs(),DictCodeList.回购拆借方向_正回购.getValue())) {
			//质押券
			if(StringUtil.isEmpty(dto.getFinprodId())){
				result.addError(new FieldError(dto.getClass().getSimpleName(),"repSecId","正回购方质押券不能为空"));
			}
			//质押数量
			if(dto.getPledgeNumber() == null){
				result.addError(new FieldError(dto.getClass().getSimpleName(),"repSecNum","正回购方质押券数量不能为空"));

			}
			//折算比例
			if(dto.getPledgeRatio() == null){
				result.addError(new FieldError(dto.getClass().getSimpleName(),"conRatio","正回购方折算比例不能为空"));

			}
			if(dto.getPledgeRatio() != null && !EtrComponentUtil.isNumberInRange(dto.getPledgeRatio(), BigDecimal.ZERO,
					BigDecimal.valueOf(1), EtrComponentUtil.RANGE_MODE_CL_CL)){
				result.addError(new FieldError(dto.getClass().getSimpleName(),"conRatio","正回购方折算比例不能超过100%"));

			}
		}


    	return result;
    }
	 /**
     * 执行个别登录
     * 
     * @param dto 指令信息
     */
 	@Override
    public void doInsert(AgreRepFristIssueDto dto) {
 		EtrPledgeComponent etrPledgeEntity = new EtrPledgeComponent();
        BeanUtil.copyPropertiesIgnoreNull(etrPledgeEntity, dto);
        if(StringUtil.isNotEmpty(dto.getFinprodId()))
        etrPledgeComponentMapper.insertSelective(etrPledgeEntity);
    }
 	
 	 /**
     * 执行个别更新
     * 
     * @param dto 指令信息
     */
 	@Override
    public void doUpdate(AgreRepFristIssueDto dto) {
 		EtrPledgeComponent etrPledgeEntity = new EtrPledgeComponent();
		EtrPledgeComponent delEtrPledgeEntity = new EtrPledgeComponent();
        BeanUtil.copyPropertiesIgnoreNull(etrPledgeEntity, dto);
		BeanUtil.copyPropertiesIgnoreNull(delEtrPledgeEntity, dto);
        //查询指令信息
		EntrustComponent entrustComponent = etrMapper.selectByPrimaryKey(dto.getEntrustId());
		//改变了质押券，这里需要先删除再插入，否则无法改变质押券信息
		if(StringUtil.isNotEmpty(dto.getOldFinprodId())) {
			delEtrPledgeEntity.setFinprodId(dto.getOldFinprodId());
			etrPledgeComponentMapper.deleteByPrimaryKey(delEtrPledgeEntity);
		}
        if(StringUtil.isNotEmpty(dto.getFinprodId())) {
			//        etrPledgeComponentMapper.updateByPrimaryKeySelective(etrPledgeEntity);
			etrPledgeComponentMapper.insertSelective(etrPledgeEntity);
		}
    }
 	 /**
     * 执行个别删除
     * 
     * @param dto 指令信息
     */
 	@Override
    public void doDelete(AgreRepFristIssueDto dto) {
 		EtrPledgeComponent etrPledgeEntity = new EtrPledgeComponent();
        BeanUtil.copyPropertiesIgnoreNull(etrPledgeEntity, dto);
        if(StringUtil.isNotEmpty(dto.getFinprodId()))
        etrPledgeComponentMapper.deleteByPrimaryKey(etrPledgeEntity);
    }
}
