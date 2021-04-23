package com.joyintech.tams.exchange.terminal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KCBPRegisterConfig {

    @Value("${kcbp.client.address}")
    private String address;

    @Value("${kcbp.client.mac}")
    private String mac;

    @Value("${kcbp.client.cpuid}")
    private String cpuId;

    @Value("${kcbp.client.hdid}")
    private String hdId;

    @Value("${kcbp.client.computename}")
    private String computeName;

    @Value("${kcbp.client.guid}")
    private String guId;

    @Value("${kcbp.client.company}")
    private String company;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getCpuId() {
        return cpuId;
    }

    public void setCpuId(String cpuId) {
        this.cpuId = cpuId;
    }

    public String getHdId() {
        return hdId;
    }

    public void setHdId(String hdId) {
        this.hdId = hdId;
    }

    public String getComputeName() {
        return computeName;
    }

    public void setComputeName(String computeName) {
        this.computeName = computeName;
    }

    public String getGuId() {
        return guId;
    }

    public void setGuId(String guId) {
        this.guId = guId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return address + " " + mac + " CPU(" + cpuId + ")" + " HD(" + hdId + ")" +
                " Computer(" + computeName + ")" + " GUID(" + guId + ")" + " COMPANY(" + company + ")";
    }
}
