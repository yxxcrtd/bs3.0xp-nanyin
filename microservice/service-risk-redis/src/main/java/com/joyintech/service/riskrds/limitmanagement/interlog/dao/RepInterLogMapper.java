package com.joyintech.service.riskrds.limitmanagement.interlog.dao;


import com.joyintech.service.riskrds.limitmanagement.interlog.dto.RepInterLogDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

/**
 * 接口日志管理
 * @author 郑俊平
 * @version 1.0 2019年9月4日
 * @see RepInterLogMapper
 * @since 1.0
 */
@Repository
public interface RepInterLogMapper {

    /**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @see
     * @since 1.0
     */
    List<RepInterLogDto> select(Map<String, Object> pageParam);


    RepInterLogDto selectUuid(@Param("uuid") String uuid);
}
