package com.joyintech.interf.bankname.sale.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.interf.bankname.sale.dao.SaleInterfMapper;
import com.joyintech.interf.bankname.sale.dto.FinProductPayIntShowDto;
import com.joyintech.interf.bankname.sale.dto.InfProdCommonDto;
import com.joyintech.interf.bankname.sale.dto.SaleAlloDto;
import com.joyintech.interf.bankname.sale.dto.SaleBonusCollectDto;
import com.joyintech.interf.bankname.sale.dto.SaleAddReduceDto;
import com.joyintech.interf.bankname.sale.dto.SaleRedeemDto;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt003Dto;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce002Dto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablish002Dto;
import com.joyintech.tams.invest.product.productduration.expire.dto.FinProductExpire002Dto;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationDto;

/**
 * 
 * 销售接口数据转换业务数据
 * 〈功能详细描述〉
 * @author 吴鼎
 * @version 1.0 2019年7月7日
 * @see SaleToBusinessDtoService
 * @since 1.0
 */
@Service
public class SaleToBusinessDtoService {
	
	
	@Autowired
	private SaleInterfMapper saleSystemInterfMapper;
	
	@Autowired(required=false)
    private BaseGetBusinessId baseGetBusinessId;


	public FinProductExpire002Dto saleRedeemToBusinessDto(SaleRedeemDto saleRedeemDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public FinNetProductDurationDto netSaleToBusinessDto(FinNetProductDurationDto model) {
		
		return model;
	}



	public FinProductAddReduce002Dto saleRedeemToAddReduceDto(SaleRedeemDto saleRedeemDto) {
		// TODO Auto-generated method stub
		return null;
	}



	public FinProductPayInt003Dto salePayIntToAddReduceDto(SaleBonusCollectDto saleBonusCollectDto) {
		// TODO Auto-generated method stub
		return null;
	}



	public FinProductEstablish002Dto rebuildProductModelAttribute(SaleAlloDto saleAlloDto) {
		
		FinProductEstablish002Dto model = new FinProductEstablish002Dto();
		
		InfProdCommonDto infProdCommonDto= saleSystemInterfMapper.getFinprodMarketIdList(saleAlloDto.getFinprodCode()).get(0);
		
		model = saleSystemInterfMapper.queryBusinessDataByFinProduct(saleAlloDto.getFinprodCode());
		
		if ("0".equals(saleAlloDto.getPs())) {
			model.setPs("C2");
		}else if ("2".equals(saleAlloDto.getPs())){
			model.setPs("C1");
		}
		
		model.setVdate(saleAlloDto.getTradedate());
		model.setVdate2(saleAlloDto.getTradedate());
		model.setAccountingAmt(saleAlloDto.getTradeamt());
		model.setCurrentSub(saleAlloDto.getTradeamt());
		model.setTradeAmt(saleAlloDto.getTradeamt());
		model.setTradeDate(saleAlloDto.getTradedate());
		//计息基础
		model.setBasis(infProdCommonDto.getBasis());
		//产品到期日
		model.setMdate(infProdCommonDto.getmDate());
		model.setPtlId(infProdCommonDto.getPortfolioId());
		model.setIsLay(infProdCommonDto.getIsCycle());
		model.setCcy(infProdCommonDto.getCcy());
		
		model.setFinprodAbbr(infProdCommonDto.getFinprodAbbr());
		model.setFinprodName(infProdCommonDto.getFinprodName());
		model.setCreateUser(infProdCommonDto.getProdmanager());
		
		model.setDealMode("01");
		model.setElementId("FinProductEstablish002_btn_save");
		model.setOperateType("save");
		model.setOperationType("02");
		model.setRemark("销售推送数据");
		if (StringUtil.isEmpty(model.getTradeId())) {
	    	BussinessCodeParameter codeParameter = new BussinessCodeParameter();
	    	codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品认申购管理);
	        String busiCode = baseGetBusinessId.buildBussinessCode(codeParameter);
	        model.setTradeId(busiCode);
	    }
		
		return model;
	}



