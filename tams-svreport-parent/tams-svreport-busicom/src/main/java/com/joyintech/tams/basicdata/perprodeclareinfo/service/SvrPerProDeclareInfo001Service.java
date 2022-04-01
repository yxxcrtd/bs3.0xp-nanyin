package com.joyintech.tams.basicdata.perprodeclareinfo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.basicdata.perprodeclareinfo.dto.SvrPerProDeclareInfo001Dto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.dto.CommonAjaxResult;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品申报登记信息 Service
 *
 * @author yxj
 * @date 1.0 2022年03月07日
 * @since 1.0
 */
@Service("SvrPerProDeclareInfo001Service")
public class SvrPerProDeclareInfo001Service extends CommonExportService<SvrPerProDeclareInfo001Dto> {

    /**
     * 分页查询
     * @param map
     * @return
     */
    public Page<SvrPerProDeclareInfo001Dto> selectForPage(Map<String, Object> map){
//        if (StringUtil.isNotEmpty(map.get("repFinprodName"))) {
//            map.put("repFinprodName", StringUtil.splitToList(null, map.get("repFinprodName").toString()));
//        }
//        if (StringUtil.isNotEmpty(map.get("repFinprodInsId"))) {
//            map.put("repFinprodInsId", StringUtil.splitToList(null, map.get("repFinprodInsId").toString()));
//        }
        Page<SvrPerProDeclareInfo001Dto> pageDto = restTemplate.postForPage(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SvrPerProDeclareInfo/selectForPage", map,
                SvrPerProDeclareInfo001Dto.class);
        return pageDto;
    }

    /**
     * 保存
     * @param dto
     * @return
     */
    public AjaxResult save(SvrPerProDeclareInfo001Dto dto) {
        return restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SvrPerProDeclareInfo/save", dto,
                SvrAjaxResult.class);
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    public AjaxResult update(SvrPerProDeclareInfo001Dto dto) {
        return restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SvrPerProDeclareInfo/update", dto,
                SvrAjaxResult.class);
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    public AjaxResult deleteByPrimaryKey(SvrPerProDeclareInfo001Dto dto) {
        return restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SvrPerProDeclareInfo/delete", dto,
                SvrAjaxResult.class);
    }

    /**
     * 双击查看详情
     * @param declareUuid
     * @return
     */
    public SvrPerProDeclareInfo001Dto selectDetail(String declareUuid) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("declareUuid",declareUuid);
        SvrPerProDeclareInfo001Dto entity = restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SvrPerProDeclareInfo/selectDetail", param,
                SvrPerProDeclareInfo001Dto.class);
        return entity;
    }

    /**
     * 联想下拉框
     * @param param
     * @return
     */
    public List<SuggestDto> queryProdInfo(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SvrPerProDeclareInfo/queryProdInfo", param,
                SuggestDto.class);
    }

    /**
     * 查询所有数据
     * @param params
     * @return
     */
    public List<SvrPerProDeclareInfo001Dto> queryAllList(Map<String, Object> params) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SvrPerProDeclareInfo/queryAllList", params,
                SvrPerProDeclareInfo001Dto.class);
    }

    /**
     * 导入前的数据检查
     * @param fileName
     * @return
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SvrPerProDeclareInfo/checkConfirmMsgForImportData",
                param, CommonAjaxResult.class);
    }

    /**
     * 导入文件
     * @param fileName
     * @return
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList(fileName));
        param.put("tempList", ExcelUtil.getTempList(fileName));
//        param.put("userName", CommonUtil.getUserName());
//        param.put("orgCode", CommonUtil.getOrgCode());
//        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SvrPerProDeclareInfo/importData",
                param,
                ExcelValidDto.class);
    }

    /**
     * 批量删除
     * @param param
     * @return
     */
    public AjaxResult deleteList(Map<String, Object> param) {
        return restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SvrPerProDeclareInfo/deleteList", param,
                SvrAjaxResult.class);
    }
}
