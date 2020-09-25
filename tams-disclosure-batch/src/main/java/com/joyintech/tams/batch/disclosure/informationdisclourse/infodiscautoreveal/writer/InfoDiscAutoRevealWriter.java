package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dao.InfoDiscAutoRevealMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto.InfoDiscAutoRevealResult;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto.FtpDetailDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service.FtpDetailService;
@Service
@Scope("prototype")
public class InfoDiscAutoRevealWriter extends BaseWriter<NeedToWriteEntity> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private InfoDiscAutoRevealMapper infoDiscAutoRevealMapper;
	@Autowired
	private FtpDetailService ftpDetailService;
    @SuppressWarnings("unchecked")
	@Override
	public synchronized void write(List<? extends NeedToWriteEntity> items) throws Exception {
		// 获取process返回组合的list值 - 披露成功list
		List<InfoDiscAutoRevealResult> result = (List<InfoDiscAutoRevealResult>) items;
		logger.info("wirte执行开始");
		try{
			//遍历执行更新操作
			for (InfoDiscAutoRevealResult infoDiscAutoRevealResult : result) {
				try {
					FtpDetailDto ftpDetailDto = new FtpDetailDto();
		  
					BeanUtil.copyPropertiesIgnoreNull(ftpDetailDto, infoDiscAutoRevealResult.getFtpDetailDto());  
					
					Boolean checkAndCreate = ftpDetailService.doReveal(ftpDetailDto);
			    	if(checkAndCreate) {
			    		infoDiscAutoRevealResult.setRevealStatus(DictCodeListExt2.披露状态_已披露.getValue());
						infoDiscAutoRevealResult.setRevealUser("system");
						infoDiscAutoRevealResult.setRevealDate(DateUtil.now());
						infoDiscAutoRevealMapper.updateRevealStatusById(infoDiscAutoRevealResult);
			    	}
				} catch (Exception e) {
					logger.error("发送文件失败:" , e);
				}
//		String ip = CacheUtil.getSysParam("FTP_IP");
//		String port = CacheUtil.getSysParam("FTP_PORT");
//		String username = CacheUtil.getSysParam("FTP_USERNAME");
//		String password = CacheUtil.getSysParam("FTP_PASSWORD");
//		FTPClient ftpClient = null;
//		try{
//			logger.info("批处理ftp执行开始");
//			ftpClient = FtpUtil.getFtpConnectClient(ip, StringUtil.toInt(port), username, password);
//			//遍历执行更新操作
//			for (InfoDiscAutoRevealResult infoDiscAutoRevealResult : result) {
//				try {
//					FtpUtil.changePath(ftpClient, "/");
//				} catch (Exception e) {
//					logger.error("目录切换失败：目录 / ", e);
//					try {
//						ftpClient.logout();
//						ftpClient.disconnect();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//					ftpClient = FtpUtil.getFtpConnectClient(ip, StringUtil.toInt(port), username, password);
//				}
//				//结果集不为空的时候更新
//				if(StringUtil.isNotEmpty(infoDiscAutoRevealResult.getId())){
//					//调用ftp参数
//					FtpDetailDto ftpDetailDto = new FtpDetailDto();
//					//优先取人工路径，若人工路径无则取系统路径
//					if(StringUtil.isNotEmpty(infoDiscAutoRevealResult.getFilepath())){
//						ftpDetailDto.setSysFilepath(infoDiscAutoRevealResult.getFilepath());
//					}
//					else{
//						ftpDetailDto.setSysFilepath(infoDiscAutoRevealResult.getSysFilepath());
//					}
//					ftpDetailDto.setProdId(infoDiscAutoRevealResult.getProdid());
//					ftpDetailDto.setReportName(infoDiscAutoRevealResult.getReportName());
////					ftpDetailDto.setFileName(infoDiscAutoRevealResult.getSysFilename());
//					ftpDetailDto.setReportCategory(infoDiscAutoRevealResult.getReportCategory()); // 获取ftp路径用
//					ftpDetailDto.setStatrDate(infoDiscAutoRevealResult.getStartDate());
//					ftpDetailDto.setEndDate(infoDiscAutoRevealResult.getEndDate());
//					ftpDetailDto.setRevealChannel(infoDiscAutoRevealResult.getRevealChannel());
//					ftpDetailDto.setFilename(infoDiscAutoRevealResult.getProdName() + "（理财编号：" + infoDiscAutoRevealResult.getProdid() + "）" + infoDiscAutoRevealResult.getReportName());
//					ftpDetailDto.setOption(DisclosureDictCodeList.披露方式_自动.getValue()); //批操作
//					Boolean checkAndCreate = ftpDetailService.checkAndCreate(ftpClient, ftpDetailDto);
//					logger.info("批处理ftp执行结束");
//					if(checkAndCreate){
//						infoDiscAutoRevealResult.setRevealStatus(DisclosureDictCodeList.披露状态_已披露.getValue());
//						infoDiscAutoRevealResult.setRevealUser("system");
//						infoDiscAutoRevealResult.setRevealDate(DateUtil.now());
//						infoDiscAutoRevealMapper.updateRevealStatusById(infoDiscAutoRevealResult);
//					}
//					else{
//						logger.info("ftp上传失败");
//					}
//				}
			}
		}catch (Exception e) {
			logger.error("处理失败", e);
		}finally {
//			logger.info("write执行结束");
//			if (ftpClient != null && ftpClient.isConnected()) {
//				ftpClient.logout();
//				ftpClient.disconnect();
//			}
			Thread.sleep(1000L);
		}
	}

}
