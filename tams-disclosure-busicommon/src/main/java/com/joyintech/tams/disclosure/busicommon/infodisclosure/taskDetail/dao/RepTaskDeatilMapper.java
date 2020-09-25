package com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.entity.InfoDisclosureReadAndGenEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.dto.RepTaskDeatil;
@Repository
public interface RepTaskDeatilMapper {
    int insert(RepTaskDeatil record);

    /**
     * 查询传入日期中是否有持有信息
     * @param generateDate
     * @return
     */
    int  RepTaskDeatil(String flag);
    
    
    int RepTaskDeatilin();
    
    
    /**
     * 更新为无待处理
     * <一句话功能简述>
     * <功能详细描述>
     * @param repTaskDeatil
     * @return
     * @see
     * @since 1.0
     */
    int updateRepTaskDeatil(@Param("flag") String flag,@Param("upflag") String upflag);
    
    
    /**
     * 初始化任务
     * <一句话功能简述>
     * <功能详细描述>
     * @param repTaskDeatil
     * @return
     * @see
     * @since 1.0
     */
    int initRepTaskDetail();
    


}