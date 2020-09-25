/*
 * 文件名：MstCaptialAccountValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstcaptialaccount.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.dto.MstCaptialAccountDto;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.service.MstCaptialAccountService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see MstCaptialAccountValidator
 * @since 1.0
 */
@Component
public class MstCaptialAccountValidator implements BeanValidator{
    /**
     * 资金账户管理service
     */
    @Autowired
    private MstCaptialAccountService mstCaptialAccountService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        MstCaptialAccountDto dto = (MstCaptialAccountDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                //行删除按钮与删除按钮校验
                List<MstCaptialAccountDto> listDto = dto.getSelectedItem();
                List<MstCaptialAccountDto> tempList = mstCaptialAccountService.checkAllCodeIsUse(listDto);
                if(tempList.size()>0){
                    for(MstCaptialAccountDto tempDto:tempList){
                        errorList.add(new ValidMessage("", getMessage("COMMON_E_0030",
                            getMessage("MstCaptialAccount002.acctNo")
                            +"("+getMessage("MstCaptialAccount002.currency")+")"
                            ,tempDto.getAcctNo()+"("+tempDto.getCcy()+")")));
                
                    }
                }   
            }
            else {
                //新建校验
                if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                    if(!StringUtil.equals(dto.getCcy(), "CNY")){
                        if(StringUtil.isEmpty(dto.getSwiftCode())){
                            errorList.add(new ValidMessage("swiftCode", getMessage("COMMON_E_0057",getMessage("MstCaptialAccount002.swiftCode"))));
                        }
                    }
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("acctName", "");
                    List<MstCaptialAccountDto> list = mstCaptialAccountService.selectCheck(map);
                    for(int i=0; i<list.size(); i++){
                    	String temp = "";
                    	if(StringUtil.isNotEmpty(list.get(i).getLargePayno())){
                    		temp = list.get(i).getLargePayno()+list.get(i).getBusiAcctNo()+list.get(i).getCcy();
                    	}
                    	else{
                            temp = ""+list.get(i).getBusiAcctNo()+list.get(i).getCcy();
                    	}
                    	if(StringUtil.equals(temp, dto.getLargePayno()+dto.getBusiAcctNo()+dto.getCcy())){
                            errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0006",
                                getMessage("MstCaptialAccount002.accountOutlet")+"+"+getMessage("MstCaptialAccount002.accountId")
                                +"+"+getMessage("MstCaptialAccount002.currency"))));
                        }
                    }  
                    String largePayno = dto.getLargePayno();
                    if(StringUtil.isNotEmpty(largePayno)){
                    	if(largePayno.length() > 12){
                            errorList.add(new ValidMessage("largePayno", getMessage("MST_CAP_COUNT_1")));
                        }
                    }else {
                        errorList.add(new ValidMessage("largePayno", getMessage("COMMON_E_0057",getMessage("MstCaptialAccount002.accountOutlet"))));
                    }
                }
                else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                    if(!StringUtil.equals(dto.getCcy(), "CNY")){
                        if(StringUtil.isEmpty(dto.getSwiftCode())){
                            errorList.add(new ValidMessage("swiftCode", getMessage("COMMON_E_0057",getMessage("MstCaptialAccount002.swiftCode"))));
                        }
                    }
                }
            }
        }
        else{
          //新建校验
            if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                if(!StringUtil.equals(dto.getCcy(), "CNY")){
                    if(StringUtil.isEmpty(dto.getSwiftCode())){
                        errorList.add(new ValidMessage("swiftCode", getMessage("COMMON_E_0057",getMessage("MstCaptialAccount002.swiftCode"))));
                    }
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("acctName", "");
                List<MstCaptialAccountDto> list = mstCaptialAccountService.selectCheck(map);
                for(int i=0; i<list.size(); i++){
                	String temp = "";
                	if(StringUtil.isNotEmpty(list.get(i).getLargePayno())){
                		temp = list.get(i).getLargePayno()+list.get(i).getBusiAcctNo()+list.get(i).getCcy();
                	}
                	else{
                        temp = ""+list.get(i).getBusiAcctNo()+list.get(i).getCcy();
                	}
                	if(StringUtil.equals(temp, dto.getLargePayno()+dto.getBusiAcctNo()+dto.getCcy())){
                        errorList.add(new ValidMessage("",getMessage("BUSS_COM_E_0006",
                            getMessage("MstCaptialAccount002.accountOutlet")+"+"+getMessage("MstCaptialAccount002.accountId")
                            +"+"+getMessage("MstCaptialAccount002.currency"))));
                    }
                }  
            }
            else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                if(!StringUtil.equals(dto.getCcy(), "CNY")){
                    if(StringUtil.isEmpty(dto.getSwiftCode())){
                        errorList.add(new ValidMessage("swiftCode", getMessage("COMMON_E_0057",getMessage("MstCaptialAccount002.swiftCode"))));
                    }
                }
            }
        }
        return errorList;
    }

}
