package com.joyintech.tams.svreport.loaninfomanagement.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.loaninfomanagement.dao.OperationalLoanInfo001Mapper;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OperationalLoanInfo001Validator implements BeanValidator{
	@Autowired
	private OperationalLoanInfo001Mapper operationalLoanInfo001Mapper;

	/**
	 * 日志管理
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<ValidMessage> valid(Object value) {
		OperationalLoanInfo001Dto dto = (OperationalLoanInfo001Dto) value;
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		if(StringUtil.equals(dto.getOperateType(), "delete")){

		}else if(StringUtil.equals(dto.getOperateType(), "edit")){
		}else if(StringUtil.equals(dto.getOperateType(), "submit")){
			//资产代码必填
			if(StringUtil.isEmpty(dto.getAssetCode())){
				errorList.add(new ValidMessage("assetCode", getMessage("REPORT_COMMON_E_0001",
						"资产代码")));
			}

			if (StringUtil.isNotEmpty(dto.getAssetCode()) &&
					StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) &&
					operationalLoanInfo001Mapper.checkAssetCodeExist(dto.getAssetCode()) > 0) {
				errorList.add(new ValidMessage("assetCode", "资产代码已存在！"));
			}

			//当资产为资产收益权时，必填校验
			if(StringUtil.equals(dto.getAssetType(), DictCodeListExt3.中债_中债贷款分类_收益权.getValue())){
				//基础资产投向分类/各比例 必填
				if(StringUtil.isEmpty(dto.getAssetsInvest())){
					errorList.add(new ValidMessage("assetsInvest", getMessage("REPORT_COMMON_E_0001",
							"基础资产投向分类")));
				}
				if(StringUtil.isEmpty(dto.getEachProportion())){
					errorList.add(new ValidMessage("eachProportion", getMessage("REPORT_COMMON_E_0001",
							"各比例")));
				}else{
					try {
						int each = 0;
						for(String code : dto.getEachProportion().split(":")){
							each = each + Integer.parseInt(code);
						}
						if(each!=100){
							errorList.add(new ValidMessage("eachProportion", "各比例分割后合计必须等于100",
									"各比例"));
						}
					}catch(Exception e){
						logger.error("数据转换错误:",e);
						errorList.add(new ValidMessage("eachProportion", "各比例分割后合计必须等于100",
								"各比例"));
					}

				}
			}else if(StringUtil.equals(dto.getAssetType(), DictCodeListExt3.中债_中债贷款分类_贷款.getValue())){
				//必填验证
				dataCheck(dto,errorList);

				//当“贷款种类”选择“转让贷款”时,贷款转让方信息、贷款转让折扣率（%）   必填
				if (StringUtil.isNotEmpty(dto.getLoansType()) &&
						StringUtil.equals(dto.getLoansType(), DictCodeListExt3.贷款种类_转让贷款.getValue())) {


					/***转让**/
					if(StringUtil.isNotEmpty(dto.getLoanTransferorRate()) &&  !isNumber(dto.getLoanTransferorRate().toString(),17,2)){
						errorList.add(new ValidMessage("loanTransferorRate", getMessage("贷款转让折扣率（%）格式需要为n(17,2)!")));
					}

					if(StringUtil.isNotEmpty(dto.getLoanTransferorId()) && dto.getLoanTransferorId().length() > 6){
						errorList.add(new ValidMessage("loanTransferorId", getMessage("贷款转让方机构代码最大长度为6位!")));
					}

					try{
						if(StringUtil.isNotEmpty(dto.getLoanTransferorName()) && dto.getLoanTransferorName().getBytes("GBK").length > 500){
							errorList.add(new ValidMessage("loanTransferorName", getMessage("贷款转让方机构名称最多只能输入500个字节!")));
						}
						if(StringUtil.isNotEmpty(dto.getLoanContractOrgId()) &&  dto.getLoanContractOrgId().length() > 6){
							errorList.add(new ValidMessage("loanContractOrgId", getMessage("贷款合同原始发放机构代码最大长度为6位!")));
						}
						if(StringUtil.isNotEmpty(dto.getLoanContractOrgName()) &&  dto.getLoanContractOrgName().getBytes("GBK").length > 500){
							errorList.add(new ValidMessage("loanContractOrgName", getMessage("贷款合同原始发放机构名称最多只能输入500个字节!")));
						}
					}catch(Exception e){
						errorList.add(new ValidMessage("loanTransferorName", getMessage("数据转换异常")));
					}


					if(!StringUtil.isNotEmpty(dto.getLoanTransferorId())){
						errorList.add(new ValidMessage("loanTransferorId", getMessage("BUSS_COM_E_0038",
								"贷款转让方机构代码")));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanTransferorName())){
						errorList.add(new ValidMessage("loanTransferorName", getMessage("BUSS_COM_E_0038",
								"贷款转让方机构名称")));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanContractOrgId())){
						errorList.add(new ValidMessage("loanContractOrgId", getMessage("BUSS_COM_E_0038",
								"贷款合同原始发放机构代码")));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanContractOrgName())){
						errorList.add(new ValidMessage("loanContractOrgName", getMessage("BUSS_COM_E_0038",
								"贷款合同原始发放机构名称")));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanContractOrgProvince())){
						errorList.add(new ValidMessage("loanContractOrgProvince", getMessage("BUSS_COM_E_0038",
								"贷款合同原始发放机构所在地(省)")));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanContractOrgCity())){
						errorList.add(new ValidMessage("loanContractOrgCity", getMessage("BUSS_COM_E_0038",
								"贷款合同原始发放机构所在地(市)")));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanContractOrgCounty())){
						errorList.add(new ValidMessage("loanContractOrgCounty", getMessage("BUSS_COM_E_0038",
								"贷款合同原始发放机构所在地(县)")));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanTransferorRate())){
						errorList.add(new ValidMessage("loanTransferorRate", getMessage("BUSS_COM_E_0038",
								"贷款转让折扣率(%)")));
					}
				}

				//当贷款种类为“其他债权”时,贷款借据编码、贷款产品类别、贷款质量、贷款状态   置灰不可填，否则必填
				if (StringUtil.isNotEmpty(dto.getLoansType()) &&
						!StringUtil.equals(dto.getLoansType(), DictCodeListExt3.贷款种类_其他债权收益权.getValue())) {
					if(!StringUtil.isNotEmpty(dto.getLoanBasisId())){
						errorList.add(new ValidMessage("loanBasisId", getMessage("BUSS_COM_E_0038",
								"贷款借据编码")));
					}
					try {
						/**发放,转让**/
						if(StringUtil.isNotEmpty(dto.getLoanBasisId()) &&  dto.getLoanBasisId().getBytes("GBK").length > 35){
							errorList.add(new ValidMessage("loanBasisId", getMessage("贷款借据编码不得大于35位字符!")));
						}

					}catch(Exception e){
						logger.error("数据转换错误:",e);
						errorList.add(new ValidMessage("loanBasisId", getMessage("贷款借据编码不得大于35位字符!")));
					}

					if(StringUtil.isNotEmpty(dto.getLoanPrdType())
							&& StringUtil.equals(dto.getLoanPrdType(), DictCodeListExt3.贷款产品类别_其他贷款.getValue())){
						errorList.add(new ValidMessage("loanPrdType", "只有贷款种类为其他债权时才能选择“F99-其他贷款”"));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanQuality())){
						errorList.add(new ValidMessage("loanQuality", getMessage("BUSS_COM_E_0038",
								"贷款质量")));
					}
					if(!StringUtil.isNotEmpty(dto.getLoanState())){
						errorList.add(new ValidMessage("loanState", getMessage("BUSS_COM_E_0038",
								"贷款状态")));
					}
				}

				//当行业信息为“2 境外”时，借款人国别 必填;
				if(StringUtil.isNotEmpty(dto.getIndustryInfo())){
					if(StringUtil.equals(dto.getIndustryInfo(), DictCodeListExt3.行业门内编码_非居民.getValue())){
						if(!StringUtil.isNotEmpty(dto.getBorrowerNationality())){
							errorList.add(new ValidMessage("borrowerNationality", getMessage("BUSS_COM_E_0038",
									"借款人国别")));
						}
					}
				}

				//行业信息不为“境内个人”"非居民"时，借款人地区(省市县) 必填
				if(!StringUtil.equals(dto.getIndustryInfo(), DictCodeListExt3.行业门内编码_境内个人.getValue())
						&& !StringUtil.equals(dto.getIndustryInfo(), DictCodeListExt3.行业门内编码_非居民.getValue())){
					if(!StringUtil.isNotEmpty(dto.getBorrowerProvince())){
						errorList.add(new ValidMessage("borrowerProvince", getMessage("BUSS_COM_E_0038",
								"借款人地区(省)")));
					}
					if(!StringUtil.isNotEmpty(dto.getBorrowerCity())){
						errorList.add(new ValidMessage("borrowerCity", getMessage("BUSS_COM_E_0038",
								"借款人地区(市)")));
					}
					if(!StringUtil.isNotEmpty(dto.getBorrowerCounty())){
						errorList.add(new ValidMessage("borrowerCounty", getMessage("BUSS_COM_E_0038",
								"借款人地区(县)")));
					}
				}

				//借款人类型为“1-住户、2-广义政府、5-特定目的载体、6-境外”时,企业出资人经济成分(一二三级) 置灰不可填
				if(StringUtil.isNotEmpty(dto.getBorrowerType()) &&
						StringUtil.equals(dto.getBorrowerType(), DictCodeListExt3.借款人类型_非金融企业.getValue())
						|| StringUtil.equals(dto.getBorrowerType(), DictCodeListExt3.借款人类型_金融机构.getValue())){
					if(!StringUtil.isNotEmpty(dto.getEnterpriseInvestorTypeFirst())){
						errorList.add(new ValidMessage("enterpriseInvestorTypeFirst", getMessage("BUSS_COM_E_0038",
								"企业出资人经济成分(一级)")));
					}
					if(!StringUtil.isNotEmpty(dto.getEnterpriseInvestorTypeSec())){
						errorList.add(new ValidMessage("enterpriseInvestorTypeSec", this.getMessage("BUSS_COM_E_0038",
								"企业出资人经济成分(二级)")));
					}
					if(!StringUtil.isNotEmpty(dto.getEnterpriseInvestorTypeThird())){
						errorList.add(new ValidMessage("enterpriseInvestorTypeThird", this.getMessage("BUSS_COM_E_0038",
								"企业出资人经济成分(三级)")));
					}
				}

				//当借款人类型为“1-住户、6-境外”时,企业规模   置灰不可填，否则必填
				if(StringUtil.isNotEmpty(dto.getBorrowerType()) &&
						!StringUtil.equals(dto.getBorrowerType(), DictCodeListExt3.借款人类型_住户.getValue())
						&& !StringUtil.equals(dto.getBorrowerType(), DictCodeListExt3.借款人类型_境外.getValue())){
					if(!StringUtil.isNotEmpty(dto.getEnterpriseScale())){
						errorList.add(new ValidMessage("enterpriseScale", getMessage("BUSS_COM_E_0038",
								"企业规模")));
					}
				}

				//若“贷款状态”为“展期”，必填，且贷款展期到期日期需大于资产到期日。日期格式yyyy-mm-dd
				if(StringUtil.isNotEmpty(dto.getLoanState()) &&
						StringUtil.equals(dto.getLoanState(), DictCodeListExt3.贷款状态_展期.getValue())){
					if(!StringUtil.isNotEmpty(dto.getLoanExtensionDueDate())){
						errorList.add(new ValidMessage("loanExtensionDueDate", getMessage("BUSS_COM_E_0038",
								"贷款展期到期日期")));
					}

					//投资封闭期（到）> 资产到期日
					if (StringUtil.isNotEmpty(dto.getLoanExtensionDueDate()) && StringUtil.isNotEmpty(dto.getLoanMdate())) {
						if (DateUtil.compareDate(dto.getLoanExtensionDueDate(), dto.getLoanMdate()) <= 0) {
							errorList.add(new ValidMessage("loanExtensionDueDate",
									getMessage("REPORT_COMMON_E_0059", getMessage("贷款展期到期日期"),
											"贷款到期日期")));
						}
					}
				}else if(StringUtil.isNotEmpty(dto.getLoanState()) &&
						StringUtil.equals(dto.getLoanState(), DictCodeListExt3.贷款状态_逾期.getValue())){
					if(StringUtil.isNotEmpty(dto.getLoanExtensionDueDate()) && StringUtil.isNotEmpty(dto.getLoanMdate())
							&&DateUtil.compareDate(dto.getLoanExtensionDueDate(), dto.getLoanMdate()) <= 0){
						errorList.add(new ValidMessage("loanExtensionDueDate",
								getMessage("REPORT_COMMON_E_0059", getMessage("贷款展期到期日期"),
										"贷款到期日期")));
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
			errorList.add(new ValidMessage("loansType", getMessage("REPORT_COMMON_E_0001",
					"贷款种类")));
		}
		if(StringUtil.isEmpty(dto.getBorrowerType())){
			errorList.add(new ValidMessage("borrowerType", getMessage("REPORT_COMMON_E_0001",
					"借款人类型")));
		}
		if(StringUtil.isEmpty(dto.getIndustryInfo())){
			errorList.add(new ValidMessage("industryInfo", getMessage("REPORT_COMMON_E_0001",
					"行业信息")));
		}
		if(StringUtil.isEmpty(dto.getBorrowerId())){
			errorList.add(new ValidMessage("borrowerId", getMessage("REPORT_COMMON_E_0001",
					"借款人代码")));
		}
		if(StringUtil.isEmpty(dto.getLoanPrdType())){
			errorList.add(new ValidMessage("loanPrdType", getMessage("REPORT_COMMON_E_0001",
					"贷款产品类别")));
		}
		if(StringUtil.isEmpty(dto.getLoanActualInvest())){
			errorList.add(new ValidMessage("loanActualInvest", getMessage("REPORT_COMMON_E_0001",
					"贷款实际投向")));
		}
		if(!StringUtil.isNotEmpty(dto.getLoanVdate())){
			errorList.add(new ValidMessage("loanVdate", getMessage("REPORT_COMMON_E_0001",
					"贷款发放日期")));
		}
		if(!StringUtil.isNotEmpty(dto.getLoanMdate())){
			errorList.add(new ValidMessage("loanMdate", getMessage("REPORT_COMMON_E_0001",
					"贷款到期日期")));
		}
		//贷款发放日期  <= 贷款到期日期
		if (StringUtil.isNotEmpty(dto.getLoanVdate()) && StringUtil.isNotEmpty(dto.getLoanMdate())) {
			if (DateUtil.compareDate(dto.getLoanVdate(), dto.getLoanMdate()) > 0) {
				errorList.add(new ValidMessage("loanMdate",
						getMessage("REPORT_COMMON_E_0106", getMessage("贷款发放日期"),
								"贷款到期日期")));
			}
		}
		if(StringUtil.isEmpty(dto.getRateFixed())){
			errorList.add(new ValidMessage("rateFixed", getMessage("REPORT_COMMON_E_0001",
					"利率是否固定")));
		}
		if(!StringUtil.isNotEmpty(dto.getRateLevel())){
			errorList.add(new ValidMessage("rateLevel", getMessage("REPORT_COMMON_E_0001",
					"利率水平(%)")));
		}
		if(StringUtil.isEmpty(dto.getLoanGuaranteeMode())){
			errorList.add(new ValidMessage("loanGuaranteeMode", getMessage("REPORT_COMMON_E_0001",
					"贷款担保方式")));
		}

		if(StringUtil.isEmpty(dto.getOrigCcy())){
			errorList.add(new ValidMessage("origCcy", getMessage("REPORT_COMMON_E_0001",
					"原始合同币种代码")));
		}
		if(!StringUtil.isNotEmpty(dto.getOrigContractAmount())){
			errorList.add(new ValidMessage("origContractAmount", getMessage("REPORT_COMMON_E_0001",
					"原始合同金额")));
		}
		if(!StringUtil.isEmpty(dto.getOrigCcy()) &&  "CNY".equals(dto.getOrigCcy())){
			if(StringUtil.isNotEmpty(dto.getOrigContractAmount()) && StringUtil.isNotEmpty(dto.getOrigContractAmountCny())){
				if(dto.getOrigContractAmount().compareTo(dto.getOrigContractAmountCny()) != 0){
					errorList.add(new ValidMessage("origContractAmountCny", getMessage("原始合同币种代码人民币时,原始合同金额折人民币必须与原始合同金额相等")));
				}
			}
		}else {
			if(!StringUtil.isNotEmpty(dto.getOrigContractAmountCny())){
				errorList.add(new ValidMessage("origContractAmountCny", getMessage("REPORT_COMMON_E_0001",
						"原始合同金额折人民币")));
			}
		}
		/*校验修复 zc add by start*/
		try {
			if (StringUtil.isNotEmpty(dto.getBorrowerId()) && dto.getBorrowerId().getBytes("GBK").length > 30) {
				errorList.add(new ValidMessage("borrowerId", getMessage("借款人代码最大长度为30位!")));
			}

		}catch(Exception e){
			logger.error("数据转换错误:",e);
			errorList.add(new ValidMessage("borrowerId", getMessage("借款人代码最大长度为30位!")));
		}

		if(StringUtil.isNotEmpty(dto.getRateLevel()) && !isNumber(dto.getRateLevel().toString(),17,2) ){
			errorList.add(new ValidMessage("rateLevel", getMessage("利率水平（%）格式需要为n(17,2)!")));
		}

		if(StringUtil.isNotEmpty(dto.getOrigContractAmount()) &&  !isNumber(dto.getOrigContractAmount().toString(),17,2)){
			errorList.add(new ValidMessage("origContractAmount", getMessage("原始合同金额格式需要为n(17,2)!")));
		}
		if(StringUtil.isNotEmpty(dto.getOrigContractAmountCny()) &&  !isNumber(dto.getOrigContractAmountCny().toString(),17,2)){
			errorList.add(new ValidMessage("origContractAmountCny", getMessage("原始合同金额折人民币格式需要为n(17,2)!")));
		}
		/*校验修复 zc add by end*/
	}

	public boolean isNumber(String srcValue,int allbit,int decbit){
		String format = "";
		if ( decbit>0){
			format = "^([-+]?[0-9]{1,"+(allbit-decbit)+"})?[.][0-9]{"+decbit+"}$";
		}else{
			format = "^([-+]?[0-9]{1,"+(allbit-decbit)+"})?[.]?[0-9]{"+decbit+"}$";
		}

		return srcValue.matches(format);
	}

}
