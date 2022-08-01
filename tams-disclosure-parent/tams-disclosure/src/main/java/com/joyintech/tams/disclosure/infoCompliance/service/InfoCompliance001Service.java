package com.joyintech.tams.disclosure.infoCompliance.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.disclosure.infoCompliance.dao.InfoComplianceMapper;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InfoCompliance001Service extends BaseService<InfoComplianceDto> {

    @Autowired
    private InfoComplianceMapper infoCompliance001Mapper;

    public Page<InfoComplianceDto> findPage(int page, int length, Map<String, Object> pageParam) {
        return super.queryForPageForSelectReturn(page, length, () -> infoCompliance001Mapper.findPage(pageParam));
    }
}
