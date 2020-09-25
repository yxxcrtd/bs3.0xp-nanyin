/*
 * 文件名：RskValStructure002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalstructure.controller;

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
import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.service.RskValStructuresService002;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月11日
 * @see RskValStructure002Controller
 * @since 1.0
 */
@RestController("rskValStructure002Ctl_dtx")
@RequestMapping("/RskValStructure002")
public class RskValStructure002Controller {
    @Autowired
    private RskValStructuresService002 rskValStructuresService002;
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
    public int insert(@RequestBody RskValStructureDto dto){
        return rskValStructuresService002.insert(dto);
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
    public int update(@RequestBody RskValStructureDto dto){
        return rskValStructuresService002.update(dto);
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
    public int delete(@RequestBody RskValStructureDto dto){
        return rskValStructuresService002.delete(dto);
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
    public List<RskValStructureDto> selectByName(@RequestBody Map<String,Object> params){
    	String structName = (String) params.get("structName");
        return rskValStructuresService002.selectByName(structName);
    }
    /**
     * 修改映射名称
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    @PostMapping("/selectById")
    public RskValStructureDto selectById(@RequestBody RskValStructureDto dto){
        return rskValStructuresService002.selectById(dto);
    }
}
