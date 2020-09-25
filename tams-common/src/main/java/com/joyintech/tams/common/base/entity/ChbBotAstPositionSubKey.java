package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbBotAstPositionSubKey extends BaseEntity{
	  
	  /**
     * 登记序号
     */
    private String registSeqno;
    
    /**
     * 登记序号
     * @return REGIST_SEQNO 登记序号
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 登记序号
     * @param registSeqno 登记序号
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

}
