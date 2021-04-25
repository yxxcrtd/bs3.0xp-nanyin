/*
 * 文件名：TrdAvailableCashQueryController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.common.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.trade.service.BussRepoSecService;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.trade.dto.PledgeInsertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("BussRepoSecCtr_dtx")
@RequestMapping("/BussRepoSec")
public class BussRepoSecController {
    /**
     * trdAvailableCashQueryService
     */
    @Autowired
    private BussRepoSecService bussRepoSecService;

    /**
     * 债券代码：下拉框
     *
     * @param params 画面参数
     * @return 债券代码：下拉框
     */
    @PostMapping("getBondCode")
    public List<SelectOptionDto> getSecId(@RequestBody Map<String, Object> params) {
        return bussRepoSecService.getBondCode(params);
    }
    
    /**
     * 债券质押回购多组合新增
     * 张庚林
     * 20190723
     */   
    @PostMapping("portfolioName")
    public PledgeBondInfoDto portfolioName(@RequestBody Map<String, Object> params) {
        return bussRepoSecService.portfolioName(params);
    }

    /**
     * 通过entrustId/bizCode检索已质押券信息
     *
     * @param params 画面参数
     * @return List<BussRepoSecDto>
     */
    @PostMapping("selectBussRepoSec")
    public BussRepoSecDto selectBussRepoSec(@RequestBody Map<String, Object> params) {
        BussRepoSecDto bussRepoSecDto = bussRepoSecService.selectBussRepoSec(params);
        return bussRepoSecDto;
    }

    /**
     * 债券质押回购多组合新增
     * 张庚林
     * 20190723
     */   
    @PostMapping("selectAvaPledgeBondDetailListPage/{page}/{length}")
    public Page<PledgeBondInfoDto> selectAvaPledgeBondDetailListPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) {
        Page<PledgeBondInfoDto> pledgeBondInfoDto=bussRepoSecService.selectAvaPledgeBondDetailPage(param,page,length);
        return pledgeBondInfoDto;
    }
    @PostMapping("selectAvaPledgeBondDetailList")
    public List<PledgeBondInfoDto> selectAvaPledgeBondDetailList(@RequestBody Map<String, Object> param) {
        List<PledgeBondInfoDto> pledgeBondInfoDto=bussRepoSecService.selectAvaPledgeBondDetailList(param);
        return pledgeBondInfoDto;
    }
    /**
     * 质押券置换换出列表按投资目的获取数据
     * nixiukuan
     * 20191129
     */
    @PostMapping("selectOutPledgeBondDetailList")
    public List<PledgeBondInfoDto> selectOutPledgeBondDetailList(@RequestBody Map<String, Object> param) {
        List<PledgeBondInfoDto> pledgeBondInfoDto=bussRepoSecService.selectOutPledgeBondDetailList(param);
        return pledgeBondInfoDto;
    }

    /**
     * 撤销委托/回购交易时，删除回购质押品信息
     *
     * @param param 删除参数
     * @return successCount
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deletePledgeBond")
    public int deletePledgeBond(@RequestBody Map<String, Object> param) {
        return bussRepoSecService.deletePledgeBond(param);
    }


    /**
     * 7.6写入回购质押品信息
     *
     * @param pledgeInsertDto 回购质押品信息
     * @return successCount
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody PledgeInsertDto pledgeInsertDto) {
        return bussRepoSecService.insert(pledgeInsertDto);
    }
}
