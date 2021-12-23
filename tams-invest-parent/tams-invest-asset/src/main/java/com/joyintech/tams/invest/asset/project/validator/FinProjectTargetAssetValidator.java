/*
 * 文件名：FinProjectTargetAssetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.project.validator;

import com.joyintech.framework.base.intf.Page;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.asset.project.dto.FinProjectTargetAssetDto;
import com.joyintech.tams.invest.asset.project.service.FinProjectTargetAssetService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.service.InvDataCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *  项目类资产--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see FinProjectTargetAssetValidator
 * @since 1.0
 */
@Component
public class FinProjectTargetAssetValidator implements BeanValidator{

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Autowired
    private FinProjectTargetAssetService finProjectTargetAssetService;

    @Autowired
    private InvDataCheckService chbInvDataCheckService;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProjectTargetAssetDto dto = (FinProjectTargetAssetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
                for(FinProjectTargetAssetDto entity : dto.getSelectedItem()){
                    if(commonBusinessService.isTransInAsset(entity.getFinprodId())!=0){
                        resultList.add(new ValidMessage("",
                            getMessage("资产存在交易,不可删除!")));
                        break;
                    }
                }
            }
        }
        else{
            if (StringUtil.isNotEmpty(dto.getFinprodMarketId()) && GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())) {
                String finprodId = finProjectTargetAssetService.checkMarketExists(dto.getFinprodMarketId());
                if (StringUtil.isNotEmpty(finprodId)) {
                    resultList.add(
                            new ValidMessage("finprodMarketId", "资产代码已存在。"));
                }
            }
           if(StringUtil.equals("02", CacheUtil.getSysParam(BussinessConstant.CEMODEL))){
               if(StringUtil.equals(dto.getCreditFlag(), DictCodeList.是否_是.getValue())
                       && (dto.getActualCreditProvid() == null || StringUtil.equals(dto.getActualCreditProvid(), ""))){
                   resultList.add(
                           new ValidMessage("actualCreditProvid",this.getMessage("FW_E_0006", getMessage("FinPlanAssetC002.actualCreditProvid"))));
               }
           }
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
                || StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                if(dto != null && dto.getType6() != null
                    && (StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_非标债权.getValue())
                        || StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_结构化证券.getValue())
                        || StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票定向增发投资.getValue())
                        || StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票质押式回购.getValue()))){
                    if(StringUtil.isEmpty(dto.getProjInvestIndustry())){
                        resultList.add(new ValidMessage("projInvestIndustry",
                            getMessage("COMMON_E_0057", getMessage("FinProjectTargetAsset002.projInvestIndustry"))));
                    }
                    if(StringUtil.isEmpty(dto.getProjInvestIndustry2())){
                        resultList.add(new ValidMessage("projInvestIndustry2",
                            getMessage("COMMON_E_0057", getMessage("FinProjectTargetAsset002.projInvestIndustry2"))));
                    }
                }
                Page page = CacheUtil.getPage("FinProjectTargetAsset002");
                //mod by wangyaning 20211022 start
                // modify by etliu 20211223 start
                // SYS_ELEMENT_CONTROL表没有配置，认为是显示，需要做check
