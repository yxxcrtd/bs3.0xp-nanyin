package com.joyintech.tams.svreport.infomanagement.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.BaseInformationDto;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.loaninfomanagement.dao.OperationalLoanInfo001Mapper;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;

@Component
public class OperationalLoanInfo001CheckService{
	@Autowired
	private OperationalLoanInfo001Mapper operationalLoanInfo001Mapper;

	public List<ExcelValidDto> checkImportData(List<Map<String, String>> dataMapList) {
		
        List<ExcelValidDto> checkResult = new ArrayList<>();

		//Iterator<Map<String,String>> it = dataMapList.iterator();
		
		for(int index = 0; index<dataMapList.size(); index++){
			Map<String,String> x = dataMapList.get(index);
			int rouwNum = index + 2;
			String assetCode = x.get("ASSET_CODE");
			ExcelValidDto excelValidDto = new ExcelValidDto(assetCode, String.valueOf(rouwNum),StringUtil.EMPTY, StringUtil.EMPTY);
			String errorInfo = excelValidDto.getErrorInfo();
				if (StringUtil.isNotEmpty(x.get("EACH_PROPORTION")) || StringUtil.isNotEmpty(x.get("ASSETS_INVEST"))){
					String assetsInvest= x.get("ASSETS_INVEST");
					String[] assetsInvestArr = assetsInvest.split(";");
					String eachProportion= x.get("EACH_PROPORTION");
					String[] eachProportionArr = eachProportion.split(":");
					String sum = "0";
					if(assetsInvestArr.length > 0 && StringUtil.isNotEmpty(x.get("EACH_PROPORTION"))){
						for(int i=0; i<eachProportionArr.length; i++){
							sum = new BigInteger(eachProportionArr[i]).add(new BigInteger(sum)).toString();
						}
						if(!StringUtil.equals(sum, "100")){
							//checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "各比例字段格式不正确或比例之和不等于100！"));
							errorInfo = errorInfo +	"各比例字段格式不正确或比例之和不等于100！"+";";
						}
					}else if(assetsInvestArr.length==1){
						//checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "各比例输入个数跟基础资产投向分类选择的不一致，请确认！"));
						errorInfo = errorInfo +	"各比例输入个数跟基础资产投向分类选择的不一致，请确认！"+";";

					}	
					if(!(assetsInvestArr.length == eachProportionArr.length)){
						//checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "各比例输入个数跟基础资产投向分类选择的不一致，请确认！"));
						errorInfo = errorInfo +	"各比例输入个数跟基础资产投向分类选择的不一致，请确认！"+";";
					}
				}
				
				//查询资产
				ChbAssetInfo dto = operationalLoanInfo001Mapper.checkByAssetCode(x.get("ASSET_CODE"));
				if(StringUtil.isNotEmpty(dto)){
					//当资产为资产收益权时，必填校验
					//if(StringUtil.equals(dto.getAssetType(), DictCodeListExt3.人行资产一级分类_资产收益权.getValue())){
					if(StringUtil.equals(dto.getPbcAssetType1(), DictCodeListExt3.人行资产一级分类_资产收益权.getValue())){
						//基础资产投向分类/各比例 必填
						if(StringUtil.isEmpty(x.get("ASSETS_INVEST"))){
		        			//checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当资产为资产收益权时，基础资产投向分类要素不可为空！"));
							errorInfo = errorInfo +	"当资产为资产收益权时，基础资产投向分类要素不可为空！"+";";
						}
						if(StringUtil.isEmpty(x.get("EACH_PROPORTION"))){
		        			//checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当资产为资产收益权时，各比例要素不可为空！"));
							errorInfo = errorInfo +	"当资产为资产收益权时，各比例要素不可为空！"+";";
						}
					}else if(StringUtil.equals(dto.getPbcAssetType1(), DictCodeListExt3.人行资产二级分类_除回购或拆借外贷款.getValue())){
						//当资产为人行资产二级分类_除回购或拆借外贷款
					}else if(StringUtil.equals(dto.getPbcAssetType1(), DictCodeListExt3.人行资产一级分类_贷款.getValue())){
						if(StringUtil.isEmpty(x.get("LOAN_PRD_TYPE"))){
							errorInfo = errorInfo +	"当资产为贷款时，贷款产品类别要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("LOAN_ACTUAL_INVEST"))){
							errorInfo = errorInfo +	"当资产为贷款时，贷款实际投向要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("LOAN_VDATE"))){
							errorInfo = errorInfo +	"当资产为贷款时，贷款发放日期要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("LOAN_MDATE"))){
							errorInfo = errorInfo +	"当资产为贷款时，贷款到期日期要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("RATE_FIXED"))){
							errorInfo = errorInfo +	"当资产为贷款时，利率是否固定要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("RATE_LEVEL"))){
							errorInfo = errorInfo +	"当资产为贷款时，利率水平（%）要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("LOAN_GUARANTEE_MODE"))){
							errorInfo = errorInfo +	"当资产为贷款时，贷款担保方式要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("ORIG_CCY"))){
							errorInfo = errorInfo +	"当资产为贷款时，原始合同币种代码要素不可为空！"+";";
						}
						if(StringUtil.isEmpty(x.get("ORIG_CONTRACT_AMOUNT"))){
							errorInfo = errorInfo +	"当资产为贷款时，原始合同金额要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("LOANS_TYPE"))){
							errorInfo = errorInfo +	"当资产为贷款时，贷款种类要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("BORROWER_TYPE"))){
							errorInfo = errorInfo +	"当资产为贷款时，借款人类型要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("INDUSTRY_INFO"))){
							errorInfo = errorInfo +	"当资产为贷款时，行业信息要素不可为空！ "+";";
						}
						if(StringUtil.isEmpty(x.get("BORROWER_ID"))){
							errorInfo = errorInfo +	"当资产为贷款时，借款人代码要素不可为空！ "+";";
						}
					}
				}
				
				if(StringUtil.isNotEmpty(errorInfo)){
					excelValidDto.setErrorInfo(errorInfo);
					checkResult.add(excelValidDto);
				}
				
				/* //当行业信息为“2 境外”时，借款人国别 必填;
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
			}*/
				
				/*//必填验证
				//dataCheck(dto,checkResult);
				
				//当“贷款种类”选择“转让贷款”时,贷款转让方信息、贷款转让折扣率（%）   必填
		        if (StringUtil.isNotEmpty(x.get("LOANS_TYPE")) && 
		        		StringUtil.equals(x.get("LOANS_TYPE"), DictCodeListExt3.贷款种类_转让贷款.getValue())) {
		        	if(!StringUtil.isNotEmpty(dto.getLoanTransferorId())){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当资产为贷款时，贷款种类要素不可为空！"));

		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_TRANSFEROR_NAME"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类选择“2-转让贷款”时，贷款转让方机构名称要素不可为空！"));

		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_CONTRACT_ORG_ID"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类选择“2-转让贷款”时，贷款转让方机构代码要素不可为空！"));

		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_CONTRACT_ORG_NAME"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类选择“2-转让贷款”时，贷款转让方机构名称要素不可为空！"));

		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_CONTRACT_ORG_PROVINCE"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类选择“2-转让贷款”时，贷款合同原始发放机构所在地（省）要素不可为空！"));

		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_CONTRACT_ORG_CITY"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类选择“2-转让贷款”时，贷款合同原始发放机构所在地（市）要素不可为空！"));
		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_CONTRACT_ORG_COUNTY"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类选择“2-转让贷款”时，贷款合同原始发放机构所在地（县）要素不可为空！"));

		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_TRANSFEROR_RATE"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类选择“2-转让贷款”时，贷款转让折扣率（%）要素不能为空！"));
		        	}
		        }
		        
		        //当贷款种类为“其他债权”时,贷款借据编码、贷款产品类别、贷款质量、贷款状态   置灰不可填，否则必填
		        if (StringUtil.isNotEmpty(x.get("LOANS_TYPE")) && 
		        		!StringUtil.equals(x.get("LOANS_TYPE"), DictCodeListExt3.贷款种类_其他债权收益权.getValue())) {
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_BASIS_ID"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类不为“3-其他债权”时，贷款借据编码要素不可为空！"));
		        	}
		        	if(StringUtil.isNotEmpty(x.get("LOAN_PRD_TYPE")) 
		        			&& StringUtil.equals(x.get("LOAN_PRD_TYPE"), DictCodeListExt3.贷款产品类别_其他贷款.getValue())){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类不为“3-其他债权”时，该字段不能为“F99-其他贷款！"));
		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_QUALITY"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类不选择“3-其他债权”时，贷款质量要素不能为空！"));
		        	}
		        	if(!StringUtil.isNotEmpty(x.get("LOAN_STATE"))){
	        			checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(index), "当贷款种类不选择“3-其他债权”时，贷款状态要素不能为空！"));
		        	}
		        }
			        */
			       
		}
		return checkResult;
	}
	
	/**
	 * 保存时贷款类资产必填项判断
	 * @param dto
	 *//*
	private void dataCheck(OperationalLoanInfo001Dto dto,List<ExcelValidDto> checkResult){
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
*/
}
