package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixjgzcgx.dao;

import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixjgzcgx.dto.FxImixJgzcgxDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface FxImixJgzcgxMapper {

    List<FxImixJgzcgxDto> selectPageData(Map para);

    int deleteByPrimaryKey(BigDecimal seqno);

    int insert(FxImixJgzcgxDto record);

    int insertSelective(FxImixJgzcgxDto record);

    FxImixJgzcgxDto selectByPrimaryKey(BigDecimal seqno);

    int updateByPrimaryKeySelective(FxImixJgzcgxDto record);

    int updateByPrimaryKey(FxImixJgzcgxDto record);
}