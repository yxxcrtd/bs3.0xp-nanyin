package com.joyintech.tams.svreport.chbsd.prdraisetotsub.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.component.svrreport.chb.dto.ChbPrdRaisetotSubDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.service.RaiseTotalRegist001Service;
import com.joyintech.tams.svreport.chbsd.prdraisetotsub.dao.SdRaiseTotalSvrMapper;
import com.joyintech.tams.svreport.chbsd.prdraisetotsub.dto.SdRaiseTotalSvrDto;

/**
 * 功能:产品募集总量登记报送批处理主service
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @作者：zhaoyongsheng @创建时间： @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SdProdRaisetotBatchService {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SdProdRaisetotBatchServiceOneService sdProdRaisetotBatchServiceOneService;
	
	@Autowired
	private SdRaiseTotalSvrMapper sdRaiseTotalSvrMapper;

	@Autowired
	private RaiseTotalRegist001Service raiseTotalRegist001Service;
	
	public void sendRaisetotInfoMsg(Date batchDate, List<String> prodIdList) throws Exception  {
		logger.debug("产品募集总量信息登记未报送数据检索开始------" + df.format(new Date()));
		// 1、报送数据检索,取得 List
		List<SdRaiseTotalSvrDto> listSub = sdRaiseTotalSvrMapper.selcteDataByIds(DateUtil.format(batchDate), prodIdList);
		listSub.forEach(dto -> {
			if(StringUtil.isNotEmpty(dto.getIsConsign())){
				dto.setIsConsign(dto.getIsConsign()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债,dto.getIsConsign()));
			}
		});
		logger.debug("产品募集总量信息登记未报送数据检索结束------" + df.format(new Date()));

		if(StringUtil.isEmptyList(listSub)){
			logger.info("没有未报送数据，批处理结束");
    		return;
    	} 

		// 调用check逻辑
		logger.debug("产品募集总量信息登记数据检查开始------检查前数据个数：" + listSub.size());
		List<CheckReport001Dto> resultlist = raiseTotalRegist001Service.check(null);
		Map<String,CheckReport001Dto> checkmap = resultlist.stream().collect(Collectors.toMap(CheckReport001Dto::getRefseqno,(p)->p,(key1, key2)->key1));
		for (int i = listSub.size() - 1; i >= 0; i--){
			if (checkmap.containsKey(listSub.get(i).getRegistSeqno())
					&& (StringUtil.isNotEmpty(checkmap.get(listSub.get(i).getRegistSeqno()).getErrorInfo())
					|| (StringUtil.isNotEmpty(checkmap.get(listSub.get(i).getRegistSeqno()).getWarnInfo())
					&& !StringUtil.equals("01", CacheUtil.getSysParam("CHB_WARNING_CHECKRESULT_IS_SEND"))))){
				listSub.remove(i);
			}
		}
		logger.debug("产品募集总量信息登记数据检查结束------检查后数据个数：" + listSub.size());
		
		if(StringUtil.isEmptyList(listSub)){
			logger.info("检查后的数据全部不合规，批处理结束");
    		return;
    	}
		//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		List<ChbPrdRaisetotSubDto> dataList = new ArrayList<ChbPrdRaisetotSubDto>();
		for(SdRaiseTotalSvrDto sdDto : listSub){
			ChbPrdRaisetotSubDto target = new ChbPrdRaisetotSubDto();
			BeanUtils.copyProperties(target, sdDto); 
			dataList.add(target);
		}

		this.sendBatchCommon(dataList);
	}

	/**
	 * 发送共通处理
	 * @param dataList
	 */
	public void sendBatchCommon(List<ChbPrdRaisetotSubDto> dataList) throws Exception{
		// 报文发送
		logger.debug("产品募集总量信息登记报文报送开始------" + df.format(new Date()));
		//获取单批次发送条目数
		int dataCountPerTime = Integer.valueOf(
				CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME_FIRST));
		// 处理批次
		int handleIndex = 1;
		int dataCount = dataList.size();
		// 报送数据条数
		List<ChbPrdRaisetotSubDto> sendResult = new ArrayList<ChbPrdRaisetotSubDto>();
		List<ChbPrdRaisetotSubDto> newDataList = new ArrayList<ChbPrdRaisetotSubDto>();
		newDataList.addAll(dataList);
		//分批处理数据报送
		if (dataCount <= dataCountPerTime) {
			sdProdRaisetotBatchServiceOneService
					.handleDataPerTime(handleIndex, dataCountPerTime,dataList,sendResult);
		}else{
			// 分批次数
			int part = dataCount / dataCountPerTime;
			for (int i = 0; i < part; i++ ) {
				sdProdRaisetotBatchServiceOneService
						.handleDataPerTime(handleIndex, dataCountPerTime,newDataList.subList(0, dataCountPerTime),
								sendResult);
				newDataList.subList(0, dataCountPerTime).clear();
				handleIndex ++;

			}
			// 最后剩下的数据
			if (!StringUtil.isEmptyList(newDataList)) {
				sdProdRaisetotBatchServiceOneService
						.handleDataPerTime(handleIndex, dataCountPerTime,newDataList,sendResult);
			}
		}
		//ChbMsgReturnInfoDto returnMsg = msgSend.sendChbPrdRaisendotSubMsgs(dataList);
		logger.debug("产品募集总量信息登记报文报送结束------" + df.format(new Date()));
		//4、日志输出
		logger.info("产品募集总量信息登记自动报送处理结束, 共检索出未报送数据 " + dataList.size() + " 条 "
				+" 实际报送数据 " +  sendResult.size() + "条");
	}

}
