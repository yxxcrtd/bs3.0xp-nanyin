package com.joyintech.tams.svreport.interf.dis.institutiondata.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.institutiondata.dto.DisAssociatedInstitutions;
import com.joyintech.tams.svreport.interf.dis.prodbaseinfo.dto.DisRepProdInfo;
import com.joyintech.tams.svreport.interf.dis.prodbonusregister.dto.DisProdBondsRegistration;
@Repository
public interface DisAssociatedInstitutionsMapper {
	
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int deleteByModel(@Param("list")List<DisAssociatedInstitutions> dtoList);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int insertByModel(@Param("list")List<DisAssociatedInstitutions> dtoList);

}