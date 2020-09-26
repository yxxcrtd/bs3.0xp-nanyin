/*
 * 文件名：StandardMarketQuotationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqBondMarketInfo;

/**
 * 标准债券市场行情
 * 
 * @author chendijun
 * @version 1.0 2018年11月22日
 * @see StandardBondMarketInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class StandardBondMarketInfoService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                  TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回结果
        HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();
        

        List<HqBondMarketInfo> valHqBondMarketInfo = new ArrayList<HqBondMarketInfo>();
        //1.解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        if (DictCodeInterfList.接口文件类型_STTXT.getValue().equals(acctEntity.getTmpType())) {
            returnResult = templateAnalysisForXMLService.execute(acctEntity);
        }
        //2.文件解析
        valHqBondMarketInfo = returnResult.getT(HqBondMarketInfo.class);
        //3.更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String,Object> resultMap = this.handleValHqBondMarketInfo(valHqBondMarketInfo, acctEntity.getResovleDate());
        
        result.setListValBondMarketInfo((List<MstBondMarketInfo>)resultMap.get("valMstBondMarketInfo"));
        
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    private Map<String,Object> handleValHqBondMarketInfo(List<HqBondMarketInfo> valHqBondMarketInfo,Date date) {
        //定义返回结果
        Map<String,Object> resultMap = new HashMap<>();
        if(valHqBondMarketInfo == null) {
            valHqBondMarketInfo = new ArrayList<HqBondMarketInfo>();
        }
        
        List<MstBondMarketInfo> valMstBondMarketInfo = new ArrayList<MstBondMarketInfo>();
        for(HqBondMarketInfo hqBondMarketInfo:valHqBondMarketInfo) {
            MstBondMarketInfo bondMarketInfo = this.getMstBondMarketInfo(hqBondMarketInfo);
            valMstBondMarketInfo.add(bondMarketInfo);
        }
        resultMap.put("valMstBondMarketInfo", valMstBondMarketInfo);
        return resultMap;
    }
    
    private MstBondMarketInfo getMstBondMarketInfo(HqBondMarketInfo hqBondMarketInfo) {
        MstBondMarketInfo bondMarketInfo = new MstBondMarketInfo();
        //债券代码 = A.证券代码
        bondMarketInfo.setSecId(hqBondMarketInfo.getSecId());
        //估值来源 = InterfHelper.getDictColdeList_ItemName(DictCode.估值来源_资讯.getValue(),A.估值来源)
//        bondMarketInfo.setValueSource(InterfHelper.getDictColdeList_ItemName(DictCode.估值来源_资讯.getValue(), hqBondMarketInfo.getValueSource()));
        //估值日 = A.估值日
        bondMarketInfo.setValueDate(hqBondMarketInfo.getValueDate());
        //收盘价 = A.收盘价
        bondMarketInfo.setClosePrice(hqBondMarketInfo.getClosePrice());
        //平均价 = A.平均价
        bondMarketInfo.setAveragePrice(hqBondMarketInfo.getAveragePrice());
        //最高价 = A.最高价
        bondMarketInfo.setMaxPrice(hqBondMarketInfo.getMaxPrice());
        //最低价 = A.最低价
        bondMarketInfo.setMinPrice(hqBondMarketInfo.getMinPrice());
        //买一价 = A.买一价
        bondMarketInfo.setBuyOnePrice(hqBondMarketInfo.getBuyOnePrice());
        //卖一价 = A.卖一价
        bondMarketInfo.setSellOnePrice(hqBondMarketInfo.getSellOnePrice());
        
        return bondMarketInfo;
    }
}
