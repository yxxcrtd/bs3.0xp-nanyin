/*
 * 文件名：FinFundTargetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.fund.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.*;

import com.joyintech.tams.valuation.basicdata.asset.fund.dto.FinFundTargetDto;
import com.joyintech.tams.valuation.basicdata.base.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.asset.dto.BussFinIntPayDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 基金类标的信息service
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月10日
 * @see FinFundTargetService
 * @since 1.0
 */
@Service
public class FinFundTargetService extends CommonExportService<FinFundTargetDto> implements BaseProcessService<FinFundTargetDto> {
    /**
     * 投管接口共通组件
     */
//    @Autowired
//    private InvestInterfCommService investInterfCommService;
//    @Autowired
//    private CommonAutoCouponService commonAutoService;
    /**
     * 使用给定的sqlID检索分页数据。
     *  
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinFundTargetDto> selectForPage(Map<String, Object> pageParam)
        throws Exception {
        if (StringUtil.isNotEmpty(pageParam.get("trmFundType"))) {
            pageParam.put("trmFundType", StringUtil.splitToList(null, pageParam.get("trmFundType").toString()));
        }else{
            pageParam.remove("trmFundType");
        }
        // type9-11 created by wangguiqi on 2019/7/19 -end
        Page<FinFundTargetDto> page =  restTemplate.postForPage(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinFundTargetController/findPage",
            pageParam, FinFundTargetDto.class);
        return page;
    }

    public List<FinFundTargetDto> selectForList(Map<String, Object> pageParam)
            throws Exception {
        if (StringUtil.isNotEmpty(pageParam.get("trmFundType"))) {
            pageParam.put("trmFundType", StringUtil.splitToList(null, pageParam.get("trmFundType").toString()));
        }else{
            pageParam.remove("trmFundType");
        }
        List<FinFundTargetDto> list =  restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinFundTargetController/selectForList",
                pageParam, FinFundTargetDto.class);
        return list;
    }
    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketIdList(String param) {
        return restTemplate.getForList(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME +
                "/FinFundTargetController/getFinprodMarketIdList?finprodMarketId="+ param,
                    SuggestDto.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doInsert(FinFundTargetDto entity, OperationType type){
        //entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinFundTargetController/doInsert",
            entity, Integer.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinFundTargetDto entity){
        restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinFundTargetController/doInsert",
            entity, Integer.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinFundTargetDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinFundTargetController/" + action, entity,
            Integer.class);
    }
    /**
     * 修改/双击查看根据金融产品代码查询dto
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinFundTargetDto selectByFinprodId(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinFundTargetController/selectByFinprodId?finprodId="
                +finprodId,FinFundTargetDto.class);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    public FinFundTargetDto findEntity(Map<String,Object> map) {
        return 
            restTemplate.postForObject(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinFundTargetController/findEntity"
                ,map,FinFundTargetDto.class);
    }

    /**
     * 查询接口表资产信息
     * @param finprodMarketId 市场代码
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public FinFundTargetDto getInterfFundDto (String finprodMarketId) throws ParseException{
//        InfChinamutualfunddescriptionDto dto = new InfChinamutualfunddescriptionDto();
        List<String> list = new ArrayList<>();
        list.add(finprodMarketId);
//        dto.setfInfoWindcodeList(list);
//        dto = commonAutoService.autoFundCoupon(dto, finprodMarketId);
        
        BaseFinProductBaseInfoDto baseInfoDto  = new BaseFinProductBaseInfoDto();
//        if (StringUtil.isNotEmpty(dto)) {
//            baseInfoDto =  dto.getBaseFinProductBaseInfoList().get(0);
//        }
        //基金基本信息
        FinFundTargetDto fundTargetDto = new FinFundTargetDto();
        if (!StringUtil.isNotEmpty(baseInfoDto.getFinProduct())) {
            return null;
        }
        BeanUtil.copyPropertiesIgnoreNull(fundTargetDto, baseInfoDto.getFinProduct());
        //附属表信息
        fundTargetDto.setPurSpeed(StringUtil.numberToString(baseInfoDto.getFinProductAdd().getPurSpeed()));
        fundTargetDto.setIsCompoundInt(baseInfoDto.getFinProductAdd().getIsCompoundInt());
        //基金类型
        fundTargetDto.setFundType(baseInfoDto.getFinProductType().getType4());
        fundTargetDto.setSecondType(baseInfoDto.getFinProductType().getType5());
        fundTargetDto.setType9(baseInfoDto.getFinProductType().getType9());
        fundTargetDto.setType10(baseInfoDto.getFinProductType().getType10());
        if (StringUtil.equals(CacheUtil.getSysParam("IS_SHOW_TYPE"),"01")){
            fundTargetDto.setType11(baseInfoDto.getFinProductType().getType11());
            fundTargetDto.setType12(baseInfoDto.getFinProductType().getType12());
        }
        //分红计划
        BussFinIntPayDto payDto = new BussFinIntPayDto();
        BeanUtil.copyPropertiesIgnoreNull(payDto, baseInfoDto.getFinCashPayRuleList().get(0));
        fundTargetDto.setBussFinIntPayDto(payDto);
//        payDto.setPayCycle(baseInfoDto.getFinCashPayRuleList().get(0).getPayCycle());
        //中债
//        fundTargetDto.setRepfund(baseInfoDto.getRepfund());
//        fundTargetDto.setRepAssetDept(baseInfoDto.getRepAssetDept());
        return fundTargetDto;
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	String isShowType = (String) exportParam.getSelectParam().get("isShowType");
		if (StringUtil.equals(isShowType, "01")) {
			exportParam.config("/com/joyintech/tams/valuation/basicdata/asset/fund/conf/export_conf.xml")
					.dataSource(DataSourceType.MASERT);
		} else {
			exportParam.config("/com/joyintech/tams/valuation/basicdata/asset/fund/conf/export_conf002.xml")
					.dataSource(DataSourceType.MASERT);
		}
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinFundTargetDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getManager(Map<String,Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/ValAssetCommon/getManager",param,
                SelectOptionDto.class);
    }

    public List<SelectOptionDto> getTrmFundTypeList(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME +
                        "/FinFundTargetController/getTrmFundTypeList",param,SelectOptionDto.class);
    }


    /**
     * 通过上市流通代码查询Dto
     */
    public FinFundTargetDto selectBycirculationCode(String circulationCode) {
        return restTemplate.getForObject(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinFundTargetController/selectBycirculationCode?circulationCode="
                        +circulationCode,FinFundTargetDto.class);
    }

    /**
     * 查询交易日历信息
     * @param param
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getCalendarIdList(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME +
                        "/FinFundTargetController/getCalendarIdList",param,
                SelectOptionDto.class);
    }
}
