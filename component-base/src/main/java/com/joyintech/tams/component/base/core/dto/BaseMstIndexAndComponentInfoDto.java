/*
 * 文件名：BaseMstIndexAndComponentInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.core.dto;

import java.util.List;
import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.tams.common.base.entity.MstIndexInfo;

/**
 * 基准信息含基准成分Dto 〈一句话功能简述〉 〈功能详细描述〉
 * 基准信息含基准成分操作用DTO
 * @author huwei
 * @version 1.0 2018年9月3日
 * @see BaseMstIndexAndComponentInfoDto
 * @since 1.0
 */
public class BaseMstIndexAndComponentInfoDto {

    /*
     * 基准信息Entity
     */
    private MstIndexInfo mstIndexInfo;

    /**
     * List<基准成分表Entity>
     */
    private List<MstIndexComponent> mstIndexComponents;

    /**
     * 获取 mstIndexInfo
     * 
     * @return mstIndexInfo.
     */
    public MstIndexInfo getMstIndexInfo() {
        return mstIndexInfo;
    }

    /**
     * 设置 mstIndexInfo
     * 
     * @param mstIndexInfo mstIndexInfo
     */
    public void setMstIndexInfo(MstIndexInfo mstIndexInfo) {
        this.mstIndexInfo = mstIndexInfo;
    }
    
    /**
     * 获取 mstIndexComponents
     * @return mstIndexComponents.
     */
    public List<MstIndexComponent> getMstIndexComponents() {
        return mstIndexComponents;
    }
    
    /**
     * 设置 mstIndexComponents
     * @param mstIndexComponents mstIndexComponents
     */
    public void setMstIndexComponents(List<MstIndexComponent> mstIndexComponents) {
        this.mstIndexComponents = mstIndexComponents;
    }
}
