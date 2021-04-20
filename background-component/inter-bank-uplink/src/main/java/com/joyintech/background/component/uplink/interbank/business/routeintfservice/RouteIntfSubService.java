/*
 * 文件名：RouteSubService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.routeintfservice;

import java.util.List;

import com.joyintech.background.component.uplink.interbank.business.dto.BondDialogueQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.BondRequestQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.RepoDialogueQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.ResultBaseDto;

/**
 * 路由接口子组件服务 〈功能详细描述〉
 * 
 * @author caoguo
 * @version 1.0 2020年5月8日
 * @see RouteIntfSubService
 * @since 1.0
 */
public interface RouteIntfSubService {

    /**
     * 现券对话报价报文组装及发送，并保存接口数据
     * 
     * @param quote 现券对话报价信息
     * @param sendTypeIBU 发送方式
     * @see
     * @since 1.0
     */
    ResultBaseDto sendBondDialogue(BondDialogueQuoteDto quote, String sendTypeIBU);

    /**
     * 现券请求报价报文组装及发送，并保存接口数据
     * 
     * @param quote 现券请求报价信息
     * @param sendTypeIBU 发送方式
     * @see
     * @since 1.0
     */
    ResultBaseDto sendBondRequest(BondRequestQuoteDto quote, String sendTypeIBU);

    /**
     * 质押式回购报价报文拼装及发送，并保存接口数据
     * 
     * @param quote 质押式回购报价信息
     * @param sendTypeIBU 发送方式
     * @see
     * @since 1.0
     */
    ResultBaseDto sendRepoDialogue(RepoDialogueQuoteDto quote, String sendTypeIBU);

    /**
     * 现券对话报价必须校验
     * 
     * @param quote 现券对话报价信息
     * @return 检验结果
     * @see
     * @since 1.0
     */
    ResultBaseDto inputBondDlgCheck(BondDialogueQuoteDto quote);

    /**
     * 现券请求报价必须校验
     * 
     * @param quote 现券请求报价信息
     * @return 检验结果
     * @see
     * @since 1.0
     */
    ResultBaseDto inputBondReqCheck(BondRequestQuoteDto quote);

    /**
     * 质押式回购报价必须校验
     * 
     * @param quote 现券请求报价信息
     * @return 检验结果
     * @see
     * @since 1.0
     */
    ResultBaseDto inputRepoDlgCheck(RepoDialogueQuoteDto quote);
    
    /**
     * 现券对话报价报文组装及发送，并保存接口数据
     * 
     * @param quote 现券对话报价信息
     * @param sendTypeIBU 发送方式
     * @see
     * @since 1.0
     */
    RepoDialogueQuoteDto receiveRepoDialogue(String msg);
    
    /**
     * 现券对话报价报文组装及发送，并保存接口数据
     * 
     * @param quote 现券对话报价信息
     * @param sendTypeIBU 发送方式
     * @see
     * @since 1.0
     */
    BondRequestQuoteDto receiveBondRequest(String msg);

    /**
     * 现券对话报价报文组装及发送，并保存接口数据
     * 
     * @param quote 现券对话报价信息
     * @param sendTypeIBU 发送方式
     * @see
     * @since 1.0
     */
    BondDialogueQuoteDto receiveBondDialogue(String msg);

    /**
     * 报文保存
     * 
     * @param msg
     * @return
     * @see
     * @since 1.0
     */
    void insertMessgeIbu(String msg);

    /**
     * 报文查询
     * 
     * @param condition 查询条件
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<String> queryMessgeIbu(String condition);
}
