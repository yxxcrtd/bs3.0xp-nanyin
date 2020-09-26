/*
 * 文件名：OutsideCommitteeQuotationTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.util.ArrayList;
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
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValNfundRate;
/**
 *  委外(非公募基金）行情 (VAL_NFUND_RATE)
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年10月23日
 * @see OutsideCommitteeQuotationTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OutsideCommitteeQuotationTemplateRecService {

  	@Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    @SuppressWarnings({"unchecked", "rawtypes"})
    public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();
        List<ValNfundRate> listValNfundRate = new ArrayList<ValNfundRate>();
        
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 文件路径设置 TODO 此部分处理待以后从画面入口的时候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。

        // 1、文件读取解析
        if (DictCodeInterfList.接口文件类型_XML.getValue().equals(acctEntity.getTmpType())) {
            // 模板类型为XML时候，调用XML解析功能，并返回解析后的数据结构
            returnResult = templateAnalysisForXMLService.execute(acctEntity);
        }
        else if (DictCodeInterfList.接口文件类型_TXT.getValue().equals(acctEntity.getTmpType())) {

        }
        else if (DictCodeInterfList.接口文件类型_EXCEL.getValue().equals(acctEntity.getTmpType())) {

        }
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValNfundRate = returnResult.getT(ValNfundRate.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String,Object> map = handleOutsideCommitteeQuotation(listValNfundRate);
        if(map != null){
        	//场外金融产品行情list
            List<FinProductValuation> listValFinProductValuation = (List<FinProductValuation>)map.get(
            		"ValFinProductValuation");
            result.setListValFinProductValuation(listValFinProductValuation);
        }
        //添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }
	    
	    
   /**
    * <一句话功能简述>
    * <功能详细描述>
    * @param listValNfundRate
    * @return  场外金融产品行情list
    */
	private Map<String, Object> handleOutsideCommitteeQuotation(List<ValNfundRate> listValNfundRate) {
		// TODO Auto-generated method stub
		 Map<String, Object> resultMap = new HashMap<>();			
		 //场外金融产品行情list
		 List<FinProductValuation>  listValFinProductValuation = new  ArrayList<FinProductValuation>();
		 for(ValNfundRate dto :listValNfundRate){
			 //场外金融产品行情
			 FinProductValuation valFinProductValuation = this.getFinProductValuation(dto);
			 listValFinProductValuation.add(valFinProductValuation);
		 }
		  resultMap.put("ValFinProductValuation", listValFinProductValuation);
		return resultMap;
	}

  /**
   * 场外金融产品行情 接收
   * <一句话功能简述>
   * <功能详细描述>
   * @return FinProductValuation
   */
	private FinProductValuation getFinProductValuation(ValNfundRate dto) {
		//场外金融产品行情
		FinProductValuation valFinProductValuation= new FinProductValuation();
		//金融产品代码 =A.产品代码
		valFinProductValuation.setFinprodId(dto.getFinprodId());
		//金融产品类型 =A.金融产品类型
		valFinProductValuation.setFinprodType(dto.getFinprodType());
		//公布日期=A.公布日期
		valFinProductValuation.setPublishDate(dto.getPublishDate());		
		//估值日期=A.估值日期
		valFinProductValuation.setValDate(dto.getValDate());
		//估值=A.估值
		valFinProductValuation.setValuation(dto.getValuation());
		//录入方式=A.录入方式_接口导入
		valFinProductValuation.setInputType(DictCodeListExt1.录入方式_接口导入.getValue());
		return valFinProductValuation;
	}
}
