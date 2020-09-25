/*
 * 文件名：BussRepAssetCreditNonstdInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepAssetCreditNonstdInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 信贷及非标资产报送--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BussRepAssetCreditNonstdInfoValidator
 * @since 1.0
 */
@Component
public class BussRepAssetCreditNonstdInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussRepAssetCreditNonstdInfoDto dto = (BussRepAssetCreditNonstdInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信托贷款.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_委托贷款.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信贷资产转让.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_收_受益权.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_委托债权.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_应收账款.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_带回购条款的股权性融资.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_债权融资类产品.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信贷资产流转项目.getValue())
                ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_小微企业信贷资产流转和收益权转让.getValue())){
                if(StringUtil.isNotEmpty(dto.getRepAssetType())){
                    if(StringUtil.isEmpty(dto.getRepTradeMarket())){
                        resultList.add(new ValidMessage("repTradeMarket",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repTradeMarket"))));
                    }
                    //add by 张年  20190909 start
                    //投管二级分类为收/受益权  必填
                    if(StringUtil.equals(dto.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_收_受益权.getValue())){
                        if(StringUtil.isEmpty(dto.getRepProfitType())){
                            resultList.add(new ValidMessage("repProfitType",
                                    getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repProfitType"))));
                        }
                    }
                    //add by 张年  20190909 end
                    if(null==dto.getRepIntpayCycle()){
                        resultList.add(new ValidMessage("repIntpayCycle",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repIntpayCycle"))));
                    }
                    if(null==dto.getRepRepayCmt()){
                        resultList.add(new ValidMessage("repRepayCmt",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repRepayCmt"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepFinancier())){
                        resultList.add(new ValidMessage("repFinancier",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFinancier"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepFcrGradeIn())){
                        resultList.add(new ValidMessage("repFcrGradeIn",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFcrGradeIn"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepFcrGradeOut())){
                        resultList.add(new ValidMessage("repFcrGradeOut",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFcrGradeOut"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepFinancierType1())){
                        resultList.add(new ValidMessage("repFinancierType1",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFinancierType1"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepFinancierType2())){
                        resultList.add(new ValidMessage("repFinancierType2",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFinancierType2"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepFinancierType3())){
                        resultList.add(new ValidMessage("repFinancierType3",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFinancierType3"))));
                    }
                    if(StringUtil.equals(dto.getRepIndustryFlag(), DictCodeList.是否_是.getValue())){
                        if(StringUtil.isEmpty(dto.getRepMonitType())){
                            resultList.add(new ValidMessage("repMonitType",
                                    getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repMonitType"))));
                        }
                        if(StringUtil.isEmpty(dto.getRepMonTypeComm())){
                            resultList.add(new ValidMessage("repMonTypeComm",
                                    getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repMonTypeComm"))));
                        }
                    }
                    if(StringUtil.isEmpty(dto.getRepFinanceProj())){
                        resultList.add(new ValidMessage("repFinanceProj",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFinanceProj"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepIndustry1())){
                        resultList.add(new ValidMessage("repIndustry1",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repIndustry1"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepIndustryLevel2())){
                        resultList.add(new ValidMessage("repIndustryLevel2",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repIndustryLevel2"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepGuaType())){
                        resultList.add(new ValidMessage("repGuaType",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repGuaType"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepGuaTypeRemark())){
                        resultList.add(new ValidMessage("repGuaTypeRemark",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repGuaTypeRemark"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepFinancierGrade())){
                        resultList.add(new ValidMessage("repFinancierGrade",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFinancierGrade"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepInGrade())){
                        resultList.add(new ValidMessage("repInGrade",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repInGrade"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepOutGrade())){
                        resultList.add(new ValidMessage("repOutGrade",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repOutGrade"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepChose())){
                        resultList.add(new ValidMessage("repChose",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repChose"))));
                    }
                    /*if(StringUtil.isEmpty(dto.getRepConditionCmm())){
                        resultList.add(new ValidMessage("repConditionCmm",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repConditionCmm"))));
                    }*/
                    if(StringUtil.isEmpty(dto.getRepFinArea())){
                        resultList.add(new ValidMessage("repFinArea",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFinArea"))));
                    }
                /*if(null==dto.getRepTotalFeeRate()){
                    resultList.add(new ValidMessage("repTotalFeeRate",
                        getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repTotalFeeRate"))));
                }*/
                    if(StringUtil.isEmpty(dto.getRepFinOrgCode())){
                        resultList.add(new ValidMessage("repFinOrgCode",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFinOrgCode"))));
                    }else{
                        //String pattern = "[A-Z].*[0-9]|[0-9].*[A-Z]";
                        String pattern = "[A-Z0-9]+";
                        if(!Pattern.matches(pattern, dto.getRepFinOrgCode())){
                            resultList.add(new ValidMessage("repFinOrgCode",
                                    getMessage("FIN_ASSET_E_0027", getMessage("BussRepAssetCreditNonstdInfo.repFinOrgCode"))));
                        }else if(StringUtil.byteLength(dto.getRepFinOrgCode())!=9&&StringUtil.byteLength(dto.getRepFinOrgCode())!=18){
                            resultList.add(new ValidMessage("repFinOrgCode",
                                    getMessage("FIN_ASSET_E_0027", getMessage("BussRepAssetCreditNonstdInfo.repFinOrgCode"))));
                        }
                    }
                    if(StringUtil.isEmpty(dto.getRepProIndustry())){
                        resultList.add(new ValidMessage("repProIndustry",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repProIndustry"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepProIndustryLevel2())){
                        resultList.add(new ValidMessage("repProIndustryLevel2",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repProIndustryLevel2"))));
                    }
                    if(StringUtil.equals(dto.getRepTradeMarket(), DictCodeList.交易流通场所_其他交易流通场所.getValue())){
                        if(StringUtil.isEmpty(dto.getRepRemark())){
                            resultList.add(new ValidMessage("repRemark",
                                    getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repRemark"))));
                        }
                    }
                    if(StringUtil.isEmpty(dto.getRepRepayCmt())){
                        resultList.add(new ValidMessage("repRepayCmt",
                                getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repRepayCmt"))));
                    }
                    //中债新增 20200410 start
                    //计息类别
                    if(StringUtil.isEmpty(dto.getRepCouponSpecies())){
                    	resultList.add(new ValidMessage("repCouponSpecies",
                    			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repCouponSpecies"))));
                    }
                    //利息分布方式
                    if(StringUtil.isEmpty(dto.getRepIntpayRule())){
                    	resultList.add(new ValidMessage("repIntpayRule",
                    			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repIntpayRule"))));
                    }
                    //结构档次
                    /*if(StringUtil.isEmpty(dto.getRepStructGrade())){
                    	resultList.add(new ValidMessage("repStructGrade",
                    			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repStructGrade"))));
                    }*/
                    //还本方式
                    /*if(StringUtil.isEmpty(dto.getRepRepaymentType())){
                    	resultList.add(new ValidMessage("repRepaymentType",
                    			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repRepaymentType"))));
                    }*/
                    //分期还款标识
                    if(StringUtil.isEmpty(dto.getRepPeriodRepaymentFlag())){
                    	resultList.add(new ValidMessage("repPeriodRepaymentFlag",
                    			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repPeriodRepaymentFlag"))));
                    }
                    //含权类别
                    if(!StringUtil.isEmpty(dto.getRepContainType())){
                    	//行权方式
                    	if (StringUtil.isEmpty(dto.getRepExerciseType())) {
                    		resultList.add(new ValidMessage("repExerciseType",
                    				getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repExerciseType"))));
						}else if (StringUtil.equals(dto.getRepExerciseType(), DictCodeListExt1.行权方式_监管报送_欧式期权.getValue())) {
							//固定行权日
							if (null==dto.getRepExerciseDate()) {
	                    		resultList.add(new ValidMessage("repExerciseDate",
	                    				getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repExerciseDate"))));
							}
						}else if (StringUtil.equals(dto.getRepExerciseType(), DictCodeListExt1.行权方式_监管报送_百慕大期权.getValue())) {
							//首次行权日
							if (null==dto.getRepFirstExerciseDate()) {
	                    		resultList.add(new ValidMessage("repFirstExerciseDate",
	                    				getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFirstExerciseDate"))));
							}
							//行权周期
							if (null==dto.getRepExercisePeriod()) {
								resultList.add(new ValidMessage("repExercisePeriod",
										getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repExercisePeriod"))));
							}
						}
                        if (StringUtil.equals(dto.getRepContainType(), DictCodeListExt1.含权类型_发行人赎回选择权.getValue())
                                || StringUtil.equals(dto.getRepContainType(), DictCodeListExt1.含权类型_投资人回售选择权.getValue())) {
                            //行权价格
                            if (null==dto.getRepExercisePrice()) {
                                resultList.add(new ValidMessage("repExercisePrice",
                                        getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repExercisePrice"))));
                            }
                        }
                    }

                    //担保方式
                    if(StringUtil.equals(dto.getRepGuaType(), DictCodeListExt2.担保方式_抵押.getValue()) ||
                    		StringUtil.equals(dto.getRepGuaType(), DictCodeListExt2.担保方式_质押.getValue())){
                    	//抵质押物类型
                        if(StringUtil.isEmpty(dto.getRepPledgeType())){
                        	resultList.add(new ValidMessage("repPledgeType",
                        			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repPledgeType"))));
                        }
                        //抵质押物价值（元）
                        if(null==dto.getRepPledgeValue()){
                        	resultList.add(new ValidMessage("repPledgeValue",
                        			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repPledgeValue"))));
                        }
                    }else if (StringUtil.equals(dto.getRepGuaType(), DictCodeListExt2.担保方式_其他.getValue())) {
                    	//备注
                        if(StringUtil.isEmpty(dto.getRepRemark())){
                        	resultList.add(new ValidMessage("repRemark",
                        			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repRemark"))));
                        }
					}else if (StringUtil.equals(dto.getRepGuaType(), DictCodeListExt2.担保方式_信用担保.getValue()) ||
                    		StringUtil.equals(dto.getRepGuaType(), DictCodeListExt2.担保方式_第三方保证.getValue())) {
						//担保性质
                        if(StringUtil.isEmpty(dto.getRepNatureGuarantee())){
                        	resultList.add(new ValidMessage("repNatureGuarantee",
                        			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repNatureGuarantee"))));
                        }
                        //担保人与融资人关系
                        if(StringUtil.isEmpty(dto.getRepRelationFinan())){
                        	resultList.add(new ValidMessage("repRelationFinan",
                        			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repRelationFinan"))));
                        }
					}
                  //中债新增 20200410 end 
                    
                    if(!StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信贷资产流转项目.getValue())
                            && !StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_小微企业信贷资产流转和收益权转让.getValue())){
                    	//份额面值
                        if(null==dto.getRepPrinBaseamt()){
                        	resultList.add(new ValidMessage("repPrinBaseamt",
                        			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repPrinBaseamt"))));
                        }
                        
                        //永续条款类型
                        if(StringUtil.isEmpty(dto.getRepPerpetualClauseType())){
                        	resultList.add(new ValidMessage("repPerpetualClauseType",
                        			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repPerpetualClauseType"))));
                        }else if(StringUtil.equals(dto.getRepPerpetualClauseType(), DictCodeListExt1.永续条款类型_可续期条款.getValue())
                        		|| StringUtil.equals(dto.getRepPerpetualClauseType(), DictCodeListExt1.永续条款类型_永续条款.getValue())){
                        	//重定价日期
                        	if(null==dto.getRepFirstReprceDate()){
                            	resultList.add(new ValidMessage("repFirstReprceDate",
                            			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repFirstReprceDate"))));
                            }
                        	//重定价周期
                        	if(null==dto.getRepReprceCycle()){
                        		resultList.add(new ValidMessage("repReprceCycle",
                        				getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repReprceCycle"))));
                        	}
    					}
                        
                      	//利息递延条款类型
                        if(StringUtil.isEmpty(dto.getRepDeferredInterestType())){
                        	resultList.add(new ValidMessage("repDeferredInterestType",
                        			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repDeferredInterestType"))));
                        }else{
                            //当“递延利息条款类型”选择“02 有利息递延条款”或“03 非利递延条款引起的递延”时，递延利息是否计息栏位必填
    	                    if(StringUtil.equals(dto.getRepDeferredInterestType(),DictCodeListExt1.利息递延条款类型_有利息递延条款.getValue())
    	                    		||StringUtil.equals(dto.getRepDeferredInterestType(),DictCodeListExt1.利息递延条款类型_非利息递延条款引起的递延.getValue())){
    	                    		if(StringUtil.isEmpty(dto.getRepDeferredInterestInt())){
    	                    			resultList.add(new ValidMessage("repDeferredInterestInt",
    	                            			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repDeferredInterestInt"))));
    	                    	
    	                    		}
    	                       }
                        }
                        
                        //部分赎回标识
                        if(StringUtil.equals(dto.getRepPartRedemFlag(), DictCodeList.是否_中债_是.getValue())){
                        	if(!StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_信贷资产流转项目.getValue())){
                        		//部分赎回比例%
                            	if(null==dto.getRepPartRedemRate()){
                                	resultList.add(new ValidMessage("repPartRedemRate",
                                			getMessage("COMMON_E_0057", getMessage("BussRepAssetCreditNonstdInfo.repPartRedemRate"))));
                                }
                        	}
                        	
                        }
                        
                    }
            }
                
        }
        return resultList;
    }

}
