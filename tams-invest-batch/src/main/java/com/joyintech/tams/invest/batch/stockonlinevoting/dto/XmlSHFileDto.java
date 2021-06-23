package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tls")
public class XmlSHFileDto {

    @XmlElement(name = "rootvote")
    private String rootvote;

    @XmlElement(name = "company")
    private List<XmlSHCompanyDto> company;

    public String getRootvote() {
        return rootvote;
    }

    public void setRootvote(String rootvote) {
        this.rootvote = rootvote;
    }

    public List<XmlSHCompanyDto> getCompany() {
        return company;
    }

    public void setCompany(List<XmlSHCompanyDto> company) {
        this.company = company;
    }
}
