package com.joyintech.component.svrreport.chb.feedbackmsg.dao;


import com.joyintech.component.svrreport.chb.feedbackmsg.dto.ChbFeedbackFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbFeedbackFileComponentMapper {
    int deleteByPrimaryKey(String rtnRepMesNo);

    int insert(ChbFeedbackFile record);

    int insertSelective(ChbFeedbackFile record);

    ChbFeedbackFile selectByPrimaryKey(String rtnRepMesNo);

    int updateByPrimaryKeySelective(ChbFeedbackFile record);

    int updateByPrimaryKeyWithBLOBs(ChbFeedbackFile record);

    int updateByPrimaryKey(ChbFeedbackFile record);

    /**
     * 查询待处理文件列表
     * @return 待处理文件列表
     */
    List<ChbFeedbackFile> queryChbFeedbackFileList(@Param("handleTypeUndo")String handleTypeUndo, @Param("rtnRepMesNo")String rtnRepMesNo);
}