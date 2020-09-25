package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.servermanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.servermanage.dto.ServerManage001Dto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.servermanage.service.ServerManage001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 外汇交易中心服务器管理
 * @author zcf
 * @version 1.0
 * @date 2020-01-13
 */

@Controller
@PageId("ServerManage001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/servermanage/ServerManage001")
public class ServerManage001Controller extends BaseController {

    @Autowired
    private ServerManage001Service serverManage001Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     *  分页查询
     * @return
     */
    @PostMapping("selectForPage")
    @ResponseBody
    public Page<ServerManage001Dto> selectForPage(){
        return serverManage001Service.selectPageData(RequestUtil.getPageParam());
    }

    /**
     * 刷新
     * @return
     */
    @RequestMapping("refresh")
    @ResponseBody
    public AjaxResult refresh() {
        AjaxResult result = new AjaxResult();
        //样例 start
        ServerManage001Dto dto = new ServerManage001Dto();
        dto.setStatus("正常");
        dto.setBeatTime(DateUtil.formatDateTime(new Date()));
        result.setData(dto);
        //end
        return result;
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public AjaxResult login() {
        AjaxResult result = new AjaxResult();
        return result;
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping("loginOut")
    @ResponseBody
    public AjaxResult loginOut() {
        AjaxResult result = new AjaxResult();
        return result;
    }
}
