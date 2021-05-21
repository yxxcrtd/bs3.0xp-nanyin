package com.joyintech.tams.report.system.chbemployee.service;

import cn.hutool.core.util.ArrayUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbEmployeeInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.chbemployee.dao.ChbEmployeeInfo001Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChbEmployeeInfo001Service extends CommonExportService<ChbEmployeeInfo> {

    @Autowired
    private ChbEmployeeInfo001Mapper chbEmployeeInfo001Mapper;

    public Page<ChbEmployeeInfo> searchForPage(Map<String, Object> param){
        Page<ChbEmployeeInfo> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        String employeeType = (String)param.get("employeeType");
        String status = (String)param.get("status");
        List<String> selectemployeeType = StringUtil.splitToList(GlobalConstant.COMMA, employeeType);
        List<String> selectStatus = StringUtil.splitToList(GlobalConstant.COMMA, status);
        param.put("selectemployeeType", selectemployeeType);
        param.put("selectStatus", selectStatus);
        page=  super.queryForPage(pagenum,legth,()->chbEmployeeInfo001Mapper.queryForPage(param));
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
    private void dealDataList(List<ChbEmployeeInfo> list) {
        list.forEach(item -> {
            item.setStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.维护数据状态, item.getStatus()));
            item.setEmployeeTypeStr(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.从业人员类型, item.getEmployeeType() ));
            item.setEmployeeSexStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.性别_中债, item.getEmployeeSex()));
            item.setBelongsBankStr(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.所属总行或分支行, item.getBelongsBank()));
            item.setBelongsPlaceStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.销售区域_中债, item.getBelongsPlace()));
            item.setEducationStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.学历, item.getEducation()));
            item.setAcademicDegreeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.学位, item.getAcademicDegree()));
            item.setProfessionalTitleStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.专业技术职称, item.getProfessionalTitle()));
            item.setFinProfessionalCrtfStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.理财专业证书, item.getFinProfessionalCrtf()));
            item.setRegBusiTypeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.登记业务分类, item.getRegBusiType()));
            item.setRegEmployeeTypeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.登记人员类别, item.getRegEmployeeType()));
        });
    }
    public void updateChbEmployeeInfo(List<ChbEmployeeInfo> dtoList) {
        chbEmployeeInfo001Mapper.updateChbEmployeeInfo(dtoList);
        return;
    }
    public List<Integer> selectChbMstCounterPartyData(List<ChbMstCounterParty> dtoList) {
        return chbEmployeeInfo001Mapper.selectChbMstCounterPartyData(dtoList);
    }

    public void insertChbEmployeeInfoData(List<ChbEmployeeInfo> dtoList) {
        chbEmployeeInfo001Mapper.insertChbEmployeeInfoData(dtoList);
        return;
    }

    public List<ChbEmployeeInfo> queryReportList(Map<String, Object> param) {
        String keys = (String)param.get("keys");
        String[] strs =  ArrayUtil.newArray(String.class, 0) ;
        if (StringUtil.isNotEmpty(keys)) {
            strs = keys.split(" ,");
        }
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        List<ChbEmployeeInfo> dtoList=new ArrayList<>();
        for(int i=0;i<strs.length;i++){
            String arr =strs[i];
            String[] s =arr.split(",");
            String employeeType=s[0];
            String identifyNumber=s[1];
            ChbEmployeeInfo ms=new ChbEmployeeInfo();
            ms.setEmployeeType(employeeType);
            ms.setIdentifyNumber(identifyNumber);
            dtoList.add(ms);
        }
        return chbEmployeeInfo001Mapper.queryReportList(dtoList);
    }


    public List<ChbEmployeeInfo> selectOneDate(Map<String, Object> param) {
        return chbEmployeeInfo001Mapper.selectOneDate( param);
    }

    public void updateOne(Map<String, Object> param) {
        chbEmployeeInfo001Mapper.updateOne(param);
    }

}
