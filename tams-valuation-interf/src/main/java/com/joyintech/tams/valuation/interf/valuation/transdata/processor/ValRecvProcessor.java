/*
 * 文件名：ValRecvProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.transdata.processor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.valuation.transdata.dao.ProtocolMapper;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvFileEntity;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvServerInfoEntity;
import com.joyintech.tams.valuation.interf.valuation.transdata.service.DbService;
import com.joyintech.tams.valuation.interf.valuation.transdata.service.LocalhostService;
import com.joyintech.tams.valuation.interf.valuation.transdata.service.ValFtpService;
import com.joyintech.tams.valuation.interf.valuation.transdata.service.ValSFtpService;
import com.joyintech.tams.common.base.entity.ValInterfTemplate;

/**
 * 估值核算接入
 * 
 * @author 王波
 * @version 1.0 2018年6月20日
 * @see ValRecvProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValRecvProcessor extends InterfBaseProcessor<ValRecvServerInfoEntity, ValRecvFileEntity> {

	@Autowired
	private ValFtpService ftpService;

	@Autowired
	private ValSFtpService sftpService;
	
	@Autowired
	private DbService dbService;
	
	@Autowired
	private LocalhostService localService;
	
	@Autowired
	ProtocolMapper protocolMapper;

	@SuppressWarnings("unchecked")
	@Override
	public ValRecvFileEntity process(ValRecvServerInfoEntity info) throws Exception {
	    
	    ValRecvFileEntity needToWrite = null ;
		// 接口时间
		Date interfTime = (Date) getParams().get("interfTime");
		info.setOccurTime(interfTime);

		info.setInstitutionCode((String)getParams().get("INSTITUTION_CODE"));

		List<String> interfList = (List<String>) getParams().get(InterfCommonConstant.接口_运行接口列表);
		Map<String, Object> params = new HashMap<>();
		params.put("interfList", interfList);
		params.put("systemId", info.getSystemID());
		//根据待运行接口列表获取所有的模版相关信息
		List<ValInterfTemplate> tempList = protocolMapper.queryTempListByInterfList(params);
		/*多级法人改造只做了前面三种类型改造*/
		if (DictCodeInterfList.接口服务器类型_FTP.getValue().equals(info.getServerType())) {
		    needToWrite = ftpService.doRecv(info, tempList);
		} else if (DictCodeInterfList.接口服务器类型_SFTP.getValue().equals(info.getServerType())) {
		    needToWrite = sftpService.doRecv(info, tempList);
		} else if (DictCodeInterfList.接口服务器类型_SFTP_IDENTITY.getValue().equals(info.getServerType())) {
		    needToWrite = sftpService.doRecvId(info, tempList);
		}else if(DictCodeInterfList.接口服务器类型_DB.getValue().equals(info.getServerType())){
		    needToWrite = dbService.doRecv(info, tempList);
		}else if(DictCodeInterfList.接口服务器类型_LOCALHOST.getValue().equals(info.getServerType())){
		    needToWrite = localService.doRecv(info, tempList);
		}
		return needToWrite;
	}

}
