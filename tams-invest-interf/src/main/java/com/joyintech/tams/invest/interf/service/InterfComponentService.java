/*
 * 文件名：InterfComponentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.StlDvpOrders;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.interf.dto.*;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.InterfSendMurex;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.interf.constant.InterfServiceCaller;
import com.joyintech.tams.invest.interf.constant.InterfUtil;

/**
 * 接口组件Service
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月24日
 * @see InterfComponentService
 * @since 1.0
 */
@Service
public class InterfComponentService {

    /**
     * restTemplate
     */
    @Autowired
    private JoyinRestTemplate restTemplate;

    /**
     * 调用接口微服务（产品端画面用）对接零售
     * 
     * @param pageId 画面ID
     * @param elementId 事件源（按钮ID等）
     * @param prodList 产品列表</br>
     *            <p>
     *            {@link FinProdDto}
     *            </p>
     *            <p>
     *            产品新建审批后调用时
     *            </p>
     * 
     *            <pre>
     * 必须的参数有：finProdId（金融产品代码）、saleDept（销售部门:01发零售）、type（处理模式）
     *            </pre>
     *            <p>
     *            产品到期日调整审批后调用时
     *            </p>
     * 
     *            <pre>
     * 必须的参数有：finProdId（金融产品代码）、saleDept（销售部门:01发零售）、type（处理模式）
     *            </pre>
     *            <p>
     *            产品报价审批后调用时
     *            </p>
     * 
     *            <pre>
     * 必须的参数有：finProdId（金融产品代码）、saleDept（销售部门:01发零售）、type（处理模式）、cdate（生效日）
     *            </pre>
     *            <p>
     *            产品成立同步时
     *            </p>
     * 
     *            <pre>
     * 必须的参数有：finProdId（金融产品代码）、saleDept（销售部门:01发零售）、type（处理模式）、
     * </br> startdate（销售开始日-开放阶梯、滚动N天、普通N天 之外的产品必须）、
     * </br> enddate（销售截止日-开放阶梯、滚动N天、普通N天 之外的产品必须）、
     * </br> cdate（成立日-开放阶梯、滚动N天、普通N天 产品必须）
     *            </pre>
     *            <p>
     *            产品存续期交易同步时
     *            </p>
     * 
     *            <pre>
     * 必须的参数有：finProdId（金融产品代码）、saleDept（销售部门:01发零售）、type（处理模式）、
     * </br> cdate（交易日）
     *            </pre>
     *            <p>
     *            产品到期同步时
     *            </p>
     * 
     *            <pre>
     * 必须的参数有：finProdId（金融产品代码）、saleDept（销售部门:01发零售）、type（处理模式）、
     * </br> startdate（起息日-滚动N天 产品必须）、
     * </br> cdate（实际到期日）
     *            </pre>
     * 
     * @return 处理结果
     * @see
     * @since 1.0
     */
    public List<FinProdDto> sendRequest(@NotNull String pageId, String elementId,
                                        List<FinProdDto> prodList) {
        String caller = StringUtil.isEmpty(elementId) ? pageId : elementId;
        // 设置请求参数列表
        Map<String, Object> param = new HashMap<>();
        param.put(InterfUtil.CALLER, caller);
        param.put(InterfUtil.PAGE_ID, pageId);
        param.put(InterfUtil.DATA_LIST, prodList);
        // 根据事件源或者画面取得请求URL
        String url = InterfUtil.INTERF_SERVICE_NAME + InterfUtil.getUrl(caller);
        // 向接口发送请求
        return restTemplate.postForList(url, param, FinProdDto.class);
    }

    /**
     * 调用接口微服务（交易端.清算端画面用）
     * 
     * @param odrCaller 调用模块
     * @param tradeList 交易编号/清算指令列表
     *            <p>
     *            {@link TradeClearDto}
     *            </p>
     * 
     *            <pre>
     * 参数：orderOrTradeNo（指令/交易编号）、
     * <br>remark（备注，CMS信贷项目信息撤销时该项作为撤销原因必须）
     *            </pre>
     * 
     * @return 处理结果
     * @see
     * @since 1.0
     */
    public List<TradeClearDto> sendTradeClearRequest(@NotNull OrderCaller odrCaller,
                                                     List<TradeClearDto> tradeList) {
        String caller = odrCaller.name();
        // 设置请求参数列表
        Map<String, Object> param = new HashMap<>();
        param.put(InterfUtil.CALLER, caller);
        param.put(InterfUtil.DATA_LIST, tradeList);
        // 根据事件源或者画面取得请求URL
        String url = InterfUtil.INTERF_SERVICE_NAME
                     + InterfUtil.getUrl(InterfServiceCaller.SEND_ORDER.code());
        // 向接口发送请求
        return restTemplate.postForList(url, param, TradeClearDto.class);
    }

