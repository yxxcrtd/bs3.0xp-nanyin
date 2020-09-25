//package com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.joyintech.framework.constant.GlobalConstant;
//import com.joyintech.framework.listener.FileUploadListener;
//import com.joyintech.framework.util.DateUtil;
//import com.joyintech.framework.util.FileNameUtil;
//import com.joyintech.system.common.listener.UploadListener;
//
//@Component
////@Order(Integer.MIN_VALUE)
//public class UploadFile implements FileUploadListener {
////public class UploadFile extends UploadListener {
//
//	/**
//     * 日志记录
//     */
//    private static final Logger LOGGER = LoggerFactory.getLogger(UploadListener.class);
//
//	@Override
//	public String beforeUpload(HttpServletRequest request, String fileName) {
//		String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length()); 
//		String 	fileNameNew = "uploadFile" + GlobalConstant.UNDER_LINE +DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1) +fileTyle;
//		// TODO Auto-generated method stub
//		String prexFileName = FileNameUtil.getBaseName(fileNameNew);
//        StringBuilder builder = new StringBuilder(FileNameUtil.createUploadFilePath(prexFileName));
//        builder.append(GlobalConstant.UNDER_LINE).append(FileNameUtil.createFileName(FileNameUtil.getExtension(fileNameNew)));
//        return builder.toString();
//	}
//
//	@Override
//	public void uploaded(com.joyintech.framework.base.dto.UploadFile fileInfo) {
//		// TODO Auto-generated method stub
//		if(LOGGER.isDebugEnabled()) {
//            LOGGER.debug("文件{}已上传", fileInfo.getTargetFile().getPath());
//        }
//	}
//	
//}
