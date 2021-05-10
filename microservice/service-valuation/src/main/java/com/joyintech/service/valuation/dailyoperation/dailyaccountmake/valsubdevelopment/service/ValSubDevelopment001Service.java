package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dao.ValSubDevelopment001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dto.ValSubDevelopment001Dto;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.valuation.common.auxiliary.dto.BokBookdetailCurrentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("backValSubDevelopment001Service")
public class ValSubDevelopment001Service extends CommonExportService<ValSubDevelopment001Dto> {
    @Autowired
    private ValSubDevelopment001Mapper valSubDevelopment001Mapper;

    /**
     * 查询账套模板
     */
    public Page<SelectOptionDto> selectBooksetId(int page, int length, Map<String, Object> params){
        return this.queryForPageForSelectReturn(page,length,()->valSubDevelopment001Mapper.selectBooksetId(params));
    }

    /**
     * 查询带扩展科目号
     */
    public Page<SelectOptionDto> selectThirdSubjectId(int page, int length, Map<String, Object> params){
        return this.queryForPageForSelectReturn(page,length,()->valSubDevelopment001Mapper.selectThirdSubjectId(params));
    }

    // 删除业务明细科目表(BOK_BUSS_DETSUB)
    public int deleteBokBussDetSub(Map<String, Object> param){
        return valSubDevelopment001Mapper.deleteBokBussDetSub(param);
    }
    // 插入删除的数据
    public int insertBokBussDetSub(BokBussDetsub sub){
        return valSubDevelopment001Mapper.insertBokBussDetSub(sub);
    }

    // 更新账务明细代码表(BOK_DETAIL_ID)
    public int updateBokDetailId(BokBookdetailCurrentDto dto){
        return valSubDevelopment001Mapper.updateBokDetailId(dto);
    }
    // 更新账务明细历史表(BOK_BOOKDETAIL_ALL)
    public int updateBokBookDetailAll(BokBookdetailCurrentDto dto){
        return valSubDevelopment001Mapper.updateBokBookDetailAll(dto);
    }
    // 更新账务明细当前表(BOK_BOOKDETAIL_CURRENT)
    public int updateBokBookDetailCurrent(BokBookdetailCurrentDto dto){
        return valSubDevelopment001Mapper.updateBokBookDetailCurrent(dto);
    }
}
