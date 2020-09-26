package com.joyintech.tams.valuation.interf.valuation.sztinterf.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.sztinterf.constant.DictCodeSztList;
import com.joyintech.tams.valuation.common.sztinterf.constant.SztValConstant;
import com.joyintech.tams.valuation.common.sztinterf.utils.SztDecodeXml;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.dao.SztInterfDealMapper;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.dto.SztIntfLogModel;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.dto.SztResultDetailModel;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.dto.SztResultDetailXmlModel;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.utils.ApplicationConfig;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.utils.SztConnector;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.utils.SztMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SztInterfRecService {
	
    private static Logger logger = LoggerFactory.getLogger(SztInterfRecService.class.getName());
	
	@Autowired
	private SztInterfDealMapper sztInterfDealMapper;

	/**
	 * 深圳通接收处理
	 */
	public void sztRecDeal(){
		try {
			//初始化深圳通接口
			SztConnector connector = ApplicationConfig.connectorInit();
			//接受消息
			SztMessage respMsg = connector.recvMessage();
			String result = "";
			if (null != respMsg) {
				result = SztDecodeXml.decodeOutZip(respMsg.getBody());
				System.out.println("result:" + result);
				System.out.println("Response:orgPkgId:" + respMsg.getCorrPkgId() + ",body:" + respMsg.getBody());
			}
			else{
				logger.info("深圳通接口轮询查询到的返回结果为空");
			}
            //String result = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><OUT><FILE_TYPE>1001</FILE_TYPE><FUND_ID>1</FUND_ID><REPORT_TYPE>01</REPORT_TYPE><BEGIN_DATE>20191010</BEGIN_DATE><END_DATE/><DEPT_CODE>0133</DEPT_CODE><CERT_ID/><SERIAL_NO>20191214000000000027</SERIAL_NO><Record><RESULT>0</RESULT><REF_NO>001</REF_NO><FXMDMB>1</FXMDMB><FXMDMD>1</FXMDMD><BJE>1</BJE><DJE>1</DJE><BSL>1</BSL><DSL>1</DSL><NOTE>1</NOTE><CHECK_FLAG>1</CHECK_FLAG><DEALER>1</DEALER><TIME>1</TIME><BJEO>1</BJEO><DJEO>1</DJEO><BJET>1</BJET><DJET>1</DJET><BJETH>1</BJETH><DJETH>1</DJETH><BJEF>1</BJEF><DJEF>1</DJEF><BJEFI>1</BJEFI><DJEFI>1</DJEFI><BBY1>1</BBY1><DBY1>1</DBY1></Record></OUT></root>";
			//保存非及时返回的数据
			try {
				Thread.sleep(SztValConstant.深圳通_接口处理休眠时间);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(StringUtil.isNotEmpty(result)) {
				//定义 变量.对账结果 = 解析返回报文(result);
				SztResultDetailModel sztResultDetailModel = analysisRecModel(result);
				if(sztResultDetailModel != null) {
					//业务流水号
					String serialNo = sztResultDetailModel.getSerialNo();
					//产品代码
					String prodId = sztResultDetailModel.getFundId();
					//对账类型
					String interType = null;
					int successNum = 0;
					if(!StringUtil.isEmptyList(sztResultDetailModel.getReconciliaResults())) {
						for(SztResultDetailXmlModel item : sztResultDetailModel.getReconciliaResults()) {
							//成功标识的数量
							if (SztValConstant.深圳通_接口返回成功标识.equals(item.getResult())) {
								successNum = successNum + 1;
							}
							interType = item.getCheckFlag();
							item.setCreateUser(SztValConstant.深圳通_接口创建人);//创建人
							item.setCreateTime(new Date());//创建日期
							item.setUpdateUser(SztValConstant.深圳通_接口更新人);//更新人
							item.setUpdateTime(new Date());//更新时间
							sztInterfDealMapper.insertResultDetail(item);
						}
						//查询员发送的条数
						int sendNum = sztInterfDealMapper.getDetailCount(prodId, serialNo);
						SztIntfLogModel model = new SztIntfLogModel();
						model.setFinprodId(prodId);
						model.setRefNo(serialNo);
						model.setInterType(interType);
						model.setCdate(sztResultDetailModel.getBeginDate());
						model.setCreateUser(SztValConstant.深圳通_接口创建人);//创建人
						model.setCreateTime(new Date());//创建日期
						model.setUpdateUser(SztValConstant.深圳通_接口更新人);//更新人
						model.setUpdateTime(new Date());//更新时间
						if(sendNum == successNum){
							model.setCheckStatus(DictCodeListExt1.深圳通对账状态_对账一致.getValue());
						}
						else {
							model.setCheckStatus(DictCodeListExt1.深圳通对账状态_对账不一致.getValue());
						}
						sztInterfDealMapper.updateInterfLog(model);
					}
					else {
						logger.info("深圳通对账结果返回ReconciliaResults为空");
					}

				}
				else {
					logger.info("深圳通对账结果返回sztResultDetailModel为空");
				}
			}
			logger.info("the sztConnector listen thread is exit!" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("the sztConnector listen throws Exception!");
		}
	}

	/**
	 * 解析深圳通报文映射成实体类
	 * @param result
	 * @return
	 */
	public SztResultDetailModel analysisRecModel(String responseMessage) {
		SztResultDetailModel result = new SztResultDetailModel();
		List<SztResultDetailXmlModel> results = new ArrayList<SztResultDetailXmlModel>();
		try {
			Document responseDocument = DocumentHelper.parseText(responseMessage);
			Element service = responseDocument.getRootElement();
			Element out = getChildNode(service, DictCodeSztList.深圳通接口报文返回字段_解析报文头.getValue());
			/**
			 * 文件类型
			 */
			Element fileType = getChildNode(out, DictCodeSztList.深圳通接口报文公共接口字段_文件类型.getValue());
			if (fileType != null) {
				result.setFileType(fileType.getTextTrim());
			}
			//组合代码
			Element fundId = getChildNode(out, DictCodeSztList.深圳通接口报文公共接口字段_组合代码.getValue());
			if (fundId != null) {
				result.setFundId(fundId.getTextTrim());
			}
			//报表类型
			Element reportType = getChildNode(out, DictCodeSztList.深圳通接口报文公共接口字段_报表类型.getValue());
			if (reportType != null) {
				result.setReportType(reportType.getTextTrim());
			}
			//开始日期
			Element beginDate = getChildNode(out, DictCodeSztList.深圳通接口报文公共接口字段_开始日期.getValue());
			if (beginDate != null) {
				result.setBeginDate(beginDate.getTextTrim());
			}
			//结束日期
			Element endDate = getChildNode(out, DictCodeSztList.深圳通接口报文公共接口字段_结束日期.getValue());
			if (endDate != null) {
				result.setEndDate(endDate.getTextTrim());
			}
			//公司代码
			Element deptCode = getChildNode(out, DictCodeSztList.深圳通接口报文公共接口字段_公司代码.getValue());
			if (deptCode != null) {
				result.setDeptCode(deptCode.getTextTrim());
			}
			//证书代码
			Element certId = getChildNode(out, DictCodeSztList.深圳通接口报文公共接口字段_证书代码.getValue());
			if (certId != null) {
				result.setCertId(certId.getTextTrim());
			}
			//业务流水号
			Element serialNo = getChildNode(out, DictCodeSztList.深圳通接口报文公共接口字段_业务流水号.getValue());
			if (serialNo != null) {
				result.setSerialNo(serialNo.getTextTrim());
			}
			// 结果集合
			List<Element> rets = getChildNodes(out, DictCodeSztList.深圳通接口报文公共接口字段_数据行.getValue());//TODO 确定是RECORD,还是Record
			if (!StringUtil.isEmptyList(rets)) {
				rets.forEach(ele -> {
					SztResultDetailXmlModel xml = new SztResultDetailXmlModel();
					// 对帐结果
					Element resultEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对帐结果.getValue());
					if(null != resultEle) {
						xml.setResult(resultEle.getTextTrim());
					}
					Element refNoEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_关联流水号.getValue());
					if(null != refNoEle) {
						xml.setRefNo(refNoEle.getTextTrim());
					}
					// 本方项目代码
					Element fxmdmbEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方项目代码.getValue());
					if(null != fxmdmbEle) {
						xml.setFxmdmB(fxmdmbEle.getTextTrim());
					}
					// 对方项目代码
					Element fxmdmdEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方项目代码.getValue());
					if(null != fxmdmdEle) {
						xml.setFxmdmD(fxmdmdEle.getTextTrim());
					}
					// 本方金额
					Element bjeEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方金额.getValue());
					if(null != bjeEle) {
						xml.setBje(new BigDecimal(bjeEle.getTextTrim()));
					}
					// 对方金额
					Element djeEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方金额.getValue());
					if(null != djeEle) {
						xml.setDje(new BigDecimal(djeEle.getTextTrim()));
					}

					// 本方数量
					Element bslEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方数量.getValue());
					if(null != bslEle) {
						xml.setBsl(new BigDecimal(bslEle.getTextTrim()));
					}

					// 对方数量
					Element dslEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方数量.getValue());
					if(null != dslEle) {
						xml.setDsl(new BigDecimal(dslEle.getTextTrim()));
					}
					// 附言
					Element noteEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_附言.getValue());
					if(null != noteEle) {
						xml.setNote(noteEle.getTextTrim());
					}
					// 对账类型
					Element checkFlagEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对账类型.getValue());
					if(null != checkFlagEle) {
						xml.setCheckFlag(checkFlagEle.getTextTrim());
					}
					// 处理人
					Element dealerEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_处理人.getValue());
					if(null != dealerEle) {
						xml.setDealer(dealerEle.getTextTrim());
					}
					// 时间
					Element timeEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_时间.getValue());
					if(null != timeEle) {
						xml.setTime(timeEle.getTextTrim());
					}

					// 本方金额一
					Element bjeoEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方金额一.getValue());
					if(null != bjeoEle) {
						xml.setBjeO(new BigDecimal(bjeoEle.getTextTrim()));
					}

					// 对方金额一
					Element djeoEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方金额一.getValue());
					if(null != djeoEle) {
						xml.setDjeO(new BigDecimal(djeoEle.getTextTrim()));
					}

					// 本方金额二
					Element  bjetEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方金额二.getValue());
					if(null != bjetEle) {
						xml.setBjeT(new BigDecimal(bjetEle.getTextTrim()));
					}

					// 对方金额二
					Element  djetEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方金额二.getValue());
					if(null != djetEle) {
						xml.setDjeT(new BigDecimal(djetEle.getTextTrim()));
					}

					// 本方金额三
					Element  bjethEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方金额三.getValue());
					if(null != bjethEle) {
						xml.setBjeTh(new BigDecimal(bjethEle.getTextTrim()));
					}

					// 对方金额三
					Element  djethEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方金额三.getValue());
					if(null != djethEle) {
						xml.setDjeTh(new BigDecimal(djethEle.getTextTrim()));
					}

					// 本方金额四
					Element  bjefEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方金额四.getValue());
					if(null != bjefEle) {
						xml.setBjeF(new BigDecimal(bjefEle.getTextTrim()));
					}

					// 对方金额四
					Element  djefEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方金额四.getValue());
					if(null != djefEle) {
						xml.setDjeF(new BigDecimal(djefEle.getTextTrim()));
					}

					// 本方金额五
					Element  bjefiEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方金额五.getValue());
					if(null != bjefiEle) {
						xml.setBjeFi(new BigDecimal(bjefiEle.getTextTrim()));
					}

					// 对方金额五
					Element  djefiEle = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方金额五.getValue());
					if(null != djefiEle) {
						xml.setDjeFi(new BigDecimal(djefiEle.getTextTrim()));
					}

					// 本方备用1
					Element  bby1Ele = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_本方备用1.getValue());
					if(null != bby1Ele) {
						xml.setBby1(bby1Ele.getTextTrim());
					}

					// 对方备用1
					Element  dby1Ele = getChildNode(ele, DictCodeSztList.深圳通接口报文返回字段_对方备用1.getValue());
					if(null != dby1Ele) {
						xml.setDby1(dby1Ele.getTextTrim());
					}
					results.add(xml);
				});
			}
		}
		catch (DocumentException e) {
			e.printStackTrace();
		}
		//对账集合
		result.setReconciliaResults(results);
		return result;
	}


	/**
	 * 获取子节点
	 * @param parent
	 * @param child
	 * @return
	 */
	private Element getChildNode(Element parent, String child) {
		if(parent != null){
			return parent.element(child);
		}
		return null;
	}

	/**
	 * 获取子节点
	 * @param parent
	 * @param child
	 * @return
	 */
	private List<Element> getChildNodes(Element parent, String child) {
		if(parent != null){
			return parent.elements(child);
		}
		return null;
	}
}
