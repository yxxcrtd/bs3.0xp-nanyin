/*
 * 文件名：MstOpicsParaMante001Controller.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：huwei 修改时间：2018年7月23日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstopicsparamante.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.mstopicsparamante.dto.MstOpicsParaManteDto;
import com.joyintech.service.basicdata.mstopicsparamante.service.MstOpicsParaManteService;

/**
 * OPICS参数维护查询
 * 
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstOpicsParaManteController
 * @since 1.0
 */
@RestController("MstOpicsParaManteCtr_dtx")
@RequestMapping("/MstOpicsParaMante")
public class MstOpicsParaManteController {

    /**
     * OPICS参数维护业务层
     */
    @Autowired
    private MstOpicsParaManteService mstOpicsParaManteService;

    /**
     * 查询按钮的查询方法
     * 
     * @param pageParam map
     * @return Page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/selectOpicsParaManteInfo/{page}/{length}")
    public Page<MstOpicsParaManteDto> selectOpicsParaManteInfo(@RequestBody Map<String, Object> pageParam,
                                                               @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return mstOpicsParaManteService.selectOpicsParaManteInfo(page, length, pageParam);
    }

    /**
     * 通过主键查询方法
     * 
     * @param param map
     * @return MstOpicsParaManteDto
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByPrimaryKey")
    public MstOpicsParaManteDto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
        return mstOpicsParaManteService.selectByPrimaryKey(param);
    }

    /**
     * 工作流完成 <功能详细描述>
     * 
     * @param map
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete/{type}")
    public void doComplete(@RequestBody MstOpicsParaManteDto entity, @PathVariable("type") String type)
        throws Exception {
        // MstOpicsParaManteDto entity = (MstOpicsParaManteDto) JsonUtils.map2entity((Map<String, Object>) map.get("entity"),
        // MstOpicsParaManteDto.class);
        // String type = (String) map.get("type");
        mstOpicsParaManteService.doComplete(entity, type);
    }
}