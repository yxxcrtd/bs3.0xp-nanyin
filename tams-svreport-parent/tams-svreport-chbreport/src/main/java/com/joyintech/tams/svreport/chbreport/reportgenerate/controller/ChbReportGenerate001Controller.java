package com.joyintech.tams.svreport.chbreport.reportgenerate.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.chbreport.investreport.common.dto.ReportGenerate001Dto;
import com.joyintech.tams.svreport.chbreport.investreport.common.service.ChbInvestReportCommonService;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbInvestOperation001Controller
 * @Description: 非标投资运行报告查询controller
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@Controller
@PageId("ChbReportGenerate001")
@RequestMapping("/svreport/chbreport/reportgenerate/ChbReportGenerate001")
public class ChbReportGenerate001Controller extends BaseController {

    @Autowired
    private ChbInvestReportCommonService chbInvestReportCommonService;

    @Autowired
    private ProgressBarService progressBarService;

    @Override
    public String init(Model model) {
        model.addAttribute("CHB_DISCLOSURE_REPORT_01", DictCodeListExt3.中债披露报告_成立公告.getValue());
        model.addAttribute("CHB_DISCLOSURE_REPORT_02", DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue());
        model.addAttribute("CHB_DISCLOSURE_REPORT_03", DictCodeListExt3.中债披露报告_到期公告.getValue());
        model.addAttribute("CHB_DISCLOSURE_REPORT_04", DictCodeListExt3.中债披露报告_产品定期公告.getValue());
        model.addAttribute("CHB_DISCLOSURE_REPORT_05", DictCodeListExt3.中债披露报告_产品净值披露.getValue());
        super.setSelectOptionToModel(model,DictCode.中债定期报告类型);
        return super.init(model);
    }


