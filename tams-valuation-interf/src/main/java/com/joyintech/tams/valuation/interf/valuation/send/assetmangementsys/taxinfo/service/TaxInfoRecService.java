/*
 * 文件名：taxInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.taxinfo.service;
import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.taxinfo.dao.TaxInfoMapper;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.taxinfo.dto.TaxInfoDto;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.taxinfo.dto.TaxInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 增值税缴纳Service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月19日
 * @see TaxInfoRecService
 * @since 1.0
 */
@Service
public class TaxInfoRecService {
    /**
     * 增值税缴纳Mapper
     */
    @Autowired
    private TaxInfoMapper taxInfoMapper;
    
    public ValAddTaxFlowprocessorResult execute(SendReaderEntity item){
        //TODO Auto-generated method stub
        return getTaxInfoProcessorResult(taxInfoMapper.queryTaxData(item));
    }
    /**
     * 获得TaxInfoProcessorResult
     * <一句话功能简述>
     * <功能详细描述>
     * @param listdto
     * @return
     * @see
     * @since 1.0
     */
    private ValAddTaxFlowprocessorResult getTaxInfoProcessorResult(List<TaxInfoDto> listdto) {
        //返回值 = 税额 * ((1+税率))
        double number = (1+0.03);
        BigDecimal bigDecimal=BigDecimal.valueOf(number);
        //0
        BigDecimal zero = new BigDecimal(0);
        for(TaxInfoDto dto:listdto){
            //查询出的数据若为空，默认为0
            //城建税附加
            if(StringUtils.isEmpty(dto.getAdditionalTaxCityBuild())){
                dto.setAdditionalTaxCityBuild(zero);
            }
            //教育费附加 
            if(StringUtils.isEmpty(dto.getEducationFeePlus())){
                dto.setEducationFeePlus(zero);
            }
            //地方教育费附加
            if(StringUtils.isEmpty(dto.getLocalEducationFeePlus())){
                dto.setLocalEducationFeePlus(zero);
            }
            //利息收入税 
            if(StringUtils.isEmpty(dto.getInterestIncomeTax())){
                dto.setInterestIncomeTax(zero);
            }
            //红利收入税 
            if(StringUtils.isEmpty(dto.getDividendTax())){
                dto.setDividendTax(zero);
            }
            //价差收入税
            if(StringUtils.isEmpty(dto.getSpreadIncomeTax())){
                dto.setSpreadIncomeTax(zero);
            }
            //利息收入=利息收入税 *((1+税率))/(税率 ,小数位数,尾数处理方式)税率:3% 小数位数:2 尾差处理方式:四舍五入 
            dto.setInterestIncome(dto.getInterestIncomeTax().multiply(bigDecimal)
                .divide(new BigDecimal("0.03"),2, BigDecimal.ROUND_HALF_UP));
            //红利收入=红利收入税*((1+税率))/(税率 ,小数位数,尾数处理方式)税率:3% 小数位数:2 尾差处理方式:四舍五入 
            dto.setDividend(dto.getDividendTax().multiply(bigDecimal)
                .divide(new BigDecimal("0.03"),2, BigDecimal.ROUND_HALF_UP));
            //价差收入=价差收入税*((1+税率))/(税率 ,小数位数,尾数处理方式)税率:3% 小数位数:2 尾差处理方式:四舍五入 
            dto.setSpreadIncome(dto.getSpreadIncomeTax().multiply(bigDecimal)
                .divide(new BigDecimal("0.03"),2, BigDecimal.ROUND_HALF_UP));
            //应交增值税为三个增值税合计
            dto.setVatPayable(dto.getInterestIncomeTax().add(dto.getDividendTax())
                .add(dto.getSpreadIncomeTax()));
            //应交增值附加税为三个附加税合计
            dto.setSupertax(dto.getAdditionalTaxCityBuild().add(dto.getEducationFeePlus())
                .add(dto.getLocalEducationFeePlus()));
        }
        ValAddTaxFlowprocessorResult valAddTaxFlowprocessorResult = new ValAddTaxFlowprocessorResult();
        valAddTaxFlowprocessorResult.setDatas(listdto);
        valAddTaxFlowprocessorResult.setTmpEName("ValAddTaxDataBack");
        return  valAddTaxFlowprocessorResult;
    }
}
