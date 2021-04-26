/*
 * 文件名：FundBaseInfoNFundRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.fund;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ValInterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FundBaseInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundNBaseInfoN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 净值型基金基础信息 基金类资产定义净值型基金基础信息 = "VAL_FUND_N_BASE_INFO_N
 * 
 * @author HuangJian
 * @version 1.0 2018年6月26日
 * @see FundBaseInfoNFundRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FundBaseInfoNFundRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * FundBaseInfoForInterfRecCommonResult
     * 
     * @param templateSourceFlgDTO TemplateSourceFlgDTO
     * @param acctEntity TemplateOrderInfoReaderResult
     * @return FundBaseInfoForInterfRecCommonResult
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public FundBaseInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                        TemplateOrderInfoReaderResult acctEntity) {
        FundBaseInfoForInterfRecCommonResult result = new FundBaseInfoForInterfRecCommonResult();
        List<ValFundNBaseInfoN> listValFundNBaseInfoN = new ArrayList<ValFundNBaseInfoN>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValFundNBaseInfoN = returnResult.getT(ValFundNBaseInfoN.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValFundNBaseInfoN(listValFundNBaseInfoN);
        if (!map.isEmpty()) {
            result.setListValFinProduct((List<FinProduct>)map.get("valFinProduct"));
            result.setListValFinProductExt((List<FinProductAdd>)map.get("valFinProductExt"));
            result.setListValFinProductType((List<FinProductType>)map.get("valFinProductType"));
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构 <功能详细描述>
     * 
     * @param listValFundNBaseInfoN List<ValFundNBaseInfoN>
     * @return Map<String,Object>
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValFundNBaseInfoN(List<ValFundNBaseInfoN> listValFundNBaseInfoN) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();
        List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
        List<FinProductAdd> listValFinProductExt = new ArrayList<FinProductAdd>();
        for (ValFundNBaseInfoN item : listValFundNBaseInfoN) {
            FinProduct valFinProduct = this.getValFinProduct(item);
            listValFinProduct.add(valFinProduct);
            FinProductType valFinProductType = this.getValFinProductType(item);
            listValFinProductType.add(valFinProductType);
            FinProductAdd valFinProductExt = this.getValFinProductExt(item);
            listValFinProductExt.add(valFinProductExt);
        }
        resultMap.put("valFinProduct", listValFinProduct);
        resultMap.put("valFinProductType", listValFinProductType);
        resultMap.put("valFinProductExt", listValFinProductExt);
        return resultMap;
    }

    /**
     * 接收 标的类金融产品 T1
     * 
     * @param dto ValFundNBaseInfoN
     * @return ValFinProduct
     * @see
     * @since 1.0
     */
    private FinProduct getValFinProduct(ValFundNBaseInfoN dto) {
        FinProduct result = new FinProduct();
        // 金融产品代码 = D.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 = F06：基金
        result.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        // 金融产品简称 = D.资产名称
        result.setFinprodAbbr(dto.getAssetName());
        // 金融产品全称 = D.资产名称
        result.setFinprodName(dto.getAssetName());
        // 收益类型 = 固定值 01：净值
        result.setProfitType(DictCodeList.收益类型_净值型.getValue());
        // 市场代码 = D.市场代码
        result.setFinprodMarketId(dto.getMarketCode());
        // 币种 = D.币种
        result.setCcy(dto.getCcy());
        // 境内外 = D.境内外
        result.setBlnArea(dto.getBlnArea());
        // 交易场所 = D.交易场所
        result.setTradeMarket(dto.getTradeMarket());
        // 交易日历= 
        result.setCalendarId(interfCommonService.getCalendarId(dto.getTradeMarket()));
        // 发行日 = D.发行日
        result.setIdate(dto.getIdate());
        // 起息日 = D.发行日
        result.setVdate(dto.getIdate());
        // 原金融产品代码 = D.资产代码
        result.setoFinprodId(dto.getAssetId());
        // 原金融产品类型 = F06：基金
//        result.setoFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        // 本金收益特征 = A.本金收益特征
        result.setCapiIncomeFeature(dto.getCapiProfFeature());
        /** modify by yanya on 20210222 增加发行人字段入库 start**/
        result.setIssuer(dto.getManagerId());
        /** modify by yanya on 20210222 增加发行人字段入库 end**/
        result.setManager(dto.getManagerId());
        return result;
    }

    /**
     * 接收 标的类金融产品扩展表 T2
     * 
     * @param dto ValFundNBaseInfoN
     * @return ValFinProductExt
     * @see
     * @since 1.0
     */
    private FinProductAdd getValFinProductExt(ValFundNBaseInfoN dto) {
    	FinProductAdd result = new FinProductAdd();
        // 金融产品代码 = D.资产代码
        result.setFinprodId(dto.getAssetId());
        // 申购确认速度 = D.申购确认速度
        result.setPurSpeed(dto.getPurSpeed());
        // 赎回确认速度 = D.赎回确认速度
        result.setRedSpeed(dto.getRedSpeed());
        //面值  = A.面值
        result.setFaceValue(dto.getFaceValue());
        //add by yanya on 20210318 数据来源 = 常量.估值数据来源
        result.setDataSource(ValInterfCommonConstant.getValDataSource());
        result.setTrmFundType(dto.getTrmFundType());
        return result;
    }

    /**
     * 接收 金融产品分类表 T3
     * 
     * @param dto ValFundNBaseInfoN
     * @return ValFinProductType
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValFundNBaseInfoN dto) {
        FinProductType result = new FinProductType();
        // 金融产品代码 = D.资产代码
        result.setFinprodId(dto.getAssetId());
        // 分类1 = D.基金类型
        result.setType1(dto.getFundType());
        // 分类2 = 调用共通方法查询核算分类
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        //资产类型
        fdto.setAssetType(dto.getFundType());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());
        // 场内基金类型
        fdto.setTrmFundType(dto.getTrmFundType());
        //交易场所
        fdto.setTradeMarket(dto.getTradeMarket());
        result.setType2(interfCommonService.getFinanceProductClass(fdto));
        // 分类3 = 调用共通方法查询税务分类
        result.setType3(interfCommonService.getFinanceProductTaxClass(fdto));
        result.setBranch(InterfCommonConstant.分支序号_默认);
        result.setType6(dto.getFundType());
        return result;
    }
}
