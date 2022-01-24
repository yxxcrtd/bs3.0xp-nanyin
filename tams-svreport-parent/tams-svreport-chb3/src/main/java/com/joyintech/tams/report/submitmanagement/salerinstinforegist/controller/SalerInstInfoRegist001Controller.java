package com.joyintech.tams.report.submitmanagement.salerinstinforegist.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmanagement.salerinstinforegist.dto.SalerInstInfoRegistDto;
import com.joyintech.tams.report.submitmanagement.salerinstinforegist.service.SalerInstInfoRegist001Service;
import com.joyintech.tams.report.submitmanagement.salerinstinforegist.service.SalerInstInfoRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 画面
 */
@Controller
@PageId("SalerInstInfoRegist001")
@RequestMapping("/report/submitmanagement/salerinstinforegist/SalerInstInfoRegist001")
public class SalerInstInfoRegist001Controller extends BaseController {

    @Autowired
    private SalerInstInfoRegist001Service salerInstInfoRegist001Service;
    @Autowired
    private SalerInstInfoRegistService salerInstInfoRegistService;

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
            model.addAttribute("searchDataStart", map.get("searchDataStart"));
            model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
            model.addAttribute("isMain", "01");
        }
        super.setSelectOptionToModel(model, DictCode.报送数据状态,
            DictCode.报送状态,DictCode.报送反馈状态);
        List<SelectOptionDto> listSendStatus = querySendStatus();
        model.addAttribute("salerOrgCode",RequestUtil.getParam("salerOrgCode"));
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
    public Page<SalerInstInfoRegistDto> search(){
        Map<String, Object> param = RequestUtil.getPageParam();
        return salerInstInfoRegistService.searchForPage(param);
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
    public List<SalerInstInfoRegistDto> searchInfo(@RequestParam Map<String,Object> param){
        return salerInstInfoRegistService.queryForInfoRegist(param);
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
        salerInstInfoRegistService.updateSumStaus(map);
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
        salerInstInfoRegistService.cancelMannul(map);
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
        return salerInstInfoRegist001Service.download(this.getPageId(), RequestUtil.getPageParam(),datas);
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
     * 销售机构代码下拉框
     */
    @ResponseBody
    @RequestMapping("/getSalerInstCode")
    public List<SuggestDto> getSalerInstCode()
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salerInstCode", RequestUtil.getPageParam().get("salerInstCode"));
        List<SuggestDto> temp = salerInstInfoRegistService.getSalerInstCode(map);
        return temp;
    }

    /**
     * 总部或分支机构名称下拉框
     */
    @ResponseBody
    @RequestMapping("/getSalerOrgName")
    public List<SuggestDto> getSalerOrgName()
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salerOrgName", RequestUtil.getPageParam().get("salerOrgName"));
        List<SuggestDto> temp = salerInstInfoRegistService.getSalerOrgName(map);
        return temp;
    }
}