    /**
     * 调用接口微服务（处理ESB请求用）
     * 
     * @param reqStr ESB请求字符串
     * @return ESB应答字符串
     * @see
     * @since 1.0
     */
    public EsbReqRspDto sendEsbRequest(String reqStr) {
        EsbReqRspDto param = new EsbReqRspDto();
        param.setReqStr(reqStr);
        String url = InterfUtil.INTERF_SERVICE_NAME
                     + InterfUtil.getUrl(InterfServiceCaller.ESB.code());
        return restTemplate.postForObject(url, param, EsbReqRspDto.class);
    }

    /**
     * 调用接口微服务（Batch用）
     * 
     * @param caller 调用模块
     * @param dto 任务信息
     * @return 执行结果
     * @see
     * @since 1.0
     */
    public BatchResultDto sendBatchRequest(@NotNull String caller, @NotNull BatchResultDto dto) {
        String url = InterfUtil.INTERF_SERVICE_NAME + InterfUtil.getUrl(caller);
        return restTemplate.postForObject(url, dto, BatchResultDto.class);
    }

    /**
     * 发送已保存的邮件，如上次发送失败的邮件或需要再次发送的邮件（支持发送多封）
     * 
     * @param mailList 邮件序号列表
     * @return 返回发送结果
     * @see
     * @since 1.0
     */
    public List<SendEmailDto> sendEmailList(List<String> mailList) {
        // 若参数为空，则不作任何操作返回空列表
        if (StringUtil.isEmptyList(mailList)) {
            return new ArrayList<>();
        }
        // 设置请求参数列表
        Map<String, Object> param = new HashMap<>();
        param.put(InterfUtil.CURRENT_USER, SecurityUtil.getLoginUser().getUserName());
        param.put(InterfUtil.DATA_LIST, mailList);
        String url = InterfUtil.INTERF_SERVICE_NAME
                     + InterfUtil.getUrl(InterfServiceCaller.SEND_SAVED_EMAIL.code());
        return restTemplate.postForList(url, param, SendEmailDto.class);
    }

    /**
     * 发送新建邮件
     * 
     * @param msg 新建的邮件内容
     *            <p>
     *            {@link SendEmailDto}
     *            </p>
     *            <p>
     *            必须参数：
     *            </p>
     * 
     *            <pre>
     *            busitype（业务类型）、busicode（模板号）
     *            </pre>
     * 
     *            <pre>
     *            title（标题）、content（消息内容）、subcontent（特殊目的载体业务类型）
     *            </pre>
     * 
     * @return 返回发送结果
     * @see
     * @since 1.0
     */
    public SendEmailDto sendNewEmail(InterfEmailmessage msg) {
        // 若参数为空，则不作任何操作返回空
        if (msg == null) {
            return null;
        }
        String url = InterfUtil.INTERF_SERVICE_NAME
                     + InterfUtil.getUrl(InterfServiceCaller.SEND_NEW_EMAIL.code());
        return restTemplate.postForObject(url, msg, SendEmailDto.class);
    }

    /**
     * 指令调用模块
     * 
     * @author zhangrui1
     * @version 1.0 2019年1月10日
     * @see OrderCaller
     * @since 1.0
     */
    public enum OrderCaller {
        /**
         * 债券交易
         */
        BOND001,
        /**
         * 交易结果导入
         */
        MOPT1001,
        /**
         * 质押式回购交易
         */
        REPO002,
        /**
         * 拆借交易
         */
        LNBR001,
        /**
         * 发送OPICS
         */
        OPDL03,
        /**
         * CMS信贷项目信息撤销
         */
        CMS003
    }

