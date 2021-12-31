package com.joyintech.tams.svreport.chb.identityrepair.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.chb.datacheck.beforeinsert.service.ChbCommonCheckService;
import com.joyintech.tams.svreport.chb.identityrepair.dao.SvrIdentityRepairInterfMapper;
import com.joyintech.tams.svreport.chb.identityrepair.dto.ChbIdentityRepair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 功能:身份信息修复批处理
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrIdentityRepairInterf extends BaseService{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SvrIdentityRepairInterfMapper svrIdentityRepairInterfMapper;


	/**
	 * 入库前校验用Service
	 */
	@Autowired
	private ChbCommonCheckService chbCommonCheckService;

	public void generateData(Date batchDate) {
        //获取最后一次修复时间参数
		String lastRepairTime = svrIdentityRepairInterfMapper.getLastRepairTime();
		if(svrIdentityRepairInterfMapper.idenCheck(lastRepairTime) == 0){
			logger.info("当日身份信息数据不存在，或者没有接入，批处理退出。");
			return;
		}
		//1、开始检索维护表数据  根据每日的明细进行数据的修复
		logger.info("身份信息修复批处理信息检索开始------->>>>>>");

		List<ChbIdentityRepair> list = svrIdentityRepairInterfMapper.selectBatchData(lastRepairTime);

		logger.info("身份信息修复批处理信息检索结束-------<<<<<<");

		if(StringUtil.isEmptyList(list)){
			logger.info("没有检索到数据,批处理结束!");
			return;
		}

		//4、入库
		logger.info("身份信息修复批处理信息入库开始------->>>>>>");
		dbData(list,batchDate);
		logger.info("身份信息修复批处理信息入库结束-------<<<<<<");

	}

	/**
	 * 更新数据
	 * @param list
	 */
	private void dbData(List<ChbIdentityRepair> list,Date batchDate) {
		if (StringUtil.isNotEmpty(list)) {
			logger.debug("身份信息修复批处理登记插入数据：插入开始----------");
			for(ChbIdentityRepair sub : list){
				String fundAccPlace = sub.getFundAccPlace();
				String orgIndentifyMark = sub.getOrgIndentifyMark();
				String agencyCode = sub.getAgencyCode();
				String registBankCode = sub.getRegistBankCode();
				if(sub.getEquMark().equals("SUB")) {
					svrIdentityRepairInterfMapper.updataSub(agencyCode,registBankCode,fundAccPlace,orgIndentifyMark);
				}
				if(sub.getEquMark().equals("CHECK")) {
					svrIdentityRepairInterfMapper.updataCheck(agencyCode,registBankCode,fundAccPlace,orgIndentifyMark);
				}
			}
			//更新最后一次修复时间为实际时间
			svrIdentityRepairInterfMapper.updataLastRepairTime();
			logger.debug("身份信息修复批处理登记插入数据：插入结束----------");
		}
	}
}
