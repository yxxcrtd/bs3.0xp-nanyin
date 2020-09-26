/*
 * 文件名：NPrdBaseinfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstFundMarketInfo;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqFundMarketInfo;

/**
 * 行情信息-基金市场行情
 * 
 * @author wangwei1
 * @version 1.0 2018年6月28日
 * @see HqFundMktInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class HqFundMktInfoTemplateRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                           TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();
        
        List<HqFundMarketInfo> hqFundMarketInfo = new ArrayList<HqFundMarketInfo>();

        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        
        //文件路径设置  TODO 此部分处理待以后从画面入口的时        候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。
        // 1、文件读取解析 进行数据有效性的check,并出进入日志表的数据结构
        // XML读取追加过滤器
        if (DictCodeInterfList.接口文件类型_XML.getValue().equals(acctEntity.getTmpType())) {
            templateAnalysisForXMLService.setRootElement("Security");
            Map<String, List<String>> filter = new HashMap<String, List<String>>();
            String[] filters = {"14","15","16","17","18","19","20","23","24","25","26"};
            filter.put("SecurityType", Arrays.asList(filters));
            templateAnalysisForXMLService.setFilter(filter);
        }
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
        hqFundMarketInfo = returnResult.getT(HqFundMarketInfo.class);

        // 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleListToVaLInfo(hqFundMarketInfo, acctEntity.getResovleDate()); 
        
        result.setListValFundMarketInfo((List<MstFundMarketInfo>)map.get("valFundMarketInfo"));
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param cashsecurityclosemdList
     * @return
     * @see
     * @since 1.0
     */
    private Map<String, Object>  handleListToVaLInfo(List<HqFundMarketInfo> infoList, Date date) {
        Map<String, Object> resultMap = new HashMap<>();
        List<MstFundMarketInfo> valFundMarketInfoList  = new ArrayList<MstFundMarketInfo>();
        for (HqFundMarketInfo dto : infoList) {
            // 基金市场行情 处理
            MstFundMarketInfo valFundMarketInfo = getValFundMarketInfo(dto, date);
            valFundMarketInfoList.add(valFundMarketInfo);
        }
        resultMap.put("valFundMarketInfo", valFundMarketInfoList);
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
    private MstFundMarketInfo getValFundMarketInfo(HqFundMarketInfo dto, Date date) {
        MstFundMarketInfo valFundMarketInfo=new MstFundMarketInfo();
        //债券代码    SEC_ID              A.证券代码  需根据A.证券代码确认是债券还是基金，规则由需求提供 
        valFundMarketInfo.setFundCode(dto.getFundCode());
        // 估值来源    VALUE_SOURCE                默认值：03  数据字典：交易流通场所-上海交易所
//        if (dto.getValueSource() != null) {
//            valFundMarketInfo.setValueSource(dto.getValueSource());
//        } else {
//            valFundMarketInfo.setValueSource(DictCodeList.交易流通场所_深圳证券交易所.getValue());
//        }
        // 估值日 VALUE_DATE              默认值：参数给定    
        if (dto.getValueDate() != null) {
            valFundMarketInfo.setValueDate(dto.getValueDate());
        } else {
            valFundMarketInfo.setValueDate(date);
        }
        //收盘价 CLOSE_PRICE             A.今日收盘价 
        valFundMarketInfo.setClosePrice(dto.getClosePrice());
        // 平均价 AVERAGE_PRICE
//        valFundMarketInfo.setAveragePrice(null);        
        // 最高价 MAX_PRICE A.最高价
        valFundMarketInfo.setMaxPrice(dto.getMaxPrice());
        // 最低价 MIN_PRICE A.最低价
        valFundMarketInfo.setMinPrice(dto.getMinPrice());
        // 买一价 BUY_ONE_PRICE A.申买价一
        valFundMarketInfo.setBuyOnePrice(dto.getBuyOnePrice());
        // 卖一价 SELL_ONE_PRICE A.申卖价一
        valFundMarketInfo.setSellOnePrice(dto.getSellOnePrice());
        
        return valFundMarketInfo;
    }

   
}
