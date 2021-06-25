/*
 * 文件名：EtrTradePlaceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etribbondinfo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.basicdata.dto.BusiFinProductRatingDto;
import com.joyintech.tams.component.business.basicdata.service.BusiProductRatingService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etribbondinfo.dto.EtrFinProductDto;
import com.joyintech.tams.invest.entrust.etribbondinfo.dto.EtrIbBondinfo001Dto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrIbBondinfo001Service
 * @since 1.0
 */
@Service
public class EtrIbBondinfo001Service extends CommonExportService<EtrIbBondinfo001Dto> {
    /**
     * 市场行情接口地址
     */
/*    @Value("${MarketInfoInterf.BaseUrl}")
    private String MarketInfoInterf_BaseUrl;*/

	/**
	 * 外汇交易中心行情接口地址
	 */
	@Value("${MarketInfoInterf.cmdsUrl}")
	private String MarketInfoInterf_cmdsUrl;


	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 产品评级共通
	 */
	@Autowired(required = false)
	private BusiProductRatingService busiProductRatingService;

	@Autowired
	private EtrCommonService etrCommonService;

	@Autowired
	private EtribBondInfo003Service etribBondInfo003Service;

    /**
     * 分页查询产品持仓信息
     * <功能详细描述>
     * @param param 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
	public Page<EtrIbBondinfo001Dto> findPositionsPage(Map<String, Object> param) {
		try {
			String userId = SecurityUtil.getLoginUser().getUserName();
			param.put("MarketInfoInterf_cmdsUrl", MarketInfoInterf_cmdsUrl);
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				return restTemplate.postForPage(
						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo001/findPositionsPage", param,
						EtrIbBondinfo001Dto.class);
			}
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

	/**
	 * 分页查询债券质押持仓信息
	 * 
	 * @param param
	 *            查询参数
	 * @return 分页结果
	 * @see
	 * @since 1.0
	 */
	public Page<EtrIbBondinfo001Dto> findZqzyPositionsPage(Map<String, Object> param) {
		try {
			String userId = SecurityUtil.getLoginUser().getUserName();
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				return restTemplate.postForPage(
						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo001/findZqzyPositionsPage", param,
						EtrIbBondinfo001Dto.class);
			}
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		if (exportParam.getSelectParam().get("isDvp") != null && Boolean.parseBoolean((String) exportParam.getSelectParam().get("isDvp"))) {
			exportParam.config("/com/joyintech/tams/invest/entrust/etribbondinfo/config/export_conf1.xml").dataSource(
					DataSourceType.MASERT);
		}
		else {
			exportParam.config("/com/joyintech/tams/invest/entrust/etribbondinfo/config/export_conf2.xml").dataSource(DataSourceType.MASERT);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<EtrIbBondinfo001Dto> selectPageData(Map<String, Object> selectParam) {
		if (selectParam.get("isDvp") != null && Boolean.parseBoolean((String) selectParam.get("isDvp"))) {
			return findPositionsPage(selectParam);
		}
		else {
			return findZqzyPositionsPage(selectParam);
		}
	}

	/**
	 * 债券信息
	 * @param paramMap
	 * @return
	 */
	public EtrFinProductDto findSecinfo(Map<String, Object> paramMap) {
		EtrFinProductDto dto = restTemplate.postForObject(
					ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo001/getEtrFinProductInfo", paramMap,
					EtrFinProductDto.class);
		if (dto != null) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("oCode", dto.getIssuer());
			param.put("oType", DictCodeList.评级类型_主体评级.getValue());
			//查询评级类型为主体评级的数据
			List<BusiFinProductRatingDto> ztList = busiProductRatingService.getZtBestRatingByOrgId(param);
			if(ztList != null && ztList.size()>0){
				//只取第一条数据
				dto.setZtGrade(SysDataUtil.getNameFromDataDict(DictCode.评级结果, ztList.get(0).getGradeResult()));
			}
			Map<String,Object> zxparam = new HashMap<String,Object>();
			zxparam.put("oCode", dto.getSecId());
			zxparam.put("oType", DictCodeList.评级类型_债项评级.getValue());
			//查询评级类型是债项评级的数据
			List<BusiFinProductRatingDto> zxList = busiProductRatingService.getZxBestRatingByMarketId(zxparam);
			if(zxList != null && zxList.size()>0){
				//只取第一条数据
				dto.setZxGrade(SysDataUtil.getNameFromDataDict(DictCode.评级结果, zxList.get(0).getGradeResult()));
			}
			//下一付息日若无，则显示为“—”
			if (StringUtil.isEmpty(dto.getLastPaydate())) {
				dto.setLastPaydate("--");
			}
			//行权日若无，则显示为“—”
			if (StringUtil.isEmpty(dto.getXqdate())) {
				dto.setXqdate("--");
			}
			if (StringUtil.isEmpty(dto.getXqFlg())) {
				dto.setXqFlg("无");
			}
		}
		return dto;
	}

	/**
	 * 持仓信息
	 * @param paramMap
	 * @return
	 */
	public EtrFinProductDto findPositionInfo(Map<String, Object> paramMap) {
		EtrFinProductDto dto = new EtrFinProductDto();
		//银行间日历
		paramMap.put("calId", "03");
		Map<String, Object> mapRst = etribBondInfo003Service.getPortfolioAllocationValues(paramMap);
		dto.setZhkyxj_t(mapRst.get("proAvailableCash") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("proAvailableCash"));
		dto.setZhkyxj_t1(mapRst.get("proAvailableCashT1") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("proAvailableCashT1"));
		dto.setTghkyxj_t(mapRst.get("secManageAvailablePosition") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("secManageAvailablePosition"));
		dto.setTghkyxj_t1(mapRst.get("secManageAvailablePositionT1") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("secManageAvailablePositionT1"));
		dto.setTdkyxj_t(mapRst.get("channelAvailable") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("channelAvailable"));
		dto.setTdkyxj_t1(mapRst.get("channelAvailableT1") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("channelAvailableT1"));
		dto.setZccc_qmze(mapRst.get("assetPositionEntrustAmt") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("assetPositionEntrustAmt"));
		dto.setZcky_qmze(mapRst.get("assetAvailableEntrustAmt") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("assetAvailableEntrustAmt"));

		return dto;
	}

	public Page<SelectOptionDto> queryAssetCode(Map<String, Object> param) {
		return restTemplate.postForPage(
				ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/queryAssetCode", param,
				SelectOptionDto.class);
	}

	@Override
	public void setCellValue(String beanName, Cell cell, String propName, Object value) {
		if(StringUtil.equals(propName,"transactioncom")){
			cell.setCellValue(value+"%");
		}
		if("vdate".equals(propName) || "mdate".equals(propName)){
			if(value instanceof Date){
				cell.setCellValue(DateUtil.format((Date)value));
			}else if (value instanceof Long){
				cell.setCellValue(DateUtil.format(new Date((Long) value)));
			}
		}
	}

	/**
	 * 查询债券行情信息
	 * @param pageParam
	 * @return
	 */
	public Map<String, Object> findQuotations(Map<String, Object> pageParam) {
		String secCode = (String) pageParam.get("seccode");
		String url = MarketInfoInterf_cmdsUrl+"/rest/markinfo/sec/getMarkinfoBySecCode?seccode=" + secCode;
		return etrCommonService.getMarketInfo(url);
	}

}
