package com.joyintech.service.chb3.submitmaintenance.agentinstitution;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.dto.AgentInstitutionDto;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.service.AgentInstitution001Service;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.service.AgentInstitution002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/AgentInstitution")
public class AgentInstitutionController {

    @Autowired
    private AgentInstitution001Service agentInstitution001Service;

    @Autowired
    private AgentInstitution002Service agentInstitution002Service;

    /**
     * 分页查询列表
     *
     * @return Page
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<AgentInstitutionDto> findPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length)
    {
        return agentInstitution001Service.selectForPage(page,length,pageParam);
    }

    /**
     *从info表取值设置页面
     * selectDetail
     */
    @PostMapping("selectDetail")
    public AgentInstitutionDto selectDetail(@RequestBody Map<String, Object> param){
        return  agentInstitution002Service.selectDetail(param);
    }

    /**
     * 新建保存
     * @param dto
     */
    @PostMapping("saveAndSubmit")
    public void saveAndSubmit(@RequestBody AgentInstitutionDto dto) {
        agentInstitution002Service.saveAndSubmit(dto);
    }

    /**
     * 修改保存
     * @param dto
     */
    @PostMapping("update")
    public void update(@RequestBody AgentInstitutionDto dto) {
        agentInstitution002Service.update(dto);
    }

    /**
     * 导出数据查询
     *
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @PostMapping("/queryAllList")
    public List<AgentInstitutionDto> queryAllList(@RequestBody Map<String, Object> params) throws Exception{
        return agentInstitution001Service.queryAllList(params);
    }

    /**
     *
     * 逻辑删除
     */
    @PostMapping("deleteByPrimaryKey")
    public void deleteByPrimaryKey(@RequestBody AgentInstitutionDto dto) {
        agentInstitution001Service.deleteByPrimaryKey(dto);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param param 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return agentInstitution001Service.checkConfirmMsgForImportData(dataList);
    }

    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return agentInstitution001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }

    /**
     * 查询发行机构代码
     * @param orgCode
     * @return
     */
    @PostMapping("selectIssuePartyId")
    public String selectIssuePartyId(@RequestBody String orgCode){
        return  agentInstitution002Service.selectIssuePartyId(orgCode);
    }

    /**
     * 代理销售机构代码下拉框
     */
    @PostMapping("getAgentInstitutionCode")
    public List<SuggestDto> getAgentInstitutionCode(@RequestBody Map<String, Object> map) {
        return agentInstitution001Service.getAgentInstitutionCode(map);
    }
}
