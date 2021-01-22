package com.joyintech.tams.svreport.chbhz.inPrdDeclareSub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdDeclareSubTmp;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;

@Repository
public interface InprodDeclareInfoSvrMapper {
	
	/**
	 * 查询申报接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbPrdDeclareSubTmp>  genChbPrdDeclareSubTmpList(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
    /**
     * 根据接口信息查询申报信息表
     * @return
     */
    List<PerProDeclareRegDto>   genChbPrdDeclareSubList(@Param("keylist")List<String> keylist);
    /**
     * 查询所有已报送的数据
     * @return
     */
    List<PerProDeclareRegDto>   genChbPrdDeclareSubAllList();
    
    /**
     * 不同产品之间，产品名称与产品期次不得同时重复
     */
    
    List<ChbPrdDeclareSubTmp>  genChbPrdDeclareSubTmp1ByProdInsId(@Param("prodInsId")String  prodInsId);

    /**
     * 不同产品之间，产品名称与产品期次不得同时重复
     */
    
    List<ChbPrdDeclareSubTmp>  genChbPrdDeclareSubTmp2ByProdInsId(@Param("prodInsId")String  prodInsId);

}