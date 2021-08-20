package com.joyintech.service.bankuplink.reponew.dao;

import com.joyintech.background.component.uplink.interbank.business.dto.RepoQuoteExtDto;
import com.joyintech.background.component.uplink.interbank.business.dto.RepoQuoteSearchDto;
import com.joyintech.service.bankuplink.cstp.dto.CstpSelectDto;
import com.joyintech.service.bankuplink.reponew.dto.CfetsRepoDealQuotedPr001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenjiaxing
 * @version 1.0 2021年8月7日
 * @see CfetsRepoDealQuotedPr001Mapper
 * @since 1.0
 */
@Repository
public interface CfetsRepoDealQuotedPr001Mapper {
    /**
     * 委托指令查询
     *
     * @param pageParam 入参
     * @return 委托指令
     * @see
     * @since 1.0
     */
    List<CfetsRepoDealQuotedPr001Dto> queryZlzxList(Map<String, Object> pageParam);

    /**
     * 委托指令查询
     *
     * @param pageParam 入参
     * @return 委托指令
     * @see
     * @since 1.0
     */
    List<CfetsRepoDealQuotedPr001Dto> queryZyqglList(Map<String, Object> pageParam);

    /**
     * 质押式回购报价查询-指定条件
     * @param pageParam 入参
     * @return List<RepoQuoteExtDto>
     */
    List<RepoQuoteExtDto> selectRepoQuoteList(Map<String, Object> pageParam);

    /**
     * 分页查询结果
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<CstpSelectDto> getCstpQryTableList(Map<String,Object> map);
}