    /**
     * 分页查询明细展示数据
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/queryDetailData")
    public Page<ReportGenerate001Dto>  queryDetailData() throws Exception {
        return chbInvestReportCommonService.queryDetailData(RequestUtil.getPageParam());
    }

    /**
     * 分页查询投资运作报告
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/queryRepFtpPathList")
    public Page<RepFtpPath>  queryRepFtpPathList() throws Exception {
        return chbInvestReportCommonService.queryRepFtpPathList(RequestUtil.getPageParam());
    }
    /**
     * 查询分页产品下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryPrdInfoPage")
    public Page<SelectOptionDto> queryPrdInfoPage() {
        return chbInvestReportCommonService.queryPrdInfoPage(RequestUtil.getPageParam());
    }

    /**
     * 导出报告
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "exportReportDoc", method = RequestMethod.POST)
    public ResponseEntity<byte[]> exportReportDoc() throws Exception {
        return chbInvestReportCommonService.exportDoc(RequestUtil.getPageParam());
    }

    /**
     * 生成报告
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("createReportDoc")
    public AjaxResult createReportDoc() throws Exception {
        String pageId = this.getPageId();
        String barId = "createReportDoc001";
        double currentProcessD = 0.05D;//更新进度条
        this.updatePercent(pageId, barId, currentProcessD);//更新进度
        try {
            String repId = this.getParam("repId");//报表id
            String reportType = this.getParam("reportType");
            String year = this.getParam("year");
            Map<String, Object> param = new HashMap<>();
            param.put("repId", repId);
            param.put("prodId", this.getParam("prodId"));
            param.put("endDate", chbInvestReportCommonService.getDqEndDate(reportType, year));
            param.put("startDate", chbInvestReportCommonService.getDqStartDate(reportType, year));
            param.put("reportType", reportType);
            List<ReportGenerate001Dto> list = null;
            if (StringUtil.equals(DictCodeListExt3.中债披露报告_成立公告.getValue(), repId)
                    || StringUtil.equals(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(), repId)
                    || StringUtil.equals(DictCodeListExt3.中债披露报告_到期公告.getValue(), repId.toString())
                    || StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), repId)) {
                String datas = this.getParam("datas");
                list = JsonUtils.json2List(datas, ReportGenerate001Dto.class);
            } else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品定期公告.getValue(), repId)) {
                list = chbInvestReportCommonService.selectDqReportList(param);
            } else {
                currentProcessD = 1.00D;//更新进度条
                this.updatePercent(pageId, barId, currentProcessD);//更新进度
                return new AjaxResult("披露报告类型暂不支持！", MessageType.错误消息);
            }
            currentProcessD = 0.20D;//更新进度条
            this.updatePercent(pageId, barId, currentProcessD);//更新进度
            int totalSize = list.size();
            String isConfirm = DictCodeListExt1.确认类型_手动.getValue();
            String isReveal = DictCodeListExt1.确认类型_手动.getValue();
            if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), repId)) {
                isConfirm = DictCodeListExt1.确认类型_自动.getValue();
                isReveal = DictCodeListExt1.确认类型_自动.getValue();
            }
            String errMsg = "";
            double createPercent = 0.7500D;//生成占比0.75
            this.updatePercent(pageId, barId, currentProcessD);//更新进度
            int index = 0;
            for(ReportGenerate001Dto item  : list) {
                index++;
                param.put("prodId", item.getProdId());
                param.put("endDate", item.getEndDate());
                param.put("startDate", item.getStartDate());
                param.put("prodName", item.getProdName());
                param.put("prodIdName", item.getProdIdName());
                if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品定期公告.getValue(), repId)) {
                    list = chbInvestReportCommonService.selectDqReportList(param);
                }
                boolean flag = chbInvestReportCommonService.createReportDoc(param, isConfirm, isReveal);
                if (flag) {
                    //return new AjaxResult("披露报告生成成功！");
                } else {
                    errMsg = errMsg + item.getProdIdName() + ChbInvestReportConstant.逗号;
                    //return new AjaxResult("披露报告生成出现错误！", MessageType.错误消息);
                }
                if(currentProcessD + createPercent * (index / totalSize) > currentProcessD + createPercent) {
                    this.updatePercent(pageId, barId, currentProcessD + createPercent);//更新进度
                }
                else {
                    this.updatePercent(pageId, barId, currentProcessD + createPercent * (index / totalSize));//更新进度
                }
            }
            if(StringUtil.isNotEmpty(errMsg)) {
                currentProcessD = 1.00D;//更新进度条
                this.updatePercent(pageId, barId, currentProcessD);//更新进度
                return new AjaxResult(errMsg + "，生成出现错误！", MessageType.错误消息);
            }
            currentProcessD = 1.00D;//更新进度条
            this.updatePercent(pageId, barId, currentProcessD);//更新进度
            return new AjaxResult("披露报告生成成功！");
        }
        catch (Exception e) {
            e.printStackTrace();
            currentProcessD = 1.00D;//更新进度条
            this.updatePercent(pageId, barId, currentProcessD);//更新进度
            return new AjaxResult("披露报告生成出现错误，请联系管理员！", MessageType.错误消息);
        }
    }

    /**
     * 数据校验
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ReportGenerate001Dto dto) throws IOException {
        String  repId = dto.getRepId();
        String datas = dto.getDatas();
        if(repId != null) {
            if (StringUtil.equals(DictCodeListExt3.中债披露报告_成立公告.getValue(), repId.toString())
            || StringUtil.equals(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(), repId.toString())
            || StringUtil.equals(DictCodeListExt3.中债披露报告_到期公告.getValue(), repId.toString())
            || StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), repId.toString())) {
                if(!StringUtil.isNotEmpty(datas)) {
                    return new AjaxResult("请先勾选要生成的数据！", MessageType.错误消息);
                }
                else {
                    List<ReportGenerate001Dto> paramsList = JsonUtils.json2List(dto.getDatas(), ReportGenerate001Dto.class);
                    List<RepReveal> list = chbInvestReportCommonService.selectReprevealListByDatas(paramsList);
                    if(!StringUtil.isEmptyList(list)) {
                        String errMsg = "";
                        String tipMsg = "";
                        for(RepReveal item : list) {
                            if(StringUtil.equals(item.getConfirmStatus(), DictCodeList.确认状态_已确认.getValue())) {
                                errMsg = errMsg + item.getProdName() + ChbInvestReportConstant.左括号 + item.getProdid() +
                                        ChbInvestReportConstant.右括号 + ChbInvestReportConstant.逗号;
                            }
                            else {
                                tipMsg = tipMsg + item.getProdName() + ChbInvestReportConstant.左括号 + item.getProdid() +
                                        ChbInvestReportConstant.右括号 + ChbInvestReportConstant.逗号;
                            }
                        }
                        if(StringUtil.isNotEmpty(errMsg)) {
                            return new AjaxResult(errMsg + "报告已确认，无法重新生成！", MessageType.错误消息);
                        }
                        if(StringUtil.isNotEmpty(tipMsg)) {
                            return new AjaxResult(tipMsg + "存在相同报告，确认重新生成？", MessageType.确认消息);
                        }
                    }
                }
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品定期公告.getValue(), repId.toString())) {
//                if(!StringUtil.isNotEmpty(dto.getProdId())) {
//                    return new AjaxResult("请先选择要生成产品！", MessageType.错误消息);
//                }
                if(!StringUtil.isNotEmpty(dto.getYear())) {
                    return new AjaxResult("请先选择要生成的查询年份！", MessageType.错误消息);
                }
                if(!StringUtil.isNotEmpty(dto.getReportType())) {
                    return new AjaxResult("请先选择要生成的报告类型！", MessageType.错误消息);
                }
                Map<String, Object> param = new HashMap<>();
                param.put("repId", dto.getRepId());
                param.put("prodId", dto.getProdId());
                param.put("endDate", chbInvestReportCommonService.getDqEndDate(dto.getReportType(), dto.getYear()));
                param.put("startDate", chbInvestReportCommonService.getDqStartDate(dto.getReportType(), dto.getYear()));
                param.put("reportType", dto.getReportType());
                List<ReportGenerate001Dto> list = chbInvestReportCommonService.selectDqReportList(param);
                if(!StringUtil.isEmptyList(list)) {
                    String tipMsg = "";
                    for(ReportGenerate001Dto item : list) {
                        if (StringUtil.equals(item.getConfirmStatus(), DictCodeList.确认状态_未确认.getValue())) {
                            tipMsg = tipMsg + item.getProdIdName() + ChbInvestReportConstant.逗号;
                        }
                    }
                    if(StringUtil.isNotEmpty(tipMsg)) {
                        return new AjaxResult(tipMsg + "存在相同报告，确认重新生成？", MessageType.确认消息);
                    }
                }
                else {
                    return new AjaxResult("无报告数据需要生成！", MessageType.错误消息);
                }
            }
            return new AjaxResult();
        }
        else {
            return new AjaxResult("请先选择要生成的报告！", MessageType.错误消息);
        }
    }

    /**
     * 更新进度条进度
     * @param pageId
     * @param barId
     * @param processD
     */
    public void updatePercent(String pageId, String barId, double processD) {
        if(StringUtil.isNotEmpty(pageId) && StringUtil.isNotEmpty(barId) && progressBarService != null) {
            progressBarService.updatePercent(pageId, barId, processD, false);
        }
    }
}
