package com.joyintech.tams.svreport.chb.botastpositionregist.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.common.base.dao.ChbBotAstPositionSubMapper;
import com.joyintech.tams.common.base.entity.ChbBotAstPositionSub;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPosCBMapper;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:底层持仓登记批处理
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbBotAstPosRegistBatchService  extends SvrCBBaseService{

    @Autowired
    private ChbBotAstPosCBMapper chbBotAstPosCBMapper;
    
    @Autowired
    private ChbBotAstPositionSubMapper chbBotAstPositionSubMapper;
    
    
	public void createRegist(Date batchTime){
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.底层持仓信息登记报送);

        logInfoBatchStart();

        createRegistAction(batchTime);
		logInfoBatchEnd();
	}
	
	private void createRegistAction(Date batchTime){
		List<Date> isUpdate = chbBotAstPosCBMapper.queryUpdateDate(DateUtil.format(batchTime));
		if(CollectionUtils.isEmpty(isUpdate))
			return;
		for(Date posDate : isUpdate){
			//每周日或每月最后一天生成登记
			if(!isLastDay(posDate)) continue;

			//得到底层资产持仓信息
			String isChbBtm = "01";//通道是否穿透，默认“是”
			isChbBtm = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_CHL_BTM);
			List<Date> dateList = null;
	    	Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
			dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_资管计划底层持仓信息登记.getValue(), DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
					posDate);
			Date chbSubmitDeadline = null;
			Date submitDeadline = null;
			if (CollectionUtils.isNotEmpty(dateList)) {
				chbSubmitDeadline = dateList.get(0);
				submitDeadline = dateList.get(1);
			}
			//删除数据
			Map<String,Object> para = new HashMap<String,Object>();
			para.put("posDate", DateUtil.format(posDate));
			para.put("sendStatus", "01");
			chbBotAstPosCBMapper.delete(para);
			List<ChbBotAstPositionSub> updateOrDelList = chbBotAstPosCBMapper.queryUpdateOrDelList(DateUtil.format(posDate), isChbBtm);
			for (ChbBotAstPositionSub sub : updateOrDelList) {
				sub.setRegistSeqno(IDGenerator.generate(36));
                sub.setCreateUser(CommonUtil.getUserName());
                sub.setUpdateUser(CommonUtil.getUserName());
				// 数据日期
				sub.setStatusDate(batchTime);
				sub.setChbSubmitDeadline(chbSubmitDeadline);
				sub.setSubmitDeadline(submitDeadline);
				sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
				chbBotAstPositionSubMapper.insert(sub);
			}
			
			chbBotAstPosCBMapper.queryGenerateListAndInsert(DateUtil.format(posDate), isChbBtm, ToolsUtil.getCurrentTime(batchTime),
					chbSubmitDeadline, submitDeadline);
		}
	}
    
	/*
	 * 判断是否为周末或者月末
	 */
	public boolean isLastDay(Date date) {
		boolean rs = false;
 	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));//加一天
	    if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {//1号
	    	rs =  true;
	    }
	    if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {//周1
	    	rs =  true;
	    }
	    return rs;
	}


    /**
     * 功能:数据变更接口统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param executeDate
     * @param prodList
     * @param assetList
     * @throws BusinessException 
     * @see com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService#execute(java.util.Date, java.util.List, java.util.List)
     */
    @Override
    public void execute(Date executeDate, List<String> prodList, List<String> assetList,Map<String,List<String>> param) throws BusinessException {
        try {
        	createRegist(executeDate);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
	
}