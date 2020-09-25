/*
 * 文件名：PtlManagementInfo003Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.validate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfo003Dto;

/**
 * 组合用户分配
 * @author fanshuanghu
 * @version 1.0 2018年12月25日
 * @see PtlManagementInfo003Validator
 * @since 1.0
 */
@Transactional
@Component
public class PtlManagementInfo003Validator implements BeanValidator{
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlManagementInfo003Dto dto = (PtlManagementInfo003Dto)value;
        if(StringUtil.equals(dto.getAllocationWay(), "01")){
            if(StringUtil.isEmpty(dto.getPortfolioId())){
                errorList.add(new ValidMessage("portfolioId",getMessage("FW_E_0006", getMessage("PtlManagementInfo003.portfolio"))));
            }
            if(dto.getUserList() == null || dto.getUserList().size() == 0){
                errorList.add(new ValidMessage("userList",getMessage("FW_E_0006",getMessage("PtlManagementInfo003.user"))));
            }
        }else if(StringUtil.equals(dto.getAllocationWay(), "02")){
            if(StringUtil.isEmpty(dto.getUserId())){
                errorList.add(new ValidMessage("userId",getMessage("FW_E_0006",getMessage("PtlManagementInfo003.user"))));
            }
            if(dto.getSelectedList() == null || dto.getSelectedList().size() == 0){
                errorList.add(new ValidMessage("selectItem",getMessage("FW_E_0006",getMessage("PtlManagementInfo003.portfolio"))));
            }
        }else{
            
        }
      return errorList;
    }
}
