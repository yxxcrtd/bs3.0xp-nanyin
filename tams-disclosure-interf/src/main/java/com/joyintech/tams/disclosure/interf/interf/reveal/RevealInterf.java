package com.joyintech.tams.disclosure.interf.interf.reveal;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepFtpDetailMsg;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;



/**
 * 信息披露披露文件发送共通接口
 * @author leipeng
 *
 */
public interface RevealInterf {
	
	
	/**
	 * 发送文件共通接口.个性化需要实现此接口
	 * @param prodInfo 产品基本信息
	 * @param repReveal 报表相关信息
	 * @param ftpPath 发送相关信息
	 * @param detailId 发送主键.返回信息的detailId需要以此为主键区分
	 * @return  返回相关
	 */
	public List<RepFtpDetailMsg> sendReportfile(RepProdInfo prodInfo,RepReveal repReveal,RepFtpPath ftpPath,String detailId);
	
	
	
	/**
	 * 发送文件共通接口.个性化需要实现此接口
	 * @param prodInfo 产品基本信息(集合)
	 * @param repReveal 报表相关信息(集合)
	 * @param ftpPath 发送相关信息
	 * @param detailId 发送主键.返回信息的detailId需要以此为主键区分(集合)
	 * @return  返回相关
	 */
	public List<RepFtpDetailMsg> sendReportfileList(List<RepProdInfo> prodInfoList,List<RepReveal> repRevealList,RepFtpPath ftpPath,List<String> detailIdList);
	
	
	
	
	
	/**
	 * 发送文件共通接口.个性化需要实现此接口
	 * @param detailId 发送主键.返回信息的detailId需要以此为主键区分
	 * @return  返回相关
	 */
	public List<RepFtpDetailMsg> getSendStatus(String detailId);

}
