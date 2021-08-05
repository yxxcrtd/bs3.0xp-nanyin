package com.joyintech.service.trade.cstptrdmatchinfo.dao;

import com.joyintech.service.trade.cstp.dto.CstpSelectDto;
import com.joyintech.service.trade.cstptrdmatchinfo.dto.CstpTrdMatchInfoDto;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName trdMatchInfoMapper
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
@Repository
public interface CstpTrdMatchInfoMapper {
    List<CstpTrdMatchInfoDto> findPage(Map<String,Object> map);
    
    int deleteTrdPledgeMatchInfo(TrdMatchInfo trdMatchInfo);

    String getOurdepositaccountnumber(Map<String,Object> map);

    CstpTrdMatchInfoDto getFeeSetting(Map<String,Object> map);

    List<CstpTrdMatchInfoDto> getCstpInfo(Map<String,Object> map);
}

