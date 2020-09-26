package com.joyintech.tams.svreport.pbc.classic.data.dao;

import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDatfileDetailRelation;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDatfileDetailRelationKey;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface PbcDatfileDetailRelationMapper {
    int deleteByPrimaryKey(PbcDatfileDetailRelationKey key);

    int insert(PbcDatfileDetailRelation record);

    int insertSelective(PbcDatfileDetailRelation record);

    int insertBasicByBasicInfo(PbcDatfileDetailRelation record);

    PbcDatfileDetailRelation selectByPrimaryKey(PbcDatfileDetailRelationKey key);

    int updateByPrimaryKeySelective(PbcDatfileDetailRelation record);

    int updateByPrimaryKey(PbcDatfileDetailRelation record);

	int insertList(List<PbcDatfileDetailRelation> list);

	List<PbcDatfileDetailRelation> selectUnSendDatafileAfterYesterday(Map<String, Object> map);
	
	int deleteByDetailid(PbcDatfileDetailRelation record);
	
	List<PbcDatfileDetailRelation> selectRelationByFileid(Map<String, Object> map);

	List<PbcDatfileDetailRelation> selectAllDatafileAfterYesterday(Map<String, Object> searchParam);

	List<PbcDatfileDetailRelation> selectRelationByDetailids(Map<String, Object> searchParam);

	List<PbcDatfileDetailRelation> selectSendRelationByFileid(Map<String, Object> searchparam);
}