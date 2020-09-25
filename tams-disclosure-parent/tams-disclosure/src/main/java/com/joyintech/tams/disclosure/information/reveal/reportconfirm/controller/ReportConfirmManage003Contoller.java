package com.joyintech.tams.disclosure.information.reveal.reportconfirm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.excelimp.dto.ExcelImpFileInfoDto;
import com.joyintech.system.excelimp.dto.ExcelImpResultDto;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.BokFileTransfer003Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage003Service;
import com.joyintech.tams.disclosure.interf.interf.common.util.FileUtil;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;

/**
 * 附件上传
 * 
 * @author Administrator
 *
 */
@Controller
@PageId("ReportConfirmManage003")
@RequestMapping("/disclosure/information/reveal/reportconfirm/ReportConfirmManage003")
public class ReportConfirmManage003Contoller extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private ReportConfirmManage003Service reportConfirmManage003Service;
//
//	@Autowired
//	private BaseCommonService baseCommonService;

	@Autowired
	private FileAttachmentProcess fileAttachmentProcess;
	
	
	@Autowired
    private JoyinDFSTemplate joyinDFSTemplate;
	
	/**
     * 进度条组件
     */
    @Autowired(required = false)
    @Qualifier("commonProgressBarService")
    private ProgressBarService progressBarService;
	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
	    //产品代码
	    String finprodId = this.getParam("finprodId");
//	    model.addAttribute("finprodId", finprodId);
	    //产品简称
	    String finprodAbbr = this.getParam("finprodAbbr");
//        model.addAttribute("finprodAbbr", finprodAbbr);
	    
        //估值表余额表标识
	    String valBalFlagCode = this.getParam("valBalFlagCode");
	    if(valBalFlagCode!=null){
//	        if(valBalFlagCode.equals(DictCodeList.文件类型_估值表.getValue())){
//	            model.addAttribute("valBalFlag", DictCodeList.文件类型_估值表.getCodeName());
//	        }else {
//	            model.addAttribute("valBalFlag", DictCodeList.文件类型_余额表.getCodeName());
//	        }
	    }
	    //估值日期
	    String valDate = this.getParam("valDate");
