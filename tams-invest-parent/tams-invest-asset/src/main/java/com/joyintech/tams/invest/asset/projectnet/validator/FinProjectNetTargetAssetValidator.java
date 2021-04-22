/*
 * 文件名：FinProjectTargetAssetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.projectnet.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.invest.asset.projectnet.dto.FinProjectNetTargetAssetDto;
import com.joyintech.tams.invest.asset.projectnet.service.FinProjectNetTargetAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  权益类资产--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see FinProjectNetTargetAssetValidator
 * @since 1.0
 */
@Component
public class FinProjectNetTargetAssetValidator implements BeanValidator{

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Autowired
    private FinProjectNetTargetAssetService finProjectNetTargetAssetService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProjectNetTargetAssetDto dto = (FinProjectNetTargetAssetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
                for(FinProjectNetTargetAssetDto entity : dto.getSelectedItem()){
                    if(commonBusinessService.isTransInAsset(entity.getFinprodId())!=0){
                        resultList.add(new ValidMessage("",
                            getMessage("资产存在交易,不可删除!")));
                        break;
                    }
                }
            }
        }
        else{
            if (StringUtil.isNotEmpty(dto.getFinprodMarketId()) && GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())) {
                String finprodId = finProjectNetTargetAssetService.checkMarketExists(dto.getFinprodMarketId());
                if (StringUtil.isNotEmpty(finprodId)) {
                    resultList.add(
                            new ValidMessage("finprodMarketId", "资产代码已存在。"));
                }
            }

            if (StringUtil.isNotEmpty(dto.getMdate()) && (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())||GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel()))) {
                if (dto.getVdate().compareTo(dto.getMdate())>=0){
                    resultList.add(
                            new ValidMessage("mdate", "退出日必须大于成立日"));
                }
            }

        }
        if(null!=dto.getVdate()){
            if(StringUtil.isNotEmpty(dto.getFinprodId())){
                Date maxTradeDate = commonBusinessService.getMaxTradeDate(dto.getFinprodId());
                if(null!=maxTradeDate){
                    if(DateUtil.compareDate(dto.getVdate(), maxTradeDate)<1){
                        resultList.add(new ValidMessage("vdate",
                            getMessage("BUSS_COM_E_0035", getMessage("FinProjectNetTargetAsset002.vdate"),"最大交易日")));
                    }
                }
            }
        }
        return resultList;
    }

}
