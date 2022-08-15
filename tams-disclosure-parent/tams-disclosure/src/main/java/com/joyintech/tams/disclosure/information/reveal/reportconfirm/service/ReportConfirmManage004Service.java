package com.joyintech.tams.disclosure.information.reveal.reportconfirm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.google.json.JsonSanitizer;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceCommentDto;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage004Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageCommentDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.UploadFileDto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.constant.ProcessType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.entity.WorkflowEntity;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.service.BusinessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao.UploadattAchment001Mapper;
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
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao.ReportConfirmManage004Mapper;

@Service
public class ReportConfirmManage004Service extends BaseService<ReportConfirmManage004Dto> implements BaseProcessService<ReportConfirmManage004Dto> {

    private static final Logger logger = LoggerFactory.getLogger(ReportConfirmManage004Service.class);

    @Autowired
    private RepRevealMapper repRevealMapper;

    @Autowired
    private ProgressBarService progressBarService;

    @Autowired
    ReportConfirmManage004Mapper reportConfirmManage004Mapper;
    @Autowired
    UploadattAchment001Mapper uploadattAchment001Mapper;
    @Autowired
    private FileRemoteService minioFileRemoteService;

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
        try{
            UploadFile uploadFile = this.uploadMinioFile(repReveal.getFilepath(),repReveal.getFilename());
            repReveal.setFilename(uploadFile.getFileName());
            repReveal.setFilepath(uploadFile.getFileNo());
        }catch(Exception e){
            logger.error("上传文件异常",e);
            checkList.add(new ValidMessage("附件上传远程服务失败"));
        }
        if (StringUtil.isEmptyList(checkList)) {
            repRevealMapper.insert(repReveal);
            return null;
        }
        else {
            return checkList;
        }
    }
    /**
     * @Author ZY22350
     * @Description //上传到文件服务器
     * @Date 19:20 2022/8/5
     * @Param [fileNo, fileName]
     * @return com.joyintech.framework.base.dto.UploadFile
     **/
    private UploadFile uploadMinioFile(String fileNo,String fileName){
        UploadFile uploadFile =minioFileRemoteService.getUploadFileByFileNo(fileNo);
        String filePath = uploadFile.getTargetFile().getPath();
        String newfilePath = filePath.replace(":\\","://");
        newfilePath = newfilePath.replace("\\","/");
        String basePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if(StringUtil.isNotEmpty(basePath)){
            newfilePath = newfilePath.replace(basePath,"");
        }
        if(newfilePath.toLowerCase(Locale.CHINA).startsWith("http:")||newfilePath.toLowerCase(Locale.CHINA).startsWith("https:")){
            return uploadFile;
        }

        if(logger.isDebugEnabled()){
            logger.debug("上传文件开始,文件名称:{}",fileName);
        }
        FileInputStream fis = null;
        String newFileNo = IDGenerator.generate();
        try {
            fis = new FileInputStream(filePath);
            File localFile = new SafelyFile(newfilePath);
            UploadFile newUploadFile = new UploadFile(fileName, newFileNo,localFile);
            newUploadFile = minioFileRemoteService.uploadFile(newUploadFile,fis);
            return newUploadFile;
        }catch(Exception e){
            logger.error("上传文件失败",e);
            throw new RuntimeException("本地文件上传Minio失败");
        }finally{
            if(fis != null ){
                try{
                    fis.close();
                }catch (IOException e){
                    logger.error("关闭流异常",e);
                }
            }
        }
    }

    /**
     * 读取文件
     * 
     * @param request
     * @return
     */
    public void readFile(HttpServletRequest request){
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
//        SelectOptionDto s2 = new SelectOptionDto();
        SelectOptionDto s3 = new SelectOptionDto();
        s1.setId("01");
        s1.setText("官网");
//        update by zoujiamin 2021/05/28 TISHKBS-2070 start
//        s2.setId("02");
//        s2.setText("点对点");
//        update by zoujiamin 2021/05/28 TISHKBS-2070 end
        s3.setId("99");
        s3.setText("其他");
        listSelectDto.add(s1);
//        listSelectDto.add(s2);
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
    public void removePercent(HttpServletRequest request){
        progressBarService.removePercent("ReportConfirmManage004", ProgressBarService.FILE_UPLOAD_ID);
    }

    public List<UploadFileDto> getFileByBusinessCode(Map<String,String> param){
        return uploadattAchment001Mapper.getFileByBusinessCode(param);
    }

    @Override
    public void doInsert(ReportConfirmManage004Dto dto, OperationType type) {

        Date nowTime = new Date();

        // 人工上传时间
        dto.setTime(nowTime);
        dto.setRevealStatus(DictCodeListExt2.披露状态_未披露.getValue());
        if (dto.getIsConfirm().equals(DictCodeListExt1.确认类型_自动.getValue())) {
            // 确认状态
            dto.setConfirmStatus(DictCodeList.确认状态_已确认.getValue());
            dto.setConfirmDate(nowTime);
        }
        else {
            // 确认状态
            dto.setConfirmStatus(DictCodeList.确认状态_未确认.getValue());
        }
        if (StringUtil.isNotEmpty(dto.getRevealEndDate()) && !StringUtil.equals("", dto.getRevealEndDate())) {
            dto.setRevealEndDate(DateUtil.parse(DateUtil.format(dto.getRevealEndDate()) + " 23:59:00"));
        }


        Map<String,String> param = new HashMap<>();
        param.put("id",dto.getId());
        //删除旧数据
        reportConfirmManage004Mapper.deleteInfo(param);
        //保存新数据
        reportConfirmManage004Mapper.saveInfo(dto);

        if (CollectionUtils.isNotEmpty(dto.getUploadFileList2())) {
            for (UploadFileDto file : dto.getUploadFileList2()){
                UploadFileDto sysFile = new UploadFileDto();
                sysFile.setBusinessId(dto.getId());
                sysFile.setSeqNo(file.getFileNo());
                sysFile.setFileType("02");
                reportConfirmManage004Mapper.updateUplodFile(sysFile);
            }
        }
    }
    /**
     *  流程中保存按钮
     * */
    @Override
    public void doUpdate(ReportConfirmManage004Dto dto){
        //从待办下发起提交和保存,或者是退回后在发起提交()
        doInsert(dto, null);
        //复核人向下流转流程
        //保存修订意见
        if(StringUtil.isNotEmpty(dto.getComment())){
            String infoSeqno = dto.getId();
            ReportConfirmManageCommentDto commentDto = new ReportConfirmManageCommentDto(IDGenerator.generate(),infoSeqno,dto.getComment());
            reportConfirmManage004Mapper.saveInfoComment(commentDto);
            //修订意见与修订附件关联
            reportConfirmManage004Mapper.relationFileAndComment(commentDto);
        }

    }

    public void doBack(ReportConfirmManage004Dto entity) {
        //退回删除已上传,未关联批注的附件
        reportConfirmManage004Mapper.deleteUploadFile(entity.getId());

    }

    @Override
    public void doComplete(ReportConfirmManage004Dto dto, OperationType operationType) {
        dto.setStatus("E");
        reportConfirmManage004Mapper.updateInfo(dto);
    }

    /**
     *  流程中需要增加批注,不能进行批量审批
     **/
    @Override
    public String checkForSubmit(FlowEntity entity, boolean isMutiApproval)  {
        if (isMutiApproval) {
            return "报表确认管理不能批量审批";
        }
        try{
            if(ProcessType.SUBMIT.equals(entity.getProcessType()) || ProcessType.BACK.equals(entity.getProcessType())){
                FlowEntity flowEntity = WorkflowContext.getProcessService().getRuntimeEntity(entity.getProcessInstanceId());
                BusinessService<WorkflowEntity> businessService = WorkflowContext.getBusinessService(flowEntity.getBusinessService());
                Class<?> clz = WorkFlowUtil.getClassByBusinessServiceName(flowEntity.getBusinessService());
                ProcessMark businessEntity = WorkFlowUtil.getBusinessObjectFromRequest(clz, true);
                Object a = JsonUtils.OBJECT_MAPPER.readValue(JsonSanitizer.sanitize(businessEntity.getBusinessData()), GenericsUtils.getSuperClassGenricType(ProxyUtil.getTargetClass(businessService.getClass())));
                ReportConfirmManage004Dto infoDto = (ReportConfirmManage004Dto)a;

                String firstNodeId = reportConfirmManage004Mapper.getFirstNodeId(entity.getWorkflowId());
                if(!entity.getCurNodeId().equals(firstNodeId)){
                    //非第一个节点提交,需要修订意见
                    if(StringUtil.isEmpty(infoDto.getComment())){
                        return "修订意见不能为空!";
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void doDelete(String workflowId) {
        Map<String,String> param = new HashMap<>();
        param.put("workflowId",workflowId);
        reportConfirmManage004Mapper.deleteInfo(param);
    }

    public ReportConfirmManage004Dto queryInfo(Map<String,String> paramMap){
        return reportConfirmManage004Mapper.queryInfo(paramMap);
    }

    public List<ReportConfirmManageCommentDto> getInfo(String infoSeqno){
        return reportConfirmManage004Mapper.getInfo(infoSeqno);
    }

    public List<SelectOptionDto> getPrdInfoSelect(Map<String, Object> map) {
        List<SelectOptionDto> result = reportConfirmManage004Mapper.getPrdInfoSelect(map);
        return result;
    }
}
