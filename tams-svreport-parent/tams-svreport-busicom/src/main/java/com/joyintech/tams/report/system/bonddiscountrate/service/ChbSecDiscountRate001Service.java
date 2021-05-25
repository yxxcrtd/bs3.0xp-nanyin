package com.joyintech.tams.report.system.bonddiscountrate.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.bonddiscountrate.dao.ChbSecDiscountRate001Mapper;
import com.joyintech.tams.report.system.bonddiscountrate.dto.ChbSecDiscountRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChbSecDiscountRate001Service extends CommonExportService<ChbSecDiscountRateDto> {

    @Autowired
    private ChbSecDiscountRate001Mapper chbSecDiscountRate001Mapper;


    public Page<ChbSecDiscountRateDto> searchForPage(Map<String, Object> param){
        Page<ChbSecDiscountRateDto> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        List<String> selectSecType = StringUtil.splitToList(GlobalConstant.COMMA, String.valueOf(param.get("secType")));
        param.put("selectSecType", selectSecType);
        page=  super.queryForPage(pagenum,legth,()->chbSecDiscountRate001Mapper.queryForPage(param));
        dealDataList(page.getRoot());
        return page;
    }

    /**
     * 处理查询结果 <功能详细描述>
     *
     * @param list
     *            待处理数据
     * @see
     * @since 1.0
     */
    private void dealDataList(List<ChbSecDiscountRateDto> list) {
        list.forEach(item -> {
            item.setSecTypeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.中债债券类型, item.getSecType()));
            item.setIssueTypeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.债券类资产发行方式, item.getIssueType()));
            item.setGradeTypeStr(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.中债评级类型, item.getGradeType()));
        });
    }

    public ChbSecDiscountRateDto selectOneDate(Map<String, Object> param) {
        return chbSecDiscountRate001Mapper.selectOneDate( param);
    }

    public void save(ChbSecDiscountRateDto dto){
        AjaxResult webResult = new AjaxResult();
        Map<String, Object> map=new HashMap<>();
        map.put("datauuid",dto.getDatauuid());
        ChbSecDiscountRateDto infoDto= chbSecDiscountRate001Mapper.selectOneDate(map);
        if (StringUtil.isNotEmpty(infoDto)){
            chbSecDiscountRate001Mapper.updateOne(dto);
        }
    }


}
