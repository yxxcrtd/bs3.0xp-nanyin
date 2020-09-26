/*
 * 文件名：ValNetAssetQuotationInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valnetassetquotationinfo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.basedata.marketdata.valnetassetquotationinfo.dto.ValNetAssetQuotationInfo001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 计划类（净值型）资产估值行情信息查询service
 * 
 * @author liuwei
 * @version 1.0 2018年8月13日
 * @see ValNetAssetQuotationInfo001Service
 * @since 1.0
 */

@Service
@Transactional
public class ValNetAssetQuotationInfo001Service extends CommonExportService<ValNetAssetQuotationInfo001Dto> 
    implements BaseProcessService<ValNetAssetQuotationInfo001Dto> {

    /**
     * 查询按钮点击事件 通过条件查询数据
     * 
     * @param pageParam 页面参数
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ValNetAssetQuotationInfo001Dto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        pageParam.put("PROFITTYPE_VALUE_03", DictCodeList.收益类型_货币型.getValue());
        pageParam.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetAssetQuotationInfo/findPage", pageParam,
            ValNetAssetQuotationInfo001Dto.class);
    }

    /**
     * 资产名称联想文本框 可以通过输入的资产名称模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetName(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetAssetQuotationInfo/getAssetName", map,
            SuggestDto.class);
    }

    /**
     * 查询界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetCode(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetAssetQuotationInfo/getAssetCode", map,
            SuggestDto.class);
    }

    /**
     * 新建界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssetCodeList(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetAssetQuotationInfo/getAssetCodeList", map,
            SelectOptionDto.class);
    }

    /**
     * 通过资产代码查询数据 完成新建、编辑界面和双击查看界面初始化
     * 
     * @param param 资产代码
     * @return dto
     * @see
     * @since 1.0
     */
    public ValNetAssetQuotationInfo001Dto selectMstNetAsset(Map<String, Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetAssetQuotationInfo/selectMstNetAsset",
            param, ValNetAssetQuotationInfo001Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValNetAssetQuotationInfo001Dto entity, OperationType type) {
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
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetAssetQuotationInfo/" + action, entity,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> param = exportParam.getSelectParam();
        param.put("PROFITTYPE_VALUE_01", DictCodeList.收益类型_净值型.getValue());
        param.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        exportParam.config(
            "/com/joyintech/tams/valuation/basedata/marketdata/valnetassetquotationinfo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("valDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    @Override
    public Page<ValNetAssetQuotationInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 通过资产代码和估值日期查询数据 查询插入数据的主键是否与数据库中的相同
     * 
     * @param finprodId 金融产品代码
     * @param valDate 估值日期
     * @return dto
     * @see
     * @since 1.0
     *//*
       * public ValNetAssetQuotationInfo001Dto selectByIdAndDate(String finprodId, String valDate)
       * { return mstnetassetquotationinfo001mapper.selectByIdAndDate(finprodId,valDate); }
       */

    /**
     * 通过资产代码查询资产名称和管理人 完成资产代码画面联动
     * 
     * @param map 查询参数
     * @return dto
     * @see
     * @since 1.0
     */
    public ValNetAssetQuotationInfo001Dto selectNameAndFundManager(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/ValNetAssetQuotationInfo/selectNameAndFundManager",
            map, ValNetAssetQuotationInfo001Dto.class);
    }

    /**
     * 
     * 检验validate
     * <功能详细描述>
     * @param map 金融产品代码和估值日
     * @return dto
     * @see
     * @since 1.0
     */
    public ValNetAssetQuotationInfo001Dto selectByIdAndDate(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                       + "/ValNetAssetQuotationInfo/selectByIdAndDate",
            map,ValNetAssetQuotationInfo001Dto.class);
    }
}
