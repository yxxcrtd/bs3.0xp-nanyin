/*
 * 文件名：VersionSwitchController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：曹锦
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.versionswitch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.basicdata.versionswitch.service.VersionSwitchService;

/**
 * 版本切换
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author caojin
 * @version 1.0 2020年5月23日
 * @see VersionSwitchController
 * @since 1.0
 */
@RestController
@RequestMapping("/VersionSwitch")
public class VersionSwitchController {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private VersionSwitchService versionSwitchService;

    /**
     * 更新识字字典表默认值 <功能详细描述>
     * 
     * @param pageParam Map<String, Object>
     * @return List<MstYieldCurveInfoDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("/selectVersionType")
    public String selectVersionType(@RequestBody String versionType) throws Exception {
    	return versionSwitchService.selectVersionType();
    }
    
    /**
     * 更新识字字典表默认值 <功能详细描述>
     * 
     * @param pageParam Map<String, Object>
     * @return List<MstYieldCurveInfoDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("/updateVersionType")
    public void updateVersionType(@RequestBody String versionType) throws Exception {
         versionSwitchService.updateVersionType(versionType);
    }
    
}
