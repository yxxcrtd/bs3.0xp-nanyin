/*
 * 文件名：FinProductExpire001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.expire.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.productduration.expire.dto.FinProductExpire001Dto;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品到期查询
 * 
 * @author zhangliang
 * @version 1.0 2018年11月13日
 * @see FinProductExpire001Validator
 * @since 1.0
 */
@Component
public class FinProductExpire001Validator implements BeanValidator {

    /**
     * {@linkplain}
     */
    @Autowired
    private ProductCommonService productCommonService;
    
    /**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required = false)
    private LeaveMarkProcessService leaveMarkProcessService;
    
    @Autowired
    private IPlatformDate platformDateImpl;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductExpire001Dto finProductExpire001Dto = (FinProductExpire001Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();

        if (StringUtil.equals(finProductExpire001Dto.getOperateType(), "confirm")) {
            // 数据状态必须为待处理
            if (!StringUtil.equals(finProductExpire001Dto.getEffectStatusStr(),
                DictCodeListExt1.有效状态_待处理.getCodeName())) {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.有效状态_待处理.getCodeName())));
            }
        }
        else if (StringUtil.equals(finProductExpire001Dto.getOperateType(), "revoke")) {
            // 数据状态必须为有效
            if (!StringUtil.equals(finProductExpire001Dto.getEffectStatusStr(),
                DictCodeListExt1.有效状态_有效.getCodeName())) {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.有效状态_有效.getCodeName())));
            }
    		if(finProductExpire001Dto.getMdate().compareTo(platformDateImpl.getSysDate())<0) {
            	ValidMessage validMessage = new ValidMessage("截止日不能小于平台日期");
            	validList.add(validMessage);
            }  
            // 存在流程中数据
            if (WorkFlowUtil.isNotFromToDoList()) {
            	
            	//add by leipeng 20190628 【收益型产品赎回/到期】撤销，撤销交易被审核人退回到代办，这笔交易还可以继续撤销，提交时就报系统异常。 start
            	// 判断流程中是否存在相同的数据

                ProcessMark processMark = new ProcessMark();
                processMark.setBusinessDataPk1(finProductExpire001Dto.getFinprodId());
                processMark.setBusinessDataPk2(finProductExpire001Dto.getBusiNo());
                
                List<String> resultList =leaveMarkProcessService.getBusinessDataInFlow(processMark);
                
                if (!StringUtil.isEmptyList(resultList)) {
                	validList.add(new ValidMessage(this.getMessage("COMMON_E_0012", finProductExpire001Dto.getBusiNo())));
                }
                
              //add by leipeng 20190628 【收益型产品赎回/到期】撤销，撤销交易被审核人退回到代办，这笔交易还可以继续撤销，提交时就报系统异常。 end
            	
//                if (productCommonService.isInFlowDataExisted(
//                    finProductExpire001Dto.getBusiNo())) {
//                    validList.add(new ValidMessage(
//                        getMessage("COMMON_E_0012", finProductExpire001Dto.getBusiNo())));
//                }
            }

            // 删除状态不允许撤回
            if (productCommonService.isRevokeDataExisted(finProductExpire001Dto.getBusiNo())) {
                validList.add(new ValidMessage(getMessage("TRD_W_0001",
                    getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke"))));
            }
        }

        return validList;
    }

}
