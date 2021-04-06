/*
 * 文件名：FinOpenPlanMaintenanceValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.maintenance.validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.FinOpenRuleMapper;
import com.joyintech.tams.common.base.entity.FinOpenRule;
import com.joyintech.tams.common.base.entity.FinOpenRuleKey;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.FinOpenPlanMaintenanceDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月23日
 * @see FinOpenPlanMaintenanceValidator
 * @since 1.0
 */
@Component
public class FinOpenPlanMaintenanceValidator implements BeanValidator{
    @Autowired
    private FinOpenRuleMapper finOpenRuleMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinOpenPlanMaintenanceDto dto = (FinOpenPlanMaintenanceDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        List<FinOpenPlanMaintenanceDto> selectedDto = dto.getSelectedDto();
        Date vdate = null;
        if(StringUtil.isNotEmpty(dto.getVdate())){
            vdate = DateUtil.parse(dto.getVdate());
        }
        if(selectedDto != null && selectedDto.size()>0){
            Set<Date> openPlanStartSet = new HashSet<>();
            boolean start = true;
          //add by wm 2019-08-01增加净值型产品查询净值日取值规则，当净值日取值规则为 工作日-1时，不校验其净值日与开放起始日的大小 start
            String cpriceRule = "";
            if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), dto.getProfitType())){
            	FinOpenRuleKey finOpenRuleKey = new FinOpenRuleKey();
            	finOpenRuleKey.setCashId(dto.getCashId());
            	finOpenRuleKey.setOpenType(selectedDto.get(0).getOpenType());
            	FinOpenRule finOpenRule = finOpenRuleMapper.selectByPrimaryKey(finOpenRuleKey);
            	if(finOpenRule!=null){
            		cpriceRule = finOpenRule.getCpriceRule();
            	}
            }
            //add by wm 2019-08-01增加净值型产品查询净值日取值规则，当净值日取值规则为 工作日-1时，不校验其净值日与开放起始日的大小 end
            for(int i = 0; i< selectedDto.size(); i++ ){
                FinOpenPlanMaintenanceDto entity = selectedDto.get(i);
                if(!StringUtil.equals(entity.getUpdateFlagStr(), "02")){
                    //开放起始日
                    if(entity.getOpenDateStr() != null ){
                        //开放起始日不能小于产品起息日
                        if(vdate != null && entity.getOpenDateStr().compareTo(vdate) < 0){
                            resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0004",
                                getMessage("FinOpenPlanMaintenance001.openDateStr") + DateUtil.format(entity.getOpenDateStr()),
                                getMessage("FinProductBuild001.vdate") + dto.getVdate())));
                        }
                       // 开放结束日不能小于开放起始日
                        if (entity.getOpenDateEnd() != null && entity.getOpenDateStr().compareTo(entity.getOpenDateEnd()) > 0) {
                            resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0003",
                                getMessage("FinOpenPlanMaintenance001.openDateStr")  + DateUtil.format(entity.getOpenDateStr()),
                                getMessage("FinOpenPlanMaintenance001.openDateEnd") + DateUtil.format(entity.getOpenDateEnd()))));
                        }

                    } else {
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006",
                            getMessage("FinOpenPlanMaintenance001.openDateStr"))));
                    }
                    //开放结束日
                    if(entity.getOpenDateEnd() != null){
                        //开放结束日不能小于产品起息日
                        if(vdate != null && entity.getOpenDateEnd().compareTo(vdate) < 0){
                            resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0004",
                                getMessage("FinOpenPlanMaintenance001.openDateEnd") + DateUtil.format(entity.getOpenDateEnd()),
                                getMessage("FinProductBuild001.vdate") + dto.getVdate())));
                        }
                        //开放确认日 不能小于开放结束日
                        if(entity.getOpenVdate() != null && entity.getOpenDateEnd().compareTo(entity.getOpenVdate()) > 0){
                            resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0003",
                                getMessage("FinOpenPlanMaintenance001.openDateEnd") + DateUtil.format(entity.getOpenDateEnd()),
                                getMessage("FinOpenPlanMaintenance001.openVdate")+DateUtil.format(entity.getOpenVdate()))));
                        }
                       //开放确认日不能小于开放结束日
                       if(entity.getOpenVdate() !=null && DateUtil.compareDate(entity.getOpenDateEnd(), entity.getOpenVdate()) > 0){
                            resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0127",
                                getMessage("FinOpenPlanMaintenance001.openVdate")  + DateUtil.format(entity.getOpenVdate()) , 
                                getMessage("FinOpenPlanMaintenance001.openDateEnd") + DateUtil.format(entity.getOpenDateEnd()))));
                        }
                    } else {
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006",
                            getMessage("FinOpenPlanMaintenance001.openDateEnd"))));
                    }
                    //开放确认日
                    if(entity.getOpenVdate() != null){
                        if(entity.getOpenVdate().compareTo(DateUtil.platDateFromCache()) < 0){
                            resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0004",
                                getMessage("FinOpenPlanMaintenance001.openVdate") + DateUtil.format(entity.getOpenVdate()),
                                "平台日" + DateUtil.getSysDate())));
                        }
                    } else {
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006",
                            getMessage("FinOpenPlanMaintenance001.openVdate"))));
                    }
                    
                    if(start){
                        if(openPlanStartSet.contains(entity.getOpenVdate())){
                            start = false;
                            resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0026",
                                getMessage("FinOpenPlanMaintenance001.openVdate") + DateUtil.format(entity.getOpenVdate()))));
                        }
                        else{
                            openPlanStartSet.add(entity.getOpenVdate());
                        }
                    }

                    if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), dto.getProfitType()) && null == entity.getOpenNdate()){
                        resultList.add(new ValidMessage(this.getMessage("FW_E_0006",
                            getMessage("FinOpenPlanMaintenance001.Ndate"))));
                    } 
                    //净值日 
                    //modify by wm 2019-08-01增加净值型产品查询净值日取值规则，当净值日取值规则为 工作日-1时，不校验其净值日与开放起始日的大小 start
                    else if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), dto.getProfitType()) 
                        && entity.getOpenDateStr() != null && DateUtil.compareDate(entity.getOpenDateStr(), entity.getOpenNdate()) > 0
                        && !StringUtil.equals(cpriceRule, DictCodeListExt1.净值日规则_开放确认日_1工作日.getValue())){
                        resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0127",
                            getMessage("FinOpenPlanMaintenance001.Ndate") + DateUtil.format(entity.getOpenNdate()), 
                            getMessage("FinOpenPlanMaintenance001.openDateStr") + DateUtil.format(entity.getOpenDateStr()))));
                    }
                    //modify by wm 2019-08-01增加净值型产品查询净值日取值规则，当净值日取值规则为 工作日-1时，不校验其净值日与开放起始日的大小 end
                    
                    //开放周期check
                 /*   for(int j = 0; j < selectedDto.size(); j++ ){
                        if(i == j){
                            continue;
                        }
                        FinOpenPlanMaintenanceDto entity2 = selectedDto.get(j);
                        if(DateUtil.compareDate(entity.getOpenDateStr(), entity2.getOpenDateStr()) >=0 &&  DateUtil.compareDate(entity.getOpenDateStr(), entity2.getOpenDateEnd()) <= 0){
                            resultList.add(new ValidMessage(this.getMessage("计划列表中第["+ (i+1) + "]行开放开始日被包含在第["+ (j+1) +"]行开放计划周期内!" )));
                        }
                        if(DateUtil.compareDate(entity.getOpenDateEnd(), entity2.getOpenDateStr()) >=0 &&  DateUtil.compareDate(entity.getOpenDateEnd(), entity2.getOpenDateEnd()) <= 0){
                            resultList.add(new ValidMessage(this.getMessage("计划列表中第["+ (i+1) + "]行开放结束日被包含在第["+ (j+1) +"]行开放计划周期内!" )));
                        }
                    } */
                }
            }
        }
        return resultList;
    }

}
