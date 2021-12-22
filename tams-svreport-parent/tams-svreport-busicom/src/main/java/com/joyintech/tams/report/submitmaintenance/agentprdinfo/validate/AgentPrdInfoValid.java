package com.joyintech.tams.report.submitmaintenance.agentprdinfo.validate;

import com.joyintech.background.component.common.check.DataCheckCommonUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dto.AgentPrdInfoDto;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.service.AgentPrdInfo002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AgentPrdInfoValid implements BeanValidator {
    @Autowired
    private DataCheckCommonUtil chbDataCheckCommonUtil;

    @Autowired
    private AgentPrdInfo002Service agentPrdInfo002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        AgentPrdInfoDto dto = (AgentPrdInfoDto)value;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        //代理销售机构代码重复校验
        if(dto.getPageModel() != null && dto.getPageModel() != "" && dto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(dto.getAgentInstitutionCode() != null && dto.getAgentInstitutionCode() != ""
                    && dto.getProdRegistCode() != null && dto.getProdRegistCode() != ""){
                if(agentPrdInfo002Service.checkProdRegistCode(dto.getAgentInstitutionCode(),dto.getProdRegistCode()) > 0) {
                    errorList.add(new ValidMessage("", "代理销售机构代码相同时，产品登记编码不可重复。"));
                }
            }
        }
        //代理销售机构代码有效校验
        if(dto.getPageModel() != null && dto.getPageModel() != "" && dto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(dto.getAgentInstitutionCode() != null && dto.getAgentInstitutionCode() != ""){
                if(agentPrdInfo002Service.checkAgentInstitutionCode(dto.getAgentInstitutionCode()) == 0){
                    errorList.add(new ValidMessage("", "代理销售机构代码已被删除请重新选择。"));
                }
            }
        }
        //调用数据库配置脚本check方法
        errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, dto, "AgentPrdInfo002", null, ValidMessage.class);
        return errorList;
    }
}
