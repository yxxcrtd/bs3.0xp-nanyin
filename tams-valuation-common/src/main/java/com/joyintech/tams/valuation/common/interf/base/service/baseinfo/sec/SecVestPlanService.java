/*
 * 文件名：SecExercisePlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.SecBaseInfoRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecVestPlan;
/**
 * 债券定义--行权计划
 * 〈一句话功能简述〉
 * @author lishicheng
 * @version 1.0 2018年8月17日
 * @see SecExercisePlanService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecVestPlanService {
	/**
	 *  TemplateAnalysisForXMLService
	 */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SecBaseInfoRecProcessorRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                          TemplateOrderInfoReaderResult acctEntity) {
        SecBaseInfoRecProcessorRecCommonResult result = new SecBaseInfoRecProcessorRecCommonResult();
        List<ValSecVestPlan> listValSecVestPlan = new ArrayList<ValSecVestPlan>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValSecVestPlan = returnResult.getT(ValSecVestPlan.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        result.setListValExercisePlan(this.getValVestPlan(listValSecVestPlan));
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    private List<FinExercisePlan> getValVestPlan(List<ValSecVestPlan> listValSecVestPlan) {
        List<FinExercisePlan> result = new ArrayList<FinExercisePlan>();
        for (ValSecVestPlan item : listValSecVestPlan) {
        	FinExercisePlan valFinExercisePlan = new FinExercisePlan();
        	/**modify by wujie on 20190121 start
        	// 现金流代码 = E.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.行权
            String cashId = InterfHelper.getMd5CashId_(item.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
            		,DictCodeListExt1.现金流二级类型_行权.getValue());
            **/
            // 现金流代码 = E.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.本金
            String cashId = InterfHelper.getMd5CashId_(item.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
            		, DictCodeListExt1.现金流二级类型_本金.getValue());
           /**modify by wujie on 20190121 end**/
            
            valFinExercisePlan.setCashId(cashId);
        	
            //行权方式 = E.行权方式
        	valFinExercisePlan.setExerciseType(item.getExecMode());
        	//申请开始日 = E.行权申请开始日
        	valFinExercisePlan.setVdate(item.getVdate());
        	//申请结束日 = E.行权申请结束日
        	valFinExercisePlan.setMdate(item.getMdate());
        	//计划行权日 = E.行权日期
        	valFinExercisePlan.setExerciseDate(item.getExecDate());
        	//行权价 = E.行权价格
        	valFinExercisePlan.setExercisePrice(item.getExecCprice());
        	//金融产品代码 = E.资产代码
        	valFinExercisePlan.setFinprodId(item.getAssetId());
        	//金融产品类型 = 数据字典：金融产品类型.债券
        	valFinExercisePlan.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        	//分支序号 = 设置全局变量(默认为0）
        	valFinExercisePlan.setBranch(InterfCommonConstant.分支序号_默认);
        	//实际行权日 = E.实际行权日 add by yanya on 20210309
            valFinExercisePlan.setActExerciseDate(item.getPayDate());
            result.add(valFinExercisePlan);
        }
        return result;
    }
}
