package com.joyintech.tams.invest.batch.stockonlinevoting.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface StockOnlineVotingMapper {


    /**
     * 批量删除 股东大会议案信息
     * @param map
     * @return
     */
    int deleteBatchStMeetingProposalInfo(Map<String,Object> map);

    /**
     * 批量删除 股东大会议案投票信息
     * @param map
     * @return
     */
    int deleteBatchStMeetingProposalVoteinfo(Map<String,Object> map);


//    /**
//     * 获取资产代码
//     * @param refcode
//     * @return
//     */
//    String selectFinprodId(String refcode);
}
