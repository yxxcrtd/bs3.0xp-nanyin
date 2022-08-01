package com.joyintech.tams.disclosure.infoCompliance.dao;

import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceDto;
import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InfoComplianceMapper {

    List<InfoComplianceDto> findPage(Map<String, Object> pageParam);

    void saveInfoCompliance(InfoComplianceDto dto);

    InfoComplianceDto queryInfoCompliance(Map<String,String> paramMap);

    void deleteInfoCompliance(Map<String,String> paramMap);

    void updateInfoCompliance(InfoComplianceDto dto);

    String selectProcInstIid(@Param("seqNo") String seqNo);

    void updateUplodFile(UploadFileDto dto);

    //根据文件类型和fileType查询文件
    List<UploadFileDto> getFileByBusinessCode(@Param("businessId")String businessId,@Param("fileType")String fileType);
}
