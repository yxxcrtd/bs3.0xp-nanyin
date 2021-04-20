package com.joyintech.tams.report.system.prorate.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.prorate.dao.sqlmap.ProRateInfo001Mapper;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
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
}
