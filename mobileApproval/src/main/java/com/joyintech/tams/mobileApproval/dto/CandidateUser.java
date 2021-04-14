package com.joyintech.tams.mobileApproval.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * esb报文头候选处理人信息类
 * add by houjifei
 */
public class CandidateUser extends BaseEntity {

    //候选处理人员工号
    private String USER_ID;

    //候选处理人类型
    private String USER_TYPE;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public void setUSER_TYPE(String USER_TYPE) {
        this.USER_TYPE = USER_TYPE;
    }
}
