/*
 * 文件名：ESBLC005InterfAction.java
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
import com.dc.eai.data.Array;
import com.dc.eai.data.CompositeData;
import com.dc.eai.data.Field;
import com.dc.eai.data.FieldAttr;
import com.dc.eai.data.FieldType;
import com.dcfs.esb.client.ESBClient;
import com.dcfs.esb.client.exception.TimeoutException;
import com.joyintech.tams.valuation.interf.valuation.send.dao.ESBLC005InterfActionMapper;
import com.joyintech.tams.valuation.interf.valuation.send.dto.ESBLC005Dto;
import com.joyintech.tams.valuation.interf.valuation.send.dto.SendPageResult;
import com.joyintech.tams.valuation.interf.valuation.send.utils.ESBInterfaceUtil;

public class ESBLC005InterfAction {
	/**
	 * 服务代码
	 */
	private static final String SERVICECODE = "09003000013";
	/**
	 * 服务场景
	 */
	private static final String SERVICE_SCENE = "15";
	/**
	 * USERID
	 */
	private static final String USERID = "120001";
	/**
	 * 交易码
	 */
	private static final String TRANCODE = "448103";
	
	 @Autowired
	 private ESBLC005InterfActionMapper eSBLC005InterfActionMapper;
	/**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(ESBLC005InterfAction.class);
    
 	public String execute(SendPageResult context){
		//获取当前时间
		long beginTime = System.currentTimeMillis();
		log.info("ESBLC005InterfAction----- execute beginTime =" + beginTime);
		
		String prodid = context.getProdId();
		String cdate  = context.getCdate();
		
		log.info("ESBLC005InterfAction-----  prodid=" + prodid + ",cdate=" + cdate);
		
		if (StringUtils.isBlank(prodid)) {
			log.error("ESBLC005InterfAction----- 产品代码为空!");
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
			Map<String, String> sysMap = new HashMap<String, String>();
			// 服务请求发送方流水号
			//consumer_seq_no = ESBInterfaceUtil.queryBusSeq(connection);
			// 发起方业务流水号
			//biz_seq_no = ESBInterfaceUtil.queryBusSeq(connection);
			
			//取值逻辑不明758373+编号+sequens
			consumer_seq_no="758373"+prodid;
			biz_seq_no="758373"+prodid;
			

			sysMap.put("SERVICE_CODE", SERVICECODE);
			sysMap.put("SERVICE_SCENE", SERVICE_SCENE);
			sysMap.put("CONSUMER_SEQ_NO", consumer_seq_no);
			sysMap.put("BIZ_SEQ_NO", biz_seq_no);
			sysMap.put("TRAN_CODE", tran_code);
			sysMap.put("WS_ID", "G");
			
			// 报文体数据拼接
			CompositeData bodyData = new CompositeData();
			// body发送数据信息
			if ("-1".equals(queryBodyData(context, bodyData))) {
				log.error("ESBLC005InterfAction----- 组装接口报文失败!");
				return "-1";
			}
			//组装ESB请求数据，定义相关结构体
			CompositeData reqData = ESBInterfaceUtil.createEsbReqData(sysMap,
					bodyData);
			log.info( "ESBLC005InterfAction----- ESB发送报文：\r\n"+ reqData);
			
			// 发送并接收返回信息
			CompositeData rspData = ESBClient.request(reqData);
			log.info( "ESBLC005InterfAction----- ESB接收报文:\r\n"+ rspData);
			
			//ESB返回状态
			Map<String, String> returnMsg = ESBInterfaceUtil
					.esbResultMsg(rspData);
			if (!"S".equals(returnMsg.get("RET_STATUS"))) {
				return "-1";
			}
			
		} catch (TimeoutException te) {
			log.error("ESBLC005InterfAction-----发送ESB信息超时，错误详情：TimeoutException te=" + te.getMessage());
			return "-1";
		} catch (Exception ex) {
			log.error("ESBLC005InterfAction-----发送ESB信息失败，错误详情：Exception ex=" + ex.getMessage());
			return "-1";
		}
		long endTime = System.currentTimeMillis();
		log.info("ESBLC005InterfAction----- execute endTime =" + endTime);
		log.info("ESBLC005InterfAction----- execute time = " + (endTime - beginTime));
		return "0";
	}
	/**
	 * 查询接口报文休
	 * 
	 * @param connection
	 * @param lstProdid
	 * @return
	 */
	private String queryBodyData(SendPageResult sendPageResult, CompositeData bodyData) {
		try {
			List<ESBLC005Dto> list=new ArrayList<ESBLC005Dto>();
			list=eSBLC005InterfActionMapper.queryLC005(sendPageResult);
			Array objArray = new Array();
			CompositeData objCompositeData = null;
		
			Field field = new Field(new FieldAttr(FieldType.FIELD_STRING, 100, 0));
			for (ESBLC005Dto dto : list) {
				objCompositeData = new CompositeData();
				// 持有产品天数区间
				field.setValue(dto.getHoldDaysInterval());
				objCompositeData.addField("HOLD_DAYS_INTERVAL", field);

				// 购买金额区间
				field = new Field(new FieldAttr(FieldType.FIELD_STRING, 100, 0));
				field.setValue(dto.getBuyAmtInterval());
				objCompositeData.addField("BUY_AMT_INTERVAL", field);

				// 客户等级区间
				field = new Field(new FieldAttr(FieldType.FIELD_STRING, 20, 0));
				field.setValue(dto.getClientLevelInterval());
				objCompositeData.addField("CLIENT_LEVEL_INTERVAL", field);

				// 分层收益率
				field = new Field(new FieldAttr(FieldType.FIELD_DOUBLE, 20, 7));
				field.setValue(Double.valueOf(dto.getBedYield()));
				objCompositeData.addField("BED_YIELD", field);

				// 年计息天数
				field = new Field(new FieldAttr(FieldType.FIELD_STRING, 10, 0));
				field.setValue(dto.getYearInterestDays());
				objCompositeData.addField("YEAR_INTEREST_DAYS", field);

				// 添加列表对象
				objArray.addStruct(objCompositeData);
				
			}
			
			// 产品代码
			field = new Field(new FieldAttr(FieldType.FIELD_STRING, 20, 0));
			field.setValue(list.get(0).getProductCode());
			bodyData.addField("PRODUCT_CODE", field);
			// 产品成立日期
			field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
			field.setValue(list.get(0).getEstablishDate());
			bodyData.addField("ESTABLISH_DATE", field);
			// 到期日
			field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
			field.setValue(list.get(0).getExpiryDate());
			bodyData.addField("EXPIRY_DATE", field);
			// 销售费率
			field = new Field(new FieldAttr(FieldType.FIELD_DOUBLE, 20, 9));
			field.setValue(list.get(0).getSaleFeeRate());
			bodyData.addField("SALE_FEE_RATE", field);
			
			// 产品信息数组
			bodyData.addArray("PRODUCT_ARRAY", objArray);
			
		}
		 catch (Exception ex) {
			log.error("ESBLC005InterfAction-----组装报文出错，错误详情：Exception ex="+ ex.getMessage());
			return "-1";
		} 
		return "0";
	}
	
}
