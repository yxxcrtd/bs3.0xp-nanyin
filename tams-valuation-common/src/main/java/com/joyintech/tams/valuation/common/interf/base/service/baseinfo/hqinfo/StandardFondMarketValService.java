/*
 * 文件名：StandardFondMarketValService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqParFundInfo;

/**
 * 标准基金市场估值模板处理
 * 
 * @author zhangzhao
 * @version 1.0 2018年10月26日
 * @see StandardFondMarketValService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class StandardFondMarketValService {

	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
				// 定义返回变量
				HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();

				List<HqParFundInfo>  valHqParFundInfo= new ArrayList<HqParFundInfo>();

				// 解析模板后的list以及日志list
				TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
				
				if (DictCodeInterfList.接口文件类型_STTXT.getValue().equals(acctEntity.getTmpType())) {
		            returnResult = templateAnalysisForXMLService.execute(acctEntity);
		        }
				
				// 2、List数据接收转换
				valHqParFundInfo = returnResult.getT(HqParFundInfo.class);

		        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		        Map<String, Object> map = handleStandardFondMarketVal(valHqParFundInfo,acctEntity.getResovleDate());
		        
		        result.setListValFinProductValuation((List<FinProductValuation>)map.get("standardFondMarketVal"));
		        // 添加日志
		        result.setListValInterflog(returnResult.getValInterflog());
		        return result;
	}

	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param hqStandardFondMarketVal
	 * @param date 
	 * @return
	 * @see
	 * @since 1.0
	 */
	private Map<String, Object> handleStandardFondMarketVal(List<HqParFundInfo> hqStandardFondMarketVal, Date date) {
	    if(hqStandardFondMarketVal == null){
	        hqStandardFondMarketVal = new ArrayList<>();
	    }
        Map<String, Object> resultMap = new HashMap<>();
        List<FinProductValuation> valFinProductValuationList = new ArrayList<FinProductValuation>();
        for (HqParFundInfo dto : hqStandardFondMarketVal) {
            // 场外金融产品行情 处理
            FinProductValuation valFinProductValuation = getValInfo(dto, date);
            //add by wujie on 20181102 start remark:估值为零时不入库
            if(BigDecimal.ZERO.compareTo(valFinProductValuation.getValuation())==0){
            	continue;
            }
            //add by wujie on 20181102 end
            valFinProductValuationList.add(valFinProductValuation);
        }
        resultMap.put("standardFondMarketVal", valFinProductValuationList);
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
    private FinProductValuation getValInfo(HqParFundInfo dto, Date date) {
        FinProductValuation valFinProductValuation =  new FinProductValuation();
        
        //金融产品代码  FINPROD_ID              A.证券代码  需根据A.证券代码确认是债券还是基金，规则由需求提供
        valFinProductValuation.setFinprodId(dto.getZqdm());
        //金融产品类型  FINPROD_TYPE                默认值：F06 数据字典：金融产品类型-基金
        valFinProductValuation.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
        //公布日期    PUBLISH_DATE               
        valFinProductValuation.setPublishDate(date);
        //估值日期    VAL_DATE              
        valFinProductValuation.setValDate(dto.getValDate());
        // 估值  VALUATION               A.单位资产净值    
        valFinProductValuation.setValuation(dto.getNetValue());
        // TODO 估值类型    VAL_TYPE                    根据A.基金类型判断，如果为货币型 则为万份收益，否则为净值
        //录入方式 固定值:    数据字典.录入方式.接口导入
        valFinProductValuation.setInputType(DictCodeListExt1.录入方式_接口导入.getValue());
        return valFinProductValuation;
    }
    
}
