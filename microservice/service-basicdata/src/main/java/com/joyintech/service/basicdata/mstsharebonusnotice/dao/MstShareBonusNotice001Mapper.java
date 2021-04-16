package com.joyintech.service.basicdata.mstsharebonusnotice.dao;


import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MstShareBonusNotice001Mapper {
    //资产代码下拉框
    List<SelectOptionDto> getShareId(Map<String,Object> key);

    //分页查询
    List<MstShareBounsNoticeDto> selectForPage(Map<String, Object> param);

    void updateByPrimaryKeySelective(MstShareBounsNoticeDto entity);

    void insert(MstShareBounsNoticeDto entity);

    void deleteByPrimaryKey(MstShareBounsNoticeDto entity);


    /**
     *
     * @Description //选择出符合目标的数据
     * @Param [pageParam]
     * @return java.util.List<com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto>
     * @version 1.0
     * @see
    **/
    List<MstShareBounsNoticeDto> selectAllList(Map<String, Object> pageParam);
}
