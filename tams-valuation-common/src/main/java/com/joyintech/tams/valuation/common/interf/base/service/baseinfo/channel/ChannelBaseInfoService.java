/*
 * 文件名：ChannelBaseInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.channel;

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
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinProductValuationPara;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelBaseInfo;


/**
 * 通道基础信息
 * @author HuangJian
 * @version 1.0 2019年3月12日
 * @see ChannelBaseInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ChannelBaseInfoService {

    
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
//    @Autowired
//    private InterfCommonService interfCommonService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ChannelBaseInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
            TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        ChannelBaseInfoCommonResult result = new ChannelBaseInfoCommonResult();
        List<ValChannelBaseInfo> listValChannelBaseInfo= new ArrayList<ValChannelBaseInfo>();

        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValChannelBaseInfo = returnResult.getT(ValChannelBaseInfo.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValChannelBaseInfo(listValChannelBaseInfo );

        // 判断返回结果不为空
        if (map != null) {
          //标的类金融产品表
            List<FinProduct> listValFinProduct = (List<FinProduct>) map.get("ValFinProduct");
            //标的类金融产品扩展表
            List<FinProductAdd> listValFinProductExt = (List<FinProductAdd>) map.get("ValFinProductExt");
            //金融产品分类表
            List<FinProductType> listValFinProductType = (List<FinProductType>) map.get("ValFinProductType");
            //金融产品估值参数表
            List<FinProductValuationPara> listValFinProductValuationPara = (List<FinProductValuationPara>) map.get("ValFinProductValuationPara");
            
            result.setListValFinProduct(listValFinProduct);
            result.setListValFinProductExt(listValFinProductExt);
            result.setListValFinProductType(listValFinProductType);
            result.setListValFinProductValuationPara(listValFinProductValuationPara);
        }
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;

    }
    
    
    private Map<String,Object> handleValChannelBaseInfo(List<ValChannelBaseInfo> dto){
        Map<String, Object> resultMap = new HashMap<>();
        //标的类金融产品
        List<FinProduct> valFinProductList = new ArrayList<FinProduct>();
        //标的类金融产品扩展表
        List<FinProductAdd> valFinProductExtList = new ArrayList<FinProductAdd>();
        //金融产品分类表
        List<FinProductType> valFinProductTypeList = new ArrayList<FinProductType>();
        //金融产品估值参数表
        List<FinProductValuationPara> valFinProductValuationParaList = new ArrayList<FinProductValuationPara>();
        for(ValChannelBaseInfo item :dto){
         // 标的类金融产品 处理
            FinProduct valFinProduct = getValFinProduct(item);
            valFinProductList.add(valFinProduct);
            
            // 标的类金融产品扩展信息 待处理
             FinProductAdd valFinProductExt = getValFinProductExt(item);
             valFinProductExtList.add(valFinProductExt);
             
            // 金融产品分类表
             FinProductType valFinProductType = getValFinProductType(item);
             valFinProductTypeList.add(valFinProductType);
            // 金融产品估值参数表
             FinProductValuationPara valFinProductValuationPara = getValFinProductValuationPara(item);
             valFinProductValuationParaList.add(valFinProductValuationPara);
        }
        resultMap.put("ValFinProduct", valFinProductList);
        resultMap.put("ValFinProductExt", valFinProductExtList);
        resultMap.put("ValFinProductType", valFinProductTypeList);
        resultMap.put("ValFinProductValuationPara", valFinProductValuationParaList);
       
        return resultMap;
    }
    
    

    /**
     * 标的类金融产品表 接收 
     * @param dto
     * @return 标的类金融产品
     */
    private FinProduct getValFinProduct(ValChannelBaseInfo dto) {
        FinProduct valFinProduct = new FinProduct();
        // 金融产品代码 = A.通道代码
        valFinProduct.setFinprodId(dto.getChannelId());
        // 金融产品类型 = 数据字典：金融产品类型.通道
        valFinProduct.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        // 金融产品简称= A.通道名称
        valFinProduct.setFinprodAbbr(dto.getChannelName());
        // 金融产品全称= A.通道名称
        valFinProduct.setFinprodName(dto.getChannelName());
        // 收益类型= 收益类型.货币
        valFinProduct.setProfitType(DictCodeList.收益类型_货币型.getValue());
        // 通道代码= A.通道代码
        valFinProduct.setChlFinprodId(dto.getChannelId());
        // 币种= A.币种
        valFinProduct.setCcy(dto.getCcy());
        //发行日=A.成立日期
        valFinProduct.setIdate(dto.getVdate());
        //起息日 = A.成立日期
        valFinProduct.setVdate(dto.getVdate());
        //到期日 = A.终止日期
        valFinProduct.setMdate(dto.getMdate());
        //实际到期日 =A.终止日期
        valFinProduct.setActmdate(dto.getMdate());
        //是否通道 = 数据字典：是否.是
        valFinProduct.setIsChl(DictCodeList.是否_是.getValue());
        // 原金融产品代码 = A.通道代码
        valFinProduct.setoFinprodId(dto.getChannelId());
//        // 原金融产品类型 = 数据字典：金融产品类型.通道
//        valFinProduct.setoFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        //add by liupengfei for 多级法人改造 S
        valFinProduct.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        //add by liupengfei for 多级法人改造 E
        return valFinProduct;
    }

    /**
     * 标的类金融产品扩展表 接收
     * @param dto
     * @return 标的类金融产品扩展表
     */
    private FinProductAdd getValFinProductExt(ValChannelBaseInfo dto) {
        // 标的类金融产品扩展表
    	FinProductAdd valFinProductExt = new FinProductAdd();       
        //金融产品代码 = A.通道代码
        valFinProductExt.setFinprodId(dto.getChannelId());
//        // 金融产品类型 = 数据字典：金融产品类型.通道
//        valFinProductExt.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
//        //基金结转频率= 数据字典：基金分红频率.日
//        valFinProductExt.setCarryoverFreq(DictCodeList.基金分红频率_日.getValue());
        //申购确认速度= A.申购确认速度
        valFinProductExt.setPurSpeed(0l);
        //赎回确认速度= A.赎回确认速度
        valFinProductExt.setRedSpeed(0l);
        //托管活期利息计提= A.托管活期利息计提
//        TODO
//        valFinProductExt.setIsCalc(dto.getIsCalc());
        //收益标识，非保本浮动收益型、保本浮动、保证收益型 = A.本金收益特征
        valFinProductExt.setProfitFlag(dto.getCapiProfFeature());
//      TODO
//        valFinProductExt.setIsReconciliation(DictCodeList.是否_否.getValue());
        return valFinProductExt;
    }
    
    /**
     * 金融产品分类表 接收
     * @param dto
     * @return 金融产品分类表
     */
    private FinProductType getValFinProductType(ValChannelBaseInfo dto) {
        // 金融产品分类表
        FinProductType valFinProductType = new FinProductType();    
        // 金融产品代码=A.通道代码
        valFinProductType.setFinprodId(dto.getChannelId());
//        // 金融产品类型= 数据字典：金融产品类型.通道
//        valFinProductType.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        // 分类1= A.通道类型
        valFinProductType.setType1(dto.getChannelType());
        valFinProductType.setBranch(InterfCommonConstant.分支序号_默认);
       
        return valFinProductType;
    }
    
    
    /**
     * 金融产品估值参数表 接收
     * @param dto
     * @return 金融产品估值参数表
     */
    private FinProductValuationPara getValFinProductValuationPara(ValChannelBaseInfo dto) {
        // 金融产品估值参数表
        FinProductValuationPara valFinProductValuationPara = new FinProductValuationPara();     
        // 金融产品代码= A.通道代码
        valFinProductValuationPara.setFinprodId(dto.getChannelId());
        // 分支序号= 固定值 设置全局变量(默认为0）
        valFinProductValuationPara.setBranch(InterfCommonConstant.分支序号_默认);
        
        //估值表模板 = A.估值表模板
        valFinProductValuationPara.setValuationTemplate(dto.getValuationTemplate());
        //税务方案代码 = A.税务方案
        valFinProductValuationPara.setTaxScheId(dto.getIsCompoundInt());
        return valFinProductValuationPara;
    }
    
}
