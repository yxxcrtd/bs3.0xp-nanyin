package com.joyintech.tams.invest.riskredis.quota.interlog.controller;


import java.util.Base64;
import java.util.Base64.Decoder;

import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.riskredis.quota.interlog.dto.RepInterLogDto;
import com.joyintech.tams.invest.riskredis.quota.interlog.service.RepInterLogService;

/**
 * 接口日志管理
 * @author 郑俊平
 * @version 1.0 2019年9月4日
 * @see RepInterLog002Controller
 * @since 1.0
 */
@Controller
@PageId("RepInterLog002")
@RequestMapping("/invest/riskredis/quota/interlog/RepInterLog002")
public class RepInterLog002Controller extends BaseController {

    /**
     * 接口日志查询service
     */
    @Autowired
    private RepInterLogService repInterLogService;
    

    /**
     * 接口日志查询详细的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
         this.setSelectOptionToModel(model);
         
         String uuid = this.getParam("uuid");
         RepInterLogDto repInterLogDto = repInterLogService.selectInfo(uuid);
        // SNSTZJY-1002 modify by zhangrui1 start
        if (!StringUtil.isEmpty(repInterLogDto.getResponseData())) {
            Decoder decoder = Base64.getDecoder();
            repInterLogDto.setResponseData(
                new String(decoder.decode(repInterLogDto.getResponseData())));
        }
        // SNSTZJY-1002 modify by zhangrui1 end
         repInterLogDto.setPageModel(this.getParam("pageModel"));;
         model.addAttribute("entity", repInterLogDto);
         return super.init(model);
    }

}
