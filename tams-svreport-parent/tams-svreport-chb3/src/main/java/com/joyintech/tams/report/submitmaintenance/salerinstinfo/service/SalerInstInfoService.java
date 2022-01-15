package com.joyintech.tams.report.submitmaintenance.salerinstinfo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.submitmaintenance.salerinstinfo.dto.SalerInstInfoDto;
import com.joyintech.tams.systemtools.dto.CommonAjaxResult;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalerInstInfoService extends CommonExportService<SalerInstInfoDto> {


    /**
     * 分页查询列表
     *
     * @return Page
     */
    public Page<SalerInstInfoDto> searchForPage(Map<String, Object> param) {
        return restTemplate.postForPage(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/findPage",param,
                SalerInstInfoDto.class);
    }

    /**
     * 双击查看或编辑
     * selectDetail
     */
    public SalerInstInfoDto selectDetail(String salerOrgCode,String erroOrInfo) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("salerOrgCode",salerOrgCode);
        param.put("erroOrInfo",erroOrInfo);
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/selectDetail", param,
                SalerInstInfoDto.class);
    }

    /**
     * 提交保存
     * @param dto
     * @return
     */
    public AjaxResult saveAndSubmit(SalerInstInfoDto dto){
        if(StringUtil.isEmpty(dto.getOrgCode())){
            dto.setOrgCode(CommonUtil.getOrgCode());
        }
        if(StringUtil.isEmpty(dto.getCreateDept())){
            dto.setCreateDept(CommonUtil.getDeptCode());
        }
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/saveAndSubmit", dto,
                SvrAjaxResult.class);
    }

    /**
     * 修改保存
     * @param dto
     * @return
     */
    public AjaxResult update(SalerInstInfoDto dto){
        if(StringUtil.isEmpty(dto.getOrgCode())){
            dto.setOrgCode(CommonUtil.getOrgCode());
        }
        if(StringUtil.isEmpty(dto.getCreateDept())){
            dto.setCreateDept(CommonUtil.getDeptCode());
        }
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/update", dto,
                SvrAjaxResult.class);
    }

    /**
     * 导出
     * @param params
     * @return
     * @throws Exception
     */
    public List<SalerInstInfoDto> queryAllList(Map<String, Object> params) throws Exception{
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/queryAllList",
                params, SalerInstInfoDto.class);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/checkConfirmMsgForImportData",
                param,
                CommonAjaxResult.class);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList(fileName));
        param.put("tempList", ExcelUtil.getTempList(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/importData",
                param,
                ExcelValidDto.class);
    }

    /**
     * 逻辑删除
     * <功能详细描述>
     * @param dto 待校验数据
     * @return jiaoyanjieguo
     * @see
     * @since 1.0
     */
    public AjaxResult deleteByPrimaryKey(SalerInstInfoDto dto){
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/deleteByPrimaryKey", dto,
                CommonAjaxResult.class);
    }

    /**
     * 查询发行机构代码
     * @param orgCode
     * @return
     */
    public String selectIssuePartyId(String orgCode) {
         return  restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SalerInstInfo/selectIssuePartyId",orgCode,
                String.class);
    }

    /**
     * 总部或分支机构编码联想框
     */
    public List<SuggestDto> getSalerOrgCode(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerInstInfo/getSalerOrgCode",
                map, SuggestDto.class);
    }
}
