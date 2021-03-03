package com.joyintech.tams.svreport.chbsd.bottomastsub.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.component.svrreport.chb.dto.ChbBotAstPosSubDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;
import com.joyintech.tams.svreport.chb.botastpositionregist.service.ChbBotAstPositionRegist001Service;
import com.joyintech.tams.svreport.chbsd.bottomastsub.dao.SdBotAssetPosSvrMapper;
/**
 * 功能:底层资产持仓信息登记信息登记直连报送生成批处理主service
 * @since 1.0.0
 * @version 1.0.0
 * @作者：lingxq
 * @创建时间：
 * @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SdBotAssetPosRegBatchService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	@Autowired
	private SdBotAssetPosSvrMapper sdBotAssetPosSvrMapper;
	
	@Autowired
	private SdBotAssetPosRegBatchServiceOneService sdBotAssetPosRegBatchServiceOneService;

	@Autowired
	private ChbBotAstPositionRegist001Service chbBotAstPositionRegist001Service;
	
	public void sdBottomAstPositionRegData(Date batchDate) throws Exception {
		//1、报送数据检索,取得 List
		logger.info("【底层资产持仓信息登记】未报送数据检索开始------" +  df.format(new Date()));
		List<ChbBotAstPositionRegist001Dto> list = sdBotAssetPosSvrMapper.selcteUnRegData(DateUtil.format(batchDate));
		logger.info("【底层资产持仓信息登记】未报送数据检索结束------" +  df.format(new Date()));

		if(StringUtil.isEmptyList(list)){
			logger.info("没有未报送数据，批处理结束");
    		return;
    	}

		// 调用check逻辑
		logger.debug("【底层资产持仓信息登记】数据检查开始------检查前数据个数：" + list.size());
		List<CheckReport001Dto> resultlist = chbBotAstPositionRegist001Service.check(null);
		Map<String,CheckReport001Dto> checkmap = resultlist.stream().collect(Collectors.toMap(CheckReport001Dto::getRefseqno,(p)->p,(key1, key2)->key1));
		for (int i = list.size() - 1; i >= 0; i--){
			if (checkmap.containsKey(list.get(i).getRegistSeqno())
					&& (StringUtil.isNotEmpty(checkmap.get(list.get(i).getRegistSeqno()).getErrorInfo())
					|| (StringUtil.isNotEmpty(checkmap.get(list.get(i).getRegistSeqno()).getWarnInfo())
					&& !StringUtil.equals("01", CacheUtil.getSysParam("CHB_WARNING_CHECKRESULT_IS_SEND"))))){
				list.remove(i);
			}
		}
		logger.debug("【底层资产持仓信息登记】数据检查结束------检查后数据个数：" + list.size());

		if(StringUtil.isEmptyList(list)){
			logger.info("检查后的数据全部不合规，批处理结束");
    		return;
    	}
		
		//2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		List<ChbBotAstPosSubDto> msgRstList = transformModelToMsg(list);

		this.sendBatchCommon(msgRstList);
	}

	/**
	 * 将报送数据的DTO转化为报文拼接所需要的DTO
	 * @param list
	 * @return
	 */
	private List<ChbBotAstPosSubDto> transformModelToMsg(List<ChbBotAstPositionRegist001Dto> list) {
		List<ChbBotAstPosSubDto> resultList = new ArrayList<ChbBotAstPosSubDto>();
		list.forEach(item -> {
			ChbBotAstPosSubDto tmp = new ChbBotAstPosSubDto();
			BeanUtils.copyProperties(item,tmp);
			resultList.add(tmp);
		});
		return resultList;
	}

	/**
	 * 发送共通处理
	 * @param dataList
	 */
	public void sendBatchCommon(List<ChbBotAstPosSubDto> dataList) throws Exception{
		int dataCountPerTime = Integer.valueOf(
				CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME_FIRST));

		logger.info("开始按批次处理【资产交易信息登记】数据,共检索出未报送数据:" + dataList.size() + " 条,"+"每批次处理数据条数：" + dataCountPerTime);

		// 处理批次
		int handleIndex = 1;

		int dataCount = dataList.size();
		// 报送数据条数
		List<ChbBotAstPosSubDto> sendResult = new ArrayList<ChbBotAstPosSubDto>();
		List<ChbBotAstPosSubDto> newDataList = new ArrayList<ChbBotAstPosSubDto>();
		newDataList.addAll(dataList);
		//分批处理数据报送
		if (dataCount <= dataCountPerTime) {
			sdBotAssetPosRegBatchServiceOneService
					.handleDataPerTime(handleIndex, dataCountPerTime,dataList,sendResult);
		}else{
			// 分批次数
			int part = dataCount / dataCountPerTime;
			for (int i = 0; i < part; i++ ) {
				sdBotAssetPosRegBatchServiceOneService
						.handleDataPerTime(handleIndex, dataCountPerTime,newDataList.subList(0, dataCountPerTime),
								sendResult);
				newDataList.subList(0, dataCountPerTime).clear();
				handleIndex ++;
			}
			// 最后剩下的数据
			if (!StringUtil.isEmptyList(newDataList)) {
				sdBotAssetPosRegBatchServiceOneService
						.handleDataPerTime(handleIndex, dataCountPerTime,newDataList,sendResult);
			}
		}

		//3、日志输出
		logger.info("【底层资产持仓信息登记】自动报送处理结束, 共检索出未报送数据 " + dataList.size() + " 条 "
				+" 实际报送数据 " + sendResult.size() + "条");
	}
}
