/*
 * 文件名：FinCreditProjectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.credit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.interf.dto.TradeClearDto;
import com.joyintech.tams.invest.interf.service.InterfComponentService;
import com.joyintech.tams.invest.interf.service.InterfComponentService.OrderCaller;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.invest.asset.credit.dto.InterfCreditItemInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 信贷项目信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月15日
 * @see FinCreditProjectService
 * @since 1.0
 */
@Service
public class FinCreditProjectService extends CommonExportService<InterfCreditItemInfoDto> 
{
	/**
	 * 接口业务组建
	 */
	@Autowired
	private InterfComponentService interfComponentService;
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<InterfCreditItemInfoDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinCreditProject/findPage",
            pageParam, InterfCreditItemInfoDto.class);
    }
    
    /**
     * 信贷项目撤销
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void update(InterfCreditItemInfoDto dto){
        restTemplate.postForEntity(ServiceApplication.ASSET_SERVICE_NAME + "/FinCreditProject/update", 
            dto, Integer.class);
        TradeClearDto tradeClearDto = new TradeClearDto();
        tradeClearDto.setOrderOrTradeNo(dto.getCashOutNo());
        tradeClearDto.setRemark(dto.getReason());
        List<TradeClearDto> list = new ArrayList<TradeClearDto>();
        list.add(tradeClearDto);
        interfComponentService.sendTradeClearRequest(OrderCaller.CMS003,list);
        
    }
    
    /**
     * 出账编号下拉文本框
     * <功能详细描述>
     * @param search
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCashOutNo(String search){
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME + "/FinCreditProject/getCashOutNo?search="+search, 
            SuggestDto.class);
    }
    
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/asset/credit/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<InterfCreditItemInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
}
