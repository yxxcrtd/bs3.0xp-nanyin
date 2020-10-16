/*
 * 文件名：RskValSumname002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalsumname.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblSumsubMapDtlMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumsubMapMapper;
import com.joyintech.tams.common.base.entity.RskValtblSumsubMap;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dao.RskValSumnameMapper;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月17日
 * @see RskValSumname002Service
 * @since 1.0
 */
@Service("backRskValSumname002Service")
public class RskValSumname002Service extends BaseService<RskValSumnameDto>{
    /**
     * 汇总映射mapper
     */
    @Autowired
    private RskValtblSumsubMapMapper rskValtblSumsubMapMapper;
    /**
     * 本地mapper
     */
    @Autowired
    private RskValSumnameMapper rskValSumnameMapper;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int insert(RskValSumnameDto dto) {
        RskValtblSumsubMap record = new RskValtblSumsubMap();
        record.setId(dto.getId());
        record.setName(dto.getName());
        record.setTrimChars(dto.getTrimChars());
        return rskValtblSumsubMapMapper.insert(record);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int update(RskValSumnameDto dto) {
        RskValtblSumsubMap record = new RskValtblSumsubMap();
        record.setId(dto.getId());
        record.setName(dto.getName());
        record.setTrimChars(dto.getTrimChars());
        return rskValtblSumsubMapMapper.updateByPrimaryKey(record);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(RskValSumnameDto dto) {
        //根据id删除汇总映射明细表
        rskValSumnameMapper.deleteById(dto);
        return rskValtblSumsubMapMapper.deleteByPrimaryKey(dto.getId());
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public RskValSumnameDto selectById(RskValSumnameDto dto) {
        RskValtblSumsubMap entity = rskValtblSumsubMapMapper.selectByPrimaryKey(dto.getId()); 
        if(entity != null && entity.getId() != null){
            RskValSumnameDto e = new RskValSumnameDto();
            e.setId(entity.getId());
            e.setName(entity.getName());
            e.setTrimChars(entity.getTrimChars());
            return e;
        }
        return null;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param name
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValSumnameDto> selectByName(String name) {
        return rskValSumnameMapper.selectBySumName(name);
    }

}
