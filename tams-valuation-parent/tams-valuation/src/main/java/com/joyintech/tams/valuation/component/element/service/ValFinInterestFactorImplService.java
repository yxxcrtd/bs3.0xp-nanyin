/*
 * 文件名：ValFinInterestFactorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.service;


import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.PaFinCashCalcRule;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.component.element.dao.ValFinInterestFactorMapper;
import com.joyintech.tams.valuation.component.element.dto.ValFinInterestFactorDto;
import com.joyintech.tams.valuation.component.element.interf.ValFinInterestFactorService;

/**
 * 计息要素业务组件接口
 * @author zhangnian
 * @version 1.0 2018年9月6日
 * @see ValFinInterestFactorService
 * @since 1.0
 */
@Service
public class ValFinInterestFactorImplService extends BaseService<ValFinInterestFactorDto> implements ValFinInterestFactorService{

	@Autowired
	private ValFinInterestFactorMapper finInterestFactorMapper;
	
    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.息票品种, DictCode.计息基础,
            DictCode.利率类型,DictCode.重置类型_基准利率调整规则,DictCode.提前观察单位_延后时间单位
            ,DictCode.利息分配方式,DictCode.利率行情,DictCode.计息利率方式};
    }
    
    /**
     * 输入：FinInterestFactorDto
     * 输出:按要求封装entity--输出 FinInterestFactorDto
     */
    @Override
    public ValFinInterestFactorDto getFinInterestFactorDto(ValFinInterestFactorDto dto){
        //FinRateInfo--现金流利率信息   FinCashCalcRule--现金流计算规则  PaFinCashCalcRule--现金流计算规则扩展
        FinRateInfo finRateInfo = new FinRateInfo();
        FinCashCalcRule finCashCalcRule = new FinCashCalcRule(); 
        PaFinCashCalcRule paFinCashCalcRule = new PaFinCashCalcRule();
        BeanUtil.copyPropertiesIgnoreNull(finRateInfo, dto);
        BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, dto);
        BeanUtil.copyPropertiesIgnoreNull(paFinCashCalcRule,dto);
        dto.setFinRateInfo(finRateInfo);dto.setFinCashCalcRule(finCashCalcRule);
        dto.setPaFinCashCalcRule(paFinCashCalcRule);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValFinInterestFactorDto getFinInterestFactorByProductId(String finprodId,String vdate) {
//        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
//            + "/FinInterestFactor/getFinInterestFactorByProductId?finprodId="
//            + finprodId+"&vdate="+vdate+"&cashType="+ DictCodeListExt1.现金流二级类型_利息.getValue(),
//            FinInterestFactorDto.class);
    	return finInterestFactorMapper.getFinInterestFactorByProductId(finprodId,vdate,DictCodeListExt1.现金流二级类型_利息.getValue());
    }
}
