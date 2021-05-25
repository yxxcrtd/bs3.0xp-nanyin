package com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.dao;

import com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.dto.ChbNetWorthInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbNetWorthOperationMapper
 * @Description: 净值披露报告
 * @Author wangyong
 * @Date 2021-05-09
 * @Version V1.0
 **/
@Repository
public interface ChbNetWorthOperationMapper {
    /**
     * 获取净值披露
     * @param param
     * @return
     */
    List<ChbNetWorthInfoDto> getNetWorthData(Map<String, Object> param);

}
