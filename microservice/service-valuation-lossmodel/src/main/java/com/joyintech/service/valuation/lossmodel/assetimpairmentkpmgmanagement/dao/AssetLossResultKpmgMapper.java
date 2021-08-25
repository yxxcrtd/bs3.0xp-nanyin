package com.joyintech.service.valuation.lossmodel.assetimpairmentkpmgmanagement.dao;

import com.joyintech.service.valuation.lossmodel.assetimpairmentkpmgmanagement.dto.AssetLossResultKpmg;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetLossResultKpmgMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(AssetLossResultKpmg record);

    int insertSelective(AssetLossResultKpmg record);

    int insertBasicByBasicInfo(AssetLossResultKpmg record);

    AssetLossResultKpmg selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(AssetLossResultKpmg record);

    int updateByPrimaryKey(AssetLossResultKpmg record);
    String getFirstActionType(String workflowId);
}