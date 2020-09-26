package com.joyintech.tams.svreport.interf.dis.repfeetrdinterf.dao;

import com.joyintech.tams.svreport.interf.dis.repfeetrdinterf.dto.DisRepFeeTrd;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DisRepFeeTrdMapper {
    int insert(List<DisRepFeeTrd> list);

    int insertSelective(DisRepFeeTrd record);
    
    
    int mergeRepFeeTrdInfo (List<DisRepFeeTrd> DisRepFeeTrd);

    int insertBasicByBasicInfo(DisRepFeeTrd record);
    
    void deleteDisRepFeeTrd();
    

    void mergeDisRepFeeTrdInfo(List<DisRepFeeTrd> DisRepFeeTrd);
    
    DisRepFeeTrd selectCashIdByProdidAndCashType2(DisRepFeeTrd record);

    /**
     * 删除数据
     * <功能详细描述>
     * @param repFeeTrdDto
     * @see
     * @since 1.0
     */
    int deleteByPaydate(DisRepFeeTrd repFeeTrdDto);
}