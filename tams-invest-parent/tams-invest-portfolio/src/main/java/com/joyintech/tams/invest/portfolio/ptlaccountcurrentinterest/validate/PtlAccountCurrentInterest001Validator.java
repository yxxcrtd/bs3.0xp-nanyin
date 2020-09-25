/*
 * 文件名：PtlAccountCurrentInterest001Validator.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：fanshuanghu
 * 修改时间：2018年10月11日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.service.PtlAccountCurrentInterest002Service;

/**
 * 账户活期利率
 * 
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest001Validator
 * @since 1.0
 */
@Transactional
@Component
public class PtlAccountCurrentInterest001Validator implements BeanValidator {

    @Autowired
    private PtlAccountCurrentInterest002Service ptlAccountCurrentInterest002Service;

    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    @Override
    public List<ValidMessage> valid(Object value) {
        Date sysDate = null;
        if (iPlatformDate != null) {
            sysDate = iPlatformDate.getSysDate() == null ? new Date() : DateUtil.parse(
                DateUtil.getSysDate());
        }
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlAccountCurrentInterest001Dto dto = (PtlAccountCurrentInterest001Dto)value;
        if (StringUtil.isEmpty(dto.getCashId())) {
            String cashId = CommonUtil.generateCashFlowCode(dto.getBusiAcctNo(), DictCodeListExt1.现金流业务类型_资金账户.getValue(), 0L,
                DictCodeListExt1.现金流二级类型_利息.getValue());
            dto.setCashId(cashId);
            //add by yanya 20190911 增加流程中的检验默认值
            dto.setFinprodType(DictCodeListExt1.现金流业务类型_资金账户.getValue());
        }
        // 校验重复数据
        if (dto.getEffDate() != null
            && (dto.getOldEffDate() == null
                || !StringUtil.equals(DateUtil.format(dto.getEffDate()), DateUtil.format(dto.getOldEffDate())))) {
            if (StringUtil.isNotEmpty(dto.getEffDateStr())
                && (StringUtil.equals(dto.getOperateType(), "save") || StringUtil.equals(dto.getOperateType(), "submit"))) {
                if (ptlAccountCurrentInterest002Service.countExistsData(dto) > 0) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(dto.getBusiAcctNo()).append(GlobalConstant.COMMA).append(dto.getCcy()).append(
                        GlobalConstant.COMMA).append(dto.getEffDateStr());
                    errorList.add(new ValidMessage(getMessage("COMMON_E_0011", sb.toString())));
                }
            }
        }
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            if (dto.getBusiAcctNo() != "" && dto.getBusiAcctNo() != null && dto.getEffDate() != null
                && ptlAccountCurrentInterest002Service.getLastEffDate(dto.getBusiAcctNo()) != null
                && dto.getEffDate().compareTo(ptlAccountCurrentInterest002Service.getLastEffDate(dto.getBusiAcctNo())) < 0) {
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0004",
                    getMessage("PtlAccountCurrentInterest001.effDate") + DateUtil.format(dto.getEffDate()),
                    "最后生效日"+DateUtil.format(ptlAccountCurrentInterest002Service.getLastEffDate(dto.getBusiAcctNo())))));
            }
            else if (dto.getEffDate() != null && dto.getEffDate().compareTo(sysDate) < 0) {
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0004",
                    getMessage("PtlAccountCurrentInterest001.effDate") + DateUtil.format(dto.getEffDate()),
                    "平台日"+DateUtil.format(sysDate))));
            }
            if (dto.getRateType() != null && !"".equals(dto.getRateType())
                && dto.getRateType().equals(DictCodeList.利率类型_浮动利率.getValue())) {
                if (dto.getIndexId() == null || dto.getIndexId().equals("")) {
                    errorList.add(new ValidMessage("currentRate",
                        getMessage("FW_E_0006", getMessage("PtlAccountCurrentInterest001.currentRate"))));
                }
            }
            else if (dto.getRateType() != null && !"".equals(dto.getRateType())
                     && dto.getRateType().equals(DictCodeList.利率类型_固定利率.getValue())) {
                if (dto.getRate() == null ) {
                    errorList.add(
                        new ValidMessage("rate", getMessage("FW_E_0006", getMessage("PtlAccountCurrentInterest001.rate"))));
                }
            }
        }
        else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {

        	// 查询改该账号最大生效日的活期利率信息dto
        	Map<String, Object> map = new HashMap<String,Object>();
    		map.put("finprodId", dto.getBusiAcctNo());
    		PtlAccountCurrentInterest001Dto infoLastDto = ptlAccountCurrentInterest002Service.getInitInfoLastFinprodId(map);
    		
        	//列表画面，点击编辑按钮，只能修改该账号生效日最大的活期利率 start 
        	if(dto.getEffDate() != null && StringUtil.equals("edit", dto.getOperateType())) {
        		if(null != infoLastDto && dto.getEffDate().compareTo(infoLastDto.getEffDate()) < 0) {
        			errorList.add(new ValidMessage("",getMessage("只能修改该账号最大生效日的活期利率！")));
        		}
        	}
        	//列表画面，点击编辑按钮，只能修改该账号生效日最大的活期利率 end 
        	
        	//明细画面，点击保存或提交，修改的日期必须大于本条数据以外的最大生效日期  start 
        	if(infoLastDto!=null && dto.getEffDate() != null && (StringUtil.equals("save", dto.getOperateType()) || StringUtil.equals("submit", dto.getOperateType()))) {
        		map.put("effDate", DateUtil.format(infoLastDto.getEffDate()));// sql中排除该生效日
        		// 排除该账号最大生效日之后的生效日最新的活期利率信息
        		PtlAccountCurrentInterest001Dto infoLastDto2 = ptlAccountCurrentInterest002Service.getInitInfoLastFinprodId(map);
        		if(null != infoLastDto && null !=infoLastDto2 && dto.getEffDate().compareTo(infoLastDto2.getEffDate()) <= 0) {
        			errorList.add(new ValidMessage("",getMessage("本次生效日必须大于该账号其他活期利率中的最大生效日期！")));
        		}
        	}
        	//明细画面，点击保存或提交，修改的日期必须大于本条数据以外的最大生效日期  end
        	
        	if (dto.getEffDate() != null && dto.getEffDate().compareTo(sysDate) < 0) {
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0018",
                    getMessage("PtlAccountCurrentInterest001.effDate") + DateUtil.format(dto.getEffDate()),
                    "平台日"+DateUtil.format(sysDate))));
            }
            if (dto.getRateType() != null && !"".equals(dto.getRateType())
                && dto.getRateType().equals(DictCodeList.利率类型_浮动利率.getValue())) {
                if (dto.getIndexId() == null) {
                    errorList.add(new ValidMessage("currentRate",
                        getMessage("FW_E_0006", getMessage("PtlAccountCurrentInterest001.currentRate"))));
                }
                if (dto.getCoefficient() == null) {
                    errorList.add(new ValidMessage("coefficient",
                        getMessage("FW_E_0006", getMessage("PtlAccountCurrentInterest001.coefficient"))));
                }
                if (dto.getSpreadRate() == null) {
                    errorList.add(new ValidMessage("spreadRate",
                        getMessage("FW_E_0006", getMessage("PtlAccountCurrentInterest001.spreadRate"))));
                }
            }
            else {
                if (dto.getRate() == null) {
                    errorList.add(
                        new ValidMessage("rate", getMessage("FW_E_0006", getMessage("PtlAccountCurrentInterest001.rate"))));
                }
            }
        }
        else {
            if (dto.getSelectedItem() != null)
                for (PtlAccountCurrentInterest001Dto entity : dto.getSelectedItem()) {
                    if (entity.getEffDate() != null && entity.getEffDate().compareTo(sysDate) < 0) {
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0015",
                            getMessage("PtlAccountCurrentInterest001.effDate") + DateUtil.format(dto.getEffDate()),
                            DateUtil.format(sysDate))));
                    }
                }
        }
        return errorList;
    }
}
