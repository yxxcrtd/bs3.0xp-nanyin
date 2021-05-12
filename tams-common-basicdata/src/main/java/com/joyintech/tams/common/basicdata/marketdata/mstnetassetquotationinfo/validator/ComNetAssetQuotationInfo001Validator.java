/*
 * 文件名：MstNetAssetQuotationInfo001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.mstnetassetquotationinfo.validator;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.mstnetassetquotationinfo.dto.ComNetAssetQuotationInfo001Dto;
import com.joyintech.tams.common.basicdata.marketdata.mstnetassetquotationinfo.service.ComNetAssetQuotationInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 计划类（净值型）资产估值行情信息验证类
 * 
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see ComNetAssetQuotationInfo001Validator
 * @since 1.0
 */
@Component
public class ComNetAssetQuotationInfo001Validator implements BeanValidator{
    
    /**
     * 计划类（净值型）资产估值行情信息Mapper
     */
    @Autowired
    private ComNetAssetQuotationInfo001Service comNetAssetQuotationInfo001Service;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        ComNetAssetQuotationInfo001Dto dto = (ComNetAssetQuotationInfo001Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if(dto.getSelectedItem()!=null||dto.getSelectedItem().size()>0){
                    //删除时估值日期小于平台日期，报错
                    for(ComNetAssetQuotationInfo001Dto selectDto:dto.getSelectedItem()){
						/*
						 * if (selectDto.getValDate().compareTo(sysDate) < 0) { errorList.add(new
						 * ValidMessage("finprodId",getMessage("BUSS_COM_E_0015",
						 * getMessage("MstNetAssetQuotationInfo001.valuationDate"),
						 * DateUtil.format(sysDate)))); }
						 */
                        //add wangwei 1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
                        //修改/删除时，仅能修改/删除导入日为平台日的数据
//                        if (StringUtil.isNotEmpty(selectDto.getImportDate()) && DateUtil.compareDate(DateUtil.formatDate(selectDto.getImportDate()), DateUtil.format(sysDate))  != 0) {
//                        	errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
//                        }
                    }
                }
            }
            else{
                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    if(!StringUtil.isNotEmpty(dto.getFinProdName())||
                        !StringUtil.isNotEmpty(dto.getValDate())||!StringUtil.isNotEmpty(dto.getFinProdMarketId())){
                        return errorList;
                    }
                    Map<String,Object> map = RequestUtil.getPageParam();
                    //Map<String,Object> map = new HashMap<>();
                    map.put("finprodId", dto.getFinprodId());
                    map.put("valDate", dto.getValDateStr());
                    //新建时，资产代码+估值日期检索货币型基金行情信息，数据已存在的情况下，报错
                    if (comNetAssetQuotationInfo001Service.selectByIdAndDate(map) != null) {
                        errorList.add(new ValidMessage("finprodId",getMessage("BUSS_COM_E_0006",
                            getMessage("ComNetAssetQuotationInfo002.assetCode")+":"+dto.getFinProdMarketId()+
                            getMessage("ComNetAssetQuotationInfo002.valuationDate")+":"+dto.getValDateStr())));
                    }
                    //新建时估值日期小于平台日期，报错
					/*
					 * if (dto.getValDate().compareTo(sysDate) < 0) { errorList.add(new
					 * ValidMessage("valDate",getMessage("BUSS_COM_E_0004",getMessage(
					 * "MstNetAssetQuotationInfo001.valuationDate"), DateUtil.format(sysDate)))); }
					 */
                }
                else if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                  //编辑时估值日期小于平台日期，报错
					/*
					 * if (dto.getValDate().compareTo(sysDate) < 0) { errorList.add(new
					 * ValidMessage(getMessage("BUSS_COM_E_0018",getMessage(
					 * "MstNetAssetQuotationInfo001.valuationDate"), DateUtil.format(sysDate)))); }
					 */
                    
                    //add wangwei 1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
                    //修改/删除时，仅能修改/删除导入日为平台日的数据
//                    if (StringUtil.isNotEmpty(dto.getImportDate()) && DateUtil.compareDate(DateUtil.formatDate(dto.getImportDate()), DateUtil.format(sysDate))  != 0) {
//                    	errorList.add(new ValidMessage("修改/删除时,仅能修改/删除导入日为平台日的数据!"));
//                    }
                    
                } 
            }
        }
        else{
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                if(!StringUtil.isNotEmpty(dto.getFinProdName())||
                        !StringUtil.isNotEmpty(dto.getValDate())||!StringUtil.isNotEmpty(dto.getFinProdMarketId())){
                    return errorList;
                }
                Map<String,Object> map = new HashMap<>();
                map.put("finprodId", dto.getFinprodId());
                map.put("valDate", dto.getValDateStr());
                //新建时，资产代码+估值日期检索货币型基金行情信息，数据已存在的情况下，报错
                if (comNetAssetQuotationInfo001Service.selectByIdAndDate(map) != null) {
                    errorList.add(new ValidMessage("finprodId",getMessage("BUSS_COM_E_0006",
                            getMessage("ComNetAssetQuotationInfo002.assetCode")+":"+dto.getFinProdMarketId()+
                                    getMessage("ComNetAssetQuotationInfo002.valuationDate")+":"+dto.getValDateStr())));
                }
                
                
				/*add wangwei1 YUCHUBS-521债券估值行情、基金估值行情、计划类资产估值行情：功能优化
				 * //新建时估值日期小于平台日期，报错
				 *  if (dto.getValDate().compareTo(sysDate) < 0) {
				 * errorList.add(new
				 * ValidMessage("valDate",getMessage("BUSS_COM_E_0004",getMessage(
				 * "MstNetAssetQuotationInfo001.valuationDate"), DateUtil.format(sysDate)))); }
				 */
            }
        }
        return errorList;
    }
    

}
