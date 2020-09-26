/*
 * 文件名：ESB448103InterfAction.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：平安估值核算系统ESB448103净值下发接口
 * 修改人：wangwei1
 * 修改时间：2018年8月03日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.tams.valuation.interf.valuation.send.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dc.eai.data.CompositeData;
import com.dc.eai.data.Field;
import com.dc.eai.data.FieldAttr;
import com.dc.eai.data.FieldType;
import com.dcfs.esb.client.ESBClient;
import com.dcfs.esb.client.exception.TimeoutException;
import com.joyintech.tams.valuation.interf.valuation.send.dao.ESB448103InterfActionMapper;
import com.joyintech.tams.valuation.interf.valuation.send.dto.ESB448103Dto;
import com.joyintech.tams.valuation.interf.valuation.send.dto.SendPageResult;
import com.joyintech.tams.valuation.interf.valuation.send.utils.ESBInterfaceUtil;

public class ESB448103InterfAction {
	/**
	 * 服务代码
	 */
	private static final String SERVICECODE = "09002000013";
	/**
	 * 服务场景 同业
	 */
	private static final String SERVICESCENE_T = "09";
	/**
	 * 服务场景 对公
	 */
	private static final String SERVICESCENE_G = "11";
	/**
	 * 服务场景 零售
	 */
	private static final String SERVICESCENE_L = "27";
	/**
	 * USERID
	 */
	private static final String USERID = "120001";
	/**
	 * 交易码
	 */
	private static final String TRANCODE = "448103";
	
	 @Autowired
	 private ESB448103InterfActionMapper eSB448103InterfActionMapper;
	
	/**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(ESB448103InterfAction.class);
    
    
	public String execute(SendPageResult context){
		//获取当前时间
		long beginTime = System.currentTimeMillis();
		log.info("ESB448103InterfAction----- execute beginTime =" + beginTime);
		
		String prodid = context.getProdId();
		String cdate  = context.getCdate();
		
		log.info("ESB448103InterfAction-----  prodid=" + prodid + ",cdate=" + cdate);
		
		if (StringUtils.isBlank(prodid)) {
			log.error("ESB448103InterfAction----- 产品代码为空!");
			return "-1";
		}
		if (StringUtils.isBlank(cdate)) {
			cdate = ESBInterfaceUtil.getCurDate();
		} else {
			cdate = cdate.replaceAll("-", "");
		}
		
		// 服务请求发送方流水号
		String consumer_seq_no = "";
		// 发起方业务流水号
		String biz_seq_no = "";
		// 交易码
		String tran_code = TRANCODE;

		try {
			// 查询销售渠道
			String mktDeptNo = eSB448103InterfActionMapper.getMktDeptNo(context);
			//避免估值系统无数据，默认为零售
			if(!mktDeptNo.isEmpty() && ("L".equalsIgnoreCase(mktDeptNo) || "零售".equals(mktDeptNo)) ){
		       mktDeptNo ="L";
			}else if(!mktDeptNo.isEmpty() && ("G".equalsIgnoreCase(mktDeptNo) || "对公".equals(mktDeptNo))){
			   mktDeptNo ="G";
			}else if(!mktDeptNo.isEmpty() && ("T".equalsIgnoreCase(mktDeptNo) || "同业".equals(mktDeptNo))){
			   mktDeptNo ="G";
			}else{
				mktDeptNo ="L";
			}
			
			if ("-1".equals(mktDeptNo)) {
				log.error("ESB448103InterfAction----- 查询销售部门出错!");
				return "-1";
			}
			Map<String, String> sysMap = new HashMap<String, String>();
			// 服务请求发送方流水号  758373+编号+sequens
			//consumer_seq_no = ESBInterfaceUtil.queryBusSeq(connection);
			// 发起方业务流水号
			//biz_seq_no = ESBInterfaceUtil.queryBusSeq(connection);
			
			consumer_seq_no="758373"+prodid;
			biz_seq_no="758373"+prodid;

			sysMap.put("SERVICE_CODE", SERVICECODE);
			if ("L".equalsIgnoreCase(mktDeptNo)) {
				sysMap.put("SERVICE_SCENE", SERVICESCENE_L);
			} else if ("G".equalsIgnoreCase(mktDeptNo)) {
				sysMap.put("SERVICE_SCENE", SERVICESCENE_G);
			} else {
				sysMap.put("SERVICE_SCENE", SERVICESCENE_T);
			}
			sysMap.put("CONSUMER_SEQ_NO", consumer_seq_no);
			sysMap.put("BIZ_SEQ_NO", biz_seq_no);
			sysMap.put("TRAN_CODE", tran_code);
			sysMap.put("WS_ID", "G");
			
			// 报文体数据拼接
			CompositeData bodyData = new CompositeData();
			// body发送数据信息
			if ("-1".equals(queryBodyData(context, bodyData))) {
				log.error("ESB448103InterfAction----- 组装接口报文失败!");
				return "-1";
			}
			//组装ESB请求数据，定义相关结构体
			CompositeData reqData = ESBInterfaceUtil.createEsbReqData(sysMap,
					bodyData);
			log.info( "ESB448103InterfAction----- ESB发送报文：\r\n"+ reqData);
			
			// 发送并接收返回信息
			CompositeData rspData = ESBClient.request(reqData);
			log.info( "ESB448103InterfAction----- ESB接收报文:\r\n"+ rspData);
			
			//ESB返回状态
			Map<String, String> returnMsg = ESBInterfaceUtil
					.esbResultMsg(rspData);
			if (!"S".equals(returnMsg.get("RET_STATUS"))) {
				return "-1";
			}
			
		} catch (TimeoutException te) {
			log.error("ESB448103InterfAction-----发送ESB信息超时，错误详情：TimeoutException te=" + te.getMessage());
			return "-1";
		} catch (Exception ex) {
			log.error("ESB448103InterfAction-----发送ESB信息失败，错误详情：Exception ex=" + ex.getMessage());
			return "-1";
		}
		long endTime = System.currentTimeMillis();
		log.info("ESB448103InterfAction----- execute endTime =" + endTime);
		log.info("ESB448103InterfAction----- execute time = " + (endTime - beginTime));
		return "0";
	}
	/**
	 * 查询接口报文休
	 * 
	 * @param connection
	 * @param lstProdid
	 * @return
	 */
	private String queryBodyData(SendPageResult sendPageResult,CompositeData bodyData) {
		try {
			List<ESB448103Dto> rs=new ArrayList<ESB448103Dto>();
			rs = eSB448103InterfActionMapper.queryEsb448103(sendPageResult);
			for (ESB448103Dto dto : rs) {
				
					Field field = new Field(new FieldAttr(
							FieldType.FIELD_STRING, 70, 0));
					field.setValue(dto.getShortname());
					bodyData.addField("ShortName", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 10,
							0));
					field.setValue(dto.getNetvalue());
					bodyData.addField("NetValue ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getNavdate());
					bodyData.addField("NavDate ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getUsedate());
					bodyData.addField("UseDate ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getTotalnetvalue());
					bodyData.addField("TotalNetValue ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getYearrate());
					bodyData.addField("YearRate ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getSevenyearrate());
					bodyData.addField("SevenYearRate ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 10,
							0));
					field.setValue(dto.getFundincome());
					bodyData.addField("FundIncome ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 10,
							0));
					field.setValue(dto.getSalerate());
					bodyData.addField("SaleRate ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 1,
							0));
					field.setValue(dto.getProdtype());
					bodyData.addField("ProdType ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getDueyield());
					bodyData.addField("DueYield ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getDuedate());
					bodyData.addField("DueDate ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getLowestyield());
					bodyData.addField("LowestYield ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getHighestyield());
					bodyData.addField("HighestYield ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getMonthyield());
					bodyData.addField("MonthYield ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getQuarteryield());
					bodyData.addField("QuarterYield ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getSemiannualyield());
					bodyData.addField("SemiAnnualYield ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getDailyincrease());
					bodyData.addField("DailyIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getDailyyearincrease());
					bodyData.addField("DailyYearIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getWeeklyincrease());
					bodyData.addField("WeeklyIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getWeeklyyearincrease());
					bodyData.addField("WeeklyYearIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getMonthlyincrease());
					bodyData.addField("MonthlyIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getMonthlyyearincrease());
					bodyData.addField("MonthlyYearIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getQuarterincrease());
					bodyData.addField("QuarterIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getQuarteryearincrease());
					bodyData.addField("QuarterYearIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getSemiannualincrease());
					bodyData.addField("SemiAnnualIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getSemiannualyearincrease());
					bodyData.addField("SemiAnnualYearIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getAnnualincrease());
					bodyData.addField("AnnualIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getAnnualyearincrease());
					bodyData.addField("AnnualYearIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 11,
							0));
					field.setValue(dto.getSinceestablishincrease());
					bodyData.addField("SinceEstablishIncrease ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getHisrate());
					bodyData.addField("HisRate ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 20,
							0));
					field.setValue(dto.getDivamt());
					bodyData.addField("DivAmt ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getRegisterdate());
					bodyData.addField("RegisterDate ", field);
					field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8,
							0));
					field.setValue(dto.getDividenddate());
					bodyData.addField("DividendDate ", field);
				}
		}  catch (Exception ex) {
			log.error("ESB448103InterfAction-----组装报文出错，错误详情：Exception ex="+ ex.getMessage());
			return "-1";
		} 
		return "0";
	}
	
}
