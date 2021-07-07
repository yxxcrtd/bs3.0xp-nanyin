package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSZVroleDto {
    
    @XmlElement(name = "ShareHolderRole")
    private String shareHolderRole;

    @XmlElement(name = "refcode")
    private String refcode;


    public String getShareHolderRole() {
        return shareHolderRole;
    }

    public void setShareHolderRole(String shareHolderRole) {
        this.shareHolderRole = shareHolderRole;
    }

    public String getRefcode() {
        return refcode;
    }

    public void setRefcode(String refcode) {
        this.refcode = refcode;
    }
}
