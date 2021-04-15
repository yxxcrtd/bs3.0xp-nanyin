package com.joyintech.service.basicdata.mstseatinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstseatinfo.dto.MstSeatInfoDto;

/**
 * 
 * @author lijie2
 *
 */
@Repository
public interface MstSeatInfoMapper {

	int deleteByPrimaryKey(String seatId);

	int insert(MstSeatInfoDto mstSeatInfo);

	int insertSelective(MstSeatInfoDto mstSeatInfo);

	int updateByPrimaryKeySelective(MstSeatInfoDto mstSeatInfo);

	int updateByPrimaryKey(MstSeatInfoDto mstSeatInfo);

	MstSeatInfoDto selectBySeatId(String seatId);

	List<MstSeatInfoDto> findPage(Map<String, Object> param);

	List<SuggestDto> fuzzySearchSeatId(Map<String, String> map);
	
	List<SuggestDto> selectCounterSeat(Map<String, String> map);
}