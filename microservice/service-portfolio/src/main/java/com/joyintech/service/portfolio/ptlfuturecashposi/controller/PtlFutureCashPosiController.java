/*
 * 文件名：PtlFutureCashPosiController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlfuturecashposi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.portfolio.ptlfuturecashposi.dto.PtlFutureCashPosiDto;
import com.joyintech.service.portfolio.ptlfuturecashposi.service.PtlFutureCashPosiService;

/**
 * 组合未来头寸分析 〈功能详细描述〉
 * 
 * @author renping
 * @version 1.0 2019年11月9日
 * @see PtlFutureCashPosiController
 * @since 1.0
 */
@RestController
@RequestMapping("/PtlFutureCashPosi")
public class PtlFutureCashPosiController {

    /**
     * PtlFutureCashPosiService
     */
    @Autowired
    private PtlFutureCashPosiService ptlFutureCashPosiService;

    /**
     * 画面查询方法
     * 
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception Exception
     */
    @PostMapping("/selectData")
    public List<PtlFutureCashPosiDto> selectAll(@RequestBody Map<String, Object> param)
        throws Exception {
        return ptlFutureCashPosiService.selectData(param);
    }
}
