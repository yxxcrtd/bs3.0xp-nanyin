/*
 * 文件名：CurrentDepositRateTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.currentDeposit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CurrentDepositCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValCurrentDepositRate;
/**
 *  活期存款利率 (VAL_CURRENT_DEPOSIT_RATE)
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年8月22日
 * @see CurrentDepositRateTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CurrentDepositRateTemplateRecService {

  	@Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
  	
  	@Autowired
  	private InterfCommonService interfCommonService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CurrentDepositCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
    	CurrentDepositCommonResult result = new CurrentDepositCommonResult();
        List<ValCurrentDepositRate> listValCurrentDepositRate = new ArrayList<ValCurrentDepositRate>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValCurrentDepositRate = returnResult.getT(ValCurrentDepositRate.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String,Object> map = handleCurrentDepositRate(listValCurrentDepositRate);
        if(map != null){
        	//现金流计算规则list
            List<FinCashCalcRule> listValFinCashCalcRule = (List<FinCashCalcRule>)map.get(
            		"ValFinCashCalcRule");
            result.setListValCashCalcRule(listValFinCashCalcRule);
            
            //现金流利率信息list
            List<FinRateInfo> listValFinCashIntInfo = (List<FinRateInfo>)map.get(
                    "ValFinCashIntInfo");
            result.setListValCashIntInfo(listValFinCashIntInfo);
        }
        //添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
	    
	    
   /**
    * 01 活期存款利率
    * <一句话功能简述>
    * <功能详细描述>
    * @param listValCurrentDepositRate
    * @return  现金流计算规则list +现金流利率信息list
    */
	private Map<String, Object> handleCurrentDepositRate(List<ValCurrentDepositRate> listValCurrentDepositRate) {
		 Map<String, Object> resultMap = new HashMap<>();
		 //现金流计算规则list
		 List<FinCashCalcRule>  listFinCashCalcRule = new  ArrayList<FinCashCalcRule>();
		 //现金流利率信息list
		 List<FinRateInfo> listFinCashIntInfo = new ArrayList<FinRateInfo>();
		 for(ValCurrentDepositRate dto :listValCurrentDepositRate){
			 //现金流利率信息
			 FinRateInfo valFinCashIntInfo = this.getFinCashIntInfo(dto);
			 listFinCashIntInfo.add(valFinCashIntInfo);
			 
			 //现金流计算规则
			 FinCashCalcRule valFinCashCalcRule =interfCommonService.getFinCashCalcRule(valFinCashIntInfo);
			 if(valFinCashCalcRule!=null){
				 listFinCashCalcRule.add(valFinCashCalcRule);
			 }
		 }
		  resultMap.put("ValFinCashCalcRule", listFinCashCalcRule);
		  resultMap.put("ValFinCashIntInfo", listFinCashIntInfo);
		return resultMap;
	}

  /**
   * 现金流利率信息表 接收
   * <一句话功能简述>
   * <功能详细描述>
   * @return FinCashIntInfo
   */
	private FinRateInfo getFinCashIntInfo(ValCurrentDepositRate dto) {
		//现金流利率信息
		FinRateInfo valFinCashIntInfo = new FinRateInfo();
		//现金流代码=MD5(A.资金账户账号+数据字典：金融产品类型.资金账户+数据字典：现金流二级类型.利息)
		valFinCashIntInfo.setCashId(InterfHelper.getMd5CashId_(dto.getAcctId(),
				DictCodeListExt1.金融产品类型_资金账户.getValue(),DictCodeListExt1.现金流二级类型_利息.getValue()));
		//生效日=A.生效日
		valFinCashIntInfo.setEffDate(dto.getEffDate());
		//利率类型=A.利率类型
		valFinCashIntInfo.setIntType(dto.getRateType());
		//重置类型 =A.重置类型
		valFinCashIntInfo.setResetType(dto.getResetType());
		//重置频率 =A.重置频率
		valFinCashIntInfo.setResetFreq(dto.getResetFrequency());
		//首次重置日 = A.首次重置日
		/**
		 * modify by wujie on 20180939 start
		if(dto.getFirstResetDate()!=null){
			valFinCashIntInfo.setFirstResetDate(new BigDecimal(dto.getFirstResetDate().getTime()));
		}
		**/
		valFinCashIntInfo.setFirstResetDate(dto.getFirstResetDate());
		/**modify by wujie on 20180930 end**/
		
		//指定重置日期 =A.指定重置日期
		valFinCashIntInfo.setResetDate(dto.getAppointResetDate());
		//提前观察数量 =A.提前观察数量
		valFinCashIntInfo.setObserveBefDay(dto.getObsCycleNumber());
		//提前观察单位=A.提前观察单位
		valFinCashIntInfo.setObserveBefUnit(dto.getObsCycleUnit());
		//初始利率=A.初始利率
		valFinCashIntInfo.setRate(dto.getIntRate());
		//计算基础=A.计息基础
		valFinCashIntInfo.setBasis(dto.getChargeBase());
		//系数=A.系数
		valFinCashIntInfo.setCoefficient(dto.getRatio());
		//利差=A.利差
		valFinCashIntInfo.setSpreadRate(dto.getSpreadRate());
		//利率上限=A.利率上限
		valFinCashIntInfo.setHighestRate(dto.getRateMax());
		//利率下限=A.利率下限
		valFinCashIntInfo.setLowestRate(dto.getRateMin());
		//基准类型 = A.基准类型
		valFinCashIntInfo.setBenchmarkType(dto.getLinkRateType());
		//基准编号=A.基准利率编号
		valFinCashIntInfo.setBenchmarkId(dto.getIntRateId());
		//金融产品代码=A.资金账户账号
		valFinCashIntInfo.setFinprodId(dto.getAcctId());
		//金融产品类型=数据字典：金融产品类型.资金账户
		valFinCashIntInfo.setFinprodType(DictCodeListExt1.金融产品类型_资金账户.getValue());
		//分支序号 = 设置全局变量(默认为0）
		valFinCashIntInfo.setBranch(InterfCommonConstant.分支序号_默认);
		
		return valFinCashIntInfo;
	}
}
