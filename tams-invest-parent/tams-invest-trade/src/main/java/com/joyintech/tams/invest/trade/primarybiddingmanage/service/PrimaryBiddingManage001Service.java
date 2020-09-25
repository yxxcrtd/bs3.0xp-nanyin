/*
 * 文件名：TrdPlanNetInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.primarybiddingmanage.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2019年05月08日
 * @see PrimaryBiddingManage001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class PrimaryBiddingManage001Service extends CommonExportService<PrimaryBiddingManageDto>{

	/**
     * 检索债券一级投标指令信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
	public Page<PrimaryBiddingManageDto> findPage(Map<String, Object> pageParam) {
		pageParam.put("loginUserName", LoginUserUtil.getUserName());
		return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage001/findPage",
	            pageParam,PrimaryBiddingManageDto.class);
	}

	public List<PrimaryBiddingManageDto> findDataByFinprodIdAndCounterId(Map<String, Object> mapParam){
		mapParam.put("trader", SecurityUtil.getLoginUser().getUserName());
		return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage001/findDataByFinprodIdAndCounterId",
				mapParam,PrimaryBiddingManageDto.class);
	}

	/**
     * 导出 
     */
     @Override
     protected void buildExportParam(ExportParam exportParam) {
         exportParam.config(
                 "/com/joyintech/tams/invest/trade/primarybiddingmanage/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
     }
     
     /**
      * {@inheritDoc}
      */
     @Override
     public Page<PrimaryBiddingManageDto> selectPageData(Map<String, Object> selectParam) {
         return findPage(selectParam);
     }

	/**
	 * 截标
	 * @param dto dto
	 * @return
	 */
	public String closeOrCancelBid(PrimaryBiddingManageDto dto){
		return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/PrimaryBiddingManage001/closeOrCancelBid",
				dto, String.class);
	}
}
