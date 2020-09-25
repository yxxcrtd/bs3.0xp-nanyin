/*
 * 文件名：FinProductRateQuotationValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finproductratequotation.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.service.FinProductRateQuotationService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品费率报价管理check
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月15日
 * @see FinProductRateQuotationValidator
 * @since 1.0
 */
@Component
public class FinProductRateQuotationValidator implements BeanValidator{
    
    /**
     * 产品费率报价管理Service
     */
    @Autowired
    private FinProductRateQuotationService finProductRateQuotationService;
    
    /**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required=false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private ProductCommonService productCommonService;
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        FinProductRateQuotationDto finProductRateQuotationDto = (FinProductRateQuotationDto)value;
        //if (WorkFlowUtil.isNotFromToDoList()) {
        if (StringUtil.equals(finProductRateQuotationDto.getOperateType(), "delete")) {
            for(FinProductRateQuotationDto temp:finProductRateQuotationDto.getSelectedItem()){
                FinProductRateQuotationDto dto = finProductRateQuotationService.selectDetail(temp.getCashId(), 
                    temp.getFinprodId(), DateUtil.format(temp.getEffDate()));
                if(dto == null){
                    errorList.add(new ValidMessage("","已被删除的费率报价信息不允许删除,产品代码："+temp.getFinprodMarketId()));
                    return errorList;
                }
            }
            for(FinProductRateQuotationDto temp:finProductRateQuotationDto.getSelectedItem()){
                //删除记录.是否初始利率 = 数据字典.是否.是
                if(temp.getIsInitial().equals(DictCodeList.是否_是.getValue())){
                    errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0012",
                        "产品代码："+temp.getFinprodMarketId(),
                        ",生效日期："+temp.getEffDateStr())));
                }
                // 删除记录.生效日 < 平台日
                if (temp.getEffDate().compareTo(DateUtil.platDateFromCache()) < 0) {
                    errorList.add(new ValidMessage("", getMessage("BUSS_COM_E_0015",
                        "生效日：" + temp.getEffDateStr(), "平台日:" + DateUtil.getSysDate())));
                }
            }
        }
        if(StringUtil.equals(finProductRateQuotationDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(!StringUtil.isNotEmpty(finProductRateQuotationDto.getEffDate())){
                return errorList;
            }
            //画面.生效日 < 标的类金融产品.起息日 时，提交报错
            FinProductRateQuotationDto temp = new FinProductRateQuotationDto();
            temp = finProductRateQuotationService.getVdateAndMdate(finProductRateQuotationDto.getFinprodId());
            if(finProductRateQuotationDto.getEffDate().compareTo(temp.getVdate())<0){
                errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
                    "生效日："+finProductRateQuotationDto.getEffDateStr(),
                    "起息日:"+DateUtil.format(temp.getVdate()))));
            }
            //画面.生效日 > 标的类金融产品.到期日 时，提交报错
            if(finProductRateQuotationDto.getEffDate().compareTo(temp.getMdate())>=0){
                errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0035",
                    "生效日："+finProductRateQuotationDto.getEffDateStr(),
                    "到期日："+DateUtil.format(temp.getMdate()))));
            }
            // 新建-生效日 < 平台日
            if (finProductRateQuotationDto.getEffDate().compareTo(
                DateUtil.platDateFromCache()) < 0) {
                errorList.add(new ValidMessage("",
                    getMessage("BUSS_COM_E_0004",
                        "生效日：" + finProductRateQuotationDto.getEffDateStr(),
                        "平台日:" + DateUtil.getSysDate())));
            }
            //当同一生效日存在有效的记录时，提交报错
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("effDate", DateUtil.format(finProductRateQuotationDto.getEffDate()));
            for(FinProductRateQuotationDto finProductRateQuotation:finProductRateQuotationDto.getSelectedItem()){
                param.put("finprodId", finProductRateQuotation.getFinprodId());
                param.put("cashId", finProductRateQuotation.getCashIdStr());
                if(finProductRateQuotationService.selectAlreadyHava(param).size()>0){
                    errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0009", finProductRateQuotationDto.getEffDateStr(),"有效报价记录")));
                    break;
                }
            }
        }
        if(StringUtil.equals(finProductRateQuotationDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            if(!StringUtil.isNotEmpty(finProductRateQuotationDto.getEffDate())){
                return errorList;
            }
         // 存在流程中数据
           /*if (WorkFlowUtil.isNotFromToDoList()) {
                if (productCommonService.isInFlowDataExisted(
                		finProductRateQuotationDto.getCashId())) {
                	errorList.add(new ValidMessage(
                        getMessage("COMMON_E_0012", finProductRateQuotationDto.getCashId())));
                }
            }*/
            //编辑记录.是否初始利率 = 数据字典.是否.是
            if(StringUtil.isNotEmpty(finProductRateQuotationDto.getIsInitial())){
                if(finProductRateQuotationDto.getIsInitial().equals(DictCodeList.是否_是.getValue())){
                    errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0012",
                        "产品代码："+finProductRateQuotationDto.getFinprodMarketId(),
                        ",生效日："+finProductRateQuotationDto.getEffDateStr())));
                }
            }
            // 编辑记录.生效日 < 平台日
            if (finProductRateQuotationDto.getEffDate().compareTo(
                DateUtil.platDateFromCache()) < 0) {
                errorList.add(new ValidMessage("",
                    getMessage("BUSS_COM_E_0018",
                        "生效日：" + finProductRateQuotationDto.getEffDateStr(),
                        "平台日:" + DateUtil.getSysDate())));
            }
            //画面.生效日 < 标的类金融产品.起息日 时，提交报错
            FinProductRateQuotationDto temp = new FinProductRateQuotationDto();
            temp = finProductRateQuotationService.getVdateAndMdate(finProductRateQuotationDto.getFinprodId());
            if(finProductRateQuotationDto.getEffDate().compareTo(temp.getVdate())<0){
                errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004",
                    "生效日："+finProductRateQuotationDto.getEffDateStr(),
                    "起息日:"+DateUtil.format(temp.getVdate()))));
            }
            //画面.生效日 > 标的类金融产品.到期日 时，提交报错
            if(finProductRateQuotationDto.getEffDate().compareTo(temp.getMdate())>=0){
                errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0035",
                    "生效日："+finProductRateQuotationDto.getEffDateStr(),
                    "到期日："+DateUtil.format(temp.getMdate()))));
            }
            //当同一生效日存在有效的记录时，提交报错
            Map<String, Object> param = new HashMap<String, Object>();
            if(StringUtil.isNotEmpty(finProductRateQuotationDto.getEffDateOld()) && 
                !finProductRateQuotationDto.getEffDate().equals(finProductRateQuotationDto.getEffDateOld())){
                param.put("effDate", DateUtil.format(finProductRateQuotationDto.getEffDate()));
                for(FinProductRateQuotationDto finProductRateQuotation:finProductRateQuotationDto.getSelectedItem()){
                    param.put("finprodId", finProductRateQuotation.getFinprodId());
                    param.put("cashId", finProductRateQuotation.getCashIdStr());
                    if(finProductRateQuotationService.selectAlreadyHava(param).size()>0){
                        errorList.add(new ValidMessage("",getMessage("FIN_PROD_E_0009", finProductRateQuotationDto.getEffDateStr(),"有效报价记录")));
                    }
                }
            }
        }
        //}
        //add by gcl 2019/3/22 start
        if (WorkFlowUtil.isNotFromToDoList()) {
            if(finProductRateQuotationDto != null 
            		/*&& finProductRateQuotationDto.getWorkflowId() != null*/){
              //判断流程中是否存在相同的数据
                WflValidInputParameter param = new WflValidInputParameter();
                param.setWorkflowId(finProductRateQuotationDto.getWorkflowId());
                param.setBusinessDataPk1(null);
                param.setBusinessDataPk2(DateUtil.format(finProductRateQuotationDto.getEffDate()));
                param.setBusinessDataPk3(finProductRateQuotationDto.getFinprodId());
                param.setPageId("FinProductRateQuotation002");
                if(null != mstWorkflowCommonService 
                		&& 
                    mstWorkflowCommonService.isInWflProcess(param)){
                    errorList.add(
                        new ValidMessage("已存在流程,不能操作!"));
                }
                if(finProductRateQuotationDto.getSelectedItem()!=null){
                    for(FinProductRateQuotationDto dto : finProductRateQuotationDto.getSelectedItem()){
                      //判断流程中是否存在相同的数据
                        WflValidInputParameter param1 = new WflValidInputParameter();
                        param1.setWorkflowId(finProductRateQuotationDto.getWorkflowId());
                        param1.setBusinessDataPk1(null);
                        param1.setBusinessDataPk2(DateUtil.format(dto.getEffDate()));
                        param1.setBusinessDataPk3(dto.getFinprodId());
                        param1.setPageId("FinProductRateQuotation002");
                        if(null != mstWorkflowCommonService 
                                && 
                            mstWorkflowCommonService.isInWflProcess(param1)){
                            errorList.add(
                                new ValidMessage("已存在流程,不能操作!"));
                        }
                    }
                }
            }
          //add by gcl 2019/3/22 end
        }
        return errorList;
    }

}
