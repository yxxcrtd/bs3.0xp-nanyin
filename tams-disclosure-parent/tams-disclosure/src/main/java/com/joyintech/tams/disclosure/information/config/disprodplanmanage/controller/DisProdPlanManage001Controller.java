/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage001Service;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;

/**
 * 报表模板管理 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see DisProdPlanManage001Controller
 * @since 1.0
 */
@Controller
@PageId("DisProdPlanManage001")
@RequestMapping("/disclosure/information/config/disprodplanmanage/DisProdPlanManage001")
public class DisProdPlanManage001Controller extends BaseController {

    @Autowired
    private DisProdPlanManage001Service disProdPlanManage001Service;
    @Autowired
    private DisProdPlanManage002Service disProdPlanManage002Service;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage001Controller.class);

    @Override
    public String init(Model model) {
		/*String isModelSelect = this.getParam("isModelSelect");

		model.addAttribute(ReportGlobalConstant.REPORT_SERVER_URL,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL));
		model.addAttribute(ReportGlobalConstant.REPORT_SERVER_PATH,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH));
		model.addAttribute(ReportGlobalConstant.REPORT_USERNAME,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME));
		model.addAttribute(ReportGlobalConstant.REPORT_PASSWORD,
				CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD));

		model.addAttribute("isModelSelect", isModelSelect);*/
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 导出 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws Exception
     */
    /**
     * 导出 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws Exception*/
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(){
        return disProdPlanManage001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
/*    @SuppressWarnings("unchecked")
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws IOException {
        // Map<String, Object> paraMap = RequestUtil.getPageParam();
        String datas = this.getParam("datas");
        List<DisProdPlanManage001Dto> listdto = null;
        if (datas == null) {
            listdto = disProdPlanManage001Service.searchReportsNew(RequestUtil.getPageParam());
        } else {
            listdto = JsonUtils.json2List(datas, DisProdPlanManage001Dto.class);
        }
        File outFile = commonExportService.makeFile(
                "/com/joyintech/tams/disclosure/information/config/disprodplanmanage/export_disprodplanmanage001.xml",
                listdto);
        String exportFileName = this.getPageId();
        com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(this.getPageId());
        if (null != page) {
            exportFileName = page.getName()
                    + SystemGlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                    + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                    + FileNameUtil.getFileExtName(outFile);
        }
        return ExportUtil.export(exportFileName, outFile, true);
    }*/


    @SuppressWarnings("finally")
    @RequestMapping("/searchReports")
    @ResponseBody
    public AjaxResult searchReports() {
        AjaxResult value = new AjaxResult();
        try {
            List<DisProdPlanManage001Dto> result = disProdPlanManage001Service
                    .searchReportsNew(RequestUtil.getPageParam());
            value.setData(result);
        } catch (Exception e) {
            value.setMessage("查询报表模板:" + e.getMessage(), MessageType.错误消息);
            logger.error("error:", e);
        } finally {
            //return value;
        }
        return value;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) DisProdPlanManage002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 左侧栏查询 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<DisProdPlanManage001Dto> findIndexId() {
        Page<DisProdPlanManage001Dto> page = disProdPlanManage001Service.findByPage(RequestUtil.getPageParam());
        return page;
    }

    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupEdit.class}) DisProdPlanManage002Dto dto) {
        dto.setUpdateUser(LoginUserUtil.getUserName());
        ProcessParameter<DisProdPlanManage002Dto> processParameter = new ProcessParameter<DisProdPlanManage002Dto>();
        WorkFlowParameter<DisProdPlanManage002Dto> params = new WorkFlowParameter<DisProdPlanManage002Dto>();
        List<DisProdPlanManage002Dto> selectedItem = dto.getSelectedItem();
        selectedItem.forEach(e->{
            e.setOperateType("DELETE");
        });
        params.setPageId("DisProdPlanManage002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getProdPlanId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品排期计划.getValue());
        params.setComment(entity -> "共通业务类型_产品排期计划-产品代码" + "(" + entity.getProdId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = disProdPlanManage002Service.start(processParameter, selectedItem.toArray(new DisProdPlanManage002Dto[selectedItem.size()]));

        return new AjaxResult(msg);
    }


    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return disProdPlanManage001Service.checkImportData(fileName);
    }


    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importFile(String fileName) {
        return disProdPlanManage001Service.importData(fileName);
    }
}
