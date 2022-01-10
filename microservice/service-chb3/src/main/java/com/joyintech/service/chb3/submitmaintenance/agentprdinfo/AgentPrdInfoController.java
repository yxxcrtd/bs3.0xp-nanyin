package com.joyintech.service.chb3.submitmaintenance.agentprdinfo;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dto.AgentPrdInfoDto;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.service.AgentPrdInfo001Service;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.service.AgentPrdInfo002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/AgentPrdInfo")
public class AgentPrdInfoController {

    @Autowired
    private AgentPrdInfo001Service agentPrdInfo001Service;

    @Autowired
    private AgentPrdInfo002Service agentPrdInfo002Service;

    /**
     * 分页查询列表
     *
     * @return Page
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<AgentPrdInfoDto> findPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length)
    {
        return agentPrdInfo001Service.selectForPage(page,length,pageParam);
    }

    /**
     *从info表取值设置页面
     * selectDetail
     */
    @PostMapping("selectDetail")
    public AgentPrdInfoDto selectDetail(@RequestBody Map<String, Object> param){
        return  agentPrdInfo002Service.selectDetail(param);
    }

    /**
     * 新建保存
     * @param dto
     */
    @PostMapping("saveAndSubmit")
    public void saveAndSubmit(@RequestBody AgentPrdInfoDto dto) {
        agentPrdInfo002Service.saveAndSubmit(dto);
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
    public List<AgentPrdInfoDto> queryAllList(@RequestBody Map<String, Object> params) throws Exception{
        return agentPrdInfo001Service.queryAllList(params);
    }

    /**
     *
     * 逻辑删除
     */
    @PostMapping("deleteByPrimaryKey")
    public void deleteByPrimaryKey(@RequestBody AgentPrdInfoDto dto) {
       agentPrdInfo001Service.deleteByPrimaryKey(dto);
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
        return agentPrdInfo001Service.checkConfirmMsgForImportData(dataList);
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
        return agentPrdInfo001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }

    /**
     * 查询发行机构代码
     * @param orgCode
     * @return
     */
    @PostMapping("selectIssuePartyId")
    public String selectIssuePartyId(@RequestBody String orgCode){
        return  agentPrdInfo002Service.selectIssuePartyId(orgCode);
    }

    /**
     * 代理销售机构代码下拉框
     */
    @PostMapping("getAgentInstitutionCode")
    public List<SuggestDto> getAgentInstitutionCode(@RequestBody Map<String, Object> map) {
        return agentPrdInfo001Service.getAgentInstitutionCode(map);
    }

    /**
     * 产品代码下拉框
     */
    @PostMapping("getProdId")
    public List<SuggestDto> getProdId(@RequestBody Map<String, Object> map) {
        return agentPrdInfo001Service.getProdId(map);
    }

    /**
     * 产品登记编码下拉框
     */
    @PostMapping("getProdRegistCode")
    public List<SuggestDto> getProdRegistCode(@RequestBody Map<String, Object> map) {
        return agentPrdInfo001Service.getProdRegistCode(map);
    }
}
