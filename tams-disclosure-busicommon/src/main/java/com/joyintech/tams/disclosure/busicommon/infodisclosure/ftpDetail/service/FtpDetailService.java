package com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csii.dc.client.util.FileUtil;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
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
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
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
	 * @param dto
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
			RepProdInfo reportinfo = repProdInfoMapper.selectByPrimaryKey(dto.getProdId());
			RepReveal repReveal = repRevealMapper.selectByPrimaryKey(dto.getRevealId());
			
			
			String filePath = repReveal.getSysFilepath();
			String fileName = repReveal.getSysFilename();
			
			if(null!=repReveal&&StringUtil.isNotEmpty(repReveal.getFilepath())){
				filePath = repReveal.getFilepath();
				fileName = repReveal.getFilename();
			}
			
			UploadFile uploadfile = fileAttachmentProcess.fetch(filePath);
			if(StringUtil.isNotEmpty(uploadfile)){
				repReveal.setFilepath(uploadfile.getTargetFile().getPath());
				repReveal.setFilename(fileName);
				
				try {
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
					if(revealFlag.equals(DictCodeListExt3.披露发送标识_反馈状态.getValue())){
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
}
