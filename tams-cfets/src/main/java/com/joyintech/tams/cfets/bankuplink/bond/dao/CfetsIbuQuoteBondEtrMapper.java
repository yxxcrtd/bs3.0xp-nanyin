package com.joyintech.tams.cfets.bankuplink.bond.dao;

import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondDealEntrustOrderDto;
import com.joyintech.tams.common.base.entity.EtrDistributeInterfTmp;
import com.joyintech.tams.common.base.entity.EtrQuoteFinishStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 指令相关操作
 */
@Repository
public interface CfetsIbuQuoteBondEtrMapper {

    int checkDataStatus(Map<String, Object> map);



    List<EtrDistributeInterfTmp> listEtrDistributeInterfTmp(Map<String, Object> map);



    List<EtrQuoteFinishStatus> listEtrQuoteFinishStatus(Map<String, Object> param);
}
