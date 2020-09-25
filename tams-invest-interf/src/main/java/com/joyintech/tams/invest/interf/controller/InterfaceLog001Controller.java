/*
 * 文件名：FinBondTargetAsset001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.interf.controller;

import com.jixu.common.util.JsonUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.interf.dto.InterfStatus;
import com.joyintech.tams.invest.interf.service.InterfaceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 第三方接口接收和发送日志查询画面
 * @author xkni
 * @version 1.0 2020年2月18日
 * @see InterfaceLog001Controller
 * @since 1.0
 */
@Controller
@PageId("InterfaceLog001")
@RequestMapping("/system/log/InterfaceLog001")
public class InterfaceLog001Controller extends BaseController{
   
    @Autowired
    private InterfaceLogService interfaceLogService;
    

    @Override
    public String init(Model model){
        List<SelectOptionDto> codeList =new ArrayList<SelectOptionDto>();
        SelectOptionDto send=new SelectOptionDto();
        send.setId("01");
        send.setText("已发送");
        codeList.add(send);
        SelectOptionDto noSend=new SelectOptionDto();
        noSend.setId("02");
        noSend.setText("未发送");
        codeList.add(noSend);
        model.addAttribute("interfStsType", JsonUtil.objToString(codeList));
        return super.init(model);
    }
    
    
    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<InterfStatus> selectForPage()
        throws Exception {
        Page<InterfStatus> page = interfaceLogService.selectForPage(
            RequestUtil.getPageParam());
        page.getRoot().forEach(item->{
            if(item.getInterfSts().equals("01"))
            {
                item.setInterfSts("已发送");
            }
            else
            {
                item.setInterfSts("未发送");
            }
            if(item.getSendFlg().equals("01"))
            {
                item.setSendFlg("是");
            }
            else
            {
                item.setSendFlg("否");
            }
        });
        return page;
    }

    /**
     * 再次发送
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("restartSend")
    public AjaxResult restartSend()
    {
        interfaceLogService.restartSend(
                RequestUtil.getPageParam());
        return new AjaxResult();
    }


}
