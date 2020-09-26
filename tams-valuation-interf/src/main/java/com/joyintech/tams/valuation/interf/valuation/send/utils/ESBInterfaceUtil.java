package com.joyintech.tams.valuation.interf.valuation.send.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dc.eai.data.Array;
import com.dc.eai.data.CompositeData;
import com.dc.eai.data.Field;
import com.dc.eai.data.FieldAttr;
import com.dc.eai.data.FieldType;
import com.dcfs.esb.client.converter.PackUtil;
import com.joyintech.framework.util.DateUtil;

/**
 * @author  ESB接口处理
 * 
 */
public class ESBInterfaceUtil {
    
	private static final String sTimeFormat = "HHmmssSSS";
	private static final String sTimeFormat6 = "HHmmss";
	  /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(ESBInterfaceUtil.class);
    
	/**
	 * 获取原ESB请求报文头
	 * 
	 * @param map
	 * @return
	 */
	public static CompositeData createReqData(Map<String, String> map,
			CompositeData bodyData) {
		/*******************************************************************
		 * *********构造请求数据，定义相关结构体******
		 ******************************************************************/
		CompositeData reqData = new CompositeData();
		CompositeData sysData = new CompositeData();
		CompositeData appData = new CompositeData();
		CompositeData localData = new CompositeData();

		reqData.addStruct("SYS_HEAD", sysData);
		reqData.addStruct("APP_HEAD", appData);
		reqData.addStruct("LOCAL_HEAD", localData);
		reqData.addStruct("BODY", bodyData);
		/*******************************************************************
		 * ********* 系统头******************
		 ******************************************************************/
		// 服务代码
		Field field = new Field(new FieldAttr(FieldType.FIELD_STRING, 30, 0));
		field.setValue(map.get("SERVICE_CODE"));
		sysData.addField("SERVICE_CODE", field);

		// 服务场景
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 2, 0));
		field.setValue(map.get("SERVICE_SCENE"));
		sysData.addField("SERVICE_SCENE", field);

		// 消费者ID
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue("758373");
		sysData.addField("CONSUMER_ID", field);

		// 发送方交易日期
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
		field.setValue(getCurDate());
		sysData.addField("TRAN_DATE", field);

		// 发送方交易时间
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 9, 0));
		field.setValue(getCurTime());
		sysData.addField("TRAN_TIMESTAMP", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("BIZ_SEQ_NO"));
		sysData.addField("ESB_SEQ_NO", field);

		// 服务请求发送方流水号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("CONSUMER_SEQ_NO"));
		sysData.addField("CONSUMER_SEQ_NO", field);

		/*******************************************************************
		 * ********** 应用体***************
		 ******************************************************************/
		// 发起方业务流水号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("BIZ_SEQ_NO"));
		appData.addField("BIZ_SEQ_NO", field);
		// 柜员号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 30, 0));
		field.setValue("#SYS01");//
		appData.addField("USER_ID", field);

		// 服务请求者的机构归属 0793 清算中心
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 10, 0));
		field.setValue("0799");
		appData.addField("BRANCH_ID", field);
		/*******************************************************************
		 * ********** LOCAL***************
		 ******************************************************************/
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 2, 0));
		field.setValue("01");
		localData.addField("ChannelId", field);
		//
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 10, 0));
		field.setValue("0799");
		localData.addField("BuildBankId", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 5, 0));
		field.setValue(map.get("TRAN_CODE"));
		localData.addField("TranCode", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
		field.setValue(getCurDate());
		localData.addField("TranDate", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue(getCurTime6());
		localData.addField("TranTime", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 20, 0));
		field.setValue("");
		localData.addField("TermId", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 50, 0));
		field.setValue("#SYS01");
		localData.addField("BuildUserId", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 42, 0));
		field.setValue(map.get("BIZ_SEQ_NO"));
		localData.addField("ThirdTrcNo", field);
		return reqData;
	}

	/**
	 * 获取新核心ESB请求报文头
	 * 
	 * @param map
	 * @return
	 */
	public static CompositeData createEsbReqData(Map<String, String> map,
			CompositeData bodyData) {
		/*******************************************************************
		 * *********构造请求数据，定义相关结构体******
		 ******************************************************************/
		CompositeData reqData = new CompositeData();
		CompositeData sysData = new CompositeData();
		CompositeData appData = new CompositeData();
		CompositeData localData = new CompositeData();

		reqData.addStruct("SYS_HEAD", sysData);
		reqData.addStruct("APP_HEAD", appData);
		reqData.addStruct("LOCAL_HEAD", localData);
		reqData.addStruct("BODY", bodyData);
		/*******************************************************************
		 * ********* 系统头******************
		 ******************************************************************/
		// 服务代码
		Field field = new Field(new FieldAttr(FieldType.FIELD_STRING, 30, 0));
		field.setValue(map.get("SERVICE_CODE"));
		sysData.addField("SERVICE_CODE", field);

		// 服务场景
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 2, 0));
		field.setValue(map.get("SERVICE_SCENE"));
		sysData.addField("SERVICE_SCENE", field);

		// 消费者ID
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue("758373");
		sysData.addField("CONSUMER_ID", field);

		// 发送方交易日期
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
		field.setValue(getCurDate());
		sysData.addField("TRAN_DATE", field);

		// 发送方交易时间
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 9, 0));
		field.setValue(getCurTime());
		sysData.addField("TRAN_TIMESTAMP", field);
		// 服务请求发送方流水号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("CONSUMER_SEQ_NO"));
		sysData.addField("CONSUMER_SEQ_NO", field);
		// 终端序号(终端标识) 由ICS分配
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 30, 0));
		String ws_id = StringUtils.isBlank(map.get("WS_ID")) ? "TCMS" : map
				.get("WS_ID");
		field.setValue(ws_id);
		sysData.addField("WS_ID", field);
		// 请求系统模块标识
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 4, 0));
		String module_id = StringUtils.isBlank(map.get("MODULE_ID")) ? "9998"
				: map.get("MODULE_ID");
		field.setValue(module_id);
		sysData.addField("MODULE_ID", field);

		// 交易码
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue(map.get("TRAN_CODE"));
		sysData.addField("TRAN_CODE", field);

		// 版本
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 30, 0));
		field.setValue("2.0");
		sysData.addField("version", field);

		// 请求系统编号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue("758373");
		sysData.addField("ORG_SYS_ID", field);
		/*******************************************************************
		 * ********** 应用体***************
		 ******************************************************************/
		// 发起方业务流水号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("BIZ_SEQ_NO"));
		appData.addField("BIZ_SEQ_NO", field);
		// 柜员号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 50, 0));
		field.setValue("EB001");//
		appData.addField("USER_ID", field);
		// 要求每页返回记录总数
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 5, 0));
		field.setValue("2");//
		appData.addField("PER_PAGE_NUM", field);
		// 发起方会计日期
		// field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
		// field.setValue(TpUtil.getCurDate());
		// field.setValue("20160126");
		// appData.addField("ACCOUNT_DATE", field);
		// 服务请求者的机构归属 0793 清算中心
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 20, 0));
		field.setValue("0793");
		appData.addField("BRANCH_ID", field);

		/*******************************************************************
		 * ********** LOCAL***************
		 ******************************************************************/
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 2, 0));
		field.setValue("02");
		localData.addField("ChannelId", field);
		//
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 10, 0));
		field.setValue("0799");
		localData.addField("BuildBankId", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 5, 0));
		field.setValue(map.get("TRAN_CODE"));
		localData.addField("TranCode", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
		field.setValue(getCurDate());
		localData.addField("TranDate", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue(getCurTime6());
		localData.addField("TranTime", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 20, 0));
		field.setValue("NET");
		localData.addField("TermId", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 50, 0));
		field.setValue("#SYS01");
		localData.addField("BuildUserId", field);

		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 42, 0));
		field.setValue(map.get("BIZ_SEQ_NO"));
		localData.addField("ThirdTrcNo", field);
		return reqData;
	}

	/**
	 * 获取ESB响应请求报文头
	 * 
	 * @param map
	 * @return
	 */
	public static CompositeData createEsbRspData(Map<String, String> map) {
		/*******************************************************************
		 * *********构造请求数据，定义相关结构体******
		 ******************************************************************/
		CompositeData reqData = new CompositeData();
		CompositeData sysData = new CompositeData();
		CompositeData appData = new CompositeData();
		CompositeData localData = new CompositeData();

		reqData.addStruct("SYS_HEAD", sysData);
		reqData.addStruct("APP_HEAD", appData);
		reqData.addStruct("LOCAL_HEAD", localData);
		/*******************************************************************
		 * ********* 系统头******************
		 ******************************************************************/
		// 服务代码
		Field field = new Field(new FieldAttr(FieldType.FIELD_STRING, 30, 0));
		field.setValue(map.get("SERVICE_CODE"));
		sysData.addField("SERVICE_CODE", field);

		// 服务场景
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 2, 0));
		field.setValue(map.get("SERVICE_SCENE"));
		sysData.addField("SERVICE_SCENE", field);

		// 消费者ID
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue("758373");
		sysData.addField("CONSUMER_ID", field);

		// 服务原始发起方的系统编号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue(map.get("ORG_SYS_ID"));
		sysData.addField("ORG_SYS_ID", field);

		// 发送方交易日期
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
//		field.setValue(sDateFormat.format(new Date(System.currentTimeMillis())));
		field.setValue(DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT));
		sysData.addField("TRAN_DATE", field);

		// 发送方交易时间
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
//		field.setValue(sTimeFormat.format(new Date(System.currentTimeMillis())));
		field.setValue(DateUtil.format(new Date(),sTimeFormat));
		sysData.addField("TRAN_TIMESTAMP", field);
		// 服务请求发送方流水号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("CONSUMER_SEQ_NO"));
		sysData.addField("CONSUMER_SEQ_NO", field);

		// 交易码
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("TRAN_CODE"));
		sysData.addField("TRAN_CODE", field);

		// 交易状态
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("RET_STATUS"));
		sysData.addField("RET_STATUS", field);

		// 返回代码、返回信息
		Array array = new Array();
		CompositeData cd = new CompositeData();
		// 交易返回代码
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue(map.get("RET_CODE"));
		cd.addField("RET_CODE", field);
		// 交易返回信息
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue(map.get("RET_MSG"));
		cd.addField("RET_MSG", field);
		array.addStruct(cd);
		sysData.addArray("RET", array);

		/*******************************************************************
		 * ********** 应用体***************
		 ******************************************************************/
		// 发起方业务流水号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 52, 0));
		field.setValue(map.get("BIZ_SEQ_NO"));
		appData.addField("BIZ_SEQ_NO", field);
		// 柜员号
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 6, 0));
		field.setValue("EB001");//
		appData.addField("USER_ID", field);
		// 发起方会计日期
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 8, 0));
//		field.setValue(sDateFormat.format(new Date(System.currentTimeMillis())));
		field.setValue(DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT));
		appData.addField("ACCOUNT_DATE", field);
		// 服务请求者的机构归属 0793 清算中心
		field = new Field(new FieldAttr(FieldType.FIELD_STRING, 4, 0));
		field.setValue("0793");
		appData.addField("BRANCH_ID", field);

		return reqData;
	}

	/**
	 * 查询busseq
	 * 
	 * @return
	 */
	public static String queryBusSeq(Connection conn) {
	    String ret = "-1";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT PKG_TCMS_CAPITALBACK.F_GET_BUSSEQ() FROM DUAL";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
			    ret = rs.getString(1);
			}
		} catch (SQLException e) {
			log.error("查询busseq出错，错误详情：" + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();				
			} catch (Exception e) {
			    log.error("关闭结果集出错，错误详情：" + e.getMessage());
			}try {
				if (rs != null)
					rs.close();				
			} catch (Exception e) {
			    log.error("关闭结果集出错，错误详情：" + e.getMessage());
			}
		}
		return ret;
	}

	/**
	 * ESB返回状态
	 * 
	 * @param repData
	 * @return
	 */
	public static boolean checkEsbStatus(CompositeData repData) {
		boolean result = false;
		try {
			String retStatus = repData.getStruct("SYS_HEAD")
					.getField("RET_STATUS").strValue();
			String retCode = repData.getStruct("SYS_HEAD").getArray("RET")
					.getStruct(0).getField("RET_CODE").strValue();
			String retMsg = repData.getStruct("SYS_HEAD").getArray("RET")
					.getStruct(0).getField("RET_MSG").strValue();
			if ("S".equals(retStatus) && "000000".equals(retCode)) {
				result = true;
				log.info("ESB服务处理正常.");
			} else {
				log.error("ESB服务处理错误,错误码：[" + retCode + "],错误信息:[" + retMsg + "]");
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	/**
	 * ESB返回状态
	 * 
	 * @param repData
	 * @return
	 */
	public static Map<String, String> esbResultMsg(CompositeData repData) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			String retStatus = repData.getStruct("SYS_HEAD")
					.getField("RET_STATUS").strValue();
			String retCode = repData.getStruct("SYS_HEAD").getArray("RET")
					.getStruct(0).getField("RET_CODE").strValue();
			String retMsg = repData.getStruct("SYS_HEAD").getArray("RET")
					.getStruct(0).getField("RET_MSG").strValue();
			log.error("ESB服务处理 状态：["
					+ retStatus + "],错误码：[" + retCode + "],错误信息:[" + retMsg
					+ "]");
			
			result.put("RET_STATUS", retStatus);
			result.put("RET_CODE", retCode);
			result.put("RET_MSG", retMsg);
		} catch (Exception e) {
			log.error("返回结果解释异常e=" + e.toString());
		}
		return result;
	}

	/**
	 * 以yyyyMMdd格式返回当前日期
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT);
	}

	/**
	 * 以HHmmssSSS格式返回当前时间
	 * 
	 * @return
	 */
	public static String getCurTime() {
		return DateUtil.format(new Date(),sTimeFormat);
	}

	/**
	 * 以HHmmssSSS格式返回当前时间
	 * 
	 * @return
	 */
	public static String getCurTime6() {
		return DateUtil.format(new Date(),sTimeFormat6);
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		CompositeData bodyData = new CompositeData();
		CompositeData reqData = ESBInterfaceUtil
				.createEsbReqData(map, bodyData);
		System.out.println("aaa=" + PackUtil.packXmlStr(reqData));
	}
}
