package com.joyintech.tams.svreport.chb.investtradereg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.investtradereg.dto.ChbInvestTradeSubHisDto;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;

@Repository
public interface ChbInvestTradeSubHisMapper {
    int deleteByPrimaryKey(String hisNo);

    int insert(ChbInvestTradeSubHisDto record);

    int insertSelective(ChbInvestTradeSubHisDto record);

    ChbInvestTradeSubHisDto selectByPrimaryKey(String hisNo);

    int updateByPrimaryKeySelective(ChbInvestTradeSubHisDto record);

    int updateByPrimaryKey(ChbInvestTradeSubHisDto record);
    /**
     * 功能:根据交易编号查询投资交易登记表数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月20日
     * @param assetList
     * @return 
     * List<InvestTradeRegist001Dto>
     */
    List<InvestTradeRegist001Dto> selectInvestTradeRegByTradeCode(@Param("tradeList")List<String> tradeList);
    
    /**
     * 功能:根据履历序号删除的投资交易履历信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月20日
     * @param deleteList 删除的数据
     * @param sendStatusList 报送状态
     * @return 
     * int
     */
    int deleteByHisno(@Param("deleteList")List<String> deleteList);

}