/*
 * 文件名：SppiTest002Controller
 * 版权：Copyright 2017-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020-10-16
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.asset.sppi.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.asset.sppi.dto.SppiTemplateDto;
import com.joyintech.service.asset.sppi.service.SppiTest002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * SPPI测试新增、查看、审核功能的服务控制类
 * @author Jiaoxujin
 * @version 1.0 2020-10-16
 */
@RestController("SppiTest002Controller_dtx")
@RequestMapping("/SppiTest002")
public class SppiTest002Controller {
    @Autowired
    private SppiTest002Service sppiTest002Service;

    /**
     * 嵌套查询SPPI测试详细信息
     * @param sppiTestId String
     * @param finprodId String
     * @return SppiTemplateDto
     */
    @GetMapping("selectSppiTestDetail")
    public SppiTemplateDto selectSppiTestDetail(@RequestParam(value = "sppiTestId") String sppiTestId,
                                                @RequestParam(value = "finprodId") String finprodId) {
        return sppiTest002Service.selectSppiTestDetail(sppiTestId, finprodId);
    }

    /**
     * 新增保存SPPI测试结果
     * @param dto SppiTemplateDto
     * @return int
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("saveSppiTest")
    public int saveSppiTest(@RequestBody SppiTemplateDto dto) throws Exception {
        return sppiTest002Service.saveSppiTest(dto);
    }

    /**
     * 修改SPPI测试内容
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateSppiTest")
    public int updateSppiTest(@RequestBody SppiTemplateDto dto) throws Exception {
        return sppiTest002Service.updateSppiTest(dto);
    }

    /**
     * SPPI测试审核完成处理
     * @param dto SppiTemplateDto
     * @return int
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("auditCompleteSppiTest")
    public int auditCompleteSppiTest(@RequestBody SppiTemplateDto dto) throws Exception {
        return sppiTest002Service.auditCompleteSppiTest(dto);
    }

    /**
     * 删除本次SPPI测试记录
     * @param dto
     * @return int
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteSppiTest")
    public int deleteSppiTest(@RequestBody SppiTemplateDto dto) throws Exception {
        return sppiTest002Service.deleteSppiTest(dto);
    }

    /**
     * 更新SPPI测试主表的状态
     * @param dto
     * @return int
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateSppiTestStatus")
    public int updateSppiTestStatus(@RequestBody SppiTemplateDto dto) throws Exception {
        return sppiTest002Service.updateSppiTestStatus(dto);
    }

    /**
     * 查询金融产品有效交易数
     * @param finprodId
     * @return
     */
    @GetMapping("queryValidTradeCount")
    public int queryValidTradeCount(@RequestParam(value = "finprodId") String finprodId) {
        return sppiTest002Service.queryValidTradeCount(finprodId);
    }
}
