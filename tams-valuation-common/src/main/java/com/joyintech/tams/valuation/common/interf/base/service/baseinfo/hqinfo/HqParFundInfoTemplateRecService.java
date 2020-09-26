/*
 * 文件名：HqParCloseFundInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqParFundInfo;

/**
 * 行情信息-万德开放式基金 
 * 
 * @author wangwei1
 * @version 1.0 2018年6月28日
 * @see HqParFundInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class HqParFundInfoTemplateRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                           TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();
        
        List<HqParFundInfo> hqParOpenfundInfoList = new ArrayList<HqParFundInfo>();

        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        
        //文件路径设置  TODO 此部分处理待以后从画面入口的时候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。
        // 1、文件读取解析 进行数据有效性的check,并生出进入日志表的数据结构
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
        hqParOpenfundInfoList = returnResult.getT(HqParFundInfo.class);
        
        List<HqParFundInfo> infolist = new ArrayList<HqParFundInfo>();
        if (acctEntity.getTmpEName().equals(TempConstant.开放式基金估值)) {
        	for (HqParFundInfo info : hqParOpenfundInfoList) {
				if (info.getTotalNet() != null) {
					infolist.add(info);
				}
			}
        } else {
        	infolist = hqParOpenfundInfoList;
        }

        // 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 判断返回结果不为空
            List<FinProductValuation> valFinProductValuationList = (List<FinProductValuation>)handleListToVaLInfo(
            		infolist, acctEntity.getResovleDate()).get("valFinProductValuation");
            
            result.setListValFinProductValuation(valFinProductValuationList);
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param hqParOpenfundInfoList
     * @return
     * @see
     * @since 1.0
     */
    private Map<String, Object>  handleListToVaLInfo(List<HqParFundInfo> hqParOpenfundInfoList, Date date) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinProductValuation> valFinProductValuationList = new ArrayList<FinProductValuation>();
        for (HqParFundInfo dto : hqParOpenfundInfoList) {
            // 场外金融产品行情 处理
            FinProductValuation valFinProductValuation = getValInfo(dto, date);
            valFinProductValuationList.add(valFinProductValuation);
        }
        resultMap.put("valFinProductValuation", valFinProductValuationList);
        return resultMap;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinProductValuation getValInfo(HqParFundInfo dto, Date date) {
        FinProductValuation valFinProductValuation =  new FinProductValuation();
        
        //金融产品代码  FINPROD_ID              A.证券代码  需根据A.证券代码确认是债券还是基金，规则由需求提供
        valFinProductValuation.setFinprodId(dto.getZqdm());
        //金融产品类型  FINPROD_TYPE                默认值：F06 数据字典：金融产品类型-基金
        valFinProductValuation.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        //公布日期    PUBLISH_DATE               
        valFinProductValuation.setPublishDate(date);
        //估值日期    VAL_DATE                默认值：参数给定    
        valFinProductValuation.setValDate(dto.getDataDate());
       // 估值  VALUATION               A.单位资产净值    
        valFinProductValuation.setValuation(dto.getNetValue());
       // 估值类型    VAL_TYPE                    根据A.基金类型判断，如果为货币型 则为万份收益，否则为净值
//        valFinProductValuation.setValType("货币型".equals(dto.getFundType())?"万份收益":"净值");
        
        return valFinProductValuation;
    }




   
}
