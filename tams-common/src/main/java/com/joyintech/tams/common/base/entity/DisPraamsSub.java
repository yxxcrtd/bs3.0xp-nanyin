package com.joyintech.tams.common.base.entity;

public class DisPraamsSub extends DisPraamsSubKey {
    /**
     * 序号
     */
    private String disOrder;

    /**
     * 取得 序号
     * @return 序号
     */
    public String getDisOrder() {
        return disOrder;
    }

    /**
     * 设置 序号
     * @param disOrder 序号
     */
    public void setDisOrder(String disOrder) {
        this.disOrder = disOrder == null ? null : disOrder.trim();
    }
}