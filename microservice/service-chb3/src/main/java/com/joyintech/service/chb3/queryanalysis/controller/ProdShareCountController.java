package com.joyintech.service.chb3.queryanalysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.queryanalysis.prodsharecount.dto.ProdShareCount001Dto;
import com.joyintech.tams.report.queryanalysis.prodsharecount.service.ProdShareCount001Service;

/**
 * 产品份额统计服务
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see ProdShareCountController
 * @since 1.0
 */ 
@RestController
@RequestMapping("/ProdShareCount")
public class ProdShareCountController extends BaseController{
    /**
     * 产品份额统计servive
     */
    @Autowired
    private ProdShareCount001Service prodShareCount001Service;


    /**
     *
     * 业务画面查询事件
     *
     * @return 结果列表
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ProdShareCount001Dto> selectAllData(@RequestBody Map<String, Object> param) {
        Page<ProdShareCount001Dto> page = prodShareCount001Service.searchPage(param);
        return page;
    }

    /**
     *
     * 业务画面导出查询
     * @return 結果
     * @see
     * @since 1.0
     */
    @PostMapping("setDataList")
    public List<ProdShareCount001Dto> setDataList(@RequestBody Map<String, Object> param) {
    	return prodShareCount001Service.setDataList(param);
    }
}
