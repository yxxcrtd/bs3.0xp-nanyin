package com.joyintech.tams.svreport.pbc.classic.pbcNewDat.dao;

import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFileDetail;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dto.PbcPrdBaseinfoEndSubNew;

import java.util.List;
import java.util.Map;

public interface PbcNewPrdDatFileMapper {
    String getMaxZipCode(Map<String,String> param);

    List<PbcPrdBaseinfoEndSubNew> getPrdEndList(String sendStatus);

}