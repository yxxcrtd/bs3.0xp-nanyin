/*
 * 文件名：ValCoinFundQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valcoinfundquotationinfo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.basedata.marketdata.valcoinfundquotationinfo.dto.ValCoinFundQuotationInfo001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 基金类行情信息
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月3日
 * @see ValCoinFundQuotationInfo001Service
 * @since 1.0
 */
@Service
public class ValCoinFundQuotationInfo001Service extends CommonExportService<ValCoinFundQuotationInfo001Dto> 
    implements BaseProcessService<ValCoinFundQuotationInfo001Dto> {

    /**
     * 分页查询
     * 
     * @param pageParam 查询参数
     * @return page对象
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    public Page<ValCoinFundQuotationInfo001Dto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/selectForPage",
            pageParam, ValCoinFundQuotationInfo001Dto.class);
    }

    /**
     * 画面数据初期化
     * 
     * @param pageParam 查询参数
     * @return page对象
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    public List<ValCoinFundQuotationInfo001Dto> selectPageInfo(Map<String, Object> pageParam){
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/selectPageInfo", 
            pageParam, ValCoinFundQuotationInfo001Dto.class);
    }
    /**
     * 联想文本框service层实现
     * 
     * @param param 检索参数
     * @return SuggestDto
     */
    public List<SuggestDto> selectForCode(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/selectForCode",
            param, SuggestDto.class);
    }

    /**
     * data-url 方式的下拉框
     * 
     * @param map 检索参数
     * @return SuggestDto
     */
    public List<SuggestDto> getManagement(Map<String, Object> map) {
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/getManagement",
            map, SuggestDto.class);
    }

    /**
     * 联想下拉框基金代码
     * @param map 查询参数
     * @return List<FinprodSeleOptionDto>金融产品实体类集合
     * @see
     * @since 1.0
     */
    public List<SuggestDto> queryFinprod(Map<String, Object> map) {
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/queryFinprod",
            map, SuggestDto.class);
    }

    /**
     * 以基金代码获取金融产品信息集合
     * 
     * @param finprodId
     * @return List<ValCoinFundQuotationInfo001Dto>金融产品实体类集合
     * @see
     * @since 1.0
     *//*
       * public List<ValCoinFundQuotationInfo001Dto> getByFunCode(String finprodId){ return
       * mstCoinFundQuotationInfo001Mapper.getByfinprodId(finprodId); }
       */
    /**
     * 以基金代码获取基金类型和基金管理人名称
     * 
     * @param map 界面参数
     * @return ValCoinFundQuotationInfo001Dto金融产品实体类
     * @see
     * @since 1.0
     */
    public ValCoinFundQuotationInfo001Dto getManageAndType(Map<String,Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/getManageAndType",
            map, ValCoinFundQuotationInfo001Dto.class);
    }

    /**
     * 流程中间类 {@inheritDoc}
     */
    @Override
    public void doComplete(ValCoinFundQuotationInfo001Dto entity, OperationType type) {
        new HashMap<>();
        entity.setType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/doComplete",
            entity, Integer.class);
    }
    /**
     * 导出 {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/valuation/basedata/marketdata/valcoinfundquotationinfo/conf/export_conf.xml").dataSource(
                    DataSourceType.MASERT);
    }
    /**
     * 导出
     */
    @Override
    public Page<ValCoinFundQuotationInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("valDate".equals(propName) || "importDay".equals(propName) || "publishDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    /**
     * 通过估值日与ID查询
     * <功能详细描述>
     * @param finprodId id
     * @param valDate 估值日期
     * @return 结果
     * @see
     * @since 1.0
     */
    public String selectByIdAndValDate(String finprodId, String valDate) {
        Map<String,Object>map=new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("valDate", valDate);
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/selectByIdAndValDate",
            map, String.class);
    }
    
    public String getIplatDate(){
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValCoinFundQuotationInfo/getIplatDate", String.class);
    }
}
