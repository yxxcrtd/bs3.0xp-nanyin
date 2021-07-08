package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSHVroleDto {

    @XmlElement(name = "vrole")
    private String vrole;

    @XmlElement(name = "refcode")
    private String refcode;

    public String getVrole() {
        return vrole;
    }

    public void setVrole(String vrole) {
        this.vrole = vrole;
    }

    public String getRefcode() {
        return refcode;
    }

    public void setRefcode(String refcode) {
        this.refcode = refcode;
    }
}
