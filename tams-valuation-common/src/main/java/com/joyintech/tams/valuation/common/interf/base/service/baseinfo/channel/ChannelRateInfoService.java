/*
 * 文件名：ChannelRateInfoService.java
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

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
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
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelRateAdjust;


/**
 * 通道利率信息
 * @author HuangJian
 * @version 1.0 2019年3月12日
 * @see ChannelRateInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ChannelRateInfoService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ChannelBaseInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
            TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        ChannelBaseInfoCommonResult result = new ChannelBaseInfoCommonResult();
        List<ValChannelRateAdjust> listValChannelRateAdjust= new ArrayList<ValChannelRateAdjust>();

        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValChannelRateAdjust = returnResult.getT(ValChannelRateAdjust.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValChannelRateAdjust(listValChannelRateAdjust,acctEntity.getResovleDate());

        // 判断返回结果不为空
        if (map != null) {
            //现金流信息
            List<FinCashCalcRule> valFinCashCalcRuleList = (List<FinCashCalcRule>)map.get("FinCashCalcRule");
            result.setListValCashCalcRule(valFinCashCalcRuleList);
            //通道代码
            List<String> list = (List<String>)map.get("ChannelId");
            result.setTdlist(list);
        }
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;

    }
    
    private Map<String,Object> handleValChannelRateAdjust(List<ValChannelRateAdjust> dto,Date sysDate){
        Map<String, Object> resultMap = new HashMap<>();
        //现金流信息
        List<FinCashCalcRule> valFinCashCalcRuleList = new ArrayList<FinCashCalcRule>();
        List<String> tdlist = new ArrayList<>();
        Map<String,String> tdMap = new HashMap<String,String>();
        for(ValChannelRateAdjust item :dto){
        	item.setCreateTime(sysDate);
            FinCashCalcRule FinCashCalcRule = getFinCashCalcRule(item);
            valFinCashCalcRuleList.add(FinCashCalcRule);
            tdMap.put(item.getChannelId(), item.getChannelId());
        }
        for(String chlId :tdMap.keySet()){
        	tdlist.add(chlId);
        }
        resultMap.put("ChannelId", tdlist);
        resultMap.put("FinCashCalcRule", valFinCashCalcRuleList);
        return resultMap;
    }
    
    /**
     * 
     * 现金流计算规则表
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getFinCashCalcRule(ValChannelRateAdjust dto){
        FinCashCalcRule result= new FinCashCalcRule();
        //现金流代码 = InterfHelper.getMd5CashId_(B.通道代码,B.费用ID)
        result.setCashId(InterfHelper.getMd5CashId_(dto.getChannelId(),dto.getFeeId()));
        //生效日 = c.生效日
        result.setEffDate(dto.getEffDate());
        /**add by wujie on 20190327 start**/
        result.setBasis(dto.getChargeBase());
        /**add by wujie on 20190327 end**/
        //生效利率 =C.费率
        result.setYield(dto.getFeeRate());

        /**
         * modify by wujie on 20190617 start
         //基数类型 =数据字典：基数类型.实收资本
         result.setBaseType(DictCodeList.计费基数类型_实收资本.getValue());
         **/
        result.setBaseType(DictCodeList.基数类型_资产净值.getValue());
        /**modify by wujie on 20190617 end**/

        //基数日期类型 =数据字典：计费基数日期类型.前一日
        result.setBaseDateType(DictCodeList.基数日期类型_前一自然日.getValue());
        //金融产品代码 =C.通道代码
        result.setFinprodId(dto.getChannelId());
        //金融产品类型 =数据字典：金融产品类型.通道
        result.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        //分支序号=设置全局变量(默认为0）
        result.setBranch(InterfCommonConstant.分支序号_默认);
        result.setCreateTime(dto.getCreateTime());
        return result;
        
    }
    
}