	public FinNetProductDurationDto dealModelAttributeAllo(SaleAlloDto saleAlloDto) {

		FinNetProductDurationDto finNetProductDurationDto = new FinNetProductDurationDto();
		
		InfProdCommonDto infProdCommonDto= saleSystemInterfMapper.getFinprodMarketIdList(saleAlloDto.getFinprodCode()).get(0);
		BigDecimal netValue = saleSystemInterfMapper.queryNetValue(saleAlloDto.getTradedate(), infProdCommonDto.getFinprodId());
		
		//03货币型  反之净值型
		if ("0".equals(saleAlloDto.getPs())) {
			finNetProductDurationDto.setDealType("C2");
			if(DictCodeList.收益类型_净值型.getValue().equals(saleAlloDto.getProfitType())){
				finNetProductDurationDto.setPurDealAmt(saleAlloDto.getTradeamt());
				finNetProductDurationDto.setPurDealNum(saleAlloDto.getShareamt());
				finNetProductDurationDto.setPurConfirmDate(saleAlloDto.getTradedate());
				finNetProductDurationDto.setPurNetValueDate(saleAlloDto.getCpricedate());
				finNetProductDurationDto.setPurUnitNetValue(netValue ==null?new BigDecimal(1):netValue);
				finNetProductDurationDto.setPurRemark("销售推送数据");
			}else if(DictCodeList.收益类型_货币型.getValue().equals(saleAlloDto.getProfitType())){
				finNetProductDurationDto.setPurDealAmt03(saleAlloDto.getTradeamt());
				finNetProductDurationDto.setPurConfirmDate03(saleAlloDto.getTradedate());
				finNetProductDurationDto.setPurRemark03("销售推送数据");
			}
			
		}else if ("2".equals(saleAlloDto.getPs())){
			finNetProductDurationDto.setDealType("C1");
			if(DictCodeList.收益类型_净值型.getValue().equals(saleAlloDto.getProfitType())){
				finNetProductDurationDto.setSubDealAmt(saleAlloDto.getTradeamt());
				finNetProductDurationDto.setSubDealNum(saleAlloDto.getShareamt());
				finNetProductDurationDto.setSubConfirmDate(saleAlloDto.getTradedate());
				finNetProductDurationDto.setSubRemark("销售推送数据");
			}else if(DictCodeList.收益类型_货币型.getValue().equals(saleAlloDto.getProfitType())){
				finNetProductDurationDto.setSubDealAmt03(saleAlloDto.getTradeamt());
				finNetProductDurationDto.setSubConfirmDate03(saleAlloDto.getTradedate());
				finNetProductDurationDto.setSubRemark03("销售推送数据");
			}
		}
		
		finNetProductDurationDto.setFinprodMarketId(saleAlloDto.getFinprodCode());
		finNetProductDurationDto.setFinprodId(infProdCommonDto.getFinprodId());
		finNetProductDurationDto.setPortfolioId(infProdCommonDto.getPortfolioId());
		finNetProductDurationDto.setIsCycle(infProdCommonDto.getIsCycle());
		finNetProductDurationDto.setProfitType(infProdCommonDto.getProfitType());
		finNetProductDurationDto.setCcy(infProdCommonDto.getCcy());
		finNetProductDurationDto.setCalendarId(infProdCommonDto.getCalendarId());
		finNetProductDurationDto.setvDate(infProdCommonDto.getvDate());
		finNetProductDurationDto.setmDate(infProdCommonDto.getmDate());
		finNetProductDurationDto.setProdAbbr(infProdCommonDto.getFinprodAbbr());
		finNetProductDurationDto.setProdName(infProdCommonDto.getFinprodName());
		finNetProductDurationDto.setCreateUser(infProdCommonDto.getProdmanager());//产品经理
		finNetProductDurationDto.setOperateType("save");
		finNetProductDurationDto.setOperationType("02");
		finNetProductDurationDto.setPageModel("2");//画面模式
		DictCodeList dictCodeList = this.getBussinessDictCodeList(finNetProductDurationDto.getDealType());
	    if (StringUtil.isEmpty(finNetProductDurationDto.getTradeId())) {
	        BussinessCodeParameter parameter = new BussinessCodeParameter();
	        parameter.setCommonBusinessType(dictCodeList);
	        String busiCode = baseGetBusinessId.buildBussinessCode(parameter);
	        finNetProductDurationDto.setTradeId(busiCode);
	    }
	    return finNetProductDurationDto ;
		
	}

	
	public DictCodeList getBussinessDictCodeList(String dealtype) {
		DictCodeList bussinessDictCodeList = DictCodeList.共通业务类型_收益型产品认申购管理;
        if(DictCodeList.交易方向_产品认购.getValue().equals(dealtype)){
        	bussinessDictCodeList = DictCodeList.共通业务类型_收益型产品认申购管理;
        }else if(DictCodeList.交易方向_产品申购.getValue().equals(dealtype)){
        	bussinessDictCodeList = DictCodeList.共通业务类型_产品申购;
        }else if(DictCodeList.交易方向_产品赎回.getValue().equals(dealtype)
                || DictCodeList.交易方向_产品提前赎回.getValue().equals(dealtype)
                || DictCodeList.交易方向_产品违约赎回.getValue().equals(dealtype)){
        	bussinessDictCodeList = DictCodeList.共通业务类型_产品赎回;
        }else if(DictCodeList.交易方向_产品到期.getValue().equals(dealtype)
                || DictCodeList.交易方向_提前终止.getValue().equals(dealtype)){
        	bussinessDictCodeList = DictCodeList.共通业务类型_收益型产品赎回到期管理;
        }else if(DictCodeList.交易方向_份额强增.getValue().equals(dealtype) ||
        		DictCodeList.交易方向_份额强减.getValue().equals(dealtype)){
        	bussinessDictCodeList = DictCodeList.共通业务类型_收益型产品提前赎回管理;
        }else if(DictCodeList.交易方向_产品现金分红.getValue().equals(dealtype)){
        	bussinessDictCodeList = DictCodeList.共通业务类型_收益型产品中间付息管理;
        }else if(DictCodeList.交易方向_产品红利转投.getValue().equals(dealtype)){
        	bussinessDictCodeList = DictCodeList.共通业务类型_收益型产品中间付息管理;
        }          
        return bussinessDictCodeList;
	}



