/*
 * 文件名：MstProjectCatInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.755
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.syspara.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;
import com.joyintech.service.basicdata.projectcatinfo.service.MstProjectCatInfoService;
import com.joyintech.system.base.dao.SysParaMapper;
import com.joyintech.system.base.entity.SysPara;
import com.joyintech.system.base.entity.SysParaKey;
import com.joyintech.system.syspara.service.SysParaService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统参数
 * 
 * @author chaowei
 * @version 1.0 2020-06-30T15:13:50.755
 * @see InterfSysParaController
 * @since 1.0
 */
@RestController("/InterfSysPara_dtx")
@RequestMapping("/InterfSysPara")
public class InterfSysParaController {

    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private SysParaMapper sysParaMapper;

    /**
     * 平台日期
     *
     */
    @PostMapping("getSysDate")
    public Map<String,Object> getSysDate()
        throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sysDate",platformDateImpl.getSysDateStr());
        return map;
    }

    /**
     * 返回系统参数
     *
     */
    @PostMapping("getSysPara")
    public Map<String,Object> getSysPara(@RequestBody Map<String, Object> param)
            throws Exception {
        SysParaKey key = new SysParaKey();
        key.setParaCode((String)param.get("paraCode"));
        key.setSystemCode((String)param.get("systemCode"));
        Map<String,Object> map = new HashMap<String,Object>();

        SysPara sysPara = sysParaMapper.selectByPrimaryKey(key);
        if(sysPara!=null) {
            map.put("paramValue", sysPara.getParamValue());
        }
        return map;
    }

}
