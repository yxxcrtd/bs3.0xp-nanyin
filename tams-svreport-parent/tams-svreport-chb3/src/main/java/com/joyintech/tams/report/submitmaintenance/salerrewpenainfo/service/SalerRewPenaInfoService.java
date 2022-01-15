package com.joyintech.tams.report.submitmaintenance.salerrewpenainfo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.submitmaintenance.salerrewpenainfo.dao.ChbSalerRewPenaInfoMapper;
import com.joyintech.tams.report.submitmaintenance.salerrewpenainfo.dto.SalerRewPenaInfoDto;
import com.joyintech.tams.systemtools.dto.CommonAjaxResult;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalerRewPenaInfoService extends CommonExportService<SalerRewPenaInfoDto> {

    @Autowired
    private ChbSalerRewPenaInfoMapper chbSalerRewPenaInfoMapper;

    /**
     * 分页查询列表
     *
     * @return Page
     */
    public Page<SalerRewPenaInfoDto> searchForPage(Map<String, Object> param) {
        return restTemplate.postForPage(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/findPage",param,
                SalerRewPenaInfoDto.class);
    }

    /**
     * 双击查看或编辑info
     * selectDetail
     */
    public SalerRewPenaInfoDto selectDetail(String salerRewPenaNo,String erroOrInfo) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("salerRewPenaNo",salerRewPenaNo);
        param.put("erroOrInfo",erroOrInfo);
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/selectDetail", param,
                SalerRewPenaInfoDto.class);
    }

    /**
     * 提交保存
     * @param dto
     * @return
     */
    public AjaxResult saveAndSubmit(SalerRewPenaInfoDto dto){
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/saveAndSubmit", dto,
                SvrAjaxResult.class);
    }

    /**
     * 修改保存
     * @param dto
     * @return
     */
    public AjaxResult update(SalerRewPenaInfoDto dto){
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/update", dto,
                SvrAjaxResult.class);
    }

    /**
     * 导出
     * @param params
     * @return
     * @throws Exception
     */
    public List<SalerRewPenaInfoDto> queryAllList(Map<String, Object> params) throws Exception{
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/queryAllList",
                params, SalerRewPenaInfoDto.class);
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
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/checkConfirmMsgForImportData",
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
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/importData",
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
    public AjaxResult deleteByPrimaryKey(SalerRewPenaInfoDto dto){
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/deleteByPrimaryKey", dto,
                CommonAjaxResult.class);
    }

    /**
     * 查询发行机构代码
     * @param orgCode
     * @return
     */
    public String selectIssuePartyId(String orgCode) {
         return  restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/SalerRewPenaInfo/selectIssuePartyId",orgCode,
                String.class);
    }

    /**
     * 内部编号联想框
     */
    public List<SuggestDto> getSalerInsNo(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/SalerRewPenaInfo/getSalerInsNo",
                map, SuggestDto.class);
    }

    /**
     * 内部编号下拉框
     */
    public List<SelectOptionDto> selectSalerInsNo(Map<String, Object> map) {
        List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
        dtoList = chbSalerRewPenaInfoMapper.selectSalerInsNo(map);
        return dtoList;
    }
}