	public FinProductExpire002Dto rebuildProductInfo(SaleRedeemDto saleRedeemDto) {
	
		FinProductExpire002Dto model = new FinProductExpire002Dto();
		
		FinProductEstablish002Dto infoModel = new FinProductEstablish002Dto();
		
		infoModel = saleSystemInterfMapper.queryBusinessDataByFinProduct(saleRedeemDto.getFinprodCode());
		
		model.setMdate(saleRedeemDto.getTradedate());
		model.setPayDate(saleRedeemDto.getSubscribepaydate());
		model.setFinprodId(infoModel.getFinprodId());
		model.setFinprodMarketId(saleRedeemDto.getFinprodCode());
		if("0".equals(saleRedeemDto.getPs())){
			model.setDealType("C4");
			model.setPs(DictCodeList.共通业务类型_产品赎回.getValue());
		}else if("4".equals(saleRedeemDto.getPs())){
			model.setDealType("C5");
			model.setPs(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue());
		}
		model.setmCusRevAmt(saleRedeemDto.getShareamt());
		model.setReduceAmt(saleRedeemDto.getShareamt());
		
		String finprodId = infoModel.getFinprodId();//产品代码
		Date mdate =saleRedeemDto.getTradedate();//到期日
		
		model.setElementId("FinProductExpire002_btn_save");//页面元素
		model.setFinprodAbbr(infoModel.getFinprodAbbr());//金融产品简称
		model.setFinprodName(infoModel.getFinprodName());//金融产品名称
		model.setVdate(infoModel.getVdate());//起息日
		model.setOperateType("save");//操作类型
		model.setRemark("销售推送数据");// 备注
		model.setCcy(infoModel.getCcy());//币种
		model.setPtlId(infoModel.getPortfolioId());// 组合代码
		model.setCreateUser(infoModel.getCreateUser());//产品经理
		Map<String, Object> params = new HashMap<>();
		params.put("finprodId", finprodId);// 产品代码
		params.put("tradeDate", mdate);// 赎回日
		params.put("C7", "C7");// 交易方向
		// 查询
		List<ReduceInfoDto> reduceInfoList = saleSystemInterfMapper.findReduceInfoData(params);
		if (reduceInfoList.size() > 0) {
			for (ReduceInfoDto reduceInfo : reduceInfoList) {
				BigDecimal  interest = reduceInfo.getInterest() == null ? BigDecimal.ZERO : reduceInfo.getInterest();
				model.setCusProfit(interest);// 应付客户收益
				model.setActCusProfit(interest);// 实付客户收益
				//reduceInfoList.get(0).setActInterest(reduceInfofinProductPayInt003Dto.getRemainPrinAmt());
			}
			model.setFinProductExpireMInfoDtoList(reduceInfoList);
		}
		if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            if(model.getPs().equals(DictCodeList.共通业务类型_产品赎回.getValue())){
            	codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品赎回);
            	model.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }else if(model.getPs().equals(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue())){
            	codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品赎回到期管理);
            	model.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
            }
        }
		
		return model;
	}



	public FinNetProductDurationDto dealModelAttributeAddReduce(SaleAddReduceDto saleAddReduceDto) {
		
		FinNetProductDurationDto finNetProductDurationDto = new FinNetProductDurationDto();
		
		if ("0".equals(saleAddReduceDto.getPs())) {
			finNetProductDurationDto.setDealType("C3");
		}else if ("1".equals(saleAddReduceDto.getPs())){
			finNetProductDurationDto.setDealType("C6");
		}
		
		finNetProductDurationDto.setFinprodMarketId(saleAddReduceDto.getFinprodCode());
		finNetProductDurationDto.setSubConfirmDate(saleAddReduceDto.getTradedate());
		finNetProductDurationDto.setPurDealAmt(saleAddReduceDto.getTradeamt());
		finNetProductDurationDto.setPurDealNum(saleAddReduceDto.getShareamt());
//		finNetProductDurationDto.setPurUnitNetValue(saleAddReduceDto.getValuation());
		finNetProductDurationDto.setPurNetValueDate(saleAddReduceDto.getCpricedate());
		
		InfProdCommonDto infProdCommonDto= saleSystemInterfMapper.getFinprodMarketIdList(saleAddReduceDto.getFinprodCode()).get(0);
		
		BigDecimal netValue = saleSystemInterfMapper.queryNetValue(saleAddReduceDto.getTradedate(), infProdCommonDto.getFinprodId());
		finNetProductDurationDto.setPurUnitNetValue(netValue ==null?new BigDecimal(1):netValue);
		
		finNetProductDurationDto.setFinprodId(infProdCommonDto.getFinprodId());
		finNetProductDurationDto.setFinprodMarketId(infProdCommonDto.getFinprodMarketId());
		finNetProductDurationDto.setPortfolioId(infProdCommonDto.getPortfolioId());
		finNetProductDurationDto.setIsCycle(infProdCommonDto.getIsCycle());
		finNetProductDurationDto.setProfitType(infProdCommonDto.getProfitType());
		finNetProductDurationDto.setCcy(infProdCommonDto.getCcy());
		finNetProductDurationDto.setCalendarId(infProdCommonDto.getCalendarId());
		finNetProductDurationDto.setvDate(infProdCommonDto.getvDate());
		finNetProductDurationDto.setmDate(infProdCommonDto.getmDate());
		finNetProductDurationDto.setProdAbbr(infProdCommonDto.getFinprodAbbr());
		finNetProductDurationDto.setProdName(infProdCommonDto.getFinprodName());
		finNetProductDurationDto.setmDate(infProdCommonDto.getmDate());
		finNetProductDurationDto.setCreateUser(infProdCommonDto.getProdmanager());//产品经理
		finNetProductDurationDto.setOperateType("save");
		finNetProductDurationDto.setOperationType("02");
		DictCodeList dictCodeList = this.getBussinessDictCodeList(finNetProductDurationDto.getDealType());
	    if (StringUtil.isEmpty(finNetProductDurationDto.getTradeId())) {
	        BussinessCodeParameter parameter = new BussinessCodeParameter();
	        parameter.setCommonBusinessType(dictCodeList);
	        String busiCode = baseGetBusinessId.buildBussinessCode(parameter);
	        finNetProductDurationDto.setTradeId(busiCode);
	    }
	    return finNetProductDurationDto ;
	}


	public FinNetProductDurationDto dealModelAttributeRedeem(SaleRedeemDto saleRedeemDto) {

		FinNetProductDurationDto finNetProductDurationDto = new FinNetProductDurationDto();
		
		finNetProductDurationDto.setFinprodMarketId(saleRedeemDto.getFinprodCode());
		InfProdCommonDto infProdCommonDto= saleSystemInterfMapper.getFinprodMarketIdList(saleRedeemDto.getFinprodCode()).get(0);
		
		BigDecimal netValue = saleSystemInterfMapper.queryNetValue(saleRedeemDto.getTradedate(), infProdCommonDto.getFinprodId());
		
		//03货币 反之净值
		if ("0".equals(saleRedeemDto.getPs())) {
			finNetProductDurationDto.setDealType("C4");
			if(DictCodeList.收益类型_净值型.getValue().equals(saleRedeemDto.getProfitType())){
				finNetProductDurationDto.setRedUnitNetValue(netValue ==null?new BigDecimal(1):netValue);
				finNetProductDurationDto.setRedConfirmDate(saleRedeemDto.getTradedate());
				finNetProductDurationDto.setRedPayDate(saleRedeemDto.getPaydate());
				finNetProductDurationDto.setRedNetValueDate(saleRedeemDto.getCpricedate());
				finNetProductDurationDto.setRedDealAmt(saleRedeemDto.getTradeamt());
				finNetProductDurationDto.setRedDealNum(saleRedeemDto.getShareamt());
				finNetProductDurationDto.setRedFeeLc(saleRedeemDto.getBackfeeprod());
				finNetProductDurationDto.setRedTotalNetValue(saleRedeemDto.getNetamt());
				finNetProductDurationDto.setRedFee(saleRedeemDto.getBackfee());
				finNetProductDurationDto.setRedPurFeeHd(saleRedeemDto.getSgfee());
				finNetProductDurationDto.setRedProfit(saleRedeemDto.getRedprofit());
				finNetProductDurationDto.setRedCost(saleRedeemDto.getRedcost());
				finNetProductDurationDto.setRedRemark("销售推送数据");
			} else if(DictCodeList.收益类型_货币型.getValue().equals(saleRedeemDto.getProfitType())){
				finNetProductDurationDto.setRedConfirmDate03(saleRedeemDto.getTradedate());
				finNetProductDurationDto.setRedPrinPayDate03(saleRedeemDto.getPaydate());
				finNetProductDurationDto.setRedProfitPayDate03(saleRedeemDto.getProfitpaydate());
				finNetProductDurationDto.setRedPrinAmt03(saleRedeemDto.getShareamt());
				finNetProductDurationDto.setRedProfit03(saleRedeemDto.getRedprofit());
				finNetProductDurationDto.setRedDealAmt03(saleRedeemDto.getTradeamt());
				finNetProductDurationDto.setRedFee03(saleRedeemDto.getBackfee());
				finNetProductDurationDto.setRedFeeLc03(saleRedeemDto.getBackfeeprod());
				finNetProductDurationDto.setRedTotalNetValue03(saleRedeemDto.getNetamt());
				finNetProductDurationDto.setRedPurFeeHd03(saleRedeemDto.getSgfee());
				finNetProductDurationDto.setRedRemark03("销售推送数据");
			}
		}else if ("4".equals(saleRedeemDto.getPs())){
			finNetProductDurationDto.setDealType("C5");
			if(DictCodeList.收益类型_净值型.getValue().equals(saleRedeemDto.getProfitType())){
				finNetProductDurationDto.setExpireUnitNetValue(netValue ==null?new BigDecimal(1):netValue);
				finNetProductDurationDto.setExpireConfirmDate(saleRedeemDto.getTradedate());
				finNetProductDurationDto.setExpirePayDate(saleRedeemDto.getPaydate());
				finNetProductDurationDto.setExpireNetValueDate(saleRedeemDto.getCpricedate());
				finNetProductDurationDto.setExpireDealAmt(saleRedeemDto.getTradeamt());
				finNetProductDurationDto.setExpireDealNum(saleRedeemDto.getShareamt());
				finNetProductDurationDto.setExpireProfit(saleRedeemDto.getRedprofit());
				finNetProductDurationDto.setExpireCost(saleRedeemDto.getRedcost());
				finNetProductDurationDto.setExpireUnitNetValue(saleRedeemDto.getValuation());
				finNetProductDurationDto.setExpireRemark("销售推送数据");
			}else if(DictCodeList.收益类型_货币型.getValue().equals(saleRedeemDto.getProfitType())){
				finNetProductDurationDto.setExpireConfirmDate03(saleRedeemDto.getTradedate());
				finNetProductDurationDto.setExpirePrinPayDate03(saleRedeemDto.getPaydate());
				finNetProductDurationDto.setExpireProfitPayDate03(saleRedeemDto.getProfitpaydate());
				finNetProductDurationDto.setExpirePrinAmt03(saleRedeemDto.getShareamt());
				finNetProductDurationDto.setExpireProfit03(saleRedeemDto.getRedprofit());
				finNetProductDurationDto.setExpireDealAmt03(saleRedeemDto.getTradeamt());
				finNetProductDurationDto.setExpireRemark03("销售推送数据");
			}
		}
		
		finNetProductDurationDto.setFinprodMarketId(saleRedeemDto.getFinprodCode());
		
		finNetProductDurationDto.setRedUnitNetValue(netValue ==null?new BigDecimal(1):netValue);
		
		finNetProductDurationDto.setFinprodId(infProdCommonDto.getFinprodId());
		finNetProductDurationDto.setFinprodMarketId(infProdCommonDto.getFinprodMarketId());
		finNetProductDurationDto.setPortfolioId(infProdCommonDto.getPortfolioId());
		finNetProductDurationDto.setIsCycle(infProdCommonDto.getIsCycle());
		finNetProductDurationDto.setProfitType(infProdCommonDto.getProfitType());
		finNetProductDurationDto.setCcy(infProdCommonDto.getCcy());
		finNetProductDurationDto.setCalendarId(infProdCommonDto.getCalendarId());
		finNetProductDurationDto.setvDate(infProdCommonDto.getvDate());
		finNetProductDurationDto.setmDate(infProdCommonDto.getmDate());
		finNetProductDurationDto.setProdAbbr(infProdCommonDto.getFinprodAbbr());
		finNetProductDurationDto.setProdName(infProdCommonDto.getFinprodName());
		finNetProductDurationDto.setCreateUser(infProdCommonDto.getProdmanager());//产品经理
		finNetProductDurationDto.setOperateType("save");
		finNetProductDurationDto.setOperationType("02");
		finNetProductDurationDto.setPageModel("2");//画面模式
		DictCodeList dictCodeList = this.getBussinessDictCodeList(finNetProductDurationDto.getDealType());
	    if (StringUtil.isEmpty(finNetProductDurationDto.getTradeId())) {
	        BussinessCodeParameter parameter = new BussinessCodeParameter();
	        parameter.setCommonBusinessType(dictCodeList);
	        String busiCode = baseGetBusinessId.buildBussinessCode(parameter);
	        finNetProductDurationDto.setTradeId(busiCode);
	    }
	    return finNetProductDurationDto ;
		
	
	}



	public FinProductPayInt003Dto rebuildProductPayIntInfo(SaleBonusCollectDto saleBonusCollectDto) {

		FinProductPayInt003Dto model = new FinProductPayInt003Dto();

		FinProductEstablish002Dto infoModel = new FinProductEstablish002Dto();
		
		infoModel = saleSystemInterfMapper.queryBusinessDataByFinProduct(saleBonusCollectDto.getFinprodCode());
		
		model.setPayDate(saleBonusCollectDto.getPaydate());
		model.setFinprodId(saleBonusCollectDto.getFinprodCode());
		model.setActCusProfit(saleBonusCollectDto.getInterest());
		model.setPayintEndDate(saleBonusCollectDto.getEnddate());
		model.setThisIntAccrDay(0);
		model.setPayintStartDate(infoModel.getVdate());
		
		String finprodId = infoModel.getFinprodId();// 产品代码
		Date payintEndDate = saleBonusCollectDto.getEnddate();//支付到期日
		model.setFinprodId(infoModel.getFinprodId());// 产品代码
		model.setFinprodName(infoModel.getFinprodName());// 产品名称
		model.setElementId("FinProductPayInt003_btn_save");// 页面元素
		model.setOperateType("save");// 操作类型
		model.setStatus("N");// 状态
		model.setTradeRemark("销售推送数据");// 备注
		model.setPageModel("2");// 画面模式
		model.setCcy(infoModel.getCcy());// 币种
		model.setDealMode(infoModel.getDealMode());// 处理模式
		model.setFinprodAbbr(infoModel.getFinprodAbbr());// 产品简称
		model.setPortfolioId(infoModel.getPortfolioId());// 组合代码
		model.setMdate(infoModel.getMdate());// 产品到期日
		model.setVdate(infoModel.getVdate());
		model.setProfitType(saleBonusCollectDto.getProfitType());// 收益类型
		model.setCreateUser(infoModel.getCreateUser());//产品经理
		Map<String, Object> params = new HashMap<>();
		params.put("finprodId", finprodId);// 产品代码
		params.put("tradeDate", payintEndDate);// 付息结束日
		params.put("C7", "C7");// 交易方向
		// 查询
		List<ReduceInfoDto> reduceInfoList = saleSystemInterfMapper.findReduceInfoData(params);
		if (reduceInfoList.size() > 0) {
			for (ReduceInfoDto reduceInfofinProductPayInt003Dto : reduceInfoList) {
				model.setPrinAmt(reduceInfofinProductPayInt003Dto.getRemainPrinAmt());// 本金金额
				model.setTheoryCusProfit(reduceInfofinProductPayInt003Dto.getInterest());// 应付客户收益
				model.setActCusProfit(reduceInfofinProductPayInt003Dto.getInterest());
				//reduceInfoList.get(0).setActInterest(reduceInfofinProductPayInt003Dto.getRemainPrinAmt());
			}
			model.setIntDetailDataList(reduceInfoList);
		}
		// 查询对应现金流计划表
		FinProductPayIntShowDto finProductPayIntShowDto = new FinProductPayIntShowDto();
		finProductPayIntShowDto.setFinprodId(finprodId);// 产品代码
		finProductPayIntShowDto.setPayintEndDate(payintEndDate);// 付息结束日
		finProductPayIntShowDto = saleSystemInterfMapper.queryCashInfo(finProductPayIntShowDto);
		if (finProductPayIntShowDto != null) {
			model.setPayintStartDate(infoModel.getVdate());// 产品起息日
		}
		return model;
	}



	public FinNetProductBonusSchemeDto rebuildProductBonusCashDetail(SaleBonusCollectDto saleBonusCollectDto) {
		
		FinNetProductBonusSchemeDto finNetProductBonusSchemeDto = new FinNetProductBonusSchemeDto();
		
		String finprodId = "F16_" + saleBonusCollectDto.getFinprodCode().trim();
		Map<String,Object> map = new HashMap<String,Object> ();
		map.put("BUSI_TYPE", "17");
        map.put("FINPROD_TYPE_TG_F16", "F16");
        map.put("PROFIT_TYPE_01","01");
        map.put("dealdate",saleBonusCollectDto.getDeldate());
        map.put("finprodid",finprodId);
        finNetProductBonusSchemeDto= saleSystemInterfMapper.selectInfo(map);
		
		finNetProductBonusSchemeDto.setFinprodId(finprodId);
		finNetProductBonusSchemeDto.setFinprodMarketId(saleBonusCollectDto.getFinprodCode());
		finNetProductBonusSchemeDto.setFinprodAbbr(finNetProductBonusSchemeDto.getFinprodAbbr());
		finNetProductBonusSchemeDto.setFinprodName(finNetProductBonusSchemeDto.getFinprodName());
		finNetProductBonusSchemeDto.setTradeId(finNetProductBonusSchemeDto.getBonusPlanId());
		finNetProductBonusSchemeDto.setActBonusDate(saleBonusCollectDto.getBonusdate());		
		finNetProductBonusSchemeDto.setBonusCashAmt(saleBonusCollectDto.getBonusamt());
		finNetProductBonusSchemeDto.setBonusShareAmt(saleBonusCollectDto.getRebonusamt());
		finNetProductBonusSchemeDto.setBonusShareNum(saleBonusCollectDto.getRedealqty());
		finNetProductBonusSchemeDto.setCcy(finNetProductBonusSchemeDto.getCcy());
		finNetProductBonusSchemeDto.setBonuScalcFlag(finNetProductBonusSchemeDto.getBonuScalcFlag());
		finNetProductBonusSchemeDto.setBonusPlanId(finNetProductBonusSchemeDto.getBonusPlanId());
		finNetProductBonusSchemeDto.setBaseDate(saleBonusCollectDto.getBonusdate());
		finNetProductBonusSchemeDto.setTdyUnpaidIntincexp(finNetProductBonusSchemeDto.getTdyUnpaidIntincexp());
		finNetProductBonusSchemeDto.setDelDate(finNetProductBonusSchemeDto.getDelDate());
		finNetProductBonusSchemeDto.setRegDate(finNetProductBonusSchemeDto.getRegDate());
		finNetProductBonusSchemeDto.setIsNetMormalize(finNetProductBonusSchemeDto.getIsNetMormalize());
		finNetProductBonusSchemeDto.setVluRefDate(finNetProductBonusSchemeDto.getVluRefDate());
		finNetProductBonusSchemeDto.setTdyUnitValue(finNetProductBonusSchemeDto.getTdyUnitValue());
		finNetProductBonusSchemeDto.setTdyShareAmt(finNetProductBonusSchemeDto.getTdyShareAmt());
		finNetProductBonusSchemeDto.setBonusDate(finNetProductBonusSchemeDto.getBonusDate());
		finNetProductBonusSchemeDto.setUnitBonusVlu(finNetProductBonusSchemeDto.getUnitBonusVlu());
		finNetProductBonusSchemeDto.setTotalAmt(saleBonusCollectDto.getBonusamt());
		finNetProductBonusSchemeDto.setNetvalue(finNetProductBonusSchemeDto.getNetvalue());
		finNetProductBonusSchemeDto.setActTotalAmt(saleBonusCollectDto.getBonusamt());
		finNetProductBonusSchemeDto.setActUnitBonusVlu(finNetProductBonusSchemeDto.getUnitBonusVlu());
		finNetProductBonusSchemeDto.setRegDateNum(finNetProductBonusSchemeDto.getRegDateNum());
		finNetProductBonusSchemeDto.setBonusDealRemark("销售推送数据");
		
		InfProdCommonDto infProdCommonDto= saleSystemInterfMapper.getFinprodMarketIdList(saleBonusCollectDto.getFinprodCode()).get(0);
		finNetProductBonusSchemeDto.setPortfolioId(infProdCommonDto.getPortfolioId());
		finNetProductBonusSchemeDto.setCreateUser(infProdCommonDto.getProdmanager());//产品经理
		finNetProductBonusSchemeDto.setElementId("FinNetProductBonusScheme004_btn_save");//页面元素
		finNetProductBonusSchemeDto.setOperateType("save");//操作类型
		finNetProductBonusSchemeDto.setDealType("bonusCash");//处理方式
		
		finNetProductBonusSchemeDto.setRemark("销售推送数据");//备注
		finNetProductBonusSchemeDto.setPageModel("2");
		
		return finNetProductBonusSchemeDto;
	}



	public FinNetProductBonusSchemeDto rebuildProductBonusShareDetail(SaleBonusCollectDto saleBonusCollectDto) {
		
		FinNetProductBonusSchemeDto finNetProductBonusSchemeDto = new FinNetProductBonusSchemeDto();
		
		String finprodId = "F16_" + saleBonusCollectDto.getFinprodCode().trim();
		Map<String,Object> map = new HashMap<String,Object> ();
		map.put("BUSI_TYPE", "17");
        map.put("FINPROD_TYPE_TG_F16", "F16");
        map.put("PROFIT_TYPE_01","01");
        map.put("dealdate",saleBonusCollectDto.getDeldate());
        map.put("finprodid", finprodId);
        finNetProductBonusSchemeDto= saleSystemInterfMapper.selectInfo(map);
//        BigDecimal netValue = saleSystemInterfMapper.queryNetValue(saleBonusCollectDto.getBonusdate(), finprodId);
		
		finNetProductBonusSchemeDto.setFinprodId(finprodId);
		finNetProductBonusSchemeDto.setFinprodMarketId(saleBonusCollectDto.getFinprodCode());
		finNetProductBonusSchemeDto.setFinprodAbbr(finNetProductBonusSchemeDto.getFinprodAbbr());
		finNetProductBonusSchemeDto.setFinprodName(finNetProductBonusSchemeDto.getFinprodName());
		finNetProductBonusSchemeDto.setTradeId(finNetProductBonusSchemeDto.getBonusPlanId());
		finNetProductBonusSchemeDto.setActBonusDate(saleBonusCollectDto.getBonusdate());		
		finNetProductBonusSchemeDto.setBonusCashAmt(saleBonusCollectDto.getBonusamt());
		finNetProductBonusSchemeDto.setBonusShareAmt(saleBonusCollectDto.getRebonusamt());
		finNetProductBonusSchemeDto.setBonusShareNum(saleBonusCollectDto.getRedealqty());
		finNetProductBonusSchemeDto.setCcy(finNetProductBonusSchemeDto.getCcy());
		finNetProductBonusSchemeDto.setBonusPlanId(finNetProductBonusSchemeDto.getBonusPlanId());
		finNetProductBonusSchemeDto.setDelDate(finNetProductBonusSchemeDto.getDelDate());
		finNetProductBonusSchemeDto.setConfirmDate(saleBonusCollectDto.getConfirmdate());
		finNetProductBonusSchemeDto.setVluRefDate(finNetProductBonusSchemeDto.getVluRefDate());
		finNetProductBonusSchemeDto.setTdyUnitValue(finNetProductBonusSchemeDto.getTdyUnitValue());
		finNetProductBonusSchemeDto.setBonusShareAmt(finNetProductBonusSchemeDto.getBonusShareAmt());
		finNetProductBonusSchemeDto.setBonusShareAmt(saleBonusCollectDto.getRebonusamt());
		finNetProductBonusSchemeDto.setBonusShareNum(saleBonusCollectDto.getRedealqty());
		
		InfProdCommonDto infProdCommonDto= saleSystemInterfMapper.getFinprodMarketIdList(saleBonusCollectDto.getFinprodCode()).get(0);
		finNetProductBonusSchemeDto.setPortfolioId(finNetProductBonusSchemeDto.getPortfolioId());
		finNetProductBonusSchemeDto.setTradeId(finNetProductBonusSchemeDto.getBonusPlanId());
		finNetProductBonusSchemeDto.setCreateUser(infProdCommonDto.getProdmanager());//产品经理
		finNetProductBonusSchemeDto.setElementId("FinNetProductBonusScheme005_btn_save");//页面元素
		finNetProductBonusSchemeDto.setOperateType("save");//操作类型
		finNetProductBonusSchemeDto.setDealType("bonusShareConfirm");//处理方式
		finNetProductBonusSchemeDto.setRemark("销售推送数据");//备注
		finNetProductBonusSchemeDto.setPageModel("2");
		
		return finNetProductBonusSchemeDto;
		
	}



}
