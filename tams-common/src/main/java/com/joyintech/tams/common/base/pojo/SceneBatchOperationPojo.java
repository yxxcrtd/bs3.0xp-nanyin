/*
 * 文件名：SceneBatchOperationPojo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2019年8月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.common.base.pojo;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 情景批量处理提交参数对象
 * @author Jiaoxujin
 * @version 1.0 2019年8月24日
 * @see SceneBatchOperationPojo
 * @since 1.0
 */
public class SceneBatchOperationPojo extends BaseEntity implements ITableRow  , ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7780489155466746420L;

    /**
     * 情景ID列表，逗号隔开
     */
    private String sceneIds;
    
    /**
     * 情景状态
     */
    private String effStatus;

    /**
     * 获取 sceneIds
     * @return sceneIds.
     */
    public String getSceneIds() {
        return sceneIds;
    }

    /**
     * 设置 sceneIds
     * @param sceneIds sceneIds
     */
    public void setSceneIds(String sceneIds) {
        this.sceneIds = sceneIds;
    }

    /**
     * 获取 sceneIdList
     * @return sceneIdList.
     */
    public List<String> getSceneIdList() {
        if (StringUtils.isNotBlank(sceneIds)) {
            return Arrays.asList(sceneIds.split(","));
        }
        return null;
    }

    /**
     * 获取 effStatus
     * @return effStatus.
     */
    public String getEffStatus() {
        return effStatus;
    }

    /**
     * 设置 effStatus
     * @param effStatus effStatus
     */
    public void setEffStatus(String effStatus) {
        this.effStatus = effStatus;
    }

}
