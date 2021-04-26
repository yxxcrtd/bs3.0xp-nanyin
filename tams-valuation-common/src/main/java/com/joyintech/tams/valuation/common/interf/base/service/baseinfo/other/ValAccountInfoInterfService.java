/*
 * 文件名：ValAccountInfoInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.OtherBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAccountInfo;

@Service
public class ValAccountInfoInterfService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    /**
     * 读取模板处理
     * 
     * @param templateSourceFlgDTO
     * @param acctEntity
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public OtherBaseInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                         TemplateOrderInfoReaderResult acctEntity) {
    	OtherBaseInfoCommonResult result = new OtherBaseInfoCommonResult();
        List<ValAccountInfo> listValAccountInfo = new ArrayList<ValAccountInfo>(); 
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
        
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValAccountInfo = returnResult.getT(ValAccountInfo.class);
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleListValAccountInfo(listValAccountInfo);
        // 判断返回结果不为空
        if (map != null) {
        	List<StlCapacctInfo> listValMstCapacctInfo = (List<StlCapacctInfo>)map.get("ValMstCapacctInfo");
            result.setValMstCapacctInfoList(listValMstCapacctInfo);
            /**add by wujie on 20180814 start remark:活期利息改造**/
            if(map.get("FinCashInfo")!=null){
            	List<FinCashInfo> finCashInfoList = (List<FinCashInfo>)map.get("FinCashInfo");
    			result.setFinCashInfoList(finCashInfoList);
            }
            if(map.get("FinProductType")!=null){
            	List<FinProductType> finProductTypeList = (List<FinProductType>)map.get("FinProductType");
    			result.setFinProductTypeList(finProductTypeList);
            }
			Map<String,Object> paramMap = new HashMap<String,Object>();
			if(map.get("FinCashInfo_no")!=null){
				paramMap.put("FinCashInfo_no", map.get("FinCashInfo_no"));
			}
			if(map.get("FinProductType_no")!=null){
				paramMap.put("FinProductType_no", map.get("FinProductType_no"));
			}
			result.setParamMap(paramMap);
			/**add by wujie on 20180814 end**/
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    /**
     * 
     * @param handleListValAccountInfo 账户信息表
     * @return 清算账户信息表
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValAccountInfo(List<ValAccountInfo> listValAccountInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        List<StlCapacctInfo> valMstCapacctInfoList = new ArrayList<StlCapacctInfo>();
        
        /**add by wujie on 20180814 start remark:活期利息改造**/
		List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
		List<FinCashInfo> finCashInfoList_no = new ArrayList<FinCashInfo>();
		List<FinProductType> finProductTypeList = new ArrayList<FinProductType>();
		List<FinProductType> finProductTypeList_no = new ArrayList<FinProductType>();
		/**add by wujie on 20180814 end**/
		
        // 获取基础信息
        for (ValAccountInfo dto : listValAccountInfo) {
            // 清算账户信息 处理
            StlCapacctInfo valMstCapacctInfo = getValMstCapacctInfo(dto);
            valMstCapacctInfoList.add(valMstCapacctInfo);
            /**add by wujie on 20180814 start remark:活期利息改造**/
            if(DictCodeList.是否_是.getValue().equals(dto.getIsCalc())){
            	FinCashInfo finCashInfo =  this.getFinCashInfo(dto);
            	finCashInfoList.add(finCashInfo);
            	
            	FinProductType finProductType = this.getValFinProductType(dto);
            	finProductTypeList.add(finProductType);
            }else{
            	FinCashInfo finCashInfo =  this.getFinCashInfo(dto);
            	finCashInfoList_no.add(finCashInfo);
            	
            	FinProductType finProductType = this.getValFinProductType(dto);
            	finProductTypeList_no.add(finProductType);
            }
            /**add by wujie on 20180814 end**/
        }
        resultMap.put("ValMstCapacctInfo", valMstCapacctInfoList);
        
        /**add by wujie on 20180814 start remark:活期利息改造**/
		resultMap.put("FinCashInfo", finCashInfoList);
		resultMap.put("FinCashInfo_no", finCashInfoList_no);
		resultMap.put("FinProductType", finProductTypeList);
		resultMap.put("FinProductType_no", finProductTypeList_no);
		/**add by wujie on 20180814 end**/
        return resultMap;
    }
    
    /**
     * 清算账户表 接收
     * 
     * @param dto 01-账户信息
     * @return 清算账户表
     * @see
     * @since 1.0
     */
    private StlCapacctInfo getValMstCapacctInfo(ValAccountInfo dto) {

        StlCapacctInfo valMstCapacctInfo = new StlCapacctInfo();
        // 账号 = A.账号
        valMstCapacctInfo.setAcctNo(dto.getAcctId());
        valMstCapacctInfo.setBusiAcctNo(dto.getAcctId());
        // 币种 = A.币种
        valMstCapacctInfo.setCcy(dto.getCcy());
        // 户名 = A.户名
        valMstCapacctInfo.setAcctName(dto.getAcctName());
        // 开户行 = A.开户行
        valMstCapacctInfo.setBankDep(dto.getBankDep());
        // TODO 客户代码 = A.所属机构代码
//        valMstCapacctInfo.setCustomerId(dto.getBelongId());
        // TODO 行内行外 = 待定
        valMstCapacctInfo.setInOutBank("");
        // 联行行号 = A.联行行号
        valMstCapacctInfo.setCorrNo(dto.getCorrNo());
        // 大额支付系统号 = A.大额支付系统号
        valMstCapacctInfo.setLargePayno(dto.getLargePayno());
        // 境内境外 = A.境内境外
        valMstCapacctInfo.setBlnArea(dto.getBlnArea());
        // TODO 是否DVP清算 = 待定
//        valMstCapacctInfo.setIsDvp("");
        // TODO 是否允许透支 = 待定
        valMstCapacctInfo.setIsOverdraw("");
        // 所属机构 = A.所属机构   add by tanxuan on 20210310 多级法人权限控制改造
        valMstCapacctInfo.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        return valMstCapacctInfo;
    }
	/**
	 * 现金流信息表 接收
	 * 
	 * @param dto
	 *            标的账户信息
	 * @return 现金流信息表 
	 * @see
	 * @since 1.0
	 * add by wujie on 20180814
	 */
	private FinCashInfo getFinCashInfo(ValAccountInfo dto) {
		FinCashInfo finCashInfo = new FinCashInfo();
		//现金流代码=金融产品代码+金融产品类型+现金流二级类型
		String cashId = InterfHelper.getMd5CashId_(dto.getAcctId(),DictCodeListExt1.金融产品类型_资金账户.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue());
		finCashInfo.setCashId(cashId);
		
		//现金流一级类型
		finCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
		
	    //现金流二级类型
		finCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
		
		//金融产品代码 = A.账号
		finCashInfo.setFinprodId(dto.getAcctId());
		
		//金融产品类型 = 数据字典：金融产品类型.资金账户
		finCashInfo.setFinprodType(DictCodeListExt1.金融产品类型_资金账户.getValue());
		
		finCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
		
		return finCashInfo;
	}
	
	/**
     * 接收 金融产品分类 T3
     * 
     * @param dto ValSecBaseInfo
     * @return ValFinProductType
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValAccountInfo dto) {
        FinProductType result = new FinProductType();
        // 金融产品代码 = A.账户
        result.setFinprodId(dto.getAcctId());
        // 金融产品类型 =数据字典：金融产品类型.资金账户
//        result.setFinprodType(DictCodeListExt1.金融产品类型_资金账户.getValue());
        // 分类3 = 数据字典：增值税资产分类.活期存款-活期存款
        result.setType3(DictCodeList.增值税资产分类_活期存款_活期存款.getValue());
        result.setBranch(InterfCommonConstant.分支序号_默认);
        return result;
    }
    
//	
//	/**
//	 * 现金流计算规则表 接收
//	 * 
//	 * @param dto
//	 *            标的账户信息
//	 * @return 现金流计算规则表  账务明细代码表
//	 * @see
//	 * @since 1.0
//	 * add by wujie on 20180814
//	 */
//	private FinCashCalcRule getFinCashCalcRule(ValAccountInfo dto,FinCashInfo finCashInfo) {
//		FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
//		//现金流代码
//		finCashCalcRule.setCashId(finCashInfo.getCashId());
//		//生效日 = A.利率生效日
//		finCashCalcRule.setEffDate(dto.getEffDate());
//		
//		//计提类型
//		finCashCalcRule.setCalcType(DictCodeListExt1.计算类型_固定利率.getValue());
//		
//		//生效利率 = A.利率
//		finCashCalcRule.setYield(dto.getRate());
//		
//		//计息基础 = A.计息基础
//		finCashCalcRule.setBasis(dto.getChargeBase());
//		
//		//金融产品代码 = A.账号
//		finCashCalcRule.setFinprodId(dto.getAcctId());
//		
//		//金融产品类型 = A.账户类型
//		finCashCalcRule.setFinprodType(dto.getActType());
//		
//		finCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
//		
//		return finCashCalcRule;
//	}

}
