package com.joyintech.tams.report.submitmanagement.agentprdinforegist.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmanagement.agentprdinforegist.dto.AgentPrdInfoRegistDto;
import com.joyintech.tams.report.submitmanagement.agentprdinforegist.service.AgentPrdInfoRegist001Service;
import com.joyintech.tams.report.submitmanagement.agentprdinforegist.service.AgentPrdInfoRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * 画面
 */
@Controller
@PageId("AgentPrdInfoRegist001")
@RequestMapping("/report/submitmanagement/agentprdinforegist/AgentPrdInfoRegist001")
public class AgentPrdInfoRegist001Controller extends BaseController {

    @Autowired
    private AgentPrdInfoRegist001Service agentPrdInfoRegist001Service;
    @Autowired
    private AgentPrdInfoRegistService agentPrdInfoRegistService;

    /**
     * 初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        Map<String, Object> map = RequestUtil.getPageParam();
        if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
            model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
            if (StringUtil.isNotEmpty(map.get("status"))) {
                model.addAttribute("status", map.get("status").toString().trim());
            }
            if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
                model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
            model.addAttribute("searchDataDateStart", map.get("searchDataDateStart"));
            model.addAttribute("searchDataDateEnd", map.get("searchDataDateEnd"));
            model.addAttribute("isMain", "01");
        }
        super.setSelectOptionToModel(model, DictCode.报送数据状态,
            DictCode.报送状态,DictCode.报送反馈状态);
        List<SelectOptionDto> listSendStatus = querySendStatus();
        model.addAttribute("agentInstitutionCode",RequestUtil.getParam("agentInstitutionCode"));
        model.addAttribute("prodRegistCode",RequestUtil.getParam("prodRegistCode"));
        // 增加发送状态
        model.addAttribute("SUBMIT_SEND_STATUS", JsonUtils.objectArray2Json(listSendStatus.toArray()));
        return super.init(model);
    }
    /**
     *
     * 查询功能
     * <功能详细描述>
     * @return 存储了查询结果的list
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<AgentPrdInfoRegistDto> search(){
        Map<String, Object> param = RequestUtil.getPageParam();
        return agentPrdInfoRegistService.searchForPage(param);
    }

    /**
     *
     * 查询功能(无分页)
     * <功能详细描述>
     * @return 存储了查询结果的list
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/searchInfo")
    public List<AgentPrdInfoRegistDto> searchInfo(@RequestParam Map<String,Object> param){
        return agentPrdInfoRegistService.queryForInfoRegist(param);
    }

    /**
     * 手工处理功能
     *
     * @return 提示信息
     * @param map
     *            条件map
     * @seea
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doManual")
    public AjaxResult doUpdate(@RequestBody Map<String, Object> map) {
        agentPrdInfoRegistService.updateSumStaus(map);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }

    /**
     * 取消手工处理功能
     *
     * @return 提示信息
     * @param map
     *            条件map
     * @seea
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/cancelMannul", method = RequestMethod.POST)
    public AjaxResult cancelMannul(@RequestBody Map<String, Object> map) {
        agentPrdInfoRegistService.cancelMannul(map);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }

    /**
     * 导出文件
     *
     * @return 导出的excel
     * @see
     * @since 1.6.0
     */
    @ResponseBody
    @RequestMapping("download")
    public ResponseEntity<byte[]> download() throws IOException {
        String datas = this.getParam("keyList");
        return agentPrdInfoRegist001Service.download(this.getPageId(), RequestUtil.getPageParam(),datas);
    }

    /**
     * @Title: querySendStatus
     * @Description: (报送登记发送状态)
     * @param @return    参数
     * @return List<SelectOptionDto>    返回类型
     * @throws
     */
    private List<SelectOptionDto> querySendStatus() {
        List<SelectOptionDto> listSendStatus = new ArrayList<SelectOptionDto>();
        SelectOptionDto selectOptionDto1 = new SelectOptionDto();
        selectOptionDto1.setId("01");
        selectOptionDto1.setText("发送成功");
        selectOptionDto1.setCol("");
        listSendStatus.add(selectOptionDto1);
        SelectOptionDto selectOptionDto2 = new SelectOptionDto();
        selectOptionDto2.setId("02");
        selectOptionDto2.setText("发送失败");
        selectOptionDto2.setCol("");
        listSendStatus.add(selectOptionDto2);
        return listSendStatus;
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
        List<SuggestDto> temp = agentPrdInfoRegistService.getAgentInstitutionCode(map);
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
        List<SuggestDto> temp = agentPrdInfoRegistService.getProdId(map);
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
        List<SuggestDto> temp = agentPrdInfoRegistService.getProdRegistCode(map);
        return temp;
    }
}