package com.joyintech.tams.disclosure.information.config.repprodinfo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dao.DisProdInfoCommonMapper;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.ProdInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProdInfoCommonService extends CommonExportService<ProdInfoDto> {

    @Autowired
    private DisProdInfoCommonMapper commonMapper;


    /**
     * 获取产品登记编码
     * @param
     * @return
     */
    public Page<SelectOptionDto> getRegistNo() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page"))
                , Integer.parseInt((String) pageParam.get("length"))
                ,()->commonMapper.getRegistNo(pageParam));

    }

    /**
     * 根据产品登记编码 获取产品主代码和产品名称,支持联动
     * @param
     * @return
     */
    public Page<SelectOptionDto> getProdIdAndNameByRegistNo() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page"))
                , Integer.parseInt((String) pageParam.get("length"))
                ,()->commonMapper.getRegistNo(pageParam));
    }




}
