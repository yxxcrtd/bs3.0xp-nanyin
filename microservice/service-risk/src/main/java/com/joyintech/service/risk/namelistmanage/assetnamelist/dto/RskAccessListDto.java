package com.joyintech.service.risk.namelistmanage.assetnamelist.dto;

import com.joyintech.tams.common.base.entity.RskAccessList;
import com.joyintech.tams.common.base.entity.WflRskAccessList;
import com.joyintech.tams.common.base.entity.WflRskAccessListDetail;
import com.joyintech.tams.common.base.entity.WflRskAccessListLog;

import java.util.List;

/**
 * 名单dto
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see RskAccessListDto
 * @since 1.0
 */
public class RskAccessListDto extends RskAccessList {

    /**
     *名单明细
     */
    private List<RskAccessListDetailDto> rskAccessListDetailList;

    /**
     * 履历明细
     */
    private List<RskAccessListLogDto> rskAccessListLogDtoList;

    /**
        * 名单(业务流程表)
     * @return
         */
    private WflRskAccessList wflRskAccessList;

    /**
     * 名单明细(业务流程表)
     */
    private List<WflRskAccessListDetail> wflRskAccessListDetail;

    /**
     * 名单履历(业务流程表)
     * @return
     */
    private List<WflRskAccessListLog> wflRskAccessListLog;

    public List<RskAccessListDetailDto> getRskAccessListDetailList() {
        return rskAccessListDetailList;
    }

    public void setRskAccessListDetailList(List<RskAccessListDetailDto> rskAccessListDetailList) {
        this.rskAccessListDetailList = rskAccessListDetailList;
    }

    public List<RskAccessListLogDto> getRskAccessListLogDtoList() {
        return rskAccessListLogDtoList;
    }

    public void setRskAccessListLogDtoList(List<RskAccessListLogDto> rskAccessListLogDtoList) {
        this.rskAccessListLogDtoList = rskAccessListLogDtoList;
    }

    public WflRskAccessList getWflRskAccessList() {
        return wflRskAccessList;
    }

    public void setWflRskAccessList(WflRskAccessList wflRskAccessList) {
        this.wflRskAccessList = wflRskAccessList;
    }

    public List<WflRskAccessListDetail> getWflRskAccessListDetail() {
        return wflRskAccessListDetail;
    }

    public void setWflRskAccessListDetail(List<WflRskAccessListDetail> wflRskAccessListDetail) {
        this.wflRskAccessListDetail = wflRskAccessListDetail;
    }

    public List<WflRskAccessListLog> getWflRskAccessListLog() {
        return wflRskAccessListLog;
    }

    public void setWflRskAccessListLog(List<WflRskAccessListLog> wflRskAccessListLog) {
        this.wflRskAccessListLog = wflRskAccessListLog;
    }
}
