package com.joyintech.tams.svreport.interf.dis.prodbonusregister.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.prodbonusregister.dto.DisProdBondsRegistration;
@Repository
public interface DisProdBondsRegistrationMapper {
	
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    void mergeRepProdInfo(List<DisProdBondsRegistration> dtoList);

}