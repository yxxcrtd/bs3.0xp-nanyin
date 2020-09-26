/*
 * 文件名：PlannedCapPlanTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.mngasset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.PlannedAssetBaseInfoRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngCapPlan;

/**
 * 计划类资产（利率型）还本计划模板处理
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2018年6月26日
 * @see PlannedCapPlanTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlannedCapPlanTemplateRecService {
    
    /**
     *基本信息模板处理service
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public PlannedAssetBaseInfoRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                           TemplateOrderInfoReaderResult acctEntity) {
        //定义返回变量
        PlannedAssetBaseInfoRecCommonResult result = new PlannedAssetBaseInfoRecCommonResult();
        List<ValMngCapPlan> listValMngCapPlan = new ArrayList<ValMngCapPlan>(); 
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
                
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValMngCapPlan = returnResult.getT(ValMngCapPlan.class);
    
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String,Object> map = handleListValMngCapPlan(listValMngCapPlan);
        // 判断返回结果不为空
        if (map != null) {
            List<FinCashPlan> listValCashPlan = (List<FinCashPlan>)map.get("ValCashPlan");
            result.setListValCashPlan(listValCashPlan);
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    /**
     * 
     * @param listValMngCapPlan 计划类资产（利率型）还本计划信息
     * @return 现金流计划表 
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValMngCapPlan(List<ValMngCapPlan> listValMngCapPlan) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinCashPlan> ValCashPlanList = new ArrayList<FinCashPlan>();
        // 获取付息计划信息
        for (ValMngCapPlan dto : listValMngCapPlan) {
            // 现金流计划信息 处理
            FinCashPlan ValCashPlan = getValCashPlan(dto);
            ValCashPlanList.add(ValCashPlan);
        }
        resultMap.put("ValCashPlan", ValCashPlanList);
        return resultMap;
    }
    
    /**
     * 现金流计划表 接收
     * 
     * @param dto 还本计划
     * @return 现金流计划信息
     * @see
     * @since 1.0
     */
    private FinCashPlan getValCashPlan(ValMngCapPlan dto) {
        FinCashPlan valCashPlan = new FinCashPlan();
        // 现金流代码 = A.资产代码+数据字典：金融产品类型.计划类资产+数据字典：现金流二级类型.本金
        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(), DictCodeListExt1.金融产品类型_计划类资产.getValue()
        		,DictCodeListExt1.现金流二级类型_本金.getValue());
        valCashPlan.setCashId(cashId);
        
        // 现金流序号= A.现金流序号
        valCashPlan.setCashNum(dto.getOrderNo().longValue());
        // 调整前的计划支付日 = A.支付日
        valCashPlan.setPayDateUnadjust(dto.getPayDate());
        //调整前的计算结束日 = A.还本日
        valCashPlan.setMdateUnadjust(dto.getRepayDate());
        // 调整后的计算结束日= A.还本日
        valCashPlan.setMdate(dto.getRepayDate());
        // 调整后的计划支付日= 支付日
        valCashPlan.setPayDate(dto.getPayDate());
        // 计划支付本金= A.还本金额
        valCashPlan.setPrinAmt(dto.getRepayAmt());
        //ADD JINMAODONG 曹国说需要给计息本金塞值
        valCashPlan.setIntPrinAmt(dto.getRepayAmt());
        valCashPlan.setPrinAmt(dto.getRepayAmt());
        
        /**modify by wujie on 20190114 start
        if(dto.getIntAmt()!=null){
            //利息金额>0
            if(dto.getIntAmt().compareTo(BigDecimal.ZERO )>1){
                // 是否支付利息= Y:是
                valCashPlan.setIsPayInt(DictCodeList.是否_是.getValue());
            }
            //利息金额<0
            else if(dto.getIntAmt().compareTo(BigDecimal.ZERO )<1){
                // 是否支付利息= Y:是
                valCashPlan.setIsPayInt(DictCodeList.是否_否.getValue());
            }
        }
        **/
        // 计划支付利息= A.利息金额
        valCashPlan.setIntAmt(dto.getIntAmt());
        
        /** modify by wujie on 20190114 start
        // 计划支付现金流= A.利息金额+A.还本金额
        valCashPlan.setCashAmt(dto.getIntAmt().add(dto.getRepayAmt()));
        **/
        //是否支付利息 = A.是否还本带息
        valCashPlan.setIsPayInt(dto.getIsInt()==null?DictCodeList.是否_是.getValue():dto.getIsInt());
        //A.是否还本带息 eq 否 计划支付现金流 = 计划支付本金
        //A.是否还本带息 eq 是 计划支付现金流 = 计划支付本金+计划支付利息
        BigDecimal cashAmt = dto.getRepayAmt()==null?BigDecimal.ZERO:dto.getRepayAmt();
        if(DictCodeList.是否_是.getValue().equals(valCashPlan.getIsPayInt())){
        	cashAmt = cashAmt.add(dto.getIntAmt()==null?BigDecimal.ZERO:dto.getIntAmt());
        }
        valCashPlan.setCashAmt(cashAmt);
        /**modify by wujie on 20190114 end**/
        // 现金流单位= 02：总额
        valCashPlan.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码= A.资产名称
        valCashPlan.setFinprodId(dto.getAssetId());
        // 金融产品类型= F17:债权类资产
        valCashPlan.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 分支序号= 设置全局变量(默认为0）
        valCashPlan.setBranch(InterfCommonConstant.分支序号_默认);
        return valCashPlan;
    }
}
