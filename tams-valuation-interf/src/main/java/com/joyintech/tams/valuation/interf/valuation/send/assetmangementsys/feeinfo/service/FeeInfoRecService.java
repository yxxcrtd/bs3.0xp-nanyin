/*
 * 文件名：taxInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto.ValAddTaxFlowprocessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.dao.FeeInfoMapper;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.dto.FeeInfoDto;
import com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.dto.FeeInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 产品费用Service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月20日
 * @see FeeInfoRecService
 * @since 1.0
 */
@Service
public class FeeInfoRecService {
    /**
     * 产品费用Mapper
     */
    @Autowired
    private FeeInfoMapper feeInfoMapper;
    public ValAddTaxFlowprocessorResult execute(SendReaderEntity item){
        List<FeeInfoDto> listdto=
            getFeeType(feeInfoMapper.queryFeeData(item));
        //查询现金流代码
        for(FeeInfoDto dto:listdto){
            FinCashInfo finCashInfo=feeInfoMapper.queryCashId(dto);
            FeeInfoDto feeInfoDto=null;
            //非空判断，否则不差此数据
            if(!StringUtils.isEmpty(finCashInfo)){
                String cashId=finCashInfo.getCashId();
                Map<String, Object> paramer = new HashMap<String, Object>();
                paramer.put("exeDate", item.getExeDate());
                paramer.put("cashId", cashId);
                //根据查出的现金流代码查询费率,计息基础
                feeInfoDto=feeInfoMapper.queryFeeRate(paramer);  
            }
            //if 基数类型 = 资产净值
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("exeDate", item.getExeDate());
            map.put("prodId", dto.getBooksetId());
            FeeInfoDto Dto=feeInfoMapper.queryFeeCalcBase(map);
            //0
            BigDecimal zero = new BigDecimal(0);
            if(StringUtils.isEmpty(Dto.getTotalProductShare())){
                Dto.setTotalProductShare(zero);
            } 
            if(StringUtils.isEmpty(Dto.getNetAssetValue())){
                Dto.setNetAssetValue(zero);
            }
            if(!StringUtils.isEmpty(feeInfoDto)){
                //计息基础
                dto.setFeeCalcType(feeInfoDto.getFeeCalcType());
                //费率
                dto.setFeeRate(feeInfoDto.getFeeRate());
                if("03".equals(feeInfoDto.getBaseType())){
                    //计费基数 =产品总份额*单位净值
                    dto.setFeeCalcBase(Dto.getTotalProductShare().multiply(Dto.getNetAssetValue()));
                }else {
                    //计费基数 = 产品总份额
                    dto.setFeeCalcBase(Dto.getTotalProductShare());
                }
            }    
        }
        ValAddTaxFlowprocessorResult feeInfoProcessorResult = new ValAddTaxFlowprocessorResult();
        feeInfoProcessorResult.setDatas(listdto);
        feeInfoProcessorResult.setTmpEName("BProdCostDataBack");
        return feeInfoProcessorResult;
    }
    /**
     * 获得费用类型
     * <一句话功能简述>
     * <功能详细描述>
     * @param listdto
     * @return
     * @see
     * @since 1.0
     */
    private List<FeeInfoDto> getFeeType(List<FeeInfoDto> listdto) {
        for(FeeInfoDto dto:listdto){
            if("22060101".equals(dto.getSubjectNo())){
               dto.setFeeTypeCode("固定管理费");
               dto.setFeeType("C03");
            }else if("22060201".equals(dto.getSubjectNo())){
                dto.setFeeTypeCode("超额管理费");
                dto.setFeeType("C04");
            }else if("22070101".equals(dto.getSubjectNo())){
                dto.setFeeTypeCode("托管费");
                dto.setFeeType("C05");
            }else if("22080101".equals(dto.getSubjectNo())){
                dto.setFeeTypeCode("销售服务费");
                dto.setFeeType("C06");
            }else if("22100101".equals(dto.getSubjectNo())){
                dto.setFeeTypeCode("代销费");
                dto.setFeeType("C10");
            }else if("22110101".equals(dto.getSubjectNo())){
                dto.setFeeTypeCode("外包服务费");
                dto.setFeeType("C09");
            }   
        }
        return  listdto;
    }
}
