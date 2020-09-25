/*
 * 文件名：BussFinRecommentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.asset.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.common.service.BussFinRecommentService;
import com.joyintech.tams.common.base.entity.FinProductRecomment;

/**
 * 资产推荐方及费率信息 〈功能详细描述〉
 * 
 * @author renping
 * @version 1.0 2019年10月10日
 * @see BussFinRecommentController
 * @since 1.0
 */
@RestController
@RequestMapping("/BussFinRecomment")
public class BussFinRecommentController {

    @Autowired
    private BussFinRecommentService bussFinRecommentService;

    /**
     * 初始化资产推荐方及费率信息
     * 
     * @param finprodId
     * @return 资产推荐方及费率信息
     * @see
     * @since 1.0
     */
    @GetMapping("getFinProductRecommentByProductId")
    public List<FinProductRecomment> getFinProductRecommentByProductId(String finprodId) {
        return bussFinRecommentService.getFinProductRecommentByProductId(finprodId);
    }

    /**
     * 构建推荐方下拉框
     * 
     * @param param 参数
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("qryRecommenderSelect/{page}/{length}")
    public Page<SelectOptionDto> qryRecommenderSelect(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length) {
        return bussFinRecommentService.qryRecommenderSelect(page, length, param);
    }

}
