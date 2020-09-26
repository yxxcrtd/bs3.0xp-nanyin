/*
 * 文件名：ValFinProductAcctInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.base.entity.MstTradeCalendar;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.OtherBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValHolidayInfo;
@Service
public class ValHolidayInfoInterfService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 读取模板处理
     * 
     * @param templateSourceFlgDTO
     * @param acctEntity
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public OtherBaseInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                         TemplateOrderInfoReaderResult acctEntity) {
        //定义返回变量
        OtherBaseInfoCommonResult result = new OtherBaseInfoCommonResult();
        List<ValHolidayInfo> listValHolidayInfo = new ArrayList<ValHolidayInfo>(); 
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
        
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValHolidayInfo = returnResult.getT(ValHolidayInfo.class);
        
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleListValHolidayInfo(listValHolidayInfo);
        // 判断返回结果不为空
        if (map != null) {
            List<MstCalendarInfo> valCalendarInfoList = (List<MstCalendarInfo>)map.get("ValCalendarInfo");
            List<MstTradeCalendar> valTradeCalendarList = (List<MstTradeCalendar>)map.get("ValTradeCalendar");
            List<MstHoliday> valHolidayList = (List<MstHoliday>)map.get("ValHoliday");
            result.setValCalendarInfoList(valCalendarInfoList);
            result.setValTradeCalendarList(valTradeCalendarList);
            result.setValHolidayList(valHolidayList);
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    /**
     * 
     * @param handleListValHolidayInfo 节假日信息表
     * @return 日历信息表 +交易场所日历表+日历节假日表
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValHolidayInfo(List<ValHolidayInfo> listValHolidayInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        List<MstCalendarInfo> valCalendarInfoList = new ArrayList<MstCalendarInfo>();
        List<MstTradeCalendar> valTradeCalendarList = new ArrayList<MstTradeCalendar>();
        List<MstHoliday> valHolidayList = new ArrayList<MstHoliday>();
        Map<String,String> paramMap = new HashMap<String,String>();
        // 获取基础信息
        for (ValHolidayInfo dto : listValHolidayInfo) {
        	paramMap.put(dto.getTradeMarket(), dto.getTradeMarket());
        	
        	// 日历节假日表 
        	MstHoliday valHoliday = getValHoliday(dto);
        	valHolidayList.add(valHoliday);
           
        }
        List<String> tradeMarketList = new ArrayList<String>(paramMap.values());
        for(String tradeMarket :tradeMarketList){
        	//日历信息表
        	MstCalendarInfo valCalendarInfo = getValCalendarInfo(tradeMarket);
            valCalendarInfoList.add(valCalendarInfo);
            
            // 交易场所日历 处理
            MstTradeCalendar valTradeCalendar = getValTradeCalendar(tradeMarket);
            valTradeCalendarList.add(valTradeCalendar);
        }
        
        resultMap.put("ValCalendarInfo", valCalendarInfoList);
        resultMap.put("ValTradeCalendar", valTradeCalendarList);
        resultMap.put("ValHoliday", valHolidayList);
        return resultMap;
    }
    
    /**
     * 日历信息表 接收
     * 
     * @param dto 节假日信息 
     * @return 日历信息 
     * @see
     * @since 1.0
     */
    private MstCalendarInfo getValCalendarInfo(String tradeMarket) {

        MstCalendarInfo valCalendarInfo = new MstCalendarInfo();
        //日历编码 = 交易市场
        valCalendarInfo.setCalendarId(tradeMarket);
        String calendarName = InterfHelper.getDictColdeList_ItemName(DictCode.节假日_交易市场.getValue(), tradeMarket);
        // 日历名称 = A.交易场所
        valCalendarInfo.setCalendarName(calendarName==null?tradeMarket:calendarName);
        // 第三方日历编号= A.交易场所
        valCalendarInfo.setOtherId(tradeMarket);
        //是否交易日历:默认N
        valCalendarInfo.setIsTradeCalendar(DictCodeList.是否_否.getValue());
        return valCalendarInfo;
    }
    
    /**
     * 交易场所日历表 接收
     * 
     * @param dto 节假日信息 
     * @return 交易场所日历 
     * @see
     * @since 1.0
     */
    private MstTradeCalendar getValTradeCalendar(String tradeMarket) {

        MstTradeCalendar valTradeCalendar = new MstTradeCalendar();
        // 交易场所= A.交易场所
        valTradeCalendar.setTradeCalendarId(tradeMarket);
        // 日历编码 =  A.交易场所
        valTradeCalendar.setCalendarId(tradeMarket);
        return valTradeCalendar;
    }
    
    /**
     * 日历节假日表 接收
     * 
     * @param dto 节假日信息 
     * @return 日历节假日 
     * @see
     * @since 1.0
     */
    private MstHoliday getValHoliday(ValHolidayInfo dto) {
        MstHoliday valHoliday = new MstHoliday();
        // 日历编码 = A.交易场所
        valHoliday.setCalendarId(dto.getTradeMarket());
        // 日期 = A.日期
        valHoliday.setCdate(dto.getCdate());
        // 是否节假日= A.是否节假日
        valHoliday.setIsHoliday(dto.getIsHoliday().longValue());
        return valHoliday;
    }
}
