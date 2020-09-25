package com.joyintech.service.product.rddesgin.productSimulationTrial.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dao.ProductSimulationTrialMapper;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductSimulationTrialDto;
import com.joyintech.tams.common.base.entity.MockResultInfo;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;

/**
 * 产品模拟试算service
 * 
 * @author yaozhenguo
 * @version 1.0 2019年10月29日
 * @see ProductSimulationTrialDto
 * @since 1.0
 */
@Service
public class ProductSimulationTrialService extends BaseService<ProductSimulationTrialDto> {

	@Autowired
	private ProductSimulationTrialMapper productSimulationTrialMapper;

	/**
	 * 产品设计系列编号下拉框
	 * @param paramMap 过滤参数
	 * @return 下拉框列表
	 * @throws Exception
	 */
	public Page<SelectOptionDto> queryDesignIdInfo(int page,int length,Map<String, Object> paramMap) {
		paramMap.put("DATA_STATUS", DictCodeListExt1.有效状态_有效.getValue());
		return this.queryForPageForSelectReturn(page,length,()->productSimulationTrialMapper.queryDesignIdInfo(paramMap));
	}

	/**
	 * 查询模拟试算信息
	 *
	 * @param page page
	 * @param length length
	 * @param pageParam 参数
	 * @return 模拟试算信息
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	public Page<ProductSimulationTrialDto> findPage(int page, int length,
													Map<String, Object> pageParam)
			throws Exception {
		Page<ProductSimulationTrialDto> resultPage = this.queryForPage(page, length,
				() -> productSimulationTrialMapper.findPage(pageParam));
		return resultPage;
	}
	
	/**
	 * 待试算产品的产品规模查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @see
	 * @since 1.0
	 */
	public BigDecimal getPrdScale(@RequestBody Map<String, Object> params) {
	    BigDecimal prdScale = null;
	    List<MockResultInfo> list = productSimulationTrialMapper.queryPrdScaleFromMockResultInfo(params);
	    if(StringUtil.isEmptyList(list)) {
//	        MockResultInfo mockResultInfo = productSimulationTrialMapper.queryPrdScaleFromDesignInfo(params);
//	        if(mockResultInfo != null) {
//	            prdScale = mockResultInfo.getPrdScale();
//	        }
	    }
	    else {
	        MockResultInfo mockResultInfo = list.get(0);
	        prdScale = mockResultInfo.getPrdScale();
	    }
        return prdScale;
	    
	}
	
	public List<MstIntRateInfo> queryMstIntRateInfo(Map<String,Object> param) {
        return productSimulationTrialMapper.queryMstIntRateInfo(param); 
	}
	
	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public boolean deleteMockTrade(@RequestBody Map<String,Object> param) {
	    //先删除模拟试算交易利率
        productSimulationTrialMapper.deleteMockTradeRate(param);
	    //删除模拟试算交易
	    productSimulationTrialMapper.deleteMockTradeDeal(param);

        return true;  
	}
	
	/**
	 * 获取试算结果
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param paramMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ProductSimulationTrialDto getProductSimulationTrialBySeqNo(Map<String, Object> paramMap){
	    paramMap.put("DATA_STATUS_01", DictCodeListExt1.有效状态_有效.getValue());
        return productSimulationTrialMapper.getProductSimulationTrialBySeqNo(paramMap);
	}
	
	/**
	 * 查询模拟试算交易
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public Page<ProductSimulationTrialDto> findTablePage(int page, int length, Map<String, Object> pageParam){
	    pageParam.put("DATA_STATUS_01", DictCodeListExt1.有效状态_有效.getValue());
	    pageParam.put("RATE_MNCLASS_01", DictCodeListExt3.利率上下限区分_利率上限.getValue());
	    pageParam.put("RATE_MNCLASS_02", DictCodeListExt3.利率上下限区分_利率下限.getValue());
	    pageParam.put("INT_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
	    
	    pageParam.put("SECRATE_DATA", DictCode.利率行情.getValue());
        pageParam.put("MARKET_ID", DictCode.曲线指数.getValue());
        pageParam.put("CURVE_CODE", DictCode.曲线代码.getValue());
	    Page<ProductSimulationTrialDto> resultPage = this.queryForPage(page, length,
            () -> productSimulationTrialMapper.findTablePage(pageParam));
	    return resultPage;
	}
	
	/**
	 * 获取新的批次号
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public String getNewBacthId(Map<String,Object> param){
	    String mockDate = (String)param.get("mockDate");
	    String NewBacthId = productSimulationTrialMapper.getNewBacthId();
	    NewBacthId = mockDate+NewBacthId;
	    NewBacthId = StringUtils.leftPad(String.valueOf(NewBacthId), 10, '0');
        return NewBacthId;
	}
	
	/**
	 * 浮动利率时获取浮动利率下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> getIndexIdSource(int page,int length,Map<String, Object> param) {
	    param.put("BENCHMARK_TYPE_02", DictCodeListExt1.基准类型_2_指数.getValue());
	    param.put("BENCHMARK_TYPE_03", DictCodeListExt1.基准类型_2_收益率曲线.getValue());
	    param.put("BENCHMARK_TYPE_04", DictCodeListExt1.基准类型_2_利率.getValue());
	    
        param.put("BENCHMARK_TYPE_NAME_02", DictCodeListExt1.基准类型_2_指数.getCodeName());
        param.put("BENCHMARK_TYPE_NAME_03", DictCodeListExt1.基准类型_2_收益率曲线.getCodeName());
        param.put("BENCHMARK_TYPE_NAME_04", DictCodeListExt1.基准类型_2_利率.getCodeName());
        
        param.put("SECRATE_DATA", DictCode.利率行情.getValue());
        param.put("MARKET_ID", DictCode.曲线指数.getValue());
        param.put("CURVE_CODE", DictCode.曲线代码.getValue());
        
	    Page<SelectOptionDto> resultPage = this.queryForPageForSelectReturn(page, length,
	            () -> productSimulationTrialMapper.getIndexIdSource(param));    
        return resultPage;
	}
	
}
