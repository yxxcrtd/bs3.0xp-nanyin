package com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao;

import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.UploadFileDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface UploadattAchment001Mapper {
    List<UploadFileDto> getFileByBusinessCode(Map<String,String> param);

    void updateUplodFile(UploadFileDto dto);
}
