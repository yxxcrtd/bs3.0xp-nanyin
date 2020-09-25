package com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.dao.FtpPathMapper;

@Service("disclosureFtpPathService")
public class FtpPathService {
	
	@Autowired
	private FtpPathMapper ftpPathMapper;
	
	public List<SelectOptionDto> getReportCategory(){
		List<RepFtpPath> repFtpPathList = ftpPathMapper.getReportCategory();
		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
		SelectOptionDto dto = new SelectOptionDto();
        for (RepFtpPath signle : repFtpPathList) {
        	dto = new SelectOptionDto();
        	dto.setId(signle.getId());
        	dto.setText(signle.getName());
        	listSelectDto.add(dto);
		}
    	return listSelectDto;
	}
}
