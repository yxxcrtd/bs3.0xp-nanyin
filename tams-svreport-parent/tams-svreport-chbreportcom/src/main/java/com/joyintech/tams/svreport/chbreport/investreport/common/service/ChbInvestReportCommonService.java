package com.joyintech.tams.svreport.chbreport.investreport.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbPrdBaseinfoMapper;
import com.joyintech.tams.common.base.dao.RepInstallLogDetailMapper;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto.FtpDetailDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service.FtpDetailService;
import com.joyintech.tams.svreport.chbreport.investreport.ChbExpireOperation.service.ChbExpireOperationService;
import com.joyintech.tams.svreport.chbreport.investreport.chbestablishoperation.service.ChbEstablishOperationService;
//import com.joyintech.tams.svreport.chbreport.investreport.chbexpireoperation.service.ChbExpireOperationService;
import com.joyintech.tams.svreport.chbreport.investreport.chbinvestoperation.service.ChbInvestOperationService;
import com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.service.ChbPeriodicService;
import com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.service.ChbNetWorthOperationService;
import com.joyintech.tams.svreport.chbreport.investreport.common.dao.ChbInvestReportCommonMapper;
import com.joyintech.tams.svreport.chbreport.investreport.common.dto.ReportGenerate001Dto;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportConstant;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName ChbInvestOperationService
 * @Description: 非标投资运行报告查询service
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@Service
public class ChbInvestReportCommonService extends BaseService {

    @Autowired
    private ChbInvestReportCommonMapper chbInvestReportCommonMapper;

    @Autowired
    private RepRevealMapper repRevealMapper;

    @Autowired
    private ChbPrdBaseinfoMapper chbPrdBaseinfoMapper;
    /**
     * 日志详情表mapper
     */
    @Autowired
    private RepInstallLogDetailMapper repInstallLogDetailMapper;

    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;


    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    /**
     * 理财产品非标投资运作报告service
     */
    @Autowired
    private ChbInvestOperationService chbInvestOperationService;

    /**
     * 净值披露service
     */
    @Autowired
    private ChbNetWorthOperationService chbNetWorthOperationService;

    /**
     * 成立公告service
     */
    @Autowired
    private ChbEstablishOperationService chbEstablishOperationService;

    /**
     * 到期公告service
     */
    @Autowired
    private ChbExpireOperationService chbExpireOperationService;

    /**
     * 中招报告发送
     */
    @Autowired
    private FtpDetailService ftpDetailService;

