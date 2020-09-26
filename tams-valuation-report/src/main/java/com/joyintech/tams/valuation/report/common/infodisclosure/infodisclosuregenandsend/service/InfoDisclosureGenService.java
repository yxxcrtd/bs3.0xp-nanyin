package com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosuregenandsend.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RepInstallFileMapper;
import com.joyintech.tams.common.base.dao.RepInstallLogDetailMapper;
import com.joyintech.tams.common.base.dao.RepRevealMapper;
import com.joyintech.tams.common.base.entity.RepInstallLogDetail;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.valuation.report.common.base.service.ServiceFromJar;
import com.joyintech.tams.valuation.report.common.constant.ReportDictCodeList;
import com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosuregenandsend.dao.InfoDisclosureGenAndSendMapper;
import com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosuregenandsend.entity.InfoDisclosureGenAndSendEntity;
import com.joyintech.tams.valuation.report.common.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.valuation.report.common.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.valuation.report.common.util.FtpUtil;
import com.joyintech.tams.valuation.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.valuation.report.common.constant.DateUtil;



@Service("valuationInfoDisclosureGenService")
@Transactional(rollbackFor = Exception.class)
public class InfoDisclosureGenService {
    @Autowired
	//private FileAttachmentProcess fileAttachmentProcess;
    private ServiceFromJar fileAttachmentProcess;
    
    
    @Autowired
	private RepRevealMapper repRevealMapper;
    @Autowired
	private RepInstallFileMapper repInstallFileMapper;
    /**
     * 拼接url中固定部分
     */
    private static final String MID_URL = "/rest_v2/reports";
    /**
     * 信息披露生成报表并发送批处理service
     */
    @Autowired
    private InfoDisclosureGenAndSendMapper infoDisclosureGenAndSendMapper;
    @Autowired
    private ReportDealService reportDealService;
    @Autowired
	private JasparConnectUtilService jasparConnectUtilService;
    /**
     * 日志详情表mapper
     */
    @Autowired
    private RepInstallLogDetailMapper repInstallLogDetailMapper;
    
