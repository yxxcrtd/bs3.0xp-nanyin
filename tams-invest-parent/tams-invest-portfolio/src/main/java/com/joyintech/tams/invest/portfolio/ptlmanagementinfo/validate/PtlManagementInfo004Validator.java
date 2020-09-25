/*
 * 文件名：PtlManagementInfo001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.validate;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.component.business.portfolio.dto.FundAccountInfoDto;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfo004Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 组合信息验证
 *
 * @author fanshuanghu
 * @version 1.0 2018年9月6日
 * @see PtlManagementInfo004Validator
 * @since 1.0
 */
@Transactional
@Component
public class PtlManagementInfo004Validator implements BeanValidator {
    /**
     * 组合service001
     */
    @Autowired
    private PtlManagementInfo001Service ptlManagementInfo001Service;

    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlManagementInfo004Dto dto = (PtlManagementInfo004Dto) value;
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            if (StringUtil.isNotEmpty(dto.getChildPortfolioId()) && dto.getChildPortfolioId().getBytes().length > 25) {
                errorList.add(new ValidMessage(getMessage("FW_E_0007", getMessage("PtlManagementInfo004.childPortfolioId"), "25")));
            }
            //校验子组合代码重复性
            if (StringUtil.isNotEmpty(dto.getChildPortfolioId())) {
                if (ptlPortfolioMapper.selectByPrimaryKey(dto.getChildPortfolioId()) != null) {
                    errorList.add(new ValidMessage("childPortfolioId", getMessage("PTL_COM_E_0019")));
                }
            }

            if (StringUtil.isEmpty(dto.getChildPortfolioName())) {
                errorList.add(new ValidMessage("childPortfolioName", getMessage("BUSS_COM_E_0038", getMessage("PtlManagementInfo004.childPortfolioName"))));
            }
            if (null == dto.getDisPercent()) {
                errorList.add(new ValidMessage("disPercent", getMessage("BUSS_COM_E_0038", getMessage("PtlManagementInfo004.inheritanceRatio"))));
            } 
            else if(dto.getDisPercent().compareTo(BigDecimal.ZERO)<0 || dto.getDisPercent().compareTo(new BigDecimal(100))>0){
                errorList.add(new ValidMessage("disPercent", getMessage("PTL_COM_E_0025", getMessage("PtlManagementInfo004.inheritanceRatio"))));  
            }
        }else {
            if (StringUtil.isEmpty(dto.getChildPortfolioName())) {
                errorList.add(new ValidMessage("childPortfolioName", getMessage("BUSS_COM_E_0038", getMessage("PtlManagementInfo004.childPortfolioName"))));
            }
            if (null == dto.getDisPercent()) {
                errorList.add(new ValidMessage("disPercent", getMessage("BUSS_COM_E_0038", getMessage("PtlManagementInfo004.inheritanceRatio"))));
            }
            else if(dto.getDisPercent().compareTo(BigDecimal.ZERO)<0 || dto.getDisPercent().compareTo(new BigDecimal(100))>0){
                errorList.add(new ValidMessage("disPercent", getMessage("PTL_COM_E_0025", getMessage("PtlManagementInfo004.inheritanceRatio"))));  
            }
        }

        return errorList;
    }
}
