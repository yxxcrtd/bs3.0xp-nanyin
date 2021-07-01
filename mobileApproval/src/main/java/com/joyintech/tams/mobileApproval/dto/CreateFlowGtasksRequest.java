package com.joyintech.tams.mobileApproval.dto;

public class CreateFlowGtasksRequest extends RequestBase {

    //任务标识
    private String LINK_ID;

    //任务名称
    private String TITLE;

    //来源系统名
    private String SOURCE;

    //待办类型
    private String TYPE;

    //处理人信息
    private Owner[] OWNER_LIST;

    //是否提醒
    private int NEED_REMIND;

    //表单信息
    private String FORM_ITEM_LIST;

    //候选处理人信息
    private CandidateUser[] CANDIDATE_USER_LIST;


    public String getLINK_ID() {
        return LINK_ID;
    }

    public void setLINK_ID(String LINK_ID) {
        this.LINK_ID = LINK_ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public Owner[] getOWNER_LIST() {
        return OWNER_LIST;
    }

    public void setOWNER_LIST(Owner[] OWNER_LIST) {
        this.OWNER_LIST = OWNER_LIST;
    }

    public int getNEED_REMIND() {
        return NEED_REMIND;
    }

    public void setNEED_REMIND(int NEED_REMIND) {
        this.NEED_REMIND = NEED_REMIND;
    }

    public String getFORM_ITEM_LIST() {
        return FORM_ITEM_LIST;
    }

    public void setFORM_ITEM_LIST(String FORM_ITEM_LIST) {
        this.FORM_ITEM_LIST = FORM_ITEM_LIST;
    }

    public CandidateUser[] getCANDIDATE_USER_LIST() {
        return CANDIDATE_USER_LIST;
    }

    public void setCANDIDATE_USER_LIST(CandidateUser[] CANDIDATE_USER_LIST) {
        this.CANDIDATE_USER_LIST = CANDIDATE_USER_LIST;
    }
}