    /**
     * 定期报告service
     */
    @Autowired
    private ChbPeriodicService chbPeriodicService;

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ChbInvestReportCommonService.class);

    /**
     * 查询产品下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryPrdInfoPage(Map<String, Object> param) {
        Page<SelectOptionDto> page = null;
        if (param.get(GlobalConstant.PARAM_PAGE) != null
                && param.get(GlobalConstant.PARAM_LENGTH) != null) {
            int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
            int length = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
            page = super.queryForPage(
                    pagenum, length, () -> chbInvestReportCommonMapper.queryPrdInfoList(param));
        } else {
            page = queryForPage(ChbInvestReportCommonMapper.class, "queryPrdInfoList", param);
        }
        return page;
    }

    /**
     * 查询报告类型
     * @param param
     * @return
     */
    public Page<RepFtpPath> queryRepFtpPathList(Map<String, Object> param) {
        Page<RepFtpPath> page = null;
        if (param.get(GlobalConstant.PARAM_PAGE) != null
                && param.get(GlobalConstant.PARAM_LENGTH) != null) {
            int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
            int length = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
            page = super.queryForPage(
                    pagenum, length, () -> chbInvestReportCommonMapper.queryRepFtpPathList(param));
        } else {
            page = queryForPage(ChbInvestReportCommonMapper.class, "queryRepFtpPathList", param);
        }
        return page;
    }

    /**
     * 查询报告数据
     * @param param
     * @return
     */
    public Page<ReportGenerate001Dto> queryDetailData(Map<String, Object> param) {
        Page<ReportGenerate001Dto> page = null;
        Object repId = param.get("repId");
        if (param.get(GlobalConstant.PARAM_PAGE) != null
                && param.get(GlobalConstant.PARAM_LENGTH) != null) {
            int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
            int length = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
            if (StringUtil.equals(DictCodeListExt3.中债披露报告_成立公告.getValue(), repId.toString())) {
                page = super.queryForPage(
                        pagenum, length, () -> chbInvestReportCommonMapper.queryDetailData01(param));
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(), repId.toString())) {
                page = super.queryForPage(
                        pagenum, length, () -> chbInvestReportCommonMapper.queryDetailData02(param));
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_到期公告.getValue(), repId.toString())) {
                page = super.queryForPage(
                        pagenum, length, () -> chbInvestReportCommonMapper.queryDetailData03(param));
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品定期公告.getValue(), repId.toString())) {

            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), repId.toString())) {
                page = super.queryForPage(
                        pagenum, length, () -> chbInvestReportCommonMapper.queryDetailData05(param));
            }

        } else {
            if (StringUtil.equals(DictCodeListExt3.中债披露报告_成立公告.getValue(), repId.toString())) {
                page = queryForPage(ChbInvestReportCommonMapper.class, "queryDetailData01", param);
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(), repId.toString())) {
                page = queryForPage(ChbInvestReportCommonMapper.class, "queryDetailData02", param);
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_到期公告.getValue(), repId.toString())) {
                page = queryForPage(ChbInvestReportCommonMapper.class, "queryDetailData03", param);
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品定期公告.getValue(), repId.toString())) {

            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), repId.toString())) {
                page = queryForPage(ChbInvestReportCommonMapper.class, "queryDetailData05", param);
            }

        }
        return page;
    }

    /**
     * 根据条件查询报表数据
     * @param param
     * @return
     */
    public RepReveal selectReprevealByParams(Map<String, Object> param){
        return chbInvestReportCommonMapper.selectReprevealByParams(param);
    }

    /**
     * 根据条件更新已生成的数据状态
     * @param param
     * @return
     */
    public void updateReprevealByParams(Map<String, Object> param){
        chbInvestReportCommonMapper.updateReprevealByParams(param);
    }
    /**
     * 生成报表确认数据
     * @param repId
     * @param prodId
     * @param startDate
     * @param endDate
     * @param fileKey
     * @param fileName
     * @param ReportName
     * @param reportCategory
     * @param isConfirm
     * @param isReveal
     * @return
     */
    public boolean insertRepReveal(String repId, String prodId, String startDate, String endDate, String fileKey,String fileName,
                                   String reportName, String reportCategory, String isConfirm, String isReveal){
        RepReveal repReveal = new RepReveal();
        repReveal.setId(IDGenerator.generate());
        repReveal.setRepId(repId);
        repReveal.setSysFilepath(fileKey);
        repReveal.setSysFilename(fileName);
        repReveal.setReportName(reportName);
        if(StringUtil.isNotEmpty(prodId)){
            repReveal.setProdid(prodId);
            //查询产品名称的全称
            ChbPrdBaseinfo chbPrdBaseinfo = chbPrdBaseinfoMapper.selectByProdid(prodId);
            if(chbPrdBaseinfo != null) {
                repReveal.setProdName(chbPrdBaseinfo.getProdName());
            }
        }
        if(StringUtil.isNotEmpty(startDate)){
            repReveal.setStartDate(DateUtil.parse(startDate));
        }
        if(StringUtil.isNotEmpty(endDate)){
            repReveal.setEndDate(DateUtil.parse(endDate));
        }
        if(StringUtil.equals(DictCodeListExt1.确认类型_自动.getValue(), isConfirm)) {
            repReveal.setConfirmStatus(DictCodeList.确认状态_已确认.getValue());
        }
        else {
            repReveal.setConfirmStatus(DictCodeList.确认状态_未确认.getValue());
        }
        if(StringUtil.equals(DictCodeListExt1.确认类型_自动.getValue(), isReveal)) {
            repReveal.setRevealStatus(DictCodeListExt2.披露状态_已披露.getValue());
        }
        else {
            repReveal.setRevealStatus(DictCodeListExt2.披露状态_未披露.getValue());
        }
        repReveal.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
        // 系统生成确认方式和披露方式设置为手动
        repReveal.setIsConfirm(isConfirm);
        repReveal.setIsReveal(isReveal);
        repReveal.setReportCategory(reportCategory);
        repRevealMapper.insert(repReveal);

        // 插入日志
        // 将待组装数据成日志信息
        RepInstallLogDetail repInstallLogDetail = new RepInstallLogDetail();
        //主键 id
        repInstallLogDetail.setId(IDGenerator.generate());
        //执行状态
        repInstallLogDetail.setExecStatus(DictCodeListExt3.日志状态_生成成功.getValue());
        //报表编号
        repInstallLogDetail.setRepId(repReveal.getRepId());
        //报表名称
        repInstallLogDetail.setRepName(repReveal.getReportName());
        //产品编号
        repInstallLogDetail.setProdid(repReveal.getProdid());
        //文件类型
        repInstallLogDetail.setFileType(repReveal.getSysFilename().substring(repReveal.getSysFilename().lastIndexOf(".") + 1));
        //产品名称
        repInstallLogDetail.setProdName(repReveal.getProdName());
        //开始日
        repInstallLogDetail.setStartDate(repReveal.getStartDate());
        //结束日日
        repInstallLogDetail.setEndDate(repReveal.getEndDate());
        //披露渠道
        repInstallLogDetail.setRevealChannel(repReveal.getRevealChannel());
        //披露截止日
        repInstallLogDetail.setRevealEndDate(repReveal.getRevealEndDate());
        //是否自动确认
        repInstallLogDetail.setIsConfirm(repReveal.getIsConfirm());
        //是否自动披露
        repInstallLogDetail.setIsReveal(repReveal.getIsConfirm());
        //设置数据状态
        repInstallLogDetail.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
        //更新日志表
        repInstallLogDetailMapper.insert(repInstallLogDetail);
        //自动披露时，需要调用披露方法
        if(StringUtil.equals(DictCodeListExt1.确认类型_自动.getValue(), isReveal)) {
            //发送
            return doSendReport(repReveal);
        }
        return true;
    }

    /**
     * 生成文件
     * @param param 参数：包含产品代码、日期等
     * @param isConfirm 自动确认还是手工确认
     * @param isReveal 自动披露还是手工披露
     * @return
     * @throws Exception
     */
    public boolean createReportDoc(Map<String, Object> param, String isConfirm, String isReveal) throws Exception {
        Object repId = param.get("repId");
        File file = null;//生成的文件
        if(repId != null) {
            String prodId = null;//产品代码
            String startDate = null;//报告开始日期
            String endDate = null;//报告结束日期，只有一个日期是，存储该值
            String reportName = null;//报告名称
            String reportCategory = repId.toString();//报告分类，跟报告编号保持一致
            if (StringUtil.equals(DictCodeListExt3.中债披露报告_成立公告.getValue(), repId.toString())) {
                prodId = (String) param.get("prodId");
                endDate = (String) param.get("endDate");
                reportName = DictCodeListExt3.中债披露报告_成立公告.getCodeName();
                file = chbEstablishOperationService.createDoc(param, reportName);
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(), repId.toString())) {
                prodId = (String) param.get("prodId");
                endDate = (String) param.get("endDate");
                reportName = DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getCodeName();
                file = chbInvestOperationService.createDoc(param, reportName);
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_到期公告.getValue(), repId.toString())) {
                prodId = (String) param.get("prodId");
                endDate = (String) param.get("endDate");
                reportName = DictCodeListExt3.中债披露报告_到期公告.getCodeName();
                file = chbExpireOperationService.createDoc(param, reportName);
            }
            //定期公告里面要考虑传递进去的产品代码，区分出来净值、货币，另外还需要区分出来是否含有多份额的子产品
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品定期公告.getValue(), repId.toString())) {
                prodId = (String) param.get("prodId");
                endDate = (String) param.get("endDate");
                reportName = DictCodeListExt3.中债披露报告_产品定期公告.getCodeName();
                file = chbPeriodicService.createDoc(param, reportName);
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), repId.toString())) {
                prodId = (String) param.get("prodId");
                endDate = (String) param.get("endDate");
                reportName = DictCodeListExt3.中债披露报告_产品净值披露.getCodeName();
                file = chbNetWorthOperationService.createExcel(param, reportName);
            }
            if(file != null) {
                UploadFile uploadFile = new UploadFile(file.getName(), IDGenerator.generate(), file);
                //存入SYS_UPLOAD_FILE_INFO表
                if(null != fileAttachmentProcess) {
                    fileAttachmentProcess.upload(uploadFile);
                }
                //add by leipeng 上传文件处理
                SysUploadFileInfo sysUploadFileInfo = new SysUploadFileInfo();
                sysUploadFileInfo.setSeqNo(uploadFile.getFileNo());
                sysUploadFileInfo.setFileName(FileNameUtil.formatFileName(uploadFile.getTargetFile().getName()));
                sysUploadFileInfo.setPath(uploadFile.getTargetFile().getPath());
                joyinDFSTemplate.upload(sysUploadFileInfo);
                this.updateReprevealByParams(param);//更新已有数据
                this.insertRepReveal(repId.toString(), prodId, startDate, endDate, uploadFile.getFileNo(), uploadFile.getSrcName(),
                        reportName, reportCategory, isConfirm, isReveal);
                return true;
            }
        }
        return false;
    }

    /**
     * 导出数据
     * @param param
     * @return
     * @throws Exception
     */
    public ResponseEntity<byte[]> exportDoc(Map<String, Object> param) throws Exception {
        Object repId = param.get("repId");
        if(repId != null) {
            if (StringUtil.equals(DictCodeListExt3.中债披露报告_成立公告.getValue(), repId.toString())) {
                return chbEstablishOperationService.exportDoc(param, DictCodeListExt3.中债披露报告_成立公告.getCodeName());
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getValue(), repId.toString())) {
                return chbInvestOperationService.exportDoc(param, DictCodeListExt3.中债披露报告_理财产品非标投资运作报告.getCodeName());
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_到期公告.getValue(), repId.toString())) {
                return chbExpireOperationService.exportDoc(param, DictCodeListExt3.中债披露报告_到期公告.getCodeName());
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品定期公告.getValue(), repId.toString())) {
                return chbExpireOperationService.exportDoc(param, DictCodeListExt3.中债披露报告_产品定期公告.getCodeName());
            }
            else if (StringUtil.equals(DictCodeListExt3.中债披露报告_产品净值披露.getValue(), repId.toString())) {
                return  chbNetWorthOperationService.exportExcel(param, DictCodeListExt3.中债披露报告_产品净值披露.getCodeName());
            }
        }
        return null;
    }

    /**
     * 根据list查询 生成的数据
     * @param list
     * @return
     */
    public List<RepReveal> selectReprevealListByDatas(List<ReportGenerate001Dto> list){
        return chbInvestReportCommonMapper.selectReprevealListByDatas(list);
    }

    /**
     * 查询定期报告要生成的数据
     * @param param
     * @return
     */
    public List<ReportGenerate001Dto> selectDqReportList(Map<String, Object> param){
        return chbInvestReportCommonMapper.selectDqReportList(param);
    }

    /**
     * 获取定期报告的结束日期
     * @param reportType
     * @param year
     * @return
     */
    public String getDqEndDate(String reportType, String year) {
        if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_一季度报.getValue())) {
            return year + ChbInvestReportConstant.三月最后一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_二季度报.getValue())) {
            return year + ChbInvestReportConstant.六月最后一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_上半年报.getValue())) {
            return year + ChbInvestReportConstant.六月最后一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_三季度报.getValue())) {
            return year + ChbInvestReportConstant.九月最后一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_四季度报.getValue())) {
            return year + ChbInvestReportConstant.十二月最后一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_下半年报.getValue())) {
            return year + ChbInvestReportConstant.十二月最后一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_年报.getValue())) {
            return year + ChbInvestReportConstant.十二月最后一天;
        }

        return ChbInvestReportConstant.空字符;
    }

    /**
     * 获取定期报告数据起始日期
     * @param reportType
     * @param year
     * @return
     */
    public String getDqStartDate(String reportType, String year) {
        if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_一季度报.getValue())) {
            return year + ChbInvestReportConstant.一月第一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_二季度报.getValue())) {
            return year + ChbInvestReportConstant.四月第一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_上半年报.getValue())) {
            return year + ChbInvestReportConstant.一月第一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_三季度报.getValue())) {
            return year + ChbInvestReportConstant.十月第一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_四季度报.getValue())) {
            return year + ChbInvestReportConstant.十二月最后一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_下半年报.getValue())) {
            return year + ChbInvestReportConstant.七月第一天;
        }
        else if(StringUtil.equals(reportType, DictCodeListExt3.中债定期报告类型_年报.getValue())) {
            return year + ChbInvestReportConstant.一月第一天;
        }

        return ChbInvestReportConstant.空字符;
    }


    /**
     * 调用发送
     * @param repReveal
     * @return
     */
    private Boolean doSendReport(RepReveal repReveal) {
        FtpDetailDto ftpDetailDto = new FtpDetailDto();
        ftpDetailDto.setSysFilepath(repReveal.getSysFilepath());
        ftpDetailDto.setProdId(repReveal.getProdid());
        ftpDetailDto.setReportId(repReveal.getRepId());
        ftpDetailDto.setReportCategory(repReveal.getReportCategory()); // 获取ftp路径用
        ftpDetailDto.setOption(DictCodeListExt2.披露方式_手动.getValue()); //手工操作
        if(StringUtil.isNotEmpty(repReveal.getStartDate())) {
            ftpDetailDto.setStatrDate(repReveal.getStartDate());
        }
        if(StringUtil.isNotEmpty(repReveal.getEndDate())) {
            ftpDetailDto.setEndDate(repReveal.getEndDate());
        }
        ftpDetailDto.setRevealChannel(repReveal.getRevealChannel());
        ftpDetailDto.setRevealId(repReveal.getId());
        return ftpDetailService.doReveal(ftpDetailDto);
    }

}
