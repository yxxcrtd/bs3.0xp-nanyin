package com.joyintech.tams.svreport.pbc.common.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatDownloadDto {

    private String url;

    private List<ValidMessage> validMessages;

    private Map<String,String> innerProdIdMapping;

    private List<PbcPrdBaseinfoSubDat> pbcPrdBaseinfoSubDats;

    private List<PbcPrdBaseinfoEndSubDat> pbcPrdBaseinfoEndSubDats;

    private List<String> prodIds;

    public List<String> getProdIds() {
        return prodIds;
    }

    public void setProdIds(List<String> prodIds) {
        this.prodIds = prodIds;
    }

    public Map<String, String> getInnerProdIdMapping() {
        return innerProdIdMapping;
    }

    public void setInnerProdIdMapping(Map<String, String> innerProdIdMapping) {
        this.innerProdIdMapping = innerProdIdMapping;
    }

    public List<PbcPrdBaseinfoSubDat> getPbcPrdBaseinfoSubDats() {
        return pbcPrdBaseinfoSubDats;
    }

    public void setPbcPrdBaseinfoSubDats(List<PbcPrdBaseinfoSubDat> pbcPrdBaseinfoSubDats) {
        this.pbcPrdBaseinfoSubDats = pbcPrdBaseinfoSubDats;
    }

    public List<PbcPrdBaseinfoEndSubDat> getPbcPrdBaseinfoEndSubDats() {
        return pbcPrdBaseinfoEndSubDats;
    }

    public void setPbcPrdBaseinfoEndSubDats(List<PbcPrdBaseinfoEndSubDat> pbcPrdBaseinfoEndSubDats) {
        this.pbcPrdBaseinfoEndSubDats = pbcPrdBaseinfoEndSubDats;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ValidMessage> getValidMessages() {
        return validMessages;
    }

    public void setValidMessages(List<ValidMessage> validMessages) {
        this.validMessages = validMessages;
    }

    public static void newErrorMsg(DatDownloadDto datDownloadDto,String... msg){
        List<ValidMessage> validMessages = new ArrayList<>();
        for (String s : msg) {
            ValidMessage message = new ValidMessage();
            message.setMessage(s+"<br>");
            validMessages.add(message);
        }
        List<ValidMessage> validMessages1 = datDownloadDto.getValidMessages();
        if(CollectionUtil.isNotEmpty(validMessages1)){
            validMessages1.addAll(validMessages);
            datDownloadDto.setValidMessages(validMessages1);
        }else{
            datDownloadDto.setValidMessages(validMessages);
        }
    }
}
