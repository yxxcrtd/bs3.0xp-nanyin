package com.joyintech.tams.report.system.chbemployee.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.department.dto.SysDepartmentDto;
import com.joyintech.tams.common.base.entity.ChbEmployeeInfo;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.busicommon.common.constants.GlobalConstant;
import com.joyintech.tams.report.system.chbemployee.dto.ChbEmployeeInfoDto;
import com.joyintech.tams.report.system.chbemployee.service.ChbEmployeeInfoService;
import com.joyintech.tams.report.system.mstbond.dto.MstBondCreditRatingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@PageId("ChbEmployeeInfo002")
@RequestMapping("/svreport/system/chbemployee/ChbEmployeeInfo002")
public class ChbEmployeeInfo002Controller extends BaseController {
    @Autowired
    private ChbEmployeeInfoService chbEmployeeInfoService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.从业人员类型, DictCode.维护数据状态,DictCode.性别_中债,DictCode.所属总行或分支行,
                DictCode.销售区域_中债,DictCode.学历,DictCode.学位,DictCode.专业技术职称,DictCode.理财专业证书,DictCode.登记业务分类,DictCode.登记人员类别);
        ChbEmployeeInfo chbEmployeeInfo=new ChbEmployeeInfo();
        Map<String,Object> map= RequestUtil.getPageParam();
        if(!"add".equals(map.get("operateType"))){
            List<ChbEmployeeInfo> ChbEmployeeInfos= chbEmployeeInfoService.selectOneDate(map);
            if(null!=ChbEmployeeInfos && ChbEmployeeInfos.size()>0){
                ChbEmployeeInfo c=ChbEmployeeInfos.get(0);
                c.setType("update");
                model.addAttribute("entity", c);
            }else{
                chbEmployeeInfo.setType("add");
                model.addAttribute("entity", chbEmployeeInfo);
            }

        }else{
            //新建时获取当前登录人归属部门 作为 所属部门的 初始值
            Map<String, SysDepartmentDto> deptMap = CacheUtil.getAppCache(GlobalConstant.SYS_DEPARTMENT_KEY, Map.class);
            SysDepartmentDto deptDto = deptMap.get(LoginUserUtil.getDeptCode());
            chbEmployeeInfo.setBelongsDept(null!=deptDto?deptDto.getDeptName():"");
            chbEmployeeInfo.setType("add");
            model.addAttribute("entity",chbEmployeeInfo);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbEmployeeInfoDto dto, BindingResult result) {
        return commonCheck(result, "save");
    }

    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbEmployeeInfo dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        List<ChbEmployeeInfo> dtoList=new ArrayList<>();
        Map<String, Object> map=new HashMap<>();
        Map<String, Object> mapOld=new HashMap<>();
        map.put("identifyNumber",dto.getIdentifyNumber());
        map.put("employeeType",dto.getEmployeeType());
        List<ChbEmployeeInfo> ChbEmployeeInfos= chbEmployeeInfoService.selectOneDate(map);
        if("add".equals(dto.getType())){
            if(null!=ChbEmployeeInfos && ChbEmployeeInfos.size()>0){
                webResult.setMessage("已存身份证号、从业人员类型的数据", MessageType.错误消息);
            }else{
                try{
                    dto.setCreateUser(LoginUserUtil.getUserName());
                    dto.setCreateTime(new Date());
                    dto.setStatus("E");
                    dto.setUpdateUser(LoginUserUtil.getUserName());
                    dto.setUpdateTime(new Date());
                    dtoList.add(dto);
                    chbEmployeeInfoService.insertChbEmployeeInfoData(dtoList);
                    webResult.setMessage("保存成功");
                }catch (Exception e){
                    e.printStackTrace();
                    webResult.setMessage("保存失败", MessageType.错误消息);
                }
            }
        }
        //如果 旧身份证号 != 空  && 旧从业人员类型 != 空  && 身份证号 != 旧身份证号 && 从业人员类型 != 旧从业人员类型
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        if("update".equals(dto.getType())){
            if(null!=dto.getOldIdentifyNumber() && null!=dto.getOldEmployeeType()  &&
                    !dto.getIdentifyNumber().equals(dto.getOldIdentifyNumber()) &&
                    !dto.getEmployeeType().equals(dto.getOldEmployeeType())){
                if(null!=ChbEmployeeInfos && ChbEmployeeInfos.size()>0){
                    webResult.setMessage("已存身份证号、从业人员类型的数据", MessageType.错误消息);
                }else{
                    mapOld.put("identifyNumber",dto.getIdentifyNumber());
                    mapOld.put("employeeType",dto.getEmployeeType());
                    mapOld.put("employeeName",dto.getEmployeeName());
                    mapOld.put("employeeSex",dto.getEmployeeSex());
                    mapOld.put("issuePartyId",dto.getIssuePartyId());
                    mapOld.put("belongsBank",dto.getBelongsBank());
                    mapOld.put("belongsPlace",dto.getBelongsPlace());
                    mapOld.put("companyName",dto.getCompanyName());
                    mapOld.put("belongsDept",dto.getBelongsDept());
                    mapOld.put("position",dto.getPosition());
                    mapOld.put("education",dto.getEducation());
                    mapOld.put("academicDegree",dto.getAcademicDegree());
                    mapOld.put("firsttime2work",dto.getFirsttime2work());
                    mapOld.put("firsttime2finwork",dto.getFirsttime2finwork());
                    mapOld.put("professionalTitle",dto.getProfessionalTitle());
                    mapOld.put("finProfessionalCrtf",dto.getFinProfessionalCrtf());
                    mapOld.put("finTrainingNo",dto.getFinTrainingNo());
                    mapOld.put("awards",dto.getAwards());
                    mapOld.put("officeTel",dto.getOfficeTel());
                    mapOld.put("mobileTel",dto.getMobileTel());
                    mapOld.put("mail",dto.getMail());
                    mapOld.put("regBusiType",dto.getRegBusiType());
                    mapOld.put("regEmployeeType",dto.getRegEmployeeType());
                    mapOld.put("remark",dto.getRemark());
                    mapOld.put("status","E");
                    mapOld.put("updateUser",LoginUserUtil.getUserName());
                    mapOld.put("updateTime",dfs.format(new Date()));
                    mapOld.put("oldEmployeeType",dto.getOldEmployeeType());
                    mapOld.put("oldIdentifyNumber",dto.getOldIdentifyNumber());
                    chbEmployeeInfoService.updateOne(mapOld);
                    webResult.setMessage("保存成功");
                }
            }else{
                mapOld.put("identifyNumber",dto.getIdentifyNumber());
                mapOld.put("employeeType",dto.getEmployeeType());
                mapOld.put("employeeName",dto.getEmployeeName());
                mapOld.put("employeeSex",dto.getEmployeeSex());
                mapOld.put("issuePartyId",dto.getIssuePartyId());
                mapOld.put("belongsBank",dto.getBelongsBank());
                mapOld.put("belongsPlace",dto.getBelongsPlace());
                mapOld.put("companyName",dto.getCompanyName());
                mapOld.put("belongsDept",dto.getBelongsDept());
                mapOld.put("position",dto.getPosition());
                mapOld.put("education",dto.getEducation());
                mapOld.put("academicDegree",dto.getAcademicDegree());
                mapOld.put("firsttime2work",dto.getFirsttime2work());
                mapOld.put("firsttime2finwork",dto.getFirsttime2finwork());
                mapOld.put("professionalTitle",dto.getProfessionalTitle());
                mapOld.put("finProfessionalCrtf",dto.getFinProfessionalCrtf());
                mapOld.put("finTrainingNo",dto.getFinTrainingNo());
                mapOld.put("awards",dto.getAwards());
                mapOld.put("officeTel",dto.getOfficeTel());
                mapOld.put("mobileTel",dto.getMobileTel());
                mapOld.put("mail",dto.getMail());
                mapOld.put("regBusiType",dto.getRegBusiType());
                mapOld.put("regEmployeeType",dto.getRegEmployeeType());
                mapOld.put("remark",dto.getRemark());
                mapOld.put("status","E");
                mapOld.put("updateUser",LoginUserUtil.getUserName());
                mapOld.put("updateTime",dfs.format(new Date()));
                mapOld.put("oldEmployeeType",dto.getOldEmployeeType());
                mapOld.put("oldIdentifyNumber",dto.getOldIdentifyNumber());
                chbEmployeeInfoService.updateOne(mapOld);
                webResult.setMessage("保存成功");
            }
        }
        return webResult;
    }

}
