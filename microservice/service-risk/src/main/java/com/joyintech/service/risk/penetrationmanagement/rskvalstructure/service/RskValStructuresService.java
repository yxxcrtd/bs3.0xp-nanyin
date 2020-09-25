/*
 * 文件名：RskValStructuresService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalstructure.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dao.RskValStructureMapper;
import com.joyintech.service.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月11日
 * @see RskValStructuresService
 * @since 1.0
 */
@Service
public class RskValStructuresService extends BaseService<RskValStructureDto>{
    @Autowired
    private RskValStructureMapper rskValStructureMapper;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValStructureDto> getStructName(Map<String, Object> map) {
        return rskValStructureMapper.getStructName(map);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<RskValStructureDto> selectForPage(int page, int length,
                                                  Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page, length, () -> {
            rskValStructureMapper.selectForPage(pageParam);
        });
    }
    
    public List<RskValStructureDto> selectForList(String structId){
        return rskValStructureMapper.selectForList(structId);
    }
    /**
     * 选中记录在业务表中是否已关联组合关联组合
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public boolean getCountById(RskValStructureDto dto) {
        return rskValStructureMapper.getCountById(dto)>0;
    }
    
}
