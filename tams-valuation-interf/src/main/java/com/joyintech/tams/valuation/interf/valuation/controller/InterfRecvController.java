/*
 * 文件名：InterfRecvController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.controller;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.interf.valuation.recv.common.runner.InterfRecRunner;
import com.joyintech.tams.valuation.interf.valuation.send.common.runner.InterfSendRunner;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.action.SztInterfAction;
import com.joyintech.tams.valuation.interf.valuation.vas.VASBatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 资产管理系统Controller 
 * 
 * @author 王波
 * @version 1.0 2018年8月7日
 * @see InterfRecvController
 * @since 1.0
 */
@Controller
@RequestMapping("/public")
public class InterfRecvController {

    // 第0步
    private final String ORDER_0 = "0,";

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private static boolean isAlreadyRunning = false;

    @Autowired
    private InterfRecRunner recvRunner;
    
    @Autowired
    private InterfSendRunner sendRunner;
    
    @Autowired
    private VASBatchAction vasBatchAction;
    
    @Autowired
    private SztInterfAction sztInterfAction;
    
    private ExecutorService executor = Executors.newCachedThreadPool() ;

    @ResponseBody
    @RequestMapping(InterfCommonConstant.接口消息入口)
    public void getMessage() {
        if (isAlreadyRunning) {
            return;
        }
        isAlreadyRunning = true;

        Map<String, Object> params = new HashMap<String, Object>();
        String dateTime = RequestUtil.getParam(InterfCommonConstant.接口_接口时间);

        if (StringUtil.isNotEmpty(dateTime)) {
            params.put("interfTime", DateUtil.parse(dateTime));
        }

        //add by liupengfei for 多级法人改造 S
        if(MultilevelDataAuthorityUtil.isEnabled()){
            String institutionCode = RequestUtil.getParam("INSTITUTION_CODE");
            if(StringUtil.isNotEmpty(institutionCode)){
                params.put("INSTITUTION_CODE",institutionCode);
            }
        }
        //add by liupengfei for 多级法人改造 E

        String orderNos = RequestUtil.getParam(InterfCommonConstant.接口_批次号列表);
        List<String> orderNoList = null;
        // 接收文件名
        String tempEnames = RequestUtil.getParam(InterfCommonConstant.接口_运行接口名称);
        
        // 接口执行任务ID
        String execTaskSeqno = RequestUtil.getParam(InterfCommonConstant.接口_任务执行ID);
        
        params.put(InterfCommonConstant.接口_任务执行ID, execTaskSeqno);
        
        String rs = RequestUtil.getParam(InterfCommonConstant.接口_发送接收);
        params.put(InterfCommonConstant.接口_发送接收, rs);
        
        params.put(InterfCommonConstant.接口_运行接口账套ID, RequestUtil.getParam(InterfCommonConstant.接口_运行接口账套ID));
        //接口执行账套
        List<String> booksetIdList = null;
        String booksetIds = RequestUtil.getParam(InterfCommonConstant.接口_运行接口账套ID);
        if (StringUtil.isNotEmpty(booksetIds)&&!"".equals(booksetIds)) {
        	booksetIdList = Arrays.asList(booksetIds.split(","));
        }
//        String booksetId = RequestUtil.getParam(InterfCommonConstant.接口_运行接口账套ID);//modify by dongjun on 20180104
        params.put(InterfCommonConstant.接口_运行接口账套ID, booksetIdList);

        //接口模式
        String valModle = RequestUtil.getParam(InterfCommonConstant.接口模式);//modify by dongjun on 20180104
        params.put(InterfCommonConstant.接口模式, valModle);

        /**ADD BY WUJIE ON 20190521 START**/
        String batchBooksetType = RequestUtil.getParam(InterfCommonConstant.接口_界面账套选择类型);
        params.put(InterfCommonConstant.接口_界面账套选择类型, batchBooksetType);

        String booksetId_bef = RequestUtil.getParam(InterfCommonConstant.接口_界面选择账套或账套组);
        params.put(InterfCommonConstant.接口_界面选择账套或账套组, booksetId_bef);

        /**ADD BY WUJIE ON 20190521 END**/

        if (execTaskSeqno == null) {
            logger.error("无任务ID, 接口将不进行工作！");
            isAlreadyRunning = false;
            return;
        }
        List<String> tempENameList = null;
        if (StringUtil.isNotEmpty(tempEnames)) {
            tempENameList = Arrays.asList(tempEnames.split(","));
            if (StringUtil.isEmptyList(tempENameList)) {
                tempENameList = new ArrayList<>();
            }
        }
        params.put(InterfCommonConstant.接口_运行接口列表, tempENameList);
        if (DictCodeInterfList.接口发送接收状态_接收.getValue().equals(rs) && StringUtil.isNotEmpty(orderNos)) {
            // 默认添加第0步去服务器上取消息
            orderNos = ORDER_0 + orderNos;
            orderNoList = Arrays.asList(orderNos.split(","));
            if (StringUtil.isEmptyList(orderNoList)) {
                orderNoList = new ArrayList<>();
            }
            params.put(InterfCommonConstant.INTERF_ORDER_LIST, orderNoList);
        }

        String startDate = RequestUtil.getParam(InterfCommonConstant.接口_开始日);
        String endDate = RequestUtil.getParam(InterfCommonConstant.接口_结束日);
        params.put(InterfCommonConstant.接口_开始日, startDate);
        params.put(InterfCommonConstant.接口_结束日, endDate);
        
        recvRunner.setParams(params);
        sendRunner.setParams(params);
        try {
            if (DictCodeInterfList.接口发送接收状态_接收.getValue().equals(rs)) {
                recvRunner.execute();
            }
            else {
                sendRunner.execute();
            }
        }
        catch (Exception e) {
            isAlreadyRunning = false;
            logger.error("执行接口：" + DateUtil.format(new Date()) + "失败！", e);
            return;
        }
                
        isAlreadyRunning = false;
    }
    
    
    @ResponseBody
    @RequestMapping(InterfCommonConstant.组装XMl文件)
    public void buildXml() throws Exception {
    	
    	String startDa = RequestUtil.getParam(InterfCommonConstant.接口_开始日);

        String endDa = RequestUtil.getParam(InterfCommonConstant.接口_结束日);
        try {
            vasBatchAction.execute( startDa, endDa);
		} catch (Exception e) {
			logger.error("生成xml文件失败",e);
		}
    }

    /**
     * add by hudongyang 深证通接口消息入口 从xybranch20190115版本合并过来
     */
    @ResponseBody
    @RequestMapping(InterfCommonConstant.深证通接口消息入口)
    public void getSztMessage() {
        sztInterfAction.setParams(RequestUtil.getPageParam());
        sztInterfAction.execute();
    }

}
