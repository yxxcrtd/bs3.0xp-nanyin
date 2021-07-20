package com.joyintech.tams.invest.batch.stockonlinevoting.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.StMeetingInfoMapper;
import com.joyintech.tams.common.base.dao.StMeetingProposalInfoMapper;
import com.joyintech.tams.common.base.dao.StMeetingProposalVoteinfoMapper;
import com.joyintech.tams.common.base.entity.StMeetingInfo;
import com.joyintech.tams.common.base.entity.StMeetingProposalInfo;
import com.joyintech.tams.common.base.entity.StMeetingProposalVoteinfo;
import com.joyintech.tams.invest.batch.stockonlinevoting.dao.StockOnlineVotingMapper;
import com.joyintech.tams.invest.batch.stockonlinevoting.dto.StockOnlineVotingResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 股票网络投票基础数据导入 Writer
 */
@Service
@Scope("prototype")
public class StockOnlineVotingWriter extends BaseWriter<NeedToWriteEntity> {
    @Autowired
    private StMeetingInfoMapper stMeetingInfoMapper;

    @Autowired
    private StMeetingProposalInfoMapper stMeetingProposalInfoMapper;

    @Autowired
    private StMeetingProposalVoteinfoMapper stMeetingProposalVoteinfoMapper;

    @Autowired
    private StockOnlineVotingMapper stockOnlineVotingMapper;

    @Override
    public void write(List<? extends NeedToWriteEntity> list) throws Exception {
        List<StockOnlineVotingResultDto> stockOnlineVotingResultDtoList = (List<StockOnlineVotingResultDto>) list;

        if(!StringUtil.isEmptyList(stockOnlineVotingResultDtoList)) {
            for (StockOnlineVotingResultDto dto : stockOnlineVotingResultDtoList) {
                if(!StringUtil.isEmptyList(dto.getStMeetingInfoList())) {
                    for(StMeetingInfo stMeetingInfo:dto.getStMeetingInfoList()) {
                        //先删除
                        stMeetingInfoMapper.deleteByPrimaryKey(stMeetingInfo.getMeetingseq());
                        //入库
                        stMeetingInfoMapper.insertSelective(stMeetingInfo);

                        Map<String,Object> map = new HashMap<>();
                        map.put("meetingseq",stMeetingInfo.getMeetingseq());
                        //先批量删除
                        stockOnlineVotingMapper.deleteBatchStMeetingProposalVoteinfo(map);
                        stockOnlineVotingMapper.deleteBatchStMeetingProposalInfo(map);

                        if(!StringUtil.isEmptyList(dto.getStMeetingProposalInfoList())) {
                            for (StMeetingProposalInfo stMeetingProposalInfo : dto.getStMeetingProposalInfoList()) {
                                //入库
                                if(StringUtil.equals(stMeetingInfo.getMeetingseq(),stMeetingProposalInfo.getMeetingseq())) {
                                    stMeetingProposalInfoMapper.insertSelective(stMeetingProposalInfo);
                                }
                            }
                        }

                        if(!StringUtil.isEmptyList(dto.getStMeetingProposalVoteinfoList())) {
                            for(StMeetingProposalVoteinfo stMeetingProposalVoteinfo : dto.getStMeetingProposalVoteinfoList()) {
                                //入库
                                if(StringUtil.equals(stMeetingInfo.getMeetingseq(),stMeetingProposalVoteinfo.getMeetingseq())) {
                                    stMeetingProposalVoteinfoMapper.insertSelective(stMeetingProposalVoteinfo);
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
