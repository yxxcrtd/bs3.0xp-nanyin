package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "evoteparams")
public class XmlSZFileDto {

    @XmlElement(name = "company")
    private List<XmlSZCompanyDto> company;

    public List<XmlSZCompanyDto> getCompany() {
        return company;
    }

    public void setCompany(List<XmlSZCompanyDto> company) {
        this.company = company;
    }
}
