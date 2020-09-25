/*
 * 文件名：FinIntPayDateAdjustmentValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.dto.FinIntPayDateAdjustmentDto;
import com.joyintech.tams.invest.product.messageadjust.finintpaydateadjustment.service.FinIntPayDateAdjustmentService;

/**
 * 信息调整管理_付息日调整  
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月23日
 * @see FinIntPayDateAdjustmentValidator
 * @since 1.0
 */
@Component
public class FinIntPayDateAdjustmentValidator implements BeanValidator{
    
    @Autowired
    private FinIntPayDateAdjustmentService finIntPayDateAdjustmentService;
    
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinIntPayDateAdjustmentDto finIntPayDateAdjustmentDto = (FinIntPayDateAdjustmentDto)value;
        List<ValidMessage> erroList = new ArrayList<ValidMessage>();
        List<FinIntPayDateAdjustmentDto> selectedItem = finIntPayDateAdjustmentDto.getSelectedItem1();
        //if (WorkFlowUtil.isNotFromToDoList()) {
        if(StringUtil.equals(finIntPayDateAdjustmentDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            //遍历所有状态未“未支付”或空的记录，第i行.支付日 < 系统缓存.平台日期时
            for(FinIntPayDateAdjustmentDto temp:selectedItem){
                if((temp.getPayStatus() == null || "未付息".equals(temp.getPayStatus())) 
                    && DateUtil.compareDate(temp.getPayDate(), iPlatformDate.getSysDate())<0){
                    erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0004", "支付日："+DateUtil.format(temp.getPayDate()),
                        "平台日期："+iPlatformDate.getSysDateStr())));
                }
            }
            //遍历所有状态未“未支付”或空的记录，第i行.计息区间开始日 > 第i行.计息区间结束日时
            for(FinIntPayDateAdjustmentDto temp:selectedItem){
                if(temp.getVdate().compareTo(temp.getMdate())>0){
                    erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0003", "计息区间开始日："+DateUtil.format(temp.getVdate()),
                        "计息区间结束日 ："+DateUtil.format(temp.getMdate())))); 
                }
            }
            //当前遍历节点.计息区间开始日 ！= 上一个遍历节点.计息区间结束日时
            for(int i=0; i<selectedItem.size()-1; i++){
                if("已付息".equals(selectedItem.get(i).getPayStatus())){
                    
                }
                else{
                    if(selectedItem.get(i+1).getVdate().compareTo(selectedItem.get(i).getMdate()) != 0 ){
                        //erroList.add(new ValidMessage("",getMessage("FIN_PROD_E_0054")));
                        erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0012","计息区间开始日："+DateUtil.format(selectedItem.get(i+1).getVdate()),
                            "上一周期","计息区间结束日："+DateUtil.format(selectedItem.get(i).getMdate()))));
                    }
                }
            }
            //最后一个区间的计息区间开始日 ≠ 产品起息日 时，提交报错
            //最后一个区间的计息区间结束日 ≠ 产品到期日 时，提交报错
            if(StringUtil.isNotEmpty(finIntPayDateAdjustmentDto.getFinprodId())){
                FinProduct tempDto = finIntPayDateAdjustmentService.getVdateMdate(finIntPayDateAdjustmentDto.getFinprodId());
                if(finIntPayDateAdjustmentDto.getSelectedItem1().get(0).getVdate()
                		.compareTo(tempDto.getVdate()) != 0){
                    erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0012","第一个区间的计息区间开始日:"+DateUtil.format(
                        finIntPayDateAdjustmentDto.getSelectedItem1().get(0).getVdate()),
                        "产品信息","起息日:"+DateUtil.format(tempDto.getVdate()))));
                }
                
                // modify by lizhi BS-1108 永续产品不做到期日与最后一个区间的计息区间结束日 的比较 start
                String isSus = finIntPayDateAdjustmentService.queryProductIsSus(finIntPayDateAdjustmentDto.getFinprodId());
                if(StringUtil.isEmpty(isSus) || "N".equals(isSus)){
                	if(finIntPayDateAdjustmentDto.getSelectedItem1().get(finIntPayDateAdjustmentDto.getSelectedItem1().size()-1).getMdate()
                            .compareTo(tempDto.getMdate()) != 0){
                            erroList.add(new ValidMessage("",getMessage("BUSS_COM_E_0012","最后一个区间的计息区间结束日:"+DateUtil.format(
                                finIntPayDateAdjustmentDto.getSelectedItem1().get(finIntPayDateAdjustmentDto.getSelectedItem1().size()-1).getMdate()),
                                "产品信息","到期日:"+DateUtil.format(tempDto.getMdate()))));
                        }
                }
                // modify by lizhi BS-1108 永续产品不做到期日与最后一个区间的计息区间结束日 的比较 end
                
                /*if(finIntPayDateAdjustmentDto.getSelectedItem1().get(0).getMdate().compareTo(finIntPayDateAdjustmentDto.getSelectedItem1().get(0).getPayDate())>0){
                	erroList.add(new ValidMessage("",getMessage("不能小与付息结束日","支付日")));
                	
                }
                if(finIntPayDateAdjustmentDto.getSelectedItem1().get(finIntPayDateAdjustmentDto.getSelectedItem1().size()-1).getMdate().compareTo(finIntPayDateAdjustmentDto.getSelectedItem1().get(finIntPayDateAdjustmentDto.getSelectedItem1().size()-1).getPayDate())>0){
                	erroList.add(new ValidMessage("",getMessage("不能小与付息结束日","支付日")));
                	
                }*/
            }
            //finIntPayDateAdjustmentDto.getVdate().compareTo(finIntPayDateAdjustmentDto.getVdate()) != 0 && 
            
            //当前遍历节点.计息区间开始日 = 上一个遍历节点.计息区间开始日时
            for(int i=0; i<selectedItem.size()-1; i++){
                if(selectedItem.get(i).getVdate().compareTo(selectedItem.get(i+1).getVdate()) == 0){
                    erroList.add(new ValidMessage("",getMessage("FIN_PROD_E_0055","")));
                }
            }
            //add by gcl 2019/3/24 start
            //支付日不能小与付息结束日
            for(int i=0; i<selectedItem.size(); i++){
                if(selectedItem.get(i).getMdate().compareTo(selectedItem.get(i).getPayDate()) > 0){
                    erroList.add(new ValidMessage("",getMessage("支付日不能小于计息区间结束日","")));
                }
            }
          //add by gcl 2019/3/24  end
        }
        //}
        
        return erroList;
    }

}
