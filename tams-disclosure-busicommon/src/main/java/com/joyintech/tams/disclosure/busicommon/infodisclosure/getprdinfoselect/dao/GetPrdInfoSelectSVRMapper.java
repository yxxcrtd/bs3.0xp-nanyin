package com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.dao;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GetPrdInfoSelectSVRMapper {

	List<SelectOptionDto> getPrdInfoSelect(Map<String, Object> paraMap);
}
