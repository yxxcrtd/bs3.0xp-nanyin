package com.joyintech.tams.disclosure.information.manage.prodfilemanage.validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RepInvassetkindDto;
import com.joyintech.tams.common.base.entity.RepProdFileFeeInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dao.ReportProdFileManage001Mapper;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto.ReportProdFileManage001Dto;

@Component
public class ReportProdFileManage001Validator implements BeanValidator{
	
	@Autowired
	private ReportProdFileManage001Mapper reportProdFileManage001Mapper;
	
	@Override
	public List<ValidMessage> valid(Object value) {
		ReportProdFileManage001Dto dto = (ReportProdFileManage001Dto) value;
		List<ValidMessage> errorList = new ArrayList<>();
		if(StringUtil.equals(dto.getOperateType(), "delete")){
			for(ReportProdFileManage001Dto item:dto.getSelectedItem()){
				if(StringUtil.equals(item.getDataStatus(), DisclosureDictCodeList.维护数据状态_删除.getValue())){
					errorList.add(new ValidMessage("只能删除有效状态的数据！"));
					break;
				}
			}
		}else if(StringUtil.equals(dto.getOperateType(), "edit")){
			if(StringUtil.equals(dto.getDataStatus(), DisclosureDictCodeList.维护数据状态_删除.getValue())){
				errorList.add(new ValidMessage("删除状态的数据不支持修改！"));
			}
		}else if(StringUtil.equals(dto.getOperateType(), "submit")){
			
			//必填验证
			dataCheck(dto,errorList);
			
//			if(StringUtil.isNotEmpty(dto.getInvestTypes())){
//				Set<String> investSet = new HashSet<String>();
//				String[] investTypes = dto.getInvestTypes().split(";");
//		        for(int i = 0;i<investTypes.length;i++){
//		        	investSet.add(investTypes[i]);
//		    	}
//		        if(investSet.size() != investTypes.length){
//		        	errorList.add(new ValidMessage("存在相同的投资种类，请重新选择！"));
//		        }
//			}
			List<RepInvassetkindDto> investLists = dto.getInvestLists();
			if(!StringUtil.isEmptyList(investLists)){
				Set<String> investSet = new HashSet<String>();
				for(RepInvassetkindDto investDto:investLists){
					investSet.add(investDto.getCode());
				}
				if(investSet.size() != investLists.size()){
		        	errorList.add(new ValidMessage("存在相同的投资种类，请重新选择！"));
		        }
			}
			
			if(!StringUtil.isEmptyList(dto.getFeeLists())){
				Set<String> feeSet = new HashSet<String>();
				List<RepProdFileFeeInfoDto> feeLists = dto.getFeeLists();
				for(int i = 0;i<feeLists.size();i++){
					RepProdFileFeeInfoDto feeinfo = feeLists.get(i);
					feeSet.add(feeinfo.getFeeId());
		        	
		        	if(StringUtil.isEmpty(feeinfo.getFeeId())){
	                	errorList.add(new ValidMessage("请输入费用名称！"));
	                }
	                if(!StringUtil.isNotEmpty(feeinfo.getFeerate())){
	                	errorList.add(new ValidMessage("请输入费率！"));
	                }
	                if(StringUtil.isEmpty(feeinfo.getBasicbill())){
	                	errorList.add(new ValidMessage("请输入计费基数！"));
	                }
	                if(StringUtil.isEmpty(feeinfo.getBasis())){
	                	errorList.add(new ValidMessage("请输入计费基础！"));
	                }
		    	}
		        if(feeSet.size() != feeLists.size()){
		        	errorList.add(new ValidMessage("存在相同的费用名称，请重新选择！"));
		        }
	        }
			
			if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) && 
					reportProdFileManage001Mapper.checkProdInfoExistPF(dto.getProdId()) > 0) {
				errorList.add(new ValidMessage("产品代码已存在！"));
            }
			
			//是否有投资封闭期为“是”时，必填  投资封闭期（从）/投资封闭期（到）
	        if (StringUtil.isNotEmpty(dto.getInvestcloseFlag())  && 
	        		StringUtil.equals(dto.getInvestcloseFlag(), DictCodeList.是否_是.getValue())) {
	        	if(!StringUtil.isNotEmpty(dto.getInvestcloseStrdate())){
	        		errorList.add(new ValidMessage("investcloseStrdate", this.getMessage("BUSS_COM_E_0038",
	    	                this.getMessage("ReportProdFileManage002.investcloseStrdate"))));
	        	}
	        	if(!StringUtil.isNotEmpty(dto.getInvestcloseEnddate())){
	        		errorList.add(new ValidMessage("investcloseEnddate", this.getMessage("BUSS_COM_E_0038",
	    	                this.getMessage("ReportProdFileManage002.investcloseEnddate"))));
	        	}
	        	
	        	//投资封闭期（到）>= 投资封闭期（从）
	        	if (StringUtil.isNotEmpty(dto.getInvestcloseFlag()) && StringUtil.isNotEmpty(dto.getInvestcloseEnddate())) {
	                if (DateUtil.compareDate(dto.getInvestcloseStrdate(), dto.getInvestcloseEnddate()) > 0) {
	                	errorList.add(new ValidMessage("investcloseEnddate",
	                        getMessage("PRODFILE_E_0001", getMessage("ReportProdFileManage002.investcloseEnddate"),
	                            getMessage("ReportProdFileManage002.investcloseStrdate"))));
	                }
	            }
	        }
	        
