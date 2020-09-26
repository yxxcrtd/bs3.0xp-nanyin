package com.joyintech.tams.common.svreport.seq.dto;

import java.util.Date;

public class SysSeqModel {
	private String seqdate;

    private String seqcode;
	
    private Long seqvalue;

    private Object remark;

    public String getSeqdate() {
        return seqdate;
    }
    
    public void setSeqdate(String seqdate) {
        this.seqdate = seqdate;
    }

    public String getSeqcode() {
        return seqcode;
    }
    
    public void setSeqcode(String seqcode) {
        this.seqcode = seqcode;
    }
    
    public Long getSeqvalue() {
        return seqvalue;
    }
    
    public void setSeqvalue(Long seqvalue) {
        this.seqvalue = seqvalue;
    }
    
    public Object getRemark() {
        return remark;
    }
    
    public void setRemark(Object remark) {
        this.remark = remark;
    }

}