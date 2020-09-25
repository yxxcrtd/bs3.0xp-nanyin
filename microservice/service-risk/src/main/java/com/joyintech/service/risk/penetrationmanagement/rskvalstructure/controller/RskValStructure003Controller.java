/*
 * 文件名：RskValStructure003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalstructure.controller;

import java.lang.reflect.InvocationTargetException;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.service.RskValStructuresService003;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月13日
 * @see RskValStructure003Controller
 * @since 1.0
 */
@RestController("rskValStructure003Ctl_dtx")
@RequestMapping("/RskValStructure003")
public class RskValStructure003Controller {
    @Autowired
    private RskValStructuresService003 rskValStructuresService003;
    /**
     * 新建映射明细
     *
     * @param dto 
     * @return 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insert")
    public int insert(@RequestBody RskValStructureDto dto){
        return rskValStructuresService003.insert(dto);
    }
    /**
     * 修改映射明细
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/update")
    public int update(@RequestBody RskValStructureDto dto){
        return rskValStructuresService003.update(dto);
    }
    /**
     * 删除映射明细
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public int delete(@RequestBody RskValStructureDto dto){
        return rskValStructuresService003.delete(dto);
    }
    /**
     * 查询
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @PostMapping("/select")
    public RskValStructureDto select(@RequestBody RskValStructureDto dto) throws IllegalAccessException, InvocationTargetException{
        return rskValStructuresService003.select(dto);
    }
}
