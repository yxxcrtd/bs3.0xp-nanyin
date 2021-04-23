/*
 * 文件名：RouteIntfSubServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.business.routeintfservice.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joyintech.background.component.common.pack.common.base.BaseService;
import com.joyintech.background.component.uplink.interbank.business.dto.BondDialogueQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.BondRequestQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.RepoDialogueQuoteDto;
import com.joyintech.background.component.uplink.interbank.business.dto.ResultBaseDto;
import com.joyintech.background.component.uplink.interbank.business.routeintfservice.RouteIntfSubService;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBond;

/**
 * 路由接口子组件服务实现类
 * @author Jiaoxujin
 * @version 1.0 2020年5月12日
 * @see RouteIntfSubServiceImpl
 * @since 1.0
 */
@Service
public class RouteIntfSubServiceImpl extends BaseService<IbuQuoteBond> implements RouteIntfSubService {

    /**
     * {@inheritDoc}
     * @return 
     */
    @Override
    public ResultBaseDto sendBondDialogue(BondDialogueQuoteDto quote, String sendTypeIBU) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultBaseDto sendBondRequest(BondRequestQuoteDto quote, String sendTypeIBU) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultBaseDto sendRepoDialogue(RepoDialogueQuoteDto quote, String sendTypeIBU) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultBaseDto inputBondDlgCheck(BondDialogueQuoteDto quote) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultBaseDto inputBondReqCheck(BondRequestQuoteDto quote) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultBaseDto inputRepoDlgCheck(RepoDialogueQuoteDto quote) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertMessgeIbu(String msg) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> queryMessgeIbu(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepoDialogueQuoteDto receiveRepoDialogue(String msg) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BondRequestQuoteDto receiveBondRequest(String msg) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BondDialogueQuoteDto receiveBondDialogue(String msg) {
        // TODO Auto-generated method stub
        return null;
    }

}
