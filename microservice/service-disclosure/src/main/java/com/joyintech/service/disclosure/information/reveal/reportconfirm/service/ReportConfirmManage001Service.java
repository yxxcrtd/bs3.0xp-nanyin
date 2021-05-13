/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.reveal.reportconfirm.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dao.ReportConfirmManage001Mapper;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see
 * @since 1.0
 */
@Service(value="serviceReportConfirmManage001Service")
@Transactional(rollbackFor = Exception.class)
public class ReportConfirmManage001Service extends CommonExportService<ReportConfirmManage001Dto> implements BaseProcessService<ReportConfirmManage001Dto> {
	
    /**
     * ValProductInfoQuery001Mapper
     */
    @Autowired
    private ReportConfirmManage001Mapper reportConfirmManage001Mapper;
    
    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;
    
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportConfirmManage001Service.class);
    /**
     *  报表下载临时路径
     */
    public static final String KEY_REPORT_DOWNLOAD_PATH="REPORT_DOWNLOAD_PATH";
    
//    @Autowired
//    private SendNetValueInterfServices sendNetValueInterfServices;
    
    /**
     * 日志
     */
     private static Logger log = LoggerFactory.getLogger(ReportConfirmManage001Service.class);
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ReportConfirmManage001Dto> findPage(int page, int length, Map<String, Object> params) throws Exception {
        
        //获取获取理财产品类型
        String finprodId = (String)params.get("finprodId");
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        params.put("finprodIdArr", finprodIdArr);
        
        //用于流程-我的待办-点击查看
        params.put("finprodIdForFlow", (String)params.get("finprodIdForFlow"));
        params.put("valDateForFlow", (String)params.get("valDateForFlow"));
        params.put("valBalFlagCodeForFlow", (String)params.get("valBalFlagCodeForFlow"));
        
        String repType = (String)params.get("repType");
        String[] repTypeArr = null;
        if(StringUtil.isNotEmpty(repType)){
            repTypeArr = repType.split(",");
        }
        params.put("repTypeArr", repTypeArr);
        String revealChanel=(String)params.get("revealChanel");
        String[] revealChanelArr = null;
        if(StringUtil.isNotEmpty(revealChanel)){
        	revealChanelArr = revealChanel.split(",");
        }
        params.put("revealChanelArr", revealChanelArr);
        // 获取确认方式
        String confirmType = (String)params.get("confirmType");
        String[] confirmTypeArr = null;
        if(StringUtil.isNotEmpty(confirmType)){
        	confirmTypeArr = confirmType.split(",");
        }
        params.put("confirmTypeArr", confirmTypeArr);
        return super.queryForPage(page, length, ()-> reportConfirmManage001Mapper.queryPageList(params));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId(Map<String,Object> map) {
        List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
        List<ReportConfirmManage001Dto> listBookset = reportConfirmManage001Mapper.getFinprodId(map);
        listBookset.forEach(item -> {
            SelectOptionDto dto=new SelectOptionDto();
//            dto.setId(item.getFinprodId());
//            dto.setText(item.getFinprodAbbr());
            listSelectDto.add(dto);
        });
        return listSelectDto;
    }

    /**
     * 根据产品findprodid获取产品详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public ReportConfirmManage001Dto queryProductByfinprodId(Map<String, Object> map) {
        return reportConfirmManage001Mapper.queryProductByfinprodId(map);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportConfirmManage001Dto> queryPageList(Map<String, Object> params) {
      //获取获取理财产品类型
    	String profitType = (String)params.get("profitType");
        String finprodId = (String)params.get("finprodId");
        String checkResult = (String)params.get("checkResult");
        String checkStatus = (String)params.get("checkStatus");
        String confirmStatus = (String)params.get("confirmStatus");
        String noticeStatus = (String)params.get("noticeStatus");
        String[] profitTypeArr = null;
        if(StringUtil.isNotEmpty(profitType)){
            profitTypeArr = profitType.split(",");
        }
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        String[] checkResultArr = null;
        if(StringUtil.isNotEmpty(checkResult)){
            checkResultArr = checkResult.split(",");
        }
        String[] checkStatusArr = null;
        if(StringUtil.isNotEmpty(checkStatus)){
            checkStatusArr = checkStatus.split(",");
        }
        String[] confirmStatustArr = null;
        if(StringUtil.isNotEmpty(confirmStatus)){
            confirmStatustArr = confirmStatus.split(",");
        }
        String[] noticeStatusArr = null;
        if(StringUtil.isNotEmpty(noticeStatus)){
            noticeStatusArr = noticeStatus.split(",");
        }
        params.put("profitTypeArr", profitTypeArr);
        params.put("finprodIdArr", finprodIdArr);
        params.put("checkResultArr", checkResultArr);
        params.put("checkStatusArr", checkStatusArr);
        params.put("confirmStatustArr", confirmStatustArr);
        params.put("noticeStatusArr", noticeStatusArr);
        return reportConfirmManage001Mapper.queryPageList(params);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param
     * @see
     * @since 1.0
     */
    public int updateConfirmStatus(List<ReportConfirmManage001Dto> listdto) {
        int counts=0;
        for(ReportConfirmManage001Dto dto:listdto){
            counts=counts+reportConfirmManage001Mapper.updateConfirmStatusById(dto);
        }
        return counts;
    }

    /**
     * 估值表余额表状态表 - 确认状态和下发状态更新
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ReportConfirmManage001Dto entity, OperationType type){
        if(entity!=null){
            //估值表余额表状态表 - 确认状态更新
//            if("QR".equals(entity.getConfirmOrNotice())){
//                mapper.updateConfirmStatus(entity);
//            }else{
        /*		//估值表余额表状态表 - 下发状态更新	
				mapper.updateNoticeStatus(entity);
				
    			String bankName = CacheUtil.getSysParam("NET_SEND");
        		if("PABANK".equals(bankName)){
        			
        			//组装页面信息
//        			ValNetValueConfirmDto dto = new ValNetValueConfirmDto();
//        			BeanUtil.copyPropertiesIgnoreNull(dto, entity);
        			
	    			//调用净值下发接口
//					  int flag  = sendNetValueInterfServices.execute(dto);
        			int flag = 1;
        			
					  if(flag==0)
						  //回滚当前事务
						  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        	}*/
//            }    
            entity.setConfirmStatus(DictCodeList.确认状态_已确认.getValue());
            entity.setConfirmDate(new Date());
            //entity.setConfirmUser(SecurityUtil.getLoginUser().getUserName());
            //entity.setConfirmDateStr(DateUtil.getCurrentDate());
            
            // 业务主键为空，则从data的json字符串（选中的row）中取  zhangyf 20190111 start
            if(!StringUtil.isNotEmpty(entity.getId())){
                List<ReportConfirmManage001Dto> dtoList = null;
                try {
                    dtoList = JsonUtils.json2List(entity.getData(), ReportConfirmManage001Dto.class);
                }
                catch (IOException e) {
                    log.error("解析json异常，json=" + entity.getData());
//                    e.printStackTrace();
                    logger.error("error:",e);
                }
                if(!StringUtil.isEmptyList(dtoList) && dtoList.get(0) != null  && StringUtil.isNotEmpty(dtoList.get(0).getSeqno())){
                    entity.setId(dtoList.get(0).getSeqno());
                }
            }
            // 业务主键为空，则从data的json字符串（选中的row）中取  zhangyf 20190111 end
            //entity.setUpdateUser(LoginUserUtil.getUserName());
        	reportConfirmManage001Mapper.updateConfirmStatusById(entity);
        }
    }
        /**
         * 删除service
         * <一句话功能简述>
         * <功能详细描述>
         * @param
         * @return
         */
		public int deleteComfirmByPrimaryKey(ReportConfirmManage001Dto dto) {
			// TODO Auto-generated method stub
			return reportConfirmManage001Mapper.deleteComfirmByPrimaryKey(dto);
		}
		
		// add by chendong in 2018-11-18 start
		/**
         * 下载报表信息
         * @param paraMap
         * @return 报表结果集
		 * @throws IOException 
         */
		public ResponseEntity<byte[]> downLoadReportInfo(Map<String, Object> paraMap) throws IOException{
			List<RepReveal> reportList = reportConfirmManage001Mapper.queryReportInfo(paraMap);
			//时间戳
			String dateTime=DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT);
			String exportFilePath = CacheUtil.getSysParam(
	                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
	        String zipFileName = FileNameUtil.createFileName("zip");
	        File zipFile = new File(exportFilePath + File.separator + zipFileName);
	        ZipOutputStream  zipOut =null;
			if(StringUtil.isNotEmpty(reportList)){
				String basePath  = CacheUtil.getSysParam(KEY_REPORT_DOWNLOAD_PATH);
				//需要层级。01：是，02：否
				if(StringUtil.equals(CacheUtil.getSysParam(
		                ReportGlobalConstant.IS_NEED_SECOND), "01") ){
					File firstFile = null;
					File secondFile = null;
			        try {
			        	zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			        	
				        	for(RepReveal report : reportList){
                                // TODO
				        	    String fileType=StringUtil.substring(report.getSysFilename(), report.getSysFilename().lastIndexOf("."));
				        	    String reportName=report.getReportName()+fileType;
				        	    if(StringUtil.isNotEmpty(report.getEndDate())&&report.getEndDate()!=null){
				        	        reportName=DateUtil.format(report.getEndDate(),DateUtil.DEFAULT_DATE_FORMAT_EN)+GlobalConstant.UNDER_LINE+reportName;
				        	    }
				        	    if(StringUtil.isNotEmpty(report.getProdid())){
                                    reportName=report.getProdid()+GlobalConstant.UNDER_LINE+reportName;
                                }
				        	    
				        		//报表类别名称
				        		String reportCategoryName=reportConfirmManage001Mapper.getReportCategoryName(report.getReportCategory());
								String firstPath = basePath + report.getProdid();
								// 存在人工上传的报表
								String downloadPathPeople = null;
								File downloadFilePeople = null;
								
								if(StringUtil.isNotEmpty(report.getFilepath())){
									downloadPathPeople = this.getReportPath(report.getFilepath());
									File downloadFilePeopleTmp = new File(downloadPathPeople);
									downloadFilePeople = new File(downloadFilePeopleTmp.getParent()+File.separator+report.getFilename());
									FileUtil.copyFile(downloadFilePeopleTmp, downloadFilePeople);
								}
								// 获取报表下载路径
								String downloadPath = null;
								File downloadFile = null;
								if(StringUtil.isNotEmpty(report.getSysFilepath())){
									downloadPath = this.getReportPath(report.getSysFilepath());
									
									File downloadFileTmp = new File(downloadPath);
								    downloadFile = new File(downloadFileTmp.getParent()+File.separator+report.getSysFilename());
									FileUtil.copyFile(downloadFileTmp, downloadFile);
								}
								
								// 第一层目录
								firstFile = new File(firstPath);
								// 判断第一层文件夹是否存在，否则创建
								if(!firstFile.exists()){
									firstFile.mkdir();
								}
								String secondPath = firstPath + File.separator + report.getReportCategory();
								// 第二层目录
								secondFile = new File(secondPath);
								// 判断第二层文件夹是否存在，否则创建
								if(!secondFile.exists()){
									secondFile.mkdir();
									//是否需要下载系统生成报表。01：是，02：否
									if(StringUtil.equals(CacheUtil.getSysParam(
								                ReportGlobalConstant.IS_NEED_SYSFILE), "01") ){
										//存在人工上传报表
										if(StringUtil.isNotEmpty(downloadFilePeople)){
											String filename = downloadFilePeople.getName();
											List<String> splitToList = StringUtil.splitToList(".", reportName);
											//将下载的报表文件重命名
											String newFileName = "";
											if(StringUtil.isNotEmpty(splitToList)) {
												//获得报表文件类型
												fileType = splitToList.get(splitToList.size() - 1);
												splitToList.remove(splitToList.size() - 1);
												//命名规则：产品名称+报表类别名称+时间戳+（手工上传）
												newFileName = splitToList.get(splitToList.size() - 1)+"_(手动上传)."+fileType;
											}
											File secondPeopleFile = null;
											if(StringUtil.isNotEmpty(newFileName)) {
												String secondPeoplePath = secondPath + File.separator + newFileName;
												secondPeopleFile = new File(secondPeoplePath);
											}
											
											if(StringUtil.isNotEmpty(downloadFilePeople)){

												if(StringUtil.isNotEmpty(secondPeopleFile)) {
													FileUtil.copyFile(downloadFilePeople, secondPeopleFile);
												}else {
													FileUtil.copyFile(downloadFilePeople, secondFile);
												}
											}
										}
										//存在系统生成文件
										if(StringUtil.isNotEmpty(downloadFile) && downloadFile != null){
											String sysFileName=downloadFile.getName();
											//报表类型
											String sysFileType=sysFileName.substring(sysFileName.lastIndexOf("."),sysFileName.length());
											//重命名。命名规则：产品名称+报表类型+时间戳
											String newSysFileName =reportName;
											File secondSysFile = null;
											if(StringUtil.isNotEmpty(newSysFileName)) {
												String secondSysPath = secondPath + File.separator + newSysFileName;
												secondSysFile = new File(secondSysPath);
											}
							
											if(StringUtil.isNotEmpty(secondSysFile)) {
												FileUtil.copyFile(downloadFile, secondSysFile);
											}else {
												FileUtil.copyFile(downloadFile, secondFile);
											}
										}
									}
									//当存在手动上传和系统生成文件时不需要下载系统生成文件
									else{
										//存在人工上传文件，下载手动上传报表
										if(StringUtil.isNotEmpty(downloadFilePeople)){
											List<String> splitToList = StringUtil.splitToList(".", reportName);
											String newFileName = "";
											if(StringUtil.isNotEmpty(splitToList)) {
												fileType = splitToList.get(splitToList.size() - 1);
												splitToList.remove(splitToList.size() - 1);
												//重命名。命名规则：产品名称+报表类型
												newFileName = splitToList.get(splitToList.size() - 1)+"_(手动上传)."+fileType;
											}
											File secondPeopleFile = null;
											if(StringUtil.isNotEmpty(newFileName)) {
												String secondPeoplePath = secondPath + File.separator + newFileName;
												secondPeopleFile = new File(secondPeoplePath);
											}
											if(StringUtil.isNotEmpty(secondPeopleFile)) {
												FileUtil.copyFile(downloadFilePeople, secondPeopleFile);
											}else {
												FileUtil.copyFile(downloadFilePeople, secondFile);
											}
										}
										//不存在手动上传文件时下载系统生成文件
										else{
											if(StringUtil.isNotEmpty(downloadFile)){
												String newSysFileName =reportName;
												File secondSysFile = null;
												if(StringUtil.isNotEmpty(newSysFileName)) {
													String secondSysPath = secondPath + File.separator + newSysFileName;
													secondSysFile = new File(secondSysPath);
												}
												if(StringUtil.isNotEmpty(secondSysFile)) {
														FileUtil.copyFile(downloadFile, secondSysFile);
												}else {
														FileUtil.copyFile(downloadFile, secondFile);
												}
											
											}
										}
									}
								}
								zipOut.setEncoding("gbk");
								compressFile(zipOut, firstFile, firstFile.getName());
								deleteFile(firstFile);
								if(downloadFilePeople!=null){
									downloadFilePeople.delete();
								}
								if(downloadFile!=null){
									downloadFile.delete();
								}
							}
				        	zipOut.close();
				        }
			        catch(Exception e) {
			        	log.error("",e);
			        }finally {
			        	if(zipOut != null){
							zipOut.close();
						}
					}
				}
				//不需要层级
				else{
					try {
						zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
						for(RepReveal report : reportList){
						    // TODO
                            String reportName=report.getReportName();
                            if(StringUtil.isNotEmpty(report.getEndDate())&&report.getEndDate()!=null){
                                reportName=DateUtil.format(report.getEndDate(),DateUtil.DEFAULT_DATE_FORMAT_EN)+GlobalConstant.UNDER_LINE+reportName;
                            }
                            if(StringUtil.isNotEmpty(report.getProdid())){
                                reportName=report.getProdid()+GlobalConstant.UNDER_LINE+reportName;
                            }
							String reportCategoryName=reportConfirmManage001Mapper.getReportCategoryName(report.getReportCategory());
							File baseFile=new File(basePath);
							if(!baseFile.exists()){
								baseFile.mkdir();
							}
							// 存在人工上传的报表
							String downloadPathPeople = null;
							File downloadFilePeople = null;
							if(StringUtil.isNotEmpty(report.getFilepath())){
								downloadPathPeople = this.getReportPath(report.getFilepath());
								File downloadFilePeopleTmp = new File(downloadPathPeople);
								downloadFilePeople = new File(downloadFilePeopleTmp.getParent()+File.separator+report.getFilename());
								FileUtil.copyFile(downloadFilePeopleTmp, downloadFilePeople);
							}
							// 获取报表下载路径（系统生成）
							String downloadPath = null;
							File downloadFile = null;
							if(StringUtil.isNotEmpty(report.getSysFilepath())){
								downloadPath = this.getReportPath(report.getSysFilepath());
								
								File downloadFileTmp = new File(downloadPath);
								
							    downloadFile = new File(downloadFileTmp.getParent()+File.separator+report.getProdName()+report.getSysFilename());
								FileUtil.copyFile(downloadFileTmp, downloadFile);
							}
							//需要系统文件
							if(StringUtil.equals(CacheUtil.getSysParam(
						                ReportGlobalConstant.IS_NEED_SYSFILE), "01") ){
								if(StringUtil.isNotEmpty(downloadFilePeople)){
									String filename = downloadFilePeople.getName();
									List<String> splitToList = StringUtil.splitToList(".", reportName);
									String newFileName = "";
									if(StringUtil.isNotEmpty(splitToList)) {
										String fileType = splitToList.get(splitToList.size() - 1);
										splitToList.remove(splitToList.size() - 1);
										//命名规则：产品名称+报表类型名称+时间戳
										newFileName = splitToList.get(splitToList.size() - 1)+"_(手动上传)."+fileType;
									}
									File peopleFile = null;
									if(StringUtil.isNotEmpty(newFileName)) {
										String peoplePath = basePath + File.separator + newFileName;
										peopleFile = new File(peoplePath);
									}
									if(StringUtil.isNotEmpty(downloadFilePeople)){

										if(StringUtil.isNotEmpty(peopleFile)) {
											FileUtil.copyFile(downloadFilePeople, peopleFile);
										}else {
											FileUtil.copyFile(downloadFilePeople, baseFile);
										}
									}
								}
								if(StringUtil.isNotEmpty(downloadFile)){
									String sysFileName=downloadFile.getName();
									//文件类型
									String sysFileType=sysFileName.substring(sysFileName.lastIndexOf("."),sysFileName.length());
									//命名规则：产品名称+报表类型+时间戳
									String newSysFileName =reportName;
									File sysFile = null;
									if(StringUtil.isNotEmpty(newSysFileName)) {
										String sysPath = basePath + File.separator + newSysFileName;
										sysFile = new File(sysPath);
									}
									if(StringUtil.isNotEmpty(sysFile)) {
										FileUtil.copyFile(downloadFile, sysFile);
									}else {
										FileUtil.copyFile(downloadFile, baseFile);
									}
								}
							}
							//不需要系统文件
							else{
								//如果存在人工上传，下载人工上传
								if(StringUtil.isNotEmpty(downloadFilePeople)){
									String filename = downloadFilePeople.getName();
									List<String> splitToList = StringUtil.splitToList(".", reportName);
									String newFileName = "";
									if(StringUtil.isNotEmpty(splitToList)) {
										String fileType = splitToList.get(splitToList.size() - 1);
										splitToList.remove(splitToList.size() - 1);
										newFileName = splitToList.get(splitToList.size() - 1)+"_(手动上传)."+fileType;
									}
									File peopleFile = null;
									if(StringUtil.isNotEmpty(newFileName)) {
										String peoplePath = basePath + File.separator + newFileName;
										peopleFile = new File(peoplePath);
									}
									
									if(StringUtil.isNotEmpty(downloadFilePeople)){
										if(StringUtil.isNotEmpty(peopleFile)) {
											FileUtil.copyFile(downloadFilePeople, peopleFile);
										}else {
											FileUtil.copyFile(downloadFilePeople, baseFile);
										}
									}
								}
								//没有人工上传，下载系统文件
								else{
									if(StringUtil.isNotEmpty(downloadFile) && downloadFile != null){
										String sysFileName=downloadFile.getName();
										//文件类型
										String sysFileType=sysFileName.substring(sysFileName.lastIndexOf("."),sysFileName.length());
										//命名规则：产品名称+报表类型+时间戳
										String newSysFileName =reportName;
										File sysFile = null;
										if(StringUtil.isNotEmpty(newSysFileName)) {
											String sysPath = basePath + File.separator + newSysFileName;
											sysFile = new File(sysPath);
										}
										if(StringUtil.isNotEmpty(sysFile)) {
											FileUtil.copyFile(downloadFile, sysFile);
										}else {
											FileUtil.copyFile(downloadFile, baseFile);
										}
									
									}
								}
							}
							zipOut.setEncoding("gbk");
							compressFile2(zipOut, baseFile,baseFile.getName());
							deleteFile(baseFile);
							if(downloadFilePeople!=null){
								downloadFilePeople.delete();
							}
							if(downloadFile!=null){
								downloadFile.delete();
							}
						}
						zipOut.close();
					} 
					catch(Exception e) {
			        	log.error("",e);
			        }
					
				}
			}
			return ExportUtil.export(zipFileName, zipFile, true);
		}
		
		/**
         * 压缩报表信息
         * @param
         * @param
         * @param
		 * @throws IOException 
         */
		private void compressFile(ZipOutputStream out, File sourcefile, String basedir) throws IOException{
	        if (sourcefile.isDirectory()) {
	        	File[] files = sourcefile.listFiles();
	        	
	        	if(files==null||files.length==0) {
					out.putNextEntry(new ZipEntry(basedir + File.separator));
				} 
	        	else {
					for(int i=0; i<files.length; i++) {
						compressFile(out, files[i], basedir+File.separator+files[i].getName());
					}
				}
	        }
	        else {
    			out.putNextEntry(new ZipEntry(basedir));

				FileInputStream fos = null;
				BufferedInputStream bis = null;
    			try {
					 fos = new FileInputStream(sourcefile);
					 bis = new BufferedInputStream(fos);
					int len;
					byte[] buf = new byte[1024];
					while((len=bis.read(buf, 0, 1024)) != -1) {
						out.write(buf, 0, len);
					}
				}catch (Exception e)
				{
					e.printStackTrace();
				}
				finally {
					if (bis != null){
						bis.close();
					}
					if (fos != null){
						fos.close();
					}
				}
        	}
		}
		
		
		/**
         * 压缩报表信息
         * @param
         * @param
         * @param
		 * @throws IOException 
         */
		private void compressFile2(ZipOutputStream out, File sourcefile, String basedir) throws IOException{
	        if (sourcefile.isDirectory()) {
	        	File[] files = sourcefile.listFiles();
	        	
	        	if(files==null||files.length==0) {
					out.putNextEntry(new ZipEntry(basedir + File.separator));
				} 
	        	else {
					for(int i=0; i<files.length; i++) {
						compressFile2(out, files[i], files[i].getName());
					}
				}
	        }
	        else {
    			out.putNextEntry(new ZipEntry(basedir));

				FileInputStream fos = null;
				BufferedInputStream bis = null;
    	        try {
					 fos = new FileInputStream(sourcefile);
					 bis = new BufferedInputStream(fos);
					int len;
					byte[] buf = new byte[1024];
					while((len=bis.read(buf, 0, 1024)) != -1) {
						out.write(buf, 0, len);
					}
				}catch (Exception e)
				{

				}
				finally {
					if(bis!=null)
					{
						bis.close();
					}
					if(fos!=null)
					{
						fos.close();
					}

				}


        	}
		}
		
		/**
         * 删除多余文件
         * @param
         */
		private void deleteFile(File file){
		      if(file.isDirectory()){
		           File[] files = file.listFiles();
		           if(files!=null&&files.length>0){
		        	   for(int i=0; i<files.length; i++){
			                deleteFile(files[i]);
			           }
		           }
		          
		     }
		     file.delete();
		}
		
		/**
         * 获取报表路径
         * @param Seqno
         * @return 报表路径
         */
	    @DataSource(DataSourceType.OTHER)
		public String getReportPath(String Seqno){
	        
	        
	        UploadFile file = fileAttachmentProcess.fetch(Seqno);

			return file.getTargetFile().getPath();
		}
	    
	    /**
	     * 获取确认方式
	     * 
	     * @return List<SelectOptionDto>
	     */
	    public List<SelectOptionDto> getConfirmType(){
	    	List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
	    	SelectOptionDto dto = new SelectOptionDto();
	    	dto.setId("01");
	    	dto.setText("自动");
	    	listSelectDto.add(dto);
	    	dto = new SelectOptionDto();
	    	dto.setId("02");
	    	dto.setText("手动");
	    	listSelectDto.add(dto);
	    	return listSelectDto;
	    }
	    
	    /**
	     * 取消确认
	     * @param
	     * @see
	     * @since 1.0
	     */
	    public int confirmCancle(Map<String, Object> paraMap) {
	    	return reportConfirmManage001Mapper.confirmCancle(paraMap);
	    }

	    /**
         * 删除文件
         * 
         * @param pageParam
         * @return
         */
        public int deleteFile(Map<String, Object> pageParam) {
            return reportConfirmManage001Mapper.deleteFile(pageParam);
        }

		/**
		 * 代办中修改数据时同步更新
		 * 
		 * @param wflList
		 * @return
		 */
		public ReportConfirmManage001Dto findNewData(ReportConfirmManage001Dto wflList) {
			ReportConfirmManage001Dto result = reportConfirmManage001Mapper.findNewData(wflList.getId());
			return result;
		}

		public String getFileName(String sysFilepath) {
			return reportConfirmManage001Mapper.getFileName(sysFilepath);
			
		}

		public String getFormate(String sysFilepath) {
			return reportConfirmManage001Mapper.getFormate(sysFilepath);
		}

		
}
