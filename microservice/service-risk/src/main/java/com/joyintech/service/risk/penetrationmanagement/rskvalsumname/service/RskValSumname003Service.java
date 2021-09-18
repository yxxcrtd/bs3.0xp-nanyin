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
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtl;
import com.joyintech.tams.common.base.entity.RskValtblStructMapDtl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    /**
     * 新建映射明细
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public void insertList(List<RskValtblSumsubMapDtl> list){
        //分批次插入数据库
        int maxDataCountOneTime = reportInsertLongCount;
        //插入list大小
        int dataCount = list.size();
        //若需要插入的数据比最大插入的数据少，则直接全部插入
        if (dataCount <= maxDataCountOneTime) {
            rskValtblSumsubMapDtlMapper.insertList(list);
            return ;
        }

        List<RskValtblSumsubMapDtl> newDataList = new ArrayList<RskValtblSumsubMapDtl>();
        newDataList.addAll(list);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
            rskValtblSumsubMapDtlMapper.insertList(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            rskValtblSumsubMapDtlMapper.insertList(newDataList);
        }
    }
}
