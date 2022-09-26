package com.joyintech.tams.disclosure.interf.interf.reveal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tools.util.SFtpConnect;
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
		
		if(prodInfo==null||null!=prodInfo.getMdate()){
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
			}
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
	public Boolean ftpPushFile(RepFtpPath ftpPath,String filePath,String filename, String option) {
		SFtpConnect sFtpConnect = null;
		// sftp登录
		try {
			if(StringUtil.isNotEmpty(ftpPath) && StringUtil.equals(DictCodeListExt2.披露方式_手动.getValue(), option)) {
				sFtpConnect = SFtpConnect.build(ftpPath.getIp(), StringUtil.toInt(ftpPath.getProt()), ftpPath.getUsername(), ftpPath.getPassword());
				//如果文件不存在则创建文件路径
				String dateStr = DateUtil.format(new Date(),"yyyyMMdd");
				String newFtpPath = ftpPath.getPath() +"/" + dateStr;
				sFtpConnect.mkdir(newFtpPath);
				sFtpConnect.upload(filePath, newFtpPath);
				return true;
			}
		} catch (Exception e) {
			logger.error("发送文件失败:",e);
		}finally {
			if(null != sFtpConnect) {
				sFtpConnect.close();
			}

		}
		return false;
	}

	@Override
	public List<RepFtpDetailMsg> sendReportfileList(List<RepProdInfo> prodInfoList, List<RepReveal> repRevealList,
			RepFtpPath ftpPath, List<String> detailIdList) {
		// TODO Auto-generated method stub
		return null;
	}

}
