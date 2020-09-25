/*
 * 文件名：MstCaptialAccountValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dto.FxTraderManagerDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.service.FxTraderManager001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see FxTraderManagerValidator
 * @since 1.0
 */
@Component
public class FxTraderManagerValidator implements BeanValidator{
    /**
     * 资金账户管理service
     */
    @Autowired
    private FxTraderManager001Service fxTraderManagerService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        FxTraderManagerDto dto = (FxTraderManagerDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
          //新建校验
            if(StringUtil.equals(dto.getPageModel(),"add")){
                //判断是否为空
                if(StringUtil.isEmpty(dto.getStaffid())){
                    errorList.add(new ValidMessage("staffid", getMessage("COMMON_E_0057",getMessage("FxTraderManager.user"))));
                } else if(StringUtil.isEmpty(dto.getCstptraderid())){
                    errorList.add(new ValidMessage("cstptraderid", getMessage("COMMON_E_0057",getMessage("FxTraderManager.cstpjyyid"))));
                } else if(StringUtil.isEmpty(dto.getCstptrader())){
                    errorList.add(new ValidMessage("cstptrader", getMessage("COMMON_E_0057",getMessage("FxTraderManager.cstpjyy"))));
                }
                int staffcount = 0;
                staffcount = fxTraderManagerService.findCountByStaffid(dto);
                if(staffcount > 0){
                    errorList.add(new ValidMessage(getMessage("用户名已经映射其他外汇交易员，请重新选择用户")));
                }
                int cstptradercount = 0;
                cstptradercount = fxTraderManagerService.findCountByCstptraderid(dto);
                if(cstptradercount > 0){
                    errorList.add(new ValidMessage(getMessage("外汇交易员已经被映射到其他用户上，请重新选择外汇交易员")));
                }
            }
            else if(StringUtil.equals(dto.getPageModel(), "edit")){
                //判断是否为空
                if(StringUtil.isEmpty(dto.getStaffid())){
                    errorList.add(new ValidMessage("staffid", getMessage("COMMON_E_0057",getMessage("FxTraderManager.user"))));
                } else if(StringUtil.isEmpty(dto.getCstptraderid())){
                    errorList.add(new ValidMessage("cstptraderid", getMessage("COMMON_E_0057",getMessage("FxTraderManager.cstpjyyid"))));
                } else if(StringUtil.isEmpty(dto.getCstptrader())){
                    errorList.add(new ValidMessage("cstptrader", getMessage("COMMON_E_0057",getMessage("FxTraderManager.cstpjyy"))));
                }
                int staffcount = 0;
                staffcount = fxTraderManagerService.findCountByStaffid(dto);
                if(staffcount > 0){
                    errorList.add(new ValidMessage(getMessage("用户名已经映射其他外汇交易员，请重新选择用户")));
                }
                int cstptradercount = 0;
                cstptradercount = fxTraderManagerService.findCountByCstptraderid(dto);
                if(cstptradercount > 0){
                    errorList.add(new ValidMessage(getMessage("外汇交易员已经被映射到其他用户上，请重新选择外汇交易员")));
                }
            }
        return errorList;
    }

}