//                if (null != page && null != page.getElementcontrol("bussRepProjectInfo") && page.getElementcontrol("bussRepProjectInfo").getIsShow()) {
                if (null != page && ((null != page.getElementcontrol("bussRepProjectInfo") && page.getElementcontrol("bussRepProjectInfo").getIsShow())
                    || page.getElementcontrol("bussRepProjectInfo") == null)) {
                // modify by etliu 20211223 end
                    if(StringUtil.isNotEmpty(dto.getRepAssetType())
                            && StringUtil.equals(dto.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_股权.getValue())){
                        if(dto.getBussRepEquityInfoDto()!=null && !StringUtil.isNotEmpty(dto.getBussRepEquityInfoDto().getIsUnlisted())){
                            resultList.add(new ValidMessage("bussRepEquityInfoDto.isUnlisted",
                                    getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.isUnlisted"))));
                        }
                    }
                    if(StringUtil.isNotEmpty(dto.getRepAssetType())
                            && StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())){
                        if(dto.getBussRepOtherInvestTargetInfoDto()!=null && !StringUtil.isNotEmpty(dto.getBussRepOtherInvestTargetInfoDto().getIsUnlisted())){
                            resultList.add(new ValidMessage("bussRepOtherInvestTargetInfoDto.isUnlisted",
                                    getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.isUnlisted"))));
                        }
                    }
                    if(StringUtil.isNotEmpty(dto.getRepAssetType())
                            && StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.项目类资产二级类型_票据类.getValue())){
                        if(dto.getBussRepAssetAstBillInfoDto()!=null && !StringUtil.isNotEmpty(dto.getBussRepAssetAstBillInfoDto().getIsBzhpj())){
                            resultList.add(new ValidMessage("bussRepAssetAstBillInfoDto.isBzhpj",
                                    getMessage("COMMON_E_0057", getMessage("BussRepAssetAstBillInfo.isBzhpj"))));
                        }
                    }
                }
                //mod by wangyaning 20211022 end
                /****1、需要增加对评级信息的必填项check,评级信息中若没有主体评级的记录则不允许提交且给出提示；***/
                //ZGBSSE-576   【项目类标的】新建项目类标的，发行人已维护评级信息，点击提交时，系统仍提示：评级信息中必须包含主体评级信息! 项目类不再验证
//                boolean flag = true;
//                if(null!=dto.getBusiFinBondRatingInfoDto()&&null!=dto.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()){
//                    for(MstBondCreditRating dao :dto.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList()){
//                        if(!DictCodeList.评级类型_主体评级.getValue().equals(dao.getoType())){
//                            flag = false;
//                            break;
//                        }
//                    }
//                }
//                if(flag){
//                    resultList.add(new ValidMessage(getMessage("评级信息中必须包含主体评级信息!")));
//                }
                /****1、需要增加对评级信息的必填项check,评级信息中若没有主体评级的记录则不允许提交且给出提示；***/
            }

            //编辑前检查
            if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
                for(FinProjectTargetAssetDto entity : dto.getSelectedItem()){
                	if(StringUtil.equals(entity.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                    	//到期日小于平台日的标的不可编辑
                    	if(DateUtil.compareDate(DateUtil.platDateFromCache(), entity.getMdate()) > 0){
                            resultList.add(new ValidMessage("该项目类标的已到期，不能修改！"));
                        }
                    }
                }
            }

            //修改保存前检查
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            	//到期日不能小于平台日的标的不可编辑
            	if(DateUtil.compareDate(dto.getMdate(), DateUtil.platDateFromCache()) < 0){
                    resultList.add(new ValidMessage("到期日不能小于平台日！"));
                }
            }
        }
        // 项目类资产五级分类改造 delete by renping 2019-08-09 start
//        if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_非标债权.getValue())){
//            if(StringUtil.isEmpty(dto.getType7())){
//                resultList.add(new ValidMessage("type7",
//                    getMessage("COMMON_E_0057", getMessage("FinProjectTargetAsset002.type7"))));
//            }
//        }
        // 项目类资产五级分类改造 delete by renping 2019-08-09 end
        if(null!=dto.getVdate()&&null!=dto.getMdate()){
            if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())!=-1){
                resultList.add(new ValidMessage("",
                    getMessage("BUSS_COM_E_0003", getMessage("FinProjectTargetAsset002.vdate"),getMessage("FinProjectTargetAsset002.mdate"))));
            }
            if(StringUtil.isNotEmpty(dto.getFinprodId())){
                Date maxTradeDate = commonBusinessService.getMaxTradeDate(dto.getFinprodId());
                if(null!=maxTradeDate){
                    if(DateUtil.compareDate(dto.getMdate(), maxTradeDate)<1){
                        resultList.add(new ValidMessage("mdate",
                            getMessage("BUSS_COM_E_0036", getMessage("FinDepositTarget002.mdate"),"最大交易日")));
                    }
                }
            }
        }
		// 是否展示资产四五级分类
		if (StringUtil.equals(CacheUtil.getSysParam("IS_SHOW_TYPE"), "01") &&  (StringUtil.equals(dto.getOperateType(), "save") ||  StringUtil.equals(dto.getOperateType(), "submit"))) {
			if (StringUtil.isEmpty(dto.getType11())) {
				resultList.add(new ValidMessage("type11",
						getMessage("COMMON_E_0057", getMessage("FinProjectTargetAsset002.type11"))));
			}
			if (StringUtil.isEmpty(dto.getType12())) {
				resultList.add(new ValidMessage("type12",
						getMessage("COMMON_E_0057", getMessage("FinProjectTargetAsset002.type12"))));
			}
		}
		if (!StringUtil.isNotEmpty(dto.getBusiFinBondRatingInfoDto())){
            return resultList;
        }
        List<MstBondCreditRating> mstBondCreditRatingList = dto.getBusiFinBondRatingInfoDto().getMstBondCreditRatingList();
