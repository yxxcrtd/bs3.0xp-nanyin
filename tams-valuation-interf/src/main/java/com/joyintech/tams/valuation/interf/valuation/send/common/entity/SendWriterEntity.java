/*
 * 文件名：SendWriterEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.common.entity;

import java.util.List;
import com.joyintech.framework.base.BaseEntity;

public class SendWriterEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 文件路径
     */
    private String path;
    
    /**
     * 数据实体List
     */
    private List<? extends Object> datas;
    
    /**
     * 模板英文名
     */
    private String tmpEName;

    
    /**
     * 获取 path
     * @return path.
     */
    public String getPath() {
        return path;
    }

    
    /**
     * 设置 path
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
    }

    
    /**
     * 获取 datas
     * @return datas.
     */
    public List<? extends Object> getDatas() {
        return datas;
    }

    
    /**
     * 设置 datas
     * @param datas datas
     */
    public void setDatas(List<? extends Object> datas) {
        this.datas = datas;
    }

    
    /**
     * 获取 tmpEName
     * @return tmpEName.
     */
    public String getTmpEName() {
        return tmpEName;
    }

    
    /**
     * 设置 tmpEName
     * @param tmpEName tmpEName
     */
    public void setTmpEName(String tmpEName) {
        this.tmpEName = tmpEName;
    }
    
    

}
