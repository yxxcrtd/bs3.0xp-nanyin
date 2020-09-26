/*
 * 文件名：StandardMarketBondPriceValService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqBondValuationInfo;

/**
 * 标准债券市价估值模板处理
 *
 * @author zhangzhao
 * @version 1.0 2018年10月26日
 * @see StandardMarketBondPriceValService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class StandardMarketBondPriceValService {

	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {

		// 定义返回变量
		HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();

		List<HqBondValuationInfo> valHqBondValuationInfo = new ArrayList<HqBondValuationInfo>();

		// 解析模板后的list以及日志list
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		
		if (DictCodeInterfList.接口文件类型_STTXT.getValue().equals(acctEntity.getTmpType())) {
            returnResult = templateAnalysisForXMLService.execute(acctEntity);
        }
		
		// 2、List数据接收转换
		valHqBondValuationInfo = returnResult.getT(HqBondValuationInfo.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleStandardFondMarketVal(valHqBondValuationInfo,acctEntity.getResovleDate());
        result.setMstBondValuationInfoList((List<MstBondValuationInfo>)map.get("valBondValuationInfo"));
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
	}

	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param hqStandardFondMarketVal
	 * @return
	 * @see
	 * @since 1.0
	 */
	private Map<String, Object> handleStandardFondMarketVal(List<HqBondValuationInfo> infoList,
			Date date) {
	    if(infoList == null){
	        infoList = new ArrayList<>();
	    }
	       
		Map<String, Object> resultMap = new HashMap<>();
        List<MstBondValuationInfo> valBondValuationInfoList  = new ArrayList<MstBondValuationInfo>();
        for (HqBondValuationInfo dto : infoList) {
        	MstBondValuationInfo valBondValuationInfo = getStandardFondMarketVal(dto, date);
        	valBondValuationInfoList.add(valBondValuationInfo);
        }
        resultMap.put("valBondValuationInfo", valBondValuationInfoList);
        return resultMap;
	}

	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @param date
	 * @return
	 * @see
	 * @since 1.0
	 */
	private MstBondValuationInfo getStandardFondMarketVal(HqBondValuationInfo dto, Date date) {
		MstBondValuationInfo valBondValuationInfo = new MstBondValuationInfo();
		//债券代码  = A.债券id
		valBondValuationInfo.setSecId(dto.getSecId());
		
		//估值来源 = A.估值来源
		/**add by wujie on 20181105 start remark:估值来源**/
		String valueSource = dto.getValueource();
		String valueSourceKg = CacheUtil.getSysParam(InterfCommonConstant.估值来源_资讯_默认值开关);
		if("ON".equals(valueSourceKg)){
			valueSource = CacheUtil.getSysParam(InterfCommonConstant.估值来源_资讯_默认值);
		}else{
			//TODO 该数据字典不存在
//			valueSource = InterfHelper.getDictColdeList_ItemName(DictCode.估值来源_资讯.getValue(),
//			dto.getValueource());
		}
		/**add by wujie on 20181105 end remark:估值来源**/
		
		valBondValuationInfo.setValueSource(valueSource);
		//估值日 = A.估值日
		valBondValuationInfo.setValueDate(dto.getValueDate());
		//剩余期限 = A.剩余期限
		valBondValuationInfo.setLastPeriod(dto.getLastPeriod());
		//市价全价 = A.市场全价
		valBondValuationInfo.setPrice(dto.getPrice());
		//市价净价 = A.市场净价
		valBondValuationInfo.setNetprice(dto.getNetprice());
		//市价久期 = A.市价久期     
		valBondValuationInfo.setmDuration(dto.getmDuration());
		//市价凸性 =	A.市价凸性
		valBondValuationInfo.setmConvexity(dto.getmConvexity());
		//基点价值 =	A.基点价值
		valBondValuationInfo.setBpvalue(dto.getBpvalue());
		//利差久期 =	A.利差久期
		valBondValuationInfo.setSduration(dto.getSduration());
		//利差凸性 =	A.利差凸性
		valBondValuationInfo.setScnvxty(dto.getScnvxty());
		//利率久期 =	A.利率久期
		valBondValuationInfo.setInterestDuration(dto.getInterestDuration());
		//利率凸性 =	A.利率凸性
		valBondValuationInfo.setInterestCnvxty(dto.getInterestCnvxty());
		//市价收益率 = A.市价收益率
		BigDecimal bpyield = dto.getBpyield();
		if(bpyield!=null){
			bpyield = bpyield.divide(new BigDecimal(100), 12,BigDecimal.ROUND_HALF_UP);
		}
		valBondValuationInfo.setBpyield(bpyield);
		
		return valBondValuationInfo;
	}

	
}
