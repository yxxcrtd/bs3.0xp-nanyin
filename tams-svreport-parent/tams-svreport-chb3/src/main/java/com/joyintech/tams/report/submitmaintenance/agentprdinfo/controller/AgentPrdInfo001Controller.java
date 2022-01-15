package com.joyintech.tams.report.submitmaintenance.agentprdinfo.controller;

import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dao.ChbAgentPrdInfoMapper;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.service.AgentPrdInfoService;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dto.AgentPrdInfoDto;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.service.AgentPrdInfo001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("AgentPrdInfo001")
@RequestMapping("/report/submitmaintenance/agentprdinfo/AgentPrdInfo001")
public class AgentPrdInfo001Controller extends BaseController {

    /**
     * dao
     */
    @Autowired
    private AgentPrdInfo001Service agentPrdInfo001Service;
    @Autowired
    private AgentPrdInfoService agentPrdInfoService;
    @Autowired
    private ChbAgentPrdInfoMapper chbAgentPrdInfoMapper;
    /**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.代理销售机构信息数据状态);
        return super.init(model);
    }

    /**
     * 分页查询列表
     *
     * @return Page
     */
    @ResponseBody
    @RequestMapping(value = "findPage")
    public Page<AgentPrdInfoDto> findPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return agentPrdInfoService.searchForPage(param);
    }
    /**
     *check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid AgentPrdInfoDto AgentPrdInfoDto,
                            BindingResult br) {
        return commonCheck(br, AgentPrdInfoDto.getOperateType());
    }


    /**
     * 导出数据
     *
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> params= RequestUtil.getPageParam();
        List<AgentPrdInfoDto> listdto ;
        if (datas == null) {
            listdto = agentPrdInfoService.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, AgentPrdInfoDto.class);
        }
        File outFile = agentPrdInfoService.makeFile(
                "/com/joyintech/tams/report/submitmaintenance/agentprdinfo/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                "委托销售产品编码信息" + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(DateUtil.platDateFromCache())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return agentPrdInfoService.checkConfirmMsgForImportData(fileName);
    }


    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName) {
        return this.agentPrdInfoService.importData(fileName);
    }

    /**
     *
     * 逻辑删除
     * <功能详细描述>
     * @param dto 删除参数
     * @param br 删除参数
     * @return 删除返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) AgentPrdInfoDto dto, BindingResult br)
            throws Exception {
        if(!(dto.getSelectedItem().isEmpty())){
              agentPrdInfoService.deleteByPrimaryKey(dto);
              return new AjaxResult("删除成功");
        }
        return new AjaxResult("删除失败");
    }

    /**
     * 代理销售机构代码下拉框
     */
    @ResponseBody
    @RequestMapping("/getAgentInstitutionCode")
    public List<SuggestDto> getAgentInstitutionCode()
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("agentInstitutionCode", RequestUtil.getPageParam().get("agentInstitutionCode"));
        List<SuggestDto> temp = agentPrdInfoService.getAgentInstitutionCode(map);
        return temp;
    }

    /**
     * 产品代码下拉框
     */
    @ResponseBody
    @RequestMapping("/getProdId")
    public List<SuggestDto> getProdId()
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("prodId", RequestUtil.getPageParam().get("prodId"));
        List<SuggestDto> temp = agentPrdInfoService.getProdId(map);
        return temp;
    }

    /**
     * 产品登记编码下拉框
     */
    @ResponseBody
    @RequestMapping("/getProdRegistCode")
    public List<SuggestDto> getProdRegistCode()
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("prodRegistCode", RequestUtil.getPageParam().get("prodRegistCode"));
        List<SuggestDto> temp = agentPrdInfoService.getProdRegistCode(map);
        return temp;
    }
}
