package com.joyintech.tams.valuation.report.common.infodisclosure.ftpDetail.service;

import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csii.dc.client.util.FileUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepFtpDetailMapper;
import com.joyintech.tams.common.base.dao.RepFtpPathMapper;
import com.joyintech.tams.common.base.entity.RepFtpDetail;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.valuation.report.common.base.service.ServiceFromJar;
import com.joyintech.tams.valuation.report.common.constant.ReportDictCodeList;
import com.joyintech.tams.valuation.report.common.infodisclosure.ftpDetail.dao.FtpDetailMapper;
import com.joyintech.tams.valuation.report.common.infodisclosure.ftpDetail.dto.FtpDetailDto;
import com.joyintech.tams.valuation.report.common.infodisclosure.util.SftpTools;
import com.joyintech.tams.valuation.report.common.infodisclosure.util.ZipFileCmpUtil;


/**
 * 披露时生成xml并上传到指定ftp
 * 
 * @author guozhongbao
 *
 */

@Service("valuationFtpDetailService")
public class FtpDetailService {
	
	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(FtpDetailService.class);
    
    private static String DIAN = ".";
	
	@Autowired
	private FtpDetailMapper ftpDetailMapper;
	
	@Autowired
	private RepFtpDetailMapper repFtpDetailMapper;
	
	@Autowired
	//private FileAttachmentProcess fileAttachmentProcess;
	private ServiceFromJar fileAttachmentProcess;
	
	@Autowired
    private RepFtpPathMapper repFtpPathMapper;

	@Transactional(rollbackFor = Exception.class)
	public Boolean checkAndCreate(FtpDetailDto dto) {
//		String uuid = IDGenerator.generate();
//		dto.setId(uuid);
//		dto.setDate(new Date());
//		RepFtpDetail isExits = ftpDetailMapper.selectFtpDetatil(dto);
//		if(isExits != null) {
//			// 上传
//			try {
//				Boolean isSuccess = this.filesToXmlAndPut(dto.getSysFilepath(), dto.getReportCategory(), uuid);
//				if(isSuccess) {
//					logger.info("文件上传成功");
//					// 操作表
//					// 更新存在的数据
//					isExits.setStatus(ReportDictCodeList.数据状态_删除.getValue());
//					repFtpDetailMapper.updateByPrimaryKeySelective(isExits);
//					// 插入新数据
//					dto.setFileOk(ReportDictCodeList.数据状态_有效.getValue());
//					dto.setStatus(ReportDictCodeList.数据状态_有效.getValue());
//					BeanUtil.copyPropertiesIgnoreNull(isExits, dto);
//					isExits.setFileName(uuid);
//					repFtpDetailMapper.insertSelective(isExits);
//					return true;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}else {
//			// 上传
//			try {
//				Boolean isSuccess = this.filesToXmlAndPut(dto.getSysFilepath(), dto.getReportCategory(), uuid);
//				if(isSuccess) {
//					logger.info("文件上传成功");
//					// 操作表
//					dto.setFileOk(ReportDictCodeList.数据状态_有效.getValue());
//					dto.setStatus(ReportDictCodeList.数据状态_有效.getValue());
//					RepFtpDetail repFtpDetail = new RepFtpDetail();
//					BeanUtil.copyPropertiesIgnoreNull(repFtpDetail, dto);
//					repFtpDetail.setFileName(uuid);
//					repFtpDetailMapper.insertSelective(repFtpDetail);
//					return true;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}
	
	/**
	 * 生成xml文件并上传至ftp
	 * 
	 * @param sysFilepath
	 * @param id
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public Boolean filesToXmlAndPut(String sysFilepath, String id, String uuid) throws Exception {
		// 通过seqno获取文件路径
		String filePath = fileAttachmentProcess.fetch(sysFilepath).getTargetFile().getPath();
		String fileType = DIAN + FileUtil.getFileType(filePath);
		String filePathXml = filePath + ".xml";
    	RepReveal repReveal = new RepReveal();
    	repReveal.setFilepath(filePath);
    	
    	InputStream callCMD = ZipFileCmpUtil.callCMD(repReveal);
    	
    	if(callCMD != null) {
    		SftpTools sftpTools = new SftpTools();
    		RepFtpPath repFtpPath = repFtpPathMapper.selectByPrimaryKey(id);
            // sftp登录
        	if(StringUtil.isNotEmpty(repFtpPath)) {
        		// ftp登录
        		sftpTools.login(repFtpPath.getUsername(), repFtpPath.getPassword(), repFtpPath.getIp(), StringUtil.toInt(repFtpPath.getProt()));
        		// 将本地文件放在远程服务器
        		Boolean putFile = sftpTools.putFile(filePath, repFtpPath.getPath(), uuid + fileType);
        		Boolean putFile2 = sftpTools.putFile(filePathXml, repFtpPath.getPath(), uuid + fileType + ".xml");
        		sftpTools.close();
        		if(putFile && putFile2){
        			return true;
        		}
        	}
    	}
    	return false;
	}
}
