package com.joyintech.service.period.trdsecovertransfer.dao;

import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationZrDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TrdSecOverTransferMapper {
    List<TrdSecOverdueDurationZrDto> selectPageData(Map<String, Object> param);
}
