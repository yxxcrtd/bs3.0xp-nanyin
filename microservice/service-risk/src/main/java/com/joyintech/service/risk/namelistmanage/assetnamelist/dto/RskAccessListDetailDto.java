package com.joyintech.service.risk.namelistmanage.assetnamelist.dto;

/**
 * 名单明细DTO
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see RskAccessListDetailDto
 * @since 1.0
 */

import com.joyintech.tams.common.base.entity.RskAccessListDetail;

public class RskAccessListDetailDto extends RskAccessListDetail {

    /**
     * 名单主体名称(旧)
     */
    private String oldlistDetailName;

    private String operatePs;

    /**
     * 审批人
     */
    private String spr;

    /**
     * 名单类型，白名单、黑名单
     */
    private String listType;

    /**
     * 名单级别，一级库、二级库
     */
    private String listLevel;
 
    public String getOldlistDetailName() {
        return oldlistDetailName;
    }

    public void setOldlistDetailName(String oldlistDetailName) {
        this.oldlistDetailName = oldlistDetailName;
    }

    public String getOperatePs() {
        return operatePs;
    }

    public void setOperatePs(String operatePs) {
        this.operatePs = operatePs;
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }
     
    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getListLevel() {
        return listLevel;
    }

    public void setListLevel(String listLevel) {
        this.listLevel = listLevel;
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
