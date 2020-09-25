/*
 * 文件名：RskValSumname002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalsumname.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.service.RskValSumname002Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月17日
 * @see RskValSumname002Controller
 * @since 1.0
 */
@RestController("rskValSumname002Ctl_dtx")
@RequestMapping("/RskValSumname002")
public class RskValSumname002Controller {
    @Autowired
    private RskValSumname002Service rskValSumname002Service;
    /**
     * 新建映射名称
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
        return rskValSumname002Service.insert(dto);
    }
    /**
     * 修改映射名称
     *
     * @param map 查询的参数
     * @return 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/update")
    public int update(@RequestBody RskValSumnameDto dto){
        return rskValSumname002Service.update(dto);
    }
    /**
     * 删除映射名称
     *
     * @param map 查询的参数
     * @return 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public int delete(@RequestBody RskValSumnameDto dto){
        return rskValSumname002Service.delete(dto);
    }
    /**
     * 根据id查询映射
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    @PostMapping("/selectById")
    public RskValSumnameDto selectById(@RequestBody RskValSumnameDto dto){
        return rskValSumname002Service.selectById(dto);
    }
    /**
     * 修改映射名称
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByName")
    public List<RskValSumnameDto> selectByName(@RequestBody Map<String,Object> params){
    	String name = (String) params.get("name");
        return rskValSumname002Service.selectByName(name);
    }
}
