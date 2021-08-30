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

import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValStockQuotationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行情信息-股票行情
 *
 * @author yanya
 * @version 1.0 2021-08-31
 * @see ValStockQuotationRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValStockQuotationRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                  TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();
        List<ValStockQuotationInfo> hqStockInfo = new ArrayList<ValStockQuotationInfo>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 此部分处理待以后从画面入口的时候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。
        // 1、文件读取解析 进行数据有效性的check,并生出进入日志表的数据结构
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        hqStockInfo = returnResult.getT(ValStockQuotationInfo.class);
        // 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        List<MstSharePrice> valShareInfo = (List<MstSharePrice>) handleListToVaLInfo(hqStockInfo).get("valShareInfo");
        result.setValShareInfo(valShareInfo);
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 转换为股票行情表数据
     * @param infoList
     * @return
     */
    private Map<String, Object> handleListToVaLInfo(List<ValStockQuotationInfo> infoList) {
        Map<String, Object> resultMap = new HashMap<>();
        List<MstSharePrice> valShareInfoList = new ArrayList<MstSharePrice>();
        for (ValStockQuotationInfo dto : infoList) {
            // 债券市场行情 处理
            MstSharePrice valShareInfo = getValShareInfo(dto);
            valShareInfoList.add(valShareInfo);
        }
        resultMap.put("valShareInfo", valShareInfoList);
        return resultMap;
    }

    /**
     * 转换为股票行情表数据
     * @param dto
     * @return
     */
    private MstSharePrice getValShareInfo(ValStockQuotationInfo dto) {
        MstSharePrice valShareInfo = new MstSharePrice();
        /**股票代码:市场代码**/
        valShareInfo.setSharesId(dto.getSharesId());
        /**交易日**/
        valShareInfo.setDealDate(dto.getDealDate());
        /**前收盘价**/
        valShareInfo.setPreClosePrice(dto.getPreClosePrice());
        /**开盘价**/
        valShareInfo.setOpenPrice(dto.getOpenPrice());
        /**最高价**/
        valShareInfo.setHighPrice(dto.getHighPrice());
        /**最低价**/
        valShareInfo.setLowPrice(dto.getLowPrice());
        /**收盘价**/
        valShareInfo.setClosePrice(dto.getClosePrice());
        /**交易状态**/
        valShareInfo.setTradeStatus(dto.getTradeStatus());
        //valShareInfo.setInputType(dto.getSharesId());
        return valShareInfo;
    }

}
