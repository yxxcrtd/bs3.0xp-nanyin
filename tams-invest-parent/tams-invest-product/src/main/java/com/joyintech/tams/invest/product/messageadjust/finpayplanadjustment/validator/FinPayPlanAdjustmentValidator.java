/*
 * 文件名：FinPayPlanAdjustmentValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.validator;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustmentDto;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.service.FinPayPlanAdjustmentService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月22日
 * @see FinPayPlanAdjustmentValidator
 * @since 1.0
 */
@Component
public class FinPayPlanAdjustmentValidator implements BeanValidator{
    
    /**
     * 付费计划调整Service
     */
    @Autowired
    private FinPayPlanAdjustmentService finPayPlanAdjustmentService;

    /**
     * 信息调整管理_付费计划调整  
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinPayPlanAdjustmentDto finPayPlanAdjustmentDto = (FinPayPlanAdjustmentDto)value;
        List<ValidMessage> erroList = new ArrayList<ValidMessage>();
        List<FinPayPlanAdjustmentDto> selectedItem = finPayPlanAdjustmentDto.getSelectedItem1();
        //if (WorkFlowUtil.isNotFromToDoList()) {
        if(StringUtil.equals(finPayPlanAdjustmentDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            //画面.支付日 < 平台日期 时，提交报错
            TreeSet<Date> mdateTreeSet = new TreeSet<>();
            for(FinPayPlanAdjustmentDto temp:selectedItem){
                if("已支付".equals(temp.getPayStatus())){
                    
                }else{
                    if(temp.getPayDate().compareTo(DateUtil.platDateFromCache())<0){
                        erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004", "支付日："+DateUtil.format(temp.getPayDate()),
                            "平台日期："+DateUtil.getSysDate())));
                    }
                }

                mdateTreeSet.add(temp.getMdate());
            }

            if(!StringUtil.isEmptyList(selectedItem)){
                if(selectedItem.size() > mdateTreeSet.size()){
                    erroList.add(new ValidMessage(getMessage("BUSS_COM_E_0060")));
                }
            }


            //画面.计提开始日 > 画面.计提结束日 时，提交报错
            for(FinPayPlanAdjustmentDto temp:selectedItem){
                if(temp.getVdate().compareTo(temp.getMdate())>0){
                    erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0003", "计提开始日："+DateUtil.format(temp.getVdate()),
                        "计提结束日："+DateUtil.format(temp.getMdate())))); 
                }
            }
            //画面.计提开始日 ≠ 上一周期计提结束日 时，提交报错
            for(int i=0; i<selectedItem.size()-1; i++){
                if("已支付".equals(selectedItem.get(i).getPayStatus())){
                    
                }else{
                    if(selectedItem.get(i+1).getVdate().compareTo(selectedItem.get(i).getMdate()) != 0 ){
                        erroList.add(new ValidMessage("",getMessage("FIN_PROD_E_0128","计提开始日:"+DateUtil.format(selectedItem.get(i+1).getVdate())))); 
                    }
                }
            }
            //最后一个区间的计提结束日 ≠ 产品到期日 时，提交报错
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("finprodId", finPayPlanAdjustmentDto.getFinprodId());//finPayPlanAdjustmentService.getMdate(param).getVdate()
            if(StringUtil.isNotEmpty(finPayPlanAdjustmentDto.getFinprodId())){
                if(selectedItem.get(0).getVdate().compareTo(finPayPlanAdjustmentDto.getVdateCheck()) != 0&&!("已支付").equals(selectedItem.get(0).getPayStatus())){
                    erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0012","第一个区间的计提开始日："+
                        DateUtil.format(selectedItem.get(0).getVdate()),
                        "产品信息","起息日："+DateUtil.format(finPayPlanAdjustmentDto.getVdateCheck()))));
                }
                if(selectedItem.get(selectedItem.size()-1).getMdate().compareTo(finPayPlanAdjustmentService.getMdate(param).getMdate()) != 0){
                    erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0012","最后一个区间的计提结束日："+
                        DateUtil.format(selectedItem.get(selectedItem.size()-1).getMdate()),
                        "产品信息","到期日："+DateUtil.format(finPayPlanAdjustmentService.getMdate(param).getMdate()))));
                }
            }
        }
        //}
        return erroList;
    }

}
