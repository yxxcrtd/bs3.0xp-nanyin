/*
 * 文件名：RskPortfolioPackage003Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioPackage003Dto;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service.RskSupervisionPortfPoint003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.PtlParamPoolConfig;

/**
 * 组合包验证
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2018年11月13日
 * @see RskPortfolioPackage003Validator
 * @since 1.0
 */
@Component
public class RskPortfolioPackage003Validator implements BeanValidator{

	@Autowired
	private RskSupervisionPortfPoint003Service rskSupervisionPortfPoint003Service;
	
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        RskPortfolioPackage003Dto dto = (RskPortfolioPackage003Dto)value;
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            //判断组合包名称是否重复
            int count = rskSupervisionPortfPoint003Service.searchRepeatPortfNameByPortName(dto.getPtlBagName());
            if(count>0){
            	resultList.add(new ValidMessage("ptlBagName", getMessage("RSKRDS_W_0034",getMessage("RskSupervisionPortfPoint003.portfolioBagName"))));
            }
        }
        if((StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)||StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT))
        		&&dto.getRskPortfolioPoolConfigDto()!=null&&dto.getRskPortfolioPoolConfigDto().getPtlParamPoolConfigList()!=null&&dto.getRskPortfolioPoolConfigDto().getPtlParamPoolConfigList().size()>0){
        	for(PtlParamPoolConfig e:dto.getRskPortfolioPoolConfigDto().getPtlParamPoolConfigList()){
        		if(e.getParamNum()!=1&&StringUtil.isEmpty(e.getLinkSign())){
        			resultList.add(new ValidMessage(getMessage("RSKRDS_E_0062")));
        		}
        	}
        }
        if (StringUtil.equals(dto.getOperateType(), "deleteBag")) {
              if(StringUtil.isNotEmpty(dto.getSelectedItem())) {
                  dto.getSelectedItem().stream().forEach(item->{
                      //检测当前这个组合包是否还存在
                      RskPortfolioPackage003Dto rskPortfolioPackage003Dto=rskSupervisionPortfPoint003Service.queryPortfBagInfoByBagId(item.getPtlBagId());
                      if (StringUtil.isNotEmpty(rskPortfolioPackage003Dto)) {
                          if (StringUtil.isNotEmpty(rskPortfolioPackage003Dto.getRskPortfolioPackageDtlDto())&&
                                  StringUtil.isEmptyList(rskPortfolioPackage003Dto.getRskPortfolioPackageDtlDto().getRskPortfolioPackageDtlList())) {
                              resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
                          }
                      }
                      //检查删除是否有正在关联的指标
                      int count=rskSupervisionPortfPoint003Service.selectReleteIndByBagId(item.getPtlBagId());
                      if(count>0){
                          resultList.add(new ValidMessage(getMessage("RSKRDS_W_0015")));
                      }
                  });
              }
        }
        if (StringUtil.equals(dto.getOperateType(), "editBag")) {
            if(StringUtil.isNotEmpty(dto.getSelectedItem())) {
                dto.getSelectedItem().stream().forEach(item->{
                    //检测当前这个组合包是否还存在
                    RskPortfolioPackage003Dto rskPortfolioPackage003Dto=rskSupervisionPortfPoint003Service.queryPortfBagInfoByBagId(item.getPtlBagId());
                    if (StringUtil.isNotEmpty(rskPortfolioPackage003Dto)) {
                        if (StringUtil.isNotEmpty(rskPortfolioPackage003Dto.getRskPortfolioPackageDtlDto())&&
                                StringUtil.isEmptyList(rskPortfolioPackage003Dto.getRskPortfolioPackageDtlDto().getRskPortfolioPackageDtlList())) {
                            resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
                        }
                    }
                });
            }
        }
        return resultList;
    }

}
