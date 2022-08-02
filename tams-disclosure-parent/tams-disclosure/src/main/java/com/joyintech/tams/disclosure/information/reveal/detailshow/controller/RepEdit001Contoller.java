/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.disclosure.information.reveal.detailshow.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.service.RepCheckInfoService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.ReportManager;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.disclosure.information.config.reporttree.service.ReportTreeMenuConfiguration001Service;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.WebOfficeObject;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see RepEdit001Contoller
 * @since 1.0
 */

@Controller
@PageId("RepEdit001")
@RequestMapping("/disclosure/information/reveal/detailshow/RepEdit001")
public class RepEdit001Contoller extends BaseController {
	
	@Autowired
	private ReportInfoMapper reportInfoMapper;
	
    @Autowired
    private PoiReportService poiReportService;
    
    @Autowired
    ReportDealService reportDealService;
    
    @Autowired
	@Qualifier("masterDataSource") DataSource master;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private RepDetailsShow001Service repDetailsShow001Service;
    
    private String editFilename = "";
    private String saveFilename = "";
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(RepEdit001Contoller.class);
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
    	
    	String templateName = RequestUtil.getPageParam().get("repName")==null?"":RequestUtil.getPageParam().get("repName").toString();
    	Date reportDate = RequestUtil.getPageParam().get("enddate")==null?commonPlatformDateService.getSysDate():DateUtil.parse(RequestUtil.getPageParam().get("enddate").toString());
    	 String portnum = RequestUtil.getPageParam().get("prodid")==null?RequestUtil.getPageParam().get("pprodid").toString():RequestUtil.getPageParam().get("prodid").toString();
    	String reportId = RequestUtil.getPageParam().get("repid")==null?"":RequestUtil.getPageParam().get("repid").toString();
    	
