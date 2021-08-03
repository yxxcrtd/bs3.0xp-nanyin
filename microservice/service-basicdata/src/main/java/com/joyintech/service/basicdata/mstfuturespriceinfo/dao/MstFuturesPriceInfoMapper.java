package com.joyintech.service.basicdata.mstfuturespriceinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MstFuturesPriceInfoMapper {
    List<MstFuturesPriceInfoDto> findPage(Map<String,Object> map);

    List<SelectOptionDto> getContractId(Map<String,Object> map);

    List<SelectOptionDto> getWindCode(Map<String,Object> map);

    MstFuturesPriceInfoDto selectByKey(Map<String,Object> map);

    void insert(MstFuturesPriceInfoDto dto);

    void delete(MstFuturesPriceInfoDto dto);

    void update(MstFuturesPriceInfoDto dto);
}
