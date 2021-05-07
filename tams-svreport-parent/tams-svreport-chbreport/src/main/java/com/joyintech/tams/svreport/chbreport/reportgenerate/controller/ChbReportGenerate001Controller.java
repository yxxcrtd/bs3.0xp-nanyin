package com.joyintech.tams.svreport.chbreport.reportgenerate.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
        boolean flag =  chbInvestReportCommonService.createReportDoc(RequestUtil.getPageParam(),
                DictCodeListExt1.确认类型_手动.getValue(), DictCodeListExt1.确认类型_手动.getValue());
        if(flag) {

            return new AjaxResult("披露报告生成成功！");
        }
        else {
            return new AjaxResult("披露报告生成出现错误！", MessageType.错误消息);
        }
    }

    /**
     * 数据校验
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ReportGenerate001Dto dto) {
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
            }
            Map<String, Object> param = new HashMap<>();
            param.put("repId", dto.getRepId());
            param.put("prodId", dto.getProdId());
            param.put("endDate", dto.getEndDate());
            param.put("reportType", dto.getReportType());
            RepReveal selectReprevealByParams = chbInvestReportCommonService.selectReprevealByParams(param);
            if(selectReprevealByParams != null ) {
                if(StringUtil.equals(selectReprevealByParams.getConfirmStatus(), DictCodeList.确认状态_已确认.getValue())) {
                    return new AjaxResult("已确认的报告无法重新生成！", MessageType.错误消息);
                }
                else {
                    return new AjaxResult("存在相同报告，确认重新生成？", MessageType.确认消息);
                }
            }
            else {
                return new AjaxResult();
            }
        }
        else {
            return new AjaxResult("请先选择要生成的报告！", MessageType.错误消息);
        }

    }
}