        model.addAttribute("endDate", reportDate);
        model.addAttribute("repName", templateName); 
        model.addAttribute("portnum", portnum);
        model.addAttribute("reportId", reportId);
        
        ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(reportId);
        //reportDate = DateUtil.parse("2023-03-01");
        editFilename = poiReportService.buildReportFileName(PoiReportService.REPORT_TYPE_报表文件,reportInfo, reportDate, portnum,PoiReportService.FILE_TYPE_DOCX);
    	saveFilename = poiReportService.buildReportFileName(PoiReportService.REPORT_TYPE_报表文件,reportInfo, reportDate, portnum,PoiReportService.FILE_TYPE_DOCX);
        return super.init(model);
    }

    @RequestMapping(value="/doWebOffice", method = RequestMethod.POST)
    public void doWebOffice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
        //System.out.println("OfficeServer.service Start");
        //System.out.println("OfficeServer.service --- request.getSession().getId() = " + request.getSession().getId());
        
        
        //TODO:文件操作的根路径,通过系统参数配置实现
        String mFilePath = editFilename;

        try{
            Map<String,Object> params = JsonUtils.stringToMap( RequestUtil.getPageParam().get("FormData").toString() );
            if(request.getMethod().equalsIgnoreCase("POST"))	{//判断请求方式
                String mOption = params.get("OPTION").toString();
                String mFileName = editFilename; //params.get("FILENAME").toString();
                WebOfficeObject webOfficeObject = new WebOfficeObject();
                if(mOption.equalsIgnoreCase("LOADFILE")){
                    webOfficeObject.parse(request,mFileName);
//						System.out.println("ExtParam:"+ExtParam);
//						if (MsgObj.MsgFileLoad(mFilePath+"\\Document\\" + mFileName)){
//							MsgObj.MsgTextClear();
//							System.out.println("文件路径："+mFilePath+"\\Document\\" + mFileName);
//							//MsgObj.SetMsgByName("string1", "测试 内容");
//							System.out.println(mFileName + "文档已经加载");
//						}

                }
                else if(mOption.equalsIgnoreCase("SAVEFILE")){
                    String outputFilename = saveFilename;
                    int ret = this.saveFile (request,outputFilename );
                    logger.info(mFileName+"文档已经保存成功");

                }else if(mOption.equalsIgnoreCase("SAVEPDF")){
//					System.out.println(mRecordID+"文档转PDF");
//					mRecordID = MsgObj.GetMsgByName("RECORDID");                        //取得文档编号
//					mFileName = MsgObj.GetMsgByName("FILENAME");//取得文档名称
//					MsgObj.MsgTextClear();//清除文本信息
//					mFilePath = mFilePath+"\\Document";
//					MsgObj.MakeDirectory(mFilePath);
//					if (MsgObj.MsgFileSave(mFilePath + "\\"+mFileName)){
//						System.out.println(mRecordID+"文档已经转换成功");
//					}

                }else if(mOption.equalsIgnoreCase("SAVEHTML")){
//					System.out.println(mRecordID+"文档上传中");
//					mRecordID = MsgObj.GetMsgByName("RECORDID");                        //取得文档编号
//					mFileName = MsgObj.GetMsgByName("FILENAME");//取得文档名称
//					mDirectory = MsgObj.GetMsgByName("DIRECTORY"); //获取目录名称
//					MsgObj.MsgTextClear();//清除文本信息
//					System.out.println("mDirectory=="+mDirectory);
//					if (mDirectory.equalsIgnoreCase("")) {
//						mFilePath = mFilePath + "\\HTML";
//					}
//					else {
//						mFilePath = mFilePath + "\\HTML\\" + mDirectory;
//						System.out.println("mFilePath==="+mFilePath);
//					}
//					MsgObj.MakeDirectory(mFilePath);
//					System.out.println("开始MsgFileSave"+mFilePath);
//					if (MsgObj.MsgFileSave(mFilePath + "\\" + mFileName)){
//						System.out.println(mRecordID+"文档已经保存成功");
//					}
                }else if (mOption.equalsIgnoreCase("SAVEIMAGE")) {                     //下面的代码为将OFFICE存为HTML图片页面
//					mFileName = MsgObj.GetMsgByName("FILENAME");                        //取得文件名称
//					mDirectory = MsgObj.GetMsgByName("DIRECTORY");                      //取得目录名称
//					MsgObj.MsgTextClear();
//					if (mDirectory.trim().equalsIgnoreCase("")) {
//						mFilePath = mFilePath + "\\HTMLIMAGE";
//					}
//					else {
//						mFilePath = mFilePath + "\\HTMLIMAGE\\" + mDirectory;
//					}
//					MsgObj.MakeDirectory(mFilePath);                                    //创建路径
//					if (MsgObj.MsgFileSave(mFilePath + "\\" + mFileName)) {             //保存HTML文件
//						System.out.println(mRecordID+"文档已经保存成功");               //设置状态信息
//					}else {
//						System.out.println("保存HTML图片失败!");
//					}
                }else if (mOption.equalsIgnoreCase("LOADTEMPLATE")) {                  //下面的代码为打开服务器数据库里的模板文件
//					mTemplate = MsgObj.GetMsgByName("TEMPLATE");                        //取得模板文档类型
//					//本段处理是否调用文档时打开模版，还是套用模版时打开模版。
//					MsgObj.MsgTextClear();//清除文本信息
//					if (MsgObj.MsgFileLoad(mFilePath + "\\Document\\" + mTemplate)) { //从服务器文件夹中调入模板文档
//						System.out.println(mTemplate+"文档已经转换成功");                              //清除错误信息
//					}

                }else if (mOption.equalsIgnoreCase("SAVETEMPLATE")) {                  //下面的代码为打开服务器数据库里的模板文件
//					mTemplate = MsgObj.GetMsgByName("TEMPLATE");//取得模板名称
//					System.out.println(mTemplate+"模板上传中");
//					MsgObj.MsgTextClear();//清除文本信息
//					if (MsgObj.MsgFileSave(mFilePath+"\\Document\\"+mTemplate)){
//						System.out.println(mTemplate+"模板保存成功");
//					}
                }else if (mOption.equalsIgnoreCase("INSERTFILE")) {                  //下面的代码为打开服务器数据库里的模板文件
//					System.out.println("进入INSERTFILE");
//					mTemplate = MsgObj.GetMsgByName("TEMPLATE");//取得模板名称
//					System.out.println(mTemplate+"模板上传中");
//					MsgObj.MsgTextClear();//清除文本信息
//					if (MsgObj.MsgFileLoad(mFilePath+"\\Document\\"+mTemplate)){
//						System.out.println(mRecordID+"模板保存成功");
//					}
//				}else if (mOption.equalsIgnoreCase("LOADBOOKMARKS")) {                 //下面的代码为取得文档标签
//					mTemplate = MsgObj.GetMsgByName("RECORDID");                        //取得模板编号
//					mFileName = MsgObj.GetMsgByName("FILENAME");                        //取得文档名称
//					mFileType = MsgObj.GetMsgByName("FILETYPE");                        //取得文档类型
//					MsgObj.MsgTextClear();
//					System.out.println("mTemplate:=" + mTemplate);
//					if (LoadBookMarks()) {
//						System.out.println("获取书签信息成功");                                          //清除错误信息
//					}
//					else {
//						System.out.println("获取书签信息失败");                            //设置错误信息
//					}
                }else if(mOption.equalsIgnoreCase("GETFILE")){
//					System.out.println("开始下载文档");
//					mRecordID = MsgObj.GetMsgByName("RECORDID");                 //取得文档编号
//					mRemoteFile = MsgObj.GetMsgByName("REMOTEFILE");				//取得远程文件名称
//					MsgObj.MsgTextClear();//清除文本信息
//					System.out.println(mFilePath+"\\Document\\"+mRemoteFile);
//					if (MsgObj.MsgFileLoad(mFilePath+"\\Document\\"+mRemoteFile)){
//						System.out.println(mRemoteFile+"文档已经下载");
//					}
                }else if(mOption.equalsIgnoreCase("PUTFILE")){
//					System.out.println("开始下载文档");
//					mRemoteFile = MsgObj.GetMsgByName("REMOTEFILE");				//取得远程文件名称
//					MsgObj.MsgTextClear();//清除文本信息
//					System.out.println(mFilePath+"\\Document\\"+mRemoteFile);
//					if (MsgObj.MsgFileSave(mFilePath+"\\Document\\"+mRemoteFile)){
//						System.out.println(mRemoteFile+"文档已经上传成功");
//					}
                }else if(mOption.equalsIgnoreCase("DELFILE")){
//					mRemoteFile=MsgObj.GetMsgByName("REMOTEFILE");				//取得远程文件名称
//					MsgObj.MsgTextClear();									//清除文本信息
//					if (MsgObj.DelFile(mFilePath+"\\Document\\"+mRemoteFile)){							//删除文档
//						System.out.println("删除文件成功");
//					}
//					else{
//						System.out.println("删除文件失败");							//设置错误信息
//					}
//				}else if (mOption.equalsIgnoreCase("SENDMESSAGE")) {                   //下面的代码为Web页面请求信息[扩展接口]
//					mCommand = MsgObj.GetMsgByName("COMMAND");                          //取得自定义的操作类型
//					mOfficePrints = MsgObj.GetMsgByName("OFFICEPRINTS");                //取得Office文档的打印次数
//					mUserName = MsgObj.GetMsgByName("USERNAME");
//					mInfo = MsgObj.GetMsgByName("TESTINFO");
//					MsgObj.MsgTextClear();
//					if(mCommand == null){
//						mCommand = "INPORTTEXT";
//					}
//					if(mCommand.equalsIgnoreCase("COPIES")) {                     //打印份数控制功能
//						System.out.println("mOfficePrints:"+mOfficePrints);
//						mCopies = Integer.parseInt(mOfficePrints);                        //获得客户需要打印的份数
//						System.out.println("mCopies:"+mCopies);
//						if (mCopies <= 2) {                                               //比较打印份数，拟定该文档允许打印的总数为2份，注：可以在数据库中设置好文档允许打印的份数
//							if (UpdataCopies(2 - mCopies)) {                                //更新打印份数
//								MsgObj.SetMsgByName("STATUS", "1");                           //设置状态信息，允许打印
//								System.out.println("在打印范围内开始打印");
//							}
//						}
//						else {
//							MsgObj.SetMsgByName("STATUS", "0");                             //不允许打印
//							System.out.println("超过打印限度不允许打印");
//						}
//					}else if(mCommand.equalsIgnoreCase("INPORTTEXT")){
//						mInfo = "服务器端收到客户端传来的信息：“" + mInfo + "” | ";
//						mInfo = mInfo + "当前服务器时间：" + DbaObj.GetDateTime();        //组合返回给客户端的信息
//						MsgObj.SetMsgByName("RETURNINFO", mInfo);
//						System.out.println("发送数据到前台名为:" + mInfo);
//					}
//
//				}else if (mOption.equalsIgnoreCase("SAVEBOOKMARKS")) {                 //下面的代码为取得标签文档内容
//					mTemplate = MsgObj.GetMsgByName("TEMPLATE");                        //取得模板编号
//					if (SaveBookMarks()) {
//						System.out.println("保存书签信息成功!");                                              //清除错误信息
//					}
//					else {
//						System.out.println("保存书签信息失败!");                             //设置错误信息
//					}
//					MsgObj.MsgTextClear();                                              //清除文本信息
//					MsgObj.ListClear();
                }else if (mOption.equalsIgnoreCase("INSERTIMAGE")) {                   //下面的代码为插入服务器图片
//					mRecordID = MsgObj.GetMsgByName("RECORDID");                        //取得文档编号
//					mImageName = MsgObj.GetMsgByName("IMAGENAME");                      //图片名
//					mFilePath = mFilePath + "\\Document\\" + mImageName;                //图片在服务器的完整路径
//					MsgObj.MsgTextClear();
//					if (MsgObj.MsgFileLoad(mFilePath)) {                                //调入图片
//						System.out.println("插入图片成功!");
//					}
//					else {
//						System.out.println("插入图片失败!");                                    //设置错误信息
//					}
                }
                //System.out.println("SendPackage");

                int codec = 0;
                String htmlHttp="";
                if(htmlHttp == "BASE64")
                    codec = 1;
                //iMsgServer2015 MsgObj = new iMsgServer2015();
                webOfficeObject.send(response, codec);
            }
        }catch (Exception e) {
        	logger.error("保存文件错误",e);
            e.printStackTrace();
        }
        //System.out.println("OfficeServer.service End");
    }
    
    public int saveFile (HttpServletRequest request,String outputFilename ) throws IOException{
        int ret = 0;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = StringUtil.trim(file.getOriginalFilename());
                    //String filePath = System.getProperty("user.home");
                    // 如果名称不为空,说明该文件存在，否则说明该文件不存在
                    if (StringUtil.isNotEmpty(myFileName)) {
                        // 文件存储到服务器之前调用监听器

                        File localFile = new SafelyFile(outputFilename);
                        File dir = localFile.getParentFile();
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        if (!localFile.exists()) {
                            localFile.createNewFile();
                        }
                        // file.transferTo(localFile);
                        ret = FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(localFile));
                        //PoiUtils.copy(file.getName(), localFile.getName());

//                        UploadFile fileInfo = new UploadFile(myFileName, IDGenerator.generate(),
//                                localFile);
//                        fileInfo.setFileName(localFile.getPath());
//                        if(null != fileAttachmentProcess) {
//                            fileAttachmentProcess.upload(fileInfo);
//                        }
                    }
                }
            }
        }
//        progressBarService.updatePercent(pageId, ProgressBarService.FILE_UPLOAD_ID, 0.5D, true);
        return  ret;
    }
    
    public int loadFile (HttpServletResponse response,String inputFilename) throws IOException {
        FileInputStream fis = new FileInputStream(inputFilename);
        OutputStream os = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ( (len = fis.read(buffer))!=-1 ) {
            os.write(buffer,0,len);
        }
        fis.close();
        os.flush();

        return len;
    }

}
