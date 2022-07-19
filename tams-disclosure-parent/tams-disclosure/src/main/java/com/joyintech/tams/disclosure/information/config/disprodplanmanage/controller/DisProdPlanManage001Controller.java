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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage001Service;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.service.DisProdPlanManage002Service;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.system.constant.SystemGlobalConstant;

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

    @SuppressWarnings("rawtypes")
    @Autowired
    private CommonExportService commonExportService;

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
        this.setSelectOptionToModel(model, DictCode.信披_数据状态);
        return super.init(model);
    }

    /**
     * 导出 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
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
    }


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
        params.setPageId("DisProdPlanManage002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdPlanId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品排期计划.getValue());
        params.setComment(entity -> "共通业务类型_产品排期计划-产品代码" + "(" + entity.getProdId() + ")");
        processParameter.setOperationType(OperationType.DELETE);
        processParameter.setWorkFlowParameter(params);
        String msg = disProdPlanManage002Service.start(processParameter, dto.getSelectedItem().toArray(new DisProdPlanManage002Dto[dto.getSelectedItem().size()]));

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
    public AjaxResult importFile(@RequestBody Map<String, Object> param) {
        return disProdPlanManage001Service.importFile(param);
    }
}
