package com.joyintech.tams.svreport.chbreport.investreport.ChbExpireOperation.dao;


import com.joyintech.tams.svreport.chbreport.investreport.ChbExpireOperation.dto.ChbExpirePrdInfo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbExpireOperationMapper
 * @Description: 到期报告
 * @Author wangyong
 * @Date 2021-05-12
 * @Version V1.0
 **/
@Repository
public interface ChbExpireOperationMapper {

    /**
     * 获取到期公告数据
     * @param param
     * @return
     */
    List<ChbExpirePrdInfo> getExpireinfo(Map<String, Object> param);

}
