/*
 * 文件名：SysInstRegBankCode002Validator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年5月22日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.system.instregbankcode.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.ChbOrgRegistBankCodeMapper;
import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import com.joyintech.tams.report.submitmaintenance.equityinfo.validate.EquityInfoValid;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.ChbTransformRuleObjDto;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode002Mapper;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode002Dto;
import com.joyintech.system.institution.service.InstitutionService;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 机构登记银行代码新建修改check
 *
 * @author 刘二通
 * @version 1.0 2018年5月22日
 * @see SysInstRegBankCode002Validator
 * @since 1.0
 */
@Component
public class SysInstRegBankCode002Validator implements BeanValidator {

    /**
     * 机构管理service
     */
    @Autowired(required = false)
    private InstitutionService institutionService;

    /**
     * check
     */
    @Autowired
    private EquityInfoValid equityInfoValid;

    /**
     * ChbOrgRegistBankCodeMapper
     */
    @Autowired
    private ChbOrgRegistBankCodeMapper chbOrgRegistBankCodeMapper;

    /**
     * SysInstRegBankCode002Mapper
     */
    @Autowired
    private SysInstRegBankCode002Mapper sysInstRegBankCode002Mapper;

    @Override
    public List<ValidMessage> valid(Object value) {

        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        SysInstRegBankCode002Dto dataDto = (SysInstRegBankCode002Dto)value;

        // 机构代码
        if (StringUtil.isNotEmpty(dataDto.getOrgCode())) {
            if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, dataDto.getPageModel())) {
                // 机构代码是否存在
                // 取得系统中所有机构
                List<SuggestDto> institutionList = institutionService.getInstitutionCode(null);
                Map<String, String> institutionMap = new HashMap<String, String>();
                if (!StringUtil.isEmptyList(institutionList)) {
                    institutionMap = institutionList.stream().collect(
                        JoyinCollectors.toMap(x -> x.getCode(), x -> x.getName()));
                }
                if (!institutionMap.containsKey(dataDto.getOrgCode())) {
                    errorList.add(
                        new ValidMessage("orgCode", this.getMessage("REPORT_E_0015", "机构代码")));
                }

                // 是否已经配置了该机构的登记银行代码数据
                ChbOrgRegistBankCode chbOrgRegistBankCode = chbOrgRegistBankCodeMapper.selectByPrimaryKey(
                    dataDto.getOrgCode());
                if (chbOrgRegistBankCode != null) {
                    errorList.add(new ValidMessage("orgCode",
                        this.getMessage("REPORT_COMMON_E_0011", "机构代码：" + dataDto.getOrgCode())));
                }
            }
        }

        // 登记银行代码
        if (StringUtil.isNotEmpty(dataDto.getRegistBankCode())) {
            List<String> tempMsgList = new ArrayList<>();
            Map<String,TransformRule001Dto> spzMap=new HashMap<String,TransformRule001Dto>();
            Map<String,List<ChbTransformRuleObjDto>> objFiledsMap=new HashMap<String,List<ChbTransformRuleObjDto>>();
            equityInfoValid.checkBankCodeNew(tempMsgList, dataDto.getRegistBankCode(),spzMap,objFiledsMap,null);
            if (!StringUtil.isEmptyList(tempMsgList)) {
                errorList.add(new ValidMessage("registBankCode", tempMsgList.get(0)));
            }
            else {
                // 是否已经配置了登记银行代码数据
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("registBankCode", dataDto.getRegistBankCode());
                if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, dataDto.getPageModel())) {
                    paramMap.put("orgCode", dataDto.getOrgCode());
                }
                int registBankCodeDataCount = sysInstRegBankCode002Mapper.selectRegistBankCodeDataCount(paramMap);
                if (registBankCodeDataCount > 0) {
                    errorList.add(new ValidMessage("registBankCode",
                        this.getMessage("REPORT_COMMON_E_0011", "登记银行代码：" + dataDto.getRegistBankCode())));
                }
            }
        }

        return errorList;
    }

}