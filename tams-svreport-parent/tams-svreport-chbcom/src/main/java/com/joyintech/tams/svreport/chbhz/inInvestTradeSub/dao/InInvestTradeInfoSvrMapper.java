package com.joyintech.tams.svreport.chbhz.inInvestTradeSub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbInvestTradeSubTmp;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;

@Repository
public interface InInvestTradeInfoSvrMapper {
	/**
	 * 查询申报接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbInvestTradeSubTmp>  genChbInvestTradeSubTmpList(@Param("batchdate")String  batchdate,@Param("prodIdList") List<String> prodIdList);
    /**
     * 根据接口信息查询申报信息表
     * @return
     */
    List<InvestTradeRegist001Dto>   genChbInvestTradeSubList(@Param("keylist")List<String> keylist);
    /**
     * 查询所有已报送的数据
     * @return
     */
    List<InvestTradeRegist001Dto>   genChbInvestTradeSubAllList();
    
    
  //  List<String> selectRegistCodeByid(@Param("prodIdList")List<String> prodIdList);
}
