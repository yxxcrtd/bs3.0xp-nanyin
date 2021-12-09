package com.joyintech.tams.business.common.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.business.common.dto.CashPositionFlowDto;
import com.joyintech.tams.business.common.dto.CfetsOprHisInfoRequest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 公共方法mapper
 */
@Repository
public interface JoyinbusinessCommonMapper {

    /**
     * 根据条件查询证券账户
     *
     * @param param
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getSecManageAcctId(Map<String, Object> param);

    /**
     * 委托代码的联想文本框的查询方法
     *
     * @param param
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getEntrustId(Map<String, Object> param);
    /**
     *
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param);
    /**
     *
     * 获取新建界面的组合联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> selectPortfolioIdList(Map<String, Object> param);
    /**
     * 获取投资经理
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getManagers(Map<String, Object> param);
    /**
     *
     * 获取新建界面的行权债资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRightfinprodIdForAdd(Map<String, Object> param);

    /**
     * 更新操作记录
     * @param record
     * @return
     */
    int insertCfetsOprHis(CfetsOprHisInfoRequest record);
    /**
     * 查询本方机构列表-银行间
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryMySideList(Map<String, Object> param);
    /**
     * 查询本方机构列表,且关联交易对手-银行间
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryMySideAndPartyList(Map<String, Object> param);
    /**
     * 查询本方机构列表-银行间
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryCstpPartyList(Map<String, Object> param);
    /**
     * 查询本方机构列表
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryCounterPartyList(Map<String, Object> param);
    /**
     * 查询本方交易员列表
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryMySideTraderList(Map<String, Object> param);

    BigDecimal selectCommandAvailableCash(CashPositionFlowDto dto);

    /**
     * 查询手工调整流水数据
     * @param dto
     * @return
     */
    BigDecimal selectManualCashFlow(CashPositionFlowDto dto);
}
