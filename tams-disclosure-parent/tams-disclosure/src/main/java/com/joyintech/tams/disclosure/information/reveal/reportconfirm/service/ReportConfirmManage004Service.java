package com.joyintech.tams.disclosure.information.reveal.reportconfirm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao.ReportConfirmManage004Mapper;

@Service
public class ReportConfirmManage004Service extends BaseService<BaseEntity> {

    @Autowired
    private RepRevealMapper repRevealMapper;

    @Autowired
    private ProgressBarService progressBarService;

    @Autowired
    ReportConfirmManage004Mapper reportConfirmManage004Mapper;

    /**
     * 手工上传入库操作
     * 
     * @param params
     * @return
     */
    public List<ValidMessage> insertRepReveal(Map<String, Object> params) {
        RepReveal repReveal = new RepReveal();
        Date nowTime = new Date();

        // 人工上传时间
        repReveal.setTime(nowTime);
        // 产品代码
        repReveal.setProdid(params.get("prodId").toString());
        // 产品名称
        repReveal.setProdName(params.get("prodName").toString());
        // 报表类别
        repReveal.setReportCategory(params.get("reportType").toString());
        /* repReveal.setReportCategory(params.get("reportCategory").toString()); */
        // ID
        repReveal.setId(IDGenerator.generate());
        // 状态
        repReveal.setStatus(DisclosureDictCodeList.数据状态_有效.getValue());
        // 披露渠道
        repReveal.setRevealChannel(params.get("revealChannel").toString());
        if (StringUtil.isNotEmpty(repReveal.getRevealChannel())) {
            List<String> revealChannelList = new ArrayList<String>();
            revealChannelList = StringUtil.splitToList(",", repReveal.getRevealChannel());
            if (revealChannelList.contains("03")) {
                revealChannelList.remove("03");
                revealChannelList.add(DictCodeListExt2.披露渠道_其他.getValue());
            }
            repReveal.setRevealChannel(StringUtil.joinString(revealChannelList, ","));
        }
        // 报表名称
        repReveal.setReportName(params.get("reportName").toString());
        // 是否自动确认
        repReveal.setIsConfirm(params.get("isConfirm").toString());
        if (repReveal.getIsConfirm().equals(DictCodeListExt1.确认类型_自动.getValue())) {
            // 确认状态
            repReveal.setConfirmStatus(DictCodeList.确认状态_已确认.getValue());
            repReveal.setConfirmDate(nowTime);
        }
        else {
            // 确认状态
            repReveal.setConfirmStatus(DictCodeList.确认状态_未确认.getValue());
        }
        // 是否自动披露
        repReveal.setIsReveal(params.get("isReveal").toString());
        // modify by guozhongbao 更改披露状态未做ftp上传，此处交给批处理 20181230 start
        // if(repReveal.getIsReveal().equals(ReportDictCodeList.披露方式_自动.getValue())) {
        // // 披露状态
        // repReveal.setRevealStatus(ReportDictCodeList.披露状态_已披露.getValue());
        // repReveal.setRevealDate(nowTime);
        // }
        // else {
        // // 披露状态
        // repReveal.setRevealStatus(ReportDictCodeList.披露状态_未披露.getValue());
        // }
        repReveal.setRevealStatus(DictCodeListExt2.披露状态_未披露.getValue());
        // modify by guozhongbao 更改披露状态未做上传ftp上传，此处交给批处理 20181230 end
        // 上传说明
        repReveal.setDetail(params.get("uploadDescribe").toString());
        // 文件名字
        String filename = params.get("fileName").toString().replace(" ", "");
        repReveal.setFilename(filename);
        // 文件路径
        repReveal.setFilepath(params.get("filePath").toString());
        // 报告日期
        String startDate = params.get("reportDateBegin").toString();
        String endDate = params.get("reportDateEnd").toString();
        if (StringUtil.isNotEmpty(startDate)) {
            // 报告开始日期
            repReveal.setStartDate(DateUtil.parse(startDate));
            // 报告结束日期
            repReveal.setEndDate(DateUtil.parse(endDate));
        }
        String revealEndDatestr = params.get("revealEndDate").toString();
        if (StringUtil.isNotEmpty(revealEndDatestr) && !StringUtil.equals("", revealEndDatestr)) {
            repReveal.setRevealEndDate(DateUtil.parse(revealEndDatestr + " 23:59:00"));
        }
        List<ValidMessage> checkList = checkInfo(repReveal);
        if (StringUtil.isEmptyList(checkList)) {
            repRevealMapper.insert(repReveal);
            return null;
        }
        else {
            return checkList;
        }
    }

