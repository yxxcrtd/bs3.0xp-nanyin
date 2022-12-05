package com.joyintech.tams.disclosure.information.reveal.reportreviewtg.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage001Service;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportreviewtg.service.ReportReviewTg001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

/**
 *
 * 托管复核管理
 * @author zhengxiaolian
 * @version 1.0 2022年10月09日
 * @see ReportReviewTg001Controller
 * @since 1.0
 */

@Controller
@PageId("ReportReviewTg001")
@RequestMapping("/disclosure/information/reveal/reportreviewtg/ReportReviewTg001")
public class ReportReviewTg001Controller extends BaseController {

    @Autowired
    private ReportReviewTg001Service reportReviewTg001Service;

    @Autowired
    private ReportConfirmManage001Service reportConfirmManage001Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCodeExt1.托管复核状态,DictCodeExt1.信披报表类别);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_募集方式);
        model.addAttribute("reviewTgStatus", "01");
        return super.init(model);
    }


    @RequestMapping("findPage")
    @ResponseBody
    public Page<ReportReview001Dto> findPage(){
        Map<String, Object> param= RequestUtil.getPageParam();
        Page<ReportReview001Dto> page = reportReviewTg001Service.findPage(param);
        page.getRoot().forEach(dto -> {
            String raiseType = reportConfirmManage001Service.selectRaiseTypebyProdID(dto.getProdid());
            dto.setRaiseType(raiseType);
        });
        List<ReportReview001Dto> ReportReview001DtoList=page.getRoot();
        String r=RequestUtil.getPageParam().get("raiseType").toString();
        String raiseTypeRequest[]=null;
        if(StringUtil.isNotEmpty(r)) {
            raiseTypeRequest = RequestUtil.getPageParam().get("raiseType").toString().split(",");
        }
        if(StringUtil.isNotEmpty(raiseTypeRequest)) {
            for (int i = 0; i < ReportReview001DtoList.size(); i++) {
                if(!Arrays.asList(raiseTypeRequest).contains(ReportReview001DtoList.get(i).getRaiseType())){
                    ReportReview001DtoList.remove(i);
                    // 减一操作
                    i--;
                }
            }
        }
        return page;
    }

    @ResponseBody
    @RequestMapping("pushRevealCheck")
    public AjaxResult pushRevealCheck(@RequestBody ReportConfirmManage001Dto dto) throws IOException {
        AjaxResult res = new AjaxResult();
        String data=dto.getData();

        List<ReportReview001Dto> dataList = new ArrayList<ReportReview001Dto>();
        if(StringUtil.isNotEmpty(data)){
            dataList = JsonUtils.json2List(data, ReportReview001Dto.class);
            res = reportReviewTg001Service.pushRevealCheck(dataList);
        }
        return res;
    }

    @ResponseBody
    @RequestMapping("pushReveal")
    public AjaxResult pushReveal(@RequestBody ReportConfirmManage001Dto dto) throws IOException {
        AjaxResult res = new AjaxResult();
        String id =dto.getId();
        List<String> idList = new ArrayList<String>();
        if(StringUtil.isNotEmpty(id)){
            idList = JsonUtils.json2List(id, String.class);
            res = reportReviewTg001Service.pushReveal(idList);
        }
        return res;
    }

    @ResponseBody
    @RequestMapping("returnData")
    public AjaxResult returnData(@RequestBody ReportConfirmManage001Dto dto) throws IOException {
        AjaxResult res = new AjaxResult();
        String id =dto.getId();
        List<String> idList = new ArrayList<String>();
        if(StringUtil.isNotEmpty(id)){
            idList = JsonUtils.json2List(id, String.class);
            res = reportReviewTg001Service.returnData(idList);
        }
        return res;
    }
    @ResponseBody
    @RequestMapping("getTgUserList")
    public Page<SelectOptionDto> getTgUserList() {
        return reportReviewTg001Service.getTgUserList(RequestUtil.getPageParam());
    }

    @RequestMapping("export")
    public ResponseEntity<byte[]> export(){
        return reportReviewTg001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping("downloadReport")
    public ResponseEntity<byte[]> downloadReport() throws IOException {
        String id = RequestUtil.getParam("id");
        List<String> idList = new ArrayList<String>();
        if(StringUtil.isNotEmpty(id)){
            idList = JsonUtils.json2List(id, String.class);
        }
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("idList", idList);
        return reportConfirmManage001Service.downLoadReportInfo(paraMap);
    }
}
