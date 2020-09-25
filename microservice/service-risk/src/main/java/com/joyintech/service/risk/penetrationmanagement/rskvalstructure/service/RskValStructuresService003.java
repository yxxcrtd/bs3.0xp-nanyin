/*
 * 文件名：RskValStructuresService003.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalstructure.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblStructMapDtlMapper;
import com.joyintech.tams.common.base.entity.RskValtblStructMapDtl;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月13日
 * @see RskValStructuresService003
 * @since 1.0
 */
@Service
public class RskValStructuresService003 {
    @Autowired
    private RskValtblStructMapDtlMapper rskValtblStructMapDtlMapper;
    /**
     * 修改映射明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int update(RskValStructureDto dto) {
        return rskValtblStructMapDtlMapper.updateByPrimaryKey(dto);
    }

    /**
     * 新建映射明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int insert(RskValStructureDto dto) {
        return rskValtblStructMapDtlMapper.insert(dto);
    }
    /**
     * 删除映射明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(RskValStructureDto dto) {
        return rskValtblStructMapDtlMapper.deleteByPrimaryKey(dto);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    public RskValStructureDto select(RskValStructureDto dto) throws IllegalAccessException, InvocationTargetException {
        RskValtblStructMapDtl entity = rskValtblStructMapDtlMapper.selectByPrimaryKey(dto);
        if(entity != null){
            RskValStructureDto d = new RskValStructureDto();
            BeanUtil.copyProperties(d,entity);
            return d;
        }
        return null;
    }
}
