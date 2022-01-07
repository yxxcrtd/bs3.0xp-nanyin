package com.joyintech.tams.report.submitmaintenance.agentinstitution.controller;

import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.dao.ChbAgentInstitutionInfoMapper;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.service.AgentInstitutionService;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.dto.AgentInstitutionDto;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.service.AgentInstitution001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.grid.Page;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("AgentInstitution001")
@RequestMapping("/report/submitmaintenance/agentinstitution/AgentInstitution001")
public class AgentInstitution001Controller extends BaseController {

    /**
     * dao
     */
    @Autowired
    private AgentInstitution001Service agentInstitution001Service;
    @Autowired
    private AgentInstitutionService agentInstitutionService;
    @Autowired
    private ChbAgentInstitutionInfoMapper chbAgentInstitutionInfoMapper;
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
    public Page<AgentInstitutionDto> findPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return agentInstitutionService.searchForPage(param);
    }

    /**
     *check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid AgentInstitutionDto AgentInstitutionDto,
                            BindingResult br) {
        return commonCheck(br, AgentInstitutionDto.getOperateType());
    }

    /**
     * 删除check
     * @param agentInstitutionDto
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult deleteCheck(@RequestBody AgentInstitutionDto agentInstitutionDto) {
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        List<AgentInstitutionDto> agentInstitutionCodeList = agentInstitutionDto.getSelectedItem();
        for(AgentInstitutionDto info : agentInstitutionCodeList) {
            String agentInstitutionCode = info.getAgentInstitutionCode();
            if (chbAgentInstitutionInfoMapper.deleteCheck(agentInstitutionCode) > 0) {
                JoyinMessage ms = new JoyinMessage("选择机构已维护过委托销售产品编码信息，请确认是否删除全部相关信息?", MessageType.确认消息);
                joyinMessages.add(ms);
                wr.setJoyinMessages(joyinMessages);
                break;
            }
        }
        return wr;
    }

    /**
     * 删除附件check
     * @param agentInstitutionDto
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteFileCheck")
    public AjaxResult deleteFileCheck(@RequestBody AgentInstitutionDto agentInstitutionDto) {
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        AgentInstitutionDto agentInstitutionCodeList = agentInstitutionDto.getSelectedItem().get(0);
            String agentInstitutionCode = agentInstitutionCodeList.getAgentInstitutionCode();
        List<AgentInstitutionDto> deleteFileCheckList = chbAgentInstitutionInfoMapper.deleteFileCheck(agentInstitutionCode);
        if(deleteFileCheckList.size() != 0) {
            for (AgentInstitutionDto deleteFileCheck : deleteFileCheckList) {
                if(deleteFileCheckList.size() == 1) {
                    if (!deleteFileCheck.getSendStatus().equals("01")) {
                        JoyinMessage ms = new JoyinMessage("该条数据已报送，请确认是否删除附件?", MessageType.确认消息);
                        joyinMessages.add(ms);
                        wr.setJoyinMessages(joyinMessages);
                    }
                }
            }
        }
        return wr;
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
        List<AgentInstitutionDto> listdto ;
        if (datas == null) {
            listdto = agentInstitutionService.queryAllList(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, AgentInstitutionDto.class);
        }
        File outFile = agentInstitutionService.makeFile(
                "/com/joyintech/tams/report/submitmaintenance/agentinstitution/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                "代理销售机构信息" + SystemGlobalConstant.OPEN_PARENTHESIS
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
        return agentInstitutionService.checkConfirmMsgForImportData(fileName);
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
        return this.agentInstitutionService.importData(fileName);
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
            ValidateGroupDelete.class}) AgentInstitutionDto dto, BindingResult br)
            throws Exception {
        if(!(dto.getSelectedItem().isEmpty())){
                agentInstitutionService.deleteByPrimaryKey(dto);
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
        List<SuggestDto> temp = agentInstitutionService.getAgentInstitutionCode(map);
        return temp;
    }

    /**
     * 删除附件
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deltFile", method = RequestMethod.POST)
    public AjaxResult deltFile(@RequestParam Map<String, Object> map)  {
        agentInstitution001Service.deltFile(map);
        return new AjaxResult("删除成功");
    }

    /**
     * 附件下载
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    public AjaxResult downloadFile(@RequestParam Map<String, Object> map)  {
        return new AjaxResult("下载成功");

    }

    /**
     * 查看附件
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFileList", method = RequestMethod.POST)
    public String getFileList(@RequestParam Map<String, Object> map)  {
        String res;
        try {
            res = agentInstitution001Service.getFileList(map);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return res;

    }
}
