/*
 * 文件名：TrdSecLendDurationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdsecoverdueduration.dao;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 债券借贷存续期管理
 *
 * @author 裴家俊
 * @version 1.0 2019年7月1日
 * @see TrdSecOverdueDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdSecOverdueDurationMapper {

    List<TrdSecOverdueDurationDto> theoryFindPage(Map<String, Object> param);

    List<TrdSecOverdueDurationDto> realityFindPage(Map<String, Object> param);

    List<TrdSecOverdueDurationDto> findRecePositionDetail(Map<String, Object> param);

    List<TrdSecOverdueDurationDto> findIntertstDetail(Map<String, Object> param);

    TrdSecOverdueDurationDto selectJxwuFromFinprodId(Map<String, Object> param);

    TrdSecOverdueDurationDto selectCashIdsByBranchAll(Map<String, Object> param);

    TrdSecOverdueDurationDto selectWithOutBranchByLiBranch(Map<String, Object> param);

    List<TrdSecOverdueDurationDto> findRecePositionDetailByPortfolioId(Map<String, Object> param);

	TrdSecOverdueDurationDto selectNewBasicdata(Map<String, Object> param);

	List<FinAssetIntInfo> selectNewInterestDetails(Map<String, Object> param);

    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);


    TrdSecOverdueDurationDto findTFEffData(Map<String, Object> param);

    List<SelectOptionDto> selectPortfolioId(Map<String, Object> param);

    int isSecManage(Map<String, Object> param);

	TrdSecOverdueDurationDto findSecOverdueData(Map<String, Object> param);


    int checkCounterFlowDeal(Map<String, Object> param);
}
