package com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;

@Repository
public interface GetPrdInfoSelectMapper {

	List<SelectOptionDto> getPrdInfoSelect(Map<String, Object> paraMap);
	
	List<SelectOptionDto> getCusInfoSelect(Map<String, Object> paraMap);

	List<SelectOptionDto>	getInfoSelect();

}
