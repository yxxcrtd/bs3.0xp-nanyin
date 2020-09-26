/*
 * 文件名：PenaltyInterestBaseInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.penaltyinterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.PenaltyInterestCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFxTargetInfo;


/**
 * 拆借付息规则(VAL_DL_TARGET_RATE_RULE) 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年8月25日
 * @see PenaltyInterestBaseInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PenaltyInterestBaseInfoRecService {
	/**
	 * TemplateAnalysisForXMLService
	 */
	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PenaltyInterestCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		PenaltyInterestCommonResult result = new PenaltyInterestCommonResult();
		List<ValFxTargetInfo> listValFxTargetInfo = new ArrayList<ValFxTargetInfo>();
		// 解析模板后的list以及日志list
	    TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValFxTargetInfo =returnResult.getT(ValFxTargetInfo.class);
        
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handlePanaltyBaseInfo(listValFxTargetInfo);
        // 判断返回结果不为空
        if (map != null) {
            // 交易类金融产品表
            List<FinTradeProduct> listFinTradeProduct = (List<FinTradeProduct>)map.get("listFinTradeProduct");
            result.setListValTradeFinProduct(listFinTradeProduct);
            // 交易类金融产品附属表
            List<FinTradeProductAdd> listFinTradeProductAdd = (List<FinTradeProductAdd>)map.get("listFinTradeProductAdd");
            result.setListValTradeFinProductAdd(listFinTradeProductAdd);
            // 现金流信息表
            List<FinCashInfo> listFinCashInfo = (List<FinCashInfo>)map.get("listFinCashInfo");
            result.setListValCashInfo(listFinCashInfo);
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
	}
    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * <一句话功能简述>
     * <功能详细描述>
     * @param listValFxTargetInfo
     * @return 现金流支付规则list
     */
	private Map<String, Object> handlePanaltyBaseInfo(List<ValFxTargetInfo> listValFxTargetInfo) {
	    Map<String, Object> resultMap = new HashMap<>();
	    // 交易类金融产品
	    List<FinTradeProduct> listFinTradeProduct = new ArrayList<FinTradeProduct>();
	    // 交易类金融产品附属表
	    List<FinTradeProductAdd> listFinTradeProductAdd = new ArrayList<FinTradeProductAdd>();
	    // 现金流信息表
	    List<FinCashInfo> listFinCashInfo = new ArrayList<FinCashInfo>();
        //现金流支付规则
		for(ValFxTargetInfo item : listValFxTargetInfo){
		    //交易类金融产品
		    FinTradeProduct finTradeProduct = this.getFinTradeProduct(item);
		    listFinTradeProduct.add(finTradeProduct);
            
            //交易类金融产品
            FinTradeProductAdd finTradeProductAdd = this.getFinTradeProductAdd(item);
            listFinTradeProductAdd.add(finTradeProductAdd);
            
		    // 现金流信息-收息
            FinCashInfo valCashInfo = getValCashInfo(item);
            listFinCashInfo.add(valCashInfo);
		}
		// 交易金融产品
        resultMap.put("listFinTradeProduct", listFinTradeProduct);
        // 交易金融产品附属
        resultMap.put("listFinTradeProductAdd", listFinTradeProductAdd);
        // 现金流信息
        resultMap.put("listFinCashInfo", listFinCashInfo);
        return resultMap;
	}
	
	/**
     * 交易类金融产品 T1
     * 
     * @param dto ValFxTargetInfo
     * @return FinTradeProduct
     * @see
     * @since 1.0
     */
    private FinTradeProduct getFinTradeProduct(ValFxTargetInfo dto) {
        FinTradeProduct result = new FinTradeProduct();
        // 金融产品代码 = A.罚息资产代码
        result.setFinprodId(dto.getAssetCode());
        // 分支序号 = A.分支序号
        result.setBranch(dto.getBranch());
        // 截止日是否冲回 = A.截止日是否冲回
        result.setIsRushBack(dto.getIsCorrect());
        return result;
    }
	
	/**
     * 交易类金融产品附属表 T1
     * 
     * @param dto ValFxTargetInfo
     * @return FinTradeProductAdd
     * @see
     * @since 1.0
     */
    private FinTradeProductAdd getFinTradeProductAdd(ValFxTargetInfo dto) {
        FinTradeProductAdd result = new FinTradeProductAdd();
        // 金融产品代码 = A.罚息资产代码
        result.setFinprodId(dto.getAssetCode());
        // 2020年1月7日 魏鹏：只更新需要的字段，除主键赋值外，其他字段不赋值更新
//        // 金融产品类型 = A.罚息资产类型
//        result.setFinprodType(dto.getFinprodType());
        // 分支序号 = A.分支序号
        result.setBranch(dto.getBranch());
        // 是否计提罚息 = A.是否计提罚息
        result.setIsAccrPenaltyInt(dto.getIsFxdraw());
        // 罚息计提基数 = A.罚息计提基数
        result.setPenaltyBaseType(dto.getPenaltyBaseType());
        return result;
    }
    
    /**
     * T2 现金流信息
     * 
     * @param dto 01-基础信息
     * @return 现金流信息
     * @see
     * @since 1.0
     */
    private FinCashInfo getValCashInfo(ValFxTargetInfo dto) {
        FinCashInfo valCashInfo = new FinCashInfo();
        // 现金流代码 = InterfHelper.getMd5CashId_(A.罚息资产代码,A.罚息资产类型,数据字典：现金流二级类型.罚息)
        valCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getAssetCode(),dto.getFinprodType(),
            String.valueOf(dto.getBranch()), DictCodeListExt1.现金流二级类型_罚息.getValue()));
        // IF 数据字典：分支类型.应付本金 eq  A.分支类型  数据字典：现金流一级类型.本金    ELSE   数据字典：现金流一级类型.利息
        if(DictCodeList.分支类型_应收本金.getValue().equals(dto.getBranchType())){
            // 现金流一级类型 = 本金
            valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
        }
        else{
            // 现金流一级类型 = 利息
            valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        }
        // 现金流二级类型 = 罚息
        valCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_罚息.getValue());
        // 币种 = A.币种
        valCashInfo.setCcy(dto.getCcy());
        // 业务代码 = A.罚息资产代码
        valCashInfo.setFinprodId(dto.getAssetCode());
        // 业务类型 = A.罚息资产类型
        valCashInfo.setFinprodType(dto.getFinprodType());
        // 分支序号 = A.分支序号
        valCashInfo.setBranch(dto.getBranch());
        
        return valCashInfo;
    }

}