    public void genDetail(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity, String serverUrl, 
    		String serverUser, String serverPwd){
    	String exeStatus = ReportDictCodeList.执行结果_失败.getValue();
		//将查询出的配置转换成披露dto及日志详情dto
    	//将待组装数据成信批数据
    	RepReveal repReveal = new RepReveal();
    	//将待组装数据成日志信息
    	RepInstallLogDetail repInstallLogDetail = new RepInstallLogDetail();
    	try{
    		repInstallFileToDto(infoDisclosureGenAndSendEntity, repReveal, repInstallLogDetail);
//    		if(StringUtil.equals(ReportDictCodeList.批处理信息披露状态_未执行.getValue(), infoDisclosureGenAndSendEntity.getExecStatus())){
//    			//插入日志表
//    			repInstallLogDetailMapper.insert(repInstallLogDetail);
//    			infoDisclosureGenAndSendEntity.setExecStatus(ReportDictCodeList.批处理信息披露状态_未生成.getValue());
//    		}
    		boolean genFlag = false;
    		//未生成数据操作
    		//当时报表类型时
			if(StringUtil.equals(ReportDictCodeList.信息披露报表类型_报表.getValue(), infoDisclosureGenAndSendEntity.getRepType())){
				if(StringUtil.isNotEmpty(infoDisclosureGenAndSendEntity.getTemplateId())){
					if(reportDealService.selectConfirmStatus(repReveal)){
						//获取连接url
						String url = getAllUrl(serverUrl, serverUser, serverPwd, infoDisclosureGenAndSendEntity);
						genFlag = gentFile(infoDisclosureGenAndSendEntity, url);
						repReveal.setId(IDGenerator.generate());
						//披露时间
						repReveal.setRevealDate(DateUtil.now());
						//报表类别
						repReveal.setReportCategory(infoDisclosureGenAndSendEntity.getReportCategory());
					    //状态
						repReveal.setStatus(ReportDictCodeList.维护数据状态_有效.getValue());
						//文件名
						repReveal.setSysFilename(infoDisclosureGenAndSendEntity.getFileName());
						//seq
						repReveal.setSysFilepath(infoDisclosureGenAndSendEntity.getFilePathSeqno());
						
						repReveal.setSysFilenameDate(DateUtil.now());
						//先将上条数据更新为删除，在插入
						reportDealService.updateDataStatus(repReveal);
						//插入
						repRevealMapper.insert(repReveal);
						if(genFlag){
							exeStatus = ReportDictCodeList.执行结果_成功.getValue();
						}
					}	
					else{
						exeStatus = ReportDictCodeList.执行结果_成功.getValue();
					}
				}
			}
			//暂无，待设计确定后开发
			else if(StringUtil.equals(ReportDictCodeList.信息披露报表类型_集合.getValue(), infoDisclosureGenAndSendEntity.getRepType())){
				
			}	
//    		if(StringUtil.equals(DictCodeList.是否_是.getValue(), infoDisclosureGenAndSendEntity.getIsConfirm())&&
//    				StringUtil.equals(ReportDictCodeList.批处理信息披露状态_已生成.getValue(), infoDisclosureGenAndSendEntity.getExecStatus())){
//    			repReveal.setConfirmStatus(ReportDictCodeList.确认状态_已确认.getValue());
//    			repReveal.setConfirmDate(DateUtil.now());
//    			infoDisclosureGenAndSendEntity.setExecStatus(ReportDictCodeList.批处理信息披露状态_已确认.getValue());
//    		}
//    		if(StringUtil.equals(ReportDictCodeList.批处理信息披露状态_已确认.getValue(), infoDisclosureGenAndSendEntity.getExecStatus())){
//    			//将生成的文件上传至svn
//    			boolean uploadFlag = uploadFile(repReveal.getId(),infoDisclosureGenAndSendEntity.getFtpCode(),infoDisclosureGenAndSendEntity.getFileName(),
//    					infoDisclosureGenAndSendEntity.getLocalPath(),infoDisclosureGenAndSendEntity.getFilePathSeqno());
//    			if(uploadFlag){
//    				infoDisclosureGenAndSendEntity.setExecStatus(ReportDictCodeList.批处理信息披露状态_未披露.getValue());
//    			}
//    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();	
    	}
    	finally{
    		repInstallLogDetail.setExecStatus(exeStatus);
    		//更新披露表
//			repRevealMapper.updateByPrimaryKeySelective(repReveal);
			//更新日志表
			repInstallLogDetailMapper.insert(repInstallLogDetail);
			//删除
//			infoDisclosureGenAndSendMapper.updateRepInstallStatus(infoDisclosureGenAndSendEntity);
			repInstallFileMapper.deleteByPrimaryKey(infoDisclosureGenAndSendEntity.getId());
    	}
    }
	/**
	 * 上传文件到ftp服务器功能
	 * @param id 披露表主键
	 * @param ftpCode ftp相关配置
	 * @param fileName 文件名
	 * @param localPath 真实路径
	 * @param filePathSeqno 对应文件seqno
	 * @return
	 * @throws FileNotFoundException
	 */
	public  boolean uploadFile(String id,String ftpCode,String fileName,String localPath,String filePathSeqno) throws FileNotFoundException{
		boolean isFtpUploadSucess = false;
		//ftp相关配置通过ftpCode获取
		String url = "192.168.70.66";
		int port  = 21;
		String userName = "test1";
		String passWord = "112233";
		String path = "/test1/information";
		//String filename = infoDisclosureGenAndSendEntity.getFileName();
		if(StringUtil.isEmpty(filePathSeqno)&&StringUtil.isNotEmpty(id)){
			//首先查询出披露表的信息
			RepReveal repReveal = repRevealMapper.selectByPrimaryKey(id);
			//通过其报表关联id查询匹配相关信息
			UploadFile uploadFile = fileAttachmentProcess.fetch(repReveal.getSysFilepath());
			if(uploadFile!=null){
				fileName = uploadFile.getFileName();
				localPath = uploadFile.getTargetFile().getPath();
			}
		}
		if(StringUtil.isNotEmpty(localPath)){
			InputStream input = new FileInputStream(localPath);
			isFtpUploadSucess = FtpUtil.uploadFile(url, port, userName, passWord, path, fileName, input);
		}		
		
		return isFtpUploadSucess;
	}
	/**
     * 生成文件方法
     * @param infoDisclosureGenAndSendEntity
     * @return
     * @throws MalformedURLException 
     */
    public Boolean gentFile(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity,String url) throws MalformedURLException,Exception{
    	boolean flag = false;
    	UploadFile fileInfo  = jasparConnectUtilService.connectJasparAndDownload(url, infoDisclosureGenAndSendEntity.getReportName(), infoDisclosureGenAndSendEntity.getFileType());
//    	URL connUrl = new URL(url);
//    	String fileName = infoDisclosureGenAndSendEntity.getReportName() + GlobalConstant.UNDER_LINE + DateUtil.getNewSysDate() +GlobalConstant.POINT+ infoDisclosureGenAndSendEntity.getFileType();
//
//		URLConnection conn = connUrl.openConnection();
//        // 防止屏蔽程序抓取而返回403错误
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//        InputStream inputStream = conn.getInputStream();
//        
//        // 获取自己数组
//        byte[] getData = readInputStream(inputStream);
//        
//
//        
//        String path = FileNameUtil.createUploadFilePath("");
//        
//        File localFile = new File(path+ File.separator+fileName);
//        File dir = localFile.getParentFile();
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//            
//        FileOutputStream fos = new FileOutputStream(localFile);
//        fos.write(getData);
//        if (fos != null) {
//            fos.close();
//        }
//        if (inputStream != null) {
//            inputStream.close();
//        }
//        
//		String fileKey = IDGenerator.generate();
//
//        UploadFile fileInfo = new UploadFile(fileName, fileKey,
//            localFile);
//        if(null != fileAttachmentProcess) {
//            fileAttachmentProcess.upload(fileInfo);
//        }
        infoDisclosureGenAndSendEntity.setFileName(fileInfo.getSrcName());
        infoDisclosureGenAndSendEntity.setFilePathSeqno(fileInfo.getFileNo());
        infoDisclosureGenAndSendEntity.setLocalPath(fileInfo.getTargetFile().getPath());
        flag = true;
    	
    	return flag;
    }
    
