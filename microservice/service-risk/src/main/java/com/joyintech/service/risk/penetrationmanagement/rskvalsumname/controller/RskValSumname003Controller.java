/*
 * 文件名：RskValSumname003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalsumname.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.service.RskValSumname003Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月19日
 * @see RskValSumname003Controller
 * @since 1.0
 */
@RestController("rskValSumname003Ctl_dtx")
@RequestMapping("/RskValSumname003")
public class RskValSumname003Controller {
    @Autowired
    private RskValSumname003Service rskValSumname003Service;
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
    public int insert(@RequestBody RskValSumnameDto dto){
        return rskValSumname003Service.insert(dto);
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
    public int update(@RequestBody RskValSumnameDto dto){
        return rskValSumname003Service.update(dto);
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
    public int delete(@RequestBody RskValSumnameDto dto){
        return rskValSumname003Service.delete(dto);
    }
    /**
     * 根据主键查询映射明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByKey")
    public RskValSumnameDto selectByKey(@RequestBody RskValSumnameDto dto) throws IllegalAccessException, InvocationTargetException{
        return rskValSumname003Service.selectByKey(dto);
    }
    /**
     * 根据id和映射明细名称查询映射明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByName")
    public RskValSumnameDto selectByName(@RequestBody RskValSumnameDto dto){
        return rskValSumname003Service.selectByName(dto);
    }
}
