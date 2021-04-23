package com.joyintech.service.basicdata.mstfundbonusnotice.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.mstfundbonusnotice.dto.MstFundBonusNoticeDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Classname MstFundBonusNotice001Mapper
 * @Description 基金分红公告
 * @author zhuhanlu
 * @date 2020/8/7 13:39
 * @version 1.0
 */
@Repository
public interface MstFundBonusNotice001Mapper {

    //资产代码下拉框
     List<SelectOptionDto> getFundId(Map<String,Object> map);


     List<MstFundBonusNoticeDto> selectForPage(Map<String,Object> map);

    void deleteByPrimaryKey(MstFundBonusNoticeDto entity);

    void insert(MstFundBonusNoticeDto entity);

    void updateByPrimaryKeySelective(MstFundBonusNoticeDto entity);

    List<MstFundBonusNoticeDto> selectAllList(Map<String, Object> pageParam);
}