//        model.addAttribute("valDate", valDate);
//		String sysDate = baseCommonService.getSysDateString();
//		if (sysDate == null || "".equals(sysDate)) {
//			sysDate = DateUtil.format(new Date());
//		}
//		model.addAttribute("sysDate", sysDate);
		return super.init(model);
	}
	
	/**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<BokFileTransfer003Dto> findPage() throws Exception{
//        return valNetValueConfirm003Service.findPage(RequestUtil.getPageParam());
        return null;
    }

	/**
	 * 文件上传
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param model
	 *            ModelAndView
	 * @return 保存文件信息
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
	    AjaxResult res = new AjaxResult();
//		try {
//		    reportConfirmManage003Service.readFile(request);
//		} catch (Exception e) {
//			String msg = "上传失败：" + e.getMessage();
//			res =  new AjaxResult(msg, MessageType.错误消息);
//			return res;
//		}
	    int num = reportConfirmManage003Service.updateRepRevealById(request);
	    
    	if(num>0){
    		res = new AjaxResult(getMessage("EXCELIMP_E_0020"), MessageType.提示消息);
    	}else {
	    	String msg = "上传信息入库失败";
	    	res = new AjaxResult(msg, MessageType.错误消息);
    	}

		return res;
	}
	
	/**
	 * 批量上传披露文件
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param model
	 *            ModelAndView
	 * @return 保存文件信息
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping(value = "/batchUploadFile")
	@ResponseBody
	public AjaxResult batchUploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
	    AjaxResult value = new AjaxResult();
	    //获取所有错误信息
	    List<ValidMessage> checkList = new ArrayList<ValidMessage>();
	 // 画面ID
        String pageId = StringUtil.trim(request.getParameter(GlobalConstant.PAGE_ID));
    	// 获取本地ZIP包路径
		String filePath = request.getParameter("fileName");
		//获取解压在服务器上的路径
        String localFilePath = CacheUtil.getSysParam(ReportGlobalConstant.DECOMPRESS_FILEPATH);
        File localFilePath1 = new File(localFilePath);
		if(!localFilePath1.exists()){
			localFilePath1.mkdirs();
		}
		try{
			//解压zip格式文件 (File originFile, String targetDir)
			boolean deczip =  FileUtil.decompressZip(new File(filePath), localFilePath);
			//获取解压后本地文件全路径
			List<String> listFileName = getAllFile(localFilePath,false);
			for(int i=0; i<listFileName.size(); i++){
				//如果为空跳出循环
				if(!StringUtil.isNotEmpty(listFileName.get(i))){
					continue;
				}
				String[] arr = getFileDetail(listFileName.get(i));
				boolean flag = this.checkUpload(arr);
				//若为true,则可以上传,若为false,则不能上传文件
				if(flag){
					//创建服务器上的上传路径
					String path = FileNameUtil.createUploadFilePath("");
					File filepath1 = new File(path);
					if(!filepath1.exists()){
						filepath1.mkdirs();
					}
					String localFileName = getLocalFileName(listFileName.get(i));
					File file = new File(localFileName);
					String uploadFilePath = dealUploadFilePath(localFileName);
					File localFile = new File(uploadFilePath);
					//读取、写入文件
					FileInputStream fis = null;
					FileOutputStream fos = null;
					try {
						 fis = new FileInputStream(listFileName.get(i));
						 fos = new FileOutputStream(uploadFilePath);
//            		copyFile(file.getInputStream(), new FileOutputStream(localFile), file.getSize(), pageId);		
						copyFile(fis, fos, file.length(), pageId);		
					} finally {
						try {
							if(fis!=null)
							{
								fis.close();
							}

							if(fos!=null)
							{
								fos.close();
							}

						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

					}
					
					String  uuid = IDGenerator.generate();
					
					UploadFile uploadFile = new UploadFile(localFileName,uuid,localFile);
					uploadFile.setFileName(localFile.getPath());
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
					
					//更新数据库中信息
					RepReveal repReveal1= updateRepRevealInfo(arr);
					RepReveal repReveal = new RepReveal();
					repReveal.setFilepath(uuid);
					repReveal.setFilename(localFileName);
					repReveal.setTime(DateUtil.parse(DateUtil.getCurrentDate()));
					repReveal.setProdid(repReveal1.getProdid());
					repReveal.setReportName(repReveal1.getReportName());
					repReveal.setStartDate(repReveal1.getStartDate());
					int num = reportConfirmManage003Service.updateRepRevealByInfo(repReveal);
					if(num>0){
						String msg = "上传信息入库成功："+localFileName;
					}else {
						String msg = "上传信息入库失败："+localFileName;
						checkList.add(new ValidMessage(msg));
					}
				}else{
					String localFileName = getLocalFileName(listFileName.get(i));
					String msg = "状态为已确认,该文件不可上传："+localFileName;
					checkList.add(new ValidMessage(msg));
					value = new AjaxResult(msg, MessageType.错误消息);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "批量上传披露文件错误";
			checkList.add(new ValidMessage(msg));
			value = new AjaxResult(msg, MessageType.错误消息);
		} finally {
			// 上传完成，删除本地解压文件
			deleteDirectory(localFilePath);
		}
        value.setErrorList(checkList);
		return value;
	}
	
	/**
	 * 对上传的文件路径进行处理
	 * @return
	 */
	public static String dealUploadFilePath(String fileName){
		String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length()); 
		String 	fileNameNew = "uploadFile" + GlobalConstant.UNDER_LINE +DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1) +fileTyle;
		String prexFileName = FileNameUtil.getBaseName(fileNameNew);
        StringBuilder builder = new StringBuilder(FileNameUtil.createUploadFilePath(prexFileName));
        builder.append(GlobalConstant.UNDER_LINE).append(FileNameUtil.createFileName(FileNameUtil.getExtension(fileNameNew)));
		return builder.toString();
	}
	
	
	/**
     * 解压zip格式文件
     *
     * @param originFile zip文件。
     * @param targetDir 要解压到的目标路径。
     * @return 如果目标文件不是zip文件则返回false。
     * @throws IOException 如果发生I/O错误。
     */
    @SuppressWarnings("unchecked")
    public static boolean decompressZip(File originFile, String targetDir)
        throws IOException {

        if (!StringUtil.equals("zip", FileNameUtil.getFileExtName(originFile))) {
            return false;
        }
        if (!targetDir.endsWith(File.separator)) {
            targetDir += File.separator;
        }
        ZipFile zipFile = new ZipFile(originFile,"GBK");
        ZipEntry zipEntry;
        Enumeration<ZipEntry> entry = zipFile.getEntries();
        while (entry.hasMoreElements()) {
            zipEntry = entry.nextElement();
            String fileName = zipEntry.getName();
            File outputFile = new File(targetDir + fileName);
            if (zipEntry.isDirectory()) {
                FileUtils.forceMkdir(outputFile);
                continue;
            }
            else if (!outputFile.getParentFile().exists()) {
                FileUtils.forceMkdir(outputFile.getParentFile());
            }

			OutputStream outputStream = null;
			InputStream inputStream = null;
			try {
				 outputStream = new FileOutputStream(outputFile);
				 inputStream = zipFile.getInputStream(zipEntry);
				int len;
				byte[] buffer = new byte[8192];
				while (-1 != (len = inputStream.read(buffer))) {
					outputStream.write(buffer, 0, len);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally {
				if(outputStream!=null)
				{
					outputStream.close();

				}
				if(inputStream!=null)
				{
					inputStream.close();

				}
			}


        }
        zipFile.close();
        return true;
    }
    
	/**
     * 复制文件
     * @param in 文件输入流
     * @param out 文件输出流
     * @param fileSize 源文件大小
     * @param pageId 画面ID
     * @return 返回复制文件的字节数
     * @throws IOException IO异常
     */
    private int copyFile(InputStream in, OutputStream out, long fileSize, String pageId) throws IOException {
        int byteCount = 0;
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        try {
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                byteCount += bytesRead;
                if(null != progressBarService) {
                    progressBarService.updatePercent(pageId, ProgressBarService.FILE_UPLOAD_ID, (double)byteCount * 0.5 / fileSize, false);
                }
            }
            out.flush();
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
            try {
                out.close();
            }
            catch (IOException ex) {
            }
        }
        return byteCount;
    }
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) { 
		boolean flag = false;
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
		boolean flag = false;
	    flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	

	/**
	 * 判断本文件是否可以上传，可上传返回false，不可上传返回true
	 */
	public boolean checkUpload(String[] arr){
		RepReveal repReveal = new RepReveal();
		boolean flag = false;
		//如果数组为1,只传了报表名称
    	if(arr.length == 1){
    		repReveal.setReportName(arr[0]);
    	}else if(arr.length == 2){
    		String result = checkDate(arr[0]);
    		//若为不为空，则说明是时间字符串
    		if(StringUtil.isNotEmpty(checkDate(arr[1]))){
        		repReveal.setReportName(arr[1]);
        		repReveal.setStartDate(DateUtil.parse(result));
    		}else {
    			//为空，则为产品编号
        		repReveal.setReportName(arr[1]);
        		repReveal.setProdid(arr[0]);
    		}
    	}else if (arr.length == 3){
    		
			if(StringUtil.isNotEmpty(checkDate(arr[1]))){
				//若长度为3，则三个全传过来
				repReveal.setProdid(arr[0]);
				repReveal.setStartDate(DateUtil.parse(checkDate(arr[1])));
	    		repReveal.setReportName(arr[2]);
			}else{
				return false;
			}
			
			
    	}
    	//若为true,则可以上传,若为false，则不能上传
    	flag = reportConfirmManage003Service.selectConfirmStatus(repReveal);
		return flag;
	}
	
	/**
	 *  批量上传成功后,更新数据库信息时需要的条件
	 */
	public RepReveal updateRepRevealInfo(String[] arr){
		RepReveal repReveal = new RepReveal();
		//如果数组为1
    	if(arr.length == 1){
    		repReveal.setReportName(arr[0]);
    		repReveal.setProdid(null);
    		repReveal.setStartDate(null);
    	}else if(arr.length == 2){
    		String result = checkDate(arr[0]);
    		//若为true，则说明是时间字符串
    		if(result!=null && !result.equals("false")){
    			repReveal.setStartDate(DateUtil.parse(result));
        		repReveal.setReportName(arr[1]);
        		repReveal.setProdid(null);
    		}else {
    			//不为true，则为产品编号
    			repReveal.setProdid(arr[0]);
    			repReveal.setStartDate(null);
        		repReveal.setReportName(arr[1]);
    		}
    	}else if (arr.length == 3){
    		//若长度为3，则三个全传过来
			repReveal.setProdid(arr[0]);
			repReveal.setStartDate(DateUtil.parse(checkDate(arr[1])));
    		repReveal.setReportName(arr[2]);
    	}
		return repReveal;
	}
	
	
	
	/**
	 * 获取本地上传文件名称并返回数组
	 */
	public static String[] getFileDetail(String fileName){
		File file = new File(fileName);
		String b = file.getName();
		// modifed by zhangyf 20190422 start
		// int endIndex = b.lastIndexOf(".pdf");
		int endIndex = b.lastIndexOf(".");
		// modifed by zhangyf 20190422 end
		String b1 = b.substring(0, endIndex);
		String aa [] = b1.split("_");
		return aa;
	}
	/**
	 * 获取本地文件名称
	 */
	public static String getLocalFileName(String filePath){
		File file = new File(filePath);
		String localFileNmae = file.getName();
		return localFileNmae;
	}
	
	
	/**
	 * 判断字符串能否能转换成时间
	 */
	public static String checkDate(String str){
		String date = "";
		boolean flag = str.contains("-");
		if(flag){
			/*try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Date strToDate = sdf.parse(str);
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf1.format(strToDate);
			} catch (Exception e) {
				date = "false";
			}*/
			date = str;
		}else{
			date = null;
		}
		return date;
	}
	
    /**
     * 获取路径下的所有文件/文件夹
     * @param directoryPath 需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath,boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if(isAddDirectory){
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(),isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }
	
	/**
	 * 保存
	 * 
	 * @param request
	 *            request
	 * @param impFileInfo
	 *            导入文件信息
	 * @return 画面结果
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
	public ExcelImpResultDto save(HttpServletRequest request, @RequestBody ExcelImpFileInfoDto impFileInfo)
			throws Exception {
		return null;
	}
	
	
	/**
     * 文件下载
     *
     * @param request
     *            请求
     * @return 保存文件信息
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request)
            throws Exception { 
//           return valNetValueConfirm003Service.downloadFile(request);
           return null;
    }
    
    /**
     * 文件下载信息删除
     *
     * @param request
     *            请求
     * @return 保存文件信息
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public void deleteFile(HttpServletRequest request)
            throws Exception { 
//           valNetValueConfirm003Service.deleteFile(request);
    }
}
