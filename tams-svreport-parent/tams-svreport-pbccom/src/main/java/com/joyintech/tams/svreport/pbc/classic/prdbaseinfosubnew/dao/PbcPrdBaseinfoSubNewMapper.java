package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dao;

import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcPrdBaseinfoSubNewMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcPrdBaseinfoSubNew record);

    int insertSelective(PbcPrdBaseinfoSubNew record);

    PbcPrdBaseinfoSubNew selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcPrdBaseinfoSubNew record);

    int updateByPrimaryKey(PbcPrdBaseinfoSubNew record);

    List<PbcPrdBaseinfoSubNew> selectNeedRegisteData(PbcPrdBaseinfoSubNew qmodel);

    List<PbcPrdBaseinfoSubNew> selectAlReadyRegisteData(@Param("reglist") List<PbcPrdBaseinfoSubNew> reglist);

    List<PbcPrdBaseinfoSubNew> selectAllRegisteData(@Param("reglist") List<PbcPrdBaseinfoSubNew> reglist);

    void deleteByProdid(@Param("deleteList") List<PbcPrdBaseinfoSubNew> subList);

    List<PbcPrdBaseinfoSubNew> searchPage(Map<String, Object> searchParam);

    List<PbcPrdBaseinfoSubDat> searchDat(Map<String, Object> searchParam);

    int cancelManul(List<PbcPrdBaseinfoSubNew> updateList);

    int doManul(@Param("list") List<PbcPrdBaseinfoSubNew> updateList, @Param("submitadd") String submitadd);

    int insetBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoSubNew> insertList);

    int deleteBatchSubFromHisByUpdateTime(List<PbcPrdBaseinfoSubNew> insertList);

    int insertHisBatchBySendStatus(List<PbcPrdBaseinfoSubNew> insertList);

    void deleteBatchBySendStatus(List<PbcPrdBaseinfoSubNew> deleteList);

    int updateZipDatStatus(@Param("updateStatus") String updateStatus, @Param("sendStatus") String sendStatus);

    int updateZipDatStatusUn(@Param("updateStatus") String updateStatus, @Param("sendStatus") String sendStatus);

    int updateBaseSubDat(@Param("list") List<PbcPrdBaseinfoSubNew> updateList);


    int insertBaseSubDatHis(@Param("list") List<PbcPrdBaseinfoSubNew> updateList);

    int deleteBaseSubDatHis(@Param("list") List<PbcPrdBaseinfoSubNew> updateList);

    /***根据中债编码获取人行编码**/
    String selectPbcBankCodeName(String orgCode);

    /**
     * 查询所有不是删除状态的人行产品信息
     *
     * @param param
     * @return 所有不是删除状态的人行产品信息
     */
    List<PbcPrdBaseinfoSubNew> queryAllPbcPrdBaseInfo(Map<String, Object> param);

    List<PbcPrdAccountSub> selectAllRegisteAccountData(@Param("reglist") List<PbcPrdBaseinfoSubNew> reglist);

    List<String> selectRegistSeqnoList(@Param("list") List<PbcPrdBaseinfoSubNew> registList, @Param("flag") String flag);

    int updateByProdIds(@Param("list")List<String> list);
}