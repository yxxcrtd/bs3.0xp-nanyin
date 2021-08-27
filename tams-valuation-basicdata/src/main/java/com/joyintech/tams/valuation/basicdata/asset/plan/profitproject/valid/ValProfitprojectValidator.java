/*
 * 文件名：FinPlanAssetCValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.plan.profitproject.valid;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.plan.profitproject.dto.ValProfitprojectDto;
import com.joyintech.tams.valuation.basicdata.asset.plan.profitproject.service.ValProfitprojectService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年1月7日
 * @see ValProfitprojectValidator
 * @since 1.0
 */
@Component
public class ValProfitprojectValidator implements BeanValidator {
    /**
     * 各种业务共通组件Service
     */
    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * finPlanAssetCService
     */
    @Autowired
    private ValProfitprojectService valProfitprojectService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ValProfitprojectDto dto = (ValProfitprojectDto) value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                for(ValProfitprojectDto e:dto.getSelectedItem()) {
                    if (commonBusinessService.isTransInAsset(e.getFinprodId())>0){
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0043")));
                    }
                    if(valProfitprojectService.getBasicInfoByFinprodId(e.getFinprodId()) == null){
                        errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0022")));
                    }
                }
            }
            else{
            	if(dto.getMdate() == null){
//                    errorList.add(
//                            new ValidMessage("mdate",this.getMessage("FW_E_0006", getMessage("FinPlanAssetC002.mDate"))));
                }
                if(dto.getVdate() != null && dto.getMdate() != null 
                    && dto.getVdate().compareTo(dto.getMdate()) >= 0){
//                    errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0035",
//                        getMessage("FinPlanAssetC002.vDate") + DateUtil.format(dto.getVdate()),
//                        getMessage("FinPlanAssetC002.mDate")+DateUtil.format(dto.getMdate()))));
                }
//                if(dto.getBussFinIntPayDto() != null && dto.getBussFinIntPayDto().getPayCycle() != null
//                    && StringUtil.equals(dto.getBussFinIntPayDto().getPayCycle(), DictCodeList.收益结转频率_按月结转.getValue())
//                    && (dto.getIsCompoundInt() == null || StringUtil.equals(dto.getIsCompoundInt(), ""))){
//                    errorList.add(
//                        new ValidMessage("isCompoundInt",this.getMessage("FW_E_0006", getMessage("FinPlanAssetC002.isCompoundInt"))));
//                }
//                if(StringUtil.equals(dto.getCreditFlag(), DictCodeList.是否_是.getValue())
//                    && (dto.getActualCreditProvid() == null || StringUtil.equals(dto.getActualCreditProvid(), ""))){
//                    errorList.add(
//                        new ValidMessage("actualCreditProvid",this.getMessage("FW_E_0006", getMessage("FinPlanAssetC002.actualCreditProvid"))));
//                }
                /*增加资产代码重复校验 add by zhuyongkang 20191021 start*/
                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    if (valProfitprojectService.selectFinprodMarketId(dto.getFinprodMarketId()) > 0) {
//                        errorList.add(new ValidMessage("finprodMarketId", getMessage("FIN_ASSET_E_0007", dto.getFinprodMarketId())));
                    }
                }
                if (dto.getMdate() != null && DateUtil.compareDate(dto.getMdate(), DateUtil.platDateFromCache()) < 0) {
                    errorList.add(new ValidMessage("mdate",
                            getMessage("BUSS_COM_E_0004", getMessage("ValProfitproject001.mDate"), getMessage("FinInterestFactor.platDate"))));
                }
                // 资产必须有还本计划
                if ( ((dto.getBussFinRepaymentDto() == null || dto.getBussFinRepaymentDto().getFinCashPlanList() == null
                        || dto.getBussFinRepaymentDto().getFinCashPlanList().size() == 0))) {
                    errorList.add(new ValidMessage(getMessage("还本计划不能为空!")));
                }
                if(StringUtil.equals(DictCodeListExt1.证券品种_计划类资产_收益型.getValue(), dto.getType18())) {
                    if (StringUtil.isEmpty(dto.getTransMode())) {
                        errorList.add(new ValidMessage(getMessage("清算速度不能为空!")));
                    }
                }
                if(StringUtil.isEmpty(dto.getIsEffPledge())){
                    errorList.add(new ValidMessage(getMessage("是否包含有效抵质押物不能为空!")));
                }
                if(StringUtil.isEmpty(dto.getBondRiskType())){
                    errorList.add(new ValidMessage(getMessage("风险五级分类不能为空!")));
                }
                if(StringUtil.equals(DictCodeListExt1.证券品种_其他债权类.getValue(), dto.getType18())) {
                    if (StringUtil.isEmpty(dto.getBondRiskType())) {
                        errorList.add(new ValidMessage(getMessage("融资人不能为空!")));
                    }
                }

            }
        }
        else {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                if (valProfitprojectService.selectFinprodMarketId(dto.getFinprodMarketId()) > 0) {
                    errorList.add(new ValidMessage("finprodMarketId", getMessage("FIN_ASSET_E_0007", dto.getFinprodMarketId())));
                }
            }
        }
        /*增加资产代码重复校验 add by zhuyongkang 20191021 end*/
		// 是否展示资产四五级分类
//		if (StringUtil.equals(CacheUtil.getSysParam("IS_SHOW_TYPE"), "01") &&  (StringUtil.equals(dto.getOperateType(), "save") ||  StringUtil.equals(dto.getOperateType(), "submit"))) {
//			if (StringUtil.isEmpty(dto.getType11())) {
//				errorList.add(new ValidMessage("type11",
//						getMessage("COMMON_E_0057", getMessage("FinPlanAssetC002.type11"))));
//			}
//			if (StringUtil.isEmpty(dto.getType12())) {
//				errorList.add(new ValidMessage("type12",
//						getMessage("COMMON_E_0057", getMessage("FinPlanAssetC002.type12"))));
//			}
//		}
//选公募计划时，是否投向金融资产投资公司发行的私募资产管理产品，必填 by zhuyongkang  20191010 start
//		if(StringUtil.equals(dto.getType(),DictCodeList.计划类资产二级分类_公募计划.getValue())) {
//            if (StringUtil.isNotEmpty(dto.getBussRepAssetMngProdInfo())) {
//                if (StringUtil.isEmpty(dto.getBussRepAssetMngProdInfo().getRepPrivateprdFlag())) {
//                    errorList.add(new ValidMessage("BussRepAssetMngProdInfo.repPrivateprdFlag",
//                            getMessage("FW_E_0006", getMessage("BussRepAssetMngProdInfo.repPrivateprdFlag"))));
//                }
//            }
//        }
        return errorList;
    }

}
