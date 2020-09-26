/*
 * 文件名：TxtReadUtils.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqBondMarketInfo;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqBondValuationInfo;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqFundMarketInfo;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqIntRateInfo;

/**
 * 解析txt接口文件
 * @author wangwei1
 * @version 1.0 2018年6月26日
 * @see TxtReadUtils
 * @since 1.0
 */
@Service
@Scope("prototype")
public class TxtReadUtils {
	
	 /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(TxtReadUtils.class);

    /**
     * txt类型接口文档解析
     * @param acctEntity
//     * @param xmlPath 文件路径
//     * @param tmpEName 模板名称
//     * @param tempNo 预留字段，暂为空
     * @see
     * @since 1.0
     */
    public <T> TemplateCheckReturnResult readTxt(TemplateOrderInfoReaderResult acctEntity) {
        
        TemplateCheckReturnResult result = new TemplateCheckReturnResult();
        //文件路径
        String filePath= acctEntity.getFilePath();
        //模板英文名
        String tmpEName= acctEntity.getTmpEName();
        //DTO名称
        String dtoName = acctEntity.getDtoName();
        List<T> listAll = new ArrayList<>();
        // 获取文件绝对路径
        List<String> pathList = SearchFilePath.queryFilePath(filePath, tmpEName);
        for (String path : pathList) {
            List list = new ArrayList();
            InputStreamReader isr;
            BufferedReader br =null;
            try {          	
                File file = new File(path);
                if (file.isFile() && file.exists()) {
					isr = new InputStreamReader( new FileInputStream(file), "utf-8");
                    br = new BufferedReader(isr);
                    String lineTxt = null; // 读取文件的方法
//                    int num=0;
                    // 中证估值行情
                    if(dtoName.equals(TempConstant.债券估值行情)){
                        while ((lineTxt = br.readLine()) != null) {
                            List<HqBondValuationInfo> infoList = readBondValuation(lineTxt);
                            if (infoList != null) {
                                list.addAll(infoList);
                            }
                        }
                    // 债券市场行情
                    } else if (dtoName.equals(TempConstant.债券市场行情)){
                        List<HqBondMarketInfo> infoList=new ArrayList<HqBondMarketInfo>();
                        String date = "";
                        while ((lineTxt = br.readLine()) != null) {
                            HqBondMarketInfo info=new HqBondMarketInfo();
                            String[] arrStrings = lineTxt.split("\\|"); // 用于把一个字符串分割成字符串数组
                            String s1 = arrStrings[0].trim().toString();
                            if("HEADER".equals(s1)){
                                date = arrStrings[6].trim().toString().substring(0, 8);
                            }
                            String s2 = arrStrings[1].trim().toString();
                            if(!"HEADER".equals(s1) && !"TRAILER".equals(s1) && 
                                ("01".equals(s2.substring(0,2)) || "1".equals(s2.substring(0,1)))){
                                info.setSecId(arrStrings[1].trim().toString());
                                info.setValueSource(DictCodeListExt1.估值交易市场_上交所.getValue());
                                info.setValueDate(DateUtil.parse(date));
                                info.setMaxPrice(new BigDecimal(arrStrings[7].trim().toString()));
                                info.setMinPrice(new BigDecimal(arrStrings[8].trim().toString()));
                                info.setClosePrice(new BigDecimal(arrStrings[10].trim().toString()));
                                info.setBuyOnePrice(new BigDecimal(arrStrings[11].trim().toString()));
                                info.setSellOnePrice(new BigDecimal(arrStrings[13].trim().toString()));
                                infoList.add(info);
                            }
                        }
                        list.addAll(infoList);
                    } else if (dtoName.equals(TempConstant.基金市场行情)){
                        List<HqFundMarketInfo> infoList=new ArrayList<HqFundMarketInfo>();
                        String date = "";
                        while ((lineTxt = br.readLine()) != null) {
                            HqFundMarketInfo info=new HqFundMarketInfo();
                            String[] arrStrings = lineTxt.split("\\|"); // 用于把一个字符串分割成字符串数组
                            String s1 = arrStrings[0].trim().toString();
                            if("HEADER".equals(s1)){
                                date = arrStrings[6].trim().toString().substring(0, 8);
                            }
                            String s2 = arrStrings[1].trim().toString();
                            if(!"HEADER".equals(s1) && !"TRAILER".equals(s1) && "5".equals(s2.substring(0,1))){
                                info.setFundCode(arrStrings[1].trim().toString());
                                info.setValueSource(DictCodeListExt1.估值交易市场_上交所.getValue());
                                info.setValueDate(DateUtil.parse(date));
                                info.setMaxPrice(new BigDecimal(arrStrings[7].trim().toString()));
                                info.setMinPrice(new BigDecimal(arrStrings[8].trim().toString()));
                                info.setClosePrice(new BigDecimal(arrStrings[10].trim().toString()));
                                info.setBuyOnePrice(new BigDecimal(arrStrings[11].trim().toString()));
                                info.setSellOnePrice(new BigDecimal(arrStrings[13].trim().toString()));
                                infoList.add(info);
                            }
                        }
                        list.addAll(infoList);
                    } else if(dtoName.equals(TempConstant.利率行情)) {
                        while ((lineTxt = br.readLine()) != null) {
                            HqIntRateInfo rateInfo = readRateInfo(lineTxt);
                            if (rateInfo != null) {
                                list.add(readRateInfo(lineTxt));
                            }
                        }
                    }else {
                        //预留其他txt接口文件处理
                    } 
                }
            }
            catch (Exception e) {
            	logger.error("",e);
            }finally{
            	if(br!=null){
            		try {
						br.close();
					} catch (IOException e) {
						logger.error("",e);
					} 
            	}
            }
            listAll.addAll(list);
        }
        result.setT(listAll);
        return result;
    }
    
