/*
 * 文件名：SecBaseInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.stock;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.constants.ValInterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValStockBaseInfoRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValStockBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 股票标的接口
 * 
 * @author yanya
 * @version 1.0 2021-08-27
 * @see ValStockBaseRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValStockBaseRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    @Autowired
    private InterfCommonService interfCommonService;
    

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ValStockBaseInfoRecProcessorRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                               TemplateOrderInfoReaderResult acctEntity) {
        ValStockBaseInfoRecProcessorRecCommonResult result = new ValStockBaseInfoRecProcessorRecCommonResult();
        List<ValStockBaseInfo> listValStockBaseInfo = new ArrayList<ValStockBaseInfo>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValStockBaseInfo = returnResult.getT(ValStockBaseInfo.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValStockBaseInfo(listValStockBaseInfo);
        if (!map.isEmpty()) {
            result.setListValFinProduct((List<FinProduct>) map.get("listValFinProduct"));
            result.setListValFinProductType((List<FinProductType>) map.get("listValFinProductType"));
            result.setListValFinProductExt((List<FinProductAdd>) map.get("listValFinProductAdd"));
        }
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValStockBaseInfo List<ValStockBaseInfo>
     * @return Map<String,Object>
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValStockBaseInfo(List<ValStockBaseInfo> listValStockBaseInfo) {
        Map<String, Object> mapResult = new HashMap<>();
        List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();
        List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
        List<FinProductAdd> listValFinProductAdd = new ArrayList<FinProductAdd>();
        for (ValStockBaseInfo item : listValStockBaseInfo) {
        	//接收 标的类金融产品 T1
            FinProduct ValFinProduct = this.getValFinProduct(item);
            listValFinProduct.add(ValFinProduct);
            FinProductType valFinProductType = this.getValFinProductType(item);
            listValFinProductType.add(valFinProductType);
            listValFinProductAdd.add(this.getValFinProductAdd(item));
        }
        mapResult.put("listValFinProduct", listValFinProduct);
        mapResult.put("listValFinProductType", listValFinProductType);
        mapResult.put("listValFinProductAdd", listValFinProductAdd);
        return mapResult;
    }

    /**
     * 接收 标的类金融产品 T1
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinProduct getValFinProduct(ValStockBaseInfo dto) {
        FinProduct result = new FinProduct();
        // 金融产品代码 = A.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 = F10 股票
        result.setFinprodType(DictCodeListExt1.金融产品类型_股票.getValue());
        // 金融产品简称 = A.股票名称
        result.setFinprodAbbr(dto.getStockName());
        // 金融产品全称 = A.股票名称
        result.setFinprodName(dto.getStockName());
        // 通道代码 = A.通道代码
        result.setChlFinprodId(dto.getChlId());
        // 市场代码 = A.股票代码代码
        result.setFinprodMarketId(dto.getStockId());
        // 币种 = A.币种
        result.setCcy(dto.getCcy());
        //交易日历 = 调用共通设计->【详细设计-接口共通方法设计.xlsx】->交易日历映射关系SHEET
        result.setCalendarId(interfCommonService.getCalendarId(dto.getTradePlace()));
        // 交易场所 = A.交易场所
        result.setTradeMarket(dto.getTradePlace());
        // 发行人 = A.发行人
        result.setIssuer(dto.getCustomerId());
        // 发行日 = A.起息日
        result.setIdate(dto.getVdate());
        // 起息日 = A.起息日
        result.setVdate(dto.getVdate());
        // 到期日= A.到期日
        result.setMdate(dto.getMdate());
        // 实际到期日 = A.到期日
        result.setActmdate(dto.getMdate());
        // 是否通道 = A.是否通道
        if(StringUtil.isNotEmpty(dto.getIsChl())) {
            result.setIsChl(dto.getIsChl());
        }
        else {
            result.setIsChl(DictCodeListExt2.是否_通道_否.getValue());
        }
        // 原金融产品代码 = A.资产代码
        result.setoFinprodId(dto.getAssetId());
        result.setProfitType(DictCodeList.收益类型_净值型.getValue());
        //托管登记机构 = A.托管登记机构
        result.setRegistOrg(DictCodeList.登记托管机构_中国证券登记结算有限责任公司.getValue());
        result.setBlnArea(DictCodeList.境内外_境内.getValue());

        return result;
    }

    /**
     * 构建标的类金融产品扩展表
     * @param dto
     * @return
     */
    private FinProductAdd getValFinProductAdd(ValStockBaseInfo dto) {
        FinProductAdd result = new FinProductAdd();
        // 金融产品代码 = A.资产代码
        result.setFinprodId(dto.getAssetId());
        result.setIsMarginFinprod(dto.getIsMarginFinprod());//是否融资融券标的
        result.setShOrZh(dto.getShOrZh());//港股通市场
        result.setBoard(dto.getBoard());//上市板
        //数据来源 = 常量.估值数据来源
        result.setDataSource(ValInterfCommonConstant.getValDataSource());
        result.setInvestNature(DictCodeList.投资性质_权益类.getValue());
        return result;
    }


    /**
     * 接收 金融产品分类 T2
     * 
     * @param dto ValSecBaseInfo
     * @return ValFinProductType
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValStockBaseInfo dto) {
        FinProductType result = new FinProductType();
        // 金融产品代码 = A.资产代码
        result.setFinprodId(dto.getAssetId());
        if(StringUtil.equals(dto.getTradePlace(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                || StringUtil.equals(dto.getTradePlace(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())
        ) {
            result.setType1("01");//股票
        }
        else {
            result.setType1("02");//港股
        }
        // 分类2 = 调用共通方法查询核算分类
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_股票.getValue());
        //资产类型
        fdto.setAssetType(result.getType1());
        //交易场所
        fdto.setTradeMarket(dto.getTradePlace());
        result.setType2(interfCommonService.getFinanceProductClass(fdto));
        // 分类3 = 调用共通方法查询税务分类
        result.setType3(interfCommonService.getFinanceProductTaxClass(fdto));
        // 分类3 = 调用共通方法查询证券品种
        result.setType18(interfCommonService.getFinanceProductSecType(fdto));
        result.setBranch(InterfCommonConstant.分支序号_默认);
        return result;
    }


}
