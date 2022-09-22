package com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import com.joyintech.tams.constant.*;
import org.apache.commons.net.ftp.FTPClient;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.tams.common.base.dao.RepFtpDetailMapper;
import com.joyintech.tams.common.base.dao.RepFtpDetailMsgMapper;
import com.joyintech.tams.common.base.dao.RepFtpPathMapper;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.RepFtpDetail;
import com.joyintech.tams.common.base.entity.RepFtpDetailMsg;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dao.FtpDetailMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto.FtpDetailDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.ZipFileCmpUtil;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.interf.framework.util.FtpUtil;
import com.joyintech.tams.disclosure.interf.interf.reveal.RevealInterf;


/**
 * 披露时生成xml并上传到指定ftp
 * 
 * @author guozhongbao
 *
 */

@Service("disclosureFtpDetailService")
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
    private RepFtpPathMapper repFtpPathMapper;
	
	@Autowired
	private RepProdInfoMapper repProdInfoMapper;
	
	@Autowired
	private RepRevealMapper repRevealMapper;
	
	@Autowired
	private RepFtpDetailMsgMapper repFtpDetailMsgMapper;
	
	
	private RevealInterf revealInterf;
	
	@Autowired
	private FileAttachmentProcess fileAttachmentProcess;

	@Autowired
	private FileRemoteService minioFileRemoteService;

	/**
	 * 单条发送接口信息
	 * @param dto
	 * @return
	 */

	@Transactional(rollbackFor = Exception.class)
	public Boolean doReveal(FtpDetailDto dto) {
		Boolean flag = false;
		//记录ftp发送数据
		dto.setId(IDGenerator.generate());
		repFtpDetailMapper.insert(dto);
		
		//获取ftp信息
		RepFtpPath ftpPath = repFtpPathMapper.selectByPrimaryKey(dto.getReportCategory());
		
		//获取实现接口
		revealInterf = (RevealInterf) SpringContextUtil.getBean(ftpPath.getBeanName());
		//
		flag = this.pushInterfServer(dto, ftpPath,dto.getId());
		return flag;
	}
	
	
	
	/**
	 * 批量发送接口
	 * @param dtoList
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<RepFtpDetailMsg> doRevealList(List<FtpDetailDto> dtoList) {
		Boolean flag = false;
		String benaName = null;
		//记录ftp发送数据
		//获取实现接口
		RepFtpPath ftpPath = null;
		for (FtpDetailDto ftpDetailDto : dtoList) {
			if(null==revealInterf){
				ftpPath = repFtpPathMapper.selectByPrimaryKey(ftpDetailDto.getReportCategory());
				
				if(StringUtil.isNotEmpty(ftpPath)&&null!=ftpPath.getBeanName()){
					//获取ftp信息
					revealInterf = (RevealInterf) SpringContextUtil.getBean(ftpPath.getBeanName());
				}
			}
			
			ftpDetailDto.setId(IDGenerator.generate());
			repFtpDetailMapper.insert(ftpDetailDto);
			//
			flag = this.pushInterfServer(ftpDetailDto, ftpPath,ftpDetailDto.getId());
		}
		return null;
	}
	
	/**
	 * 获取反馈信息接口
	 * @param
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Boolean getDetailMsgBack(String ftpDetailId,String revealId) {
		Boolean flag = false;
		
		
		
		return flag;
	}
	
	
		/**
		 * 发送披露请求到
		 * @param dto
		 * @param ftpPath
		 */
		public Boolean pushInterfServer(FtpDetailDto dto,RepFtpPath ftpPath,String DetailId){
			Boolean flag = false;
			//获取发送报表需要数据
			RepProdInfo reportinfo = ftpDetailMapper.selectProdInfo(dto.getProdId());
			if(reportinfo ==null){
				reportinfo = ftpDetailMapper.selectProdInfoByProdId(dto.getProdId());
			}
			RepReveal repReveal = repRevealMapper.selectByPrimaryKey(dto.getRevealId());

			String filePath = repReveal.getSysFilepath();
			String fileName = repReveal.getSysFilename();
			
			if(null!=repReveal&&StringUtil.isNotEmpty(repReveal.getFilepath())){
				filePath = repReveal.getFilepath();
				fileName = repReveal.getFilename();
			}

			//获取文件名称
			String[] newFileNames = fileName.split("/");
			String  newFileName = newFileNames[newFileNames.length-1];
			String prefix = "";
			//产品代码后缀
			String  instrprd="";

			String reportCategory = repReveal.getReportCategory();
			//发行公告,运行公告,到期公告,临时公告,销售公告
			if("01".equals(reportCategory) || "02".equals(reportCategory) || "03".equals(reportCategory) || "04".equals(reportCategory) || "05".equals(reportCategory) ){
				//根据P_PROD_ID查询产品信息,部分普通产品也有P_PROD_ID
				List<RepProdInfo> repInfoList = ftpDetailMapper.selectProdInfoByPProdId(reportinfo.getProdId());
				if(repInfoList !=null && !repInfoList.isEmpty()){
					prefix = repInfoList.stream().map(RepProdInfo::getProdId).distinct().collect(Collectors.joining(","));
				} else {
					if(StringUtil.equals(reportinfo.getRepProdType(), DictCodeListExt5.信批产品类别_普通产品.getValue())){
						String prodId = ftpDetailMapper.selectProdIdByProdId(dto.getProdId());
						reportinfo.setProdId(prodId);
					}
					prefix = reportinfo.getProdId();
				}
				//募集方式:南银官网字典项
				prefix = prefix+"_"+reportinfo.getRaiseType().replace("0","");
				instrprd="_"+reportinfo.getRaiseType().replace("0","");
				//文件类型:转换成南银官网字典项
				if("01".equals(reportCategory)){
					reportCategory = "3";
				} else if("02".equals(reportCategory)){
					reportCategory = "2";
				}else if("03".equals(reportCategory)){
					reportCategory = "4";
				}else if("04".equals(reportCategory)){
					reportCategory = "5";
				}else if("05".equals(reportCategory)){
					reportCategory = "1";
				}
				//文件类型
				prefix = prefix + "_" + reportCategory;
				instrprd=instrprd + "_" + reportCategory;
			}
			String repId = repReveal.getRepId();
			String fileSuffix = newFileName.substring(newFileName.lastIndexOf("."));
			//发行公告,产品名称+发行公告
			if("CP_001_001_001".equals(repId)){
				newFileName = reportinfo.getProdName() + "发行公告" + fileSuffix;
			} else if("CP_001_002_003".equals(repId) || "CP_001_002_004".equals(repId)) {
				//日开产品净值公告（公募现管类）,日开产品净值公告（公募净值型产品）
				//普通产品文件名命名规则:产品名称+净值公告+（yyyy-MM-dd）
				if("02".equals(reportinfo.getRepProdType())){
					newFileName = reportinfo.getProdName() + "净值公告（" + DateUtil.format(repReveal.getEndDate()) +"）"+fileSuffix;
				}
				//如果子产品
				if("03".equals(reportinfo.getRepProdType())){
					RepProdInfo pRepProdInfo = ftpDetailMapper.selectPProdInfoByCProdId(reportinfo.getProdId());
					newFileName = pRepProdInfo.getProdName() +"（" + reportinfo.getProdId() + "）净值公告（" + DateUtil.format(repReveal.getEndDate()) +"）"+fileSuffix;
				}
			} else if("CP_001_002_005".equals(repId) || "CP_001_002_006".equals(repId)){
				String suffix = "公告";
				//每周净值公告
				if("CP_001_002_006".equals(repId)){
					suffix = "报告";
				}
				//普通产品文件名命名规则:产品名称+净值公告+（yyyy-MM-dd）
				if("02".equals(reportinfo.getRepProdType())){
					newFileName = reportinfo.getProdName() + "每周净值"+suffix+fileSuffix;
				}
				//如果子产品
				if("03".equals(reportinfo.getRepProdType())){
					RepProdInfo pRepProdInfo = ftpDetailMapper.selectPProdInfoByCProdId(reportinfo.getProdId());
					newFileName = pRepProdInfo.getProdName() +"（" + reportinfo.getProdId() + "）每周净值"+suffix+fileSuffix;
				}
			} else if("CP_001_002_015".equals(repId) || "CP_001_002_016".equals(repId)){
				//季度报告
				int length = reportinfo.getProdName().length();
				newFileName = newFileName.replace("第01季度","第1季度");
				newFileName = newFileName.replace("第02季度","第2季度");
				newFileName = newFileName.replace("第03季度","第3季度");
				newFileName = newFileName.replace("第04季度","第4季度");
				newFileName = newFileName.substring(0,length) + "理财产品" + newFileName.substring(length);
			}else if("CP_001_004_017".equals(repId) || "CP_001_004_016".equals(repId)){
				//巨额赎回公告
				String suffix = "公告";
				//查询巨赎的产品代码
			    String pdListName=ftpDetailMapper.selectJsProdIdInfo(repReveal);
				newFileName = pdListName+instrprd+"关于"+reportinfo.getProdName() + pdListName+"发生巨额赎回的"+suffix+fileSuffix;
			}

			if("CP_001_004_017".equals(repId) || "CP_001_004_016".equals(repId)){
				fileName=newFileName;
			}else{
				fileName = prefix + "_" + newFileName;
			}

			UploadFile uploadfile = fileAttachmentProcess.fetch(filePath);

			if(StringUtil.isNotEmpty(uploadfile)){
				repReveal.setFilepath(uploadfile.getTargetFile().getPath());
				repReveal.setFilename(fileName);
				
				try {
					//系统升级后,文件存储的路径为http://形式,用该路径put到远程服务器，会导致找不到本地文件。
					// 处理：先copy一份至临时路径下，用copy出的文件上传服务器
					File downloadFileTemp = null;
					String tempDir = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
					downloadFileTemp = fileCopy(tempDir,repReveal.getFilepath(),downloadFileTemp,repReveal.getFilename());
					repReveal.setFilepath(downloadFileTemp.getPath());
					List<RepFtpDetailMsg> list = revealInterf.sendReportfile(reportinfo, repReveal, ftpPath, DetailId);
					
					flag = doReveiceMsg(list,DetailId);
				} catch (Exception e) {
					//如果捕捉到异常则标志位发送失败
					RepFtpDetailMsg msg = new RepFtpDetailMsg();
					msg.setDetailId(DetailId);
					msg.setId(IDGenerator.generate());
					msg.setSendStatus(DictCodeListExt3.披露发送状态_失败.getValue());
					repFtpDetailMsgMapper.insert(msg);
					logger.error("披露失败:",e);
				}
			}
			
			return flag;
		}
		
		
		/**
		 * 记录返回信息
		 * @param list
		 * @param DetailId
		 */
		public Boolean doReveiceMsg(List<RepFtpDetailMsg> list,String DetailId){
			
			Boolean flag = true;
			
			if(null!=list){
				flag = true;
				String revealFlag = CacheUtil.getSysParam(ReportGlobalConstant.REVEAL_FLAG);
				for (RepFtpDetailMsg repFtpDetailMsg : list) {
					if(revealFlag.equals(DictCodeListExt3.披露发送标识_发送状态.getValue())){
						if(StringUtil.isNotEmpty(repFtpDetailMsg.getReceiveStatus())&&
								repFtpDetailMsg.getReceiveStatus().equals(DictCodeListExt3.披露发送状态_失败.getValue())){
							flag = false;
						}
					}
					repFtpDetailMsg.setDetailId(DetailId);
					repFtpDetailMsg.setId(IDGenerator.generate());
					repFtpDetailMsgMapper.insert(repFtpDetailMsg);
				}
			}else{
				RepFtpDetailMsg msg = new RepFtpDetailMsg();
				msg.setDetailId(DetailId);
				msg.setId(IDGenerator.generate());
				msg.setSendStatus(DictCodeListExt3.披露发送状态_不披露.getValue());
				repFtpDetailMsgMapper.insert(msg);
				
			}
			
			return flag;
		}

	private File fileCopy(String basePath,String filePath,File newFile,String filename) throws IOException {
		if(filePath.startsWith("http:")) {
			byte[] bytes = minioFileRemoteService.downloadFile(filePath);
			if(bytes !=null) {
				if(newFile == null) {
					File downloadFilePeopleTmp = new SafelyFile(minioFileRemoteService.getSimpleFilePathName(filePath));
					newFile = new SafelyFile(getEncodeFileName(basePath+File.separator+
							downloadFilePeopleTmp.getParent() + File.separator + filename));
					File parentFile = new File(newFile.getParent());
					if(!parentFile.exists()){
						boolean ret = parentFile.mkdirs();
						if ( ret==false ) {
							logger.error("创建目录错误："+parentFile.getName());
						}
					}
				}
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(newFile);
					fos.write(bytes);
					fos.flush();
				}catch(Exception e){
					logger.error("远程文件写入本地文件异常",e);
				}finally{
					if(fos != null){
						try{
							fos.close();
						}catch(IOException e){
							logger.error("关闭流异常",e);
						}
					}
				}
			}
		}else{
			File downloadFilePeopleTmp = new SafelyFile(filePath);
			if(newFile == null) {
				newFile = new SafelyFile(getEncodeFileName(
						downloadFilePeopleTmp.getParent() + File.separator + filename));
			}
			try {
				FileUtil.copyFile(downloadFilePeopleTmp, newFile);
			}catch(Exception e){
				logger.error("本地文件Copy异常",e);
			}
		}
		return newFile;
	}


	/**
	 * 中文文件名编码转换
	 * @param fileName
	 * @return
	 */
	private String getEncodeFileName(String fileName) {
		try {
			return StringUtil.isNotEmpty(fileName) ? (new String(fileName.getBytes(System.getProperty("file.encoding")==null?"":System.getProperty("file.encoding")), "UTF-8")) : fileName;
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		}
		return fileName;
	}
}