    /**
     * 读取文件
     * 
     * @param request
     * @return
     */
    public void readFile(HttpServletRequest request)
        throws Exception {
        progressBarService.updatePercent("ReportConfirmManage004", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
    }

    /**
     * 获取披露渠道信息
     * 
     * @param request
     * @return listSelectDto
     */
    public List<SelectOptionDto> getRevealChannel() {
        // List<? extends DataDictItem> channelList =
        // SysDataUtil.getDataDictItem("REVEAL_CHANNEL");
        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
        SelectOptionDto s1 = new SelectOptionDto();
        SelectOptionDto s2 = new SelectOptionDto();
        SelectOptionDto s3 = new SelectOptionDto();
        s1.setId("01");
        s1.setText("官网");
        s2.setId("02");
        s2.setText("点对点");
        s3.setId("03");
        s3.setText("其他");
        listSelectDto.add(s1);
        listSelectDto.add(s2);
        listSelectDto.add(s3);
        // for(DataDictItem channel : channelList){
        // SelectOptionDto dto = new SelectOptionDto();
        // dto.setId(channel.getItemCode());
        // dto.setText(channel.getItemValue());
        // listSelectDto.add(dto);
        // }
        return listSelectDto;
    }

    /**
     * 获取报表类别
     * 
     * @param request
     * @return listSelectDto
     */
    public List<SelectOptionDto> getReportStyle() {
        List<? extends DataDictItem> reportStyleList = SysDataUtil.getDataDictItem("REPORT_STYLE");
        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
        for (DataDictItem reportStyle : reportStyleList) {
            SelectOptionDto dto = new SelectOptionDto();
            dto.setId(reportStyle.getItemCode());
            dto.setText(reportStyle.getItemValue());
            listSelectDto.add(dto);
        }

        return listSelectDto;
    }

    /**
     * 验证报表信息是否为空
     * 
     * @param RepReveal repReveal
     * @return List<ValidMessage>
     */
    public List<ValidMessage> checkInfo(RepReveal repReveal) {
        List<ValidMessage> checkList = new ArrayList<ValidMessage>();
    	//报表人工上传时，有些报表并没有产品名称，要求放开产品名称验证
//        if (StringUtil.isEmpty(repReveal.getProdid())) {
//            checkList.add(new ValidMessage("产品名称不能为空"));
//        }
        if (StringUtil.isEmpty(repReveal.getReportName())) {
            checkList.add(new ValidMessage("报表名称不能为空"));
        }
        if (StringUtil.isEmpty(repReveal.getReportCategory())) {
            checkList.add(new ValidMessage("报表类别不能为空"));
        }
        if (StringUtil.isEmpty(repReveal.getFilename())) {
            checkList.add(new ValidMessage("上传文件不能为空"));
        }
        if (repReveal.getStartDate() == null) {
            checkList.add(new ValidMessage("报告日期不能为空"));
        }
        if (repReveal.getRevealEndDate() == null) {
            checkList.add(new ValidMessage("披露截止日不能为空"));
        }
        if (repReveal.getRevealChannel() == null || StringUtil.equals("", repReveal.getRevealChannel())) {
            checkList.add(new ValidMessage("披露渠道不能为空"));
        }
        if (repReveal.getIsConfirm() == null || StringUtil.equals("", repReveal.getIsConfirm())) {
            checkList.add(new ValidMessage("确认方式不能为空"));
        }
        if (repReveal.getIsReveal() == null || StringUtil.equals("", repReveal.getIsReveal())) {
            checkList.add(new ValidMessage("披露方式不能为空"));
        }
        return checkList;
    }

    /**
     * 查询相同报表数据
     * 
     * @param Map<String, Object>
     * @return 产品信息
     */
    public int querySameCount(Map<String, Object> params) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage004/querySameCount", params,
            Integer.class);
    }

    /**
     * 移除进度条
     * 
     * @param request
     * @return
     */
    public void removePercent(HttpServletRequest request)
        throws Exception {
        progressBarService.removePercent("ReportConfirmManage004", ProgressBarService.FILE_UPLOAD_ID);
    }

}
