package com.joyintech.service.portfolio.actualtimeasset.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.actualtimeasset.dto.ActualTimeAssetDto;
import com.joyintech.service.portfolio.actualtimeasset.service.ActualTimeAssetService;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeAssetController
 * @author: yanya
 * @create: 2019-10-15
 **/
@RestController("backActualTimeAssetController_dtx")
@RequestMapping("/ActualTimeAsset")
public class ActualTimeAssetController {

    /**
     * 实时资产头寸Service
     */
    @Autowired
    private ActualTimeAssetService actualTimeAssetService;

    /**
     * 证券账户下拉框事件
     *
     * @param map 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getSecAcct")
    public List<SelectOptionDto> getSecAcct(@RequestBody Map<String,Object> map){
        return actualTimeAssetService.getSecAcct(map);
    }

    /**
     * 资产代码的联想下拉框事件
     *
     * @param map 输入参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetsInfo")
    public List<SelectOptionDto> getAssetsInfo(@RequestBody Map<String,Object> map){
        return actualTimeAssetService.getAssetsInfo(map);
    }

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ActualTimeAssetDto> selectAll(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return actualTimeAssetService.selectForPage(page, length, param);
    }
}
