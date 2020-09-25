package com.joyintech.tams.common.base.entity;

public class BokSchemeSubjectConfig extends BokSchemeSubjectConfigKey {
    /**
     * null
     */
    private String othersideSubjectName;

    /**
     * null
     */
    private String isGenSubs;

    /**
     * null
     */
    private String blockType;

    /**
     * 取得 null
     * @return null
     */
    public String getOthersideSubjectName() {
        return othersideSubjectName;
    }

    /**
     * 设置 null
     * @param othersideSubjectName null
     */
    public void setOthersideSubjectName(String othersideSubjectName) {
        this.othersideSubjectName = othersideSubjectName == null ? null : othersideSubjectName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsGenSubs() {
        return isGenSubs;
    }

    /**
     * 设置 null
     * @param isGenSubs null
     */
    public void setIsGenSubs(String isGenSubs) {
        this.isGenSubs = isGenSubs == null ? null : isGenSubs.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getBlockType() {
        return blockType;
    }

    /**
     * 设置 null
     * @param blockType null
     */
    public void setBlockType(String blockType) {
        this.blockType = blockType == null ? null : blockType.trim();
    }
}