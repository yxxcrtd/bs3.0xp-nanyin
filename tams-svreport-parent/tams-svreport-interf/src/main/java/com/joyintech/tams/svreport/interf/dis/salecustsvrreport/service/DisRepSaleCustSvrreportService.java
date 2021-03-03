package com.joyintech.tams.svreport.interf.dis.salecustsvrreport.service;

import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DisRepSaleCustSvrreportService extends FileImpService {
    @Override
    public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
        return null;
    }

    @Override
    public String importDB(List<Map<String, String>> listData, InterfFile interfFile) {
        return null;
    }
}
