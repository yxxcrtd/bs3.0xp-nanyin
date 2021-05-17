package com.joyintech.tams.disclosure.information.reveal.reportreveal.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.file.SafelyFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.util.SftpTools;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao.ReportConfirmManage001Mapper;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.BokFileInfoDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.BokFileTransfer003Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.BokNetValueCfmFileDto;

/**
 * 附件上传service
 * 
 * @author Administrator
 *
 */
@Service
public class ReportRevealManage003Service extends CommonExportService<BokFileTransfer003Dto>  {

	@Autowired
	private ProgressBarService progressBarService;
	
	@Autowired
    private ReportConfirmManage001Mapper reportConfirmManage001Mapper;
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportRevealManage003Service.class);

	/**
	 * 读取文件并上传到文件服务器
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void readFile(HttpServletRequest request) throws Exception {
		// 文件本地路径
		String filePath = request.getParameter("fileName");
		request.getParameter("id");
		request.getParameter("fileNo");
		request.getParameter("fileNameOnly");
		
		//产品id
		String finprodId = request.getParameter("finprodId");
		
		//估值表余额表标识
		String valBalFlag = request.getParameter("valBalFlag");
        if(StringUtil.isNotEmpty(valBalFlag)){
//            if(valBalFlag.equals(DictCodeList.文件类型_估值表.getCodeName())){
//                valBalFlag = DictCodeList.文件类型_估值表.getValue();
//            }else {
//                valBalFlag = DictCodeList.文件类型_余额表.getValue();
//            }
        }
		
		//估值表余额表标识
        String valDateS = request.getParameter("valDate");
        String valDate = valDateS.replace("-", "");
        
		//产品唯一标识
        String prodName = finprodId+valDate+valBalFlag;
		// 文件名称
		String fileName = FileNameUtil.formatFileName(filePath);
		
		File file = new SafelyFile(filePath);
		boolean rec = file.renameTo(new SafelyFile(fileName));
		if(rec){
			logger.info("处理成功");
		}		
//		String execDate = request.getParameter("execDate");

		// 主机IP
		String ip = CacheUtil.getSysParam("FILEUPLOAD_HOST");

		// 主机端口
		Integer port = Integer.parseInt(CacheUtil.getSysParam("FILEUPLOAD_PORT"));

		// 主机用户名
		String username = CacheUtil.getSysParam("FILEUPLOAD_USERNAME");

		// 主机密码
		String password = CacheUtil.getSysParam("FILEUPLOAD_PASSWORD");

		// 主机远程路径
		String remotePath = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH");
//		String remotePath = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH") + "/"
//				+ DateUtil.format(DateUtil.parse(execDate), DateUtil.DEFAULT_OTHER_FORMAT);

		SftpTools sftpTools = new SftpTools();
		// sftp登录
		sftpTools.login(username, password, ip, port);

		sftpTools.putFileTransfer(fileName, remotePath,prodName);
		// 关闭登录
		sftpTools.close();

		progressBarService.updatePercent("ValNetValueConfirm003", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
	}
	
	
	public int insertFileTransferInfo(HttpServletRequest request) throws Exception {
	    BokFileTransfer003Dto dto = new BokFileTransfer003Dto();
	    //文件信息
	    BokFileInfoDto infoDto = new BokFileInfoDto();
	    //净值确认附件信息
	    BokNetValueCfmFileDto cfmDto = new BokNetValueCfmFileDto();
	    //产品代码
	    String finprodId = request.getParameter("finprodId");
	    cfmDto.setFinprodId(finprodId);
	    dto.setFinprodId(finprodId);
	    //估值表余额表标识
	    String valBalFlag = request.getParameter("valBalFlag");
	    if(StringUtil.isNotEmpty(valBalFlag)){
//	        if(valBalFlag.equals(DictCodeList.文件类型_估值表.getCodeName())){
//	            valBalFlag = DictCodeList.文件类型_估值表.getValue();
//	        }else {
//	            valBalFlag = DictCodeList.文件类型_余额表.getValue();
//	        }
	    }
	    cfmDto.setValBalFlag(valBalFlag);
	    dto.setValBalFlag(valBalFlag);
	    //估值日期
	    String valDateS = request.getParameter("valDate");
	    Date valDate = DateUtil.parseDate(valDateS, "yyyy-MM-dd");
	    cfmDto.setValDate(valDate);
	    dto.setValDate(valDate);

	    //文件名称
	    String filename = request.getParameter("fileNameOnly");
	    infoDto.setFileName(filename);
	    dto.setFileName(filename);
	    
	    //文件类型
//	    String fileType = DictCodeList.文件类型_净值确认下发附件.getValue();
//	    infoDto.setFileType(fileType);
	    
	    //产品附件唯一标识
	    String valDateStr = valDateS.replace("-", "");
	    String prodName = finprodId+valDateStr+valBalFlag;
	    //文件存放目录
	    String filePath = CacheUtil.getSysParam("FILEUPLOAD_REMOTE_PATH") +"/"+ prodName;
	    infoDto.setFilePath(filePath);
	    //校验是否已有数据
        List<BokFileTransfer003Dto> list= reportConfirmManage001Mapper.quryBokFileInfo(dto);
        if(list.size()>0){
            String fileid = reportConfirmManage001Mapper.quryBokFileInfoKey(infoDto);
            //删除原有纪录
            if(StringUtil.isNotEmpty(fileid)){
                infoDto.setFileId(fileid);
                cfmDto.setFileId(fileid);
                reportConfirmManage001Mapper.deleteBokFileInfo(infoDto);
                reportConfirmManage001Mapper.deleteBokNetValueCfmFile(cfmDto);
            }
        }
	    //创建时间
	    infoDto.setCreateTime(new Date());
	    cfmDto.setCreateTime(new Date());
	    //更新时间
	    infoDto.setUpdateTime(new Date());
	    cfmDto.setCreateTime(new Date());
	    infoDto.setCreateUser("admin");
	    cfmDto.setCreateUser("admin");
	    //fileId
//        String fileId = interfCommonService.getUUid();
//        infoDto.setFileId(fileId);
//        cfmDto.setFileId(fileId);
	    int res = 0;
	    int res1 = reportConfirmManage001Mapper.insertBokFileInfo(infoDto);
	    int res2 = reportConfirmManage001Mapper.insertBokNetValueCfmFile(cfmDto);
	    if(res1>0&&res2>0){
	        res = 1;
	    }
	    return res;
	}

    /**
     * 文件下载
     * 
     * @param request
     * @throws Exception
     */
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request) throws Exception {
        // 文件本地路径
        String fileId = request.getParameter("fileId");
        
        BokFileTransfer003Dto dto = reportConfirmManage001Mapper.qryBokFileInfoByFileId(fileId);
        // 附件存储路径
        String filePath = dto.getFilePath();
        
        // 文件名称
        String fileName = dto.getFileName();
        File file = new SafelyFile(filePath);
        boolean rec = file.renameTo(new SafelyFile(fileName));
        if(rec){
        	logger.info("处理成功");
        }
        
        //估值时间
        String valDateS = dto.getValDateS();
        String valDate = valDateS.replace("-", "");
        //产品id
        String prodId = dto.getFinprodId();
        //估值表余额表标识
        String flag = dto.getValBalFlag();
        if(StringUtil.isNotEmpty(flag)){
//            if(flag.equals("01")){
//                flag = DictCodeList.文件类型_估值表.getCodeName();
//            }else {
//                flag = DictCodeList.文件类型_余额表.getCodeName();
//            }
        }
        //产品唯一标识
        String prodName = prodId+flag+valDate;

        // 主机IP
        String ip = CacheUtil.getSysParam("FILEUPLOAD_HOST");

        // 主机端口
        Integer port = Integer.parseInt(CacheUtil.getSysParam("FILEUPLOAD_PORT"));

        // 主机用户名
        String username = CacheUtil.getSysParam("FILEUPLOAD_USERNAME");

        // 主机密码
        String password = CacheUtil.getSysParam("FILEUPLOAD_PASSWORD");

        // 本地路径
        String localPath = CacheUtil.getSysParam("upload_file_path");

        SftpTools sftpTools = new SftpTools();
        // sftp登录
        sftpTools.login(username, password, ip, port);
        File outFile = null;
        try {
            outFile = sftpTools.getFileTransfer(filePath,fileName,localPath,prodName);
        }
        catch (Exception e) {
           logger.error("",e);
        }
        try {
            // 关闭登录
            sftpTools.close();
        }
        catch (Exception e) {
        	 logger.error("",e);
        }
        return ExportUtil.export(fileName, outFile, true);
    }

    /**
     * 更新文件信息
     * <功能详细描述>
     * @param request
     * @return
     * @see
     * @since 1.0
     */
    public int updateRepRevealById(Map<String, Object> params) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage003/updateRepRevealById",
            params, Integer.class);
    }

}
