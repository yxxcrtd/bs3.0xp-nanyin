package com.joyintech.tams.disclosure.interf.interf.reveal.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepFtpDetailMsg;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.interf.framework.util.FtpUtil;
import com.joyintech.tams.disclosure.interf.interf.reveal.RevealInterf;

@Component("ftpRevealInterf")
public class FtpRevealInterf implements RevealInterf{

	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(FtpRevealInterf.class);

    
    

	@Override
	public List<RepFtpDetailMsg> sendReportfile(RepProdInfo prodInfo, RepReveal repReveal, RepFtpPath ftpPath,
			String detailId) {
		
		List<RepFtpDetailMsg> list = null;
		
		RepFtpDetailMsg msg = new RepFtpDetailMsg();
		msg.setDetailId(detailId);
		msg.setSendStatus(DictCodeListExt3.披露发送状态_成功.getValue());
		msg.setSendDate(new Date());
		msg.setReceiveStatus(DictCodeListExt3.披露发送状态_失败.getValue());
		msg.setFileName(repReveal.getFilename());
		msg.setChannelCode(repReveal.getRevealChannel());
		try {
			Boolean flag = ftpPushFile(ftpPath,repReveal.getFilepath(),repReveal.getFilename(),DictCodeListExt2.披露方式_手动.getValue());
			msg.setReceiveDate(new Date());
			if(flag){
				msg.setReceiveStatus(DictCodeListExt3.披露发送状态_成功.getValue());
			}
			list = new ArrayList<>();
			list.add(msg);
		} catch (Exception e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<RepFtpDetailMsg> getSendStatus(String detailId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/**
	 * ftp发送文件方法
	 * @param ftpPath
	 * @param filePath
	 * @param filename
	 * @param option
	 * @return
	 * @throws Exception
	 */
	public Boolean ftpPushFile(RepFtpPath ftpPath,String filePath,String filename, String option) throws Exception {
		boolean flag = false;
		// 通过seqno获取文件路径
			InputStream pdfIs = new FileInputStream(filePath);
			
            // sftp登录
        	try {
        		if(StringUtil.isNotEmpty(ftpPath)) {
            		if(StringUtil.equals(DictCodeListExt2.披露方式_手动.getValue(), option)) {
    	        			
    	        			Boolean putFile = FtpUtil.uploadFile(ftpPath.getIp(), StringUtil.toInt(ftpPath.getProt()), ftpPath.getUsername(), ftpPath.getPassword(), ftpPath.getPath(), filename, pdfIs);
    	
    	        			if(putFile){
    	        				flag = true;
    	        			}else {
    	        				logger.info("ftp上传失败");
    	        			}
    	        		}else {
    	        			FtpUtil.changePath(ftpPath.getPath());
    	        			Boolean putFile = FtpUtil.uploadFile(filename, pdfIs);
    	        			if(putFile) {
    	        				flag = true;
    	        			}else {
    	        				logger.info("上传失败");
    	        			}
    	        		}
            	}
			} catch (Exception e) {
				flag = false;
				logger.error("发送文件失败:",e);
			}finally {
				pdfIs.close();

			}
		return flag;
	}

	@Override
	public List<RepFtpDetailMsg> sendReportfileList(List<RepProdInfo> prodInfoList, List<RepReveal> repRevealList,
			RepFtpPath ftpPath, List<String> detailIdList) {
		List<RepFtpDetailMsg> list = null;
		
		try {
			FtpUtil.getFtpConnect(ftpPath.getIp(), Integer.parseInt(ftpPath.getProt()), ftpPath.getUsername(), ftpPath.getPassword());
			
			for (int i = 0; i < repRevealList.size(); i++) {
				try {
						RepProdInfo prodInfo = prodInfoList.get(i);
						RepReveal repReveal = repRevealList.get(i);
						String detailId = detailIdList.get(i);
						
						RepFtpDetailMsg msg = new RepFtpDetailMsg();
						msg.setDetailId(detailId);
						msg.setSendStatus(DictCodeListExt3.披露发送状态_成功.getValue());
						msg.setSendDate(new Date());
						msg.setReceiveStatus(DictCodeListExt3.披露发送状态_失败.getValue());
						msg.setFileName(repReveal.getFilename());
						msg.setChannelCode(repReveal.getRevealChannel());
						
						Boolean flag = ftpPushFile(ftpPath,repReveal.getFilepath(),repReveal.getFilename(),DictCodeListExt2.披露方式_自动.getValue());
						msg.setReceiveDate(new Date());
						if(flag){
							msg.setReceiveStatus(DictCodeListExt3.披露发送状态_成功.getValue());
						}
						if(list!=null)
						{
							list.add(msg);
						}

					} catch (Exception e) {
						logger.error("error",e);
						e.printStackTrace();
					}
			}
		} catch (NumberFormatException | IOException e1) {
			logger.error("连接",e1);
			e1.printStackTrace();
		}
		
		

		return list;
	}

}
