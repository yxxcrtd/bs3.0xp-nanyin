package com.joyintech.service.risk.namelistmanage.assetnamelist.dto;

import com.joyintech.tams.common.base.entity.RskAccessListLog;

/**
 * 操作履历DTO
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see RskAccessListLogDto
 * @since 1.0
 */
public class RskAccessListLogDto extends RskAccessListLog {

    /**
     * 审批人
     */
    private String spr;

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }
    
    /**
     * 机构代码 add by songyawei
     */
    private String insitituteCode;
    
    public String getInsitituteCode() {
		return insitituteCode;
	}
	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}
}
