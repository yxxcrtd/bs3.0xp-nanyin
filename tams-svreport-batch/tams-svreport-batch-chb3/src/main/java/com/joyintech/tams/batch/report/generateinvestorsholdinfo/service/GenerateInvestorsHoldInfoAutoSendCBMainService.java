/*
 * 文件名：GetInvestorsHoldInfoRegistAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.report.generateinvestorsholdinfo.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.generateinvestorsholdinfo.dao.GenerateInvestorsHoldInfoAutoSendCBMapper;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfo;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSub;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 投资者持有信息根据产品到期信息及导入信息自动生成批处理service
 * @author wangmin
 * @version 1.0 2018年9月25日
 * @see GenerateInvestorsHoldInfoAutoSendCBMainService
 * @since 1.0
 */
@Service
public class GenerateInvestorsHoldInfoAutoSendCBMainService  extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 投资者持有信息登记数据自动生成批处理 业务mapper
     */
    @Autowired
    private GenerateInvestorsHoldInfoAutoSendCBMapper mapper;
    
    @Value("${application.reportSelectOrDeleteCount}")
    public  int reportSelectOrDeleteCount;
    /**
     * 自动检查报送
     * @param executeDate 
     *
     * @see
     * @since 1.0
     */
    public void generateRegistData(String executeDateStr) {
    	
    	setBatchBeanName(BatchBeanName.投资者报送数据生成);
    	
    	try{
            logInfoBatchStart();

    		String isGenerateInvewtor = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.IS_GENERATE_INVESTOR);
        	/**
        	 * 开关打开时
        	 * 1.首先判断该T-1日持有信息是否有持有信息，若无不生成持有信息，若有则进行一下操作
        	 * 2.查询未到期的，且T-1日不存在，T-1之前存在的持有信息
        	 * 3.生成持有信息
        	 * 
        	 */
    		String generateDate = DateUtil.getYesterDayDate();
        	if(StringUtil.equals(DictCodeList.是否_中债_是.getValue(), isGenerateInvewtor)){
        		//判断T-1日是否有持有信息
        		if(mapper.queryInvestorInfoCount(generateDate)>0){
        			//查询未到期的，且T-1日不存在，T-1之前存在的持有信息
        			List<ChbInvestholdInfo> investHoldList = mapper.queryGenerateList(generateDate);
        			Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        			List<Date> dateList = null;
        			Date holdDate = DateUtil.parse(generateDate);
        			List<ChbInvestholdInfoSub> subList = new ArrayList<ChbInvestholdInfoSub>();
        			ChbInvestholdInfoSub chbInvestholdInfoSub = null;
        			//处理维护信息表
        			for (ChbInvestholdInfo chbInvestholdInfo : investHoldList) {
        				chbInvestholdInfo.setHoldDate(holdDate);
        				chbInvestholdInfo.setCreateUser("system");
        				chbInvestholdInfo.setUpdateUser("system");
        				chbInvestholdInfoSub = new ChbInvestholdInfoSub();
        				//登记表处理
        				chbInvestholdInfoSub.setAmount(chbInvestholdInfo.getAmount());
        				chbInvestholdInfoSub.setCcy(chbInvestholdInfo.getCcy());
        				dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_投资者持有信息登记.getValue(), DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
        						chbInvestholdInfo.getHoldDate());
        				if (CollectionUtils.isNotEmpty(dateList)) {
        					chbInvestholdInfoSub.setChbSubmitDeadline(dateList.get(0));
        					chbInvestholdInfoSub.setSubmitDeadline(dateList.get(1));
        				}
        				chbInvestholdInfoSub.setCheckSeqno("0");
        				chbInvestholdInfoSub.setCnyAmt(chbInvestholdInfo.getCnyAmt());
        				chbInvestholdInfoSub.setCreateDept(chbInvestholdInfo.getCreateDept());
        				chbInvestholdInfoSub.setCreateUser("system");
        				chbInvestholdInfoSub.setDeptCode(chbInvestholdInfo.getDeptCode());
        				chbInvestholdInfoSub.setHoldDate(holdDate);
        				chbInvestholdInfoSub.setIndentifyMark(chbInvestholdInfo.getIndentifyMark());
        				chbInvestholdInfoSub.setOrgCode(chbInvestholdInfo.getOrgCode());
        				chbInvestholdInfoSub.setPrdRegistCode(chbInvestholdInfo.getPrdRegistCode());
        				chbInvestholdInfoSub.setProdid(chbInvestholdInfo.getProdid());
        				chbInvestholdInfoSub.setQty(chbInvestholdInfo.getQty());
        				chbInvestholdInfoSub.setRegistBankCode(chbInvestholdInfo.getRegistBankCode());
        				chbInvestholdInfoSub.setRegistSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
        				chbInvestholdInfoSub.setRemark(chbInvestholdInfo.getRemark());
        				chbInvestholdInfoSub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
        				chbInvestholdInfoSub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
        				chbInvestholdInfoSub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
        				chbInvestholdInfoSub.setUpdateUser("system");
        				subList.add(chbInvestholdInfoSub);
					}
        			int  singalCount = reportSelectOrDeleteCount;
        			int totalCount = investHoldList.size();
        			//批量插入表
        			if(totalCount>0){
        				if(totalCount<=singalCount){
        					mapper.insertInvestorsHoldBatchList(investHoldList);
        					mapper.insertInvestorsHoldRegistBatchList(subList);
        				}
        				else{
        					for(int i = 0; i < totalCount/singalCount ; i++){
        						mapper.insertInvestorsHoldBatchList(investHoldList.subList(0, singalCount));
        						mapper.insertInvestorsHoldRegistBatchList(subList.subList(0, singalCount));    
        						investHoldList.removeAll(investHoldList.subList(0, singalCount));
        						subList.removeAll(subList.subList(0, singalCount));
        					}
                            if(CollectionUtils.isNotEmpty(investHoldList)){
                            	mapper.insertInvestorsHoldBatchList(investHoldList);
        						mapper.insertInvestorsHoldRegistBatchList(subList);                                }
        				}
        			}
        		}
        	}
        	else{
        		logger.info("存续期内缺少持有信息的产品系统是否自动生成投资者身份信息开关关闭，不生成持有信息");
        	}
        	
            logInfoBatchEnd();
    	}
    	 catch (Exception e) {
             logInfo(e.getMessage());
			 logger.error("投资者持有信息根据产品到期信息及导入信息自动生成批处理Error",e);
             insertResultLogException(DateUtil.now());
             throw e;
         }
    }
}
