/*
 * 文件名：EtrTradePlaceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etribrepo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.basicdata.dto.BusiFinProductRatingDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etribbondinfo.service.EtribBondInfo003Service;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrFinProductDto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepo001Dto;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond001Dto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrIbRepo001Service
 * @since 1.0
 */
@Service
public class EtrIbRepo001Service extends CommonExportService<EtrIbRepo001Dto> {
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
	
	@Autowired
	private EtribRepoInfo003Service etribRepoInfo003Service;

	@Autowired
	private EtribBondInfo003Service etribBondInfo003Service;

	/**
	 * 公共服务类
	 */
	@Autowired
	private EtrCommonService etrCommonService;

	
	private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 分页查询产品持仓信息
     * <功能详细描述>
     * @param param 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
	public Page<EtrIbRepo001Dto> findZlglPage(Map<String, Object> param) {
		try {
			String userId = SecurityUtil.getLoginUser().getUserName();
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				return restTemplate.postForPage(
						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBoutRepo001/findZlglPage", param,
						EtrIbRepo001Dto.class);
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
	public Page<EtrIbRepo001Dto> findZlglPositionPage(Map<String, Object> param) {
		try {
			String userId = SecurityUtil.getLoginUser().getUserName();
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				return restTemplate.postForPage(
						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBoutRepo001/findZlglPositionPage", param,
						EtrIbRepo001Dto.class);
			}
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		if (exportParam.getSelectParam().get("isDvp") != null && Boolean.parseBoolean((String) exportParam.getSelectParam().get("isDvp"))) {
			exportParam.config("/com/joyintech/tams/invest/entrust/etribrepo/config/export_conf1.xml").dataSource(
					DataSourceType.MASERT);
		}
		else {
			exportParam.config("/com/joyintech/tams/invest/entrust/etribrepo/config/export_conf2.xml").dataSource(DataSourceType.MASERT);
		}
	}
	
	/**
     * 设置日期格式
     * {@inheritDoc}
     */
//    @Override
//    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
//        if(("vdate".equals(propName)) && value != null){
//            cell.setCellValue(DateUtil.format((Date)value));
//        }
//        if(("mdate".equals(propName)) && value != null){
//            cell.setCellValue(DateUtil.format((Date)value));
//        }
//    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<EtrIbRepo001Dto> selectPageData(Map<String, Object> selectParam) {
		if (selectParam.get("isDvp") != null && Boolean.parseBoolean((String) selectParam.get("isDvp"))) {
			return findZlglPage(selectParam);
		}
		else {
			return findZlglPositionPage(selectParam);
		}
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
//		dto.setZccc_qmze(mapRst.get("assetPositionEntrustAmt") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("assetPositionEntrustAmt"));
//		dto.setZcky_qmze(mapRst.get("assetAvailableEntrustAmt") == null ? BigDecimal.ZERO : (BigDecimal) mapRst.get("assetAvailableEntrustAmt"));

		return dto;
	}

	public Page<SelectOptionDto> getSecManageAcctId(Map<String, Object> param) {
		return restTemplate.postForPage(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepo001/getSecManageAcctId", param,
				SelectOptionDto.class);
	}
	
	/**
	 * 回购信息
	 * @param paramMap
	 * @return
	 */
	public EtrFinProductDto findhginfo(Map<String, Object> paramMap) {
		EtrFinProductDto dto = restTemplate.postForObject(
					ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepo001/getEtrFinProductInfo", paramMap,
					EtrFinProductDto.class);
		return null;
	}

	
    /**
    * 查询登录用户是否满足指定的角色岗位 
    */
   public Long selectUserRolePosList(Map<String, Object> param){
       param.put("userId", SecurityUtil.getLoginUser().getUserName());
       return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/selectUserRolePosList",
           param, Long.class);
   }
   
   // 出库操作
   public Long outSave(EtrIbRepo001Dto dto) {
       Long insertCount = 0L;
       if (!StringUtil.isEmptyList(dto.getSelectedItem())) {
           insertCount += restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepo001/outSave",
                   dto, Long.class);
       }
       return insertCount;
   }

   /**
    * 查询质押式回购实时行情
    * @param pageParam
    * @return
    */
	public Map<String, Object> findQuotations(Map<String, Object> pageParam) {
		String termType = (String) pageParam.get("termType");
		String MarketType = (String) pageParam.get("MarketType");
		String url = MarketInfoInterf_cmdsUrl+"/rest/markinfo/getRepoMarkinfo?termType=" + termType + "&MarketType=" + MarketType;
		return etrCommonService.getMarketInfo(url);
	}
}
