package com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dto.SalesTransactionMaintenanceRegist001Dto;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.service.SalesTransactionMaintenanceRegist001Service;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.service.SalesTransactionMaintenanceRegistMService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 画面
 */
@Controller
@PageId("SalesTransactionMaintenanceRegist001")
@RequestMapping("report/submitmanagement/salestransactionmaintenanceregist/SalesTransactionMaintenanceRegist001")
public class SalesTransactionMaintenanceRegist001Controller extends BaseController {

    @Autowired
    private SalesTransactionMaintenanceRegist001Service salesTransactionMaintenanceRegist001Service;
    @Autowired
    private SalesTransactionMaintenanceRegistMService salesTransactionMaintenanceRegistMService;
    // 平台日期
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.报送数据状态,
            DictCode.报送状态,DictCode.报送反馈状态);

        model.addAttribute("sysDate", iPlatformDate.getSysDateStr());
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
    public Page<SalesTransactionMaintenanceRegist001Dto> search(){
        Map<String, Object> param = RequestUtil.getPageParam();
        return salesTransactionMaintenanceRegistMService.searchForPage(param);
    }

    /**
     * 数据检查
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
        return salesTransactionMaintenanceRegistMService.reportCheck(RequestUtil.getPageParam());
    }

    /**
     * 检查是否有未执行或正在执行中的批处理任务
     *
     * @param
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForAllManual")
    public AjaxResult checkConfirmMsgForAllManual() {
        AjaxResult ajaxResult = new AjaxResult();
        if (!salesTransactionMaintenanceRegistMService.checkIfInBatchByTaskBeanName(
                BatchBeanName.投资者身份信息登记手工处理.getTaskId())) {
            ajaxResult.setMessage(this.getMessage("REPORT_MANUAL_0008"), MessageType.错误消息);
        }
        return ajaxResult;
    }

    /**
     * 启动批处理导出和手工处理
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "batchExportAndDoManual")
    public AjaxResult batchExportAndDoManual(@RequestParam Map<String, Object> param) {
        return salesTransactionMaintenanceRegist001Service.batchExportAndDoManual(param);
    }

    /**
     * 启动批处理导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "batchExport")
    public AjaxResult batchExport(@RequestParam Map<String, Object> param) {
        return salesTransactionMaintenanceRegist001Service.batchExport(param);
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
    public List<SalesTransactionMaintenanceRegist001Dto> searchInfo(@RequestParam Map<String,Object> param){
        return salesTransactionMaintenanceRegistMService.queryForInfoRegist(param);
    }
    /**
     *
     * 导出功能
     * <功能详细描述>
     * @return 结果
     * @see
     * @since 1.0
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty((String) pageParam.get("keyList"))){
            pageParam.put("export_method_flg", "exportSelect");
        }else{
            pageParam.put("export_method_flg", "export");
        }
        return salesTransactionMaintenanceRegist001Service.export(this.getPageId(), pageParam);
    }

    /**
     *手工处理功能
     * @return 提示信息
     * @param map 条件map
     * @seea
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = ReportGlobalConstant.PAGE_UPDATE ,method = RequestMethod.POST)
    public AjaxResult doUpdate(@RequestParam Map<String,Object> map){
        return salesTransactionMaintenanceRegistMService.doUpdate(map);
    }

    /**
     *取消手工处理功能
     * @return 提示信息
     * @param map 条件map
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/cancelMannul" ,method = RequestMethod.POST)
    public AjaxResult cancelMannul(@RequestBody Map<String,Object> map) {
        return null;
    }
    /**
     *
     * 检查
     * <功能详细描述>
     * @param dto 参数
     * @param result 结果
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value="checkSalesTransactionMaintenanceRegist")
    public AjaxResult check(@Valid @RequestBody SalesTransactionMaintenanceRegist001Dto dto,BindingResult result){
        if(result.hasErrors()) {
            return new AjaxResult(result);
        }
        return null;
    }
    /**
     * 将选择的数据导出成excel
     * @return 返回需要的格式
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/exportSelect")
    public ResponseEntity<byte[]> exportSelect(){
        Map<String,Object> map = RequestUtil.getPageParam();
        String exportKeys =(String) map.get("expKeys");
        List<SalesTransactionMaintenanceRegist001Dto> list = this.salesTransactionMaintenanceRegistMService.queryExportSelectRegistList(exportKeys);
        String flag = (String)RequestUtil.getPageParam().get("flag");
        File outFile = null;
        if(StringUtil.equals("0", flag)){
            outFile = salesTransactionMaintenanceRegist001Service.makeFile(
                "/com/joyintech/tams/report/submitmanagement/salestransactionmaintenanceregist/conf/export_conf.xml", list);
        }
        else{
            outFile = salesTransactionMaintenanceRegist001Service.makeFile(
                "/com/joyintech/tams/report/submitmanagement/salestransactionmaintenanceregist/conf/export_conf_all.xml", list);
        }
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
     
     
     /**
      * 导出中债excel之前检查数据量
      *
      * @param param 查询条件
      * @return 处理结果
      */
     @ResponseBody
     @RequestMapping(value = "countExportNum")
     public int countExportNum(@RequestParam Map<String, Object> param) {
         int dataNum = salesTransactionMaintenanceRegist001Service.countExportNum(param);
         return dataNum;

     }
     /**
      * 导出检查
      * @param param
      * @return
      */
     @ResponseBody
     @RequestMapping(value = "checkExport")
 	public AjaxResult checkExport(@RequestBody Map<String, Object> param) {
 		AjaxResult ajaxResult = new AjaxResult();
 		boolean batchExport = false;
 		// 未勾选
 		if (!RequestUtil.getPageParam().containsKey("keyList")) {
 			// 中债报送批处理导出数据量。大于等于此参数则需要批处理导出。
 			String batchExportNum = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SUBINFO_BATCH_EXPORT_NUM);
 			if (!StringUtil.isNotEmpty(batchExportNum)) {
 				batchExportNum = "1000000"; // 默认为100w
 			}
 			int dataNum = salesTransactionMaintenanceRegist001Service.countExportNum(param);
 			if (dataNum >= Integer.parseInt(batchExportNum)) {
 				batchExport = true;
 			}
 		}
 		ajaxResult.setData(batchExport);
 		return ajaxResult;
 	}
}