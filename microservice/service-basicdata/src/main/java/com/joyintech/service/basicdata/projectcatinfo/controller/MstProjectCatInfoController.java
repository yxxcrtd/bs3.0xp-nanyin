/*
 * 文件名：MstProjectCatInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.755
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.projectcatinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;
import com.joyintech.service.basicdata.projectcatinfo.service.MstProjectCatInfoService;

/**
 * 项目类资产估值行情信息
 * 
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.755
 * @see MstProjectCatInfoController
 * @since 1.0
 */
@RestController("/MstProjectCatInfo_dtx")
@RequestMapping("/MstProjectCatInfo")
public class MstProjectCatInfoController {

    @Autowired
    private MstProjectCatInfoService mstProjectCatInfoService;

    /**
     * 分页查询
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<MstProjectCatInfoDto> selectForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return mstProjectCatInfoService.selectForPage(page, length, param);
    }

    /**
     * 修改或查看界面
     *
     * @param param 查询条件
     * @return 结果
     */
    @PostMapping("selectInfo")
    public MstProjectCatInfoDto selectInfo(@RequestBody Map<String, Object> param) {
        return mstProjectCatInfoService.selectInfo(param);
    }

    /**
     * 删除方法
     *
     * @param dto 画面数据
     * @return 删除结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstProjectCatInfoDto dto) {
        return mstProjectCatInfoService.delete(dto);
    }

    /**
     * 插入方法
     *
     * @param dto 画面数据
     * @return 保存结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstProjectCatInfoDto dto) {
        return mstProjectCatInfoService.insert(dto);
    }

    /**
     * 修改方法
     *
     * @param dto 画面数据
     * @return 保存结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstProjectCatInfoDto dto) {
        return mstProjectCatInfoService.update(dto);
    }

    /**
     * 查询界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     *
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetCode")
    public List<SuggestDto> getAssetCode(@RequestBody Map<String, Object> map) {
        return mstProjectCatInfoService.getAssetCode(map);
    }
    
    
    /**
     * 新建界面资产代码联想文本框 可以通过输入的资产代码模糊查询
     * 
     * @param map 查询参数
     * @return 资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetCodeList")
    public List<SelectOptionDto> getAssetCodeList(@RequestBody Map<String, Object> map) {
        return mstProjectCatInfoService.getAssetCodeList(map);
    }

}
