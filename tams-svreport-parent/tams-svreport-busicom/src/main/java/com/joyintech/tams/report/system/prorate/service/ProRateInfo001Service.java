package com.joyintech.tams.report.system.prorate.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.prorate.dao.ProRateInfo001Mapper;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProRateInfo001Service extends CommonExportService<DisRepCashYield> {

    @Autowired
    private ProRateInfo001Mapper proRateInfo001Mapper;

    public Page<DisRepCashYield> searchForPage(Map<String, Object> param){
        Page<DisRepCashYield> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        String busiId = (String)param.get("busiId");
        String cashType2 = (String)param.get("cashType2");
        String status = (String)param.get("status");
        List<String> selectbusiId = StringUtil.splitToList(GlobalConstant.COMMA, busiId);
        List<String> selectcashType2 = StringUtil.splitToList(GlobalConstant.COMMA, cashType2);
        List<String> selectStatus = StringUtil.splitToList(GlobalConstant.COMMA, status);
        param.put("selectbusiId", selectbusiId);
        param.put("selectcashType2", selectcashType2);
        param.put("selectStatus", selectStatus);
        page=  super.queryForPage(pagenum,legth,()->proRateInfo001Mapper.queryForPage(param));
        List<ChbPrdBaseinfo> list =proRateInfo001Mapper.getProdinfo(param);
        Map<String,String> map=new HashMap<String,String>();
        if(null!=list && list.size()>0){
            for(ChbPrdBaseinfo chbPrdBaseinfo:list){
                map.put(chbPrdBaseinfo.getProdId(),chbPrdBaseinfo.getProdName());
            }
        }
        dealDataList(page.getRoot(),map);
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
    private void dealDataList(List<DisRepCashYield> list,Map<String,String> map) {
        list.forEach(item -> {
            item.setCashType2Name(SysDataUtil.getZZDictCodeAndName(
                    DictCode.信披费用类型, item.getCashType2()));
            item.setStatusName(SysDataUtil.getZZDictCodeAndName(
                    DictCode.维护数据状态, item.getStatus()));
            item.setDataSourceName(SysDataUtil.getZZDictCodeAndName(
                    DictCode.数据来源, item.getDataSource()));
            item.setBusiIdName(map.get(item.getBusiId()));
        });


    }
    private void recoverDataList(List<DisRepCashYield> list) {
        list.forEach(item -> {
            item.setCashType2(SysDataUtil.getZZDictCodeFromName(
                    DictCode.信披费用类型, item.getCashType2()));
            item.setStatus(SysDataUtil.getZZDictCodeFromName(
                    DictCode.维护数据状态, item.getStatus()));
            item.setDataSource(SysDataUtil.getZZDictCodeFromName(
                    DictCode.数据来源, item.getDataSource()));
        });
    }

    public List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param) {
        return proRateInfo001Mapper.getProdinfo( param);
    }
    public List<SysDataDict> getSysDataDict(Map<String, Object> param) {
        return proRateInfo001Mapper.getSysDataDict( param);
    }

    public List<Integer> selectPrdFeeData(List<DisRepCashYield> dtoList) {
        return proRateInfo001Mapper.selectPrdFeeData(dtoList);
    }

    public void insertPrdFeeData(List<DisRepCashYield> dtoList) {
         proRateInfo001Mapper.insertPrdFeeData(dtoList);
        return;
    }

    public void updateDisRepCashYield(List<DisRepCashYield> dtoList) {
        //recoverDataList(dtoList);
        proRateInfo001Mapper.updateDisRepCashYield(dtoList);
        return;
    }

    public void deleteDisRepCashYieldErrorData(List<DisRepCashYield> dtoList) {
        proRateInfo001Mapper.deleteDisRepCashYieldErrorData(dtoList);
        return;
    }

    public List<DisRepCashYield> queryReportList(Map<String, Object> param) {
        return proRateInfo001Mapper.queryReportList( param);
    }

    public List<DisRepCashYield> selectOneDate(Map<String, Object> param) {
        return proRateInfo001Mapper.selectOneDate( param);
    }

    public List<DisPrdFeeRateInfoErrodataWithBLOBs> selectdisPrdFeeRateErrordata(String datauuid) {
        return proRateInfo001Mapper.selectdisPrdFeeRateErrordata( datauuid);
    }
}
