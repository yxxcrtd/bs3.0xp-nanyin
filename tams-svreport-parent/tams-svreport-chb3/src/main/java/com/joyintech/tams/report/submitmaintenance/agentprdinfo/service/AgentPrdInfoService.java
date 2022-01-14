package com.joyintech.tams.report.submitmaintenance.agentprdinfo.service;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dao.ChbAgentPrdInfoMapper;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dto.AgentPrdInfoDto;
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
public class AgentPrdInfoService extends CommonExportService<AgentPrdInfoDto> {
    @Autowired
    private ChbAgentPrdInfoMapper chbAgentPrdInfoMapper;

    /**
     * 分页查询列表
     *
     * @return Page
     */
    public Page<AgentPrdInfoDto> searchForPage(Map<String, Object> param) {
        return restTemplate.postForPage(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/findPage",param,
                AgentPrdInfoDto.class);
    }

    /**
     * 双击查看或编辑info
     * selectDetail
     */
    public AgentPrdInfoDto selectDetail(String agentInstitutionCode,String prodRegistCode) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("agentInstitutionCode",agentInstitutionCode);
        param.put("prodRegistCode",prodRegistCode);
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/selectDetail", param,
                AgentPrdInfoDto.class);
    }

    /**
     * 提交保存
     * @param dto
     * @return
     */
    public AjaxResult saveAndSubmit(AgentPrdInfoDto dto){
        if(StringUtil.isEmpty(dto.getOrgCode())){
            dto.setOrgCode(CommonUtil.getOrgCode());
        }
        if(StringUtil.isEmpty(dto.getCreateDept())){
            dto.setCreateDept(CommonUtil.getDeptCode());
        }
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/saveAndSubmit", dto,
                SvrAjaxResult.class);
    }

    /**
     * 查询发行机构代码
     * @param orgCode
     * @return
     */
    public String selectIssuePartyId(String orgCode) {
         return  restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/AgentPrdInfo/selectIssuePartyId",orgCode,
                String.class);
    }

    /**
     * 导出
     * @param params
     * @return
     * @throws Exception
     */
    public List<AgentPrdInfoDto> queryAllList(Map<String, Object> params) throws Exception{
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/queryAllList",
                params, AgentPrdInfoDto.class);
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
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/checkConfirmMsgForImportData",
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
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/importData",
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
    public AjaxResult deleteByPrimaryKey(AgentPrdInfoDto dto){
        return restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/deleteByPrimaryKey", dto,
                CommonAjaxResult.class);
    }
    
    /**
     * 代理销售机构代码下拉框
     */
    public List<SuggestDto> getAgentInstitutionCode(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/getAgentInstitutionCode",
                map, SuggestDto.class);
    }

    /**
     * 产品代码下拉框
     */
    public List<SuggestDto> getProdId(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/getProdId",
                map, SuggestDto.class);
    }

    /**
     * 产品登记编码下拉框
     */
    public List<SuggestDto> getProdRegistCode(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/AgentPrdInfo/getProdRegistCode",
                map, SuggestDto.class);
    }

    /**
     * 代理销售机构代码下拉框（新建）
     */
    public List<SelectOptionDto> selectAgentInstitutionCode(Map<String, Object> map) {
        List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
        dtoList = chbAgentPrdInfoMapper.selectAgentInstitutionCode(map);
        return dtoList;
    }

    /**
     * 产品登记编码下拉框(新建)
     */
    public List<SelectOptionDto> selectProdRegistCode(Map<String, Object> map) {
        List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
        dtoList = chbAgentPrdInfoMapper.selectProdRegistCode(map);
        return dtoList;
    }

}