    /**
     * 
     * 读取中证债券估值行情
     * 
     * @param line
     * @return
     */
    private List<HqBondValuationInfo> readBondValuation(String line) {
        List<HqBondValuationInfo> list = new ArrayList<HqBondValuationInfo>();
        String[] arrStrings = line.split("\\|"); // 用于把一个字符串分割成字符串数组
        if (arrStrings.length > 1) {
            HqBondValuationInfo info = null;
            if (StringUtils.isNotEmpty(arrStrings[1].trim())) {
                info = new HqBondValuationInfo();
                info.setSecId(arrStrings[1].trim().toString());
                info.setValueource(DictCodeList.交易流通场所_上海证券交易所.getValue());
                info.setValueDate(DateUtil.parse(arrStrings[0].trim().toString()));
                info.setPrice(arrStrings[4].trim().toString().equals("")?null:new BigDecimal(arrStrings[4].trim().toString()));
                info.setBpyield(arrStrings[5].trim().toString().equals("")?null:new BigDecimal(arrStrings[5].trim().toString()));
                info.setmDuration(arrStrings[6].trim().toString().equals("")?null:new BigDecimal(arrStrings[6].trim().toString()));
                info.setmConvexity(arrStrings[7].trim().toString().equals("")?null:new BigDecimal(arrStrings[7].trim().toString()));
                info.setNetprice(arrStrings[8].trim().toString().equals("")?null:new BigDecimal(arrStrings[8].trim().toString()));
                list.add(info);
            }
            if (StringUtils.isNotEmpty(arrStrings[2].trim())) {
                info = new HqBondValuationInfo();
                info.setSecId(arrStrings[2].trim().toString());
                info.setValueource(DictCodeList.交易流通场所_深圳证券交易所.getValue());
                info.setValueDate(DateUtil.parse(arrStrings[0].trim().toString()));
                info.setPrice(arrStrings[4].trim().toString().equals("")?null:new BigDecimal(arrStrings[4].trim().toString()));
                info.setBpyield(arrStrings[5].trim().toString().equals("")?null:new BigDecimal(arrStrings[5].trim().toString()));
                info.setmDuration(arrStrings[6].trim().toString().equals("")?null:new BigDecimal(arrStrings[6].trim().toString()));
                info.setmConvexity(arrStrings[7].trim().toString().equals("")?null:new BigDecimal(arrStrings[7].trim().toString()));
                info.setNetprice(arrStrings[8].trim().toString().equals("")?null:new BigDecimal(arrStrings[8].trim().toString()));
                list.add(info);
            }
            if (StringUtils.isNotEmpty(arrStrings[3].trim())) {
                info = new HqBondValuationInfo();
                info.setSecId(arrStrings[3].trim().toString());
                info.setValueource(DictCodeList.交易流通场所_银行间债券市场.getValue());
                info.setValueDate(DateUtil.parse(arrStrings[0].trim().toString()));
                info.setPrice(arrStrings[4].trim().toString().equals("")?null:new BigDecimal(arrStrings[4].trim().toString()));
                info.setBpyield(arrStrings[5].trim().toString().equals("")?null:new BigDecimal(arrStrings[5].trim().toString()));
                info.setmDuration(arrStrings[6].trim().toString().equals("")?null:new BigDecimal(arrStrings[6].trim().toString()));
                info.setmConvexity(arrStrings[7].trim().toString().equals("")?null:new BigDecimal(arrStrings[7].trim().toString()));
                info.setNetprice(arrStrings[8].trim().toString().equals("")?null:new BigDecimal(arrStrings[8].trim().toString()));
                list.add(info);
            }
        } else {
            return null;
        }
        return list;
    }
    
    /**
     * 
     * 利率行情信息读取
     *
     * @param line
     * @return
     */
    private HqIntRateInfo readRateInfo(String line) {
        HqIntRateInfo info = new HqIntRateInfo();
        String[] arrStrings = line.split("\\|"); // 用于把一个字符串分割成字符串数组
        if (!arrStrings[0].trim().equals("PUBLISHDATE")) {
            info.setIntRateId(arrStrings[2].trim().toString());
            info.setRelaeseDate(DateUtil.parse(arrStrings[0].trim().toString()));
            info.setEffDate(DateUtil.parse(arrStrings[5].trim().toString()));
            info.setIntRate(new BigDecimal(arrStrings[4].trim().toString()));
        } else {
            return null;
        }
        return info;
    }
}
