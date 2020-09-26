/*
 * 文件名：ChannelFeeBaseInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.channel;

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

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelFeeInfo;

/**
 * 通道费用基础信息
 * @author HuangJian
 * @version 1.0 2019年3月12日
 * @see ChannelFeeBaseInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ChannelFeeBaseInfoService {
    
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
//    @Autowired
//    private InterfCommonService interfCommonService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ChannelBaseInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
            TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        ChannelBaseInfoCommonResult result = new ChannelBaseInfoCommonResult();
        List<ValChannelFeeInfo> listValChannelFeeInfo= new ArrayList<ValChannelFeeInfo>();

        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValChannelFeeInfo = returnResult.getT(ValChannelFeeInfo.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValChannelFeeInfo(listValChannelFeeInfo,acctEntity.getResovleDate());

        // 判断返回结果不为空
        if (map != null) {
            //现金流信息
            List<FinCashInfo> valFinCashInfoList = (List<FinCashInfo>)map.get("FinCashInfo");
            result.setListValCashInfo(valFinCashInfoList);
           
            //通道代码
            List<String> tdlist = (List<String>)map.get("ChannelId");
            result.setTdlist(tdlist);
        }
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;

    }
    
    
    private Map<String,Object> handleValChannelFeeInfo(List<ValChannelFeeInfo> dto,Date resovleDate){
        Map<String, Object> resultMap = new HashMap<>();
        //现金流信息
        List<FinCashInfo> valFinCashInfoList = new ArrayList<FinCashInfo>();
        
        List<String> tdlist = new ArrayList<>();
        Map<String,String> tdMap = new HashMap<String,String>();
        
        for(ValChannelFeeInfo item :dto){
            /**add by wujie on 20190325 start**/
        	item.setCreateTime(resovleDate);
        	/**add by wujie on 20190325 end**/
            FinCashInfo FinCashInfo = getFinCashInfo(item);
            valFinCashInfoList.add(FinCashInfo);
            tdMap.put(item.getChannelId(), item.getChannelId());
        }
        for(String chlId :tdMap.keySet()){
        	tdlist.add(chlId);
        }
        resultMap.put("ChannelId", tdlist);
        resultMap.put("FinCashInfo", valFinCashInfoList);
        return resultMap;
    }
    
    private FinCashInfo getFinCashInfo(ValChannelFeeInfo dto){
        FinCashInfo  result = new FinCashInfo();
        //现金流代码 = InterfHelper.getMd5CashId_(B.通道代码,B.费用ID)
        result.setCashId(InterfHelper.getMd5CashId_(dto.getChannelId(),dto.getFeeId()));
        // 现金流一级类型 = 数据字典：现金流一级类型.费用
        result.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
        //现金流二级类型=B.费用类型
        result.setCashType2(dto.getFeeType());
        //金融产品代码=B.通道代码
        result.setFinprodId(dto.getChannelId());
        //金融产品类型=数据字典：金融产品类型.通道
        result.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        //分支序号=设置全局变量(默认为0）
        result.setBranch(InterfCommonConstant.分支序号_默认);
        /**add by wujie on 20190325 start**/
        result.setCreateTime(dto.getCreateTime());
        /**add by wujie on 20190325 end**/
        return result;
    }
}
