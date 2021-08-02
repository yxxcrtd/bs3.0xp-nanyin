/*
 * 文件名：FinDepositTargetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.assetinfodetail.validator;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.AssetInfoDetailRisk001Dto;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  存款类资产--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see FinDepositTargetValidator
 * @since 1.0
 */
@Component
public class AssetInfoDetailRiskValidator implements BeanValidator{


    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    /**
     * 资产要素service
     */
    @Autowired
    private AssetInfoDetailRisk001Service assetInfoDetailRisk001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	AssetInfoDetailRisk001Dto dto = (AssetInfoDetailRisk001Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        //如果资产没有资产编号.默认为新增.检查是否存在对应资产
        if(StringUtil.isEmpty(dto.getAssetUuid())){
        	AssetInfoDetailRisk001Dto resultDto = assetInfoDetailRisk001Service.queryAssetInfo(dto.getAssetCode());
            if(null!=resultDto){
                resultList.add(new ValidMessage("assetCode","资产编码已存在"));
            }
        }
        
        if(!StringUtil.isNotEmpty(dto.getAssetType2())) {
      		resultList.add(new ValidMessage("assetType2",
      				getMessage("COMMON_E_0057", getMessage("资产负债二级类别")))); 
      		if(!StringUtil.isNotEmpty(dto.getAssetCode())) {
          		resultList.add(new ValidMessage("assetCode",
          				getMessage("COMMON_E_0057", getMessage("行内资产/负债编码"))));      		
          	}
          	if(!StringUtil.isNotEmpty(dto.getAssetName())) {
          		resultList.add(new ValidMessage("assetName",
          				getMessage("COMMON_E_0057", getMessage("资产名称"))));      		
          	}
          	if(!StringUtil.isNotEmpty(dto.getCcy())) {
          		resultList.add(new ValidMessage("ccy",
          				getMessage("COMMON_E_0057", getMessage("币种"))));      		
          	}
          	if(!StringUtil.isNotEmpty(dto.getTradeMarket())) {
          		resultList.add(new ValidMessage("tradeMarket",
          				getMessage("COMMON_E_0057", getMessage("交易流通场所"))));      		
          	}
          	if(!StringUtil.isNotEmpty(dto.getIssuePartyId())) {
          		resultList.add(new ValidMessage("issuePartyId",
          				getMessage("COMMON_E_0057", getMessage("发行机构代码"))));      		
          	}            	
          		if(!StringUtil.isNotEmpty(dto.getVdate())) {
              		resultList.add(new ValidMessage("vdate",
              				getMessage("COMMON_E_0057", getMessage("起息日"))));      		
              	} 
          		if(!StringUtil.isNotEmpty(dto.getMdate())) {
              		resultList.add(new ValidMessage("mdate",
              				getMessage("COMMON_E_0057", getMessage("到期日"))));      		
              	}          
        }else {
       //权益类资产字段必填校验
        //股票代码必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getAssetId())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.assetId",
        				getMessage("COMMON_E_0057", getMessage("股票代码"))));      		
        	}
      }
      //股票/企业名称必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getAssetName())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.assetName",
        				getMessage("COMMON_E_0057", getMessage("股票/企业名称"))));      		
        	}
      }
      //股票类型必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getSharesType())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.sharesType",
        				getMessage("COMMON_E_0057", getMessage("股票类型"))));      		
        	}
      }
      //行业一级分类必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getIndustryShares())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.industryShares",
        				getMessage("COMMON_E_0057", getMessage("行业一级分类"))));      		
        	}
      }
      //行业二级分类必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getIndustryShares2())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.industryShares2",
        				getMessage("COMMON_E_0057", getMessage("行业二级分类"))));      		
        	}
      }
      //投资阶段必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getInvestStage())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.investStage",
        				getMessage("COMMON_E_0057", getMessage("投资阶段"))));      		
        	}
      }
    //企业类型（按规模划分）必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getSharesType1())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.sharesType1",
        				getMessage("COMMON_E_0057", getMessage("企业类型（按规模划分）"))));      		
        	}
      }
      //企业类型（按技术领域划分）必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getSharesType3())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.sharesType3",
        				getMessage("COMMON_E_0057", getMessage("企业类型（按技术领域划分）"))));      		
        	}
      }
    //企业类型（按经济类型划分）必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getSharesType2())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.sharesType2",
        				getMessage("COMMON_E_0057", getMessage("企业类型（按经济类型划分）"))));      		
        	}
      }  
    //是否为质押融资必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getIsPledge())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.isPledge",
        				getMessage("COMMON_E_0057", getMessage("是否为质押融资"))));      		
        	}
      }
    //是否为债转股(权益类资产)必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getDeptEquity())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.deptEquity",
        				getMessage("COMMON_E_0057", getMessage("是否为债转股(权益类资产)"))));      		
        	}
      }
      //备注必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1305")||StringUtil.equals(dto.getAssetType2(), "1302")
    		  ||StringUtil.equals(dto.getAssetType2(), "1301")){
    	  if(StringUtil.equals(dto.getSvrBussRepEquityInfoRiskDto().getSharesType1(),"99") 
    			  || StringUtil.equals(dto.getSvrBussRepEquityInfoRiskDto().getSharesType2(),"99")
    			  || StringUtil.equals(dto.getSvrBussRepEquityInfoRiskDto().getSharesType3(),"99")
				  || StringUtil.equals(dto.getSvrBussRepEquityInfoRiskDto().getSharesType(),"99")) {
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepEquityInfoRiskDto().getRemark())) {
        		resultList.add(new ValidMessage("svrBussRepEquityInfoRiskDto.remark",
        				getMessage("COMMON_E_0057", getMessage("备注"))));      		
        	}
         }
      }
      //QDII债券资产字段必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1501")){
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getCountryArea())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.countryArea",
        				getMessage("COMMON_E_0057", getMessage("所属国家或地区"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getAssetName())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.assetName",
        				getMessage("COMMON_E_0057", getMessage("债券名称"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getAssetId())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.assetId",
        				getMessage("COMMON_E_0057", getMessage("债券代码"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getIssuePartyName())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.issuePartyName",
        				getMessage("COMMON_E_0057", getMessage("发行机构"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getIndustry())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.industry",
        				getMessage("COMMON_E_0057", getMessage("发行机构所属一级行业"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getTermMonth())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.termMonth",
        				getMessage("COMMON_E_0057", getMessage("期限（月）"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getPartyGrade())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.partyGrade",
        				getMessage("COMMON_E_0057", getMessage("发行机构主体信用评级"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getSecCreditGradeType())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.secCreditGradeType",
        				getMessage("COMMON_E_0057", getMessage("债券信用评级"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getAssetYield())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.assetYield",
        				getMessage("COMMON_E_0057", getMessage("票面利率%"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getIntpayCycle())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.intpayCycle",
        				getMessage("COMMON_E_0057", getMessage("付息频率（个月/次）"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getGuaTypeRemark())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.guaTypeRemark",
        				getMessage("COMMON_E_0057", getMessage("担保情况说明"))));      		
        	}
        	if(!StringUtil.isNotEmpty(dto.getSvrBussRepQDIIBondInfoRiskDto().getSpecialCmt())) {
        		resultList.add(new ValidMessage("svrBussRepQDIIBondInfoRiskDto.specialCmt",
        				getMessage("COMMON_E_0057", getMessage("是否含权等特殊条款情况说明"))));      		
        	}

      }
      //金融衍生品字段必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1402")||StringUtil.equals(dto.getAssetType2(), "1404")
    		  ||StringUtil.equals(dto.getAssetType2(), "1404")){
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussRepFinanciallInfoRiskDto().getNomCapi())) {
      		resultList.add(new ValidMessage("svrBussRepFinanciallInfoRiskDto.nomCapi",
      				getMessage("COMMON_E_0057", getMessage("名义本金"))));      		
      	   }
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussRepFinanciallInfoRiskDto().getTargetType3())) {
        		resultList.add(new ValidMessage("svrBussRepFinanciallInfoRiskDto.targetType3",
        				getMessage("COMMON_E_0057", getMessage("标的类别"))));      		
        	}
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussRepFinanciallInfoRiskDto().getHoldPurpose())) {
      		resultList.add(new ValidMessage("svrBussRepFinanciallInfoRiskDto.holdPurpose",
      				getMessage("COMMON_E_0057", getMessage("持有目的"))));      		
      	   }
    	  if(StringUtil.equals(dto.getSvrBussRepFinanciallInfoRiskDto().getTargetType3(),"99") 
    			  ||StringUtil.equals(dto.getSvrBussRepFinanciallInfoRiskDto().getHoldPurpose(),"99") ) {
    		  if(!StringUtil.isNotEmpty(dto.getSvrBussRepFinanciallInfoRiskDto().getRemark())) {
    	      		resultList.add(new ValidMessage("svrBussRepFinanciallInfoRiskDto.remark",
    	      				getMessage("COMMON_E_0057", getMessage("备注"))));      		
    	      	   } 		
           }
    	  
       }
      //QDII股票 基金类资产字段必填校验
      if(StringUtil.equals(dto.getAssetType2(), "1502")||StringUtil.equals(dto.getAssetType2(), "1505")
    		  ||StringUtil.equals(dto.getAssetType2(), "1507")
    		  ||StringUtil.equals(dto.getAssetType2(), "1508")
    		  ||StringUtil.equals(dto.getAssetType2(), "1509")){
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussQDIISharesInfoRiskDto().getCountryArea())) {
        		resultList.add(new ValidMessage("svrBussQDIISharesInfoRiskDto.countryArea",
        				getMessage("COMMON_E_0057", getMessage("所属国家或地区"))));      		
        	   }
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussQDIISharesInfoRiskDto().getAssetId())) {
      		resultList.add(new ValidMessage("svrBussQDIISharesInfoRiskDto.assetId",
      				getMessage("COMMON_E_0057", getMessage("股票/基金代码"))));      		
      	   }
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussQDIISharesInfoRiskDto().getAssetName())) {
      		resultList.add(new ValidMessage("svrBussQDIISharesInfoRiskDto.assetName",
      				getMessage("COMMON_E_0057", getMessage("股票/基金名称"))));      		
      	   }
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussQDIISharesInfoRiskDto().getIssuePartyName())) {
      		resultList.add(new ValidMessage("svrBussQDIISharesInfoRiskDto.issuePartyName",
      				getMessage("COMMON_E_0057", getMessage("发行机构"))));      		
      	   }
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussQDIISharesInfoRiskDto().getIndustryShares())) {
      		resultList.add(new ValidMessage("svrBussQDIISharesInfoRiskDto.industryShares",
      				getMessage("COMMON_E_0057", getMessage("行业一级分类"))));      		
      	   }
    	  if(!StringUtil.isNotEmpty(dto.getSvrBussQDIISharesInfoRiskDto().getIndustryShares2())) {
      		resultList.add(new ValidMessage("svrBussQDIISharesInfoRiskDto.industryShares2",
      				getMessage("COMMON_E_0057", getMessage("行业二级分类"))));      		
      	   }
		  if(!StringUtil.isNotEmpty(dto.getSvrBussQDIISharesInfoRiskDto().getInvestNature())) {
			  resultList.add(new ValidMessage("svrBussQDIISharesInfoRiskDto.investNature",
					  getMessage("COMMON_E_0057", getMessage("资产投资性质"))));
		  }
      }
      //基本信息字段必填校验
      	if(!StringUtil.isNotEmpty(dto.getAssetCode())) {
      		resultList.add(new ValidMessage("assetCode",
      				getMessage("COMMON_E_0057", getMessage("行内资产/负债编码"))));      		
      	}
      	if(!StringUtil.isNotEmpty(dto.getAssetName())) {
      		resultList.add(new ValidMessage("assetName",
      				getMessage("COMMON_E_0057", getMessage("资产名称"))));      		
      	}
      	if(!StringUtil.isNotEmpty(dto.getCcy())) {
      		resultList.add(new ValidMessage("ccy",
      				getMessage("COMMON_E_0057", getMessage("币种"))));      		
      	}
      	if(!StringUtil.isNotEmpty(dto.getTradeMarket())) {
      		resultList.add(new ValidMessage("tradeMarket",
      				getMessage("COMMON_E_0057", getMessage("交易流通场所"))));      		
      	}
      	if(!StringUtil.isNotEmpty(dto.getIssuePartyId())) {
      		resultList.add(new ValidMessage("issuePartyId",
      				getMessage("COMMON_E_0057", getMessage("发行机构代码"))));      		
      	}  
      	 if(!StringUtil.equals(dto.getAssetType2(), "1302")&&!StringUtil.equals(dto.getAssetType2(), "1305")){
      		if(!StringUtil.isNotEmpty(dto.getVdate())) {
          		resultList.add(new ValidMessage("vdate",
          				getMessage("COMMON_E_0057", getMessage("起息日"))));      		
          	} 
      		if(!StringUtil.isNotEmpty(dto.getMdate())) {
          		resultList.add(new ValidMessage("mdate",
          				getMessage("COMMON_E_0057", getMessage("到期日"))));      		
          	} 
      	 }

   }
      
      return resultList;
    }

}
