package com.joyintech.tams.disclosure.information.config.repprodinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface DisProdBaseInfoMapper  {

    int deleteByPrimaryKey(String prodId);

    int insert(ProdInfoDto record);

    int insertSelective(ProdInfoDto record);

    int insertBasicByBasicInfo(ProdInfoDto record);

    ProdInfoDto selectByPrimaryKey(String prodId);

    ProdInfoDto selectData(Map<String, Object> param);
    ProdInfoDto selectByPrimaryKeyPT(String prodId);

    int updateByPrimaryKeySelective(ProdInfoDto record);

    int updateByPrimaryKey(ProdInfoDto record);
    int updateStatus(ProdInfoDto record);

    List<ProdInfoDto> searchProdList(Map<String, Object> params);

    ProdInfoDto selectBySonProdId(String prodId);


    ProdInfoDto selectByCommonProdId(String prodId);


    ProdInfoDto selectByProdStatus(@Param("prodId") String prodId,@Param("status") String status);

    void updateIssuingStatus(ProdInfoDto dto);

    List<ProdInfoDto> queryForList(String[] split);

    List<ProdInfoDto> selectListByPprodId(@Param("prodId") String prodId);

    int updateRaiseAmtByPprodId(ProdInfoDto record);

    List<SelectOptionDto> queryPprodIds(Map<String, Object> paramMap);

    List<SelectOptionDto> queryPprodName(Map<String, Object> paramMap);


    List<SelectOptionDto> queryPprodName1(Map<String, Object> paramMap);

    /**
     * 根据产品主代码查询母产品基本信息
     * @param prodId
     * @return
     */
    ProdInfoDto selectParentProdInfo(String prodId);

    List<ProdInfoDto> selectProdList(@Param("list") List<ProdInfoDto> list);

    List<ProdInfoDto> getPtParentProdInfo(@Param("pProdId") String pProdId);

    int hasReapetData(ProdInfoDto dto);

    /**
     * 查询所有产品登记编码
     * @return
     */
    List<ProdInfoDto>  selectReAll();

    int saveHisData(ProdInfoDto dto);

    ProdInfoDto selectHisData(String workflowId);

    ProdInfoDto selectCfsj(String prodId);
}
