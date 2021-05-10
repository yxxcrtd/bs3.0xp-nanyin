/*
 * 文件名：FinDepositTargetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.depo.validator;


import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.valuation.basicdata.asset.depo.service.FinDepositTargetService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  存款类资产--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see com.joyintech.tams.valuation.basicdata.asset.depo.validator.FinDepositTargetValidator
 * @since 1.0
 */
@Component
public class FinDepositTargetValidator implements BeanValidator {

    @Autowired
    private CommonBusinessService commonBusinessService;
    @Autowired(required = false)
    private FinDepositTargetService finDepositTargetService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinDepositTargetDto dto = (FinDepositTargetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
                for(FinDepositTargetDto entity : dto.getSelectedItem()){
                    if(commonBusinessService.isTransInAsset(entity.getFinprodId()) != 0){
                        if(StringUtil.equals(DictCodeList.是否_是.getValue(), dto.getBatchDelFlag())) {
                            resultList.add(new ValidMessage("", getMessage("VAL_ASSET_DEL_INFO_002", entity.getFinprodId())));
                            //break;
                        }
                        else {
                            resultList.add(new ValidMessage("", getMessage("VAL_ASSET_DEL_INFO_003")));
                            //break;
                        }
                        continue;
                    }
                    if(!StringUtil.equals(DictCodeListExt3.估值数据来源_手工.getValue(), entity.getDataSource())) {
                        if(StringUtil.equals(DictCodeList.是否_是.getValue(), dto.getBatchDelFlag())) {
                            resultList.add(new ValidMessage("", getMessage("VAL_ASSET_DEL_INFO_001", entity.getFinprodId())));
                            //break;
                        }
                        else {
                            resultList.add(new ValidMessage("", getMessage("VAL_ASSET_DEL_INFO_004")));
                            //break;
                        }
                        continue;
                    }

                }
            }
        }else if(StringUtil.equals(dto.getOperateType(), "edit")
                && DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue().equals(dto.getAssetType())){

        }
        else{
//        	//有效状态资产不能保存  zhangyf 20190809 start
//            //update by  zhangnian  不能通过金融产品代码是否为空判断该数据是否是有效状态，新建保存到代办已生成金融产品代码
//        	if(StringUtil.equals(dto.getOperateType(), "save") && StringUtil.isNotEmpty(dto.getFinprodId())) {
//        	    if(null!=bussCommonMapperDelegateService
//                        .selectBySinglePrimaryKey(FinProductMapper.class, dto.getFinprodId(), FinProduct.class)){
//                    resultList.add(new ValidMessage("",getMessage("有效状态的资产可以提交，不能保存！")));
//                }
//        	    if(null!=mstWorkflowCommonService&& StringUtil.isNotEmpty(dto.getWorkflowId())){
//                    WflValidInputParameter param = new WflValidInputParameter();
//                    param.setWorkflowId(dto.getWorkflowId());
//                    String processState = mstWorkflowCommonService.getProcessState(param);
//                    if(StringUtil.equals(processState, ProcessState.BACK.toString())){
//                        resultList.add(new ValidMessage("",getMessage("审批被驳回的资产可以提交，不能保存！")));
//                    }
//                }
//        	}
//        	//有效状态资产不能保存  zhangyf 20190809 end
//            //新建，校验资产代码是否有重复
			if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
					&& StringUtil.isNotEmpty(dto.getFinprodMarketId())){
				// 组装金融产品代码
				FinDepositTargetDto finprodIdDto = finDepositTargetService.getFinprodId(dto);
				// 根据金融产品代码（主键）查询
				if(finDepositTargetService.selectCountByfinprodId(dto.getFinprodMarketId())>0){
                    // 资产代码已存在
                    resultList.add(new ValidMessage("",
                            this.getMessage("BUSS_COM_E_0006", getMessage("FinDepositTarget002.finprodId"),dto.getFinprodMarketId())));
                };
			}
            //起息日必须小于到期日
            if(null!=dto.getVdate()&&null!=dto.getMdate()){
                if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())!=-1){
                    resultList.add(new ValidMessage("vdate",
                            getMessage("BUSS_COM_E_0035", getMessage("FinDepositTarget002.vdate"),getMessage("FinDepositTarget002.mdate"))));
                }
                //到期日必须小于最大到期日
                if(StringUtil.isNotEmpty(dto.getFinprodId())){
                    Date maxTradeDate = commonBusinessService.getMaxTradeDate(dto.getFinprodId());
                    if(null!=maxTradeDate){
                        if(DateUtil.compareDate(dto.getMdate(), maxTradeDate)<1){
                            resultList.add(new ValidMessage("mdate",
                                    getMessage("BUSS_COM_E_0035", getMessage("FinDepositTarget002.mdate"),"最大交易日")));
                        }
                    }
                }
            }
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
                    && !StringUtil.isNotEmpty(dto.getOrgClass())){
                String msg = this.getMessage("VAL_COMMON_E_0002",
                        this.getMessage("FinDepositTarget002.issuer"));
                resultList.add(new ValidMessage(msg));
            }
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
                    && !StringUtil.isNotEmpty(dto.getBlnArea())){
                String msg = this.getMessage("VAL_COMMON_E_0002",
                        this.getMessage("FinDepositTarget002.blnArea"));
                resultList.add(new ValidMessage(msg));
            }
//            if(null!=dto.getMdate() && !StringUtil.isEmptyList(dto.getTable1())){
//                for(DateTableDto dateTableDto:dto.getTable1()){
//                    if(DateUtil.compareDate(DateUtil.parse(dateTableDto.getEffDate()), dto.getMdate())!=-1){
//                        resultList.add(new ValidMessage("effDate",
//                                getMessage("BUSS_COM_E_0003", getMessage("FinDepositTarget002.effDate"),getMessage("FinDepositTarget002.mdate"))));
//                    }
//                }
//            }
        }

         return resultList;
    }

}
