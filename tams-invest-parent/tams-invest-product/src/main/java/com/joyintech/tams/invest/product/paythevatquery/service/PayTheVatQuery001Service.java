/*
 * 文件名：TaxBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.paythevatquery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.paythevatquery.dao.PayTheVatQuery001Mapper;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery001Dto;;

/**
 * 纳税增值税查询
 * 
 * @author chufengjie
 * @version 1.0 2019年02月23日
 * @see PayTheVatQuery001Dto
 * @since 1.0
 */
@Service("FinproductPayTheVatQuery001Service")
public class PayTheVatQuery001Service extends CommonExportService<PayTheVatQuery001Dto> {

	@Autowired
	private PayTheVatQuery001Mapper payTheVatQuery001Mapper;

	
    /**
     * 产品代码下拉框
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> param)
        throws Exception {
        //收益型产品 下拉选项过滤净值型产品 lijun add SHBS-518
        //param.put("PROFIT_TYPE", BussinessDictCodeList.收益类型_预期收益.getValue());
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductDurDeal/queryProdIdInfo", param,
            SelectOptionDto.class);
    }
    
    /**
     * 通道代码下拉框
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    public Page<SelectOptionDto> queryChannelIdInfo(Map<String, Object> param)
        throws Exception {
        
        param.put("finprodType2_F28", BussinessDictCodeList.金融产品类型_投管用_通道.getValue());
        return super.queryForPageForSelectReturn(() -> payTheVatQuery001Mapper.queryChannelIdInfo(param));
    }
	/**
	 * 根据条件分页查询
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Page<PayTheVatQuery001Dto> selectForPage(Map<String, Object> params) throws Exception {

		// 产品代码多选
		if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
			String booksetId = (String) params.get("portfolioId");
			String[] portfolioIdArr = null;
			portfolioIdArr = booksetId.split(",");
			params.put("portfolioIdArr", portfolioIdArr);
		}
		if(StringUtil.isNotEmpty(params.get("vatDateBegin"))){
			params.put("vatDateBegin",DateUtil.parse((String) params.get("vatDateBegin")));
		}
		if(StringUtil.isNotEmpty(params.get("vatDateEnd"))){
			params.put("vatDateEnd",DateUtil.parse((String) params.get("vatDateEnd")));
		}
		
		//导出时，有选择项的时候导出选择项
		if(StringUtil.isNotEmpty(params.get("datas"))){
			List<String> tradeIdList = new ArrayList<String>();
			List<PayTheVatQuery001Dto> list = JsonUtils.json2List((String)params.get("datas"),PayTheVatQuery001Dto.class);
			for(PayTheVatQuery001Dto dto:list) {
				tradeIdList.add(dto.getTradeId());
			}
			params.put("tradeIdList",tradeIdList);
		}
		
		params.put("taxClass_01", DictCodeListExt3.增值税品种_产品增值税.getValue());
		params.put("taxClass_02", DictCodeListExt3.增值税品种_通道增值税.getValue());
//		params.put("TAX0102", BussinessDictCodeList.税类型_利息收入.getValue());
		params.put("TAX0102", BussinessDictCodeList.税类型_利息收入.getValue());
		params.put("TAX0202", BussinessDictCodeList.税类型_红利收入.getValue());
		params.put("TAX0302", BussinessDictCodeList.税类型_价差收入.getValue());
		params.put("ALT0102", BussinessDictCodeList.税类型_城建税.getValue());
		params.put("ALT0202", BussinessDictCodeList.税类型_教育费.getValue());
		params.put("ALT0302", BussinessDictCodeList.税类型_地方教育费.getValue());
		params.put("ALT0402", BussinessDictCodeList.税类型_水利基金税.getValue());
		params.put("busiType16", BussinessDictCodeList.业务类型_增值税交易.getValue());
		params.put("finprodType", DictCodeListExt1.金融产品类型_税额.getValue());
		params.put("finprodType2", BussinessDictCodeList.金融产品类型_投管用_税额.getValue());
		params.put("finprodType2_F28", BussinessDictCodeList.金融产品类型_投管用_通道.getValue());
		params.put("ps01", BussinessDictCodeList.收支方向_收入.getValue());
//		params.put("tradeStatus01", BussinessDictCodeList.有效状态_有效.getValue());
//		params.put("tradeStatus03", BussinessDictCodeList.有效状态_撤销.getValue());
		Page<PayTheVatQuery001Dto> page = super.queryForPage(() -> payTheVatQuery001Mapper.selectList(params));
		
		return page;
	}

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	@Override
    public Page<PayTheVatQuery001Dto> selectPageData(Map<String, Object> selectParam) {
        try {
			return selectForPage(selectParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	/**
	 * 根据条件分页查询
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<PayTheVatQuery001Dto> findByList(Map<String, Object> params) throws Exception {

		// 产品代码多选
		if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
			String booksetId = (String) params.get("portfolioId");
			String[] portfolioIdArr = null;
			portfolioIdArr = booksetId.split(",");
			params.put("portfolioIdArr", portfolioIdArr);
		}
		if(StringUtil.isNotEmpty(params.get("vatDateBegin"))){
			params.put("vatDateBegin",DateUtil.parse((String) params.get("vatDateBegin")));
		}
		if(StringUtil.isNotEmpty(params.get("vatDateEnd"))){
			params.put("vatDateEnd",DateUtil.parse((String) params.get("vatDateEnd")));
		}
		params.put("TAX0102", BussinessDictCodeList.税类型_利息收入.getValue());
		params.put("TAX0202", BussinessDictCodeList.税类型_红利收入.getValue());
		params.put("TAX0302", BussinessDictCodeList.税类型_价差收入.getValue());
		params.put("ALT0102", BussinessDictCodeList.税类型_城建税.getValue());
		params.put("ALT0202", BussinessDictCodeList.税类型_教育费.getValue());
		params.put("ALT0302", BussinessDictCodeList.税类型_地方教育费.getValue());
		params.put("ALT0402", BussinessDictCodeList.税类型_水利基金税.getValue());
		params.put("busiType16", BussinessDictCodeList.业务类型_增值税交易.getValue());
		params.put("finprodType", DictCodeListExt1.金融产品类型_税额.getValue());
		params.put("finprodType2", BussinessDictCodeList.金融产品类型_投管用_税额.getValue());
		params.put("ps01", BussinessDictCodeList.收支方向_收入.getValue());
		params.put("tradeStatus01", BussinessDictCodeList.有效状态_有效.getValue());
		params.put("tradeStatus03", BussinessDictCodeList.有效状态_撤销.getValue());
		//增加通道增值税缴纳
		params.put("finprodType2_F28", BussinessDictCodeList.金融产品类型_投管用_通道.getValue());
		params.put("taxClass_01", DictCodeListExt3.增值税品种_产品增值税.getValue());
		params.put("taxClass_02", DictCodeListExt3.增值税品种_通道增值税.getValue());

		List<PayTheVatQuery001Dto> page = payTheVatQuery001Mapper.selectList(params);
		return page;
	}

	/**
	 * 下拉框分页获取产品
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<PayTheVatQuery001Dto> getFinprodId(String param) {
		return payTheVatQuery001Mapper.getBooksetId(param);
	}
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/paythevatquery/conf/export_paythevatquery001.xml").dataSource(
            DataSourceType.MASERT);
    }
    
    public List<SuggestDto> getBooksetId(Map<String, Object> param) {
    	 return restTemplate.postForList(com.joyintech.tams.valuation.common.constants.ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetId", param, SuggestDto.class);     
    }
    
    public Page<SelectOptionDto> selectPortfolioId(Map<String, Object> param){
    	
    	return super.queryForPageForSelectReturn(() -> payTheVatQuery001Mapper.selectPortfolioId(param)) ; 
    }
}
