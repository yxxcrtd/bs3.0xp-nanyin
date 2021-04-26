/*
 * 文件名：SettleDataClear001Controller.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年08月10日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.invest.basicdata.dataclear.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.basicdata.dataclear.dto.SettleBatchResultDto;
import com.joyintech.tams.invest.basicdata.dataclear.service.SettleDataClear001Service;
import com.joyintech.tams.invest.basicdata.dataimport.dto.SettleTransResultDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 数据清算导入
 * @author 王波
 * @date 2020年08月10日
 * @see SettleDataClear001Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/basicdata/dataclear/SettleDataClear001")
@PageId("SettleDataClear001")
public class SettleDataClear001Controller extends BaseController {

    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private SettleDataClear001Service settleDataClear001Service;

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
        List<TreeNode> treeList = settleDataClear001Service.getInterGroup(map);
        return treeList;
    }

    /**
     * 查找日志分页数据
     * @return
     */
    @RequestMapping("/findLogPage")
    @ResponseBody
    public Page<SettleBatchResultDto> findLogPage(){
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return settleDataClear001Service.getInterActionLog(pageParam);
    }

    /**
     * 执行按钮处理
     * @return
     */
    @RequestMapping("execute")
    @ResponseBody
    public String execute() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return settleDataClear001Service.execute(pageParam);
    }

}
