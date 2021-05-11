package com.joyintech.tams.svreport.chbreport.investreport.chbestablishoperation.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chbreport.investreport.chbestablishoperation.dto.ChbEstablishPrdInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbEstablishOperationMapper
 * @Description: 成立报告
 * @Author wangyong
 * @Date 2021-05-11
 * @Version V1.0
 **/
@Repository
public interface ChbEstablishOperationMapper {

    /**
     * 查询产品下拉框
     * @param param
     * @return
     */
    List<ChbEstablishPrdInfo> getEstablishinfo(Map<String, Object> param);

}
