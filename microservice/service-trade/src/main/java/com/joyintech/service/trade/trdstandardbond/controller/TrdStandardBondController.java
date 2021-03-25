/*
 * 文件名：TrdStandardBondController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.trdstandardbond.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.trdstandardbond.dto.TrdStandardBondInDto;
import com.joyintech.service.trade.trdstandardbond.dto.TrdStandardBondOutDto;
import com.joyintech.service.trade.trdstandardbond.dto.TrdStandardBondSearchDto;
import com.joyintech.service.trade.trdstandardbond.service.TrdStandardBondService;

/**
 * 回购交易微服务
 */
@RestController("TrdStandardBondCtr_dtx")
@RequestMapping("/TrdStandardBond")
public class TrdStandardBondController {

    @Autowired
    private TrdStandardBondService trdStandardBondService;

    /**
     * 标准券查询画面，查询标准券信息列表
     * 质押券入库画面，查询可质押债券列表
     *
     * @param page   page
     * @param length length
     * @param param  参数
     * @return Page<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectSearchDataList/{page}/{length}")
    public Page<TrdStandardBondSearchDto> selectForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
            throws Exception {
        return trdStandardBondService.selectSearchDataList(page, length, param);
    }

    /**
     * 查询债券代码：下拉框
     *
     * @param param 画面参数
     * @return 债券代码：下拉框
     */
    @RequestMapping("getBondCode")
    public List<SuggestDto> getBondCode(@RequestBody Map<String, Object> param) {
        return trdStandardBondService.getBondCode(param);
    }

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    public List<SuggestDto> getSecManageAcctId(@RequestBody Map<String, Object> param) {
        return trdStandardBondService.getSecManageAcctId(param);
    }

    /**
     * 质押券入库画面，查询可质押债券列表
     *
     * @param param 参数
     * @return Page<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectAvaPledgeBondList")
    public List<TrdStandardBondInDto> selectAvaPledgeBondList(@RequestBody Map<String, Object> param)
            throws Exception {
        return trdStandardBondService.selectAvaPledgeBondList(param);
    }

    /**
     * 质押券出库画面，查询可出库债券列表
     *
     * @param page   page
     * @param length length
     * @param param  参数
     * @return Page<TrdStandardBondOutDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectOutListData")
    public List<TrdStandardBondOutDto> selectOutListData(@RequestBody Map<String, Object> param) throws Exception {
        return trdStandardBondService.selectOutListData(param);
    }

    /**
     * 质押券入库画面，保存处理
     *
     * @param param 参数
     * @return inSaveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("inSave")
    public Long inSave(@RequestBody TrdStandardBondInDto dto) throws Exception {
        return trdStandardBondService.inSave(dto);
    }

    /**
     * 质押券入库画面，提交处理
     *
     * @param dto 参数
     * @return inSaveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("inSubmit")
    public Long inSubmit(@RequestBody TrdStandardBondInDto dto) throws Exception {
        return trdStandardBondService.inSubmit(dto);
    }

    /**
     * 质押券出库画面，保存处理
     *
     * @param dto 参数
     * @return outSaveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("outSave")
    public Long outSave(@RequestBody TrdStandardBondOutDto dto) throws Exception {
        return trdStandardBondService.outSave(dto);
    }
}
