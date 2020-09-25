/*
 * 文件名：RskValStructuresService002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalstructure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblStructMapMapper;
import com.joyintech.tams.common.base.entity.RskValtblStructMap;
import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dao.RskValStructureMapper;
import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月11日
 * @see RskValStructuresService002
 * @since 1.0
 */
@Service
public class RskValStructuresService002 {
    @Autowired
    private RskValtblStructMapMapper rskValtblStructMapMapper;
    @Autowired
    private RskValStructureMapper rskValStructureMapper;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int insert(RskValStructureDto dto) {
        RskValtblStructMap record = new RskValtblStructMap();
        record.setId(dto.getId());
        record.setName(dto.getStructName());
        return rskValtblStructMapMapper.insert(record);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int update(RskValStructureDto dto) {
        RskValtblStructMap record = new RskValtblStructMap();
        record.setId(dto.getId());
        record.setName(dto.getStructName());
        return rskValtblStructMapMapper.updateByPrimaryKey(record);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param structName
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValStructureDto> selectByName(String structName) {
        return rskValStructureMapper.selectByName(structName);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public RskValStructureDto selectById(RskValStructureDto dto) {
        RskValtblStructMap entity = rskValtblStructMapMapper.selectByPrimaryKey(dto.getId());
        if(entity != null){
            RskValStructureDto d = new RskValStructureDto();
            d.setId(entity.getId());
            d.setStructName(entity.getName());
            return d;
        }
        return null;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(RskValStructureDto dto) {
        //根据id删除映射明细表
        rskValStructureMapper.deleteById(dto);
        return rskValtblStructMapMapper.deleteByPrimaryKey(dto.getId());
    }

}
