package com.joyintech.tams.report.system.prorate.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProrateService extends BaseService<ProRateInfo001Dto> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<ProRateInfo001Dto> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/findPage", paramMap,
                ProRateInfo001Dto.class);
    }
    /**
     *
     * 产品代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    public List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/getProdinfo",param,
                ChbPrdBaseinfo.class);
    }

    public List<SysDataDict> getSysDataDict(Map<String , Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/getSysDataDict",map,
                SysDataDict.class);
    }

    public List<Integer> selectPrdFeeData(List<ProRateInfo001Dto> dtoList) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/Prorate/selectPrdFeeData",dtoList,
                Integer.class);
    }
}