	        //产品处理模式为“周期型”时
	        if(StringUtil.equals(dto.getDealMode(), DictCodeList.处理模式_周期型.getValue())){
	        	//期限必填
	        	if(!StringUtil.isNotEmpty(dto.getTermtype())){
	        		errorList.add(new ValidMessage("termtype", this.getMessage("BUSS_COM_E_0038",
	    	                this.getMessage("ReportProdFileManage002.termtype"))));
	        	}
	        	//是否自动续投 必填
	        	if(!StringUtil.isNotEmpty(dto.getAutocontinueFlag())){
	        		errorList.add(new ValidMessage("autocontinueFlag", this.getMessage("BUSS_COM_E_0038",
	    	                this.getMessage("ReportProdFileManage002.autocontinueFlag"))));
	        	}
	        }
	        
	        //收益类型为“预期收益型”时，预期年化收益率 必填
	        if(StringUtil.equals(dto.getProfitType(), DictCodeList.收益类型_收益型.getValue())
	        		&& !StringUtil.isNotEmpty(dto.getYield())){
        		errorList.add(new ValidMessage("yield", this.getMessage("BUSS_COM_E_0038",
    	                this.getMessage("ReportProdFileManage002.yield"))));
	        }
	        
	        //当发行方式为“开放式”时
	        if(StringUtil.equals(dto.getIssueType(), DictCodeList.开放类型_开放式.getValue())){
	        	//申购开放日 必填
	        	if(!StringUtil.isNotEmpty(dto.getPurOpenDate())){
	        		errorList.add(new ValidMessage("purOpenDate", this.getMessage("BUSS_COM_E_0038",
	    	                this.getMessage("ReportProdFileManage002.purOpenDate"))));
	        	}
	        	//赎回开放日 必填
	        	if(!StringUtil.isNotEmpty(dto.getRedOpenDate())){
	        		errorList.add(new ValidMessage("redOpenDate", this.getMessage("BUSS_COM_E_0038",
	    	                this.getMessage("ReportProdFileManage002.redOpenDate"))));
	        	}
	        }
	        
		}
		return errorList;
	}
	
	/**
	 * 保存时必填项判断
	 * @param dto
	 */
	private void dataCheck(ReportProdFileManage001Dto dto,List<ValidMessage> errorList){
		if(StringUtil.isEmpty(dto.getProdId())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.prodId"))));
        }
		if(StringUtil.isEmpty(dto.getProdName())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.prodName"))));
        }
		if(StringUtil.isEmpty(dto.getRegCode())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.regCode"))));
        }
		if(StringUtil.isEmpty(dto.getProdSeries())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.prodSeries"))));
        }
		if(StringUtil.isEmpty(dto.getProfitType())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.profitType"))));
        }
		if(StringUtil.isEmpty(dto.getDealMode())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.dealMode"))));
        }
		if(StringUtil.isEmpty(dto.getIssueType())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.issueType"))));
        }
		if(StringUtil.isEmpty(dto.getOperateMode())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.operateMode"))));
        }
		if(StringUtil.isEmpty(dto.getProfitFlag())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.profitFlag"))));
        }
		if(StringUtil.isEmpty(dto.getIvstorientation())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.ivstorientation"))));
        }
		if(StringUtil.isEmpty(dto.getCcy())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.ccy"))));
        }
		if(StringUtil.isEmpty(dto.getRaiseType())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.raiseType"))));
        }
		if(StringUtil.isEmpty(dto.getRiskLevel())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.riskLevel"))));
        }
		if(StringUtil.isEmpty(dto.getRiskType())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.riskType"))));
        }
		if(StringUtil.isEmpty(dto.getPayType())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.payType"))));
        }
		if(StringUtil.isEmpty(dto.getBeforeendFlag())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.beforeendFlag"))));
        }
		if(!StringUtil.isNotEmpty(dto.getRaiseVdate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.raiseVdate"))));
        }
		if(!StringUtil.isNotEmpty(dto.getRaiseEnddate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.raiseEnddate"))));
        }
		//画面.募集开始日> 画面.募集结束日
    	if (StringUtil.isNotEmpty(dto.getRaiseVdate()) && StringUtil.isNotEmpty(dto.getRaiseEnddate())) {
            if (DateUtil.compareDate(dto.getRaiseVdate(), dto.getRaiseEnddate()) > 0) {
            	errorList.add(new ValidMessage("raiseEnddate",
                    getMessage("PRODFILE_E_0001", getMessage("ReportProdFileManage002.raiseEnddate"),
                        getMessage("ReportProdFileManage002.raiseVdate"))));
            }
        }
        // 画面.募集结束日>画面.产品起息日
        if (StringUtil.isNotEmpty(dto.getRaiseEnddate()) && StringUtil.isNotEmpty(dto.getVdate())) {
            if (DateUtil.compareDate(dto.getRaiseEnddate(), dto.getVdate()) > 0) {
            	errorList.add(new ValidMessage("raiseEnddate",
                    getMessage("PRODFILE_E_0001", getMessage("ReportProdFileManage002.vdate"),
                        getMessage("ReportProdFileManage002.raiseEnddate"))));
            }
        }
		if(!StringUtil.isNotEmpty(dto.getVdate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.vdate"))));
        }
		if(!StringUtil.isNotEmpty(dto.getMdate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.mdate"))));
        }
		if(!StringUtil.isNotEmpty(dto.getTerm())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.term"))));
        }
		if(StringUtil.isEmpty(dto.getInvestcloseFlag())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.investcloseFlag"))));
        }
		if(!StringUtil.isNotEmpty(dto.getLiquidationDate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.liquidationDate"))));
        }
		if(StringUtil.isEmpty(dto.getBasic())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.basic"))));
        }
		if(StringUtil.isEmpty(dto.getInvestManager())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.investManager"))));
        }
		if(StringUtil.isEmpty(dto.getTrustee())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.trustee"))));
        }
		if(StringUtil.isEmpty(dto.getInvestmentScope())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.investmentScope"))));
        }
		if(!StringUtil.isNotEmpty(dto.getSubsRate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.subsRate"))));
        }
		if(!StringUtil.isNotEmpty(dto.getPurRate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.purRate"))));
        }
		if(!StringUtil.isNotEmpty(dto.getRedRate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.redRate"))));
        }
//		if(StringUtil.isEmpty(dto.getTruFee())){
//			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
//					this.getMessage("ReportProdFileManage002.truFee"))));
//        }
//		if(StringUtil.isEmpty(dto.getSaleFee())){
//			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
//					this.getMessage("ReportProdFileManage002.saleFee"))));
//        }
//		if(StringUtil.isEmpty(dto.getManageFee())){
//			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
//					this.getMessage("ReportProdFileManage002.manageFee"))));
//        }
		if(StringUtil.isEmpty(dto.getRepYield())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.repYield"))));
        }
		if(StringUtil.isEmpty(dto.getFloatFee())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.floatFee"))));
        }
		if(!StringUtil.isNotEmpty(dto.getRaiseAmtPlan())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.raiseAmtPlan"))));
        }
		if(!StringUtil.isNotEmpty(dto.getSaleMax())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.saleMax"))));
        }
		if(!StringUtil.isNotEmpty(dto.getSaleMin())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.saleMin"))));
        }
		if(!StringUtil.isNotEmpty(dto.getStartamt())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.startamt"))));
        }
		if(!StringUtil.isNotEmpty(dto.getLowestamt())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.lowestamt"))));
        }
		if(!StringUtil.isNotEmpty(dto.getSubsamtMax())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.subsamtMax"))));
        }
		if(!StringUtil.isNotEmpty(dto.getCustSubsamtMax())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.custSubsamtMax"))));
        }
//		if(StringUtil.isEmpty(dto.getSubsCancelFlag())){
//			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
//					this.getMessage("ReportProdFileManage002.subsCancelFlag"))));
//        }
		if(!StringUtil.isNotEmpty(dto.getMinRedQty())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.minRedQty"))));
        }
		if(!StringUtil.isNotEmpty(dto.getMinRetainQty())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.minRetainQty"))));
        }
		if(!StringUtil.isNotEmpty(dto.getHugeRedRatio())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.hugeRedRatio"))));
        }
		if(StringUtil.isEmpty(dto.getRedPrinciple())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.redPrinciple"))));
        }
		if(StringUtil.isEmpty(dto.getPurCdate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.purCdate"))));
        }
		if(StringUtil.isEmpty(dto.getRedCdate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.redCdate"))));
        }
		if(StringUtil.isEmpty(dto.getInvestPrinDate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.investPrinDate"))));
        }
		if(StringUtil.isEmpty(dto.getInvestProfDate())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.investProfDate"))));
        }
//		if(StringUtil.isEmpty(dto.getSaleChannel())){
//			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
//					this.getMessage("ReportProdFileManage002.saleChannel"))));
//        }
		if(StringUtil.isEmpty(dto.getPurHolidayFlag())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.purHolidayFlag"))));
		}
		if(StringUtil.isEmpty(dto.getRedHolidayFlag())){
			errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("ReportProdFileManage002.redHolidayFlag"))));
		}
	}

}
