package com.joyintech.tams.svreport.loaninfomanagement.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.loaninfomanagement.dao.OperationalLoanInfo001Mapper;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;

@Component
public class OperationalLoanInfo001Validator implements BeanValidator{
	@Autowired
	private OperationalLoanInfo001Mapper operationalLoanInfo001Mapper;

	@Override
	public List<ValidMessage> valid(Object value) {
		OperationalLoanInfo001Dto dto = (OperationalLoanInfo001Dto) value;
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		if(StringUtil.equals(dto.getOperateType(), "delete")){
			
		}else if(StringUtil.equals(dto.getOperateType(), "edit")){
		}else if(StringUtil.equals(dto.getOperateType(), "submit")){
			//资产代码必填
			if(StringUtil.isEmpty(dto.getAssetCode())){
				errorList.add(new ValidMessage("assetCode", this.getMessage("REPORT_COMMON_E_0001",
						this.getMessage("OperationalLoanInfo002.assetCode"))));
	        }
			
			if (StringUtil.isNotEmpty(dto.getAssetCode()) &&
					StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) &&
					operationalLoanInfo001Mapper.checkAssetCodeExist(dto.getAssetCode()) > 0) {
				errorList.add(new ValidMessage("assetCode", "资产代码已存在！"));
            }
			
			//当资产为资产收益权时，必填校验
			if(StringUtil.equals(dto.getAssetType(), DictCodeListExt3.人行资产一级分类_资产收益权.getValue())){
				//基础资产投向分类/各比例 必填
				if(StringUtil.isEmpty(dto.getAssetsInvest())){
					errorList.add(new ValidMessage("assetsInvest", this.getMessage("REPORT_COMMON_E_0001",
							this.getMessage("OperationalLoanInfo002.assetsInvest"))));
		        }
				if(StringUtil.isEmpty(dto.getEachProportion())){
					errorList.add(new ValidMessage("eachProportion", this.getMessage("REPORT_COMMON_E_0001",
							this.getMessage("OperationalLoanInfo002.eachProportion"))));
		        }
			}else if(StringUtil.equals(dto.getAssetType(), DictCodeListExt3.人行资产二级分类_除回购或拆借外贷款.getValue())){//当资产为资产收益权时
				//必填验证
				dataCheck(dto,errorList);
				
				//当“贷款种类”选择“转让贷款”时,贷款转让方信息、贷款转让折扣率（%）   必填
		        if (StringUtil.isNotEmpty(dto.getLoansType()) && 
		        		StringUtil.equals(dto.getLoansType(), DictCodeListExt3.贷款种类_转让贷款.getValue())) {
		        	if(!StringUtil.isNotEmpty(dto.getLoanTransferorId())){
		        		errorList.add(new ValidMessage("loanTransferorId", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanTransferorId"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanTransferorName())){
		        		errorList.add(new ValidMessage("loanTransferorName", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanTransferorName"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanContractOrgId())){
		        		errorList.add(new ValidMessage("loanContractOrgId", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanContractOrgId"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanContractOrgName())){
		        		errorList.add(new ValidMessage("loanContractOrgName", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanContractOrgName"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanContractOrgProvince())){
		        		errorList.add(new ValidMessage("loanContractOrgProvince", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanContractOrgProvince"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanContractOrgCity())){
		        		errorList.add(new ValidMessage("loanContractOrgCity", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanContractOrgCity"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanContractOrgCounty())){
		        		errorList.add(new ValidMessage("loanContractOrgCounty", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanContractOrgCounty"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanTransferorRate())){
		        		errorList.add(new ValidMessage("loanTransferorRate", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanTransferorRate"))));
		        	}
		        }
		        
		        //当贷款种类为“其他债权”时,贷款借据编码、贷款产品类别、贷款质量、贷款状态   置灰不可填，否则必填
		        if (StringUtil.isNotEmpty(dto.getLoansType()) && 
		        		!StringUtil.equals(dto.getLoansType(), DictCodeListExt3.贷款种类_其他债权收益权.getValue())) {
		        	if(!StringUtil.isNotEmpty(dto.getLoanBasisId())){
		        		errorList.add(new ValidMessage("loanBasisId", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanBasisId"))));
		        	}
		        	if(StringUtil.isNotEmpty(dto.getLoanPrdType()) 
		        			&& StringUtil.equals(dto.getLoanPrdType(), DictCodeListExt3.贷款产品类别_其他贷款.getValue())){
		        		errorList.add(new ValidMessage("loanPrdType", "只有贷款种类为其他债权时才能选择“F99-其他贷款”"));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanQuality())){
		        		errorList.add(new ValidMessage("loanQuality", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanQuality"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getLoanState())){
		        		errorList.add(new ValidMessage("loanState", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanState"))));
		        	}
		        }
		        
		        //当行业信息为“2 境外”时，借款人国别 必填;
		        if(StringUtil.isNotEmpty(dto.getIndustryInfo())){
		        	if(StringUtil.equals(dto.getIndustryInfo(), DictCodeListExt3.行业门内编码_非居民.getValue())){
			        	if(!StringUtil.isNotEmpty(dto.getBorrowerNationality())){
			        		errorList.add(new ValidMessage("borrowerNationality", this.getMessage("BUSS_COM_E_0038",
			    	                this.getMessage("OperationalLoanInfo002.borrowerNationality"))));
			        	}
			        }
		        }
		        
		        //行业信息不为“境内个人”"非居民"时，借款人地区(省市县) 必填
		        if(!StringUtil.equals(dto.getIndustryInfo(), DictCodeListExt3.行业门内编码_境内个人.getValue())
		        		&& !StringUtil.equals(dto.getIndustryInfo(), DictCodeListExt3.行业门内编码_非居民.getValue())){
		        	if(!StringUtil.isNotEmpty(dto.getBorrowerProvince())){
		        		errorList.add(new ValidMessage("borrowerProvince", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.borrowerProvince"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getBorrowerCity())){
		        		errorList.add(new ValidMessage("borrowerCity", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.borrowerCity"))));
		        	}
		        	if(!StringUtil.isNotEmpty(dto.getBorrowerCounty())){
		        		errorList.add(new ValidMessage("borrowerCounty", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.borrowerCounty"))));
		        	}
		        }
		        
		        //借款人类型为“1-住户、2-广义政府、5-特定目的载体、6-境外”时,企业出资人经济成分(一二三级) 置灰不可填
		        if(StringUtil.isNotEmpty(dto.getBorrowerType()) && 
		        		StringUtil.equals(dto.getBorrowerType(), DictCodeListExt3.借款人类型_非金融企业.getValue())
		        		|| StringUtil.equals(dto.getBorrowerType(), DictCodeListExt3.借款人类型_金融机构.getValue())){
		        	if(!StringUtil.isNotEmpty(dto.getEnterpriseInvestorTypeFirst())){
		        		errorList.add(new ValidMessage("enterpriseInvestorTypeFirst", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.enterpriseInvestorTypeFirst"))));
		        	}
		         	if(!StringUtil.isNotEmpty(dto.getEnterpriseInvestorTypeSec())){
		        		errorList.add(new ValidMessage("enterpriseInvestorTypeSec", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.enterpriseInvestorTypeSec"))));
		        	}
		         	if(!StringUtil.isNotEmpty(dto.getEnterpriseInvestorTypeThird())){
		        		errorList.add(new ValidMessage("enterpriseInvestorTypeThird", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.enterpriseInvestorTypeThird"))));
		        	}
		        }
		        
		        //当借款人类型为“1-住户、6-境外”时,企业规模   置灰不可填，否则必填
		        if(StringUtil.isNotEmpty(dto.getBorrowerType()) && 
		        		!StringUtil.equals(dto.getBorrowerType(), DictCodeListExt3.借款人类型_住户.getValue())
		        		&& !StringUtil.equals(dto.getBorrowerType(), DictCodeListExt3.借款人类型_境外.getValue())){
		        	if(!StringUtil.isNotEmpty(dto.getEnterpriseScale())){
		        		errorList.add(new ValidMessage("enterpriseScale", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.enterpriseScale"))));
		        	}
		        }
		        
		        //若“贷款状态”为“展期”，必填，且贷款展期到期日期需大于资产到期日。日期格式yyyy-mm-dd
		        if(StringUtil.isNotEmpty(dto.getLoanState()) && 
		        		StringUtil.equals(dto.getLoanState(), DictCodeListExt3.贷款状态_展期.getValue())){
		        	if(!StringUtil.isNotEmpty(dto.getLoanExtensionDueDate())){
		        		errorList.add(new ValidMessage("loanExtensionDueDate", this.getMessage("BUSS_COM_E_0038",
		    	                this.getMessage("OperationalLoanInfo002.loanExtensionDueDate"))));
		        	}
		        	
		        	//投资封闭期（到）> 资产到期日
		        	if (StringUtil.isNotEmpty(dto.getLoanExtensionDueDate()) && StringUtil.isNotEmpty(dto.getLoanMdate())) {
		                if (DateUtil.compareDate(dto.getLoanExtensionDueDate(), dto.getLoanMdate()) <= 0) {
		                	errorList.add(new ValidMessage("loanExtensionDueDate",
		                        getMessage("REPORT_COMMON_E_0059", getMessage("OperationalLoanInfo002.loanExtensionDueDate"),
		                            getMessage("OperationalLoanInfo002.loanMdate"))));
		                }
		            }
		        }
			}
			
		}
		return errorList;
	}
	
	/**
	 * 保存时贷款类资产必填项判断
	 * @param dto
	 */
	private void dataCheck(OperationalLoanInfo001Dto dto,List<ValidMessage> errorList){
		if(StringUtil.isEmpty(dto.getLoansType())){
			errorList.add(new ValidMessage("loansType", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.loansType"))));
        }
		if(StringUtil.isEmpty(dto.getBorrowerType())){
			errorList.add(new ValidMessage("borrowerType", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.borrowerType"))));
        }
		if(StringUtil.isEmpty(dto.getIndustryInfo())){
			errorList.add(new ValidMessage("industryInfo", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.industryInfo"))));
        }
		if(StringUtil.isEmpty(dto.getBorrowerId())){
			errorList.add(new ValidMessage("borrowerId", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.borrowerId"))));
        }
		if(StringUtil.isEmpty(dto.getLoanPrdType())){
			errorList.add(new ValidMessage("loanPrdType", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.loanPrdType"))));
        }
		if(StringUtil.isEmpty(dto.getLoanActualInvest())){
			errorList.add(new ValidMessage("loanActualInvest", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.loanActualInvest"))));
        }
		if(!StringUtil.isNotEmpty(dto.getLoanVdate())){
			errorList.add(new ValidMessage("loanVdate", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.loanVdate"))));
        }
		if(!StringUtil.isNotEmpty(dto.getLoanMdate())){
			errorList.add(new ValidMessage("loanMdate", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.loanMdate"))));
        }
		//贷款发放日期  <= 贷款到期日期
    	if (StringUtil.isNotEmpty(dto.getLoanVdate()) && StringUtil.isNotEmpty(dto.getLoanMdate())) {
            if (DateUtil.compareDate(dto.getLoanVdate(), dto.getLoanMdate()) > 0) {
            	errorList.add(new ValidMessage("loanMdate",
                    getMessage("REPORT_COMMON_E_0106", getMessage("OperationalLoanInfo002.loanVdate"),
                        getMessage("OperationalLoanInfo002.loanMdate"))));
            }
        }
		if(StringUtil.isEmpty(dto.getRateFixed())){
			errorList.add(new ValidMessage("rateFixed", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.rateFixed"))));
        }
		if(!StringUtil.isNotEmpty(dto.getRateLevel())){
			errorList.add(new ValidMessage("assetCode", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.rateLevel"))));
        }
		if(StringUtil.isEmpty(dto.getLoanGuaranteeMode())){
			errorList.add(new ValidMessage("loanGuaranteeMode", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.loanGuaranteeMode"))));
        }
		
		if(StringUtil.isEmpty(dto.getOrigCcy())){
			errorList.add(new ValidMessage("origCcy", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.origCcy"))));
        }
		if(!StringUtil.isNotEmpty(dto.getOrigContractAmount())){
			errorList.add(new ValidMessage("origContractAmount", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.origContractAmount"))));
        }
		if(!StringUtil.isNotEmpty(dto.getOrigContractAmountCny())){
			errorList.add(new ValidMessage("origContractAmountCny", this.getMessage("REPORT_COMMON_E_0001",
					this.getMessage("OperationalLoanInfo002.origContractAmountCny"))));
        }
	}

}
