package com.joyintech.tams.exchange.service.trade.dao.mapper;

import com.joyintech.tams.exchange.datacommon.db.dao.IMybatisMapper;
import com.joyintech.tams.exchange.service.trade.dao.domain.TerminalAgentInfo;
import com.joyintech.tams.exchange.service.trade.dao.domain.TerminalAgentInfoExample;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lifangpeng
 * @date 2021/10/12
 */
@Repository
public interface TerminalAgentInfoMapper extends IMybatisMapper<TerminalAgentInfo, String, TerminalAgentInfoExample> {
    /**
     * 查询接口配置信息
     *
     * @param example 查询条件
     * @return 接口配置信息
     */
    @Override
    List<TerminalAgentInfo> selectByExample(TerminalAgentInfoExample example);

}
