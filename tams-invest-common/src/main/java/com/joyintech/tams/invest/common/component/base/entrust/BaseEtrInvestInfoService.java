/*
 * 文件名：BaseEtrInvestInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.common.component.base.entrust;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.entrust.dto.BaseEtrFinishAmtDto;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.trade.entrust.EtrFinishStatusDto;

import java.util.List;
/**
 * 投资委托 基础组件
 * 〈一句话功能简述〉 
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年8月31日
 * @see BaseEtrInvestInfoService
 * @since 1.0
 */
public interface BaseEtrInvestInfoService {

    /**
     * 委托操作相关校验 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param entrustId String 委托代码
     * @param delegateOperationType String 委托操作类型
     * @param traderList List 交易员列表
     * @param delegateCloseWay String 委托关闭方式
     * @return List 
     * @see
     * @since 1.0
     */
    List<ValidMessage> checkDelegateOperation(String entrustId,
                                                     String delegateOperationType,
                                                     List<String> traderList,
                                                     String delegateCloseWay);
    
    /**
     * 查询已经生效的委托
     * @param entrustId String 
     * @return BaseEtrInvestInfoDto
     * @see
     * @since 1.0
     */
    BaseEtrInvestInfoDto queryActiveMandate(String entrustId);

    /**
     * 新建委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean newDelegate(BaseEtrInvestInfoDto dto);

    /**
     * 删除委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteDelegate(String entrustId);

    /**
     * 修改委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updateDelegate(BaseEtrInvestInfoDto dto);

    /**
     * 更新委托状态 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param entrustId String
     * @param entrustStatus String
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updateDelegateStatus(String entrustId, String entrustStatus);

    /**
     * 撤回委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean recallDelegate(BaseEtrInvestInfoDto dto);

    /**
     * 分发委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param list list
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean distributeDelegate(List<EtrDistribute> list);

    /**
     * 接收委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param list list
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean receptionDelegate(List<EtrDistribute> list);

    /**
     * 退回委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param list list
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean returnDelegate(List<EtrDistribute> list,String reason);

    /**
     * 关闭委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean closeDelegate(String entrustId,String closeWay);

    /**
     * 委托完成状况查询 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param entrustIdList List
     * @return List
     * @see
     * @since 1.0
     */
    List<BaseEtrFinishAmtDto> queryDelegateStatus(List<String> entrustIdList);

    /**
     * 完成委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param etrFinishStatusList List
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean completeDelegate(List<EtrFinishStatusDto> etrFinishStatusList);

    /**
     * 取消完成委托 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean cancelCompleteDelegate(String entrustId,String tradeId);

    boolean updateBackReason(String entrustId,String reason);

    BaseEtrFinishAmtDto  selectEtrFinishAmtDtoByPrimaryKey(String entrustId);
}