    /**
     * 从输入流中获取字节数组
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    /**
     * 获取报表全路径
     * @param serverUrl 服务器url
     * @param serverUser 用户
     * @param serverPwd  密码
     * @param infoDisclosureGenAndSendEntity 批处理配置
     * @return
     */
    public String getAllUrl(String serverUrl, String serverUser, String serverPwd, InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity){
    	String url = serverUrl + MID_URL + infoDisclosureGenAndSendEntity.getTemplatePath() + ReportGlobalConstant.OBLIQUE_LINE + infoDisclosureGenAndSendEntity.getTemplateId()
    	            +GlobalConstant.POINT + infoDisclosureGenAndSendEntity.getFileType() ;
    	String paramStr = ReportGlobalConstant.QUESTION_MARK + ReportGlobalConstant.J_USERNAME + serverUser + ReportGlobalConstant.AMPERS_AND + ReportGlobalConstant.J_PASSWORD + serverPwd;
    	//获取相应报表对应的查询条件
    	List<RepScreen> screenList = infoDisclosureGenAndSendMapper.getRepScreenList(infoDisclosureGenAndSendEntity.getRepId());
    	for (RepScreen  repScreen: screenList) {
//        	Field field = ReflectionUtils.findField(infoDisclosureGenAndSendEntity.getClass(), repScreen.getScreenKey());
    		if(StringUtil.equals("portnum", repScreen.getScreenKey())){
    			 paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + repScreen.getScreenKey() + '=' + infoDisclosureGenAndSendEntity.getProdid();
    		}
    		else if(StringUtil.equals("startDate", repScreen.getScreenKey())){
    			paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + repScreen.getScreenKey() + '=' + DateUtil.format(infoDisclosureGenAndSendEntity.getStartDate());
    		}
    		else if(StringUtil.equals("endDate", repScreen.getScreenKey())){
    			paramStr = paramStr + ReportGlobalConstant.AMPERS_AND + repScreen.getScreenKey() + '=' + DateUtil.format(infoDisclosureGenAndSendEntity.getEndDate());
    		}
		}
    	url = url+ paramStr + ReportGlobalConstant.OBLIQUE_LINE + ReportGlobalConstant.HOPE_DOWNLOAD_NAME + GlobalConstant.POINT + infoDisclosureGenAndSendEntity.getFileType();
    	return url;
    }
    /**
     * 组装dto
     * @param repInstallFile 组装信息
     * @param repReveal      需要组装的披露信息
     * @param repInstallLogDetail 组装的日志信息
     */
    public void repInstallFileToDto(InfoDisclosureGenAndSendEntity repInstallFile,RepReveal repReveal, RepInstallLogDetail repInstallLogDetail){
    	//将待组装数据成信批数据
        //id
    	//repReveal.setId(IDGenerator.generate());
    	//报表编号
    	repReveal.setRepId(repInstallFile.getRepId());
    	//报表名称  
    	repReveal.setReportName(repInstallFile.getReportName());
    	//产品编号
    	repReveal.setProdid(repInstallFile.getProdid());
    	//产品名称 
    	repReveal.setProdName(repInstallFile.getProdName());
    	//开始日
    	repReveal.setStartDate(repInstallFile.getStartDate());
    	//结束日
    	repReveal.setEndDate(repInstallFile.getEndDate());
    	//是否自动披露 
    	repReveal.setIsReveal(repInstallFile.getIsReveal());
    	//是否自动确认 
    	repReveal.setIsConfirm(repInstallFile.getIsConfirm());
    	//确认状态
    	repReveal.setConfirmStatus(ReportDictCodeList.确认状态_未确认.getValue());
    	//披露状态
    	repReveal.setRevealStatus(ReportDictCodeList.披露状态_未披露.getValue());
    	//披露截止日
    	repReveal.setRevealEndDate(repInstallFile.getRevealEndDate());
    	//披露渠道
    	repReveal.setRevealChannel(repInstallFile.getRevealChannel());
    	//将待组装数据成日志信息
    	//主键 id
    	repInstallLogDetail.setId(IDGenerator.generate());
    	//作业编号
    	repInstallLogDetail.setJobId(repInstallFile.getJobId());
    	//作业流水号
    	repInstallLogDetail.setJobSeq(repInstallFile.getJobSeq());
    	//产品编号
    	repInstallLogDetail.setProdid(repInstallFile.getProdid());
    	//产品名称 暂无
    	repInstallLogDetail.setProdName(repInstallFile.getProdName());
    	//开始日
    	repInstallLogDetail.setStartDate(repInstallFile.getStartDate());
    	//结束日日
    	repInstallLogDetail.setEndDate(repInstallFile.getEndDate());
    	//披露渠道 
    	repInstallLogDetail.setRevealChannel(repInstallFile.getRevealChannel());
    	//披露截止日
    	repInstallLogDetail.setRevealEndDate(repInstallFile.getRevealEndDate());
    	//报表编号
    	repInstallLogDetail.setRepId(repInstallFile.getRepId());
    	//报表名称
    	repInstallLogDetail.setRepName(repInstallFile.getRepName());
    	//报表类型 
    	repInstallLogDetail.setRepType(repInstallFile.getRepType());
    	//生成文件类型
    	repInstallLogDetail.setFileType(repInstallFile.getFileType());
    	//是否自动确认
    	repInstallLogDetail.setIsConfirm(repInstallFile.getIsConfirm());
    	//是否自动披露
    	repInstallLogDetail.setIsReveal(repInstallFile.getIsConfirm());    
    	//状态
    	repInstallLogDetail.setStatus(ReportDictCodeList.数据状态_有效.getValue());
    }
}
