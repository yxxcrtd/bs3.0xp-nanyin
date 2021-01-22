package com.joyintech.tams.svreport.chbhz.inAstValSub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAstValSubTmp;
import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValueRegist001Dto;

@Repository
public interface InAstValInfoSvrMapper {
	/**
	 * 查询申报接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbAstValSubTmp>  genChbAstValSubTmpList(@Param("batchdate")String  batchdate);
    /**
     * 根据接口信息查询申报信息表
     * @return
     */
    List<ChbAstValueRegist001Dto>   genChbAstValSubList(@Param("keylist")List<String> keylist);
    /**
     * 查询所有已报送的数据
     * @return
     */
    List<ChbAstValueRegist001Dto>   genChbAstValSubAllList();
}
