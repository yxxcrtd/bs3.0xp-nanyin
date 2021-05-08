package com.joyintech.tams.report.system.mstbond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyDto;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.report.system.chbmst.util.ErrdataConvertUtil;
import com.joyintech.tams.report.system.mstbond.dto.MstBondCreditRatingDto;
import com.joyintech.tams.report.system.mstbond.service.MstBondCreditRatingNewService;
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
@PageId("MstBondCreditRating002")
@RequestMapping("/svreport/system/mstbond/MstBondCreditRating002")
public class MstBondCreditRating002Controller extends BaseController {

    @Autowired
    private MstBondCreditRatingNewService mstBondCreditRatingNewService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.评级类型, DictCode.维护数据状态,DictCode.评级机构,DictCode.主体评级_中债);
        MstBondCreditRating mstBondCreditRating=new MstBondCreditRating();
        Map<String,Object> map= RequestUtil.getPageParam();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        if(!"add".equals(map.get("operateType"))){
            if(null!=map.get("gradeDate")){
                String gradeDate = dfs.format(Long.parseLong(String.valueOf(map.get("gradeDate"))));
                map.put("gradeDate",gradeDate);
            }
                List<MstBondCreditRating> MstBondCreditRatings= mstBondCreditRatingNewService.selectOneDate(map);
                if(null!=MstBondCreditRatings && MstBondCreditRatings.size()>0){
                    MstBondCreditRating c=MstBondCreditRatings.get(0);
                    c.setType("update");
                    model.addAttribute("entity", c);
                }else{
                    mstBondCreditRating.setType("add");
                    model.addAttribute("entity", mstBondCreditRating);
                }

        }else{
            mstBondCreditRating.setType("add");
            model.addAttribute("entity",mstBondCreditRating);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) MstBondCreditRatingDto dto, BindingResult result) {
        return commonCheck(result, "save");
    }

    @ResponseBody
    @PostMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) MstBondCreditRating dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        List<MstBondCreditRating> dtoList=new ArrayList<>();
        Map<String, Object> map=new HashMap<>();
        Map<String, Object> mapOld=new HashMap<>();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        String gradeDate = dfs.format(dto.getGradeDate());
        map.put("oCode",dto.getoCode());
        map.put("gradeOrgId",dto.getGradeOrgId());
        map.put("gradeDate",gradeDate);
        map.put("oType",dto.getoType());
        List<MstBondCreditRating> MstBondCreditRatings= mstBondCreditRatingNewService.selectOneDate(map);
        if("add".equals(dto.getType())){
            if(null!=MstBondCreditRatings && MstBondCreditRatings.size()>0){
                webResult.setMessage("已存债券代码、评级机构、评级日期、评级类型的数据", MessageType.错误消息);
            }else{
                try{
                    dto.setCreateUser(LoginUserUtil.getUserName());
                    dto.setCreateTime(new Date());
                    dto.setSvrDataSources("01");
                    dto.setStatus("E");
                    dto.setUpdateUser(LoginUserUtil.getUserName());
                    dto.setUpdateTime(new Date());
                    dtoList.add(dto);
                    mstBondCreditRatingNewService.insertMstBondCreditRatingData(dtoList);
                    webResult.setMessage("保存成功");
                }catch (Exception e){
                    e.printStackTrace();
                    webResult.setMessage("保存失败", MessageType.错误消息);
                }
            }
        }
        //如果 旧评级机构 != 空 && 旧评级日期 != 空 &&旧评级类型 != 空 && 评级机构 != 旧评级机构 && 评级日期 != 旧评级日期 && 评级类型 != 旧评级类型
            if("update".equals(dto.getType())){
                if(null!=dto.getOldGradeOrgId() && null!=dto.getOldGradeDateStr() && null!=dto.getOldOType() &&
                        !dto.getGradeOrgId().equals(dto.getOldGradeOrgId()) &&  !gradeDate.equals(dto.getOldGradeDateStr()) &&
                        !dto.getoType().equals(dto.getOldOType())){
                    if(null!=MstBondCreditRatings && MstBondCreditRatings.size()>0){
                        webResult.setMessage("已存债券代码、评级机构、评级日期、评级类型的数据", MessageType.错误消息);
                    }else{
                        mapOld.put("oCode",dto.getoCode());
                        mapOld.put("gradeOrgId",dto.getGradeOrgId());
                        mapOld.put("gradeDate",gradeDate);
                        mapOld.put("oType",dto.getoType());
                        mapOld.put("gradeResult",dto.getGradeResult());
                        mapOld.put("status","E");
                        mapOld.put("svrDataSources","01");
                        mapOld.put("updateUser",LoginUserUtil.getUserName());
                        mapOld.put("updateTime",new Date());
                        mapOld.put("oldGradeOrgId",dto.getOldGradeOrgId());
                        mapOld.put("oldGradeDate",dto.getOldGradeDateStr());
                        mapOld.put("oldOType",dto.getOldOType());
                        mstBondCreditRatingNewService.updateOne(mapOld);
                        webResult.setMessage("保存成功");
                    }
                }else{
                    mapOld.put("oCode",dto.getoCode());
                    mapOld.put("gradeOrgId",dto.getGradeOrgId());
                    mapOld.put("gradeDate",gradeDate);
                    mapOld.put("oType",dto.getoType());
                    mapOld.put("gradeResult",dto.getGradeResult());
                    mapOld.put("status","E");
                    mapOld.put("svrDataSources","01");
                    mapOld.put("updateUser",LoginUserUtil.getUserName());
                    mapOld.put("updateTime",new Date());
                    mapOld.put("oldGradeOrgId",dto.getOldGradeOrgId());
                    mapOld.put("oldGradeDate",dto.getOldGradeDateStr());
                    mapOld.put("oldOType",dto.getOldOType());
                    mstBondCreditRatingNewService.updateOne(mapOld);
                    webResult.setMessage("保存成功");
                }
            }
        return webResult;
    }

}
