package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbBotAstPositionKey extends BaseEntity{
    /**
     * 主键
     */
    private String posId;
    
    /**
     * 主键
     * @return POS_ID 主键
     */
    public String getPosId() {
        return posId;
    }

    /**
     * 主键
     * @param posId 主键
     */
    public void setPosId(String posId) {
        this.posId = posId == null ? null : posId.trim();
    }


}
