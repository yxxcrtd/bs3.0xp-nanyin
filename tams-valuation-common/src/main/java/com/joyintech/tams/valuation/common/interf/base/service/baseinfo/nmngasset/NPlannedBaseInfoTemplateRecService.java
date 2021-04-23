/*
 * 文件名：PlannedAssetBaseInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nmngasset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.NPlannedAssetBaseInfoCommonRecResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngAssetInfoN;

/**
 * 计划类资产（净值型）基础信息模板处理
 * 〈功能详细描述〉
 * @author chufengjie
 * @version 1.0 2018年8月03日
 * @see NPlannedBaseInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPlannedBaseInfoTemplateRecService {
    
    /**
     * 基本信息模板处理service
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @Autowired
    private InterfCommonService interfCommonService;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public NPlannedAssetBaseInfoCommonRecResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                           TemplateOrderInfoReaderResult acctEntity) {
        //定义返回变量
        NPlannedAssetBaseInfoCommonRecResult result = new NPlannedAssetBaseInfoCommonRecResult();
        List<ValMngAssetInfoN> listValMngAssetInfoN = new ArrayList<ValMngAssetInfoN>();
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();    
        
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
                
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValMngAssetInfoN = returnResult.getT(ValMngAssetInfoN.class);
        
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> resultMap = handleListValMngAssetInfo(listValMngAssetInfoN);
        // 判断返回结果不为空
        if (resultMap != null) {
            List<FinProduct> listValFinProduct = (List<FinProduct>)resultMap.get("ValFinProduct");
            List<FinProductType> ValFinProductType = (List<FinProductType>)resultMap.get("ValFinProductType"); 
            result.setListValFinProduct(listValFinProduct);
            result.setListValFinProductType(ValFinProductType);             
            }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    /**
     * 基础信息
     * 
     * @param handleListValMngAssetInfo 计划类资产（净值型）基础信息表
     * @return 标的类金融产品 +金融产品分类 计算规则
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValMngAssetInfo(List<ValMngAssetInfoN> listValMngAssetInfoN) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinProduct> valFinProductList = new ArrayList<FinProduct>();
        List<FinProductType> valFinProductTypeList = new ArrayList<FinProductType>();
        
        // 获取基础信息
        for (ValMngAssetInfoN dto : listValMngAssetInfoN) {
            // 标的类金融产品 处理
            FinProduct valFinProduct = getValFinProduct(dto);
            valFinProductList.add(valFinProduct);
            // 金融产品分类 处理   
            FinProductType valFinProductType = getValFinProductType(dto);
            valFinProductTypeList.add(valFinProductType);
            
        }
        resultMap.put("ValFinProduct", valFinProductList);
        resultMap.put("ValFinProductType", valFinProductTypeList);

        return resultMap;
    }
    
    /**
     * 标的类金融产品表 接收
     * 
     * @param dto 01-基础信息
     * @return 标的类金融产品
     * @see
     * @since 1.0
     */
    private FinProduct getValFinProduct(ValMngAssetInfoN dto) {

        FinProduct valFinProduct = new FinProduct();
        // 金融产品代码 = A.资产代码
        valFinProduct.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 固定值(数据字典：金融产品类型.计划类资产)
        valFinProduct.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 金融产品简称= A.资产名称
        valFinProduct.setFinprodAbbr(dto.getAssetName());
        // 金融产品全称= A.资产名称
        valFinProduct.setFinprodName(dto.getAssetName());
        // 收益类型= 固定值 （ 数据字典：收益类型.净值）
        valFinProduct.setProfitType(DictCodeList.收益类型_净值型.getValue());
        // 市场代码= A.资产代码
        valFinProduct.setFinprodMarketId(dto.getAssetId());
        // 币种= A.币种
        valFinProduct.setCcy(dto.getCcy());
        // 境内外= A.境内外
        valFinProduct.setBlnArea(dto.getBlnArea());
        //交易日历 = 调用共通设计->【详细设计-接口共通方法设计.xlsx】->交易日历映射关系SHEET
        valFinProduct.setCalendarId(interfCommonService.getCalendarId(dto.getTradeMarket()));
        // 交易场所= A.交易场所
        valFinProduct.setTradeMarket(dto.getTradeMarket());
        // 发行方式 = A.发行方式 模板上提示字段不用
        valFinProduct.setIdate(null);
        /** modify by yanya on 20210222 增加发行人字段入库 start**/
        //发行人
        //valFinProduct.setIssuer(null);
        valFinProduct.setIssuer(dto.getManagerId());
        /** modify by yanya on 20210222 增加发行人字段入库 end**/
        // 发行日= A.起息日
        valFinProduct.setIdate(dto.getVdate());
        // 起息日= A.起息日
        valFinProduct.setVdate(dto.getVdate());
        // 到期日= A.到期日
        valFinProduct.setMdate(dto.getMdate());
        // 实际到期日= A.到期日
        valFinProduct.setActmdate(dto.getMdate());     
        // 母金融产品代码 = A.资产代码
        valFinProduct.setpFinprodId(dto.getAssetId());
        // 原金融产品代码 = A.资产代码
        valFinProduct.setoFinprodId(dto.getAssetId());
        // 原金融产品类型= F17:债权类资产
//        valFinProduct.setoFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        //本金收益特征 = A.本金收益特征
        valFinProduct.setCapiIncomeFeature(dto.getCapiProfFeature());
        // 所属机构 = A.所属机构   add by tanxuan on 20210310 多级法人权限控制改造
		valFinProduct.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        return valFinProduct;
    }
    /**
     * 金融产品分类表 接收
     * 
     * @param dto 01-金融产品分类
     * @return 金融产品分类
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValMngAssetInfoN dto) {

        FinProductType valFinProductType = new FinProductType();
        //金融产品代码= A.资产代码
        valFinProductType.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 固定值(数据字典：金融产品类型.计划类资产)
//        valFinProductType.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        /**add by wujie on 20180822 start**/
        //分类1 = A.资产分类
        valFinProductType.setType1(dto.getAssetClass());
        
        // 分类2 = 调用共通方法查询核算分类
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());
        /**add by wujie on 20181108 start remark:增加资产类型查询条件查询核算分类、税务分类的查询**/
        //资产分类
        fdto.setAssetType(dto.getAssetClass());
        /**add by wujie on 20181108 end**/

        /*add by sunshuaishuai 计划类资产添加收益类型区分 20210315 start*/
        fdto.setProfitType(DictCodeList.收益类型_净值型.getValue());
        /*add by sunshuaishuai 计划类资产添加收益类型区分 20210315 end*/

        valFinProductType.setType2(interfCommonService.getFinanceProductClass(fdto));
        
        // 分类3 = 调用共通方法查询税务分类
        valFinProductType.setType3(interfCommonService.getFinanceProductTaxClass(fdto));
        /**add by wujie on 20180822 end**/
        
        valFinProductType.setBranch(InterfCommonConstant.分支序号_默认);
        return valFinProductType;
        
    }
}
