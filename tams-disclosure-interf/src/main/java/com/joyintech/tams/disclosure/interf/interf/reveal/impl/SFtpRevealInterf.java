package com.joyintech.tams.disclosure.interf.interf.reveal.impl;

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
import com.joyintech.tams.disclosure.interf.interf.common.util.SftpUtil;
import com.joyintech.tams.disclosure.interf.interf.reveal.RevealInterf;

@Component("sftpRevealInterf")
public class SFtpRevealInterf implements RevealInterf{

	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SFtpRevealInterf.class);


	@Override
	public List<RepFtpDetailMsg> sendReportfile(RepProdInfo prodInfo, RepReveal repReveal, RepFtpPath ftpPath,
			String detailId) {
		
		List<RepFtpDetailMsg> list = null;
		
		//if(null!=prodInfo.getMdate()){
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
		//}
		
		
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

			
            // sftp登录
        	try {
        		if(StringUtil.isNotEmpty(ftpPath)) {
            		if(StringUtil.equals(DictCodeListExt2.披露方式_手动.getValue(), option)) {
    	        			
    	        			SftpUtil.SFTPconnect(ftpPath.getUsername(),ftpPath.getIp(), StringUtil.toInt(ftpPath.getProt()),  ftpPath.getPassword());
    	        			SftpUtil.putFile(filePath, ftpPath.getPath());
    	        			flag = true;
    	        		}
            	}
			} catch (Exception e) {
				flag = false;
				logger.error("发送文件失败:",e);
			}finally {
				SftpUtil.SFTPDisConnect();

			}
		return flag;
	}

	@Override
	public List<RepFtpDetailMsg> sendReportfileList(List<RepProdInfo> prodInfoList, List<RepReveal> repRevealList,
			RepFtpPath ftpPath, List<String> detailIdList) {
		// TODO Auto-generated method stub
		return null;
	}

}