    /**
     * 调用接口微服务（产品端画面用）对接统一TA</br>
     * 除了净值分红信息查询接口（190011）之外的接口都调用此方法
     * 
     * @param pageId 画面ID（调用模块为150006时必须）
     * @param taCaller {@link TACaller} 调用模块
     * @param prodList 对象产品列表</br>
     *            <p>
     *            {@link FinProdDto}
     *            </p>
     *            <p>
     *            参数有：
     *            </p>
     * 
     *            <pre>
     * finProdId（金融产品代码）、cdate（日期-非必须）
     *            </pre>
     * 
     * @return 发送结果列表
     * @see
     * @since 1.0
     */
    public List<FinProdDto> sendTARequest(String pageId, TACaller taCaller,
                                          List<FinProdDto> prodList) {
        // 设置请求参数列表
        Map<String, Object> param = new HashMap<>();
        String caller = taCaller.name();
        param.put(InterfUtil.CALLER, caller);
        param.put(InterfUtil.PAGE_ID, pageId);
        param.put(InterfUtil.DATA_LIST, prodList);
        // 取得请求URL
        String url = InterfUtil.INTERF_SERVICE_NAME
                     + InterfUtil.getUrl(InterfServiceCaller.SEND_TA.code());
        // 向接口发送请求
        return restTemplate.postForList(url, param, FinProdDto.class);
    }

    /**
     * 净值分红信息查询接口（190011）
     * 
     * @param dto 查询DTO
     *            <p>
     *            {@link FinProdDto}
     *            </p>
     *            <p>
     *            必须的参数有：
     *            </p>
     * 
     *            <pre>
     * prodId（产品代码/市场代码）
     * vendorCode（单个销售渠道-渠道代码）
     * pageNo（页码）
     * pageSize（页大小）
     *            </pre>
     *            <p>
     *            非必须的参数有：
     *            </p>
     * 
     *            <pre>
     * prodBatchNo（期次号）
     * queryStartDate（查询开始日YYYYMMDD）
     * queryEndDate（查询结束日YYYYMMDD）
     *            </pre>
     * 
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<SendBonusDto> sendTABonusRequest(SendBonusDto dto) {
        // 取得请求URL
        String url = InterfUtil.INTERF_SERVICE_NAME + InterfUtil.getUrl(TACaller.TA190011.name());
        return restTemplate.postForList(url, dto, SendBonusDto.class);
    }

    /**
     * 统一TA调用模块
     * 
     * @author zhangrui1
     * @version 1.0 2019年1月17日
     * @see TACaller
     * @since 1.0
     */
    public enum TACaller {
        /**
         * 资管费用信息接口（150010）
         */
        TA150010,
        /**
         * 产品信息预创建接口（150006）
         */
        TA150006,
        /**
         * 报价接口（150005）
         */
        TA150005,
        /**
         * 天天成长当日销售数据统计查询（291001）
         */
        TA291001,
        /**
         * 净值型产品申购赎回确认数据查询（291002）
         */
        TA291002,
        /**
         * 净值分红信息查询接口（190011）
         */
        TA190011;
    }

    /**
     * 发送托管网银划款指令、查询指令处理状态
     * 
     * @param orderNo 指令编号 不能为空
     * @param isDVP 是否DVP划款
     * @param type 处理类型 不能为空
     * 
     *            <pre>
     *            1：发送划款指令</br>
     *            2：查询指令处理状态
     *            </pre>
     * 
     * @return 接口处理结果
     */
    public CusdepOrderDto sendCusdepRequest(String orderNo, boolean isDVP, String type) {
        // 构建请求内容
        CusdepOrderDto dto = new CusdepOrderDto();
        dto.setOrderNo(orderNo);
        dto.setDvp(isDVP);
        dto.setInterfType(type);
        // 取得URL
        String url = InterfUtil.INTERF_SERVICE_NAME
                     + InterfUtil.getUrl(InterfServiceCaller.SEND_CUSDEP.code());
        // 发送请求
        return restTemplate.postForObject(url, dto, CusdepOrderDto.class);
    }

    /**
     * 发送交易单确认
     * 
     * @param dto 交易单确认信息</br>
     *            {@link CusdepOrderDto}
     * 
     *            <pre>
     *            外汇交易中心的成交单编号（orderNo）-必须 </br>
     *            确认标志（confirmFlag）-必须 0-确认通过 1-确认拒绝</br>
     *            确认方向（confirmDirection）-必须 1-买/融入/逆回购方 2-卖/融出/正回购方</br>
     *            指令确认日期（confirmDate）-必须 日期格式：YYYYMMDD</br>
     *            托管账号（acctNo）-必须 </br>
     *            附件名称（affixName）-必须 </br>
     *            确认拒绝原因（reason）-非必须
     *            </pre>
     * 
     * @return 接口处理结果
     */
    public CusdepOrderDto sendCusdepConfirmRequest(CusdepOrderDto dto) {
        // 取得URL
        String url = InterfUtil.INTERF_SERVICE_NAME
                     + InterfUtil.getUrl(InterfServiceCaller.SEND_CUSDEP.code());
        // 发送请求
        return restTemplate.postForObject(url, dto, CusdepOrderDto.class);
    }

