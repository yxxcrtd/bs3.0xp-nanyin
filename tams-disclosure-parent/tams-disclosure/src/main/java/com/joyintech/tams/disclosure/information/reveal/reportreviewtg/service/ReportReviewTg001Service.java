package com.joyintech.tams.disclosure.information.reveal.reportreviewtg.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportreviewtg.dao.ReportReviewTg001Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return  queryForPageForSelectReturn(()->reportReviewTg001Mapper.findPage(param));
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

}
