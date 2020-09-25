package com.joyintech.tams.common.base.entity;

public class RepInvassetkindDtoKey {
    /**
     * 产品编号
     */
    private String prodId;

    /**
     * 编号(投资种类)
     */
    private String code;

    /**
     * 产品编号
     * @return PROD_ID 产品编号
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 产品编号
     * @param prodId 产品编号
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 编号(投资种类)
     * @return CODE 编号(投资种类)
     */
    public String getCode() {
        return code;
    }

    /**
     * 编号(投资种类)
     * @param code 编号(投资种类)
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}