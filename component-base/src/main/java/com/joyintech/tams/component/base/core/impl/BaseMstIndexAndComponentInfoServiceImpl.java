/*
 * 文件名：BaseMstIndexAndComponentInfoServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.core.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.tams.common.base.dao.MstIndexComponentMapper;
import com.joyintech.tams.common.base.dao.MstIndexInfoMapper;
import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.tams.common.base.entity.MstIndexInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.base.core.BaseMstIndexAndComponentInfoService;
import com.joyintech.tams.component.base.core.dto.BaseMstIndexAndComponentInfoDto;
import com.joyintech.tams.component.base.core.dto.BaseMstIndexInfoDto;
import com.joyintech.tams.component.base.core.impl.dao.BaseMstIndexAndComponentInfoMapper;

/**
 * 基准信息组件实现类
 * 
 * @author huwei
 * @version 1.0 2018年8月29日
 * @see BaseMstIndexAndComponentInfoServiceImpl
 * @since 1.0
 */
@Service
public class BaseMstIndexAndComponentInfoServiceImpl implements BaseMstIndexAndComponentInfoService {

    /**
     * 基准信息Mapper
     */
    @Autowired
    private MstIndexInfoMapper mstIndexInfoMapper;

    /**
     * 基准信息成分表Mapper
     */
    @Autowired
    private MstIndexComponentMapper mstIndexComponentMapper;

    /**
     * 基准信息组件Mapper
     */
    @Autowired
    private BaseMstIndexAndComponentInfoMapper baseMstIndexAndComponentInfoMapper;

    /**
     * 新建基准信息 <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean newIndexInfo(List<MstIndexInfo> mstIndexInfos) {
        // 遍历集合将数据插入基准信息表中
        if (mstIndexInfos != null && mstIndexInfos.size() > 0) {
            for (MstIndexInfo mstIndexInfo : mstIndexInfos) {
                mstIndexInfoMapper.insertSelective(mstIndexInfo);
            }
            return true;
        }
        return false;
    }

    /**
     * 新建基准信息(含基准成分) <一句话功能简述> <功能详细描述>
     * 
     * @param dtos
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean newIndexInfoContainsElement(List<BaseMstIndexAndComponentInfoDto> dtos) {
        if (dtos != null && dtos.size() > 0) {
            // 循环输入参数List<基准信息含基准成分dto>，将基准信息含基准成分dto插入基准信息和基准成分表
            for (BaseMstIndexAndComponentInfoDto baseMstIndexAndComponentInfoDto : dtos) {
                mstIndexInfoMapper.insertSelective(
                    baseMstIndexAndComponentInfoDto.getMstIndexInfo());
                if (baseMstIndexAndComponentInfoDto.getMstIndexComponents() != null
                    && baseMstIndexAndComponentInfoDto.getMstIndexComponents().size() > 0) {
                    for (MstIndexComponent mstIndexComponent : baseMstIndexAndComponentInfoDto.getMstIndexComponents()) {
                        mstIndexComponentMapper.insertSelective(mstIndexComponent);
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 查询基准信息 <一句话功能简述> <功能详细描述>
     * 
     * @param mstIndexInfo
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public List<MstIndexInfo> queryIndexInfo(MstIndexInfo mstIndexInfo) {
        List<MstIndexInfo> mstIndexInfos = baseMstIndexAndComponentInfoMapper.queryIndexInfo(
            mstIndexInfo);
        return mstIndexInfos;
    }
    
    /**
     * 查询基准信息（含基准成分）
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseMstIndexInfoDto
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public List<BaseMstIndexAndComponentInfoDto> queryIndexInfoContainsElement(BaseMstIndexInfoDto baseMstIndexInfoDto) {
        return baseMstIndexAndComponentInfoMapper.queryIndexInfoContainsElement(
            baseMstIndexInfoDto);
    }

    /**
     * 删除基准信息 <一句话功能简述> <功能详细描述>
     * 
     * @param mstIndexInfo
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean deleteIndexInfo(MstIndexInfo mstIndexInfo) {
        // 按照输入参数删除基准信息
        int count = mstIndexInfoMapper.deleteByPrimaryKey(mstIndexInfo.getIndexId());
        // 判断是否完成
        if (count == 1) {
            return true;
        }
        return false;
    }

    /**
     * 删除基准信息（含基准成分） <一句话功能简述> <功能详细描述>
     * 
     * @param BaseMstIndexInfoDto
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean deleteIndexInfoContainsElement(BaseMstIndexInfoDto baseMstIndexInfoDto) {
        // 基准编号必填
        if (StringUtil.isNotEmpty(baseMstIndexInfoDto.getIndexId())) {
            // 按照输入参数删除基准信息和基准成分表
            mstIndexInfoMapper.deleteByPrimaryKey(baseMstIndexInfoDto.getIndexId());
            baseMstIndexAndComponentInfoMapper.deleteByPrimaryKey(baseMstIndexInfoDto.getIndexId(),
                baseMstIndexInfoDto.getComponentId(), baseMstIndexInfoDto.getComponentType());
            return true;
        }
        return false;

    }

    /**
     * 修改基准信息 <一句话功能简述> <功能详细描述>
     * 
     * @param mstIndexInfos
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean upadteIndexInfo(List<MstIndexInfo> mstIndexInfos) {
        // 循环输入参数List<基准信息Entity>，更新基准信息表
        if (mstIndexInfos != null && mstIndexInfos.size() > 0) {
            for (MstIndexInfo mstIndexInfo : mstIndexInfos) {
                mstIndexInfoMapper.updateByPrimaryKeySelective(mstIndexInfo);
            }
            return true;
        }
        return false;
    }

    /**
     * 修改基准成分表 <一句话功能简述> <功能详细描述>
     * 
     * @param mstIndexComponents
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean upadteIndexComponent(List<MstIndexComponent> mstIndexComponents) {
        // 循环输入参数List<基准成分表Entity>，更新基准成分表
        if (mstIndexComponents != null && mstIndexComponents.size() > 0) {
            for (MstIndexComponent mstIndexComponent : mstIndexComponents) {
                mstIndexComponentMapper.updateByPrimaryKeySelective(mstIndexComponent);
            }
            return true;
        }
        return false;
    }
}
