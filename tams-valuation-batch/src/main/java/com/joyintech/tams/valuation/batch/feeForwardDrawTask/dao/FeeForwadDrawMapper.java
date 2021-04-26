package com.joyintech.tams.valuation.batch.feeForwardDrawTask.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.TrdAccountmfConfig;
import com.joyintech.tams.valuation.batch.feeForwardDrawTask.dto.FeeForwadDrawConfigDto;
import org.springframework.stereotype.Repository;

/**
 * 账户维护费每日计提 Dao
 * @author hpp
 *
 */
@Repository
public interface FeeForwadDrawMapper {

	List<FeeForwadDrawConfigDto> getFeeForwadDrawInfo(Map<String, Object> param);
}
