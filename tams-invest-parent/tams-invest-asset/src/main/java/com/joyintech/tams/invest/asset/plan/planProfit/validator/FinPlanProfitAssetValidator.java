package com.joyintech.tams.invest.asset.plan.planProfit.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.invest.asset.plan.planProfit.dto.FinPlanProfitAssetDto;
import com.joyintech.tams.invest.asset.plan.planProfit.service.FinPlanProfitAssetService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Component
public class FinPlanProfitAssetValidator implements BeanValidator {
    /**
     * 计划类资产（净值型）Service
     */
    @Autowired
    private FinPlanProfitAssetService finPlanProfitAssetService;

    /**
     * 各种业务共通组件Service
     */
    @Autowired
    private CommonBusinessService commonBusinessService;

	@Autowired
	private FinProductMapper finProductMapper;

	@Autowired
	private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    @Override
    public List<ValidMessage> valid(Object value) {
        FinPlanProfitAssetDto dto = (FinPlanProfitAssetDto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        
        if (!StringUtil.equals(dto.getOperateType(), "delete")) {
            if(StringUtil.isNotEmpty(dto.getBussRepAssetMngProdInfo()) && StringUtil.equals(dto.getBussRepAssetMngProdInfo().getRepAssetType(),"1705")) {
                if (StringUtil.isEmpty(dto.getBussRepAssetMngProdInfo().getRepFinancialComissue()) || dto.getBussRepAssetMngProdInfo().getRepFinancialComissue() == "") {
                    errorList.add(new ValidMessage("BussRepAssetMngProdInfo.repFinancialComissue",
                            "请输入是否由金融资产投资公司发行。"));
                }
            }    
        }
        if (StringUtil.isNotEmpty(dto.getPageModel())) {
	        if(dto.getVdate()!=null && dto.getMdate()!=null && dto.getVdate().compareTo(dto.getMdate()) >= 0){
	            errorList.add(new ValidMessage("vdate",getMessage("BUSS_COM_E_0035",getMessage("FinPlanProfitAsset002.vdate"),
	                getMessage("FinPlanProfitAsset002.mdate"))));
	        }
	        if (!GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())) {
				//非母计划  买断利率必填
				if (StringUtil.equals(dto.getParentPlan(), DictCodeList.是否_否.getValue())) {
					if (null == dto.getBuyOutRate()) {
						errorList.add(new ValidMessage("buyOutRate",
								getMessage("COMMON_E_0057", getMessage("FinPlanProfitAsset002.buyOutRate"))));
					}
					if (null == dto.getIssueAmt()) {
						errorList.add(new ValidMessage("issueAmt",
								getMessage("COMMON_E_0057", getMessage("FinPlanProfitAsset002.issueAmt"))));
					}
				}
			}
	        if (WorkFlowUtil.isNotFromToDoList()) {
	            if (StringUtil.equals(dto.getOperateType(), "delete")) {
	                //if (dto.getSelectedItem() != null || dto.getSelectedItem().size() > 0) {
	                //    for(FinPlanProfitAssetDto dto1:dto.getSelectedItem()) {
	                //        if (commonBusinessService.isTransInAsset(dto1.getFinprodId())>0){
	                //            errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0043")));
	                //        }
	                //    }
	                //}
            } 
            else{
                if(StringUtil.equals(dto.getCreditFlag(), DictCodeList.是否_是.getValue())
                    && (dto.getActualCreditProvid() == null || StringUtil.equals(dto.getActualCreditProvid(), ""))
					&&"02".equals(CacheUtil.getSysParam(BussinessConstant.CEMODEL))){
                    errorList.add(
                        new ValidMessage("actualCreditProvid",this.getMessage("FW_E_0006", getMessage("FinPlanAssetC002.actualCreditProvid"))));
                }
                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    if (finPlanProfitAssetService.selectFinprodMarketId(dto.getFinprodMarketId()) > 0) {
						errorList.add(new ValidMessage("finprodMarketId",getMessage("FIN_ASSET_E_0007", dto.getFinprodMarketId())));
                    }
					//     附件上传改为非必填 by zhuyongkang 20191010 start
//                    if(dto.getFileCommonList().size()==0){
//                        errorList.add(new ValidMessage(getMessage("FIN_ASSET_E_0003")));
//                    }
                }
            }
            
	        } else {
	            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
	                if (finPlanProfitAssetService.selectFinprodMarketId(dto.getFinprodMarketId()) > 0) {
						errorList.add(new ValidMessage("finprodMarketId", getMessage("FIN_ASSET_E_0007", dto.getFinprodMarketId())));
	                }
	            }
	        }
        }
		if (StringUtil.equals(dto.getOperateType(), "delete")) {
			if (dto.getSelectedItem() != null || dto.getSelectedItem().size() > 0) {
				for(FinPlanProfitAssetDto dto1:dto.getSelectedItem()) {
					if (commonBusinessService.isTransInAsset(dto1.getFinprodId())>0){
						errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0043")));
					}

					// 校验子计划有没有交易
					List<String> subProd = finProductMapper.getSubProd(dto1.getFinprodId());
					if(!StringUtil.isEmptyList(subProd)){
						for (String subProdId: subProd) {
							if (commonBusinessService.isTransInAsset(subProdId)>0){
								errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0061",subProdId)));
							}
						}
					}

				}
			}
		}
		// 是否展示资产四五级分类
		if (StringUtil.equals(CacheUtil.getSysParam("IS_SHOW_TYPE"), "01") &&  (StringUtil.equals(dto.getOperateType(), "save") ||  StringUtil.equals(dto.getOperateType(), "submit"))) {
			if (StringUtil.isEmpty(dto.getType11())) {
				errorList.add(new ValidMessage("type11",
						getMessage("COMMON_E_0057", getMessage("FinPlanProfitAsset002.type11"))));
			}
			if (StringUtil.isEmpty(dto.getType12())) {
				errorList.add(new ValidMessage("type12",
						getMessage("COMMON_E_0057", getMessage("FinPlanProfitAsset002.type12"))));
			}
		}
		// 根据二级分类的值判断是否投向金融资产投资公司发行的私募资产管理产品是必填还是选填 by 赵东东 2019.10.16
		if(StringUtil.equals(dto.getType(),DictCodeList.计划类资产二级分类_公募计划.getValue())) {
			// 编辑时BussRepAssetMngProdInfo为空，不check by 赵东东 2019.10.16 
			if(StringUtil.isNotEmpty(dto.getBussRepAssetMngProdInfo())) {
			if(StringUtil.isEmpty(dto.getBussRepAssetMngProdInfo().getRepPrivateprdFlag())) {
				errorList.add(new ValidMessage("BussRepAssetMngProdInfo.repPrivateprdFlag",
						getMessage("FIN_ASSET_E_0066", getMessage("FinPlanNetAsset002.type"))));
			}
		}
		}

//		if (dto.getOperateType() != null && !StringUtil.equals(dto.getOperateType(), "delete")) {
//			errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, value, null, dto.getRepAssetType(), ValidMessage.class);
//		}

		return errorList;
	}
}
