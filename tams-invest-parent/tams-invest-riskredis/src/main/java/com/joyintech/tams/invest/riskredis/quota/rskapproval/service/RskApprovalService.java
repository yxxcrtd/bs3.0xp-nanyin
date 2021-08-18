/*
 * 文件名：RskApprovalService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.riskredis.quota.rskapproval.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.invest.common.client.ClientUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskIsupTrialDto;

/**
 * 风险审批共通Service
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月14日
 * @see RskApprovalService
 * @since 1.0
 */
public abstract class RskApprovalService<V extends ITableRow> extends BaseService<V> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 资管系统URL
     */
    public static final String APPROVAL_SYS = "APPROVAL_SYS_";

    /**
     * 取得 试算结论
     * 
     * @param eventId 委托编号
     * @return 试算结论
     * @see
     * @since 1.0
     */
    public List<RskIsupTrial> selectConclusion(String eventId) {
        return restTemplate.getForList(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskApproval/selectConclusion/" + eventId,
            RskIsupTrial.class);
    }

    /**
     * 取得 指标计算结论
     * 
     * @param eventId 委托编号
     * @param assetCode 委托编号
     * @return 指标计算结论
     * @see
     * @since 1.0
     */
    public Page<RskIsupTrialDto> selectForPage(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskApproval/findPage", param,
            RskIsupTrialDto.class);
    }

    /**
     * 取得 指标计算结论
     *
     * @return 指标计算结论
     * @see
     * @since 1.0
     */
    public Page<RskIsupTrialDto> selectForList(Map<String, Object> param) {
        Page<RskIsupTrialDto> dto = new Page<RskIsupTrialDto>();
        List<RskIsupTrialDto> rskIsupTrialDtos = restTemplate.postForList(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskApproval/findRskIsupTrialList", param,
                RskIsupTrialDto.class);
        dto.setRoot(rskIsupTrialDtos);
        return dto;
    }

    protected void send(String code, String rsk, String sysCode) {
        Map<String, Object> data = new HashMap<>();
        data.put("sysCode", StringUtil.contains(sysCode, GlobalConstant.UNDER_LINE) ?
        		StringUtil.substring(sysCode, 0, StringUtil.indexOf(sysCode, GlobalConstant.UNDER_LINE))
        		: sysCode);
        String eventId = StringUtil.contains(code, GlobalConstant.COLON) ? 
        		StringUtil.substring(code, StringUtil.indexOf(code, GlobalConstant.COLON) + 1)
        		: code;
        // 交易系统
        if (StringUtil.equals((String) data.get("sysCode"), CacheUtil.getSysParam("tradeSys"))) {
            data.put(BussinessConstantRds.EVENT_ID, eventId);
            data.put(BussinessConstantRds.STATUS, "1");
        }
        else {
            data.put(BussinessConstantRds.ENTRUSTCODE, eventId);
            data.put(BussinessConstantRds.APPROVEMSG, "通过");
            data.put(BussinessConstantRds.FLOWSTATUS, "02");
            data.put(BussinessConstantRds.INTERFCODE, rsk);
        }
        ClientUtil.sendRequest(CacheUtil.getSysParam(APPROVAL_SYS + sysCode), data,restTemplate);
    }

    /**
     * 将审批结果 通知给交易系统
     *
     * @param eventId 编号
     * @see
     * @since 1.0
     */
    protected void sendApprovalResultToExchange(String eventId) {
        logger.info("通知交易系统审批状态接口【{}】  接口URL："+ServiceApplication.EXCHANGE_SERVICE_NAME +
                "/joyin/instruction/examine");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("eventId", eventId);
        paramMap.put("approveResult", "1");
        logger.info("请求报文【{}】","eventId = "+ eventId+";approveResult=1");

        Map<String, Object> result = restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME +
                        "/joyin/instruction/examine?eventId="+eventId+"&approveResult=1",
                paramMap, Map.class);
        logger.info("应答报文【{}】", "errorCode:"+(Integer)result.get("errorCode")+";errorMsg:"+(String)result.get("errorMsg"));

    }

    protected Logger getLogger(){
        return logger;
    }
}
