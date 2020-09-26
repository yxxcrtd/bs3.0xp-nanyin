package com.joyintech.tams.valuation.basedata.marketdata.valbondquotationinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.basedata.marketdata.valbondquotationinfo.dto.ValBondQuotationInfoDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * @author wangzehui
 */
@Service
public class ValBondQuotationInfoService extends CommonExportService<ValBondQuotationInfoDto> implements BaseProcessService<ValBondQuotationInfoDto> {

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<ValBondQuotationInfoDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondQuotationInfo/findPage",
            pageParam, ValBondQuotationInfoDto.class);
    }

    /**
     * 资产代码的联想下拉框
     * 
     * @param param param
     * @return List
     * @throws Exception 异常
     */
    public List<SuggestDto> getSecIdOption(String param) {
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME
                                       + "/ValBondQuotationInfo/getSecId?secId=" + param,
            SuggestDto.class);
    }

    /**
     * 查询债券简称
     * 
     * @return List
     */
    public List<SelectOptionDto> getFinprodAbbrOption(Map<String,Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                                       + "/ValBondQuotationInfo/getFinprodAbbrOption",param,
            SelectOptionDto.class);
    }

    /**
     * 删除
     * 
     * @param pageId 画面ID
     * @param dto ValBondQuotationInfoDto
     * @return 删除结果
     */
    public String delete(String pageId, ValBondQuotationInfoDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondQuotationInfo/{pageId}/delete",
            dto, String.class, pageId);
    }

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(ValBondQuotationInfoDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // entity.setSystemCode("SYS");
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
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondQuotationInfo/" + action, entity,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/valuation/basedata/marketdata/valbondquotationinfo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ValBondQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 获取债券简称
     * 
     * @param finprodAbbr 债券简称
     * @return List
     */
    public List<SuggestDto> getFinProdAbbr(String finprodAbbr) {
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME
                                       + "/ValBondQuotationInfo/getFinProdAbbr?finprodAbbr="
                                       + finprodAbbr,
            SuggestDto.class);
    }

    /**
     * @param secId secId
     * @return dto
     */
    public ValBondQuotationInfoDto getBySecId(String secId) {
        return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                         + "/ValBondQuotationInfo/getBySecId?secId=" + secId,
            ValBondQuotationInfoDto.class);
    }

    /**
     * @param secId secId
     * @param valueDate valueDate
     * @param valueSource valueSource
     * @return dto
     */
    public ValBondQuotationInfoDto selectByKey(String secId, String valueDate,String valueSource) {
        return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                         + "/ValBondQuotationInfo/selectByKey?secId="
                                         + secId + "&valueDate=" + valueDate+ "&valueSource=" + valueSource,
            ValBondQuotationInfoDto.class);
    }

    /**
     * 新建画面的资产代码查询
     * 
     * @param secId 债券代码
     * @return list
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getSecIdToNew(String secId) {
        return restTemplate.getForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondQuotationInfo/getSecIdToNew?secId="
                                                           + secId,
                                                           SuggestDto.class);
    }

    public List<SelectOptionDto> getAssetCodeList() {
        return restTemplate.getForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValBondQuotationInfo/getFinProdAbbr", 
            SelectOptionDto.class);
    }
    
}
