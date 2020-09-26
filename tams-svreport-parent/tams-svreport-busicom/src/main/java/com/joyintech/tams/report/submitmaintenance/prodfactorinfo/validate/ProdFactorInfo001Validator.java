/*
 * 文件名：ProdFactorInfo001Validator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：liaochunyu
 * 修改时间：2017年12月21日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.report.submitmaintenance.prodfactorinfo.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.submitmaintenance.prodfactorinfo.dto.ProdFactorInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.prodfactorinfo.service.ProdFactorInfo001Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author liaochunyu
 * @version 1.0 2017年12月21日
 * @see ProdFactorInfo001Validator
 * @since 1.0
 */
@Component
public class ProdFactorInfo001Validator implements BeanValidator{
    
    /**
     * prodFactorInfo001Service
     */
    @Autowired
    private ProdFactorInfo001Service prodFactorInfo001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        //删除的校验 
        ProdFactorInfo001Dto entity = (ProdFactorInfo001Dto)value;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<ProdFactorInfo001Dto> dtoList = entity.getDtoList();
        if(dtoList.size() > 0){
            for(ProdFactorInfo001Dto dto : dtoList) {
                //6.1.1删除时进行判断：产品起息之后不能对其进行删除。
                if(dto.getPrdVdate() != null){
                    if(dto.getPrdVdate().before(DateUtil.now())){
                        errorList.add(new ValidMessage(dto.getProdId() + ":" + this.getMessage("CHB_PRD_BASEINFO_001")));
                    }  
                }
                //6.1.2报备状态 为“报备成功”时，点击【删除】按钮时给出提示信息：“产品要素已报备成功，不允许删除产品报送要素信息”。
                if(dto.getDeclareStatus() != null){
                    if(dto.getDeclareStatus().equals("01")){
                        errorList.add(new ValidMessage(dto.getProdId() + ":" + this.getMessage("CHB_PRD_BASEINFO_002")));
                    }  
                }
                //6.1.4产品报送要素的数据状态为“有效”且发行状态为“发行成功”时，点击【删除】按钮时给出提示信息“产品要素已发行成功，不允许删除产品报送要素信息”。
                //6.1.5产品报送要素的数据状态为“有效”且发行状态为“发行失败”时，点击【删除】按钮时给出提示信息“产 品要素已发行失败，不允许删除产品报送要素信息”。
                if(dto.getIssuingStatus() != null){
                    if(!dto.getIssuingStatus().equals("01")){
                        errorList.add(new ValidMessage(dto.getProdId() + ":" + this.getMessage("CHB_PRD_BASEINFO_003")));
                    }  
                }
//                //6.1.6对应的产品已发生产品交易或费用交易时，点击【删除】按钮时给出提示信息：
//                if(prodFactorInfo001Service.isDeal(dto)){
//                    errorList.add(new ValidMessage(dto.getProdId() + ":" + this.getMessage("CHB_PRD_BASEINFO_004")));
//                }
            }
        }
        
        return errorList;
    }

}
