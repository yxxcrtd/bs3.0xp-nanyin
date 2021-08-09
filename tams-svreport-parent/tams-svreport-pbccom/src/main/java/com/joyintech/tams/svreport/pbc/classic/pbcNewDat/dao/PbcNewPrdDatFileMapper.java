package com.joyintech.tams.svreport.pbc.classic.pbcNewDat.dao;

import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFileDetail;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dto.PbcPrdBaseinfoEndSubNew;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PbcNewPrdDatFileMapper {
    String getMaxZipCode(Map<String,Object> param);

    void insertBatchZipData(@Param("dataList") List<PbcSendZipDatFile> dataList);

    /**
     * 根据理财产品代码删除
     * @param list
     * @return
     */
    int deletePrdBatchByProdId(@Param("deleteList") List<String> list);


    /**
     * 根据理财产品代码删除
     * @param list
     * @return
     */
    int deleteEndBatchByProdId(@Param("deleteList") List<String> list);

    int updateZipSendCount(@Param("OrgCode") String OrgCode,@Param("List") List<String> list);

    int deleteZipData(@Param("OrgCode") String OrgCode);

}