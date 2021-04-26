package com.joyintech.tams.settle.batch.datadown.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * @author 何阳阳
 * @date 2020/7/2
 * @see DataToWriteEntity
 * @since 1.0
 */
public class DataToWriteEntity extends NeedToWriteEntity {

    private Object entity;

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
