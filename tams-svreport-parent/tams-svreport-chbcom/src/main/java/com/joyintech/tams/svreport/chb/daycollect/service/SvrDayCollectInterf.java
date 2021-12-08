package com.joyintech.tams.svreport.chb.daycollect.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.chb.daycollect.dao.SvrDayCollectInterfMapper;
import com.joyintech.tams.svreport.chb.daycollect.dto.ChbDayCollect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrDayCollectInterf extends BaseService{
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SvrDayCollectInterfMapper svrDayCollectInterfMapper;

    public void generateData(Date batchDate) {
		if(svrDayCollectInterfMapper.checkTransaction(DateUtil.format(batchDate)) == 0){
			logger.info("当日交易数据不存在，或者没有接入，请确认后跑批。");
			return;
		}
		if(svrDayCollectInterfMapper.checkIdentity(DateUtil.format(batchDate)) == 0){
			logger.info("当日身份信息不存在，或者没有接入,批处理继续。");
		}
    	//1、开始检数据
    	logger.info("销售月报每日明细汇总信息检索开始------->>>>>>");
    	List<ChbDayCollect> list = svrDayCollectInterfMapper.selectBatchData(DateUtil.format(batchDate));
    	logger.info("销售月报每日明细汇总信息检索结束-------<<<<<<");

    	if(StringUtil.isEmptyList(list)){
    		logger.info("没有检索到数据,批处理结束!");
    		return;
    	}
		//更新表
    	logger.info("销售月报每日明细汇总信息入库开始------->>>>>>");
    	dbData(list,batchDate);
    	logger.info("销售月报每日明细汇总信息入库结束-------<<<<<<");
    }
    
    /**
     * 先删后插
     */
    private void dbData(List<ChbDayCollect> list,Date batchDate) {
    	
    	if (StringUtil.isNotEmpty(list)) {
			//当日汇总数据清除
			logger.debug("当日汇总数据清除开始----------");
			svrDayCollectInterfMapper.deleteBatchData(DateUtil.format(batchDate));
			logger.debug("当日汇总数据清除结束----------");

            logger.debug("销售月报每日明细汇总信息插入数据：插入开始----------");
			for(ChbDayCollect sub : list){
				if(sub.getHolderType() == null) {
					sub.setHolderType("00");
				}
				svrDayCollectInterfMapper.insertBatchData(sub);
			}
            logger.debug("销售月报每日明细汇总信息插入数据：插入结束----------");
		}
	}
}
