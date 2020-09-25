/*
 * 文件名：ValInertfStartUp001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.interf.inertfStartUp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;
import com.joyintech.tams.common.base.entity.ValInterfTask;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;

/**
 * 接口启动
 * 
 * @author HuangJian
 * @version 1.0 2018年7月2日
 * @see ValInertfStartUp001Controller
 * @since 1.0
 */
@RestController(value="serviceValInertfStartUp001Controller")
@RequestMapping("/ValInertfStartUp001")
public class ValInertfStartUp001Controller extends BaseController {

    @Autowired
    private ValInertfStartUp001Service valInertfStartUp001Service;
    
    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * 查询
     * 
     * @return List<ValInterfTask>
     * @see
     * @since 1.0
     */
    @PostMapping("openInertfGroupSelect")
    public List<ValInterfTask> openBatchGroupSelect(String name) {
        return valInertfStartUp001Service.selectInertfTask(name);
    }

    /**
     * 接口启动
     * 
     * @throws IOException
     * @see
     * @since 1.0
     */
    @RequestMapping("runAs")
    @ResponseBody
    public void runAs()
        throws IOException {
        String datas = this.getParam("datas");
        String startDate = this.getParam("startDate");
        String endDate = this.getParam("endDate");
        String department = this.getParam("department");
        
        String execTaskId = interfCommonService.getUUid();
        List<ValInterfTask> listdto = JsonUtils.json2List(datas, ValInterfTask.class);
        String url = valInertfStartUp001Service.getUrl(department);
        Map<String, String> urlParams = valInertfStartUp001Service.getParm(listdto, DateUtil.formatDate(startDate),
            DateUtil.formatDate(endDate));
        urlParams.put(InterfCommonConstant.接口_任务执行ID, execTaskId);
        valInertfStartUp001Service.runInterf(url, urlParams);

    }

}
