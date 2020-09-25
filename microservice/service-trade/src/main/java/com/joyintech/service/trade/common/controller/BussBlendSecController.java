/*
 * 文件名：TrdAvailableCashQueryController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.common.controller;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.common.service.BussBlendSecService;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/BussBlendSec")
public class BussBlendSecController {
    /**
     * bussBlendSecService
     */
    @Autowired
    private BussBlendSecService bussBlendSecService;

    /**
     * 债券代码：下拉框
     *
     * @param params 画面参数
     * @return 债券代码：下拉框
     */
    @PostMapping("getBondCode")
    public List<SelectOptionDto> getSecId(@RequestBody Map<String, Object> params) {
        return bussBlendSecService.getBondCode(params);
    }

    /**
     * 通过entrustId/bizCode检索已融入券信息
     *
     * @param params 画面参数
     * @return List<BussRepoSecDto>
     */
    @PostMapping("selectBussRepoSec")
    public BussRepoSecDto selectBussRepoSec(@RequestBody Map<String, Object> params) {
        return bussBlendSecService.selectBussRepoSec(params);
    }

    /**
     * 根据画面条件检索可融入券信息
     *
     * @param param 画面参数
     * @return List<PledgeBondInfoDto>
     */
    @PostMapping("selectAvaBlendBondDetailList")
    public List<PledgeBondInfoDto> selectAvaBlendBondDetailList(@RequestBody Map<String, Object> param) {
        return bussBlendSecService.selectAvaBlendBondDetailList(param);
    }

}
