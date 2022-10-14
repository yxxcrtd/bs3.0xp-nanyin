package com.joyintech.tams.disclosure.information.reveal.reportreview.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepRevealReview;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao.ReportConfirmManage001Mapper;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dao.ReportReview001Mapper;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportReview001Service extends CommonExportService<ReportReview001Dto> {

    @Value("${application.reportInsertCount}")
    public  String reportInsertCount;
    @Autowired
    private ReportReview001Mapper reportReview001Mapper;

    @Autowired
    private ReportConfirmManage001Mapper reportConfirmManage001Mapper;

    public Page<SelectOptionDto> getReportNameList(Map<String, Object> param){
        Page<SelectOptionDto> page = queryForPageForSelectReturn(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> reportReview001Mapper.getReportNameList(param));
        return page;
    }

    public Page<ReportReview001Dto> findPage(Map<String, Object> param){
        String loginUser= LoginUserUtil.getUserName();
        param.put("loginUser",loginUser);
        param.put("REVIEW_STATUS_06",DictCodeListExt5.复核状态_被退回确认.getValue());

        String pProdId=(String) param.get("pProdId");
        String[] pProdIdArr = null;
        if(StringUtil.isNotEmpty(pProdId)){
            pProdIdArr = pProdId.split(",");
            param.put("pProdIdArr",pProdIdArr);
        }

        String prodId=(String) param.get("prodId");
        String[] prodIdArr = null;
        if(StringUtil.isNotEmpty(prodId)){
            prodIdArr = prodId.split(",");
            param.put("prodIdArr",prodIdArr);
        }

        String reportType=(String) param.get("reportType");
        String[] reportTypeArr = null;
        if(StringUtil.isNotEmpty(reportType)){
            reportTypeArr = reportType.split(",");
            param.put("reportTypeArr",reportTypeArr);
        }

        String reviewStatus=(String) param.get("reviewStatus");
        String[] reviewStatusArr = null;
        if(StringUtil.isNotEmpty(reviewStatus)){
            reviewStatusArr = reviewStatus.split(",");
            param.put("reviewStatusArr",reviewStatusArr);
        }

        return  queryForPageForSelectReturn(()->reportReview001Mapper.findPage(param));
    }

    public AjaxResult returnData(List<String> idList) {
        AjaxResult res = new AjaxResult();

        int maxDataCountOneTime = Integer.parseInt(reportInsertCount);
        int dataCount = idList.size();
        if (dataCount <= maxDataCountOneTime) {
            this.updateData(idList);
            return new AjaxResult("已退回");
        }
        List<String> newDataList = new ArrayList<String>();
        newDataList.addAll(idList);
        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++) {
            this.updateData(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (!CollectionUtils.isEmptyList(newDataList)) {
            this.updateData(newDataList);
        }

        res = new AjaxResult("已退回");
        return res;
    }


    public void updateData(List<String> idList){
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("idList", idList);
        paraMap.put("updateUser", LoginUserUtil.getUserName());
        paraMap.put("confimStatus", DictCodeListExt1.披露确认状态_未确认.getValue());
        paraMap.put("reviewStatus",DictCodeListExt5.复核状态_被退回确认.getValue());
        reportConfirmManage001Mapper.confirmCancle(paraMap);
        reportReview001Mapper.updateReviewStatus(paraMap);
    }

    public AjaxResult pushReveal(List<String> idList) {
        AjaxResult res = new AjaxResult();

        int maxDataCountOneTime = Integer.parseInt(reportInsertCount);
        int dataCount = idList.size();
        if (dataCount <= maxDataCountOneTime) {
            this.updateReviewInfo(idList);
            return new AjaxResult("推送披露成功");
        }
        List<String> newDataList = new ArrayList<String>();
        newDataList.addAll(idList);
        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++) {
            this.updateReviewInfo(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (!CollectionUtils.isEmptyList(newDataList)) {
            this.updateReviewInfo(newDataList);
        }

        res = new AjaxResult("推送披露成功");
        return res;
    }

    public void updateReviewInfo(List<String> idList){
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("idList", idList);
        paraMap.put("updateUser", LoginUserUtil.getUserName());
        paraMap.put("reviewTime", DateUtil.now());
        paraMap.put("reviewStatus",DictCodeListExt5.复核状态_已推送披露.getValue());
        reportReview001Mapper.updateReviewInfo(paraMap);
    }

    public AjaxResult pushTg(ReportReview001Dto dto) throws IOException {
        AjaxResult res = new AjaxResult();
        List<String> idList= JsonUtils.json2List(dto.getId(), String.class);

        String reviewTgGroup=dto.getReviewTgGroup();

        int maxDataCountOneTime = Integer.parseInt(reportInsertCount);
        int dataCount = idList.size();
        if (dataCount <= maxDataCountOneTime) {
            this.updateReviewTgInfo(idList,reviewTgGroup);
            return new AjaxResult("推送托管成功");
        }
        List<String> newDataList = new ArrayList<String>();
        newDataList.addAll(idList);
        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++) {
            this.updateReviewTgInfo(newDataList.subList(0, maxDataCountOneTime),reviewTgGroup);
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (!CollectionUtils.isEmptyList(newDataList)) {
            this.updateReviewTgInfo(newDataList,reviewTgGroup);
        }

        res = new AjaxResult("推送托管成功");
        return res;
    }

    public void updateReviewTgInfo(List<String> idList,String reviewTgGroup){
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("idList", idList);
        paraMap.put("reviewUser",LoginUserUtil.getUserName());
        paraMap.put("reviewTime", DateUtil.now());
        paraMap.put("reviewStatus",DictCodeListExt5.复核状态_已推送托管.getValue());
        paraMap.put("reviewTgStatus",DictCodeListExt5.托管复核状态_待复核.getValue());
        paraMap.put("reviewTgGroup",reviewTgGroup);

        paraMap.put("updateUser", LoginUserUtil.getUserName());
        reportReview001Mapper.updateReviewTgInfo(paraMap);
    }


    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/reveal/reportreview/conf/export_ReportReview001.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ReportReview001Dto> selectPageData(Map<String, Object> selectParam) {
        String datas = (String) selectParam.get("datas");
        if (StringUtil.isNotEmpty(datas)) {
            try {
                List<ReportReview001Dto>dataList=JsonUtils.json2List(datas, ReportReview001Dto.class);
                if(!CollectionUtils.isEmptyList(dataList)){
                    Page<ReportReview001Dto>pageList=new Page<ReportReview001Dto>();
                    pageList.setRoot(dataList);
                    return pageList;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            return this.findPage(selectParam);
        }

        return null;
    }
}