    /**
     * 发送MUREX信息
     * 
     * @param busiType 业务类型：拆借、质押式回购、债券
     * @param sendData 需要发送的数据 </br>
     *            {@link Map}
     *            <P>
     *            根据详细设计将报文名称作为key存入相应的字段值
     *            </p>
     * @return 发送MUREX的UUID
     */
    @Async
    public List<InterfSendMurex> sendMurexRequest(OrderCaller busiType,
                                                  List<Map<String, Object>> sendData) {
        // 设置请求参数列表
        Map<String, Object> param = new HashMap<>();
        param.put(InterfUtil.CALLER, busiType.name());
        param.put(InterfUtil.DATA_LIST, sendData);
        // 取得请求URL
        String url = InterfUtil.INTERF_SERVICE_NAME
                     + InterfUtil.getUrl(InterfServiceCaller.SEND_MUREX.code());
        // 向接口发送请求
        return restTemplate.postForList(url, param, InterfSendMurex.class);
    }

    /**
     * 更新托管流水信息
     * @param dto 现金流实体类
     *         <pre>
     *            验证标志(validationFlag) -非必须</br>
     *            账号(acctNo) -必须</br>
     *            机构(分行)号(branchId) -非必须</br>
     *            币种(ccy) -非必须</br>
     *            业务类型(bussType) -非必须</br>
     *            分账户序号(branchAcctNoSeq) -非必须</br>
     *            子账户序号(subAcctSerialNo) -非必须</br>
     *            起始日期(startDate) -非必须</br>
     *            终止日期(endDate) -非必须</br>
     *         </pre>
     *
     */
    public CashDto cashInfSel(CashDto dto){
        String url = InterfUtil.INTERF_SERVICE_NAME
                + InterfUtil.getUrl(InterfServiceCaller.SEND_CASH_SELECT.code());
        return restTemplate.postForObject(url, dto,
                CashDto.class);
    }



    /**
     * 划款信息向其它系统发送报文信息
     *
     * @param
     * @param revokeFlag 撤销标识
     * @param payType 转账类型
     * @see
     * @since 1.0
     */
    public void sendCusdepOrderDvp(List<StlDvpOrders> stlDvpOrdersList, boolean revokeFlag, String payType){
        // 是否需要发送接口
        if (!StringUtil.equals(CacheUtil.getSysParam("interfFlag_send_orders"), "01")){
            return;
        }
        // 划款列表为空不处理
        if (StringUtil.isEmptyList(stlDvpOrdersList)){
            return;
        }

        for (StlDvpOrders stlDvpOrders : stlDvpOrdersList){
            // 是否需要发送指令
            if (!StringUtil.equals(stlDvpOrders.getIsNeedSend(), BussinessDictCodeList.是否_是.getValue())){
                continue;
            }

            // 构建请求内容
            CusdepOrderDto cusdepOrderDto = new CusdepOrderDto();
            cusdepOrderDto.setOrderNo(stlDvpOrders.getOrderId());

            if(!revokeFlag){
                cusdepOrderDto.setDvp(true);
                cusdepOrderDto.setCore(false);
                cusdepOrderDto.setInterfType("1");
                cusdepOrderDto.setTransferType(payType);
                cusdepOrderDto.setStlDvpOrders(stlDvpOrders);
                // 投资交易画面未登陆DVP划款表，此处传true从DVP划款表查数据
                this.sendCusdepRequest(cusdepOrderDto.getOrderNo(), true,cusdepOrderDto.getInterfType());
                /*cusdepOrderDto = this.sendCusdepRequest(cusdepOrderDto);
                if(cusdepOrderDto == null || cusdepOrderDto.isResult() != Boolean.TRUE){
                    throw new RuntimeException("MSG:指令推送失败: " + cusdepOrderDto.getErrMsg());
                }*/
            }
        }
    }

}
