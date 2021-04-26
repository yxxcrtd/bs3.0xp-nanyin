/*
 * 文件名：SettleDataImport001Controller.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年08月06日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.invest.basicdata.dataimport.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.basicdata.dataimport.dto.SettleTransResultDto;
import com.joyintech.tams.invest.basicdata.dataimport.service.SettleDataImport001Service;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;

/**
 * @author 王波
 * @date 2020年08月06日
 * @see SettleDataImport001Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/basicdata/dataimport/SettleDataImport001")
@PageId("SettleDataImport001")
public class SettleDataImport001Controller extends BaseController {

    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private SettleDataImport001Service settleDataImport001Service;
    /**
     *
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 获取平台日
        model.addAttribute("iPlatformDate", platformDateImpl.getSysDate());
        return super.init(model);
    }

    /**
     * 获取树列表
     * @return
     */
    @RequestMapping("/getInterGroup")
    @ResponseBody
    public List<TreeNode> getInterGroup(){
        Map<String,Object> map = RequestUtil.getPageParam();
        List<TreeNode> treeList = settleDataImport001Service.getInterGroup(map);
        return treeList;
    }

    /**
     * 查询日志列表
     * @return
     */
    @RequestMapping("/findLogPage")
    @ResponseBody
    public Page<SettleTransResultDto> findLogPage(){
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return settleDataImport001Service.getInterActionLog(pageParam);
    }

    /**
     * 执行按钮处理
     * @return
     */
    @RequestMapping("execute")
    @ResponseBody
    public String execute() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return settleDataImport001Service.execute(pageParam);
    }
}
