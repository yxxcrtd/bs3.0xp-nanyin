/*
 * 文件名：ValSeclendInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.seclend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinDealTarget;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.SecLendInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSeclendInfo;

/**
 * 债券借贷 -债券借贷标的信息模板处理service(VAL_SECLEND_INFO)
 * 
 * @author wangqidong
 * @version 1.0 2018年10月30日
 * @see ValSeclendInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValSeclendInfoService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 共通service
     */
    @Autowired
    private InterfCommonService interfCommonService;
    /**
     * 债券借贷 - 债券借贷标的信息接口处理
     * 
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return SecLendInfoCommonResult
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public SecLendInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                           TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        SecLendInfoCommonResult result = new SecLendInfoCommonResult();
        List<ValSeclendInfo> listValSeclendInfo = new ArrayList<ValSeclendInfo>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValSeclendInfo = returnResult.getT(ValSeclendInfo.class);

        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValSeclendInfo(listValSeclendInfo);
        // 定义 金融产品分类List
        List<FinProductType> listValFinProductType = (List<FinProductType>)map.get(
            "ValFinProductType");
        result.setListValFinProductType(listValFinProductType);
        // 定义交易类金融产品List
        List<FinTradeProduct> listValFinTradeProduct = (List<FinTradeProduct>)map.get(
            "ValFinTradeProduct");
        result.setListValFinTradeProduct(listValFinTradeProduct);
        // 定义交易类金融产品附属表List
        List<FinTradeProductAdd> listValFinTradeProductAdd = (List<FinTradeProductAdd>)map.get(
            "ValFinTradeProductAdd");
        result.setListValFinTradeProductAdd(listValFinTradeProductAdd);
        // 定义金融产品标的信息List
        List<FinDealTarget> listValFinDealTarget = (List<FinDealTarget>)map.get(
            "ValFinDealTarget");
        result.setListFinDealTarget(listValFinDealTarget);
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param listValSeclendInfo
     * @return
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValSeclendInfo(List<ValSeclendInfo> listValSeclendInfo) {
        Map<String, Object> result = new HashMap<>();
        // 定义 金融产品分类List
        List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
        // 定义 交易类金融产品List
        List<FinTradeProduct> listValFinTradeProduct = new ArrayList<FinTradeProduct>();
        // 定义 交易类金融产品附属表List
        List<FinTradeProductAdd> listValFinTradeProductAdd = new ArrayList<FinTradeProductAdd>();
        // 定义 金融产品标的信息List
        List<FinDealTarget> listValFinDealTarget = new ArrayList<FinDealTarget>();

        for (ValSeclendInfo dto : listValSeclendInfo) {
            // 交易类金融产品
            FinTradeProduct valFinTradeProduct = getValFinTradeProduct(dto);
            listValFinTradeProduct.add(valFinTradeProduct);
            // 金融产品分类
            FinProductType valFinProductType = getValFinProductType(dto,valFinTradeProduct);
            listValFinProductType.add(valFinProductType);            
            // 交易类金融产品附属表
            FinTradeProductAdd valFinTradeProductAdd = getValFinTradeProductAdd(dto,valFinTradeProduct);
            listValFinTradeProductAdd.add(valFinTradeProductAdd);
            // 金融产品标的信息
            FinDealTarget valFinDealTarget = getValFinDealTarget(dto);
            listValFinDealTarget.add(valFinDealTarget);
        }
        result.put("ValFinProductType", listValFinProductType);
        result.put("ValFinTradeProduct", listValFinTradeProduct);
        result.put("ValFinTradeProductAdd", listValFinTradeProductAdd);
        result.put("ValFinDealTarget", listValFinDealTarget);
        return result;
    }

    /**
     * 交易类金融产品
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinTradeProduct getValFinTradeProduct(ValSeclendInfo dto) {
        // 定义 交易类金融产品dto
        FinTradeProduct finTradeProduct = new FinTradeProduct();
        // 金融产品代码 = A.标的代码
        finTradeProduct.setFinprodId(dto.getTradeId());
        // 金融产品类型 = A.金融产品类型
        finTradeProduct.setFinprodType(dto.getFinprodType());
        // 分支序号 = 设置全局变量(默认为0)
        finTradeProduct.setBranch(InterfCommonConstant.分支序号_默认);
        // 名义本金  = A.标的券面总额
        finTradeProduct.setPrin(dto.getPrinAmt());
        // 本金币种  = A.币种
        finTradeProduct.setCcy(dto.getCcy());
        // 起息日 = A.起息日
        finTradeProduct.setVdate(dto.getVdate());
        // 到期日 = A.到期日
        finTradeProduct.setMdate(dto.getMdate()); 
        // 期限(天) = A.借贷期限
        finTradeProduct.setTerm(dto.getTerm());        
        // 利率类型 = 数据字典固定利率
        finTradeProduct.setIntType(DictCodeList.利率类型_固定利率.getValue());
        // 固定利率 = A.回购利率
        finTradeProduct.setIntRate(dto.getFeeRate());
        // 计息基础 = A.计息基础
        finTradeProduct.setBasis(dto.getBasis());
        // 到期本金 = A.到期本金
        finTradeProduct.setmPrinAmt(dto.getPrinAmt());
        // 到期利息 = A.借贷费用  
        finTradeProduct.setmIntAmt(dto.getFee());
        
        BigDecimal mTradeAmt = dto.getFee()==null?BigDecimal.ZERO:dto.getFee();
        mTradeAmt = mTradeAmt.add(dto.getPrinAmt()==null?BigDecimal.ZERO:dto.getPrinAmt());
        // 到期金额 = A.到期本金+A.借贷费用  
        finTradeProduct.setmTradeAmt(mTradeAmt.setScale(2, BigDecimal.ROUND_HALF_UP));
        // 原金融产品代码 = A.标的代码
        finTradeProduct.setoFinprodId(dto.getTradeId());
        // 原金融产品类型 = A.金融产品类型
//        finTradeProduct.setoFinprodType(dto.getFinprodType());
        // 本金收益特征 = A.本金收益特征
        finTradeProduct.setCapiIncomeFeature(dto.getCapiIncomeFeature());
        // 交易场所 = A.交易场所
        finTradeProduct.setTradeMarket(dto.getTradeMarket());
        // 交易日历 = 共通
        finTradeProduct.setCalendarId(interfCommonService.getCalendarId(dto.getTradeMarket()));
        // 所属机构 = A.所属机构   add by tanxuan on 20210310 多级法人权限控制改造
        finTradeProduct.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        // 返回结果集
        return finTradeProduct;
    }

    /**
     * 金融产品分类表
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValSeclendInfo dto,FinTradeProduct T1) {
        // 定义 金融产品分类表dto
        FinProductType finProductType = new FinProductType();
        // 金融产品代码 = T1.金融产品代码
        finProductType.setFinprodId(T1.getFinprodId());
        // 金融产品类型 = T1.金融产品类型
//        finProductType.setFinprodType(T1.getFinprodType());
        // 分类1 = A.金融产品类型
        finProductType.setType1(dto.getFinprodType());
        // 分类3 = 数据字典.增值税资产分类.债券借贷融出 
        /**add by wujie on 20181210 start **/
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(dto.getFinprodType());
        //资产类型
        fdto.setAssetType(dto.getFinprodType());
        finProductType.setType2(interfCommonService.getFinanceProductClass(fdto));
        /**add by wujie on 20181210 end**/
        finProductType.setBranch(InterfCommonConstant.分支序号_默认);
        return finProductType;
    }
    
    /**
     * 交易类金融产品附属表
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinTradeProductAdd getValFinTradeProductAdd(ValSeclendInfo dto,FinTradeProduct T1) {
        // 定义 交易类金融产品附属表dto
        FinTradeProductAdd finTradeProductAdd = new FinTradeProductAdd();
        // 金融产品代码 = T1.金融产品代码
        finTradeProductAdd.setFinprodId(T1.getFinprodId());
        // 金融产品类型 = T1.金融产品类型
        finTradeProductAdd.setFinprodType(T1.getFinprodType());
        // 分支序号 = 设置全局变量(默认为0)
        finTradeProductAdd.setBranch(InterfCommonConstant.分支序号_默认);
        // 登记托管机构 = A.登记托管机构
        finTradeProductAdd.setRegistOrg(dto.getRegistOrg());
        // 押品只数 = A.押品只数
        finTradeProductAdd.setPledgeSecNum(dto.getPledgeSecNum());
        return finTradeProductAdd;
    }

    /**
     * 金融产品标的信息
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinDealTarget getValFinDealTarget(ValSeclendInfo dto) {
        // 定义 金融产品标的信息dto
        FinDealTarget finDealTarget = new FinDealTarget();
        // 金融产品代码 = A.标的代码
        finDealTarget.setFinprodId(dto.getTradeId());
        // 金融产品类型 = A.金融产品类型
//        finDealTarget.setFinprodType(dto.getFinprodType());
        // 分支序号 = 设置全局变量(默认值为0)
        finDealTarget.setBranch(InterfCommonConstant.分支序号_默认);
        // 标的金融产品代码  = A.标的债券代码
        finDealTarget.setPledgeFinprodId(dto.getSecId());
        // 标的金融产品类型 = 数据字典金融产品类型_债券
//        finDealTarget.setPledgeFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        // 标的券面总额 = A.标的券面总额
        finDealTarget.setPledgeFaceValue(dto.getPrinAmt());
        return finDealTarget;
    }


}
