package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskAccessListLog;
import com.joyintech.tams.constant.DictCode;

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

    /**
     * 名单操作方式String
     * @return
     */
    public String getOperateTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.名单操作方式, this.getOperateType());
    }

    /**
     * 名单操作方向String
     * @return
     */
    public String getOperatePsStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.名单操作方向, this.getOperatePs());
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }

    public String getListDateStr(){
        return DateUtil.format(this.getListDate());
    }
}
