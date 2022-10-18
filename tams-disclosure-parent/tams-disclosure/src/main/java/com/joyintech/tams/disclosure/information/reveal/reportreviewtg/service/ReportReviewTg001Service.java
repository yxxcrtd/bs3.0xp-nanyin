package com.joyintech.tams.disclosure.information.reveal.reportreviewtg.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportreviewtg.dao.ReportReviewTg001Mapper;
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
public class ReportReviewTg001Service extends CommonExportService<ReportReview001Dto> {

    @Value("${application.reportInsertCount}")
    public  String reportInsertCount;
    @Autowired
    private ReportReviewTg001Mapper reportReviewTg001Mapper;

    public Page<ReportReview001Dto> findPage(Map<String, Object> param){
        String loginUser= LoginUserUtil.getUserName();
        param.put("loginUser",loginUser);

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

        String reviewTgUser=(String) param.get("reviewTgUser");
        String[] reviewTgUserArr = null;
        if(StringUtil.isNotEmpty(reviewTgUser)){
            reviewTgUserArr = reviewTgUser.split(",");
            param.put("reviewTgUserArr",reviewTgUserArr);
        }

        return  queryForPageForSelectReturn(()->reportReviewTg001Mapper.findPage(param));
    }

    public AjaxResult pushRevealCheck(List<ReportReview001Dto> dataList) {
        AjaxResult res = new AjaxResult();

        for (ReportReview001Dto tmp:dataList) {
            String ss =getReviewDataList(tmp);
            if(StringUtil.isNotEmpty(ss)){
                return new AjaxResult("您操作的数据中有数据的托管复核状态已发生变化，请刷新后重试!", MessageType.错误消息);
            }
        }
        return res;
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
        paraMap.put("reviewTgStatus",DictCodeListExt5.托管复核状态_已推送披露.getValue());
        paraMap.put("reviewTgTime", DateUtil.now());
        paraMap.put("reviewTgUser", LoginUserUtil.getUserName());

        paraMap.put("idList", idList);

        reportReviewTg001Mapper.updateReviewInfo(paraMap);
    }

    public String getReviewDataList(ReportReview001Dto tmp){

        return reportReviewTg001Mapper.getReviewDataList(tmp);
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
        paraMap.put("reviewStatus",DictCodeListExt5.复核状态_被托管退回.getValue());
        paraMap.put("reviewTgTime", DateUtil.now());
        paraMap.put("updateUser", LoginUserUtil.getUserName());
        paraMap.put("idList", idList);
        reportReviewTg001Mapper.updateReturnStatus(paraMap);
    }

    public Page<SelectOptionDto> getTgUserList(Map<String, Object> map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<SelectOptionDto> result = queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> reportReviewTg001Mapper.getTgUserList(map));
        return result;
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/reveal/reportreviewtg/conf/export_ReportReviewTg001.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ReportReview001Dto> selectPageData(Map<String, Object> selectParam) {
        String datas = (String) selectParam.get("datas");
        if (StringUtil.isNotEmpty(datas)) {
            try {
                List<ReportReview001Dto>dataList= JsonUtils.json2List(datas, ReportReview001Dto.class);
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
