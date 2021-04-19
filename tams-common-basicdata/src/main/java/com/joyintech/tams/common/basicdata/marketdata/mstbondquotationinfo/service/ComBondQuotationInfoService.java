package com.joyintech.tams.common.basicdata.marketdata.mstbondquotationinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.mstbondquotationinfo.dto.ComBondQuotationInfoDto;
import com.joyintech.tams.common.basicdata.util.ComBasicDataExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @author wangzehui
 */
@Service
public class ComBondQuotationInfoService extends CommonExportService<ComBondQuotationInfoDto> implements BaseProcessService<ComBondQuotationInfoDto> {

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<ComBondQuotationInfoDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.CommonBasicDataService + "/ComBondQuotationInfo/findPage",
            pageParam, ComBondQuotationInfoDto.class);
    }

    /**
     * 资产代码的联想下拉框
     * 
     * @param param param
     * @return List
     * @throws Exception 异常
     */
    public List<SuggestDto> getSecIdOption(String param) {
        return restTemplate.getForList(ServiceApplication.CommonBasicDataService
                                       + "/ComBondQuotationInfo/getSecId?secId=" + param,
            SuggestDto.class);
    }

    /**
     * 查询债券简称
     * 
     * @return List
     */
    public List<SelectOptionDto> getFinprodAbbrOption(Map<String,Object> param) {
        return restTemplate.postForList(ServiceApplication.CommonBasicDataService
                                       + "/ComBondQuotationInfo/getFinprodAbbrOption",param,
            SelectOptionDto.class);
    }

    /**
     * 删除
     * 
     * @param pageId 画面ID
     * @param dto MstBondQuotationInfoDto
     * @return 删除结果
     */
    public String delete(String pageId, ComBondQuotationInfoDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.CommonBasicDataService + "/ComBondQuotationInfo/{pageId}/delete",
            dto, String.class, pageId);
    }

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(ComBondQuotationInfoDto entity, OperationType type) {
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
            ServiceApplication.CommonBasicDataService + "/ComBondQuotationInfo/" + action, entity,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/common/basicdata/marketdata/mstbondquotationinfo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ComBondQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 获取债券简称
     * 
     * @param finprodAbbr 债券简称
     * @return List
     */
    public List<SuggestDto> getFinProdAbbr(String finprodAbbr) {
        return restTemplate.getForList(ServiceApplication.CommonBasicDataService
                                       + "/ComBondQuotationInfo/getFinProdAbbr?finprodAbbr="
                                       + finprodAbbr,
            SuggestDto.class);
    }

    /**
     * @param secId secId
     * @return dto
     */
    public ComBondQuotationInfoDto getBySecId(String secId) {
        return restTemplate.getForObject(ServiceApplication.CommonBasicDataService
                                         + "/ComBondQuotationInfo/getBySecId?secId=" + secId,
            ComBondQuotationInfoDto.class);
    }

    /**
     * @param secId secId
     * @param valueDate valueDate
     * @param valueSource valueSource
     * @return dto
     */
    public ComBondQuotationInfoDto selectByKey(String secId, String valueDate, String valueSource) {
        return restTemplate.getForObject(ServiceApplication.CommonBasicDataService
                                         + "/ComBondQuotationInfo/selectByKey?secId="
                                         + secId + "&valueDate=" + valueDate+ "&valueSource=" + valueSource,
            ComBondQuotationInfoDto.class);
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
            ServiceApplication.CommonBasicDataService + "/ComBondQuotationInfo/getSecIdToNew?secId="
                                                           + secId,
                                                           SuggestDto.class);
    }

    public List<SelectOptionDto> getAssetCodeList() {
        return restTemplate.getForList(
            ServiceApplication.CommonBasicDataService + "/ComBondQuotationInfo/getFinProdAbbr",
            SelectOptionDto.class);
    }
    
    
  //hsgexinghua   add by zhangxing1 20200220 start

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ComBasicDataExcelUtil.getDataListLocal(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                ServiceApplication.CommonBasicDataService + "/ComBondQuotationInfo/checkConfirmMsgForImportData",
                param,
                AjaxResult.class);
    }



    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ComBasicDataExcelUtil.getDataListLocal(fileName));
        param.put("tempList", ComBasicDataExcelUtil.getTempListLocal(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComBondQuotationInfo/importData",
                param,
                ExcelValidDto.class);
    }
    
}
