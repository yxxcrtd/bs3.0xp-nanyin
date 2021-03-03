package com.joyintech.tams.svreport.chbhz.inPrdRaisetotSub.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdRaisetotSubTmp;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.RaiseTotalRegist001Dto;

@Repository
public interface InprodRaisetotInfoSvrMapper {
	
	/**
	 * 查询申报接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbPrdRaisetotSubTmp>  genChbPrdRaisetotSubTmpList(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
    /**
     * 根据接口信息查询申报信息表
     * @return
     */
    List<RaiseTotalRegist001Dto>   genChbPrdRaisetotSubList(@Param("keylist")List<String> keylist);
    /**
     * 查询所有已报送的数据
     * @return
     */
    List<RaiseTotalRegist001Dto>   genChbPrdRaisetotSubAllList();
    
    
    List<String> selectRegistCodeByid(@Param("prodIdList")List<String> prodIdList);
    
    Date selectRaiseEnddateByRegistCode(@Param("prodRegistCode")String prodRegistCode);
}
