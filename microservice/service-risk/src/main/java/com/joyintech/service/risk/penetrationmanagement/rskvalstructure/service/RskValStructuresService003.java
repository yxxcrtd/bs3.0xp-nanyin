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
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.RepExpectedLog;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
     * 长sql最大一次插入或者更新条数
     */
    @Value("${application.reportInsertLongCount}")
    public int reportInsertLongCount;
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
    /**
     * 新建映射明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Boolean insertList(List<RskValtblStructMapDtl> list) {
            //分批次插入数据库
            int maxDataCountOneTime = reportInsertLongCount;
            //插入list大小
            int dataCount = list.size();
            //若需要插入的数据比最大插入的数据少，则直接全部插入
            if (dataCount <= maxDataCountOneTime) {
                rskValtblStructMapDtlMapper.insertList(list);
                return true;
            }

            List<RskValtblStructMapDtl> newDataList = new ArrayList<RskValtblStructMapDtl>();
            newDataList.addAll(list);

            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                rskValtblStructMapDtlMapper.insertList(newDataList.subList(0, maxDataCountOneTime));
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                rskValtblStructMapDtlMapper.insertList(newDataList);
            }
            return true;
    }
}
