/*
 * 文件名：PtlAnalyzeAfterInvest001Controller
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：李淑萍
 * 修改时间：2019/10/23
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.service.portfolio.ptlanalyzeafterinvest.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest001Dto;
import com.joyintech.service.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest002Dto;
import com.joyintech.service.portfolio.ptlanalyzeafterinvest.service.PtlAnalyzeAfterInvest001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author 李淑萍
 * @version 1.0 2019/10/23
 * @see
 * @since 1.0
 */
@RestController("backPtlAnalyzeAfterInvest001Controller")
@RequestMapping("/PtlAnalyzeAfterInvest")
public class PtlAnalyzeAfterInvest001Controller {

    @Autowired
    PtlAnalyzeAfterInvest001Service ptlAnalyzeAfterInvest001Service = new PtlAnalyzeAfterInvest001Service();

    /**
     * 分页查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlAnalyzeAfterInvest001Dto> selectAll(@RequestBody Map<String, Object> param,
                                                       @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        return ptlAnalyzeAfterInvest001Service.selectForPage(page,length,param);
    }
    /**
     *@title: getSourceData
     *@description: 获取行情波动图数据源
     *@author: 王贵奇
     *@date: 2019/11/05
     *@return: Page<PtlAnalyzeAfterInvest002Dto>
     *@throws:
     */
    @PostMapping("/getSourceData")
    public List<PtlAnalyzeAfterInvest002Dto> getCurveType(@RequestBody Map<String, Object> param)
        throws Exception {
        return ptlAnalyzeAfterInvest001Service.getSourceData(param);
    }
}
