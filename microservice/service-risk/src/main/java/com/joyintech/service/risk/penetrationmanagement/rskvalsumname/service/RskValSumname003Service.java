/*
 * 文件名：RskValSumname003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalsumname.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblSumsubMapDtlMapper;
import com.joyintech.tams.common.base.entity.RskValtblSumsubMapDtl;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dao.RskValSumnameMapper;
import com.joyintech.service.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月19日
 * @see RskValSumname003Service
 * @since 1.0
 */
@Service("backRskValSumname003Service")
public class RskValSumname003Service extends BaseService<RskValSumnameDto>{
    /**
     * 汇总映射明细mapper
     */
    @Autowired
    private RskValtblSumsubMapDtlMapper rskValtblSumsubMapDtlMapper;
    @Autowired
    private RskValSumnameMapper rskValSumnameMapper;
    /**
     * 修改映射明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int update(RskValSumnameDto dto) {
        return rskValtblSumsubMapDtlMapper.updateByPrimaryKey(dto);
    }

    /**
     * 新建映射明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int insert(RskValSumnameDto dto) {
        return rskValtblSumsubMapDtlMapper.insert(dto);
    }
    /**
     * 删除映射明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(RskValSumnameDto dto) {
        return rskValtblSumsubMapDtlMapper.deleteByPrimaryKey(dto);
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
    public RskValSumnameDto selectByKey(RskValSumnameDto dto) throws IllegalAccessException, InvocationTargetException {
        RskValtblSumsubMapDtl entity = rskValtblSumsubMapDtlMapper.selectByPrimaryKey(dto);
        if(entity != null){
            RskValSumnameDto d = new RskValSumnameDto();
            BeanUtil.copyProperties(d,entity);
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
    public RskValSumnameDto selectByName(RskValSumnameDto dto) {
        return rskValSumnameMapper.selectByName(dto);
    }
}
