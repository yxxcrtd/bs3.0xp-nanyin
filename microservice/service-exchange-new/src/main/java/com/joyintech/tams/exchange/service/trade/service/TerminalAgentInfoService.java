package com.joyintech.tams.exchange.service.trade.service;

import com.joyintech.tams.exchange.service.trade.dao.domain.TerminalAgentInfo;
import com.joyintech.tams.exchange.service.trade.dao.domain.TerminalAgentInfoExample;

import java.util.List;

/**
 * @author lifangpeng
 * @date 2021/10/12
 */
public interface TerminalAgentInfoService {
    /**
     * 查询接口配置信息
     *
     * @param example 查询条件
     * @return 接口配置信息
     */
    List<TerminalAgentInfo> selectByExample(TerminalAgentInfoExample example);

    /**
     * 根据终端和请求码查询接口配置信息
     *
     * @param agentType    终端
     * @param functionCode 请求码
     * @param entrustProp  委托属性
     * @param quoteType    行情类型
     * @param market       市场
     * @return 接口配置信息
     */
    List<TerminalAgentInfo> select(String agentType, Integer functionCode, String entrustProp,
                                   Integer quoteType, String market);

    /**
     * 根据终端和请求码查询接口配置信息
     *
     * @param agentType    终端
     * @param functionCode 请求码
     * @return 接口配置信息
     */
    List<TerminalAgentInfo> select(String agentType, Integer functionCode);

    /**
     * 根据终端和请求码查询接口配置信息
     *
     * @param agentType    终端
     * @param functionCode 请求码
     * @return 接口配置信息
     */
    TerminalAgentInfo selectOne(String agentType, Integer functionCode);

    /**
     * 根据终端和请求码查询接口配置信息
     *
     * @param agentType    终端
     * @param functionCode 请求码
     * @param entrustProp  委托属性
     * @return 接口配置信息
     */
    TerminalAgentInfo selectOne(String agentType, Integer functionCode, String entrustProp);

    /**
     * 根据终端和请求码查询接口配置信息
     *
     * @param agentType    终端
     * @param functionCode 请求码
     * @param quoteType    行情类型
     * @param market       市场
     * @return 接口配置信息
     */
    TerminalAgentInfo selectOne(String agentType, Integer functionCode, Integer quoteType, String market);
}
