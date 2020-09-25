package com.joyintech.tams.mobileApproval.dto;

/**
 * esb报文头处理人信息类
 * add by houjifei
 */
public class Owner {

    //处理人员工号
    private String OWNER_ID;

    //处理人类型
    private String OWNER_TYPE;

    public String getOWNER_ID() {
        return OWNER_ID;
    }

    public void setOWNER_ID(String OWNER_ID) {
        this.OWNER_ID = OWNER_ID;
    }

    public String getOWNER_TYPE() {
        return OWNER_TYPE;
    }

    public void setOWNER_TYPE(String OWNER_TYPE) {
        this.OWNER_TYPE = OWNER_TYPE;
    }
}
