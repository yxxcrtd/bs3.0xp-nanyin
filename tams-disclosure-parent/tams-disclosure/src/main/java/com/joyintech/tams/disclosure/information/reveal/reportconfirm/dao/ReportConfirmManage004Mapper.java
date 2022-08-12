package com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.common.common.dto.ChbDeadLine001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage004Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageCommentDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.UploadFileDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportConfirmManage004Mapper {
	/**
     * 
     * 系统生成时查询相同的报表
     * @return count
     * @see
     * @since 1.0
     */
    int querySameCount(Map<String, Object>params);
    
    /**
     *
     * 删除相同的报表
     * @return count
     * @see
     * @since 1.0
     */
    int updateDataStatus(Map<String, Object>params);

    void updateUplodFile(UploadFileDto dto);

    void deleteInfo(Map<String,String> paramMap);

    void saveInfo(ReportConfirmManage004Dto dto);

    void saveInfoComment(ReportConfirmManageCommentDto commentDto);

    void relationFileAndComment(ReportConfirmManageCommentDto commentDto);

    void  deleteUploadFile(@Param("businessId") String businessId);

    void updateConfirmStatus(ReportConfirmManage004Dto dto);

    void updateInfo(ReportConfirmManage004Dto dto);

    ReportConfirmManage004Dto queryInfo(Map<String,String> paramMap);

    List<ReportConfirmManageCommentDto> getInfo(@Param("infoSeqno") String infoSeqno);

    String  getFirstNodeId(@Param("seqNo") String seqNo);

    List<SelectOptionDto> getPrdInfoSelect(Map<String, Object> paramMap);

    ChbDeadLine001Dto getChbDeadline(@Param("marketCode") String marketCode, @Param("holidayDate") String date,
                                     @Param("type") String type, @Param("days")  int days);
}
