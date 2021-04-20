package com.joyintech.tams.report.system.prorate.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.report.system.prorate.dao.sqlmap.ProRateInfo001Mapper;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProRateInfo001Service extends CommonExportService<ProRateInfo001Dto> {

    @Autowired
    private ProRateInfo001Mapper proRateInfo001Mapper;
}