//        校验重复的评级信息
        Set<String> keySet = new HashSet<>();
        if(null!=mstBondCreditRatingList&&!mstBondCreditRatingList.isEmpty()){
            for(MstBondCreditRating entity:mstBondCreditRatingList){
                //评级类型不可为空
                if(StringUtil.isEmpty(entity.getoType())){
                    resultList.add(new ValidMessage("",
                            getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.gradeType"))));
                    break;
                }
                //评级结果不可为空
                if(StringUtil.isEmpty(entity.getGradeResult())){
                    resultList.add(new ValidMessage("",
                            getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.gradeResult"))));
                    break;
                }
//                if(null==entity.getGradeDate()){
//                    resultList.add(new ValidMessage("",
//                            getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.gradeDate"))));
//                    break;
//                }
//                if(StringUtil.isEmpty(entity.getGradeOrgId())){
//                    resultList.add(new ValidMessage("",
//                            getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.gradeOrgId"))));
//                    break;
//                }
//                MstBondCreditRating newEntity = new MstBondCreditRating();
//                newEntity.setGradeOrgId(entity.getGradeOrgId());
//                newEntity.setGradeDate(entity.getGradeDate());
//                newEntity.setGradeType(entity.getGradeType());
//                增加check 同一评级机构、评级日期、评级类型只能有一条数据，不能重复。
//                原检验逻辑 未重写hsahcode 方法导致校验失败
                StringBuffer sb = new StringBuffer();
                sb.append(entity.getGradeOrgId()).append(GlobalConstant.VERTICAL_LINE).append(DateUtil.format(entity.getGradeDate())).append(GlobalConstant.VERTICAL_LINE).append(entity.getoType());
                if(!keySet.add(sb.toString())){
                    resultList.add(new ValidMessage("",
                            getMessage("FIN_ASSET_E_0018")));
                    break;
                }
//                if(!set.add(newEntity)){
//                    resultList.add(new ValidMessage("",
//                        getMessage("FIN_ASSET_E_0018")));
//                }
            }
        }

        if (dto.getOperateType() != null && !StringUtil.equals(dto.getOperateType(), "delete")) {
            //信贷及非标资产报送信息
            if (dto.getBussRepAssetCreditNonstdInfoDto() != null) {
                dto.getBussRepAssetCreditNonstdInfoDto().setRepPartRedemFlag(coverNo(dto.getBussRepAssetCreditNonstdInfoDto().getRepPartRedemFlag()));
            }
            resultList = chbInvDataCheckService.chbCheckCommon(resultList, dto, null, dto.getRepAssetType());
        }

        //校验融资人
        //@NotEmpty(fieldName = "FinProjectTargetAsset002.financier",groups={ValidateGroupEdit.class})
        if (dto.getOperateType() != null && !StringUtil.equals(dto.getOperateType(), "delete")) {
        	if((!StringUtil.isNotEmpty(dto.isFinancierIsHid())||!dto.isFinancierIsHid())&&(!StringUtil.isNotEmpty(dto.getFinancier()))) {
        		 ValidMessage message = new ValidMessage(getMessage("FW_E_0006", getMessage("FinProjectTargetAsset002.financier")));
        		 message.setField("financier");
        		 message.setOrder(0);
        		 resultList.add(message);
        	}
        }
        // 校验还款周期说明
        if (dto.getBussFinRepaymentDto() != null
                && StringUtil.isNotEmpty(dto.getBussFinRepaymentDto().getPayCycle())
                && DictCodeList.频率_按明确时间的还款计划还本.getValue().equals(dto.getBussFinRepaymentDto().getPayCycle())) {
            if (StringUtil.isEmpty(dto.getBussFinRepaymentDto().getRemark())) {
                ValidMessage message = new ValidMessage(getMessage("FW_E_0006", getMessage("FinProjectTargetAsset002.repaymentRemark")));
                message.setField("bussFinRepaymentDto.remark");
                message.setOrder(0);
                resultList.add(message);
            }
        }

        return resultList;
    }

    /**
     * 01 转换成 Y
     * @param oriStr 原数据N
     * @return
     */
    private String coverNo(String oriStr){
        if (StringUtil.equals(oriStr, DictCodeList.是否_中债_否.getValue())){
            return DictCodeList.是否_否.getValue();
        }else if(StringUtil.equals(oriStr, DictCodeList.是否_中债_是.getValue())){
            return DictCodeList.是否_是.getValue();
        } else{
            return oriStr;
        }

    }

}
