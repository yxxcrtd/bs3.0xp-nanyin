package com.joyintech.tams.report.system.prorate.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.prorate.dao.ProRateInfo001Mapper;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProRateInfo001Service extends CommonExportService<ProRateInfo001Dto> {

    @Autowired
    private ProRateInfo001Mapper proRateInfo001Mapper;

    public Page<ProRateInfo001Dto> searchForPage(Map<String, Object> param){
        Page<ProRateInfo001Dto> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        page=  super.queryForPage(pagenum,legth,()->proRateInfo001Mapper.queryForPage(param));
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
    private void dealDataList(List<ProRateInfo001Dto> list) {
        list.forEach(item -> {
            item.setCashType2(SysDataUtil.getZZDictCodeAndName(
                    DictCode.信披费用类型, item.getCashType2()));
        });
    }

    public List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param) {
        return proRateInfo001Mapper.getProdinfo( param);
    }
    public List<SysDataDict> getSysDataDict(Map<String, Object> param) {
        return proRateInfo001Mapper.getSysDataDict( param);
    }

    public List<Integer> selectPrdFeeData(List<ProRateInfo001Dto> dtoList) {
        return proRateInfo001Mapper.selectPrdFeeData(dtoList);
    }
}
