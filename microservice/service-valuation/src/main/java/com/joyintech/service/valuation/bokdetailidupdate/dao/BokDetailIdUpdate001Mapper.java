package com.joyintech.service.valuation.bokdetailidupdate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokDetailIdUpdate001Dto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokDetailIdUpdate002Dto;

/**
 * @author tanxuan
 * @createTime 2019-10-10
 */
@Repository("serviceBokDetailIdUpdate001Mapper")
public interface BokDetailIdUpdate001Mapper {

    List<BokDetailIdUpdate001Dto> selectList(Map<String, Object> params);

//    List<SelectOptionDto> getBooksetId(Map<String, Object> params);
    
    List<SelectOptionDto> getBokDetailId(Map<String, Object> params);
    
    int checkExecute();
    
    int checkInterLog();
    
    void updateBatch1(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch2(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch3(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch4(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch5(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch6(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch7(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch8(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch9(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch10(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch11(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch12(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
//    void updateBatch13(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);
    
    void updateBatch14(@Param("updateList")List<BokDetailIdUpdate002Dto> updateList);

}
