package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dao.ValSubjectInfo001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubjectinfo.dto.ValSubjectInfo001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

@Service("backValSubjectInfo001Service")
public class ValSubjectInfo001Service extends CommonExportService<ValSubjectInfo001Dto> {

    @Autowired
    private ValSubjectInfo001Mapper valSubjectInfo001Mapper;

    /**
     * 电子对账页面table查询 <功能详细描述>
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValSubjectInfo001Dto> findPage(int page, int length, Map<String, Object> params)
        throws Exception {
        params.put("BOOKSET_TYPE_TEMPLATE", DictCodeList.账套类别_账套模板.getValue());
        params.put("INTER_TYPE_SUBJECTINFO", DictCodeListExt1.深圳通接口类型_科目信息表.getValue());
        return super.queryForPage(page, length,
            () -> valSubjectInfo001Mapper.queryPageList(params));
    }

    /**
     * 页面产品下拉框查询方法 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(int page, int length, Map<String, Object> map) {
        map.put("BOOKSET_TYPE_TEMPLATE", DictCodeList.账套类别_账套模板.getValue());
        return super.queryForPageForSelectReturn(page, length,
            () -> valSubjectInfo001Mapper.getFinprodId(map));
    }

}
