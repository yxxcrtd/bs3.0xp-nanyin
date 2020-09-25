/*
 * 文件名：InterfCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年1月17日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.invest.interf.common.core.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.utility.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InfDictCode;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import com.joyintech.invest.interf.common.core.contants.InterfColumns;
import com.joyintech.invest.interf.common.core.contants.InterfList;
import com.joyintech.invest.interf.common.core.contants.ServiceApplicationInvestInterf;
import com.joyintech.invest.interf.common.core.dao.InvestInterfStsMapper;
import com.joyintech.invest.interf.common.core.dao.SysInvestInfParaMapper;
import com.joyintech.invest.interf.common.core.entity.InterfStatus;
import com.joyintech.invest.interf.common.core.utility.InvestCoreUtility;
import com.joyintech.invest.interf.common.exception.InterfException;

/**
 * 
 * 接口共通
 * 
 * @author 王波
 * @version 1.0 2020年1月17日
 * @see InvestInterfCommonService
 * @since 1.0
 */
@Service
public class InvestInterfCommonService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private InvestInterfStsMapper investInterfStsMapper;

	/**
	 * restTemplate
	 */
	@Autowired
	private JoyinRestTemplate restTemplate;

	@Autowired
	private SysInvestInfParaMapper sysParaMapper;

	/**
	 * 
	 * < 发送数据到Utility>
	 * 
	 * @param data
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Map<String, Object> sendToUitility(Map<String, Object> data) {
		String interfTermtrc = (String) data.get(InfDictCode.接口流水号.getValue());
		Map<String, Object> res = new HashMap<>();
		//****** 新增防重复发送机制 20200306 【根据当前流水获取对应的状态，如果在发送中直接返回，否则更新对应数据状态】*****

		//1.获取当前流水号的发送状态
		String sendStatus = getSendStatus(interfTermtrc);
		if(StringUtil.isNotEmpty(sendStatus) &&sendStatus.equals(InfDictCodeList.发送状态_发送中.getValue()))
		{
			res.put("message",InfDictCodeList.发送状态_发送中.getValue());
			return res;
		}
		//2.根据当前流水号更新当前状态为发送中【先判断当前流水是不是在发送中，如果在发送中则直接返回错误码】
		updateInterfStatus(interfTermtrc,InfDictCodeList.发送状态_发送中.getValue());

		try {
			res = InvestCoreUtility.send(data);

			if(StringUtil.isNotEmpty(res)&&res.size()>0) {
				// 增加发送次数
				incrSendCount(interfTermtrc);
				//3.根据当前流水号更新当前状态为已发送
				updateInterfStatus(interfTermtrc, InfDictCodeList.发送状态_已发送.getValue());
			}
			else
			{
				updateInterfStatus(interfTermtrc,InfDictCodeList.发送状态_发送失败.getValue());
			}
		} catch (IOException e) {
			updateInterfStatus(interfTermtrc,InfDictCodeList.发送状态_发送失败.getValue());
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * 
	 * <登入记录表>
	 * 
	 * @param data    数据体
	 * @param sendFlg 发送标识
	 * @throws InterfException
	 * @see
	 * @since 1.0
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void writeLog(Map<String, Object> data, String sendFlg) throws InterfException {
		// TODO 写日志

		@SuppressWarnings("unchecked")
		Map<String, Object> head = (HashMap<String, Object>) data.get("head");
		if (head == null || head.isEmpty()) {
			logger.error("无效的head信息");
			throw new InterfException("无效的head信息");
		}

		InterfStatus interfStatus = new InterfStatus();
		// 设置接口ID
		String interfId = (String) head.get(InfDictCode.接口ID.getValue());
		if (StringUtils.isBlank(interfId)) {
			throw new InterfException("无效的接口ID");
		}
		interfStatus.setInterfId(interfId);

		// 第一次发送
		interfStatus.setSendCount(0);

		// 发送时设置发送标识
		if (sendFlg.equals(InfDictCodeList.发送标识_发送.getValue())) {
			interfStatus.setInterfSts(InfDictCodeList.发送状态_已发送.getValue());
		}

		// 报文体
		String jsonBody = JsonUtils.object2Json(data);
		interfStatus.setMsgBody(jsonBody);

		// 发送标识
		interfStatus.setSendFlg(sendFlg);
		
		// 设置平台日
		interfStatus.setBusiDate(sysParaMapper.getSysDate());

		//接口名称
		interfStatus.setInterfName(EnumUtils.getEnumName(InfDictCodeList.class, InfDictCode.接口名称,interfId));
		interfStatus.setCreateUser("INTERF");
		interfStatus.setCreateDept("INTERF");
		interfStatus.setCreateTime(new Date());
		interfStatus.setUpdateTime(new Date());
		interfStatus.setUpdateUser("INTERF");
		// 设置接口流水号
		String interfTermtrc = (String) head.get("REQUEST_SEQNO");
		if (StringUtils.isBlank(interfTermtrc)) {
			throw new InterfException("无效的接口流水号");
		}
		interfStatus.setInterfTermtrc(interfTermtrc);

		investInterfStsMapper.insertSendMsg(interfStatus);
	}

	/**
	 * <根据接口流水号增加发送次数>
	 * 
	 * @see
	 * @since 1.0
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void incrSendCount(String interfTermtrc) {
		investInterfStsMapper.incrSendCount(interfTermtrc);
	}

	/**
	 * 
	 * <更新接口状态>
	 * 
	 * @see
	 * @since 1.0
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateInterfStatus(String interfTermtrc, String status) {
		investInterfStsMapper.updateInterfStatus(interfTermtrc, status);
	}

	/**
	 *
	 * <根据接口流水号更新发送状态>
	 *
	 * @see
	 * @since 1.0
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getSendStatus(String interfTermtrc) {
	 return investInterfStsMapper.getSendStatus(interfTermtrc);
	}

	/**
	 * 根据接口ID调用对应的业务微服务写数据
	 *
	 * @param paramMap 微服务参数
	 * @param infID    接口ID
	 * @see
	 * @since 1.0
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object writeToMicroService(Map<String, Object> paramMap, String infID) {
	    // 其他现金流交易信息
        if (StringUtil.equals(infID, "R001")) {
            return restTemplate.postForObject(ServiceApplicationInvestInterf.PORTFOLIO_SERVICE_NAME
                                              + "/PtlCashFlow/insertCashFlowInfo",
                paramMap, String.class);
        }
        // 到账确认信息
        else if (StringUtil.equals(infID, "R004")) {
            return restTemplate.postForObject(
                ServiceApplicationInvestInterf.TRADE_SERVICE_NAME + "/", paramMap, String.class);
        }
        // 组合信息
        else if (StringUtil.equals(infID, "R005")) {
            return restTemplate.postForObject(ServiceApplicationInvestInterf.PORTFOLIO_SERVICE_NAME
                                              + "/ptlmanagementinfo/insertPortfolioInfo",
                paramMap, String.class);
        }
        //交易清算状态
        else if (StringUtil.equals(infID, "R002")) {
            return restTemplate.postForObject(ServiceApplicationInvestInterf.SETTLE_SERVICE_NAME
                                              + "/StlOrderManagement/insertInfPayOrdersStatus",
                paramMap, String.class);
        }
        //sppi 测试结果
        else if (StringUtil.equals(infID, "R003")) {
            return restTemplate.postForObject(
                ServiceApplicationInvestInterf.ASSET_SERVICE_NAME + "/AssetCommon/insertSppiInfo", paramMap,
                String.class);
        }
		//组合资产估值信息
	    else if (StringUtil.equals(infID, "R008")) {
			return restTemplate.postForObject(ServiceApplicationInvestInterf.PORTFOLIO_SERVICE_NAME
							+ "/PtlValution/insertPtlValution",
					paramMap, String.class);
		}
        //组合估值信息
        else if (StringUtil.equals(infID, "R009")) {
            return restTemplate.postForObject(ServiceApplicationInvestInterf.PORTFOLIO_SERVICE_NAME
                                              + "/PtlPortfolioValution/insertPortfolioValution",
                paramMap, String.class);
        }
		//组合费用估值信息
		else if (StringUtil.equals(infID, "R010")) {
			return restTemplate.postForObject(ServiceApplicationInvestInterf.PORTFOLIO_SERVICE_NAME
							+ "/PtlCostValution/insertCostValution",
					paramMap, String.class);
		}
        //组合证券账户信息
        else if (StringUtil.equals(infID, "R012")) {
            return restTemplate.postForObject(ServiceApplicationInvestInterf.PORTFOLIO_SERVICE_NAME
                                              + "/ptlmanagementinfo/insertPtlSecAcct",
                paramMap, String.class);
        }
        //组合资金账户信息
        else if (StringUtil.equals(infID, "R013")) {
            return restTemplate.postForObject(ServiceApplicationInvestInterf.PORTFOLIO_SERVICE_NAME
                                              + "/ptlmanagementinfo/insertPtlCapacctInfo",
                paramMap, String.class);
        }
		//组合业绩基准信息
		else if (StringUtil.equals(infID, "R014")) {
			return restTemplate.postForObject(ServiceApplicationInvestInterf.PORTFOLIO_SERVICE_NAME
							+ "/ptlmanagementinfo/insertPtlBenchmarkInfo",
					paramMap, String.class);
		}
        return null;
    }

	/**
	 *
	 * 获取日志列表
	 *
	 * @see
	 * @since 1.0
	 */
	public List<InterfStatus> getLogListByDate(Map<String, Object> paraMap) {
	 return 	investInterfStsMapper.getLogListByDate(paraMap);
	}

	/**
	 *
	 * 生成序列号sequence
	 *
	 * @see
	 * @since 1.0
	 */
	public  String getNextSequence()
	{
		String nextValBySequenceName = sysParaMapper.getNextValBySequenceName(BusinessUtil.INVINTERF_SEQUENCE);
		// 组装9位序列号 不足补0
		String sequence = StringUtils.leftPad(nextValBySequenceName, 9, '0');
		return sequence;
	}

    /**
     * @description 接口执行方法(文件传输)
     * 
     * @param date 数据日期
     * @param file 数据文件绝对路径
     * @return 处理结果（应答数据）
     * @see
     * @since 1.0
     */
    public Map<String, Object> executeByRecvFile(String interfId, String date, String file) {

        logger.info("请求参数:{}", "interfId:" + interfId + ",date" + date + ",file" + file);

        // 2.根据接口ID取得接口模块实例进行事件分发
        InterfRecvBaseService<?> service = InterfList.getRecvBean(interfId);
        // 2.1 若接口模块不存在，则处理结束返回结果
        if (service == null) {
            return this.errMsg("接口模块异常【" + interfId + "对应的接口不存在】");
        }

        // 接口模块处理
        return service.executeByRecvFile(date, file);
    }


    /**
     * @Description errMsg
     * @param errMsg
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> errMsg(String errMsg) {
        ClientData data = new ClientData();
        data.setHeadField(InterfColumns.COM_RTNCODE.fieldName(), 1);
        data.setHeadField(InterfColumns.COM_RTNMSG.fieldName(), errMsg);
        return data.convertToMap();
    }

}
